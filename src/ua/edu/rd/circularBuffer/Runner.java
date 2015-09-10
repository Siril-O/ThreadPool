package ua.edu.rd.circularBuffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Runner {

	private static final int CONSUMER_QUANTITY = 2;
	private static final int PRODUCER_QUANTITY = 4;

	public static void main(String... args) {
		List<Producer> producers = new ArrayList<>();
		List<Consumer> consumers = new ArrayList<>();

		CircularBuffer buffer = new CircularBuffer(10);
		for (int i = 0; i < CONSUMER_QUANTITY; i++) {
			consumers.add(new Consumer(buffer));
		}

		for (int i = 0; i < PRODUCER_QUANTITY; i++) {
			producers.add(new Producer(buffer));
		}

		for (Producer producer : producers) {
			new Thread(producer, "Producer").start();
		}

		for (Consumer consumer : consumers) {
			new Thread(consumer, "Consumer").start();
		}

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (Producer producer : producers) {
			producer.stop();
		}

		buffer.stopQueue();

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (Consumer consumer : consumers) {
			consumer.stop();
		}

		synchronized (buffer) {
			buffer.notifyAll();
		}
		List<Product> allProducedProducts = new ArrayList<>();
		List<Product> allConsumedProducts = new ArrayList<>();

		for (Producer producer : producers) {
			allProducedProducts.addAll(producer.products);
			// System.out.println(producer.products);
		}
		for (Consumer consumer : consumers) {
			allConsumedProducts.addAll(consumer.products);
			// System.out.println(consumer.products);
		}

		System.out.println(allProducedProducts);
		System.out.println(allConsumedProducts);

		System.out.println(buffer.size);
		System.out.println(Arrays.deepEquals(allProducedProducts.toArray(),
				allConsumedProducts.toArray()));

	}
}
