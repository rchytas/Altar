package com.rchytas.utilities;
/**
 * Example of Javadeadlock.
 * @author Rchytas
 *
 */

public class JavaDeadlock implements Runnable {
	String s1;
	String s2;

	public JavaDeadlock(String s1, String s2) {
		this.s1 = s1;
		this.s2 = s2;
	}

	public void printOne() throws Exception {
		synchronized (s1) {
			Thread.sleep(1000);
			synchronized (s2) {
				System.out.println(s1 + s2);
			}
		}
	}

	public void printTwo() throws Exception {
		synchronized (s2) {
			Thread.sleep(1000);
			synchronized (s1) {
				System.out.println(s1 + s2);
			}
		}
	}

	public void run() {
		try {
			printOne();
		} catch (Exception e) {

		}
	}

	public static void main(String[] args) throws Exception {
		String s1 = "dead";
		String s2 = "lock";
		JavaDeadlock t = new JavaDeadlock(s1, s2);
		new Thread(t).start();
		t.printTwo();
	}
}
