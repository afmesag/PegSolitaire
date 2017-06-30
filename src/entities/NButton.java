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

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public int getGridx() {
		return gridx;
	}

	public void setGridx(int gridx) {
		this.gridx = gridx;
	}

	public int getGridy() {
		return gridy;
	}

	public void setGridy(int gridy) {
		this.gridy = gridy;
	}

}
