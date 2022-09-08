CREATE TABLE if not exists parking (
	"id"            serial          not null UNIQUE,
	"address"         varchar(255)    not null default '-'
);

CREATE table if not exists car (
	"id"            serial          not null UNIQUE,
	"gos_number"       varchar(20)     not null default '-',
	"model"       varchar(20)     not null default '-'
);

CREATE table if not exists parking_car (
    "parking_id" int not null,
    "car_id" int not null UNIQUE
);

ALTER TABLE parking_car ADD FOREIGN KEY (parking_id) REFERENCES parking(id);
ALTER TABLE parking_car ADD FOREIGN KEY (car_id) REFERENCES car(id);

commit;