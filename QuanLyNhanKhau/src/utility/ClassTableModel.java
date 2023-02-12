package utility;

import Bean.HoKhauBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.CuocHopModel;
import models.GiaDinhModel;
import models.HoKhauModel;
import models.NhanKhauModel;
import models.TieuSuModel;
import services.MysqlConnection;

/**
 *
 * @author Hai
 * class dinh nghia cac dang table co trong phan mem
 */
public class ClassTableModel {
    // bang cho main frame
    public DefaultTableModel setTableNhanKhau(List<NhanKhauModel> listItem, String[] listColumn) {
        final int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 5 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[columns];
        listItem.forEach((NhanKhauModel item) -> {
            obj[0] = item.getID();
            obj[1] = item.getHoTen();
            obj[2] = item.getNamSinh();
            obj[3] = item.getGioiTinh();
            obj[4] = item.getDiaChiHienNay();
            dtm.addRow(obj);
        });
        return dtm;
    }
    // table cho tieu su
    public DefaultTableModel setTableTieuSu(List<TieuSuModel> tieuSu, String[] listColumn) {
        final int column = listColumn.length;
        
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                 return columnIndex == 5 ? Boolean.class : String.class;
            }
        };
        
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[column];
        
        tieuSu.forEach((TieuSuModel item) -> {
            obj[0] = item.getTuNgay().toString();
            obj[1] = item.getDenNgay().toString();
            obj[2] = item.getDiaChi();
            obj[3] = item.getNgheNghiep();
            obj[4] = item.getNoiLamViec();
            dtm.addRow(obj);
        });
        
        dtm.addRow(new Object[] {"", "", "", "", ""});
        
