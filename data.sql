create table user (id bigint primary key auto_increment,name varchar(100) not null unique ,password varchar(150) not null ,email varchar(100) not null );
create table message(id bigint primary key auto_increment,message varchar(255),user_id bigint ,foreign key(user_id) references user(id));
insert into user (name,email,password) values ('dima','dima@mail.ru','$2a$10$jukOrGXVs4NAqyk8CW5gc.vesNnos93IfTZ7pqAe9F8OaeMroaetW'),
                                              ('valera','valerov@mail.ru','$2a$10$jukOrGXVs4NAqyk8CW5gc.vesNnos93IfTZ7pqAe9F8OaeMroaetW'),
                                              ('vlad','vladov@mail.ru','$2a$10$jukOrGXVs4NAqyk8CW5gc.vesNnos93IfTZ7pqAe9F8OaeMroaetW');
insert into message(message,user_id) values ('message1',1),
                                            ('message2',2),
                                            ('message3',3),
                                            ('message4',1),
                                            ('message5',2),
                                            ('message6',3);
