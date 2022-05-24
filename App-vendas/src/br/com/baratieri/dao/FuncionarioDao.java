/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.baratieri.dao;

import br.com.baratieri.jdbc.ConnectionFactory;
import br.com.model.Cliente;
import br.com.model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FuncionarioDao {

    private final Connection con;

    public FuncionarioDao() throws Exception {
        this.con = ConnectionFactory.abrir();
    }

    public void cadastrarFucionario(Funcionario obj) {

        try {

            String sql = "insert into tb_funcionarios (nome,rg,cpf,email,cargo,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado) "
                    + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getCargo());
            stmt.setString(6, obj.getTelefone());
            stmt.setString(7, obj.getCelular());
            stmt.setString(8, obj.getCep());
            stmt.setString(9, obj.getEndereco());
            stmt.setInt(10, obj.getNumero());
            stmt.setString(11, obj.getComplemento());
            stmt.setString(12, obj.getBairro());
            stmt.setString(13, obj.getCidade());
            stmt.setString(14, obj.getUf());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar." + e);

        }
    }
    
     public void alterarFuncionario(Funcionario obj) {

        try {

            String sql = "update tb_funcionarios set nome = ?,cargo = ?,rg = ?,cpf = ?,email = ?,telefone = ?,celular = ?,cep = ?,endereco = ?"
                    + ",numero = ?,complemento = ?,bairro = ?,cidade = ?,estado = ? where id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCargo());
            stmt.setString(3, obj.getRg());
            stmt.setString(4, obj.getCpf());
            stmt.setString(5, obj.getEmail());
            stmt.setString(6, obj.getTelefone());
            stmt.setString(7, obj.getCelular());
            stmt.setString(8, obj.getCep());
            stmt.setString(9, obj.getEndereco());
            stmt.setInt(10, obj.getNumero());
            stmt.setString(11, obj.getComplemento());
            stmt.setString(12, obj.getBairro());
            stmt.setString(13, obj.getCidade());
            stmt.setString(14, obj.getUf());
            stmt.setInt(15, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Funcionario Atualizado com sucesso.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar funcionario." + e);

        }
     }
     
       public void excluirFuncionario(Funcionario obj) {
        try {

            String sql = "delete from tb_funcionarios where id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso com sucesso.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir." + e);

        }

    }


    public List<Funcionario> listarFuncionarios() {

        try {
            List<Funcionario> lista = new ArrayList();

            String sql = "select * from tb_funcionarios";

            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Funcionario obj = new Funcionario();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCargo(rs.getString("cargo"));
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

    public List<Funcionario> buscaFuncionarioPorNome(String nome) {
        try {

            //1 passo criar a lista
            List<Funcionario> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_funcionarios where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario obj = new Funcionario();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCargo(rs.getString("cargo"));
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

    public Funcionario consultaPorNome(String nome) {
        try {
            //1 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_funcionarios where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Funcionario obj = new Funcionario();

            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCargo(rs.getString("cargo"));
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
            JOptionPane.showMessageDialog(null, "Funcionario não encontrado!");
            return null;
        }

    }
    
    
    
}

   
