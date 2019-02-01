-- Creates initially all the necessary Tables and Foreign Keys for the Database. This will be executed with H2 and MSSQL
create sequence hibernate_sequence start with 1 increment by 1
create table contact_person (id bigint not null, name varchar(255) not null, primary key (id))
create table local_authority_district (id bigint not null, district varchar(255), district_key double, government_district varchar(255), name varchar(255) not null, type varchar(255) not null, contact_person_id bigint, primary key (id))
create table pricing_detail (id bigint not null, price decimal(20,5), pricing_detail_type varchar(255), pricing_master_data_id bigint, primary key (id))
create table pricing_master_data (id bigint not null, primary key (id))
create table project (id bigint not null, primary key (id))
create table project_master_data (id bigint not null, development_area_name varchar(255) not null, network_operator varchar(255) not null, primary key (id))
alter table local_authority_district add constraint FKm68yhu9o2ohbt9fseoej6fhlw foreign key (contact_person_id) references contact_person
alter table pricing_detail add constraint FKmgh2c53351dv94c7osy5lhfhl foreign key (pricing_master_data_id) references pricing_master_data
