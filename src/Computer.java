import java.util.Scanner;

public class Computer {

    public static void main(String[] args) {
        String input;
        Scanner scanner = new Scanner(System.in);

        if (args.length > 1) {
            throw new IllegalArgumentException("Bad arguments!");
        } else if (args.length == 1) {
            input = args[0];
        } else
            input = scanner.nextLine();

        SolvingEquations solvingEquations = new SolvingEquations(input);
        solvingEquations.solve();
    }
}

// "9X^2 + 3*x^2 - 5x^2 - 6x^1  + 1 = 1 x ^2 - 2x^1  + 1"
