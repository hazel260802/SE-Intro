package models;
import java.util.Date;
/**
 *
 * @author huuph
 */
public class CuocHopModel {
    private int ID;
    private String maCuocHop;
    private Date thoiGianHop;
    private Date thoiGianTaoLichHop;
    private String diaDiem;
    private String noiDungChinh;
    private String nguoiTaoCuocHop;
    private int soNguoiThamGia;
    private String trangThai;

    public CuocHopModel(int ID, String maCuocHop, Date thoiGianHop, Date thoiGianTaoLichHop, 
            String diaDiem, String noiDungChinh, String nguoiTaoCuocHop, int soNguoiThamGia) {
        this.ID = ID;
        this.maCuocHop = maCuocHop;
        this.thoiGianHop = thoiGianHop;
        this.thoiGianTaoLichHop = thoiGianTaoLichHop;
        this.diaDiem = diaDiem;
        this.noiDungChinh = noiDungChinh;
        this.nguoiTaoCuocHop = nguoiTaoCuocHop;
        this.soNguoiThamGia = soNguoiThamGia;
    }
    public CuocHopModel(){
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaCuocHop() {
        return maCuocHop;
    }

    public void setMaCuocHop(String maCuocHop) {
        this.maCuocHop = maCuocHop;
    }

    public Date getThoiGianHop() {
        return thoiGianHop;
    }

    public void setThoiGianHop(Date thoiGianHop) {
        this.thoiGianHop = thoiGianHop;
    }

    public Date getThoiGianTaoLichHop() {
        return thoiGianTaoLichHop;
    }

    public void setThoiGianTaoLichHop(Date thoiGianTaoLichHop) {
        this.thoiGianTaoLichHop = thoiGianTaoLichHop;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public String getNoiDungChinh() {
        return noiDungChinh;
    }

    public void setNoiDungChinh(String noiDungChinh) {
        this.noiDungChinh = noiDungChinh;
    }

    public String getNguoiTaoCuocHop() {
        return nguoiTaoCuocHop;
    }

    public void setNguoiTaoCuocHop(String nguoiTaoCuocHop) {
        this.nguoiTaoCuocHop = nguoiTaoCuocHop;
    }

    public int getSoNguoiThamGia() {
        return soNguoiThamGia;
    }

    public void setSoNguoiThamGia(int soNguoiThamGia) {
        this.soNguoiThamGia = soNguoiThamGia;
    }

    public void setTrangThai(){ 
        Date currentDate = new Date(System.currentTimeMillis());
        if(thoiGianHop.before(currentDate)){
            trangThai = "Đã diễn ra";
        } else trangThai = "Chưa diễn ra";
    }
    
    public String getTrangThai(){
        return trangThai;
    } 
    
    
}
