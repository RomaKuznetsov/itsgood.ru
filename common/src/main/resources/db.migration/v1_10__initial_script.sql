create table category
(
    id          int unsigned auto_increment,
    title       char(30)  not null,
    description char(100) null,
    primary key (id, title),
    constraint title
        unique (title)
);

create table customer
(
    id          int unsigned auto_increment,
    firstname   varchar(50)                            not null,
    lastname    varchar(50)                            not null,
    username    varchar(50)                            not null,
    mail        varchar(250)                           not null,
    password    varchar(250)                           not null,
    phone       bigint(16)                             not null,
    birthday    date                                   not null,
    gender      enum ('male', 'female', 'transgender') null,
    create_time timestamp                              not null,
    update_time timestamp                              null,
    primary key (id, username, mail, phone, password),
    constraint mail
        unique (mail),
    constraint mail_2
        unique (mail),
    constraint password
        unique (password),
    constraint phone
        unique (phone),
    constraint username
        unique (username),
    constraint username_2
        unique (username)
);

create table address
(
    id          int unsigned auto_increment,
    customer_id int unsigned        not null,
    code        enum ('REG', 'DEL') not null,
    country     char(30)            not null,
    region      char(30) default '' not null,
    city        char(30)            not null,
    street      char(30)            not null,
    house       int(10)             not null,
    frame       char(30) default '' not null,
    apartment   int(10)             null,
    primary key (id, customer_id, code),
    constraint customer_id_1
        foreign key (customer_id) references customer (id)
            on update cascade on delete cascade
);

create index code
    on address (code);

create index customer_id
    on address (customer_id);

create index authentication
    on customer (username);

create index main
    on customer (mail);

create table equipment
(
    id            int unsigned auto_increment,
    address_id    int unsigned                                 not null,
    firstname     char(30)                                     not null,
    lastname      char(30)                                     not null,
    phone         bigint(16)                                   not null,
    loading_time  timestamp                                    null,
    shipment_time timestamp                                    null,
    stock_index   bigint(16) unsigned                          not null,
    distance      enum ('pickup', 'city', 'postal', 'country') not null,
    price         int unsigned                                 not null,
    validity      date                                         not null,
    primary key (id, phone, stock_index, address_id),
    constraint address_id_2
        foreign key (address_id) references address (id)
            on update cascade on delete cascade
);

create index address_id
    on equipment (address_id);

create index phone
    on equipment (phone);

create index stock_index
    on equipment (stock_index);

create table item
(
    id          int unsigned auto_increment,
    title       char(30)                        not null,
    price       int(10)                         not null,
    firm        char(100)                       null,
    link        char(255)                       null,
    description text                            null,
    weight      double(10, 3)                   not null,
    volume      enum ('big', 'small', 'medium') null,
    create_time timestamp                       not null,
    update_time timestamp                       null,
    category_id int(40) unsigned                not null,
    primary key (id, title, category_id),
    constraint link
        unique (link),
    constraint category_id_1
        foreign key (category_id) references category (id)
            on update cascade on delete cascade
);

create index category_id
    on item (category_id);

create table payment
(
    id          int unsigned auto_increment,
    status      enum ('ACT', 'PAS') not null,
    customer_id int unsigned        not null,
    phone       bigint(16)          not null,
    card        bigint(16)          not null,
    validity    date                not null,
    primary key (id, customer_id, phone, card),
    constraint card
        unique (card),
    constraint customer_id_3
        foreign key (customer_id) references customer (id)
            on update cascade on delete cascade
);

create table contract
(
    id            int unsigned auto_increment,
    customer_id   int unsigned                    not null,
    address_id    int unsigned                    not null,
    payment_id    int unsigned                    null,
    sum_order     int unsigned                    null,
    payment_types enum ('cash', 'card')           not null,
    relevance     enum ('relevant', 'irrelevant') not null,
    create_time   timestamp                       not null,
    update_time   timestamp                       null,
    primary key (id, customer_id, address_id, payment_types),
    constraint payment_id
        unique (payment_id),
    constraint address_id_1
        foreign key (address_id) references address (id)
            on update cascade on delete cascade,
    constraint customer_id_4
        foreign key (customer_id) references customer (id)
            on update cascade on delete cascade,
    constraint payment_id_1
        foreign key (payment_id) references payment (id)
            on update cascade on delete cascade
);

create table bucket
(
    id           int unsigned auto_increment,
    contract_id  int unsigned not null,
    item_id      int unsigned null,
    equipment_id int unsigned null,
    create_time  timestamp    not null,
    update_time  timestamp    null,
    primary key (id, contract_id),
    constraint contract_id_2
        foreign key (contract_id) references contract (id)
            on update cascade on delete cascade,
    constraint equipment_id_1
        foreign key (equipment_id) references equipment (id)
            on update cascade on delete set null,
    constraint item_id_1
        foreign key (item_id) references item (id)
            on update cascade on delete set null
);

create index contract_id
    on bucket (contract_id);

create index equipment_id
    on bucket (equipment_id);

create index item_id_q
    on bucket (item_id);

create index address
    on contract (address_id);

create index customer_id
    on contract (customer_id);

create index payment
    on contract (payment_id);

create index customer_id
    on payment (customer_id);

create table role
(
    id          int unsigned auto_increment,
    role        enum ('ROLE_administrator', 'ROLE_moderator', 'ROLE_user', 'ROLE_guest') not null,
    customer_id int unsigned                                                             not null,
    create_time timestamp                                                                not null,
    update_time timestamp                                                                null,
    validity    date                                                                     not null,
    primary key (id, customer_id, role),
    constraint customer_id_2
        foreign key (customer_id) references customer (id)
            on update cascade on delete cascade
);

create index customer_id
    on role (customer_id);

