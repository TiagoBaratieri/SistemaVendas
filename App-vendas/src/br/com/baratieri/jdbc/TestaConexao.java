/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.baratieri.jdbc;

import javax.swing.JOptionPane;

/**
 *
 * @author tiago
 */
public class TestaConexao {

    public static void main(String[] args) {

        try {
            ConnectionFactory.abrir();
            JOptionPane.showMessageDialog(null, "Conectado com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops aconteceu um erro: " + e);
        }

    }
}
