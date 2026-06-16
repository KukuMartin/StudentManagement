CREATE DATABASE schoolDB;
USE schoolDB;

CREATE TABLE Account (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    birthDate DATE NOT NULL,
    phoneNumber VARCHAR(20) NOT NULL,
    address VARCHAR(150) NOT NULL
);

CREATE TABLE Admin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    accountId INT NOT NULL,
    FOREIGN KEY (accountId) REFERENCES Account(id) ON DELETE CASCADE
);

CREATE TABLE Advisor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    accountId INT NOT NULL,
    FOREIGN KEY (accountId) REFERENCES Account(id) ON DELETE CASCADE
);

CREATE TABLE Teacher (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    accountId INT NOT NULL,
    FOREIGN KEY (accountId) REFERENCES Account(id) ON DELETE CASCADE
);

CREATE TABLE Section (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    code VARCHAR(20) UNIQUE
);

CREATE TABLE Student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    studentId VARCHAR(50) NOT NULL UNIQUE,
    course VARCHAR(50) NOT NULL,
    sectionId INT,
    accountId INT NOT NULL,

    FOREIGN KEY (sectionId) REFERENCES Section(id) ON DELETE CASCADE,
    FOREIGN KEY (accountId) REFERENCES Account(id) ON DELETE CASCADE
);

CREATE TABLE Subject (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    code VARCHAR(20) NOT NULL UNIQUE,
    scheduleStart TIME,
    scheduleEnd TIME,
    teacherId INT,

    FOREIGN KEY (teacherId) REFERENCES Teacher(id)
);

CREATE TABLE `Record` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    subjectId INT NOT NULL,
    studentId INT NOT NULL,

    FOREIGN KEY (subjectId) REFERENCES Subject(id) ON DELETE CASCADE,
    FOREIGN KEY (studentId) REFERENCES Student(id) ON DELETE CASCADE
);

CREATE TABLE Assessment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    percent DOUBLE NOT NULL,
    recordId INT NOT NULL,
    period ENUM('MIDTERM', 'FINAL') NOT NULL,

    FOREIGN KEY (recordId) REFERENCES `Record`(id) ON DELETE CASCADE
);

CREATE TABLE Activity (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    totalScore INT NOT NULL,
    currentScore INT NOT NULL,
    assessmentId INT NOT NULL,

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


INSERT INTO Account (firstName, middleName, lastName, gender, birthDate, phoneNumber) VALUES 
('Eunice', 'A', 'Admin', 'MALE', '2001-05-10', '09111111111'),
('Vincent', 'B', 'Advisor', 'FEMALE', '2005-07-15', '09222222222'),
('Neil', 'C', 'Teacher', 'MALE', '1800-03-20', '09333333333');

INSERT INTO Address (accountId, houseNumber, street, barangay, city, province, zipCode)
VALUES
(1, '101', 'Admin St', 'Barangay 1', 'Binan', 'Laguna', '4000'),
(2, '202', 'Advisor St', 'Barangay 2', 'Binan', 'Laguna', '4000'),
(3, '303', 'Teacher St', 'Barangay 3', 'Binan', 'Laguna', '4000');

INSERT INTO Admin (username, password, accountId)
VALUES ('admin', 'admin123', 1);

INSERT INTO Advisor (username, password, accountId)
VALUES ('advisor', 'advisor123', 2);

INSERT INTO Teacher (username, password, accountId)
VALUES ('teacher', 'teacher123', 3);