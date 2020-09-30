import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HexagonTest {

    @Test
    void changeFlag() {
        Hexagon h = new Hexagon(1,1);
        assertTrue(()->{
            h.changeFlag();
            return h.flag();
        });
    }

    @Test
    void flag(){
        Hexagon hexagon = new Hexagon(1,1);
        assertFalse(hexagon.flag());
    }

    @Test
    void getXY(){
        Hexagon hexagon = new Hexagon(1,1);
        assertEquals(1, hexagon.getX());
        assertEquals(1, hexagon.getY());
    }

    @Test
    void setVisible(){
        Hexagon h = new Hexagon(1,1);
        assertTrue(()->{
            h.setVisible();
            return h.getVisibility();
        });
    }

    @Test
    void getVisibility(){
        Hexagon hexagon = new Hexagon(1,1);
        assertFalse(hexagon.getVisibility());
    }

    @Test
    void getMine() {
        Hexagon hexagon = new Hexagon(1,1);
        assertFalse(hexagon.getMine()); }

    @Test
    void setMine() {
        Hexagon h = new Hexagon(1,1);
        assertTrue(()->{
            h.setMine();
            return h.getMine();
        });
    }

    @Test
    void getMinesCount() {
        Hexagon h = new Hexagon(10,11);
        h.setMinesCount((byte) 5);
        assertEquals(5, h.getMinesCount());
    }
}
