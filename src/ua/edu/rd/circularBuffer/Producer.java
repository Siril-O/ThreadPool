package ua.edu.rd.circularBuffer;

import java.util.LinkedList;
import java.util.List;

public class Producer implements Runnable {

	private CircularBuffer buffer;
	private static int counter;
	private volatile boolean isStoped;

	protected List<Product> products = new LinkedList<>();

	public Producer(CircularBuffer buffer) {
		super();
		this.buffer = buffer;
		isStoped = false;
	}

	@Override
	public void run() {
		while (!isStoped) {
			produce();
		}
		System.out.println(Thread.currentThread().getName() + " terminated");
	}

	public void produce() {
		Product product = new Product(counter++);
		products.add(product);
		buffer.add(product);
	}

	public void stop() {
		isStoped = true;
	}
}
