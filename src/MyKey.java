import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class MyKey {
private static Map<Character, Character> map = new HashMap<Character, Character>();
private static char[] encodeChar = "GSJbq3EjrLD 02A5uWmzg6.ohypXFa1tk4d8wfe9OUZYVCPcHlvNxKiQ7BsnRMIT".toCharArray();
private static char[] trueChar = " .0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
private static void mapSetUp() {
for (int i = 0; i < encodeChar.length; i++) {
map.put(encodeChar[i], trueChar[i]);
}
}
private static String decodeMessage(String message) {
String decode = "";
for (int i = 0; i < message.length(); i++) {
decode += map.get(message.charAt(i));
}
return decode;
}
public static void main(String[] args){
Scanner sc = new Scanner(System.in);
System.out.print("Input your encrypted message : ");String enMessage = sc.nextLine();mapSetUp();
System.out.println("Decrypted message : "+decodeMessage(enMessage));
}}