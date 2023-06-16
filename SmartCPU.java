/*********************
 * CLASS:   SmartCPU
 * 
 * AUTHOR:  Braden Yablonski
 * 
 * REMAKRS: This class is creates the smart cpu which makes smarter choices during a Poker game.
 ********************/

public class SmartCPU extends CPUPlayer {
    
    //Constructor

    //Basic constructor that just calls the super class constructor.
    public SmartCPU()
    {
        super();
    }//end constructor

    //Instance Methods

    /******************
     * chooseDiscard
     * 
     * PURPOSE: This method is used to select the cards from the hand to discard.
     * PARAMETERS:
     *      This method does not accept any parameters. It just just calls the bestDispose method from 
     *      the Hand class to make a smart choice on which cards to dispose of if any. Note that the 
     *      bestDispose method will select the cards to dispose of.
     * RETURNS: This method does not return anything.
     *****************/
    public void chooseDiscard()
    {
        Handable hand = getHand();//Get the hand of the CPU.

        if(null != hand)
        {
            //In this case we have a method in the Hand class to make a smarter choice than the dumb CPU.
            if(hand instanceof Hand)
            {
                ((Hand) hand).bestDispose();
            }//end if
        }//end if
    }//end chooseDiscard

    //This method is just used for returning a String on the name of the CPU being used.
    public String cpuName()
    {
        return "Smart CPU";
    }//end cpuName
}//end DumbCPU class
