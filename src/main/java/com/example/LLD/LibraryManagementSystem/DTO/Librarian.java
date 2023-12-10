package com.example.LLD.LibraryManagementSystem.DTO;
import com.example.LLD.LibraryManagementSystem.Constants.Constants.Constants;
import com.example.LLD.LibraryManagementSystem.Enums.BookFormat;
import com.example.LLD.LibraryManagementSystem.Enums.BookStatus;
import com.example.LLD.LibraryManagementSystem.Repo.*;


import java.time.temporal.ChronoUnit;
import java.util.*;

public class Librarian extends Members implements librarianAdmin {

    BookRepo bookRepo=new BookRepo();
    TransactionRepo transactionRepo= new TransactionRepo();
    AccountRepo accountRepo= new AccountRepo();
    MemberRepo memberRepo= new MemberRepo();
    WishedbookRepo wishedbookRepo= new WishedbookRepo();


    Scanner sc = new Scanner(System.in);
    @Override
    public void AddbookItem(BookItem bookItem) {
        List<BookItem> bookItemList= new ArrayList<>();
        
        System.out.println("Enter the Book ISBN NO");
        String isbn=sc.next();
        System.out.println("Enter the Book Name");
        String bookname=sc.next();
        System.out.println("Enter the Book Authors");
        String author=sc.next();
        System.out.println("Enter the No of Book Copies");
        int noofCopies=sc.nextInt();
        for(int i=0;i<noofCopies;i++){
            BookItem currBookItem = new BookItem();
            currBookItem.setIsbn(isbn);
            currBookItem.setTitle(bookname);
            currBookItem.setAuthor(Collections.singletonList(author));
            System.out.println("Enter the RackNumber");
            int rackLoc = sc.nextInt();
            System.out.println("Enter the Barcode");
            String barcode = sc.next();
            System.out.println("is the Book refernce book ?");
            boolean reference = sc.nextBoolean();
            currBookItem.setBookFormat(BookFormat.HARDCOPY);
            currBookItem.setBarcode(barcode);
            currBookItem.setReference(reference);
            currBookItem.setRack(new Rack(rackLoc));
            currBookItem.setBookStatus(BookStatus.AVAILABLE);
            bookItemList.add(currBookItem);
        }
        bookRepo.addBook(isbn,bookItemList);

    }

    @Override
    public void DeletebookItem(BookItem bookItem, String barcode) {
        if(bookRepo.ifBookExists(bookItem.getIsbn()))
                bookRepo.removeBook(bookItem,barcode);
        else
            System.out.println("No Book found . Hence can't be Deleted");
    }

    @Override
    public void checkoutBookItem(Account account,BookItem checkoutBookItem) {
       if(bookRepo.ifBookExists(checkoutBookItem.getIsbn())){
           List<BookItem> currbookItemList=bookRepo.getbookDetails(checkoutBookItem.getIsbn());
           for (BookItem bookitem: currbookItemList) {
               if(checkoutBookItem.getBarcode().equalsIgnoreCase(bookitem.getBarcode())) {
                   //Reference book cant be borrowed
                   if (bookitem.isReference()) {
                       System.out.println("This is a reference book. Cant be Boorowed");
                       return;}
                   else if(!bookitem.isReference() && account.getMembers().getBookIssued().size() >= Constants.MAX_NO_OF_BOOKS_ISSUED){
                       //User breached the count of Max no of Books allowed to hold
                       System.out.println("Max number of Books that user can hold is " + Constants.MAX_NO_OF_BOOKS_ISSUED +
                               "You are not allowed to issue further");
                       return;
                   }
                   else if(isbookReservedbyAnyUser(bookitem,currbookItemList))
                       System.out.println("The book is reserved by Users before you. Hence cant be borrowed");
                       return;
                   }
                    else{
                     Map<String,List<BookItem>> bookStatusCount = getCurrentBookStatusCount(currbookItemList);
                        if(bookStatusCount.get(BookStatus.AVAILABLE).size()>0){
                        List<BookItem>availBookList=bookStatusCount.get(BookStatus.AVAILABLE);
                                for (BookItem availbookitem:availBookList) {
                                    if (availbookitem.getBarcode().equalsIgnoreCase(checkoutBookItem.getBarcode())) {
                                        System.out.println("The Book Can be borowed. please wait for System to update it");
                                        availbookitem.setBookStatus(BookStatus.BORROWED);
                                        availbookitem.setIssuedate(new Date());
                                        availbookitem.setDuedate(getDuedate());
                                        account.getMembers().getBookIssued().add(availbookitem);
                                        System.out.println("Book is assigned to you . Happy Reading !!!");
                                    }
                                    else
                                        System.out.println("The book is not in AVL State");
                                }
                            }
                        }
                    }
                }
            }

    private Date getDuedate() {
        Date today = new Date();
        long ltime = today.getTime()+10*24*60*60*1000;
        Date today10 = new Date(ltime);
        return today10;
    }

