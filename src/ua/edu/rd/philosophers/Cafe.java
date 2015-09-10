package ua.epam.rd.philosophers;

import java.util.LinkedList;
import java.util.List;

public class Cafe {

	private static final int PHILOSOPHER_QUANTITY = 5;
	private static List<Philosopher> philosophers;

	public Cafe() {
		super();
	}

	public void init() {
		philosophers = new LinkedList<>();
		PhilosofersNames[] names = PhilosofersNames.values();

		for (int i = 0; i < PHILOSOPHER_QUANTITY; i++) {
			Fork firstFork = new Fork(i);
			Fork secondFork = new Fork(i == (PHILOSOPHER_QUANTITY - 1) ? 0
					: (i + 1));
			Philosopher philosopher = new Philosopher(i, firstFork, secondFork,
					names[i].name());

			philosophers.add(philosopher);
		}

	}

	public static void main(String[] args) {

		Cafe cafe = new Cafe();
		cafe.init();

		for (Philosopher philosopher : philosophers) {
			new Thread(philosopher).start();
		}

		sleepForTime(10000);

		for (Philosopher philosopher : philosophers) {
			philosopher.stop();
		}

		sleepForTime(500);

		for (Philosopher philosopher : philosophers) {
			System.out.println(philosopher.getName() + ": eated:"
					+ philosopher.eatingQuantity + " times.");
		}
	}

	private static void sleepForTime(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
