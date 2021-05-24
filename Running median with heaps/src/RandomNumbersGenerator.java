import java.util.Random;

public class RandomNumbersGenerator {
	
	private Random generator;
	
	public RandomNumbersGenerator(Random generator) {
		
		this.generator=generator;
	}
	
	public float createFloat(int low,int high) {
		
		int range = high - low;
		int a = generator.nextInt(range);
		int number = a + low;
		float f = generator.nextFloat();
		float real = number + f;
		
		
		return real;
		
	}
	
	public int createInt(int low,int high) {
		
		int range = high - low;
		
		int a = generator.nextInt(range);
		int number = a + low;
		
		return number;
	}
}
