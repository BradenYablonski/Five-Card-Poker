/*********************
 * CLASS:   DumbCPU
 * 
 * AUTHOR:  Braden Yablonski
 * 
 * REMAKRS: This class is creates the dumb cpu which only makes random choices in a poker game.
 ********************/

public class DumbCPU extends CPUPlayer {
    
    //Constructor

    //Basic constructor that just calls the super class constructor.
    public DumbCPU()
    {
        super();
    }//end constructor

    //Instance Methods

    /******************
     * chooseDiscard
     * 
     * PURPOSE: This method is used to select the cards from the hand to discard.
     * PARAMETERS:
     *      This method does not accept any parameters. It just chooses a random number
     *      of cards to discard and then randomly chooses that number of cards to discard.
     * RETURNS: This method does not return anything.
     *****************/
    public void chooseDiscard()
    {
        Hand hand = getHand();//Get the hand of the CPU.
        int numCards;//The number of cards to discard.
        Cardable card;//Points to the card selected.

        if(null != hand)
        {
            //Choose a random number of cards from 0 to HAND_SIZE
            numCards = (int) (Math.random()*(Handable.HAND_SIZE + 1));

            //Now randomly select the cards to select. Note that is does not matter if the same number gets chosen twice.
            for(int i = 0; i < numCards; i++)
            {
                card = hand.getCard((int) (Math.random()*Handable.HAND_SIZE));

                //Make sure the card is not already selected before swtiching the state.
                if(null != card && !card.getSelected())
                {
                    card.switchSelectedState();
                }//end if
            }//end for
        }//end if
    }//end chooseDiscard

    //This method is just used for returning a String on the name of the CPU being used.
    public String cpuName()
    {
        return "Dumb CPU";
    }//end cpuName
}//end DumbCPU class
