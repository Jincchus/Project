package kMarket.member.dto;

public class SignupDTO {
	private String content;
	private String type;
	private String title;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "SignupDTO [content=" + content + ", type=" + type + "]";
	}

	
}
