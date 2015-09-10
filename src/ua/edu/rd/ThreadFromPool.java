package ua.edu.rd;

public class ThreadFromPool extends Thread {

	private BlockingQueue<Runnable> tasks;
	private volatile boolean isStoped;

	public ThreadFromPool(BlockingQueue<Runnable> tasks) {
		super();
		this.tasks = tasks;
	}

	@Override
	public void run() {
		while (!isStoped) {
			try {
				Runnable task = tasks.poll();
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

	public  void terminate() {
		isStoped = true;
	}

	public  boolean isStoped() {
		return isStoped;
	}
}
