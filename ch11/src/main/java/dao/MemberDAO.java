package dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DBHelper;
import dto.MemberDTO;

public class MemberDAO extends DBHelper {

	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	private MemberDAO() {}
	
	// 로거 생성
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertMember(MemberDTO member) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("INSERT INTO `Member` VALUES(?,?,?,?,?,?)");
			logger.info("psmt");
			psmt.setString(1, member.getUid());
			psmt.setString(2, member.getName());
			psmt.setString(3, member.getHp());
			psmt.setString(4, member.getPos());
			psmt.setString(5, member.getDep());
			psmt.setString(6, member.getRdate());
			psmt.executeUpdate();
			
			System.out.println("DAO : " + member.getName());
			closeAll();
		}catch(Exception e) {
			logger.error("insertMember() - " + e.getMessage());
		}
		
	}
	public MemberDTO selectMember() {
		return null;}
	public List<MemberDTO> selectMembers() {
		List<MemberDTO> members = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("SELECT * FROM `Member`");
			logger.info("psmt");
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO member = new MemberDTO();
				member.setUid(rs.getString(1));
				member.setName(rs.getString(2));
				member.setHp(rs.getString(3));
				member.setPos(rs.getString(4));
				member.setDep(rs.getString(5));
				member.setRdate(rs.getString(6));
				members.add(member);
			}
			closeAll();
			
		}catch(Exception e) {
			//로그 출력
			logger.error("selectMembers() - " + e.getMessage());
		}
		return members;
	}
	public void updateMember() {}
	public void deleteMember() {}

}
