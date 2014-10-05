package texasHoldemPoker.Model;

public class Player {
	private int id;
	
	private String name;
	
	private String email;
	
	private int salary;
	
	public Player(String name) {
		this.name = name;
	}
	
	public Player(int id,String name, String email, int balance) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.salary = balance;
	}
	
	public Player(String name, String email, int balance) {
		this.name = name;
		this.email = email;
		this.salary = balance;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
