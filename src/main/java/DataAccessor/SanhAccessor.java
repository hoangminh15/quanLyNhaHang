package DataAccessor;

import Model.Data;
import Model.Sanh;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SanhAccessor extends DataAccessor{
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
        sanhSet.setDonGia(Integer.parseInt(rs.getString("sucChua")));
        return sanhSet;
    }

    @Override
    public ArrayList<String> layDanhSach() throws Exception {
        String sql = "SELECT * FROM NhaHang.Sanh";
        String maSanh;
        try{
            rs = statement.executeQuery(sql);
            while(rs.next()){
                maSanhList.add(rs.getString("idDichVu"));
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return maSanhList;

    }
}
