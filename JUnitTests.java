/*******************
 * CLASS:	JUnitTests
 * 
 * AUTHOR:  Braden Yablonski
 * 
 * REMAKRS: This class is used to perform JUnit tests on the compareTo method for a Handable object.
 *******************/

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class JUnitTests {

	/********************
	 * test1
	 * 
	 * PURPOSE: Comparing a straight to a three of kind.
	 * PARAMETERS:
	 * 		This method does not accept any parameters. This method is a JUnit test method to test
	 * 		the compareTo method for a Handable.
	 * RETURNS: Does not return anything.
	 ********************/
    @Test
    public void test1() 
	{
		Cardable[] cards1 = {new Card(2, Cardable.Suit.CLUB), new Card(2, Cardable.Suit.HEART), new Card(3, Cardable.Suit.CLUB), new Card(4, Cardable.Suit.CLUB), new Card(2, Cardable.Suit.DIAMOND)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(3, Cardable.Suit.HEART), new Card(4, Cardable.Suit.HEART), new Card(5, Cardable.Suit.HEART), new Card(6, Cardable.Suit.HEART), new Card(7, Cardable.Suit.DIAMOND)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) < 0, "Straight beats Three of a kind.");
    }//end test1

	/********************
	 * test2
	 * 
	 * PURPOSE: Comparing low straight to the next higher straight to ensure that ACE is counted as the low card in a low straight.
	 * PARAMETERS:
	 * 		This method does not accept any parameters. This method is a JUnit test method to test
	 * 		the compareTo method for a Handable.
	 * RETURNS: Does not return anything.
	 ********************/
	@Test
    public void test2() 
	{
		Cardable[] cards1 = {new Card(3, Cardable.Suit.CLUB), new Card(4, Cardable.Suit.HEART), new Card(14, Cardable.Suit.CLUB), new Card(5, Cardable.Suit.CLUB), new Card(2, Cardable.Suit.DIAMOND)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(3, Cardable.Suit.HEART), new Card(4, Cardable.Suit.HEART), new Card(5, Cardable.Suit.HEART), new Card(6, Cardable.Suit.HEART), new Card(2, Cardable.Suit.DIAMOND)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) < 0, "Any other straight beats a low straight.");
    }//end test2

	/********************
	 * test3
	 * 
	 * PURPOSE: Making sure that a flush beats a regular non-flush straight.
	 * PARAMETERS:
	 * 		This method does not accept any parameters. This method is a JUnit test method to test
	 * 		the compareTo method for a Handable.
	 * RETURNS: Does not return anything.
	 ********************/
	@Test
    public void test3() 
	{
		Cardable[] cards1 = {new Card(3, Cardable.Suit.CLUB), new Card(4, Cardable.Suit.CLUB), new Card(14, Cardable.Suit.CLUB), new Card(5, Cardable.Suit.CLUB), new Card(2, Cardable.Suit.CLUB)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(3, Cardable.Suit.HEART), new Card(4, Cardable.Suit.HEART), new Card(5, Cardable.Suit.HEART), new Card(6, Cardable.Suit.HEART), new Card(2, Cardable.Suit.DIAMOND)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) > 0, "Flush beats a Straight.");
    }//end test3

	/********************
	 * test4
	 * 
	 * PURPOSE: Making sure that the suits of the cards do not play any role in the value of a hand.
	 * PARAMETERS:
	 * 		This method does not accept any parameters. This method is a JUnit test method to test
	 * 		the compareTo method for a Handable.
	 * RETURNS: Does not return anything.
	 ********************/
	@Test
    public void test4() 
	{
		Cardable[] cards1 = {new Card(10, Cardable.Suit.CLUB), new Card(13, Cardable.Suit.CLUB), new Card(14, Cardable.Suit.CLUB), new Card(12, Cardable.Suit.CLUB), new Card(11, Cardable.Suit.CLUB)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(11, Cardable.Suit.HEART), new Card(14, Cardable.Suit.HEART), new Card(12, Cardable.Suit.HEART), new Card(13, Cardable.Suit.HEART), new Card(10, Cardable.Suit.HEART)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) == 0, "Two royal straight flushes with different suits. Suits do not have different values.");
    }//end test4

	/********************
	 * test5
	 * 
	 * PURPOSE: Making sure that full houses are comnpared by the value of the three of a kind card.
	 * PARAMETERS:
	 * 		This method does not accept any parameters. This method is a JUnit test method to test
	 * 		the compareTo method for a Handable.
	 * RETURNS: Does not return anything.
	 ********************/
	@Test
    public void test5() 
	{
		Cardable[] cards1 = {new Card(10, Cardable.Suit.CLUB), new Card(10, Cardable.Suit.HEART), new Card(3, Cardable.Suit.SPADE), new Card(3, Cardable.Suit.CLUB), new Card(3, Cardable.Suit.DIAMOND)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(5, Cardable.Suit.HEART), new Card(5, Cardable.Suit.SPADE), new Card(7, Cardable.Suit.HEART), new Card(7, Cardable.Suit.HEART), new Card(5, Cardable.Suit.DIAMOND)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) < 0, "Full houses are compared with the three of a kind.");
    }//end test5

	/********************
	 * test6
	 * 
	 * PURPOSE: Testing comparing one pair hands we look at the next highest value in the hand.
	 * PARAMETERS:
	 * 		This method does not accept any parameters. This method is a JUnit test method to test
	 * 		the compareTo method for a Handable.
	 * RETURNS: Does not return anything.
	 ********************/
	@Test
    public void test6() 
	{
		Cardable[] cards1 = {new Card(10, Cardable.Suit.DIAMOND), new Card(4, Cardable.Suit.CLUB), new Card(8, Cardable.Suit.SPADE), new Card(10, Cardable.Suit.CLUB), new Card(3, Cardable.Suit.DIAMOND)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(2, Cardable.Suit.HEART), new Card(10, Cardable.Suit.SPADE), new Card(7, Cardable.Suit.HEART), new Card(10, Cardable.Suit.HEART), new Card(5, Cardable.Suit.DIAMOND)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) > 0, "Highest odd card determines the winner.");
    }//end test6

	/********************
	 * test7
	 * 
	 * PURPOSE: Testing comparing two pair hands we look at the odd card.
	 * PARAMETERS:
	 * 		This method does not accept any parameters. This method is a JUnit test method to test
	 * 		the compareTo method for a Handable.
	 * RETURNS: Does not return anything.
	 ********************/
	@Test
    public void test7() 
	{
		Cardable[] cards1 = {new Card(10, Cardable.Suit.DIAMOND), new Card(4, Cardable.Suit.CLUB), new Card(4, Cardable.Suit.SPADE), new Card(10, Cardable.Suit.CLUB), new Card(3, Cardable.Suit.DIAMOND)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(4, Cardable.Suit.CLUB), new Card(10, Cardable.Suit.SPADE), new Card(4, Cardable.Suit.HEART), new Card(10, Cardable.Suit.HEART), new Card(5, Cardable.Suit.DIAMOND)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) < 0, "Highest odd card determines the winner.");
    }//end test7

	/********************
	 * test8
	 * 
	 * PURPOSE: Hands that do not have anything but have almost all the same cards except for the lowest one.
	 * PARAMETERS:
	 * 		This method does not accept any parameters. This method is a JUnit test method to test
	 * 		the compareTo method for a Handable.
	 * RETURNS: Does not return anything.
	 ********************/
	@Test
    public void test8() 
	{
		Cardable[] cards1 = {new Card(4, Cardable.Suit.DIAMOND), new Card(3, Cardable.Suit.CLUB), new Card(11, Cardable.Suit.SPADE), new Card(14, Cardable.Suit.CLUB), new Card(13, Cardable.Suit.HEART)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(4, Cardable.Suit.CLUB), new Card(11, Cardable.Suit.SPADE), new Card(2, Cardable.Suit.DIAMOND), new Card(13, Cardable.Suit.HEART), new Card(14, Cardable.Suit.HEART)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) > 0, "Compare the high cards of the deck.");
    }//end test8

	/********************
	 * test9
	 * 
	 * PURPOSE: Comparing four of a kind to a full house.
	 * PARAMETERS:
	 * 		This method does not accept any parameters. This method is a JUnit test method to test
	 * 		the compareTo method for a Handable.
	 * RETURNS: Does not return anything.
	 ********************/
	@Test
    public void test9() 
	{
		Cardable[] cards1 = {new Card(4, Cardable.Suit.DIAMOND), new Card(4, Cardable.Suit.CLUB), new Card(11, Cardable.Suit.SPADE), new Card(4, Cardable.Suit.CLUB), new Card(4, Cardable.Suit.HEART)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(14, Cardable.Suit.CLUB), new Card(13, Cardable.Suit.SPADE), new Card(14, Cardable.Suit.DIAMOND), new Card(14, Cardable.Suit.HEART), new Card(13, Cardable.Suit.HEART)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) > 0, "Four of a kind beats a full house.");
    }//end test9

	/********************
	 * test10
	 * 
	 * PURPOSE: Full House beats a flush even if the flush has higher value cards.
	 * PARAMETERS:
	 * 		This method does not accept any parameters. This method is a JUnit test method to test
	 * 		the compareTo method for a Handable.
	 * RETURNS: Does not return anything.
	 ********************/
	@Test
    public void test10() 
	{
		Cardable[] cards1 = {new Card(7, Cardable.Suit.DIAMOND), new Card(6, Cardable.Suit.CLUB), new Card(6, Cardable.Suit.SPADE), new Card(7, Cardable.Suit.CLUB), new Card(7, Cardable.Suit.HEART)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(14, Cardable.Suit.SPADE), new Card(13, Cardable.Suit.SPADE), new Card(9, Cardable.Suit.SPADE), new Card(11, Cardable.Suit.SPADE), new Card(13, Cardable.Suit.SPADE)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) > 0, "Full House beats a flush.");
    }//end test10

	/********************
	 * test11
	 * 
	 * PURPOSE: Testing three of kind beats two pairs.
	 * PARAMETERS:
	 * 		This method does not accept any parameters. This method is a JUnit test method to test
	 * 		the compareTo method for a Handable.
	 * RETURNS: Does not return anything.
	 ********************/
	@Test
    public void test11() 
	{
		Cardable[] cards1 = {new Card(14, Cardable.Suit.DIAMOND), new Card(14, Cardable.Suit.CLUB), new Card(9, Cardable.Suit.SPADE), new Card(3, Cardable.Suit.CLUB), new Card(9, Cardable.Suit.HEART)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(10, Cardable.Suit.CLUB), new Card(7, Cardable.Suit.SPADE), new Card(7, Cardable.Suit.HEART), new Card(7, Cardable.Suit.HEART), new Card(4, Cardable.Suit.HEART)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) < 0, "Three of kind beats two pairs.");
    }//end test11

	/********************
	 * test12
	 * 
	 * PURPOSE: Testing two pairs agaist one pair
	 * PARAMETERS:
	 * 		This method does not accept any parameters. This method is a JUnit test method to test
	 * 		the compareTo method for a Handable.
	 * RETURNS: Does not return anything.
	 ********************/
	@Test
    public void test12() 
	{
		Cardable[] cards1 = {new Card(14, Cardable.Suit.SPADE), new Card(14, Cardable.Suit.SPADE), new Card(3, Cardable.Suit.SPADE), new Card(4, Cardable.Suit.DIAMOND), new Card(5, Cardable.Suit.HEART)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(2, Cardable.Suit.CLUB), new Card(2, Cardable.Suit.SPADE), new Card(5, Cardable.Suit.HEART), new Card(5, Cardable.Suit.CLUB), new Card(14, Cardable.Suit.HEART)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) < 0, "Two pairs is better than one.");
    }//end test12

	/********************
	 * test13
	 * 
	 * PURPOSE: Two hands with the exact same cards are equal as long as either both hands have at least one card with a different suit or both hands have all cards of the same suit.
	 *			Testing three of kind beats two pairs.
	 * PARAMETERS:
	 * 		This method does not accept any parameters. This method is a JUnit test method to test
	 * 		the compareTo method for a Handable.
	 * RETURNS: Does not return anything.
	 ********************/
	@Test
    public void test13() 
	{
		Cardable[] cards1 = {new Card(6, Cardable.Suit.DIAMOND), new Card(2, Cardable.Suit.DIAMOND), new Card(9, Cardable.Suit.DIAMOND), new Card(8, Cardable.Suit.DIAMOND), new Card(10, Cardable.Suit.HEART)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(6, Cardable.Suit.CLUB), new Card(2, Cardable.Suit.CLUB), new Card(9, Cardable.Suit.HEART), new Card(8, Cardable.Suit.CLUB), new Card(10, Cardable.Suit.SPADE)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) == 0, "Both hands have the same cards except for the suits and neither hands are flushes.");
    }//end test13

	/********************
	 * test14
	 * 
	 * PURPOSE: Both hands have the same card ranks and flushes with different suits.
	 * PARAMETERS:
	 * 		This method does not accept any parameters. This method is a JUnit test method to test
	 * 		the compareTo method for a Handable.
	 * RETURNS: Does not return anything.
	 ********************/
	@Test
    public void test14() 
	{
		Cardable[] cards1 = {new Card(6, Cardable.Suit.DIAMOND), new Card(2, Cardable.Suit.DIAMOND), new Card(9, Cardable.Suit.DIAMOND), new Card(8, Cardable.Suit.DIAMOND), new Card(10, Cardable.Suit.DIAMOND)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(6, Cardable.Suit.CLUB), new Card(2, Cardable.Suit.CLUB), new Card(9, Cardable.Suit.CLUB), new Card(8, Cardable.Suit.CLUB), new Card(10, Cardable.Suit.CLUB)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) == 0, "Both hands are flushes with the same card values.");
    }//end test14

	/********************
	 * test15
	 * 
	 * PURPOSE: Four of a kind beats three of a kind.
	 * PARAMETERS:
	 * 		This method does not accept any parameters. This method is a JUnit test method to test
	 * 		the compareTo method for a Handable.
	 * RETURNS: Does not return anything.
	 ********************/
	@Test
    public void test15() 
	{
		Cardable[] cards1 = {new Card(8, Cardable.Suit.DIAMOND), new Card(2, Cardable.Suit.SPADE), new Card(8, Cardable.Suit.HEART), new Card(8, Cardable.Suit.DIAMOND), new Card(10, Cardable.Suit.DIAMOND)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(4, Cardable.Suit.SPADE), new Card(4, Cardable.Suit.CLUB), new Card(4, Cardable.Suit.CLUB), new Card(8, Cardable.Suit.CLUB), new Card(4, Cardable.Suit.DIAMOND)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) < 0, "Four of kind beats three of kind.");
    }//end test15

	/********************
	 * test16
	 * 
	 * PURPOSE: Four of a kind beats two pairs.
	 * PARAMETERS:
	 * 		This method does not accept any parameters. This method is a JUnit test method to test
	 * 		the compareTo method for a Handable.
	 * RETURNS: Does not return anything.
	 ********************/
	@Test
    public void test16() 
	{
		Cardable[] cards1 = {new Card(8, Cardable.Suit.DIAMOND), new Card(2, Cardable.Suit.SPADE), new Card(8, Cardable.Suit.HEART), new Card(2, Cardable.Suit.DIAMOND), new Card(10, Cardable.Suit.DIAMOND)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(4, Cardable.Suit.SPADE), new Card(4, Cardable.Suit.CLUB), new Card(4, Cardable.Suit.CLUB), new Card(8, Cardable.Suit.CLUB), new Card(4, Cardable.Suit.DIAMOND)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) < 0, "Four of kind beats two pairs.");
    }//end test16

	/********************
	 * test17
	 * 
	 * PURPOSE: Four of a kind beats one pair.
	 * PARAMETERS:
	 * 		This method does not accept any parameters. This method is a JUnit test method to test
	 * 		the compareTo method for a Handable.
	 * RETURNS: Does not return anything.
	 ********************/
	@Test
    public void test17() 
	{
		Cardable[] cards1 = {new Card(8, Cardable.Suit.DIAMOND), new Card(2, Cardable.Suit.SPADE), new Card(9, Cardable.Suit.HEART), new Card(2, Cardable.Suit.DIAMOND), new Card(10, Cardable.Suit.DIAMOND)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(4, Cardable.Suit.SPADE), new Card(4, Cardable.Suit.CLUB), new Card(4, Cardable.Suit.CLUB), new Card(8, Cardable.Suit.CLUB), new Card(4, Cardable.Suit.DIAMOND)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) < 0, "Four of kind beats one pair.");
    }//end test17

	/********************
	 * test18
	 * 
	 * PURPOSE: Low straight beats a hand with high cards but nothing.
	 * PARAMETERS:
	 * 		This method does not accept any parameters. This method is a JUnit test method to test
	 * 		the compareTo method for a Handable.
	 * RETURNS: Does not return anything.
	 ********************/
	@Test
    public void test18() 
	{
		Cardable[] cards1 = {new Card(2, Cardable.Suit.DIAMOND), new Card(3, Cardable.Suit.SPADE), new Card(14, Cardable.Suit.CLUB), new Card(5, Cardable.Suit.DIAMOND), new Card(4, Cardable.Suit.CLUB)};
		Hand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(13, Cardable.Suit.DIAMOND), new Card(14, Cardable.Suit.CLUB), new Card(9, Cardable.Suit.CLUB), new Card(12, Cardable.Suit.HEART), new Card(11, Cardable.Suit.DIAMOND)};
		Hand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) > 0, "Low straight beats a hand with nothing even if the hand has higher ranked cards.");
    }//end test18

	/********************
	 * test19
	 * 
	 * PURPOSE: Any pair beats any hand with nothing.
	 * PARAMETERS:
	 * 		This method does not accept any parameters. This method is a JUnit test method to test
	 * 		the compareTo method for a Handable.
	 * RETURNS: Does not return anything.
	 ********************/
	@Test
    public void test19() 
	{
		Cardable[] cards1 = {new Card(2, Cardable.Suit.DIAMOND), new Card(2, Cardable.Suit.SPADE), new Card(7, Cardable.Suit.CLUB), new Card(6, Cardable.Suit.DIAMOND), new Card(4, Cardable.Suit.CLUB)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(13, Cardable.Suit.DIAMOND), new Card(14, Cardable.Suit.CLUB), new Card(9, Cardable.Suit.CLUB), new Card(12, Cardable.Suit.HEART), new Card(11, Cardable.Suit.DIAMOND)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) > 0, "Low straight beats a hand with nothing even if the hand has higher ranked cards.");
    }//end test19

	/********************
	 * test20
	 * 
	 * PURPOSE: Two four of a kinds one a flush the other is not, higher four of a kind wins.
	 * PARAMETERS:
	 * 		This method does not accept any parameters. This method is a JUnit test method to test
	 * 		the compareTo method for a Handable.
	 * RETURNS: Does not return anything.
	 ********************/
	@Test
    public void test20() 
	{
		Cardable[] cards1 = {new Card(7, Cardable.Suit.DIAMOND), new Card(7, Cardable.Suit.DIAMOND), new Card(7, Cardable.Suit.DIAMOND), new Card(6, Cardable.Suit.DIAMOND), new Card(7, Cardable.Suit.DIAMOND)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(13, Cardable.Suit.DIAMOND), new Card(13, Cardable.Suit.CLUB), new Card(13, Cardable.Suit.CLUB), new Card(5, Cardable.Suit.HEART), new Card(13, Cardable.Suit.DIAMOND)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) < 0, "Higher four of a kind wins.");
    }//end test20

}//end JUnitTests
