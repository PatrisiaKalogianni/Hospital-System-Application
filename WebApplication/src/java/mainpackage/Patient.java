package mainpackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;

/**
 * This class implements a User of type Patient.
 * A Patient can have an AMKA number, which gives access to the system.
 * A Patient can register to the system and make appointments.
 * An additional variable counts the number of appointments.
 */
public class Patient extends Users {
	
	int num_of_appointments = 0;
	
	public Patient() { //manually
		//Users patient = new Users(){ -- overriding Users constructor.
		boolean valid_entry = false;
		while (!valid_entry){
		try{
			final long AMKA = LConsole();
			valid_entry = true;
		}
			//}
		catch(InputMismatchException e){
			System.out.println("Your entry was invalid. Please try again");
			}
		}
		};
		
	public Patient(String filename) { //upload--overloadded version
			//String try_again = "Y";
			String data[];
			long myAMKA = 0; //initialize
			//while(try_again.equals("Y")){
				try{
					try {
						FileReader file = new FileReader(filename);
						BufferedReader inputStream = new BufferedReader(file);
						try{
							String line = inputStream.readLine();
							data = line.split(" "); 
							myAMKA = Long.parseLong(data[0]);
							setName(data[1]);
							setSurname(data[2]);
							setUsername(data[3]);
							setPassword(data[4]);
							System.out.println("Your registration was successful");
							System.out.println("Your username is: "+getUsername());
							System.out.println("Your password is: "+getPassword());
							login();
						}
						catch(IOException e){
				            System.out.println("An error occured. Do you want to try again? Y/N");
				            //try_again = SConsole();
				            //continue;
						}
						catch(InputMismatchException e){
				            System.out.println("An error occured. Do you want to try again? Y/N");
				            //try_again = SConsole();
				            //continue;
						}
					}
					 catch (FileNotFoundException e){
			            System.out.println("File was not found. Do you want to try again? Y/N");
			            //try_again = SConsole();
			            //continue;
				}
				}
				catch(InputMismatchException e){
					System.out.println("Your data were invalid. Do you want to try again? Y/N");
					//try_again = SConsole();
				}
			//}
			if (myAMKA != 0){
				final long AMKA = myAMKA;
			}
			};

	public void Registration(){
		String try_again = "Y";
		while(try_again.equals("Y")){
			try{
			System.out.println("This is the registration field. Please follow the instructions");
			System.out.println("Give your name");
			setName(SConsole());
			System.out.println("Give your surname");
			setSurname(SConsole());
			System.out.println("Give a username");
			setUsername(SConsole());
			System.out.println("Give a password");
			setPassword(SConsole()); 
			System.out.println("Your registration was successful");
			System.out.println("Your username is: "+getUsername());
			System.out.println("Your password is: "+getPassword());
			try_again = "N";
			}
			catch(InputMismatchException e){
				System.out.println("Your data were invalid. Do you want to try again? Y/N");
				try_again = SConsole();
			}
		}
		
		
	}

	public void CancelAppointment(){
		if (num_of_appointments>0){
			System.out.println("Type a date");
			int date = Users.IConsole();
			num_of_appointments--;
			System.out.println("Your appointments at "+ date + " is canceled");
		}
		else
			System.out.println("You have no appointments to be canceled");
			
	}
	
	public String getAMKA(){
		String my_AMKA = "SELECT patientAMKA FROM Patients WHERE Username= ? AND Password = ?";
		return my_AMKA;
	}
	
	public String showPatient(){
		String q = "SELECT patientAMKA,name,surname FROM PATIENTS WHERE username = ? AND password= ?";
		return q;
	}
	
	public String showHistory(){
		String q = "WITH aux(t,doctorAMKA,diagnosis)AS (SELECT t,doctorAMKA,diagnosis FROM PATIENTS,APPOINTMENTS WHERE patientAMKA = 4017954515276 ) SELECT t,name,surname,diagnosis FROM aux,doctor WHERE aux.doctorAMKA = doctor.doctorAMKA";
		return q;
	}
	
}
