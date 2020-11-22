package Model;

public class Sanh implements Data{
    private int idSanh;
    private String maSanh;
    private int donGia;
    private int sucChua;

    public Sanh(){

    }

    public Sanh(int idSanh, String maSanh, int donGia, int sucChua) {
        this.idSanh = idSanh;
        this.maSanh = maSanh;
        this.donGia = donGia;
        this.sucChua = sucChua;
    }

    public int getIdSanh() {
        return idSanh;
    }

    public void setIdSanh(int idSanh) {
        this.idSanh = idSanh;
    }

    public String getMaSanh() {
        return maSanh;
    }

    public void setMaSanh(String maSanh) {
        this.maSanh = maSanh;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getSucChua() {
        return sucChua;
    }

    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }
}
