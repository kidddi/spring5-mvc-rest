create table category (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB;
create table customer (id bigint not null auto_increment, firstName varchar(255), lastName varchar(255), customerId varchar(255), primary key (id)) engine=InnoDB;