CREATE TABLE person(personid INT NOT NULL PRIMARY KEY AUTO_INCREMENT, name TEXT);
CREATE TABLE thing(thingid INT NOT NULL PRIMARY KEY AUTO_INCREMENT, title TEXT, personid INT REFERENCES person(personid));


INSERT INTO person(name) VALUES ('john'),('jane');
INSERT INTO thing(title, personid) VALUES
    ('car', (SELECT personid FROM person WHERE name = 'john')),
    ('house', (SELECT personid FROM person WHERE name = 'jane')),
    ('tv', (SELECT personid FROM person WHERE name = 'john')),
    ('pc', (SELECT personid FROM person WHERE name = 'john'));