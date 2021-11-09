USE `projeto_mecanica`;
/*
=
= CRIAÇÃO DE TRIGGERS PARA UPDATES DE DATAS
=
*/

DROP TRIGGER IF EXISTS `trigger_usuario_update`;

DELIMITER $$
CREATE TRIGGER `trigger_usuario_update` BEFORE UPDATE 
ON `usuario` FOR EACH ROW 
BEGIN
	SET NEW.updated_at=NOW();
END $$
DELIMITER ;

DROP TRIGGER IF EXISTS `trigger_produto_update`;

DELIMITER $$
CREATE TRIGGER `trigger_produto_update` BEFORE UPDATE 
ON `produtos` FOR EACH ROW 
BEGIN
	SET NEW.updated_at=NOW();
END $$
DELIMITER ;


DROP TRIGGER IF EXISTS `trigger_vendas_update`;

DELIMITER $$
CREATE TRIGGER `trigger_vendas_update` BEFORE UPDATE 
ON `vendas` FOR EACH ROW 
BEGIN
	SET NEW.updated_at=NOW();
END $$
DELIMITER ;


/*
= 
= PROCEDURE DE AUTENTICACAO
=
*/

DROP function IF EXISTS `projeto_mecanica`.`fun_autentica_usuario`;
;
DELIMITER $$
CREATE FUNCTION `fun_autentica_usuario`(email VARCHAR(300), passwd VARCHAR(100)) 
	RETURNS INT
    DETERMINISTIC
BEGIN
	DECLARE cod_ret INT;
    
    SELECT COUNT(*) as cnt INTO cod_ret FROM `usuario` u 
    WHERE u.email = email
    AND u.senha = passwd;
    
    IF cod_ret > 0 THEN RETURN 1;
	
    END IF;
    RETURN 0;
END$$
DELIMITER ;
;

DROP procedure IF EXISTS `projeto_mecanica`.`sp_criar_usuario`;
;
DELIMITER $$
CREATE PROCEDURE `sp_criar_usuario`(
	IN  cpf VARCHAR(11), 
	IN	nome VARCHAR(200),
	IN  senha VARCHAR(100),
	IN  email VARCHAR(300),
	IN  endereco VARCHAR(200),
    IN  nivel INT UNSIGNED
)
BEGIN
	IF (nivel = 0 OR nivel = NULL) THEN SET nivel = 1; END IF;

	INSERT INTO `usuario` (cpf, nome, senha, email, endereco, nivel) VALUES(cpf, nome, senha,email, endereco, nivel);
END$$

DELIMITER ;
;

DROP PROCEDURE IF EXISTS `projeto_mecanica`.`sp_getinfo_user_by_id`;
;
DELIMITER $$
CREATE PROCEDURE `sp_getinfo_user_by_id`(IN id_user INT UNSIGNED)
BEGIN
	SELECT 
		 cpf
        ,nome
        ,email
        ,nivel
        ,endereco
	FROM `usuario` WHERE usuarioid = id_user;
END$$
DELIMITER ;
;

DROP PROCEDURE IF EXISTS `projeto_mecanica`.`sp_getinfo_user_by_mail`;
;
DELIMITER $$
CREATE PROCEDURE `sp_getinfo_user_by_mail`(IN mail VARCHAR(300))
BEGIN
	SELECT 
		 cpf
        ,nome
        ,email
        ,nivel
        ,endereco
	FROM `usuario` WHERE email = mail;
END$$
DELIMITER ;
;

DROP PROCEDURE IF EXISTS `projeto_mecanica`.`sp_get_vendas_by_user`;
;
DELIMITER $$
CREATE PROCEDURE `sp_get_vendas_by_user`(IN mail VARCHAR(300))
BEGIN

	SELECT *
	FROM `vendas` vd

    INNER JOIN orcamento orca ON orca.vendaid = vd.vendasid
    INNER JOIN produtos  prod ON orca.produtoid = prod.produtosid
    INNER JOIN usuarios  usr  ON usr.usuarioid = vd.funcionarioid

    WHERE usr.email = mail
    ORDER BY `vd`.`created_at` DESC;

END$$
DELIMITER ;
;


DROP procedure IF EXISTS `projeto_mecanica`.`sp_criar_produto`;
;
DELIMITER $$
CREATE PROCEDURE `sp_criar_produto`(
	IN  descricao VARCHAR(200), 
	IN	tipoproduto INT,
	IN  tipomarca INT,
	IN  valor DECIMAL(10,2)
)
proc_label:BEGIN
	
    IF (descricao = NULL OR descricao = '') THEN LEAVE proc_label; END IF;
	IF (tipoproduto = NULL OR tipoproduto = 0 OR valor = NULL) THEN LEAVE proc_label; END IF;
    
	INSERT INTO `produtos` (descricao, tipoproduto, tipomarca, valor) VALUES(descricao, tipoproduto, tipomarca, valor);
    
END$$
DELIMITER ;
;



DROP procedure IF EXISTS `projeto_mecanica`.`sp_realiza_orcamento`;
;

DELIMITER $$
CREATE PROCEDURE `sp_realiza_orcamento`(
    IN vendaid INT,
    IN produtoid INT
)
proc_label:BEGIN

    DECLARE estoque_inicial INT = (SELECT estoque FROM `projeto_mecanica`.`produtos` WHERE produtosid = produtoid);

    IF(estoque_inicial = 0) THEN LEAVE proc_label; END IF;

    estoque_inicial = estoque_inicial-1;

    -- Atualização de estoque
    UPDATE `projeto_mecanica`.`produtos` SET estoque = estoque_inicial WHERE produtosid = produtoid

    -- Atualização de disponibilidade (Se estoque chegar a zero)
    IF (estoque_inicial = 0 )THEN
        UPDATE `projeto_mecanica`.`produtos` SET disponivel = 0 WHERE produtosid = produtoid;
    END IF;

END$$
DELIMITER ;
;



DROP procedure IF EXISTS `projeto_mecanica`.`sp_realiza_venda`;
;

DELIMITER $$
CREATE PROCEDURE `sp_realiza_venda`(
        IN funcionarioid INT,
        IN cpf_cliente VARCHAR(11),
        IN metodo_pagamentoid INT
        OUT vendaid INT
)
proc_label:BEGIN

    IF (funcionarioid = NULL OR funcionarioid < 0) THEN LEAVE proc_label; END IF;
	IF (cpf_cliente = '') THEN cpf_cliente = NULL END IF;
    IF (metodo_pagamentoid = NULL OR metodo_pagamentoid < 0) THEN LEAVE proc_label; END IF;


    INSERT INTO vendas(funcionarioid, cpf_cliente, metodo_pagamento)
        VALUES(funcionarioid, cpf_cliente, metodo_pagamento);

    RETURN SELECT LAST_INSERT_ID();

END$$
DELIMITER ;
;