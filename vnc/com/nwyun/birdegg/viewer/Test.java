package com.nwyun.birdegg.viewer;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class Test implements Runnable {

	public static void main(String[] args) {
		Test t = new Test();
		// TODO
		Thread thread = new Thread(t);
		thread.run();
	}

	@Override
	public void run() {
		// TODO
		System.out.println("run");
	}
}
