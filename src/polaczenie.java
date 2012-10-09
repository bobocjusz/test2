
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Slawek
 */
public class polaczenie {
 
    public polaczenie ()
    {}
    
      public String laczenie()
                { String tekst="";
                try {

                        Class.forName("oracle.jdbc.driver.OracleDriver");

                } catch (ClassNotFoundException e) {
                        panel.getFrames();
                        tekst=("Nie chodzi bilbioteka, jest lipa");
                        e.printStackTrace();
                        String s;
                        return tekst;
                        
                }
                Connection connection = null;
                  try {

                        connection = DriverManager.getConnection(
                                        "jdbc:oracle:thin:@cytrynowypatrol.no-ip.org:1521:DB11G", "DAGMARA",
                                        "123");

                } catch (SQLException e) {

                        tekst="Nie laczy się, coś zrypane";
                        e.printStackTrace();   
                         return tekst;
                }       
                  if (connection != null) {
                        tekst="Zajebiście działa !";
                         return tekst;
                } else {
                        tekst="Nie moge się połączyć!";
                         return tekst;
                }
               // Dupa jasiu
                }
}
