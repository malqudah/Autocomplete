/* *****************************************************************************
 *  Name:    Mohammad Alqudah
 *  NetID:   malqudah
 *  Precept: P05
 *
 *  Description:  Uses a modified version of base binary search
 * to find the first occurrence of a given key as opposed to any occurrence,
 * and the last occurrence for a given key as opposed to any occurence.
 * implements the original binary search code but with some changes
 * to account for the first/last occurrences needed
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class BinarySearchDeluxe {

    /* @citation Copied/Adapted from: https://algs4.cs.princeton.edu/11model/
    BinarySearch.java.html Accessed 02/24/2020.
    */

    // Returns the index of the first key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key,
                                         Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException();
        }
        if (a.length == 0) {
            return -1;
        }
        int lo = 0;
        int hi = a.length - 1;
        while (lo < hi - 1) {
            int mid = lo + (hi - lo) / 2;
            int compare = comparator.compare(key, a[mid]);
            if (compare < 0) {
                hi = mid - 1;
            }
            else if (compare > 0) {
                lo = mid + 1;
            }
            else {
                hi = mid;
            }
        }
        if (comparator.compare(a[lo], key) == 0) {
            return lo;
        }
        if (comparator.compare(a[hi], key) == 0) {
            return hi;
        }
        return -1;
    }

    // Returns the index of the last key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key,
                                        Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException();
        }
        if (a.length == 0) {
            return -1;
        }
        int lo = 0;
        int hi = a.length - 1;
        while (lo < hi - 1) {
            int mid = lo + (hi - lo) / 2;
            int compare = comparator.compare(key, a[mid]);
            if (compare < 0) {
                hi = mid - 1;
            }
            else if (compare > 0) {
                lo = mid + 1;
            }
            else {
                lo = mid;
            }
        }
        if (comparator.compare(a[hi], key) == 0) {
            return hi;
        }
        if (comparator.compare(a[lo], key) == 0) {
            return lo;
        }
        return -1;
    }

    // unit testing (required)
    public static void main(String[] args) {

        String[] a = { "A", "A", "C", "G", "G", "T" };
        int firstIndex = BinarySearchDeluxe.firstIndexOf(
                a, "G", String.CASE_INSENSITIVE_ORDER);
        StdOut.println("First occurrence: " + firstIndex);

        int lastIndex = BinarySearchDeluxe.lastIndexOf(
                a, "G", String.CASE_INSENSITIVE_ORDER);
        StdOut.println("Last occurrence: " + lastIndex);

        StdOut.println(firstIndexOf(a, "OHX", String.CASE_INSENSITIVE_ORDER));
        StdOut.println(lastIndexOf(a, "YSR", String.CASE_INSENSITIVE_ORDER));

    }
}
