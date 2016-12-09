/**

ProjectGUI class used for building the gui components for all of the screens within the game.

Author: Steven Asten
Date: 12/7/2016
Class: IST 311

*/

import java.awt.GridBagLayout; // Layout manager
import java.awt.GridBagConstraints; // Layout manager component
import javax.swing.*; // Swing GUI components 
import javax.swing.border.EmptyBorder; // Used for empty space/layout help
import java.awt.*; // Container class
import java.awt.event.*; // ActionListener
import javax.imageio.ImageIO; // Used for importing images
import java.io.IOException; // Exception handling


public class ProjectGUI extends JFrame implements ActionListener {
   
   private Container contentPane;

   JButton b1, b2, b3; // buttons for choosing a game mode on the main screen
   
   private JPanel masterPanel = new JPanel(new BorderLayout()); // overall container panel below contentPane
   GridBagConstraints constraints = new GridBagConstraints(); // used for layout of the main centerPane

   private JLabel stateName; // used to display name of state in first game mode (guessing capital)
   private JLabel capitalName; // used to display the name of the capital in the second game mode (guessing state)
   private JLabel stateImage; // used to display the image of the state in all game modes

   private JTextField guess; // used to take guess for first and second game modes
   private JTextField guessCapital; // used to take capital guess in third game mode
   private JTextField guessState; // used to take state guess in third game mode
   
   private JLabel score; // used for the score counter in all game modes
   private JLabel round; // used for the round counter in all game modes
   private int scoreValue = 0; // used to set the score value
   private int roundNumber = 1; // used to set the round value
   
   private JLabel symbolCorrect; // displays check for correct/x for incorrect
   private JLabel symbol2Correct; // second symbol for both game mode
      
   private JButton exit; // used in the Game Over screen to get back to the main menu
   
   
   private JButton nextRound; // button used to go to the next question in-game
   
      
   private JMenuItem quitItem; // menu item used to quit out of the game to the main menu
   private JMenuItem exitItem; // menu item used to exit the application
   private JMenuItem infoBox; // info on how to play game
   
   // instructions displayed when "Instructions" is pressed in JMenuBar
   private String instructions = "At main menu, select game mode to begin. Based on the given information, enter the answer(s) in the text field, " + 
                "and click next round. At the end of the game, you will be given your results.";
   
   
   // JLabels used for storing score information at end of game
   private JLabel yourScore, yourScoreNumber, outOfScore;
   
   
   // Declared objects of each game type for purposes of the end game screen
   GuessCapital guessCapitalGame;
   GuessState guessStateGame;
   GuessBoth guessBothGame;
   
   // Says which game mode you're in (1, 2, or 3)
   // This will say which of the above objects is used at end game time
   // When populating the end game screen
   private int gameType = 0;

   
   //used to decide what the quitItem menu item does
   //see quitItem's action listener for more information
   private boolean inGame = false; 
	
   /**
   
    Sets the properties of the JFrame, builds a menu bar, and calls buildGUI();
    to add components to the JFrame.
   
   */
   public ProjectGUI()
   {
      buildGUI();
      setTitle("Map Game");
      setSize(1150, 800);
      setLocationRelativeTo(null); //window location defaults to center of screen
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // add a menu bar to the jframe
      JMenuBar jmb = new JMenuBar();
      setJMenuBar(jmb);
      
      // add the top level "file" drop down button to menu bar
      JMenu file = new JMenu("File");
      jmb.add(file);
      
      // add item to the menu bar to quit to main menu
      quitItem = new JMenuItem("Main Menu");
      file.add(quitItem);
      quitItem.addActionListener(this);
      
      // add item to menu bar to display game play instructions
      infoBox = new JMenuItem("Instructions");
      file.add(infoBox);
      infoBox.addActionListener(this);
           
            
      // add item to menu bar to exit the application
      exitItem = new JMenuItem("Exit");
      file.add(exitItem);
      exitItem.addActionListener(this);
      
      setVisible(true);
 
   } // end ProjectGUI method


