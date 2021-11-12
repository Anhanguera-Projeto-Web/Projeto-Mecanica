DROP SCHEMA IF EXISTS `projeto_mecanica`;
CREATE SCHEMA `projeto_mecanica` DEFAULT CHARACTER SET UTF8MB4 ;
USE `projeto_mecanica`;

CREATE TABLE `nivel` (
	nivelid TINYINT UNSIGNED NOT NULL PRIMARY KEY UNIQUE AUTO_INCREMENT,
    definicao VARCHAR(100) NOT NULL
);

CREATE TABLE `usuario`(
	usuarioid INT UNSIGNED NOT NULL PRIMARY KEY UNIQUE AUTO_INCREMENT,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    nome VARCHAR(200) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    email VARCHAR(300) NOT NULL UNIQUE, 
	endereco VARCHAR(200) NOT NULL DEFAULT '',
    nivel TINYINT UNSIGNED NOT NULL DEFAULT 1,
    created_at DATETIME NOT NULL DEFAULT NOW(),
    updated_at DATETIME,
    CONSTRAINT FOREIGN KEY(nivel) 
		REFERENCES nivel(nivelid)
);

CREATE TABLE `produto_tipo`(
	produto_tipoid INT UNSIGNED NOT NULL PRIMARY KEY UNIQUE AUTO_INCREMENT,
    codigo VARCHAR(100) NOT NULL,
    definicao VARCHAR(200) NOT NULL
);

CREATE TABLE `produto_marca`(
	produto_marcaid INT UNSIGNED NOT NULL PRIMARY KEY UNIQUE AUTO_INCREMENT,
    codigo VARCHAR(100) NOT NULL,
    definicao VARCHAR(100) NOT NULL
);

CREATE TABLE `produtos`(
	produtosid INT UNSIGNED NOT NULL PRIMARY KEY UNIQUE AUTO_INCREMENT,
    tipoproduto INT UNSIGNED NOT NULL,
    tipomarca INT UNSIGNED NOT NULL,
    descricao VARCHAR(200) NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    estoque INT UNSIGNED DEFAULT NULL,
    disponivel BOOLEAN DEFAULT TRUE,
    created_at DATETIME NOT NULL DEFAULT NOW(),
    updated_at DATETIME,
    
    CONSTRAINT FOREIGN KEY (tipoproduto)
		REFERENCES produto_tipo(produto_tipoid),
        
    CONSTRAINT FOREIGN KEY (tipomarca)
		REFERENCES produto_marca(produto_marcaid)
);

CREATE TABLE `metodo_pagamento`(
	metodo_pagamentoid INT UNSIGNED NOT NULL PRIMARY KEY UNIQUE AUTO_INCREMENT,
	codigo VARCHAR(100) NOT NULL,
    definicao VARCHAR(100) NOT NULL
);

CREATE TABLE `vendas`(
	vendasid INT UNSIGNED NOT NULL PRIMARY KEY UNIQUE AUTO_INCREMENT,
    funcionarioid INT UNSIGNED NOT NULL,
    cpf_cliente VARCHAR(11) DEFAULT NULL,
    metodo_pagamento INT UNSIGNED NOT NULL,
    valortotal DECIMAL(10,2) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT NOW(),
    updated_at DATETIME,
    
    CONSTRAINT FOREIGN KEY (funcionarioid)
		REFERENCES usuario(usuarioid),
        
	CONSTRAINT FOREIGN KEY (metodo_pagamento)
		REFERENCES metodo_pagamento(metodo_pagamentoid)
);

CREATE TABLE `orcamento`(
	orcamentoid INT UNSIGNED NOT NULL PRIMARY KEY UNIQUE AUTO_INCREMENT,
    vendaid INT UNSIGNED NOT NULL,
    produtoid INT UNSIGNED NOT NULL,

    FOREIGN KEY (vendaid)
        REFERENCES vendas(vendasid),
    FOREIGN KEY (produtoid)
		REFERENCES produtos(produtosid)
);