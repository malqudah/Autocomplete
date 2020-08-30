/* *****************************************************************************
 *  Name:    Mohammad Alqudah
 *  NetID:   malqudah
 *  Precept: P05
 *
 *  Description:  given a specific query and a weight, creates a Term and
 * compares it to other terms using a series of comparisons; implements
 * comparable to sort by reverse order, as well as through lexicographic order
 * in general and lexicographic order based off the first N terms.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Term implements Comparable<Term> {

    // instance variable for the query
    private final String query;

    // instance variable for the weight
    private final long weight;

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        if (query == null) {
            throw new IllegalArgumentException();
        }
        if (weight < 0) {
            throw new IllegalArgumentException();
        }

        // initializes the instance variables to given ones
        this.query = query;
        this.weight = weight;

    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new ViaReverseOrder();
    }

    private static class ViaReverseOrder implements Comparator<Term> {

        public int compare(Term t1, Term t2) {
            return Long.compare(t2.weight, t1.weight);
        }
    }

    // Compares the two terms in lexicographic order,
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0) {
            throw new IllegalArgumentException();
        }
        return new LexicoOrderLimited(r);
    }

    private static class LexicoOrderLimited implements Comparator<Term> {

        // instance variable for first r characters
        private final int charNum;

        // constructor to initialize charNum to r from prev method
        private LexicoOrderLimited(int r) {
            charNum = r;
        }

        public int compare(Term t1, Term t2) {

            int minLength = Math.min(t1.query.length(),
                                     Math.min(t2.query.length(), charNum));
            for (int i = 0; i < minLength; i++) {
                if (t1.query.charAt(i) - t2.query.charAt(i) > 0) {
                    return +1;
                }
                if (t1.query.charAt(i) - t2.query.charAt(i) < 0) {
                    return -1;
                }
            }
            if (t1.query.length() < charNum && t1.query.length() <
                    t2.query.length()) {
                return -1;
            }
            if (t2.query.length() < charNum && t2.query.length() <
                    t1.query.length()) {
                return +1;
            }
            return 0;
        }
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        return this.query.compareTo(that.query);
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return weight + "\t" + query;
    }

    // unit testing (required)
    public static void main(String[] args) {

        Term testOne = new Term("hello", 5);
        Term testTwo = new Term("Hippocampus", 11);

        Comparator<Term> cmp = Term.byReverseWeightOrder();
        StdOut.println("by reverse order: " + cmp.compare(testOne, testTwo));

        Comparator<Term> cmpTwo = Term.byPrefixOrder(3);
        StdOut.println("by prefix order: " + cmpTwo.compare(testOne, testTwo));

        StdOut.println("compareTo result: " + testOne.compareTo(testTwo));


    }

}
