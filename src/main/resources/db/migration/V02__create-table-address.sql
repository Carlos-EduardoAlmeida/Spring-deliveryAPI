CREATE TABLE address (
                         userid TEXT PRIMARY KEY,
                         cep TEXT NOT NULL,
                         bairro TEXT NOT NULL ,
                         logradouro TEXT NOT NULL,
                         localidade TEXT NOT NULL,
                         complemento TEXT,
                         numero TEXT NOT NULL
);