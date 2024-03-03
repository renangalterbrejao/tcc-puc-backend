set foreign_key_checks = 0;

lock tables cidade write, estado write, estabelecimento_produto write, estabelecimento_cozinha write,
	estabelecimento_forma_pagamento write, foto_item write, item_pedido write, pedido write,
	cliente write, item write, estabelecimento write, usuario write, forma_pagamento write,
	produto write, cozinha write, horario write; 

delete from cidade;
delete from estado;
delete from estabelecimento_produto;
delete from estabelecimento_cozinha;
delete from estabelecimento_forma_pagamento;
delete from foto_item;
delete from item_pedido;
delete from pedido;
delete from cliente;
delete from item;
delete from estabelecimento;
delete from usuario;
delete from forma_pagamento;
delete from produto;
delete from cozinha;
delete from horario;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table estado auto_increment = 1;
alter table estabelecimento_produto auto_increment = 1;
alter table estabelecimento_cozinha auto_increment = 1;
alter table estabelecimento_forma_pagamento auto_increment = 1;
alter table foto_item auto_increment = 1;
alter table item_pedido auto_increment = 1;
alter table pedido auto_increment = 1;
alter table cliente auto_increment = 1;
alter table item auto_increment = 1;
alter table estabelecimento auto_increment = 1;
alter table usuario auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table produto auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table horario auto_increment = 1;

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');
insert into estado (id, nome) values (4, 'Santa Catarina');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);
insert into cidade (id, nome, estado_id) values (6, 'Americana', 2);
insert into cidade (id, nome, estado_id) values (7, 'Florianópolis', 4);

insert into horario (id, hora_abertura_segunda, hora_abertura_terca, hora_abertura_quarta, hora_abertura_quinta, hora_abertura_sexta, 
	hora_abertura_sabado, 
	hora_abertura_domingo, hora_fechamento_segunda, hora_fechamento_Terca, hora_fechamento_quarta, hora_fechamento_quinta, hora_fechamento_sexta, 
	hora_fechamento_sabado, hora_fechamento_domingo) values (1, '08:00:00','08:00:00','08:00:00','08:00:00','08:00:00','08:00:00','08:00:00',
	'17:00:00', '17:00:00','17:00:00','17:00:00','17:00:00','17:00:00','12:00:00');
	
insert into horario (id, hora_abertura_segunda, hora_abertura_terca, hora_abertura_quarta, hora_abertura_quinta, hora_abertura_sexta, 
	hora_abertura_sabado, 
	hora_abertura_domingo, hora_fechamento_segunda, hora_fechamento_Terca, hora_fechamento_quarta, hora_fechamento_quinta, hora_fechamento_sexta, 
	hora_fechamento_sabado, hora_fechamento_domingo) values (2, '08:00:00','08:00:00','08:00:00','08:00:00','08:00:00','08:00:00','00:00:00',
	'17:00:00', '17:00:00','17:00:00','17:00:00','17:00:00','12:00:00','00:00:00');
	
insert into cozinha (id, categoria_cozinha, data_cadastro) values (1, 'Brasileira', '2023-05-31 18:20:04');
insert into cozinha (id, categoria_cozinha, data_cadastro) values (2, 'Americana', '2023-06-01 10:10:00');
insert into cozinha (id, categoria_cozinha, data_cadastro) values (3, 'Chinesa', '2023-06-01 11:20:15');
insert into cozinha (id, categoria_cozinha, data_cadastro) values (4, 'Japonesa', '2023-06-01 09:54:21');
insert into cozinha (id, categoria_cozinha, data_cadastro) values (5, 'Arabe', '2023-06-02 14:32:28');
insert into cozinha (id, categoria_cozinha, data_cadastro) values (6, 'Espanhola', '2023-06-02 16:00:45');
insert into cozinha (id, categoria_cozinha, data_cadastro) values (7, 'Italiana', '2023-06-03 08:03:34');
insert into cozinha (id, categoria_cozinha, data_cadastro) values (8, 'Mineira', '2023-06-03 09:12:58');

