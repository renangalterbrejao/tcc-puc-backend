DROP TABLE IF EXISTS `horario`;
CREATE TABLE `horario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `hora_abertura_domingo` time NOT NULL,
  `hora_abertura_quarta` time NOT NULL,
  `hora_abertura_quinta` time NOT NULL,
  `hora_abertura_sabado` time NOT NULL,
  `hora_abertura_segunda` time NOT NULL,
  `hora_abertura_sexta` time NOT NULL,
  `hora_abertura_terca` time NOT NULL,
  `hora_fechamento_domingo` time NOT NULL,
  `hora_fechamento_quarta` time NOT NULL,
  `hora_fechamento_quinta` time NOT NULL,
  `hora_fechamento_sabado` time NOT NULL,
  `hora_fechamento_segunda` time NOT NULL,
  `hora_fechamento_sexta` time NOT NULL,
  `hora_fechamento_terca` time NOT NULL,
  PRIMARY KEY (`id`)
) engine=InnoDB default charset=utf8;

DROP TABLE IF EXISTS `cozinha`;
CREATE TABLE `cozinha` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `categoria_cozinha` varchar(60) NOT NULL,
  `data_cadastro` datetime NOT NULL,
  PRIMARY KEY (`id`)
) engine=InnoDB default charset=utf8;

DROP TABLE IF EXISTS `produto`;
CREATE TABLE `produto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `categoria_produto` varchar(255) NOT NULL,
  `data_cadastro` datetime NOT NULL,
  `quantidade_medida` double NOT NULL,
  `tipo_embalagem` varchar(255) DEFAULT NULL,
  `tipo_medida` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) engine=InnoDB default charset=utf8;

