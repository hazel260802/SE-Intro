/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author huuph
 */
public class ThamGiaHopModel {
     private int idCuocHop;
     private int idNhanKhau;

    public ThamGiaHopModel(int idCuocHop, int idNhanKhau) {
        this.idCuocHop = idCuocHop;
        this.idNhanKhau = idNhanKhau;
    }

    public ThamGiaHopModel() {
    }
    
    public int getIdCuocHop() {
        return idCuocHop;
    }

    public void setIdCuocHop(int idCuocHop) {
        this.idCuocHop = idCuocHop;
    }

    public int getIdNhanKhau() {
        return idNhanKhau;
    }

    public void setIdNhanKhau(int idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }
}
