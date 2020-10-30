DROP TABLE IF EXISTS subject CASCADE;
DROP TABLE IF EXISTS type CASCADE;

create table type
(
    id serial not null
        constraint type_pk
            primary key,
    name varchar
);

alter table type owner to current_user ;

create unique index type_id_uindex
    on type (id);

create table subject
(
	id serial not null
		constraint city_pk
			primary key
		constraint subject_subject_id_fk
			references subject
				on update cascade on delete cascade,
	name varchar,
	populating integer default 0 not null,
	parent integer,
	type integer
		constraint subject_type_id_fk
			references type
				on update cascade on delete set null
);

alter table subject owner to current_user;

create unique index city_id_uindex
	on subject (id);





INSERT INTO subject( name, populating, parent, type) VALUES ('Russia',99999999,null,null);
INSERT INTO subject( name, populating, parent, type) VALUES ('Moscow',999999,1,null);
INSERT INTO subject( name, populating, parent, type) VALUES ('Moscow region',99999,1,null);
INSERT INTO subject( name, populating, parent, type) VALUES ('Khimki',9999,3,null);
INSERT INTO subject( name, populating, parent, type) VALUES ('Mayakowskogo',99,4,null);
INSERT INTO subject( name, populating, parent, type) VALUES ('9k1',9,5,null);