# Bookstore_Management
Online-Book-Store Management Using SPRING BOOT and REST API
Project Description:
This is a Mini-project to develop a Spring Boot based RESTful API service for a basic Online Book Store with the
option to use in-memory data source. Include detailed HTTP methods and URL structures. Authentication and Authorization is provided using Spring Security.It has both user and admin privilege.
Admin Have Following Access for this online store site:-
Add New Books
Add New Author 
View Books Available
Remove Books
Update Book Details
View Author Details
Update Admin profile

Users Have Following Access for this online store site:-
Create New Account or Register
Login
View Available Books
Add Review to the Books
View Author Details
Update User Profile

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

