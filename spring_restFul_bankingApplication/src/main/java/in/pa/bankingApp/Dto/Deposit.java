package in.pa.bankingApp.Dto;

import org.springframework.web.bind.annotation.PathVariable;

public class Deposit {

	private long accNum;
	private double depositAmt;
	public long getAccNum() {
		return accNum;
	}
	public void setAccNum(long accNum) {
		this.accNum = accNum;
	}
	public double getDepositAmt() {
		return depositAmt;
	}
	public void setDepositAmt(double depositAmt) {
		this.depositAmt = depositAmt;
	}

}
