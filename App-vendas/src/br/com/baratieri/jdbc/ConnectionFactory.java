/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.baratieri.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static final String USUARIO = "usuariocurso";
    private static final String SENHA = "123";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/bdvendas";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    // Conectar ao banco
    public static Connection abrir() throws Exception {
        // Registrar o driver
        Class.forName(DRIVER);
        // Capturar a conexão
        Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
        // Retorna a conexao aberta
        return conn;
    }
}
