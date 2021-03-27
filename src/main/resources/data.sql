INSERT INTO `usuario` (`cpf`, `data_de_nascimento`, `email`, `nome`) VALUES ('88661285038', '2000-03-25', 'miguel@mail.com', 'Miguel Silva');
INSERT INTO `usuario` (`cpf`, `data_de_nascimento`, `email`, `nome`) VALUES ('04157792009', '1998-10-12', 'alice@mail.com', 'Alice Souza');

INSERT INTO `vacina` (`data_vacinacao`, `nome`, `usuario_id`) VALUES ('2000-03-25', 'bcg', 1);
INSERT INTO `vacina` (`data_vacinacao`, `nome`, `usuario_id`) VALUES ('2000-03-25', 'Influenza', 1);
INSERT INTO `vacina` (`data_vacinacao`, `nome`, `usuario_id`) VALUES ('1998-10-12', 'Tetravalente', 2);
INSERT INTO `vacina` (`data_vacinacao`, `nome`, `usuario_id`) VALUES ('1998-10-12', 'Hepatite B', 2);
INSERT INTO `vacina` (`data_vacinacao`, `nome`, `usuario_id`) VALUES ('1998-10-12', 'Hepatite A', 2);