Requirements
From User side
1) User should be able to search a book using isbn number , title , genre , author and publication date
2) User should be able to issue a book provided if #of book issued by person is less than the allowed (let say 5)
3) User should be able to reserve a book if #ofcopies not avaialble
4) User should be notified once the book is avaialble to issue
5) User should be able to return the book and should be charged for Penalty (if days >=10)


From System admin side
1) User should be enrolled in system and any update in bio is allowed too ->USER CRUD
2) Librarian or Libarary members are also enrolled in system -> CRUD
3) Only System admin should be able to add book / Users
4) Each book in a system shouuld be identified using Physical address ( which row and which rack)
5) system should be able to give info abput book checkout current history and get #of avail copies
6) Each book and member should have a unique bar code


NOTES
->Always impkement the core functionality (Authentication CRUD members need not be taken care of)
-> for any payemnet related stuff -> USE PROXY DESIGN PATTERN
-> Have rough code in mind
-> Then create class Diagram