package record_test;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class RecordTest {

    record Person(String name, Integer age) {
    }

    @Test
    public void create() {
        Person person = new Person("vasi", 55);

        assert person.age.equals(55);
        assert person.name.equals("vasi");

        //так тоже работает
        assertEquals("vasi", person.name);
        assertEquals("vasi", person.name());

        assertEquals(Integer.valueOf(55), person.age);
        assertTrue(55 == person.age); //????
        assertTrue(person.age.equals(55));
        assertEquals(55, person.age.intValue());

        assertEquals(Integer.valueOf(55), person.age());
    }
}
