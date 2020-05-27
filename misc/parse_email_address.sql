-- Function to validate email address
-- Works with Oracle 10g and above, tested using SQL Plus
-- TODO: expand regular expression
CREATE OR REPLACE FUNCTION isValidEmail (input IN VARCHAR2) RETURN VARCHAR2 IS
                EmailRegexp CONSTANT VARCHAR2(1000) := '^\w+(\.\w+)*+@\w+(\.\w+)+$';
BEGIN
                IF REGEXP_LIKE(input,EmailRegexp,'i') THEN
                                RETURN 'Valid';
                ELSE 
                                RETURN 'Invalid';
                END IF;
END isValidEmail;
/

-- Function to parse email parts into variables and print to sql out
-- Works with Oracle 10g and above, tested using SQL Plus
CREATE OR REPLACE FUNCTION parseEmail (email_address IN VARCHAR2) RETURN VARCHAR2
IS            
                name_part VARCHAR2(255);
                domain VARCHAR2(255);
                domain_suffix VARCHAR2(255);                
BEGIN
                IF isValidEmail(email_address) = 'Valid' THEN      
                                name_part := SUBSTR(email_address,1,INSTR(email_address,'@')-1);
                                domain := SUBSTR(email_address,INSTR(email_address,'@')+1,INSTR(email_address,'.')-2);                       
                                domain_suffix := SUBSTR(SUBSTR(email_address,INSTR(email_address,'@')),INSTR(SUBSTR(email_address,INSTR(email_address,'@')),'.'));
                                dbms_output.put_line ('Email Address: '||email_address);
                                dbms_output.put_line ('Email Name Part: '||name_part);
                                dbms_output.put_line ('Email Domain Part: '||domain);
                                dbms_output.put_line ('Email Domain Suffix: '||domain_suffix);
                END IF;
RETURN email_address;
END parseEmail;
/

drop table email_test;

create table email_test (name VARCHAR2(255), email VARCHAR2(255));

insert into email_test values ('Manish Pandit 1', 'manish.pandit@gmail.com');

insert into email_test values ('Manish Pandit 2', 'manish.pandit@com');

insert into email_test values ('Manish Pandit 3', 'manish@com');

set serveroutput on;

select name,email,isValidEmail(email) from email_test;

select email, parseEmail(email) from email_test;

-- Done with testing drop all the objects
drop table email_test;

drop function isValidEmail;

drop function parseEmail;

