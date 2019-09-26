DROP DATABASE IF EXISTS test;
CREATE DATABASE test CHARSET = utf8 COLLATE = utf8_general_ci;
USE test;

drop table test.organization;
drop table test.management;
drop table test.department;
drop table test.manager_job;
drop table test.employee_job;

-- organization 1 -> * management (one to many)
create table organization(
    id INT NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL UNIQUE,
    PRIMARY KEY ( id )
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- management 1 -> * department (one to many)
create table management(
   id INT NOT NULL UNIQUE AUTO_INCREMENT,
   org_id INT NOT NULL,
   name VARCHAR(40) NOT NULL,
   PRIMARY KEY ( id ),
   FOREIGN KEY (org_id) REFERENCES organization(id),
   UNIQUE KEY org_id_name (org_id, name)  # no duplicate management department for one organization
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

create table department(
   id INT NOT NULL UNIQUE AUTO_INCREMENT,
   parent_dep_id INT NULL,
   man_id INT NOT NULL,
   name VARCHAR(40) NOT NULL,
   PRIMARY KEY ( id ),
   FOREIGN KEY (man_id) REFERENCES management(id),
   FOREIGN KEY (parent_dep_id) REFERENCES department(id),
   UNIQUE KEY man_id_name (man_id, name)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

create table manager_job(
    id INT,
    name VARCHAR(40) NOT NULL,
    description TEXT NOT NULL,
    PRIMARY KEY ( id ),
    FOREIGN KEY(id) REFERENCES management(id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

create table employee_job(
    id INT,
    dep_id INT NOT NULL,
    name VARCHAR(40) NOT NULL,
    description TEXT NOT NULL,
    PRIMARY KEY ( id ),
    FOREIGN KEY(dep_id) REFERENCES department(id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;


-- INSERTS TO STRUCTURE
-- 
-- 
-- 
-- CREATE ORGANIZATIONS FOR GOOGLE & MICROSOFT
INSERT INTO test.organization (name) VALUES('GOOGLE');
INSERT INTO test.organization (name) VALUES('MICROSOFT');

-- Must fail (no duplicate entries)
-- INSERT INTO test.organization (name) VALUES('GOOGLE');

-- MANAGEMENT FOR GOOGLE & MICROSOFT
INSERT INTO test.management (org_id, name) VALUES(1, 'Sales');
INSERT INTO test.management (org_id, name) VALUES(1, 'Development');
INSERT INTO test.management (org_id, name) VALUES(2, 'Sales');
INSERT INTO test.management (org_id, name) VALUES(2, 'Development');

-- Must fail no duplicate management department for one organization
-- INSERT INTO test.management (org_id, name) VALUES(2, 'Development');


-- DEVELOPMENT (Level 1) DEPARTMENTS FOR GOOGLE & MICROSOFT
-- 
-- Top level GOOGLE development department for Google Search
INSERT INTO test.department (parent_dep_id, man_id, name) VALUES(NULL, 2, 'Google Search');
-- Top level MICROSOFT development department for Bing Search
INSERT INTO test.department (parent_dep_id, man_id, name) VALUES(NULL, 2, 'Bing Search');

-- SALES (Level 1) DEPARTMENTS FOR GOOGLE & MICROSOFT
-- 
-- Top level GOOGLE sales department for USA
INSERT INTO test.department (parent_dep_id, man_id, name) VALUES(NULL, 1, 'USA');
-- Top level GOOGLE sales department for EU
INSERT INTO test.department (parent_dep_id, man_id, name) VALUES(NULL, 1, 'EU');
-- Top level MICROSOFT sales department for USA
INSERT INTO test.department (parent_dep_id, man_id, name) VALUES(NULL, 2, 'USA');
-- Top level MICROSOFT sales department for EU
INSERT INTO test.department (parent_dep_id, man_id, name) VALUES(NULL, 2, 'EU');

-- SALES (Level 2) DEPARTMENTS FOR GOOGLE
-- 
-- Second level GOOGLE sales department for USA, California
INSERT INTO test.department (parent_dep_id, man_id, name) VALUES(1, 1, 'California');
-- Second level GOOGLE sales department for USA, New York
INSERT INTO test.department (parent_dep_id, man_id, name) VALUES(1, 1, 'New York');

-- SALES (Level 3) DEPARTMENTS FOR GOOGLE New York
-- Third level GOOGLE sales department for USA, New York, Queens (parent_dep_id = 2 for New York, USA)
INSERT INTO test.department (parent_dep_id, man_id, name) VALUES(2, 1, 'Queens');


-- JOBS
-- 
-- MANAGERS
-- id refers to management table id, only one per management table row
-- Senior Sales Manager at Google 
INSERT INTO test.manager_job (id, name, description) VALUES(1, 'Alice', 'Senior Sales Manager');

-- Must fail no more than one manager to management table
-- INSERT INTO test.manager_job (id, name, description) VALUES(2, 'Bob', 'Senior Sales Manager');


-- EMPLOYEES
INSERT INTO test.employee_job (id, dep_id, name, description) VALUES(0, 1, 'John0', 'Junior Sales');
INSERT INTO test.employee_job (id, dep_id, name, description) VALUES(1, 1, 'John1', 'Junior Sales');
INSERT INTO test.employee_job (id, dep_id, name, description) VALUES(2, 1, 'John2', 'Junior Sales');
INSERT INTO test.employee_job (id, dep_id, name, description) VALUES(3, 1, 'John3', 'Junior Sales');
INSERT INTO test.employee_job (id, dep_id, name, description) VALUES(4, 1, 'John4', 'Junior Sales');
INSERT INTO test.employee_job (id, dep_id, name, description) VALUES(5, 1, 'John5', 'Junior Sales');
INSERT INTO test.employee_job (id, dep_id, name, description) VALUES(6, 1, 'John6', 'Junior Sales');
INSERT INTO test.employee_job (id, dep_id, name, description) VALUES(7, 1, 'John7', 'Junior Sales');


SELECT * FROM test.organization;
SELECT * FROM test.management;
SELECT * FROM test.department;
SELECT * FROM test.manager_job;
select * from test.employee_job;