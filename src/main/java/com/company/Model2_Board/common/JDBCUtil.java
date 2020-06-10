package com.company.Model2_Board.common;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 *  DB ���ÿ� ���� �ҽ��� �����̱� ������ JDBCUtil Ŭ������ ��������!
 */

public class JDBCUtil {
	//H2 DB ������ ���� �ҽ�
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
	
	//insert, update, delete �۾� ����� ȣ���Ͽ� �ڿ� ���� ��Ű�� �޼ҵ�
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
	
	//select �۾� ����� ȣ���Ͽ� �ڿ� ���� ��Ű�� �޼ҵ� (�����ε�)
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