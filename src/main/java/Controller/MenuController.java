package Controller;

import DataAccessor.MenuAccessor;
import Model.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class MenuController extends Controller implements Initializable {

    @FXML
    TableView<Menu> table;
    @FXML
    TableColumn<Menu, Integer> idMenu;
    @FXML
    TableColumn<Menu, Integer> donGia;
    @FXML
    TextField khaiViText;
    @FXML
    TextField donGiaText;
    @FXML
    TextField idText;
    @FXML
    TextArea monChinhText;
    @FXML
    TextField trangMiengText;
    @FXML
    Label idLB;
    @FXML
    Label donGiaLB;
    @FXML
    Label khaiViLB;
    @FXML
    Label monChinhLB;
    @FXML
    Label trangMiengLB;

    private ObservableList<Menu> menuList;
    private MenuAccessor menuAccessor;
    private ArrayList<String> danhSachIDMenu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myFormatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        menuAccessor = new MenuAccessor();
        danhSachIDMenu = menuAccessor.layDanhSach();
        ArrayList<Menu> menuListTemp = new ArrayList<>();
        for (String idMenu: danhSachIDMenu){
            menuListTemp.add(menuAccessor.layMenuData(Integer.parseInt(idMenu)));
        }
        menuList = FXCollections.observableList(menuListTemp);
        idMenu.setCellValueFactory(new PropertyValueFactory<>("idMenu"));
        donGia.setCellValueFactory(new PropertyValueFactory<>("donGia"));
        table.setItems(menuList);

        monChinhLB.setMaxWidth(300);
        monChinhLB.setWrapText(true);
        trangMiengLB.setMaxWidth(300);
        trangMiengLB.setWrapText(true);
    }

    public void them() throws SQLException {
        try{
            Integer.parseInt(idText.getText());
            Integer.parseInt((donGiaText.getText()));
        } catch( NumberFormatException e){
            popUpInvalidAlert();
            return;
        }
        danhSachIDMenu = menuAccessor.layDanhSach();
        if (danhSachIDMenu.contains(idText.getText())){
            popUpDuplicatedIDAlert();
            return;
        }
        if(idText.getText().equals("") || khaiViText.getText().equals("") || monChinhText.getText().equals("") || trangMiengText.getText().equals("") || donGiaText.getText().equals("")){
            popUpMissingFieldAlert();
            return;
        } else {
            Menu MenuData = new Menu();
            MenuData.setIdMenu(Integer.parseInt(idText.getText()));
            MenuData.setDonGia(Integer.parseInt(donGiaText.getText()));
            MenuData.setKhaiVi(khaiViText.getText());
            MenuData.setMonChinh(monChinhText.getText());
            MenuData.setTrangmieng(trangMiengText.getText());
            menuAccessor.them(MenuData);
            menuList.add(MenuData);
        }
    }

    public void xoa(){
        Menu menuBiXoa = table.getSelectionModel().getSelectedItem();
        if (menuBiXoa == null) {
            Alert nonSelectedAlert = new Alert(Alert.AlertType.INFORMATION);
            nonSelectedAlert.setTitle("Chú ý!");
            nonSelectedAlert.setHeaderText("Bạn cần chọn menu để xóa ");
            return;
        }
        menuList.remove(menuBiXoa);
        menuAccessor.xoa(menuBiXoa);
    }

    //Xem Menu khi double click vào hàng cần xem
    public void xemMenu(MouseEvent event){
        Menu menuDuocChon = table.getSelectionModel().getSelectedItem();
        if(event.getClickCount() == 2 && (menuDuocChon != null)){
            idLB.setText(String.valueOf(menuDuocChon.getIdMenu()));
            donGiaLB.setText(String.valueOf(menuDuocChon.getDonGia()));
            khaiViLB.setText(menuDuocChon.getKhaiVi());
            monChinhLB.setText(menuDuocChon.getMonChinh());
            trangMiengLB.setText(menuDuocChon.getTrangmieng());
        }
    }
}
