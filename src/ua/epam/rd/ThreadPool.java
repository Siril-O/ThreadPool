package ua.epam.rd;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

	private static final int INITIAL_POOL_SIZE = 2;

	private List<ThreadFromPool> threads;
	private BlockingQueue<Runnable> tasks = new BlockingQueue<>();
	private int poolSize;

	public ThreadPool(int poolSize) {
		super();
		this.poolSize = poolSize;
	}

	public ThreadPool() {
		super();
		poolSize = INITIAL_POOL_SIZE;
	}

	private void init() {
		threads = new LinkedList<>();
		for (int i = 0; i < poolSize; i++) {
			threads.add(new ThreadFromPool(tasks));
		}
		for (ThreadFromPool thread : threads) {
			thread.start();
		}
	}

	public void execute(Runnable task) {
		tasks.addLast(task);
	}

	public void stop() {
		for (ThreadFromPool thread : threads) {
			thread.terminate();
		}
	}

	public void startPool() {
		init();
	}

	public int avaliableTasks() {
		return tasks.size();
	}
}
