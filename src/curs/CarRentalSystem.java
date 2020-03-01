package curs;

import java.util.HashMap;
import java.util.Scanner;
 
public class CarRentalSystem {
 
  private static Scanner sc = new Scanner(System.in);
  private HashMap<String, String> rentedCars = new HashMap<String, String>(100, 0.5f);
  private HashMap<String, RentedCars> myMap = new HashMap<>();
  
 
  private static String getPlateNo() {
    System.out.println("Introduceti numarul de inmatriculare:");
    return sc.nextLine();
  }
 
  private static String getOwnerName() {
    System.out.println("Introduceti numele proprietarului:");
    return sc.nextLine();
  }
 
  // search for a key in hashtable
  private boolean isCarRent(String plateNo) {
    return this.rentedCars.containsKey(plateNo);
  }
 
  // get the value associated to a key
  private String getCarRent(String plateNo) {
	  if(isCarRent(plateNo)) {
		  return this.rentedCars.get(plateNo);
	  }else return "Masina introdusa nu este in sistem";
  }
  
  private int getTotalNumberOfCars() {
	  
	  return this.rentedCars.size();
	  
  }
  
  private void getCarsNo(String ownerName) {
	  if(myMap.containsKey(ownerName)) {
		  System.out.println("Proprietarul " + ownerName + " are " 
			  	+  myMap.get(ownerName).cars.size() + " masini inchiriate") ;
	  }else System.out.println("Numele introdus nu exista in baza de date");
	  
  }
  
  private void getCarsList(String ownerName) {
	  if(myMap.containsKey(ownerName)) {
		  System.out.println("Proprietarul " + ownerName + " are inchiriate masinile cu numerele:" 
			  	+  myMap.get(ownerName).cars) ;
	  }else System.out.println("Numele introdus nu exista in baza de date");
	  
  }
 
  // add a new (key, value) pair
  private void rentCar(String plateNo, String ownerName) {
	
	if(myMap.containsKey(ownerName)) {
		
		RentedCars aux = myMap.get(ownerName);
		aux.addCar(plateNo);
		
	}else {
		RentedCars aux = new RentedCars();
		aux.addCar(plateNo);
		myMap.put(ownerName, aux);
	}
	  
	if(isCarRent(plateNo)) {
		System.out.println("Eroare: Masina este deja inchiriata");
		return;
	}
    this.rentedCars.put(plateNo, ownerName);
  }
 
  // remove an existing (key, value) pair
  private void returnCar(String plateNo) {
	  
	  if(isCarRent(plateNo)) {
		  System.out.println("Clientul " + this.rentedCars.get(plateNo) + " a returnat masina cu numarul de inmatricluare " + plateNo);
		  rentedCars.remove(plateNo);
		  
	  }else System.out.println("Masina nu este in sistem");
    
  }
 
  private static void printCommandsList() {
    System.out.println("help         - Afiseaza aceasta lista de comenzi");
    System.out.println("add          - Adauga o noua pereche (masina, sofer)");
    System.out.println("check        - Verifica daca o masina este deja luata");
    System.out.println("remove       - Sterge o masina existenta din hashtable");
    System.out.println("getOwner     - Afiseaza proprietarul curent al masinii");
    System.out.println("quit         - Inchide aplicatia");
    System.out.println("totalRented  - Numarul total de masini inchiriate");
    System.out.println("getCarsNo    - Numarul de masini inchiriate de proprietarul temporar");
    System.out.println("getCarsList  - Lista masinilor inchiriate de proprietarul temporar");
  }
 
  public void run() {
    boolean quit = false;
    while(!quit) {
      System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
      String command = sc.nextLine();
      switch(command) {
        case "help":
          printCommandsList();
          break;
        case "add":
          rentCar(getPlateNo(), getOwnerName());
          break;
        case "check":
          System.out.println(isCarRent(getPlateNo()));
          break;
        case "remove":
          returnCar(getPlateNo());
          break;
        case "getOwner":
          System.out.println(getCarRent(getPlateNo()));
          break;
        case "totalRented":  
          System.out.println(getTotalNumberOfCars());
          break;
        case "getCarsNo":
          getCarsNo(getOwnerName());
          break;
        case "getCarsList":
          getCarsList(getOwnerName());
          break;  
        case "quit":
          System.out.println("Aplicatia se inchide...");
          quit = true;
          break;
        default:
          System.out.println("Unknown command. Choose from:");
          printCommandsList();
      }
    }
  }
 
  public static void main(String[] args) {
 
    // create and run an instance (for test purpose)
    new CarRentalSystem().run();
 
  }
}