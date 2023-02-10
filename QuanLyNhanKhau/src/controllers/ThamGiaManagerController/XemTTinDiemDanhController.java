/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controllers.ThamGiaManagerController;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import models.CuocHopModel;
import models.NhanKhauModel;
import services.LichHopService;
import utility.ClassTableModel;
import views.infoViews.InfoJframe;

/**
 *
 * @author huuph
 */
public class XemTTinDiemDanhController {

    private JPanel tableTopJpn;
    private JPanel tableBotJpn;
    private JTextField maCuocHopJtf;
    private JTextField thoiGianHopJtf;
    private JTextField diaDiemHopJtf;
    private JButton taoDSBtn;
    private JButton themTTBtn;
    private JButton cancelBtn;
    private JButton acceptBtn;
    private final LichHopService lichHopService = new LichHopService();
    private JFrame XemTtinDiemDanhJFrame;
    
    private List<CuocHopModel> listCuocHop;
    private List<NhanKhauModel> listNhanKhauThamGia = new ArrayList<>();
    private final ClassTableModel tableModelNhanKhau = new ClassTableModel();
    private final String[] COLUMNS_NK = {"ID", "Họ tên", "Ngày sinh","Giới tính","Địa chỉ hiện nay"};
    private final String[] COLUMNS_CH = {"STT","Thời gian họp", "Địa điểm", "Nội dung họp","DSDD"};
    private CuocHopModel cuocHopSelected;
    
    public XemTTinDiemDanhController(JFrame XemTtinDiemDanhJFrame) {
        this.XemTtinDiemDanhJFrame = XemTtinDiemDanhJFrame;
    }
    
    public void init() {
        listCuocHop = this.lichHopService.getListCuocHop();
        setData();
        
//    acceptBtn.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (maKhuVucJtf.getText().trim().isEmpty() 
//                        || diaChiJtf.getText().trim().isEmpty() 
//                        || maHoKhauMoiJtf.getText().trim().isEmpty() 
//                        || chuHoMoiJtf.getText().trim().isEmpty()) {
//                    JOptionPane.showMessageDialog(null, "Vui lòng nhập hết các trường bắt buộc!");
//                } else {
//                    hoKhauMoi.getHoKhauModel().setDiaChi(diaChiJtf.getText().trim());
//                    hoKhauMoi.getHoKhauModel().setMaHoKhau( maHoKhauMoiJtf.getText().trim());
//                    hoKhauMoi.getHoKhauModel().setMaKhuVuc(maKhuVucJtf.getText().trim());
//                    hoKhauService.tachHoKhau(hoKhauMoi);
//                    TachHoKhau tachHoKhau = (TachHoKhau)tachHoKhauJFrame;
//                    tachHoKhau.getParentJFrame().setEnabled(true);
//                    tachHoKhau.dispose();
//                }
//            }
//            
//        });
 }
    
