package ua.epam.rd.threadpool;

public class ThreadFromPool extends Thread {

	private ThreadPool pool;
	private volatile boolean isStoped;

	public ThreadFromPool(ThreadPool pool) {
		super();
		this.pool = pool;
	}

	@Override
	public void run() {
		while (!isStoped) {
			try {
				Runnable task = pool.getTask();
				if (task != null) {
					task.run();
				}
			} catch (Exception e) {
				System.out.println("Something happend in the task. Thread:"
						+ Thread.currentThread().getName());
				e.printStackTrace();
			}
		}
		System.out.println("Thread from pool name:"
				+ Thread.currentThread().getName() + " terminated");
	}

	public void terminate() {
		isStoped = true;
	}

	public boolean isStoped() {
		return isStoped;
	}
}
