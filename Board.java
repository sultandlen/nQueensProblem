public class Board {
    private final int size; // size of the board
    private int[] queenList; // list of the queens
    private int numRestarts = 0; // number of restarts
    private int numMoves = 0; // number of moves

    public Board(int size) {
        this.size = size;
        this.queenList = new int[size];
        for (int i = 0; i < size; i++) {
            queenList[i] = 0;
        }
        this.randomRestart();
    }

    public int[] copyQueenList() {
        int[] copyQueenList = new int[size];
        for (int i = 0; i < size; i++) {
            copyQueenList[i] = this.queenList[i];
        }
        return copyQueenList;
    }

    public int getNumRestarts() {
        return numRestarts;
    }

    public int getNumMoves() {
        return numMoves;
    }

    public int getSize() {
        return size;
    }

    public int[] getQueenList() {
        return queenList;
    }

    public void setQueenList(int[] queenList) {
        this.queenList = queenList;
    }

    public void setNumMoves() {
        this.numMoves++;
    }

    public void randomRestart() {
        for (int i = 0; i < size; i++) {
            int j = (int) (Math.random() * size);
            queenList[i] = j;
        }
        numRestarts++;
    }

    public void move(int row, int col) {
        queenList[row] = col;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(size - i);
            for (int j = 0; j < size; j++) {
                if (j == queenList[i]) {
                    System.out.print(" ♕ ");
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
        System.out.print(" ");
        for (int i = 97; i < size + 97; i++) {
            System.out.print(" " + Character.toString((char) i) + " ");
        }
        System.out.println("\n");
    }
}