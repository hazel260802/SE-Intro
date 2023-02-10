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
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.CuocHopModel;
import services.LichHopService;
import utility.ClassTableModel;

/**
 *
 * @author huuph
 */
public class ThamGiaPanelController {

    private JPanel jpnView;
    private LichHopService lichHopService;
    private List<CuocHopModel> listCuocHop;
    private ClassTableModel classTableModel = null;
    private final String[] COLUMNS = {"STT", "Thời gian họp", "Địa điểm", "Nội dung họp","Số lượng tham gia"};
    private JFrame parentJFrame;

    public ThamGiaPanelController(JPanel jpnView) {
        this.jpnView = jpnView;
        classTableModel = new ClassTableModel();
        this.lichHopService = new LichHopService();
        this.listCuocHop = this.lichHopService.getListCuocHop();
    }
        
    public void setDataTable() {
        List<CuocHopModel> listItem = new ArrayList<>();
        this.listCuocHop.forEach(cuocHop -> {
            listItem.add(cuocHop);
        });
        DefaultTableModel model = classTableModel.setTableCuocHopSapToi(listItem, COLUMNS);
        JTable table = new JTable(model) {
            @Override
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
            
        };
        
        // thiet ke bang
        
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(70, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getColumnModel().getColumn(0).setMaxWidth(40);
        table.getColumnModel().getColumn(0).setMinWidth(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                JOptionPane.showConfirmDialog(null, table.getSelectedRow());
                if (e.getClickCount() > 1) {
                    CuocHopModel temp = listCuocHop.get(table.getSelectedRow());
                }
            }
            
        });
        
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(new Dimension(800, 400));
        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(scroll);
        jpnView.validate();
        jpnView.repaint(); 
    }

    public void setParentJFrame(JFrame parentJFrame) {
        this.parentJFrame = parentJFrame;
    }
    
    public void refreshData() {
        this.listCuocHop = this.lichHopService.getListCuocHopSapToi();
        setDataTable();
    }
    public JPanel getJpnView() {
        return jpnView;
    }

    public void setJpnView(JPanel jpnView) {
        this.jpnView = jpnView;
    }

    public ThamGiaPanelController() {
    }
}
