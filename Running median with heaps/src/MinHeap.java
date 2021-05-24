import java.util.Scanner;

public class MinHeap {
	
	private Point[] points;
	private int counter = 0;
	private int height = 1;
	private int[][] locators;
	private Scanner scanner;
	
	public MinHeap(int size) {
		
		points = new Point[size];
		locators = new int[1000][1000];
		scanner = new Scanner(System.in);
	}
	
	public void insert(Point point) {
		
		points[counter] = point;
		locators[point.getX()][point.getY()] = counter+1;
		counter++;
		calculateHeight();
	}
	
	public void heapify(int pos) {
		
		Point[] p = new Point[2];
		
		int i=pos;
		while(i>1) {
			
			//System.out.println("Enter heapify");
			//System.out.println("The heap before heapify");
			//printHeap();
			
			float temp1 = points[i-1].getTemp();
			float temp2 = points[(i/2)-1].getTemp();
			if(temp1<temp2) {
				p[0] = points[i-1];
				p[1] = points[(i/2)-1];
				
				points[i-1] = p[1];
				points[(i/2)-1] = p[0];
				locators[p[1].getX()][p[1].getY()] = i;
				locators[p[0].getX()][p[0].getY()] = i/2;
			}
			else {
				//System.out.println("The heap after heapify");
				//printHeap();
				//System.out.println("Exit heapify");
				break;
			}
			
			i=i/2;
			
			//System.out.println("The heap after heapify");
			//printHeap();
			//System.out.println("Exit heapify");
			//scanner.nextLine();
			
		}
		
	}
	
	public void deleteNode(int i,int c) {
		
		//System.out.println("\nEnter delete node");
		//System.out.println("The heap before deletion");
		//printHeap();
			
		Point leftChild = points[(2*i)-1];
		Point rightChild = points[(2*i+1)-1];
		//System.out.println("i="+String.valueOf(i));
		//System.out.println("parent="+String.valueOf(points[i-1].getTemp()));
		//if(leftChild!=null)
			//System.out.println("leftchild="+String.valueOf(leftChild.getTemp()));
		//if(rightChild!=null)
			//System.out.println("rightchild="+String.valueOf(rightChild.getTemp()));
		
		if(leftChild==null && rightChild==null) {
			//System.out.println("\n11\n");
			
			if(i==counter){
				
				if(c==0)
					locators[points[counter-1].getX()][points[counter-1].getY()] = 0;
				
				points[counter-1] = null;
			}
			
			if(i!=counter){
				
				locators[points[counter-1].getX()][points[counter-1].getY()] = i;
				if(c==0)
					locators[points[i-1].getX()][points[i-1].getY()] = 0;
				points[i-1] = points[counter-1];
				points[counter-1] = null;
				heapify(i);
			}
			
			counter--;
		}
		else {
			if(rightChild==null) {
				if(c==0)
					locators[points[i-1].getX()][points[i-1].getY()] = 0;
				locators[leftChild.getX()][leftChild.getY()] = i;
				points[i-1] = leftChild;
				
				points[(2*i)-1] = null;
				
				counter--;
				//System.out.println("i="+String.valueOf(i));
				//System.out.println("\n22\n");
			}
			else {
				if(leftChild.getTemp()<rightChild.getTemp()) {
					//System.out.println("\n33\n");
					if(c==0)
						locators[points[i-1].getX()][points[i-1].getY()] = 0;
					locators[leftChild.getX()][leftChild.getY()] = i;
					points[i-1] = leftChild;
					
					if(checkNode(2*i)==0) {
						//System.out.println("\n44\n");
						locators[points[(2*i+1)-1].getX()][points[(2*i+1)-1].getY()] = 2*i;
						points[(2*i)-1] = points[(2*i+1)-1];
						
						if((2*i+1)!=counter) {
							locators[points[counter-1].getX()][points[counter-1].getY()] = 2*i+1;
							points[(2*i+1)-1] = points[counter-1];
						}
						
						points[counter-1] = null;
						
						counter--;
						//System.out.println("i="+String.valueOf(i));
						if((counter+1)!=(2*i+1))
							heapify(2*i+1);
					}
					else {
						//System.out.println("\n55\n");
						c++;
						deleteNode(2*i,c);
					}
				}
				else {
					//System.out.println("\n66\n");
					if(c==0)
						locators[points[i-1].getX()][points[i-1].getY()] = 0;
					
					points[i-1] = rightChild;
					locators[rightChild.getX()][rightChild.getY()] = i;
					c++;
					deleteNode(2*i+1,c);
				}
			}
		}
		
		//System.out.println("The heap after deletion");
		//System.out.println("i="+String.valueOf(i));
		//printHeap();
		//System.out.println("Exit delete node");
	}
	
	public int checkNode(int i) {
		
		// returns the number of children a specific node of the heap has
		
		i = (2*i)-1;
		if(points[i]==null) {
			return 0;
		}
		else {
			if(points[i+1]==null) {
				return 1;
			}
			else {
				return 2;
			}
		}
		
	}
	
	public int calculateHeight() {
		
		boolean condition = true;
		int c=height;
		while(condition) {
			if(counter==c) {
				height = counter;
				condition = false;
			}
			if(c>counter)
				condition = false;
			c = c*2;
		}
		
		return height;
	}
	
	public boolean search(int x,int y) {
			
		if(locators[x][y] != 0) {
			//System.out.println("x="+String.valueOf(x)+" y="+String.valueOf(y));
			//System.out.println("MinHeap before search.");
			//printHeap();
			
			deleteNode(locators[x][y],0);
			//System.out.println("MinHeap after search.");
			//printHeap();
			return true;
		}
		
		return false;
	}
	
	public int getCounter() {
		
		return counter;
	}
	
	public Point getRoot() {
		
		return points[0];
	}
	
	public void printHeap() {
		
		String heap = "[";
		String c = ",";
		String end = "]";
		for(int i=0;i<counter;i++) {
			if(points[i]==null) {
				String tt = "null";
				heap = heap + tt;
			}
			else {
				float t = points[i].getTemp();
				String tt = String.valueOf(t);
				heap = heap + tt;
			}
			if(i!=counter-1)
				heap = heap + c;
			else
				heap = heap + end;
		}
		System.out.println(heap);
		
	}
	
}
