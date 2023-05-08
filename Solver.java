public class Solver {
    public static void printTable(int[][] data) {
        System.out.println("       | Time(ms) | Restart |  Moves  |");
        System.out.println("-------|----------|---------|---------|");
        for (int i = 0; i < data.length; i++) {
            System.out.print("Board " + (i + 1));
            System.out.printf("|%10d|%9d|%9d|\n", data[i][0], data[i][1], data[i][2]);
        }
    }

    public static void main(String[] args) {
        nQueensProblem nQueensProblem = new nQueensProblem();
        int[][] data = nQueensProblem.runQueensSolver(9, 8);
        printTable(data);
    }
}
