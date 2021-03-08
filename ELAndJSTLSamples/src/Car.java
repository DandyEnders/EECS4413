
public class Car {
	private String make;
	private int    year;
	private String color;

	public Car() {
		
	};

public Car(String m, int y, String c) {
	make=m;
	year=y;
	color=c;
}

public String getMake() {
	return make;
}

public void setMake(String make) {
	this.make = make;
}

public int getYear() {
	return year;
}

public void setYear(int year) {
	this.year = year;
}

public String getColor() {
	return color;
}

public void setColor(String color) {
	this.color = color;
}
}
