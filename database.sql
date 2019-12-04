create database OOPD;
use OOPD;
show tables;

/* users Table */
CREATE TABLE account_holders(account_number VARCHAR(30) primary key, fname VARCHAR(30), lname VARCHAR(30), dateOfJoining date, password VARCHAR(40),active_session boolean);
/**/

/* Admin Login status */
CREATE TABLE admin(username VARCHAR(30) primary key,password VARCHAR(40),active_status boolean);
DROP TABLE admin;
INSERT INTO admin values('admin',SHA1('qwerty'),false); /*You can change admin credential here if you want*/
/**/

/*CREATE SEQUENCE FOR account_number generation*/
CREATE TABLE current_account_number(account_number numeric primary key);
INSERT INTO current_account_number values (12); /*Choose from where your sequence starts*/
/**/


/*Account Table*/
CREATE TABLE accounts(account_number VARCHAR(30) primary key, balance NUMERIC(10,2), foreign key(account_number) references account_holders(account_number) ON DELETE CASCADE);
/**/
