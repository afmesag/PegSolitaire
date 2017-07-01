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
	private NButton restart;
	private NButton undo;
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
	 * Creates and sets the restart button's position in GrigBagLayout and adds it to the main panel
	 */
	public void setRestartButton(){
		restart = (new NButton(new JButton("restart"),7,6));
		restart.setText("Restart");
		setGridColRow(7, 6);
		panel.add(restart, c);
	}
	
	/**
	 * Creates and sets the undo button's position in GrigBagLayout and adds it to the main panel
	 */
	public void setUndoButton(){
		undo = (new NButton(new JButton("undo"),7,5));
		undo.setText("Undo");
		setGridColRow(7, 5);
		panel.add(undo, c);
		undo.setEnabled(false);
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

	/**
	 * Creates and sets a NButton, locates it's position on a given layout and
	 * finally adds it to the panel.
	 * 
	 * @param gridx
	 *            an integer meaning the number of column
	 * @param gridy
	 *            an integer meaning the number of row
	 */
	public void holeSetting(int gridx, int gridy, int button) {
		holeButtons.add(new NButton(new JButton(),gridx,gridy));
		holeButtons.get(button).setBackground(Color.GRAY);

		setGridColRow(gridx, gridy);
		panel.add(holeButtons.get(button), c);
	}

	/**
	 * Obtains the location of a given button
	 * @param buttonClicked
	 * @return an array with two coordinates 
	 */
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
	
	/**
	 * Gets the restart button
	 * @return restart NButton 
	 */
	public NButton getRestartButton() {
		return restart;
	}
	
	/**
	 * Gets the undo button
	 * @return undo NButton 
	 */
	public NButton getUndoButton() {
		return undo;
	}
	
	/**
	 * Ask if a given button is a peg
	 * @param A button in the board
	 * @return True if the given button is a peg, false otherwise
	 */
	public Boolean isPeg(NButton button){
		return Arrays.asList(pegButtons).contains(button) ? true : false;
	}
	
	/**
	 * Ask if a given button is a hole
	 * @param A button in the board
	 * @return True if the given button is a peg, false otherwise
	 */
	public Boolean isHole(NButton button){
		return Arrays.asList(holeButtons).contains(button) ? true : false;
	}
	
	/**
	 * Updates a button turning it into a peg according to the logic of the game
	 * @param coord of the button to be updated
	 */
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
	
	/**
	 * Updates a button turning it into a hole according to the logic of the game
	 * @param coord of the button to be updated
	 */
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
		setRestartButton();
		setUndoButton();
		// Figure 1 configuration
		pane.add(panel);
	}

	

}
