// Classe cursoDAO
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import db.conexao;
import entity.curso;

public class cursoDAO {
    public void cadastrar_curso(curso curso) {
        String query = "INSERT INTO curso (id, nome, descricao) VALUES (?, ?, ?)";
        conexao nova_conexao = new conexao();
        Connection conectar = nova_conexao.getConexao();
        PreparedStatement preparar = null;

        try {
            preparar = conectar.prepareStatement(query);
            preparar.setInt(1, curso.getId());
            preparar.setString(2, curso.getNome());
            preparar.setString(3, curso.getDescricao());
            preparar.execute();
        } catch (SQLException erro_cadastrar_curso) {
            System.out.println("Erro ao cadastrar curso no banco de dados!\n" + erro_cadastrar_curso.getMessage());
        }
    }


    public void atualizar_curso(curso curso) {
        String query = "UPDATE curso SET nome = ?, descricao = ? WHERE id = ?";

        conexao nova_conexao = new conexao();
        Connection conectar = nova_conexao.getConexao();
        PreparedStatement preparar = null;

        try {
            preparar = conectar.prepareStatement(query);
            preparar.setString(1, curso.getNome());
            preparar.setString(2, curso.getDescricao());
            preparar.setInt(3, curso.getId());
            preparar.executeUpdate();
        } catch (SQLException erro_atualizar_curso) {
            System.out.println("Erro ao atualizar curso no banco de dados!\n" + erro_atualizar_curso.getMessage());
        }
    }

    public void remover_curso(int id) {
        String query = "DELETE FROM curso WHERE id = ?";

        conexao nova_conexao = new conexao();
        Connection conectar = nova_conexao.getConexao();
        PreparedStatement preparar = null;

        try {
            preparar = conectar.prepareStatement(query);
            preparar.setInt(1, id);
            preparar.executeUpdate();
        } catch (SQLException erro_remover_curso) {
            System.out.println("Erro ao remover curso no banco de dados!\n" + erro_remover_curso.getMessage());
        }
    }

    public List<curso> listar_cursos() {
        List<curso> cursos = new ArrayList<>();
        String query = "SELECT id, nome, descricao FROM curso";

        conexao nova_conexao = new conexao();
        Connection conectar = nova_conexao.getConexao();

        try {
            PreparedStatement preparar = conectar.prepareStatement(query);
            ResultSet resultado = preparar.executeQuery();

            while (resultado.next()) {
                curso c = new curso();
                c.setId(resultado.getInt("id"));
                c.setNome(resultado.getString("nome"));
                c.setDescricao(resultado.getString("descricao"));
                cursos.add(c);
            }

        } catch (SQLException erro_listar_cursos) {
            System.out.println("Erro ao listar cursos: " + erro_listar_cursos.getMessage());
        }

        return cursos;
    }
}