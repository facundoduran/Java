package texasHoldemPoker.Model;

import java.util.Date;

public class SalaryHistory {
	private Date date;
	private double amount;
	private double balance;
	private int playerId;
	
	public SalaryHistory(Date date, double amount, double balance) {
		this.date = date;
		this.amount = amount;
		this.balance = balance;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public int getPlayerId() {
		return playerId;
	}
	
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}	
}
