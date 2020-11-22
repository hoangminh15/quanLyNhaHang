package DataAccessor;

import Model.DichVu;
import Model.Menu;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MenuAccessor extends DataAccessor{
    Menu menu;
    ArrayList<String> idMenuList;

    public MenuAccessor(){
        thietLapKetNoi();
    }

    //Can kiem tra idMenu
    public Menu layMenuData(int idMenu) {
        String sql = "SELECT * FROM NhaHang.Menu WHERE idMenu = '" + idMenu + "'";
        try{
            rs = statement.executeQuery(sql);
            rs.next();
            menu = getFromResultSet(rs);
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return menu;
    }

    @Override
    public Menu getFromResultSet(ResultSet rs) throws Exception {
        Menu menuSet = new Menu();
        menu = menuSet = new Menu();
        menuSet.setIdMenu(Integer.parseInt(rs.getString("idMenu")));
        menuSet.setDonGia(Integer.parseInt(rs.getString("donGia")));
        menuSet.setKhaiVi(rs.getString("khaiVi"));
        menuSet.setMonChinh(rs.getString("monChinh"));
        menuSet.setTrangmieng(rs.getString("trangMieng"));
        return menuSet;
    }

    public ArrayList<String> layDanhSach(){
        String sql = "SELECT idMenu FROM NhaHang.Menu";
        idMenuList = new ArrayList<>();
        try {
            rs = statement.executeQuery(sql);
            while(rs.next()){
                idMenuList.add(rs.getString("idMenu"));
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return idMenuList;
    }
}
