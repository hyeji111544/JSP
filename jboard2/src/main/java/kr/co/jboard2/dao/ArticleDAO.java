package kr.co.jboard2.dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.db.DBHelper;
import kr.co.jboard2.db.SQL;
import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.FileDTO;

public class ArticleDAO extends DBHelper{
	
	private static ArticleDAO instance = new ArticleDAO();
	public static ArticleDAO getInstance() {
		return instance;
	}
	private ArticleDAO() {}
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int insertArticle(ArticleDTO articleDTO) {
		
		int pk = 0;
		
		try {
			conn = getConnection();
			
			// INSERT가 실행되고 자동 생성되는 PK 값을 리턴하는 옵션.
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE, Statement.RETURN_GENERATED_KEYS);
			psmt.setString(1, articleDTO.getTitle());
			psmt.setString(2, articleDTO.getContent());
			psmt.setInt(3, articleDTO.getFile());
			psmt.setString(4, articleDTO.getWriter());
			psmt.setString(5, articleDTO.getRegip());
			logger.info("insertArticle : " + psmt);
			
			// INSERT 실행
			psmt.executeUpdate();
			
			// 생성된 PK 가져오기.
			rs = psmt.getGeneratedKeys();
			if(rs.next()) {
				pk = rs.getInt(1);
			}
			
			closeAll();
		}catch(Exception e) {
			logger.error("insertArticle : " + e.getMessage());
		}
		
