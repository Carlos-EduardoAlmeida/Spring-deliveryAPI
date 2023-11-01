CREATE TABLE address (
     userid TEXT PRIMARY KEY,
     FOREIGN KEY (userid) references users (id),
     cep TEXT NOT NULL,
     bairro TEXT NOT NULL ,
     logradouro TEXT NOT NULL,
     localidade TEXT NOT NULL,
     complemento TEXT,
     numero TEXT NOT NULL
);