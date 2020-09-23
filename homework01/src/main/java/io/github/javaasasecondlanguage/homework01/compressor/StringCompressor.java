package io.github.javaasasecondlanguage.homework01.compressor;

public class StringCompressor {
    /**
     * Given an array of characters, compress it using the following algorithm:
     *
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

        if (str.length == 0) {
            return new char[0];
        }

        StringBuilder result = new StringBuilder();
        int startPosition = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] < 'a' || str[i] > 'z') {
                throw new IllegalArgumentException(
                        "Only chars in range 'a'..'z' are allowed"
                );
            }

            if (str[i] != str[startPosition]) {
                addPattern(result, str[startPosition], i - startPosition);
                startPosition = i;
            }
        }
        addPattern(result, str[startPosition], str.length - startPosition);

        return result.toString().toCharArray();
    }

    private void addPattern(StringBuilder result, char symbol, int len) {
        result.append(symbol);
        if (len > 1) {
            result.append(len);
        }
    }
}
