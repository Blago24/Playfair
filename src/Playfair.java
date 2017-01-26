import java.util.Arrays;

public class Playfair {

	public static final int TABLE_SIZE = 5;
	public static final int FIRST_POSITION = 0;
	public static final int ONE_DIMENSIONAL_ARRAY_SIZE = 25;
	public static final char THE_X = 'X';
	public static final char THE_Q = 'Q';
	public static final char THE_I = 'I';
	public static final char THE_J = 'J';
	public static final int FIRST_LETTER_ROW = 0;
	public static final int FIRST_LETTER_COL = 1;
	public static final int SECOND_LETTER_ROW = 2;
	public static final int SECOND_LETTER_COL = 3;
	public static final int START_INDEX_0 = 0;
	public static final int START_INDEX_1 = 1;

	/*
	 * We need this global variables , because use them is three methods
	 */
	public static char outGoingFirstLetter = '\u0000';
	public static char outGoingSecondLetter = '\u0000';

	public static void main(String[] args) {
		
	}

	/*
	 * Adding the key
	 */
	public static char[] insertingKey(String inputFromWindow) {
		
		String keyString = inputFromWindow;
		char[] arrayWithTheKeyBeforeTheCut = keyString.toCharArray();
		char[] key = makingTheKey(arrayWithTheKeyBeforeTheCut);
		return key;
	}

	/*
	 * This method is calling two other methods ,which are removing the spaces
	 * and change everything to be with upper letters
	 */
	public static char[] makingTheKey(char[] arrayWithTheKeyBeforeTheCut) {

		// now we have the array with key , but we have to cut out the spaces
		// (if we have)
		char[] finalArrayWithKey = removingTheSpaces(arrayWithTheKeyBeforeTheCut);
		return becomeUpperLetters(finalArrayWithKey);

	}

	/*
	 * First we remove the spaces than we make the array without them
	 */
	public static char[] removingTheSpaces(char[] arrayWithTheTextBeforeTheCut) {
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
	public static char[] makeNewArrayWithoutSpaces(char[] arrayWithTheText,
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
	public static char[] becomeUpperLetters(char[] finalArrayWithKey) {
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
	public static char[][] makingTheTableFromTheKey(char[] theArrayWithTheKey) {
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
	public static int makingTheTableToTheKeyLength(char[] theArrayWithTheKey,
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
	public static char makingTheTableAfterTheKeyLength(int countForTheDifferentLettersWhichPass,
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
	public static char[][] changeTheTableFromOneDimensionalArrayToTwoDimensional(
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
	public static char[] insertingText(String inputFromWindow) {
		String textString = inputFromWindow;
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
	public static char[] makingTheText(char[] arrayWithTheTextBeforeTheCut) {

		// now we have the array with key , but we have to cut out the spaces
		// (if we have)
		char[] finalArrayWithText = removingTheSpaces(arrayWithTheTextBeforeTheCut);

		finalArrayWithText = becomeUpperLetters(finalArrayWithText);
		System.out.println(Arrays.toString(finalArrayWithText));
		finalArrayWithText = removeJ(finalArrayWithText);
		System.out.println(Arrays.toString(finalArrayWithText));
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

	private static char[] removeJ(char[] finalArrayWithText) {
		for (int row = 0; row < finalArrayWithText.length; row++) {
			if(finalArrayWithText[row]=='J'){
				finalArrayWithText[row]='I';
			}
		}
		return finalArrayWithText;
	}

	/*
	 * we are finding the Column and Row of the first and the second letter
	 */
	public static int[] findingTheIndexesOfTheLettersInTheTable(char firstLetter, char secondLetter, char[][] table) {

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

	public static char[] encoding(int firstLetterRow, int firstLetterCol, int secondLetterRow, int secondLetterCol,
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

	/*
	 * we just have to change the positions of columns and rows
	 */
	public static void encodeDifferentRowsAndDifferentCols(int firstLetterRow, int firstLetterCol, int secondLetterRow,
			int secondLetterCol, char[][] table) {
		outGoingFirstLetter = table[firstLetterRow][secondLetterCol];
		outGoingSecondLetter = table[secondLetterRow][firstLetterCol];
	}

	/*
	 * there are tree main situations ONE-when the columns of the two letters
	 * are under the TABLE_SIZE - 1(under 4) we add 1 TWO- when the first letter
	 * column equals TABLE_SIZE - 1 and the second letter column is under the
	 * TABLE_SIZE - 1(under 4) But now we have two situations : if the first
	 * letter row equals TABLE_SIZE - 1 first letter positions become [0][0] and
	 * the second letter column+=1; if the first letter row is under TABLE_SIZE
	 * - 1 only the first letter column become 0; The same is for the COLUMNS
	 */
	public static void encodeSameRowsAndDifferentCols(int firstLetterRow, int firstLetterCol, int secondLetterRow,
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
	 * The comment is the same as the comment for the
	 * encodeSameRowsAndDifferentCols(), but here we just have to change the
	 * positions if the rows and columns
	 */
	public static void encodeDifferentRowsAndSameCols(int firstLetterRow, int firstLetterCol, int secondLetterRow,
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

	/*
	 * returning letters two by two
	 */
	public static char[] putTheLettersInArray(char outGoingFirstLetter, char outGoingSecondLetter) {
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
	public static String makeTheEncriptedText(char[][] table, char[] text) {
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
	public static String makeTheEncodedTextAsString(char[] ecncriptedTextForWindow) {
		String encodedText = "";
		for (int i = START_INDEX_1; i < ecncriptedTextForWindow.length; i += 2) {
			encodedText += ecncriptedTextForWindow[i - START_INDEX_1] + "" + ecncriptedTextForWindow[i] + " ";
		}
		return encodedText;
	}

	/*
	 * this functions shows the table in the console
	 */
	public static String showTable(char[][] table) {
		StringBuilder finaleTable = new StringBuilder();
		
		for (int rows = START_INDEX_0; rows < TABLE_SIZE; rows++) {
			for (int cols = START_INDEX_0; cols < TABLE_SIZE; cols++) {
				finaleTable.append(String.valueOf(table[rows][cols]));
				finaleTable.append(" ");
				
			}
			finaleTable.append("\n");
		}
		
		return finaleTable.toString();
	}

	/*
	 * this functions shows the encoded text in the console
	 */
	public static void showTheEncriptedText(String encodedText) {
		System.out.println("The encripted text is :");
		System.out.println(encodedText);

	}
}
