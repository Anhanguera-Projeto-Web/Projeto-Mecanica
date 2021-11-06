CALL sp_criar_usuario("12345834", "José", "1234", "j@s.com", "Rua R", 1);  -- Criará este usuário
CALL sp_criar_usuario("18092785708", "Wesley Silva", "admin", "admin@admin.com", "Rua B", 2);

SELECT fun_autentica_usuario("j@s.com", "1234") as validator;  -- Retornará 1, logo, existe e bate com o banco.
SELECT fun_autentica_usuario("j@ss.com", "1234") as validator; -- Retornará 0, pois o e-mail não bate.
SELECT fun_autentica_usuario("j@s.com", "1235") as validator;  -- Retornará 0, pois a senha não bate.
update usuario set nome = "aria" where usuarioid = 1;
select * from usuario; -- Teste do Trigger de Update


CALL sp_criar_produto("Aerofolio", 4, 3, 300.0); -- Criando produto novo.
UPDATE produtos SET descricao = "Aerofolio Prateado" where produtosid = 1 ; -- Teste do Trigger de Update

SELECT * FROM produtos;
SELECT * FROM view_retorna_produtos WHERE disponivel = 1 -- Testando View.


CALL sp_getinfo_user(1);