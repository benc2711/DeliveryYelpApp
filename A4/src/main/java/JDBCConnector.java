import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//class Reservation {
//    int restaurantId;
//    String reservationDate;
//    String reservationTime;
//    String notes;
//
//    // Constructor
//    public Reservation(int restaurantId, String reservationDate, String reservationTime, String notes) {
//        this.restaurantId = restaurantId;
//        this.reservationDate = reservationDate;
//        this.reservationTime = reservationTime;
//        this.notes = notes;
//    }
//
//    // Getters and Setters
//    // (Add here if necessary)
//}


public class JDBCConnector {
	//"OpenAI. (2023). Java Servlet Coding and Implementation Details. ChatGPT Session. General Reference"

	
	public void insertNewUser(String username, String password, String email) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
           
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/josetables?user=root&password=reddog44");
            System.out.println("CONNECTED");
            st = conn.prepareStatement("INSERT INTO Users (username, password, email) VALUES (?, ?, ?)");
            st.setString(1, username);
            st.setString(2, password);
            st.setString(3, email);

            st.executeUpdate();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
        }catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally {
           
            try {
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException sqle) {
                System.out.println(sqle.getMessage());
            }
        }
    }

	
	public boolean loginUser(String username, String password) {
	    Connection conn = null;
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost/josetables?user=root&password=reddog44");
	        st = conn.prepareStatement("SELECT * FROM Users WHERE username = ? AND password = ?");
	        st.setString(1, username);
	        st.setString(2, password);
	        rs = st.executeQuery();

	        return rs.next(); 
	    } catch (ClassNotFoundException e) {
	        System.out.println("JDBC Driver not found: " + e.getMessage());
	    } catch (SQLException sqle) {
	        System.out.println(sqle.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (st != null) st.close();
	            if (conn != null) conn.close();
	        } catch (SQLException sqle) {
	            System.out.println(sqle.getMessage());
	        }
	    }
	    return false; 
	}

	public boolean checkUserExists(String username) {
	    Connection conn = null;
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost/josetables?user=root&password=reddog44");
	        st = conn.prepareStatement("SELECT username FROM Users WHERE username = ?");
	        st.setString(1, username);
	        rs = st.executeQuery();

	        return rs.next(); 
	    } catch (ClassNotFoundException e) {
	        System.out.println("JDBC Driver not found: " + e.getMessage());
	    } catch (SQLException sqle) {
	        System.out.println(sqle.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (st != null) st.close();
	            if (conn != null) conn.close();
	        } catch (SQLException sqle) {
	            System.out.println(sqle.getMessage());
	        }
	    }
	    return false; 
	}


	
}
	