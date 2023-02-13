/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controllers.ThamGiaManagerController;

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
import javax.swing.table.DefaultTableModel;
import models.CuocHopModel;
import models.NhanKhauModel;
import services.LichHopService;
import services.MysqlConnection;
import utility.ClassTableModel;
import views.infoViews.InfoJframe;

/**
 *
 * @author huuph
 */
public class XemTTinDiemDanhController {

    private JPanel tableTopJpn;
    private JPanel tableBotJpn;
    private JTextField idCuocHopJtf;
    private JTextField maCuocHopJtf;
    private JDateChooser thoiGianHopJtf;
    private JTextField diaDiemHopJtf;
    private JTextField noiDungHopJtf;
    private JButton taoDSBtn;
    private JButton themTTBtn;
    private JButton cancelBtn;
    private JButton acceptBtn;
    private final LichHopService lichHopService = new LichHopService();
    private JFrame XemTtinDiemDanhJFrame;
    
    private List<CuocHopModel> listCuocHop;
    private List<NhanKhauModel> listNhanKhauThamGia = new ArrayList<>();
    private NhanKhauModel nhanKhauSelected = new NhanKhauModel();
    private final ClassTableModel tableModelNhanKhau = new ClassTableModel();
    private final String[] COLUMNS_NK = {"ID", "Họ tên", "Ngày sinh","Giới tính","Mã Hộ Khẩu"};
    private final String[] COLUMNS_CH = {"ID","Thời gian họp", "Địa điểm", "Nội dung họp","Trạng thái"};
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
                    cuocHopSelected = temp;
                    idCuocHopJtf.setText(String.valueOf(temp.getID()));
                    maCuocHopJtf.setText(temp.getMaCuocHop());
                    thoiGianHopJtf.setDate(temp.getThoiGianHop());
                    diaDiemHopJtf.setText(temp.getDiaDiem());
                    noiDungHopJtf.setText(temp.getNoiDungChinh());
                    
                    maCuocHopJtf.setEditable(false);
                    idCuocHopJtf.setEditable(false);
                    diaDiemHopJtf.setEditable(false);
                    noiDungHopJtf.setEditable(false);
                    setDataChoose();
                }
            }
            
        });
        
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(tableTopJpn.getSize());
        table.getColumnModel().getColumn(0).setMaxWidth(35);
        table.getColumnModel().getColumn(0).setMinWidth(35);
        table.getColumnModel().getColumn(0).setPreferredWidth(35);
        tableTopJpn.removeAll();
        tableTopJpn.setLayout(new BorderLayout());
        tableTopJpn.add(scroll);
        tableTopJpn.validate();
        tableTopJpn.repaint();
    }
    
    public void setDataChoose() {
        listNhanKhauThamGia = new ArrayList<>();
        listNhanKhauThamGia = lichHopService.getListNhanKhauThamGia(cuocHopSelected.getID());
        DefaultTableModel model;
        model = ClassTableModel.setTableThamGiaHop(listNhanKhauThamGia, COLUMNS_NK);
        JTable table = new JTable(model) {
            @Override
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
            
        };
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 30));
        table.setRowHeight(30);
        table.validate();
        table.repaint();
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nhanKhauSelected = listNhanKhauThamGia.get(table.getSelectedRow());
            }
            
        });
        
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(tableBotJpn.getSize());
        table.getColumnModel().getColumn(0).setMaxWidth(30);
        table.getColumnModel().getColumn(0).setMinWidth(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        tableBotJpn.removeAll();
        tableBotJpn.setLayout(new BorderLayout());
        tableBotJpn.add(scroll);
        tableBotJpn.validate();
        tableBotJpn.repaint();
    }
    public boolean xoaDiemDanh() throws SQLException, ClassNotFoundException{
        try{
        Connection connection = MysqlConnection.getMysqlConnection();
        String query = "DELETE FROM thamGiaHop where idNhanKhau = " + nhanKhauSelected.getID() +
                " and idCuocHop = " + cuocHopSelected.getID();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
        query = "SELECT * FROM thamGiaHop join thanh_vien_cua_ho on thamGiaHop.idNhanKhau = thanh_vien_cua_ho.idNhanKhau where "
                + "idCuocHop = " + cuocHopSelected.getID() +  " and idHoKhau = (select idHoKhau from thanh_vien_cua_ho where idNhanKhau = " + nhanKhauSelected.getID() + ")";
        preparedStatement = connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        int count = 0;
        while(rs.next()){
            count++;
        }
        if(count == 0){
            query = "UPDATE ho_khau set soLanThamGiaHop = soLanThamGiaHop - 1 where ID = (select idHoKhau from thanh_vien_cua_ho where idNhanKhau = " + nhanKhauSelected.getID() + ")"
                    + " and soLanThamGiaHop > 0";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        }
        
        query = "UPDATE cuoc_hop set soNguoiThamGia = soNguoiThamGia - 1 where ID = " + cuocHopSelected.getID();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        connection.close();
        refreshData();
        } catch(Error e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    public void refreshData() {
        this.listNhanKhauThamGia = this.lichHopService.getListNhanKhauThamGia(cuocHopSelected.getID());
        setDataChoose();
    }
    
    public JTextField getIdCuocHopJtf() {
        return idCuocHopJtf;
    }

    public void setIdCuocHopJtf(JTextField idCuocHopJtf) {
        this.idCuocHopJtf = idCuocHopJtf;
    }

    public JTextField getMaCuocHopJtf() {
        return maCuocHopJtf;
    }

    public void setMaCuocHopJtf(JTextField maCuocHopJtf) {
        this.maCuocHopJtf = maCuocHopJtf;
    }

    public JDateChooser getThoiGianHopJtf() {
        return thoiGianHopJtf;
    }

    public void setThoiGianHopJtf(JDateChooser thoiGianHopJtf) {
        this.thoiGianHopJtf = thoiGianHopJtf;
    }

    public JTextField getNoiDungHopJtf() {
        return noiDungHopJtf;
    }

    public void setNoiDungHopJtf(JTextField noiDungHopJtf) {
        this.noiDungHopJtf = noiDungHopJtf;
    }

    public NhanKhauModel getNhanKhauSelected() {
        return nhanKhauSelected;
    }

    public void setNhanKhauSelected(NhanKhauModel nhanKhauSelected) {
        this.nhanKhauSelected = nhanKhauSelected;
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
