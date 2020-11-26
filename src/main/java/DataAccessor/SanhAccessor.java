package DataAccessor;

import Model.Data;

import Model.Menu;
import Model.Sanh;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SanhAccessor extends DataAccessor implements iDataController{
    Sanh sanh;
    ArrayList<String> maSanhList;

    public SanhAccessor(){
        thietLapKetNoi();
    }

    //Lay ve data cua 1 sanh duy nhat
    public Sanh laySanhData(String maSanh){
        String sql = "SELECT * FROM NhaHang.Sanh WHERE maSanh = '" + maSanh + "'";
        try {
            rs = statement.executeQuery(sql);
            rs.next();
            sanh = getFromResultSet(rs);
        } catch(Exception e){
            e.printStackTrace();
        }
        return sanh;
    }



    @Override
    public Sanh getFromResultSet(ResultSet rs) throws Exception {
        Sanh sanhSet = new Sanh();
        sanhSet.setIdSanh(Integer.parseInt(rs.getString("idSanh")));
        sanhSet.setDonGia(Integer.parseInt(rs.getString("donGia")));
        sanhSet.setMaSanh(rs.getString("maSanh"));
        sanhSet.setSucChua(Integer.parseInt(rs.getString("sucChua")));
        return sanhSet;
    }

    @Override
    public ArrayList<String> layDanhSach(){
        String sql = "SELECT maSanh FROM NhaHang.Sanh";
        maSanhList = new ArrayList<>();
        try{
            rs = statement.executeQuery(sql);
            while(rs.next()){
                maSanhList.add(rs.getString("maSanh"));
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return maSanhList;

    }

    @Override
    public void them(Data data) throws SQLException {
        Sanh sanhObject = (Sanh) data;
        int idSanh = sanhObject.getIdSanh();
        int donGia = sanhObject.getDonGia();
        String maSanh = sanhObject.getMaSanh();
        int sucChua = sanhObject.getSucChua();
        String sql = "INSERT INTO NhaHang.Sanh VALUES ('" + idSanh + "', '" + maSanh + "', '" + sucChua + "', '" + donGia + "');";
        statement.executeUpdate(sql);
    }

    @Override
    public void xoa(Data data) {
        Sanh menuObject = (Sanh) data;
        int idSanh = menuObject.getIdSanh();
        String sql = "DELETE FROM NhaHang.Sanh WHERE idSanh = '" + idSanh + "'";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
