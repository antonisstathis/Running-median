public class Point {
	
	private int x;
	private int y;
	private float temperature;
	private RandomNumbersGenerator generator;
	
	public Point(RandomNumbersGenerator generator) {
		
		this.generator = generator;
		
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
	
	public void setNewTemp() {
		
		this.temperature = generator.createFloat(-50, 50);
	}
	
}
