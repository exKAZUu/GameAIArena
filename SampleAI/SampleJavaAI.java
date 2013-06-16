import java.util.Scanner;

public class SampleJavaAI {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			System.out.println(line);
		}
		scanner.close();
	}
}