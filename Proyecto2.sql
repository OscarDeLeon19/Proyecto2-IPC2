DROP SCHEMA Proyecto2;

CREATE DATABASE Proyecto2;

CREATE USER 'Hospital'@'localhost' IDENTIFIED BY 'Hospital_123';
GRANT SELECT, INSERT, DELETE, UPDATE ON Proyecto2.* TO 'Hospital'@'localhost';

USE Proyecto2;

CREATE TABLE Paciente(
	Codigo VARCHAR(20) NOT NULL,
	Nombre VARCHAR(50) NOT NULL,
	Sexo VARCHAR(10) NOT NULL,
	Fecha_De_Nacimiento DATE NOT NULL,
	DPI VARCHAR(13) NOT NULL,
	Telefono INT(10) NOT NULL,
	Peso FLOAT NOT NULL,
	Tipo_De_Sangre VARCHAR(20) NOT NULL,
	Correo_Electronico VARCHAR(50) NOT NULL,
	Contraseña VARCHAR(50),
	PRIMARY KEY (Codigo)
);

CREATE TABLE Medico(
	Codigo VARCHAR(20) NOT NULL,
	Nombre VARCHAR(50) NOT NULL,
	Numero_De_Colegiado INT(30) NOT NULL,
	DPI VARCHAR(13) NOT NULL,
	Telefono INT(10) NOT NULL,
	Correo_Electronico VARCHAR(50) NOT NULL,
	Hora_Entrada VARCHAR(5) NOT NULL,
	Hora_Salida VARCHAR(5) NOT NULL,
	Fecha_Inicio DATE NOT NULL,
	Contraseña VARCHAR(50),
	PRIMARY KEY (Codigo)
);

CREATE TABLE Consulta(
	Tipo VARCHAR(50) NOT NULL,
	Costo FLOAT NOT NULL,
	PRIMARY KEY (Tipo)
);

CREATE TABLE Especialidad(
	ID INT(10) NOT NULL AUTO_INCREMENT,
	Codigo_Medico VARCHAR(20) NOT NULL,
	Titulo VARCHAR(50) NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (Codigo_Medico) REFERENCES Medico(Codigo) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (Titulo) REFERENCES Consulta(Tipo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Tipo_Examen(
	Codigo VARCHAR(20) NOT NULL,
	Nombre VARCHAR(50) NOT NULL,
	Orden BOOLEAN NOT NULL,
	Descripcion LONGBLOB NOT NULL,
	Costo FLOAT NOT NULL,
	Tipo_Informe VARCHAR(5) NOT NULL,
	PRIMARY KEY (Codigo)
);

CREATE TABLE Laboratorista(
	Codigo VARCHAR(20) NOT NULL,
	Nombre VARCHAR(50) NOT NULL,
	Numero_de_Registro VARCHAR(30) NOT NULL,
	DPI VARCHAR(13) NOT NULL,
	Telefono INT(10) NOT NULL,
	Tipo_De_Examen VARCHAR(50) NOT NULL,
	Correo_Electronico VARCHAR(50) NOT NULL,
	Fecha_Inicio DATE NOT NULL,
	Contraseña VARCHAR(50),
	PRIMARY KEY (Codigo)
);

CREATE TABLE Dia_De_Trabajo(
	ID INT(10) NOT NULL AUTO_INCREMENT,
	Codigo_Laboratorista VARCHAR(20) NOT NULL,
	Dia VARCHAR(20) NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (Codigo_Laboratorista) REFERENCES Laboratorista(Codigo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Administrador(
	Codigo VARCHAR(20) NOT NULL,
	Nombre VARCHAR(50) NOT NULL,
	DPI VARCHAR(13) NOT NULL,
	Contraseña VARCHAR(50),
	PRIMARY KEY (Codigo)
);

CREATE TABLE Informe(
	Codigo INT(20) NOT NULL AUTO_INCREMENT,
	Codigo_Paciente VARCHAR(20) NOT NULL,
	Codigo_Medico VARCHAR(20) NOT NULL,
	Descripcion LONGBLOB NOT NULL,
	Fecha DATE NOT NULL,
	Hora VARCHAR(10) NOT NULL,
	PRIMARY KEY (Codigo),
	FOREIGN KEY (Codigo_Paciente) REFERENCES Paciente(Codigo) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (Codigo_Medico) REFERENCES Medico(Codigo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Resultado(
	Codigo INT(20) NOT NULL AUTO_INCREMENT,
	Codigo_Paciente VARCHAR(20) NOT NULL,
	Codigo_Examen VARCHAR(20) NOT NULL,
	Codigo_Medico VARCHAR(20) NOT NULL,
	Codigo_Laboratorista VARCHAR(20) NOT NULL,
	Orden VARCHAR(100),
	Informe VARCHAR(100) NOT NULL,
	Fecha Date NOT NULL,
	Hora VARCHAR(10) NOT NULL,
	PRIMARY KEY (Codigo),
	FOREIGN KEY (Codigo_Paciente) REFERENCES Paciente(Codigo) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (Codigo_Examen) REFERENCES Tipo_Examen(Codigo) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (Codigo_Laboratorista) REFERENCES Laboratorista(Codigo) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (Codigo_Medico) REFERENCES Medico(Codigo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Cita(
	Codigo INT(20) NOT NULL AUTO_INCREMENT,
	Codigo_Paciente VARCHAR(20) NOT NULL,
	Codigo_Medico VARCHAR(20) NOT NULL,
	Especialidad VARCHAR(20) NOT NULL,
	Fecha Date NOT NULL,
	Hora VARCHAR(10) NOT NULL,
	Estado VARCHAR(20),
	PRIMARY KEY (Codigo),
	FOREIGN KEY (Codigo_Paciente) REFERENCES Paciente(Codigo) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (Codigo_Medico) REFERENCES Medico(Codigo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Examen(
	Codigo INT(20) NOT NULL AUTO_INCREMENT,
	Codigo_Paciente VARCHAR(20) NOT NULL,
	Codigo_Medico VARCHAR(20),
	Codigo_Examen VARCHAR(20) NOT NULL,
	Fecha DATE NOT NULL,
	Orden VARCHAR(100),
	Estado VARCHAR(15),
	PRIMARY KEY (Codigo),
	FOREIGN KEY (Codigo_Paciente) REFERENCES Paciente(Codigo) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (Codigo_Medico) REFERENCES Medico(Codigo) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (Codigo_Examen) REFERENCES Tipo_Examen(Codigo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Sesion(
	Codigo VARCHAR(20) NOT NULL PRIMARY KEY
);


