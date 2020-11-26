package Controller;

import DataAccessor.DichVuAccessor;
import Model.DichVu;
import Model.HopDong;
import Model.HopDongHolder;
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

public class DichVuController extends Controller implements Initializable {

    @FXML
    TableView<DichVu> table;
    @FXML
    TableColumn<DichVu, Integer> idDichVu;
    @FXML
    TableColumn<DichVu, String> tenDichVu;
    @FXML
    TableColumn<DichVu, Integer> donGia;
    @FXML
    TextField idThem;
    @FXML
    TextField tenDichVuThem;
    @FXML
    TextField donGiaThem;
    @FXML
    Label dichVuDaChonLabel;
    @FXML
    Label tongTienLabel;

    private ObservableList<DichVu> dichVuList;
    private DichVuAccessor dichVuAccessor;
    private ArrayList<String> danhSachIdDichVu;
    private long tongTien = 0;
    private Stage stage;

    //Giống với constructor ; method này được chạy khi controller được khởi tạo
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        myFormatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        dichVuAccessor = new DichVuAccessor();
        danhSachIdDichVu = dichVuAccessor.layDanhSach();
        ArrayList<DichVu> dichVuListTemp = new ArrayList<>();
        //Loop qua danh sach va lay cac object ve cho vao mot list object DichVu
        for (String idDichVu : danhSachIdDichVu){
            dichVuListTemp.add(dichVuAccessor.layDichVuData(Integer.parseInt(idDichVu)));
        }
        dichVuList = FXCollections.observableList(dichVuListTemp);
        idDichVu.setCellValueFactory(new PropertyValueFactory<>("idDichVu"));
        tenDichVu.setCellValueFactory(new PropertyValueFactory<>("tenDichVu"));
        donGia.setCellValueFactory(new PropertyValueFactory<>("donGia"));
        //Test
        table.setItems(dichVuList);
    }

    //Chọn để xem dịch vụ
    public void chonHuyDichVu(MouseEvent event){
        DichVu dichVuDuocChon = table.getSelectionModel().getSelectedItem();
        if(event.getClickCount() == 2 && (dichVuDuocChon != null)){
            int id = dichVuDuocChon.getIdDichVu();
            String textHienTai = dichVuDaChonLabel.getText();
            if (!textHienTai.contains(" " + id)){
                dichVuDaChonLabel.setText(textHienTai.concat("  " + dichVuDuocChon.getIdDichVu()));
                tongTien += dichVuDuocChon.getDonGia();
                String output = myFormatter.format(tongTien);
                tongTienLabel.setText(output);
            }
        }
    }

    // set dịch vụ đã chọn khi truyền data từ home trở lại trang dịch vụ, giúp tránh mất data khi chuyển scene
    public void setDichVuDaChon(String dichVuDaChon){
        dichVuDaChonLabel.setText(dichVuDaChon);
    }

    //set tổng chi phí dịch vụ vào biến tongTien để tiện tính toán
    public void setTongDichVu(String giaDichVu){
        tongTienLabel.setText(giaDichVu);
        String tongTienString = giaDichVu.replaceAll(",", "").trim();
        tongTien = Integer.parseInt(tongTienString);
    }

    //thêm dịch vụ vào danh sách dịch vụ, đồng thời thêm vào database
    public void them() throws SQLException {
        try{
            Integer.parseInt(idThem.getText());
            Integer.parseInt((donGiaThem.getText()));
        } catch( NumberFormatException e){
            popUpInvalidAlert();
            return;
        }
        danhSachIdDichVu = dichVuAccessor.layDanhSach();
        if (danhSachIdDichVu.contains(idThem.getText())){
            popUpDuplicatedIDAlert();
            return;
        }
        if(idThem.getText().equals("") || tenDichVuThem.getText().equals("") || donGiaThem.getText().equals("")){
            popUpMissingFieldAlert();
            return;
        } else {
            DichVu dichVuData = new DichVu();
            dichVuData.setIdDichVu(Integer.parseInt(idThem.getText()));
            dichVuData.setDonGia(Integer.parseInt(donGiaThem.getText()));
            dichVuData.setTenDichVu(tenDichVuThem.getText());
            dichVuAccessor.them(dichVuData);
            dichVuList.add(dichVuData);
        }
    }

    //Xóa dịch vụ khỏi danh sách
    public void xoa(){
        DichVu dichVuBiXoa = table.getSelectionModel().getSelectedItem();
        dichVuList.remove(dichVuBiXoa);
        dichVuAccessor.xoa(dichVuBiXoa);
    }

    //Chọn lại dịch vụ
    public void chonLai(){
        tongTienLabel.setText("");
        dichVuDaChonLabel.setText("");
        tongTien = 0;
    }

    //Quay trở lại home, chỉnh sửa data của hopDong nếu người dùng đã chọn dịch vụ
    public void troLai(ActionEvent event) throws IOException {
        holder = HopDongHolder.getInstance();
        HopDong hopDong = holder.getHopDong();
        stage = retrieveStage(event);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/Home.fxml"));
        Parent homeParent = loader.load();
        Scene scene = new Scene(homeParent);
        HomeController homeController = loader.getController();

        //Pass tra data:
        hopDong.setDichVuDaChon(dichVuDaChonLabel.getText());
        homeController.setBackHopDong(hopDong);
        homeController.setGiaDichVu(String.valueOf(tongTien));
        stage.setScene(scene);
    }
}
