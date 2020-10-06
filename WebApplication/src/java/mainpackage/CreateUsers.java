package mainpackage;

public class CreateUsers {

	public static void main(String[] args) {
		
		System.out.println("--You access the system as Patient--");
				System.out.println("Give your AMKA to enter the system");
				Patient user = new Patient();
				user.Registration();
				user.login();
				System.out.println("Make an appointment");
				Appointment app = new Appointment();
				app.New_Appointment(user);
				System.out.println("Cancel an appointment");
				user.CancelAppointment();
				System.out.println("--You will now logout and continue as Patient--");
				user.logout(); 
				
				System.out.println("--Upload your data through a file to enter the system--");

				
				System.out.println("Do you want to upload your data to the system? Y/N");
				String answer =  Users.SConsole();
				if (answer.equals("Y")){
				System.out.println("Type the name of the file");	
				Patient user2 = new Patient(Users.SConsole());
				
				
				
				System.out.println("--You will now logout and continue as Administrator--");
				user2.logout();
				}
				
		Admin admin = new Admin();
		
		System.out.println("This is the doctor's registration field");
		admin.New_Doctor();
		 
		System.out.println("--You will now logout and continue as Doctor--");
		Doctor dr = new Doctor(admin.getAMKA());
		dr.login();
		System.out.println("Check your schedule");
		dr.Schedule();
		System.out.println("Cancel an appointment. Type a date");
		dr.CancelAppointment(dr.SConsole());
		dr.logout();
		
		admin.System_Control();
		admin.System_Control();
		System.out.println("--The program is terminated--");


	}

}