insert into produto (id, categoria_produto, data_cadastro, tipo_medida, quantidade_medida, tipo_embalagem)
	values (1, 'Bebidas', '2023-05-31 19:00:00', 'Mililitro', '350', 'Lata');
insert into produto (id, categoria_produto, data_cadastro, tipo_medida, quantidade_medida, tipo_embalagem)
	values (2, 'Bebidas', '2023-05-31 19:00:00', 'Litro', '1', 'Garrafa');
insert into produto (id, categoria_produto, data_cadastro, tipo_medida, quantidade_medida, tipo_embalagem)
	values (3, 'Bebidas', '2023-05-31 19:00:00', 'Litro', '2', 'Garrafa');
insert into produto (id, categoria_produto, data_cadastro, tipo_medida, quantidade_medida, tipo_embalagem)
	values (4, 'Petiscos', '2023-05-31 19:00:00', 'Unidade', '1', 'Pote');
insert into produto (id, categoria_produto, data_cadastro, tipo_medida, quantidade_medida, tipo_embalagem)
	values (5, 'Petiscos', '2023-05-31 19:00:00', 'Unidade', '1', 'Pacote');
insert into produto (id, categoria_produto, data_cadastro, tipo_medida, quantidade_medida, tipo_embalagem)
	values (6, 'Ovos, leites, queijos e cia', '2023-05-31 19:00:00', 'Unidade', '1', 'Bandeja');
insert into produto (id, categoria_produto, data_cadastro, tipo_medida, quantidade_medida, tipo_embalagem)
	values (7, 'Biscoitos', '2023-05-31 19:00:00', 'Unidade', '1', 'Embalagem');
insert into produto (id, categoria_produto, data_cadastro, tipo_medida, quantidade_medida, tipo_embalagem)
	values (8, 'Frutas', '2023-05-31 19:00:00', 'Kilograma', '2.5', 'Caixa');
insert into produto (id, categoria_produto, data_cadastro, tipo_medida, quantidade_medida, tipo_embalagem)
	values (9, 'Bebidas', '2023-05-31 19:00:00', 'Mililitro', '473', 'Lata');
insert into produto (id, categoria_produto, data_cadastro, tipo_medida, quantidade_medida, tipo_embalagem)
	values (10, 'Bebidas', '2023-05-31 19:00:00', 'Unidade', '12', 'Fardo');
	
	
	
insert into forma_pagamento (id, tipo_forma_pagamento, data_cadastro)
	values ('1', 'Cartão de Crédito', '2023-05-31 19:00:00');
insert into forma_pagamento (id, tipo_forma_pagamento, data_cadastro)
	values ('2', 'Pix', '2023-05-31 19:00:00');
	
	
	
insert into usuario (id, email, nome, senha, contato, data_cadastro, data_atualizacao)
	values ('1', 'avaliadortccamericana@gmail.com', 'Avaliador 1', '123456', '(19) 993813035', 
    '2023-05-31 19:00:00', '2023-05-31 19:00:00');
insert into usuario (id, email, nome, senha, contato, data_cadastro, data_atualizacao)
	values ('2', 'pizzaria_labaroni@gmail.com', 'Jorge Alberto Mendonza', '123456', '(19) 993813035', 
    '2023-05-31 19:00:00', '2023-05-31 19:00:00');
insert into usuario (id, email, nome, senha, contato, data_cadastro, data_atualizacao)
	values ('3', 'adegacarvalho@gmail.com', 'Patrícia Carvalho', '123456', '(19) 993813035', 
    '2023-05-31 19:00:00', '2023-05-31 19:00:00');
insert into usuario (id, email, nome, senha, contato, data_cadastro, data_atualizacao)
	values ('4', 'sao_vicente_saovito@gmail.com', 'Administrador', '123456', '(19) 993813035', 
    '2023-05-31 19:00:00', '2023-05-31 19:00:00');
insert into usuario (id, email, nome, senha, contato, data_cadastro, data_atualizacao)
	values ('5', 'mercado_maxi@gmail.com', 'Administrador', '123456', '(19) 993813035', 
    '2023-05-31 19:00:00', '2023-05-31 19:00:00');
