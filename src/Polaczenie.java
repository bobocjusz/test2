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
            connection = DriverManager.getConnection( "jdbc:oracle:thin:@cytrynowypatrol.no-ip.org:1521:DB11G", "DAGMARA", "123");
            tekst = "Połączono!";
        } 
        catch (ClassNotFoundException e) {
            tekst=("Nie chodzi bilbioteka, jest lipa");
            e.printStackTrace();
        }
    }
    
    public String zapiszKlient (String NIP, String Nazwa_firmy, String Nazwisko, String Imie, String Miasto, String Ulica, String Numer, String Kod_pocztowy, String Poczta, String Telefon) throws ClassNotFoundException, SQLException {
        if (connection != null) {
            java.sql.Statement s = connection.createStatement();
            s.execute("INSERT INTO KLIENCI (NIP, Nazwa_firmy, Nazwisko, Imie, Miasto, Ulica, Numer, Kod_pocztowy, Poczta, Telefon) VALUES ('"+ NIP + "', '" + Nazwa_firmy + "', '" + Nazwisko + "', '" + Imie + "', '" + Miasto + "', '" + Ulica + "', '" + Numer + "', '" + Kod_pocztowy + "', '" + Poczta + "', '" + Telefon + "')");
            tekst = "Dodano do bazy danych!";
            s.close();
        } 
        else {
            tekst = "Nie moge się połączyć! I jest mega dupa";
        }
        return tekst;
    }
}