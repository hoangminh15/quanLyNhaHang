package Controller;

import Model.HopDong;
import Model.HopDongHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;

//Superclass cua cac controller
public abstract class Controller {
    DecimalFormat myFormatter;
    HopDongHolder holder;

    //Method dung de lấy stage trong javafx
    public Stage retrieveStage(ActionEvent event){
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    //Hiển thị thông báo khi các thông tin điền vào là không hợp lệ
    public void popUpInvalidAlert(){
        Alert invalidAlert = new Alert(Alert.AlertType.INFORMATION);
        invalidAlert.setTitle("Chú ý");
        invalidAlert.setHeaderText("ID và đơn giá phải là các số");
        invalidAlert.setContentText("Vui lòng kiểm tra lại");
        invalidAlert.show();
    }

    //Hiện thị thông báo khi ID thêm vào bị trùng
    public void popUpDuplicatedIDAlert(){
        Alert duplicatedId = new Alert(Alert.AlertType.INFORMATION);
        duplicatedId.setTitle("ID bị trùng");
        duplicatedId.setHeaderText("ID dịch vụ mới phải khác với các ID đã có");
        duplicatedId.setContentText("Vui lòng nhập lại");
        duplicatedId.show();
    }

    //Hiển thị thông báo khi các trường bị thiếu
    public void popUpMissingFieldAlert(){
        Alert missingFieldAlert = new Alert(Alert.AlertType.INFORMATION);
        missingFieldAlert.setTitle("Chú ý!");
        missingFieldAlert.setHeaderText("Thông tin chưa đầy đủ");
        missingFieldAlert.setContentText("Kiểm tra lại các thông tin còn trống");
        missingFieldAlert.show();
    }

    //Quay trở lại trang chủ , chuyển data trở lại trang chủ home
    public void troLai(ActionEvent event) throws IOException {
        holder = HopDongHolder.getInstance();
        HopDong hopDong = holder.getHopDong();
        Stage stage = retrieveStage(event);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/Home.fxml"));
        Parent homeParent = loader.load();
        Scene scene = new Scene(homeParent);
        HomeController homeController = loader.getController();
        homeController.setBackHopDong(hopDong);
        stage.setScene(scene);
    }
}
