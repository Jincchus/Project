package kMarket.product.dto;

public class OrderDTO {

	private int ordNo;
	private String ordUid;
	private int ordCount;
	private int ordPrice;
	private int ordDiscount;
	private int ordDelivery;
	private int savePoint;
	private int usedPoint;
	private int ordTotalPrice;
	private String recipName;
	private String recipHp;
	private String recipZip;
	private String recipAddr1;
	private String recipAddr2;
	private int ordPayment;
	private int ordComplete;
	private String ordDate;
	
	public int getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(int ordNo) {
		this.ordNo = ordNo;
	}
	public String getOrdUid() {
		return ordUid;
	}
	public void setOrdUid(String ordUid) {
		this.ordUid = ordUid;
	}
	public int getOrdCount() {
		return ordCount;
	}
	public void setOrdCount(int ordCount) {
		this.ordCount = ordCount;
	}
	public int getOrdPrice() {
		return ordPrice;
	}
	public void setOrdPrice(int ordPrice) {
		this.ordPrice = ordPrice;
	}
	public int getOrdDiscount() {
		return ordDiscount;
	}
	public void setOrdDiscount(int ordDiscount) {
		this.ordDiscount = ordDiscount;
	}
	public int getOrdDelivery() {
		return ordDelivery;
	}
	public void setOrdDelivery(int ordDelivery) {
		this.ordDelivery = ordDelivery;
	}
	public int getSavePoint() {
		return savePoint;
	}
	public void setSavePoint(int savePoint) {
		this.savePoint = savePoint;
	}
	public int getUsedPoint() {
		return usedPoint;
	}
	public void setUsedPoint(int usedPoint) {
		this.usedPoint = usedPoint;
	}
	public int getOrdTotalPrice() {
		return ordTotalPrice;
	}
	public void setOrdTotalPrice(int ordTotalPrice) {
		this.ordTotalPrice = ordTotalPrice;
	}
	public String getRecipName() {
		return recipName;
	}
	public void setRecipName(String recipName) {
		this.recipName = recipName;
	}
	public String getRecipHp() {
		return recipHp;
	}
	public void setRecipHp(String recipHp) {
		this.recipHp = recipHp;
	}
	public String getRecipZip() {
		return recipZip;
	}
	public void setRecipZip(String recipZip) {
		this.recipZip = recipZip;
	}
	public String getRecipAddr1() {
		return recipAddr1;
	}
	public void setRecipAddr1(String recipAddr1) {
		this.recipAddr1 = recipAddr1;
	}
	public String getRecipAddr2() {
		return recipAddr2;
	}
	public void setRecipAddr2(String recipAddr2) {
		this.recipAddr2 = recipAddr2;
	}
	public int getOrdPayment() {
		return ordPayment;
	}
	public void setOrdPayment(int ordPayment) {
		this.ordPayment = ordPayment;
	}
	public int getOrdComplete() {
		return ordComplete;
	}
	public void setOrdComplete(int ordComplete) {
		this.ordComplete = ordComplete;
	}
	public String getOrdDate() {
		return ordDate;
	}
	public void setOrdDate(String ordDate) {
		this.ordDate = ordDate;
	}
	
	@Override
	public String toString() {
		return "OrderDTO [ordNo=" + ordNo + ", ordUid=" + ordUid + ", ordCount=" + ordCount + ", ordPrice=" + ordPrice
				+ ", ordDiscount=" + ordDiscount + ", ordDelivery=" + ordDelivery + ", savePoint=" + savePoint
				+ ", usedPoint=" + usedPoint + ", ordTotalPrice=" + ordTotalPrice + ", recipName=" + recipName
				+ ", recipHp=" + recipHp + ", recipZip=" + recipZip + ", recipAddr1=" + recipAddr1 + ", recipAddr2="
				+ recipAddr2 + ", ordPayment=" + ordPayment + ", ordComplete=" + ordComplete + ", ordDate=" + ordDate
				+ "]";
	}
}
