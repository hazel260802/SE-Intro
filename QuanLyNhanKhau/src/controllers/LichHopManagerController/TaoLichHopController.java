/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controllers.LichHopManagerController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.CuocHopModel;
import services.MysqlConnection;

/**
 *
 * @author huuph
 */
public class TaoLichHopController {

    /**
     * @param args the command line arguments
     */
    public TaoLichHopController(){}
    public boolean addNewMeeting(CuocHopModel cuocHop) throws SQLException, ClassNotFoundException{
        Connection connection = MysqlConnection.getMysqlConnection();
        // 1 - 19
        String query = "INSERT INTO cuoc_hop (maCuocHop, thoiGianHop, thoiGianTaoLichHop, diaDiem, noiDungChinh, nguoiTaoCuocHop)" 
                        + " values (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, cuocHop.getMaCuocHop());
        java.sql.Date thoiGianHop = new java.sql.Date(cuocHop.getThoiGianHop().getTime());
        preparedStatement.setDate(2, thoiGianHop);
        java.sql.Date createDate = new java.sql.Date(quanlynhankhau.QuanLyNhanKhau.calendar.getTime().getTime());
        preparedStatement.setDate(3, createDate);
        preparedStatement.setString(4, cuocHop.getDiaDiem());
        preparedStatement.setString(5, cuocHop.getNoiDungChinh());
        preparedStatement.setString(6, cuocHop.getNguoiTaoCuocHop());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        connection.close();
        return true;
    }
}
