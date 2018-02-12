import java.util.HashMap;
import java.util.Map;
public class MyKey {
private static Map<Character, Character> map = new HashMap<Character, Character>();
private static char[] encodeChar = "8JpR1SKjHocCZDqYNixTLPbdAe9vt7wkg u5VMEU6l3BOXG0IQnsraz42FhymWf".toCharArray();
private static char[] trueChar = " 0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
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
String message = args[0];
mapSetUp();
System.out.println(decodeMessage(message));
}}