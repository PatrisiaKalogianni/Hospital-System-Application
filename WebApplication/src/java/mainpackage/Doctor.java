package mainpackage;

/**
 * This class implements an User of type Doctor.
 * A variable specifies the specialty of the doctor.
 * A Doctor can check his schedule and cancel appointments.
 *
 */
public class Doctor extends Users{
	
	static String specialty;

    public Doctor(String AMKA) {
        setAMKA(AMKA);
    }
	
	public void Schedule(){
		System.out.println("Your schedule is full!");
	}
	
	public void CancelAppointment(String date){
		System.out.println("Your appointments at "+ date+ " is canceled");
	}

}
