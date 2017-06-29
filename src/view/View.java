package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import util.ComponentMover;

public class View extends JFrame implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2579501939935352912L;

	private final static int UPRIGHT = 0;
	private final static int UPlEFT = 1;
	private final static int DOWNRIGHT = 2;
	private final static int DOWNLEFT = 3;

	private final JPanel panel = new JPanel();
	private final GridBagLayout holes;
	private final JButton[] pegs;
	private final JButton[] spaces;
	private final GridBagConstraints c;
	private final ComponentMover g;

	public View() {
		super("Pegs Solitaire beta - Software Maintenance");
		setResizable(false);
		holes = new GridBagLayout();
		pegs = new JButton[33];
		spaces = new JButton[4];
		c = new GridBagConstraints();
		g = new ComponentMover();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addComponentsToPane(getContentPane());
		pack();
		setVisible(true);
	}

	public void setGridColRow(int gridx, int gridy) {
		c.gridx = gridx;
		c.gridy = gridy;
	}

	public void setCornerSpaces(int corner) {
		this.spaces[corner] = new JButton();
		this.spaces[corner].setEnabled(false);
		if (corner == UPRIGHT) {
			setGridColRow(0, 0);
			c.anchor = GridBagConstraints.NORTHEAST;
		}
		if ((corner == UPlEFT)) {
			setGridColRow(2, 0);
			c.anchor = GridBagConstraints.NORTHWEST;
		}
		if ((corner == DOWNRIGHT)) {
			setGridColRow(0, 2);
			c.anchor = GridBagConstraints.SOUTHEAST;
		}
		if ((corner == DOWNLEFT)) {
			setGridColRow(2, 2);
			c.anchor = GridBagConstraints.SOUTHWEST;
		}
		c.weightx = 0.5;
		c.weighty = 0.5;
		panel.add(spaces[corner], c);
	}

	public void setPegs(int gridx, int gridy, int start, int finish) {
		for (int i = start; i < finish; i++) {
			pegs[i] = new JButton();
			setGridColRow(gridx, gridy);
			g.registerComponent(pegs[i]);
			panel.add(pegs[i], c);
		}
	}

	/**
	 * Adds the panel along with its buttons to the pane.
	 */
	public void addComponentsToPane(final Container pane) {
		panel.setLayout(holes);
		panel.setPreferredSize(new Dimension(800, 800));
		c.fill = GridBagConstraints.BOTH;
		for (int i = 0; i < spaces.length; i++) {
			setCornerSpaces(i);
		}
		//Figure 1 configuration
		setPegs(1, 0, 0, 5);
		setPegs(0, 1, 6, 11);
		setPegs(1, 1, 12, 20);
		setPegs(2, 1, 21, 26);
		setPegs(1, 2, 26, 33);
		pane.add(panel);
	}
}
