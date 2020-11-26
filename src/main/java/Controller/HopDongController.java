package Controller;

import DataAccessor.HopDongAccessor;
import DataAccessor.MenuAccessor;
import Model.HopDong;
import Model.HopDongHolder;
import Model.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class HopDongController extends Controller implements Initializable {

    @FXML
    TableView<HopDong> table;
    @FXML
    TableColumn<HopDong, Integer> idHopDong;
    @FXML
    TableColumn<HopDong, String> ngayLapHopDong;
    @FXML
    TableColumn<HopDong, String> donGia;
    @FXML
    TableColumn<HopDong, String> tenKhachHang;
    @FXML
    Label idLB;
    @FXML
    Label maSanhLB;
    @FXML
    Label thoiDiemLB;
    @FXML
    Label ngayLapDonLB;
    @FXML
    Label ngayToChucLB;
    @FXML
    Label soMenuLB;
    @FXML
    Label soKhachLB;
    @FXML
    Label soBanLB;
    @FXML
    Label nhanVienLB;
    @FXML
    Label tenKhachHangLB;
    @FXML
    Label diaChiLB;
    @FXML
    Label dienThoaiLB;
    @FXML
    Label giaDichVuLB;
    @FXML
    Label dichVuDaChonLB;


    ObservableList<HopDong> hopDongList;
    Stage stage;
    HopDongAccessor hopDongAccessor;
    ArrayList<String> danhSachIdHopDong;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        myFormatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        hopDongAccessor = new HopDongAccessor();
        danhSachIdHopDong = hopDongAccessor.layDanhSach();
        ArrayList<HopDong> hopDongListTemp = new ArrayList<>();
        for (String idHopDong: danhSachIdHopDong){
            hopDongListTemp.add(hopDongAccessor.layHopDongData(Integer.parseInt(idHopDong)));
        }
        hopDongList = FXCollections.observableList(hopDongListTemp);
        idHopDong.setCellValueFactory(new PropertyValueFactory<>("idHopDong"));
        ngayLapHopDong.setCellValueFactory(new PropertyValueFactory<>("ngayLapHopDong"));
        tenKhachHang.setCellValueFactory(new PropertyValueFactory<>("tenKhachHang"));
        donGia.setCellValueFactory(new PropertyValueFactory<>("donGia"));
        table.setItems(hopDongList);

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

    public void xemHopDong(MouseEvent event){
        HopDong hopDongDuocChon = table.getSelectionModel().getSelectedItem();
        if(event.getClickCount() == 2 && (hopDongDuocChon != null)){
            idLB.setText(String.valueOf(hopDongDuocChon.getId()));
            maSanhLB.setText(String.valueOf(hopDongDuocChon.getMaSanh()));
            thoiDiemLB.setText(hopDongDuocChon.getThoiDiem());
            ngayLapDonLB.setText(hopDongDuocChon.getNgayLapDon());
            ngayToChucLB.setText(hopDongDuocChon.getNgayToChuc());
            soMenuLB.setText(hopDongDuocChon.getSoMenu());
            soBanLB.setText(hopDongDuocChon.getSoMenu());
            soKhachLB.setText(hopDongDuocChon.getSoKhach());
            nhanVienLB.setText(hopDongDuocChon.getNhanVien());
            tenKhachHangLB.setText(hopDongDuocChon.getTenKhachHang());
            diaChiLB.setText(hopDongDuocChon.getDiaChi());
            dienThoaiLB.setText(hopDongDuocChon.getDienThoai());
            giaDichVuLB.setText(hopDongDuocChon.getGiaDichVu());
            dichVuDaChonLB.setText(hopDongDuocChon.getDichVuDaChon());

        }
    }
}
