package com.company.Model2_Board.user;

//사용할 클래스들 추가
//사족으로 java.sql.*; 로 미리 하는게 나을듯 싶다.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.company.Model2_Board.common.JDBCUtil;


public class UserDAO {
	//DB 관련 변수 선언
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//SQL 명령어
	private final String USER_GET = "select id, password from users where id=? and password=?";
	
	//로그인 조회(select) getUser() 메소드 구현
	public UserDO getUser(UserDO userObj){
		//[중요] 인증에 '성공'하면 user객체 생성, '실패'하면 null이 되겠금 하기 위해서!
		UserDO user = null;
		
		try {
			System.out.println("===> JDBC로 getUser() 기능 처리");
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USER_GET);
			
			pstmt.setString(1, userObj.getId());
			pstmt.setString(2, userObj.getPassword());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				user = new UserDO();
				user.setId(rs.getString("ID"));
				user.setPassword(rs.getString("PASSWORD"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs, pstmt, conn);
		}
		return user;
	}
}