    public Map<String,List<BookItem>> getCurrentBookStatusCount(List<BookItem> currbookItemList) {
        Map<String,List<BookItem>> currentBookStatusCount= new HashMap<>();
        List<BookItem> availBookItemList=new ArrayList<>();
        List<BookItem> borrowedBookItemList=new ArrayList<>();
        List<BookItem> reservedBookItemList=new ArrayList<>();
        List<BookItem> lostBookItemList=new ArrayList<>();
        for (BookItem bookItem:currbookItemList) {
            if(bookItem.getBookStatus().equals(BookStatus.AVAILABLE))
                availBookItemList.add(bookItem);
            if(bookItem.getBookStatus().equals(BookStatus.BORROWED))
                borrowedBookItemList.add(bookItem);
            if(bookItem.getBookStatus().equals(BookStatus.RESERVED))
                reservedBookItemList.add(bookItem);
            if(bookItem.getBookStatus().equals(BookStatus.LOST))
                lostBookItemList.add(bookItem);
        }
        currentBookStatusCount.put(String.valueOf(BookStatus.AVAILABLE),availBookItemList);
        currentBookStatusCount.put(String.valueOf(BookStatus.BORROWED),borrowedBookItemList);
        currentBookStatusCount.put(String.valueOf(BookStatus.RESERVED),reservedBookItemList);
        currentBookStatusCount.put(String.valueOf(BookStatus.LOST),lostBookItemList);
        return currentBookStatusCount;
    }

    private boolean isbookReservedbyAnyUser(BookItem bookitem, List<BookItem> currbookItemList) {
        for (BookItem currbookitem:currbookItemList)
            if(currbookitem.getBarcode().equalsIgnoreCase(bookitem.getBarcode()))
               return currbookitem.isReference();
        return false;
    }

    @Override
    public void renewBookItem(Account account,BookItem bookItem) {
        returnBookItem(account,bookItem);
        if(bookItem.getBookStatus().equals(BookStatus.AVAILABLE))
            checkoutBookItem(account,bookItem);
    }

    @Override
    public void returnBookItem(Account account, BookItem returnedBookItem) {
        if (bookRepo.ifBookExists(returnedBookItem.getIsbn())) {
            List<BookItem> currbookItemList = bookRepo.getbookDetails(returnedBookItem.getIsbn());
            for (BookItem bookitem : currbookItemList) {
                if (returnedBookItem.getBarcode().equalsIgnoreCase(bookitem.getBarcode())){
                    if(isPenaltyNeeded(returnedBookItem,bookitem)){
                        //if penalty need to be imposed
                        int fine =calculateFine(bookitem);
                        System.out.println("Your penalty fee is Rs" + fine);
                        System.out.println("Please pay");
                        
                        int latefee=sc.nextInt();
                        if(latefee==fine) {
                            createTransactaionalStatemment(account,bookitem,latefee,new Date());
                            System.out.println("Penalty is paid and Book is Returned. Thank you");
                        }
                    }
                    else{
                        System.out.println("Book is returned before due date. Thank you");
                    }
                    bookitem.setBookStatus(BookStatus.AVAILABLE);
                    bookitem.setIssuedate(null);
                    bookitem.setDuedate(null);
                    account.getMembers().getBookIssued().remove(bookitem);
                    account.getMembers().getReturnedBook().add(bookitem);
                    notifyifBookReservedBy(bookitem);
                }
            }
        }
        else
        System.out.println("Book doesnt belong to the Library!!!");
    }
    private void notifyifBookReservedBy(BookItem bookitem) {
        for (Account currAccount: wishedbookRepo.getWishList(bookitem.getIsbn())) {
            for (BookItem wisehedbookItem: currAccount.getMembers().getReservedBook()) {
                if(wisehedbookItem.getIsbn().equalsIgnoreCase(bookitem.getIsbn()))
                  System.out.println("Your wishlist Book can be true now . Please click on link to Borrow it");
                boolean bookNeeded = sc.nextBoolean();
                  if(bookNeeded){
                      checkoutBookItem(currAccount,bookitem);
                      currAccount.getMembers().getBookIssued().add(bookitem);
                      currAccount.getMembers().getReservedBook().remove(bookitem);}
                  else{
                      System.out.println("Based on your Response, book is not needed anymore ");
                      currAccount.getMembers().getReservedBook().remove(bookitem);
                  }
            }
            
        }
    }

    private void createTransactaionalStatemment(Account account, BookItem bookitem, int latefee, Date date) {
        Transaction transaction= new Transaction();
        transaction.setAccount(account);
        transaction.setIsbn(bookitem.getIsbn());
        transaction.setFees(latefee);
        transaction.setPaymentDate(date);
        transaction.setDueDate(bookitem.getDuedate());
        transaction.setIssueDate(bookitem.getIssuedate());
        transactionRepo.addTransaction(transaction);
    }

    private int calculateFine( BookItem bookitem) {
        Date todaydate= new Date();
        int days= (int) ChronoUnit.DAYS.between(todaydate.toInstant(), bookitem.getDuedate().toInstant());
        return days*3;
    }

    private boolean isPenaltyNeeded(BookItem returnedBookItem, BookItem bookitem) {
        Date todaydate= new Date();
        if(todaydate.compareTo(returnedBookItem.getDuedate())>0)
            return true;
        else
            return false;
    }

    @Override
    public void createAccount() {
        System.out.println("Enter the name");
        String name=sc.next();
        System.out.println("Enter the email");
        String email=sc.next();
        System.out.println("Enter the phone");
        String phone=sc.next();
        Members newMember=new Members();
        newMember.setName(name);
        newMember.setEmailaddress(email);
        newMember.setPhoneno(phone);
        newMember.setMemberId(Integer.parseInt(String.valueOf(UUID.randomUUID())));
        newMember.setDateofJoining(new Date());
        newMember.setCardNo(new LibraryCard(Integer.parseInt(String.valueOf(UUID.randomUUID())),new Date(),"SYSTEM",
                String.valueOf(UUID.randomUUID()),newMember.getMemberId()));
        memberRepo.addMembers(newMember);
        Account newAccount= new Account();
        newAccount.setMembers(newMember);
        accountRepo.addAccount(newAccount);
    }

    @Override
    public void deleteAccount(Account account) {
        accountRepo.removeAccount(account);
    }

}
