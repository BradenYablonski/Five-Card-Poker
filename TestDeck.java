import java.util.LinkedList;

public class TestDeck {
    public static void main(String[] args)
    {
        LinkedList<Cardable> myHand = new LinkedList<Cardable>();
        Deck test = new Deck();
        System.out.println(test);
        System.out.println();
        test.shuffle();
        System.out.println(test);
        System.out.println();
        // System.out.println(test);
        // System.out.println();
        // test.shuffle();
        // System.out.println(test);

        // myHand.add(test.drawACard(true));
        // myHand.add(test.drawACard(true));
        // myHand.add(test.drawACard(true));
        // myHand.add(test.drawACard(true));
        // myHand.add(test.drawACard(true));
        // myHand.add(test.drawACard(true));
        // myHand.add(test.drawACard(true));
        // myHand.add(test.drawACard(true));
        // myHand.add(test.drawACard(true));

        // System.out.println();
        System.out.println(myHand);
        // System.out.println(test);
        // System.out.println();
        // test.returnToDeck(myHand);
        // System.out.println(test);

    }
}
