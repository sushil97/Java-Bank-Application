create database OOPD;
use OOPD;
show tables;
DROP TABLE abcd125_monthly;
DROP TABLE abcd125_daily;
/* users Table */
CREATE TABLE account_holders(account_number VARCHAR(30) primary key, fname VARCHAR(30), lname VARCHAR(30), dateOfJoining date, password VARCHAR(40),active_session boolean);
SELECT * FROM account_holders;
/**/

/* Admin Login status */
CREATE TABLE admin(username VARCHAR(30) primary key,password VARCHAR(40),active_status boolean);
DROP TABLE admin;
SELECT * FROM admin;
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
SELECT * FROM abcd123_monthly;
SELECT * FROM abcd123_daily;
SELECT SUM(Interest) FROM abcd123_daily;
/**/
SELECT Interest FROM abcd123_daily;

DROP Table account_holders;
DROP TABLE accounts;
SELECT * FROM account_holders;