DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    ci  numeric NOT NULL UNIQUE,
    name varchar(30) NOT NULL UNIQUE,
    username varchar(15) NOT NULL UNIQUE,
    password varchar(15) NOT NULL,
    CONSTRAINT pk_ci PRIMARY KEY (ci)
);

CREATE TABLE accounts (
    id  SERIAL PRIMARY KEY,
    number NUMERIC NOT NULL UNIQUE,
    current_balance FLOAT NOT NULL,
    FK_User INT NOT NULL,
    FOREIGN KEY (FK_User) REFERENCES users (ci)
);

CREATE TABLE transactions (
    id  SERIAL PRIMARY KEY,
    amount FLOAT NOT NULL,
    type  varchar(30) NOT NULL,
    date DATE NOT NULL,
    description TEXT,
    FK_Source_Account INT,
    FK_Destination_Account INT,
    FOREIGN KEY (FK_Source_Account) REFERENCES accounts (id),
    FOREIGN KEY (FK_Destination_Account) REFERENCES accounts (id),
    CONSTRAINT check_type CHECK (
        type in ('deposit', 'transference', 'withdrawal')
    )
);