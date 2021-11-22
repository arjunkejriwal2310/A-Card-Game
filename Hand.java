public class Hand
{
    public Card[] hand;
    private int filled = 0;

    public Hand(int n)
    {
        this.hand = new Card[n];
    }

    public void addCard(Card c)
    {
        this.filled += 1;

        if (this.filled > this.hand.length)
        {
            System.err.println("There is no more space available in this hand");
            System.exit(1);
        }
        else
        {
            this.hand[this.filled - 1] = c;
        }
    }

    public String toString()
    {
        String output = " ";

        for (int i = 0; i < this.hand.length; i++)
        {
            if(i == 0)
            {
                output = output + (this.hand[i].toString());
            }
            else
            {
                output = output + (", " + this.hand[i].toString());
            }
        }

        return output;
    }

    public Card getCard(int i)
    {
        if (this.hand[i] == null)
        {
            System.err.println("No card is present at this position in the hand");
            System.exit(1);
        }

        return this.hand[i];
    }

    public int capacity()
    {
        return this.hand.length;
    }

    public int size()
    {
        int i = 0;
        int size = 0;

        while (this.hand[i] != null)
        {
            size += 1;
            i += 1;

            if (i == this.hand.length)
            {
                break;
            }
        }

        return size;
    }
}
