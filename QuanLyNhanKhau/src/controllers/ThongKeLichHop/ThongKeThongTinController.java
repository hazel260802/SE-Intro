package controllers.ThongKeLichHop;

import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.util.EventObject;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import services.LichHopService;
import utility.ClassTableModel;
import models.CuocHopModel;
import services.StringService;
/**
 *
 * @author Dell
 */
public class ThongKeThongTinController {
    private JTextField tuNguoiJtf;
    private JTextField denNguoiJtf;
    private JComboBox trangThaiJcb;
    private JDateChooser thoiGianHop1;
    private JDateChooser thoiGianHop2;
    private JButton hienThiBtn;
    private JPanel jpnView;
    private LichHopService lichHopService;
    private List<CuocHopModel> listCuocHop;
    private ClassTableModel classTableModel;
    private JFrame parentJFrame;
    private final String[] COLUMNS = {"ID","Thời Gian Họp","Địa điểm họp","Nội dung chính","Số người tham gia","Trạng thái"};
    
    public ThongKeThongTinController(JTextField tuNguoiJtf, JTextField denNguoiJtf,JComboBox trangThaiJcb, JDateChooser thoiGianHop1,JDateChooser thoiGianHop2,JButton hienThiBtn,JPanel jpnView) {
        this.tuNguoiJtf = tuNguoiJtf;
        this.denNguoiJtf = denNguoiJtf;
        this.thoiGianHop1 = thoiGianHop1;
        this.thoiGianHop2 = thoiGianHop2;
        this.trangThaiJcb = trangThaiJcb;
        this.hienThiBtn = hienThiBtn;
        this.jpnView = jpnView;
        this.lichHopService= new LichHopService();
        this.listCuocHop = this.lichHopService.getListCuocHop();
        this.classTableModel = new ClassTableModel();
    }
    
     public JPanel getJpnView() {
        return jpnView;
    }

    public void setJpnView(JPanel jpnView) {
        this.jpnView = jpnView;
    }
    
    public void setData() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        int tuNguoi = 0;
        int denNguoi = 200;
        Date tuThoiGian = new Date(1990,1,1);
        Date denThoiGian = new Date(2024,1,1);
        String trangThai = StringService.covertToString((String)this.trangThaiJcb.getSelectedItem());

        try {
            if (!this.tuNguoiJtf.getText().trim().isEmpty()) {
                tuNguoi = Integer.parseInt(this.tuNguoiJtf.getText().trim());
            } else {
                tuNguoi = 0;
            }
            if (!this.denNguoiJtf.getText().trim().isEmpty()) {
                denNguoi = Integer.parseInt(this.denNguoiJtf.getText().trim());
            } else {
                denNguoi = 200;
            }
            if (this.thoiGianHop1.getDate().before(denThoiGian)) {
                tuThoiGian = thoiGianHop1.getDate();
            } else {
                tuThoiGian = new Date(1990,1,1);
            }
            if (this.thoiGianHop2.getDate().after(tuThoiGian)) {
                denThoiGian = thoiGianHop2.getDate();
            } else {
                denThoiGian = new Date(2024,1,1);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(denNguoiJtf, "Vui lòng nhập đúng kiểu dữ liệu!!", "Warring", JOptionPane.ERROR_MESSAGE);
        }
        this.listCuocHop = this.lichHopService.statisticLichHop(tuNguoi, denNguoi,tuThoiGian,denThoiGian,trangThai);
        setDataTable();
    }
    public void setDataTable(){
        List<CuocHopModel> listItem = new ArrayList<>();
        this.listCuocHop.forEach(cuocHop -> {
            listItem.add(cuocHop);
        });
        DefaultTableModel model = classTableModel.setTableThongKeThongTin(listItem, COLUMNS);
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
    
    public JTextField getTuNguoiJtf() {
        return tuNguoiJtf;
    }

    public void setTuNguoiJtf(JTextField tuNguoiJtf) {
        this.tuNguoiJtf = tuNguoiJtf;
    }

    public JTextField getDenNguoiJtf() {
        return denNguoiJtf;
    }

    public void setDenNguoiJtf(JTextField denNguoiJtf) {
        this.denNguoiJtf = denNguoiJtf;
    }

    public JDateChooser getThoiGianHop1() {
        return thoiGianHop1;
    }

    public void setThoiGianHop1(JDateChooser thoiGianHop1) {
        this.thoiGianHop1 = thoiGianHop1;
    }

    public JDateChooser getThoiGianHop2() {
        return thoiGianHop2;
    }

    public void setThoiGianHop2(JDateChooser thoiGianHop2) {
        this.thoiGianHop2 = thoiGianHop2;
    }
    public JComboBox getTrangThaiJcb() {
        return trangThaiJcb;
    }

    public void setTrangThaiJcb(JComboBox trangThaiJcb) {
        this.trangThaiJcb = trangThaiJcb;
    }
    public void setParentJFrame(JFrame parentJFrame) {
        this.parentJFrame = parentJFrame;
    }
}
