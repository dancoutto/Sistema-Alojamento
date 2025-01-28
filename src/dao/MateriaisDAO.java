package dao;

import conn.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Materiais;

public class MateriaisDAO implements interfaces.InterfaceModelo {

    @Override
    public String salvar(Object o) {
        Materiais material = (Materiais) o;
        String sql = "INSERT INTO materiais (identificacao,codigo,data) VALUES ( ?, ?, ?)";
        try (Connection conexao = ConexaoFactory.criarConexao();
            PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, material.getIdentificacao());
            ps.setInt(2, material.getCodigo());
            ps.setString(3, material.getData());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ("OBJETO GRAVADO");

    }
    
    @Override
    public ArrayList<Object> consultarTodos() {
        String sql = "SELECT * from materiais";
        ArrayList MateriaisList = new ArrayList();
        try (Connection conexao = ConexaoFactory.criarConexao();
                PreparedStatement ps = conexao.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {

                String identificacao = rs.getString("identificacao");
                Integer codigo = rs.getInt("codigo");
                String data = rs.getString("data");
                
                Materiais m = new Materiais(identificacao, codigo, data);
                System.out.println(m.toString());
                MateriaisList.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return MateriaisList;
    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        String sql = "SELECT * FROM materiais where codigo like ? ";
        ArrayList MateriaisList = new ArrayList();
        try {
            Connection conexao = ConexaoFactory.criarConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, "%" + criterio + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer codigo = rs.getInt("codigo");
                String data = rs.getString("data");
                System.out.println("Codigo" + codigo);
                String identificacao = rs.getString("identificacao");
                Materiais m = new Materiais(identificacao, codigo, data);
                MateriaisList.add(m);
            }
        } catch (SQLException e) {
            return null;
        }
        return MateriaisList;
    }
    
    
    @Override
    public String excluir(int id) {
        System.out.println("id " + id);
        String sql = "DELETE FROM `materiais` WHERE codigo= ?";
        try (Connection conexao = ConexaoFactory.criarConexao();
            PreparedStatement ps = conexao.prepareStatement(sql)) {
            System.out.println(ps.toString());
            ps.setInt(1, id);
            System.out.println("Comando de EXCLUIR matrital do código " + ps.toString());
            ps.executeUpdate();
            System.out.println("Registro EXCLUIDO com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir dados. Metodo Excluir");
        }
            return ("Sucesso ao EXCLUIR em MATERIAIS!");
    }
    
    @Override
    public String atualizar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    public String devolucao(int id) {
        return null;
    }
}