-- Creates initially all the necessary Tables and Foreign Keys for the Database. This will be executed with H2 and MSSQL
create sequence hibernate_sequence start with 1 increment by 1;
create table contact_person (id bigint not null, name varchar(255) not null, primary key (id));
create table local_authority_district (id bigint not null, district varchar(255), district_key varchar(255), government_district varchar(255), name varchar(255) not null, type varchar(255) not null, contact_person_id bigint, primary key (id));
create table pricing_detail (id bigint not null, description varchar(255), price decimal(20,5), pricing_detail_type varchar(255), primary key (id));
create table pricing_master_data (id bigint not null, primary key (id));
create table pricing_master_data_pricing_details (pricing_master_data_id bigint not null, pricing_details_id bigint not null);
create table project (id bigint not null, local_authority_district_id bigint not null, pricing_master_data_id bigint not null, project_master_data_id bigint not null, primary key (id));
create table project_master_data (id bigint not null, development_area_name varchar(255) not null, network_operator varchar(255) not null, primary key (id));
alter table pricing_master_data_pricing_details add constraint UK_f5blurhux6snwoc0a27hln5ap unique (pricing_details_id);
alter table project add constraint UK_7pcg6p6hecm881w5pg14x6yyt unique (local_authority_district_id);
alter table project add constraint UK_8dlpsftw56i81lxr0hr5f5s96 unique (pricing_master_data_id);
alter table project add constraint UK_105w9x56960j9xilrbk9w9v78 unique (project_master_data_id);
alter table local_authority_district add constraint FKm68yhu9o2ohbt9fseoej6fhlw foreign key (contact_person_id) references contact_person;
alter table pricing_master_data_pricing_details add constraint FKfbp0ooiatt6hoj7slndb4j5rj foreign key (pricing_details_id) references pricing_detail;
alter table pricing_master_data_pricing_details add constraint FK4crt4mlqs3sg8tskviib7i59v foreign key (pricing_master_data_id) references pricing_master_data;
alter table project add constraint FKgnh98aneiu9q4plijbtx76x3i foreign key (local_authority_district_id) references local_authority_district;
alter table project add constraint FK9exyyvs4rg9ga1rl2s9oblc84 foreign key (pricing_master_data_id) references pricing_master_data;
alter table project add constraint FKroo6mnht0w05g2ydnfl8s85gr foreign key (project_master_data_id) references project_master_data;
