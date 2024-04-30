import java.util.Scanner;

public class StudentCounsellingAppointmentBooking {
    public static void main(String[] args) throws Exception {
        AppointmentManager manager = new AppointmentManager();
        try (Scanner input = new Scanner(System.in)) {
            int choice = 0;

            displayMenu();
            
            do {
                choice = input.nextInt();
                input.nextLine();
                switch(choice) {
                    case 1:
                        System.out.print("Enter student name: ");
                        String studentName = input.nextLine();
                        System.out.print("Enter appointment date (YYYY-MM-DD): ");
                        String appointmentDate = input.nextLine();
                        System.out.print("Enter appointment time: ");
                        String appointmentTime = input.nextLine();
                        manager.bookAppointment(studentName, appointmentDate, appointmentTime);
                    break;
                    case 2:
                        System.out.print("Enter appointment date (YYYY-MM-DD): ");
                        appointmentDate = input.nextLine();
                        System.out.print("Enter appointment time: ");
                        appointmentTime = input.nextLine();
                        manager.cancelAppointment(appointmentDate, appointmentTime);
                    break;
                    case 3:
                        manager.displayBookedAppointments();
                    break;
                    case 4:
                        manager.displayAvailableAppointments();
                    break;
                    case 5:
                        manager.exit();
                        System.exit(0);
                    break;
                    default:
                        System.out.println("Invalid option... Please try again!");
                    break;
                }

            } while(choice != 5);
        }
        
    }

    public static void displayMenu() {
        System.out.println("Welcome To The Student Counselling Appointment System");
        System.out.println("1. Book an appointment");
        System.out.println("2. Cancel an appointment");
        System.out.println("3. View booked appointments");
        System.out.println("4. View available appointments");
        System.out.println("5. Exit");
        System.out.print("\t Enter your choice (number): ");
    }

    public static class AppointmentManager {
        private static final int MAX_APPOINTMENTS = 100;
        private static final String[] AVAILABLE_TIMESLOTS = {"9:00 AM", "10:00 AM", "11:00 AM", "1:00 PM", "2:00 PM"};
        private static Appointment[] appointments;
        private static int numAppointments;

        public AppointmentManager() {
            appointments = new Appointment[MAX_APPOINTMENTS];
            numAppointments = 0;
        }
        
        public void displayAvailableAppointments() {
            System.out.println("Available Appointments:");
            for (String time : AVAILABLE_TIMESLOTS) {
                boolean isAvailable = true;
                for (int i = 0; i < numAppointments; i++) {
                    if (appointments[i].getTime().equals(time)) {
                        isAvailable = false;
                        break;
                    }
                }
                if (isAvailable) {
                    System.out.println("Time: " + time);
                }
            }
        }

        public void bookAppointment(String studentName, String appointmentDate, String appointmentTime) {
            Appointment appointment = new Appointment(studentName, appointmentDate, appointmentTime);
            appointments[numAppointments++] = appointment;
            System.out.println("Appointment booked successfully.");
        }

        public void cancelAppointment(String appointmentDate, String appointmentTime) {
            for (int i = 0; i < numAppointments; i++) {
                if (appointments[i].getDate().equals(appointmentDate) && appointments[i].getTime().equals(appointmentTime)) {
                    appointments[i] = appointments[numAppointments - 1];
                    appointments[numAppointments - 1] = null;
                    numAppointments--;
                    System.out.println("Appointment canceled successfully.");
                    return;
                }
            }
            System.out.println("Appointment not found.");
        }

        public void displayBookedAppointments() {
            if (numAppointments == 0) {
                System.out.println("No appointments scheduled.");
                return;
            }
            System.out.println("All Appointments:");
            for (int i = 0; i < numAppointments; i++) {
                System.out.println(appointments[i]);
            }
        }

        public void exit() {
            System.out.println("Exit Successful...");
        }

    }
}


class Appointment {
    private String studentName;
    private String date;
    private String time;

    public Appointment(String studentName, String date, String time) {
        this.studentName = studentName;
        this.date = date;
        this.time = time;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Student: " + studentName + ", Date: " + date + ", Time: " + time;
    }
}