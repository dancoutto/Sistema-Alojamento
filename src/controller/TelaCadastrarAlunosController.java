package controller;

import dao.AlunosDAO;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Alunos;
import model.TiposOcorrencias;
import model.cursostecnicos;

public class TelaCadastrarAlunosController implements Initializable {

    @FXML
    private TableView<Alunos> tblCadastrarAlunos;
    @FXML
    private TextField txtnome;
    @FXML
    private TextField txtmatricula;

    AlunosDAO processarAlunos = new AlunosDAO();
    private Alunos a = new Alunos();
    ArrayList listaAlunos = new ArrayList();
    ObservableList<Alunos> obslistAlunos = FXCollections.observableArrayList();
    ObservableList<Alunos> observableListAlunosTemp = FXCollections.observableArrayList();
    ObservableList<cursostecnicos> obslistcursostecnicos = FXCollections.observableArrayList();
    @FXML
    private ComboBox<cursostecnicos> cbcursotecnico;
    @FXML
    private Button btncadastrar;
    @FXML
    private Button btnlimpar;
    @FXML
    private Button btnpesquisarnome;
    @FXML
    private Button btnapagar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTabela();
        inicializarcursostecnicos();
    }

    public void inicializarTabela() {
        TableColumn colunaNome = new TableColumn<>("Nome");
        TableColumn colunaMatricula = new TableColumn<>("Matrícula");
        TableColumn colunaCurso = new TableColumn<>("Curso");

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colunaCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        tblCadastrarAlunos.getColumns().addAll(colunaNome, colunaMatricula, colunaCurso);

        listaAlunos = processarAlunos.consultarTodos();
        obslistAlunos.addAll(listaAlunos);
        tblCadastrarAlunos.setItems(obslistAlunos);
    }

    @FXML
    private void handlrebtncadastraraction(ActionEvent event) {
        System.out.println(cbcursotecnico.getValue().toString());
        System.out.println("Nome "+txtnome.getText());
        
        //(String nome, Integer matricula, String curso) 
        Alunos a = new Alunos(txtnome.getText(),
        Integer.parseInt(txtmatricula.getText().trim()),
        cbcursotecnico.getValue().toString());
        processarAlunos.salvar(a);
        obslistAlunos.add(a);
    }

    @FXML
    private void handlelimparaction(ActionEvent event) {
        limpar();
    }

    @FXML
    private void tblCadastraralunosclicked(MouseEvent event) {
        int selectedIndex = tblCadastrarAlunos.getSelectionModel().getSelectedIndex();
        a = tblCadastrarAlunos.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            txtnome.setText(a.getNome());
            txtmatricula.setText((a.getMatricula().toString()));
            
        }
    }


    @FXML
    private void handlebtnpesquisaeaction(ActionEvent event) {
        TextInputDialog td = new TextInputDialog("Matrícula a pesquisar...");
        td.showAndWait();
        String matricula = td.getEditor().getText();
        System.out.println("Matrícula digitada  " + matricula);
        listaAlunos = processarAlunos.consultar(matricula);
        System.out.println(listaAlunos.size());
        observableListAlunosTemp.clear();
        observableListAlunosTemp.addAll(listaAlunos);
        tblCadastrarAlunos.setItems(observableListAlunosTemp);
    }

    @FXML
    private void handlrebtnapagaraction(ActionEvent event) {
        int selectedIndex = tblCadastrarAlunos.getSelectionModel().getSelectedIndex();
        a = tblCadastrarAlunos.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            obslistAlunos.remove(selectedIndex);
            processarAlunos.excluir(a.getMatricula());
            limpar();
        } else {
            alerta("Não foi possível excluir este registro!");
        }
    }

    public void limpar() {
        txtnome.clear();
        txtmatricula.clear();
        txtnome.requestFocus();
        cbcursotecnico.getSelectionModel().clearSelection();
    }

    public void alerta(String mensagem) {
        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
        dialogoErro.setTitle("Erro na operação");
        dialogoErro.setContentText(mensagem);
        dialogoErro.showAndWait();
    }

    private void inicializarcursostecnicos() {
        obslistcursostecnicos.add(new cursostecnicos(1, "Técnico em Agropecuária"));
        obslistcursostecnicos.add(new cursostecnicos(2, "Técnico em Elétronica"));
        obslistcursostecnicos.add(new cursostecnicos(3, "Técnico em Elétrotecnica "));
        obslistcursostecnicos.add(new cursostecnicos(4, "Técnico em Alimentos "));
        obslistcursostecnicos.add(new cursostecnicos(5, "Técnico em Hospedagem "));
        obslistcursostecnicos.add(new cursostecnicos(6, "Técnico em Informática "));

        cbcursotecnico.setItems(obslistcursostecnicos);
        tblCadastrarAlunos.setItems(obslistAlunos);
    }

    @FXML
    private void txtmatriculaKeyPress(KeyEvent event) {
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