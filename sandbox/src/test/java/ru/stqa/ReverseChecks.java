package ru.stqa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ReverseChecks {
    @Test
    void testSqrt(){
        var input = 4.0;
        var result = Math.sqrt(input);
        var reverse = result * input;
        Assertions.assertEquals(2.0,reverse,0.00001);
    }


    @Test
    void testSort(){
        var input = new ArrayList<>(List.of(3,7,4,9,0));
        input.sort(Integer::compareTo);
        for (int i = 0; i < input.size() - 1; i++) {
            Assertions.assertTrue(input.get(i) <= input.get(i+1));
        }
    }
}
