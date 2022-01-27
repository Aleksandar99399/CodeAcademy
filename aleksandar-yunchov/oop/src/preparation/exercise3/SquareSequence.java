package preparation.exercise3;

import java.math.BigInteger;

public class SquareSequence implements Sequence<BigInteger> {
    private int i;

    public boolean hasNext() {
        return true;
    }
    public int next() {
        i++;
        return i * i;
    }

    @Override
    public OwnRand randomInts()
    {
        return new OwnRand();
    }


}
