package controller;

import dao.AlunosDAO;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Alunos;

public class retornaalunos {
    
 static Alunos a;
  static TableView<Alunos> tblCadastrarAlunos = new TableView<>();
  static TextField txtPesquisa = new TextField();

  public static Alunos retorna() {
    ArrayList listaAlunos = new ArrayList();
    AlunosDAO processarAlunos = new AlunosDAO();
    ObservableList<Alunos> obslistAlunos = FXCollections.observableArrayList();
    ObservableList<Alunos> obslistAlunostemp = FXCollections.observableArrayList();
    
    Stage stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    
    AnchorPane layout = new AnchorPane();
    HBox hbox = new HBox();
    Button btnFechar = new Button("Fechar");
    
    hbox.getChildren().addAll(txtPesquisa, btnFechar);
    VBox vbox = new VBox();
    TableView<Alunos> tblCadastrarAlunos = new TableView<>();
    
    TableColumn colunaMatricula = new TableColumn<>("Matricula");
    TableColumn colunaNome = new TableColumn<>("Nome");
    TableColumn colunaCurso = new TableColumn<>("Curso");

    colunaMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
    colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    colunaCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
    tblCadastrarAlunos.setMinWidth(800);
    colunaMatricula.prefWidthProperty().bind(tblCadastrarAlunos.widthProperty().multiply(0.2));
    colunaNome.prefWidthProperty().bind(tblCadastrarAlunos.widthProperty().multiply(0.2));
    colunaCurso.prefWidthProperty().bind(tblCadastrarAlunos.widthProperty().multiply(0.2));
    colunaMatricula.setResizable(false);
    colunaNome.setResizable(false);
     colunaCurso.setResizable(false);
    tblCadastrarAlunos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    tblCadastrarAlunos.getColumns().addAll(colunaMatricula, colunaNome, colunaCurso);
    listaAlunos = processarAlunos.consultarTodos();
    obslistAlunos.addAll(listaAlunos);
    tblCadastrarAlunos.setItems(obslistAlunos);

    tblCadastrarAlunos.setOnMouseClicked((MouseEvent e) -> {
      if (e.getButton() == MouseButton.PRIMARY) {
        if (e.getClickCount() == 2) {
          
          int selectedIndex = tblCadastrarAlunos.getSelectionModel().getSelectedIndex();
          a = tblCadastrarAlunos.getSelectionModel().getSelectedItem();
          stage.close(); 
        }
      }
    });
    
    txtPesquisa.textProperty().addListener((observable, oldValue, newValue) -> {
      if (txtPesquisa.getText().trim().length() > 0) {
        obslistAlunostemp.clear();
        for (Alunos pessoa : obslistAlunos) {
          if (pessoa.getNome().toUpperCase().startsWith(txtPesquisa.getText().toUpperCase().trim())) {
            obslistAlunostemp.add(pessoa);
            tblCadastrarAlunos.setItems(obslistAlunostemp);
          }
        }
      } else {
        tblCadastrarAlunos.setItems(obslistAlunos);
      }
    });

    vbox.getChildren().addAll(hbox, tblCadastrarAlunos);
    layout.getChildren().add(vbox);
    Scene scene = new Scene(layout, 490, 450);
    stage.setWidth(495);
    stage.setHeight(450);
    stage.setTitle("SELEÇÃO DE ALUNOS");
    stage.setScene(scene);
    stage.showAndWait();
    return a;
  }
}