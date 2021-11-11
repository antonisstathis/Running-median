import java.util.Random;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		int N = 520000;
		int M = 500000;
		int counter = 0;
		int c = 0;
		Point median = null;
		
		MaxHeap heap1 = new MaxHeap(N); // lower temperatures
		MinHeap heap2 = new MinHeap(N); // higher temperatures
		Point[] first100 = new Point[100];
		int f100 = 0;
		Point point = null;
		int counter1 = 0;
		int counter2 = 0;
		int diff = 0;
		boolean con1 = false;
		boolean con2 = false;
		boolean con3 = false;
		
		Random g = new Random(228524);
		RandomNumbersGenerator generator = new RandomNumbersGenerator(g);
		
		long start = System.currentTimeMillis();
		// the while loop that follows creates 500000 points and saves them in data structures
		while(counter<M || c<=100) {
			
			if(counter<M) {
				point = new Point(generator);
				System.out.println("\nRep="+String.valueOf(counter+1));
				System.out.println("New point produced with temp="+String.valueOf(point.getTemp()));
			}
			
			//scanner.nextLine();
			if(counter==0) {
				median = point;
				first100[f100] = point;
				f100++;
				print(median.getTemp(),counter+1,counter1,counter2);
				counter++;
			}
			else {
				
				
				con1 = heap1.search(point.getX(),point.getY());
				con2 = heap2.search(point.getX(),point.getY());
				if(con1) {
					heap1.deleteNode(heap1.search(point),0);
					counter1--;
					counter--;
					diff = counter2-counter1;
					System.out.println("The point (" + String.valueOf(point.getX()) + "," + String.valueOf(point.getY()) + ") already exists and will be updated with the new temperature.");
					if(diff==2 && (counter%2==1)) {
						//System.out.println("111");
						heap1.insert(median);
						counter1++;
						heap1.heapify(counter1);
						median = heap2.getRoot();
						heap2.deleteNode(1,0);
						counter2--;
						//float temp = median.getTemp();
						//printResults(heap1,heap2,median);
						//print(temp,counter,counter1,counter2);
						//scanner.nextLine();
					}
				}
				if(con2) {
					heap2.deleteNode(heap2.search(point),0);
					counter2--;
					counter--;
					diff = counter1-counter2;
					System.out.println("The point (" + String.valueOf(point.getX()) + "," + String.valueOf(point.getY()) + ") already exists and will be updated with the new temperature.");
					if(diff==2 && (counter%2==1)) {
						//System.out.println("444");
						heap2.insert(median);
						counter2++;
						heap2.heapify(counter2);
						median = heap1.getRoot();
						heap1.deleteNode(1,0);
						counter1--;
						//float temp = median.getTemp();
						//printResults(heap1,heap2,median);
						//print(temp,counter,counter1,counter2);
						//scanner.nextLine();
					}
				}
				
				con3 = point.getX()==median.getX() && point.getY()==median.getY();
				if(con3) {
					
					System.out.println("The point (" + String.valueOf(point.getX()) + "," + String.valueOf(point.getY()) + ") already exists and will be updated with the new temperature.");
					if( (diff==1 && (counter%2==0)) || (diff==2 && (counter%2==1)) ) {
						if(counter1>counter2) {
							median = heap1.getRoot();
							heap1.deleteNode(1,0);
							counter1--;
							counter--;
						}
						else {
							median = heap2.getRoot();
							heap2.deleteNode(1,0);
							counter2--;
							counter--;
						}
					}
					
					if(diff==0 && (counter%2==1)) {
						median = heap2.getRoot();
						heap2.deleteNode(1,0);
						counter2--;
						counter--;
					}
				}
				
				
				if(point.getTemp()>median.getTemp()) {
					
					heap2.insert(point);
					counter2++;
					heap2.heapify(counter2);
					counter++;
					diff = counter2-counter1;
					//System.out.println("difference="+String.valueOf(diff));
					
					if(diff==2 && (counter%2==1)) {
						//System.out.println("111");
						heap1.insert(median);
						counter1++;
						heap1.heapify(counter1);
						median = heap2.getRoot();
						heap2.deleteNode(1,0);
						counter2--;
						float temp = median.getTemp();
						//printResults(heap1,heap2,median);
						print(temp,counter,counter1,counter2);
						//scanner.nextLine();
					}
					
					if(diff==1 && (counter%2==0)) {
						//System.out.println("222");
						Point point1 = heap2.getRoot();
						float m = (median.getTemp() + point1.getTemp())/2;
						//printResults(heap1,heap2,median);
						print(m,counter,counter1,counter2);
						//scanner.nextLine();
					}
					
					if(diff==0 && (counter%2==1)) {
						//System.out.println("333");
						float m = median.getTemp();
						//printResults(heap1,heap2,median);
						print(m,counter,counter1,counter2);
						//scanner.nextLine();
					}
					
				}
				else {
					heap1.insert(point);
					counter1++;
					heap1.heapify(counter1);
					counter++;
					diff = counter1-counter2;
					//System.out.println("difference="+String.valueOf(diff));
					
					if(diff==2 && (counter%2==1)) {
						//System.out.println("444");
						heap2.insert(median);
						counter2++;
						heap2.heapify(counter2);
						median = heap1.getRoot();
						heap1.deleteNode(1,0);
						counter1--;
						float temp = median.getTemp();
						//printResults(heap1,heap2,median);
						print(temp,counter,counter1,counter2);
						//scanner.nextLine();
					}
					
					if(diff==1 && (counter%2==0)) {
						//System.out.println("555");
						Point point1 = heap1.getRoot();
						float m = (median.getTemp() + point1.getTemp())/2;
						//printResults(heap1,heap2,median);
						print(m,counter,counter1,counter2);
						//scanner.nextLine();
					}
					
					if(diff==0 && (counter%2==1)) {
						//System.out.println("666");
						float m = median.getTemp();
						//printResults(heap1,heap2,median);
						print(m,counter,counter1,counter2);
						//scanner.nextLine();
					}
					
				}
				
				// saves the first 100 points for next use
				boolean con = con1 || con2 || con3;
				if(!con && f100!=100) {
					point.setNewTemp();
					first100[f100] = point;
					f100++;
				}
				
			}
			
			if(counter>=M && c!=0 && c<101) {
				
				if(c!=100) {
					System.out.println("\nRep="+String.valueOf(c+1));
					point = first100[c];
				}
				c++;
			}
			
			if(counter>=M && c==0) {
				
				System.out.println("\n\nAll 500000 points are saved in the two heaps and in the median variable.");
				System.out.println("Now the first different 100 points that produced will be updated with new temperatures.\n\n");
				
				System.out.println("Rep="+String.valueOf(c+1));
				point = first100[c];
				c++;
			}
			
		}
		
		long end = System.currentTimeMillis();
		System.out.println("\nIt took: " + (end - start) + "ms.");
		
		System.out.println("Programm terminated.");
		
	}
	
	public static void print(float median,int counter,int counter1,int counter2) {
		
		System.out.println("counter1="+String.valueOf(counter1)+" counter2="+String.valueOf(counter2));
		System.out.println("median="+String.valueOf(median)+" for counter="+String.valueOf(counter));
	}
	
	public static void printResults(MaxHeap heap1,MinHeap heap2,Point median) {
		
		System.out.println("Heap1:");
		heap1.printHeap();
		System.out.println("median="+String.valueOf(median.getTemp()));
		System.out.println("Heap2:");
		heap2.printHeap();
		
	}

}
