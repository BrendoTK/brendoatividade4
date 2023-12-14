-- Inserir dados na tabela Pessoa (incluindo Médicos e Pacientes)
INSERT INTO Pessoa (Tipo_Pessoa, CRM, Nome, Telefone) VALUES('Medico', '123456789', 'Claudio', NULL);
INSERT INTO Pessoa (Tipo_Pessoa, CRM, Nome, Telefone) VALUES('Paciente', NULL, 'Brendo', '(11)12345-6789');
INSERT INTO Pessoa (Tipo_Pessoa, CRM, Nome, Telefone) VALUES('Medico', '987654321', 'Flavia', NULL);
INSERT INTO Pessoa (Tipo_Pessoa, CRM, Nome, Telefone) VALUES('Paciente', NULL, 'Nicoly', '(51)98765-4321');
INSERT INTO Pessoa (Tipo_Pessoa, CRM, Nome, Telefone) VALUES('Medico', '777777777', 'Nelma', NULL);
INSERT INTO Pessoa (Tipo_Pessoa, CRM, Nome, Telefone) VALUES('Paciente', NULL, 'Jhuly', '(88)88888-8888');

-- Inserir dados na tabela Consulta (considerando que os pacientes e médicos foram criados anteriormente)
INSERT INTO Consulta (Id_Medico, Id_Paciente, Valor, Data, Observacao) VALUES(1, 2, 100.0, '2023-11-07', 'Gripado');
INSERT INTO Consulta (Id_Medico, Id_Paciente, Valor, Data, Observacao) VALUES(3, 4, 50.0, '2023-09-10', 'Febre');
INSERT INTO Consulta (Id_Medico, Id_Paciente, Valor, Data, Observacao) VALUES(5, 6, 75.0, '2023-04-30', 'Desnutrição');
INSERT INTO Consulta (Id_Medico, Id_Paciente, Valor, Data, Observacao) VALUES(3, 2, 70.0, '2024-01-30', 'Dengue');
INSERT INTO Consulta (Id_Medico, Id_Paciente, Valor, Data, Observacao) VALUES(1, 4, 65.0, '2024-04-25', 'Chikungunya');
INSERT INTO Consulta (Id_Medico, Id_Paciente, Valor, Data, Observacao) VALUES(5, 6, 20.0, '2024-05-01', 'Verme');

-- Inserção dos perfis de usuários
INSERT INTO Role (nome) VALUES ('ROLE_ADMIN');
INSERT INTO Role (nome) VALUES ('ROLE_USER');

INSERT INTO Usuario (login, password) VALUES ('brendo', '$2a$10$M1YHKhYhdy/fO6jsBZ3IyOmJVmwz3pwnJ.jkHNC3R5ZF6N/dm5F0u'); //s:arroz123
INSERT INTO Usuario (login, password) VALUES ('douglas', '$2a$10$mn4tY7mORJpEvnPabTy4BecrjLQrLFHt4u1oFbJ8pCOGP2m8on8MG'); //s:feijao123


INSERT INTO Usuario_Roles (usuarios_id, roles_id) VALUES (1, 1);
INSERT INTO Usuario_Roles (usuarios_id, roles_id) VALUES (2, 2);