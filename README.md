# Bookstore_Management
Online-Book-Store Management Using SPRING BOOT and REST API
Project Description:
This is a Mini-project to develop a Spring Boot based RESTful API service for a basic Online Book Store using in-memory data source.Authentication and Authorization is provided using Spring Security.It has both user and admin privilege.

Access and Url Details:
General Access:
Create New Account or Register
POST METHOD :{baseUrl}/api/auth/regjster
{"username":"pavithravasan",
"password":"Pavi@123",
"email":"pavithravasan@gmail.com",
"role":"USER"
}
Login
POST METHOD :{baseUrl}/api/auth/login
Request Body :{
    "email":"pavithravasan@gmail.com",
    "password":"Pavi@123"
}
Update Profile
 PUT METHOD  :{baseUrl}/api/auth/updateProfile
Request Body : {
 "username":"pavithravasan",
 "password":"Pavi@123",
 "email":"pavithravasan@gmail.com"
}

Admin Have Following Access for this online store site:-
Add New Books :
 POST METHOD :{baseUrl}/api/books 
 Request Body :{
    "title":"Harry Potter",
    "description":"Harry Potter is a series of seven fantasy novels written by British author J. K. Rowling. The novels chronicle the lives of a young wizard, Harry Potter, and his 
                   friends Hermione Granger and Ron Weasley, all of whom are students at Hogwarts School of Witchcraft and Wizardry. ",
    "price":880.00,
    "genre":"Fantasy",
    "author":{
   *if author is already present mention author id  or else you can omit this field and enter name and details *    
       "authorId":1,
       "name" :"JK Rowling",
       "details" : "J. K. Rowling, is a British author and philanthropist. She wrote Harry Potter, a seven-volume fantasy series published from 1997 to 2007."
     }
  }

Add New Author 
 POST METHOD :{baseUrl}/api/author 
 Request Body :{
     "name" :"JK Rowling",
     "details" : "J. K. Rowling, is a British author and philanthropist. She wrote Harry Potter, a seven-volume fantasy series published from 1997 to 2007."
     }
View Books Available
 -> To get all books :
      GET METHOD :{baseUrl}/api/books
->  To get book by id
      GET METHOD :{baseUrl}/api/books/{isbn}
Remove Book By id 
 DELETE METHOD :{baseUrl}/api/books/{isbn}
Update Book Details
   PUT METHOD  :{baseUrl}/api/books/{isbn}
   RequestBody :{
    "title":"Harry Potter Part-2",
    "description":"Harry Potter is a series of seven fantasy novels written by British author J. K. Rowling. The novels chronicle the lives of a young wizard, Harry Potter, and his 
                  friends Hermione Granger and Ron Weasley, all of whom are students at Hogwarts School of Witchcraft and Wizardry. ",
    "price":880.00,
    "genre":"Fantasy",
    "author":{
    *if author is already present mention author id  or else you can omit this field and enter name and details *    
       "authorId":1,
       "name" :"JK Rowling",
       "details" : "J. K. Rowling, is a British author and philanthropist. She wrote Harry Potter, a seven-volume fantasy series published from 1997 to 2007."
     }
  }
  Since we use put method we have to pass the entire object in the request.
View Author Details
  GET METHOD :{baseUrl}/api/books/{isbn}

Users Have Following Access for this online store site:-
View Books Available
 -> To get all books :
      GET METHOD :{baseUrl}/api/books
->  To get book by id
      GET METHOD :{baseUrl}/api/books/{isbn}
Add Review to the Books
  POST METHOD :{baseUrl}/api/books/{isbn}/reviews
  Request Body:{"starRating":4,
  "bookReview":"Good to use book"}
View Author Details
  GET METHOD :{baseUrl}/api/books/{isbn}

Tools/Framework:

H2 Database : InMemory Database
JPA: Java Persistence API(JPA) is a Java programming interface specification that describes the management of relational data in applications using Java Platform.
JUNIT/Mockito: Junit is the unit testing framework for the Java programming language. Mockito is a mocking framework.
Lombok: Project Lombok is a java library that automatically plugs into editor and build tools, spicing up java. Getter, Setters, Construcutors can be created with annotation without writing the code with the help of Lombok.
ModelMapper: ModelMapper is a simple, intelligent, object mapping tool. It is used to map the object in this project.
Spring Security : Jwt Based Authorization and Authentication is provided using Spring Security

Steps To Run The Application
This project uses project Lombok. So, if you do not have Lombok plugins inserted into your IDE, please install it into your IDE.
Add h2 dependency in your pom.xml file to use h2 in-memory database in your project 
Now in your IDE, open the application.properties file, change the spring datasource url, username and password. Right now the spring datasource url=”spring.datasource.url= "jdbc:h2:mem:bookshop” which is likely to be same in your case too. If not please put on your datasource url. Right now Spring datasource name is “sa” and password is "" which is likely to change in your case. Please put on your database username and password in these fields.
Now you can run your application. The table “book” will be automatically created in “bookstore”. By default, it runs in port:8080.
Once you have set up your database, you can also run the unit tests.


Thanks 
Pavithra.S
Software Engineer

