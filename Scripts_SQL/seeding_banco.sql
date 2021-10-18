USE `projeto_mecanica`;

INSERT INTO `nivel` (definicao) VALUES ("Funcionário");   -- Nivel 1
INSERT INTO `nivel` (definicao) VALUES ("Gerente"); 	  -- Nivel 2

INSERT INTO `produto_tipo`(codigo, definicao) VALUES("S.01", "Serviço para carro");
INSERT INTO `produto_tipo`(codigo, definicao) VALUES("S.02", "Serviço para moto");
INSERT INTO `produto_tipo`(codigo, definicao) VALUES("P.01", "Produtos comuns");
INSERT INTO `produto_tipo`(codigo, definicao) VALUES("P.02", "Produtos especias");

INSERT INTO `produto_marca`(codigo, definicao) VALUES("M.00", "Genérico");
INSERT INTO `produto_marca`(codigo, definicao) VALUES("M.01", "Fiat");
INSERT INTO `produto_marca`(codigo, definicao) VALUES("M.02", "Chevrolet");
INSERT INTO `produto_marca`(codigo, definicao) VALUES("M.03", "Peugeot");
INSERT INTO `produto_marca`(codigo, definicao) VALUES("M.04", "Nissan");
INSERT INTO `produto_marca`(codigo, definicao) VALUES("M.05", "Toyota");
INSERT INTO `produto_marca`(codigo, definicao) VALUES("M.07", "Volkswagem");
INSERT INTO `produto_marca`(codigo, definicao) VALUES("M.08", "Hyundai");
INSERT INTO `produto_marca`(codigo, definicao) VALUES("M.09", "Renault");


INSERT INTO `metodo_pagamento` (codigo, definicao) VALUES("MP.01", "Cartão de Crédito");
INSERT INTO `metodo_pagamento` (codigo, definicao) VALUES("MP.02", "Cartão de Débito");
INSERT INTO `metodo_pagamento` (codigo, definicao) VALUES("MP.03", "PIX");
INSERT INTO `metodo_pagamento` (codigo, definicao) VALUES("MP.04", "Depósito Bancário");
INSERT INTO `metodo_pagamento` (codigo, definicao) VALUES("MP.05", "À Vista");