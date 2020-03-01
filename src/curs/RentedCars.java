package curs;

import java.util.LinkedList;
import java.util.List;

public class RentedCars {

	
	List<String> cars = new LinkedList<>();
	
	
	public void addCar(String car) {
		
		this.cars.add(car);
		
	}


	@Override
	public String toString() {
		return "" + cars;
	}
	
	
	
}
