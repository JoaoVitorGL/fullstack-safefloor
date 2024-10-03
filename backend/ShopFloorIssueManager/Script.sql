CREATE TABLE sector (
	sector_id int primary key auto_increment,
	sector_name varchar(40) not null unique
);

CREATE TABLE employee (
	employee_id int primary key auto_increment,
	employee_name varchar(40) not null,
	sector_fk_id int not null,
	constraint fk_sector foreign key (sector_fk_id) references sector (sector_id)
);

CREATE TABLE problem (
	problem_id int primary key auto_increment,
	problem_description varchar(40) not null
);

CREATE TABLE report (
	report_id int primary key auto_increment,
	report_date date not null,
	employee_fk_id int not null,
	problem_fk_id int not null,
	constraint fk_employee foreign key (employee_fk_id) references employee (employee_id),
	constraint fk_problem foreign key (problem_fk_id) references problem (problem_id)
);


INSERT INTO sector (sector_name)
VALUES
    ('Body Shop'),
    ('Paint Shop'),
    ('Assembly Line'),
    ('Trim Shop'),
    ('Engine Shop'),
    ('Quality Control'),
    ('Logistics');
   
INSERT INTO employee (employee_name, sector_fk_id)
VALUES
    ('James Smith', 1),
    ('John Brown', 2),
    ('Robert Miller', 3),
    ('Jose Garcia', 4),
    ('Michael Davis', 5),
    ('William Jones', 6),
    ('Patricia Martinez', 7);
	
    

INSERT INTO problem (problem_description)
VALUES ('Equipment Failure'),
       ('Lack of Supplies'),
       ('Harassment'),
       ('Personnel Failure'),
       ('Accident');
       
      
INSERT INTO report (report_date, employee_fk_id , problem_fk_id)
VALUES ('2024-09-01', 1, 1),
       ('2024-09-01', 2, 2),
       ('2024-09-01', 3, 3),
       ('2024-09-01', 4, 4),
       ('2024-09-01', 5, 5); 
      
INSERT INTO report (report_date, employee_fk_id , problem_fk_id)
VALUES ('2024-08-10', 7, 5),
       ('2024-05-10', 2, 2),
       ('2024-04-10', 3, 3),
       ('2024-07-10', 4, 4),
       ('2024-03-10', 5, 5),
       ('2024-02-10', 2, 3),
       ('2024-04-10', 3, 2); 
      
       
   