USE buildingrental;

CREATE TABLE city(
	city_id Smallint AUTO_INCREMENT primary key,
    name varchar(50) NOT NULL,
    last_update Timestamp default current_timestamp
);

CREATE TABLE district(
	district_id Smallint AUTO_INCREMENT primary key,
    name varchar(50) NOT NULL,
    last_update Timestamp default current_timestamp
);

CREATE TABLE ward(
	ward_id smallint AUTO_INCREMENT primary key,
    name varchar(50) NOT NULL,
    last_update Timestamp default current_timestamp
);

create table address(
	address_id bigint auto_increment primary key,
    address varchar(50) not null,
    city_id smallint not null,
    district_id smallint unique not null,
    ward_id smallint not null,
    last_update timestamp default current_timestamp,
    foreign key(city_id) references city(city_id),
    foreign key(district_id) references district(district_id),
    foreign key(ward_id) references ward(ward_id)
);


CREATE TABLE rent_type(
	rent_type_id smallint auto_increment primary key,
    code varchar(50) not null,
    name varchar(100) not null,
     last_update Timestamp default current_timestamp
);

create table rent_area(
	rent_area_id smallint auto_increment primary key,
    building_id bigint,
    area_value decimal(10,2) unsigned not null,
    area_unit varchar(5) not null,
    last_update Timestamp default current_timestamp,
    foreign key(building_id) references building(building_id)
);

create table role(
	role_id smallint auto_increment primary key,
    code varchar(20) not null,
    name nvarchar(50) not null,
    description nvarchar(255)
);

create table transaction_type(
	transaction_type_id smallint auto_increment primary key,
    code varchar(20) not null,
    transaction_id bigint not null,
    name nvarchar(50) not null,
    description nvarchar(255),
    foreign key(transaction_id) references transaction(transaction_id)
);

create table user(
	user_id bigint auto_increment primary key,
    username varchar(50) unique not null,
    password varchar(255) not null,
    email varchar(50) unique not null,
    phone_number varchar(50) unique not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    day_of_birth date not null,
    active boolean default true,
    avatar_path varchar(255),
    registration_date timestamp default current_timestamp,
	last_update timestamp default current_timestamp
);

create table user_role(
	user_role_id bigint auto_increment primary key,
    user_id bigint not null,
    role_id smallint not null,
    foreign key(user_id) references user(user_id),
    foreign key(role_id) references role(role_id)
);

create table staff(
	staff_id int auto_increment primary key,
    user_id bigint unique not null,
    salary int,
    hired_date timestamp,
    foreign key(user_id) references user(user_id)
);

create table owner(
	owner_id int auto_increment primary key,
    user_id bigint unique not null,
    foreign key(user_id) references user(user_id)
);

create table customer(
	customer_id int auto_increment primary key,
    user_id bigint unique not null,
    foreign key(user_id) references user(user_id)
);

create table assignment_customer(
	assignment_customer_id int auto_increment primary key,
    customer_id int not null,
    staff_id int not null,
    last_update timestamp default current_timestamp,
    foreign key(customer_id) references customer(customer_id),
    foreign key(staff_id) references staff(staff_id)
);


create table building(
	building_id bigint auto_increment primary key,
    name nvarchar(255) not null,
    address_id bigint unique not null,
    owner_id int not null,
    structure nvarchar(100) not null,
    basement_floor smallint not null,
    floor_area smallint not null,
    direction nvarchar(50) not null,
    level char not null,
    rental_price bigint not null,
    rental_price_unit varchar(10) default 'VND/tháng',
    service_fee bigint not null,
    motor_parking_fee bigint,
    car_parking_fee bigint,
    brokerage_fee bigint not null,
    overtime_fee bigint not null,
    fee_unit varchar(10) default 'VND',
    electricity_fee smallint not null,
    electricity_fee_unit varchar(10) default 'VND/KWh',
    water_fee smallint not null,
    water_fee_unit varchar(10) default 'VND/m3',
    deposit bigint not null,
    rental_time date,
    repair_time date,
    image_path varchar(255),
    description nvarchar(255),
    foreign key(address_id) references address(address_id),
    foreign key(owner_id) references owner(owner_id)
);

create table building_rent_type(
	building_rent_type_id bigint auto_increment primary key,
    rent_type_id smallint not null,
    building_id bigint not null,
    last_update timestamp default current_timestamp,
    foreign key(rent_type_id) references rent_type(rent_type_id),
    foreign key(building_id) references building(building_id)
);

create table building_rent_area(
	building_rent_area_id bigint auto_increment primary key,
    rent_area_id smallint not null,
    building_id bigint not null,
    last_update timestamp default current_timestamp,
    foreign key(rent_area_id) references rent_area(rent_area_id),
    foreign key(building_id) references building(building_id)
);

create table transaction(
	transaction_id bigint auto_increment primary key,
    customer_id int not null,
    transaction_address nvarchar(100),
    tranasaction_time timestamp default current_timestamp,
    foreign key(customer_id) references customer(customer_id)
);

