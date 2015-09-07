package ua.epam.rd;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreadPoolRunner {

	private static final int TASKS_QUANTITY = 30;
	private static final Random random = new Random();

	public static void main(String... args) {

		List<Task> tasks = new ArrayList<>();

		for (int i = 0; i < TASKS_QUANTITY; i++) {
			tasks.add(new Task("Name" + i));
		}

		ThreadPool threadPool = new ThreadPool(3);
		threadPool.startPool();

		for (Task task : tasks) {
			threadPool.execute(task);
		}

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("AvaliableTasks:" + threadPool.avaliableTasks());
		threadPool.stop();
	}

	private static class Task implements Runnable {

		private String name;

		public Task(String name) {
			super();
			this.name = name;
		}

		@Override
		public void run() {
			long time = System.currentTimeMillis();
			System.out.println("Task name:" + name + " is executing with Thread:"
					+ Thread.currentThread().getName());
			try {
				Thread.sleep(random.nextInt(400));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			time = System.currentTimeMillis() - time;
			System.out.println("Task name:" + name + " time spended:" + time
					+ " Thread:" + Thread.currentThread().getName()
					+ " finished execution.");
		}
	}
}
