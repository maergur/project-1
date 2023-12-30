import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("Mayın Tarlası'na Hoş Geldiniz!");
        playGame();
    }
    public static void playGame() {

        /* Kullanıcıdan aldığımız satır / sütun 2'den küçük olduğu sürece tekrar giriş istiyoruz.
         Başta yaptığımız rows = 0, cols = 0 sebebi zaten 2'den küçük olduğu için döngü kullanıcıya otomatik olarak
         satır / sütun kombinasyonu sormak zorunda kalacak. */

        Scanner inp = new Scanner(System.in);
        int rows = 0;
        int cols = 0;

        while (rows < 2 || cols < 2) {
            System.out.print("Mayın Tarlası'nın satır sayısını giriniz: ");
            rows = inp.nextInt();

            System.out.print("Mayın Tarlası'nın sütun sayısını giriniz: ");
            cols = inp.nextInt();

            if (rows < 2 || cols < 2) {
                System.out.println("Matris en az 2x2 boyutunda olmalıdır. Lütfen tekrar seçim yapınız!");
            }
        }

        // OOP kullaranak oluşturduğumuz MineMatrix sınıfından bir matris oluşturup oyunumuza başlıyoruz.

        MineMatrix game = new MineMatrix(rows, cols);

        while (true) {
            game.printUserField();

            System.out.print("Lütfen seçtiğiniz satır sayısı giriniz: ");
            int selectedRow = inp.nextInt();
            System.out.print("Lütfen seçtiğiniz sütun sayısı giriniz: ");
            int selectedCol = inp.nextInt();
            System.out.println("====================================== ");

            if (!game.isValidCoordinate(selectedRow, selectedCol)) {
                System.out.println("Geçersiz koordinat girdiniz. Lütfen tekrar giriş yapınız.");
                continue;
            }

            if (game.userField[selectedRow][selectedCol] != '-') {
                System.out.println("Bu koordinat daha önce seçildi. Başka bir koordinat giriniz.");
                continue;
            }

            if (game.isMine(selectedRow, selectedCol)) {
                System.out.println("Mayına bastınız! Oyunu kaybettiniz.");
                game.revealMines();
                game.printUserField();
                break;
            }

            else {
                int adjMines = game.countAdjacentMines(selectedRow, selectedCol);
                game.userField[selectedRow][selectedCol] = (char) (adjMines + '0');

                // Oyunu kazanıp kazanmadığını kontrol etmek için boolean bir kontrol kullanıyoruz.

                boolean allCellsOpened = true;
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        if (game.userField[i][j] == '-' && game.mineField[i][j] != '*') {
                            allCellsOpened = false;
                            break;
                        }
                    }
                    if (!allCellsOpened) {
                        break;
                    }
                }

                if (allCellsOpened) {
                    System.out.println("Tebrikler! Oyunu kazandınız.");
                    game.revealMines();
                    game.printUserField();
                    break;
                }
            }
        }
    }
}


