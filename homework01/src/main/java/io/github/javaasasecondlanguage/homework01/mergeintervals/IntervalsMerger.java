package io.github.javaasasecondlanguage.homework01.mergeintervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class IntervalsMerger {
    /**
     * Given array of intervals, merge overlapping intervals
     * and sort them by start in ascending order
     * Interval is defined as [start, end] where start < end
     * <p>
     * Examples:
     * [[1,3][2,4][5,6]] -> [[1,4][5,6]]
     * [[1,2][2,3]] -> [[1,3]]
     * [[1,4][2,3]] -> [[1,4]]
     * [[5,6][1,2]] -> [[1,2][5,6]]
     *
     * @param intervals is a nullable array of pairs [start, end]
     * @return merged intervals
     * @throws IllegalArgumentException if intervals is null
     */
    public int[][] merge(int[][] intervals) {

        if (intervals == null) {
            throw new IllegalArgumentException();
        }

        if (intervals.length == 0) {
            return intervals;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> result = new LinkedList<>();
        int[] resInterval = copyInterval(intervals[0]);
        for (int[] curInterval : intervals) {
            if (resInterval[1] < curInterval[0]) {
                result.add(resInterval);
                resInterval = copyInterval(curInterval);
            } else {
                resInterval[1] = Math.max(resInterval[1], curInterval[1]);
            }
        }
        result.add(resInterval);

        return result.toArray(new int[result.size()][2]);
    }

    private int[] copyInterval(int[] interval) {
        return new int[]{interval[0], interval[1]};
    }
}
