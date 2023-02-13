/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controllers.ThamGiaManagerController;

import Bean.NhanKhauBean;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import models.CuocHopModel;
import models.NhanKhauModel;
import models.ThamGiaHopModel;
import services.LichHopService;
import services.MysqlConnection;
import services.NhanKhauService;
import utility.ClassTableModel;
import views.infoViews.InfoJframe;

/**
 *
 * @author huuph
 */
public class ThemTTinDiemDanhController {
    private JPanel tableTopJpn;
    private JButton cancelBtn;
    private JButton acceptBtn;
    private JTextField jtfSearch;
    private final NhanKhauService nhanKhauService = new NhanKhauService();
    private JFrame ThemTTinDiemDanhJFrame;
    private int idCuocHop;
    
    private List<NhanKhauBean> listNhanKhau = new ArrayList<>();
    private NhanKhauModel nhanKhauSelected = new NhanKhauModel();
    private final ClassTableModel tableModelNhanKhau = new ClassTableModel();
    private final String[] COLUMNS_NK = {"ID", "Họ tên", "Ngày sinh","Giới tính","Mã hộ khẩu"};
    
    public void init() {
        listNhanKhau = this.nhanKhauService.getListNhanKhau();
        setData();
        initAction();
    }
    public ThemTTinDiemDanhController(){}
    public void setData() {
        List<NhanKhauModel> listItem = new ArrayList<>();
        this.listNhanKhau.forEach(nhankhau -> {
            listItem.add(nhankhau.getNhanKhauModel());
        });
        DefaultTableModel nhanKhau_model = this.tableModelNhanKhau.setTableThamGiaHop(listItem, COLUMNS_NK);
        JTable table = new JTable(nhanKhau_model) {
            @Override
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
        };
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.getTableHeader().setPreferredSize(new Dimension(100, 30));
        table.setRowHeight(30);
        table.validate();
        table.repaint();
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                NhanKhauModel temp = listItem.get(table.getSelectedRow());
                    // selected data
                    nhanKhauSelected = temp;
            }
            
        });
        
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(tableTopJpn.getSize());
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(0).setMinWidth(50);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        tableTopJpn.removeAll();
        tableTopJpn.setLayout(new BorderLayout());
        tableTopJpn.add(scroll);
        tableTopJpn.validate();
        tableTopJpn.repaint();
    }

    public void initAction(){
        this.jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String key = jtfSearch.getText();
                listNhanKhau = nhanKhauService.search(key.trim());
                setData();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String key = jtfSearch.getText();
                listNhanKhau = nhanKhauService.search(key.trim());
                setData();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String key = jtfSearch.getText();
                listNhanKhau = nhanKhauService.search(key.trim());
                setData();
            }
        });
    }
    
    public boolean addNewParticipation(ThamGiaHopModel thamGia) throws SQLException, ClassNotFoundException{
        Connection connection = MysqlConnection.getMysqlConnection();
        // 1 - 19
//        INSERT INTO cuoc_hop (maCuocHop, thoiGianHop, thoiGianTaoLichHop, diaDiem, noiDungChinh, nguoiTaoCuocHop)" 
//                        + " values (?, ?, ?, ?, ?, ?)
        String query = "select idNhanKhau from thamGiaHop where idCuocHop = " + thamGia.getIdCuocHop();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            if(rs.getInt("idNhanKhau") == thamGia.getIdNhanKhau()){
                String message = "Nhân khẩu này đã được điểm danh cho cuộc họp!";
                JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        query = "SELECT * FROM thamGiaHop join thanh_vien_cua_ho on thamGiaHop.idNhanKhau = thanh_vien_cua_ho.idNhanKhau where "
                + "idCuocHop = " + thamGia.getIdCuocHop() +  " and idHoKhau = (select idHoKhau from thanh_vien_cua_ho where idNhanKhau = " + thamGia.getIdNhanKhau() + ")";
        preparedStatement = connection.prepareStatement(query);
        rs = preparedStatement.executeQuery();
        int count = 0;
        while(rs.next()){
            count++;
        }
        if(count == 0){
        query = "UPDATE ho_khau set soLanThamGiaHop = soLanThamGiaHop + 1 where ID = (select idHoKhau from thanh_vien_cua_ho where idNhanKhau = " + thamGia.getIdNhanKhau() + ")";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        }
        
        query = "INSERT INTO thamGiaHop (idCuocHop, idNhanKhau) " + " values (?, ?)";
        preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, thamGia.getIdCuocHop());
        preparedStatement.setInt(2, thamGia.getIdNhanKhau());
        preparedStatement.executeUpdate();
        rs = preparedStatement.getGeneratedKeys();
        
        query = "UPDATE cuoc_hop set soNguoiThamGia = soNguoiThamGia + 1 where ID = " + thamGia.getIdCuocHop();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        connection.close();
        return true;
    }
    
    public int getIdCuocHop() {
        return idCuocHop;
    }

    public void setIdCuocHop(int idCuocHop) {
        this.idCuocHop = idCuocHop;
    }
    
    
    public JTextField getJtfSearch() {
        return jtfSearch;
    }

    public void setJtfSearch(JTextField jtfSearch) {
        this.jtfSearch = jtfSearch;
    }
    
    
    public JPanel getTableTopJpn() {
        return tableTopJpn;
    }

    public void setTableTopJpn(JPanel tableTopJpn) {
        this.tableTopJpn = tableTopJpn;
    }

    public JButton getCancelBtn() {
        return cancelBtn;
    }

    public void setCancelBtn(JButton cancelBtn) {
        this.cancelBtn = cancelBtn;
    }

    public JButton getAcceptBtn() {
        return acceptBtn;
    }

    public void setAcceptBtn(JButton acceptBtn) {
        this.acceptBtn = acceptBtn;
    }

    public JFrame getThemTTinDiemDanhJFrame() {
        return ThemTTinDiemDanhJFrame;
    }

    public void setThemTTinDiemDanhJFrame(JFrame ThemTTinDiemDanhJFrame) {
        this.ThemTTinDiemDanhJFrame = ThemTTinDiemDanhJFrame;
    }

    public List<NhanKhauBean> getListNhanKhau() {
        return listNhanKhau;
    }

    public void setListNhanKhau(List<NhanKhauBean> listNhanKhau) {
        this.listNhanKhau = listNhanKhau;
    }

    public NhanKhauModel getNhanKhauSelected() {
        return nhanKhauSelected;
    }

    public void setNhanKhauSelected(NhanKhauModel nhanKhauSelected) {
        this.nhanKhauSelected = nhanKhauSelected;
    }
    
    
}
