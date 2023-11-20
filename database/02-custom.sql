BEGIN TRANSACTION;

-- Important Note:
-- If you have previously run this Docker setup, you may need to delete the existing Docker Postgres volume 
-- to apply these initialization commands. Use the following commands to list and remove Docker volumes:
-- 
-- List all Docker volumes:
-- docker volume ls
-- 
-- Remove a specific Docker volume:
-- docker volume rm [name-of-postgres-volume]

-- Custom SQL Section
-- Users can add their custom SQL commands here.
-- For example, to create a new table, use:
-- CREATE TABLE my_table (
--   column1 datatype,
--   column2 datatype,
--   column3 datatype,
--   ...
-- );

-- More custom SQL commands can be added below.

COMMIT;
