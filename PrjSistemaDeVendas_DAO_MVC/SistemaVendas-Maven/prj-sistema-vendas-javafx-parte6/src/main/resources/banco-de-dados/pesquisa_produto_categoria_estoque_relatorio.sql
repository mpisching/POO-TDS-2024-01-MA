select produto.*, categoria.descricao, estoque.quantidade 
from produto
join categoria on produto.id_categoria=categoria.id
left join estoque on estoque.id_produto=produto.id
order by produto.id;