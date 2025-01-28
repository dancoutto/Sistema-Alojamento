package dao;

import conn.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Ocorrencias;

public class OcorrenciasDAO implements interfaces.InterfaceModelo {

    @Override
    public String salvar(Object o) {
        Ocorrencias c = (Ocorrencias) o;
        String sql = "INSERT INTO ocorrencias (matricula ,data, tipo, comentario, responsavel) VALUES (?,? ,?, ?,?)";
        try (Connection conexao = ConexaoFactory.criarConexao();
            PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, c.getMatricula());
            ps.setString(2, c.getData());
            ps.setString(3, c.getTipo());
            ps.setString(4, c.getComentario());
            ps.setString(5, c.getResponsavel());
            System.out.println("Comando salvar " + ps.toString());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao salvar dados. Metodo SALVAR");
        }
        return ("OBJETO GRAVADO");
    }

    @Override
    public String atualizar(Object o) {
        Ocorrencias c = (Ocorrencias) o;
        String sql = "UPDATE `ocorrencias` SET `matricula`=?,`data`=?,`tipo`=? , comentario=? , responsavel=? WHERE identificacao = ?";
        try (Connection conexao = ConexaoFactory.criarConexao();
            PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, c.getMatricula());
            ps.setString(2, c.getData());
            ps.setString(3, c.getTipo());
            ps.setString(4, c.getComentario());
            ps.setString(5, c.getResponsavel());
            System.out.println("Comando atualizar  " + ps.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar dados. Metodo ATUALIZAR");
        }
        return ("OBJETO GRAVADO");
    }

    @Override
    public String excluir(int identificacao) {
        System.out.println("id " + identificacao);
        String sql = "DELETE FROM ocorrencias WHERE identificacao= ?";
        try (Connection conexao = ConexaoFactory.criarConexao();
            PreparedStatement ps = conexao.prepareStatement(sql)) {
            System.out.println(ps.toString());
            ps.setInt(1, identificacao);
            System.out.println("Comando de apagar  " + ps.toString());
            ps.executeUpdate();
            System.out.println("Registro excluido com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir dados. Metodo EXCLUIR");
        }
        return ("OBJETO EXCLUIDO");
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        String sql = "SELECT * FROM `ocorrencias`";
        ArrayList OcorrenciasList = new ArrayList();
        try (Connection conexao = ConexaoFactory.criarConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            System.out.println(ps.toString());
            while (rs.next()) {
                Integer identificacao = rs.getInt("identificacao");
                Integer matricula = rs.getInt("matricula");
                String data = rs.getString("data");
                String tipo = rs.getString("tipo");
                String comentario = rs.getString("comentario");
                String responsavel = rs.getString("responsavel");
                Ocorrencias c = new Ocorrencias(identificacao, matricula, data, tipo, comentario, responsavel);
                OcorrenciasList.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar banco de dados. Metodo ConsultarTodos");
            return null;
        }
        return OcorrenciasList;
    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        String sql = "SELECT * FROM ocorrencias where matricula like ? ";
        ArrayList OcorrenciasList = new ArrayList();
        try {
            Connection conexao = ConexaoFactory.criarConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, "%" + criterio + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer identificacao = rs.getInt("identificacao");
                Integer matricula = rs.getInt("matricula");
                String data = rs.getString("data");
                String tipo = rs.getString("tipo");
                String comentario = rs.getString("comentario");
                String responsavel = rs.getString("responsavel");
                Ocorrencias c = new Ocorrencias(identificacao, matricula, data, tipo, comentario, responsavel);
                OcorrenciasList.add(c);
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar nome! Metodo Consultar");
            return null;
        }
        return OcorrenciasList;
    }

    public String devolucao(int id) {
        return null;
    
    }

    public String listar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}