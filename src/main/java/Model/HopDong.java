package Model;

public class HopDong extends Data{
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String ngayToChuc;
    private String thoiDiem;
    private String maSanh;
    private String soMenu;
    private String soBan;
    private String soKhach;
    private String nhanVien;
    private String tenKhachHang;
    private String diaChi;
    private String dienThoai;
    private String ngayLapDon;
    private String giaDichVu;
    private String dichVuDaChon;



    public HopDong(){

    }

    public HopDong(String ngayToChuc, String thoiDiem, String maSanh, String soMenu, String soBan, String soKhach, String nhanVien, String tenKhachHang, String diaChi, String dienThoai, String ngayLapDon, String giaDichVu, String dichVuDaChon) {
        this.ngayToChuc = ngayToChuc;
        this.thoiDiem = thoiDiem;
        this.maSanh = maSanh;
        this.soMenu = soMenu;
        this.soBan = soBan;
        this.soKhach = soKhach;
        this.nhanVien = nhanVien;
        this.tenKhachHang = tenKhachHang;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.ngayLapDon = ngayLapDon;
        this.giaDichVu = giaDichVu;
        this.dichVuDaChon = dichVuDaChon;
    }

    public String getDichVuDaChon() {
        return dichVuDaChon;
    }

    public void setDichVuDaChon(String dichVuDaChon) {
        this.dichVuDaChon = dichVuDaChon;
    }

    public String getNgayToChuc() {
        return ngayToChuc;
    }

    public void setNgayToChuc(String ngayToChuc) {
        this.ngayToChuc = ngayToChuc;
    }

    public String getThoiDiem() {
        return thoiDiem;
    }

    public void setThoiDiem(String thoiDiem) {
        this.thoiDiem = thoiDiem;
    }

    public String getMaSanh() {
        return maSanh;
    }

    public void setMaSanh(String maSanh) {
        this.maSanh = maSanh;
    }

    public String getSoMenu() {
        return soMenu;
    }

    public void setSoMenu(String soMenu) {
        this.soMenu = soMenu;
    }

    public String getSoBan() {
        return soBan;
    }

    public void setSoBan(String soBan) {
        this.soBan = soBan;
    }

    public String getSoKhach() {
        return soKhach;
    }

    public void setSoKhach(String soKhach) {
        this.soKhach = soKhach;
    }

    public String getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(String nhanVien) {
        this.nhanVien = nhanVien;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getNgayLapDon() {
        return ngayLapDon;
    }

    public void setNgayLapDon(String ngayLapDon) {
        this.ngayLapDon = ngayLapDon;
    }

    public String getGiaDichVu() {
        return giaDichVu;
    }

    public void setGiaDichVu(String giaDichVu) {
        this.giaDichVu = giaDichVu;
    }
}
