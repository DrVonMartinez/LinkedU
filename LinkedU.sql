CREATE TABLE LinkedU.Student
(
	firstName VARCHAR2(20) NOT NULL,
	lastName VARCHAR2(20) NOT NULL,
	userName VARCHAR2(10) NOT NULL,
	highSchool VARCHAR2(40),
	currentUniversity VARCHAR2(40),
	universityChoices VARCHAR2(40),
	majorChoices VARCHAR2(40),
	essay VARCHAR2(40),
	activities VARCHAR2(250),
	ACTScores INTEGER,
	SATScores INTEGER,
	GPA DOUBLE,
	graduationDate DATE,
	accountStatus BOOLEAN default false,
	email VARCHAR2(35) NOT NULL,
	currentState VARCHAR(2),
	phoneNumber VARCHAR(10) NOT NULL,
	CONSTRAINT student_primarykey_usernamepk PRIMARY KEY(userName)
);

CREATE TABLE LinkedU.recruiter
(
	firstName VARCHAR2(20)NOT NULL,
	lastName VARCHAR2(20) NOT NULL,
	userName VARCHAR2(10) NOT NULL,
	university VARCHAR2(50) NOT NULL,
	accountStatus BOOLEAN default false,
	email VARCHAR2(35) NOT NULL,
	phoneNumber VARCHAR(10) NOT NULL,
	CONSTRAINT recruiter_primarykey_usernamepk PRIMARY KEY(userName)
);

CREATE TABLE LinkedU.Major
(
	id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	name VARCHAR2(50) NOT NULL,
	CONSTRAINT primary_key_MajorID PRIMARY KEY (id)
);

CREATE TABLE LinkedU.University
(
	id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	name VARCHAR2(50) NOT NULL,
	USState VARCHAR(2) NOT NULL,
	picture VARCHAR2(40),
	bio VARCHAR2(4000)
	idealGPA DOUBLE,
	idealACT INTEGER,
	idealSAT INTEGER,
	websiteLink VARCHAR2(150),
	applicationLink VARCHAR2(160),
	highlighted BOOLEAN default false,
	notableMajors VARCHAR2 (50),
	timeSubscribed INTEGER default 0,
	CONSTRAINT primary_key_MajorID PRIMARY KEY (id),
	CONSTRAINT primary_key_universityName PRIMARY KEY (name)
);

CREATE TABLE LinkedU.Login
(
	userName VARCHAR2(20),
	password VARCHAR2(200)NOT NULL,
	accountType VARCHAR2(9) NOT NULL,
	CONSTRAINT linkedULogin_primarykey_usernamepk PRIMARY KEY(userName)
);


INSERT VALUES INTO LinkedU.Login (userName, password, accountType)
VALUES ('admin', 'it353it353', 'admin');

INSERT VALUES INTO LinkedU.Login (userName, password, accountType)
VALUES ('student1', 'password', 'student');

INSERT VALUES INTO LinkedU.Login (userName, password, accountType)
VALUES ('student2', 'password2', 'student');

INSERT VALUES INTO LinkedU.Login (userName, password, accountType)
VALUES ('student3', 'password3', 'student');

INSERT VALUES INTO LinkedU.Login (userName, password, accountType)
VALUES ('recruiter1', 'password1', 'recruiter');

INSERT VALUES INTO LinkedU.Login (userName, password, accountType)
VALUES ('recruiter2', 'password2', 'recruiter');

INSERT VALUES INTO LinkedU.Student (userName, firstName, lastName, highSchool, currentUniversity, ACTScores, SATScores, GPA, GraduationDate, currentState, email, phoneNumber)
VALUES ('student1', 'test', 'student1', 'a generically named high school',NULL ,28, NULL, 2.5, '2013-05-28', 'IL','bknigh1@ilstu.edu','6309346236');

INSERT VALUES INTO LinkedU.Student (userName, firstName, lastName, highSchool, currentUniversity, ACTScores, SATScores, GPA, GraduationDate, currentState, email, phoneNumber)
VALUES ('student2', 'test', 'student2', 'a differently named high school','a college that exists',32, NULL, 4.0, '2017-05-28', 'IL','bknigh1@ilstu.edu','6309346236');

INSERT VALUES INTO LinkedU.Student (userName, firstName, lastName, highSchool, currentUniversity, ACTScores, SATScores, GPA, GraduationDate, currentState, email, phoneNumber, accountStatus)
VALUES ('student3', 'test', 'student3', 'another different named high school',NULL,NULL, 400, 3.5, '2018-05-28', 'IL','bknigh1@ilstu.edu','6309346236',true);

INSERT VALUES INTO LinkedU.Student (userName, firstName, lastName, university, email, phoneNumber)
VALUES ('recruiter1', 'test', 'recruiter1', 'Illinois State University', 'bknigh1@ilstu.edu','6309346236');

INSERT VALUES INTO LinkedU.Student (userName, firstName, lastName, university, email, phoneNumber, accountStatus)
VALUES ('recruiter2', 'test', 'recruiter1', 'University of Illinois', 'bknigh1@ilstu.edu','6309346236', true);

INSERT VALUES INTO LinkedU.Major (name)
VALUES ('Mathematics');

INSERT VALUES INTO LinkedU.Major (name)
VALUES ('Engineering');

INSERT VALUES INTO LinkedU.Major (name)
VALUES ('Teaching');

INSERT VALUES INTO LinkedU.Major (name)
VALUES ('Physics');

INSERT VALUES INTO LinkedU.Major (name)
VALUES ('Computer Science');

INSERT VALUES INTO LinkedU.Major (name)
VALUES ('Nursing');

INSERT VALUES INTO LinkedU.Major (name)
VALUES ('Biology');

INSERT VALUES INTO LinkedU.Major (name)
VALUES ('Chemistry');

INSERT VALUES INTO LinkedU.Major (name)
VALUES ('Business');

INSERT VALUES INTO LinkedU.Major (name)
VALUES ('Law');

INSERT VALUES INTO LinkedU.University (name, USState, picture, bio, idealGPA,idealACT, idealSAT, websiteLink, applicationLink,highlighted,notableMajors, timeSubscribed)
VALUES ('University of Illinois', 'IL', NULL,'This is a pregenerated statement about a university. Typically this includes the location and contact information of the university and a short statement',
3.32,26,1340,'http://illinois.edu/','http://admissions.illinois.edu/apply/' , false, '2,1,5,8',0);

INSERT VALUES INTO LinkedU.University (name, USState, picture, bio, idealGPA,idealACT, idealSAT, websiteLink, applicationLink,highlighted,notableMajors, timeSubscribed)
VALUES ('Illinois State University', 'IL', NULL,'This is a pregenerated statement about a university. Typically this includes the location and contact information of the university and a short statement',
2.37,22,NULL,'https://illinoisstate.edu/','http://admissions.illinoisstate.edu/apply/' , true, '3,1,5,6,9',0);
