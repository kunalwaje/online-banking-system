package bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/adminlogin")
public class AdminLogin extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String id=req.getParameter("id");
		String password=req.getParameter("password");
		
		try {
			try {
				getClass().forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankingsystem","root","1234");
			PreparedStatement ps=connection.prepareStatement("select * from admin where id=? and password=?");
			ps.setInt(1, Integer.parseInt(id));
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				RequestDispatcher rd=req.getRequestDispatcher("AdminControls.html");
				rd.forward(req, res);
			}
			else
			{
				PrintWriter pw=res.getWriter();
				pw.println("<h1> Invalid Credentials </h1>");
				RequestDispatcher rd=req.getRequestDispatcher("AdminLoginForm.html");
				rd.forward(req, res);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
