package Controller;

import DataAccessor.DichVuAccessor;
import DataAccessor.MenuAccessor;
import DataAccessor.SanhAccessor;
import Helper.DateValidator;
import Model.Menu;
import Model.Sanh;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

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
    @FXML
    Label khaiVi;
    @FXML
    Label monChinh;
    @FXML
    Label trangMieng;
    @FXML
    Label maSanh;
    @FXML
    Label sucChua;
    @FXML
    ImageView imageView;
    @FXML
    Label giaSanh;
    @FXML
    Label giaMenu;
    @FXML
    Label giaDichVu;
    @FXML
    Label thoiDiemText;
    @FXML
    Label ngayLapDon;
    @FXML
    Label tongTienAn;


    SanhAccessor sanhAccessor;
    MenuAccessor menuAccessor;
    DichVuAccessor dichVuAccessor;
    DateValidator dateValidator;
    boolean isSatOrSun;
    DecimalFormat myFormatter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set ngay lap don
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        ngayLapDon.setText(dateFormat.format(date));

        dateValidator = new DateValidator();
        //Lay thong tin sanh tu database
        sanhAccessor = new SanhAccessor();
        ArrayList<String> maSanhAL = sanhAccessor.layDanhSach();
        ObservableList<String> maSanhList = FXCollections.observableArrayList(maSanhAL);
        maSanhCB.setItems(maSanhList);
        //Lay Menu tu database
        menuAccessor = new MenuAccessor();
        ArrayList<String> menuAL = menuAccessor.layDanhSach();
        ObservableList<String> menuList = FXCollections.observableArrayList(menuAL);
        menuCB.setItems(menuList);
        //Set max width cho mon chinh tranh tràn textk
        monChinh.setMaxWidth(400);
        monChinh.setWrapText(true);

        ObservableList<String> thoiDiemList = FXCollections.observableArrayList("Sáng", "Chiều", "Tối");
        thoiDiemCB.setItems(thoiDiemList);

        //Formatter dùng để format các giá tiền cho dễ nhìn hơn (dùng dấu phẩy ngăn cách)
        myFormatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
    }

    //Listener cho menuCB
    public void xemMenu(){
        int idMenu = Integer.parseInt(menuCB.getValue());
        Menu menu = menuAccessor.layMenuData(idMenu);
        int donGiaMenu = menu.getDonGia();
        khaiVi.setText(menu.getKhaiVi());
        monChinh.setText(menu.getMonChinh());
        trangMieng.setText(menu.getTrangmieng());

        String donGiaMenuOutput = myFormatter.format(donGiaMenu);
        giaMenu.setText(donGiaMenuOutput);

        if (soBanText.getText().equals("") || soKhachMotBanText.getText().equals("")){

        } else {
            int soBan = Integer.parseInt(soBanText.getText());
            int soKhachMotBan = Integer.parseInt(soKhachMotBanText.getText());
            int tongTienAnValue = donGiaMenu *  soBan * soKhachMotBan;

            String tongTienAnOutput = myFormatter.format(tongTienAnValue);
            tongTienAn.setText(tongTienAnOutput);
        }
    }

    public void dienSoBan(){
        xemMenu();
    }

    public void dienSoKhach(){
        xemMenu();
    }

    //Listener cho sanhCB
    public void xemSanh(){
        String maSanhValue = maSanhCB.getValue();
        Sanh sanh = sanhAccessor.laySanhData(maSanhValue);
        maSanh.setText(maSanhValue);
        sucChua.setText(String.valueOf(sanh.getSucChua()));
//        imageView.setImage(new Image("View/" + sanh.getImageLink()));
        int donGiaSanh = sanh.getDonGia();
        if (thoiDiemCB.getValue() != null){
            String thoiDiem = thoiDiemCB.getValue().toLowerCase();
            if (thoiDiem.equals("sáng")){
                donGiaSanh = (int) (donGiaSanh*0.8);
            } else if(thoiDiem.equals("chiều")){
                donGiaSanh = (int) (donGiaSanh*0.85);
            } else{
                donGiaSanh = (int) (donGiaSanh*0.9);
            }
            if (isSatOrSun){
                donGiaSanh = (int) (donGiaSanh*1.1);
            }
        }

        String output = myFormatter.format(donGiaSanh);
        giaSanh.setText(output);
    }

    public void chonThoiDiem(){
        thoiDiemText.setText("Buổi " + thoiDiemCB.getValue().toLowerCase());
        if (maSanhCB.getValue() != null){
            xemSanh();
        }
    }

    public void chonNgay(){
        LocalDate localDate = thoiGianDP.getValue();
        //Kiểm tra ngày liệu có phải thứ 7 + CN
        int dayOfWeekByInt = dateValidator.findDayOfWeek(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
        if (dayOfWeekByInt == 0 || dayOfWeekByInt == 6){
            isSatOrSun = true;
        } else {
            isSatOrSun = false;
        }
        if(maSanhCB.getValue() != null){
            xemSanh();
        }
    }

    public void changeSceneDichVu(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/DichVuPage.fxml"));
        Parent dichVuViewParent = loader.load();
        Scene scene = new Scene(dichVuViewParent);
        DichVuController dichVuController = loader.getController();
        stage.setScene(scene);
    }

}
