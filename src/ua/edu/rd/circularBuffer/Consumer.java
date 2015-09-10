package ua.edu.rd.circularBuffer;

import java.util.LinkedList;
import java.util.List;

public class Consumer implements Runnable {

	private CircularBuffer buffer;
	private boolean isStoped;

	protected List<Product> products = new LinkedList<>();

	public Consumer(CircularBuffer buffer) {
		super();
		this.buffer = buffer;
		isStoped = false;
	}

	@Override
	public void run() {
		while (!isStoped) {
			consume();
		}
		System.out.println(Thread.currentThread().getName() + " terminated");

	}

	private void consume() {

		Product product = buffer.get();
		if (product != null) {
			products.add(product);
		}
	}

	public void stop() {
		isStoped = true;
	}
}
