package model;

public class Ocorrencias {
    Integer identificacao, matricula;
    String data, tipo, comentario, responsavel; 
    
    public Ocorrencias() {
    }
    
    public Ocorrencias(Integer identificacao, Integer matricula, String data, String tipo, String comentario, String responsavel) {
        this.identificacao = identificacao;
        this.matricula = matricula;
        this.data = data;
        this.tipo = tipo;
        this.comentario = comentario;
        this.responsavel = responsavel;
    }

    public Integer getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(Integer identificacao) {
        this.identificacao = identificacao;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public Ocorrencias( Integer matricula, String data, String tipo, String comentario, String responsavel) {
        this.matricula = matricula;
        this.data = data;
        this.tipo = tipo;
        this.comentario = comentario;
        this.responsavel = responsavel;
    }
}
