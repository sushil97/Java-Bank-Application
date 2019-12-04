# Java-Bank-Application
This is a java based project on banking. In this project we are creating a retail bank which collects money from customers and in return gives them an interest in predetermined interest rate. We are trying to automate this process of interest calculation. This system will automatically calculate amount daily for each account having balance more than 0. At the end of every month total interest accumlated by each user will get added to their account balance.
The developed retail banking system for the project calculates interest daily for a user, this interest is stored separately in a table. At the end of the month the sum of all interests in the table is calculated , tax is deducted from the interests earned and the value after deducting the tax is inserted into the table which stores the interest earned for each month (the question says to display report of earning on a periodic basis we consider here that period to be as a month).

# Technology stack 
Java, JDBC, AWT Swing, MYSQL

The below three outputs are controlled by the admin:
1. Calculation of interest: What we have understood from this is that this requires the depiction of formula for calculation of interest.
2. Interest rate structure: The present rate of interest for the bank
3. Show report: The earnings of the client from the time he/she has become a customer of the bank. These services are exclusive to the admin and can ony be accessed when the admin is logged in.

## Calculation of Interest:
For the calculation of interest we have made an assumption regarding the fact that the interest needs to be computed daily. Since this is a local system based application it is implicit that we need to keep the system on all the time if we want a specific action to be performed everday without any help from an external source. Since this is not a server based or cloud based application we have made an assumption that the admin should login at 23:58-00:00 everyday to trigger the calculation of interest for that day, this needs to be done due to the issue discussed above. In this period we are not allowing any client to login between 23:58-00:00.

## Requirement of database:
The project requires an active connection to the database whenever it is opened on the system.

## Admin:
There is only one admin which simulated a real life bank by performing the following activities:
1. Calculation of interest
2. Showing report to users for their earnings
3. Display of interest rate structure
4. Can set rate of interest
5. Can set rate of tax
6. Structure followed for calculation of interest

## Client:
The client is allowed to signup as a new user into the bank and perform operations such as deposit/ withdraw. Necessary validations have been put in place for entering amount so that invalid transactions are prevented.

## Sign Up:
At the time of signup the user is provided with an account number that the user has to remember or note down somewhere as it is important for the user for some services the user might require.

## Log in:
On an application running on a system the following scenarios can occur with regard to logging in:
1. Two clients cannot be logged in at the same time
2. Admin and a client can be logged in at the same time
## Log out:
When the window for the application is closed the client/admin whoever closed the window is automatically logged out.

## Storing passwords of user:
Passwords have been stored in an encrypted form(using SHA-1 encryption) in the database

## Not giving setInterest or setTax to user:
Due to obvious reasons we are not providing the functionality of setInterest or setTax with the user as the user can change them for other users as well.

## Showreport with admin:
By entering the account number of the user, he/she can view their earnings until now from the day they joined the bank, in between the interest might have been changed by the bank, we haven’t shown that explicitly as this change is reflected in the calculated balance for the user.
The showreport option with the admin requires the admin to enter an account number for the user he/she wants to send/dispay the report to. If you are getting a blank screen after clicking on showreport option with the admin then it either implies that you have an entered an invalid account number or the account holder’s monthly transactions are null i.e. no monthly transaction registered.The application is pretty easy of use if the user is clear about his/her role to be played in the application. The buttons have labels which are self explanatory and can be used to easily manuover in the system.

# Installation:
1. Clone or download the repo
2. Import as a maven project in eclipse.
3. Run all queries under database.sql
4. If you are using any database other than mysql download and configure its jdbc driver from maven repository using pom.xml
5. Configure the database connection string in database.java under src>main>DBUtil folder.
6. Run the application from eclipse.

# Contributing
1. Fork it!
2. Create your feature branch: git checkout -b my-new-feature
3. Commit your changes: git commit -m 'new change'
4. Push to the branch : git push origin my-new-feature
5. Submit a pull request.
