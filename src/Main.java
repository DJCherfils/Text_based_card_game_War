
import java.util.*;

public class Main {

    /**
     * The ranks of the cards for this game to be sent to the deck.
     */
    private static final String[] RANKS = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

    /**
     * The suits of the cards for this game to be sent to the deck.
     */
    private static final String[] SUITS = { "♠", "♥", "♦", "♣" };

    /**
     * The values of the cards for this game to be sent to the deck.
     */
    private static final int[] POINT_VALUES = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };

    public static void main(String[] args)
    {
        //beginningDeck is the Deck we start with.  When we deal, it gets split into two
        //Decks for each player
        Deck beginningDeck = new Deck(RANKS,SUITS,POINT_VALUES);
        beginningDeck.shuffle();
        //System.out.println(beginningDeck);

        Deck computerDeck = new Deck(RANKS,SUITS,POINT_VALUES);
        Deck playerDeck = new Deck(RANKS,SUITS,POINT_VALUES);

        while(!beginningDeck.isEmpty()){
            computerDeck.addToTop(beginningDeck.deal());
            computerDeck.addToTop(beginningDeck.deal());
        }

        System.out.println("Players have been dealt their cards!");
        String quite = "n";
        Scanner s = new Scanner(System.in);
        int plays = 0;
        Deck playedDeck = new Deck();
        while(!quite.equals("y") && !computerDeck.isEmpty() && !playerDeck.isEmpty())
        {
            Card compCard = computerDeck.deal();
            System.out.println("Computer has played a " + compCard);
            playedDeck.addToTop(compCard);
            System.out.print("Press Enter to play: ");
            s.nextLine();
            Card playedCard = playerDeck.deal();
            System.out.println("You played a " + playedCard);
            playedDeck.addToTop(playedCard);
            plays++;
            if(compCard.pointValue() > playedCard.pointValue()){
                System.out.println("Computer wins!" + compCard + " beats " + playedCard + "\n");
                while(!playedDeck.isEmpty()){
                    computerDeck.addToBottom(playedDeck.deal());
                }
                System.out.println("Computer has " + computerDeck.size() + " and you have " + playerDeck.size());

            }
            else if(compCard.pointValue() < playedCard.pointValue()){
                System.out.println("Player wins! " + playedCard + " beats " + compCard + "\n");
                while(!playedDeck.isEmpty()){
                    playerDeck.addToBottom(playedDeck.deal());
                }
                System.out.println("Computer has " + computerDeck.size() + " and you have " + playerDeck);
            }
            else{
                for(int i = 0; i < 3; i ++){
                    playedDeck.addToBottom(computerDeck.deal());
                    playedDeck.addToBottom(playerDeck.deal());
                }
                System.out.println("TIE! Each player has laid down 3 cards");
            }
            if(plays % 26 == 0){
                System.out.print("You've played " + plays + "\nReady to quit? (y/n): ");
                quite = s.nextLine();
            }
        }
        System.out.println("Game over!");
        if(playerDeck.size() > computerDeck.size()){
            System.out.println("Player won with " + playerDeck.size() + " and Computer had " + computerDeck.size());
        }
        else if(playerDeck.size() < computerDeck.size()){
            System.out.println("Computer won with " + computerDeck.size() + " and Player had " + playerDeck.size());
        }
        else
            System.out.println("Dang! A tie! 26 to 26!");



    }
}