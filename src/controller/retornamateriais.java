package controller;

import dao.MateriaisDAO;
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
import model.Materiais;

public class retornamateriais {

    static Materiais m;
    static TableView<Materiais> tblCadastrarMateriais = new TableView<>();
    static TextField txtPesquisa = new TextField();

    public static Materiais retorna() {
        ArrayList listaMateriais = new ArrayList();
        MateriaisDAO processarMateriais= new MateriaisDAO();
        ObservableList<Materiais> obslistMateriais = FXCollections.observableArrayList();
        ObservableList<Materiais> obslistMateriaistemp = FXCollections.observableArrayList();
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        
        AnchorPane layout = new AnchorPane();
        HBox hbox = new HBox();
        Button btnFechar = new Button("Fechar");
        
        hbox.getChildren().addAll(txtPesquisa, btnFechar);
        VBox vbox = new VBox();
        TableView<Materiais> tblCadastrarMateriais = new TableView<>();
        
        TableColumn colunaIdentificacao = new TableColumn<>("identificacao");
        TableColumn colunaData = new TableColumn<>("Data");
        TableColumn colunaCodigo = new TableColumn<>("Codigo");
        
        colunaIdentificacao.setCellValueFactory(new PropertyValueFactory<>("identificacao"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tblCadastrarMateriais.setMinWidth(800);
        colunaIdentificacao.prefWidthProperty().bind(tblCadastrarMateriais.widthProperty().multiply(0.2));
        colunaData.prefWidthProperty().bind(tblCadastrarMateriais.widthProperty().multiply(0.2));
        colunaCodigo.prefWidthProperty().bind(tblCadastrarMateriais.widthProperty().multiply(0.2));
        colunaIdentificacao.setResizable(false);
        colunaData.setResizable(false);
        colunaCodigo.setResizable(false);
        tblCadastrarMateriais.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tblCadastrarMateriais.getColumns().addAll(colunaIdentificacao, colunaData, colunaCodigo);
        listaMateriais = processarMateriais.consultarTodos();
        obslistMateriais.addAll(listaMateriais);
        tblCadastrarMateriais.setItems(obslistMateriais);
        
        tblCadastrarMateriais.setOnMouseClicked((MouseEvent e) -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (e.getClickCount() == 2) {

                    int selectedIndex = tblCadastrarMateriais.getSelectionModel().getSelectedIndex();
                    m = tblCadastrarMateriais.getSelectionModel().getSelectedItem();
                    stage.close(); 
                }
            }
        });

        txtPesquisa.textProperty().addListener((observable, oldValue, newValue) -> {
            if (txtPesquisa.getText().trim().length() > 0) {
                obslistMateriaistemp.clear();
                for (Materiais pessoa : obslistMateriais) {
                    if (pessoa.getCodigo().toString().toUpperCase().startsWith(txtPesquisa.getText().toUpperCase().trim())) {
                        obslistMateriaistemp.add(pessoa);
                        tblCadastrarMateriais.setItems(obslistMateriaistemp);
                    }
                }
            } else {
                tblCadastrarMateriais.setItems(obslistMateriais);
            }
        });

        vbox.getChildren().addAll(hbox, tblCadastrarMateriais);
        layout.getChildren().add(vbox);
        Scene scene = new Scene(layout, 490, 450);
        stage.setWidth(490);
        stage.setHeight(450);
        stage.setTitle("SELEÇÃO DE ALUNOS");
        stage.setScene(scene);
        stage.showAndWait();
        return m;
    }
}