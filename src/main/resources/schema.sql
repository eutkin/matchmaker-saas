create table teams (
    name varchar(50) primary key not null ,
    heroes int not null ,
    titans int not null ,
    super int not null check super > 0 and super < 4,
    pack_id int not null ,
    "type" char check "type" in ('g', 'b')
);