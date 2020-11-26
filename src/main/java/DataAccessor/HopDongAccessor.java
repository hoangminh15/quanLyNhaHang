package DataAccessor;

import Model.HopDong;
import Model.Menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HopDongAccessor extends DataAccessor {

    HopDong hopDong;
    ArrayList<String> idHopDongList;

    public HopDongAccessor(){
        thietLapKetNoi();
    }

    public HopDong layHopDongData(int idHopDong) {
        String sql = "SELECT * FROM NhaHang.HopDong WHERE idHopDong = '" + idHopDong + "'";
        try {
            rs = statement.executeQuery(sql);
            rs.next();
            hopDong = getFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hopDong;
    }

    public void themHopDong(HopDong hopDongData) throws SQLException {
        String ngayToChuc = hopDongData.getNgayToChuc();
        String thoiDiem = hopDongData.getThoiDiem();
        String maSanh = hopDongData.getMaSanh();
        String soMenu = hopDongData.getSoMenu();
        String soBan = hopDongData.getSoBan();
        String soKhach = hopDongData.getSoKhach();
        String nhanVien = hopDongData.getNhanVien();
        String tenKhachHang = hopDongData.getTenKhachHang();
        String diaChi = hopDongData.getDiaChi();
        String dienThoai = hopDongData.getDienThoai();
        String ngayLapDon = hopDongData.getNgayLapDon();
        String giaDichVu = hopDongData.getGiaDichVu();
        String dichVuDaChon = hopDongData.getDichVuDaChon();
        String sql = "INSERT INTO `NhaHang`.`HopDong` (`ngayToChuc`, `thoiDiem`, `maSanh`, `soMenu`, `soBan`, `soKhach`, `nhanVien`, `tenKhachHang`, `diaChi`, `dienThoai`, `ngayLapDon`, `giaDichVu`, `dichVuDaChon`) VALUES ('" + ngayToChuc + "', '" + thoiDiem + "', '" + maSanh + "', '" + soMenu + "', '" + soBan +  "', '" + soKhach +  "', '" + nhanVien + "', '" + tenKhachHang + "', '" + diaChi + "', '" + dienThoai + "', '" + ngayLapDon + "', '" + giaDichVu + "', '" + dichVuDaChon + "')";
        statement.executeUpdate(sql);
    }

    @Override
    public ArrayList<String> layDanhSach() {
        String sql = "SELECT idHopDong FROM NhaHang.HopDong";
        idHopDongList = new ArrayList<>();
        try {
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                idHopDongList.add(rs.getString("idHopDong"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return idHopDongList;
    }

    @Override
    public HopDong getFromResultSet(ResultSet rs) throws Exception {
        HopDong hopDongSet = new HopDong();
        hopDongSet.setNgayToChuc(rs.getString("ngayToChuc"));
        hopDongSet.setThoiDiem(rs.getString("thoiDiem"));
        hopDongSet.setMaSanh(rs.getString("maSanh"));
        hopDongSet.setSoMenu(rs.getString("soMenu"));
        hopDongSet.setSoBan(rs.getString("soBan"));
        hopDongSet.setSoKhach(rs.getString("soKhach"));
        hopDongSet.setNhanVien(rs.getString("nhanVien"));
        hopDongSet.setTenKhachHang(rs.getString("tenKhachHang"));
        hopDongSet.setDiaChi(rs.getString("diaChi"));
        hopDongSet.setDienThoai(rs.getString("dienThoai"));
        hopDongSet.setNgayLapDon(rs.getString("ngayLapDon"));
        hopDongSet.setGiaDichVu(rs.getString("giaDichVu"));
        hopDongSet.setGiaDichVu(rs.getString("dichVuDaChon"));

        return hopDongSet;
    }
}
