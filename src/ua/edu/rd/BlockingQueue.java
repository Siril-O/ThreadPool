package ua.edu.rd;

import java.util.Deque;
import java.util.LinkedList;

public class BlockingQueue<E> {

	private Deque<E> queue = new LinkedList<>();

	public synchronized void addLast(E e) {
		queue.addLast(e);
	}

	public synchronized E poll() {
		return queue.poll();
	}

	public synchronized int size() {
		return queue.size();
	}
}
