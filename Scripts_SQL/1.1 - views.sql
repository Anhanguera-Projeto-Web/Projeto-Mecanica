-- =====================
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



-- =====================
DROP VIEW IF EXISTS `view_retorna_produtos_a_esgotar`;
CREATE  SQL SECURITY DEFINER VIEW `view_retorna_produtos_a_esgotar` AS
    SELECT 
        `pd`.`produtosid` AS `id`,
        `pm`.`definicao` AS `marca`,
        `pd`.`descricao` AS `descricao`,
        `pd`.`estoque` AS `estoque`
    FROM
        ((`produtos` `pd`
        JOIN `produto_tipo` `pt` ON ((`pt`.`produto_tipoid` = `pd`.`tipoproduto`)))
        JOIN `produto_marca` `pm` ON ((`pm`.`produto_marcaid` = `pd`.`tipomarca`)))
	WHERE `pd`.`disponivel` = 1
    ORDER BY `pd`.`estoque` ASC;




-- =====================
DROP VIEW IF EXISTS `view_ultimas_vendas_geral`;
CREATE  SQL SECURITY DEFINER VIEW `view_ultimas_vendas_geral` AS
    SELECT *
	FROM `vendas` vd

    INNER JOIN orcamento orca ON orca.vendaid = vd.vendasid
    INNER JOIN produtos  prod ON orca.produtoid = prod.produtosid
    ORDER BY `vd`.`created_at` DESC;




-- =====================
DROP VIEW IF EXISTS `view_gerar_relatorios`;
CREATE  SQL SECURITY DEFINER VIEW `view_gerar_relatorios` AS
    SELECT *
	FROM `vendas` vd

    INNER JOIN orcamento orca ON orca.vendaid = vd.vendasid
    INNER JOIN produtos  prod ON orca.produtoid = prod.produtosid
    WHERE (1=1)