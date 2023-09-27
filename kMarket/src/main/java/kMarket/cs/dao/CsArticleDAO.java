package kMarket.cs.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kMarket.cs.db.DBhelper;
import kMarket.cs.db.SQL;
import kMarket.cs.dto.CsArticleDTO;

public class CsArticleDAO extends DBhelper {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public List<CsArticleDTO> indexSelectNotices(){
		List<CsArticleDTO> notices = new ArrayList<>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs= stmt.executeQuery(SQL.INDEX_SELECT_NOTICE);
			while(rs.next()) {
				CsArticleDTO dto = new CsArticleDTO();
				dto.setcNo(rs.getString(1));
				dto.setParent(rs.getString(2));
				dto.setComment(rs.getString(3));
				dto.setCate(rs.getString(4));
				dto.setMenu1(rs.getString(5));
				dto.setMenu2(rs.getString(6));
				dto.setTitle(rs.getString(7));
				dto.setContent(rs.getString(8));
				dto.setFile(rs.getString(9));
				dto.setHit(rs.getString(10));
				dto.setWriter(rs.getString(11));
				dto.setRegip(rs.getString(12));
				dto.setRdate(rs.getString(13));
				notices.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("indexSelectNotices :"+e.getMessage());
		}
		
		return notices;
	}
	
	
	
	public List<CsArticleDTO> indexSelectQnas(){
		List<CsArticleDTO> qnas = new ArrayList<>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs= stmt.executeQuery(SQL.INDEX_SELECT_QNA);
			while(rs.next()) {
				CsArticleDTO dto = new CsArticleDTO();
				dto.setcNo(rs.getString(1));
				dto.setParent(rs.getString(2));
				dto.setComment(rs.getString(3));
				dto.setCate(rs.getString(4));
				dto.setMenu1(rs.getString(5));
				dto.setMenu2(rs.getString(6));
				dto.setTitle(rs.getString(7));
				dto.setContent(rs.getString(8));
				dto.setFile(rs.getString(9));
				dto.setHit(rs.getString(10));
				dto.setWriter(rs.getString(11));
				dto.setRegip(rs.getString(12));
				dto.setRdate(rs.getString(13));
				qnas.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("indexSelectqnas :"+e.getMessage());
		}
		
		return qnas;
	}
	
	
	public int insertArticle(CsArticleDTO dto) {
		int cNo = 0;
		try {
			
			conn = getConnection();
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, dto.getCate());
			psmt.setString(2, dto.getMenu1());
			psmt.setString(3, dto.getMenu2());
			psmt.setString(4, dto.getWriter());
			psmt.setString(5, dto.getTitle());
			psmt.setString(6, dto.getContent());
			psmt.setString(7, dto.getRegip());
			psmt.setString(8, dto.getFile());
			psmt.executeUpdate();
			rs=stmt.executeQuery(SQL.SELECT_MAX_NO);
			
			if(rs.next()) {
				cNo = rs.getInt(1);
			}
			
			conn.commit();
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return cNo;
	}
	
		public CsArticleDTO selectArticle(String cNo) {
				CsArticleDTO dto = null;
			try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL.SELECT_ARTICLE);
				psmt.setString(1, cNo);
				rs = psmt.executeQuery();
				
