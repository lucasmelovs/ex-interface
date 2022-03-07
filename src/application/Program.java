package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxServices;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		
		System.out.println("Enter rental data");
		System.out.print("Car model: ");
		String carModel = sc.nextLine();
		System.out.print("Pickup (dd/MM/yyyy hh:ss): ");
		Date start = sdf.parse(sc.nextLine());
		System.out.print("Return (dd/MM/yyyy hh:ss): ");
		Date finish = sdf.parse(sc.nextLine());
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
		
		
		
		System.out.print("Enter price per hour: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Enter price per day: ");
		double pricePerDay = sc.nextDouble();
	
		RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxServices());
		rentalService.processInvoice(cr);
		
		System.out.println("INVOICE");
		System.out.println("Basic payment " + cr.getInvoice().getBasicPayment());
		System.out.println("Tax " + cr.getInvoice().getTax());
		System.out.println("Total paymento " + cr.getInvoice().getTotalPayment());
		
		sc.close();
	}

}
