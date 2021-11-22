public class Deck
{
    private Card[] deck = new Card[52];
    private int currentTop = 0;

    public Deck()
    {
        int n = 0;
        for (int s = 0; s < 4; s++)
        {
            for (int r = 0; r < 13; r++)
            {
                this.deck[n] = new Card (s,r);
                n += 1;
            }
        }
    }

    public void shuffle()
    {
        for (int i = 51; i > 1; i--)
        {
            int j = (int)(Math.random() * i);
            Card temp = this.deck[i];
            this.deck[i] = this.deck[j];
            this.deck[j] = temp;
        }
    }

    public Hand getHand(int n)
    {
        Hand hand = new Hand(n);

        for (int i = 0; i < n; i++)
        {
            hand.hand[i] = this.deck[currentTop];
            currentTop += 1;
        }

        return hand;
    }

    public int size()
    {
        return this.deck.length - currentTop;
    }

    public String toString()
    {
        String output = "";

        for (int i = currentTop; i < this.deck.length; i++)
        {
            if (i == currentTop)
            {
                output = output + (this.deck[i].toString());
            }
            else
            {
                output = output + (", " + this.deck[i].toString());
            }
        }

        return output;
    }
}
