public class nQueensProblem {
    public int countConflict(Board board, int row) {
        int conflicts = 0;
        for (int i = 0; i < board.getSize(); i++) {
            if (i == row) { //skip checking conflicts with the same queen
                continue;
            }
            if (board.getQueenList()[i] == board.getQueenList()[row]) { // Check columns
                conflicts++;
            } else if (Math.abs(i - row) == Math.abs(board.getQueenList()[i] - board.getQueenList()[row])) { // check diagonals
                conflicts++;
            }
        }
        return conflicts;
    }

    public int countAllConflict(Board board) {
        int totalConflicts = 0;
        for (int i = 0; i < board.getSize(); i++) {
            totalConflicts += countConflict(board, i);
        }
        return totalConflicts / 2;
    }

    public boolean isGoal(Board board) {
        return countAllConflict(board) == 0;
    }

    public Board hillClimbing(Board board) {
        int conflicts = this.countAllConflict(board);
        Board nextBoard = new Board(board.getSize());
        Board equalBoard = new Board(board.getSize());
        int[] copyQueenList = board.copyQueenList();
        nextBoard.setQueenList(copyQueenList);
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                int copyConflicts = conflicts - countConflict(board, row);
                nextBoard.move(row, col);
                copyConflicts += countConflict(nextBoard, row);
                if (copyConflicts < conflicts) {
                    return nextBoard;
                }
                if (copyConflicts == conflicts) {
                    equalBoard.setQueenList(nextBoard.copyQueenList());
                }
            }
            nextBoard.move(row, board.getQueenList()[row]);
        }
        return equalBoard;
    }

    public void solve(Board board) {
        final int LIMIT = 1000;
        int equalCount = 0;
        while (!this.isGoal(board)) {
            Board newBoard = hillClimbing(board);
            if (countAllConflict(board) == countAllConflict(newBoard)) {
                equalCount++;
            }
            board.setQueenList(newBoard.copyQueenList());
            board.setNumMoves();
            if (equalCount >= LIMIT) {
                equalCount = 0;
                board.randomRestart();
            }
        }
    }

    public int[][] runQueensSolver(int numIterations, int boardSize) {
        int[][] data = new int[numIterations][3];
        for (int i = 0; i < numIterations; i++) {
            Board board = new Board(boardSize);
            long start = System.currentTimeMillis();
            this.solve(board);
            long end = System.currentTimeMillis();
            int duration = (int) (end - start);
            data[i][0] = duration;
            data[i][1] = board.getNumRestarts();
            data[i][2] = board.getNumMoves();
            System.out.println("Board " + (i + 1) + "\n------------------------" );
            board.print();
        }
        return data;
    }
}
