package in.pa.bankingApp.Dto;

import org.springframework.web.bind.annotation.PathVariable;

public class Transfer {

	private long fromAccNum;
	private double debitAmt;
	private long toAccNum;
	private double creditAmt;
	
	public long getFromAccNum() {
		return fromAccNum;
	}
	public void setFromAccNum(long fromAccNum) {
		this.fromAccNum = fromAccNum;
	}
	public double getDebitAmt() {
		return debitAmt;
	}
	public void setDebitAmt(double debitAmt) {
		this.debitAmt = debitAmt;
	}
	public long getToAccNum() {
		return toAccNum;
	}
	public void setToAccNum(long toAccNum) {
		this.toAccNum = toAccNum;
	}
	public double getCreditAmt() {
		return creditAmt;
	}
	public void setCreditAmt(double creditAmt) {
		this.creditAmt = creditAmt;
	}
	
	
}
