package curs;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
 
public class CarRentalSystem {
 
  private HashMap<String, String> rentedCars = new HashMap<String, String>(100, 0.5f);
  private HashMap<String, RentedCars> myMap = new HashMap<>();
  
 
  private static String getPlateNo(Scanner sc) {
    System.out.println("Introduceti numarul de inmatriculare:");
    return sc.nextLine();
  }
 
  private static String getOwnerName(Scanner sc) {
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
    System.out.println("\nhelp         - Afiseaza aceasta lista de comenzi");
    System.out.println("add          - Adauga o noua pereche (masina, sofer)");
    System.out.println("check        - Verifica daca o masina este deja luata");
    System.out.println("remove       - Sterge o masina existenta din hashtable");
    System.out.println("getOwner     - Afiseaza proprietarul curent al masinii");
    System.out.println("quit         - Inchide aplicatia");
    System.out.println("totalRented  - Numarul total de masini inchiriate");
    System.out.println("getCarsNo    - Numarul de masini inchiriate de proprietarul temporar");
    System.out.println("getCarsList  - Lista masinilor inchiriate de proprietarul temporar\n");
  }
 
  public void run() throws IOException{
	  
	  try(Scanner sc = new Scanner(new FileReader("input.txt"))){
		  while(sc.hasNextLine()) {
		    boolean quit = false;
		    while(!quit) {
		      System.out.println("\nAsteapta comanda: (help - Afiseaza lista de comenzi)");
		      String command = sc.nextLine();
		      switch(command) {
		        case "help":
		          printCommandsList();
		          break;
		        case "add":
		          rentCar(getPlateNo(sc), getOwnerName(sc));
		          break;
		        case "check":
		          System.out.println(isCarRent(getPlateNo(sc)));
		          break;
		        case "remove":
		          returnCar(getPlateNo(sc));
		          break;
		        case "getOwner":
		          System.out.println(getCarRent(getPlateNo(sc)));
		          break;
		        case "totalRented":  
		          System.out.println(getTotalNumberOfCars());
		          break;
		        case "getCarsNo":
		          getCarsNo(getOwnerName(sc));
		          break;
		        case "getCarsList":
		          getCarsList(getOwnerName(sc));
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
	  }
  }
 
  public static void main(String[] args) throws IOException{
 
    new CarRentalSystem().run();
 
  }
}