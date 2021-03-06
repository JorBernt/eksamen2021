CREATE TABLE Poststed (
      Postnr VARCHAR (4) NOT NULL,
      Poststed VARCHAR (50) NOT NULL,
      PRIMARY KEY(Postnr)
);
CREATE TABLE Kunde (
    KId int AUTO_INCREMENT NOT NULL,
    Fornavn VARCHAR (50) NOT NULL,
    Etternavn VARCHAR(50) NOT NULL,
    Adresse VARCHAR(50) NOT NULL,
    Postnr VARCHAR (4) NOT NULL,
    Telefonnr VARCHAR (8) NOT NULL,
    Epost VARCHAR (50) NOT NULL,
    PRIMARY KEY(KId),
    FOREIGN KEY(Postnr) REFERENCES Poststed(PostNr)
);
CREATE TABLE Pakke (
    PId int AUTO_INCREMENT NOT NULL,
    KId INT NOT NULL,
    Volum DECIMAL NOT NULL,
    Vekt DECIMAL NOT NULL,
    PRIMARY KEY(PId),
    FOREIGN KEY(KId) REFERENCES Kunde(KId)
);
