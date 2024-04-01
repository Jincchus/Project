package kMarket.cs.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kMarket.cs.dao.CsArticleDAO;
import kMarket.cs.dto.CsArticleDTO;

public enum CsArticleService {

	INSTANCE;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	CsArticleDAO dao = new CsArticleDAO();
	
	
	//CS인덱스 페이지
	public List<CsArticleDTO> indexSelectNotices(){
		return dao.indexSelectNotices();
	}
	
	public List<CsArticleDTO> indexSelectQnas(){
		return dao.indexSelectQnas();
	}

	
	
	public int insertArticle(CsArticleDTO dto ) {
		return dao.insertArticle(dto);
		}
	
	
	
	public CsArticleDTO selectArticle(String cNo) {
			return dao.selectArticle(cNo);
		}
	
	public List<CsArticleDTO> selectArticles(String cate, String menu1,int start) {
		return dao.selectArticles(cate,menu1,start);
	}
	
	public Map<String, List<CsArticleDTO>> getMenu2Group(List<CsArticleDTO> lists){
		Map<String, List<CsArticleDTO>> menu2Group = new HashMap<>();
		for(CsArticleDTO list : lists) {
			String menu2 = list.getMenu2();
			if(!menu2Group.containsKey(menu2)) {
				menu2Group.put(menu2, new ArrayList<>());
			}
			menu2Group.get(menu2).add(list);
		}
		
		return menu2Group;
	}
	
	public List<CsArticleDTO>selectAdminArticles(String cate,String menu1, String menu2,int start){
		return dao.selectAdminArticles(cate, menu1, menu2, start);
	}
	
	
	public void updateArticle(CsArticleDTO dto) {
		dao.updateArticle(dto);
		dao.commentPlus(dto.getParent());
	}
	
	
	public void deleteArticle(String cNo) {
		dao.deleteArticle(cNo);
	}
	
	public CsArticleDTO selectReply(String cNo) {
		return dao.selectReply(cNo);
	}
	
	
	//페이지 번호
	
	//페이지 시작 번호
		public int getPageStartNum(int total, int currentPage) {
			int start = (currentPage -1)*10;
			return total - start;
			
		}
		
		//현재 페이지 번호
		public int getCurrentPage(String pg) {
			int currentPage =1;
			
			if(pg != null) {
				currentPage = Integer.parseInt(pg);
			}
			
			return currentPage;
		}
		
		//페이지 마지막 번호
		public int getLastPageNum(int total) {
			int lastPageNum = 0;
			
			if(total % 10==0) {
				lastPageNum = total/10;
			}else {
				lastPageNum = total/10+1;
			}
			return lastPageNum;
		}
		
		//Limit 시작번호
		public int getStartNum(int currentPage) {
			return (currentPage-1)*10;
		}
		
		//전체 게시물 조회
		public int selectCountTotal(String cate, String menu1) {
			return dao.selectCountTotal(cate,menu1);
		}
		
		//menu2 게시물 조회
				public int selectMenu2CountTotal(String cate, String menu1,String menu2) {
					return dao.selectMenu2CountTotal(cate,menu1,menu2);
				}
				
		//페이지 그룹
		public int[]getPageGroupNum(int currentPage, int lastPageNum){
			
			int currentPageGroup = (int)Math.ceil(currentPage/10.0);
			int pageGroupStart = (currentPageGroup -1)*10+1;
			int pageGroupEnd = currentPageGroup * 10;
			
			if(pageGroupEnd > lastPageNum) {
				pageGroupEnd = lastPageNum;
			}
			int[] result = {pageGroupStart,pageGroupEnd};
			return result;
			
		}
	
	
		
		
		
}




/*
//파일 업로드 경로
	public String getPath(HttpServletRequest req, String dir) {
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/upload");
		return path;
	}
	
	//파일업로드
	public MultipartRequest uploadfile(HttpServletRequest req, String path) {
		int maxSize = 1024*1024*10;
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(req, path,maxSize,"UTF-8",new DefaultFileRenamePolicy());
		} catch (Exception e) {
			logger.error("uploadfile : "+e.getMessage());
		}
		return mr;
	}
	
	
	//파일명 수정
	public String renameToFile(HttpServletRequest req,String oName, String path) {
		int i = oName.lastIndexOf(".");
		String ext = oName.substring(i);
		
		String uuid = UUID.randomUUID().toString();
		String sName = uuid+ext;
		
		File f1 = new File(path+"/"+oName);
		File f2 = new File(path+"/"+sName);
		
		f1.renameTo(f2);
		return sName;
	}
	
	
	//파일 다운로드
	public void downloadfile(HttpServletRequest req,HttpServletResponse resp,FileDTO dto,String path) throws IOException {
		
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(dto.getOfile(),"utf-8"));
		resp.setHeader("Content-Transfer-Encoding", "binary");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "private");
		
		File file = new File(path+"/"+dto.getSfile());
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
				
		while(true){
			
			int data = bis.read();
			if(data == -1){
				break;
			}
			
			bos.write(data);
		}
		
		bos.close();
		bis.close();
	}
*/