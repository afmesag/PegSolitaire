package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Game;
import entities.NButton;

public class View extends JFrame implements Serializable {

	private static final long serialVersionUID = -2579501939935352912L;

	private JPanel panel = new JPanel();
	private GridBagLayout holes;
	private NButton[] pegButtons;
	private NButton[] holeButtons;
	private GridBagConstraints c;

	/**
	 * Constructor for the view class
	 */
	public View() {
		super("Pegs Solitaire beta - Software Maintenance");
		setResizable(false);
		holes = new GridBagLayout();
		pegButtons = new NButton[32];
		holeButtons = new NButton[33];
		c = new GridBagConstraints();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addComponentsToPane(getContentPane());
		pack();
		setVisible(true);
	}

	/**
	 * Sets the locations of a component in a GridBagLayout
	 * 
	 * @param gridx
	 *            an integer meaning the number of column
	 * @param gridy
	 *            an integer meaning the number of row
	 */
	public void setGridColRow(int gridx, int gridy) {
		c.gridx = gridx;
		c.gridy = gridy;
	}

	/**
	 * Creates and sets a NButton, locates it's position on a given layout and
	 * finally adds it to the panel.
	 * 
	 * @param gridx
	 *            an integer meaning the number of column
	 * @param gridy
	 *            an integer meaning the number of row
	 */

	public void pegSetting(int gridx, int gridy, int button) {
		pegButtons[button] = new NButton(new JButton(),gridx,gridy);
		pegButtons[button].addActionListener(e -> getLocation(gridx, gridy));
		setGridColRow(gridx, gridy);
		panel.add(pegButtons[button], c);
	}

	public void holeSetting(int gridx, int gridy, int button) {
		holeButtons[button] = new NButton(new JButton(),gridx,gridy);
		holeButtons[button].setBackground(Color.GRAY);
		holeButtons[button].addActionListener(e -> System.out.println("I'm a hole in:" + gridy + " " + gridx));

		setGridColRow(gridx, gridy);
		panel.add(holeButtons[button], c);
	}

	public int[] getLocation(NButton buttonClicked) {
		return new int[] { buttonClicked.getX(), buttonClicked.getY() };
	}
	
	public int[] getLocation(int gridx, int gridy) {
		return new int[] { gridx, gridy };
	}
	
	/**
	 * Sets the default (Figure 1) configuration for Pegs solitaire game
	 */
	public void defaultConfig() {
		int countPegs = 0;
		int countHoles = 0;
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7; y++) {
				if ((x < 2 || x > 4) && (y < 2 || y > 4))
					continue;
				if (!(x == 3 && y == 3)) {
					pegSetting(y, x, countPegs);
					countPegs++;
				} else {
					holeSetting(y, x, countHoles);
					countHoles++;
				}
			}
		}
	}
	
	/**
     * Returns the size of the pegButtons[] array.
     * 
     * @return the size of the pegButtons[] array.
     */
	public int getNumberOfPegButtons() {
		return pegButtons.length;
	}
	
	/**
     * Returns the size of the holeButtons[] array.
     * 
     * @return the size of the holeButtons[] array.
     */
	public int getNumberOfHoleButtons() {
		return holeButtons.length;
	}
	
	/**
	 * Returns a NButton representing a hole in the board
	 * @param index and integer representing a position in an array
	 * @return NButton with given index
	 */
	public NButton getHoleButton(int index){
		return holeButtons[index];
	}
	
	/**
	 * Returns a NButton representing a peg in the board
	 * @param index and integer representing a position in an array
	 * @return NButton with given index
	 */
	public NButton getPegButton(int index){
		return pegButtons[index];
	}
	
	/**
	 * Returns an array containing the pegs in the board 
	 * @return NButton array (pegs)
	 */
	public NButton[] getPegButtons(){
		return pegButtons;
	}
	
	/**
	 * Returns an array containing the holes in the board 
	 * @return NButton array (holes)
	 */
	public NButton[] getHoleButtons(){
		return holeButtons;
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
