package com.test.flat;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a function that will flatten an array of arbitrarily nested arrays of integers into a flat
 * array of integers. e.g. [[1,2,[3]],4] → [1,2,3,4]. If the language you're using has a function
 * to flatten arrays, you should pretend it doesn't exist.
 */
public class Flattener {

    /**
     * @param unflattened array object of integers or nested array object of integers
     * @return flattened array of integers (empty array when null)
     *
     * note: it doesn't throw exception, will flat just integer values
     */
    static Integer[] flattenInteger(Object[] unflattened) {
        if (unflattened == null) {
            return new Integer[0];
        }

        List<Integer> flat = new ArrayList<>();
        flattenInteger(unflattened, flat);

        return flat.toArray(new Integer[flat.size()]);
    }

    private static void flattenInteger(Object[] unflattened, List<Integer> flat) {
        for (Object item : unflattened) {
            if (item instanceof Object[]) {
                flattenInteger((Object[]) item, flat);

            } else if (item instanceof Integer) {
                flat.add((Integer) item);
            }
        }
    }

}
