package Model;

public class DichVu implements Data{
    private int idDichVu;
    private String tenDichVu;
    private int donGia;

    public DichVu(){

    }

    public DichVu(int idDichVu, String tenDichVu, int donGia) {
        this.idDichVu = idDichVu;
        this.tenDichVu = tenDichVu;
        this.donGia = donGia;
    }

    public int getIdDichVu() {
        return idDichVu;
    }

    public void setIdDichVu(int idDichVu) {
        this.idDichVu = idDichVu;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }
}
