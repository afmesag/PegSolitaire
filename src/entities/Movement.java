package entities;

public class Movement {
	int[] neighbor;
	int[] start;
	int[] end;
	
	/**
	 * Constructor for Movement entity
	 * @param neighbor an array of coordinates
	 * @param start an array of coordinates
	 * @param end an array of coordinates
	 */
	public Movement(int[] neighbor, int[] start, int[] end){
		this.neighbor = neighbor;
		this.start = start;
		this.end = end;
	}

	/**
	 * Gets the array neighbor
	 * @return neighbor array
	 */
	public int[] getNeighbor() {
		return neighbor;
	}
	
	/**
	 * Sets the array neighbor
	 * @param new neighbor array
	 */
	public void setNeighbor(int[] neighbor) {
		this.neighbor = neighbor;
	}

	/**
	 * Gets the array start
	 * @return start array
	 */
	public int[] getStart() {
		return start;
	}

	/**
	 * Sets the array start
	 * @param new start array
	 */
	public void setStart(int[] start) {
		this.start = start;
	}

	/**
	 * Gets the array end
	 * @return end array
	 */
	public int[] getEnd() {
		return end;
	}

	/**
	 * Sets the array end
	 * @return new end array
	 */
	public void setEnd(int[] end) {
		this.end = end;
	}
	
}
