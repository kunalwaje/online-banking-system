package bank;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/saveadmin")
public class CreateAdmin extends GenericServlet
{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=req.getParameter("id");
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
		try {
			try {
				getClass().forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankingsystem", "root","1234");
			PreparedStatement ps=connection.prepareStatement("insert into admin(id,name,email,password) values(?,?,?,?)");
			ps.setInt(1, Integer.parseInt(id));
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setString(4, password);
			ps.execute();
//			
//			PrintWriter pw=res.getWriter();
//			pw.println("<h1> Admin data saved sucessfully </h1>");
			
			RequestDispatcher rd=req.getRequestDispatcher("AdminLoginForm.html");
			rd.forward(req, res);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

