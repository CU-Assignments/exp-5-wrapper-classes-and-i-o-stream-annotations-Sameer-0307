package College;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class SumCalculator {

public static Integer parseStringToInteger(String str) {
str = str.replaceAll("\"", "").trim();
try {
return Integer.parseInt(str);
} catch (NumberFormatException e) {
System.out.println("Invalid number format: " + str);
return null; // Return null if invalid input
}
}
// Method to calculate sum
public static int calculateSum(List<Integer> numbers) {
int sum = 0;
for (Integer num : numbers) {
if (num != null) { // Ignore null values
sum += num; }}
return sum;
}
public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
List<Integer> numbers = new ArrayList<>();
System.out.println("Enter numbers separated by spaces or commas:");
String input = scanner.nextLine();
// Split input by comma and space, then trim each value
String[] tokens = input.split("[,\\s]+"); // Handles spaces and commas
// Convert each input to an integer
for (String token : tokens) {
numbers.add(parseStringToInteger(token));
}
// Calculate and display sum
System.out.println("The sum of the list is: " + calculateSum(numbers));
scanner.close(); }}
