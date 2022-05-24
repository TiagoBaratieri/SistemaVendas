/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.baratieri.dao;

import br.com.baratieri.jdbc.ConnectionFactory;
import br.com.model.Usuario;
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
public class UsuarioDao {

    private final Connection con;

    public UsuarioDao() throws Exception {
        this.con = ConnectionFactory.abrir();
    }

    public void cadastrarUsuario(Usuario obj) {

        try {

            String sql = "insert into tb_usuarios (login,nivel_acesso,senha) "
                    + " values (?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, obj.getLogin());
            stmt.setString(2, obj.getNivelAcesso());
            stmt.setString(3, obj.getSenha());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Login e senha cadastrados com sucesso.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar." + e);

        }
    }

    public List<Usuario> ListarUsuario() {

        try {
            List<Usuario> lista = new ArrayList<>();

            String sql = "select * from tb_usuarios";

            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Usuario obj = new Usuario();

                obj.setLogin(rs.getString("login"));
                obj.setNivelAcesso(rs.getString("nivel_acesso"));
                obj.setSenha(rs.getString("senha"));
                lista.add(obj);
            }

            return lista;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }
        return null;

    }

    public List<Usuario> buscaUsuario(String user) {
        try {

            //1 passo criar a lista
            List<Usuario> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_usuarios where login like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario obj = new Usuario();

                obj.setLogin(rs.getString("login"));
                obj.setNivelAcesso(rs.getString("nivel_acesso"));
                obj.setSenha(rs.getString("senha"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }
}