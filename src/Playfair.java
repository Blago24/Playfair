import java.util.Arrays;
import java.util.Scanner;

public class Playfair {
// Testing the commit
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Put the key:");
		char[] key = insertingKey(scanner);
		//System.out.println(Arrays.toString(key));
		char[][] table = makingTheTableFromTheKey(key);

		showTable(table);
		System.out.print("Put the text:");
		char[] text = insertingText(scanner);
		System.out.println(Arrays.toString(text));
		showTheEncriptedText(table, text);
	}
	private static void showTheEncriptedText(char[][] table, char[] text) {
		char[] encriptedText= new char [2];
		char[] ecncriptedTextForWindow=new char[text.length];
			for (int i = 1; i < text.length; i += 2) {
				char first = text[i - 1];
				char second = text[i];
				int[] indexesFromRowsAndCols = findingTheIndexesOfTheLettersInTheTable(first, second,table);
				int firstLetterRow = indexesFromRowsAndCols[0];
				int firstLetterCol = indexesFromRowsAndCols[1];
				int secondLetterRow = indexesFromRowsAndCols[2];
				int secondLetterCol = indexesFromRowsAndCols[3];
				//System.out.println(Arrays.toString(indexesFromRowsAndCols));
				encriptedText= encoding(firstLetterRow, firstLetterCol, secondLetterRow, secondLetterCol,table);
				ecncriptedTextForWindow[i-1]=encriptedText[0];
				ecncriptedTextForWindow[i]=encriptedText[1];
			}
			for (int i = 1; i < ecncriptedTextForWindow.length; i+=2) {
				System.out.print(ecncriptedTextForWindow[i-1]+""+ecncriptedTextForWindow[i]+" ");
			}
			
	}
	private static void showTable(char[][] table) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(table[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static char[] insertingKey(Scanner scanner) {
		
		String keyString = scanner.nextLine();
		char[] arrayWithTheKeyBeforeTheCut = keyString.toCharArray();
		char[] key = makingTheKey(arrayWithTheKeyBeforeTheCut);
		return key;
	}
private static char[] insertingText(Scanner scanner) {
		
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
		int countForTheDifferentLettersWhichPass = 0;
		char theLetterWithTheSmallestPosition = 'A';
		int countForTheDifferentLetters;

		char[] theArrayWithTheTableOneDimensional = new char[25];
		for (int i = 0; i < theArrayWithTheKey.length; i++) {
			countForTheDifferentLetters = 0;
			for (int j = 0; j < theArrayWithTheKey.length; j++) {

				if (theArrayWithTheKey[i] != theArrayWithTheTableOneDimensional[j]) {
					countForTheDifferentLetters++;

				}
				if (countForTheDifferentLetters == theArrayWithTheKey.length) {
					if (theArrayWithTheKey[i] == 'J') {
						theArrayWithTheTableOneDimensional[countForTheDifferentLettersWhichPass] = 'I';
						countForTheDifferentLettersWhichPass++;
					} else {
						theArrayWithTheTableOneDimensional[countForTheDifferentLettersWhichPass] = theArrayWithTheKey[i];
						countForTheDifferentLettersWhichPass++;
					}
				}
			}

			// System.out.println(countForTheDifferentLetters);
		}
		for (int i = countForTheDifferentLettersWhichPass; i < 25; i++) {
			countForTheDifferentLetters = 0;
			for (int j = 0; j < 25; j++) {
				if (theLetterWithTheSmallestPosition != theArrayWithTheTableOneDimensional[j]) {

					countForTheDifferentLetters++;
					// System.out.print(countForTheDifferentLetters);
				}

			}
			// System.out.print(" " + theLetterWithTheSmallestPosition);
			// System.out.println();
			if (countForTheDifferentLetters == 25) {
				theArrayWithTheTableOneDimensional[i] = theLetterWithTheSmallestPosition;

			} else if (countForTheDifferentLetters == 24) {
				i--;
			}
			if (theLetterWithTheSmallestPosition != 'I') {
				theLetterWithTheSmallestPosition++;
			} else {
				theLetterWithTheSmallestPosition += 2;
			}

		}
		char[][] theArrayWithTheTable = new char[5][5];
		int countForTheOneDimensionalArray = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				theArrayWithTheTable[i][j] = theArrayWithTheTableOneDimensional[countForTheOneDimensionalArray];
				countForTheOneDimensionalArray++;
			}
		}
		// System.out.println(Arrays.toString(theArrayWithTheTableOneDimensional));

		return theArrayWithTheTable;
		// System.out.println(Arrays.toString(theArrayWithTheTableOneDimensional));
	}

	private static char[] makingTheText(char[] arrayWithTheTextBeforeTheCut) {

		// System.out.println(Arrays.toString(arrayWithTheKeyBeforeTheCut));
		// now we have the array with key , but we have to cut out the spaces
		// (if we have)
		char[] finalArrayWithText = removingTheSpaces(arrayWithTheTextBeforeTheCut);
		
		finalArrayWithText =becomeUpperLetters(finalArrayWithText);
		// division every two letters
		char[] array;
		int countForTwoSameFollowingLetters = 0;
		for (int i = 1; i < finalArrayWithText.length; i++) {
			if (finalArrayWithText[i - 1] == finalArrayWithText[i]) {
				countForTwoSameFollowingLetters++;
			}
		}
		if ((countForTwoSameFollowingLetters + finalArrayWithText.length) % 2 == 0) {
			array = new char[countForTwoSameFollowingLetters + finalArrayWithText.length];

		} else {
			array = new char[countForTwoSameFollowingLetters + finalArrayWithText.length + 1];
			array[countForTwoSameFollowingLetters + finalArrayWithText.length] = 'Q';
		}

		array[0] = finalArrayWithText[0];
		int checkForTwoSameLetters = 0;
		for (int i = 1; i < finalArrayWithText.length; i++) {

			if (finalArrayWithText[i] != array[i - 1 + checkForTwoSameLetters]) {

				array[i + checkForTwoSameLetters] = finalArrayWithText[i];
			} else {

				array[i + checkForTwoSameLetters] = 'X';
				array[i + 1 + checkForTwoSameLetters] = finalArrayWithText[i];
				checkForTwoSameLetters++;
			}
		}
		return array;
	}

	private static char[] removingTheSpaces(char[] arrayWithTheTextBeforeTheCut) {
		char[] arrayWithTheText = new char[arrayWithTheTextBeforeTheCut.length];

		byte countForTheIndexForTheTextWithoutTheSpaces = 0;
		for (int i = 0; i < arrayWithTheTextBeforeTheCut.length; i++) {
			if (arrayWithTheTextBeforeTheCut[i] != ' ') {
				arrayWithTheText[countForTheIndexForTheTextWithoutTheSpaces] = arrayWithTheTextBeforeTheCut[i];
				countForTheIndexForTheTextWithoutTheSpaces++;
			} else {
				continue;
			}

		}
		// System.out.println(Arrays.toString(arrayWithTheKey));
		char[] finalArrayWithText = new char[countForTheIndexForTheTextWithoutTheSpaces];
		for (int i = 0; i < countForTheIndexForTheTextWithoutTheSpaces; i++) {
			finalArrayWithText[i] = arrayWithTheText[i];
		}
		return finalArrayWithText;
	}
	private static char[] encoding(int firstLetterRow, int firstLetterCol, int secondLetterRow, int secondLetterCol,char [][] table) {
		
		char outGoingFirstLetter='\u0000';
		char outGoingSecondLetter='\u0000';

		if (firstLetterRow != secondLetterRow && firstLetterCol != secondLetterCol) {
			outGoingFirstLetter = table[firstLetterRow][secondLetterCol];
			outGoingSecondLetter = table[secondLetterRow][firstLetterCol];

			

		} else if (firstLetterRow == secondLetterRow && firstLetterCol != secondLetterCol) {
			if (firstLetterCol < 4 && secondLetterCol < 4) {
				outGoingFirstLetter = table[firstLetterRow][firstLetterCol + 1];
				outGoingSecondLetter = table[secondLetterRow][secondLetterCol + 1];
				
			}else if (firstLetterCol == 4 && secondLetterCol < 4) {
				outGoingFirstLetter = table[firstLetterRow + 1][0];
				outGoingSecondLetter = table[secondLetterRow][secondLetterCol + 1];
				
			} else if (firstLetterCol < 4 && secondLetterCol == 4) {
				outGoingFirstLetter = table[firstLetterRow][firstLetterCol + 1];
				outGoingSecondLetter = table[secondLetterRow + 1][0];
				
			}
			else if(firstLetterCol==4&&firstLetterRow==4){
				outGoingFirstLetter = table[0][0];
			}else if(secondLetterCol==4&& secondLetterRow==4){
				outGoingSecondLetter=table[0][0];
				
			}

		} else if (firstLetterCol == secondLetterCol &&firstLetterRow != secondLetterRow) {
			if (firstLetterRow < 4 && secondLetterRow < 4) {
				outGoingFirstLetter = table[firstLetterRow + 1][firstLetterCol];
				outGoingSecondLetter = table[secondLetterRow + 1][secondLetterCol];
				
			} else if (firstLetterRow == 4 && secondLetterRow < 4) {
				outGoingFirstLetter = table[0][firstLetterCol + 1];
				outGoingSecondLetter = table[secondLetterRow + 1][secondLetterCol];
				
			} else if (firstLetterRow < 4 && secondLetterRow == 4) {
				outGoingFirstLetter = table[firstLetterRow + 1][firstLetterCol];
				outGoingSecondLetter = table[0][secondLetterCol+1];
				
			}
			else if(firstLetterCol==4&&firstLetterRow==4){
				outGoingFirstLetter = table[0][0];
			}else if(secondLetterCol==4&& secondLetterRow==4){
				outGoingSecondLetter=table[0][0];
				
			}

		}
		char[] OutGoingText=new char [2];
		OutGoingText[0]=outGoingFirstLetter;
		OutGoingText[1]=outGoingSecondLetter;
		return OutGoingText;

	}

	private static int[] findingTheIndexesOfTheLettersInTheTable(char firstLetter, char secondLetter, char[][] table) {
		
		byte indexRowFirstLetter = 0;
		byte indexColFirstLetter = 0;
		byte indexRowSecondLetter = 0;
		byte indexColSecondLetter = 0;
		int[] indexesFromRowsAndCols = new int[4];
		for (int rows = 0; rows < table.length; rows++) {
			for (int cols = 0; cols < table.length; cols++) {
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

		indexesFromRowsAndCols[0] = indexRowFirstLetter;
		indexesFromRowsAndCols[1] = indexColFirstLetter;
		indexesFromRowsAndCols[2] = indexRowSecondLetter;
		indexesFromRowsAndCols[3] = indexColSecondLetter;

		return indexesFromRowsAndCols;
	}

}
