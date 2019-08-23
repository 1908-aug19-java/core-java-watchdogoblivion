package com.revature.eval.java.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Book {
	String hu = "d";
}

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	// O(n) time complexity
	public String reverse(String string) {
		String[] strings = string.split("");
		String reversed = "";
		for (int i = strings.length - 1; i >= 0; i--) {
			reversed += strings[i];
		}
		return reversed;
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	// O(n) time complexity
	public String acronym(String phrase) {
		String[] strings = phrase.split(" |-");
		String acronym = "";
		for (int i = 0; i < strings.length; i++) {
			acronym += strings[i].substring(0, 1).toUpperCase();
		}
		return acronym;
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	// O(1) time complexity
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			return sideOne == sideTwo & sideTwo == sideThree;
		}

		public boolean isIsosceles() {
			return sideOne == sideTwo || sideOne == sideThree || sideTwo == sideThree;
		}

		public boolean isScalene() {
			return sideOne != sideTwo && sideOne != sideThree && sideTwo != sideThree;
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	// O(n) time complexity
	public int getScrabbleScore(String string) {
		String[] strings = string.split("");
		Map<String, Integer> scrabble = new HashMap<String, Integer>();
		setValues(scrabble);
		int score = 0;
		for (String s : strings) {
			if (scrabble.get(s.toUpperCase()) != null) {
				score += scrabble.get(s.toUpperCase());
			}
		}
		return score;
	}

	// I created a separate method in case scores needed to change
	// O(26) time complexity
	private void setValues(Map<String, Integer> scrabble) {
		String[] ones = { "A", "E", "I", "O", "U", "L", "N", "R", "S", "T" }, twos = { "D", "G" },
				threes = { "B", "C", "M", "P" }, fours = { "F", "H", "V", "W", "Y" }, fives = { "K" },
				eights = { "J", "X" }, tens = { "Q", "Z" };
		for (int i = 1; i < 11; i++) {
			switch (i) {
			case 1:
				Arrays.stream(ones).forEach(l -> scrabble.put(l, 1));
				break;
			case 2:
				Arrays.stream(twos).forEach(l -> scrabble.put(l, 2));
				break;
			case 3:
				Arrays.stream(threes).forEach(l -> scrabble.put(l, 3));
				break;
			case 4:
				Arrays.stream(fours).forEach(l -> scrabble.put(l, 4));
				break;
			case 5:
				Arrays.stream(fives).forEach(l -> scrabble.put(l, 5));
				break;
			case 8:
				Arrays.stream(eights).forEach(l -> scrabble.put(l, 8));
				break;
			case 10:
				Arrays.stream(tens).forEach(l -> scrabble.put(l, 10));
				break;
			default:
				continue;
			}
		}
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	// O(n) complexity since replace runs at O(n) and is chained
	public String cleanPhoneNumber(String string) {
		String regex = "(?=.*[a-zA-Z!@#$%^&*~<>])(?=.*[0-9]).*";
		if (string.matches(regex)) {
			throw new IllegalArgumentException();
		}
		String newString = string.replace(" ", "").replace(".", "").replace("(", "").replace(")", "").replace("-", "");
		if (newString.length() > 11) {
			throw new IllegalArgumentException();
		}
		return newString;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */

	// O(n) complexity
	public Map<String, Integer> wordCount(String string) {
		String[] stringArray = string.split(" |,|\n");
		Map<String, Integer> occurencesMap = new HashMap<String, Integer>();
		Set<String> stringSet = new HashSet<>();
		for (String s : stringArray) {
			if (!s.isEmpty()) {
				stringSet.add(s);
			}
		}
		for (String s : stringSet) {
			occurencesMap.put(s, 0);
		}
		for (String s : stringArray) {
			if (!s.isEmpty()) {
				occurencesMap.put(s, occurencesMap.get(s) + 1);
			}
		}
		return occurencesMap;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	// I extended comparable in case there are types other than numbers though that
	// is not tested
	// time complexity O(log(n))
	static class BinarySearch<T extends Comparable<T>> {
		private List<T> sortedList;

		public int indexOf(T t) {
			int result = recurse(t, (sortedList.size() - 1) / 2);
			return result;
		}

		public int recurse(T value, int index) {
			T half = sortedList.get(index);
			if (value.compareTo(half) == 0) {
				return index;
			} else if (value.compareTo(half) > 0) {
				return recurse(value, index + index / 2);
			} else {
				return recurse(value, index / 2);
			}
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	// time complexity O(m*n) because of indox.of operation worst case O(n^2)
	public String toPigLatin(String string) {
		String[] strings = string.split(" ");
		Map<Integer, Integer> vowelPositionsMap = new HashMap<Integer, Integer>();
		int startingIndex = 0;
		int offset = 0;
		for (int j = 0; j < strings.length; j++) {
			int firstVowelIndex = Integer.MAX_VALUE;
			String[] vowels = { "a", "e", "i", "o", "u" };
			// complexity constant O(5)
			for (String v : vowels) {
				int vIndex = string.indexOf(v, startingIndex);
				firstVowelIndex = vIndex < 0 ? firstVowelIndex : firstVowelIndex < vIndex ? firstVowelIndex : vIndex;
			}
			firstVowelIndex = firstVowelIndex == Integer.MAX_VALUE ? -1 : firstVowelIndex;
			vowelPositionsMap.put(j, firstVowelIndex + offset);
			startingIndex = string.indexOf(" ", firstVowelIndex) + 1;
			offset = -startingIndex;
		}
		String result = "";
		for (int i = 0; i < strings.length; i++) {
			String current = strings[i];
			if (current.substring(0, 2).equalsIgnoreCase("qu")) {
				result += current.substring(2, current.length()) + current.substring(0, 2) + "ay ";
			} else {
				result += current.substring(vowelPositionsMap.get(i), current.length())
						+ current.substring(0, vowelPositionsMap.get(i)) + "ay ";
			}

		}
		return result.trim();
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	// time complexity O(n)
	public boolean isArmstrongNumber(int input) {
		String inpString = new Integer(input).toString();
		String[] strings = inpString.split("");
		int[] integers = Arrays.stream(strings).mapToInt(a -> Integer.parseInt(a)).toArray();
		int total = 0;
		for (int i : integers) {
			total += Math.pow(i, integers.length);
		}
		return total == input;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	// Terrible time complexity O(n^2)
	// Might be better to run a parallel stream for checking primes since usage
	// leads to bad complexity
	public List<Long> calculatePrimeFactorsOf(long l) {
		List<Long> numberPrimes = new ArrayList<Long>();
		for (long i = 2; i <= l; i++) {
			if (l % i == 0) {
				if (isPrime(i) && i != 1) {
					numberPrimes.add(i);
				}
			}
		}
		long lastNumber = numberPrimes.stream().reduce((a, b) -> a * b).get();
		int repeated = 0;
		long divided = l / lastNumber;
		while (!isPrime(divided)) {
			divided = divided / lastNumber;
			repeated++;
		}

		for (int i = 0; i <= repeated; i++) {
			if (divided > 1)
				numberPrimes.add(divided);
		}

		Collections.sort(numberPrimes);
		return numberPrimes;
	}

	// time complexity is O(n/4) -> O(n)
	boolean isPrime(long n) {
		// All possible divisors are n/2, but all evens are accounted by 2, half of that
		// will be calculated so n/4
		int m = (int) (n / 4) + 1;
		// This takes into account all even numbers so that I can skip them
		if (n % 2 == 0) {
			return n == 2;
		}
		// 2 is the starting prime, but i already accounted for evens, I added this so
		// that I could use i+=2 properly
		// if i start at 3 in the loop it wont account for n=3 so I have to start higher
		// and 4 is even, there is no point of starting
		// at less that 2 since 2 is the first prime
		if (n % 3 == 0) {
			return n == 3;
		}
		for (int i = 5; i < m; i += 2) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	// time complexity o(n)
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			String[] strings = string.split("");
			int[] numericalLetters = Arrays.stream(strings).mapToInt(a -> a.charAt(0)).toArray();
			for (int i = 0; i < numericalLetters.length; i++) {
				if (numericalLetters[i] <= 122 && numericalLetters[i] >= 97) {
					rotate(numericalLetters, i, 96);
				} else if (numericalLetters[i] <= 90 && numericalLetters[i] >= 65) {
					rotate(numericalLetters, i, 64);
				}
			}
			return Arrays.stream(numericalLetters).boxed().map((Integer a) -> {
				return (char) a.intValue();
			}).map(b -> b.toString()).reduce("", (a, b) -> {
				return a + b;
			});
		}

		private void rotate(int[] numericalLetters, int i, int min) {
			if ((numericalLetters[i] - min) + key > 26) {
				numericalLetters[i] = (numericalLetters[i] - 26) + key;
			} else {
				numericalLetters[i] = numericalLetters[i] + key;
			}
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	// time complexity O((n/4)*(n-167)) -> O(n^2) However test output is much better
	// than original/standard loop due to isPrime running at n/4. nested loop ->
	// 4-5s to 0.5-1s
	public int calculateNthPrime(int i) {
		if (i == 0) {
			throw new IllegalArgumentException();
		}
		int[] primes = getPrimes();
		int ithPrime = primes.length;
		if (i <= ithPrime) {
			return primes[i - 1];
		}
		int count = ithPrime;
		for (int j = primes[ithPrime - 1] + 1;; j++) {
			if (isPrime(j)) {
				count++;
			}
			if (count == i) {
				return j;
			}
		}
	}

	public int[] getPrimes() {
		int[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
				101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199,
				211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331,
				337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457,
				461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599,
				601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733,
				739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877,
				881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997 };
		return primes;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		// O(n) time complexity
		public static String encode(String string) {
			String[] strings = string.split("");
			int[] numericalLetters = Arrays.stream(strings).mapToInt(a -> a.charAt(0)).toArray();
			for (int i = 0; i < numericalLetters.length; i++) {
				if (numericalLetters[i] <= 122 && numericalLetters[i] >= 97) {
					numericalLetters[i] = (122 - numericalLetters[i]) + 97;
				} else if ((numericalLetters[i] <= 90 && numericalLetters[i] >= 65)) {
					numericalLetters[i] = (90 - numericalLetters[i]) + 97;
				}
			}
			String[] convertedStrings = Arrays.stream(numericalLetters).filter(a -> !(a == 32 | a == 46 | a == 44))
					.boxed().map((Integer a) -> {
						return (char) a.intValue();
					}).map(b -> b.toString()).toArray(String[]::new);
			int grouped = 1;
			String result = "";
			int length = convertedStrings.length;
			for (int i = 0; i < length; i++) {
				result += convertedStrings[i];
				if (grouped % 5 == 0 && i != length - 1) {
					result += " ";
				}
				if (!"".equals(convertedStrings[i].trim())) {
					grouped++;
				}
			}
			return result;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		// time complexity O(n)
		public static String decode(String string) {
			String[] strings = string.split("");
			int[] numericalLetters = Arrays.stream(strings).mapToInt(a -> a.charAt(0)).toArray();
			for (int i = 0; i < numericalLetters.length; i++) {
				if (numericalLetters[i] <= 122 && numericalLetters[i] >= 97) {
					numericalLetters[i] = (122 - numericalLetters[i]) + 97;
				}
			}
			String[] convertedStrings = Arrays.stream(numericalLetters).filter(a -> a != 32).boxed()
					.map((Integer a) -> {
						return (char) a.intValue();
					}).map(b -> b.toString()).toArray(String[]::new);
			String result = "";
			int length = convertedStrings.length;
			for (int i = 0; i < length; i++) {
				result += convertedStrings[i];
			}
			return result;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	// time complexity O(n)
	public boolean isValidIsbn(String string) {
		String[] strings = string.replaceAll("-", "").split("");
		int length = strings.length;
		if (length < 10 || length > 10) {
			return false;
		}
		int[] ints = new int[length];
		for (int i = 0; i < length; i++) {
			try {
				if (strings[i].equalsIgnoreCase("x")) {
					ints[i] = 10;
				} else {
					int number = Integer.parseInt(strings[i]);
					ints[i] = number;
				}
			} catch (NumberFormatException ne) {
				return false;
			}
		}
		int result = 0;
		for (int i = 0; i < length; i++) {
			result += ints[i] * (length - i);
		}
		return result % 11 == 0;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	// time complexity O(n)
	public boolean isPangram(String string) {
		int alphabeticProduct = getLowerCaseAlphabet().values().stream().reduce((a, b) -> a * b).get();
		int panagramProduct = 1;
		String[] strings = string.split("");
		Set<String> stringSet = new HashSet<String>();
		for (String s : strings) {
			if (!"".equals(s.trim()))
				stringSet.add(s);
		}
		for (String s : stringSet) {
			panagramProduct *= (int) s.charAt(0);
		}
		return panagramProduct % alphabeticProduct == 0;
	}

	Map<String, Integer> getLowerCaseAlphabet() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 97; i <= 122; i++) {
			map.put(new Character((char) i).toString(), i);
		}
		return map;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	// time complexity I believe is O(1)
	public Temporal getGigasecondDate(Temporal given) {
		if (given instanceof LocalDate) {
			LocalDateTime givenConverted = LocalDateTime.of(((LocalDate) given).getYear(),
					((LocalDate) given).getMonth(), ((LocalDate) given).getDayOfMonth(), 0, 0, 0);
			return givenConverted.plus(1_000_000_000L, ChronoUnit.SECONDS);
		}
		return given.plus(1_000_000_000L, ChronoUnit.SECONDS);
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	// time complexity O(n^2)
	public int getSumOfMultiples(int j, int[] set) {
		Set<Integer> integers = new HashSet<Integer>();
		for (int i : set) {
			for (int k = 0; k < j; k++) {
				if (k % i == 0) {
					integers.add(k);
				}
			}
		}
		return integers.stream().mapToInt(a -> a).sum();
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	// time complexity O(n)
	public boolean isLuhnValid(String string) {
		String[] strings = string.replaceAll(" ", "").split("");
		int length = strings.length;
		int[] integers = new int[length];

		for (int i = 0; i < length; i++) {
			try {
				int number = Integer.parseInt(strings[i]);
				if ((i + 1) % 2 == 0) {
					number = number * 2;
					if (number > 9) {
						number = number - 9;
					}
				}
				integers[i] = number;
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return Arrays.stream(integers).sum() % 10 == 0;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		int firstNumberIndex = string.indexOf("s") + 2;
		int spaceIndex = string.indexOf(" ", firstNumberIndex);
		int firstNumber = Integer.parseInt(string.substring(firstNumberIndex, spaceIndex));

		int spaceIndex2 = string.indexOf(" ", spaceIndex + 1);
		String operation = string.substring(spaceIndex + 1, spaceIndex2);

		int result = -1, lastSpaceIndex, secondNumber;

		if (operation.equals("plus") || operation.equals("minus")) {
			lastSpaceIndex = string.indexOf(" ", spaceIndex2);
			secondNumber = Integer.parseInt(string.substring(lastSpaceIndex + 1, string.length() - 1));
			switch (operation) {
			case "plus":
				result = firstNumber + secondNumber;
				break;
			case "minus":
				result = firstNumber - secondNumber;
				break;

			}
		} else {
			lastSpaceIndex = string.indexOf(" ", string.indexOf(" ", spaceIndex2) + 1);
			secondNumber = Integer.parseInt(string.substring(lastSpaceIndex + 1, string.length() - 1));
			switch (operation) {
			case "multiplied":
				result = firstNumber * secondNumber;
				break;
			case "divided":
				result = firstNumber / secondNumber;
				break;

			}
		}
		return result;
	}

}
