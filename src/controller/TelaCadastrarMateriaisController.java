package controller;

import dao.MateriaisDAO;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Materiais;

public class TelaCadastrarMateriaisController implements Initializable {

    @FXML
    private TableView<Materiais> tblCadastrarMateriais;
  
    @FXML
    private Button btncadastrar;
    @FXML
    private Button btnlimpar;
    @FXML
    private Button btnpesquisar;
    @FXML
    private Button btnapagar;
    
   
    @FXML
    private TextField txtidentificacao;
    @FXML
    private TextField txtdata;
    @FXML
    private TextField txtcodigo;
    
    
    MateriaisDAO processarMateriais = new MateriaisDAO();
    private Materiais m = new Materiais();
    ArrayList listaMateriais = new ArrayList();
    ObservableList<Materiais> obslistMateriais = FXCollections.observableArrayList();
    ObservableList<Materiais> observableListMateriaisTemp = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTabela();
    }
    
    public void inicializarTabela() {
        TableColumn colunaIdentificacao = new TableColumn<>("Identificação");
        TableColumn colunaCodigo = new TableColumn<>("Código");
        TableColumn colunaData = new TableColumn<>("Data");
        
        colunaIdentificacao.setCellValueFactory(new PropertyValueFactory<>("identificacao"));
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tblCadastrarMateriais.getColumns().addAll(colunaIdentificacao, colunaCodigo, colunaData);

        listaMateriais = processarMateriais.consultarTodos();
        obslistMateriais.addAll(listaMateriais);
        tblCadastrarMateriais.setItems(obslistMateriais);
    }

    @FXML
    private void handlebtncadastraraction(ActionEvent event) {
        System.out.println("Nome: " + txtidentificacao.getText());
        System.out.println("Código: " + txtcodigo.getText());
        System.out.println("Data: " + txtdata.getText());
        
        Materiais m = new Materiais(txtidentificacao.getText(),
        Integer.parseInt(txtcodigo.getText().trim()),
        txtdata.getText());
        processarMateriais.salvar(m);
        obslistMateriais.add(m);
    }

    @FXML
    private void handlebtnpesquisaraction(ActionEvent event) {
        TextInputDialog td = new TextInputDialog("Código...");
        td.showAndWait();
        String identificacao = td.getEditor().getText();
        System.out.println("Código digitado  " + identificacao);
        listaMateriais = processarMateriais.consultar(identificacao);
        System.out.println(listaMateriais.size());
        observableListMateriaisTemp.clear();
        observableListMateriaisTemp.addAll(listaMateriais);
        tblCadastrarMateriais.setItems(observableListMateriaisTemp);
    }

    @FXML
    private void handlebtnapagaraction(ActionEvent event) {
        int selectedIndex = tblCadastrarMateriais.getSelectionModel().getSelectedIndex();
        m = tblCadastrarMateriais.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            obslistMateriais.remove(selectedIndex);
            processarMateriais.excluir(m.getCodigo());
            limpar();
        } else {
            alerta("Não foi possível excluir este registro!");
        }
    }
    
    @FXML
    private void handletblCadastrarMateriaisclicked(MouseEvent event) {
        int selectedIndex = tblCadastrarMateriais.getSelectionModel().getSelectedIndex();
        m = tblCadastrarMateriais.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            txtidentificacao.setText(m.getIdentificacao());
            txtcodigo.setText((m.getCodigo().toString()));
            txtdata.setText(m.getData());
        }
    }
    
    @FXML
    private void handlebtnlimparaction(ActionEvent event) {
        limpar();
    }

    public void limpar() {
        txtidentificacao.clear();
        txtcodigo.clear();
        txtdata.clear();
        txtidentificacao.requestFocus();
    }

    public void alerta(String mensagem) {
        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
        dialogoErro.setTitle("Erro na operação");
        dialogoErro.setContentText(mensagem);
        dialogoErro.showAndWait();
    }

    @FXML
    private void tfcodigokeyreleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(txtcodigo);
        tff.formatter(); 
    }

    @FXML
    private void tfdatakeyreleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("##/##/####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(txtdata);
        tff.formatter();
    }
}