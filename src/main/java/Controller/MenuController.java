package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController extends Controller{

    @FXML
    TableView<Menu> table;
    @FXML
    TableColumn<Menu, Integer> idMenu;
    @FXML
    TableColumn<Menu, Integer> donGia;

    ObservableList<Menu> menuList;
    Stage stage;

//    public void troLai(ActionEvent event) throws IOException{
//        stage = retrieveStage(event);
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/View/Home.fxml"));
//        Parent homeParent = loader.load();
//        Scene scene = new Scene(homeParent);
//        stage.setScene(scene);
//    }

}
