import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {

        while (true) {
            System.out.print("Input: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();
            Calculator calculator = new Calculator();
            String result = calculator.calc(input);
            System.out.println("Output: " + result);
        }
    }
}