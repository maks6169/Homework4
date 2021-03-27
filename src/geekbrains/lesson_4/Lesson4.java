package geekbrains.lesson_4;

import java.util.Random;
import java.util.Scanner;

public class Lesson4 {
    private static final Random RANDOM = new Random();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '.';
    private static char[][] field;
    private static int fieldSizeX;
    private static int fieldSizeY;

    public static void main(String[] args) {
        fieldSizeX = 3;
        fieldSizeY = 3;
        initField();
        printField();

        while (true) {
            humanTurn();
            printField();
            if (checkWin(DOT_HUMAN)) {
                System.out.println("Вы выиграли!");
                break;
            }
            if (checkDraw()) {
                System.out.println("DRAW!");
                break;
            }
            aiTurn();
            printField();
            if (checkWin(DOT_AI)) {
                System.out.println("Победил компьютер!");
                break;
            }
        }
    }

    private static boolean checkWin(char c) {

        for (int y = 0; y < fieldSizeY; y++) {
            int x = 0;
            if (field[y][x] == c && field[y][x + 1] == c && field[y][x + 2] == c) return true;
        }
        for (int x = 0; x < fieldSizeX; x++) {
            int y = 0;
            if (field[y][x] == c && field[y + 1][x] == c && field[y + 2][x] == c) return true;
        }
        for (int x = 0; x < fieldSizeX; x++) {
            if (field[x][x] == c && field[x + 1][x + 1] == c && field[x + 2][x + 2] == c) return true;
        }
        return false;
    }

    private static boolean checkDraw() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isCellEmpty(x, y)) return false;
            }
        }
        return true;
    }

    private static void humanTurn() {
        int x, y;
        do {
            System.out.print("Введите координаты x и y через пробел >>>>>");
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
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                System.out.print(field[y][x] + " ");
            }
            System.out.println();
        }
    }
}

