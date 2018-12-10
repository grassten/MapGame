/**

GuessState class used for the logic behind the Guess State game mode.

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


public class GuessState {

   int roundNumber = 1; 
   int scoreCount = 0; 
   
   GridBagConstraints constraints = new GridBagConstraints();

   
   int i = 0;
   
   // Strings used to hold capital, state name, and path to image
   String stateString, capitalString, imageString;
   
   States stateLines = new States(); // instantiate object of states to get access
   States[] stateObjects; // declare array of States objects


   /**
   
    Constructor with no parameters used to instantiate an object of GuessState class
   
   */
   public GuessState() {
   
   }

   /**
   
    Logic for playing the Guess State game.
    
    @param capitalName displays the capital to the screen
    @param stateImage displays the image to the screen
    @param score displays the score to the screen
    @param round displays the current round to the screen
    @param guess field where user enters their guess/answer
    @param symbolCorrect holds either a check mark or an x mark image to show correctness
    @param nextRound displays the nextRound button to the screen
   
   */
   public void playGame(JLabel capitalName, JLabel stateImage, JLabel score, JLabel round, JTextField guess, JLabel symbolCorrect, JButton nextRound) {
   
      // builds the array of objects containing the states by calling buildStateObjects
      buildStateObjects();
         
      // sets the initial state of round (1)
      round.setText(String.valueOf(roundNumber));
   
      // gets the data for the first state in the lineup calling methods from States class
      stateString = stateObjects[i].getStateName();
      capitalString = stateObjects[i].getStateCapital();
      imageString = stateObjects[i].getImagePath();
      
      // sets the capital label to the name of the capital. this is what is displayed above the image on the screen
      capitalName.setText(capitalString);
   
      // sets the image of the first state
      ImageIcon stateImg = new ImageIcon(stateObjects[i].getImagePath());
      stateImage.setIcon(stateImg);
   
      // sets the initial score
      score.setText(String.valueOf(scoreCount));
      
      // puts cursor in JTextField
      guess.requestFocus();
   
      // action listener for guess JTextField (when enter is pressed)
      guess.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                  
               // enable nextround JButton and disable guess JTextField. set focus on nextRound button so that enter can advance
               nextRound.setEnabled(true);
               guess.setEnabled(false);
               nextRound.requestFocus();
               
               // get the text out of the guess JTextField
               String stateGuess = guess.getText();
               
               // check the guessed state against the string stored in stateString (correct answer)
               // if the guess is correct, increment scoreCount, setCorrectOrNo() to change from xmark to check mark in the array
               // update the score JLabel 
               // put a check mark next to the JTextField to indicate correctness
               if (stateGuess.equalsIgnoreCase(stateString)) {
                  stateObjects[i].setCorrectOrNo();
                  scoreCount++;
                  score.setText(String.valueOf(scoreCount));
                  try{
                     Image check = ImageIO.read(getClass().getResource("Images/check.png"));
                     symbolCorrect.setIcon(new ImageIcon(check));
                  }       
                  catch(IOException ex) {}
               }
               
               // if the guess is not correct, simply add an xmark to indicate incorrectness
               else
               {
                  try{
                     Image xMark = ImageIO.read(getClass().getResource("Images/xMark.png"));
                     symbolCorrect.setIcon(new ImageIcon(xMark));
                  }       
                  catch(IOException ex) {}
               }
            
            }
         
         });
      
      // sets the actionListener for the nextRound button
      nextRound.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
               // if we are not on the last round, this will be executed
               // enable guess JTextField and put cursor there.
               // increment i counter and roundNumber
               // set stateString, capitalString, and stateImage to the data of the next state/round
               // remove symbolCorrect with "null" so it doesn't display anything (check or x)
               // disable nextRound button
               if (i <= 48){
               
                  guess.setEnabled(true);
                  guess.requestFocus();
                  
                  i++;
               
                  roundNumber++;
                  round.setText(String.valueOf(roundNumber));
               
                  stateString = stateObjects[i].getStateName();
                  capitalString = stateObjects[i].getStateCapital();
               
                  capitalName.setText(capitalString);
               
                  ImageIcon stateImg = new ImageIcon(stateObjects[i].getImagePath());
                  stateImage.setIcon(stateImg);
               
                  guess.setText("");
                  symbolCorrect.setIcon(null);
               
                  nextRound.setEnabled(false);
               
               }
            }  
         });
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
   }

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
   }	 
}