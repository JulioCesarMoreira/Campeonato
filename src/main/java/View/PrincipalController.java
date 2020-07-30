package View;

import java.io.BufferedReader;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import Model.TIme;
import Model.Jogo;
import Utilit.Dados;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PrincipalController implements Initializable {

    public List<TIme> lstPrinc = new ArrayList<>();
    Dados dados;
    private TIme timeSel;

    @FXML
    private TableView<TIme> tbVwTimes;

    @FXML
    private VBox vbResultJogos;

    @FXML
    private Label lbNomeTime;

    @FXML

    private TableView tbVwJogo;

    @FXML
    private StackPane pnJogo;
    @FXML
    private VBox vbInicial;

    @FXML
    private void btnRetornarTelaJogo(Event event) {
        pnJogo.setVisible(false);
        vbInicial.setVisible(true);
        tbVwTimes.requestFocus();
    }

    @FXML
    private void btnGravaJSON(Event event) {
        dados.gravaJson(tbVwTimes.getSelectionModel().getSelectedItem());
    }
    
    @FXML
    private void tblVwClick(Event event) {
        MouseEvent me = null;
        if (event.getEventType() == MOUSE_CLICKED) {
            me = (MouseEvent) event;

            if (me.getClickCount() == 2) {
                vbInicial.setVisible(false);
                pnJogo.setVisible(true);
                timeSel = (TIme) tbVwTimes.getSelectionModel().getSelectedItem();
                lbNomeTime.setText(timeSel.getNome());
                tbVwJogo.setItems(FXCollections.observableList(timeSel.getJogos()));
            }
        }

    }

    @FXML
    private void btnAbrirClick() {
        final Stage stage = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("abrir arquivo txt");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("arquivos txt", "*.txt"),
                new FileChooser.ExtensionFilter("todos os arquivos", "*.*")
        );
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            dados = new Dados(selectedFile.toString());
            lstPrinc = dados.ler();
            tbVwTimes.setItems(FXCollections.observableList(lstPrinc));

        }
    }
    public boolean ganhouCasa(Jogo jogo){
        return (jogo.getGolA() > jogo.getGolB());
    }
    
//criar lista de times e colocar num talbe view (nome, pontos, vots/empt/derrot, saldo de gol), colocar 3 times , no table view o valor tem que esta dentreo de um GET
    private List<TIme> lstTimes = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Jogo jgLinha = new Jogo("America",2,"Flamengo",1);
        if(ganhouCasa(jgLinha)){
            System.out.println("Time da casa ganhou!");        
        }
        tbVwTimes.setItems(FXCollections.observableList(lstTimes));
    }
}
