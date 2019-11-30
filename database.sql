create database OOPD;
use OOPD;
show tables;
DROP TABLE abcd125_monthly;
DROP TABLE abcd125_daily;
/* users Table */
CREATE TABLE account_holders(account_number VARCHAR(30) primary key, fname VARCHAR(30), lname VARCHAR(30), dateOfJoining date, password VARCHAR(40),active_session boolean);
Insert into account_holders values('abcd123','sushil','tiwari',DATE '2015-12-17',SHA1("123"),false);
UPDATE account_holders set active_session = false  where account_number = 'abcd123';
SELECT * FROM account_holders;
DELETE FROM account_holders where account_number = 'abcd124';
/**/

/* Admin Login status */
CREATE TABLE admin(username VARCHAR(30) primary key,password VARCHAR(40),active_status boolean);
DROP TABLE admin;
INSERT INTO admin values('sushil',SHA1('qwerty'),false);
ALTER TABLE admin ADD tax numeric(10,2);
SELECT * FROM admin;
UPDATE admin set tax = 0.0 where username = 'sushil'; 
UPDATE admin set active_status = false where username = 'sushil';
/**/

/*CREATE SEQUENCE FOR account_number generation*/
CREATE TABLE current_account_number(account_number numeric primary key);
DROP TABLE current_account_number;
SELECT * FROM current_account_number;
INSERT INTO current_account_number values (12);
UPDATE current_account_number set account_number = 123 where account_number = 125;
/**/



/*Account Table*/
CREATE TABLE accounts(account_number VARCHAR(30) primary key, balance NUMERIC(10,2), foreign key(account_number) references account_holders(account_number) ON DELETE CASCADE);
SELECT * FROM accounts;
INSERT INTO accounts VALUES('abcd123','0.0');
UPDATE accounts set balance = 1050 where account_number = 'abcd123';
DELETE FROM accounts where account_number='abcd124';
/**/


/*Having both common users monthly_interest and daily_interest column before hand however we will create tables for rest when they signUp*/
CREATE TABLE abcd123_monthly(mon NUMERIC primary key,Total_interest numeric(10,2));
CREATE TAbLE abcd123_daily(day_mon NUMERIC primary key, Interest numeric(10,2));
INSERT INTO abcd123_daily VALUES (17,10.00);
INSERT INTO abcd123_daily VALUES (18,11);
INSERT INTO abcd123_daily VALUES(19,12.40);
INSERT INTO abcd123_daily VALUES(20,13.69);
INSERT INTO abcd123_daily VALUES(21,14.00);
INSERT INTO abcd123_daily VALUES(22,15.67);
INSERT INTO abcd123_daily VALUES(23,16.96);
INSERT INTO abcd123_daily VALUES(24,45.90);
INSERT INTO abcd123_daily VALUES(25,50.193);
INSERT INTO abcd123_daily VALUES(26,53.244);
INSERT INTO abcd123_daily VALUES(27,69.093);
INSERT INTO abcd123_daily VALUES(28,99.144);
DROP TABLE abcd123_daily;
DROP TABLE abcd123_monthly;
SELECT * FROM abcd123_monthly;
INSERT INTO abcd123_monthly values(1,10);
SELECT * FROM abcd123_daily;
SELECT SUM(Interest) FROM abcd123_daily;
/**/
SELECT Interest FROM abcd123_daily;

DROP Table account_holders;
DROP TABLE accounts;
SELECT * FROM account_holders;
SELECT * FROM account_holders where account_number='abcd123' and password=sha1('123');
SELECT sha1(password) FROM account_holders where account_number = 'abcd123';