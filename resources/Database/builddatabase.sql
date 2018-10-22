# Table to store validation results
create table validation_result (
  id        int(10) not null auto_increment, 
  user_id   int(10) not null, 
  hash_type int(10) not null, 
  hash      varchar(128) not null, 
  file_name varchar(255) not null, 
  validated int(1) not null, 
  primary key (id)
);

# Table to store users
create table `user` (
  id       int(10) not null auto_increment, 
  username varchar(255), 
  primary key (id)
);

# Table to store countries
create table country (
  id           int(10) not null auto_increment, 
  country_code varchar(3) not null, 
  country_name varchar(255) not null, 
  primary key (id)
);

# Table to link countries to users (a user can be in multiple countries)
create table user_country (
  user_id    int(10) not null, 
  country_id int(10) not null, 
  primary key (user_id, country_id)
);


alter table user_country 
  add constraint FKuser_country_user foreign key (user_id) references `user` (id);

alter table user_country 
  add constraint FKuser_country_country foreign key (country_id) references `country` (id);

alter table validation_result 
  add constraint FKvalidation_result_user foreign key (user_id) references `user` (id);

