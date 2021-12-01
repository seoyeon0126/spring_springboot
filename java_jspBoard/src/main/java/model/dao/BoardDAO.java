package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dto.BoardDTO;
import model.dto.PageInfo;

public class BoardDAO {
	private static BoardDAO instance = new BoardDAO();
	private String sql;
	
	private BoardDAO() {}
	
	public static BoardDAO getInstance() {
		return instance;
	}

	public int getNewNum() {
		//데이터 컨넥션 얻기위한 객체 얻어오기
		int r = 0;
		//데이터베이스 연결...
		DBConnector dbconn = DBConnector.getInstance();

		//Connection, PreparedStatment, ResultSet
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select ifnull (max(num)+1,1) as num from board";
		
		try {
			conn = dbconn.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				r=rs.getInt("num");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs !=null) try { rs.close(); } catch (SQLException e) {}
			if (pstmt !=null) try { pstmt.close(); } catch (SQLException e) {}
			if (conn !=null) try { conn.close(); } catch (SQLException e) {}
		}
		return r;
	}

	public int writeArticle(BoardDTO dto) {
		//DB객체 가져오기
		//Connection, PreparedStatment
		//sql=insert문장			
		DBConnector dbconn = DBConnector.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int r=0;
		try {
			conn = dbconn.getConnection();
			sql = "insert into board(num, writer, subject, content, reg_date, ref, ip, passwd, email, step, lev) \n";
	        sql+="values(?,?,?,?,sysdate(),?,?,?,?,?,?)";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, dto.getNum());
	        pstmt.setString(2, dto.getWriter());
	        pstmt.setString(3, dto.getSubject());
	        pstmt.setString(4, dto.getContent());
	        pstmt.setInt(5, dto.getRef());
	        pstmt.setString(6, dto.getIp());
	        pstmt.setString(7, dto.getPasswd());
	        pstmt.setString(8, dto.getEmail());
	        pstmt.setInt(9, dto.getStep());
	        pstmt.setInt(10, dto.getLev());
	        //실행하기
	        r=pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt !=null) try { pstmt.close(); } catch (SQLException e) {}
			if (conn !=null) try { conn.close(); } catch (SQLException e) {}
		}
		return r;
	}

	public List<BoardDTO> getArticles(int beginRow, int endRow) {
		List<BoardDTO> list = new ArrayList<BoardDTO>(PageInfo.ROW_PER_PAGE);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//DB 연결하고
		DBConnector dbconn = DBConnector.getInstance();
		//페이지 단위로 리스트 가져오기, 계산에 의해 변경될 것임
		String sql = null;
		try {
			conn = dbconn.getConnection();
			sql = "select * from \r\n";
			sql+= "	(SELECT @rownum:=@rownum+1 as no, num, writer, subject, content, reg_date, \r\n";
			sql+= "		   readcount, lev, step, ref, ip, passwd, email\r\n";
			sql+= "	FROM board\r\n";
			sql+= "	where (@rownum:=0)=0 order by ref desc, num, step) as rownumtbl\r\n";
			sql+= "where no>=? and no <=?"; //1과 10은 페이지에 따라 변경
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNo(rs.getInt("no"));
				dto.setNum(rs.getInt("num"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setLev(rs.getInt("lev"));
				dto.setStep(rs.getInt("step"));
				dto.setRef(rs.getInt("ref"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setReg_date(rs.getString("reg_date"));
				dto.setIp(rs.getString("ip"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setEmail(rs.getString("email"));
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs !=null) try { rs.close(); } catch (SQLException e) {}
			if (pstmt !=null) try { pstmt.close(); } catch (SQLException e) {}
			if (conn !=null) try { conn.close(); } catch (SQLException e) {}
		}
		return list;
	}

	public BoardDTO getArticle(int num) {
		BoardDTO dto = new BoardDTO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//readcount 증가 시키기..
		String sql = null;
		try {
		DBConnector dbconn = DBConnector.getInstance();
		conn = dbconn.getConnection();
		sql = "update board set readcount = readcount+1 \r\n";
		sql += "where num=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		int r = pstmt.executeUpdate();
		pstmt.close();
		if(r>0) {
			sql = "SELECT num, writer, subject, content, reg_date, \r\n";
			sql+= "readcount, lev, step, ref, ip, passwd, email \r\n";
			sql+= "FROM board \r\n";	
			sql+= "WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
	        if(rs.next()) {
	        	dto = new BoardDTO();
	        	dto.setNum(rs.getInt("num"));
	        	dto.setRef(rs.getInt("ref"));
	        	dto.setStep(rs.getInt("step"));
	        	dto.setLev(rs.getInt("lev"));
	        	dto.setReadcount(rs.getInt("readcount"));
	        	dto.setWriter(rs.getString("writer"));
	        	dto.setSubject(rs.getString("subject"));
	        	dto.setContent(rs.getString("content"));
	        	dto.setReg_date(rs.getString("reg_date"));
	        	dto.setPasswd(rs.getString("passwd"));
	        	dto.setEmail(rs.getString("email"));
	        	dto.setIp(rs.getString("ip"));
	        }
		}
	} catch (Exception e) {
		System.out.println(e.getMessage());
	} finally {
		if (rs !=null) try { rs.close(); } catch (SQLException e) {}
		if (pstmt !=null) try { pstmt.close(); } catch (SQLException e) {}
		if (conn !=null) try { conn.close(); } catch (SQLException e) {}
	}
	return dto;
	}

	public int updateAtricle(BoardDTO dto) {
		int r =0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBConnector dbconn = DBConnector.getInstance();
		try {
			conn = dbconn.getConnection();
			String sql = null;
			sql="update board set writer=?, ";
			sql+="	subject = ?, ";
			sql+="	content = ?, ";
			sql+="	email = ?, ";
			sql+="	passwd = ?, ";
			sql+="	ip = ?, ";
			sql+="  reg_date = sysdate() \n";
			sql+="	where num = ? \n";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getPasswd());
			pstmt.setString(6, dto.getIp());
			pstmt.setInt(7, dto.getNum());
				r= pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(pstmt!=null) try { pstmt.close();} catch (SQLException e) {}
			if(conn!=null) try { conn.close();} catch (SQLException e) {}
		}
	return r ;
	}

	public int deleteArticle(int num) {
		int r =0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBConnector dbconn = DBConnector.getInstance();
		try {
			conn = dbconn.getConnection();
			String sql = null;
			sql="delete from board\n";
			sql+="where num=? \n";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			r= pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(pstmt!=null) try { pstmt.close();} catch (SQLException e) {}
			if(conn!=null) try { conn.close();} catch (SQLException e) {}
		}
	return r ;
	}

	public int getAllCount() {
		int cnt=0;
		String sql = "select count(num) as cnt from board";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DBConnector dbconn = DBConnector.getInstance();
		try {
			conn = dbconn.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs !=null) try { rs.close(); } catch (SQLException e) {}
			if (pstmt !=null) try { pstmt.close(); } catch (SQLException e) {}
			if (conn !=null) try { conn.close(); } catch (SQLException e) {}
		}
		return cnt;
	}
}
