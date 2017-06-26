package DAL;
import java.sql.*;
import javax.swing.JOptionPane;

public class ConectaBD {
    
    public static Connection conectabd() throws ClassNotFoundException
    {
        try
        {
           
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sisestoque","root","root");
            return con;
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, "ERRO NA CONEXÃO " + error);
            return null;
        }
    }
    
}
