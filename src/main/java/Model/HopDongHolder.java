package Model;

public class HopDongHolder {
    private HopDong hopDong;
    private final static HopDongHolder INSTANCE = new HopDongHolder();

    private HopDongHolder(){};

    public static HopDongHolder getInstance(){
        return INSTANCE;
    }

    public void setHopDong(HopDong h){
        this.hopDong = h;
    }

    public HopDong getHopDong(){
        return this.hopDong;
    }
}
