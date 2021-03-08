
CREATE  TABLE ENROLLMENT (
  cid varchar(20) not null,
  sid char (20) not null,
  credit int not null);

INSERT INTO ENROLLMENT (CID, SID, CREDIT) 

VALUES 

('EECS1001','cse12780',1),

('EECS1001','cse16547',1),

('EECS1001','cse26073',1),

('EECS1001','cse60455',1),

('EECS1001','cse62366',1),

('EECS1001','cse68286',1),

('EECS1001','cse75560',1),

('EECS1001','cse76117',1),

('EECS1001','cse88745',1),

('EECS1001','cse93770',1),

('EECS1001','cse97262',1),

('EECS1020','cse47510',3),

('EECS1020','cse62366',3),

('EECS1020','cse64365',3),

('EECS1020','cse66770',3),

('EECS1020','cse76117',3),

('EECS1020','cse79082',3),

('EECS1020','cse88745',3),

('EECS1020','cse97262',3),

('EECS2041','cse12780',4),

('EECS2041','cse26073',4),

('EECS2041','cse60455',4),

('EECS2041','cse62366',4),

('EECS2041','cse64365',4),

('EECS2041','cse66770',4),

('EECS2041','cse67278',4),

('EECS2041','cse76117',4),

('EECS2041','cse76967',4),

('EECS2041','cse79082',4),

('EECS2041','cse93770',4),

('EECS3101','cse12780',3),

('EECS3101','cse46346',3),

('EECS3101','cse47510',3),

('EECS3101','cse62366',3),

('EECS3101','cse64469',3),

('EECS3101','cse79082',3),

('EECS3101','cse97262',3),

('EECS4080','cse12780',6),

('EECS4080','cse26073',6),

('EECS4080','cse32836',6),

('EECS4080','cse40589',6),

('EECS4080','cse55671',6),

('EECS4080','cse62366',6),

('EECS4080','cse64575',6),

('EECS4080','cse66770',6),

('EECS4080','cse67278',6),

('EECS4080','cse68286',6),

('EECS4080','cse76117',6),

('EECS4080','cse81679',6),

('EECS4080','cse87755',6),

('EECS4080','cse88745',6),

('EECS4080','cse96577',6),

('EECS4413','cse29889',3),

('EECS4413','cse40589',3),

('EECS4413','cse47510',3),

('EECS4413','cse55671',3),

('EECS4413','cse60455',3),

('EECS4413','cse62366',3),

('EECS4413','cse67895',3),

('EECS4413','cse75560',3),

('EECS4413','cse76117',3),

('EECS4413','cse79082',3),

('EECS4413','cse88745',3),

('EECS4413','cse96577',3);




CREATE TABLE STUDENTS (SID varchar(20) PRIMARY KEY  NOT NULL ,GIVENNAME varchar(20) ,SURNAME varchar(20)  ,CREDIT_TAKEN int, CREDIT_GRADUATE int);


INSERT INTO STUDENTS (SID,GIVENNAME,SURNAME,CREDIT_TAKEN,CREDIT_GRADUATE) 

VALUES 

('cse12780','Nicole','Bradley',111,120),

('cse16547','Jeffrey','Russell',111,120),

('cse26073','Gerald','Reed',111,120),

('cse29889','Irene','Rodriguez',84,90),

('cse32836','Stephen','Nelson',90,90),

('cse40589','Harry','Burton',87,90),

('cse46346','Emily','Miller',93,90),

('cse47510','Louise','Kelley',99,120),

('cse55671','Sharon','Howard',87,90),

('cse60455','Emily','Peters',108,120),

('cse62366','Keith','Wallace',102,120),

('cse64365','Jeremy','Nelson',96,120),

('cse64469','Lillian','Watson',90,90),

('cse64575','Harry','Fowler',84,90),

('cse66770','Donna','Duncan',96,120),

('cse67278','Wayne','Griffin',96,120),

('cse67895','Philip','Rodriguez',81,90),

('cse68286','Shawn','Schmidt',102,120),

('cse75560','Jessica','Wilson',102,120),

('cse76117','Kathleen','Medina',99,120),

('cse76967','Henry','Rice',96,120),

('cse79082','Charles','Garrett',96,120),

('cse81679','Ronald','Carter',84,90),

('cse87755','Johnny','Lane',84,90),

('cse88745','Jeffrey','Davis',99,120),

('cse93770','Catherine','Rivera',99,120),

('cse96577','Margaret','Ryan',84,90),

('cse97262','Catherine','Johnson',99,120);