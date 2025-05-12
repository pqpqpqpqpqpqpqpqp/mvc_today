package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import member.dto.UserVO;

import javax.naming.Context;
import javax.naming.InitialContext;

public class MemberDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public MemberDAO() {
		try {
			
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MysqlDB");
			conn = ds.getConnection();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void conClose() {
		try {if(rs != null) rs.close();} catch(Exception e) {}
		try {if(rs != null) pstmt.close();} catch(Exception e) {}
		try {if(rs != null) conn.close();} catch(Exception e) {}
	}
	
	public int regiset(MemberVO memberVO) {
		
		try {
			
			String input = "INSERT USER_MVC(USER_ID, USER_PW, USER_NICKNAME) VALUES (?,?,?);";
			pstmt = conn.prepareStatement(input);
			pstmt.setString(1, memberVO.getUser_id());
			pstmt.setString(2, memberVO.getUser_pw());
			pstmt.setString(3, memberVO.getUser_nickname());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				return 1;
			}

		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			conClose();
		}
		
		return 0;
	}
	
	public MemberVO login(String user_id) {
		MemberVO memberVO = null;
		try {

			String sql = "select user_pw, me from member where USER_ID = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user_id);
			// SELECT 할 경우, ResultSet 필요
			rs = pstmt.executeQuery();

			// 결과값은 1개만 있으므로, if만 있어도 가능
			if (rs.next()) {
				userVO = new UserVO();
				String pw = rs.getString("password");
				String nickname = rs.getString("nickname");
				
				userVO.setPassword(pw);
				userVO.setNickname(nickname);
				
				return userVO;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conClose();
		}

		return null;
	}

}
