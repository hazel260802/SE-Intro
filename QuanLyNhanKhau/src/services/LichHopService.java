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
import javax.swing.JOptionPane;
import models.CuocHopModel;
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
}
