package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.House;
import com.revature.models.Resident;
import com.revature.util.ConnectionUtil;

/**
 * DAO - Data Access Object
 * DAOs are a particular kind of abstraction that define an object whose responsibility
 * is the retrieval and management of data persistence. 
 *
 */
public class HouseDao {
	
	private House extractHouse(ResultSet result) throws SQLException {
		int id = result.getInt("id");
		String buildName = result.getString("build_name");
		String apartmentNumber = result.getString("apartment_number");
		return new House(id, buildName, apartmentNumber);
	}
	
	private Resident extractResidents(ResultSet result) throws SQLException {
		int id = result.getInt("id");
		String name = result.getString("name");
		String email = result.getString("email");
		int rent = result.getInt("rent");
		int houseId = result.getInt("house_id");
		return new Resident(id, name, rent, houseId, email);
	}
	
	public House getHouse(int id) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT id, build_name, apartment_number FROM houses " +
					"WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			// Set PreparedStatement parameters
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				String buildName = result.getString("build_name");
				String apartmentNumber = result.getString("apartment_number");
				return new House(id, buildName, apartmentNumber);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}

	public House createHouse(House house) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO houses (build_name, apartment_number) " +
					" VALUES (?, ?) RETURNING *";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, house.getBuildName());
			statement.setString(2, house.getApartmentNumber());
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				return extractHouse(result);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Resident> getResidentsInHouse(House house) {
		List<Resident> residents = new ArrayList<>();
		try(Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM residents WHERE house_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, house.getId());
			
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				residents.add(extractResidents(result));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return residents;
	}
}
