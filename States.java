/**

States class used for constructing objects for each of the 50 states
and returning the contents of these objects.

Author: Steven Asten
Date: 12/7/2016
Class: IST 311


*/

public class States
{
   private String name; //name of state
   private String capital; //capital of state
   private String pathToImage; //relative path to image of state
   // stores whether the guess for each state was correct for purposes
   // of the results screen.
   private String correctOrNo; 
   
   /**
   
   Constructor with no contents, used to get access to the states class.
   
   */
   public States()
   {
   }
   
   /**
   
   Constructor used for creating objects for each of the 50 states.
   
   @param name the name of the state
   @param capital the name of the state's capital
   @param pathToImage the path to the image of the state
   @param correctOrNo whether the guess was correct (check) or incorrect (x)
   
   */
   public States(String name, String capital, String pathToImage, String correctOrNo)
   {
      this.name=name;
      this.capital=capital;
      this.pathToImage=pathToImage;
      this.correctOrNo=correctOrNo;
   }
   
   /**
   
   Returns the name of the capital.
   
   @return the name of the capital (variable capital).
   
   */
   public String getStateCapital()
   {
      return capital;
   }
   
   /**
   
   Returns the name of the state.
   
   @return the name of the state (variable name).
   
   */
   public String getStateName()
   {
      return name;
   }

   /**
   
   Returns the value of pathToImage which contains a path to the image of the state.
   
   @return the value of pathToImage, the path to the image of the state.
   
   */
   public String getImagePath()
   {
      return pathToImage;
   }
   
   /**
   
    Returns the value of correctOrNo variable, either an x or a check mark.
    
    @return the value of correctOrNo, either check or x.
   
   */
   public String getCorrectOrNo()
   {
      return correctOrNo;
   }
   
   /**
   
    Changes correctOrNo variable to a check mark using unicode.
    This indicates that the answer given for capital/state/both was correct.
   
   */
   public void setCorrectOrNo()
   {
      correctOrNo="\u2713";
   }
	        	   	   	
}