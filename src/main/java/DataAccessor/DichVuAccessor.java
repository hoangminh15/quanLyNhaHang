package DataAccessor;

import Model.Data;
import Model.DichVu;

import java.sql.*;
import java.util.ArrayList;

public class DichVuAccessor extends DataAccessor implements iDataController {
    private DichVu dichVu;
    private ArrayList<String> idDichVuList;

    public DichVuAccessor() {
        thietLapKetNoi();
    }

    //Lấy dịch vụ data theo mã
    public DichVu layDichVuData(int idDichVu) {
        String sql = "SELECT * FROM NhaHang.DichVu WHERE idDichVu = '" + idDichVu + "'";
        try {
            rs = statement.executeQuery(sql);
            rs.next();
            dichVu = getFromResultSet(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dichVu;
    }

    //Lấy danh sách ID các dịch vụ
    public ArrayList<String> layDanhSach() {
        String sql = "SELECT idDichVu FROM NhaHang.DichVu";
        idDichVuList = new ArrayList<>();
        try {
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                idDichVuList.add(rs.getString("idDichVu"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return idDichVuList;
    }

    @Override
    public void them(Data dichVuData) throws SQLException {
        //Cast back to DichVu
        DichVu dichVuObject = (DichVu) dichVuData;
        int idDichVu = dichVuObject.getIdDichVu();
        String tenDichVu = dichVuObject.getTenDichVu();
        int donGia = dichVuObject.getDonGia();
        String sql = "INSERT INTO NhaHang.DichVu VALUES ('" + idDichVu + "', '" + tenDichVu + "', '" + donGia + "')";
        statement.executeUpdate(sql);
    }

    @Override
    public void xoa(Data dichVuData) {
        DichVu dichVuObject = (DichVu) dichVuData;
        int idDichVu = dichVuObject.getIdDichVu();
        String sql = "DELETE FROM NhaHang.DichVu WHERE idDichVu = '" + idDichVu + "'";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Tách kết quả from resultSet: bộ kết quả và gán vào object chứa data là ichVu
    @Override
    public DichVu getFromResultSet(ResultSet rs) throws Exception {

        DichVu dichVuSet = new DichVu();
        dichVuSet.setIdDichVu(Integer.parseInt(rs.getString("idDichVu")));
        dichVuSet.setDonGia(Integer.parseInt(rs.getString("donGia")));
        dichVuSet.setTenDichVu(rs.getString("tenDichVu"));
        return dichVuSet;
    }
}
