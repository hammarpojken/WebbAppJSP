import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




public class LoginDao {
	
	
	
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

}
