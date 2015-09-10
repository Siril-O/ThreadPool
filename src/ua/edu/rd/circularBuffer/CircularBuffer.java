package ua.edu.rd.circularBuffer;

import java.util.ArrayList;
import java.util.List;

public class CircularBuffer {

	private List<Product> buffer;
	private int capacity;

	protected volatile int size;
	private int readIndex;
	private int writeIndex;

	private boolean isStoped = false;

	public CircularBuffer(int capacity) {
		super();
		buffer = new ArrayList<>(capacity);
		this.capacity = capacity;
	}

	public Product get() {
		synchronized (this) {
			while (size == 0) {
				try {
					wait();
					if (isStoped && size == 0) {
						Thread.currentThread().interrupt();
						System.out.println(Thread.currentThread().getName()
								+ " is interupted");
						notifyAll();
					}
				} catch (InterruptedException e) {
					return null;
				}
			}

			Product element = buffer.remove(readIndex);
			// Product element = buffer[readIndex];
			// buffer[readIndex] = null;
			readIndex = incrementIndex(readIndex);
			size--;
			System.out.println(Thread.currentThread().getName() + " consumed: "
					+ element);
			// print();
			notifyAll();
			return element;

		}
	}

	public void add(Product element) {
		synchronized (this) {
			while (size == capacity) {
				try {
					wait();
					if (isStoped) {
						Thread.currentThread().interrupt();
						System.out.println(Thread.currentThread().getName()
								+ " is interupted");
						notifyAll();
					}
				} catch (InterruptedException e) {
					return;
				}
			}
			buffer.add(writeIndex, element);
			// buffer[writeIndex] = element;
			// writeIndex = incrementIndex(writeIndex);
			size++;
			// print();
			notifyAll();
		}

	}

	private synchronized int incrementIndex(int index) {
		if (index == (capacity - 1)) {
			return 0;
		} else {
			return index++;
		}
	}

	public void stopQueue() {
		isStoped = true;
	}
	// private synchronized void print() {
	// StringBuilder builder = new StringBuilder();
	// builder.append("Buffer[");
	// for (int i = 0; i < buffer.length; i++) {
	// builder.append(buffer[i] == null ? "0" : "1").append(",");
	// }
	// builder.append("] size:" + size).append(" readIndex:" +
	// readIndex).append(" writeIndex:" + writeIndex);
	// System.out.println(builder.toString());
	// }

}