insert into usuario (id, email, nome, senha, contato, data_cadastro, data_atualizacao)
	values ('6', 'avaliador2tccbelohorizonte@gmail.com', 'Avaliador 2', '123456', '(19) 993813035', 
    '2023-05-31 19:00:00', '2023-05-31 19:00:00');
insert into usuario (id, email, nome, senha, contato, data_cadastro, data_atualizacao)
	values ('7', 'fort_atacadista_americana@gmail.com', 'Administrador', '123456', '(19) 993813035', 
    '2023-06-14 10:00:00', '2023-06-14 10:00:00');
insert into usuario (id, email, nome, senha, contato, data_cadastro, data_atualizacao)
	values ('8', 'dona_filo123@gmail.com', 'Administrador', '123456', '(19) 993813035', 
    '2023-05-31 19:00:00', '2023-05-31 19:00:00');
    
    
    
insert into estabelecimento (id, nome, descricao_empresa, taxa_minima_frete, categoria, aberto, ativo, cnpj, 
	data_cadastro, data_atualizacao, endereco_bairro, endereco_complemento, endereco_cep, endereco_logradouro, 
	endereco_numero, endereco_cidade_id, nota_satisfacao, permitir_abertura_automatica, horario_id, usuario_id)
values
	(1, 'La Baroni', 'A Pizzaria perfeita!', '10.00', 'Pizzaria', 1, 1, '20.383.613/0001-62', '2023-05-31 19:00:00', 
	'2023-05-31 19:00:00', 'Prefeito Antônio Zanaga II', '', '13465-000', 'Av. Cândido Portinari', '500', 6, 2.0, 
    0, 2, 2);
insert into estabelecimento (id, nome, descricao_empresa, taxa_minima_frete, categoria, aberto, ativo, cnpj, 
	data_cadastro, data_atualizacao, endereco_bairro, endereco_complemento, endereco_cep, endereco_logradouro, 
	endereco_numero, endereco_cidade_id, nota_satisfacao, permitir_abertura_automatica, horario_id, usuario_id)
values
	(2, 'Carvalho', 'A melhor adega da cidade!', '5.00', 'Adega', 1, 1, '20.383.613/0001-62', '2023-05-31 19:00:00', 
	'2023-05-31 19:00:00', 'Centro', '', '13465-000', 'Av. Cândido Portinari', '200', 6, 3.0, 
    0, 2, 3);
insert into estabelecimento (id, nome, descricao_empresa, taxa_minima_frete, categoria, aberto, ativo, cnpj, 
	data_cadastro, data_atualizacao, endereco_bairro, endereco_complemento, endereco_cep, endereco_logradouro, 
	endereco_numero, endereco_cidade_id, nota_satisfacao, permitir_abertura_automatica, horario_id, usuario_id)
values
	(3, 'São Vicente', 'A maior rede de supermercados da região.', '5.00', 'Mercado', 1, 1, '20.383.613/0001-62', '2023-05-31 19:00:00', 
	'2023-05-31 19:00:00', 'São Vito', '', '13465-000', 'Rua João Bernestein', '630', 6, 3.0, 
    0, 2, 4);
insert into estabelecimento (id, nome, descricao_empresa, taxa_minima_frete, categoria, aberto, ativo, cnpj, 
	data_cadastro, data_atualizacao, endereco_bairro, endereco_complemento, endereco_cep, endereco_logradouro, 
	endereco_numero, endereco_cidade_id, nota_satisfacao, permitir_abertura_automatica, horario_id, usuario_id)
values
	(4, 'Maxi Atacado', 'O maior mercado da cidade!', '8.00', 'Mercado', 1, 1, '20.383.613/0001-62', '2023-05-31 19:00:00', 
	'2023-05-31 19:00:00', 'Centro', '', '13465-000', 'Avenida Augusto de Lima', '744', 2, 3.0, 
    0, 2, 5);
insert into estabelecimento (id, nome, descricao_empresa, taxa_minima_frete, categoria, aberto, ativo, cnpj, 
	data_cadastro, data_atualizacao, endereco_bairro, endereco_complemento, endereco_cep, endereco_logradouro, 
	endereco_numero, endereco_cidade_id, nota_satisfacao, permitir_abertura_automatica, horario_id, usuario_id)
