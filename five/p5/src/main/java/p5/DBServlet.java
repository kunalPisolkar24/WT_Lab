package p5;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import jakarta.servlet.annotation.*;
import java.sql.*;

@WebServlet(name = "DBServlet", value = "/DBServlet")
public class DBServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/test1";
    private static final String uname = "root";
    private static final String password = "Kunal@123";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(jdbcUrl, uname, password);
            response.getWriter().println("Connection established"); 
            response.setContentType("text/html");
            createTable(con);
            insertData(con, "The Alchemist", "Paulo Coelho", 100, 10);
            insertData(con, "The Great Gatsby", "F. Scott Fitzgerald", 150, 5);
            insertData(con, "The Lord of the Rings", "J. R. R. Tolkien", 200, 20);
            displayDataOnSite(con, response.getWriter());

            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void createTable(Connection con) throws Exception {
        String query = "CREATE TABLE IF NOT EXISTS bookshop ( id int auto_increment primary key,bname varchar(200), author varchar(200), price int, quantity int);";
        PreparedStatement ps = con.prepareStatement(query);
        ps.executeUpdate();
        ps.close();
        System.out.println("Table created");
    }

    private static int insertData(Connection con, String bname, String author, int price, int quantity) throws Exception {
        String query = "INSERT INTO bookshop(bname, author, price, quantity) values(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, bname);
        ps.setString(2, author);
        ps.setInt(3, price);
        ps.setInt(4, quantity);
        int i = ps.executeUpdate();
        ps.close();
        System.out.println("Data inserted");
        return i;
    }

    private static int updateData(Connection con, String bname, String author, int price, int quantity, int id) throws Exception {
        String query = "UPDATE bookshop SET bname=?, author=?, price=?, quantity=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, bname);
        ps.setString(2, author);
        ps.setInt(3, price);
        ps.setInt(4, quantity);
        ps.setInt(5, id);
        int i = ps.executeUpdate();
        ps.close();
        System.out.println("Data updated");
        return i;
    }

    private static int deleteData(Connection con, int id) throws Exception {
        String query = "DELETE FROM bookshop WHERE id=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        int i = ps.executeUpdate();
        ps.close();
        System.out.println("Data deleted");
        return i;
    }

    private static void displayData(Connection con) throws Exception {
        String query = "SELECT * FROM bookshop";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            String bname = rs.getString(2);
            String author = rs.getString(3);
            int price = rs.getInt(4);
            int quantity = rs.getInt(5);
            System.out.println(id + " " + bname + " " + author + " " + price + " " + quantity);
        }
        ps.close();
        System.out.println("Data displayed");
    }

    private static void displayDataOnSite(Connection con, PrintWriter out) throws Exception {
        String query = "SELECT * FROM bookshop";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        out.println("<html><head> <style> table { font-family: arial, sans-serif; border-collapse: collapse; width: 100%; } td, th { border: 1px solid #dddddd; text-align: left; padding: 8px; } tr:nth-child(even) { background-color: #dddddd; } </style> </head><body><table border='1'>");
        out.println("<tr><td>id</td><td>bname</td><td>author</td><td>price</td><td>quantity</td></tr>");
        while (rs.next()) {
            int id = rs.getInt(1);
            String bname = rs.getString(2);
            String author = rs.getString(3);
            int price = rs.getInt(4);
            int quantity = rs.getInt(5);
            out.println("<tr><td>" + id + "</td><td>" + bname + "</td><td>" + author + "</td><td>" + price + "</td><td>" + quantity + "</td></tr>");
        }
        out.println("</table>");
        ps.close();
        System.out.println("Data displayed");
    }

}