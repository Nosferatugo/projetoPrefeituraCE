/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleDeEstoque.ConexaoLogin;



import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ConexaoBD {
    public Statement stm;
    public ResultSet rs;
    private String driver = "org.postgresql.Driver";
    private String caminho = "jdbc:postgresql://localhost:5432/controleEstoque1";
    private String usuario = "postgres";
    private String senha = "1234";
    public Connection con;
    private static boolean closeConection;
    
    public void conexao(){
          
        try {
            System.setProperty("jdbc.Drivers", driver);
            con = DriverManager.getConnection(caminho, usuario, senha);
            //JOptionPane.showMessageDialog(null, "Conexao efetuada com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "" + ex.getMessage());
        }
        
    }
    
        public void executarSql(String sql){
        try {
            stm = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar o SQL" + ex.getMessage());
        }
            
        }
    
    public void desconecta(){
        try {
            con.close();
            //JOptionPane.showMessageDialog(null, "Banco de Dados desconectado com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "" + ex.getMessage());
        }
    }
    
    
}
