Select p.id as produto_id, p.nome as produto_nome, p.descricao as produto_descricao, p.preco as produto_preco, c.id as categoria_id, c.descricao as categoria_descricao from produto p
inner join categoria c
on c.id = p.id_categoria
where p.id = 2;