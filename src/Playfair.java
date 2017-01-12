import java.util.Arrays;
import java.util.Scanner;

public class Playfair {
	private static final int TABLE_SIZE = 5;
	private static final int FIRST_POSITION = 0;
	private static final int ONE_DIMENSIONAL_ARRAY_SIZE = 25;
	private static final char THE_X = 'X';
	private static final char THE_Q = 'Q';
	private static final char THE_I = 'I';
	private static final char THE_J = 'J';
	private static final char NULL_CHARACTER = '\u0000';
	private static final int FIRST_LETTER_ROW = 0;
	private static final int FIRST_LETTER_COL = 1;
	private static final int SECOND_LETTER_ROW = 2;
	private static final int SECOND_LETTER_COL = 3;
	private static final int START_INDEX_0 = 0;
	private static final int START_INDEX_1 = 1;

	public static void main(String[] args) {
		
		char[] key = insertingKey();
		char[][] table = makingTheTableFromTheKey(key);
		showTable(table);
		char[] text = insertingText();
		System.out.println(Arrays.toString(text));
		showTheEncriptedText(table, text);
	}
	

	private static void showTheEncriptedText(char[][] table, char[] text) {
		char[] encriptedText = new char[2];
		char[] ecncriptedTextForWindow = new char[text.length];
		for (int i = START_INDEX_1; i < text.length; i += 2) {
			char first = text[i - START_INDEX_1];
			char second = text[i];
			int[] indexesFromRowsAndCols = findingTheIndexesOfTheLettersInTheTable(first, second, table);
			int firstLetterRow = indexesFromRowsAndCols[FIRST_LETTER_ROW];
			int firstLetterCol = indexesFromRowsAndCols[FIRST_LETTER_COL];
			int secondLetterRow = indexesFromRowsAndCols[SECOND_LETTER_ROW];
			int secondLetterCol = indexesFromRowsAndCols[SECOND_LETTER_COL];
			// System.out.println(Arrays.toString(indexesFromRowsAndCols));
			encriptedText = encoding(firstLetterRow, firstLetterCol, secondLetterRow, secondLetterCol, table);
			ecncriptedTextForWindow[i - START_INDEX_1] = encriptedText[START_INDEX_0];
			ecncriptedTextForWindow[i] = encriptedText[START_INDEX_1];
		}
		for (int i = START_INDEX_1; i < ecncriptedTextForWindow.length; i += 2) {
			System.out.print(ecncriptedTextForWindow[i - START_INDEX_1] + "" + ecncriptedTextForWindow[i] + " ");
		}

	}

