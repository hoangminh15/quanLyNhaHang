package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    ComboBox<String> maSanhCB;
    @FXML
    DatePicker thoiGianDP;
    @FXML
    ComboBox<String> thoiDiemCB;
    @FXML
    TextField soBanText;
    @FXML
    TextField soKhachMotBanText;
    @FXML
    ComboBox<String> menuCB;
    @FXML
    Button xemDichVuButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Lay thong tin sanh tu database
        ObservableList<String> maSanhList = FXCollections.observableArrayList("");
        ObservableList<String> thoiDiemList = FXCollections.observableArrayList("Sáng", "Chiều", "Tối");
        thoiDiemCB.setItems(thoiDiemList);
        //Lay Menu tu database
        ObservableList<String> menuList = FXCollections.observableArrayList("Menu số 1", "Menu số 2", "Menu số 3", "Menu số 4");
        menuCB.setItems(menuList);

    }
}
