/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.baratieri.dao;

import br.com.model.Cliente;
import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;

/**
 *
 * @author tiago
 */
public class ClienteDao {
    
    private Connection con;
    
    public ClienteDao(){
        this.con = new Connection
       

    public void cadastrarCliente(Cliente obj) {

        try {

            String sql = "insert into tb_cliente(nome,rg,cpf,email,telefone,celular,cep,endereco,numero,complemento,cidade,estado)\n"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
        } catch (Exception e) {
               
        }
    }

}
