create table schedule_transport IF NOT EXISTS (
    id int NOT NULL AUTO_INCREMENT primary key,
    cod_sign varchar(50) NOT NULL,
    schadule_date datetime NOT NULL
);