values
	(5, 'Fort Atacadista', 'A rede de supermercados que você confia!', '12.00', 'Mercado', 1, 1, '20.383.613/0001-62', '2023-06-14 11:00:00', 
	'2023-06-14 11:00:00', 'Centro', '', '13465-000', 'Rua Sete', '115', 6, 2.0, 
    0, 2, 4);
insert into estabelecimento (id, nome, descricao_empresa, taxa_minima_frete, categoria, aberto, ativo, cnpj, 
	data_cadastro, data_atualizacao, endereco_bairro, endereco_complemento, endereco_cep, endereco_logradouro, 
	endereco_numero, endereco_cidade_id, nota_satisfacao, permitir_abertura_automatica, horario_id, usuario_id)
values
	(6, 'Dona Filó', 'O melhor restaurante do mundo!', '10.00', 'Restaurante', 1, 1, '20.383.613/0001-62', '2023-05-31 19:00:00', 
	'2023-05-31 19:00:00', 'Centro', '', '13465-000', 'Rua Morais Costa', '98', 2, 3.0, 
    0, 2, 8);   
    
    
insert into item (id, ativo, desconto, nome, descricao, preco, cozinha_id, estabelecimento_id, produto_id)
	values (1, 1, '0.00', 'Coca-cola', 'Refrigerante Coca-cola lata', '3.50', null, 3, 1);
insert into item (id, ativo, desconto, nome, descricao, preco, cozinha_id, estabelecimento_id, produto_id)
	values (2, 1, '0.00', 'Coca-cola', 'Refrigerante Coca-cola garrafa', '4.50', null, 2, 2);
insert into item (id, ativo, desconto, nome, descricao, preco, cozinha_id, estabelecimento_id, produto_id)
	values (3, 1, '1.00', 'Coca-cola', 'Refrigerante Coca-cola garrafa', '8.50', null, 2, 3);
insert into item (id, ativo, desconto, nome, descricao, preco, cozinha_id, estabelecimento_id, produto_id)
	values (4, 1, '0.00', 'Cerveja Amstel', 'Cerveja Amstel lata', '4.99', null, 1, 1);
insert into item (id, ativo, desconto, nome, descricao, preco, cozinha_id, estabelecimento_id, produto_id)
	values (5, 1, '0.00', 'Cerveja Amstel', 'Cerveja Amstel lata', '4.99', null, 3, 1);
insert into item (id, ativo, desconto, nome, descricao, preco, cozinha_id, estabelecimento_id, produto_id)
	values (6, 1, '0.50', 'Azeitonas', 'Pote de Azeitonas', '8.99', null, 3, 4);
insert into item (id, ativo, desconto, nome, descricao, preco, cozinha_id, estabelecimento_id, produto_id)
	values (7, 1, '0.50', 'Azeitonas', 'Pote de Azeitonas', '8.99', null, 4, 4);
insert into item (id, ativo, desconto, nome, descricao, preco, cozinha_id, estabelecimento_id, produto_id)
	values (8, 1, '0.00', 'Amendoim Dori', 'Pacote de Amendoim Dori', '7.00', null, 3, 5);
insert into item (id, ativo, desconto, nome, descricao, preco, cozinha_id, estabelecimento_id, produto_id)
	values (9, 1, '0.00', 'Amendoim Dori', 'Pacotinho de Amendoim Dori', '7.00', null, 4, 5);
insert into item (id, ativo, desconto, nome, descricao, preco, cozinha_id, estabelecimento_id, produto_id)
	values (12, 1, '1.00', 'Energético Monster', 'Bebida energética Monster lata', '8.50', null, 3, 9);
insert into item (id, ativo, desconto, nome, descricao, preco, cozinha_id, estabelecimento_id, produto_id)
	values (13, 1, '3.00', 'Energético Monster', 'Bebida energética Monster fardo 12 latas', '96.00', null, 3, 10);
insert into item (id, ativo, desconto, nome, descricao, preco, cozinha_id, estabelecimento_id, produto_id)
	values (14, 1, '0.50', 'Coca-cola', 'Refrigerante Coca-cola lata', '4.10', null, 5, 1);
