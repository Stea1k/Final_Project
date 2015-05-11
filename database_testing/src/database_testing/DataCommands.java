package database_testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataCommands {
	private String dbURL = "";
	protected Statement sqlCom = null;
	protected Connection conn = null;
	
	private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static String protocol = "jdbc:derby:";
	private static String dbName = "Music";
	
	//root
	private static String USER;
	//p4ssw0rd
	private static String PASS;
	
	private boolean loggedIn = false;
	
	//Presets artist and title selections on the music viewer to false.
	//When the checkbox is selected, the switch will initiate.
	
	protected boolean artist = false;
	protected boolean title = false;
	
	protected void switchArtist(){
		if(artist){
			artist=false;
		}else{
			artist = true;
		}
	}
	protected void switchTitle(){
		if(title){
			title=false;
		}else{
			title = true;
		}
	}
	protected void setUser(String User){
		this.USER = User;
	}
	protected void setPass(String Pass){
		this.PASS = Pass;
	}
	//Sets up connection to derby database with USER and PASS
	public void DataConnect(String User, String Pass) throws ClassNotFoundException{
		try{
			setUser(User);
			setPass(Pass);
			Class.forName(driver);
			conn = DriverManager.getConnection(protocol + dbName + ";create=true", USER, PASS);
			try{
			sqlCom = conn.createStatement();
			}catch(SQLException e){
				e.printStackTrace(System.err);
			}
			System.out.println("connection established");
		}catch(SQLException e){
			e.printStackTrace(System.err);
		}
	}
//	public ResultSet getDataFromTable(String Table){
//		ResultSet dataFromTable = null;
//		try{
//			sqlCom = conn.createStatement();
//			dataFromTable = sqlCom.executeQuery("SELECT * FROM" + Table);
//		}catch(Exception e){
//			e.printStackTrace(System.err);
//		}
//		return dataFromTable;
//	}
//	public ArrayList<> getFromMultiple(String Table1, String Table2, String SharedCol){
//		ResultSet dataFromTable = null;
//		try{
//			sqlCom = conn.createStatement();
//			dataFromTable = sqlCom.executeQuery(
//					"SELECT * FROM " +Table1+" join "+Table2+
//					" on "+ Table1+"."+SharedCol+ "="+ Table2+"."+SharedCol);
//		}catch(Exception e){
//			e.printStackTrace(System.err);
//		}
//		return dataFromTable;
//	}
	
	//Login command for logging into software as a given user.
	//TODO How does this differ from getting a connection?
	
	public boolean login(String User, String Pass) throws SQLException{
		ResultSet dataFromTable = null;
		boolean found = false;
		try{
			sqlCom = conn.createStatement();
			dataFromTable = sqlCom.executeQuery(
					"SELECT * "
					+ "FROM USERS "
					+ "WHERE UserName="+"'"+User+"'"
					+ " and UserPass="+"'" +Pass+"'"
					);
			sqlCom.close();
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
		if(dataFromTable.next()){
			found = true;
		}
		dataFromTable.close();
		return found;
	}

	public void newLogin(Users user){
		try{
			sqlCom = conn.createStatement();
			sqlCom.executeQuery(
					"INSERT INTO USERS VALUES"
					+"("
					+user.getUserName()+","
					+user.getUserType()+","
					+user.getUserPass()+","
					+user.getUserPhone()+","
					+user.getUserEmail()							
					+")");
			sqlCom.close();
			try{
				sqlCom = conn.createStatement();
				sqlCom.executeQuery("Call SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY"
						+ "(derby.user."+user.getUserName()+","+user.getUserPass()+")");
				sqlCom.close();
			}catch(Exception e){
				e.printStackTrace(System.err);
			}
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
	}
//	public void addMusicRecords()
	public ArrayList<Music> searchRecords(String entry) throws SQLException{
		ArrayList<Music> records = new ArrayList<Music>();
		try{
			sqlCom = conn.createStatement();
			ResultSet dataFromRecords = null;
			if(artist && !title){
				dataFromRecords = sqlCom.executeQuery(
						"SELECT title,artist,price "
						+ "FROM RECORDS WHERE artist LIKE ("+entry+")");
			}else if(!artist && title){
				dataFromRecords = sqlCom.executeQuery(
						"SELECT title,artist,price "
						+ "FROM RECORDS WHERE title LIKE ("+entry+")");
			}else if(artist && title){
				dataFromRecords = sqlCom.executeQuery(
						"SELECT title,artist,price "
						+ "FROM RECORDS WHERE title LIKE ("+entry+")"
								+ " OR artist LIKE ("+entry+")");
			}
			while(dataFromRecords.next()){
				Music Record = new Music(dataFromRecords.getString("title"),
										 dataFromRecords.getString("artist"),
										 dataFromRecords.getFloat("price"));
				records.add(Record);
			}
			dataFromRecords.close();
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
		sqlCom.close();
		return records;
	}
}
