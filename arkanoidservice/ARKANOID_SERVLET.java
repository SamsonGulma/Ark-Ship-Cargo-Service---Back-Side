package com.arkanoidservices.arkanoidservice;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.*;

@WebServlet("/ARKANOID_SERVLET")
public class ARKANOID_SERVLET extends HttpServlet {


    // Database credentials
    static final String DB_URL = "jdbc:mysql://localhost:3306/arkanoidServicesDB";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the input values from the login form
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Connection conn = null;
        Statement statements = null;
        ResultSet result = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,"root","MyNewPass");

            String sql = "SELECT * FROM customerTBL WHERE email = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            result = statement.executeQuery();



            // Check if the login was successful or not
            if (result.next()) {
                response.sendRedirect("success.html");
            } else {
                response.sendRedirect("signIn.jsp");
            }

        } catch (SQLException | ClassNotFoundException error) {
            System.out.println(error);
        }
            // In here we are going to Close the database resources
            try {
                if (result != null) result.close();
                if (statements != null) statements.close();
                if (conn != null) conn.close();
            } catch (SQLException error) {
                System.out.println("Something went wrong!!!!!!!!!");
            }


    }

}