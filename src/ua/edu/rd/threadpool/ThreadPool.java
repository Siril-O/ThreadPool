package ua.epam.rd.threadpool;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ThreadPool {

	private static final int INITIAL_POOL_SIZE = 2;

	private List<ThreadFromPool> threads;
	private Queue<Runnable> tasks = new LinkedList<>();
	private int poolSize;
	private volatile boolean isStoped;

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
			threads.add(new ThreadFromPool(this));
		}
		for (ThreadFromPool thread : threads) {
			thread.start();
		}
	}

	public void execute(Runnable task) {
		if (isStoped) {
			return;
		}
		synchronized (tasks) {
			tasks.add(task);
			tasks.notifyAll();
		}
	}

	public Runnable getTask() {
		synchronized (tasks) {
			while (isEmpty() && (!isStoped)) {
				try {
					tasks.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return tasks.poll();
		}
	}

	private synchronized boolean isEmpty() {
		return tasks.isEmpty();
	}

	public void stop() {
		for (ThreadFromPool thread : threads) {
			thread.terminate();
		}
		isStoped = true;
		synchronized (tasks) {
			tasks.notifyAll();
		}
	}

	public void startPool() {
		init();
	}

	public synchronized int avaliableTasks() {
		return tasks.size();
	}
}
