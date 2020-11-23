package Controller;

import DataAccessor.DichVuAccessor;
import Model.DichVu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DichVuController implements Initializable {

    @FXML
    TableView<DichVu> table;
    @FXML
    TableColumn<DichVu, Integer> idDichVu;
    @FXML
    TableColumn<DichVu, String> tenDichVu;
    @FXML
    TableColumn<DichVu, Integer> donGia;

    private ObservableList<DichVu> dichVuList;
    DichVuAccessor dichVuAccessor;
    ArrayList<DichVu> dichVuListTemp;
    ArrayList<String> danhSachIdDichVu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dichVuAccessor = new DichVuAccessor();
        danhSachIdDichVu = dichVuAccessor.layDanhSach();
        dichVuListTemp = new ArrayList<DichVu>();
        //Loop qua danh sach va lay cac object ve cho vao mot list object DichVu
        for (String idDichVu : danhSachIdDichVu){
            dichVuListTemp.add(dichVuAccessor.layDichVuData(Integer.parseInt(idDichVu)));
        }
        dichVuList = FXCollections.observableList(dichVuListTemp);
        idDichVu.setCellValueFactory(new PropertyValueFactory<DichVu, Integer>("idDichVu"));
        tenDichVu.setCellValueFactory(new PropertyValueFactory<DichVu, String>("tenDichVu"));
        donGia.setCellValueFactory(new PropertyValueFactory<DichVu, Integer>("donGia"));
        table.setItems(dichVuList);

    }
}
