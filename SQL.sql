CREATE TABLE IF NOT EXISTS Endereco (
	id_endereco bigserial PRIMARY KEY,
	logradouro varchar(100),
	estado varchar(50),
	cidade varchar (50),
	numero varchar(10),
	cep varchar(15)
);

CREATE TABLE IF NOT EXISTS Funcionario (
	id_funcionario bigserial PRIMARY KEY,
	nome varchar(100) NOT NULL,
	matricula varchar(100) NOT NULL,
	tempo_experiencia integer,
	id_endereco bigint REFERENCES endereco(id_endereco)
);

CREATE TABLE IF NOT EXISTS Nutricionista (
	id_nutricionista bigserial PRIMARY KEY,
	crn varchar(15) NOT NULL,
	especialidade varchar(100)
);

CREATE TABLE IF NOT EXISTS Paciente (
	id_paciente bigserial PRIMARY KEY,
	nome varchar(100) NOT NULL,
	data_nascimento date,
	cpf varchar(15) NOT NULL,
	telefone varchar(20),
	email varchar(50),
	id_endereco bigint REFERENCES endereco(id_endereco)
);

CREATE TABLE IF NOT EXISTS Consulta (
	id_consulta bigserial PRIMARY KEY,
	id_nutricionista bigint NOT NULL REFERENCES Nutricionista(id_nutricionista),
	id_paciente bigint NOT NULL REFERENCES Paciente(id_paciente),
	data_consulta date,
	observacoes text
	);

-------------------------------------------------
select * from Endereco
	
INSERT INTO Endereco (logradouro, estado, cidade, numero, cep)
VALUES ('Rua da Areia', 'Santa Catarina', 'Florianópolis', '748', '98170-000'),
	('Rua da Praia', 'Paraná', 'Curitiba', '100', '88015-129'),
	('Rua Recanto do aconchego', 'São Paulo', 'São Paulo', '77', '77980-025');

select * from Funcionario
INSERT INTO Funcionario (nome, matricula, tempo_experiencia, id_endereco)
VALUES ('Meryl Streep','12345', 30, 1),
	   ('Viola Davis','15125', 25, 2),
	   ('Nicole Kidman','19748', 8, 3);

select * from Nutricionista
INSERT INTO Nutricionista (crn, especialidade)
VALUES ('CRN-5/1234','Nutrição gestacional'),
	   ('CRN-6/7891','Nutriçao Esportiva'),
	   ('CRN-4/5678','Nutriçao Hospitalar');

select * from Paciente
INSERT INTO Paciente (nome, data_nascimento, cpf, telefone, email, id_endereco)
VALUES ('Sandra Bullok', '17/06/1970', '12345678910', '(11) 99911-1111', 'sb@teste.com', 1),
	   ('Morgan Fremann', '02/05/1965', '10987654321', '(48) 99912-2222', 'mf@teste.com', 2),
	   ('Cesar Cielo', '21/07/1979', '1112233344456', '(54) 99955-3333', 'cc@teste.com', 3);

select * from Consulta
INSERT INTO Consulta (id_nutricionista, id_paciente, data_consulta, observacoes)
VALUES (1,1, '07/05/2024', 'Controle de peso gestacional.'),
	   (2,3, '17/06/2024', 'Dieta leve.'),
	   (3,2, '05/07/2024', 'Dieta proteica.');

---------------------------------------------
UPDATE Paciente SET telefone='(11) 99911-1115' WHERE nome = 'Sandra Bullok';

-------------------------------------------------------------------------

DELETE FROM Consulta WHERE id_nutricionista = 3;
DELETE FROM Nutricionista WHERE id_nutricionista = 3;
