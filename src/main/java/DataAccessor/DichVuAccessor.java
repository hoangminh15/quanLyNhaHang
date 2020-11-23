package DataAccessor;

import Model.Data;
import Model.DichVu;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DichVuAccessor extends DataAccessor{
    DichVu dichVu;
    ArrayList<String> idDichVuList;

    public DichVuAccessor(){
        thietLapKetNoi();
    }

    //Chu y kieu du lieu cua idDichVu
    public DichVu layDichVuData(int idDichVu){
        String sql = "SELECT * FROM NhaHang.DichVu WHERE idDichVu = '" + idDichVu + "'";
        try {
            rs = statement.executeQuery(sql);
            rs.next();
            dichVu = getFromResultSet(rs);
        } catch(Exception e){
            e.printStackTrace();
        }
        return dichVu;
    }

    public ArrayList<String> layDanhSach(){
        String sql = "SELECT idDichVu FROM NhaHang.DichVu";
        idDichVuList = new ArrayList<>();
        try {
            rs = statement.executeQuery(sql);
            while(rs.next()){
                idDichVuList.add(rs.getString("idDichVu"));
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return idDichVuList;
    }

    @Override
    public void them(DichVu dichVu) {
        int idDichVu = dichVu.getIdDichVu();
        String tenDichVu = dichVu.getTenDichVu();
        int donGia = dichVu.getDonGia();
        String sql = "INSERT INTO NhaHang.DichVu VALUES ('" + idDichVu + "', '" + tenDichVu + "', '" + donGia + "')";

    }

    @Override
    public DichVu getFromResultSet(ResultSet rs) throws Exception{

        DichVu dichVuSet = new DichVu();
        dichVuSet.setIdDichVu(Integer.parseInt(rs.getString("idDichVu")));
        dichVuSet.setDonGia(Integer.parseInt(rs.getString("donGia")));
        dichVuSet.setTenDichVu(rs.getString("tenDichVu"));
        return dichVuSet;
    }
}
