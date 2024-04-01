package kMarket.cs.dto;

public class CsArticleDTO {
	private String cNo;
	private String parent;
	private String comment;
	private String cate;
	private String menu1;
	private String menu2;
	private String title;
	private String content;
	private String file;
	private String hit;
	private String writer;
	private String regip;
	private String rdate;
	
	
	
	public String getcNo() {
		return cNo;
	}
	public void setcNo(String cNo) {
		this.cNo = cNo;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getMenu1() {
		return menu1;
	}
	public void setMenu1(String menu1) {
		this.menu1 = menu1;
	}
	
	public String getMenu2() {
		return menu2;
	}
	public void setMenu2(String menu2) {
		this.menu2 = menu2;
	}
	
	public String getFile() {
		return file;
	}
	public void setFile(String oName) {
		if(oName != null) {
			
			this.file="1";
		}else {
			this.file="0";
		}
	}
	public String getHit() {
		return hit;
	}
	public void setHit(String hit) {
		this.hit = hit;
	}
	
	public String getWriter() {
		return writer;
	}
	
	public String getMaskingWriter() {
		return idMasking(writer);
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegip() {
		return regip;
	}
	public void setRegip(String regip) {
		this.regip = regip;
	}
	public String getRdate() {
		if(rdate==null) {
			return rdate;
		}else {
		return rdate.substring(2,10);
		}
	}
	
	public String getFullRdate() {
		return rdate;
	}
	
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	
	public String idMasking(String writer) {
		String maskingWriter = null;
		if(writer!=null) {
		maskingWriter = writer.replaceAll("(?<=.{2}).", "*");
		}
		return maskingWriter;
	}
	@Override
	public String toString() {
		return "CsArticleDTO [cNo=" + cNo + ", parent=" + parent + ", comment=" + comment + ", cate=" + cate
				+ ", menu1=" + menu1 + ", menu2=" + menu2 + ", title=" + title + ", content=" + content + ", file="
				+ file + ", hit=" + hit + ", writer=" + writer + ", regip=" + regip + ", rdate=" + rdate + "]";
	}
	
	
	
}