   /**
   
    Builds GUI of the main menu page
   
   */
   public void buildGUI() 
   {
   
      contentPane = getContentPane();
   
      // reset masterPanel
      masterPanel.removeAll();
      masterPanel.revalidate();
      masterPanel.repaint();
   	
      try {
      
         // get background image
         Image background = ImageIO.read(getClass().getResource("Images/background.png"));
       
         // instantiate JPanel centerContent
         // override paint component to add background image
         JPanel centerContent = 
            new JPanel()
            {
               @Override
               protected void paintComponent(Graphics g)
               {
                  super.paintComponent(g);
                  g.drawImage(background, 0, 0, null);
               }
                  
            };
         
         // set centerContent to GridBagLayout and set constraints
         centerContent.setLayout(new GridBagLayout());
         constraints.insets = new Insets(200, 30, 30, 30);
      
         
         // button for first game mode
         b1 = new JButton("");
         b1.addActionListener(this);
      
         // sets size of b1 and adds image
         b1.setPreferredSize(new Dimension(250, 500));
         Image img = ImageIO.read(getClass().getResource("Images/option1.png"));
         b1.setIcon(new ImageIcon(img));
      
         
         // button for second game mode
         b2 = new JButton("");
         b2.addActionListener(this);
         
         // sets size of b2 and adds image
         b2.setPreferredSize(new Dimension(250, 500));
         Image img2 = ImageIO.read(getClass().getResource("Images/option2.png"));
         b2.setIcon(new ImageIcon(img2));
      
      
         //button for third game mode
         b3 = new JButton("");
         b3.addActionListener(this);
         
         // sets size of b3 and adds image
         b3.setPreferredSize(new Dimension(250, 500));
         Image img3 = ImageIO.read(getClass().getResource("Images/option3.png"));
         b3.setIcon(new ImageIcon(img3));
         
         
         // add b1 to centerContent
         // gridx and gridy control positioning of the contents
         constraints.gridx = 1;
         constraints.gridy = 0;
         centerContent.add(b1, constraints);
         
         // add b2 to centerContent
         constraints.gridx = 2;
         constraints.gridy = 0;
         centerContent.add(b2, constraints);
         
         // add b3 to centerContent
         constraints.gridx = 3;
         constraints.gridy = 0;
         centerContent.add(b3, constraints);
      
         
         // add the centerContent JPanel to masterPanel
         masterPanel.add(centerContent);
      
         // add masterPanel to the contentPane
         contentPane.add(masterPanel);
      
      } 
      catch(IOException ex) {}
      
   } // end buildGUI method

