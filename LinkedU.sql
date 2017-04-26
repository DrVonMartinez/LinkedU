CREATE TABLE LinkedU.Student
(
	firstName VARCHAR(20) NOT NULL,
	lastName VARCHAR (20) NOT NULL,
	userName VARCHAR(10) NOT NULL,
	highSchool VARCHAR(40),
	currentUniversity VARCHAR(40),
	universityChoices VARCHAR(40),
	majorChoices VARCHAR(40),
	essay VARCHAR(40),
	activities VARCHAR(250),
	ACTScores INTEGER,
	SATScores INTEGER,
	GPA DOUBLE,
	graduationDate DATE,
	accountStatus BOOLEAN default false,
	email VARCHAR(35) NOT NULL,
	currentState CHAR(2),
	phoneNumber CHAR(10) NOT NULL,
	phoneNetwork VARCHAR(20) NOT NULL,
	CONSTRAINT student_primarykey_usernamepk PRIMARY KEY(userName)
);

CREATE TABLE LinkedU.recruiter
(
	firstName VARCHAR(20)NOT NULL,
	lastName VARCHAR(20) NOT NULL,
	userName VARCHAR(10) NOT NULL,
	university VARCHAR(50) NOT NULL,
	accountStatus BOOLEAN default false,
	email VARCHAR(35) NOT NULL,
	phoneNumber CHAR(10) NOT NULL,
	CONSTRAINT recruiter_primarykey_usernamepk PRIMARY KEY(userName)
);

CREATE TABLE LinkedU.Major
(
	id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	majorName VARCHAR(50) NOT NULL,
	CONSTRAINT primary_key_MajorID PRIMARY KEY (id)
);

CREATE TABLE LinkedU.University
(
	id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	universityName VARCHAR(50) NOT NULL,
	USState CHAR(2) NOT NULL,
	picture VARCHAR(40),
	bio VARCHAR(4000)
	idealGPA DOUBLE,
	idealACT INTEGER,
	idealSAT INTEGER,
	websiteLink VARCHAR(150),
	applicationLink VARCHAR(160),
	highlighted BOOLEAN default false,
	notableMajors VARCHAR (50),
	timeSubscribed INTEGER default 0,
	CONSTRAINT primary_key_universityName PRIMARY KEY (name)
);

CREATE TABLE LinkedU.Login
(
	userName VARCHAR(20),
	password VARCHAR(200)NOT NULL,
	accountType VARCHAR(9) NOT NULL,
	CONSTRAINT linkedULogin_primarykey_usernamepk PRIMARY KEY(userName)
);


INSERT INTO LinkedU.Login (userName, password, accountType)
VALUES ('admin', 'it353it353', 'admin');

INSERT INTO LinkedU.Login (userName, password, accountType)
VALUES ('student1', 'password', 'student');

INSERT INTO LinkedU.Login (userName, password, accountType)
VALUES ('student2', 'password2', 'student');

INSERT INTO LinkedU.Login (userName, password, accountType)
VALUES ('student3', 'password3', 'student');

INSERT INTO LinkedU.Login (userName, password, accountType)
VALUES ('recruiter1', 'password1', 'recruiter');

INSERT INTO LinkedU.Login (userName, password, accountType)
VALUES ('recruiter2', 'password2', 'recruiter');

INSERT INTO LinkedU.Student (userName, firstName, lastName, highSchool, currentUniversity, ACTScores, SATScores, GPA, GraduationDate, currentState, email, phoneNumber, phoneNetwork)
VALUES ('student1', 'test', 'student1', 'a generically named high school',NULL ,28, NULL, 2.5, '2013-05-28', 'IL','bknigh1@ilstu.edu','6309346236', 'Google');

INSERT INTO LinkedU.Student (userName, firstName, lastName, highSchool, currentUniversity, ACTScores, SATScores, GPA, GraduationDate, currentState, email, phoneNumber, phoneNetwork)
VALUES ('student2', 'test', 'student2', 'a differently named high school','a college that exists',32, NULL, 4.0, '2017-05-28', 'IL','bknigh1@ilstu.edu','6309346236', 'Google');

INSERT INTO LinkedU.Student (userName, firstName, lastName, highSchool, currentUniversity, ACTScores, SATScores, GPA, GraduationDate, currentState, email, phoneNumber,phoneNetwork, accountStatus)
VALUES ('student3', 'test', 'student3', 'another different named high school',NULL,NULL, 400, 3.5, '2018-05-28', 'IL','bknigh1@ilstu.edu','6309346236','Google',true);

INSERT INTO LinkedU.Student (userName, firstName, lastName, university, email, phoneNumber, phoneNetwork)
VALUES ('recruiter1', 'test', 'recruiter1', 'Illinois State University', 'bknigh1@ilstu.edu','6309346236', 'Google');

INSERT INTO LinkedU.Student (userName, firstName, lastName, university, email, phoneNumber, accountStatus, phoneNetwork)
VALUES ('recruiter2', 'test', 'recruiter1', 'University of Illinois', 'bknigh1@ilstu.edu','6309346236', true, 'Google');

INSERT INTO LinkedU.Major (majorName)
VALUES ('Mathematics');

INSERT INTO LinkedU.Major (majorName)
VALUES ('Engineering');

INSERT INTO LinkedU.Major (majorName)
VALUES ('Teaching');

INSERT INTO LinkedU.Major (majorName)
VALUES ('Physics');

INSERT INTO LinkedU.Major (majorName)
VALUES ('Computer Science');

INSERT INTO LinkedU.Major (majorName)
VALUES ('Nursing');

INSERT INTO LinkedU.Major (majorName)
VALUES ('Biology');

INSERT INTO LinkedU.Major (majorName)
VALUES ('Chemistry');

INSERT INTO LinkedU.Major (majorName)
VALUES ('Business');

INSERT INTO LinkedU.Major (majorName)
VALUES ('Law');

INSERT INTO LinkedU.University (universityName, USState, picture, bio, idealGPA,idealACT, idealSAT, websiteLink, applicationLink,highlighted,notableMajors, timeSubscribed)
VALUES ('University of Illinois', 'IL', NULL,'This is a pregenerated statement about a university. Typically this includes the location and contact information of the university and a short statement',
3.32,26,1340,'http://illinois.edu/','http://admissions.illinois.edu/apply/' , false, '2,1,5,8',0);

INSERT INTO LinkedU.University (universityName, USState, picture, bio, idealGPA,idealACT, idealSAT, websiteLink, applicationLink,highlighted,notableMajors, timeSubscribed)
VALUES ('Illinois State University', 'IL', NULL,'This is a pregenerated statement about a university. Typically this includes the location and contact information of the university and a short statement',
2.37,22,NULL,'https://illinoisstate.edu/','http://admissions.illinoisstate.edu/apply/' , true, '3,1,5,6,9',0);
