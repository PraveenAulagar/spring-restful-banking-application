package in.pa.bankingApp.Dto;

import org.springframework.web.bind.annotation.PathVariable;

public class Withdraw {

	private long accNum;
	private double withdrawAmt;
	public long getAccNum() {
		return accNum;
	}
	public void setAccNum(long accNum) {
		this.accNum = accNum;
	}
	public double getWithdrawAmt() {
		return withdrawAmt;
	}
	public void setWithdrawAmt(double withdrawAmt) {
		this.withdrawAmt = withdrawAmt;
	}
	
	
}
