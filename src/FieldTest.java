import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    @Test
    void getMines(){
        Field field = new Field(9, 10, 30);
        assertEquals(30, field.getMines());
    }

    @Test
    void getClosed(){
        Field field = new Field(9, 10, 30);
        assertEquals(90, field.getClosed());
    }

   @Test
    void changeFlag() {
        Field f = new Field(5,5, 3);
        assertTrue(()->{
            f.changeFlag(2,2);
            Hexagon h = f.hexagon()[2][2];
            return h.flag();
        });
    }

}

