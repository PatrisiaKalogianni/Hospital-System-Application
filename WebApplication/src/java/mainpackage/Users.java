package mainpackage;

import java.util.Scanner;
/**
 * Users is the superclass of the program.
 * It implements basic methods for other classes to use.
 *
 */
public class Users {
	
	String username,password,name,surname,AMKA;
	
	private int usersCounter = 0;
	
	public String login(){
		/* boolean success = false;
		while(!success){
		System.out.println("This is the login field. Please type your credentials");
		System.out.println("Username:");
		String user = SConsole();
		System.out.println("Password:");
		String pass = SConsole();
		if (user.equals(getUsername()) && pass.equals(getPassword())){
			System.out.println("Welcome "+ getName());
			success = true;
		}
		else{
			System.out.println("Login failed. Please check your credentials and try again");
			success = false;
		}
		}
		*/
		String sql = "SELECT name FROM patient WHERE username = '?' AND password ='?'";
		return(sql);
	}
	
	
	public void logout(){

		System.out.println("You signed off the system.");
	}
	 
        	public String getAMKA(){
		return AMKA;
	}

	public void setAMKA(String name){
		this.AMKA = AMKA;
	}
        
        
        
	public String getName(){
		 return name;
	 }
	 
	public String getSurname(){
		 return surname;
	 }

	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public void setSurname(String surname){
		this.surname = surname;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public void setPassword(String pass){
		password = pass;
	}
	
	public static String SConsole(){
		Scanner input = new Scanner(System.in);
		return input.nextLine().toString();
	}
	public static int IConsole(){
		Scanner input = new Scanner(System.in);
		return input.nextInt();
	}
	
	public long LConsole(){
		Scanner input = new Scanner(System.in);
		return input.nextLong();
	}

	
	public Users(){
		usersCounter++;
	}
	
	
	
	
}
