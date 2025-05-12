package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class boardDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boardDAO() {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MysqlDB");
			conn = ds.getConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<boardVO> boardList() {
		List<boardVO> list = new ArrayList<>();
		String sql = "SELECT * FROM TRAVEL_BOARD WHERE DEL_YN = 'N' ORDER BY BOARD_IDX DESC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				boardVO vo = new boardVO();
				vo.setBoardIdx(rs.getInt("BOARD_IDX"));
				vo.setBoardTitle(rs.getString("BOARD_TITLE"));
				vo.setBoardContent(rs.getString("BOARD_CONTENT"));
				vo.setBoardCategory(rs.getString("BOARD_CATEGORY"));
				vo.setTravelLocation(rs.getString("TRAVEL_LOCATION"));
				vo.setTravelPeriod(rs.getString("TRAVEL_PERIOD"));
				vo.setTravelBudget(rs.getInt("TRAVEL_BUDGET"));
				vo.setViewCount(rs.getInt("VIEW_COUNT"));
				vo.setLikeCount(rs.getInt("LIKE_COUNT"));
				vo.setInstUser(rs.getString("INST_USER"));
				vo.setInstDate(rs.getString("INST_DATE"));
				vo.setUpdtUser(rs.getString("UPDT_USER"));
				vo.setUpdtDate(rs.getString("UPDT_DATE"));
				vo.setDelYn(rs.getString("DEL_YN"));
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeCon();
		}
		return list;
	}

	public boardVO boardDetail(int boardIdx) {
		boardVO vo = null;
		String sql = "SELECT * FROM TRAVEL_BOARD WHERE BOARD_IDX = ? AND DEL_YN = 'N'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardIdx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new boardVO();
				vo.setBoardIdx(rs.getInt("BOARD_IDX"));
				vo.setBoardTitle(rs.getString("BOARD_TITLE"));
				vo.setBoardContent(rs.getString("BOARD_CONTENT"));
				vo.setBoardCategory(rs.getString("BOARD_CATEGORY"));
				vo.setTravelLocation(rs.getString("TRAVEL_LOCATION"));
				vo.setTravelPeriod(rs.getString("TRAVEL_PERIOD"));
				vo.setTravelBudget(rs.getInt("TRAVEL_BUDGET"));
				vo.setViewCount(rs.getInt("VIEW_COUNT"));
				vo.setLikeCount(rs.getInt("LIKE_COUNT"));
				vo.setInstUser(rs.getString("INST_USER"));
				vo.setInstDate(rs.getString("INST_DATE"));
				vo.setUpdtUser(rs.getString("UPDT_USER"));
				vo.setUpdtDate(rs.getString("UPDT_DATE"));
				vo.setDelYn(rs.getString("DEL_YN"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//closeCon();
		}
		return vo;
	}

	public int boardInsert(boardVO vo) {
		int result = 0;
		String sql = "INSERT INTO TRAVEL_BOARD (BOARD_TITLE, BOARD_CONTENT, BOARD_CATEGORY, TRAVEL_LOCATION, TRAVEL_PERIOD, TRAVEL_BUDGET, INST_USER, UPDT_USER) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getBoardTitle());
			pstmt.setString(2, vo.getBoardContent());
			pstmt.setString(3, vo.getBoardCategory());
			pstmt.setString(4, vo.getTravelLocation());
			pstmt.setString(5, vo.getTravelPeriod());
			pstmt.setInt(6, vo.getTravelBudget());
			pstmt.setString(7, vo.getInstUser());
			pstmt.setString(8, vo.getInstUser());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeCon();
		}
		return result;
	}

	public int boardUpdate(boardVO vo) {
		int result = 0;
		String sql = "UPDATE TRAVEL_BOARD SET BOARD_TITLE = ?, BOARD_CONTENT = ?, BOARD_CATEGORY = ?, TRAVEL_LOCATION = ?, TRAVEL_PERIOD = ?, TRAVEL_BUDGET = ?, UPDT_USER = ?, UPDT_DATE = NOW() WHERE BOARD_IDX = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getBoardTitle());
			pstmt.setString(2, vo.getBoardContent());
			pstmt.setString(3, vo.getBoardCategory());
			pstmt.setString(4, vo.getTravelLocation());
			pstmt.setString(5, vo.getTravelPeriod());
			pstmt.setInt(6, vo.getTravelBudget());
			pstmt.setString(7, vo.getUpdtUser());
			pstmt.setInt(8, vo.getBoardIdx());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeCon();
		}
		return result;
	}

	public int boardDelete(int boardIdx) {
		int result = 0;
		String sql = "UPDATE TRAVEL_BOARD SET DEL_YN = 'Y' WHERE BOARD_IDX = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardIdx);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeCon();
		}
		return result;
	}

	public int boardViewCount(int boardIdx) {
		int result = 0;
		String sql = "UPDATE TRAVEL_BOARD SET VIEW_COUNT = VIEW_COUNT + 1 WHERE BOARD_IDX = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardIdx);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} 
		return result;
	}

	public int boardLikeCount(int boardIdx) {
		int result = 0;
		String sql = "UPDATE TRAVEL_BOARD SET LIKE_COUNT = LIKE_COUNT + 1 WHERE BOARD_IDX = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardIdx);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeCon();
		}
		return result;
	}

	public List<boardVO> searchByKeyword(String keyword) {
		List<boardVO> list = new ArrayList<>();
		String sql = "SELECT * FROM TRAVEL_BOARD WHERE DEL_YN = 'N' AND (BOARD_TITLE LIKE ? OR BOARD_CONTENT LIKE ?) ORDER BY BOARD_IDX DESC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				boardVO vo = new boardVO();
				vo.setBoardIdx(rs.getInt("BOARD_IDX"));
				vo.setBoardTitle(rs.getString("BOARD_TITLE"));
				vo.setBoardContent(rs.getString("BOARD_CONTENT"));
				vo.setInstUser(rs.getString("INST_USER"));
				vo.setInstDate(rs.getString("INST_DATE"));
				vo.setViewCount(rs.getInt("VIEW_COUNT"));
				vo.setLikeCount(rs.getInt("LIKE_COUNT"));
				vo.setBoardCategory(rs.getString("BOARD_CATEGORY"));
				vo.setTravelBudget(rs.getInt("TRAVEL_BUDGET"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon();
		}
		return list;
	}

	public List<boardVO> searchByCategory(String category) {
		List<boardVO> list = new ArrayList<>();
		String sql = "SELECT * FROM TRAVEL_BOARD WHERE DEL_YN = 'N' AND BOARD_CATEGORY = ? ORDER BY BOARD_IDX DESC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				boardVO vo = new boardVO();
				vo.setBoardIdx(rs.getInt("BOARD_IDX"));
				vo.setBoardTitle(rs.getString("BOARD_TITLE"));
				vo.setBoardContent(rs.getString("BOARD_CONTENT"));
				vo.setInstUser(rs.getString("INST_USER"));
				vo.setInstDate(rs.getString("INST_DATE"));
				vo.setViewCount(rs.getInt("VIEW_COUNT"));
				vo.setLikeCount(rs.getInt("LIKE_COUNT"));
				vo.setBoardCategory(rs.getString("BOARD_CATEGORY"));
				vo.setTravelBudget(rs.getInt("TRAVEL_BUDGET"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon();
		}
		return list;
	}

	public List<boardVO> searchByKeywordAndCategory(String keyword, String category) {
		List<boardVO> list = new ArrayList<>();
		String sql = "SELECT * FROM TRAVEL_BOARD WHERE DEL_YN = 'N' ";
		
		// 카테고리가 선택된 경우
		if (category != null && !category.trim().isEmpty()) {
			sql += "AND BOARD_CATEGORY = ? ";
		}
		
		// 키워드가 있는 경우
		if (keyword != null && !keyword.trim().isEmpty()) {
			sql += "AND (BOARD_TITLE LIKE ? OR BOARD_CONTENT LIKE ?) ";
		}
		
		sql += "ORDER BY BOARD_IDX DESC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			int paramIndex = 1;
			
			// 파라미터 설정
			if (category != null && !category.trim().isEmpty()) {
				pstmt.setString(paramIndex++, category);
			}
			if (keyword != null && !keyword.trim().isEmpty()) {
				pstmt.setString(paramIndex++, "%" + keyword + "%");
				pstmt.setString(paramIndex++, "%" + keyword + "%");
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				boardVO vo = new boardVO();
				vo.setBoardIdx(rs.getInt("BOARD_IDX"));
				vo.setBoardTitle(rs.getString("BOARD_TITLE"));
				vo.setBoardContent(rs.getString("BOARD_CONTENT"));
				vo.setBoardCategory(rs.getString("BOARD_CATEGORY"));
				vo.setTravelLocation(rs.getString("TRAVEL_LOCATION"));
				vo.setTravelPeriod(rs.getString("TRAVEL_PERIOD"));
				vo.setTravelBudget(rs.getInt("TRAVEL_BUDGET"));
				vo.setViewCount(rs.getInt("VIEW_COUNT"));
				vo.setLikeCount(rs.getInt("LIKE_COUNT"));
				vo.setInstUser(rs.getString("INST_USER"));
				vo.setInstDate(rs.getString("INST_DATE"));
				vo.setUpdtUser(rs.getString("UPDT_USER"));
				vo.setUpdtDate(rs.getString("UPDT_DATE"));
				vo.setDelYn(rs.getString("DEL_YN"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon();
		}
		return list;
	}
	
	public void closeCon() {
		try { if(rs != null) rs.close(); } catch(Exception e) {}
		try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
		try { if(conn != null) conn.close(); } catch(Exception e) {}
	}
}