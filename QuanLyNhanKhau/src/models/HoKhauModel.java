package models;

import java.util.Date;

/**
 *
 * @author Hai
 */
public class HoKhauModel {
    private int ID;
    private String maHoKhau;
    private int idChuHo;
    private String maKhuVuc;
    private String diaChi;
    private Date ngayLap;
    private Date ngayChuyenDi;
    private String lyDoChuyen;
    private int nguoiThucHien;
    private int soLanThamGiaHop;
    private String trangThai;

    public Date getNgayChuyenDi() {
        return ngayChuyenDi;
    }

    public void setNgayChuyenDi(Date ngayChuyenDi) {
        this.ngayChuyenDi = ngayChuyenDi;
    }

    public int getSoLanThamGiaHop() {
        return soLanThamGiaHop;
    }

    public void setSoLanThamGiaHop(int soLanThamGiaHop) {
        this.soLanThamGiaHop = soLanThamGiaHop;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaHoKhau() {
        return maHoKhau;
    }

    public void setMaHoKhau(String maHoKhau) {
        this.maHoKhau = maHoKhau;
    }

    public int getIdChuHo() {
        return idChuHo;
    }

    public void setIdChuHo(int idChuHo) {
        this.idChuHo = idChuHo;
    }

    public String getMaKhuVuc() {
        return maKhuVuc;
    }

    public void setMaKhuVuc(String maKhuVuc) {
        this.maKhuVuc = maKhuVuc;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public Date getNgayChuyDi() {
        return ngayChuyenDi;
    }

    public void setNgayChuyDi(Date ngayChuyenDi) {
        this.ngayChuyenDi = ngayChuyenDi;
    }

    public String getLyDoChuyen() {
        return lyDoChuyen;
    }

    public void setLyDoChuyen(String lyDoChuyen) {
        this.lyDoChuyen = lyDoChuyen;
    }

    public int getNguoiThucHien() {
        return nguoiThucHien;
    }

    public void setNguoiThucHien(int nguoiThucHien) {
        this.nguoiThucHien = nguoiThucHien;
    }
    
    public void setTrangThai(){
        if(soLanThamGiaHop>=5){
         trangThai = "Gia đinh văn hóa";   
        } else trangThai = "Không";
    }
    
    public String getTrangThai(){
        return trangThai;
    }
}
