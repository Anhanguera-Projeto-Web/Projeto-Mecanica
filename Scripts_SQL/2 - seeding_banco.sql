USE `projeto_mecanica`;

INSERT INTO `nivel` (definicao) VALUES ("Funcionário");   -- Nivel 1
INSERT INTO `nivel` (definicao) VALUES ("Gerente"); 	  -- Nivel 2

INSERT INTO `produto_tipo`(codigo, definicao) VALUES("S.01", "Serviço para carro");
INSERT INTO `produto_tipo`(codigo, definicao) VALUES("S.02", "Serviço para moto");
INSERT INTO `produto_tipo`(codigo, definicao) VALUES("S.03", "Serviço para caminhão");
INSERT INTO `produto_tipo`(codigo, definicao) VALUES("S.04", "Serviço para caminhonete");
INSERT INTO `produto_tipo`(codigo, definicao) VALUES("S.05", "Serviço para microonibus");
INSERT INTO `produto_tipo`(codigo, definicao) VALUES("S.06", "Serviço para ônibus");
INSERT INTO `produto_tipo`(codigo, definicao) VALUES("S.07", "Serviço para trator");


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
INSERT INTO `produto_marca`(codigo, definicao) VALUES("M.10", "Yamaha");


INSERT INTO `metodo_pagamento` (codigo, definicao) VALUES("MP.01", "Cartão de Crédito");
INSERT INTO `metodo_pagamento` (codigo, definicao) VALUES("MP.02", "Cartão de Débito");
INSERT INTO `metodo_pagamento` (codigo, definicao) VALUES("MP.03", "PIX");
INSERT INTO `metodo_pagamento` (codigo, definicao) VALUES("MP.04", "Depósito Bancário");
INSERT INTO `metodo_pagamento` (codigo, definicao) VALUES("MP.05", "À Vista");



CALL sp_criar_usuario("18092785708", "Wesley Silva", "admin", "admin@admin.com", "Rua B", 2);
CALL sp_criar_usuario("12312312399", "Jose´Silva", "admin", "jose@silva.com", "Rua C", 1);


CALL sp_criar_produto("Óleo de Motor", 2, 10, 45.60); -- TP/MP/Preço -- Criando produto novo.
CALL sp_criar_produto("Aerofolio", 4, 1, 300.0); -- Criando produto novo.
CALL sp_criar_produto("Lubrificante", 2, 3, 50.0); -- Criando produto novo.
CALL sp_criar_produto("Pneus", 0, 0, 379.99); -- Criando produto novo.
CALL sp_criar_produto("Cera automotiva pastosa", 0, 0, 379.99); -- Criando produto novo.
CALL sp_criar_produto("Cera automotiva líquida", 0, 0, 379.99); -- Criando produto novo.
CALL sp_criar_produto("Cera automotiva Spray", 0, 0, 379.99); -- Criando produto novo.
CALL sp_criar_produto("Limpa Motor e Rodas", 0, 0, 379.99); -- Criando produto novo.
CALL sp_criar_produto("Massa de Polir", 0, 0, 379.99); -- Criando produto novo.
CALL sp_criar_produto("Espuma multiuso", 0, 0, 379.99); -- Criando produto novo.
CALL sp_criar_produto("Esponja", 0, 0, 379.99); -- Criando produto novo.
CALL sp_criar_produto("Pano", 0, 0, 379.99); -- Criando produto novo.
CALL sp_criar_produto("Toalha de microfibra", 0, 0, 379.99); -- Criando produto novo.
CALL sp_criar_produto("Limpa vidro", 0, 0, 16.41); -- Criando produto novo.
CALL sp_criar_produto("Pretinho", 0, 0, 379.99); -- Criando produto novo.
CALL sp_criar_produto("Silicone", 0, 0, 379.99); -- Criando produto novo.
CALL sp_criar_produto("Cilindro de Roda Traseiro", 0, 0, 379.99); -- Criando produto novo.
CALL sp_criar_produto("Aspirador de pó portátil", 0, 0, 379.99); -- Criando produto novo.
CALL sp_criar_produto("Capa para volante", 0, 0, 22.99); -- Criando produto novo.
CALL sp_criar_produto("CARPETE", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Luz Led", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Vela de iginição", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Sensor de temperatura da água", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Cilíndro mestre do freio", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Junta do cabeçote", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Amortecedor dianteiro", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Disco de freio", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Radiador do motor", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Fitro de ar do motor", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Válvula termostática", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Bronzina da biela", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Cebolão do radiador", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Engrenagem do virabrequim", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Sensor lambda", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Assento", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Cavalete Carro Automotivo", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Borboleta", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Burrinho", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Freio", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Macaco", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Pistãocaco", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Caixa de câmbio", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Suspensão", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Pinhão impulsor de partida", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Pinça de Freio", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Escapamento", 0, 0, 20.99); -- Criando produto novo.
CALL sp_criar_produto("Lâmpada Super LED Headlight HB3", 0, 0, 55.01); -- Criando produto novo.
CALL sp_criar_produto("Filtro Combustível Injeção Civic", 0, 0, 55.01); -- Criando produto novo.
CALL sp_criar_produto("Som Automotivo com Bluetooth Rádio", 0, 0, 55.01); -- Criando produto novo.
CALL sp_criar_produto("Bomba de Combustível Belina e Corcel", 0, 0, 55.01); -- Criando produto novo.
CALL sp_criar_produto("Terminal Direção", 0, 0, 55.01); -- Criando produto novo.
CALL sp_criar_produto("Mangueira Retorno Radiador", 0, 0, 55.01); -- Criando produto novo.
CALL sp_criar_produto("Eletroventiladores Citroen C3", 0, 0, 55.01); -- Criando produto novo.
CALL sp_criar_produto("Agulha Carburador", 0, 0, 55.01); -- Criando produto novo.
CALL sp_criar_produto("Conjunto Cápsula Vácuo Marcha", 0, 0, 55.01); -- Criando produto novo.
CALL sp_criar_produto("Sensor Detonação Xantia", 0, 0, 55.01); -- Criando produto novo.
CALL sp_criar_produto("Sensor de Velocidade Passat", 0, 0, 55.01); -- Criando produto novo.
CALL sp_criar_produto("Diafragma Monoponto", 0, 0, 55.01); -- Criando produto novo.