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
    date TIMESTAMP NOT NULL,
    description TEXT,
    FK_Source_Account INT,
    FK_Destination_Account INT,
    FOREIGN KEY (FK_Source_Account) REFERENCES accounts (number),
    FOREIGN KEY (FK_Destination_Account) REFERENCES accounts (number),
    CONSTRAINT check_type CHECK (
        type in ('deposit', 'transference', 'withdrawal')
    )
);

INSERT INTO users VALUES (25869178,'Oscar','Oscar','123456');
INSERT INTO users VALUES (27952663,'Alfredo','Alfredo','123456');
INSERT INTO users VALUES (59292999,'Marcos','Marcos','123456');
INSERT INTO users VALUES (96356256,'Gabriel','Gabriel','123456');

INSERT INTO accounts VALUES (1,58005798,250.5,25869178);
INSERT INTO accounts VALUES (2,58004141,5000.5,25869178);
INSERT INTO accounts VALUES (3,58005555,250.5,27952663);
INSERT INTO accounts VALUES (7,56333555,250.5,27952663);
INSERT INTO accounts VALUES (4,58005544,50.5,59292999);
INSERT INTO accounts VALUES (5,58005566,89.5,59292999);
INSERT INTO accounts VALUES (6,58005577,2556.5,59292999);

INSERT INTO transactions VALUES (1,250.5,'deposit', '2020-07-07','description', 58005798,null );
INSERT INTO transactions VALUES (2,2000,'deposit', '2020-07-07','description', 58004141,null );
INSERT INTO transactions VALUES (3,1000,'deposit', '2020-07-07','description', 58004141,null );
INSERT INTO transactions VALUES (4,2000.5,'deposit', '2020-07-07','description', 58004141,null );
INSERT INTO transactions VALUES (5,250.5,'deposit', '2020-07-07','description', 58005555,null );
INSERT INTO transactions VALUES (6,250.5,'deposit', '2020-07-07','description', 56333555,null );