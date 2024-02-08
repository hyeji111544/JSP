package kr.co.jboard1.db;

public class SQL {

	public static final String SELECT_TERMS = "SELECT * FROM `terms`";
	public static final String INSERT_USER = "INSERT INTO `user` SET "
			+ "`uid`=?,"
			+ "`pass`=SHA2(?, 256), "
			+ "`name`=?, "
			+ "`nick`=?, "
			+ "`email`=?, "
			+ "`hp`=?, "
			+ "`regip`=?, "
			+ "`rdate`= NOW()";
	public static final String SELECT_USER_FOR_LOGIN = "SELECT * FROM `User` WHERE `uid`=? AND `pass`=SHA2(?, 256)";
	
	public static final String INSERT_ARTICLE = "INSERT INTO `Article` SET "
												+ "`title`=?, "
												+ "`content`=? , "
												+ "`writer`=?, "
												+ "`regip`=?, "
												+ "`rdate`=NOW()";
	public static final String INSERT_COMMENT = "INSERT INTO `Article` SET "
												+ "`parent`=?, "
												+ "`content`=? , "
												+ "`writer`=?, "
												+ "`regip`=?, "
												+ "`rdate`=NOW()";
	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(*) FROM `Article` WHERE `parent`=0;";
	
	public static final String SELECT_ARTICLES = "SELECT a.*, b.nick "
												+ "FROM `article` AS a "
												+ "JOIN `user` AS b "
												+ "ON a.writer = b.uid "
												+ "WHERE `parent`=0 "
												+ "ORDER BY `no` DESC "
												+ "LIMIT ?, 10";
	
	public static final String SELECT_COMMENTS = "SELECT a.*, b.nick "
												+ "FROM `Article` AS a "
												+ "JOIN `user` AS b ON a.writer = b.uid "
												+ "WHERE `parent`=? "
												+ "ORDER BY `no` ASC";
	
	public static final String SELECT_ARTICLE = "SELECT * FROM `Article` WHERE `no`=? ";
	
	public static final String UPDATE_HIT_COUNT = "UPDATE `Article` SET `hit` = `hit` + 1 WHERE `no`=?";
	
	public static final String UPDATE_ARTICLE = "UPDATE `Article` SET `title`=?, `content`=? Where `no`=?" ;
	public static final String UPDATE_COMMENT_PLUS = "UPDATE `Article` SET `comment` = `comment` + 1 WHERE `no`=?" ;
	public static final String UPDATE_COMMENT_MINUS = "UPDATE `Article` SET `comment` = `comment` - 1 WHERE `no`=?" ;
	public static final String DELETE_ARTICLE = "DELETE FROM `Article` WHERE `no`=? or `parent`=?";
	public static final String DELETE_COMMENT = "DELETE FROM `Article` WHERE `no`=?" ;
	public static final String MODIFY_COMMENT = "UPDATE `Article` SET `content`=? WHERE `parent`=? and `no`=?" ;
	
	
}