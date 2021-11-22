//Game Rules//

//There are between 2 and 7 players (inclusive)//
//Each player receives 7 cards//
//There are 3 community cards//

//The winner of the game is determined as follows://

/*The favorite "suit" is determined by the suit of the community card with the highest
  rank. If a card is of this favorite suit, its rank will be multiplied by 1.5. The
  average of the ranks of the community cards as well as the average of the ranks of
  each hand of cards held by the players are found. The player whose hand's average
  rank is closest to the community cards' average rank wins the game. Please note that
  all averages are rounded down to the nearest integer.

  If two or more players have the same closest average rank, the winner of the game is
  determined by the one among those players who holds a card with the highest rank. If
  two or more players have the same closest average rank and the same highest rank, the
  winner of the game is the player whose highest rank card has a suit that comes first
  in alphabetical order.
 */

public class Game
{
    public static void main (String[] args)
    {

        if (args.length != 1)
        {
            System.err.println("Please enter a single positive integer between 2 and 7 (inclusive) as the argument");
            System.exit(1);
        }

        int playerCount = Integer.parseInt(args[0]);

        if (playerCount < 2 || playerCount > 7)
        {
            System.err.println("Please enter a single positive integer between 2 and 7 (inclusive) as the argument");
            System.exit(1);
        }

        Deck deck = new Deck();
        deck.shuffle();

        Hand[] player = new Hand[playerCount];

        for (int i = 0; i < playerCount; i++)
        {
            player[i] = deck.getHand(7);
        }

        Hand community = deck.getHand(3);

        int winner = getWinner(player, community);

        for (int i = 0; i < playerCount; i++)
        {
            System.out.println("The hand of player " + (i + 1) + " is: " + player[i].toString());
        }

        System.out.println("The community cards are: " + community.toString());

        System.out.println("The winner of the game is Player " + (winner + 1));
    }

    public static int getWinner (Hand[] player, Hand community)
    {
        int maxCommunity = 0;
        int favoriteSuit = 0;

        for (int i = 0; i < 3; i++)
        {
            if (community.hand[i].getRank() > maxCommunity)
            {
                maxCommunity = community.hand[i].getRank();
                favoriteSuit = community.hand[i].getSuit();
            }
        }

        int averageCommunity = 0;

        for (int i = 0; i < 3; i++)
        {
            if (community.hand[i].getSuit() == favoriteSuit)
            {
                averageCommunity += (community.hand[i].getRank() * 1.5);
            }
            else
            {
                averageCommunity += community.hand[i].getRank();
            }
        }
        averageCommunity = averageCommunity / 3;

        int[] averagePlayer = new int [player.length];

        for (int i = 0; i < player.length; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                if (player[i].hand[j].getSuit() == favoriteSuit)
                {
                    averagePlayer[i] += (player[i].hand[j].getRank() * 1.5);
                }
                else
                {
                    averagePlayer[i] += player[i].hand[j].getRank();
                }
            }

            averagePlayer[i] = averagePlayer[i] / 7;
        }

        int[] averageDiff = new int [player.length];
        int minDiff = 14;
        int winner = 0;
        int maxRank = 0;
        char lowSuit = 'z';

        for (int i = 0; i < player.length; i++)
        {
            averageDiff[i] = Math.abs(averagePlayer[i] - averageCommunity);

            if (averageDiff[i] < minDiff)
            {
                minDiff = averageDiff[i];
                winner = i;
                for (int j = 0; j < 7; j++)
                {
                    if (player[i].hand[j].getRank() > maxRank)
                    {
                        maxRank = player[i].hand[j].getRank();
                        lowSuit = player[i].hand[j].getSuitName().charAt(0);
                    }
                }
            }
            else if (averageDiff[i] == minDiff)
            {
                for (int j = 0; j < 7; j++)
                {
                    if (player[i].hand[j].getRank() > maxRank)
                    {
                        maxRank = player[i].hand[j].getRank();
                        winner = i;
                    }
                    else if (player[i].hand[j].getRank() == maxRank)
                    {
                        if (player[i].hand[i].getSuitName().charAt(0) < lowSuit)
                        {
                            lowSuit = player[i].hand[i].getSuitName().charAt(0);
                            winner = i;
                        }
                    }
                }
            }
        }

        return winner;
    }
}