insert into item (id, ativo, desconto, nome, descricao, preco, cozinha_id, estabelecimento_id, produto_id)
	values (15, 1, '0.50', 'Cerveja Amstel', 'Cerveja Amstel lata', '5.15', null, 5, 1);
insert into item (id, ativo, desconto, nome, descricao, preco, cozinha_id, estabelecimento_id, produto_id)
	values (16, 1, '0.00', 'Amendoim Dori', 'Pacote de Amendoim Dori', '8.00', null, 5, 5);
insert into item (id, ativo, desconto, nome, descricao, preco, cozinha_id, estabelecimento_id, produto_id)
	values (17, 1, '0.30', 'Energético Monster', 'Bebida energética Monster lata', '9.50', null, 5, 9);
insert into item (id, ativo, desconto, nome, descricao, preco, cozinha_id, estabelecimento_id, produto_id)
	values (18, 1, '0.00', 'Coca-cola', 'Refrigerante Coca-cola lata', '5.00', null, 1, 1);
insert into item (id, ativo, desconto, nome, descricao, preco, cozinha_id, estabelecimento_id, produto_id)
	values (20, 1, '0.00', 'Coca-cola', 'Refrigerante Coca-cola lata', '4.00', null, 6, 1);



insert into item (id, ativo, desconto, nome, descricao, preco, cozinha_id, estabelecimento_id, produto_id)
	values (10, 1, '0.00', 'Super Hamburguer', 'Pão, Hamburguer de 300g, bacon, maionese, queijo prato e alface', '30.00', 2, 2, null);
insert into item (id, ativo, desconto, nome, descricao, preco, cozinha_id, estabelecimento_id, produto_id)
	values (11, 1, '0.00', 'Super Hamburguer', 'Pão, Hamburguer de 300g, bacon, maionese, queijo prato e alface', '30.00', 2, 4, null);
insert into item (id, ativo, desconto, nome, descricao, preco, cozinha_id, estabelecimento_id, produto_id)
	values (19, 1, '0.00', 'Pizza Napolitana', 'Presunto, mussarella, tomate, azeitonas e champignon', '35.50', 7, 1, null);
insert into item (id, ativo, desconto, nome, descricao, preco, cozinha_id, estabelecimento_id, produto_id)
	values (21, 1, '0.00', 'Pizza Napolitana', 'Presunto, tomate, azeitonas, champignon e muito queijo', '42.00', 7, 6, null);
	
	
	
insert into cliente (id, cpf, data_nascimento, endereco_bairro, endereco_cep, endereco_complemento, 
	endereco_logradouro, endereco_numero, endereco_cidade_id, numero_cartao, bandeira_cartao, codigo_seguranca_cartao,
	data_validade_cartao, usuario_id)
values (1, '607.562.780-40', '31/03/1986', 'Jardim Pérola II', '13465000', '', 'Rua Álvaro Machado', '40', 
	6, '5321 1267 1835 7787', 'Visa', '464', '2024-12-31 23:00:00', 1);
	
insert into cliente (id, cpf, data_nascimento, endereco_bairro, endereco_cep, endereco_complemento, 
	endereco_logradouro, endereco_numero, endereco_cidade_id, numero_cartao, bandeira_cartao, codigo_seguranca_cartao,
	data_validade_cartao, usuario_id)
values (6, '219.588.980-23', '20/06/1980', 'São José', '31275-000', '', 'Av. Antônio Abrahão Caram', '1001', 
	2, '5399 7238 1461 1906', 'Master Card', '744', '2025-01-09 23:00:00', 6);
	
	
