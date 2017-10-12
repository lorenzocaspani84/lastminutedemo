create table category (category_id bigint not null, name varchar(255), primary key (category_id));

create table goods (goods_id bigint not null, duty boolean, exempt boolean, name varchar(255), price numeric(19,2), quantity integer, category_id bigint, primary key (goods_id));

alter table goods add constraint FKn1hbjbxhwrd3wynoruqh31xk8 foreign key (category_id) references category (category_id);