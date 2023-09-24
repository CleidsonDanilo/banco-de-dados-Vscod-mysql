package main;

import java.util.List;
import java.util.Scanner;
import DAO.alunoDAO;
import DAO.cursoDAO;
import entity.aluno;
import entity.curso;

public class Main {

    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);
        curso curso = new curso();
        aluno aluno = new aluno();
        cursoDAO cursoDAO = new cursoDAO();
        alunoDAO alunoDAO = new alunoDAO();
        int menu = 99;

        while (menu != 0) { 

            System.out.print("\n[1] Cursos\n[2] Alunos\n[0] Sair\n\nInforme a opção desejada: ");
            menu = ler.nextInt();

            switch (menu) {
                
                case 1:
                    int opcao_curso = 3;

                    System.out.print("[1] Cadastrar curso\n[2] Atualizar curso\n[3] Remover curso\n[4] Listar cursos\nInforme a opção desejada: ");
                    opcao_curso = ler.nextInt();

                    if (opcao_curso == 1) {

                        System.out.print("Informe o id do curso: ");
                        curso.setId(ler.nextInt());

                        System.out.print("Informe o nome do curso: ");
                        curso.setNome(ler.next());

                        System.out.print("Informe a descrição do curso: ");
                        curso.setDescricao(ler.next());

                        cursoDAO.cadastrar_curso(curso);

                    } else if (opcao_curso == 2) {

                        System.out.print("Informe o id do curso a ser atualizado: ");
                        curso.setId(ler.nextInt());
                        ler.nextLine(); 
                        System.out.print("Informe o novo nome do curso: ");
                        curso.setNome(ler.next());
                        System.out.print("Informe a nova descrição do curso: ");
                        curso.setDescricao(ler.next());

                        cursoDAO.atualizar_curso(curso);

                    } else if (opcao_curso == 3) {

                        System.out.print("Informe o id do curso a ser removido: ");
                        int cursoIdParaRemover = ler.nextInt();
                        cursoDAO.remover_curso(cursoIdParaRemover);

                    } else if (opcao_curso == 4) {
                        List<curso> cursos = cursoDAO.listar_cursos();
                        System.out.println("Lista de Cursos:");
                        for (curso c : cursos) {
                            System.out.println("ID: " + c.getId() + ", Nome: " + c.getNome() + ", Descrição: " + c.getDescricao());
                        }
                    }
                    break;

                case 2:

                    int opcao_aluno = 3;

                    System.out.print("\n[1] Cadastrar aluno\n[2] Atualizar aluno\n[3] Remover aluno\n[4] Listar alunos\nInforme a opção desejada: ");
                    opcao_aluno = ler.nextInt();

                    if (opcao_aluno == 1) {
                        ler.nextLine(); 
                        System.out.print("Informe o nome do aluno: ");
                        aluno.setNome(ler.nextLine());

                        System.out.print("Informe o id do curso do aluno: ");
                        aluno.setCursoid(ler.nextInt());

                        alunoDAO.cadastrar_aluno(aluno);

                    } else if (opcao_aluno == 2) {

                        System.out.print("Informe o ID do aluno a ser atualizado: ");
                        int alunoId = ler.nextInt();
                        ler.nextLine(); 
                        System.out.print("Informe o novo nome do aluno: ");
                        String novoNome = ler.nextLine();
                        System.out.print("Informe o novo ID do curso do aluno: ");
                        int novoCursoid = ler.nextInt();

                        aluno alunoParaAtualizar = new aluno();
                        alunoParaAtualizar.setId(alunoId);
                        alunoParaAtualizar.setNome(novoNome);
                        alunoParaAtualizar.setCursoid(novoCursoid);

                        alunoDAO.atualizar_aluno(alunoParaAtualizar);

                    } else if (opcao_aluno == 3) {
                        System.out.print("Informe o ID do aluno a ser removido: ");
                        int alunoIdParaRemover = ler.nextInt();

                        alunoDAO.remover_aluno(alunoIdParaRemover);

                    } else if (opcao_aluno == 4) {

                        List<aluno> alunos = alunoDAO.listar_alunos();
                        System.out.println("\nLista de Alunos:");
                        for (aluno alunoListado : alunos) {                           
                            System.out.println("ID: " + alunoListado.getId() + ", Nome: " + alunoListado.getNome() + ", Cursoid: " + alunoListado.getCursoid());
                        }
                    }
                    break;
                    
                case 0:

                    System.out.println("Saindo do programa.");
                    break;

                default:

                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
        
        ler.close();
    }
}
