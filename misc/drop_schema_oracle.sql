-- Simple PL/SQL Function to drop schema from an Oracle Database
DECLARE
	CURSOR userProcedures IS
		SELECT   name
		FROM     user_source
		WHERE    type='PROCEDURE'
		GROUP BY name;
	CURSOR userViews IS
		SELECT   view_name
		FROM     user_views
		ORDER BY view_name;
	CURSOR userFunctions IS
		SELECT   name
		FROM     user_source
		WHERE    type='FUNCTION'
		GROUP BY name;
	CURSOR userTables IS
		SELECT   table_name
		FROM     user_tables
		ORDER BY table_name;
	CURSOR userSeqs IS
		SELECT   sequence_name
		FROM     user_sequences
		ORDER BY sequence_name;
BEGIN
	EXECUTE IMMEDIATE 'PURGE RECYCLEBIN';
	FOR userProcedure IN userProcedures LOOP
		EXECUTE IMMEDIATE 'DROP PROCEDURE ' || userProcedure.name ;
	END LOOP;
	FOR userView IN userViews LOOP
		EXECUTE IMMEDIATE 'DROP VIEW ' || userView.view_name ;
	END LOOP;
	FOR userFunction IN userFunctions LOOP
		EXECUTE IMMEDIATE 'DROP FUNCTION ' || userFunction.name ;
	END LOOP;
	FOR userTable IN userTables LOOP
		EXECUTE IMMEDIATE 'DROP TABLE ' || userTable.table_name || ' CASCADE CONSTRAINTS';
	END LOOP;
	FOR userSeq IN userSeqs LOOP
		EXECUTE IMMEDIATE 'DROP SEQUENCE ' || userSeq.sequence_name ;
	END LOOP;
	EXECUTE IMMEDIATE 'PURGE RECYCLEBIN';
END;
/

select * from user_objects;
exit