insert into pedido (
  id,
  nome_pedido,
  email_pedido,
  contato_pedido,
  cpf_pedido,
  chave_pix_pedido,
  codigo,
  bandeira_cartao_pedido,
  numero_cartao_pedido,
  codigo_seguranca_cartao_pedido,
  data_validade_cartao_pedido,
  data_horario_criacao,
  data_horario_confirmacao,
  data_horario_cancelamento,
  data_horario_falha,
  data_horario_contestacao,
  data_horario_entrega,
  taxa_frete,
  subtotal,
  desconto_total,
  preco_final,
  endereco_bairro,
  endereco_cep,
  endereco_complemento,
  endereco_logradouro,
  endereco_numero,
  motivo_cancelamento,
  status,
  tempo_entrega,
  usuario_cliente_id,
  endereco_cidade_id,
  estabelecimento_id,
  forma_pagamento_id
)
values (1, 'Avaliador 1', 'avaliadortccamericana@gmail.com', '(19) 993813035', '607.562.780-40', 
	'', '52d01bb0-5ee8-41c5-bb43-946d61956cd8', 'Visa', '5321 1267 1835 7787', '464', '2024-12-31 23:59:59', '2023-06-01 22:00:00', 
    '2023-06-01 21:06:10', null, null, null, '2023-06-01 23:02:11', 
    '5.00', '30.00', '0.00', '35.00', 'Jardim Pérola II', '13465000', '', 
    'Rua Álvaro Machado', '40', '', 
	'ENTREGUE', '02:00', 1, 6, 2, 1);
	
	
	
insert into item_pedido (id, quantidade, preco_item_pedido, desconto_item_pedido, desconto_total_item_pedido, preco_total_item_pedido,
  observacao, item_id, pedido_id) 
values (1, 1, '30.00', '0.00', '0.00', '30.00', 'Sem tomates, por favor!', 10, 1);


insert into pedido (
  id,
  nome_pedido,
  email_pedido,
  contato_pedido,
  cpf_pedido,
  chave_pix_pedido,
  codigo,
  bandeira_cartao_pedido,
  numero_cartao_pedido,
  codigo_seguranca_cartao_pedido,
  data_validade_cartao_pedido,
  data_horario_criacao,
  data_horario_confirmacao,
  data_horario_cancelamento,
  data_horario_falha,
  data_horario_contestacao,
  data_horario_entrega,
  taxa_frete,
  subtotal,
  desconto_total,
  preco_final,
  endereco_bairro,
  endereco_cep,
  endereco_complemento,
  endereco_logradouro,
  endereco_numero,
  motivo_cancelamento,
  status,
  tempo_entrega,
  usuario_cliente_id,
  endereco_cidade_id,
  estabelecimento_id,
  forma_pagamento_id
)
values (2, 'Avaliador 2', 'avaliador2tccbelohorizonte@gmail.com', '(19) 993813035', '219.588.980-23', 
	'', '2193b1c0-4959-4ee6-ab58-50ace3394e8e', 'Visa', '5399 7238 1461 1906', '744', '2024-12-31 23:59:59', '2023-06-01 22:00:00', 
    '2023-06-01 21:06:10', null, null, null, '2023-06-01 23:02:11', 
    '8.00', '42.00', '0.00', '50.00', 'São José', '31275000', '', 
    'Av. Antônio Abrahão Caram', '1001', '', 
	'CRIADO', '02:00', 6, 2, 4, 1);
	
insert into item_pedido (id, quantidade, preco_item_pedido, desconto_item_pedido, desconto_total_item_pedido, preco_total_item_pedido,
  observacao, item_id, pedido_id) 
values (5, 1, '42.00', '0.00', '0.00', '42.00', '', 21, 2);

/*foto_item*/
insert into foto_item (item_id, tamanho, nome_arquivo, descricao, content_type)
values (1, 50334, 'd5f2efa9-90da-4b2e-a965-d0cfa03c6b13_Coca_garrafa_1l.png', 'ola!', 'image/png');
insert into foto_item (item_id, tamanho, nome_arquivo, descricao, content_type)
values (5, 50334, 'b75081db-a142-47c6-aabd-529b5f795b36_Cerveja_Amstel_lata_350ml.png', 'ola!', 'image/png');
insert into foto_item (item_id, tamanho, nome_arquivo, descricao, content_type)
values (6, 50334, '866dc216-f595-459b-b54d-05acb3675d94_pote_azeitona300g.png', 'ola!', 'image/png');
insert into foto_item (item_id, tamanho, nome_arquivo, descricao, content_type)
values (8, 50334, '15e2e19d-2ab2-4dae-aab6-4d34c0ef0277_pacote_amendoim_Dori.png', 'ola!', 'image/png');
insert into foto_item (item_id, tamanho, nome_arquivo, descricao, content_type)
values (12, 50334, '02b9db71-b56d-427a-a388-0ceb9f79ec03_Energetico_Monster_lata_473ml.png', 'ola!', 'image/png');
insert into foto_item (item_id, tamanho, nome_arquivo, descricao, content_type)
values (13, 50334, '02b9db71-b56d-427a-a388-0ceb9f79ec03_Energetico_Monster_lata_473ml.png', 'ola!', 'image/png');

