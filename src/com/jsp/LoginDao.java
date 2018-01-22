package com.jsp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;




public class LoginDao {
	
	public ObservableList<Patient> data = FXCollections.observableArrayList();
	
	public boolean check(String prof, String uname, String password) {
		String query = "select * from "+prof+" where username=? and password=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false", "root", "root");
			PreparedStatement st = null;
			if(prof != null) {
				st = con.prepareStatement(query);
				st.setString(1, uname);
				st.setString(2, password);
				System.out.print(st.toString());
				
			}
			
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getString("username"));
				System.out.println(rs.getString("password"));
				System.out.println(rs.getString("lname"));
				return true;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
	
	
	
	public void getPatients() throws ClassNotFoundException{
		Connection con;
		ResultSet rs;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false", "root", "root");
		Statement st = con.createStatement();
		 rs = st.executeQuery("SELECT * FROM mydb.patient;");
		 
		 while(rs.next()){
			 data.add(new Patient(
					 rs.getLong("ssn"),
					 rs.getString("fname"),
					 rs.getString("lname"),
					 rs.getLong("phone"),
					 rs.getString("username"),
					 rs.getString("password"),
					 rs.getString("adress"),
					 rs.getInt("zipcode"),
					 rs.getString("role"),
					 rs.getLong("doctorid"),
					 rs.getString("gender"),
					 rs.getInt("status_patient"),
					 rs.getDate("checkin_date"),
					 rs.getTime("checkin_date"),
					 rs.getDate("checkout_date"),
					 rs.getTime("checkout_date"),
					 rs.getInt("room"),
					 rs.getString("blood_type")));
			 
			 
		 }

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		

	}
	
	
	}
	
	public void updatePerson(String fname, String lname, String adress, long ssn){
		String query = "UPDATE mydb.resultcard SET fname =?, lname=?, adress=? where ssn=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false", "root", "root");
			PreparedStatement st = null;
			if(prof != null) {
				st = con.prepareStatement(query);
				st.setString(1, fname);
				st.setString(2, lname);
				st.setString(3, adress);
				st.setLong(4, ssn);
				
				
			}
		}
			catch(Exception e) {
				
			}
		
		
	}
	
}
	

	