	private static void showTable(char[][] table) {
		for (int i = START_INDEX_0; i < TABLE_SIZE; i++) {
			for (int j = START_INDEX_0; j < TABLE_SIZE; j++) {
				System.out.print(table[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static char[] insertingKey() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Put the key:");
		String keyString = scanner.nextLine();
		char[] arrayWithTheKeyBeforeTheCut = keyString.toCharArray();
		char[] key = makingTheKey(arrayWithTheKeyBeforeTheCut);
		
		return key;
	}

	private static char[] insertingText() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Put the text:");
		String textString = scanner.nextLine();
		char[] arrayWithTheTextBeforeTheCut = textString.toCharArray();
		char[] text = makingTheText(arrayWithTheTextBeforeTheCut);
		
		return text;
	}

	private static char[] makingTheKey(char[] arrayWithTheKeyBeforeTheCut) {

		// System.out.println(Arrays.toString(arrayWithTheKeyBeforeTheCut));
		// now we have the array with key , but we have to cut out the spaces
		// (if we have)
		char[] finalArrayWithKey = removingTheSpaces(arrayWithTheKeyBeforeTheCut);
		return becomeUpperLetters(finalArrayWithKey);
		// System.out.println(Arrays.toString(finalArrayWithKey));
	}

	private static char[] becomeUpperLetters(char[] finalArrayWithKey) {
		String textLowercase = String.valueOf(finalArrayWithKey);
		String textUppercase = textLowercase.toUpperCase();
		finalArrayWithKey = textUppercase.toCharArray();
		return finalArrayWithKey;
	}

	private static char[][] makingTheTableFromTheKey(char[] theArrayWithTheKey) {
		theArrayWithTheKey = becomeUpperLetters(theArrayWithTheKey);

		char[] theArrayWithTheTableOneDimensional = new char[ONE_DIMENSIONAL_ARRAY_SIZE];
		int countForTheDifferentLettersWhichPass = makingTheTableToTheKeyLength(theArrayWithTheKey,
				theArrayWithTheTableOneDimensional);

		makingTheTableAfterTheKeyLength(countForTheDifferentLettersWhichPass, theArrayWithTheTableOneDimensional);

		char[][] theArrayWithTheTable = changeTheTableFromOneDimensionalArrayToTwoDimensional(
				theArrayWithTheTableOneDimensional);

		return theArrayWithTheTable;

	}

	private static char[][] changeTheTableFromOneDimensionalArrayToTwoDimensional(
			char[] theArrayWithTheTableOneDimensional) {
		char[][] theArrayWithTheTable = new char[TABLE_SIZE][TABLE_SIZE];
		int countForTheOneDimensionalArray = 0;
		for (int i = START_INDEX_0; i < TABLE_SIZE; i++) {
			for (int j = START_INDEX_0; j < TABLE_SIZE; j++) {
				theArrayWithTheTable[i][j] = theArrayWithTheTableOneDimensional[countForTheOneDimensionalArray];
				countForTheOneDimensionalArray++;
			}
		}
		return theArrayWithTheTable;
	}

	private static char makingTheTableAfterTheKeyLength(int countForTheDifferentLettersWhichPass,
			char[] theArrayWithTheTableOneDimensional) {
		char theLetterWithTheSmallestPosition = 'A';
		int countForTheDifferentLetters;
		for (int i = countForTheDifferentLettersWhichPass; i < ONE_DIMENSIONAL_ARRAY_SIZE; i++) {
			countForTheDifferentLetters = 0;
			for (int j = START_INDEX_0; j < ONE_DIMENSIONAL_ARRAY_SIZE; j++) {
				if (theLetterWithTheSmallestPosition != theArrayWithTheTableOneDimensional[j]) {

					countForTheDifferentLetters++;

				}

			}

			if (countForTheDifferentLetters == ONE_DIMENSIONAL_ARRAY_SIZE) {
				theArrayWithTheTableOneDimensional[i] = theLetterWithTheSmallestPosition;

			} else if (countForTheDifferentLetters == ONE_DIMENSIONAL_ARRAY_SIZE - 1) {
				i--;
			}
			if (theLetterWithTheSmallestPosition != THE_I) {
				theLetterWithTheSmallestPosition++;
			} else {
				theLetterWithTheSmallestPosition += 2;
			}

		}
		return theLetterWithTheSmallestPosition;
	}

	private static int makingTheTableToTheKeyLength(char[] theArrayWithTheKey,
			char[] theArrayWithTheTableOneDimensional) {
		int countForTheDifferentLettersWhichPass = 0;
		int countForTheDifferentLetters;
		for (int i = START_INDEX_0; i < theArrayWithTheKey.length; i++) {
			countForTheDifferentLetters = 0;
			for (int j = START_INDEX_0; j < theArrayWithTheKey.length; j++) {

				if (theArrayWithTheKey[i] != theArrayWithTheTableOneDimensional[j]) {
					countForTheDifferentLetters++;

				}
				if (countForTheDifferentLetters == theArrayWithTheKey.length) {
					if (theArrayWithTheKey[i] == THE_J) {
						theArrayWithTheTableOneDimensional[countForTheDifferentLettersWhichPass] = THE_I;
						countForTheDifferentLettersWhichPass++;
					} else {
						theArrayWithTheTableOneDimensional[countForTheDifferentLettersWhichPass] = theArrayWithTheKey[i];
						countForTheDifferentLettersWhichPass++;
					}
				}
			}

		}
		return countForTheDifferentLettersWhichPass;
	}

	private static char[] makingTheText(char[] arrayWithTheTextBeforeTheCut) {

		// System.out.println(Arrays.toString(arrayWithTheKeyBeforeTheCut));
		// now we have the array with key , but we have to cut out the spaces
		// (if we have)
		char[] finalArrayWithText = removingTheSpaces(arrayWithTheTextBeforeTheCut);

		finalArrayWithText = becomeUpperLetters(finalArrayWithText);
		// division every two letters
		char[] array;
		int countForTwoSameFollowingLetters = 0;
		for (int i = START_INDEX_1; i < finalArrayWithText.length; i++) {
			if (finalArrayWithText[i - START_INDEX_1] == finalArrayWithText[i]) {
				countForTwoSameFollowingLetters++;
			}
		}
		if ((countForTwoSameFollowingLetters + finalArrayWithText.length) % 2 == 0) {
			array = new char[countForTwoSameFollowingLetters + finalArrayWithText.length];

		} else {
			array = new char[countForTwoSameFollowingLetters + finalArrayWithText.length + 1];
			array[countForTwoSameFollowingLetters + finalArrayWithText.length] = THE_Q;
		}

		array[START_INDEX_0] = finalArrayWithText[START_INDEX_0];
		int checkForTwoSameLetters = 0;
		for (int i = START_INDEX_1; i < finalArrayWithText.length; i++) {

			if (finalArrayWithText[i] != array[i - START_INDEX_1 + checkForTwoSameLetters]) {

				array[i + checkForTwoSameLetters] = finalArrayWithText[i];
			} else {

				array[i + checkForTwoSameLetters] = THE_X;
				array[i + START_INDEX_1 + checkForTwoSameLetters] = finalArrayWithText[i];
				checkForTwoSameLetters++;
			}
		}
		return array;
	}

	private static char[] removingTheSpaces(char[] arrayWithTheTextBeforeTheCut) {
		char[] arrayWithTheText = new char[arrayWithTheTextBeforeTheCut.length];

		byte countForTheIndexForTheTextWithoutTheSpaces = 0;
		for (int i = START_INDEX_0; i < arrayWithTheTextBeforeTheCut.length; i++) {
			if (arrayWithTheTextBeforeTheCut[i] != ' ') {
				arrayWithTheText[countForTheIndexForTheTextWithoutTheSpaces] = arrayWithTheTextBeforeTheCut[i];
				countForTheIndexForTheTextWithoutTheSpaces++;
			} else {
				continue;
			}

		}
		// System.out.println(Arrays.toString(arrayWithTheKey));
		char[] finalArrayWithText = new char[countForTheIndexForTheTextWithoutTheSpaces];
		for (int i = START_INDEX_0; i < countForTheIndexForTheTextWithoutTheSpaces; i++) {
			finalArrayWithText[i] = arrayWithTheText[i];
		}
		return finalArrayWithText;
	}

	private static char[] encoding(int firstLetterRow, int firstLetterCol, int secondLetterRow, int secondLetterCol,
			char[][] table) {

		char outGoingFirstLetter = NULL_CHARACTER;
		char outGoingSecondLetter = NULL_CHARACTER;

		if (firstLetterRow != secondLetterRow && firstLetterCol != secondLetterCol) {
			outGoingFirstLetter = table[firstLetterRow][secondLetterCol];
			outGoingSecondLetter = table[secondLetterRow][firstLetterCol];

		} else if (firstLetterRow == secondLetterRow && firstLetterCol != secondLetterCol) {
			if (firstLetterCol < TABLE_SIZE - 1 && secondLetterCol < TABLE_SIZE - 1) {
				outGoingFirstLetter = table[firstLetterRow][firstLetterCol + 1];
				outGoingSecondLetter = table[secondLetterRow][secondLetterCol + 1];

			} else if (firstLetterCol == TABLE_SIZE - 1 && secondLetterCol < TABLE_SIZE - 1) {
				outGoingFirstLetter = table[firstLetterRow + 1][FIRST_POSITION];
				outGoingSecondLetter = table[secondLetterRow][secondLetterCol + 1];

			} else if (firstLetterCol < TABLE_SIZE - 1 && secondLetterCol == TABLE_SIZE - 1) {
				outGoingFirstLetter = table[firstLetterRow][firstLetterCol + 1];
				outGoingSecondLetter = table[secondLetterRow + 1][FIRST_POSITION];

			} else if (firstLetterCol == TABLE_SIZE - 1 && firstLetterRow == TABLE_SIZE - 1) {
				outGoingFirstLetter = table[FIRST_POSITION][FIRST_POSITION];
			} else if (secondLetterCol == TABLE_SIZE - 1 && secondLetterRow == TABLE_SIZE - 1) {
				outGoingSecondLetter = table[FIRST_POSITION][FIRST_POSITION];

			}

		} else if (firstLetterCol == secondLetterCol && firstLetterRow != secondLetterRow) {
			if (firstLetterRow < TABLE_SIZE - 1 && secondLetterRow < TABLE_SIZE - 1) {
				outGoingFirstLetter = table[firstLetterRow + 1][firstLetterCol];
				outGoingSecondLetter = table[secondLetterRow + 1][secondLetterCol];

			} else if (firstLetterRow == TABLE_SIZE - 1 && secondLetterRow < TABLE_SIZE - 1) {
				outGoingFirstLetter = table[0][firstLetterCol + 1];
				outGoingSecondLetter = table[secondLetterRow + 1][secondLetterCol];

			} else if (firstLetterRow < TABLE_SIZE - 1 && secondLetterRow == TABLE_SIZE - 1) {
				outGoingFirstLetter = table[firstLetterRow + 1][firstLetterCol];
				outGoingSecondLetter = table[FIRST_POSITION][secondLetterCol + 1];

			} else if (firstLetterCol == TABLE_SIZE - 1 && firstLetterRow == TABLE_SIZE - 1) {
				outGoingFirstLetter = table[FIRST_POSITION][FIRST_POSITION];
			} else if (secondLetterCol == TABLE_SIZE - 1 && secondLetterRow == TABLE_SIZE - 1) {
				outGoingSecondLetter = table[FIRST_POSITION][FIRST_POSITION];

			}

		}
		char[] OutGoingText = new char[2];
		OutGoingText[START_INDEX_0] = outGoingFirstLetter;
		OutGoingText[START_INDEX_1] = outGoingSecondLetter;
		return OutGoingText;

	}

	private static int[] findingTheIndexesOfTheLettersInTheTable(char firstLetter, char secondLetter, char[][] table) {

		byte indexRowFirstLetter = START_INDEX_0;
		byte indexColFirstLetter = START_INDEX_0;
		byte indexRowSecondLetter = START_INDEX_0;
		byte indexColSecondLetter = START_INDEX_0;
		int[] indexesFromRowsAndCols = new int[4];
		for (int rows = START_INDEX_0; rows < table.length; rows++) {
			for (int cols = START_INDEX_0; cols < table.length; cols++) {
				if (firstLetter == table[rows][cols]) {
					indexRowFirstLetter = (byte) rows;
					indexColFirstLetter = (byte) cols;
				}
				if (secondLetter == table[rows][cols]) {
					indexRowSecondLetter = (byte) rows;
					indexColSecondLetter = (byte) cols;
				}
			}
		}

		indexesFromRowsAndCols[FIRST_LETTER_ROW] = indexRowFirstLetter;
		indexesFromRowsAndCols[FIRST_LETTER_COL] = indexColFirstLetter;
		indexesFromRowsAndCols[SECOND_LETTER_ROW] = indexRowSecondLetter;
		indexesFromRowsAndCols[SECOND_LETTER_COL] = indexColSecondLetter;

		return indexesFromRowsAndCols;
	}

}
