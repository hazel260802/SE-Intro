/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import models.CuocHopModel;
import models.NhanKhauModel;
/**
 *
 * @author huuph
 */
public class LichHopService {

    /**
     * @param args the command line arguments
     */
    public List<CuocHopModel> getListCuocHop(){
        List<CuocHopModel> list = new ArrayList<>();
        try{
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM cuoc_hop";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            int idCuocHop = -1;
            while (rs.next()){
                CuocHopModel cuocHop = new CuocHopModel();
                idCuocHop = rs.getInt("ID");
                cuocHop.setID(idCuocHop);
                cuocHop.setMaCuocHop(rs.getString("maCuocHop"));
                cuocHop.setThoiGianHop(rs.getDate("thoiGianHop"));
                cuocHop.setThoiGianTaoLichHop(rs.getDate("thoiGianTaoLichHop"));
                cuocHop.setDiaDiem(rs.getString("diaDiem"));
                cuocHop.setNoiDungChinh(rs.getString("noiDungChinh"));
                list.add(cuocHop);
            }
            preparedStatement.close();
            for(CuocHopModel cuocHop : list){
                query = "SELECT COUNT(*) AS tong FROM thamGiaHop WHERE idCuocHop = " + cuocHop.getID();
                preparedStatement = (PreparedStatement)connection.prepareStatement(query);
                rs = preparedStatement.executeQuery();
                while(rs.next()){
                    cuocHop.setSoNguoiThamGia(rs.getInt("tong"));
                }
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            this.exceptionHandle(e.getMessage());
        }
        return list;
    }
    
        public List<CuocHopModel> getListCuocHopSapToi(){
        List<CuocHopModel> list = new ArrayList<>();
        try{
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM cuoc_hop WHERE thoiGianHop > NOW()";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            int idCuocHop = -1;
            while (rs.next()){
                CuocHopModel cuocHop = new CuocHopModel();
                idCuocHop = rs.getInt("ID");
                cuocHop.setID(idCuocHop);
                cuocHop.setMaCuocHop(rs.getString("maCuocHop"));
                cuocHop.setThoiGianHop(rs.getDate("thoiGianHop"));
                cuocHop.setThoiGianTaoLichHop(rs.getDate("thoiGianTaoLichHop"));
                cuocHop.setDiaDiem(rs.getString("diaDiem"));
                cuocHop.setNoiDungChinh(rs.getString("noiDungChinh"));
                list.add(cuocHop);
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            this.exceptionHandle(e.getMessage());
        }
        return list;
    }
        
    public List<NhanKhauModel> getListNhanKhauThamGia(int idCuocHop){
        List<NhanKhauModel> list = new ArrayList<>();
        try{
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "select * from `nhan_khau` inner join `thamGiaHop` "
                    + "on nhan_khau.ID = thamGiaHop.idNhanKhau where idCuocHop = " + idCuocHop;
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                NhanKhauModel nhanKhau = new NhanKhauModel();
                nhanKhau.setID(rs.getInt("ID"));
                nhanKhau.setHoTen(rs.getString("hoTen"));
                nhanKhau.setNamSinh(rs.getDate("namSinh"));
                nhanKhau.setGioiTinh(rs.getString("gioiTinh"));
                list.add(nhanKhau);
            }
        } catch (Exception e) {
            this.exceptionHandle(e.getMessage());
        }
        return list;
    }
        /*
     * ham tim kiem cuoc hop theo noi dung
     */
    public List<CuocHopModel> search(String keyword) {
        List<CuocHopModel> list = new  ArrayList<>();
        String query = "";
        if (keyword.trim().isEmpty()) {
            return this.getListCuocHop();
        }
        // truy cap db
        // create query
        try {
            query = "SELECT * "
                    + "FROM cuoc_hop "
                    + "WHERE noiDungChinh LIKE '%"
                    + keyword
                    + "%'";
        } catch (Exception e) {
            System.out.println(e);
        }
        
        // execute query
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                CuocHopModel cuocHop = new CuocHopModel();
                cuocHop.setID(rs.getInt("ID"));
                cuocHop.setThoiGianHop(rs.getDate("thoiGianHop"));
                cuocHop.setDiaDiem(rs.getString("diaDiem"));
                cuocHop.setNoiDungChinh(rs.getString("noiDungChinh"));
                
                list.add(cuocHop);
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception mysqlException) {
            this.exceptionHandle(mysqlException.getMessage());
        }
        return list;
    }
        /*
     * Ham xử lý ngoại lệ : thông báo ra lỗi nhận được
     */
    private void exceptionHandle(String message) {
        JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.ERROR_MESSAGE);
    }
    public List<CuocHopModel> statisticLichHop(int tuNguoi, int denNguoi,Date tuThoiGian,Date denThoiGian,String trangThai) {
        List<CuocHopModel> list = new ArrayList<>();
        
        String query = "SELECT thoiGianHop,diaDiem,noiDungChinh "
                + " FROM cuoc_hop"
                + " INNER JOIN thamGiaHop ON cuoc_hop.ID = thamGiaHop.idCuocHop\n "
                + " WHERE (thoiGianHop BETWEEN "
                + tuThoiGian
                + " AND "
                + denThoiGian + " )\n";
//                + "\nAND soNguoiThamGia >="
//                + tuNguoi
//                + "AND soNguoiThamGia <="
//                + denNguoi;
        if (trangThai.equalsIgnoreCase("Toan bo")) {
//            query += " AND (tam_tru.denNgay >= CURDATE() OR tam_tru.denNgay IS NULL)"
//                    + " AND (tam_vang.denNgay <= CURDATE() OR tam_vang.denNgay IS NULL)";
        } else if (trangThai.equalsIgnoreCase("Đã diễn ra")) {
            query += "AND thoiGianHop <= NOW()";
            
        } else if (trangThai.equalsIgnoreCase("Chưa diễn ra")) {
            query += "AND thoiGianHop > NOW() ";
        }
        query += "GROUP BY thoiGianHop";
         try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            int idCuocHop = -1;
            while (rs.next()){
                CuocHopModel cuocHop = new CuocHopModel();
                idCuocHop = rs.getInt("ID");
                cuocHop.setID(idCuocHop);
                cuocHop.setThoiGianHop(rs.getDate("thoiGianHop"));
                cuocHop.setDiaDiem(rs.getString("diaDiem"));
                cuocHop.setNoiDungChinh(rs.getString("noiDungChinh"));
//                cuocHop.setSoNguoiThamGia(rs.getInt("soNguoiThamGia"));
//                cuocHop.setTrangThai(rs.getString("trangThai"));
                   
                if(idCuocHop > 0){
                    String sql = "SELECT COUNT(idNhanKhau) as soNguoiThamGia FROM thamGiaHop"
                            +" WHERE thamGiaHop.idCuocHop = "+ idCuocHop;
                    PreparedStatement prst = (PreparedStatement)connection.prepareStatement(sql);
                    ResultSet rs_temp = prst.executeQuery();
                    while(rs_temp.next()){
                        cuocHop.setSoNguoiThamGia(rs_temp.getInt("soNguoiThamGia"));
//                        cuocHop.setTrangThai();
                    }
                    prst.close();
                }

                list.add(cuocHop);
                }
            preparedStatement.close();
            }
            catch (Exception e) {
             System.out.println(e.getMessage());
        }
        
        return list;
    }
}
