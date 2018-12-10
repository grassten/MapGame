/**

GuessBoth class used for the logic behind the Guess Both game mode.

Author: Steven Asten
Date: 12/7/2016
Class: IST 311


*/
import java.awt.event.ActionEvent; // Adding action to components
import java.awt.event.ActionListener; // Adding action to components
import javax.swing.*; // JComponents
import javax.imageio.ImageIO; // Images
import java.io.IOException; // Exception Handling
import java.awt.*; // Container class
import java.util.Collections; // Used to shuffle object array
import java.util.Arrays; // Used in shuffling object array
import java.awt.GridBagLayout; // Used for layout
import java.awt.GridBagConstraints; // Used for layout

public class GuessBoth {

   int roundNumber = 1; // holds the round number
   int scoreCount = 0; // holds the score
   int i = 0; // used as a counter
   
   String stateString, capitalString, imageString; // hold name, capital, path of current round
   
   States stateLines = new States(); // instantiate object of states to get access
   States[] stateObjects; // declare array of States objects
   
   GridBagConstraints constraints = new GridBagConstraints(); // layout manager

   /**
   
    Constructor with no parameters used to instantiate an object of GuessBoth class
   
   */
   public GuessBoth() {
   
   }

   /**
   
    Used to play the Guess Both version of the game.
    
    @param stateImage displays the image of the state
    @param score displays the current score
    @param round displays the round
    @param guessState is where the user will put their guess/answer for the state
    @param guessCapital is where the user will put their guess/answer for the capital
    @param symbolCorrect holds either a check or x mark depending on whether the capital was correct
    @param symbol2Correct holds either a check or x mark depending on whether the state was correct
    @param nextRound can be clicked to advance to the next round of the game
   
   */
   public void playGame(JLabel stateImage, JLabel score, JLabel round, JTextField guessState,JTextField guessCapital, JLabel symbolCorrect, JLabel symbol2Correct, JButton nextRound) 
   {

      // builds the array of objects containing the states by calling buildStateObjects
      buildStateObjects();

      // sets the initial state of round (1)   
      round.setText(String.valueOf(roundNumber));
   
      // gets the data for the first state in the lineup calling methods from States class
      stateString = stateObjects[i].getStateName();
      capitalString = stateObjects[i].getStateCapital();
      imageString = stateObjects[i].getImagePath();
   
      // sets the image of the first state
      ImageIcon stateImg = new ImageIcon(stateObjects[i].getImagePath());
      stateImage.setIcon(stateImg);
   
      // sets the initial score
      score.setText(String.valueOf(scoreCount));
      
      // puts cursor in JTextField
      guessCapital.requestFocus();
   
      // if enter is pressed while the cursor is in the guessState JTextField, this will be executed
      guessState.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
               // disable two JTextFields
               guessState.setEnabled(false);
               guessCapital.setEnabled(false);
            
               // enable nextRound button and give it focus to enable "Enter" press to advance
               nextRound.setEnabled(true);
               nextRound.requestFocus();
            
               // get the guesses out of the JTextFields and store them in strings
               String stateGuess = guessState.getText();
               String capitalGuess = guessCapital.getText();
               
               // if both the state and the capital are correct, increment score by 1 and set correctOrNo string of object to a check mark
               // by calling the setCorrectOrNo() method of states class
               // set the score to the new value
               if (stateGuess.equalsIgnoreCase(stateString) && capitalGuess.equalsIgnoreCase(capitalString))
               {
                  scoreCount++;
                  stateObjects[i].setCorrectOrNo();
                  score.setText(String.valueOf(scoreCount));

               }
                  
               // if the guess for the state is correct, put a check mark next to the JTextField for state guess
               if (stateGuess.equalsIgnoreCase(stateString)) {
                  try{
                     Image check = ImageIO.read(getClass().getResource("Images/check.png"));
                     symbol2Correct.setIcon(new ImageIcon(check));
                  }       
                  catch(IOException ex) {}
               }
               
               // else, put an x mark next to the JTextField for state guess
               else
               {
                  try{
                     Image xMark = ImageIO.read(getClass().getResource("Images/xMark.png"));
                     symbol2Correct.setIcon(new ImageIcon(xMark));
                  }       
                  catch(IOException ex) {}
               }
                  
               // if the guess for the capital is correct, put a check mark next to the JTextField for capital guess
               if (capitalGuess.equalsIgnoreCase(capitalString)) {
                  try{
                     Image check = ImageIO.read(getClass().getResource("Images/check.png"));
                     symbolCorrect.setIcon(new ImageIcon(check));
                  }       
                  catch(IOException ex) {}
               }
               
               // else, put an x mark next to the JTextField for capital guess
               else
               {
                  try{
                     Image xMark = ImageIO.read(getClass().getResource("Images/xMark.png"));
                     symbolCorrect.setIcon(new ImageIcon(xMark));
                  }       
                  catch(IOException ex) {}
                  
                  
               }
            }
         }); // end guessState JTextField action listener
   
      // if enter is pressed while the cursor is in the guessCapital JTextField, this will be executed
      // this is identical to the actionListener for the guessCapital JTextField
      guessCapital.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
               // disable two JTextFields
               guessState.setEnabled(false);
               guessCapital.setEnabled(false);
            
               // enable nextRound button and give it focus to enable "Enter" press to advance
               nextRound.setEnabled(true);
               nextRound.requestFocus();
            
               // get the guesses out of the JTextFields and store them in strings
               String stateGuess = guessState.getText();
               String capitalGuess = guessCapital.getText();
               
               // if both the state and the capital are correct, increment score by 1 and set correctOrNo string of object to a check mark
               // by calling the setCorrectOrNo() method of states class
               // set the score to the new value
               if (stateGuess.equalsIgnoreCase(stateString) && capitalGuess.equalsIgnoreCase(capitalString))
               {
                  scoreCount++;
                  stateObjects[i].setCorrectOrNo();
                  score.setText(String.valueOf(scoreCount));

               }
                  
               // if the guess for the state is correct, put a check mark next to the JTextField for state guess
               if (stateGuess.equalsIgnoreCase(stateString)) {
                  try{
                     Image check = ImageIO.read(getClass().getResource("Images/check.png"));
                     symbol2Correct.setIcon(new ImageIcon(check));
                  }       
                  catch(IOException ex) {}
               }
               
               // else, put an x mark next to the JTextField for state guess
               else
               {
                  try{
                     Image xMark = ImageIO.read(getClass().getResource("Images/xMark.png"));
                     symbol2Correct.setIcon(new ImageIcon(xMark));
                  }       
                  catch(IOException ex) {}
               }
                  
               // if the guess for the capital is correct, put a check mark next to the JTextField for capital guess
               if (capitalGuess.equalsIgnoreCase(capitalString)) {
                  try{
                     Image check = ImageIO.read(getClass().getResource("Images/check.png"));
                     symbolCorrect.setIcon(new ImageIcon(check));
                  }       
                  catch(IOException ex) {}
               }
               
               // else, put an x mark next to the JTextField for capital guess
               else
               {
                  try{
                     Image xMark = ImageIO.read(getClass().getResource("Images/xMark.png"));
                     symbolCorrect.setIcon(new ImageIcon(xMark));
                  }       
                  catch(IOException ex) {}
                  
                  
               }
            }         
         }); // end guessCapital JTextField action listener
         
      // actionListener for the nextRound JButton
      nextRound.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
               // if we are not on the last round, this will be executed
               if (i <= 48){
               
                  // enable capital and state guess fields, put cursor in top field
                  guessCapital.setEnabled(true);
                  guessState.setEnabled(true);
                  guessCapital.requestFocus();
               
                  // increment i counter and roundNumber
                  i++;
                  roundNumber++;
                  
                  // set new round number
                  round.setText(String.valueOf(roundNumber));

                  // set stateString, capitalString, and stateImage to the data of the next state/round
                  stateString = stateObjects[i].getStateName();
                  capitalString = stateObjects[i].getStateCapital();
               
                  ImageIcon stateImg = new ImageIcon(stateObjects[i].getImagePath());
                  stateImage.setIcon(stateImg);
               
                  // empty out guess fields
                  guessState.setText("");
                  guessCapital.setText("");
                                 

                  
                  // remove symbolCorrect with "null" so it doesn't display anything (check or x)
                  symbolCorrect.setIcon(null);
                  symbol2Correct.setIcon(null);
                  
                  // disable nextRound button
                  nextRound.setEnabled(false);
               }
            }  
         }); // end nextRound button actionListener
         
   } // end playGame method

   /**
   
    Builds the array of state objects containing the 50 states and their name, capital, 
    path to the image, and a correctness field which is set to incorrect by default.
   
   */
   private void buildStateObjects()
   {
      stateObjects = new States[50]; //array of state objects
      
      // instantiate object of States class for each state, add to stateObjects array
      stateObjects[0] = new States("Alabama", "Montgomery","Images/Alabama.jpg", "x");
      stateObjects[1] = new States("Alaska", "Juneau","Images/Alaska.jpg", "x");
      stateObjects[2] = new States("Arizona", "Phoenix","Images/Arizona.jpg", "x");
      stateObjects[3] = new States("Arkansas", "Little Rock","Images/Arkansas.jpg", "x");
      stateObjects[4] = new States("California", "Sacramento","Images/California.jpg", "x");
      stateObjects[5] = new States("Colorado", "Denver","Images/Colorado.jpg", "x");
      stateObjects[6] = new States("Connecticut", "Hartford","Images/Connecticut.jpg", "x");
      stateObjects[7] = new States("Delaware", "Dover","Images/Delaware.jpg", "x");
      stateObjects[8] = new States("Florida", "Tallahassee","Images/Florida.jpg", "x");
      stateObjects[9] = new States("Georgia", "Atlanta","Images/Georgia.jpg", "x");
      stateObjects[10] = new States("Hawaii", "Honolulu","Images/Hawaii.jpg", "x");
      stateObjects[11] = new States("Idaho", "Boise","Images/Idaho.jpg", "x");
      stateObjects[12] = new States("Illinois", "Springfield","Images/Illinois.jpg", "x");
      stateObjects[13] = new States("Indiana", "Indianapolis","Images/Indiana.jpg", "x");
      stateObjects[14] = new States("Iowa", "Des Moines","Images/Iowa.jpg", "x");
      stateObjects[15] = new States("Kansas", "Topeka","Images/Kansas.jpg", "x");
      stateObjects[16] = new States("Kentucky", "Frankfort","Images/Kentucky.jpg", "x");
      stateObjects[17] = new States("Louisiana", "Baton Rouge","Images/Louisiana.jpg", "x");
      stateObjects[18] = new States("Maine", "Augusta","Images/Maine.jpg", "x");
      stateObjects[19] = new States("Maryland", "Annapolis","Images/Maryland.jpg", "x");
      stateObjects[20] = new States("Massachusetts", "Boston","Images/Massachusetts.jpg", "x");
      stateObjects[21] = new States("Michigan", "Lansing","Images/Michigan.jpg", "x");
      stateObjects[22] = new States("Minnesota", "St. Paul","Images/Minnesota.jpg", "x");
      stateObjects[23] = new States("Mississippi", "Jackson","Images/Mississippi.jpg", "x");
      stateObjects[24] = new States("Missouri", "Jefferson City","Images/Missouri.jpg", "x");
      stateObjects[25] = new States("Montana", "Helena","Images/Montana.jpg", "x");
      stateObjects[26] = new States("Nebraska", "Lincoln","Images/Nebraska.jpg", "x");
      stateObjects[27] = new States("Nevada", "Carson City","Images/Delaware.jpg", "x");
      stateObjects[28] = new States("New Hampshire", "Concord","Images/New_Hampshire.jpg", "x");
      stateObjects[29] = new States("New Jersey", "Trenton","Images/New_Jersey.jpg", "x");            
      stateObjects[30] = new States("New Mexico", "Santa Fe","Images/New_Mexico.jpg", "x");
      stateObjects[31] = new States("New York", "Albany","Images/New_York.jpg", "x");
      stateObjects[32] = new States("North Carolina", "Raleigh","Images/North_Carolina.jpg", "x");
      stateObjects[33] = new States("North Dakota", "Bismarck","Images/North_Dakota.jpg", "x");
      stateObjects[34] = new States("Ohio", "Columbus","Images/Ohio.jpg", "x");
      stateObjects[35] = new States("Oklahoma", "Oklahoma City","Images/Oklahoma.jpg", "x");
      stateObjects[36] = new States("Oregon", "Salem","Images/Oregon.jpg", "x");
      stateObjects[37] = new States("Pennsylvania", "Harrisburg","Images/Pennsylvania.jpg", "x");
      stateObjects[38] = new States("Rhode Island", "Providence","Images/Rhode_Island.jpg", "x");
      stateObjects[39] = new States("South Carolina", "Columbia","Images/South_Carolina.jpg", "x");
      stateObjects[40] = new States("South Dakota", "Pierre","Images/South_Dakota.jpg", "x");
      stateObjects[41] = new States("Tennessee", "Nashville","Images/Tennessee.jpg", "x");
      stateObjects[42] = new States("Texas", "Austin","Images/Texas.jpg", "x");
      stateObjects[43] = new States("Utah", "Salt Lake City","Images/Utah.jpg", "x");
      stateObjects[44] = new States("Vermont", "Montpelier","Images/Vermont.jpg", "x");
      stateObjects[45] = new States("Virginia", "Richmond","Images/Virginia.jpg", "x");
      stateObjects[46] = new States("Washington", "Olympia","Images/Washington.jpg", "x");
      stateObjects[47] = new States("West Virginia", "Charleston","Images/West_Virginia.jpg", "x");
      stateObjects[48] = new States("Wisconsin", "Madison","Images/Wisconsin.jpg", "x");
      stateObjects[49] = new States("Wyoming", "Cheyenne","Images/Wyoming.jpg", "x");
   
      // shuffle/randomize states with this lovely one line of code.
      Collections.shuffle(Arrays.asList(stateObjects));
      
   } // end buildStateObjects method
   
   /**
   
    Populates the results page at the end of the game. This will show the correct Capital, State, and whether the user got it right or not.
    
    @return allCenterLabels contains all of the J components created in this method and is returned to ProjectGUI
   
   */
   public JPanel populateEndGame()
   {
      // declare and instantiate new JPanel for holding all components within this method
      // set it to gridbaglayout with insets
      JPanel allCenterLabels = new JPanel();
      allCenterLabels.setLayout(new GridBagLayout());
      constraints.insets = new Insets(10, 10, 10, 10);
   
      // three new JPanels, since the results will be in three different rows. left, center, right.
      // layout will be BoxLayout with Y_AXIS parameter, as we learned in class
      JPanel leftLabels = new JPanel();
      leftLabels.setLayout(new BoxLayout(leftLabels,BoxLayout.Y_AXIS));
      JPanel centerLabels = new JPanel();
      centerLabels.setLayout(new BoxLayout(centerLabels,BoxLayout.Y_AXIS));
      JPanel rightLabels = new JPanel();
      rightLabels.setLayout(new BoxLayout(rightLabels,BoxLayout.Y_AXIS));
      
      // declare three new JLabel arrays totaling 50 labels for the 50 states.
      JLabel answerLabelsLeft[] = new JLabel[17];
      JLabel answerLabelsCenter[] = new JLabel[17];
      JLabel answerLabelsRight[] = new JLabel[16];      
      
      // run through the left JLabels, setting text to CorrectOrNo(check or x)  Capital, State
      // if it is not correct, set the JLabel to red text. else, set it to green text.
      for(int k = 0; k<17; k++)
      {
         // instantiate the JLabels
         answerLabelsLeft[k] = new JLabel();
         
         // set the text to check/x   stateCapital, stateName
         answerLabelsLeft[k].setText(stateObjects[k].getCorrectOrNo() + "   " + stateObjects[k].getStateCapital() + ", " + stateObjects[k].getStateName());
         
         // set font
         answerLabelsLeft[k].setFont(new Font("Sans Serif", Font.PLAIN, 15));
         
         // get the value stored in correct or no. if it's an x, make the JLabel red. else, make it green
         String correctOrNo = stateObjects[k].getCorrectOrNo();
         if(correctOrNo.equals("x"))
         {
            answerLabelsLeft[k].setForeground(Color.RED);
         }
         else
         {
            answerLabelsLeft[k].setForeground(new java.awt.Color(40, 173, 3));
         }
         
         leftLabels.add(answerLabelsLeft[k]);
      }
      
      // run through the center JLabels, setting text to CorrectOrNo(check or x)  Capital, State
      // if it is not correct, set the JLabel to red text. else, set it to green text.
      for(int k = 0; k<17; k++)
      {
         answerLabelsCenter[k] = new JLabel();
         answerLabelsCenter[k].setText(stateObjects[k+17].getCorrectOrNo() + "   " + stateObjects[k+17].getStateCapital() + ", " + stateObjects[k+17].getStateName());
         answerLabelsCenter[k].setFont(new Font("Sans Serif", Font.PLAIN, 15));
         
         String correctOrNo = stateObjects[k+17].getCorrectOrNo();
         if(correctOrNo.equals("x"))
         {
            answerLabelsCenter[k].setForeground(Color.RED);
         }
         else
         {
            answerLabelsCenter[k].setForeground(new java.awt.Color(40, 173, 3));
         }
         
         centerLabels.add(answerLabelsCenter[k]);
          
      }
      
      // run through the right JLabels, setting text to CorrectOrNo(check or x)  Capital, State
      // if it is not correct, set the JLabel to red text. else, set it to green text.
      for(int k = 0; k<16; k++)
      {
         answerLabelsRight[k] = new JLabel();
         answerLabelsRight[k].setText(stateObjects[k+34].getCorrectOrNo() + "   " + stateObjects[k+34].getStateCapital() + ", " + stateObjects[k+34].getStateName());
         answerLabelsRight[k].setFont(new Font("Sans Serif", Font.PLAIN, 15));
         
         String correctOrNo = stateObjects[k+34].getCorrectOrNo();
         if(correctOrNo.equals("x"))
         {
            answerLabelsRight[k].setForeground(Color.RED);
         }
         else
         {
            answerLabelsRight[k].setForeground(new java.awt.Color(40, 173, 3));
         }
         
         rightLabels.add(answerLabelsRight[k]);
      }
   
      
      // add the left labels panel to the parent panel
      constraints.gridx = 0;
      constraints.gridy = 0;
      allCenterLabels.add(leftLabels, constraints);
      
      // add a rigid area to add spacing to the screen
      constraints.gridx = 1;
      constraints.gridy = 0;
      allCenterLabels.add(Box.createRigidArea(new Dimension(0,20)), constraints);
   
      // add the center labels panel to the parent panel
      constraints.gridx = 2;
      constraints.gridy = 0;
      allCenterLabels.add(centerLabels, constraints);
      
      // add a rigid area to add spacing to the screen
      constraints.gridx = 3;
      constraints.gridy = 0;
      allCenterLabels.add(Box.createRigidArea(new Dimension(0,20)), constraints);
      
      // add the right labels panel to the parent panel
      constraints.gridx = 4;
      constraints.gridy = 0;
      allCenterLabels.add(rightLabels, constraints);
    
      return allCenterLabels;  
   }	 // end populateEndGame method

}