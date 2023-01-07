package Day7;

// 사용 방법 예시
public class Book {
	private String bNum;
	private String bTitle;
	private boolean bCanRental;

	public Book(String bNum, String bTitle, boolean bCanRental) {
		this.bNum = bNum;
		this.bTitle = bTitle;
		this.bCanRental = bCanRental;
	}

	public String getbNum() {
		return bNum;
	}

	public void setbNum(String bNum) {
		this.bNum = bNum;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public boolean isbCanRental() {
		return bCanRental;
	}

	public void setbCanRental(boolean bCanRental) {
		this.bCanRental = bCanRental;
	}
	
	
}
