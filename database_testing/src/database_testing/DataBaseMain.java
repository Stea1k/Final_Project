package database_testing;

import java.sql.SQLException;
import java.util.ArrayList;

public class DataBaseMain extends DataCommands{
	 ///////////////////////////////
	//TODO create array of strings for each table and loop through them using each as a SQL statement.
	//need to cut down on redundancy.
	///////////////////////////////
	static ArrayList<String> sqlQueries = new ArrayList<String>();
	private static String createTable;
	protected static String setTableToCreate(String command){
		return createTable = command;
	}
	protected static void assignTables(){
		setTableToCreate( 
				"CREATE TABLE USERS ("
				+ "userID int not null AUTO_INCREMENT primary key, "
				+ "userName varchar(20) not null, "
				+ "userAuth int not null DEFAULT '1', "
				+ "userPass varchar(20) not null, "
				+ "userPhone varchar(10) not null,"
				+ "userEmail varchar(20)"
				+ ")");
		sqlQueries.add(createTable);
		setTableToCreate(
				"CREATE TABLE COSIGNOR"
				+ "("
				+ "cosignorID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "cosignorName varchar(20) NOT NULL,"
				+ "cosignorPhone varchar(10) NOT NULL"
				+ ")");
		sqlQueries.add(createTable);
		setTableToCreate(
				"CREATE TABLE RECORDS"
				+ "("
				+ "musicID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "title varchar(20) NOT NULL,"
				+ "artist VARCHAR(20) NOT NULL,"
				+ "price float NOT NULL,"
				+ "dateAdded DATE,"
				+ "cosignorID INT REFERENCES COSIGNOR(cosignorID)"
				+ ")");
		sqlQueries.add(createTable);
		setTableToCreate(
				"CREATE TABLE SALES"
				+ "("
				+ "salesID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "date DATE,"
				+ "musicID INT REFERENCES MUSIC(musicID),"
				+ "userID INT REFERENCES USERS(userID)"
				+ ")");
		sqlQueries.add(createTable);
	}
	
	public void createDataTables(){
		try {
			DataConnect("root","p4ssw0rd");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String i: sqlQueries){
			try{
				sqlCom = conn.createStatement();
				Object Table = sqlCom.executeQuery(i);
			}catch(SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try{
				sqlCom.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
