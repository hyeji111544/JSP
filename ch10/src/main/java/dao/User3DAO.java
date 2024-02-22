package dao;

import java.util.List;

import db.DBHelper;
import dto.User3DTO;

public class User3DAO extends DBHelper {
	
	private static User3DAO instance = new User3DAO();
	public static User3DAO getInstance() {
		return instance;
	}

	public void insertUser3(User3DTO user) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("INSERT INTO `User3` VALUES (?,?,?,?,?)");
			psmt.setString(1, user.getUid());
			psmt.setString(2, user.getName());
			psmt.setString(3, user.getBirth());
			psmt.setString(4, user.getHp());
			psmt.setString(5, user.getAddr());
			
			psmt.executeUpdate();
			closeAll();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public User3DTO SelectUser3(String uid) {
	
		return null;
	}
	
	public List<User3DTO> selectUser3s() {
		
		return null;
	}
	public void updateUser3(User3DTO user) {
		
	}
	public void deleteUser3(String uid) {
		
	}

}
