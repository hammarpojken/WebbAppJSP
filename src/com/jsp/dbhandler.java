package com.jsp;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class dbhandler {
	
	
	
	//--------------------------------------- LOGIN METHODS--------------------------------------------
	
	public static ResultSet getUser(String proffession, String userName, String passWord) throws SQLException {
		ResultSet rs;
		
		try {
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false", "root", "root");
			Statement st = con.createStatement();
			
			if (proffession == "Staff") {
				 rs = st.executeQuery("select * FROM mydb.staff WHERE staff.username = '" + userName + "' AND staff.password = " + passWord + ";");
				 //con.close();
				 return rs;
				}
			
			else if (proffession == "Patient") {
				rs = st.executeQuery("select * FROM mydb.patient WHERE patient.username = '" + userName + "' AND patient.password = " + passWord + ";");
				
				return rs;
			}
			
		} 
		catch (SQLException e) {
		
			
			return rs = null;
			
		}
		
		return rs = null;
	}
	
	//-----------------------------------DOCTOR METHODS----------------------------------------------------------
	public static ObservableList<Patient> getPatients(){
		ObservableList<Patient> data = FXCollections.observableArrayList();
		Connection con;
		ResultSet rs;
	try {
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
	return data;
	
	}
	
	public static ResultSet getResultCard(long ssn) {
		Connection con;
		try {
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/projecthospita?autoReconnect=true&useSSL=false", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * FROM projecthospita.resultcard WHERE resultcard.patientssn = " + ssn);
			 if(rs.next() == true) {
				 
				 return rs;
			 }
			 
				  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	

	}
	
	public static ResultSet getPatientInfo(long ssn) {
		Connection con;
		try {
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/projecthospita?autoReconnect=true&useSSL=false", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select projecthospita.patient.*, projecthospita.staff.fname AS doctorfname, projecthospita.staff.lname AS doctorlname"
					+ " from projecthospita.patient left join projecthospita.staff on patient.doctorid = staff.staffid WHERE patient.ssn = " + ssn );
			 if(rs.next() == true) {
				 
				 return rs;
			 }
			 
				  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

	}
	
	public static void setResultCard(String disease, String medicine, String test, String remark, long ssn) {
		
		Connection con;
		try {
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/projecthospita?autoReconnect=true&useSSL=false", "root", "root");
			Statement st = con.createStatement();
			
			st.executeUpdate("INSERT INTO projecthospita.resultcard (patientssn, disease, medicine, test, remark) VALUES (" + ssn + ",'" + disease + "','" + medicine + "','" + test + "','" + remark +"')");
		}
			catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void updateResultCard(int rcId, long patientId, String diagnose, String remark) {
		
		Connection con;
		try {
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false", "root", "root");
			Statement st = con.createStatement();
			
			if (resultcardExist(patientId)) {
				st.executeUpdate("UPDATE mydb.resultcard SET idresultcard = " + rcId + ", patientid = " + patientId
						+ ", diagnose = '" + diagnose + "', remark = '" + remark + "' WHERE patientid = "+ patientId);
			}
			
		}
			catch (SQLException e) {
				e.printStackTrace();
			}

	}
	public static void updatePatient(String fname, String lname, long ssn, String adress, int zip, long phone, String bloodtype,
			String gender, int oldroom, int newroom) {
	
		Connection con;
		try {
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false", "root", "root");
			Statement st = con.createStatement();
			
			if(oldroom == newroom) {
				st.executeUpdate("UPDATE mydb.patient SET ssn = " + ssn + ", fname = '" + fname + "', lname = '" + lname + "', phone = " + phone + 
						", adress = '" + adress + "', zipcode = "+ zip +", gender = '" + gender + "', blood_type = '" + bloodtype + "' WHERE ssn = " + ssn);
				
			} else {
				dbhandler.addPatientToRoom(newroom);
				
				dbhandler.dischargePatientRoom(oldroom);
				st.executeUpdate("UPDATE mydb.patient SET ssn = " + ssn + ", fname = '" + fname + "', lname = '" + lname + "', phone = " + phone + 
						", adress = '" + adress + "', zipcode = "+zip+", gender = '" +gender+ "',blood_type = '"+bloodtype+ "', room = " + newroom
								+ " WHERE ssn = " + ssn);
			}
				
			
		}
			catch (SQLException e) {
				e.printStackTrace();
	
			}

	}
	public static void addPatientToRoom(int roomid){
		Connection con;
		ResultSet rs;
		try {
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false", "root", "root");
			Statement st = con.createStatement();
			 rs = st.executeQuery("SELECT available_slots FROM mydb.room WHERE idroom =" + roomid);
			 rs.next();

			 int slots = rs.getInt("available_slots") - 1;
			 st.executeUpdate("UPDATE mydb.room SET available_slots = " + slots +" WHERE idroom =" + roomid);

			
			
			
		}
			catch (SQLException e) {
				e.printStackTrace();
	
			}

		
	}
	public static void dischargePatientRoom(int roomid){
		Connection con;
		ResultSet rs;
		try {
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false", "root", "root");
			Statement st = con.createStatement();
			 rs = st.executeQuery("SELECT available_slots FROM mydb.room WHERE idroom =" + roomid);
			 rs.next();

			 
			 int slots = rs.getInt("available_slots") +1;
			 st.executeUpdate("UPDATE mydb.room SET available_slots = " + slots + " WHERE idroom =" + roomid);
			
			
			
		}
			catch (SQLException e) {
				e.printStackTrace();
	
			}

		
	}
	public static boolean resultcardExist(long ssn) {
		
		Connection con;
		ResultSet rs;
		try {
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false", "root", "root");
			Statement st = con.createStatement();
				
			rs = st.executeQuery("SELECT * FROM mydb.resultcard WHERE patientid = " + ssn);
			if (rs.next()) {
				con.close();
				return true;
			}
		
		}
		
			catch (SQLException e) {
				
				e.printStackTrace();
				return false;
				
	
			}
		
		return false;
		
	}
	public static void assignDoctor(long ssn, long docid) {
		
		Connection con;
		try {
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false", "root", "root");
			Statement st = con.createStatement();
				
			st.executeUpdate("UPDATE mydb.patient SET doctorid = " + docid + " WHERE patient.ssn = " + ssn);
		}
			catch (SQLException e) {
				e.printStackTrace();
	
			}
		
	}
	public static List<Doctor> getDoctors() {
		
		Connection con;
		ResultSet rs;
		List<Doctor> choices = new ArrayList<Doctor>();
		
		
		try {
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false", "root", "root");
			Statement st = con.createStatement();
			rs = st.executeQuery("select * FROM mydb.staff WHERE role = 'doctor'");
			
			if(rs.next() == true) {
				rs.beforeFirst();
				
				while(rs.next()) {
					choices.add(new Doctor(rs.getLong("staffid"), rs.getString("fname"), rs.getString("lname"), rs.getString("role")));
				}
				
			} else {
				choices = null;
			}
										
				
				  
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
		}
		return choices;
	}
	
	public static String getDoctor(long docid) {
		
		Connection con;
		ResultSet rs;
		String docname = "No assigned doctor";
		try {
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false", "root", "root");
			Statement st = con.createStatement();
			
				
			rs = st.executeQuery("SELECT fname, lname FROM mydb.staff WHERE staff.staffid = " + docid);
			if (rs.next()) {
				return rs.getString("fname") + " " + rs.getString("lname");
			}
			
			
		
		}catch (SQLException e) {
			e.printStackTrace();	
	
			}
		return docname;
		
	}
		
	
	public static void updateJournal (String fname, String lname, long ssn, String adress, int zip, long phone,
			String bloodtype, String gender, int oldroom, int newroom, int rcid, String diagnose, String remark) {
		
		dbhandler.updatePatient(fname, lname, ssn, adress, zip, phone, bloodtype, gender, oldroom, newroom);
	
		dbhandler.updateResultCard(rcid, ssn, diagnose, remark);
		
		
	}
	
	// --------------------------------------------------------JOURNAL METHODS-----------------------------------------------------------------------------------
	
		public static String getCity(int zip) {
		
			
		Connection con;
		ResultSet rs;
		String result = "N/A";
			
			try {
				con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false", "root", "root");
				Statement st = con.createStatement();
				rs = st.executeQuery("SELECT name FROM mydb.city WHERE city.idcity =" + zip);
				
				if(rs.next()) {
					result = rs.getString("name");
	
				}
				con.close();
				
				} catch (SQLException e) {
					
				} finally {
				
					
				}
				
				return result;
				
			} 		
		
		public static ObservableList<Medicine> getMedicine(long ssn){
			ObservableList<Medicine> presc = FXCollections.observableArrayList();
			Connection con;
			ResultSet rs;
			
			try {
				con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false", "root", "root");
				Statement st = con.createStatement();
				 rs = st.executeQuery("SELECT prescription.idprescription, prescription.medicine, medicine.name, medicine.type, medicine.volume, medicine.price FROM mydb.prescription JOIN mydb.medicine ON prescription.medicine = medicine.idmedicine WHERE prescription.patientid = " +ssn);
				 
				 while(rs.next()){
					 presc.add(new Medicine(
									 rs.getInt("medicine"),
									 rs.getString("name"),
									 rs.getString("type"),
									 rs.getString("volume"),
									 rs.getDouble("price")));
					 
				}
				 con.close();
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(); 
				}
			
				return presc;
				}
		
		public static ObservableList<Medicine> getMedicine(){
			ObservableList<Medicine> presc = FXCollections.observableArrayList();
			Connection con;
			ResultSet rs;
			
			try {
				con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false", "root", "root");
				Statement st = con.createStatement();
				 rs = st.executeQuery("SELECT * FROM mydb.medicine" );
				 
				 while(rs.next()){
					 presc.add(new Medicine(
									 rs.getInt("idmedicine"),
									 rs.getString("name"),
									 rs.getString("type"),
									 rs.getString("volume"),
									 rs.getDouble("price")));
					 
				}
				 con.close();
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(); 
				}
			
				return presc;
				}
				
		public static void prescribMed(Prescription p) {
			Connection con;
			
			try {
				con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false", "root", "root");
				Statement st = con.createStatement();
				st.execute("INSERT INTO mydb.prescription (patientid, medicine, prescription_info, withdrawl_amount, doctorid) VALUES (" + p.getPatientId() + "," + p.getMedicineId() + ",'" + p.getPrescriptionInfo() + "'," + p.getWithdrawlAmount() + "," + p.getDoctorId() + ")");
				 
				 
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(); 
				}
			
		}
		
		public static ObservableList<Test> getTest(long ssn){
			ObservableList<Test> testlist = FXCollections.observableArrayList();
			Connection con;
			ResultSet rs;
			
			try {
				con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false", "root", "root");
				Statement st = con.createStatement();
				 rs = st.executeQuery("SELECT * FROM mydb.tests WHERE tests.patientid = " + ssn);
				 
				 while(rs.next()){
					 testlist.add(new Test(
							 rs.getInt("idtests"),
							 rs.getLong("patientid"),
							 rs.getString("type")));
				}
				 con.close();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(); 
				}
				return testlist;
}
		
		public static List<ResultCard> getResultCardInfo(long ssn){
			List<ResultCard> rc = new ArrayList<ResultCard>();
			Connection con;
			ResultSet rs;
				
			try {
				con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false", "root", "root");
				Statement st = con.createStatement();
				rs = st.executeQuery("SELECT * FROM mydb.resultcard WHERE resultcard.patientid =" + ssn);
				
				while(rs.next()) {
					rc.add(new ResultCard(
							rs.getInt("idresultcard"),
							rs.getLong("patientid"),
							rs.getString("diagnose"),
							rs.getString("remark")));
				

				}
				con.close();
				
				} catch (SQLException e) {
					
				} finally {
					
				}
				
				return rc;
				
		}
		public static ObservableList<String> getRooms(){
			ObservableList<String> rc = FXCollections.observableArrayList();
			Connection con;
			ResultSet rs;
			
			try {
				con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false", "root", "root");
				Statement st = con.createStatement();
				rs = st.executeQuery("SELECT * FROM mydb.room");
				
				while(rs.next()) {
					String temp;
					temp = rs.getInt("idroom") + " " + "-" + " "+ rs.getString("type") + "  " + rs.getInt("available_slots") + "/" + rs.getInt("size");
					rc.add(temp);
				}
				
			}catch (SQLException k) {
				return rc = null;
				
			}
			return rc;
			
		}
		
// ----------------------------------------------RECEPTIONIST METOHDS---------------------------------------------
		
		public static void savePatient(Patient p) {
			Connection con;
			try {
				con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false", "root", "root");
				Statement st = con.createStatement();
				
				
				st.execute("INSERT INTO mydb.patient (ssn, fname, lname, phone, adress, zipcode, gender, status_patient, blood_type) VALUES"
						+ " ("+ p.getSsn() + ",'" + p.getFname() + "','" + p.getLname() + "'," + p.getPhone() + ",'" + p.getAdress()
						+ "'," + p.getZipcode() + ",'" + p.getGender() + "'," + 1 + ",'" + p.getBlood_type() + "')"); 
			}
					
	
				catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		//--------------------------------SEARCH METHODS----------------------------------
		
		public static ObservableList<Patient> getPatients(String search) {
			ObservableList<Patient> data = FXCollections.observableArrayList();
			Connection con;
			ResultSet rs;
			
		try {
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false", "root", "root");
			Statement st = con.createStatement();
			 rs = st.executeQuery("SELECT * FROM mydb.patient WHERE patient.ssn LIKE '" + search + "%' or patient.fname LIKE '" + search + "%'");
			 
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
		return data;
		
	}
		public static ObservableList<Prescription> getPatientPrescriptions(Long ssn) {
			
			ObservableList<Prescription> data = FXCollections.observableArrayList();
			Connection con;
			ResultSet rs;
			
		try {
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false", "root", "root");
			Statement st = con.createStatement();
			 rs = st.executeQuery("SELECT * FROM mydb.prescription join medicine on prescription.medicine = medicine.idmedicine where prescription.patientid = " + ssn);
			 
			 while(rs.next()){
				 data.add(new Prescription(rs.getInt("idprescription"),rs.getLong("patientid"), rs.getLong("doctorid"), rs.getInt("medicine"),rs.getString("prescription_info"), rs.getInt("withdrawl_amount"),
						 new Medicine(rs.getInt("idmedicine"), rs.getString("name"), rs.getString("type"), rs.getString("volume"), rs.getDouble("price"))));
						
				 
				 
			 }
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			

		}
		return data;
		
	}
			
}
		
		

			
				