   /**
   
    Builds the Guess Capital game mode GUI.
   
   */
   private void buildGuessCapitalGUI() 
   {  
      roundNumber = 0;
   
      // clear contents of masterPanel
      masterPanel.removeAll();
      masterPanel.revalidate();
      masterPanel.repaint();
   
      // declare and instantiate JPanels for center, west, and east regions
      JPanel guessCapPanelCenter = new JPanel();
      guessCapPanelCenter.setLayout(new GridBagLayout());
      constraints.insets = new Insets(10, 10, 10, 10);
      JPanel guessCapPanelWest = new JPanel();
      guessCapPanelWest.setBorder(new EmptyBorder(50, 50, 50, 50));
      JPanel guessCapPanelEast = new JPanel();
      guessCapPanelEast.setBorder(new EmptyBorder(50, 50, 50, 50));
      
      // JLabel for holding the name of the state
      stateName = new JLabel();
      stateName.setFont(new Font("Sans Serif", Font.PLAIN, 30));
      
      // JLabel for holding image of the state
      stateImage = new JLabel();
   
      // JLabel holding the current score
      score = new JLabel();
      score.setFont(new Font("Sans Serif", Font.PLAIN, 24));
   	
      // JLabel which displays the word "Score:"
      JLabel scoreLabel = new JLabel("Score:");
      scoreLabel.setFont(new Font("Sans Serif", Font.PLAIN, 24));
   	
      // JLabel which displays the word "Capital"
      JLabel guessLabel = new JLabel("Capital:");
      guessLabel.setFont(new Font("Sans Serif", Font.PLAIN, 16));
   
      // JLabel which holds the round number
      round = new JLabel();
      round.setFont(new Font("Sans Serif", Font.PLAIN, 24));
   
      // JLabel which displays "Round:"
      JLabel roundLabel = new JLabel("Round:");
      roundLabel.setFont(new Font("Sans Serif", Font.PLAIN, 24));
   	
      // JTextLabel which takes the user's guess
      guess = new JTextField(10);
      guess.addActionListener(this);
   
      
      // add score components to west panel
      guessCapPanelWest.add(scoreLabel);
      guessCapPanelWest.add(score);
   	
      // add round components to east panel
      guessCapPanelEast.add(roundLabel);
      guessCapPanelEast.add(round);
   	
      
      // add guessLabel to center JPanel
      // constraints gridx and gridy used to set position on screen
      constraints.gridx = 0;
      constraints.gridy = 2;
      guessCapPanelCenter.add(guessLabel, constraints);
   	
      // add guess to center JPanel
      constraints.gridx = 1;
      constraints.gridy = 2;
      guessCapPanelCenter.add(guess, constraints);
      
      // add symbolCorrect 
      symbolCorrect = new JLabel("");
      constraints.gridx = 2;
      constraints.gridy = 2;
      guessCapPanelCenter.add(symbolCorrect, constraints);
      
      // instantiate and add nextRound button to JPanel
      nextRound = new JButton("  Next Round  ");
      constraints.gridx = 1;
      constraints.gridy = 3;
      guessCapPanelCenter.add(nextRound, constraints);
      nextRound.setEnabled(false); //disable button to start
      nextRound.addActionListener(this);
      
      // allow enter click to execute button (found this code online)
      UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
   
      
      // add stateName label to center JPanel
      constraints.gridx = 0;
      constraints.gridy = 0;
      constraints.gridwidth = 6;
      guessCapPanelCenter.add(stateName, constraints);
   	
      // add stateImage to center JPanel
      constraints.gridx = 0;
      constraints.gridy = 1;
      constraints.gridwidth = 6;
      guessCapPanelCenter.add(stateImage, constraints);
   	
   	// add JPanels to masterPanel
      masterPanel.add("East", guessCapPanelEast);
      masterPanel.add("West", guessCapPanelWest);
      masterPanel.add("Center", guessCapPanelCenter);
   
      // add masterPanel to contentPane
      contentPane.add(masterPanel);
      
      // reset gridwidth so that it is 1 if used in future
      constraints.gridwidth = 1;
   
   } // end buildGuessCapitalGUI method