insert into foto_item (item_id, tamanho, nome_arquivo, descricao, content_type)
values (4, 50334, 'b75081db-a142-47c6-aabd-529b5f795b36_Cerveja_Amstel_lata_350ml.png', 'ola!', 'image/png');
insert into foto_item (item_id, tamanho, nome_arquivo, descricao, content_type)
values (18, 50334, 'd5f2efa9-90da-4b2e-a965-d0cfa03c6b13_Coca_garrafa_1l.png', 'ola!', 'image/png');
insert into foto_item (item_id, tamanho, nome_arquivo, descricao, content_type)
values (19, 50334, '555d323e-2c97-4882-8fa4-7a94e5b8efe1_pizza.png', 'ola!', 'image/png');

insert into foto_item (item_id, tamanho, nome_arquivo, descricao, content_type)
values (2, 50334, 'd5f2efa9-90da-4b2e-a965-d0cfa03c6b13_Coca_garrafa_1l.png', 'ola!', 'image/png');
insert into foto_item (item_id, tamanho, nome_arquivo, descricao, content_type)
values (3, 50334, 'd5f2efa9-90da-4b2e-a965-d0cfa03c6b13_Coca_garrafa_1l.png', 'ola!', 'image/png');
insert into foto_item (item_id, tamanho, nome_arquivo, descricao, content_type)
values (10, 50334, '6e53ff44-7f67-49f7-be8a-5f264cc5180f_super_hamburguer.png', 'ola!', 'image/png');

insert into foto_item (item_id, tamanho, nome_arquivo, descricao, content_type)
values (14, 50334, 'd5f2efa9-90da-4b2e-a965-d0cfa03c6b13_Coca_garrafa_1l.png', 'ola!', 'image/png');
insert into foto_item (item_id, tamanho, nome_arquivo, descricao, content_type)
values (15, 50334, 'b75081db-a142-47c6-aabd-529b5f795b36_Cerveja_Amstel_lata_350ml.png', 'ola!', 'image/png');
insert into foto_item (item_id, tamanho, nome_arquivo, descricao, content_type)
values (17, 50334, '02b9db71-b56d-427a-a388-0ceb9f79ec03_Energetico_Monster_lata_473ml.png', 'ola!', 'image/png');
insert into foto_item (item_id, tamanho, nome_arquivo, descricao, content_type)
values (16, 50334, '15e2e19d-2ab2-4dae-aab6-4d34c0ef0277_pacote_amendoim_Dori.png', 'ola!', 'image/png');

insert into foto_item (item_id, tamanho, nome_arquivo, descricao, content_type)
values (20, 50334, 'd5f2efa9-90da-4b2e-a965-d0cfa03c6b13_Coca_garrafa_1l.png', 'ola!', 'image/png');
insert into foto_item (item_id, tamanho, nome_arquivo, descricao, content_type)
values (21, 50334, '555d323e-2c97-4882-8fa4-7a94e5b8efe1_pizza.png', 'ola!', 'image/png');

insert into foto_item (item_id, tamanho, nome_arquivo, descricao, content_type)
values (7, 50334, '866dc216-f595-459b-b54d-05acb3675d94_pote_azeitona300g.png', 'ola!', 'image/png');
insert into foto_item (item_id, tamanho, nome_arquivo, descricao, content_type)
values (9, 50334, '15e2e19d-2ab2-4dae-aab6-4d34c0ef0277_pacote_amendoim_Dori.png', 'ola!', 'image/png');
insert into foto_item (item_id, tamanho, nome_arquivo, descricao, content_type)
values (11, 50334, '6e53ff44-7f67-49f7-be8a-5f264cc5180f_super_hamburguer.png', 'ola!', 'image/png');


