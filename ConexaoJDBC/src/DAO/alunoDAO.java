
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import db.conexao;
import entity.aluno;

public class alunoDAO {

    public void cadastrar_aluno(aluno aluno) {
        String query = "INSERT INTO aluno (nome, cursoid) VALUES (?, ?)";

        conexao nova_conexao = new conexao();
        Connection conectar = nova_conexao.getConexao();
        PreparedStatement preparar = null;

        try {
            preparar = conectar.prepareStatement(query);
            preparar.setString(1, aluno.getNome()); 
            preparar.setInt(2, aluno.getCursoid()); 
            preparar.execute();
        } catch (SQLException erro_cadastrar_aluno) {
            System.out.println("Erro ao cadastrar aluno no banco de dados!\n" + erro_cadastrar_aluno.getMessage());
        }
    }

    public void atualizar_aluno(aluno aluno) {
        String query = "UPDATE aluno SET nome = ?, cursoid = ? WHERE id = ?";

        conexao nova_conexao = new conexao();
        Connection conectar = nova_conexao.getConexao();
        PreparedStatement preparar = null;

        try {
            preparar = conectar.prepareStatement(query);
            preparar.setString(1, aluno.getNome()); 
            preparar.setInt(2, aluno.getCursoid()); 
            preparar.setInt(3, aluno.getId()); 
            preparar.executeUpdate();
        } catch (SQLException erro_atualizar_aluno) {
            System.out.println("Erro ao atualizar aluno no banco de dados!\n" + erro_atualizar_aluno.getMessage());
        }
    }

    public void remover_aluno(int alunoId) {
        String query = "DELETE FROM aluno WHERE id = ?";

        conexao nova_conexao = new conexao();
        Connection conectar = nova_conexao.getConexao();
        PreparedStatement preparar = null;

        try {
            preparar = conectar.prepareStatement(query);
            preparar.setInt(1, alunoId); 
            preparar.executeUpdate();
        } catch (SQLException erro_remover_aluno) {
            System.out.println("Erro ao remover aluno no banco de dados!\n" + erro_remover_aluno.getMessage());
        }
    }

    public List<aluno> listar_alunos() {
        String query = "SELECT id, nome, cursoid FROM aluno";
        List<aluno> alunos = new ArrayList<>();

        conexao nova_conexao = new conexao();
        Connection conectar = nova_conexao.getConexao();
        PreparedStatement preparar = null;

        try {
            preparar = conectar.prepareStatement(query);
            ResultSet resultado = preparar.executeQuery();

            while (resultado.next()) {
                aluno aluno = new aluno();
                aluno.setId(resultado.getInt("id"));
                aluno.setNome(resultado.getString("nome"));
                aluno.setCursoid(resultado.getInt("cursoid"));
                alunos.add(aluno);
            }
        } catch (SQLException erro_listar_alunos) {
            System.out.println("Erro ao listar alunos: " + erro_listar_alunos.getMessage());
        }

        return alunos;
    }
}
