public class Computer {

    public static void main(String[] args) {
        SolvingEquations solvingEquations = new SolvingEquations();
        solvingEquations.setSecondDegree(1);
        solvingEquations.setFirstDegree(4);
        solvingEquations.setZeroDegree(3);
        solvingEquations.standardEquation();
        solvingEquations.printStandardEquation();
    }
}
