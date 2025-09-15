CREATE TABLE accommodation
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    name      VARCHAR(255) NULL,
    category  VARCHAR(255) NULL,
    host_id   BIGINT NULL,
    num_rooms INT NULL,
    is_rented BIT(1) NOT NULL,
    CONSTRAINT pk_accommodation PRIMARY KEY (id)
);

CREATE TABLE app_user
(
    username                   VARCHAR(255) NOT NULL,
    password                   VARCHAR(255) NULL,
    name                       VARCHAR(255) NULL,
    surname                    VARCHAR(255) NULL,
    is_account_non_expired     BIT(1)       NOT NULL,
    is_account_non_locked      BIT(1)       NOT NULL,
    is_credentials_non_expired BIT(1)       NOT NULL,
    is_enabled                 BIT(1)       NOT NULL,
    `role`                     VARCHAR(255) NULL,
    CONSTRAINT pk_app_user PRIMARY KEY (username)
);

CREATE TABLE country
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    name      VARCHAR(255) NULL,
    continent VARCHAR(255) NULL,
    CONSTRAINT pk_country PRIMARY KEY (id)
);

CREATE TABLE host
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    name       VARCHAR(255) NULL,
    surname    VARCHAR(255) NULL,
    country_id BIGINT NULL,
    user_123   VARCHAR(255) NULL,
    CONSTRAINT pk_host PRIMARY KEY (id)
);

CREATE TABLE review
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    comment          VARCHAR(255) NULL,
    rate             INT NOT NULL,
    accommodation_id BIGINT NULL,
    CONSTRAINT pk_review PRIMARY KEY (id)
);

CREATE TABLE temporary_reservation
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    user_username      VARCHAR(255) NULL,
    reservation_status VARCHAR(255) NULL,
    CONSTRAINT pk_temporaryreservation PRIMARY KEY (id)
);

CREATE TABLE temporary_reservation_accommodations
(
    temporary_reservation_id BIGINT NOT NULL,
    accommodations_id        BIGINT NOT NULL
);

ALTER TABLE host
    ADD CONSTRAINT uc_host_user_123 UNIQUE (user_123);

ALTER TABLE accommodation
    ADD CONSTRAINT FK_ACCOMMODATION_ON_HOST FOREIGN KEY (host_id) REFERENCES host (id);

ALTER TABLE host
    ADD CONSTRAINT FK_HOST_ON_COUNTRY FOREIGN KEY (country_id) REFERENCES country (id);

ALTER TABLE host
    ADD CONSTRAINT FK_HOST_ON_USER_123 FOREIGN KEY (user_123) REFERENCES app_user (username);

ALTER TABLE review
    ADD CONSTRAINT FK_REVIEW_ON_ACCOMMODATION FOREIGN KEY (accommodation_id) REFERENCES accommodation (id);

ALTER TABLE temporary_reservation
    ADD CONSTRAINT FK_TEMPORARYRESERVATION_ON_USER_USERNAME FOREIGN KEY (user_username) REFERENCES app_user (username);

ALTER TABLE temporary_reservation_accommodations
    ADD CONSTRAINT fk_temresacc_on_accommodation FOREIGN KEY (accommodations_id) REFERENCES accommodation (id);

ALTER TABLE temporary_reservation_accommodations
    ADD CONSTRAINT fk_temresacc_on_temporary_reservation FOREIGN KEY (temporary_reservation_id) REFERENCES temporary_reservation (id);