		return pk;
	}
	public ArticleDTO selectArticle(String no) {
		ArticleDTO article = null;
		List<FileDTO> files = new ArrayList<>();
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLE);
			psmt.setString(1, no);
			psmtEtc1 = conn.prepareStatement(SQL.UPDATE_HIT_COUNT);
			psmtEtc1.setString(1, no);
			
			System.out.println("psmt : " +psmt);
			rs = psmt.executeQuery();
			psmtEtc1.executeUpdate();
			while(rs.next()) {
				// 글 하나당 파일이 여러개 일 필요가 없기때문에
				if(article == null) {
					article = new ArticleDTO();
					article.setNo(rs.getInt(1));
					article.setParent(rs.getInt(2));
					article.setComment(rs.getInt(3));
					article.setCate(rs.getString(4));
					article.setTitle(rs.getString(5));
					article.setContent(rs.getString(6));
					article.setFile(rs.getInt(7));
					article.setHit(rs.getInt(8));
					article.setWriter(rs.getString(9));
					article.setRegip(rs.getString(10));
					article.setRdate(rs.getString(11));
				}
				
			
				FileDTO fileDTO = new FileDTO();
				fileDTO.setFno(rs.getInt(12));
				fileDTO.setAno(rs.getInt(13));
				fileDTO.setoName(rs.getString(14));
				fileDTO.setsName(rs.getString(15));
				fileDTO.setDownload(rs.getInt(16));
				fileDTO.setRdate(rs.getString(17));
				files.add(fileDTO);
			}
			System.out.println(psmt);
			article.setFileDTOs(files);
			
			conn.commit();
			closeAll();
			
		}catch (Exception e) {
			logger.error("selectArticle : " + e.getMessage());
		}
		return article;
	}
	
	public List<ArticleDTO> selectArticles(int start) {
		List<ArticleDTO> articles = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLES + SQL.SELECT_ARTICLES_ORDER_LIMIT);
			psmt.setInt(1, start);
			logger.info("psmt");
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleDTO article = new ArticleDTO();
				article.setNo(rs.getString(1));
				article.setParent(rs.getInt(2));
				article.setComment(rs.getInt(3));
				article.setCate(rs.getString(4));
				article.setTitle(rs.getString(5));
				article.setContent(rs.getString(6));
				article.setFile(rs.getInt(7));
				article.setHit(rs.getInt(8));
				article.setWriter(rs.getString(9));
				article.setRegip(rs.getString(10));
				article.setRdate(rs.getString(11));
				article.setNick(rs.getString(12));
				
				
				articles.add(article);
			}
			
			closeAll();
			
		}catch (Exception e) {
			logger.error("selectArticles : " + e.getMessage());
		}
		
		return articles;
	}
	
	public int seletCountTotal() {
		int total = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL);
			rs = psmt.executeQuery();
				if(rs.next()) {
					total = rs.getInt(1);
				}
				closeAll();
				
		}catch (Exception e) {
			logger.error("selectArticlesTotal : " + e.getMessage());
		}
		
		return total;
	}
	
	
	public void updateArticle(ArticleDTO article) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE);
			psmt.setString(1, article.getTitle());
			psmt.setString(2, article.getContent());
			psmt.setInt(3, article.getNo());
			psmt.executeUpdate();
			closeAll();
		}catch (Exception e) {
			logger.error("updateArticle : " + e.getMessage());
		}
	}
	public void deleteArticle(String no, String ano) {
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			psmt = conn.prepareStatement(SQL.DELETE_FILE);
			psmt.setString(1, ano);
			psmtEtc1 = conn.prepareStatement(SQL.DELETE_ARTICLE);
			psmtEtc1.setString(1, no);
			psmtEtc1.setString(2, no);
			
			psmt.executeUpdate();
			psmtEtc1.executeUpdate();
			
			conn.commit();
			closeAll();
		}catch (Exception e) {
			logger.error("deleteArticle : " + e.getMessage());
		}
	}
	

	
	// 코멘트 처리 
	public void insertComment(ArticleDTO comment) {
			try {
				conn = getConnection();
				conn.setAutoCommit(false);
				
				psmt = conn.prepareStatement(SQL.INSERT_COMMENT);
				psmt.setInt(1, comment.getParent());
				psmt.setString(2, comment.getContent());
				psmt.setString(3, comment.getWriter());
				psmt.setString(4, comment.getRegip());
				
				psmtEtc1 = conn.prepareStatement(SQL.UPDATE_COMMENT_PLUS);
				psmtEtc1.setInt(1, comment.getParent());
				
				psmt.executeUpdate();
				psmtEtc1.executeUpdate();
				
				conn.commit();
				closeAll();
			}catch (Exception e) {
				logger.error("insertComment : "+ e.getMessage());
			}
	}
	
	public List<ArticleDTO> selectComments(String parent){
			
		List<ArticleDTO> comments = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COMMENTS);
			psmt.setString(1, parent);
			logger.info("selectComments : " + psmt);
			
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				ArticleDTO comment = new ArticleDTO();
				comment.setNo(rs.getInt(1));
				comment.setParent(rs.getInt(2));
				comment.setContent(rs.getString(6));
				comment.setWriter(rs.getString("writer"));
				comment.setRegip(rs.getString("regip"));//칼럼명 작성 가능.
				comment.setRdate(rs.getString("rdate"));
				comment.setNick(rs.getString("nick"));
				
				comments.add(comment);
				System.out.println("comment : "+ comment);
			}
			
			closeAll();
			
		}catch (Exception e) {
			logger.error("selectComments : " + e.getMessage());
		}
		return comments;
	}
	
	public void deleteComment(String no, String parent) {
		System.out.println("delectComment no: " + no);
		System.out.println("delectComment parent: " + parent);
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			psmt = conn.prepareStatement(SQL.DELETE_COMMENT);
			psmt.setString(1, no);
			System.out.println("delete_comment: " +psmt);
			
			psmtEtc1 = conn.prepareStatement(SQL.UPDATE_COMMENT_MINUS);
			psmtEtc1.setString(1, parent);
			System.out.println("update_comment_minus: " +psmtEtc1);
			
			psmt.executeUpdate();
			psmtEtc1.executeUpdate();
			
			conn.commit();
			closeAll();
		}catch (Exception e) {
			logger.error("deleteComment : " + e.getMessage());
		}
	}
}
