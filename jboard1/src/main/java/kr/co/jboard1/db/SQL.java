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
	
	public static final String SELECT_ARTICLES = "SELECT `title`, "
												+ "CONCAT('[', comment, ']') AS comment, "
												+ "`writer`, "
												+ "`rdate` "
												+ "FROM `Article`";

}