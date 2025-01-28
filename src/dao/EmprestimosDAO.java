package dao;

import conn.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import model.Emprestimos;

public class EmprestimosDAO {

    public String salvar(Object o) {
        Emprestimos em = (Emprestimos) o;
        String sql = "INSERT INTO emprestimos (alunoMatricula ,materiaisCodigo, dataemprestimo,datadevolucao) VALUES (?,?,?,?)";
        try (Connection conexao = ConexaoFactory.criarConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, em.getAlunoMatricula());
            ps.setInt(2, em.getMateriaisCodigo());
            String ano = String.valueOf(em.getDataemprestimo().getYear());
            String mes = String.valueOf(em.getDataemprestimo().getMonthValue());
            String dia = String.valueOf(em.getDataemprestimo().getDayOfMonth());
            ps.setString(3, ano + "-" + mes + "-" + dia);
            ps.setNull(4, Types.DATE);

            System.out.println("Comando salvar " + ps.toString());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao salvar dados. Metodo SALVAR");
        }
        return ("OBJETO GRAVADO");
    }

    public String atualizar(Object o) {
        Emprestimos em = (Emprestimos) o;
        String sql = "UPDATE `emprestimos` SET `alunoMatricula`=?,`materiaisCodigo`=?,`dataemprestimo`=? , datadevolucao=?  WHERE id = ?";
        try (Connection conexao = ConexaoFactory.criarConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, em.getAlunoMatricula());
            ps.setInt(2, em.getMateriaisCodigo());
            //    ps.setDate(3, (java.sql.Date) em.getDataemprestimo());
            //    ps.setDate(4, (java.sql.Date) em.getDatadevolucao());
            System.out.println("Comando atualizar  " + ps.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar dados. Metodo ATUALIZAR");
        }
        return ("OBJETO GRAVADO");
    }

    public String excluir(int id) {
        System.out.println("id " + id);
        String sql = "DELETE FROM emprestimos WHERE id= ?";
        try (Connection conexao = ConexaoFactory.criarConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            System.out.println(ps.toString());
            ps.setInt(1, id);
            System.out.println("Comando de apagar  " + ps.toString());
            ps.executeUpdate();
            System.out.println("Registro excluido com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir dados. Metodo EXCLUIR");
        }
        return ("OBJETO EXCLUIDO");
    }

    /**
     * String dataemprestimo = rs.getString("entrada"); String datadevolucao = *
     * rs.getString("saida"); System.out.println("do banco de dados entrada: " +
     * dataemprestimo); System.out.println("do banco de dados saida: " +
     * datadevolucao);
     *
     */
    public ArrayList<Object> consultarTodos() {
        String sql = "SELECT * FROM `emprestimos`";
        ArrayList EmprestimosList = new ArrayList();
        try (Connection conexao = ConexaoFactory.criarConexao();
                PreparedStatement ps = conexao.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            System.out.println(ps.toString());
            while (rs.next()) {
                Integer id = rs.getInt("id");
                Integer alunoMatricula = rs.getInt("alunoMatricula");
                Integer materiaisCodigo = rs.getInt("materiaisCodigo");

                String dataemprestimo = rs.getString("dataemprestimo");
                String datadevolucao = rs.getString("datadevolucao");

                LocalDate entrada, saida;
                entrada = LocalDate.parse(dataemprestimo);
                if (datadevolucao == null) {
                    Emprestimos em = new Emprestimos(id, alunoMatricula, materiaisCodigo, entrada);
                    EmprestimosList.add(em);
                } else {
                    saida = LocalDate.parse(datadevolucao);
                    Emprestimos em = new Emprestimos(id, alunoMatricula, materiaisCodigo, entrada, saida);
                    EmprestimosList.add(em);
                }

                //     Emprestimos em = new Emprestimos(id, alunoMatricula, materiaisCodigo, dataemprestimo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar banco de dados. Metodo ConsultarTodos");
            return null;
        }
        return EmprestimosList;
    }

    public ArrayList<Object> consultar(String criterio) {
        String sql = "SELECT * FROM emprestimos where datadevolucao is null";
        ArrayList EmprestimosList = new ArrayList();
        try {
            Connection conexao = ConexaoFactory.criarConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
           
                Date datadevolucao = rs.getDate("datadevolucao");

               
            }
        } catch (Exception e) {
System.out.println("Erro ao pesquisar nome! Metodo Consultar");
            return null;
        }
        return EmprestimosList;
    }

    public String devolucao(Object o) {
        Emprestimos em = (Emprestimos) o;
        int id = em.getId();
        String sql = "UPDATE emprestimos SET datadevolucao=? where id=?";
        try (Connection conexao = ConexaoFactory.criarConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            String ano = String.valueOf(em.getDatadevolucao().getYear());
            String mes = String.valueOf(em.getDatadevolucao().getMonthValue());
            String dia = String.valueOf(em.getDatadevolucao().getDayOfMonth());
            ps.setString(1, ano + "-" + mes + "-" + dia);
            ps.setInt(2, id);
            System.out.println("Saida do devolucao: " + ps.toString());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar dados. Metodo DEVOLUÇÃO");
        }
        return ("OBJETO GRAVADO");

    }
    

  public ArrayList<Object> consultarPendencias() {
        String sql = "SELECT * FROM `emprestimos` where datadevolucao is null";
        ArrayList EmprestimosList = new ArrayList();
        try (Connection conexao = ConexaoFactory.criarConexao();
                PreparedStatement ps = conexao.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            System.out.println(ps.toString());
            while (rs.next()) {
                Integer id = rs.getInt("id");
                Integer alunoMatricula = rs.getInt("alunoMatricula");
                Integer materiaisCodigo = rs.getInt("materiaisCodigo");

                String dataemprestimo = rs.getString("dataemprestimo");
                              LocalDate entrada, saida;
                entrada = LocalDate.parse(dataemprestimo);
                    Emprestimos em = new Emprestimos(id, alunoMatricula, materiaisCodigo, entrada);
                    EmprestimosList.add(em);
              
                //     Emprestimos em = new Emprestimos(id, alunoMatricula, materiaisCodigo, dataemprestimo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar banco de dados. Metodo COnsultar Pendencias");
            return null;
        }
        return EmprestimosList;
    }  
}