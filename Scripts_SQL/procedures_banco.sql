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

DROP procedure IF EXISTS `projeto_mecanica`.`sp_criar_produto`;
;
DELIMITER $$
CREATE PROCEDURE `sp_criar_produto`(
	IN  descricao VARCHAR(11), 
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
DROP VIEW IF EXISTS `view_retorna_produtos`;

CREATE  SQL SECURITY DEFINER VIEW `view_retorna_produtos` AS
    SELECT 
        `pd`.`produtosid` AS `produtoid`,
        `pt`.`codigo` AS `cod_tipo_produto`,
        `pt`.`definicao` AS `tipo_produto`,
        `pm`.`codigo` AS `cod_tipo_marca`,
        `pm`.`definicao` AS `marca`,
        `pd`.`descricao` AS `descricao`,
        `pd`.`valor` AS `valor`,
        `pd`.`estoque` AS `estoque`,
        `pd`.`disponivel` AS `disponivel`
    FROM
        ((`produtos` `pd`
        JOIN `produto_tipo` `pt` ON ((`pt`.`produto_tipoid` = `pd`.`tipoproduto`)))
        JOIN `produto_marca` `pm` ON ((`pm`.`produto_marcaid` = `pd`.`tipomarca`)))
    WHERE 
        (1 = 1);

