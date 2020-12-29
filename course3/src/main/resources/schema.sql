create table if not exists candy (
        id bigint not null,
        name nvarchar(255),
        price decimal(12,4),
        delivery_id bigint,
        primary key (id)
    );