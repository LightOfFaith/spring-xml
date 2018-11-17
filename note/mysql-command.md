SELECT COUNT(1) FROM mysql.`user`;
SELECT COUNT(*) FROM mysql.`user`;
COUNT(1)==COUNT(*)

mysqldump [options] db_name [tbl_name ...]

--no-data, -d

--complete-insert, -c
Use complete INSERT statements that include column names.
 
mysqldump --databases db_name  --log-error=file_name  -h host_name -P port_num  -u user_name -p[password] --set-charset  -v  -w 'where_condition' -r file_name

mysqldump admin user --log-error=d://user.log  -h 127.0.0.1 -P 3306  -u root -p -r d://user.sql --set-charset -v -w "gmt_create>='2018-10-10 16:00:00' AND gmt_create<'2018-10-18 16:00:00'"  11~19

--注意：不建议使用时间备份数据，由于写数据时，是以 0时区 时间写入的，但执行mysqldump命令备份时则使用的是本地 东8区 的时间，所以就有了8小时的差距。

--skip-tz-utc

mysqldump admin user --log-error=d://user.log  -h 127.0.0.1 -P 3306  -u root -p -r d://user.sql --set-charset -v --skip-tz-utc -w "gmt_create>'2018-10-10' AND gmt_create<'2018-10-11'"


mysql -e "SELECT User, Host, plugin FROM mysql.user" mysql

mysqlimport [options] db_name textfile1 [textfile2 ...]

mysqlimport admin user -h 127.0.0.1 -P 3306 -u root -p -v D://user.sql

SHOW VARIABLES;
SHOW VARIABLES LIKE 'max_join_size';
SHOW VARIABLES LIKE 'secure_file_priv';

SHOW VARIABLES LIKE 'TIME_ZONE';
SHOW GLOBAL VARIABLES LIKE '%TIME_ZONE%'

ALTER TABLE tbl_name DROP FOREIGN KEY fk_symbol;

ALTER TABLE tbl_name RENAME new_tbl_name;

ALTER TABLE tbl_name DEFAULT CHARACTER SET charset_name;

ALTER TABLE t2 DROP COLUMN c, DROP COLUMN d;

ALTER TABLE authorities DROP FOREIGN KEY fk_authorities_users;

ALTER TABLE authorities DROP INDEX ix_auth_username;

ALTER TABLE authorities CONSTRAINT fk_authorities_users ADD FOREIGN KEY (username) REFERENCES users (username)

CREATE UNIQUE INDEX ix_auth_username ON authorities (username,authority);

CASE value WHEN [compare_value] THEN result [WHEN [compare_value] THEN
result ...] [ELSE result] END

CASE WHEN [condition] THEN result [WHEN [condition] THEN result ...] [ELSE
result] END

SET GLOBAL sql_log_bin = ON;
SET SQL_LOG_BIN=OFF;
SOURCE datadir/meta/backup_gtid_executed.sql
SET SQL_LOG_BIN=ON;

SHOW BINARY LOGS;
SHOW MASTER LOGS;

SHOW VARIABLES;

[mysqld]
log-bin=mysql-bin
server-id=1


