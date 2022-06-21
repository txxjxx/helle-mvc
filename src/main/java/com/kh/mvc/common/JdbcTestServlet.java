package com.kh.mvc.common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JdbcTestServlet
 */
@WebServlet("/jdbc/test")
public class JdbcTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JdbcTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain; charset=utf-8");	
		response.getWriter().append("Database 연결 테스트 ...- 서버 콘솔 확인");
		
		try {
			testDatabaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void testDatabaseConnection() throws ClassNotFoundException, SQLException {
		String driverClass = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "task";
		String password = "task";
		
		Connection conn = null; //db 접속을 위한 connection 객체
		PreparedStatement pstmt = null; //실제 쿼리를 실행할 preqaredStraement 객체
		ResultSet rset = null;// 쿼리 실행후 결과집합
		String sql = "select * from member";
		
		//DrvierClass등록
		Class.forName(driverClass);
		System.out.println("> Drvier Class 등록성공");
		
		//Connection 객체 생성
		conn = DriverManager.getConnection(url, user, password);
		System.out.println("> Connection 객체 생성");
		
		//PreparedStatement 객체 생성 & 미완성 쿼리 값대입
		pstmt = conn.prepareStatement(sql);
		System.out.println("> PreparedStatement 객체 생성");
		
		//쿼리 실행 & ResultSet 반환
		rset = pstmt.executeQuery();
		System.out.println("> 쿼리 실행 및 ResultSet 반환 성공");
		
		//ResultSet 처리
		while(rset.next()) {
			String memberId = rset.getString("member_id");
			String memberName = rset.getString("member_name");
			Date birthday= rset.getDate("birthday");
			int point = rset.getInt("point");
			
			System.out.printf("%s\t%s\t%s\t%s%n",memberId,memberName,birthday,point);
			
		}
		//사용한 자원반납
		rset.close();
		pstmt.close();
		conn.close();
		System.out.println("> 자원반납 성공");
	}

	
	}


