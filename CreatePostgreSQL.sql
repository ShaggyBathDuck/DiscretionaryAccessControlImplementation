CREATE TABLE Dostawcy (
  ID SERIAL NOT NULL,
	nip VARCHAR(20)  NOT NULL,
	nazwa VARCHAR(30) NOT NULL,
	telefon VARCHAR(9) NOT NULL,
	ulica VARCHAR(30) NOT NULL,
	numerDomu VARCHAR(10) NOT NULL,
	numerLokalu INT NOT NULL,
	kodPocztowy VARCHAR(6) NOT NULL,
	miasto VARCHAR(30) NOT NULL,
	PRIMARY KEY(id),
	UNIQUE(nip)
);

CREATE TABLE Zakupy (
	ID SERIAL NOT NULL,
	data DATE NOT NULL,
	idDostawcy INT NOT NULL,
	nrFaktury VARCHAR(30),
	foreign key (idDostawcy) references Dostawcy(id),
	PRIMARY KEY (ID)
);

CREATE TABLE PozycjeZakupow (
	ID SERIAL NOT NULL,
	IDZakupu INT NOT NULL,
	cena DECIMAl NOT NULL,
	ilosc INT NOT NULL,
	PRIMARY KEY(ID),
	foreign key (IDZakupu) references Zakupy(ID)
);

CREATE TABLE Towary (
	ID SERIAL NOT NULL,
	kodSWW INT NOT NULL,
	numerIdentyfikacyjny INT NOT NULL,
	jednostkaMiary VARCHAR(20) NOT NULL,
	kategoria VARCHAR(30) NOT NULL,
	producent VARCHAR(30),
	nazwa VARCHAR(30) NOT NULL,
	opis TEXT NOT NULL,
	stawkaVAT DECIMAL NOT NULL,
	PRIMARY KEY(ID)
);

CREATE TABLE TowaryMagazyn (
	ID SERIAL NOT NULL,
	IDTowaru INT NOT NULL,
	cenaSprzedazy DECIMAL NOT NULL,
	lokalizacja VARCHAR(50) NOT NULL,
	IDZakupu INT NOT NULL,
	ilosc INTEGER NULL,
	PRIMARY KEY(ID),
	foreign key (IDTowaru) references Towary(ID),
	foreign key (IDZakupu) references Zakupy(ID)
);

CREATE TABLE Klienci (
  ID SERIAL NOT NULL,
	nip VARCHAR(20)  NOT NULL,
	nazwa VARCHAR(30) NOT NULL,
	telefon VARCHAR(9) NOT NULL,
	ulica VARCHAR(30) NOT NULL,
	numerDomu VARCHAR(10) NOT NULL,
	numerLokalu INT NOT NULL,
	kodPocztowy VARCHAR(6) NOT NULL,
	miasto VARCHAR(30) NOT NULL,
	rabat INT NOT NULL,
	PRIMARY KEY(ID),
	UNIQUE(nip)
);

CREATE TABLE Sprzedaze (
  ID SERIAL NOT NULL,
	nrFaktury VARCHAR(30) NOT NULL,
	idKlienta INT NOT NULL,
	data DATE NOT NULL,
	PRIMARY KEY(ID),
	UNIQUE(nrFaktury),
	foreign key (idKlienta) references Klienci(id)
);

CREATE TABLE PozycjeSprzedazy (
	ID SERIAL NOT NULL,
	idSprzedaz INT NOT NULL,
	idMagazynowe INT NOT NULL,
	ilosc INT NOT NULL,
	rabat INT NOT NULL,
	PRIMARY KEY(ID),
	foreign key (idSprzedaz) references Sprzedaze(id),
	foreign key (idMagazynowe) references TowaryMagazyn(ID)
);


CREATE TABLE Uzytkownicy(
	ID SERIAL NOT NULL,
	login VARCHAR(15) NOT NULL,
	haslo VARCHAR(100) NOT NULL,
	email VARCHAR(30) NOT NULL,
	Primary key (ID)
);

CREATE TYPE akcje as ENUM('NONE', 'ACCESS', 'GRANT');

create table Uprawnienia(
  ID int NOT NULL,
  Stworz akcje NOT NULL,
  Czytaj akcje NOT NULL,
  Modyfikuj akcje NOT NULL,
  Usun akcje NOT NULL,
  PRIMARY KEY (ID)
);

create table PrzekazywanieUprawnien(
	dawca int NOT NULL,
	biorca int not null,
	klienci int not null,
	zakupy int not null,
	pozycjeZakupow int not null,
	towary int not null,
	towaryMagazyn int not null,
	sprzedaze int not null,
	pozycjeSprzedazy int not null,
	dostawcy int not null,
	przejmij BOOLEAN not NULL,
	admin BOOLEAN,
	PRIMARY KEY(biorca, dawca),
	foreign key (biorca) references Uzytkownicy(ID),
	foreign key (dawca) references Uzytkownicy(ID),
	foreign key (klienci) references Uprawnienia(ID),
	foreign key (zakupy) references Uprawnienia(ID),
	foreign key (pozycjeZakupow) references Uprawnienia(ID),
	foreign key (towary) references Uprawnienia(ID),
	foreign key (towaryMagazyn) references Uprawnienia(ID),
	foreign key (sprzedaze) references Uprawnienia(ID),
	foreign key (pozycjeSprzedazy) references Uprawnienia(ID),
	foreign key (dostawcy) references Uprawnienia(ID)
);

