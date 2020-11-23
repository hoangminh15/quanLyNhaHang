package Model;

public class Menu extends Data{
    private int idMenu;
    private String khaiVi;
    private String monChinh;
    private String trangmieng;
    private int donGia;

    public Menu(){

    }

    public Menu(int idMenu, String khaiVi, String monChinh, String trangmieng, int donGia) {
        this.idMenu = idMenu;
        this.khaiVi = khaiVi;
        this.monChinh = monChinh;
        this.trangmieng = trangmieng;
        this.donGia = donGia;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getKhaiVi() {
        return khaiVi;
    }

    public void setKhaiVi(String khaiVi) {
        this.khaiVi = khaiVi;
    }

    public String getMonChinh() {
        return monChinh;
    }

    public void setMonChinh(String monChinh) {
        this.monChinh = monChinh;
    }

    public String getTrangmieng() {
        return trangmieng;
    }

    public void setTrangmieng(String trangmieng) {
        this.trangmieng = trangmieng;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }
}
