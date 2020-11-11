import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.util.Scanner;
import java.util.Random;

/**
 * A program intended to inspire song writers who have an idea for a song, but have only discovered a few applicable words before being stuck with writer's block.
 * The program will ask the user details regarding what ideas they have so far: such as nouns, verbs, words that rhyme, full phrases, etc.
 * With this information, the program will shuffle the words randomly and then print out proper sentences according to English grammatical rules as best as it can.
 * From there, the user will then interact with the program by purposefully shuffling certain words or statements around until the user is satisfied with the skeleton.
 * Once the user is satisfied, the program will then transfer the text into a file so that later it may be accessed so that the user may continue their work in progress.
 * Please note that this program pays homage to David Bowie and his original analog "cut-up technique" in which he would put possible lyrics in a hat, shake it up, and
 * then pick them out and put them together and then rearrange them. Later, during the 90s, he coded this technique into what he called the "Verbasizer".
 * Unfortunately, this program was not made available to the public, and since technology has changed so much since then, I felt, as a programmer and music enthusiast, that
 * it would be a crime not to recreate and revamp it for the modern musician.
 * 
 * Currently I am studying more coding in my free time to implement this program with essential features like a GUI and machine learning.
 */

/**
 * @McKenna Hollinger 
 */
public class Verbasizer {

	/**
	*Boolean method that prompts user if they have a previous file they want to open by asking them to enter Y or N. Upon input, program checks if the string inputed has a length of 0. 
	*If the length is greater than 0, the first letter of whatever entered is converted into a character. If the character is Y then the program will return true, that user wants to open file.
	*If the character is N then the program will return false. If the character is neither or the user entered nothing then an error message will print and will ask user again until they input Y or N.   
	*/
	public static boolean lookingForFile(Scanner in) {
		boolean fileWanted = false;
		char userChoice;
		System.out.print("Do you have a previous draft that you would like to continue? [Y/N]: ");
		String wantFile = in.nextLine();
		if (wantFile.length() != 0) {
			userChoice = wantFile.toUpperCase().charAt(0); // converts String to capital character
			if (userChoice == 'y' || userChoice == 'Y') {
				fileWanted = true;
			} else if (userChoice == 'n' || userChoice == 'N') {
				fileWanted = false;
			} else {
				while (userChoice != 'y' && userChoice != 'Y' && userChoice != 'n' && userChoice != 'N') {
					System.out.println("Error: Please only enter Y or N.");
					System.out.print("[Y/N]?: ");
					wantFile = in.nextLine();
					userChoice = wantFile.toUpperCase().charAt(0);
					if (userChoice == 'y' || userChoice == 'Y') {
						fileWanted = true;
					}
				}
			}
		} else {
			while (wantFile.length() == 0) {
				System.out.println("Error: Please only enter Y or N.");
				System.out.print("[Y/N]?: ");
				wantFile = in.nextLine();
				if (wantFile.length() != 0) {
					userChoice = wantFile.toUpperCase().charAt(0); // converts String to a capitalized character
					if (userChoice == 'y' || userChoice == 'Y') {
						fileWanted = true;
					} else if (userChoice == 'n' || userChoice == 'N') {
						fileWanted = false;
					} else {
						while (userChoice != 'y' && userChoice != 'Y' && userChoice != 'n' && userChoice != 'N') {
							System.out.println("Error: Please only enter Y or N.");
							System.out.print("[Y/N]?: ");
							wantFile = in.nextLine();
							userChoice = wantFile.toUpperCase().charAt(0);
							if (userChoice == 'y' || userChoice == 'Y') {
								fileWanted = true;
							}
						}
					}
				}
			}
		}
		return fileWanted;
	}

	/**
	 * Method executes if lookingForFile returned true prompts user for file name. String inputed by user is immediately returned unless no string is inputed.
	 * If no string is inputed then method prompts user until they input one.  
	*/
	public static String promptForFile(Scanner in) {
		String fileName = "";
		System.out.println("");
		System.out.print("Please enter the file name of your previous draft: ");
		fileName = in.nextLine();
		System.out.println("");
		if (fileName.length() == 0) {
			System.out.print("Please enter a file name: ");
			fileName = in.nextLine();
			System.out.println("");
			while (fileName.length() == 0) {
				System.out.println("Please enter a file name: ");
				fileName = in.nextLine();
				System.out.println("");
			}
		}
		return fileName; // return file name
	}

