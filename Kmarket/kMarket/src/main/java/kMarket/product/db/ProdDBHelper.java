package kMarket.product.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProdDBHelper {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected Connection conn = null;
	protected PreparedStatement psmt = null;
	protected PreparedStatement psmt1 = null;
	protected Statement stmt = null;
	protected ResultSet rs = null;
	
	public Connection getConnection() {
		try {
			Context ctx = (Context) new InitialContext().lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("jdbc/kMarket");
			
			conn = ds.getConnection();
			
			
		} catch (Exception e) {
			logger.error("Connection error : " + e.getMessage());
		}
		return conn;
	}

	public void close() throws SQLException {

		if(rs != null) {
			rs.close();
		}
		if(stmt != null) {
			stmt.close();
		}
		if(psmt != null) {
			psmt.close();
		}
		if(psmt1 != null) {
			psmt.close();
		}
		if(conn != null) {
			conn.close();
		}
	}
}
