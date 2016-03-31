package info.source4code.jsf.primefaces.controller.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import info.source4code.jsf.primefaces.model.CandidateProfile;
import info.source4code.jsf.primefaces.model.ProfilesModel;

import java.sql.*;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		

		System.out.println("Insert Image Example!");
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "socialnetwork";
		String userName = "root";
		String password = "root";
		Connection con = null;

		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url + dbName, userName, password);
			Statement st = con.createStatement();
			PreparedStatement ps = con
					.prepareStatement("select * from userprofile");
			//ps.setString(1, id);

			ResultSet rs = ps.executeQuery();

			ProfilesModel profiles = new ProfilesModel();
			while(rs.next())
			{
			CandidateProfile candidateProfile = new CandidateProfile();
			candidateProfile.setCompany(rs.getNString("company"));
			candidateProfile.setJobTitle("jobtitle");
			candidateProfile.setProfilePictureString("image");
			candidateProfile.setUsername("username");
			candidateProfile.setFirstName("firstname");
			candidateProfile.setLastName("lastname");
			profiles.getCandidateProfiles().add(candidateProfile);
			}
			
			ps.close();
			con.close();
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
