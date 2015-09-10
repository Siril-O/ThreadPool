package ua.epam.rd.philosophers;

import java.util.Random;

public class Philosopher implements Runnable {

	private static final int MAX_THINKING_TIME = 100;
	private static final int MAX_EATING_TIME = 50;

	private int id;
	private Fork firstFork;
	private Fork secondFork;
	private String name;
	private volatile boolean isStoped;
	private Random random = new Random();
	protected int eatingQuantity;

	public Philosopher(int id, Fork firstFork, Fork secondFork, String name) {
		super();
		this.id = id;
		this.firstFork = firstFork;
		this.secondFork = secondFork;
		this.name = name;
	}

	@Override
	public void run() {
		while (!isStoped) {
			eat();
			think();
		}
		System.out.println(name + " is going home!");
	}

	private void eat() {
		if (firstFork.getId() > secondFork.getId()) {
			Fork tmp = firstFork;
			firstFork = secondFork;
			secondFork = tmp;
		}
		synchronized (firstFork) {
			synchronized (secondFork) {
				System.out.println(name + " start eat.");
				try {
					Thread.sleep(random.nextInt(MAX_EATING_TIME));
				} catch (InterruptedException e) {
					System.out.println(name + " interupted");
					e.printStackTrace();
				}
				eatingQuantity++;
				System.out.println(name + " finished eat.");
			}
		}
	}

	private void think() {
		System.out.println(name + " start thinking.");
		try {
			Thread.sleep(random.nextInt(MAX_THINKING_TIME));
		} catch (InterruptedException e) {
			System.out.println(name + " interupted");
			e.printStackTrace();
		}
		System.out.println(name + " finished thinking.");

	}

	public void stop() {
		isStoped = true;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Philosopher other = (Philosopher) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Philosopher [id=" + id + " name=" + name + "]";
	}

}
