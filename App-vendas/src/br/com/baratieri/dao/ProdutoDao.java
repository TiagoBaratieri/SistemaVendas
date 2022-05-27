/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.baratieri.dao;

import br.com.baratieri.jdbc.ConnectionFactory;
import br.com.model.Fornecedor;
import br.com.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author tiago
 */
public class ProdutoDao {

    private final Connection con;

    public ProdutoDao() throws Exception {
        this.con = ConnectionFactory.abrir();
    }

    public void cadastrarProduto(Produto obj) throws SQLException {

        try {
            String sql = "insert into tb_produtos(nome,descricao,preco,qtd_estoque,for_id)"
                    + " values (?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getDescricao());
            stmt.setDouble(3, obj.getPreco());
            stmt.setInt(4, obj.getQuantidadeEstoque());
            stmt.setInt(5, obj.getFornecedor().getId());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar." + e);

        }
    }

    public List<Produto> listaProdutos() {

        try {

            List<Produto> lista = new ArrayList<>();

            String sql = "select p.id,p.nome, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p "
                    + "inner join tb_fornecedores as f on(p.for_id = f.id)";

            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Produto obj = new Produto();
                Fornecedor f = new Fornecedor();

                obj.setId(rs.getInt("p.id"));
                obj.setNome(rs.getString("p.nome"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQuantidadeEstoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));

                obj.setFornecedor(f);

                lista.add(obj);

            }

            return lista;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro:" + e);

        }
        return null;
    }

    public void alterar(Produto obj) {
        try {

            String sql = "update tb_produtos  set nome=?, descricao=?, preco=?, qtd_estoque=?, for_id=?  where id=?";
            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getDescricao());
            stmt.setDouble(3, obj.getPreco());
            stmt.setInt(4, obj.getQuantidadeEstoque());

            stmt.setInt(5, obj.getFornecedor().getId());

            stmt.setInt(6, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto alterardo com Sucesso!");

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "Erro : " + erro);

        }

    }

    public void excluir(Produto obj) {
        try {

            String sql = "delete from tb_produtos  where id=?";
            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto excluido com Sucesso!");

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "Erro : " + erro);

        }

    }

    public List<Produto> listarProdutosPorNome(String nome) {
        try {

            //1 passo criar a lista
            List<Produto> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select p.id,p.nome, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p "
                    + "inner join tb_fornecedores as f on (p.for_id = f.id) where p.descricao like ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto obj = new Produto();
                Fornecedor f = new Fornecedor();
                obj.setId(rs.getInt("p.id"));
                obj.setNome("p.nome");
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQuantidadeEstoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }

    public Produto consultaPorNome(String nome) {
        try {
            //1 passo - criar o sql , organizar e executar.

            String sql = "select p.id,p.nome, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p "
                    + "inner join tb_fornecedores as f on (p.for_id = f.id) where p.nome like ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Produto obj = new Produto();
            Fornecedor f = new Fornecedor();

            if (rs.next()) {

                obj.setId(rs.getInt("p.id"));
                obj.setNome(rs.getString("p.nome"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQuantidadeEstoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);
            }

            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            return null;
        }
    }

}
