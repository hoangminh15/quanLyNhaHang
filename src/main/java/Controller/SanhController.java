package Controller;

import DataAccessor.SanhAccessor;
import Model.HopDong;
import Model.HopDongHolder;
import Model.Sanh;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class SanhController extends Controller implements Initializable {

    @FXML
    TableView<Sanh> table;
    @FXML
    TableColumn<Sanh, Integer> idSanh;
    @FXML
    TableColumn<Sanh, Integer> sucChua;
    @FXML
    TableColumn<Sanh, String> maSanh;
    @FXML
    TableColumn<Sanh, Integer> donGia;
    @FXML
    TextField idText;
    @FXML
    TextField sucChuaText;
    @FXML
    TextField donGiaText;
    @FXML
    Label idLB;
    @FXML
    Label maSanhLB;
    @FXML
    Label sucChuaLB;
    @FXML
    Label donGiaLB;

    private ObservableList<Sanh> sanhList;
    private Stage stage;
    private SanhAccessor sanhAccessor;
    private ArrayList<String> danhSachMaSanh;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myFormatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        sanhAccessor = new SanhAccessor();
        danhSachMaSanh = sanhAccessor.layDanhSach();
        ArrayList<Sanh> sanhListTemp = new ArrayList<>();
        for (String maSanh: danhSachMaSanh){
            sanhListTemp.add(sanhAccessor.laySanhData(maSanh));
        }
        sanhList = FXCollections.observableList(sanhListTemp);
        maSanh.setCellValueFactory(new PropertyValueFactory<>("maSanh"));
        donGia.setCellValueFactory(new PropertyValueFactory<>("donGia"));
        sucChua.setCellValueFactory(new PropertyValueFactory<>("sucChua"));
        idSanh.setCellValueFactory(new PropertyValueFactory<>("idSanh"));
        table.setItems(sanhList);
    }

    public void them() throws SQLException {

        try{
            Integer.parseInt(idText.getText());
            Integer.parseInt((donGiaText.getText()));
        } catch( NumberFormatException e){
            popUpInvalidAlert();
            return;
        }

        //Check sức chứa tối đa phải <= 35
        if(Integer.parseInt(sucChuaText.getText()) > 35){
            Alert outOfLimitAlert = new Alert(Alert.AlertType.INFORMATION);
            outOfLimitAlert.setTitle("Chú ý");
            outOfLimitAlert.setHeaderText("Sức chứa tối đa của mỗi sảnh là 35 bàn");
            outOfLimitAlert.setContentText("Vui lòng nhập lại");
            outOfLimitAlert.show();
            return;
        }

        if(idText.getText().equals("") || sucChuaText.getText().equals("") || donGiaText.getText().equals("") || donGiaText.getText().equals("")){
            popUpMissingFieldAlert();
            return;
        } else {
            Sanh sanhData = new Sanh();
            int id = Integer.parseInt(idText.getText());
            String maSanhToBeInserted = "";
            if (id < 10) {
                maSanhToBeInserted += "S00" + id;
            } else if (id < 100){
                maSanhToBeInserted = "S0" + id;
            } else if (id < 1000){
                maSanhToBeInserted = "S" + id;
            }
            danhSachMaSanh = sanhAccessor.layDanhSach();
            if (danhSachMaSanh.contains(maSanhToBeInserted)){
                popUpDuplicatedIDAlert();
                return;
            }
            sanhData.setIdSanh(id);
            sanhData.setDonGia(Integer.parseInt(donGiaText.getText()));
            sanhData.setMaSanh(maSanhToBeInserted);
            sanhData.setSucChua(Integer.parseInt(sucChuaText.getText()));
            sanhAccessor.them(sanhData);
            sanhList.add(sanhData);
            idText.setText("");
            donGiaText.setText("");
            sucChuaText.setText("");
        }
    }

    public void xoa(){
        Sanh sanhBiXoa = table.getSelectionModel().getSelectedItem();
        if (sanhBiXoa == null) {
            Alert nonSelectedAlert = new Alert(Alert.AlertType.INFORMATION);
            nonSelectedAlert.setTitle("Chú ý!");
            nonSelectedAlert.setHeaderText("Bạn cần chọn sảnh để xóa ");
            return;
        }
        sanhList.remove(sanhBiXoa);
        sanhAccessor.xoa(sanhBiXoa);
    }

    //Xem sảnh khi được click vào hàng cần xem
    public void xemSanh(MouseEvent event){
        Sanh sanhDuocChon = table.getSelectionModel().getSelectedItem();
        if(event.getClickCount() == 2 && (sanhDuocChon != null)){
            idLB.setText(String.valueOf(sanhDuocChon.getIdSanh()));
            donGiaLB.setText(String.valueOf(sanhDuocChon.getDonGia()));
            sucChuaLB.setText(String.valueOf(sanhDuocChon.getSucChua()));
            maSanhLB.setText(sanhDuocChon.getMaSanh());
        }
    }
}
