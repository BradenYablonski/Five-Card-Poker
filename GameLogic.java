/*****************
 * CLASS: GameLogic
 * 
 * AUTHOR: Braden Yablonski
 * 
 * REMARKS: This class is used to implement the logic behind the Poker game.
 ****************/

public class GameLogic implements GameLogicable {
    
    //Instance Variables
    private Handable humanHand;//The human players hand.
    private CPUPlayer cpu;//The cpu player.
    private Deckable deck;//The deck of cards to use.
    private int numGames;//The number of games played.
    private int humanWins;//Number of games won by the human player.
    private int cpuWins;//Number of games won by the cpu player.
    private int gameStage;//Keeps track of what stage in the game we are in stages 1 to MAX_GAME_STATES

    //Constructor

    /*************** 
     * constructor
     * 
     * PURPOSE: To create an instance of GameLogic.
     * PARAMETERS:
     *      This constructor does not accept any parameters. It just initializes all the fields
     *      and creates some new hands to play the first game with.
     * RETURNS: Implitly returns a pointer to the new instance of GameLogic.
     ***************/
    public GameLogic()
    {
        deck = new Deck();//Create the new deck before creating the hands.
        deck.shuffle();//Remember to shuffle before doing anything.
        humanHand = new Hand(deck, true);
        cpu = new SmartCPU();//TO CHANGE THE CPU TO THE RANDOM ONE PUT new DumbCPU() FOR THE SMART ONE PUT new SmartCPU()
        cpu.getHand().draw(deck, false);
        
        numGames = 1;//We will start at one since the user calling this constructor is assumed to play at least one game.
        gameStage = 1;//Keeps track of which stage of the game we are in it will be an integer from 1 to 6
        humanWins = 0;//No one won any games yet.
        cpuWins = 0;//No one won any games yet.
    }//end constructor

    //Interface methods

    //Getter method that gets and returns the CPU's Handable.
    public Handable getCPUHand()
    {
        return cpu.getHand();
    }//end getCPUHand

    //Getter method that gets and returns the human player's Handable.
    public Handable getHumanHand()
    {
        return humanHand;
    }//end getHumanHand

    /*****************
     * nextState
     * 
     * PURPOSE: Called when we want to move to the next state of the game.
     * PARAMETERS:
     *      An array of Strings in which each String in the array is empty and,
     *      we want to add the messages to the array to display in the GUI. The method
     *      then performs the necessary procudures of that stage of the game and then returns true,
     *      to keep the proceed button enabled.
     * RETURNS: The boolean true to keep the proceed button enabled.
     ***************/
    public boolean nextState(String[] messages)
    {
        if(4 <= messages.length)
        {
            //The first state the game is already set up and so we just put in the messages to display on the GUI.
            if(1 == gameStage)
            {  
                humanHand.showAllCards();//Remember to show all the human cards.

                messages[0] = "Beginning of Game " + numGames;
                messages[1] = "Human, choose which cards to discard";
                messages[2] = "and click proceed to continue.";
                messages[3] = null;

            }//end if
            else if(2 == gameStage)
            {
                //In this stage we need to discard the human players selected cards and then wait for the CPU.
                messages[0] = "Human player has discard cards.";
                messages[1] = cpu.cpuName() + " is thinking...";
                messages[2] = null;
                messages[3] = null;

                //Discard the selected cards from the human and return to deck.
                deck.returnToDeck(humanHand.discard());

                //Also get cpu to choose which cards to discard.
                cpu.chooseDiscard();
            }//end else if
            else if(3 == gameStage)
            {
                //Now discard the cpu cards.
                messages[0] = cpu.cpuName() + " has discarded cards.";
                messages[1] = "Each player will be dealt the same number of cards they discarded.";
                messages[2] = null;
                messages[3] = null;

                deck.returnToDeck(cpu.getHand().discard());
            }//end else if
            else if(4 == gameStage)
            {
                //Now replace all the discarded cards.
                messages[0] = "Each player has been dealt new cards.";
                messages[1] = "Click proceed to see the winner!";

                humanHand.draw(deck, true);
                cpu.getHand().draw(deck, false);
            }//end else if
            else if(5 == gameStage)
            {
                //Evalute both hands and determine the winner.
                messages[0] = cpu.cpuName() + " has: " + cpu.getHand().evaluateHand();
                messages[1] = "Human player has: " + humanHand.evaluateHand();

                //Compare the two hands.
                if(humanHand.compareTo(cpu.getHand()) > 0)
                {
                    //Human player wins.
                    messages[2] = "Human player wins!!!";
                    humanWins++;
                }//end if
                else if(humanHand.compareTo(cpu.getHand()) == 0)
                {
                    messages[2] = "We have a tie game, no one wins.";
                }//end else if
                else
                {
                    messages[2] = cpu.cpuName() + " wins!!!";
                    cpuWins++;
                }//end else 

                messages[3] = "Human player has won " + humanWins + " games. " + cpu.cpuName() + " has won " + cpuWins + " games.";

                cpu.getHand().showAllCards();//Set the cpu cards face up so we see them.
            }//end else if
            else
            {
                //In this stage we need to return all the cards and draw new cards after shuffling.
                deck.returnToDeck(humanHand.returnCards());
                deck.returnToDeck(cpu.getHand().returnCards());

                //Shuffle deck and draw new cards.
                deck.shuffle();

                //Always draw the cards face down.
                humanHand.draw(deck, false);
                cpu.getHand().draw(deck,false);

                numGames++;//Move to next game.

                messages[0] = "Click on Proceed to play a new game!";
                messages[1] = null;
                messages[2] = null;
                messages[3] = null;
            }//end else 
            
        }//end if

        //Remember to move to the next stage.
        if(MAX_GAME_STATES == gameStage)
        {
            gameStage = 1;
        }//end if
        else
        {
            gameStage++;
        }//end else 

        return true;
    }
}
