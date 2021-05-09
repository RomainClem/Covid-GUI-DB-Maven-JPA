CREATE TABLE Person (
    person_id INT,
    phoneNumber VARCHAR(255),
    email VARCHAR(255),
    PRIMARY KEY (person_id)
);

CREATE TABLE Name (
    name_id INT,
    firstName VARCHAR(255),
    middleName VARCHAR(255),
    lastName VARCHAR(255),
    PRIMARY KEY (name_id),
    FOREIGN KEY (name_id)
      REFERENCES Person (person_id) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE Contact (
     contact_id INT AUTO_INCREMENT,
     person1Id INT,
     person2Id INT,
     dateContact TIMESTAMP,
     PRIMARY KEY(contact_id),
     FOREIGN KEY (person1Id)
         REFERENCES Person (person_id) ON UPDATE CASCADE ON DELETE CASCADE,
     FOREIGN KEY (person2Id)
         REFERENCES Person (person_id) ON UPDATE CASCADE ON DELETE CASCADE
);