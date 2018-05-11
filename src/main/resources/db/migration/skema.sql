create table tabel_users(
    idusers varchar(255) not null,
    username varchar(255) not null,
    name varchar(255) not null,
    password varchar(255) not null,
    email varchar(255) not null,

    constraint pk_tabel_users_idusers PRIMARY KEY (idusers)
);

create table tabel_roles(
    idroles varchar(255) not null,
    name varchar(255) not null,
    description varchar(255) not null,

    constraint pk_tabel_roles_idroles PRIMARY KEY (idroles)
);

create table tabel_users_roles(
    idusers varchar(255) not null,
    idroles varchar(255) not null,

    constraint pk_tabel_users_roles_idusers FOREIGN KEY (idusers) REFERENCES
    tabel_users (idusers),
    constraint pk_tabel_users_roles_idroles FOREIGN KEY (idroles) REFERENCES
    tabel_roles (idroles)
);