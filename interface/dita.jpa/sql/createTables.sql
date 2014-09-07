create table Users (
    userid char(50) not null,
    password char(20) not null,
    name varchar(255),
    email varchar(255),
    theme char(50),
    avatar blob(1M),
    primary key (userid)
);

create table roles (
	role char(50),
	description varchar(255),
	primary key(role)
);

create table UserRoles (
    userid char(50) not null,
    role char(50) not null,
    primary key (userid, role),
    CONSTRAINT users_fk
    FOREIGN KEY (userid)
	REFERENCES Users (userid),
	CONSTRAINT roles_fk
    FOREIGN KEY (role)
	REFERENCES roles (role)
);

