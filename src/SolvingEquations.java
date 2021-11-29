public class SolvingEquations {

    private double thirdOrMoreDegree;
    private double secondDegree;
    private double firstDegree;
    private double zeroDegree;
    private double discriminant;
    private double firstSolution;
    private double secondSolution;
    private double singleSolution;
    private boolean twoRoots;
    private boolean oneRoot;
    private boolean noRoots;

    public SolvingEquations() {
        thirdOrMoreDegree = 0;
        secondDegree = 0;
        firstDegree = 0;
        zeroDegree = 0;
        twoRoots = false;
        oneRoot = false;
        noRoots = false;
    }

    public double getThirdOrMoreDegree() {
        return thirdOrMoreDegree;
    }

    public void setThirdOrMoreDegree(double thirdOrMoreDegree) {
        this.thirdOrMoreDegree = thirdOrMoreDegree;
    }

    public double getSecondDegree() {
        return secondDegree;
    }

    public void setSecondDegree(double secondDegree) {
        this.secondDegree = secondDegree;
    }

    public double getFirstDegree() {
        return firstDegree;
    }

    public void setFirstDegree(double firstDegree) {
        this.firstDegree = firstDegree;
    }

    public double getZeroDegree() {
        return zeroDegree;
    }

    public void setZeroDegree(int zeroDegree) {
        this.zeroDegree = zeroDegree;
    }

    public void discriminant() {
        discriminant = Math.pow(firstDegree, 2) - 4 * secondDegree * zeroDegree;
    }

    public double getDiscriminant() {
        return discriminant;
    }

    public void firstSolution() {
        firstSolution = (firstDegree * (-1) + Math.sqrt(discriminant)) / (2 * secondDegree);
    };

    public void secondSolution() {
        secondSolution = (firstDegree * (-1) - Math.sqrt(discriminant)) / (2 * secondDegree);
    };

    public void singleSolution() {
        singleSolution = firstDegree * (-1) / (2 * secondDegree);
    }

    public double getSingleSolution() {
        return singleSolution;
    }

    public double getFirstSolution() {
        return firstSolution;
    }

    public double getSecondSolution() {
        return secondSolution;
    }

    public void standardEquation() {
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

    public void printStandardEquation() {

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
            System.out.println("X1 = -b / 2a =");
            System.out.print("= " + (-1) * secondDegree + " / 2 * " + secondDegree + " =");
            System.out.println("= " + firstSolution);
        } else {
            System.out.println("The discriminant is negative, there are no solutions!");
        }
    }
}
