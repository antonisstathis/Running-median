
public class Point {
	
	int x;
	int y;
	float temperature;
	
	public Point(RandomNumbersGenerator generator) {
		
		x = generator.createInt(0, 1000);
		y = generator.createInt(0, 1000);
		temperature = generator.createFloat(-50, 50);
	}
	
	public int getX() {
		
		return x;
	}
	
	public int getY() {
		
		return y;
	}
	
	public float getTemp() {
		
		return temperature;
	}
	
}
