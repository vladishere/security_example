create sequence hibernate_sequence start 3 increment 3;

create table usr (
     id int8 not null,
     username varchar(255) not null,
     password varchar(255) not null,
     email varchar(255),
     primary key (id)
);