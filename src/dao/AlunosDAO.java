package dao;

import conn.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Alunos;

public class AlunosDAO implements interfaces.InterfaceModelo {

    @Override
    public String salvar(Object o) {
        Alunos aluno = (Alunos) o;
        String sql = "INSERT INTO alunos (nome,matricula,curso) VALUES (?, ?, ?)";
        try (Connection conexao = ConexaoFactory.criarConexao();
            PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, aluno.getNome());
            ps.setInt(2, aluno.getMatricula());
            ps.setString(3, aluno.getCurso());
            System.out.println(ps.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ("OBJETO GRAVADO");
    }

    @Override
    public String excluir(int id) {
        System.out.println("id " + id);
        String sql = "DELETE FROM `alunos` WHERE matricula= ?";
        try (Connection conexao = ConexaoFactory.criarConexao();
            PreparedStatement ps = conexao.prepareStatement(sql)) {
            System.out.println(ps.toString());
            ps.setInt(1, id);
            System.out.println("Comando de EXCLUIR matricula " + ps.toString());
            ps.executeUpdate();
            System.out.println("Registro EXCLUIDO com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir dados. Metodo Excluir");
        }
            return ("Sucesso ao EXCLUIR em ALUNOS!");
    }

    public ArrayList<Object> consultarTodos() {
        String sql = "SELECT * from alunos";
        ArrayList AlunosList = new ArrayList();
        try (Connection conexao = ConexaoFactory.criarConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String nome = rs.getString("nome");
                Integer matricula = rs.getInt("matricula");
                String curso = rs.getString("curso");
                Alunos a = new Alunos(nome, matricula, curso);
                AlunosList.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return AlunosList;
    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        String sql = "SELECT * FROM alunos where matricula like? ";
        ArrayList AlunosList = new ArrayList();
        try {
            Connection conexao = ConexaoFactory.criarConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, "%" + criterio + "%");
            ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Integer matricula = rs.getInt("matricula");
                    String nome = rs.getString("nome");
                    System.out.println("Matricula" + matricula);
                    String curso = rs.getString("curso");
                    Alunos a = new Alunos (nome,matricula,curso);
                    AlunosList.add(a);
                }
        } catch (SQLException e ) {
            return null; 
        }
      return AlunosList;
    }

    @Override
    public String atualizar(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }  
    public String devolucao(int id) {
        return null;
    
    }

}