package mainpackage;

import java.util.InputMismatchException;

/**
 * This class implements an User of type Admin.
 * An Admin can control the state of the system and register a new doctor.
 * An Admin has a fixed AMKA number to access the system
 * (since he is not using the application for medical reasons).
 * Since Admin initializes the User of type Doctor, it's variables has to pass for the login operation.
 */
public class Admin extends Users{

	boolean state = true;
	
	public void System_Control(){
		if (state == false){
		System.out.println("The system is down. Please try to access in a few minutes");
		state = true;
		}
		else if (state == true){
			System.out.println("The system is up");	
			state = false;
			}
	}
	
	public void New_Doctor(){
		String try_again = "Y";
		while(try_again.equals("Y")){
		try{
		System.out.println("Give a name");
		setName(SConsole());
		System.out.println("Give a surname");
		setSurname(SConsole());
		System.out.println("Give a specialty");
		Doctor.specialty = SConsole();
		System.out.println("Give a username");
		setUsername(SConsole());
		System.out.println("Give a password");
		setPassword(SConsole());
		System.out.println("Registration of Dr. " + getSurname() + " was successful");
		System.out.println("Your username is: "+ getUsername());
		System.out.println("Your password is: "+ getPassword());
		}
		catch(InputMismatchException e){
			System.out.println("Your data were invalid. Do you want to try again? Y/N");
			try_again = SConsole();
		}
		}
	}
	
	public Admin() {
		Users admin = new Users(){
			final int AMKA = 0000;
		};
	}

}
