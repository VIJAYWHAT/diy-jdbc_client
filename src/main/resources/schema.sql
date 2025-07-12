drop table if exists movie;

create table movie (
    id INT AUTO_INCREMENT PRIMARY KEY,
    directed_by varchar(80),
    title varchar(80) not null
)