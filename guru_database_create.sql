create table category (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB
create table customer (id bigint not null auto_increment, customer_id varchar(255), first_name varchar(255), last_name varchar(255), primary key (id)) engine=InnoDB
create table order_obj (id bigint not null auto_increment, created_at date, total double precision, updated_at date, customer_id bigint, primary key (id)) engine=InnoDB
alter table order_obj add constraint FKfudffqji7or95pxu2oruijbku foreign key (customer_id) references customer (id)
create table category (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB
create table customer (id bigint not null auto_increment, customer_id varchar(255), first_name varchar(255), last_name varchar(255), primary key (id)) engine=InnoDB
create table order_obj (id bigint not null auto_increment, created_at date, total double precision, updated_at date, customer_id bigint, primary key (id)) engine=InnoDB
alter table order_obj add constraint FKfudffqji7or95pxu2oruijbku foreign key (customer_id) references customer (id)
create table category (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB
create table customer (id bigint not null auto_increment, customer_id varchar(255), first_name varchar(255), last_name varchar(255), primary key (id)) engine=InnoDB
create table order_obj (id bigint not null auto_increment, created_at date, total double precision, updated_at date, customer_id bigint, primary key (id)) engine=InnoDB
alter table order_obj add constraint FKfudffqji7or95pxu2oruijbku foreign key (customer_id) references customer (id)
create table category (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB
create table customer (id bigint not null auto_increment, customer_id varchar(255), first_name varchar(255), last_name varchar(255), primary key (id)) engine=InnoDB
create table order_obj (id bigint not null auto_increment, created_at date, total double precision, updated_at date, customer_id bigint, primary key (id)) engine=InnoDB
alter table order_obj add constraint FKfudffqji7or95pxu2oruijbku foreign key (customer_id) references customer (id)