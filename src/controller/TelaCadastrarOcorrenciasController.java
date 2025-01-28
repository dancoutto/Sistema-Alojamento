package controller;

import dao.AlunosDAO;
import dao.OcorrenciasDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Alunos;
import model.Ocorrencias;
import model.TiposOcorrencias;
import controller.TextFieldFormatter;

public class TelaCadastrarOcorrenciasController implements Initializable {

    @FXML
    private TableView<Ocorrencias> tblCadastrarOcorrencias;
    @FXML
    private ComboBox<TiposOcorrencias> cbtipo;
    @FXML
    private TextField txtmatricula, txtdata, txtcomentario, txtresponsavel;

    OcorrenciasDAO processarOcorrencias = new OcorrenciasDAO();
    ArrayList listaOcorrencias = new ArrayList();
    ObservableList<Ocorrencias> obslistOcorrencias = FXCollections.observableArrayList();
    ObservableList<Ocorrencias> obslistOcorrenciastemp = FXCollections.observableArrayList();
    ObservableList<TiposOcorrencias> obslistTipoOcorrencias = FXCollections.observableArrayList();
    
    AlunosDAO  processarAlunos = new AlunosDAO(); 
    private Alunos a = new Alunos();
    private Alunos aPesquisa = new Alunos();
    ArrayList listaAlunos = new ArrayList();
    ObservableList<Alunos> obslistAlunos = FXCollections.observableArrayList();
    ObservableList<Alunos> obslistAlunostemp = FXCollections.observableArrayList();
    @FXML
    private Button btncadastrar;
    @FXML
    private Button btnlimpar;
    @FXML
    private Button btnapagar;
    @FXML
    private Button btnpesquisar;

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTabela();
        inicializarTipoOcorrencias();
        criarListener();
    }
    
    public void inicializarTabela() {
        TableColumn colunaIdentificao = new TableColumn<>("Identificação");
        TableColumn colunaMatricula = new TableColumn<>("Matrícula");
        TableColumn colunaData = new TableColumn<>("Data");
        TableColumn colunaTipo = new TableColumn<>("Tipo");
        TableColumn colunaComentario = new TableColumn<>("Comentário");
        TableColumn colunaResponsavel = new TableColumn<>("Responsável");
        

        colunaIdentificao.setCellValueFactory(new PropertyValueFactory<>("identificacao"));
        colunaMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colunaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colunaComentario.setCellValueFactory(new PropertyValueFactory<>("comentario"));
        colunaResponsavel.setCellValueFactory(new PropertyValueFactory<>("responsavel"));
        tblCadastrarOcorrencias.getColumns().addAll(colunaIdentificao, colunaMatricula, colunaData, colunaTipo, colunaComentario, colunaResponsavel);

        listaOcorrencias = processarOcorrencias.consultarTodos();
        obslistOcorrencias.addAll(listaOcorrencias);
        tblCadastrarOcorrencias.setItems(obslistOcorrencias);
    }
    
    private void inicializarTipoOcorrencias(){
        obslistTipoOcorrencias.add(new TiposOcorrencias(1, "Problemas no Refeitório"));
        obslistTipoOcorrencias.add(new TiposOcorrencias(2, "Problemas com aluguel"));
        obslistTipoOcorrencias.add(new TiposOcorrencias(3, "Problemas na Lavanderia"));
        cbtipo.setItems(obslistTipoOcorrencias);
        tblCadastrarOcorrencias.setItems(obslistOcorrencias);
    }

    @FXML
    private void tblOcorrenciasClicked(MouseEvent event) {
        int selectedIndex = tblCadastrarOcorrencias.getSelectionModel().getSelectedIndex();
        Ocorrencias o = tblCadastrarOcorrencias.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            txtmatricula.setText((o.getMatricula().toString()));
            txtdata.setText(o.getData());
            txtcomentario.setText(o.getComentario());
            txtresponsavel.setText(o.getResponsavel());
        }
    }

    @FXML
    private void handlebtncadastraraction(ActionEvent event) {
        Ocorrencias o = new Ocorrencias(
        Integer.parseInt(txtmatricula.getText().trim()),
        txtdata.getText(),
        cbtipo.getValue().toString(),
        txtcomentario.getText(),
        txtresponsavel.getText());
        
        processarOcorrencias.salvar(o);
        obslistOcorrencias.add(o);
    }

    @FXML
    private void handleapagaraction(ActionEvent event) {
        int selectedIndex = tblCadastrarOcorrencias.getSelectionModel().getSelectedIndex();
        Ocorrencias o = tblCadastrarOcorrencias.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            obslistOcorrencias.remove(selectedIndex);
            processarOcorrencias.excluir(o.getIdentificacao());
            limpar();
        } else {
            alerta("Não foi possível excluir este registro!");
        }
    }

    
    @FXML
    private void handlebtnlimparaction(ActionEvent event) {
        limpar();
    }

    /*@FXML
    private void handlebtnReiniciarTabela(ActionEvent event) {
        tblCadastrarOcorrencias.setItems(obslistOcorrencias);
    }*/
    
    public void limpar() {
        txtmatricula.clear();
        txtdata.clear();
        txtcomentario.clear();
        txtresponsavel.clear();
        txtmatricula.requestFocus();
        cbtipo.getSelectionModel().clearSelection();
    }
     
    public void alerta(String mensagem) {
        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
        dialogoErro.setTitle("Erro na operação");
        dialogoErro.setContentText(mensagem);
        dialogoErro.showAndWait();
    }

    private void criarListener() {
      txtmatricula.setOnKeyPressed((KeyEvent event) -> {
        if (event.getCode() == KeyCode.F1) {
            a = retornaalunos.retorna();
            Integer matricula; 
            matricula = a.getMatricula();
            txtmatricula.setText(a.getMatricula().toString());
            
        }
    });
    }

    @FXML
    private void handlepesquisaraction(ActionEvent event) {
        TextInputDialog td = new TextInputDialog("Digite a matricula ...");
        td.showAndWait();
        String matricula = td.getEditor().getText();
        System.out.println("Matricula digitado  " + matricula);
        listaOcorrencias = processarOcorrencias.consultar(matricula);
        System.out.println(listaOcorrencias.size());
        obslistOcorrenciastemp.clear();
        obslistOcorrenciastemp.addAll(listaOcorrencias);
        tblCadastrarOcorrencias.setItems(obslistOcorrenciastemp);
    }

    @FXML
    private void tfdatakeyreleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("##/##/####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(txtdata);
        tff.formatter();
        
    }

    @FXML
    private void tfmatriculakeyreleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("#####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(txtmatricula);
        tff.formatter(); 
    }
}
