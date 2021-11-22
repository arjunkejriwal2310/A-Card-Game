public class Card {
    private String[] suits = {"clubs", "diamonds", "spades", "hearts"};
    private String[] ranks={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};

    private int suit;
    private int rank;

    public Card(int suit, int rank)
    {
        this.suit = suit;
        this.rank = rank;
    }

    public String toString()
    {
        return ranks[rank] + " of " + suits[suit];
    }

    public int getSuit()
    {
        return suit;
    }

    public String getSuitName()
    {
        return suits[suit];
    }

    public int getRank() {
        return rank;
    }

    public boolean equals(Card other)
    {
        if (this.rank == other.rank && this.suit == other.suit)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int compareTo(Card other)
    {
        if (this.suit > other.suit)
        {
            return 1;
        }
        else if (this.suit == other.suit)
        {
            if (this.rank > other.rank)
            {
                return 1;
            }
            else if (this.rank == other.rank)
            {
                return 0;
            }
            else
            {
                return -1;
            }
        }
        else
        {
            return -1;
        }
    }
}
