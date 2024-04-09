<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.sql.*" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Practical 6</title>
        </head>
        <style>
            table {
                font-family: arial, sans-serif;
                border-collapse: collapse;
                width: 100%;
            }

            td,
            th {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
            }

            tr:nth-child(even) {
                background-color: #dddddd;
            }
        </style>

        <body>
            <h1> Table output </h1>

            <table>
                <tr>
                    <th>ID</th>
                    <th>BName</th>
                    <th>Author</th>
                    <th>Price</th>
                    <th>Quantity</th>
                </tr>
                <% try { Class.forName("com.mysql.jdbc.Driver"); Connection
                    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test1", "root" , "Kunal@123" );
                    Statement stmt=con.createStatement(); ResultSet rs=stmt.executeQuery("select * from bookshop");
                    while (rs.next()) { %>
                    <tr>
                        <td>
                            <%= rs.getString(1) %>
                        </td>
                        <td>
                            <%= rs.getString(2) %>
                        </td>
                        <td>
                            <%= rs.getString(3) %>
                        </td>
                        <td>
                            <%= rs.getString(4) %>
                        </td>
                        <td>
                            <%= rs.getString(5) %>
                        </td>
                    </tr>
                    <% } }catch(Exception e) { e.printStackTrace(); } %>
            </table>
        </body>

        </html>