package model;

import java.time.LocalDate;

public class Emprestimos {
     Integer id, alunoMatricula, materiaisCodigo; 
    LocalDate dataemprestimo, datadevolucao;  

  @Override
  public String toString() {
    return "Emprestimos{" + "id=" + id + ", alunoMatricula=" + alunoMatricula + ", materiaisCodigo=" + materiaisCodigo + ", dataemprestimo=" + dataemprestimo + ", datadevolucao=" + datadevolucao + '}';
  }

  public Emprestimos(Integer id, Integer alunoMatricula, Integer materiaisCodigo, LocalDate dataemprestimo) {
    this.id = id;
    this.alunoMatricula = alunoMatricula;
    this.materiaisCodigo = materiaisCodigo;
    this.dataemprestimo = dataemprestimo;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAlunoMatricula() {
    return alunoMatricula;
  }

  public void setAlunoMatricula(Integer alunoMatricula) {
    this.alunoMatricula = alunoMatricula;
  }

  public Integer getMateriaisCodigo() {
    return materiaisCodigo;
  }

  public void setMateriaisCodigo(Integer materiaisCodigo) {
    this.materiaisCodigo = materiaisCodigo;
  }

  public LocalDate getDataemprestimo() {
    return dataemprestimo;
  }

  public void setDataemprestimo(LocalDate dataemprestimo) {
    this.dataemprestimo = dataemprestimo;
  }

  public LocalDate getDatadevolucao() {
    return datadevolucao;
  }

  public void setDatadevolucao(LocalDate datadevolucao) {
    this.datadevolucao = datadevolucao;
  }

  public Emprestimos(Integer alunoMatricula, Integer materiaisCodigo, LocalDate dataemprestimo) {
    this.alunoMatricula = alunoMatricula;
    this.materiaisCodigo = materiaisCodigo;
    this.dataemprestimo = dataemprestimo;
  }

  public Emprestimos(Integer id, Integer alunoMatricula, Integer materiaisCodigo, LocalDate dataemprestimo, LocalDate datadevolucao) {
    this.id = id;
    this.alunoMatricula = alunoMatricula;
    this.materiaisCodigo = materiaisCodigo;
    this.dataemprestimo = dataemprestimo;
    this.datadevolucao = datadevolucao;
  }

   
    public Emprestimos() {
    }
}

  
    

