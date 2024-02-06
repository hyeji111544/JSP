package kr.co.jboard1.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.co.jboard1.db.DBHelper;
import kr.co.jboard1.db.SQL;
import kr.co.jboard1.dto.ArticleDTO;

public class ArticleDAO extends DBHelper {
	
	private static ArticleDAO instance = new ArticleDAO();
	public static ArticleDAO getInstance() {
		return instance;
	}
	
	private ArticleDAO() {}
	
	//기본 CRUD 메서드
	public void insertArticle(ArticleDTO article) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, article.getTitle());
			psmt.setString(2, article.getContent());
			psmt.setString(3, article.getWriter());
			psmt.setString(4, article.getRegip());
			psmt.executeUpdate();
			closeAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public ArticleDTO selectArticle(int no) {
		return null;
	}
	
	
	public List<ArticleDTO> selectArticles() {
		List<ArticleDTO> articles = new ArrayList<>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(SQL.SELECT_ARTICLES);
			
			while(rs.next()) {
				ArticleDTO dto = new ArticleDTO();
				dto.setNo(rs.getInt(1));
				dto.setTitle(rs.getString(2));
				dto.setComment(rs.getInt(3));
				dto.setWriter(rs.getString(4));
				dto.setRdate(rs.getString(5));
				
				articles.add(dto);
			}
			closeAll();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return articles;
	}
	public void updatetArticle(ArticleDTO article) {}
	public void deletetArticle(int no) {}
}
