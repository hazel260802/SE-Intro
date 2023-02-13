/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.ThongKeLichHop;

import Bean.HoKhauBean;
import Bean.MemOfFamily;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.CuocHopModel;
import models.HoKhauModel;
import models.ThanhVienCuaHoModel;
import services.HoKhauService;
import services.LichHopService;
import services.StringService;
import utility.ClassTableModel;

/**
 *
 * @author Dell
 */
public class ThongKeThamGiaController {
    private JTextField tuBuoiJtf;
    private JTextField denBuoiJtf;
    private JButton hienThiBtn;
    private JPanel jpnView;
    private HoKhauService hoKhauService;
//    private NhanKhauService nhanKhauService;
    private List<HoKhauBean> listHoKhau;
    private ClassTableModel classTableModel;
    private JFrame parentJFrame;
    private final String[] COLUMNS = {"ID","Hộ gia đình","Số buổi tham gia","Trạng thái"};
 
    public ThongKeThamGiaController(JTextField tuBuoiJtf, JTextField denBuoiJtf,JButton hienThiBtn,JPanel jpnView) {
        this.tuBuoiJtf = tuBuoiJtf;
        this.denBuoiJtf = denBuoiJtf;
        this.hienThiBtn = hienThiBtn;
        this.jpnView = jpnView;
        this.hoKhauService= new HoKhauService();
        this.listHoKhau = this.hoKhauService.getListHoKhau();
        this.classTableModel = new ClassTableModel();
    }
    
     public JPanel getJpnView() {
        return jpnView;
    }

    public void setJpnView(JPanel jpnView) {
        this.jpnView = jpnView;
    }
    
    public void setData() {
        int tuBuoi = 0;
        int denBuoi = 100;

        try {
            if (!this.tuBuoiJtf.getText().trim().isEmpty()) {
                tuBuoi = Integer.parseInt(this.tuBuoiJtf.getText().trim());
            } else {
                tuBuoi = 0;
            }
            if (!this.denBuoiJtf.getText().trim().isEmpty()) {
                denBuoi = Integer.parseInt(this.denBuoiJtf.getText().trim());
            } else {
                denBuoi = 100;
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(denBuoiJtf, "Vui lòng nhập đúng kiểu dữ liệu!!", "Warring", JOptionPane.ERROR_MESSAGE);
        }
        this.listHoKhau = this.hoKhauService.statisticHoKhau(tuBuoi, denBuoi);
        setDataTable();
    }
    public void setDataTable(){
        List<HoKhauModel> listItem = new ArrayList<>();
        this.listHoKhau.forEach(hokhau -> {
            listItem.add(hokhau.getHoKhauModel());
        });
        DefaultTableModel model = classTableModel.setTableThongKeThamGia(listItem, COLUMNS);
        JTable table = new JTable(model);
        
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getColumnModel().getColumn(0).setMaxWidth(40);
        table.getColumnModel().getColumn(0).setMinWidth(40);
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(new Dimension(800, 400));
        jpnView.removeAll();
        jpnView.setLayout(new CardLayout());
        jpnView.add(scroll);
        jpnView.validate();
        jpnView.repaint();
    }
    
    public JTextField getTuBuoi() {
        return tuBuoiJtf;
    }

    public void setTuBuoiJtf(JTextField tuBuoiJtf) {
        this.tuBuoiJtf = tuBuoiJtf;
    }

    public JTextField getDenBuoiJtf() {
        return denBuoiJtf;
    }

    public void setDenBuoiJtf(JTextField denBuoiJtf) {
        this.denBuoiJtf = denBuoiJtf;
    }
    public void setParentJFrame(JFrame parentJFrame) {
        this.parentJFrame = parentJFrame;
    }
    
}
