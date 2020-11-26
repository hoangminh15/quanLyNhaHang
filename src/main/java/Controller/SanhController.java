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
    TextField maSanhText;
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

    ObservableList<Sanh> sanhList;
    Stage stage;
    SanhAccessor sanhAccessor;
    ArrayList<String> danhSachMaSanh;

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

    public void troLai(ActionEvent event) throws IOException {
        HopDongHolder holder = HopDongHolder.getInstance();
        HopDong hopDong = holder.getHopDong();

        stage = retrieveStage(event);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/Home.fxml"));
        Parent homeParent = loader.load();
        Scene scene = new Scene(homeParent);
        HomeController homeController = loader.getController();
        homeController.setBackHopDong(hopDong);
        stage.setScene(scene);
    }

    public void them() throws SQLException {

        try{
            Integer.parseInt(idText.getText());
            Integer.parseInt((donGiaText.getText()));
        } catch( NumberFormatException e){
            popUpInvalidAlert();
            return;
        }
        danhSachMaSanh = sanhAccessor.layDanhSach();
        if (danhSachMaSanh.contains(idText.getText())){
            popUpDuplicatedIDAlert();
            return;
        }
        if(idText.getText().equals("") || maSanhText.getText().equals("") || sucChuaText.getText().equals("") || donGiaText.getText().equals("") || donGiaText.getText().equals("")){
            popUpMissingFieldAlert();
            return;
        } else {
            Sanh sanhData = new Sanh();
            sanhData.setIdSanh(Integer.parseInt(idText.getText()));
            sanhData.setDonGia(Integer.parseInt(donGiaText.getText()));
            sanhData.setMaSanh(maSanhText.getText());
            sanhData.setSucChua(Integer.parseInt(sucChuaText.getText()));
            sanhAccessor.them(sanhData);
            sanhList.add(sanhData);
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
