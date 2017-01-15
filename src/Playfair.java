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
	private static final int FIRST_LETTER_ROW = 0;
	private static final int FIRST_LETTER_COL = 1;
	private static final int SECOND_LETTER_ROW = 2;
	private static final int SECOND_LETTER_COL = 3;
	private static final int START_INDEX_0 = 0;
	private static final int START_INDEX_1 = 1;

	/*
	 * We need this global variables , because use them is three methods
	 */
	private static char outGoingFirstLetter = '\u0000';
	private static char outGoingSecondLetter = '\u0000';

	public static void main(String[] args) {
		startTheProgram();
	}

	/*
	 * That method call all of the other methods
	 */
	private static void startTheProgram() {
		char[] key = insertingKey();
		char[][] table = makingTheTableFromTheKey(key);
		showTable(table);
		char[] text = insertingText();
		String encodedText = makeTheEncriptedText(table, text);
		showTheEncriptedText(encodedText);
	}

	/*
	 * Adding the key
	 */
	private static char[] insertingKey() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Put the key:");
		String keyString = scanner.nextLine();
		char[] arrayWithTheKeyBeforeTheCut = keyString.toCharArray();
		char[] key = makingTheKey(arrayWithTheKeyBeforeTheCut);
		return key;
	}

	/*
	 * This method is calling two other methods ,which are removing the spaces
	 * and change everything to be with upper letters
	 */
	private static char[] makingTheKey(char[] arrayWithTheKeyBeforeTheCut) {

		// now we have the array with key , but we have to cut out the spaces
		// (if we have)
		char[] finalArrayWithKey = removingTheSpaces(arrayWithTheKeyBeforeTheCut);
		return becomeUpperLetters(finalArrayWithKey);

	}

	/*
	 * First we remove the spaces than we make the array without them
	 */
	private static char[] removingTheSpaces(char[] arrayWithTheTextBeforeTheCut) {
		char[] arrayWithTheText = new char[arrayWithTheTextBeforeTheCut.length];
		byte countForTheIndexForTheTextWithoutTheSpaces = 0;
		for (int row = START_INDEX_0; row < arrayWithTheTextBeforeTheCut.length; row++) {
			if (arrayWithTheTextBeforeTheCut[row] != ' ') {
				arrayWithTheText[countForTheIndexForTheTextWithoutTheSpaces] = arrayWithTheTextBeforeTheCut[row];
				countForTheIndexForTheTextWithoutTheSpaces++;
			} else {
				continue;
			}
		}
		char[] finalArrayWithText = makeNewArrayWithoutSpaces(arrayWithTheText,
				countForTheIndexForTheTextWithoutTheSpaces);
		return finalArrayWithText;
	}

	/*
	 * Now we are able to create new array without the spaces
	 */
	private static char[] makeNewArrayWithoutSpaces(char[] arrayWithTheText,
			byte countForTheIndexForTheTextWithoutTheSpaces) {
		char[] finalArrayWithText = new char[countForTheIndexForTheTextWithoutTheSpaces];
		for (int row = START_INDEX_0; row < countForTheIndexForTheTextWithoutTheSpaces; row++) {
			finalArrayWithText[row] = arrayWithTheText[row];
		}
		return finalArrayWithText;
	}

	/*
	 * This is making everything with upper letters
	 */
	private static char[] becomeUpperLetters(char[] finalArrayWithKey) {
		String textLowercase = String.valueOf(finalArrayWithKey);
		String textUppercase = textLowercase.toUpperCase();
		finalArrayWithKey = textUppercase.toCharArray();
		return finalArrayWithKey;
	}

	/*
	 * all we need for this method is the key the method works in two parts
	 * 1-From the first letter to the length if the key (after removing the
	 * repeating letters) 2-adding the other letters from the alphabet without
	 * repeating
	 */
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

	/*
	 * Checking for repeating letters and removing them Replace the J with I
	 */
	private static int makingTheTableToTheKeyLength(char[] theArrayWithTheKey,
			char[] theArrayWithTheTableOneDimensional) {
		int countForTheDifferentLettersWhichPass = 0;
		int countForTheDifferentLetters;
		for (int rows = START_INDEX_0; rows < theArrayWithTheKey.length; rows++) {
			countForTheDifferentLetters = 0;
			for (int cols = START_INDEX_0; cols < theArrayWithTheKey.length; cols++) {
				if (theArrayWithTheKey[rows] != theArrayWithTheTableOneDimensional[cols]) {
					countForTheDifferentLetters++;
				}
				if (countForTheDifferentLetters == theArrayWithTheKey.length) {
					if (theArrayWithTheKey[rows] == THE_J) {
						theArrayWithTheTableOneDimensional[countForTheDifferentLettersWhichPass] = THE_I;
						countForTheDifferentLettersWhichPass++;
					} else {
						theArrayWithTheTableOneDimensional[countForTheDifferentLettersWhichPass] = theArrayWithTheKey[rows];
						countForTheDifferentLettersWhichPass++;
					}
				}
			}
		}
		return countForTheDifferentLettersWhichPass;
	}

	/*
	 * Checking which letters do we have and adding the others that we missed If
	 * we find I and place it we skip J
	 */
	private static char makingTheTableAfterTheKeyLength(int countForTheDifferentLettersWhichPass,
			char[] theArrayWithTheTableOneDimensional) {
		char theLetterWithTheSmallestPosition = 'A';
		int countForTheDifferentLetters;
		for (int rows = countForTheDifferentLettersWhichPass; rows < ONE_DIMENSIONAL_ARRAY_SIZE; rows++) {
			countForTheDifferentLetters = 0;
			for (int cols = START_INDEX_0; cols < ONE_DIMENSIONAL_ARRAY_SIZE; cols++) {
				if (theLetterWithTheSmallestPosition != theArrayWithTheTableOneDimensional[cols]) {
					countForTheDifferentLetters++;
				}
			}
			if (countForTheDifferentLetters == ONE_DIMENSIONAL_ARRAY_SIZE) {
				theArrayWithTheTableOneDimensional[rows] = theLetterWithTheSmallestPosition;

			} else if (countForTheDifferentLetters == ONE_DIMENSIONAL_ARRAY_SIZE - 1) {
				rows--;
			}
			if (theLetterWithTheSmallestPosition != THE_I) {
				theLetterWithTheSmallestPosition++;
			} else {
				theLetterWithTheSmallestPosition += 2;
			}
		}
		return theLetterWithTheSmallestPosition;
	}

	/*
	 * To this moment our array was one dimensional , but we need it to be two
	 * dimensional so that method is doing this for us
	 */
	private static char[][] changeTheTableFromOneDimensionalArrayToTwoDimensional(
			char[] theArrayWithTheTableOneDimensional) {
		char[][] theArrayWithTheTable = new char[TABLE_SIZE][TABLE_SIZE];
		int countForTheOneDimensionalArray = 0;
		for (int rows = START_INDEX_0; rows < TABLE_SIZE; rows++) {
			for (int cols = START_INDEX_0; cols < TABLE_SIZE; cols++) {
				theArrayWithTheTable[rows][cols] = theArrayWithTheTableOneDimensional[countForTheOneDimensionalArray];
				countForTheOneDimensionalArray++;
			}
		}
		return theArrayWithTheTable;
	}

	/*
	 * read the text from the console
	 */
	private static char[] insertingText() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Put the text:");
		String textString = scanner.nextLine();
		char[] arrayWithTheTextBeforeTheCut = textString.toCharArray();
		char[] text = makingTheText(arrayWithTheTextBeforeTheCut);

		return text;
	}

	/*
	 * here we separate the text if we find two same letter next to each other
	 * we add X between them that means that we will make the final array bigger
	 * if we have to same letter , the array will grow with one field and when
	 * we add that X we have to skip the next compare For that reason we use
	 * checkForTwoSameLetters variable it grow every time we add X
	 */
	private static char[] makingTheText(char[] arrayWithTheTextBeforeTheCut) {

		// now we have the array with key , but we have to cut out the spaces
		// (if we have)
		char[] finalArrayWithText = removingTheSpaces(arrayWithTheTextBeforeTheCut);

		finalArrayWithText = becomeUpperLetters(finalArrayWithText);
		// division every two letters
		char[] array;
		int countForTwoSameFollowingLetters = 0;
		for (int row = START_INDEX_1; row < finalArrayWithText.length; row++) {
			if (finalArrayWithText[row - START_INDEX_1] == finalArrayWithText[row]) {
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
		for (int row = START_INDEX_1; row < finalArrayWithText.length; row++) {

			if (finalArrayWithText[row] != array[row - START_INDEX_1 + checkForTwoSameLetters]) {

				array[row + checkForTwoSameLetters] = finalArrayWithText[row];
			} else {

				array[row + checkForTwoSameLetters] = THE_X;
				array[row + START_INDEX_1 + checkForTwoSameLetters] = finalArrayWithText[row];
				checkForTwoSameLetters++;
			}
		}
		return array;
	}

	/*
	 * we are finding the Column and Row of the first and the second letter
	 */
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

	/*
	 * we have 3 cases 1-when the row of the first letter is different from the
	 * row of the second letter and the column of the first letter is different
	 * from the column of the second letter 2-when the row of the first letter
	 * is the same as the row of the second letter and the column of the first
	 * letter is different from the column of the second letter 3-when the row
	 * of the first letter is different from the row of the second letter and
	 * the column of the first letter is the same as the column of the second
	 * letter
	 */

	private static char[] encoding(int firstLetterRow, int firstLetterCol, int secondLetterRow, int secondLetterCol,
			char[][] table) {

		if (firstLetterRow != secondLetterRow && firstLetterCol != secondLetterCol) {
			encodeDifferentRowsAndDifferentCols(firstLetterRow, firstLetterCol, secondLetterRow, secondLetterCol,
					table);

		} else if (firstLetterRow == secondLetterRow && firstLetterCol != secondLetterCol) {
			encodeSameRowsAndDifferentCols(firstLetterRow, firstLetterCol, secondLetterRow, secondLetterCol, table);

		} else if (firstLetterRow != secondLetterRow && firstLetterCol == secondLetterCol) {
			encodeDifferentRowsAndSameCols(firstLetterRow, firstLetterCol, secondLetterRow, secondLetterCol, table);

		}
		char[] OutGoingText = putTheLettersInArray(outGoingFirstLetter, outGoingSecondLetter);
		return OutGoingText;

	}

	private static void encodeDifferentRowsAndSameCols(int firstLetterRow, int firstLetterCol, int secondLetterRow,
			int secondLetterCol, char[][] table) {
		if (firstLetterRow < TABLE_SIZE - 1 && secondLetterRow < TABLE_SIZE - 1) {
			outGoingFirstLetter = table[firstLetterRow + 1][firstLetterCol];
			outGoingSecondLetter = table[secondLetterRow + 1][secondLetterCol];

		} else if (firstLetterRow == TABLE_SIZE - 1 && secondLetterRow < TABLE_SIZE - 1) {
			if (firstLetterCol == TABLE_SIZE - 1) {
				outGoingFirstLetter = table[FIRST_POSITION][FIRST_POSITION];
				outGoingSecondLetter = table[secondLetterRow + 1][secondLetterCol];
			} else {
				outGoingFirstLetter = table[FIRST_POSITION][firstLetterCol + 1];
				outGoingSecondLetter = table[secondLetterRow + 1][secondLetterCol];
			}

		} else if (firstLetterRow < TABLE_SIZE - 1 && secondLetterRow == TABLE_SIZE - 1) {

			if (secondLetterCol == TABLE_SIZE - 1) {
				outGoingFirstLetter = table[firstLetterRow + 1][firstLetterCol];
				outGoingSecondLetter = table[FIRST_POSITION][FIRST_POSITION];
			} else {
				outGoingFirstLetter = table[firstLetterRow + 1][firstLetterCol];
				outGoingSecondLetter = table[FIRST_POSITION][secondLetterCol + 1];
			}

		}
	}

	private static void encodeSameRowsAndDifferentCols(int firstLetterRow, int firstLetterCol, int secondLetterRow,
			int secondLetterCol, char[][] table) {
		if (firstLetterCol < TABLE_SIZE - 1 && secondLetterCol < TABLE_SIZE - 1) {
			outGoingFirstLetter = table[firstLetterRow][firstLetterCol + 1];
			outGoingSecondLetter = table[secondLetterRow][secondLetterCol + 1];

		} else if (firstLetterCol == TABLE_SIZE - 1 && secondLetterCol < TABLE_SIZE - 1) {
			if (firstLetterRow == TABLE_SIZE - 1) {
				outGoingFirstLetter = table[FIRST_POSITION][FIRST_POSITION];
				outGoingSecondLetter = table[secondLetterRow][secondLetterCol + 1];
			} else {
				outGoingFirstLetter = table[firstLetterRow + 1][FIRST_POSITION];
				outGoingSecondLetter = table[secondLetterRow][secondLetterCol + 1];
			}

		} else if (firstLetterCol < TABLE_SIZE - 1 && secondLetterCol == TABLE_SIZE - 1) {
			if (firstLetterRow == TABLE_SIZE - 1) {
				outGoingFirstLetter = table[firstLetterRow][firstLetterCol + 1];
				outGoingSecondLetter = table[FIRST_POSITION][FIRST_POSITION];
			} else {
				outGoingFirstLetter = table[firstLetterRow][firstLetterCol + 1];
				outGoingSecondLetter = table[secondLetterRow + 1][FIRST_POSITION];
			}

		}
	}

	/*
	 * we just have to change the positions of columns and rows
	 */
	private static void encodeDifferentRowsAndDifferentCols(int firstLetterRow, int firstLetterCol, int secondLetterRow,
			int secondLetterCol, char[][] table) {
		outGoingFirstLetter = table[firstLetterRow][secondLetterCol];
		outGoingSecondLetter = table[secondLetterRow][firstLetterCol];
	}

	/*
	 * returning letters two by two
	 */
	private static char[] putTheLettersInArray(char outGoingFirstLetter, char outGoingSecondLetter) {
		char[] OutGoingText = new char[2];
		OutGoingText[START_INDEX_0] = outGoingFirstLetter;
		OutGoingText[START_INDEX_1] = outGoingSecondLetter;
		return OutGoingText;
	}

	/*
	 * we take letters two by two and find their indexes in the table then we
	 * encode them after that we send them in function that collects all of the
	 * letters and make string of them with the final answer
	 */
	private static String makeTheEncriptedText(char[][] table, char[] text) {
		char[] encriptedText = new char[2];
		char[] ecncriptedTextForWindow = new char[text.length];
		for (int row = START_INDEX_1; row < text.length; row += 2) {
			char first = text[row - START_INDEX_1];
			char second = text[row];
			int[] indexesFromRowsAndCols = findingTheIndexesOfTheLettersInTheTable(first, second, table);

			int firstLetterRow = indexesFromRowsAndCols[FIRST_LETTER_ROW];
			int firstLetterCol = indexesFromRowsAndCols[FIRST_LETTER_COL];
			int secondLetterRow = indexesFromRowsAndCols[SECOND_LETTER_ROW];
			int secondLetterCol = indexesFromRowsAndCols[SECOND_LETTER_COL];

			// System.out.println(Arrays.toString(indexesFromRowsAndCols));
			encriptedText = encoding(firstLetterRow, firstLetterCol, secondLetterRow, secondLetterCol, table);
			ecncriptedTextForWindow[row - START_INDEX_1] = encriptedText[START_INDEX_0];
			ecncriptedTextForWindow[row] = encriptedText[START_INDEX_1];
		}
		String encodedText = makeTheEncodedTextAsString(ecncriptedTextForWindow);
		return encodedText;
	}
/*
 * making the final string of the encoded text
 */
	private static String makeTheEncodedTextAsString(char[] ecncriptedTextForWindow) {
		String encodedText = "";
		for (int i = START_INDEX_1; i < ecncriptedTextForWindow.length; i += 2) {
			encodedText += ecncriptedTextForWindow[i - START_INDEX_1] + "" + ecncriptedTextForWindow[i] + " ";
		}
		return encodedText;
	}
 /*
  * this functions shows the table in the console
  */
	private static void showTable(char[][] table) {
		for (int rows = START_INDEX_0; rows < TABLE_SIZE; rows++) {
			for (int cols = START_INDEX_0; cols < TABLE_SIZE; cols++) {
				System.out.print(table[rows][cols] + " ");
			}
			System.out.println();
		}
	}
	/*
	  * this functions shows the encoded text in the console
	  */
	private static void showTheEncriptedText(String encodedText) {
		System.out.println("The encripted text is :");
		System.out.println(encodedText);

	}
}
