package mainpackage;

import java.util.Arrays;
import java.util.Random;

/**
 * This is a class that implements the appointments of a Patient.
 * A patient can search of a certain specialty and book an appointment.
 * In this version, the specialty is set to 'Surgeon'
 *
 */
public class Appointment {
	
	public void New_Appointment(Patient obj){
		System.out.println("What specialty are you looking for? Pathologos,Ofthalmiatros,Orthopedikos");
		String sp = Users.SConsole();
		if (Search_Doctor(sp) != null){
			System.out.println("Doctor "+Search_Doctor(sp)+" is free on the following days this month:");
			Date();
			System.out.println("Do you wish to make an appointment? Y/N");
			String answer = Users.SConsole();
			if (answer.equals("Y")){
				obj.num_of_appointments++;
			int try_again = -1;
			while (try_again == -1)
			System.out.println("Type the date you wish to make your appointment");
			int day = Users.IConsole();
			if (day!= array[0]||day!= array[1]||day!= array[2]){
				System.out.println("This date is not free. Do you want to give up appointment? Y/N");
				if (Users.SConsole().equals("Y"))
					try_again++;	
			}
			else 
				try_again++;
			}
		}
		else{
			System.out.println("The doctor was not found");
		}
	}
	
	private String Search_Doctor(String sp){
		if (sp.equals("Pathologos"))
				return "George Papas";
		else if (sp.equals("Ofthalmiatros"))
			return "Nick Papas";
		else if(sp.equals("Orthopedikos"))
			return "Eleni Papas";

		else return null;
	}

	int[] array = new int[3];
	
	private void Date(){
		Random rand = new Random();
		for (int i = 0; i<3;i++)
			array[i]= rand.nextInt(30)+1;
		Arrays.sort(array);
		for (int i = 0; i<3;i++)
			System.out.println(array[i]);
	}
	
	public Appointment() {
		// TODO Auto-generated constructor stub
	}

}
