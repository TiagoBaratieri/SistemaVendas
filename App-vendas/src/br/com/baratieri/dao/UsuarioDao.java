/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.baratieri.dao;

import br.com.baratieri.jdbc.ConnectionFactory;
import br.com.baratieri.util.Hash;
import br.com.baratieri.view.FramLogin;
import br.com.baratieri.view.FramMenu;
import br.com.model.Usuario;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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

    public void cadastrarUsuario(Usuario obj) throws NoSuchAlgorithmException {

        try {

            String sql = "insert into tb_usuarios (login,nivel_acesso,senha) "
                    + " values (?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            obj.setSenha(Hash.toMD5(obj.getSenha() + obj.getSalt()));
            obj.setSalt(Calendar.getInstance().getTime().toString() + "#&*@");
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

                obj.setId(rs.getInt("id"));
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

    public void alterarUsuario(Usuario obj) throws NoSuchAlgorithmException {

        try {

            String sql = "update tb_usuarios set login = ?,nivel_acesso= ?,senha = ? where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            obj.setSenha(Hash.toMD5(obj.getSenha() + obj.getSalt()));
            obj.setSalt(Calendar.getInstance().getTime().toString() + "#&*@");

            stmt.setString(1, obj.getLogin());
            stmt.setString(2, obj.getNivelAcesso());

            stmt.setString(3, obj.getSenha());

            stmt.setInt(4, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Usu??rio atualizado com sucesso.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar usu??rio." + e);

        }

    }

    public void excluirUsuario(Usuario obj) {
        try {

            String sql = "delete from tb_usuarios where id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso com sucesso.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir." + e);

        }

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

    public void efetuarLogin(String login, String senha) {
        try {

            String sql = "select * from tb_usuarios where login = ? and senha = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            FramMenu tela = new FramMenu();

            if (rs.next()) {
                //Usuario logou

                //Caso o usuario seja do tipo admin
                if (rs.getString("nivel_acesso").equals("Admim")) {

                    JOptionPane.showMessageDialog(null, "Seja bem vindo ao Sistema");
                    tela.usuarioLogado = rs.getString("login");
           

                    tela.setVisible(true);
                } //Caso o usuario seja do tipo limitado 
                else if (rs.getString("nivel_acesso").equals("Usu??rio")) {

                    JOptionPane.showMessageDialog(null, "Seja bem vindo ao Sistema");
                    tela.usuarioLogado  = rs.getString("login");

                    //Desabilitar os menus
                    tela.menuPosicao.setEnabled(false);
                    tela.menuControleVenda.setVisible(false);

                    tela.setVisible(true);

                }

            } else {
                //Dados incorretos
                JOptionPane.showMessageDialog(null, "Dados incorretos!");
                new FramLogin().setVisible(true);

            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro : " + erro);
        }

    }
}
