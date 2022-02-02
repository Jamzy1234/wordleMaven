import java.util.Arrays;
import java.util.Scanner;
import com.github.dhiraj072.randomwordgenerator.RandomWordGenerator;

public class wordle {

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static void main(String[] args) throws Exception {
        String wordToMatch = RandomWordGenerator.getRandomWord().toUpperCase();
        while (wordToMatch.length() != 5) {
            wordToMatch = RandomWordGenerator.getRandomWord().toUpperCase();
        }
        String charactersToMatch[] = wordToMatch.split("");
        String guessedWord;
        Integer guesses = 0;
        Integer guessCharacters;
        Integer greens;
        Integer yellows;
        Boolean tryAgain = true;
        Scanner input = new Scanner(System.in);

        for (guessCharacters = 0; guessCharacters < charactersToMatch.length; guessCharacters++) {
            System.out.print("#");
        }

        while (tryAgain == true) {

            System.out.println("");
            System.out.println("Enter a " + charactersToMatch.length + " letter word: ");
            guessedWord = input.next().toUpperCase();
            String characterGuesses[] = guessedWord.split("");
            while (characterGuesses.length != charactersToMatch.length) {
                System.out.println("Enter a " + charactersToMatch.length + " letter word: ");
                guessedWord = input.next().toUpperCase();
                characterGuesses = guessedWord.split("");
            }

            guesses += 1;
            greens = 0;
            yellows = 0;

            for (int x = 0; x < charactersToMatch.length; x++) {
                if (charactersToMatch[x].equalsIgnoreCase(characterGuesses[x])) {
                    System.out.print(ANSI_GREEN + characterGuesses[x].toUpperCase() + ANSI_BLACK);
                    greens += 1;
                } else if (Arrays.stream(charactersToMatch).anyMatch(characterGuesses[x]::equals)) {
                    System.out.print(ANSI_YELLOW + characterGuesses[x].toUpperCase() + ANSI_BLACK);
                    yellows += 1;
                } else {
                    System.out.print(characterGuesses[x].toUpperCase());
                }
            }
            System.out.println("");

            if (guesses < 6 && greens == charactersToMatch.length) {
                tryAgain = false;
                System.out.println("###############################");
                System.out.println("\t Correct! " + guesses + "/6 \t");
                System.out.println("###############################");
            } else if (guesses == 6 && greens != charactersToMatch.length) {
                tryAgain = false;
                System.out.println("###############################");
                System.out.println("\t Incorrect! \t");
                System.out.println("###############################");
                System.out.println("\t The word was " + wordToMatch.toUpperCase() + "\t");
                System.out.println("###############################");
            }

            if (tryAgain == false) {
                System.out.println("Play Again? Y or N");
                if (input.next().equalsIgnoreCase("Y")) {
                    tryAgain = true;
                    guesses = 0;
                    wordToMatch = RandomWordGenerator.getRandomWord().toUpperCase();
                    while (wordToMatch.length() != 5) {
                        wordToMatch = RandomWordGenerator.getRandomWord().toUpperCase();
                    }
                    for (guessCharacters = 0; guessCharacters < charactersToMatch.length; guessCharacters++) {
                        System.out.print("#");
                    }
                }else{
                    System.exit(0);
                }
            }
        }
    }
}