				if(rs.next()) {
					dto = new CsArticleDTO();
					dto.setcNo(rs.getString(1));
					dto.setParent(rs.getString(2));
					dto.setComment(rs.getString(3));
					dto.setCate(rs.getString(4));
					dto.setMenu1(rs.getString(5));
					dto.setMenu2(rs.getString(6));
					dto.setTitle(rs.getString(7));
					dto.setContent(rs.getString(8));
					dto.setFile(rs.getString(9));
					dto.setHit(rs.getString(10));
					dto.setWriter(rs.getString(11));
					dto.setRegip(rs.getString(12));
					dto.setRdate(rs.getString(13));
				}
				close();
			} catch (Exception e) {
				logger.error("selectcsArticle : "+e.getMessage());
			}
				return dto;
			}
		
		public List<CsArticleDTO>selectAdminArticles(String cate,String menu1, String menu2,int start){
			List<CsArticleDTO> lists = new ArrayList<>();
			
			try {
				conn = getConnection();
				
				if(menu1.equals("0")) {
					psmt=conn.prepareStatement(SQL.SELECT_ADMIN_ARTICLES);
					psmt.setString(1, cate);
					psmt.setString(2, menu1);
					psmt.setInt(3, start);
				}else {
					psmt=conn.prepareStatement(SQL.SELECT_ADMIN_MENU2_ARTICLES);
					psmt.setString(1, cate);
					psmt.setString(2, menu1);
					psmt.setString(3, menu2);
					psmt.setInt(4, start);
				}
				rs=psmt.executeQuery();
				while(rs.next()) {
					CsArticleDTO dto = new CsArticleDTO();
					dto.setcNo(rs.getString(1));
					dto.setParent(rs.getString(2));
					dto.setComment(rs.getString(3));
					dto.setCate(rs.getString(4));
					dto.setMenu1(rs.getString(5));
					dto.setMenu2(rs.getString(6));
					dto.setTitle(rs.getString(7));
					dto.setContent(rs.getString(8));
					dto.setFile(rs.getString(9));
					dto.setHit(rs.getString(10));
					dto.setWriter(rs.getString(11));
					dto.setRegip(rs.getString(12));
					dto.setRdate(rs.getString(13));
					lists.add(dto);
				}
				close();
			} catch (Exception e) {
				logger.error("selectAdminArticles : "+e.getMessage());
			}
			
			return lists;
		}
		
		public List<CsArticleDTO> selectArticles(String cate, String menu1,int start) {
			List<CsArticleDTO> lists = new ArrayList<>();
		 
			try {
				conn = getConnection();
				
				if(cate.equals("faq")) {
					psmt=conn.prepareStatement(SQL.SELECT_ARTICELS_FAQ);
					psmt.setString(1, cate);
					psmt.setString(2, menu1);
				}else if(menu1.equals("0")) {
					psmt=conn.prepareStatement(SQL.SELECT_ARTICELS_ALL);
					psmt.setString(1, cate);
					psmt.setInt(2, start);
				}else {
				psmt =conn.prepareStatement(SQL.SELECT_ARTICELS);
				psmt.setString(1, cate);
				psmt.setString(2, menu1);
				psmt.setInt(3, start);
				}
				rs=psmt.executeQuery();
				while(rs.next()) {
					CsArticleDTO dto = new CsArticleDTO();
					dto.setcNo(rs.getString(1));
					dto.setParent(rs.getString(2));
					dto.setComment(rs.getString(3));
					dto.setCate(rs.getString(4));
					dto.setMenu1(rs.getString(5));
					dto.setMenu2(rs.getString(6));
					dto.setTitle(rs.getString(7));
					dto.setContent(rs.getString(8));
					dto.setFile(rs.getString(9));
					dto.setHit(rs.getString(10));
					dto.setWriter(rs.getString(11));
					dto.setRegip(rs.getString(12));
					dto.setRdate(rs.getString(13));
					lists.add(dto);
				}
				close();
			} catch (Exception e) {
				logger.error("selectArticles : "+e.getMessage());
			}
			

			return lists;
		}
		
		
		
		public int selectCountTotal(String cate, String menu1) {
			int total = 0;
			
			try {
				conn = getConnection();
				
				if(menu1.equals("0")) {
					psmt=conn.prepareStatement(SQL.SELECT_COUNT_TOTAL_ALL);
					psmt.setString(1, cate);
				}else {
				psmt =conn.prepareStatement(SQL.SELECT_COUNT_TOTAL);
				psmt.setString(1, cate);
				psmt.setString(2, menu1);
				}
				
				rs=psmt.executeQuery();
				
				if(rs.next()) {
					total = rs.getInt(1);
				}
				close();
			} catch (Exception e) {
				logger.error("selectCountTotal : "+e.getMessage());
			}
			
			return total;
		}
		
		public int selectMenu2CountTotal(String cate, String menu1,String menu2) {
			int total = 0;

			try {
				conn = getConnection();
				
				if(menu2.equals("0")) {
					psmt=conn.prepareStatement(SQL.SELECT_MENU2_COUNT_TOTAL_ALL);
					psmt.setString(1, cate);
					psmt.setString(2, menu1);
				}else {
				psmt =conn.prepareStatement(SQL.SELECT_MENU2_COUNT_TOTAL);
				psmt.setString(1, cate);
				psmt.setString(2, menu1);
				psmt.setString(3, menu2);
				}
				
				rs=psmt.executeQuery();
				
				if(rs.next()) {
					total = rs.getInt(1);
				}
				close();
			} catch (Exception e) {
				logger.error("selectCountTotal : "+e.getMessage());
			}
			
			return total;
			
		}
		
		public void updateArticle(CsArticleDTO dto) {
			try {
				logger.debug("updateArticle start");
				conn = getConnection();
				psmt = conn.prepareStatement(SQL.INSERT_CONTENT);
				psmt.setString(1, dto.getParent());
				psmt.setString(2, dto.getMenu1());
				psmt.setString(3, dto.getMenu2());
				psmt.setString(4, dto.getContent());
				psmt.executeUpdate();
				close();
			} catch (Exception e) {
				logger.error("updateArticles : "+e.getMessage());
			}
			
		}
		
		public CsArticleDTO selectReply(String cNo) {
				CsArticleDTO dto = null;
			try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL.SELECT_REPLY);
				psmt.setString(1, cNo);
				rs= psmt.executeQuery();
				
				while(rs.next()) {
					dto = new CsArticleDTO();
					dto.setcNo(rs.getString(1));
					dto.setParent(rs.getString(2));
					dto.setComment(rs.getString(3));
					dto.setCate(rs.getString(4));
					dto.setMenu1(rs.getString(5));
					dto.setMenu2(rs.getString(6));
					dto.setTitle(rs.getString(7));
					dto.setContent(rs.getString(8));
					dto.setFile(rs.getString(9));
					dto.setHit(rs.getString(10));
					dto.setWriter(rs.getString(11));
					dto.setRegip(rs.getString(12));
					dto.setRdate(rs.getString(13));
				}
				
			} catch (Exception e) {
				logger.error("selectReply :"+e.getMessage());
			}
			
			return dto;
		}
		
		public void commentPlus(String cNo) {
			
			try {
				logger.debug("commentPlus start");
				conn = getConnection();
				psmt = conn.prepareStatement(SQL.COUNT_COMMENT_PLUS);
				psmt.setString(1, cNo);
				psmt.executeUpdate();
				close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		public void deleteArticle(String cNo) {
			
			try {
				
				conn= getConnection();
				psmt = conn.prepareStatement(SQL.DELETE_ARTICLE);
				psmt.setString(1, cNo);
				psmt.setString(2, cNo);
				psmt.executeUpdate();
			} catch (Exception e) {
				logger.error("deleteArticle :"+e.getMessage());
			}
			
		}
		

	
}
