select * from produto p 
	inner join categoria c on c.id = p.id_categoria 
    inner join fornecedor f on f.id = p.id_fornecedor
    left join nacional n on n.id_fornecedor = f.id
    left join internacional i on i.id_fornecedor = f.id