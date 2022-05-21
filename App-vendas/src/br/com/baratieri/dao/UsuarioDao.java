/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.baratieri.dao;

import br.com.baratieri.jdbc.ConnectionFactory;
import br.com.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

            String sql = "insert into tb_usuarios (senha,nivel_acesso) "
                    + " values (?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1,obj.getSenha());
            stmt.setString(2, obj.getNivelAcesso());
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar." + e);

        }

    }

}
