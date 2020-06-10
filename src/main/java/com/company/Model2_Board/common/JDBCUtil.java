package com.company.Model2_Board.common;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 *  DB 관련에 대한 소스는 공통이기 때문에 JDBCUtil 클래스에 구현하자!
 */

public class JDBCUtil {
	//H2 DB 연동에 관한 소스
	static final String driver = "org.h2.Driver";
	static final String url = "jdbc:h2:tcp://localhost/~/test";
	
	public static Connection getConnection() throws Exception{
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, "sa", "");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//insert, update, delete 작업 종료시 호출하여 자원 해제 시키는 메소드
	public static void close(PreparedStatement pstmt, Connection conn){
		if(pstmt != null){
			try {
				if(!pstmt.isClosed()) pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				pstmt = null;
			}
		}
		
		if(conn != null){
			try {
				if(!conn.isClosed()) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				conn = null;
			}
		}
	}
	
	//select 작업 종료시 호출하여 자원 해제 시키는 메소드 (오버로딩)
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn){
		if(rs != null){
			try {
				if(!rs.isClosed()) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				rs = null;
			}
		}
		
		if(pstmt != null){
			try {
				if(!pstmt.isClosed()) pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				pstmt = null;
			}
		}
		
		if(conn != null){
			try {
				if(!conn.isClosed()) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				conn = null;
			}
		}
	}
}