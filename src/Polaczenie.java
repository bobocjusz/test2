/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.util.Vector;


public class Polaczenie {
    Connection connection;
    String tekst;
    Vector columnNames;
    Vector data;
    
    public Polaczenie() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection( "jdbc:oracle:thin:@192.168.1.30:1521:DB11G", "DAGMARA", "123");
            tekst = "Połączono!";
        } 
        catch (ClassNotFoundException e) {
            tekst=("Nie chodzi bilbioteka, jest lipa");
            e.printStackTrace();
        }
    }
    
//    public String polaczenie (Connection connection) throws SQLException {
//        tekst = "";
//        connection = null;
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            connection = DriverManager.getConnection( "jdbc:oracle:thin:@cytrynowypatrol.no-ip.org:1521:DB11G", "DAGMARA", "123");
//            tekst = "Połączono!";
//        } 
//        catch (ClassNotFoundException e) {
//            tekst=("Nie chodzi bilbioteka, jest lipa");
//            e.printStackTrace();
//        }
//        return tekst;
//    }
    
    public String zapiszKlient (String NIP, String Nazwa_firmy, String Nazwisko, String Imie, String Miasto, String Ulica, String Numer, String Kod_pocztowy, String Poczta, String Telefon) throws ClassNotFoundException, SQLException {
        //Class.forName("oracle.jdbc.driver.OracleDriver");
        //connection = DriverManager.getConnection( "jdbc:oracle:thin:@cytrynowypatrol.no-ip.org:1521:DB11G", "DAGMARA", "123");
        if (connection != null) {
            java.sql.Statement s = connection.createStatement();
            s.execute("INSERT INTO KLIENCI (NIP, Nazwa_firmy, Nazwisko, Imie, Miasto, Ulica, Numer, Kod_pocztowy, Poczta, Telefon) VALUES ('"+ NIP + "', '" + Nazwa_firmy + "', '" + Nazwisko + "', '" + Imie + "', '" + Miasto + "', '" + Ulica + "', '" + Numer + "', '" + Kod_pocztowy + "', '" + Poczta + "', '" + Telefon + "')");
            tekst = "Dodano do bazy danych!";
            s.close();
            //connection.close();
        } 
        else {
            tekst = "Nie moge się połączyć! I jest mega dupa";
        }
        return tekst;
    }
    
    public String wyswietlDane() throws SQLException {
        columnNames = new Vector();
        data = new Vector();
        String tekst;

        if (connection != null) {
            String sql = "SELECT NIK, NIP, Nazwa_firmy, Nazwisko, Imie, Miasto, Ulica, Numer, Kod_pocztowy, Poczta, Telefon FROM DAGMARA.Klienci";
            java.sql.Statement stmt = connection.createStatement();
            tekst = "jest dobrze!";
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement(md.getColumnName(i));
            }
            while (rs.next()) {
                Vector row = new Vector(columns);
                for (int i = 1; i <= columns; i++){
                    row.addElement(rs.getObject(i));
                }
                data.addElement(row);
            }
            rs.close();
            
            //stmt.close();
        }
        else {
            tekst = "dupa, dupa ...!";
        }
        return tekst;
    }
}