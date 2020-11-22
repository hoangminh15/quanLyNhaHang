package Model;

public class Sanh implements Data{
    private int idSanh;
    private String maSanh;
    private int donGia;
    private int sucChua;
    private String imageLink;

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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
