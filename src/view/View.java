package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JFrame implements Serializable {

	private static final long serialVersionUID = -2579501939935352912L;

	private final JPanel panel = new JPanel();
	private final GridBagLayout holes;
	private final JButton[] pegs;
	private final GridBagConstraints c;
	
	/**
	 * Constructor for the view class
	 */
	public View() {
		super("Pegs Solitaire beta - Software Maintenance");
		setResizable(false);
		holes = new GridBagLayout();
		pegs = new JButton[33];
		c = new GridBagConstraints();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addComponentsToPane(getContentPane());
		pack();
		setVisible(true);
	}

	/**
	 * Sets the locations of a component in a GridBagLayout
	 * @param gridx an integer meaning the number of column
	 * @param gridy an integer meaning the number of row
	 */
	public void setGridColRow(int gridx, int gridy) {
		c.gridx = gridx;
		c.gridy = gridy;
	}
	
	/**
	 * Creates and sets a JButton, locates it's position on a given layout
	 * and finally adds it to the panel.
	 * @param gridx an integer meaning the number of column
	 * @param gridy an integer meaning the number of row
	 */
	
	public void pegSetting(int gridx, int gridy, int button) {
		pegs[button] = new JButton();
		setGridColRow(gridx, gridy);
		panel.add(pegs[button], c);
	}
	
	/**
	 * Sets the default (Figure 1) configuration for Pegs solitaire game
	 */
	public void defaultConfig() {
		int count = 0;
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7; y++) {
				if ((x < 2 || x > 4) && (y < 2 || y > 4))
					continue;
				if (!(x == 3 && y == 3)) {
					pegSetting(y, x, count);
					count++;
				}
			}
		}
	}

	/**
	 * Adds the panel along with its buttons to the pane.
	 */
	public void addComponentsToPane(final Container pane) {
		panel.setLayout(holes);
		panel.setPreferredSize(new Dimension(600, 600));
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 0.5;
		defaultConfig();
		// Figure 1 configuration
		pane.add(panel);
	}
}
