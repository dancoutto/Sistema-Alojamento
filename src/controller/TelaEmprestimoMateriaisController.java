package controller;

import dao.AlunosDAO;
import dao.EmprestimosDAO;
import dao.MateriaisDAO;
import dao.OcorrenciasDAO;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.Alunos;
import model.Emprestimos;
import model.Materiais;
import model.Ocorrencias;
import model.TiposOcorrencias;

public class TelaEmprestimoMateriaisController implements Initializable {

  @FXML
  private TableView<Emprestimos> tblemprestimo;
  @FXML
  private TextField txtmatriculaaluno;
  @FXML
  private TextField txtcodigomateriais;
  @FXML
  private Button btncadastrar;
  @FXML
  private Button btnapagar;
  @FXML
  private Button btnlimpar;
  @FXML
  private Button btnConsultar;

  @FXML
  private DatePicker dtEmprestimo;
  @FXML
  private DatePicker dtDevolucao;

  EmprestimosDAO processarEmprestimos = new EmprestimosDAO();
  ArrayList listaEmprestimos = new ArrayList();
  private Emprestimos em = new Emprestimos();
  ObservableList<Emprestimos> obslistEmprestimos = FXCollections.observableArrayList();
  ObservableList<Emprestimos> obslistEmprestimostemp = FXCollections.observableArrayList();

  AlunosDAO processarAlunos = new AlunosDAO();
  private Alunos a = new Alunos();
  private Alunos aPesquisa = new Alunos();
  ArrayList listaAlunos = new ArrayList();
  ObservableList<Alunos> obslistAlunos = FXCollections.observableArrayList();
  ObservableList<Alunos> obslistAlunostemp = FXCollections.observableArrayList();

  MateriaisDAO processarMateriais = new MateriaisDAO();
  private Materiais m = new Materiais();
  private Materiais mPesquisa = new Materiais();
  ArrayList listaMateriasis = new ArrayList();
  ObservableList<Materiais> obslistMateriais = FXCollections.observableArrayList();
  ObservableList<Materiais> obslistMateriaistemp = FXCollections.observableArrayList();
  

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    inicializarTabela();
    criarListener();
  }

  @FXML
  private void tblemprestimoclicked(MouseEvent event) {
    int selectedIndex = tblemprestimo.getSelectionModel().getSelectedIndex();
    Emprestimos em = tblemprestimo.getSelectionModel().getSelectedItem();
    if (selectedIndex >= 0) {
      txtmatriculaaluno.setText((em.getAlunoMatricula().toString()));
      txtcodigomateriais.setText(em.getMateriaisCodigo().toString());
      dtEmprestimo.setValue(em.getDataemprestimo());
    }
  }

  @FXML
  private void handlebtncadastraraction(ActionEvent event) {
    Emprestimos em;
    LocalDate entrada = null;
    entrada = dtEmprestimo.getValue();
    em = new Emprestimos(
        Integer.parseInt(txtmatriculaaluno.getText()),
        Integer.parseInt(txtcodigomateriais.getText()),
        entrada);
    processarEmprestimos.salvar(em);
    obslistEmprestimos.add(em);

  }

  @FXML
  private void handlebtnapagaraction(ActionEvent event) {
    int selectedIndex = tblemprestimo.getSelectionModel().getSelectedIndex();
    Emprestimos em = tblemprestimo.getSelectionModel().getSelectedItem();
    if (selectedIndex >= 0) {
      obslistEmprestimos.remove(selectedIndex);
      processarEmprestimos.excluir(em.getId());
      limpar();
    } else {
      alerta("Não foi possível excluir este registro!");
    }
  }

  private void limpar() {
    txtmatriculaaluno.clear();
    txtcodigomateriais.clear();
    dtEmprestimo.setValue(null);
    
  }

  private void alerta(String mensagem) {
    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
    dialogoErro.setTitle("Erro na operação");
    dialogoErro.setContentText(mensagem);
    dialogoErro.showAndWait();
  }

  @FXML
  private void handlebtnlimparaction(ActionEvent event) {
    limpar();
    listaEmprestimos = processarEmprestimos.consultarTodos();
    obslistEmprestimos.clear();
    obslistEmprestimos.addAll(listaEmprestimos);

  }

  private void inicializarTabela() {
    TableColumn colunaId = new TableColumn<>("ID");
    TableColumn colunaMatricula = new TableColumn<>("Matrícula");
    TableColumn colunaMaterial = new TableColumn<>("Código do Material");
    TableColumn colunaDataEmprestimo = new TableColumn<>("Data Emprestimo");
    TableColumn colunaDataDevolucao = new TableColumn<>("Data Devolução");

    colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
    colunaMatricula.setCellValueFactory(new PropertyValueFactory<>("alunoMatricula"));
    colunaMaterial.setCellValueFactory(new PropertyValueFactory<>("materiaisCodigo"));
    colunaDataEmprestimo.setCellValueFactory(new PropertyValueFactory<>("dataemprestimo"));
    colunaDataDevolucao.setCellValueFactory(new PropertyValueFactory<>("datadevolucao"));
    tblemprestimo.getColumns().addAll(colunaId, colunaMatricula, colunaMaterial, colunaDataEmprestimo, colunaDataDevolucao);

    listaEmprestimos = processarEmprestimos.consultarTodos();

    obslistEmprestimos.addAll(listaEmprestimos);
    tblemprestimo.setItems(obslistEmprestimos);
  }

  private void criarListener() {
    txtmatriculaaluno.setOnKeyPressed((KeyEvent event) -> {
      if (event.getCode() == KeyCode.F1) {
        a = retornaalunos.retorna();
        Integer matricula;
        matricula = a.getMatricula();
        txtmatriculaaluno.setText(a.getMatricula().toString());
      }
    });
    txtcodigomateriais.setOnKeyPressed((KeyEvent event) -> {
      if (event.getCode() == KeyCode.F1) {
        m = retornamateriais.retorna();
        Integer codigoMateriais;
        codigoMateriais = m.getCodigo();
        txtcodigomateriais.setText(m.getCodigo().toString());
      }
    });
  }

  @FXML
  private void handlebtndevolucaoaction(ActionEvent event) {
    int selectedIndex = tblemprestimo.getSelectionModel().getSelectedIndex();
    Emprestimos em = tblemprestimo.getSelectionModel().getSelectedItem();
    if (selectedIndex >= 0) {
      txtmatriculaaluno.setText((em.getAlunoMatricula().toString()));
      txtcodigomateriais.setText(em.getMateriaisCodigo().toString());
    }
    System.out.println("ID "+em.getId());
    LocalDate saida = null;
    saida = dtDevolucao.getValue();
    dtEmprestimo.setValue(em.getDataemprestimo());
    em.setDatadevolucao(saida);
    System.out.println("Devolucao "+em.toString());
    processarEmprestimos.devolucao(em);
    listaEmprestimos = processarEmprestimos.consultarTodos();
    obslistEmprestimos.clear();
    obslistEmprestimos.addAll(listaEmprestimos);

  }

  @FXML
  private void btnConsultarhandleAction(ActionEvent event) {
     listaEmprestimos = processarEmprestimos.consultarPendencias();
    obslistEmprestimos.clear();
    obslistEmprestimos.addAll(listaEmprestimos); 
  }
}