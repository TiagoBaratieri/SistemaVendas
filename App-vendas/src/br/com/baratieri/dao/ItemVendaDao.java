/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.baratieri.dao;

import br.com.baratieri.jdbc.ConnectionFactory;
import br.com.model.ItemVenda;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author tiago
 */
public class ItemVendaDao {

    private final Connection con;

    public ItemVendaDao() throws Exception {
        this.con = ConnectionFactory.abrir();
    }

    public void cadastrarItem(ItemVenda obj) {

        try {

            String sql = "insert into tb_itensvendas (venda_id, produto_id,qtd,subtotal) values (?,?,?,?)";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getVenda().getId());
            stmt.setInt(2, obj.getProduto().getId());;
            stmt.setInt(3, obj.getQuantidade());
            stmt.setDouble(4, obj.getSubTotal());

            stmt.execute();
            stmt.close();

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "Erro : " + erro);

        }

    }

}
