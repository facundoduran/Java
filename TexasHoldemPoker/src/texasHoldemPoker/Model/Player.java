package texasHoldemPoker.Model;

public class Player {
	
	private String name;
	
	private String email;
	
	private int salary;
	
	public Player(String name) {
		this.name = name;
	}
	
	public Player(String name, String email, int balance) {
		this.name = name;
		this.email = email;
		this.salary = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
}
