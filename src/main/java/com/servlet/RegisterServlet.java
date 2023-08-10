package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	//create the Query
	private static final String INSERT_QUERY="INSERT INTO USER(NAME,CITY,MOBILE,DOB)  VALUES(?,?,?,?)";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get printwriter
		PrintWriter pw = res.getWriter();
		// set content type
		res.setContentType("text/html");
		// read the form value
		
		String name=req.getParameter("name");
		String city=req.getParameter("city");
		String mobile=req.getParameter("mobile");
		String dob=req.getParameter("dob");
		
		System.out.println("Name:"+name);
		System.out.println("Mobile_no:"+mobile);
		System.out.println("City:"+city);
		System.out.println("Date_of_birth:"+dob);
		
		//load the jdbc driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//create the connection
		
		try(Connection con =DriverManager.getConnection("jdbc:mysql:///servletregistrationform","root","9860949212");
				PreparedStatement ps=con.prepareStatement(INSERT_QUERY);
				){
			//set the values
			ps.setString(1,name);
			ps.setString(2,city);
			ps.setString(3,mobile);
			ps.setString(4,dob);
			
			int count=ps.executeUpdate();
			
			//execute the Query
			if(count==0) {
				pw.println("Record not Stored into DataBase");
				
			}else {
				pw.println("Record Stored into DataBase");
			}						
		}catch(SQLException se) {
			pw.println(se.getMessage());
			se.printStackTrace();
		}catch(Exception e) {
			pw.println(e.getMessage());
			e.printStackTrace();
		}
		//close the Stream
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}


//Setps
//1)creating Form by using bootstarp
//2)next we create RegisterServlet
//here  read the form values
//load the jdbc driver--create connection --create table in workbench
//execute qUery

