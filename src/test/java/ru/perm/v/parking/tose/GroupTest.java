package ru.perm.v.parking.tose;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupTest {
    @Test
    void simple() {
        Assert.assertEquals("1", "1");
    }

    @Test
    void streamSort() {
        List<String> keywords = Arrays.asList("Apple", "Ananas", "Mango", "Banana", "Beer");
        Map<Character, List<String>> result = keywords.stream().sorted().
                collect(Collectors.groupingBy(it -> it.charAt(0)));
        System.out.println(result);

        Assert.assertEquals(3, result.size());

        Assert.assertEquals(2, result.get('A').size());
        Assert.assertEquals(2, result.get('B').size());

        Assert.assertEquals(List.of("Ananas", "Apple"), result.get('A'));
        Assert.assertEquals(List.of("Banana", "Beer"), result.get('B'));
    }
}