	/**
	 *Method takes fileName, opens file, scans file, converts content into string with each line appended to string builder.
	 *If file is not found then error message prints. 
	 *The default file in this folder is example.txt which contains the lyrics from the children's rhyme Hot Cross Buns.
	*/
	public static String openFile(Scanner in, File accessFile) { 
		StringBuilder addLine = new StringBuilder();
		try {
			Scanner input = new Scanner(accessFile);
			while (input.hasNextLine()) {
				String fileSentence = input.nextLine();
				addLine.append(fileSentence + "\n");
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error: Could not find file.");
		}
		return addLine.toString();
	}

	/**
	 *Since this program will accept as many or as little words from user and the user may edit words later, ArrayLists are essential.
	 *Converts strings from StringBuilder into ArrayList with consideration of lines. 
	*/
	public static ArrayList<String> convertToArrayList(String draftContents) {
		ArrayList<String> listOfStrings = new ArrayList<>(Arrays.asList(draftContents.split("\n")));
		return listOfStrings;
	}

	/**
	 *Prints the now string ArrayList contents of the file. 
	*/
	public static void printList(ArrayList<String> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	/**
	 *Checks with user that the file printed is correct. 
	 *Method follows same coding pattern as previous boolean method lookingForFile.  
	*/
	public static boolean correctDraft(Scanner in, String fileContents) {
		boolean draftWanted = false;
		char userChoice;
		System.out.println();
		System.out.print("Is this the correct draft? [Y/N]: ");
		String response = in.nextLine();
		if (response.length() != 0) {
			userChoice = response.toUpperCase().charAt(0);
			if (userChoice == 'y' || userChoice == 'Y') {
				draftWanted = true;
				System.out.println("");
			} else if (userChoice == 'n' || userChoice == 'N') {
				draftWanted = false;
			} else {
				while (userChoice != 'y' && userChoice != 'Y' && userChoice != 'n' && userChoice != 'N') {
					System.out.println("Error: Please only enter Y or N.");
					System.out.print("[Y/N]?: ");
					response = in.nextLine();
					userChoice = response.toUpperCase().charAt(0);
				}
			}
		} else {
			while (response.length() == 0) {
				System.out.println("Error: Please only enter Y or N.");
				System.out.print("[Y/N]?: ");
				response = in.nextLine();
				if (response.length() != 0) {
					userChoice = response.toUpperCase().charAt(0);
					if (userChoice == 'y' || userChoice == 'Y') {
						draftWanted = true;
						System.out.println("");
					} else if (userChoice == 'n' || userChoice == 'N') {
						draftWanted = false;
					} else {
						while (userChoice != 'y' && userChoice != 'Y' && userChoice != 'n' && userChoice != 'N') {
							System.out.println("Error: Please only enter Y or N.");
							System.out.print("[Y/N]?: ");
							response = in.nextLine();
							userChoice = response.toUpperCase().charAt(0);
							if (userChoice == 'y' || userChoice == 'Y') {
								draftWanted = true;
							}
						}
					}
				}
			}
		}
		return draftWanted;
	}

	/**
	 * Below is an incomplete method intended to ask user for any full lyrics and store them to an ArrayList as well. 
	 */
	
	 
//	 public static ArrayList <String> storeLines (Scanner in){
//	 ArrayList<String> lines = new ArrayList<>();
//	 System.out.println("Please enter any completed lines that you would like to go together (enter a blank to exit): "); String userLine = in.nextLine();
//	 lines.add(userLine); 
//	 while(userLine.length()!=0) { 
//		 userLine = in.nextLine();
//		 lines.add(userLine); } return lines; 
//	 }
	
	/**
	 * User inputs which parts of speech that certain words they're considering fall into.
	 * Stores words into ArrayLists of respective part of speech until user presses enter.   
	 * ArrayLists are then returned and printed for user.
	 */
	static ArrayList<String> storeWords(Scanner in, String str) {
		ArrayList<String> words = new ArrayList<>();
		if (str.equals("Subjects")) {
			System.out.println("Please enter any non-determiner subject(s) (enter a blank to exit): ");
		} else if (str.equals("Nouns")) {
			System.out.println("Please enter any desired nouns(s) (enter a blank to exit): ");
		} else if (str.equals("Determiners")) {
			System.out.println(
					"A determiner may be an article, possesive noun, demonstratives, numerals, ordinals or quantifiers.");
			System.out.println("Please enter any determiner(s) (enter a blank to exit): ");
		} else if (str.equals("Verbs")) {
			System.out.println("Please enter any desired verb(s) (enter a blank to exit): ");
		} else if (str.equals("Conjunctions")) {
			System.out.println("Please enter any desired conjunction(s) (enter a blank to exit): ");
		} else if (str.equals("Adjectives")) {
			System.out.println("Please enter any desired adjective(s) (enter a blank to exit): ");
		} else if (str.equals("Adverbs")) {
			System.out.println("Please enter any desired adverb(s) (enter a blank to exit): ");
		} else {
			System.out.println("Please enter any desired preposition(s) (enter a blank to exit): ");
		}
		String userWord = in.nextLine();
		words.add(userWord);
		while (userWord.length() != 0) {
			userWord = in.nextLine();
			if (userWord.length() > 0) {
				words.add(userWord);
			}
		}
		System.out.println(str + ": " + words);
		return words;
	}

	/**
	 * Asks user if they would like to change any of the ArrayLists upon review.
	 * Method follows similar coding pattern as previous boolean method lookingForFile.  
	 */
	public static boolean changeList(Scanner in) {
		boolean changeWanted = true;
		System.out.print("Is there any (other) list you would like to change? [Y/N]: ");
		String response = in.nextLine();
		char userChoice = response.toUpperCase().charAt(0);
		if (userChoice == 'y' || userChoice == 'Y') {
			changeWanted = true;
			System.out.println("");
		} else if (userChoice == 'n' || userChoice == 'N') {
			changeWanted = false;
		}
		while (userChoice != 'y' && userChoice != 'Y' && userChoice != 'n' && userChoice != 'N'
				|| response.length() != 1) {
			System.out.println("Error: Please only enter Y or N.");
			System.out.print("[Y/N]?: ");
			response = in.nextLine();
			userChoice = response.toUpperCase().charAt(0);
		}
		return changeWanted;
	}

	/**
	 * Asks user if they would like to change any of the ArrayLists upon review.
	 * Method follows similar coding pattern as previous boolean method lookingForFile.  
	 */
	public static String whichList(Scanner in) {
		System.out.print(
				"Which list would you like to change? [Subjects, Nouns, Determiners, Verbs, Conjunctions, Adjectives, Adverbs, Prepositions or enter a blank to exit]: ");
		String response = in.nextLine();
		System.out.println();
		return response;
	}

	/**
	 * If changeList returns true then the String returned by whichList is inputed.
	 * Prompts user as to whether they want to add or remove a word.
	 * As long as a String is inputed, it is converted into a character.
	 * Adding follows same pattern from adding String to ArrayList from storeWords method.
	 * Removing follows same pattern but with array.remove function.
	 * Once user enters empty String then loop is exited and updated ArrayLists returned.
	 */
	public static ArrayList<String> addOrRemove(Scanner in, String listName, ArrayList<String> array) {
		boolean continueChanging = true;
		while (continueChanging) {
			System.out.println(listName + ": " + array);
			for (int i = 0; i < array.size(); i++) {
				String indexCheck = array.get(i);
				if (indexCheck.length() == 0) {
					array.remove(indexCheck);
				}
			}
			System.out.print("Would you like to add a word, remove a word or exit? [A/R/Enter]: ");
			String response = in.nextLine();
			if (response.length() != 0) {
				char userChoice = response.toUpperCase().charAt(0);
				if (userChoice == 'a' || userChoice == 'A' || array.size() == 1) {
					System.out.println("Please enter any " + listName + " (enter a blank to exit): ");
					String userSubject = in.nextLine();
					array.add(userSubject);
					while (userSubject.length() != 0) {
						userSubject = in.nextLine();
						if (userSubject.length() > 0) {
							array.add(userSubject);
						}
					}
				} else if (userChoice == 'r' || userChoice == 'R' && array.size() >= 1) {
					System.out.println(
							"Please enter any " + listName + " you would like to remove (enter a blank to exit): ");
					String userRemove = in.nextLine();
					array.remove(userRemove);
					while (userRemove.length() != 0) {
						userRemove = in.nextLine();
						if (userRemove.length() > 0) {
							array.remove(userRemove);
						}
					}
				} else {
					while (userChoice != 'a' && userChoice != 'A' && userChoice != 'r' && userChoice != 'R') {
						System.out.println("Error: Please only select A, R or Enter.");
						System.out.print("[A/R/Enter]?: ");
						response = in.nextLine();
						userChoice = response.toUpperCase().charAt(0);
					}
					userChoice = response.toUpperCase().charAt(0);
					if (userChoice == 'a' || userChoice == 'A') {
						System.out.println("Please enter any " + listName + " (enter a blank to exit): ");
						String userSubject = in.nextLine();
						array.add(userSubject);
						while (userSubject.length() != 0) {
							userSubject = in.nextLine();
							if (userSubject.length() > 0) {
								array.add(userSubject);
							}
						}
					} else if (userChoice == 'r' || userChoice == 'R') {
						System.out.println(
								"Please enter any " + listName + " you would like to remove (enter a blank to exit): ");
						String userRemove = in.nextLine();
						array.remove(userRemove);
						while (userRemove.length() != 0) {
							userRemove = in.nextLine();
							if (userRemove.length() > 0) {
								array.remove(userRemove);
							}
						}
					}
				}
			} else {
				System.out.println("");
				System.out.println("Exiting, final list printed below:");
				System.out.println(listName + ": " + array);
				continueChanging = false;
			}
		}
		return array;
	}

	/**
	 * Method deep copies updated ArrayLists from addOrRemove prior to randomization that way user has ability to reset randomization later on.
	 * Also, some users may want to have original ArrayLists available in order to compare to final lyrics.
	 */
	public static ArrayList<String> deepCopyList(ArrayList<String> array) {
		ArrayList<String> arrayCopy = new ArrayList<>();
		String element;
		for (int i = 0; i < array.size(); i++) {
			element = array.get(i);
			arrayCopy.add(element);
		}
		return arrayCopy;
	}
	
	/**
	 *After studying English grammar structure and the parts of speech I attempted to create a method that would extract random Strings from random ArrayLists and combine them into sentences.
	 *Although this method was good logic practice, I realized that hard coding these combinations was not sustainable. Unlike a language like Chinese that is arithmetic, English is one of the most 
	 *complex languages and there are many exceptions to its grammatical structure. In addition, with each sentence structure I added I had to manually reorder the previous ones due to hierarchy of
	 *conditions. At this point I accepted that I did the most that I could do with my current knowledge and resources and that I needed to implement machine learning for best resemblance of English sentences. 
	 *In addition, after having ran and tested this program dozens of times, I realized just how inefficient this interface is for the common user. Granted this program is intended to decrease the frustration 
	 *often associated with the song writing process, I decided that it would need a GUI as well. Since these realizations I've been attempting to learn more about coding through my school's resources in my free time.      
	 */

//	public static String randomize(ArrayList<String> subjects, ArrayList<String> nouns,
//			ArrayList<String> determiners, ArrayList<String> verbs, ArrayList<String> conjunctions,
//			ArrayList<String> adjectives, ArrayList<String> adverbs, ArrayList<String> prepositions) {
//		StringBuilder randomizedLyrics = new StringBuilder();
//		Random rand = new Random();
//		int randomizer = rand.nextInt(5);
//		int randomSubject = rand.nextInt(subjects.size());
//		int randomDeterminer = rand.nextInt(determiners.size());
//		int randomVerb = rand.nextInt(verbs.size());
//		int randomConjunction = rand.nextInt(conjunctions.size());
//		int randomAdjective = rand.nextInt(adjectives.size());
//		int randomAdverb = rand.nextInt(adverbs.size());
//		int randomPreposition = rand.nextInt(prepositions.size());
//		int randomNoun = rand.nextInt(nouns.size());
//		String subjectWanted;
//		String determinerWanted;
//		String verbWanted;
//		String conjunctionWanted;
//		String adjectiveWanted;
//		String adverbWanted;
//		String prepositionWanted;
//		String nounWanted;
//		// subjects, determiners and prepositions not included in parameters of loop
//		// because they will be used multiple times
//		while (nouns.size() != 0 || verbs.size() != 0 || conjunctions.size() != 0 || adjectives.size() != 0
//				|| adverbs.size() != 0) {
//			if (randomizer == 1 && subjects.size() != 0 && verbs.size() != 0) {
//				// example: he ran
//				subjectWanted = subjects.get(randomSubject);
//				verbWanted = verbs.get(randomVerb);
//				randomizedLyrics.append(subjectWanted + " " + verbWanted + "\n");
//				verbs.remove(randomVerb);
//				if (subjects.size() > 0) {
//				randomSubject = rand.nextInt(subjects.size());
//				}
//				if (verbs.size() > 0) {
//				randomVerb = rand.nextInt(verbs.size());
//				}
//			} else if (randomizer == 2 && subjects.size() != 0 && verbs.size() != 0 && adjectives.size() != 0) {
//				// example: he ran fast
//				subjectWanted = subjects.get(randomSubject);
//				verbWanted = verbs.get(randomVerb);
//				adjectiveWanted = adjectives.get(randomAdjective);
//				randomizedLyrics.append(subjectWanted + " " + verbWanted + " " + adjectiveWanted + "\n");
//				verbs.remove(randomVerb);
//				adjectives.remove(randomAdjective);
//				if (subjects.size() > 0) {
//					randomSubject = rand.nextInt(subjects.size());
//					}
//				if (verbs.size() > 0) {
//					randomVerb = rand.nextInt(verbs.size());
//					}
//				if (adjectives.size() > 0) {
//					randomAdjective = rand.nextInt(adjectives.size());
//					}
//			} else if (randomizer == 3 && subjects.size() != 0 && verbs.size() != 0 && adverbs.size() != 0) {
//				// example: he ran quickly
//				subjectWanted = subjects.get(randomSubject);
//				verbWanted = verbs.get(randomVerb);
//				adverbWanted = adverbs.get(randomAdverb);
//				randomizedLyrics.append(subjectWanted + " " + verbWanted + " " + adverbWanted + "\n");
//				verbs.remove(randomVerb);
//				adverbs.remove(randomAdverb);
//				if (subjects.size() > 0) {
//					randomSubject = rand.nextInt(subjects.size());
//					}
//				if (verbs.size() > 0) {
//					randomVerb = rand.nextInt(verbs.size());
//					}
//				if (adverbs.size() > 0) {
//					randomAdverb = rand.nextInt(adverbs.size());
//					}
//			} else if (randomizer == 4 && subjects.size() != 0 && verbs.size() != 0 && determiners.size() != 0
//					&& nouns.size() != 0) {
//				// example: he is a doctor
//				subjectWanted = subjects.get(randomSubject);
//				verbWanted = verbs.get(randomVerb);
//				determinerWanted = determiners.get(randomDeterminer);
//				nounWanted = nouns.get(randomNoun);
//				randomizedLyrics
//						.append(subjectWanted + " " + verbWanted + " " + determinerWanted + " " + nounWanted + "\n");
//				verbs.remove(randomVerb);
//				nouns.remove(randomNoun);
//				if (subjects.size() > 0) {
//					randomSubject = rand.nextInt(subjects.size());
//					}
//				if (verbs.size() > 0) {
//					randomVerb = rand.nextInt(verbs.size());
//					}
//				if (determiners.size() > 0) {
//					randomDeterminer = rand.nextInt(determiners.size());
//					}
//				if (nouns.size() > 0) {
//					randomNoun = rand.nextInt(nouns.size());
//					}
//			} else if (randomizer == 5 && subjects.size() != 0 && adverbs.size() != 0 && verbs.size() != 0
//					&& determiners.size() != 0 && nouns.size() != 0) {
//				// example: my canary beautifully sang a song
//				subjectWanted = subjects.get(randomSubject);
//				adverbWanted = adverbs.get(randomAdverb);
//				verbWanted = verbs.get(randomVerb);
//				determinerWanted = determiners.get(randomDeterminer);
//				nounWanted = nouns.get(randomNoun);
//				randomizedLyrics.append(subjectWanted + " " + adverbWanted + " " + verbWanted + " " + determinerWanted
//						+ " " + nounWanted + "\n");
//				adverbs.remove(randomAdverb);
//				verbs.remove(randomVerb);
//				nouns.remove(randomNoun);
//				if (subjects.size() > 0) {
//					randomSubject = rand.nextInt(subjects.size());
//					}
//				if (adverbs.size() > 0) {
//					randomAdverb = rand.nextInt(adverbs.size());
//					}
//				if (verbs.size() > 0) {
//					randomVerb = rand.nextInt(verbs.size());
//					}
//				if (determiners.size() > 0) {
//					randomDeterminer = rand.nextInt(determiners.size());
//					}
//				if (nouns.size() > 0) {
//					randomNoun = rand.nextInt(nouns.size());
//					}
//			} else if (randomizer == 6 && determiners.size() != 0 && subjects.size() != 0 && verbs.size() != 0
//					&& prepositions.size() != 0 && subjects.size() != 0) {
//				// example: my canary sang to me
//				determinerWanted = determiners.get(randomDeterminer);
//				subjectWanted = subjects.get(randomSubject);
//				verbWanted = verbs.get(randomVerb);
//				prepositionWanted = adverbs.get(randomPreposition);
//				randomizedLyrics
//						.append(determinerWanted + " " + subjectWanted + " " + verbWanted + " " + prepositionWanted);
//				adverbs.remove(randomAdverb);
//				verbs.remove(randomVerb);
//				if (subjects.size() > 0) {
//					randomSubject = rand.nextInt(subjects.size());
//					}
//				if (adverbs.size() > 0) {
//					randomAdverb = rand.nextInt(adverbs.size());
//					}
//				if (verbs.size() > 0) {
//					randomVerb = rand.nextInt(verbs.size());
//					}
//				if (determiners.size() > 0) {
//					randomDeterminer = rand.nextInt(determiners.size());
//					}
//				if (prepositions.size() > 0) {
//					randomPreposition = rand.nextInt(prepositions.size());
//					}
//				randomizedLyrics.append(" " + subjectWanted + "\n");
//				randomSubject = rand.nextInt(subjects.size());
//			}
//		}
//		// want to make string builder that will create a whole new array with random
//		// sentence structures based off of the other array lists and require
//		return randomizedLyrics.toString();
//	}

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		File fileOpened = new File("");
		// int random = (int) (Math.random() * 50 + 1);
		System.out.println("Welcome to the Verbasizer.");
		System.out.println("");
		boolean previousFile = lookingForFile(keyboard);
		if (previousFile) {
			String file = promptForFile(keyboard);
			fileOpened = new File(file);
			String draft = openFile(keyboard, fileOpened);
			ArrayList<String> previousLyrics = convertToArrayList(draft);
			printList(previousLyrics);
			while (!correctDraft(keyboard, draft)) {
				promptForFile(keyboard);
				fileOpened = new File(file);
				draft = openFile(keyboard, fileOpened);
				previousLyrics = convertToArrayList(draft);
				printList(previousLyrics);
			}
		} else {
			System.out.println("");
		}
		System.out.println("Thank you, let us continue.");
		System.out.println("");
		ArrayList<String> subjectLyrics = storeWords(keyboard, "Subjects");
		System.out.println("");
		ArrayList<String> nounLyrics = storeWords(keyboard, "Nouns");
		System.out.println("");
		ArrayList<String> determinerLyrics = storeWords(keyboard, "Determiners");
		System.out.println("");
		ArrayList<String> verbLyrics = storeWords(keyboard, "Verbs");
		System.out.println("");
		ArrayList<String> conjunctionLyrics = storeWords(keyboard, "Conjunctions");
		System.out.println("");
		ArrayList<String> adjectiveLyrics = storeWords(keyboard, "Adjectives");
		System.out.println("");
		ArrayList<String> adverbLyrics = storeWords(keyboard, "Adverbs");
		System.out.println("");
		ArrayList<String> prepositionLyrics = storeWords(keyboard, "Prepositons");
		System.out.println("");
		System.out.println("Here are your lists:");
		System.out.println("Subjects: " + subjectLyrics);
		System.out.println("Nouns: " + nounLyrics);
		System.out.println("Determiners: " + determinerLyrics);
		System.out.println("Verbs: " + verbLyrics);
		System.out.println("Conjunctions: " + conjunctionLyrics);
		System.out.println("Adjectives: " + adjectiveLyrics);
		System.out.println("Adverbs: " + adverbLyrics);
		System.out.println("Prepositions: " + prepositionLyrics);
		System.out.println("");
		while (changeList(keyboard)) {
			String listToChange = whichList(keyboard);
			if (listToChange.equals("Subject") || listToChange.equals("subject") || listToChange.equals("Subjects")
					|| listToChange.equals("subjects")) {
				addOrRemove(keyboard, "Subjects", subjectLyrics);
			} else if (listToChange.equals("Noun") || listToChange.equals("noun") || listToChange.equals("Nouns")
					|| listToChange.equals("nouns")) {
				addOrRemove(keyboard, "Nouns", nounLyrics);
			} else if (listToChange.equals("Determiner") || listToChange.equals("determiner")
					|| listToChange.equals("Determiners") || listToChange.equals("determiners")) {
				addOrRemove(keyboard, "Determiners", determinerLyrics);
			} else if (listToChange.equals("Verb") || listToChange.equals("verb") || listToChange.equals("Verbs")
					|| listToChange.equals("verbs")) {
				addOrRemove(keyboard, "Verbs", verbLyrics);
			} else if (listToChange.equals("Conjunction") || listToChange.equals("conjunction")
					|| listToChange.equals("Conjunctions") || listToChange.equals("conjunctions")) {
				addOrRemove(keyboard, "Conjunctions", conjunctionLyrics);
			} else if (listToChange.equals("Adjective") || listToChange.equals("adjective")
					|| listToChange.equals("Adjectives") || listToChange.equals("adjectives")) {
				addOrRemove(keyboard, "Adjectives", adjectiveLyrics);
			} else if (listToChange.equals("Adverb") || listToChange.equals("adverb") || listToChange.equals("Adverbs")
					|| listToChange.equals("adverbs")) {
				addOrRemove(keyboard, "Adverbs", adverbLyrics);
			} else if (listToChange.equals("Preposition") || listToChange.equals("preposition")
					|| listToChange.equals("Prepositions") || listToChange.equals("preposition")) {
				addOrRemove(keyboard, "Prepositions", prepositionLyrics);
			} else if (listToChange.length() == 0) {
				System.out.println("");
			} else {
				System.out.println(
						"Please choose a valid list [Subjects, Nouns, Determiners, Verbs, Conjunctions, Adjectives, Adverbs, Prepositions or enter a blank to exit]: ");
			}
		}
		System.out.println("");
		ArrayList<String> nounLyricsCopy = deepCopyList(nounLyrics);
		ArrayList<String> verbLyricsCopy = deepCopyList(verbLyrics);
		ArrayList<String> conjunctionLyricsCopy = deepCopyList(conjunctionLyrics);
		ArrayList<String> adjectiveLyricsCopy = deepCopyList(adjectiveLyrics);
		ArrayList<String> adverbLyricsCopy = deepCopyList(adverbLyrics);
//		String randomizedDraft = randomize(subjectLyrics, nounLyricsCopy,
//				determinerLyrics, verbLyricsCopy, conjunctionLyricsCopy,
//				adjectiveLyricsCopy, adverbLyricsCopy, prepositionLyrics);
//		//ask user if they are satisfied with the randomized results, if not it will randomize again
//		ArrayList<String> userLyrics = convertToArrayList(randomizedDraft);
//		printList(userLyrics);
		//ask user if they would like their original lyrics appended to the beginning or end of opened draft, if they opened one.
	}
}