/*Estabelecimento formas de pagamento*/
insert into estabelecimento_forma_pagamento (estabelecimento_id, forma_pagamento_id)
values (1, 1);
insert into estabelecimento_forma_pagamento (estabelecimento_id, forma_pagamento_id)
values (1, 2);
insert into estabelecimento_forma_pagamento (estabelecimento_id, forma_pagamento_id)
values (2, 1);
insert into estabelecimento_forma_pagamento (estabelecimento_id, forma_pagamento_id)
values (2, 2);
insert into estabelecimento_forma_pagamento (estabelecimento_id, forma_pagamento_id)
values (3, 1);
insert into estabelecimento_forma_pagamento (estabelecimento_id, forma_pagamento_id)
values (3, 2);
insert into estabelecimento_forma_pagamento (estabelecimento_id, forma_pagamento_id)
values (4, 1);
insert into estabelecimento_forma_pagamento (estabelecimento_id, forma_pagamento_id)
values (4, 2);
insert into estabelecimento_forma_pagamento (estabelecimento_id, forma_pagamento_id)
values (5, 1);
insert into estabelecimento_forma_pagamento (estabelecimento_id, forma_pagamento_id)
values (5, 2);
insert into estabelecimento_forma_pagamento (estabelecimento_id, forma_pagamento_id)
values (6, 1);
insert into estabelecimento_forma_pagamento (estabelecimento_id, forma_pagamento_id)
values (6, 2);


/*Estabelecimento cozinhas*/
insert into estabelecimento_cozinha (estabelecimento_id, cozinha_id)
values (2, 2);
insert into estabelecimento_cozinha (estabelecimento_id, cozinha_id)
values (1, 7);
insert into estabelecimento_cozinha (estabelecimento_id, cozinha_id)
values (6, 7);
insert into estabelecimento_cozinha (estabelecimento_id, cozinha_id)
values (4, 2);


/*Estabelecimento produtos*/
insert into estabelecimento_produto (estabelecimento_id, produto_id)
values (1, 1);
insert into estabelecimento_produto (estabelecimento_id, produto_id)
values (1, 2);
insert into estabelecimento_produto (estabelecimento_id, produto_id)
values (1, 3);

insert into estabelecimento_produto (estabelecimento_id, produto_id)
values (2, 1);
insert into estabelecimento_produto (estabelecimento_id, produto_id)
values (2, 2);
insert into estabelecimento_produto (estabelecimento_id, produto_id)
values (2, 3);

insert into estabelecimento_produto (estabelecimento_id, produto_id)
values (3, 1);
insert into estabelecimento_produto (estabelecimento_id, produto_id)
values (3, 2);
insert into estabelecimento_produto (estabelecimento_id, produto_id)
values (3, 3);
insert into estabelecimento_produto (estabelecimento_id, produto_id)
values (3, 4);
insert into estabelecimento_produto (estabelecimento_id, produto_id)
values (3, 5);

insert into estabelecimento_produto (estabelecimento_id, produto_id)
values (4, 4);
insert into estabelecimento_produto (estabelecimento_id, produto_id)
values (4, 5);


insert into estabelecimento_produto (estabelecimento_id, produto_id)
values (5, 1);
insert into estabelecimento_produto (estabelecimento_id, produto_id)
values (5, 2);
insert into estabelecimento_produto (estabelecimento_id, produto_id)
values (5, 3);
insert into estabelecimento_produto (estabelecimento_id, produto_id)
values (5, 4);
insert into estabelecimento_produto (estabelecimento_id, produto_id)
values (5, 5);

insert into estabelecimento_produto (estabelecimento_id, produto_id)
values (6, 1);
insert into estabelecimento_produto (estabelecimento_id, produto_id)
values (6, 2);
insert into estabelecimento_produto (estabelecimento_id, produto_id)
values (6, 3);

unlock tables;








