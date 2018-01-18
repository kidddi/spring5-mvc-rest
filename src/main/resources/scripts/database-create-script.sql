create table category (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB
create table customer (id bigint not null auto_increment, first_name varchar(255), last_name varchar(255), primary key (id)) engine=InnoDB
create table order_obj (id bigint not null auto_increment, created_at date, total double precision, updated_at date, customer_id bigint, primary key (id)) engine=InnoDB
create table product (id bigint not null auto_increment, name varchar(255), vendor_id bigint, primary key (id)) engine=InnoDB
create table vendor (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB
alter table order_obj add constraint FKfudffqji7or95pxu2oruijbku foreign key (customer_id) references customer (id)
alter table product add constraint FK9tnjxr4w1dcvbo2qejikpxpfy foreign key (vendor_id) references vendor (id)