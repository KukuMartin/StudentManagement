CREATE DATABASE schoolDB;

USE schoolDB;

CREATE TABLE Account (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    middleName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    birthDate DATE NOT NULL,
    phoneNumber VARCHAR(20) NOT NULL
);

CREATE TABLE Address (
    id INT AUTO_INCREMENT PRIMARY KEY,
    accountId INT NOT NULL,
    houseNumber VARCHAR(20),
    street VARCHAR(100),
    barangay VARCHAR(100),
    city VARCHAR(100),
    province VARCHAR(100),
    zipCode VARCHAR(20),
    FOREIGN KEY (accountId) REFERENCES Account(id) ON DELETE CASCADE
);

CREATE TABLE Admin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    accountId INT NOT NULL,

    FOREIGN KEY (accountId) REFERENCES Account(id) ON DELETE CASCADE
);

CREATE TABLE Section (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    code VARCHAR(20)
);

CREATE TABLE Advisor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    accountId INT NOT NULL,

    FOREIGN KEY (accountId) REFERENCES Account(id) ON DELETE CASCADE
);

CREATE TABLE Teacher (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    accountId INT NOT NULL,

    FOREIGN KEY (accountId) REFERENCES Account(id) ON DELETE CASCADE
);

CREATE TABLE Student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    studentId VARCHAR(50) NOT NULL,
    course VARCHAR(50) NOT NULL,
    sectionId INT NOT NULL,
    accountId INT NOT NULL,

    FOREIGN KEY (accountId) REFERENCES Account(id) ON DELETE CASCADE
);

CREATE TABLE Subject (
    id INT AUTO_INCREMENT PRIMARY KEY,
    teacherId INT NOT NULL,
    studentId INT NOT NULL,
    FOREIGN KEY (teacherId) REFERENCES Teacher(id),
    FOREIGN KEY (studentId) REFERENCES Student(id)
);

CREATE TABLE Assessment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    percent DOUBLE NOT NULL,
    subjectId INT NOT NULL,
    period ENUM('MIDTERM', 'FINAL') NOT NULL,
    FOREIGN KEY (subjectId) REFERENCES Subject(id) ON DELETE CASCADE
);

CREATE TABLE Activity (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    totalScore INT NOT NULL,
    currentScore INT NOT NULL,
    assessmentId INT,
    FOREIGN KEY (assessmentId) REFERENCES Assessment(id) ON DELETE CASCADE
);

CREATE TABLE Attendance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    subjectId INT NOT NULL,
    period ENUM('MIDTERM', 'FINAL') NOT NULL,
    FOREIGN KEY (subjectId) REFERENCES Subject(id) ON DELETE CASCADE
);

CREATE TABLE Day (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    state ENUM('PRESENT', 'ABSENT', 'LATE', 'EXCUSED') NOT NULL,
    attendanceId INT NOT NULL,
    FOREIGN KEY (attendanceId) REFERENCES Attendance(id) ON DELETE CASCADE
);