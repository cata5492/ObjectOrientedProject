package studysaurus;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;


public class GameOptionsPage extends Page implements ActionListener {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridLayout layout = new GridLayout(2,2);
	private JButton nextButton;
	private JComboBox<String> defaultSets, customSets;
	private JRadioButton easyLevel, hardLevel;
	private Set selectedSet;
	     
	    public GameOptionsPage(String name) {
	        super(name);
	    }
	    
	    @SuppressWarnings("unchecked")
		@Override
	    public void drawPage(final Container pane) {
	    	
	    	panel.setLayout(layout);
	    	
	    	//Set up sub-panels
	        JPanel defaultSetPanel = new JPanel(new GridLayout(1,1));
	        JPanel customSetPanel = new JPanel(new GridLayout(1,1));
	        JPanel radioButtonPanel = new JPanel(new GridLayout(0,2));
	        JPanel levelPanel = new JPanel(new GridLayout(2,2));
	        panel.add(defaultSetPanel);
	        panel.add(radioButtonPanel);
	        panel.add(customSetPanel);

	         
	        //Set up components preferred size
	        JButton b = new JButton("Just fake button");
	        Dimension buttonSize = b.getPreferredSize();
	        panel.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 5.5),
	                (int)(buttonSize.getHeight() * 6.5) * 2));
	         
	        //TODO: Make it so that only one set can be selected
	        
	        //Add Default Set stuff
	        defaultSetPanel.add(new JLabel("Default Sets"));
	        ArrayList<String> defaultSetList = dc.getSets(false);
	        defaultSets = new JComboBox(defaultSetList.toArray());
	        defaultSets.addActionListener(this);
	        defaultSetPanel.add(defaultSets);
	        
	        //Add Custom Set stuff
	        customSetPanel.add(new JLabel("Custom Sets"));
	        ArrayList<String> customSetList = dc.getSets(true);
	        customSets = new JComboBox(customSetList.toArray());
	        customSets.addActionListener(this);
	        customSetPanel.add(customSets);
	        
	        //Add Level stuff
	        //TODO: Make it so that only one can be selected
	        easyLevel = new JRadioButton("Easy");
	        easyLevel.addActionListener(this);
	        hardLevel = new JRadioButton("Hard");
	        hardLevel.addActionListener(this);
	        levelPanel.add(easyLevel);
	        levelPanel.add(hardLevel);
	        radioButtonPanel.add(new JLabel("Select a Level:"));
	        radioButtonPanel.add(levelPanel);

	        
	        //Add Play Button stuff
	        nextButton = new JButton("Next");
	        nextButton.addActionListener(this);
	        panel.add(nextButton);

	        //Display to the pane
	        pane.add(panel, BorderLayout.CENTER);
	        pane.add(new JSeparator(), BorderLayout.NORTH);
	}
	    
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj == nextButton){
			PlayGamePage playGamePage = new PlayGamePage("Studysaurus");
			createAndShowGUI(playGamePage);
			this.dispose();
		}
		else if (obj == defaultSets){
			String selectedSetName = (String)defaultSets.getSelectedItem();
			selectedSet = dc.selectSet(selectedSetName);
			gameClient.setCurrentSet(selectedSet);
		}
		else if (obj == customSets){
			String selectedSetName = (String)customSets.getSelectedItem();
			Set selectedSet = dc.selectSet(selectedSetName);
			gameClient.setCurrentSet(selectedSet);
		}
		else if (obj == easyLevel){
			gameClient.setDifficulty(8);
		}
		else if (obj == hardLevel){
			gameClient.setDifficulty(4);
		}
	}


}
