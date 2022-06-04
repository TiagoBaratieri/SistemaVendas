/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.baratieri.dao;

import br.com.baratieri.jdbc.ConnectionFactory;
import br.com.model.Cliente;
import br.com.model.Venda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author tiago
 */
public class VendaDao {

    private final java.sql.Connection con;

    public VendaDao() throws Exception {
        this.con = ConnectionFactory.abrir();
    }

    public void cadastrarVenda(Venda obj) {

        try {

            String sql = "insert into tb_vendas (cliente_id, data_venda,total_venda,observacoes) values (?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getCliente().getId());
            stmt.setString(2, obj.getDataVenda());
            stmt.setDouble(3, obj.getTotalVenda());
            stmt.setString(4, obj.getObservacao());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Venda Cadastrada com sucesso.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar." + e);

        }

    }

    public int retornaUltimaVenda() {

        try {

            int idVenda = 0;

            String sql = "select max(id) id from tb_vendas";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            if (rs.next()) {
                Venda p = new Venda();

                p.setId(rs.getInt("id"));

                idVenda = p.getId();

            }
            return idVenda;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Venda> listarVendaPorPeriodo( LocalDate dataInicio,  LocalDate dataFinal) {
        List<Venda> lista = new ArrayList();

          String sql = "select v.id ,  date_format(v.data_venda,'%d/%m/%Y') as data_formatada , c.nome, v.total_venda, v.observacoes  from tb_vendas as v  "
                    + " inner join tb_clientes as c on(v.cliente_id = c.id) where v.data_venda BETWEEN ? AND ?";
        try {
            PreparedStatement stmt;

            stmt = con.prepareStatement(sql);
            stmt.setString(1, dataInicio.toString());
            stmt.setString(2, dataFinal.toString());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Venda obj = new Venda();
                Cliente objCli = new Cliente();

                obj.setId(rs.getInt("v.id"));
                obj.setDataVenda(rs.getString("data_formatada"));
                objCli.setNome(rs.getString("c.nome"));
                obj.setTotalVenda(rs.getDouble("v.total_venda"));
                obj.setObservacao(rs.getString("v.observacoes"));
                obj.setCliente(objCli);
                
                lista.add(obj);

            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }
    
     //Metodo que calcula total da venda por data
    public double retornaTotalVendaPorData(LocalDate dataVenda) {
        try {

            double totalVenda = 0;

            String sql = "select sum(total_venda) as total from tb_vendas where data_venda = ?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dataVenda.toString());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                totalVenda = rs.getDouble("total");
            }

            return totalVenda;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
