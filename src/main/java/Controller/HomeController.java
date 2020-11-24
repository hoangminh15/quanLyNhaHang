package Controller;

import DataAccessor.DichVuAccessor;
import DataAccessor.MenuAccessor;
import DataAccessor.SanhAccessor;
import Helper.DateFormatter;
import Helper.DateValidator;
import Model.HopDong;
import Model.HopDongHolder;
import Model.Menu;
import Model.Sanh;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class HomeController extends Controller implements Initializable {

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
    Label thoiDiem;
    @FXML
    Label ngayLapDon;
    @FXML
    Label tongTienAn;
    @FXML
    Label dichVuDaChon;
    @FXML
    Label dichVuDaChon2;
    @FXML
    Label dichVuDaChon3;
    @FXML
    Label soMenu;
    @FXML
    Label ngayToChuc;
    @FXML
    TextField nhanVien;
    @FXML
    TextField tenKhachHang;
    @FXML
    TextField diaChi;
    @FXML
    TextField dienThoai;

    SanhAccessor sanhAccessor;
    MenuAccessor menuAccessor;
    DichVuAccessor dichVuAccessor;
    DateValidator dateValidator;
    boolean isSatOrSun;
    DecimalFormat myFormatter;
    HopDongHolder holder;

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
        dichVuDaChon.setMaxWidth(300);
        dichVuDaChon.setWrapText(true);
        dichVuDaChon2.setMaxWidth(300);
        dichVuDaChon2.setWrapText(true);
        dichVuDaChon3.setMaxWidth(300);
        dichVuDaChon3.setWrapText(true);

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
        soMenu.setText(String.valueOf(menu.getIdMenu()));

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
                donGiaSanh = (int) (donGiaSanh*1);
            }
            if (isSatOrSun){
                donGiaSanh = (int) (donGiaSanh*1.1);
            }
        }

        String output = myFormatter.format(donGiaSanh);
        giaSanh.setText(output);
    }

    public void chonThoiDiem(){
        thoiDiem.setText(thoiDiemCB.getValue().toLowerCase());
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
        ngayToChuc.setText(thoiGianDP.getValue().toString());
    }

    public void changeSceneDichVu(ActionEvent event) throws IOException {
        Stage stage = retrieveStage(event);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/DichVu.fxml"));
        Parent dichVuViewParent = loader.load();
        Scene scene = new Scene(dichVuViewParent);

        //Lam viec voi data tu Home sang DichVu
        HopDongHolder holder = HopDongHolder.getInstance();
        holder.setHopDong(new HopDong(ngayToChuc.getText(), thoiDiem.getText(), maSanh.getText(), soMenu.getText(), soBanText.getText(), soKhachMotBanText.getText(), nhanVien.getText(), tenKhachHang.getText(), diaChi.getText(), dienThoai.getText(), ngayLapDon.getText(), giaDichVu.getText(), dichVuDaChon.getText()));

        DichVuController dichVuController = loader.getController();
        if(dichVuDaChon.getText().equals("") || dichVuDaChon.getText().equals("")){

        } else {
            dichVuController.setDichVuDaChon(dichVuDaChon.getText());
            dichVuController.setTongDichVu(giaDichVu.getText().trim());
        }
        stage.setScene(scene);
    }

    public void changeSceneMenu(ActionEvent event) throws IOException{
        Stage stage = retrieveStage(event);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/Menu.fxml"));
        Parent menuViewParent = loader.load();
        Scene scene = new Scene(menuViewParent);
        stage.setScene(scene);
    }

    public void changeSceneSanh(ActionEvent event) throws IOException{
        Stage stage = retrieveStage(event);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/Sanh.fxml"));
        Parent sanhViewParent = loader.load();
        Scene scene = new Scene(sanhViewParent);
        stage.setScene(scene);
    }

    public void setGiaDichVu(String tongGiaDichVu){
        String output = myFormatter.format(Integer.parseInt(tongGiaDichVu));
        giaDichVu.setText(output);
    }


    public void setBackHopDong(HopDong hopDong){
        if (hopDong.getNgayToChuc().equals("")) return;
        thoiGianDP.setValue(LocalDate.parse(hopDong.getNgayToChuc()));
        chonNgay();
        thoiDiemCB.setValue(hopDong.getThoiDiem());
        chonThoiDiem();
        maSanhCB.setValue(hopDong.getMaSanh());
        xemSanh();
        menuCB.setValue(hopDong.getSoMenu());
        soBanText.setText(hopDong.getSoBan());
        soKhachMotBanText.setText(hopDong.getSoKhach());
        xemMenu();
        nhanVien.setText(hopDong.getNhanVien());
        tenKhachHang.setText(hopDong.getTenKhachHang());
        diaChi.setText(hopDong.getDiaChi());
        dienThoai.setText(hopDong.getDienThoai());
        ngayLapDon.setText(hopDong.getNgayLapDon());
        giaDichVu.setText(hopDong.getGiaDichVu());
        dichVuDaChon.setText(hopDong.getDichVuDaChon());
    }
}
