/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.TableColumn;

/**
 *
 * @author Dagmara
 */
public class Wyswietl {
    public JFrame wyswietlDane () {
        Vector columnNames = new Vector();
        Vector data = new Vector();
        JPanel p=new JPanel();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection( "jdbc:oracle:thin:@192.168.1.30:1521:DB11G", "DAGMARA", "123");
            String sql = "Select * from DAGMARA.Klienci";
            java.sql.Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement( md.getColumnName(i) );
            }
            while (rs.next()) {
                Vector row = new Vector(columns);
                for (int i = 1; i <= columns; i++){
                    row.addElement( rs.getObject(i) );
                }
                data.addElement( row );
            }
            rs.close();
            stmt.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        JTable table = new JTable(data, columnNames);
        TableColumn col;
        for (int i = 0; i < table.getColumnCount(); i++) {
            col = table.getColumnModel().getColumn(i);
            col.setMaxWidth(250);
        }
        JScrollPane scrollPane = new JScrollPane( table );
        p.add( scrollPane );
        JFrame f=new JFrame();
        f.add(p);
        f.setSize(1000,400);
        f.setVisible(true);
        return f;
    }
}