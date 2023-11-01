CREATE TABLE orders (
    id TEXT NOT NULL PRIMARY KEY,
    userid TEXT NOT NULL,
    FOREIGN KEY (userid) references users (id),
    orders TEXT NOT NULL,
    quantity INTEGER NOT NULL
);