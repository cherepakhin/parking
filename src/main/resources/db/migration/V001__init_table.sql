CREATE TABLE if not exists parking (
	"id"            serial          not null UNIQUE,
	"address"         varchar(255)    not null
);

CREATE table if not exists car (
	"id"            serial          not null UNIQUE,
	"gos_number"       varchar(20)     not null,
	"mark"       varchar(20)     not null,
	"parking_id" serial          not null
);
commit;

ALTER TABLE car ADD FOREIGN KEY(parking_id) REFERENCES parking (id);