   /**
   
    Builds the Guess State game mode GUI.
   
   */
   private void buildGuessStateGUI() 
   {
      roundNumber = 0;
   
      // clear contents of masterPanel
      masterPanel.removeAll();
      masterPanel.revalidate();
      masterPanel.repaint();
   
      // declare and instantiate JPanels for center, west, and east regions
      JPanel guessStPanelCenter = new JPanel();
      guessStPanelCenter.setLayout(new GridBagLayout());
      constraints.insets = new Insets(10, 10, 10, 10);
      JPanel guessStPanelWest = new JPanel();
      guessStPanelWest.setBorder(new EmptyBorder(50, 50, 50, 50));
      JPanel guessStPanelEast = new JPanel();
      guessStPanelEast.setBorder(new EmptyBorder(50, 50, 50, 50));
   
      // JLabel for holding capital name heading
      capitalName = new JLabel();
      capitalName.setFont(new Font("Sans Serif", Font.PLAIN, 30));
      
      // JLabel for holding "State:" text
      JLabel guessLabel = new JLabel("State:");
      guessLabel.setFont(new Font("Sans Serif", Font.PLAIN, 16));
   	
      // JLabel for holding "Score:" text
      JLabel scoreLabel = new JLabel("Score:");
      scoreLabel.setFont(new Font("Sans Serif", Font.PLAIN, 24));
   	
      // JLabel for holding "Round:" text
      JLabel roundLabel = new JLabel("Round:");
      roundLabel.setFont(new Font("Sans Serif", Font.PLAIN, 24));
   	
      // JLabel for displaying the score
      score = new JLabel();
      score.setFont(new Font("Sans Serif", Font.PLAIN, 24));
   
      // JLabel for displaying round number   
      round = new JLabel();
      round.setFont(new Font("Sans Serif", Font.PLAIN, 24));
   
      // Display image of the state
      stateImage = new JLabel();
   	
      // Field where user types their guess
      guess = new JTextField(10);
      guess.addActionListener(this);
   
      // Add all components to their respective regions/JPanels
      // constraints/gridx/gridy used for positioning in GridBagLayout
      guessStPanelWest.add(scoreLabel);
      guessStPanelWest.add(score);
   	
      constraints.gridx = 0;
      constraints.gridy = 2;
      guessStPanelCenter.add(guessLabel, constraints);
   	
      symbolCorrect = new JLabel("");
      constraints.gridx = 2;
      constraints.gridy = 2;
      guessStPanelCenter.add(symbolCorrect, constraints);
      
      // Instantiate nextRound button and add next round button to the panel
      nextRound = new JButton("  Next Round  ");
      constraints.gridx = 1;
      constraints.gridy = 3;
      guessStPanelCenter.add(nextRound, constraints);
      
      // set default state of nextRound button to disabled
      nextRound.setEnabled(false);
      nextRound.addActionListener(this);
      
      // Used to make ENTER key work for Next Round button
      UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
   
   
      // Add guess textField to panel
      constraints.gridx = 1;
      constraints.gridy = 2;
      guessStPanelCenter.add(guess, constraints);
   	
      // add capitalName to panel
      constraints.gridx = 0;
      constraints.gridy = 0;
      constraints.gridwidth = 6;
      guessStPanelCenter.add(capitalName, constraints);
   	
      // Add roundLabel and round to panel
      guessStPanelEast.add(roundLabel);
      guessStPanelEast.add(round);
   	
      // add image to the panel
      constraints.gridx = 0;
      constraints.gridy = 1;
      constraints.gridwidth = 6;
      guessStPanelCenter.add(stateImage, constraints);
   	
   
      // Add JPanels to masterPanel and add masterPanel to contentPane
      masterPanel.add("West", guessStPanelWest);
      masterPanel.add("East", guessStPanelEast);
      masterPanel.add("Center", guessStPanelCenter);
   
      contentPane.add(masterPanel);
      constraints.gridwidth = 1;
      
   } // end buildGuessStateGUI method

