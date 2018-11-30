package demo;

import java.util.concurrent.Semaphore;

public class TriangleThread implements Runnable{
	static Semaphore check = new Semaphore(1);
	
	public void run() {
		try {
			check.acquire();//critical section
			PrintTriangle();
		} 
		catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		finally {
			check.release();;//end critical section
		}
		try {
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+" Task is no longer sleeping");
		
	}
	
	synchronized public void PrintTriangle() {
		System.out.println("*");
		for (int i=1;i<=7;i++) {
			for(int j=1;j<=i;j++) {
				System.out.print("*");
			}
			System.out.println("**");
		}
	}
	
	public static void main(String [] args) {
		Thread first = new Thread(new TriangleThread(),"First Triangle");
		first.start();
		Thread second = new Thread(new TriangleThread(),"Second Triangle");
		second.start();
		Thread third = new Thread(new TriangleThread(),"Third Triangle");
		third.start();
	}
}