    public void setData() {
        DefaultTableModel lichHop_model = this.tableModelNhanKhau.setTableCuocHop(listCuocHop, COLUMNS_CH);
        JTable table = new JTable(lichHop_model) {
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
                CuocHopModel temp = listCuocHop.get(table.getSelectedRow());
                if (e.getClickCount() > 1) {
                    InfoJframe infoJframe = new InfoJframe(temp.toString(), XemTtinDiemDanhJFrame);
                    infoJframe.setLocationRelativeTo(null);
                    infoJframe.setVisible(true);
                } else {
                    // selected data
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                    String strDate = dateFormat.format(temp.getThoiGianHop());
                    cuocHopSelected = temp;
                    maCuocHopJtf.setText(temp.getMaCuocHop());
                    thoiGianHopJtf.setText(strDate);
                    diaDiemHopJtf.setText(temp.getDiaDiem());
//                    setDataChoose();
                }
            }
            
        });
        
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(tableTopJpn.getSize());
        tableTopJpn.removeAll();
        tableTopJpn.setLayout(new BorderLayout());
        tableTopJpn.add(scroll);
        tableTopJpn.validate();
        tableTopJpn.repaint();
    }
    
    public void setDataChoose() {
        listNhanKhauThamGia = new ArrayList<>();
//        for (int i = 0; i < cuocHopSelected.getListNhanKhauModels().size(); i++) {
//            MemOfFamily temp = new MemOfFamily();
//            temp.getNhanKhau().setNhanKhauModel(hoKhauSelected.getListNhanKhauModels().get(i));
//            temp.setThanhVienCuaHoModel(hoKhauSelected.getListThanhVienCuaHo().get(i));
//            listThanhVien.add(temp);
//        }
//        DefaultTableModel model = tableModelHoKhau.setTableMember(listThanhVien, COLUMNS_NK);
//        
//        JTable table = new JTable(model) {
//            @Override
//            public boolean editCellAt(int row, int column, EventObject e) {
//                return false;
//            }
//            
//        };
//        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
//        table.getTableHeader().setPreferredSize(new Dimension(100, 30));
//        table.setRowHeight(30);
//        table.validate();
//        table.repaint();
//        table.setFont(new Font("Arial", Font.PLAIN, 14));
//        
//        table.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                thanhVienSeclected = listThanhVien.get(table.getSelectedRow());
//            }
//            
//        });
//        
//        JScrollPane scroll = new JScrollPane();
//        scroll.getViewport().add(table);
//        scroll.setPreferredSize(tableTopJpn.getSize());
//        tableBotJpn.removeAll();
//        tableBotJpn.setLayout(new BorderLayout());
//        tableBotJpn.add(scroll);
//        tableBotJpn.validate();
//        tableBotJpn.repaint();
    }

    public JTextField getMaCuocHopJtf() {
        return maCuocHopJtf;
    }

    public void setMaCuocHopJtf(JTextField maCuocHopJtf) {
        this.maCuocHopJtf = maCuocHopJtf;
    }

    public JTextField getThoiGianHopJtf() {
        return thoiGianHopJtf;
    }

    public void setThoiGianHopJtf(JTextField thoiGianHopJtf) {
        this.thoiGianHopJtf = thoiGianHopJtf;
    }

    public JTextField getDiaDiemHopJtf() {
        return diaDiemHopJtf;
    }

    public void setDiaDiemHopJtf(JTextField diaDiemHopJtf) {
        this.diaDiemHopJtf = diaDiemHopJtf;
    }

    public JButton getTaoDSBtn() {
        return taoDSBtn;
    }

    public void setTaoDSBtn(JButton taoDSBtn) {
        this.taoDSBtn = taoDSBtn;
    }

    public JButton getThemTTBtn() {
        return themTTBtn;
    }

    public void setThemTTBtn(JButton themTTBtn) {
        this.themTTBtn = themTTBtn;
    }

    public JFrame getXemTtinDiemDanhJFrame() {
        return XemTtinDiemDanhJFrame;
    }

    public void setXemTtinDiemDanhJFrame(JFrame XemTtinDiemDanhJFrame) {
        this.XemTtinDiemDanhJFrame = XemTtinDiemDanhJFrame;
    }

    public List<CuocHopModel> getListCuocHop() {
        return listCuocHop;
    }

    public void setListCuocHop(List<CuocHopModel> listCuocHop) {
        this.listCuocHop = listCuocHop;
    }

    public List<NhanKhauModel> getListNhanKhauThamGia() {
        return listNhanKhauThamGia;
    }

    public void setListNhanKhauThamGia(List<NhanKhauModel> listNhanKhauThamGia) {
        this.listNhanKhauThamGia = listNhanKhauThamGia;
    }

    public CuocHopModel getCuocHopSelected() {
        return cuocHopSelected;
    }

    public void setCuocHopSelected(CuocHopModel cuocHopSelected) {
        this.cuocHopSelected = cuocHopSelected;
    }

    public JPanel getTableTopJpn() {
        return tableTopJpn;
    }

    public void setTableTopJpn(JPanel tableTopJpn) {
        this.tableTopJpn = tableTopJpn;
    }

    public JPanel getTableBotJpn() {
        return tableBotJpn;
    }

    public void setTableBotJpn(JPanel tableBotJpn) {
        this.tableBotJpn = tableBotJpn;
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
}
