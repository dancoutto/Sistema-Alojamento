package model;

public class Alunos {
    String nome,curso; 
    Integer matricula;
    
    public Alunos() {
    }
    
    public Alunos(String nome, Integer matricula,String curso) {
        this.nome = nome;
        this.curso = curso;
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    } 
}

