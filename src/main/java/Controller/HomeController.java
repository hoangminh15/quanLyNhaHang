package Controller;

import DataAccessor.HopDongAccessor;
import DataAccessor.MenuAccessor;
import DataAccessor.SanhAccessor;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    @FXML
    Label tongThanhTienLB;
    @FXML
    Label sanhThanhTienLB;
    @FXML
    Label menuThanhTienLB;
    @FXML
    Label dichVuThanhTienLB;

    private SanhAccessor sanhAccessor;
    private MenuAccessor menuAccessor;
    private HopDongAccessor hopDongAccessor;
    private DateValidator dateValidator;
    private boolean isSatOrSun;
    private HopDongHolder holder;
    DateFormat dateFormat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set ngay lap don
        dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
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
        //Set max width cho mon chinh tranh tràn text
        monChinh.setMaxWidth(400);
        monChinh.setWrapText(true);
        dichVuDaChon.setMaxWidth(300);
        dichVuDaChon.setWrapText(true);

        ObservableList<String> thoiDiemList = FXCollections.observableArrayList("Sáng", "Chiều", "Tối");
        thoiDiemCB.setItems(thoiDiemList);

        //Formatter dùng để format các giá tiền cho dễ nhìn hơn (dùng dấu phẩy ngăn cách)
        myFormatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
    }

    //Listener cho menuCB
    public void xemMenu() {
        if(menuCB.getSelectionModel().isEmpty()) return;
        int idMenu = Integer.parseInt(menuCB.getValue());
        Menu menu = menuAccessor.layMenuData(idMenu);
        int donGiaMenu = menu.getDonGia();
        khaiVi.setText(menu.getKhaiVi());
        monChinh.setText(menu.getMonChinh());
        trangMieng.setText(menu.getTrangmieng());
        soMenu.setText(String.valueOf(menu.getIdMenu()));
        String donGiaMenuOutput = myFormatter.format(donGiaMenu);
        giaMenu.setText(donGiaMenuOutput);

        if (!soBanText.getText().equals("") && !soKhachMotBanText.getText().equals("")) {
            int soBan = Integer.parseInt(soBanText.getText());
            int soKhachMotBan = Integer.parseInt(soKhachMotBanText.getText());
            int tongTienAnValue = donGiaMenu * soBan * soKhachMotBan;

            String tongTienAnOutput = myFormatter.format(tongTienAnValue);
            tongTienAn.setText(tongTienAnOutput);
        }
    }

    //gọi tới xemMenu() khi số bàn được điền
    public void dienSoBan() {
        if(!soBanText.getText().isBlank()){
            xemMenu();
        }

        //check so ban nhap vao lieu co vuot qua suc chua toi da
        if(sucChua.getText().equals("")) {
            Alert sanhIsNotSelectedAlert = new Alert(Alert.AlertType.INFORMATION);
            sanhIsNotSelectedAlert.setTitle("Chú ý");
            sanhIsNotSelectedAlert.setHeaderText("Bạn cần chọn sảnh trước");
            sanhIsNotSelectedAlert.setContentText("Vui lòng chọn sảnh");
            sanhIsNotSelectedAlert.show();
            return;
        }
        if(soBanText.getText().isBlank()){
            return;
        } else if(Integer.parseInt(soBanText.getText()) > Integer.parseInt(sucChua.getText())){
            Alert outOfLimitAlert = new Alert(Alert.AlertType.INFORMATION);
            outOfLimitAlert.setTitle("Chú ý");
            outOfLimitAlert.setHeaderText("Sức chứa tối đa của mỗi sảnh là " + sucChua.getText() + " bàn");
            outOfLimitAlert.setContentText("Vui lòng nhập lại");
            outOfLimitAlert.show();
            soBanText.setText("");
            return;
        }

    }

    //gọi tới xemMenu() khi số khách được điền
    public void dienSoKhach() {
        if(!soKhachMotBanText.getText().isBlank()){
            xemMenu();
        }
    }

    //Listener cho sanhCB
    public void xemSanh() {
        if(maSanhCB.getSelectionModel().isEmpty()) return;
        String maSanhValue = maSanhCB.getValue();
        Sanh sanh = sanhAccessor.laySanhData(maSanhValue);
        maSanh.setText(maSanhValue);
        sucChua.setText(String.valueOf(sanh.getSucChua()));
        int donGiaSanh = sanh.getDonGia();
        if (thoiDiemCB.getValue() != null) {
            String thoiDiem = thoiDiemCB.getValue().toLowerCase();
            if (thoiDiem.equals("sáng")) {
                donGiaSanh = (int) (donGiaSanh * 0.8);
            } else if (thoiDiem.equals("chiều")) {
                donGiaSanh = (int) (donGiaSanh * 0.85);
            }
            if (isSatOrSun) {
                donGiaSanh = (int) (donGiaSanh * 1.1);
            }
        }

        String output = myFormatter.format(donGiaSanh);
        giaSanh.setText(output);
    }

    //Listener cho thoiDiemCB
    public void chonThoiDiem() {
        if(thoiDiemCB.getSelectionModel().isEmpty()) return;
        String output = thoiDiemCB.getValue().toLowerCase();
        if (output.equals("sáng")) {
            output += " (8h - 12h)";
        } else if (output.equals("chiều")) {
            output += " (13h - 17h)";
        } else if (output.equals("tối")){
            output += " (18h - 22h)";
        }
        thoiDiem.setText(output);
        if (!maSanhCB.getSelectionModel().isEmpty()) {
            xemSanh();
        }
    }

    //Listener cho DatePickerDP
    public void chonNgay() {
        LocalDate localDate = thoiGianDP.getValue();
        //Kiểm tra ngày liệu có phải thứ 7 + CN
        int dayOfWeekByInt = dateValidator.findDayOfWeek(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
        if (dayOfWeekByInt == 0 || dayOfWeekByInt == 6) {
            isSatOrSun = true;
        } else {
            isSatOrSun = false;
        }
        if (maSanhCB.getValue() != "") {
            xemSanh();
        }
        ngayToChuc.setText(thoiGianDP.getValue().toString());
    }

    // Đổi scene sang trang dịch vụ
    public void changeSceneDichVu(ActionEvent event) throws IOException {
        Stage stage = retrieveStage(event);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/DichVu.fxml"));
        Parent dichVuViewParent = loader.load();
        Scene scene = new Scene(dichVuViewParent);

        //Lưu data từ home sang dịch vụ
        holdDataBetweenPage();

        DichVuController dichVuController = loader.getController();
        if (!dichVuDaChon.getText().equals("") && !dichVuDaChon.getText().equals("")) {
            dichVuController.setDichVuDaChon(dichVuDaChon.getText());
            dichVuController.setTongDichVu(giaDichVu.getText().trim());
        }
        stage.setScene(scene);
    }

    //Chuyển scene sang home
    public void changeSceneMenu(ActionEvent event) throws IOException {
        Stage stage = retrieveStage(event);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/Menu.fxml"));
        Parent menuViewParent = loader.load();
        Scene scene = new Scene(menuViewParent);
        holdDataBetweenPage();
        stage.setScene(scene);
    }

    //Chuyển scene sang "sảnh"
    public void changeSceneSanh(ActionEvent event) throws IOException {
        Stage stage = retrieveStage(event);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/Sanh.fxml"));
        Parent sanhViewParent = loader.load();
        Scene scene = new Scene(sanhViewParent);
        stage.setScene(scene);
    }

    //Chuyển scene sang "xem hợp đồng"
    public void xemHopDong(ActionEvent event) throws IOException {
        Stage stage = retrieveStage(event);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/HopDong.fxml"));
        Parent hopDongViewParent = loader.load();
        Scene scene = new Scene(hopDongViewParent);
        stage.setScene(scene);
    }

    //set giá dịch vụ (phục vụ cho method khác)
    public void setGiaDichVu(String tongGiaDichVu) {
        String output = myFormatter.format(Integer.parseInt(tongGiaDichVu));
        giaDichVu.setText(output);
    }


    //Set lại data toàn bộ home page khi chuyển trở lại home page
    public void setBackHopDong(HopDong hopDong) {
        if (hopDong == null) return;
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

    //Thành tiền
    public void thanhTien() {
        String giaDichVuThanhTien;
        int giaDichVuValue;
        if (giaDichVu.getText().equals("")) {
            giaDichVuThanhTien = "0";
            giaDichVuValue = 0;
        } else {
            giaDichVuThanhTien = giaDichVu.getText();
            giaDichVuValue = formatBackToInt(giaDichVuThanhTien);
        }

        String giaSanhThanhTien = giaSanh.getText();
        String giaMenuThanhTien = tongTienAn.getText();
        if (giaSanhThanhTien.equals("") || giaMenuThanhTien.equals("")) {
            Alert missingFieldAlert = new Alert(Alert.AlertType.INFORMATION);
            missingFieldAlert.setTitle("Chú ý");
            missingFieldAlert.setHeaderText("Các trường thông tin chưa đầy đủ");
            missingFieldAlert.setContentText("Vui lòng xem lại");
            missingFieldAlert.show();
            return;
        }

        sanhThanhTienLB.setText(giaSanhThanhTien);
        menuThanhTienLB.setText(giaMenuThanhTien);
        dichVuThanhTienLB.setText(giaDichVuThanhTien);

        int giaSanhValue = formatBackToInt(giaSanhThanhTien);
        int giaMenuValue = formatBackToInt(giaMenuThanhTien);

        int tongThanhToan = giaSanhValue + giaMenuValue + giaDichVuValue;
        tongThanhTienLB.setText(myFormatter.format(tongThanhToan));

    }

    //In hợp đồng ra console , đồng thời lưu vào database
    public void inHopDong() {
        //Save hop dong vao database
        //Luu data vao data object HopDong
        thanhTien();
//        if (ngayLapDon.getText().equals("") || nhanVien.getText().equals("") || tenKhachHang.getText().equals("") || diaChi.getText().equals("") || dienThoai.getText().equals("") || ngayToChuc.getText().equals("") || tongThanhTienLB.getText().equals("") || maSanh.getText().equals("") || sucChua.getText().equals("") || giaSanh.getText().equals("") || soMenu.getText().equals("") || giaMenu.getText().equals("") || khaiVi.getText().equals("") || monChinh.getText().equals("") || trangMieng.getText().equals("") || tongTienAn.getText().equals("")) {
//            popUpMissingFieldAlert();
//            return;
//        }
        hopDongAccessor = new HopDongAccessor();
        holdDataBetweenPage();
        HopDong hopDongDeSave = holder.getHopDong();
        try {
            hopDongAccessor.themHopDong(hopDongDeSave);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //In hop dong ra console
        System.out.println("HỢP ĐỒNG ĐẶT TIỆC");
        System.out.println("Hợp đồng ngày " + ngayLapDon.getText());
        System.out.println("Nhân viên lập hợp đồng: " + nhanVien.getText());
        System.out.println("Tên khách hàng: " + tenKhachHang.getText());
        System.out.println("Địa chỉ: " + diaChi.getText());
        System.out.println("Điện thoại: " + dienThoai.getText());
        System.out.println("Ngày tổ chức tiệc: " + ngayToChuc.getText());
        System.out.println("TỔNG THANH TOÁN: " + tongThanhTienLB.getText() + "\n");

        System.out.println("THÔNG TIN SẢNH ");
        System.out.println("Mã sảnh: " + maSanh.getText());
        System.out.println("Sức chứa: " + sucChua.getText());
        System.out.println("Đơn giá sảnh: " + giaSanh.getText() + "\n");

        System.out.println("THÔNG TIN MENU ");
        System.out.println("Menu số: " + soMenu.getText());
        System.out.println("Số lượng khách: " + Integer.parseInt(soBanText.getText()) * Integer.parseInt(soKhachMotBanText.getText()));
        System.out.println("Đơn giá: " + giaMenu.getText());
        System.out.println("Khai vị: " + khaiVi.getText());
        System.out.println("Món chính: " + monChinh.getText());
        System.out.println("Tráng miệng: " + trangMieng.getText());
        System.out.println("Tổng tiền ăn: " + tongTienAn.getText() + "\n");

        System.out.println("THÔNG TIN DỊCH VỤ: ");
        System.out.println("Dịch vụ đã chọn: " + dichVuDaChon.getText());
        System.out.println("Tổng giá dịch vụ: " + giaDichVu.getText());
        //Reset tất cả các trường trong hợp đồng
        resetHopDong();
    }

    //Được gọi ở trong method thanhTien();
    public void resetHopDong() {
        thoiGianDP.getEditor().clear();
        thoiDiemCB.setValue(null);
        maSanhCB.setValue(null);
        menuCB.setValue(null);
        soBanText.setText(null);
        soKhachMotBanText.setText(null);
        thoiDiem.setText("");
        ngayToChuc.setText("");
        thoiDiem.setText("");
        maSanh.setText("");
        sucChua.setText("");
        giaSanh.setText("");
        khaiVi.setText("");
        monChinh.setText("");
        trangMieng.setText("");
        giaMenu.setText("");
        tongTienAn.setText("");
        dichVuDaChon.setText("");
        giaDichVu.setText("");
        nhanVien.setText("");
        tenKhachHang.setText("");
        diaChi.setText("");
        dienThoai.setText("");
        Date date = new Date();
        ngayLapDon.setText(dateFormat.format(date));
        sanhThanhTienLB.setText("");
        menuThanhTienLB.setText("");
        dichVuThanhTienLB.setText("");
        tongThanhTienLB.setText("");
    }

    //Format lại giá tiền dạng dễ đọc quay trở lại kiểu Integer để phục vụ tính toán
    public int formatBackToInt(String s) {
        String[] stringArray = s.split(",");
        String output = "";
        for (String e : stringArray) {
            output += e;
        }
        int outputInt = Integer.parseInt(output.trim());
        return outputInt;
    }

    //Phục vụ chuyển data giữa các page
    public void holdDataBetweenPage() {
        holder = HopDongHolder.getInstance();
        holder.setHopDong(new HopDong(ngayToChuc.getText(), thoiDiem.getText(), maSanh.getText(), soMenu.getText(), soBanText.getText(), soKhachMotBanText.getText(), nhanVien.getText(), tenKhachHang.getText(), diaChi.getText(), dienThoai.getText(), ngayLapDon.getText(), giaDichVu.getText(), dichVuDaChon.getText()));
    }
}
