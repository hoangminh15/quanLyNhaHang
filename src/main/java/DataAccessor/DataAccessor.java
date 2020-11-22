package DataAccessor;

import Model.Data;
import Model.DichVu;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public abstract class DataAccessor {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/StockData";

    //Database credentials
    static final String USERNAME = "root";
    static final String PASSWORD = "Minh1592";

    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;

    public void thietLapKetNoi() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Method de tach ket qua tu ResultSet va dat vao cac Data object
    public abstract Data getFromResultSet(ResultSet rs) throws Exception;

    //Method de lay danh sach cac data object(Khong day du thong tin)
    public abstract List<String> layDanhSach();
}
