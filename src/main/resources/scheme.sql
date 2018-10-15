drop table if exists coffee
;
create table coffee
(
	id bigserial not null
		constraint coffee_pkey
			primary key,
	name varchar(255) not null
)
;

alter table coffee owner to "user"
;

create unique index coffee_id_uindex
	on "coffee" (id)
;

