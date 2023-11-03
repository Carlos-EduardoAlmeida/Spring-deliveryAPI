CREATE TABLE address (
     id TEXT PRIMARY KEY NOT NULL,
     userid TEXT NOT NULL ,
     FOREIGN KEY (userid) references users (id),
     cep TEXT NOT NULL,
     bairro TEXT NOT NULL ,
     logradouro TEXT NOT NULL,
     localidade TEXT NOT NULL,
     complemento TEXT,
     numero TEXT NOT NULL,
     uf TEXT NOT NULL
);