DROP TABLE IF EXISTS `forma_pagamento`;
CREATE TABLE `forma_pagamento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_cadastro` datetime NOT NULL,
  `tipo_forma_pagamento` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) engine=InnoDB default charset=utf8;

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contato` varchar(255) NOT NULL,
  `data_atualizacao` datetime NOT NULL,
  `data_cadastro` datetime NOT NULL,
  `email` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) engine=InnoDB default charset=utf8;

DROP TABLE IF EXISTS `estabelecimento`;
CREATE TABLE `estabelecimento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `aberto` bit(1) DEFAULT NULL,
  `ativo` bit(1) DEFAULT NULL,
  `categoria` varchar(255) NOT NULL,
  `cnpj` varchar(255) NOT NULL,
  `data_atualizacao` datetime NOT NULL,
  `data_cadastro` datetime NOT NULL,
  `descricao_empresa` varchar(255) NOT NULL,
  `endereco_bairro` varchar(255) DEFAULT NULL,
  `endereco_cep` varchar(255) DEFAULT NULL,
  `endereco_complemento` varchar(255) DEFAULT NULL,
  `endereco_logradouro` varchar(255) DEFAULT NULL,
  `endereco_numero` varchar(255) DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `nota_satisfacao` double NOT NULL,
  `permitir_abertura_automatica` bit(1) DEFAULT NULL,
  `taxa_minima_frete` decimal(19,2) NOT NULL,
  `endereco_cidade_id` bigint DEFAULT NULL,
  `horario_id` bigint DEFAULT NULL,
  `usuario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKifsnsvrq3kolwyebnpo9o9hso` (`endereco_cidade_id`),
  KEY `FKijifq2ute5pjoy3bxeh6tyg97` (`horario_id`),
  KEY `FKe1g4xgwlkp456t5t63nuk1rj5` (`usuario_id`),
  CONSTRAINT `FKe1g4xgwlkp456t5t63nuk1rj5` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKifsnsvrq3kolwyebnpo9o9hso` FOREIGN KEY (`endereco_cidade_id`) REFERENCES `cidade` (`id`),
  CONSTRAINT `FKijifq2ute5pjoy3bxeh6tyg97` FOREIGN KEY (`horario_id`) REFERENCES `horario` (`id`)
) engine=InnoDB default charset=utf8;

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `desconto` decimal(19,2) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `preco` decimal(19,2) NOT NULL,
  `cozinha_id` bigint DEFAULT NULL,
  `estabelecimento_id` bigint NOT NULL,
  `produto_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbjsn9k22ymaajl2pv8yw5nxog` (`cozinha_id`),
  KEY `FKi230fx7vk12pa1003eudilvhq` (`estabelecimento_id`),
  KEY `FKoya2x5ip1q2t3s0936vgjiyx9` (`produto_id`),
  CONSTRAINT `FKbjsn9k22ymaajl2pv8yw5nxog` FOREIGN KEY (`cozinha_id`) REFERENCES `cozinha` (`id`),
  CONSTRAINT `FKi230fx7vk12pa1003eudilvhq` FOREIGN KEY (`estabelecimento_id`) REFERENCES `estabelecimento` (`id`),
  CONSTRAINT `FKoya2x5ip1q2t3s0936vgjiyx9` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`)
) engine=InnoDB default charset=utf8;

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bandeira_cartao` varchar(255) DEFAULT NULL,
  `codigo_seguranca_cartao` varchar(255) DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `data_nascimento` varchar(255) DEFAULT NULL,
  `data_validade_cartao` datetime DEFAULT NULL,
  `endereco_bairro` varchar(255) DEFAULT NULL,
  `endereco_cep` varchar(255) DEFAULT NULL,
  `endereco_complemento` varchar(255) DEFAULT NULL,
  `endereco_logradouro` varchar(255) DEFAULT NULL,
  `endereco_numero` varchar(255) DEFAULT NULL,
  `numero_cartao` varchar(255) DEFAULT NULL,
  `endereco_cidade_id` bigint DEFAULT NULL,
  `usuario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK16ou1bgo1f9v7qa8kylw2qla5` (`endereco_cidade_id`),
  KEY `FKc3u631ocxdrtm3ccpme0kjlmu` (`usuario_id`),
  CONSTRAINT `FK16ou1bgo1f9v7qa8kylw2qla5` FOREIGN KEY (`endereco_cidade_id`) REFERENCES `cidade` (`id`),
  CONSTRAINT `FKc3u631ocxdrtm3ccpme0kjlmu` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) engine=InnoDB default charset=utf8;

DROP TABLE IF EXISTS `pedido`;
CREATE TABLE `pedido` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bandeira_cartao_pedido` varchar(255) DEFAULT NULL,
  `chave_pix_pedido` varchar(255) DEFAULT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `codigo_seguranca_cartao_pedido` varchar(255) DEFAULT NULL,
  `contato_pedido` varchar(255) DEFAULT NULL,
  `cpf_pedido` varchar(255) DEFAULT NULL,
  `data_horario_cancelamento` datetime,
  `data_horario_confirmacao` datetime,
  `data_horario_contestacao` datetime,
  `data_horario_criacao` datetime NOT NULL,
  `data_horario_entrega` datetime,
  `data_horario_falha` datetime,
  `data_validade_cartao_pedido` datetime DEFAULT NULL,
  `desconto_total` decimal(19,2) DEFAULT NULL,
  `email_pedido` varchar(255) DEFAULT NULL,
  `endereco_bairro` varchar(255) DEFAULT NULL,
  `endereco_cep` varchar(255) DEFAULT NULL,
  `endereco_complemento` varchar(255) DEFAULT NULL,
  `endereco_logradouro` varchar(255) DEFAULT NULL,
  `endereco_numero` varchar(255) DEFAULT NULL,
  `motivo_cancelamento` varchar(255) DEFAULT NULL,
  `nome_pedido` varchar(255) DEFAULT NULL,
  `numero_cartao_pedido` varchar(255) DEFAULT NULL,
  `preco_final` decimal(19,2) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `subtotal` decimal(19,2) DEFAULT NULL,
  `taxa_frete` decimal(19,2) DEFAULT NULL,
  `tempo_entrega` time DEFAULT NULL,
  `usuario_cliente_id` bigint DEFAULT NULL,
  `endereco_cidade_id` bigint DEFAULT NULL,
  `estabelecimento_id` bigint NOT NULL,
  `forma_pagamento_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKch093tiehpl0dj4hocxp243e9` (`usuario_cliente_id`),
  KEY `FKk987vfg9cpgx7qxj3166fdqig` (`endereco_cidade_id`),
  KEY `FKf526x4h8svo99hn8uc8n2ylla` (`estabelecimento_id`),
  KEY `FKqaa411xsl0xu4tkvt1wpccd3b` (`forma_pagamento_id`),
  CONSTRAINT `FKch093tiehpl0dj4hocxp243e9` FOREIGN KEY (`usuario_cliente_id`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FKf526x4h8svo99hn8uc8n2ylla` FOREIGN KEY (`estabelecimento_id`) REFERENCES `estabelecimento` (`id`),
  CONSTRAINT `FKk987vfg9cpgx7qxj3166fdqig` FOREIGN KEY (`endereco_cidade_id`) REFERENCES `cidade` (`id`),
  CONSTRAINT `FKqaa411xsl0xu4tkvt1wpccd3b` FOREIGN KEY (`forma_pagamento_id`) REFERENCES `forma_pagamento` (`id`)
) engine=InnoDB default charset=utf8;

DROP TABLE IF EXISTS `item_pedido`;
CREATE TABLE `item_pedido` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `desconto_item_pedido` decimal(19,2) NOT NULL,
  `desconto_total_item_pedido` decimal(19,2) NOT NULL,
  `observacao` varchar(255) DEFAULT NULL,
  `preco_item_pedido` decimal(19,2) NOT NULL,
  `preco_total_item_pedido` decimal(19,2) NOT NULL,
  `quantidade` int NOT NULL,
  `item_id` bigint NOT NULL,
  `pedido_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo9utcxtj3i5ep5727jd3qjtj1` (`item_id`),
  KEY `FK60ym08cfoysa17wrn1swyiuda` (`pedido_id`),
  CONSTRAINT `FK60ym08cfoysa17wrn1swyiuda` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`),
  CONSTRAINT `FKo9utcxtj3i5ep5727jd3qjtj1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) engine=InnoDB default charset=utf8;

DROP TABLE IF EXISTS `foto_item`;
CREATE TABLE `foto_item` (
  `content_type` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `nome_arquivo` varchar(255) DEFAULT NULL,
  `tamanho` bigint DEFAULT NULL,
  `item_id` bigint NOT NULL,
  PRIMARY KEY (`item_id`),
  CONSTRAINT `FKt7f547ycqs67xugp69fmwslnx` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) engine=InnoDB default charset=utf8;

DROP TABLE IF EXISTS `estabelecimento_forma_pagamento`;
CREATE TABLE `estabelecimento_forma_pagamento` (
  `estabelecimento_id` bigint NOT NULL,
  `forma_pagamento_id` bigint NOT NULL,
  PRIMARY KEY (`estabelecimento_id`,`forma_pagamento_id`),
  CONSTRAINT `FKohq58d6o388yxnbvsbj1u1mst` FOREIGN KEY (`forma_pagamento_id`) REFERENCES `forma_pagamento` (`id`),
  CONSTRAINT `FKtdnrj6ldt06wqc36yag383mk0` FOREIGN KEY (`estabelecimento_id`) REFERENCES `estabelecimento` (`id`)
) engine=InnoDB default charset=utf8;

DROP TABLE IF EXISTS `estabelecimento_cozinha`;
CREATE TABLE `estabelecimento_cozinha` (
  `estabelecimento_id` bigint NOT NULL,
  `cozinha_id` bigint NOT NULL,
  PRIMARY KEY (`estabelecimento_id`,`cozinha_id`),
  CONSTRAINT `FKf9wb5yv8kslf4hqba6agee3yx` FOREIGN KEY (`estabelecimento_id`) REFERENCES `estabelecimento` (`id`),
  CONSTRAINT `FKr9lrflk3s2fbrrxgnmjpqtogd` FOREIGN KEY (`cozinha_id`) REFERENCES `cozinha` (`id`)
) engine=InnoDB default charset=utf8;

DROP TABLE IF EXISTS `estabelecimento_produto`;
CREATE TABLE `estabelecimento_produto` (
  `estabelecimento_id` bigint NOT NULL,
  `produto_id` bigint NOT NULL,
  PRIMARY KEY (`estabelecimento_id`,`produto_id`),
  CONSTRAINT `FKdvjn41a0a4rdtlnlelmhhg0p0` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`),
  CONSTRAINT `FKj1i3c24e77g9jgmmrqsw9o29s` FOREIGN KEY (`estabelecimento_id`) REFERENCES `estabelecimento` (`id`)
) engine=InnoDB default charset=utf8;
