create database hurtowniaSportowa;

use hurtowniaSportowa;


CREATE TABLE Dostawcy (
	nip VARCHAR(10)  NOT NULL,
	nazwa VARCHAR(30) NOT NULL,
	telefon VARCHAR(9) NOT NULL,
	ulica VARCHAR(30) NOT NULL,
	numerDomu VARCHAR(10) NOT NULL,
	numerLokalu INT NOT NULL,
	kodPocztowy VARCHAR(6) NOT NULL,
	miasto VARCHAR(30) NOT NULL,
	PRIMARY KEY(nip)
);

CREATE TABLE Zakupy (
	ID INT IDENTITY(1,1)  NOT NULL,
	data DATE NOT NULL,
	nipDostawcy VARCHAR(10) NOT NULL,
	nrFaktury VARCHAR(50),
	foreign key (nipDostawcy) references Dostawcy(nip),
	PRIMARY KEY (ID)
);

CREATE TABLE PozycjeZakupow (
	ID INT IDENTITY(1,1) NOT NULL,
	IDZakupu INT NOT NULL,
	cena DECIMAl NOT NULL,
	ilosc INT NOT NULL,
	PRIMARY KEY(ID),
	foreign key (IDZakupu) references Zakupy(ID)
);

CREATE TABLE Towary (
	ID INT IDENTITY(1,1) NOT NULL,
	kodSWW INT NOT NULL,
	numerIdentyfikacyjny INT NOT NULL,
	jednostkaMiary VARCHAR(20) NOT NULL,
	kategoria VARCHAR(30) NOT NULL,
	producent VARCHAR(30),
	nazwa VARCHAR(30) NOT NULL,
	opis TEXT NOT NULL,
	stawkaVAT DECIMAL NOT NULL
	PRIMARY KEY(ID)
);

CREATE TABLE TowaryMagazyn (
	ID INT IDENTITY(1,1) NOT NULL,
	IDTowaru INT NOT NULL,
	cenaSprzedazy DECIMAL NOT NULL,
	lokalizacja VARCHAR(50) NOT NULL,
	IDZakupu INT NOT NULL,
	ilos� INTEGER NULL,
	PRIMARY KEY(ID),
	foreign key (IDTowaru) references Towary(ID),
	foreign key (IDZakupu) references Zakupy(ID)
);

CREATE TABLE Klienci (
	nip VARCHAR(10)  NOT NULL,
	nazwa VARCHAR(30) NOT NULL,
	telefon VARCHAR(9) NOT NULL,
	ulica VARCHAR(30) NOT NULL,
	numerDomu VARCHAR(10) NOT NULL,
	numerLokalu INT NOT NULL,
	kodPocztowy VARCHAR(6) NOT NULL,
	miasto VARCHAR(30) NOT NULL,
	rabat INT NOT NULL,
	PRIMARY KEY(nip)
);

CREATE TABLE Sprzedaze (
	numerFaktury char(30) NOT NULL,
	nipKlienta VARCHAR(10) NOT NULL,
	data DATE NOT NULL,
	PRIMARY KEY(numerFaktury),
	foreign key (nipKlienta) references Klienci(nip)
);

CREATE TABLE PozycjeSprzedazy (
	ID INT IDENTITY(1,1) NOT NULL,
	numerFaktury char(30) NOT NULL,
	idMagazynowe INT NOT NULL,
	ile INT NOT NULL,
	rabat INT NOT NULL,
	PRIMARY KEY(ID),
	foreign key (numerFaktury) references Sprzedaze(numerFaktury),
	foreign key (idMagazynowe) references TowaryMagazyn(ID)
);


CREATE TABLE Uzytkownicy(
	ID INT IDENTITY(1,1) NOT NULL,
	login VARCHAR(15) NOT NULL,
	haslo VARCHAR(30) NOT NULL,
	email VARCHAR(30) NOT NULL,
	Primary key (ID)
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
	PRIMARY KEY(biorca, dawca),
	foreign key (biorca) references Uzytkownicy(ID),
	foreign key (dawca) references Uzytkownicy(ID)
);
	

