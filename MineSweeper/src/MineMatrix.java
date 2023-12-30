import java.util.Random;
public class MineMatrix {
    char[][] mineField;
    char[][] userField;
    int rows;
    int cols;
    int totalMines;
    public MineMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.totalMines = rows * cols / 4;

        mineField = new char[rows][cols];
        userField = new char[rows][cols];

            initializeFields();
            placeMines();

    }


    public void initializeFields() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mineField[i][j] = '-';
                userField[i][j] = '-';
            }
        }
    }
   public void placeMines() {
        Random random = new Random();
        int minesPlaced = 0;

        while (minesPlaced < totalMines) {
            int randRow = random.nextInt(rows);
            int randCol = random.nextInt(cols);

            if (mineField[randRow][randCol] != '*') {
                mineField[randRow][randCol] = '*';
                minesPlaced++;
            }
        }
    }

    public void revealMines() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mineField[i][j] == '*') {
                    userField[i][j] = '*';
                }
            }
        }
    }

    public void printUserField() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(userField[i][j] + " ");
            }
            System.out.println();
        }
    }

   public boolean isValidCoordinate(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

   public boolean isMine(int row, int col) {
        return mineField[row][col] == '*';
    }

   public int countAdjacentMines(int row, int col) {
        int count = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (isValidCoordinate(i, j) && isMine(i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

}
