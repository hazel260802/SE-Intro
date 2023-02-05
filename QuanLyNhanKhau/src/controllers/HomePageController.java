/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JLabel;
import services.MysqlConnection;

/**
 *
 * @author huuph
 */
public class HomePageController {
    private JLabel tongLichHoplb;
    private JLabel tongHoKhaulb;
    private JLabel tongGDVHlb;

    public HomePageController(JLabel tongLichHop, JLabel tongHoKhau, JLabel tongGDVH) {
        this.tongLichHoplb = tongLichHop;
        this.tongHoKhaulb = tongHoKhau;
        this.tongGDVHlb = tongGDVH;
    }
    
    public void setData() {
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT COUNT(*) AS tong FROM cuoc_hop";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                this.tongLichHoplb.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();
            
            query = "SELECT COUNT(*) AS tong FROM ho_khau";
            preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                this.tongHoKhaulb.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();
            
            query = "SELECT COUNT(*) AS tong FROM tam_tru WHERE denNgay > NOW()";
            preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                this.tongGDVHlb.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();
            
            connection.close();
        } catch (Exception e) {
        }
    }

    public JLabel getTongLichHoplb() {
        return tongLichHoplb;
    }

    public void setTongLichHoplb(JLabel tongLichHoplb) {
        this.tongLichHoplb = tongLichHoplb;
    }

    public JLabel getTongHoKhaulb() {
        return tongHoKhaulb;
    }

    public void setTongHoKhaulb(JLabel tongHoKhaulb) {
        this.tongHoKhaulb = tongHoKhaulb;
    }

    public JLabel getTongGDVHlb() {
        return tongGDVHlb;
    }

    public void setTongGDVHlb(JLabel tongGDVHlb) {
        this.tongGDVHlb = tongGDVHlb;
    }

    
}
