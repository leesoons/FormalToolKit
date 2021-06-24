create table requirement
(
    id int auto_increment primary key,
    text text not null,
    description text null,
    state varchar (255) not null ,
    project int not null ,
    counter_example text not null
)charset = utf8;

create table context
(
    project_id int not null primary key ,
    code text null,
    priority_array text null,
    description varchar (255) null
)charset=utf8;

create table project
(
    id int auto_increment primary key,
    description text null ,
    name varchar (255) not null ,
    userId int not null,
    constraint project_user_id_fk foreign key (userId) references user (id)
)charset=utf8;

create table user
(
    id int auto_increment primary key ,
    username varchar (255) not null ,
    password varchar (255) not null ,
    constraint user_username_uindex unique (username)
);