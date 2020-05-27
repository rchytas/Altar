package com.rchytas.utilities;
public class SingletonDemo {

	// the static singleton object
	private static final SingletonDemo theObject = new SingletonDemo();

	/**
	 * private constructor
	 */
	private SingletonDemo() {
	}

	public SingletonDemo getInstance() {
		return theObject;
	}
}
