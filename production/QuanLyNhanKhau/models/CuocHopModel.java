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
    private UserMoldel nguoiTaoCuocHop;

    public CuocHopModel(int ID, String maCuocHop, Date thoiGianHop, Date thoiGianTaoLichHop, String diaDiem, String noiDungChinh, UserMoldel nguoiTaoCuocHop) {
        this.ID = ID;
        this.maCuocHop = maCuocHop;
        this.thoiGianHop = thoiGianHop;
        this.thoiGianTaoLichHop = thoiGianTaoLichHop;
        this.diaDiem = diaDiem;
        this.noiDungChinh = noiDungChinh;
        this.nguoiTaoCuocHop = nguoiTaoCuocHop;
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

    public UserMoldel getNguoiTaoCuocHop() {
        return nguoiTaoCuocHop;
    }

    public void setNguoiTaoCuocHop(UserMoldel nguoiTaoCuocHop) {
        this.nguoiTaoCuocHop = nguoiTaoCuocHop;
    }
    
    
}
