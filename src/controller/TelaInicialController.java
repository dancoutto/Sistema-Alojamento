package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class TelaInicialController implements Initializable {

  @FXML
  private AnchorPane anchorPane;
  @FXML
  private MenuItem menuItemalunos;
  @FXML
  private MenuItem menuItemmateriais;
  @FXML
  private MenuItem menuItemocorrencias;
  @FXML
  private MenuItem menuItemempMateriais;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
  }

  @FXML
  private void handleMenuItemAlunosAction(ActionEvent event) {
    try {
      AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/TelaCadastrarAlunos.fxml"));
      anchorPane.getChildren().setAll(a);
    } catch (Exception e) {
      System.out.println("Erro ao carregar a página CADASTRO DE ALUNOS");
    }
  }

  @FXML
  private void handleMenuItemmateriaisAction(ActionEvent event) {
    try {
      AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/TelaCadastrarMateriais.fxml"));
      anchorPane.getChildren().setAll(a);
    } catch (Exception e) {
      System.out.println("Erro ao carregar a página CADASTRO DE MATERIAIS");
    }
  }

  @FXML
  private void handleMenuItemocorrenciasAction(ActionEvent event) {
    try {
      AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/TelaCadastrarOcorrencias.fxml"));
      anchorPane.getChildren().setAll(a);
    } catch (Exception e) {
      System.out.println("Erro ao carregar a página CADASTRO DE OCORRÊNCIAS");
    }
  }

  @FXML
  private void handleMenuItemEmpMateriaisAction(ActionEvent event) {
    try {
      AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/TelaEmprestimoMateriais.fxml"));
      anchorPane.getChildren().setAll(a);
    } catch (Exception e) {
      System.out.println("Erro ao carregar a página EMPRÉSTIMO DE MATERIAIS");
    }
  }
}