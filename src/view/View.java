package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import entities.NButton;

public class View extends JFrame implements Serializable {

	private static final long serialVersionUID = -2579501939935352912L;

	private JPanel panel = new JPanel();
	private GridBagLayout holes;
	private ArrayList<NButton> pegButtons;
	private ArrayList<NButton> holeButtons;
	private GridBagConstraints c;

	/**
	 * Constructor for the view class
	 */
	public View() {
		super("Pegs Solitaire beta - Software Maintenance");
		setResizable(false);
		holes = new GridBagLayout();
		pegButtons = new ArrayList<>();
		holeButtons = new ArrayList<>();
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
		pegButtons.add(new NButton(new JButton(),gridx,gridy));
		setGridColRow(gridx, gridy);
		panel.add(pegButtons.get(button), c);
	}

	public void holeSetting(int gridx, int gridy, int button) {
		holeButtons.add(new NButton(new JButton(),gridx,gridy));
		holeButtons.get(button).setBackground(Color.GRAY);

		setGridColRow(gridx, gridy);
		panel.add(holeButtons.get(button), c);
	}

	public int[] getLocation(NButton buttonClicked) {
		return new int[] { buttonClicked.getGridy(), buttonClicked.getGridx() };
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
		return pegButtons.size();
	}
	
	/**
     * Returns the size of the holeButtons[] array.
     * 
     * @return the size of the holeButtons[] array.
     */
	public int getNumberOfHoleButtons() {
		return holeButtons.size();
	}
	
	/**
	 * Returns a NButton representing a hole in the board
	 * @param index and integer representing a position in an array
	 * @return NButton with given index
	 */
	public NButton getHoleButton(int index){
		return holeButtons.get(index);
	}
	
	/**
	 * Returns a NButton representing a peg in the board
	 * @param index and integer representing a position in an array
	 * @return NButton with given index
	 */
	public NButton getPegButton(int index){
		return pegButtons.get(index);
	}
	
	/**
	 * Returns an array containing the pegs in the board 
	 * @return NButton array (pegs)
	 */
	public List<NButton> getPegButtons(){
		return pegButtons;
	}
	
	/**
	 * Returns an array containing the holes in the board 
	 * @return NButton array (holes)
	 */
	public List<NButton> getHoleButtons(){
		return holeButtons;
	}
	
	public Boolean isPeg(NButton button){
		return Arrays.asList(pegButtons).contains(button) ? true : false;
	}
	
	public Boolean isHole(NButton button){
		return Arrays.asList(holeButtons).contains(button) ? true : false;
	}
	
	public void updatePeg(int[] coord){
		int index=-1;
		for(NButton hole : holeButtons){
			index++;
			if((hole.getGridx() == coord[1]) && (hole.getGridy() == coord[0])){
				hole.setBackground(null);
				pegButtons.add(hole);
				holeButtons.remove(index);
				return;

			}
		}
	}
	public void updateHole(int[] coord){
		int index=-1;
		for(NButton peg : pegButtons){
			index++;
			if((peg.getGridx() == coord[1]) && (peg.getGridy() == coord[0])){
				peg.setBackground(Color.GRAY);
				holeButtons.add(peg);
				pegButtons.remove(index);
				return;
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
