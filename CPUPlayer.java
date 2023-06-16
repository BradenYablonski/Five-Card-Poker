/********************
 * CLASS:   CPUPlayer
 * 
 * AUTHOR:  Braden Yablonski
 * 
 * REMAKRS: This is an abtract class that is used to create the CPU players for the poker game.
 ********************/
public abstract class CPUPlayer
{
    //Instance Variables
    private Hand cpuHand;//Points to the CPU hand.

    //Constructor

    //Basic constructor that just sets cpuHand to null.
    public CPUPlayer()
    {
        cpuHand = new Hand();
    }//end constructor

    //Instance methods.

    public abstract void chooseDiscard();//This abstract method is used to select which cards to discard.
    public abstract String cpuName();//Returns the name of the CPU currently being used.

    //This is a getter method that is used to get the Handable of the CPU player.
    public Hand getHand()
    {
        return cpuHand;
    }//end getHand
}//end class CPUPlayer
