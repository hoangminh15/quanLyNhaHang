package Controller;

import DataAccessor.HopDongAccessor;
import Model.HopDong;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    TableColumn<HopDong, String> ngayLapDon;
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


    private ObservableList<HopDong> hopDongList;
    private HopDongAccessor hopDongAccessor;
    private ArrayList<String> danhSachIdHopDong;

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
        ngayLapDon.setCellValueFactory(new PropertyValueFactory<>("ngayLapDon"));
        tenKhachHang.setCellValueFactory(new PropertyValueFactory<>("tenKhachHang"));
        table.setItems(hopDongList);
    }

    //Xem hợp đồng khi được double click vào hàng cần xem
    public void xemHopDong(MouseEvent event){
        HopDong hopDongDuocChon = table.getSelectionModel().getSelectedItem();
        if(event.getClickCount() == 2 && (hopDongDuocChon != null)){
            idLB.setText(String.valueOf(hopDongDuocChon.getIdHopDong()));
            maSanhLB.setText(String.valueOf(hopDongDuocChon.getMaSanh()));
            thoiDiemLB.setText(hopDongDuocChon.getThoiDiem());
            ngayLapDonLB.setText(hopDongDuocChon.getNgayLapDon());
            ngayToChucLB.setText(hopDongDuocChon.getNgayToChuc());
            soMenuLB.setText(hopDongDuocChon.getSoMenu());
            soBanLB.setText(hopDongDuocChon.getSoBan());
            soKhachLB.setText(hopDongDuocChon.getSoKhach());
            nhanVienLB.setText(hopDongDuocChon.getNhanVien());
            tenKhachHangLB.setText(hopDongDuocChon.getTenKhachHang());
            diaChiLB.setText(hopDongDuocChon.getDiaChi());
            dienThoaiLB.setText(hopDongDuocChon.getDienThoai());
            dichVuDaChonLB.setText(hopDongDuocChon.getGiaDichVu());
        }
    }
}