create table assignment_building(
	assignment_building_id int auto_increment primary key,
    staff_id int not null ,
    building_id bigint not null,
    last_update timestamp default current_timestamp,
    foreign key(staff_id) references staff(staff_id),
    foreign key(building_id) references building(building_id)
);
create table renting_building(
	renting_building_id bigint auto_increment primary key,
    building_id bigint not null,
    customer_id int not null,
    rent_date_start datetime,
    rent_date_end datetime,
    foreign key(building_id) references building(building_id),
	foreign key(customer_id) references customer(customer_id)
);
-- /////////////////////////////////////////////////////////////////////////////////////////////////////////////////--

USE buildingrental2;

CREATE TABLE city(
	city_id Smallint AUTO_INCREMENT primary key,
    name varchar(50) NOT NULL,
    last_update Timestamp default current_timestamp
);

CREATE TABLE district(
	district_id Smallint AUTO_INCREMENT primary key,
    name varchar(50) NOT NULL,
    last_update Timestamp default current_timestamp
);

CREATE TABLE ward(
	ward_id smallint AUTO_INCREMENT primary key,
    name varchar(50) NOT NULL,
    last_update Timestamp default current_timestamp
);

create table address(
	address_id bigint auto_increment primary key,
    address varchar(50) not null,
    city_id smallint not null,
    district_id smallint unique not null,
    ward_id smallint not null,
    last_update timestamp default current_timestamp,
    foreign key(city_id) references city(city_id),
    foreign key(district_id) references district(district_id),
    foreign key(ward_id) references ward(ward_id)
);


CREATE TABLE rent_type(
	rent_type_id smallint auto_increment primary key,
    code varchar(50) not null,
    name varchar(100) not null,
     last_update Timestamp default current_timestamp
);



create table role(
	role_id smallint auto_increment primary key,
    code varchar(20) not null,
    name nvarchar(50) not null,
    description nvarchar(255)
);

create table transaction_type(
	transaction_type_id smallint auto_increment primary key,
    code varchar(20) not null,
    transaction_id bigint not null,
    name nvarchar(50) not null,
    description nvarchar(255),
    foreign key(transaction_id) references transaction(transaction_id)
);

create table user(
	user_id bigint auto_increment primary key,
    username varchar(50) unique not null,
    password varchar(255) not null,
    email varchar(50) unique not null,
    phone_number varchar(50) unique not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    day_of_birth date not null,
    active boolean default true,
    avatar_path varchar(255),
    registration_date timestamp default current_timestamp,
	last_update timestamp default current_timestamp
);

create table user_role(
	user_role_id bigint auto_increment primary key,
    user_id bigint not null,
    role_id smallint not null,
    foreign key(user_id) references user(user_id),
    foreign key(role_id) references role(role_id)
);

create table staff(
	staff_id int auto_increment primary key,
    user_id bigint unique not null,
    salary int,
    hired_date timestamp,
    foreign key(user_id) references user(user_id)
);

create table owner(
	owner_id int auto_increment primary key,
    user_id bigint unique not null,
    foreign key(user_id) references user(user_id)
);

create table customer(
	customer_id int auto_increment primary key,
    user_id bigint unique not null,
	email varchar(50) unique not null,
    phone_number varchar(50) unique not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null
);

create table assignment_customer(
	assignment_customer_id int auto_increment primary key,
    customer_id int not null,
    user_id bigint not null,
    last_update timestamp default current_timestamp,
    foreign key(customer_id) references customer(customer_id),
    foreign key(user_id) references user(user_id)
);


create table building(
	building_id bigint auto_increment primary key,
    name nvarchar(255) not null,
    district_id smallint not null,
    owner_name nvarchar(255) not null,
    structure nvarchar(100) not null,
    basement_floor smallint not null,
    floor_area smallint not null,
    direction nvarchar(50) not null,
    level char not null,
    rental_price bigint not null,
    rental_price_unit varchar(10) default 'VND/tháng',
    service_fee bigint not null,
    motor_parking_fee bigint,
    car_parking_fee bigint,
    brokerage_fee bigint not null,
    overtime_fee bigint not null,
    fee_unit varchar(10) default 'VND',
    electricity_fee smallint not null,
    electricity_fee_unit varchar(10) default 'VND/KWh',
    water_fee smallint not null,
    water_fee_unit varchar(10) default 'VND/m3',
    deposit bigint not null,
    rental_time date,
    repair_time date,
    image_path varchar(255),
    description nvarchar(255),
    foreign key(district_id) references district(district_id)
);
create table rent_area(
	rent_area_id smallint auto_increment primary key,
    building_id bigint,
    area_value decimal(10,2) unsigned not null,
    area_unit varchar(5) not null,
    last_update Timestamp default current_timestamp,
    foreign key(building_id) references building(building_id)
);

create table building_rent_type(
	building_rent_type_id bigint auto_increment primary key,
    rent_type_id smallint not null,
    building_id bigint not null,
    last_update timestamp default current_timestamp,
    foreign key(rent_type_id) references rent_type(rent_type_id),
    foreign key(building_id) references building(building_id)
);


create table transaction(
	transaction_id bigint auto_increment primary key,
    customer_id int not null,
    building_id bigint not null,
    transaction_address nvarchar(100),
    tranasaction_time timestamp default current_timestamp,
    foreign key(customer_id) references customer(customer_id)
);

create table assignment_building(
	assignment_building_id int auto_increment primary key,
    user_id bigint not null ,
    building_id bigint not null,
    last_update timestamp default current_timestamp,
    foreign key(user_id) references user(user_id),
    foreign key(building_id) references building(building_id)
);

select * from actor 