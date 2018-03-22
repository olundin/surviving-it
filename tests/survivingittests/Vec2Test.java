package survivingittests;

import survivingit.util.Vec2;

import static org.junit.Assert.*;

/**
 * Created by AngusLothian on 2018-03-21.
 */
public class Vec2Test {

    private Vec2 zeros;
    private Vec2 oneZero;
    private Vec2 zeroOne;
    private Vec2 twoZero;
    private Vec2 zeroTwo;

    private Vec2 negativePos;
    private Vec2 posNegative;
    private Vec2 negativeNegative;
    private Vec2 posPos;

    @org.junit.Before
    public void setUp() throws Exception {
        zeros = new Vec2(0, 0);
        oneZero = new Vec2(1, 0);
        zeroOne = new Vec2(0, 1);
        twoZero = new Vec2(2, 0);
        zeroTwo = new Vec2(0, 2);

        negativePos = new Vec2(-1, 1);
        posNegative = new Vec2(1, -1);
        negativeNegative = new Vec2(-1, -1);
        posPos = new Vec2(1, 1);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        // hmm
    }

    @org.junit.Test
    public void add() throws Exception {
        assertEquals(zeros, Vec2.add(zeros, zeros));
        assertEquals(oneZero, Vec2.add(oneZero, zeros));
        assertEquals(oneZero, Vec2.add(zeros, oneZero));
        assertEquals(zeroOne, Vec2.add(zeros, zeroOne));

        assertEquals(posPos, Vec2.add(zeroOne, oneZero));
        assertEquals(posPos, Vec2.add(oneZero, zeroOne));

        assertEquals(twoZero, Vec2.add(oneZero, oneZero));
        assertEquals(zeroTwo, Vec2.add(zeroOne, zeroOne));

        assertEquals(zeros, Vec2.add(posNegative, negativePos));
        assertEquals(zeros, Vec2.add(posPos, negativeNegative));
    }

    @org.junit.Test
    public void addConst() throws Exception {
    }

    @org.junit.Test
    public void sub() throws Exception {
    }

    @org.junit.Test
    public void subConst() throws Exception {
    }

    @org.junit.Test
    public void mult() throws Exception {
    }

    @org.junit.Test
    public void between() throws Exception {
    }

}