   /**
   
    Builds the Guess Both game mode GUI.
   
   */
   private void buildGuessBothGUI() 
   {   
      roundNumber = 0;
   	
      // clear contents of masterPanel
      masterPanel.removeAll();
      masterPanel.revalidate();
      masterPanel.repaint();
   
      // declare and instantiate JPanels for center, west, and east regions
      JPanel guessBothPanelCenter = new JPanel();
      guessBothPanelCenter.setLayout(new GridBagLayout());
      constraints.insets = new Insets(10, 10, 10, 10);
      JPanel guessBothPanelWest = new JPanel();
      guessBothPanelWest.setBorder(new EmptyBorder(50, 50, 50, 50));
      JPanel guessBothPanelEast = new JPanel();
      guessBothPanelEast.setBorder(new EmptyBorder(50, 50, 50, 50));
   
      // JLabel used to display "Score:"
      JLabel scoreLabel = new JLabel("Score:");
      scoreLabel.setFont(new Font("Sans Serif", Font.PLAIN, 24));
   
      // JLabel used to display the score
      score = new JLabel();
      score.setFont(new Font("Sans Serif", Font.PLAIN, 24));
   
      // JLabel used to display the image of the state
      stateImage = new JLabel();
      
      // JLabel used to display the round
      round = new JLabel();
      round.setFont(new Font("Sans Serif", Font.PLAIN, 24));
   
      // JLabel used to display "Round:"
      JLabel roundLabel = new JLabel("Round:");
      roundLabel.setFont(new Font("Sans Serif", Font.PLAIN, 24));
   
      // JLabel used to display "Capital:"
      JLabel capitalLabel = new JLabel("Capital:");
      capitalLabel.setFont(new Font("Sans Serif", Font.PLAIN, 16));
      
      // JLabel used to display "State:"
      JLabel stateLabel = new JLabel("State:");
      stateLabel.setFont(new Font("Sans Serif", Font.PLAIN, 16));
   
      // Text field user will enter State guess in
      guessState = new JTextField(10);
      guessState.addActionListener(this);
      
      // Text field user will enter Capital guess in
      guessCapital = new JTextField(10);
      guessCapital.addActionListener(this);
   
      // Add all components to their respective JPanels
      guessBothPanelWest.add(scoreLabel);
      guessBothPanelWest.add(score);
      guessBothPanelEast.add(roundLabel);
      guessBothPanelEast.add(round);
   
      // add capitalLabel to the panel
      constraints.gridx = 0;
      constraints.gridy = 2;
      guessBothPanelCenter.add(capitalLabel,constraints);
   	
      // add guessCapital to the panel
      constraints.gridx = 1;
      constraints.gridy = 2;
      guessBothPanelCenter.add(guessCapital,constraints);
   	
      // add symbolCorrect to the panel
      symbolCorrect = new JLabel("");
      constraints.gridx = 2;
      constraints.gridy = 2;
      guessBothPanelCenter.add(symbolCorrect, constraints);
      
      // add symbol2Correct to the panel
      symbol2Correct = new JLabel("");
      constraints.gridx = 2;
      constraints.gridy = 3;
      guessBothPanelCenter.add(symbol2Correct, constraints);
      
      // instantiate and add nextRound button to the panel
      nextRound = new JButton("  Next Round  ");
      constraints.gridx = 1;
      constraints.gridy = 4;
      guessBothPanelCenter.add(nextRound, constraints);
      
      // set initial state of nextRound button to disabled
      nextRound.setEnabled(false);
      nextRound.addActionListener(this);
      
      // Make ENTER key work for executing a button
      UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
     
      // add stateLabel to the panel
      constraints.gridx = 0;
      constraints.gridy = 3;
      guessBothPanelCenter.add(stateLabel,constraints);
   	
      // add guessState to the panel
      constraints.gridx = 1;
      constraints.gridy = 3;
      guessBothPanelCenter.add(guessState,constraints);
   
      // add stateImage to the panel
      constraints.gridx = 0;
      constraints.gridy = 1;
      constraints.gridwidth = 6;
      guessBothPanelCenter.add(stateImage, constraints);
   
      // Add JPanels to masterPanel
      masterPanel.add("East", guessBothPanelEast);
      masterPanel.add("West", guessBothPanelWest);
      masterPanel.add("Center", guessBothPanelCenter);
      
      // Add masterPanel to content pane
      contentPane.add(masterPanel);
      constraints.gridwidth = 1;
      
   } // end buildGuessBothGUI method
   
   /**
   
    Builds the results page at the end of the game.
    
    @param scoreCount the score that the player got (number correct)
   
   */
   public void endGameGUI(int scoreCount) 
   {
      // clear contents of masterPanel
      masterPanel.removeAll();
      masterPanel.revalidate();
      masterPanel.repaint();
   
      // JPanel for holding content of endGameGUI screen
      JPanel endGameContent = new JPanel();
      endGameContent.setLayout(new GridBagLayout());
      
      // Holds the "Your score: $yourScoreNumber out of 50." line
      JPanel northLabels = new JPanel();
      
      // JLabels for displaying "Your score: $yourScoreNumber out of 50."
      yourScore = new JLabel("Your score:");
      yourScore.setFont(new Font("Sans Serif", Font.PLAIN, 24));
      
      yourScoreNumber = new JLabel(String.valueOf(scoreValue));
      yourScoreNumber.setFont(new Font("Sans Serif", Font.PLAIN, 24));

      outOfScore = new JLabel("out of 50."); 
      outOfScore.setFont(new Font("Sans Serif", Font.PLAIN, 24));
     
            
      // JButton which returns you to main menu
      exit = new JButton("Main Menu");
      
      // Holds 50 labels which show the results to the game, correct and incorrect
      JPanel allCenterLabels = new JPanel();
      
      // if gameType == 1, populateEndGame method of GuessCapital object called
      // which populates the labels with the correct answers and correct/incorrect
      if ( gameType == 1 )
         {
            allCenterLabels = guessCapitalGame.populateEndGame();
         }
      // if gameType == 2, populateEndGame method of GuessState object called
      // which populates the labels with the correct answers and correct/incorrect
      else if ( gameType == 2 )
         {
            allCenterLabels = guessStateGame.populateEndGame();
         }
      // else, populateEndGame method of GuessBoth object called
      // which populates the labels with the correct answers and correct/incorrect
      else
      {
         allCenterLabels = guessBothGame.populateEndGame();
      }

      // Add labels to JPanel
      northLabels.add(yourScore);
      northLabels.add(yourScoreNumber);
      northLabels.add(outOfScore);
 
   	// add exit button to screen
      constraints.gridx = 1;
      constraints.gridy = 3;
      endGameContent.add(exit,constraints);
      
      // add north labels panel to screen
      constraints.gridx = 1;
      constraints.gridy = 1;
      endGameContent.add(northLabels, constraints);
      
      // add centerLabels panel to screen
      constraints.gridx = 1;
      constraints.gridy = 2;
      endGameContent.add(allCenterLabels, constraints);
      

      // add JPanel with content to masterPanel, and that to contentPane
      masterPanel.add(endGameContent);
      contentPane.add(masterPanel);
      
      
      exit.addActionListener(this);
      
      //reset gridwidth to 1 for future use of GridBagConstraints
      constraints.gridwidth = 1;
         
   } // end endGameGUI method
   
