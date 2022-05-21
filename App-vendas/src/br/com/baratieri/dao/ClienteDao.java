/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.baratieri.dao;

import br.com.baratieri.jdbc.ConnectionFactory;
import br.com.baratieri.webservices.WebServiceCep;
import br.com.model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author tiago
 */
public class ClienteDao {

    private final Connection con;

    public ClienteDao() throws Exception {
        this.con = ConnectionFactory.abrir();
    }

    public void cadastrarCliente(Cliente obj) {

        try {

            String sql = "insert into tb_clientes(nome,rg,cpf,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)\n"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCep());
            stmt.setString(8, obj.getEndereco());
            stmt.setInt(9, obj.getNumero());
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getUf());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar." + e);

        }

    }

    public void excluirCliente(Cliente obj) {
        try {

            String sql = "delete from tb_clientes where id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso com sucesso.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir." + e);

        }

    }

    public void alterarCliente(Cliente obj) {

        try {

            String sql = "update tb_clientes set nome = ?,rg = ?,cpf = ?,email = ?,telefone = ?,celular = ?,cep = ?,endereco = ?"
                    + ",numero = ?,complemento = ?,bairro = ?,cidade = ?,estado = ? where id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCep());
            stmt.setString(8, obj.getEndereco());
            stmt.setInt(9, obj.getNumero());
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getUf());
            stmt.setInt(14, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar." + e);

        }

    }

    public List<Cliente> listarClentes() {

        try {
            List<Cliente> lista = new ArrayList();

            String sql = "select *from tb_clientes";

            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Cliente obj = new Cliente();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
                lista.add(obj);
            }
            return lista;
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }
        return null;
    }
    //Metodo buscarclientePorNome - retorna uma lista

    public List<Cliente> buscaClientePorNome(String nome) {
        try {

            //1 passo criar a lista
            List<Cliente> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_clientes where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente obj = new Cliente();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }
    }

    //metodo consultaCliente por Nome
    public Cliente consultaPorNome(String nome) {
        try {
            //1 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_clientes where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Cliente obj = new Cliente();

            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
            }

            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            return null;
        }
    }

    public Cliente buscaCep(String cep) {

        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);

        Cliente obj = new Cliente();

        if (webServiceCep.wasSuccessful()) {
            obj.setEndereco(webServiceCep.getLogradouroFull());
            obj.setCidade(webServiceCep.getCidade());
            obj.setBairro(webServiceCep.getBairro());
            obj.setUf(webServiceCep.getUf());
            return obj;
        } else {
            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
            return null;
        }

    }

}
