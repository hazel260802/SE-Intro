/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controllers;

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
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import models.CuocHopModel;
import services.LichHopService;
import utility.ClassTableModel;

/**
 *
 * @author huuph
 */
public class LichHopPanelController {

    /**
     * @param args the command line arguments
     */
    private JPanel jpnView;
    private JTextField jtfSearch;
    private LichHopService lichHopService;
    private List<CuocHopModel> listCuocHop;
    private ClassTableModel classTableModel = null;
    private final String[] COLUMNS = {"STT", "Thời gian họp", "Địa điểm", "Nội dung họp","Trạng thái"};
    private JFrame parentJFrame;

    public LichHopPanelController(JPanel jpnView, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.jtfSearch = jtfSearch;
        classTableModel = new ClassTableModel();
        this.lichHopService = new LichHopService();
        this.listCuocHop = this.lichHopService.getListCuocHop();
        System.out.println("controllers.LichHopPanelController.<init>()");
        initAction();
    }
    
        public void initAction(){
        this.jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String key = jtfSearch.getText();
                listCuocHop = lichHopService.search(key.trim());
                setDataTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String key = jtfSearch.getText();
                listCuocHop = lichHopService.search(key.trim());
                setDataTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String key = jtfSearch.getText();
                listCuocHop = lichHopService.search(key.trim());
                setDataTable();
            }
        });
    }
        
    public void setDataTable() {
        List<CuocHopModel> listItem = new ArrayList<>();
        this.listCuocHop.forEach(cuocHop -> {
            listItem.add(cuocHop);
        });
        DefaultTableModel model = classTableModel.setTableCuocHop(listItem, COLUMNS);
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
        this.listCuocHop = this.lichHopService.getListCuocHop();
        setDataTable();
    }
    public JPanel getJpnView() {
        return jpnView;
    }

    public void setJpnView(JPanel jpnView) {
        this.jpnView = jpnView;
    }

    public JTextField getJtfSearch() {
        return jtfSearch;
    }

    public void setJtfSearch(JTextField jtfSearch) {
        this.jtfSearch = jtfSearch;
    }
    public LichHopPanelController() {
    }
}
