import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;

public class SolvingEquations {

    private String errorMsg;
    private String equation;
    private String leftEquation;
    private String rightEquation;
    private HashMap<Integer, Double> leftDegree;
    private HashMap<Integer, Double> rightDegree;
    private double thirdOrMoreDegree;
    private double secondDegree;
    private double firstDegree;
    private double zeroDegree;
    private double discriminant;
    private double firstSolution;
    private double secondSolution;
    private double singleSolution;
    private boolean thirdOrMoreRoots;
    private boolean twoRoots;
    private boolean oneRoot;
    private boolean noRoots;

    public SolvingEquations(String equation) {
        thirdOrMoreDegree = 0;
        secondDegree = 0;
        firstDegree = 0;
        zeroDegree = 0;
        thirdOrMoreRoots = false;
        twoRoots = false;
        oneRoot = false;
        noRoots = false;
        this.equation = equation;
    }

    private void sorting(char[] equation, HashMap<Integer, Double> anyEquation) throws Exception {
        StringBuilder bufNumber;
        boolean dot;
        double flag;
        for (int i = 0; i < equation.length; i++) {
            bufNumber = new StringBuilder();
            dot = false;
            switch (equation[i]) {
                case '-':
                    flag = -1;
                    ++i;
                    break;
                case '+':
                    ++i;
                    flag = 1;
                    break;
                default:
                    flag = 1;
            } if (Character.isDigit(equation[i]) || equation[i] == 'x') {
                while (i < equation.length && (Character.isDigit(equation[i]) || equation[i] == '.')) {
                    if (dot || (equation[i] == '.' && bufNumber.isEmpty())) {
                        throw new Exception("Bad number!");
                    } else if (equation[i] == '.')
                        dot = true;
                    bufNumber.append(equation[i++]);
                }
                if (i < equation.length &&
                        ((equation[i] == '*' && equation[i + 1] == 'x') || equation[i] == 'x')) {
                    StringBuilder bufDegree = new StringBuilder();
                    i += (equation[i] == '*' && equation[i + 1] == 'x') ? 2 : (equation[i] == 'x') ? 1 : 0;
                    i += (equation[i] == '^') ? 1 : 0;
                    if (Character.isDigit(equation[i])) {
                        while (Character.isDigit(equation[i])) {
                            bufDegree.append(equation[i++]);
                        }
                        if (anyEquation.containsKey(Integer.valueOf(bufDegree.toString()))) {
                            double buf = anyEquation.get(Integer.valueOf(bufDegree.toString()))
                                    + flag * Double.parseDouble(bufNumber.toString());
                            anyEquation.put(Integer.valueOf(bufDegree.toString()), buf);
                        } else
                            anyEquation.put(Integer.valueOf(bufDegree.toString()),
                                    flag * Double.parseDouble(bufNumber.toString()));
                    } else
                        anyEquation.put(1, flag * Double.parseDouble(bufNumber.toString()));
                } else if (!bufNumber.isEmpty()) {
                    if (anyEquation.containsKey(0)) {
                        double buf = anyEquation.get(0) + flag
                                * Double.parseDouble(bufNumber.toString());
                        anyEquation.put(0, buf);
                    } else
                        anyEquation.put(0, flag * Double.parseDouble(bufNumber.toString()));
                } else
                    throw new Exception("Bad symbol!");
                i--;
            }
        }
    }

    private void discriminant() {
        discriminant = Math.pow(firstDegree, 2) - 4 * secondDegree * zeroDegree;
    }

    private void firstSolution() {
        firstSolution = (firstDegree * (-1) + Math.sqrt(discriminant)) / (2 * secondDegree);
    };

    private void secondSolution() {
        secondSolution = (firstDegree * (-1) - Math.sqrt(discriminant)) / (2 * secondDegree);
    };

    private void singleSolution() {
        singleSolution = firstDegree * (-1) / (2 * secondDegree);
    }

    private void standardEquation() {
        discriminant();
        if (discriminant > 0) {
            twoRoots = true;
            firstSolution();
            secondSolution();
        } else if (discriminant == 0) {
            oneRoot = true;
            singleSolution();
        } else {
            noRoots = true;
        }
    }

    private void printStandardEquation() {
        System.out.println("D = b^2 - 4ac =");
        System.out.println("= " + firstDegree + " ^2 - 4 * " + secondDegree + " * " + zeroDegree + " =");
        System.out.println("= " + discriminant);

        if (twoRoots) {
            System.out.println("Discriminant is strictly positive, the two solutions are:");
            System.out.println("X1 = (-b + D^(1/2)) / 2a =");
            System.out.print("= (" + (-1) * secondDegree + " + " + discriminant + "^(1/2)) / 2 * ");
            System.out.println(secondDegree + " =");
            System.out.println("= " + firstSolution);

            System.out.println("X1 = (-b - D^(1/2)) / 2a =");
            System.out.print("= (" + (-1) * secondDegree + " - " + discriminant + "^(1/2)) / 2 * ");
            System.out.println(secondDegree + " =");
            System.out.println("= " + secondSolution);
        } else if (oneRoot) {
            System.out.println("The solution is:");
            System.out.println("X = -b / 2a =");
            System.out.print("= " + (-1) * secondDegree + " / 2 * " + secondDegree + " =");
            System.out.println("= " + firstSolution);
        } else {
            System.out.println("The discriminant is negative, there are no solutions!");
        }
    }

    private void solutionFirstDegreeEquation() {
        System.out.println("The solution is:");
        System.out.println("X = -c / b =");
        singleSolution = (-1) * zeroDegree / firstDegree;
        System.out.println("= (-1) * " + zeroDegree +  " / " + firstDegree + " =");
        System.out.println("= " + singleSolution);
    }

    private void sumTwoEquations() {
        for (int i = 0; !leftDegree.isEmpty() && !rightDegree.isEmpty(); i++) {
            if (leftDegree.containsKey(i) && rightDegree.containsKey(i)) {
                if (leftDegree.get(i) == rightDegree.get(i)) {
                    leftDegree.remove(i);
                    rightDegree.remove(i);
                } else {

                }
            } else if (!leftDegree.containsKey(i) && rightDegree.containsKey(i)) {

            } else if (leftDegree.containsKey(i) && !rightDegree.containsKey(i)) {

            }
        }
    }

    public void solve() {
        String[] equations = equation.replace(" ", "").split("=");
        if (equations.length != 2)
            errorMsg = "Bad equation!";
        else {
            leftDegree = new HashMap<>();
            rightDegree = new HashMap<>();
            try {
                sorting(equations[0].toLowerCase(Locale.ROOT).toCharArray(), leftDegree);
                sorting(equations[1].toLowerCase(Locale.ROOT).toCharArray(), rightDegree);
                sumTwoEquations();
                System.out.println(leftDegree);
                System.out.println(rightDegree);
            } catch (Exception e) {
                errorMsg = e.getMessage();
            }

        }
//        if (secondDegree != 0) {
//            System.out.println("Polynomial degree: 2");
//            standardEquation();
//            printStandardEquation();
//        } else if (secondDegree == 0 && firstDegree != 0) {
//            System.out.println("Polynomial degree: 1");
//            solutionFirstDegreeEquation();
//        } else {
//            System.out.println("Incorrect equation!");
//        }
    }
}
