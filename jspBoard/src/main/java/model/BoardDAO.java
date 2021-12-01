package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

	private static BoardDAO instance = new BoardDAO();
	
	private BoardDAO() {}
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	// 데이터베이스 접근해서 데이테베이스 처리하는 메소드들...
	public int writeBoard(BoardDTO bdto) {
		//데이터 컨넥션 얻기위한 객체 얻어오기
		DBConnector dbconn = DBConnector.getInstance();
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int r=0;
		String sql=null;
		try {
		  conn = dbconn.getConnection();
		  //데이터 저장하는 쿼리 만들고
		  //num은 키로 사용하는데 순서대로 증가하게 하고, ref에도 저장하려고 한다.   
		  sql = "select ifnull(max(num)+1,1) as num from board";
		  pstmt = conn.prepareStatement(sql);
          rs = pstmt.executeQuery();
		  if(rs.next()) {
			 bdto.setNum(rs.getInt("num"));
			 if(bdto.getRef()!=0) {
				 bdto.setStep(bdto.getStep()+1);
				 bdto.setLev(bdto.getLev()+1);
			 } else {
				 bdto.setRef(rs.getInt("num"));
			 }
		  }
         sql = "insert into board(num, writer, subject, content, reg_date, ref, ip, passwd, email, step, lev) \n";
         sql+="values(?,?,?,?,sysdate(),?,?,?,?,?,?)"; 
		  // set~~~ 데이터 ?에 맞추기
         if(pstmt!=null) pstmt.close();
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, bdto.getNum());
         pstmt.setString(2, bdto.getWriter());
         pstmt.setString(3, bdto.getSubject());
         pstmt.setString(4, bdto.getContent());
         pstmt.setInt(5, bdto.getRef());
         pstmt.setString(6, bdto.getIp());
         pstmt.setString(7, bdto.getPasswd());
         pstmt.setString(8, bdto.getEmail());
         pstmt.setInt(9, bdto.getStep());
         pstmt.setInt(10, bdto.getLev());
		  //실행하기
		 r = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(rs!=null) try { rs.close();} catch (SQLException e) {}
			if(pstmt!=null) try { pstmt.close();} catch (SQLException e) {}
			if(conn!=null) try { conn.close();} catch (SQLException e) {}
		}
		return r;
	}
	
	public List<BoardDTO> getBoards(){
		List<BoardDTO> list = new ArrayList<BoardDTO>(20);
		DBConnector dbconn = DBConnector.getInstance();
		Connection conn=null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = dbconn.getConnection();
			  sql = "select * from \r\n";
			  sql+= "	(SELECT @rownum:=@rownum+1 as no, num, writer, subject, content, reg_date, \r\n";
			  sql+= "		   readcount, lev, step, ref, ip, passwd, email\r\n";
			  sql+= "	FROM board\r\n";
			  sql+= "	where (@rownum:=0)=0) as rownumtbl\r\n";
			  sql+= "where no>=1 and no <=10"; //1과 10은 페이지에 따라 변경
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
            	BoardDTO dto = new BoardDTO();
            	dto.setNo(rs.getInt("no"));
            	dto.setNum(rs.getInt("num"));
            	dto.setWriter(rs.getString("writer"));
            	dto.setSubject(rs.getString("subject"));
            	dto.setContent(rs.getString("content"));
            	dto.setReg_date(rs.getString("reg_date"));
            	dto.setReadcount(rs.getInt("readcount"));
            	dto.setLev(rs.getInt("lev"));
            	dto.setStep(rs.getInt("step"));
            	dto.setRef(rs.getInt("ref"));
            	dto.setIp(rs.getString("ip"));
            	dto.setPasswd(rs.getString("passwd"));
            	dto.setEmail(rs.getString("email"));
            	list.add(dto);
            }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(rs!=null) try { rs.close();} catch (SQLException e) {}
			if(pstmt!=null) try { pstmt.close();} catch (SQLException e) {}
			if(conn!=null) try { conn.close();} catch (SQLException e) {}
		}
		
		return list;
	}
	
	public BoardDTO getBoard(BoardDTO dto) {
		DBConnector dbconn = DBConnector.getInstance();
		Connection conn=null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String sql = null;
		BoardDTO sdto=null;
		try {
			conn = dbconn.getConnection();
			//조회수를 증가 시키고, 불러오기
			sql = "update board set readcount = readcount+1 \r\n";
			sql+= "where num = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, dto.getNum());
            pstmt.executeUpdate();
			pstmt.close();
			//게시글 가져오기
            sql = "SELECT num, writer, subject, content, reg_date, \r\n";
            sql+= "       readcount, lev, step, ref, ip, passwd, email \r\n";
            sql+= "FROM board \r\n";	
            sql+= "WHERE num=?";	
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, dto.getNum());
            rs = pstmt.executeQuery();
            if(rs.next()) {
            	sdto = new BoardDTO();
            	sdto.setNum(rs.getInt("num"));
            	sdto.setWriter(rs.getString("writer"));
            	sdto.setSubject(rs.getString("subject"));
            	sdto.setContent(rs.getString("content"));
            	sdto.setReg_date(rs.getString("reg_date"));
            	sdto.setReadcount(rs.getInt("readcount"));
            	sdto.setLev(rs.getInt("lev"));
            	sdto.setStep(rs.getInt("step"));
            	sdto.setRef(rs.getInt("ref"));
            	sdto.setIp(rs.getString("ip"));
            	sdto.setPasswd(rs.getString("passwd"));
            	sdto.setEmail(rs.getString("email"));
            }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(rs!=null) try { rs.close();} catch (SQLException e) {}
			if(pstmt!=null) try { pstmt.close();} catch (SQLException e) {}
			if(conn!=null) try { conn.close();} catch (SQLException e) {}
		}
		return sdto;
	}
	public int updateBoard(BoardDTO dto) {
		int r =  0;
		DBConnector dbconn = DBConnector.getInstance();
		Connection conn=null;
		PreparedStatement pstmt =null;
		String 	sql = null;
		try {
			conn = dbconn.getConnection();
			sql = "update board set \n";
			sql+= "subject = ? \n";
			sql+="	,writer = ? \n";
			sql+="	,content = ? \n";
			sql+="	,email = ? \n";
			sql+="	,passwd = ? \n";
			sql+="	,ip = ? \n";		
			sql+="	,reg_date = sysdate() \n";		
			sql+="	where num = ? \n";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getPasswd());
			pstmt.setString(6, dto.getIp());
			pstmt.setInt(7, dto.getNum());
			r= pstmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close();} catch (SQLException e) {}
			if(conn!=null) try { conn.close();} catch (SQLException e) {}
		}
		
		return r;
	}
	
	public int deleteBoard(BoardDTO dto) {
		int r= 0;
		//데이터베이스 연결
		//delete from board where num =?
		DBConnector dbconn = DBConnector.getInstance();
		Connection conn=null;
		PreparedStatement pstmt =null;
		String 	sql = null;
		try {
			conn = dbconn.getConnection();
			sql = "delete from board where num =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNum());
			r= pstmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close();} catch (SQLException e) {}
			if(conn!=null) try { conn.close();} catch (SQLException e) {}
		}
		
		return r;
	}
		
	public int getAllCount() {
		int r= 0;
		//데이터베이스 연결
		DBConnector dbconn = DBConnector.getInstance();
		Connection conn=null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String 	sql = null;
			try {
				conn = dbconn.getConnection();
				sql = "select count(num) from board";
				pstmt = conn.prepareStatement(sql);
				rs= pstmt.executeQuery();
				if(rs.next()) {
					r = rs.getInt(1);
				}
			} catch(Exception e) {
				System.out.println(e.getMessage());
			} finally {
				if(rs!=null) try { rs.close(); } catch (SQLException e) {}
				if(pstmt!=null) try { pstmt.close();} catch (SQLException e) {}
				if(conn!=null) try { conn.close();} catch (SQLException e) {}
			}
			
			return r;
	}
}