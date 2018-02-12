import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * The class that use for encrypt message and create the file to decrypt it.
 * 
 * @author Triwith Mutitakul
 *
 */
public class Enigma {

	private char[] allChar;
	private char[] shuffledChar;
	private Map<Character, Character> map;
	private String message;

	public Enigma(String message) {
		this.message = message;
		setUp();
	}

	/**
	 * The method that use for set up the whole variable.
	 */
	private void setUp() {
		String allLetter = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ. 0123456789";
		allChar = allLetter.toCharArray();
		shuffledChar = shuffle(allLetter.toCharArray());
		map = new HashMap<Character, Character>();
		mapSetUp();
	}

	/**
	 * The method that use for shuffle an element of the array.
	 * 
	 * @param array
	 * @return the shuffled array
	 */
	private static char[] shuffle(char[] array) {
		Random rand = new Random();
		for (int i = 0; i < array.length; i++) {
			int randIndex = rand.nextInt(array.length - 1);
			char temp = array[i];
			array[i] = array[randIndex];
			array[randIndex] = temp;
		}
		return array;
	}

	/**
	 * The method that use for add the key and value for the map.
	 */
	private void mapSetUp() {
		for (int i = 0; i < allChar.length; i++) {
			map.put(allChar[i], shuffledChar[i]);
		}
	}

	/**
	 * The method that use for encode the message.
	 * 
	 * @return encoded message
	 */
	private String encodeMessage() {
		String encode = "";
		for (int i = 0; i < message.length(); i++) {
			encode += map.get(message.charAt(i));
		}
		generateBack();
		return encode;
	}

	/**
	 * The method that use for generate the java file that use for decrypt the
	 * message.
	 */
	private void generateBack() {
		Character[] encodeChar = (Character[]) map.values().toArray(new Character[map.size()]);
		Character[] trueChar = (Character[]) map.keySet().toArray(new Character[map.size()]);

		String allencode = "";
		String allTrue = "";

		for (int i = 0; i < encodeChar.length; i++) {
			allencode += encodeChar[i];
			allTrue += trueChar[i];
		}

		String code = "import java.util.HashMap;\n" + "import java.util.Map;\n" + "import java.util.Scanner;\n"
				+ "public class MyKey {\n"
				+ "private static Map<Character, Character> map = new HashMap<Character, Character>();\n"
				+ "private static char[] encodeChar = \"" + allencode + "\".toCharArray();\n"
				+ "private static char[] trueChar = \"" + allTrue + "\".toCharArray();\n"
				+ "private static void mapSetUp() {\n" + "for (int i = 0; i < encodeChar.length; i++) {\n"
				+ "map.put(encodeChar[i], trueChar[i]);\n" + "}\n" + "}\n"
				+ "private static String decodeMessage(String message) {\n" + "String decode = \"\";\n"
				+ "for (int i = 0; i < message.length(); i++) {\n" + "decode += map.get(message.charAt(i));\n" + "}\n"
				+ "return decode;\n" + "}\n" + "public static void main(String[] args){\n"
				+ "Scanner sc = new Scanner(System.in);\n" + "System.out.print(\"Input your encrypted message : \");"
				+ "String enMessage = sc.nextLine();" + "mapSetUp();\n"
				+ "System.out.println(\"Decrypted message : \"+decodeMessage(enMessage));\n" + "}}";
		File f = new File("MyKey.java");
		try {
			FileWriter fw = new FileWriter(f);
			BufferedWriter bf = new BufferedWriter(fw);
			bf.write(code);
			bf.close();
		} catch (IOException e) {
			System.out.println("Error about IO.");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to The Enigma!!");
		System.out.print("Input the message that you want to encrypt : ");
		String message = sc.nextLine();
		Enigma en = new Enigma(message);
		String encodeMessage = en.encodeMessage();
		System.out.println("\nYour message : " + message);
		System.out.println("Your encrypted message : " + encodeMessage);
		System.out.println("Run MyKey.java to decrypt your message!!");
		sc.close();
	}

}
