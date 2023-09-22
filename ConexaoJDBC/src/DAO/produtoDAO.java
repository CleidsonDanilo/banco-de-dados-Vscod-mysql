package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.conexao;
import entity.produto;

public class produtoDAO {

    public void cadastrar_produto (produto produto) {
        String query = "INSERT INTO produto (descricao, valor) VALUES (?, ?)"; 
        
        conexao nova_conexao = new conexao();
        Connection conectar = nova_conexao.getConexao();
        PreparedStatement preparar = null;
        
        try {
            preparar = conectar.prepareStatement(query);
            preparar.setString(1, produto.getDescricao());
            preparar.setDouble(2, produto.getValor());
            preparar.execute();
        } catch (SQLException erro_cadastrar_usuario) {
            System.out.println("Erro ao cadastrar usuário no banco de dados!\n" + erro_cadastrar_usuario.getMessage());
        }
    }

    public void consultar_produto(produto produto) {
        
        String query = "SELECT descricao, valor FROM produto WHERE codigo = '" + produto.getCodigo() + "'";

        conexao nova_conexao = new conexao();
        Connection conectar = nova_conexao.getConexao();
        
        try {
        
            // Procedimentos para obter os dados de uma tabela
            java.sql.Statement stmt = conectar.createStatement();
            ResultSet resultado = stmt.executeQuery(query);
        
		    while (resultado.next()) {
                produto.setDescricao(resultado.getString("descricao"));
                produto.setValor(resultado.getDouble("preco"));
		    }
            
        } catch (SQLException erro_consulta_produto) {
            System.out.println("Erro ao consultar o produto: " + erro_consulta_produto.getMessage());
        }
    }


    public void atualizar_produto(produto produto) {
    String query = "UPDATE produto SET descricao = ?, valor = ? WHERE codigo = ?";
     
    conexao nova_conexao = new conexao();
    Connection conectar = nova_conexao.getConexao();
    PreparedStatement preparar = null;
    
    try {
        preparar = conectar.prepareStatement(query);
        preparar.setString(1, produto.getDescricao());
        preparar.setDouble(2, produto.getValor());
        preparar.setInt(3, produto.getCodigo());
        preparar.execute();
    } catch (SQLException erro_atualizar_produto) {
        System.out.println("Erro ao cadastrar usuário no banco de dados!\n" + erro_atualizar_produto.getMessage());
    }



} 
}