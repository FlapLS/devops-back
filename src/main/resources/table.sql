drop table product;

CREATE TABLE product
(
    id         BIGINT NOT NULL,
    name       VARCHAR(255) NOT NULL,
    article    VARCHAR(255) NOT NULL,
    brand      VARCHAR(255) NOT NULL,
    type_color VARCHAR(255) NOT NULL,
    code_color VARCHAR(255) NOT NULL,
    creation_date     TIMESTAMP ,
    update_date     TIMESTAMP,
    amount FLOAT NOT NULL,
    actual_amount FLOAT NOT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);
