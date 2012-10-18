package aplikacja;

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
    Connection connection;
    Polaczenie loguj;
    
    public JScrollPane wyswietlDane (Connection connection) {
        Vector columnNames = new Vector();
        Vector data = new Vector();
        try {
            java.sql.Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from DAGMARA.Klienci");
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
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }
}