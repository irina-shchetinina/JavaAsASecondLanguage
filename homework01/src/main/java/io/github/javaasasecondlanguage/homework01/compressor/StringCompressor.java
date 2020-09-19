package io.github.javaasasecondlanguage.homework01.compressor;

public class StringCompressor {
    /**
     * Given an array of characters, compress it using the following algorithm:
     * <p>
     * Begin with an empty string s.
     * For each group of consecutive repeating characters in chars:
     * If the group's length is 1, append the character to s.
     * Otherwise, append the character followed by the group's length.
     * Return a compressed string.
     * </p>
     * Follow up:
     * Could you solve it using only O(1) extra space?
     * </p>
     * Examples:
     * a -> a
     * aa -> a2
     * aaa -> a3
     * aaabb -> a3b2
     * "" -> ""
     * null -> Illegal argument
     * 234 sdf -> Illegal argument
     *
     * @param str nullable array of chars to compress
     *            str may contain illegal characters
     * @return a compressed array
     * @throws IllegalArgumentException if str is null
     * @throws IllegalArgumentException if any char is not in range 'a'..'z'
     */
    public char[] compress(char[] str) {

        if (str == null) {
            throw new IllegalArgumentException(
                    "Array to compress should be not null"
            );
        }

        Chain curChain = new Chain(0, str);
        StringBuilder result = new StringBuilder();
        if (str.length == 0) {
            return new char[0];
        }

        for (int i = 0; i < str.length; i++) {
            if (str[i] < 'a' || str[i] > 'z') {
                throw new IllegalArgumentException(
                        "Only chars in range 'a'..'z' are allowed"
                );
            }

            if (curChain.isChainFinished(i)) {
                curChain.addPattern(result);
                curChain = new Chain(i, str);
            }
        }

        curChain.addLastPattern(result);
        return result.toString().toCharArray();
    }

    private static class Chain {
        private final int startPosition;
        private final char[] str;
        private int curIndex = 0;

        public Chain(int startPosition, char[] str) {
            this.startPosition = startPosition;
            this.str = str;
        }

        public boolean isChainFinished(int curIndex) {
            this.curIndex = curIndex;
            return str[curIndex] != str[startPosition];
        }

        public void addPattern(StringBuilder result) {
            addPatternInner(result, curIndex);
        }

        public void addLastPattern(StringBuilder result) {
            addPatternInner(result, str.length);
        }

        private void addPatternInner(StringBuilder result, int index) {
            int len = index - startPosition;
            result.append(str[startPosition]);
            if (len > 1) {
                result.append(len);
            }
        }
    }
}
