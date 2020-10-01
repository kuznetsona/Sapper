import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScanningTest{
    /*@Test
    void getField() {
        Scanning s = new Scanning(5,5,5);
        as
    }*/

    @Test
    void gameOver() {
        Scanning s = new Scanning(5,5,5);
        assertFalse(s::gameOver);
    }

    @Test
    void win() {
        Scanning s = new Scanning(5,5,5);
        assertFalse(s::win);
    }

    @Test
    void clickHexagon() {
    }

    /*@Test
    void testChangeFlag() {
        Scanning s = new Scanning(5,5,5);

        assertTrue(()->{
            s.changeFlag(2,2);
            Field f = s.getField();
            Hexagon h = f.getHexagon()[2][2];
            return h.flag();
        });*/

    //}

    @Test
    void restart() {
    }

    @Test
    void setGameParams() {
    }

}
