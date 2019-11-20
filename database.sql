create database OOPD;
use OOPD;
show tables;

/* users Table */
CREATE TABLE account_holders(account_number VARCHAR(30) primary key, fname VARCHAR(30), lname VARCHAR(30), dateOfJoining date, password VARCHAR(40),active_session boolean);
Insert into account_holders values('abcd123','sushil','tiwari',DATE '2015-12-17',SHA1("123"),false);
UPDATE account_holders set dateOfJoining = timestamp  where account_number = 'abcd123';
SELECT * FROM account_holders;
/**/

/* Admin Login status */
CREATE TABLE admin(username VARCHAR(30) primary key,password VARCHAR(40),active_status boolean);
DROP TABLE admin;
INSERT INTO admin values('sushil',SHA1('qwerty'),false);
SELECT * FROM admin;
UPDATE admin set active_status = false where username = 'sushil';
/**/

/*CREATE SEQUENCE FOR account_number generation*/
CREATE TABLE current_account_number(account_number numeric primary key);
DROP TABLE current_account_number;
SELECT * FROM current_account_number;
INSERT INTO current_account_number values (125);
UPDATE current_account_number set account_number = 123 where account_number = 133;
/**/



/*Account Table*/
CREATE TABLE accounts(account_number VARCHAR(30) primary key, balance NUMERIC(10,2), foreign key(account_number) references account_holders(account_number) ON DELETE CASCADE);
SELECT * FROM accounts;
INSERT INTO accounts VALUES('abcd123','0.0');
/**/

DROP Table account_holders;
DROP TABLE accounts;
SELECT * FROM account_holders;
SELECT * FROM account_holders where account_number='abcd123' and password=sha1('123');
SELECT sha1(password) FROM account_holders where account_number = 'abcd123';