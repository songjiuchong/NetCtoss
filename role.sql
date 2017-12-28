create table ADMIN_INFO
(
  id         NUMBER(11) not null,
  admin_code VARCHAR2(30) not null,
  password   VARCHAR2(30) not null,
  name       VARCHAR2(30) not null,
  telephone  VARCHAR2(30),
  email      VARCHAR2(50),
  enrolldate DATE not null
);
alter table ADMIN_INFO
  add constraint ADMIN_INFO_ID_PK primary key (ID);
create table ADMIN_ROLE
(
  admin_id NUMBER not null,
  role_id  NUMBER not null
);
alter table ADMIN_ROLE
  add constraint AR_PK primary key (ADMIN_ID, ROLE_ID);
create table ROLE
(
  id   NUMBER not null,
  name VARCHAR2(20)
);
alter table ROLE
  add constraint ROLE_PK primary key (ID);
create table ROLE_PRIVILEGE
(
  role_id      NUMBER not null,
  privilege_id NUMBER not null
);
alter table ROLE_PRIVILEGE
  add constraint RP_PK primary key (ROLE_ID, PRIVILEGE_ID);
insert into ADMIN_INFO (id, admin_code, password, name, telephone, email, enrolldate)
values (1, 'admin', '111111', '管理员', '13111111111', '11@11.com', to_date('13-05-2013', 'dd-mm-yyyy'));
insert into ROLE (id, name) values (1, '系统管理员');
insert into ADMIN_ROLE (admin_id, role_id)values (1, 1);
insert into ROLE_PRIVILEGE (role_id, privilege_id) values (1, 1);
insert into ROLE_PRIVILEGE (role_id, privilege_id) values (1, 2);
insert into ROLE_PRIVILEGE (role_id, privilege_id) values (1, 3);
insert into ROLE_PRIVILEGE (role_id, privilege_id) values (1, 4);
insert into ROLE_PRIVILEGE (role_id, privilege_id) values (1, 5);
insert into ROLE_PRIVILEGE (role_id, privilege_id) values (1, 6);
insert into ROLE_PRIVILEGE (role_id, privilege_id) values (1, 7);
commit;