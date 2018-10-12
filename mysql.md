SELECT CURRENT_USER();

netstat -ln | grep mysql

mysql -h host_name -P port -u user_name -p db_name

CREATE USER 'jeffrey'@'localhost' IDENTIFIED BY 'password';
GRANT ALL ON db1.* TO 'jeffrey'@'localhost';
GRANT SELECT ON db.invoice TO 'jeffrey'@'localhost';
GRANT SELECT, INSERT ON db.* TO 'someuser'@'somehost';
FLUSH PRIVILEGES;

DROP USER 'jeffrey'@'localhost';

ALTER USER user IDENTIFIED BY 'auth_string';
SET PASSWORD FOR 'jeffrey'@'localhost' = 'auth_string';

SHOW GRANTS FOR 'joe'@'home.example.com';
SHOW CREATE USER 'admin'@'localhost'\G

SELECT `Host`,`User` FROM mysql.user;

SELECT LAST_INSERT_ID();

SHOW CREATE TABLE t\G