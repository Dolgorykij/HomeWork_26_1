import org.example.Exception.IndexOutOfSizeException;
import org.example.Exception.ItemIsNullException;
import org.example.Exception.ItemNotFoundException;
import org.example.StringListService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringListServiceTest {

    StringListService stringListService = new StringListService();

    @Test
    public void testAddItem() {
        String[] testArray;
        testArray = new String[6];
        testArray[0] = "1";
        testArray[1] = "2";
        var expected = testArray;
        stringListService.add("1");
        stringListService.add(1, "2");
        var actual = stringListService.toString();
        assertThat(actual.toString().contains(expected.toString()));
    }
    @Test
    public void testAddIndex() {
        String[] testArray;
        testArray = new String[6];
        testArray[0] = "1";
        testArray[1] = "2";
        var expected = testArray;
        stringListService.add(0, "1");
        stringListService.add(1, "2");
        var actual = stringListService.toString();
        assertThat(actual.toString().contains(expected.toString()));
    }

    @Test
    public void testItemIsNull() {
        assertThrows(ItemIsNullException.class,() -> stringListService.add(null));
    }
    @Test
    public void testSet () {
        String[] testArray;
        testArray = new String[6];
        testArray[0] = "1";
        testArray[1] = "4";
        testArray[2] = "3";
        stringListService.add(0,"1");
        stringListService.add(1,"2");
        stringListService.add(2,"3");
        stringListService.set(1,"4");
        assertThat(Arrays.toString(testArray).equals(stringListService.toString()));
    }

    @Test
    public void testRemoveItem () {
        String[] testArray;
        testArray = new String[6];
        testArray[0] = "1";
        testArray[1] = "3";
        stringListService.add(0,"1");
        stringListService.add(1,"2");
        stringListService.add(2,"3");
        stringListService.remove("2");
        assertThat(Arrays.toString(testArray).equals(stringListService.toString()));
    }
    @Test
    public void testRomeveIndex () {
        String[] testArray;
        testArray = new String[6];
        testArray[0] = "1";
        testArray[1] = "3";
        stringListService.add(0,"1");
        stringListService.add(1,"2");
        stringListService.add(2,"3");
        stringListService.remove(1);
        assertThat(Arrays.toString(testArray).equals(stringListService.toString()));
    }
    @Test
    public void testNotFound () {
        assertThrows(ItemNotFoundException.class,() -> stringListService.contains("1"));
    }
}
