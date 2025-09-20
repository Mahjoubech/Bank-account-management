CREATE DATABASE ServiceBank;
USE ServiceBank;
CREATE TABLE compte (
  code VARCHAR(10) PRIMARY KEY,
  type VARCHAR(20),
  solde DOUBLE,
  decouvert DOUBLE,
  tauxInteret DOUBLE
);
CREATE TABLE operation (
  numero VARCHAR(36) PRIMARY KEY,
  code_compte VARCHAR(10),
  type VARCHAR(20),
  montant DOUBLE,
  date DATETIME,
  details VARCHAR(100),
  FOREIGN KEY (code_compte) REFERENCES compte(code)
);