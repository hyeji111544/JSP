package kr.co.jboard2.db;

public class SQL {

	public static final String SELECT_TERMS = "SELECT * FROM `terms`";
	public static final String SELECT_COUNT_USER = "SELECT COUNT(*) FROM `User` ";
	public static final String WHERE_UID	= "WHERE `uid`=?";
	public static final String WHERE_NICK	= "WHERE `nick`=?";
	public static final String WHERE_HP		= "WHERE `hp`=?";
	public static final String WHERE_EMAIL	= "WHERE `email`=?";
	
	
	public static final String INSERT_USER	= "INSERT INTO `user` SET "
											+ "`uid`=?,"
											+ "`pass`=SHA2(?, 256), "
											+ "`name`=?, "
											+ "`nick`=?, "
											+ "`email`=?, "
											+ "`hp`=?, "
											+ "`regip`=?, "
											+ "`sms`=?, "
											+ "`rdate`= NOW()";
	public static final String SELECT_USER_FOR_LOGIN = "SELECT * FROM `User` WHERE `uid`=? AND `pass`=SHA2(?, 256)";
	
	public static final String INSERT_ARTICLE = "INSERT INTO `Article` SET "
												+ "`title`=?, "
												+ "`content`=? , "
												+ "`file`=? , "
												+ "`writer`=?, "
												+ "`regip`=?, "
												+ "`rdate`=NOW()";
	public static final String INSERT_COMMENT = "INSERT INTO `Article` SET "
												+ "`parent`=?, "
												+ "`content`=? , "
												+ "`writer`=?, "
												+ "`regip`=?, "
												+ "`rdate`=NOW()";
	public static final String INSERT_FILE = "INSERT INTO `File` SET "
											+ "`ano`=?, "
											+ "`oName`=?, "
											+ "`sName`=?, "
											+ "`rdate`=NOW()";
	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(*) FROM `article` AS a JOIN `user` AS b "
													+ "ON a.writer = b.uid WHERE a.`parent` = 0 ";
	
	public static final String SELECT_ARTICLES = "SELECT a.*, b.`nick` "
												+ "FROM `article` AS a "
												+ "JOIN `user` AS b "
												+ "ON a.writer = b.uid "
												+ "WHERE `parent`=0 ";
	
	public static final String SELECT_ARTICLES_WHERE_TITLE = "AND `title` like ?";
	public static final String SELECT_ARTICLES_WHERE_CONTENT = "AND `content` like ?";
	public static final String SELECT_ARTICLES_WHERE_TITLE_CONTENT = "AND (`title` like ? OR `content` like ?)";
	public static final String SELECT_ARTICLES_WHERE_WRITER= "AND `nick` like ?";
												
	public static final String SELECT_ARTICLES_ORDER_LIMIT = "ORDER BY `no` DESC LIMIT ?, 10";
																									

	public static final String SELECT_COMMENTS = "SELECT a.*, b.nick "
												+ "FROM `Article` AS a "
												+ "JOIN `user` AS b ON a.writer = b.uid "
												+ "WHERE `parent`=? "
												+ "ORDER BY `no` ASC";
	public static final String SELECT_FILE = "SELECT * FROM `File` WHERE `fno`=?";
	public static final String SELECT_FILE_FOR_DELETE = "SELECT `ano`, `sname` FROM `File` WHERE `fno`=?";
	public static final String SELECT_FILE_FOR_SNAME = "SELECT `sname` FROM `File` WHERE `fno`=?";
	
	public static final String SELECT_ARTICLE = "SELECT * FROM `article` AS a "
												+ "LEFT JOIN `file` AS b ON a.`no`=b.`ano` WHERE `no`=?";
	
	public static final String UPDATE_HIT_COUNT = "UPDATE `Article` SET `hit` = `hit` + 1 WHERE `no`=?";
	
	public static final String UPDATE_ARTICLE = "UPDATE `Article` SET `title`=?, `content`=? Where `no`=?" ;
	public static final String UPDATE_COMMENT_PLUS = "UPDATE `Article` SET `comment` = `comment` + 1 WHERE `no`=?" ;
	public static final String UPDATE_COMMENT_MINUS = "UPDATE `Article` SET `comment` = `comment` - 1 WHERE `no`=?" ;
	
	public static final String UPDATE_FILE_DOWNLOAD = "UPDATE `File` SET `download` = `download` + 1 WHERE `fno`=?";
	
	public static final String DELETE_ARTICLE = "DELETE FROM `Article` WHERE `no`=? or `parent`=?";
	public static final String DELETE_FILES = "DELETE FROM `File` WHERE `ano`=?";
	public static final String DELETE_FILE = "DELETE FROM `File` WHERE `fno`=?";
	//public static final String UPDATE_FILE_PLUS = "UPDATE `article` AS a JOIN `file` AS b ON a.no = b.ano SET a.`file` = a.`file` + 1 WHERE a.no = ?;";
	public static final String UPDATE_ARTICLE_FOR_FILE_MINUS = "UPDATE `Article` SET `file`=`file`-1 WHERE `no`=?";
	
	public static final String DELETE_COMMENT = "DELETE FROM `Article` WHERE `no`=?" ;
	public static final String UPDATE_COMMENT = "UPDATE `Article` SET `content`=? WHERE `no`=?" ;
	
	
}