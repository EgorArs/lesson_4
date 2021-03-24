import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class lesson_4 {
    private static final Random RANDOM = new Random();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final char DOT_EMPTY = '.';
    private static char[][] field;
    private static final char DOT_HUMAN = 'X';


    private static final char DOT_AI = '0';
    private static int fieldSizeX;
    private static int fieldSizeY;

    public static void main(String[] args) {
        fieldSizeY = 3;
        fieldSizeX = 3;
        while (true) {
            initField();
            printField();

            while (true) {
                humanTurn();
                printField();
                if (gameCheck(DOT_HUMAN, "Вы победили!!!")) break;
                aiTurn();
                printField();
               if (gameCheck(DOT_AI, "Комп победил!!!")) break;
            }
            System.out.println("Желаете ли вы повторить?>>> Y or N>>");
            if (!SCANNER.next().toLowerCase().equals("y")) break;
        }
    }
    private static boolean gameCheck ( char dot, String s ){
        if (checkWin(dot)){
            System.out.println(s);
            return true;
        }
        if (checkDraw()){
            System.out.println("DRAW!!!");
            return true;
        }
        return false;
    }

    private static boolean checkWin(char c) {
        if (field[0][0] == c && field[0][1] == c && field[0][2] == c) return true;
        if (field[1][0] == c && field[1][1] == c && field[1][2] == c) return true;
        if (field[2][0] == c && field[2][1] == c && field[2][2] == c) return true;

        if (field[0][0] == c && field[1][0] == c && field[2][0] == c) return true;
        if (field[0][1] == c && field[1][1] == c && field[2][1] == c) return true;
        if (field[0][2] == c && field[1][2] == c && field[2][2] == c) return true;

        if (field[0][0] == c && field[1][1] == c && field[2][2] == c) return true;
        if (field[0][2] == c && field[1][1] == c && field[2][0] == c) return true;
        return false;
    }// В этой домашке я практически ничего не понял так как обучаюсь с нуля
    

    private static boolean checkDraw(){
        for (int y = 0; y <fieldSizeY ; y++) {
            for (int x = 0; x <fieldSizeX ; x++) {
                if (isCellEmpty(x,y)) return false;

            }

        }
        return true;
    }

    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты x и y через пробел >>>>>");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isCellValid(x, y) || !isCellEmpty(x, y));

        field[y][x] = DOT_HUMAN;
    }

    private static void aiTurn() {
        int x, y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isCellEmpty(x, y));

        field[y][x] = DOT_AI;
    }

    private static boolean isCellValid(int x, int y) {
        return x >= 0 && y >= 0 && x < fieldSizeX && y < fieldSizeY;

    }

    private static boolean isCellEmpty(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }


    private static void initField() {
        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;

            }
        }
    }

    private static void printField() {
        System.out.print("+");
        for (int i = 0; i < fieldSizeX * 2 + 1; i++)
            System.out.print((i % 2 == 0) ? "-" : i / 2 + 1);
        System.out.println();


        for (int i = 0; i < fieldSizeY; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < fieldSizeX; j++)
                System.out.print(field[i][j] + "|");
            System.out.println();
        }
        for (int i = 0; i <= fieldSizeX * 2 + 1; i++)
            System.out.print("-");
        System.out.println();

    }
//        for (int y = 0; y < fieldSizeY; y++) {
//            for (int x = 0; x < fieldSizeX; x++) {
//                System.out.print(field[y][x]+ " ");
//
//            }
//            System.out.println();
//
//        }
}


