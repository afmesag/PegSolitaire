package entities;

import java.io.Serializable;

import javax.swing.JButton;

public class NButton extends JButton implements Serializable {

	private static final long serialVersionUID = -4490599880394264046L;
	private JButton button;
	private int gridx;
	private int gridy;

	public NButton(JButton newButton, int gridx, int gridy) {
		this.button = newButton;
		this.gridx = gridx;
		this.gridy = gridy;
	}
	
	/**
	 * Gets the JButton
	 * @return JButton
	 */
	public JButton getButton() {
		return button;
	}
	
	/**
	 * Changes the given button
	 * @param button
	 */
	public void setButton(JButton button) {
		this.button = button;
	}
	
	/**
	 * Gets the location in the grid in X
	 * @return an integer representing the location in one dimension
	 */
	public int getGridx() {
		return gridx;
	}

	/**
	 * Sets the actual location with a new one in dimension X (columns)
	 * @param gridx, new location
	 */
	public void setGridx(int gridx) {
		this.gridx = gridx;
	}

	/**
	 * Gets the location in the grid in Y
	 * @return an integer representing the location in one dimension
	 */
	public int getGridy() {
		return gridy;
	}
	
	/**
	 * Sets the actual location with a new one in dimension Y (rows)
	 * @param gridy
	 */
	public void setGridy(int gridy) {
		this.gridy = gridy;
	}

}
