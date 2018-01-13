create table category (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB;
create table customer (id bigint not null auto_increment, firstName varchar(255), lastName varchar(255), customerId varchar(255), primary key (id)) engine=InnoDB;
create table order_obj (id bigint not null auto_increment, created_at date, total double, updated_at date, customer_id bigint, primary key (id));
alter table order_obj add constraint FKfudffqji7or95pxu2oruijbku foreign key (customer_id) references customer (id);