   /**
   
    actionListener method for all active components
    
    */
   public void actionPerformed(ActionEvent e)
   {
      // Left-most button on main menu screen
      // clicking this button will execute the GuessCapital game
      if (e.getSource() == b1)
      {
         inGame = true;
         gameType = 1;
      
         guessCapitalGame = new GuessCapital();
         buildGuessCapitalGUI();
         guessCapitalGame.playGame(stateName, stateImage, score, round, guess, symbolCorrect, nextRound);
      }
      
      // Middle button on main menu screen
      // clicking this button will execute the GuessState game
      if (e.getSource() == b2)
      {
         inGame = true;
         gameType = 2;
      
         guessStateGame = new GuessState();
         buildGuessStateGUI();
         guessStateGame.playGame(capitalName, stateImage, score, round, guess, symbolCorrect, nextRound);
      }
      
      // Right-most button on main menu screen
      // clicking this button will execute the GuessBoth game
      if (e.getSource() == b3)
      {
         inGame = true;
         gameType = 3;
         
         guessBothGame = new GuessBoth();
         buildGuessBothGUI();
         guessBothGame.playGame(stateImage, score, round, guessState, guessCapital, symbolCorrect, symbol2Correct, nextRound);
      }
      
      // Next Round button in all of the game types
      // clicking this button or clicking enter when it is selected
      // will take you to the next round of the game
      // also checks if it is round 50; if it is, 
      // calls endGameGUI method
      if (e.getSource() == nextRound)
      {         
         roundNumber++;
         if (roundNumber == 50) {
            scoreValue = Integer.parseInt(score.getText());
            endGameGUI(scoreValue);
         }
      }  
         
      // Button located in the GameOver screen
      // Clicking this button returns you to the main menu
      // Sets inGame to false for purpose of the quitItem menu item
      if (e.getSource() == exit)
      {
         inGame = false;
         buildGUI();
      }
      
      // Menu item "Main Menu"
      // Checks inGame Boolean; if true, JOptionPane confirm dialogue pops up
      // if false, doesn't do anything because you're already at the main menu
      if (e.getSource() == quitItem)
      {
         if (inGame == true)
         {
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?",
               "Confirm Quit", JOptionPane.YES_OPTION);
            if (result == JOptionPane.YES_OPTION)
            {
               scoreValue = Integer.parseInt(score.getText());
               endGameGUI(scoreValue);
            }
         }
      }
      
      // Menu item "Exit"
      // Opens JOptionPane "Are you sure you want to exit"
      // If yes, calls System.exit(0); to quit out of the game
      if(e.getSource() == exitItem)
      {
         int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the game?",
            "Confirm Quit", JOptionPane.YES_OPTION);
         if (result == JOptionPane.YES_OPTION)
         {
            System.exit(0);
         }
      }
      
      
      // instructions menu item, displays instructions in an option pane
      if(e.getSource() == infoBox)
      {
        JOptionPane.showMessageDialog(null, "<html><body><p style='width: 200px;'>"+instructions+"</p></body></html>", "Instructions",
        JOptionPane.INFORMATION_MESSAGE);
      
      }
   } // end ActionPerformed
} // end ProjectGUI class
