create table membership(
id varchar2(20) primary key,
pw varchar2(100),
addr varchar2(300)
);
alter table membership add 
	session_id varchar2(100) default 'nan' not null;
alter table membership add limit_time date;