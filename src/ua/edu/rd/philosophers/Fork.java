package ua.epam.rd.philosophers;

public class Fork {

	private int id;
	private volatile boolean isAvaliable = true;

	/**
	 * @return the isAvaliable
	 */
	public boolean isAvaliable() {
		return isAvaliable;
	}

	/**
	 * @param isAvaliable
	 *            the isAvaliable to set
	 */
	public void setAvaliable(boolean isAvaliable) {
		this.isAvaliable = isAvaliable;
	}

	public Fork(int id) {
		super();
		this.id = id;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Fork [id=" + id + "]";
	}

}
