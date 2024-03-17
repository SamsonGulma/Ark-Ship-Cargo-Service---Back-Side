package com.arkanoidservices.arkanoidservice;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.*;

@WebServlet("/ARKANOID_SIGNIN_SERVLET2")
public class ARKANOID_SIGNIN_SERVLET2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirm_password = request.getParameter("confirm_password");


        // Insert user data into the database
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/arkanoidServicesDB", "root",  "MyNewPass");
            String sql = "INSERT INTO customerHandler (username, email, password, confirm_password) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, confirm_password);
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException error) {
            System.out.println(error);
            // Handle database connection error
        }
    }
}