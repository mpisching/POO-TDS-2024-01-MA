package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.model.domain.Cliente;
import br.edu.ifsc.fln.model.domain.EStatusVenda;
import br.edu.ifsc.fln.model.domain.ItemDeVenda;
import br.edu.ifsc.fln.model.domain.Produto;
import br.edu.ifsc.fln.model.domain.Venda;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class VendaDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Venda venda) {
        String sql = "INSERT INTO venda(data, total, pago, taxa_desconto, empresa, situacao, id_cliente) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            stmt.setDate(1, Date.valueOf(venda.getData()));
            stmt.setBigDecimal(2, venda.getTotal());
            stmt.setBoolean(3, venda.isPago());
            stmt.setDouble(4, venda.getTaxaDesconto());
            stmt.setString(5, Venda.getEmpresa());
            if  (venda.getStatusVenda() != null) {
                stmt.setString(6, venda.getStatusVenda().name());
            } else {
                //TODO apresentar situação clara de inconsistência de dados
                //tratamento de exceções e a necessidade de uso de commit e rollback
                //stmt.setString(6, "teste");
                stmt.setString(6, EStatusVenda.ABERTA.name());
            }
            stmt.setInt(7, venda.getCliente().getId());
            stmt.execute();
            ItemDeVendaDAO itemDeVendaDAO = new ItemDeVendaDAO();
            itemDeVendaDAO.setConnection(connection);
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.setConnection(connection);
            EstoqueDAO estoqueDAO = new EstoqueDAO();
            estoqueDAO.setConnection(connection);
            for (ItemDeVenda itemDeVenda: venda.getItensDeVenda()) {
                Produto produto = itemDeVenda.getProduto();
                itemDeVenda.setVenda(this.buscarUltimaVenda());
                itemDeVendaDAO.inserir(itemDeVenda);
                produto.getEstoque().retirar(itemDeVenda.getQuantidade());
                estoqueDAO.atualizar(produto.getEstoque());
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            
        }
    }

    public boolean alterar(Venda venda) {
        String sql = "UPDATE venda SET data=?, total=?, pago=?, taxa_desconto=?, empresa=?, situacao=?, id_cliente=? WHERE id=?";
        try {
            //antes de atualizar a nova venda, a anterior terá seus itens de venda removidos
            // e o estoque dos produtos da venda sofrerão um estorno
            connection.setAutoCommit(false);
            ItemDeVendaDAO itemDeVendaDAO = new ItemDeVendaDAO();
            itemDeVendaDAO.setConnection(connection);
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.setConnection(connection);
            EstoqueDAO estoqueDAO = new EstoqueDAO();
            estoqueDAO.setConnection(connection);
            
            //Venda vendaAnterior = buscar(venda.getCdVenda());
            Venda vendaAnterior = buscar(venda);
            List<ItemDeVenda> itensDeVenda = itemDeVendaDAO.listarPorVenda(vendaAnterior);
            for (ItemDeVenda iv : itensDeVenda) {
                //Produto p = iv.getProduto(); //isto não da certo ...
                Produto p = estoqueDAO.getEstoque(iv.getProduto());
                p.getEstoque().repor(iv.getQuantidade());
                estoqueDAO.atualizar(p.getEstoque());
                itemDeVendaDAO.remover(iv);
            }
            //atualiza os dados da venda
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(venda.getData()));
            stmt.setBigDecimal(2, venda.getTotal());
            stmt.setBoolean(3, venda.isPago());
            stmt.setDouble(4, venda.getTaxaDesconto());
            stmt.setString(5, Venda.getEmpresa());
            if  (venda.getStatusVenda() != null) {
                stmt.setString(6, venda.getStatusVenda().name());
            } else {
                stmt.setString(6, EStatusVenda.ABERTA.name());
            }
            stmt.setInt(7, venda.getCliente().getId());
            stmt.setInt(8, venda.getId());
            stmt.execute();
            for (ItemDeVenda iv: venda.getItensDeVenda()) {
                //Produto p = iv.getProduto(); //isto não da certo ...
                Produto p = estoqueDAO.getEstoque(iv.getProduto());
                p.getEstoque().retirar(iv.getQuantidade());
                estoqueDAO.atualizar(p.getEstoque());
                itemDeVendaDAO.inserir(iv);
            }
            connection.commit();
            return true;
        } catch (SQLException ex) {
                try {
                    connection.rollback();
                } catch (SQLException exc1) {
                    Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, exc1);
                }
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Venda venda) {
        String sql = "DELETE FROM venda WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            try {
                connection.setAutoCommit(false);
                ItemDeVendaDAO itemDeVendaDAO = new ItemDeVendaDAO();
                itemDeVendaDAO.setConnection(connection);
                ProdutoDAO produtoDAO = new ProdutoDAO();
                produtoDAO.setConnection(connection);
                EstoqueDAO estoqueDAO = new EstoqueDAO();
                estoqueDAO.setConnection(connection);
                for (ItemDeVenda itemDeVenda : venda.getItensDeVenda()) {
                    Produto produto = itemDeVenda.getProduto();
                    produto.getEstoque().repor(itemDeVenda.getQuantidade());
                    estoqueDAO.atualizar(produto.getEstoque());
                    itemDeVendaDAO.remover(itemDeVenda);
                }
                stmt.setInt(1, venda.getId());
                stmt.execute();
                connection.commit();
            } catch (SQLException exc) {
                try {
                    connection.rollback();
                } catch (SQLException exc1) {
                    Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, exc1);
                }
                Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, exc);
            }            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Venda> listar() {
        String sql = "SELECT * FROM venda";
        List<Venda> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Venda venda = new Venda();
                Cliente cliente = new Cliente();
                List<ItemDeVenda> itensDeVenda = new ArrayList();

                venda.setId(resultado.getInt("id"));
                venda.setData(resultado.getDate("data").toLocalDate());
                //venda.setTotal(resultado.getBigDecimal("total"));
                venda.setPago(resultado.getBoolean("pago"));
                venda.setTaxaDesconto(resultado.getDouble("taxa_desconto"));
                venda.setStatusVenda(Enum.valueOf(EStatusVenda.class, resultado.getString("situacao")));
                Venda.setEmpresa(resultado.getString("empresa"));
                cliente.setId(resultado.getInt("id_cliente"));

                //Obtendo os dados completos do Cliente associado à Venda
                ClienteDAO clienteDAO = new ClienteDAO();
                clienteDAO.setConnection(connection);
                cliente = clienteDAO.buscar(cliente);

                //Obtendo os dados completos dos Itens de Venda associados à Venda
                ItemDeVendaDAO itemDeVendaDAO = new ItemDeVendaDAO();
                itemDeVendaDAO.setConnection(connection);
                itensDeVenda = itemDeVendaDAO.listarPorVenda(venda);

                venda.setCliente(cliente);
                venda.setItensDeVenda(itensDeVenda);
                retorno.add(venda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Venda buscar(Venda venda) {
        String sql = "SELECT * FROM venda WHERE id=?";
        Venda vendaRetorno = new Venda();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, venda.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Cliente cliente = new Cliente();
                vendaRetorno.setId(resultado.getInt("id"));
                vendaRetorno.setData(resultado.getDate("data").toLocalDate());
//                vendaRetorno.setTotal(resultado.getBigDecimal("total"));
                vendaRetorno.setStatusVenda(Enum.valueOf(EStatusVenda.class, resultado.getString("situacao")));
                vendaRetorno.setPago(resultado.getBoolean("pago"));
                vendaRetorno.setTaxaDesconto(resultado.getDouble("taxa_desconto"));
                //vendaRetorno.setEmpresa(resultado.getString("empresa"));
                cliente.setId(resultado.getInt("id_cliente"));
                vendaRetorno.setCliente(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vendaRetorno;
    }
    
    public Venda buscar(int id) {
        /*
            Método necessário para evitar que a instância de retorno seja 
            igual a instância a ser atualizada.
        */
        String sql = "SELECT * FROM venda WHERE id=?";
        Venda vendaRetorno = new Venda();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Cliente cliente = new Cliente();
                vendaRetorno.setId(resultado.getInt("id"));
                vendaRetorno.setData(resultado.getDate("data").toLocalDate());
//                vendaRetorno.setTotal(resultado.getBigDecimal("total"));
                vendaRetorno.setStatusVenda(Enum.valueOf(EStatusVenda.class, resultado.getString("situacao")));
                vendaRetorno.setPago(resultado.getBoolean("pago"));
                vendaRetorno.setTaxaDesconto(resultado.getDouble("taxa_desconto"));
                //vendaRetorno.setEmpresa(resultado.getString("empresa"));
                cliente.setId(resultado.getInt("id_cliente"));
                vendaRetorno.setCliente(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vendaRetorno;
    }    
    public Venda buscarUltimaVenda() {
        String sql = "SELECT max(id) as max FROM venda";
        
        Venda retorno = new Venda();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                retorno.setId(resultado.getInt("max"));
                return retorno;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
//
//    public Map<Integer, ArrayList> listarQuantidadeVendasPorMes() {
//        String sql = "select count(cdVenda) as count, extract(year from data) as ano, extract(month from data) as mes from vendas group by ano, mes order by ano, mes";
//        Map<Integer, ArrayList> retorno = new HashMap();
//        
//        try {
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            ResultSet resultado = stmt.executeQuery();
//
//            while (resultado.next()) {
//                ArrayList linha = new ArrayList();
//                if (!retorno.containsKey(resultado.getInt("ano")))
//                {
//                    linha.add(resultado.getInt("mes"));
//                    linha.add(resultado.getInt("count"));
//                    retorno.put(resultado.getInt("ano"), linha);
//                }else{
//                    ArrayList linhaNova = retorno.get(resultado.getInt("ano"));
//                    linhaNova.add(resultado.getInt("mes"));
//                    linhaNova.add(resultado.getInt("count"));
//                }
//            }
//            if (retorno.size() > 0) {
//                retorno = ordenar(retorno);
//            }
//            return retorno;
//        } catch (SQLException ex) {
//            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            
//        }
//        return retorno;
//    }
//    
//    private Map<Integer, ArrayList> ordenar(Map<Integer, ArrayList> vendas) {
//        LinkedHashMap<Integer, ArrayList> orderedMap = vendas.entrySet() 
//            .stream() 
//            .sorted(Map.Entry.comparingByKey()) 
//                .collect(Collectors.toMap(Map.Entry::getKey, 
//                    Map.Entry::getValue, //
//                    (key, content) -> content, //
//                    LinkedHashMap::new));
//        return orderedMap;
//    }

}