//        dtm.addTableModelListener(new TableModelListener() {
//            @Override
//            public void tableChanged(TableModelEvent e) {
//                int a = dtm.getRowCount();
//                if ((e.getLastRow() + 1) == dtm.getRowCount()) {
//                    System.out.println(); 
//                }
//                
//            }
//        });
        return dtm;
    }
    public DefaultTableModel setTableGiaDinh(List<GiaDinhModel> giaDinh, String[] listColumn) {
        final int column = listColumn.length;
        
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                 return columnIndex == 6 ? Boolean.class : String.class;
            }
        };
        
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[column];
        
        giaDinh.forEach((GiaDinhModel item) -> {
            obj[0] = item.getHoTen();
            obj[1] = item.getNamSinh().toString();
            obj[2] = item.getGioiTinh();
            obj[3] = item.getQuanHeVoiNhanKhau();
            obj[4] = item.getNgheNghiep();
            obj[5] = item.getDiaChiHienTai();
            dtm.addRow(obj);
        });
        
        dtm.addRow(new Object[] {"", "", "", "", "", ""});
        return dtm;
    }
    
    public DefaultTableModel setTableHoKhau(List<HoKhauBean> listItem, String[] listColumn) {
        final int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 5 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[columns];
        listItem.forEach((HoKhauBean item) -> {
            obj[0] = item.getHoKhauModel().getID();
            obj[1] = item.getHoKhauModel().getMaHoKhau();
            obj[2] = item.getChuHo().getHoTen();
            obj[3] = item.getHoKhauModel().getDiaChi();
            obj[4] = item.getHoKhauModel().getNgayLap();
            dtm.addRow(obj);
        });
        return dtm;
    }
    
        public DefaultTableModel setTableCuocHop(List<CuocHopModel> listItem, String[] listColumn) {
        final int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 5 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[columns];
        listItem.forEach((CuocHopModel item) -> {
            obj[0] = item.getID();
            obj[1] = item.getThoiGianHop();
            obj[2] = item.getDiaDiem();
            obj[3] = item.getNoiDungChinh();
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
            long millis = System.currentTimeMillis();
            java.sql.Date now = new java.sql.Date(millis);
            long tmp = Long.parseLong(fmt.format(item.getThoiGianHop())) - Long.parseLong(fmt.format(now));
            if(tmp > 0) obj[4] = "Chưa diễn ra";
            else if(tmp < 0) obj[4] = "Đã diễn ra";
            else obj[4]= "Diễn ra hôm nay";
            dtm.addRow(obj);
        });
        return dtm;
    }
    
    public DefaultTableModel setTableCuocHopSapToi(List<CuocHopModel> listItem, String[] listColumn) {
        final int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 5 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[columns];
        for(int i = 0; i < listItem.size(); i++) {
            CuocHopModel item = listItem.get(i);
            obj[0] = i + 1;
            obj[1] = item.getThoiGianHop();
            obj[2] = item.getDiaDiem();
            obj[3] = item.getNoiDungChinh();
            if(item.getSoNguoiThamGia() == 0) obj[4] = "";
            else obj[4] = item.getSoNguoiThamGia();
            dtm.addRow(obj);
        };
        return dtm;
    }
        
    public static DefaultTableModel setTableThamGiaHop(List<NhanKhauModel> listItem, String[] listColumn) {
        final int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 5 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[columns];
        for(int i = 0; i < listItem.size(); i++) {
            NhanKhauModel item = listItem.get(i);
            obj[0] = item.getID();
            obj[1] = item.getHoTen();
            obj[2] = item.getNamSinh();
            obj[3] = item.getGioiTinh();
            try{
                Connection connection = MysqlConnection.getMysqlConnection();
                String query = "SELECT maHoKhau FROM thanh_vien_cua_ho inner join ho_khau on thanh_vien_cua_ho.idHoKhau = ho_khau.ID "
                        + "where idNhanKhau = " + item.getID();
                PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
                ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                obj[4] = rs.getString("maHoKhau");
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
        }
        dtm.addRow(obj);
    }
        return dtm;
    }
    public DefaultTableModel setTableThongKeThongTin(List<CuocHopModel> listItem, String[] listColumn) {
        final int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 6 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[columns];
        listItem.forEach((CuocHopModel item) -> {
            obj[0] = item.getID();
            obj[1] = item.getThoiGianHop();
            obj[2] = item.getDiaDiem();
            obj[3] = item.getNoiDungChinh();
            obj[4] = item.getSoNguoiThamGia();
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
            long millis = System.currentTimeMillis();
            java.sql.Date now = new java.sql.Date(millis);
//            long tmp = Long.parseLong(fmt.format(item.getThoiGianHop())) - Long.parseLong(fmt.format(now));
            if(item.getThoiGianHop().after(now)) obj[5] = "Chưa diễn ra";
            else if(item.getThoiGianHop().before(now)) obj[5] = "Đã diễn ra";
            else obj[5]= "Diễn ra hôm nay";
            dtm.addRow(obj);
        });
        return dtm;
    }
    public DefaultTableModel setTableThongKeThamGia(List<HoKhauModel> listItem, String[] listColumn) {
        final int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 4 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[columns];
        listItem.forEach((HoKhauModel item) -> {
            obj[0] = item.getID();
            obj[1] = item.getMaHoKhau();
//            obj[2] = item.getSoLanThamGiaHop();
            try{
                Connection connection = MysqlConnection.getMysqlConnection();
//                String query = "SELECT COUNT(idCuochop) as soLanThamGiaHop FROM thamGiaHop,nhan_khau,thanh_vien_cua_ho\n "
                String query = "SELECT soLanThamGiaHop FROM ho_khau"
                +" WHERE ID = " + item.getID();
//                +" AND thanh_vien_cua_ho.idNhanKhau=nhan_khau.ID\n"
//                +" AND thanh_vien_cua_ho.idHoKhau = " + item.getID()
//                +" GROUP BY idHoKhau";
                PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()){
                    obj[2] = rs.getInt("soLanThamGiaHop");
                    item.setSoLanThamGiaHop(rs.getInt("soLanThamGiaHop"));
                } 
                preparedStatement.close();
                connection.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (item.getSoLanThamGiaHop() >=5){
                obj[3]="Gia đình văn hóa";
            } else{
                obj[3] = "Không";
            }
            dtm.addRow(obj);
        });
        return dtm;
    }
    
     
}
