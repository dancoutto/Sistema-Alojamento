package model;

public class Materiais {
    String identificacao;
    Integer codigo; 
    String data; 

    @Override
    public String toString() {
        return "Materiais{" + "identificacao=" + identificacao + ", codigo=" + codigo + ", data=" + data + '}';
    }
       
    public Materiais() {
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Materiais(String identificacao, Integer codigo, String data) {
        this.identificacao = identificacao;
        this.codigo = codigo;
        this.data = data;
    }  
}
