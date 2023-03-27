package minesweeper;

public class Main {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("How many mines do you want on the field? ");
        int mines = Integer.parseInt(scanner.nextLine());
        boolean win = true;
        String[][] field = new String[12][12];
        initializationField(field);
        fillWithMines(field, mines);
        fillWithQtyOfMines(field);
        String[][] fieldForGamer = new String[12][12];
        initializationField(fieldForGamer);
        vizualizeFull(fieldForGamer);
        vizualizeFull(field);

        int i = 0;
        while (i != mines) {
            System.out.print("Set/delete mines marks (x and y coordinates): ");
            String[] scannedLine = scanner.nextLine().split(" ");
            int y = Integer.parseInt(scannedLine[1]) + 1;
            int x = Integer.parseInt(scannedLine[0]) + 1;
            String command = scannedLine[2];
            if (fieldForGamer[y][x].equals("*") && command.equals("mine")) {
                fieldForGamer[y][x] = ".";
                vizualizeFull(fieldForGamer);
            } else if (field[y][x].equals("X") && command.equals("mine")) {
                fieldForGamer[y][x] = "*";
                ++i;
                vizualizeFull(fieldForGamer);
            } else if (fieldForGamer[y][x].equals(".") && command.equals("mine")) {
                fieldForGamer[y][x] = "*";
                vizualizeFull(fieldForGamer);
            } else if (field[y][x].equals("X") && command.equals("free")) {
                defeatOutput(field, fieldForGamer);
                vizualizeFull(fieldForGamer);
                System.out.println("You stepped on a mine and failed!");
                win = false;
                break;
            } else if (field[y][x].equals(".") && command.equals("free")) {
                fill(field, y, x, ".", "/");
                floodFillCopy(field, fieldForGamer);
                vizualizeFull(fieldForGamer);
            } else if (!field[y][x].equals(".") && !field[y][x].equals("X") && !field[y][x].equals("/") && command.equals("free")) {
                fieldForGamer[y][x] = field[y][x];
                vizualizeFull(fieldForGamer);
            }
        }
        if (win) System.out.println("Congratulations! You found all the mines!");
        scanner.close();
    }

    public static void fillWithMines(String[][] field, int mines) {
        int count = 0;
        java.util.Random random = new java.util.Random();
        while (count < mines) {
            int vert = random.nextInt(9) + 2;
            int hori = random.nextInt(9) + 2;
            if (field[vert][hori].equals("X")) continue;
            else field[vert][hori] = "X";
            count++;
        }
    }

    public static void vizualizeFull(String[][] field) {
        for (String[] strings : field) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.print("\n");
        }
    }

    public static void fillWithQtyOfMines(String[][] field) {
        for (int i = 2; i < field.length - 1; i++) {
            for (int j = 2; j < field[i].length - 1; j++) {
                int num = 0;
                if (field[i][j].equals(".")) {
                    if (i > 2 && i < field.length - 2 && j > 2 && j < field[i].length - 2) {
                        if (field[i - 1][j - 1].equals("X")) {
                            num++;
                        }
                        if (field[i - 1][j].equals("X")) {
                            num++;
                        }
                        if (field[i - 1][j + 1].equals("X")) {
                            num++;
                        }
                        if (field[i][j - 1].equals("X")) {
                            num++;
                        }
                        if (field[i][j + 1].equals("X")) {
                            num++;
                        }
                        if (field[i + 1][j - 1].equals("X")) {
                            num++;
                        }
                        if (field[i + 1][j].equals("X")) {
                            num++;
                        }
                        if (field[i + 1][j + 1].equals("X")) {
                            num++;
                        }
                    } else if (i == 2 && j == 2) {
                        if (field[i + 1][j].equals("X")) {
                            num++;
                        }
                        if (field[i][j + 1].equals("X")) {
                            num++;
                        }
                        if (field[i + 1][j + 1].equals("X")) {
                            num++;
                        }
                    } else if (i == field.length - 2 && j == 2) {
                        if (field[i - 1][j].equals("X")) {
                            num++;
                        }
                        if (field[i][j + 1].equals("X")) {
                            num++;
                        }
                        if (field[i - 1][j + 1].equals("X")) {
                            num++;
                        }
                    } else if (i == 2 && j == field[i].length - 2) {
                        if (field[i + 1][j].equals("X")) {
                            num++;
                        }
                        if (field[i][j - 1].equals("X")) {
                            num++;
                        }
                        if (field[i + 1][j - 1].equals("X")) {
                            num++;
                        }
                    } else if (i == field.length - 2 && j == field[i].length - 2) {
                        if (field[i - 1][j].equals("X")) {
                            num++;
                        }
                        if (field[i][j - 1].equals("X")) {
                            num++;
                        }
                        if (field[i - 1][j - 1].equals("X")) {
                            num++;
                        }
                    } else if (i == 2 && j < field[i].length - 2 && j > 2) {
                        if (field[i][j - 1].equals("X")) {
                            num++;
                        }
                        if (field[i][j + 1].equals("X")) {
                            num++;
                        }
                        if (field[i + 1][j + 1].equals("X")) {
                            num++;
                        }
                        if (field[i + 1][j].equals("X")) {
                            num++;
                        }
                        if (field[i + 1][j - 1].equals("X")) {
                            num++;
                        }
                    } else if (i == field.length - 2 && j < field[i].length - 2 && j > 2) {
                        if (field[i][j - 1].equals("X")) {
                            num++;
                        }
                        if (field[i][j + 1].equals("X")) {
                            num++;
                        }
                        if (field[i - 1][j + 1].equals("X")) {
                            num++;
                        }
                        if (field[i - 1][j].equals("X")) {
                            num++;
                        }
                        if (field[i - 1][j - 1].equals("X")) {
                            num++;
                        }
                    } else if (i > 2 && i < field.length - 2 && j == field[i].length - 2) {
                        if (field[i - 1][j].equals("X")) {
                            num++;
                        }
                        if (field[i + 1][j].equals("X")) {
                            num++;
                        }
                        if (field[i - 1][j - 1].equals("X")) {
                            num++;
                        }
                        if (field[i][j - 1].equals("X")) {
                            num++;
                        }
                        if (field[i + 1][j - 1].equals("X")) {
                            num++;
                        }
                    } else if (i > 2 && i < field.length - 2 && j == 2) {
                        if (field[i - 1][j].equals("X")) {
                            num++;
                        }
                        if (field[i + 1][j].equals("X")) {
                            num++;
                        }
                        if (field[i - 1][j + 1].equals("X")) {
                            num++;
                        }
                        if (field[i][j + 1].equals("X")) {
                            num++;
                        }
                        if (field[i + 1][j + 1].equals("X")) {
                            num++;
                        }
                    }
                }
                if (num > 0) field[i][j] = String.valueOf(num);
            }
        }
    }

    public static void initializationField(String[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = " ";
                if (j == 1 || j == 11) {
                    field[i][j] = "|";
                }
                if (j > 1 && j < 11 && i == 0) {
                    field[i][j] = String.valueOf(j - 1);
                }
                if (i == 1 || i == 11) {
                    if (j == 0 || (j > 1 && j < 11)) {
                        field[i][j] = "-";
                    }
                }
                if (j == 0 && (i > 1 && i < 11)) {
                    field[i][j] = String.valueOf(i - 1);
                }
                if (i > 1 && i < 11 && j > 1 && j < 11) {
                    field[i][j] = ".";
                }
            }
        }
    }

    public static void clearFieldForOutput(String[][] fieldForGamer) {
        for (int i = 0; i < fieldForGamer.length; i++) {
            for (int j = 0; j < fieldForGamer[i].length; j++) {
                if (fieldForGamer[i][j].equals("X")) fieldForGamer[i][j] = ".";
            }
        }
    }

    public static void fill(String[][] fieldForGamer, int y, int x, String oldChar, String newChar) {
        if (y < 0 || x < 0 || y >= fieldForGamer.length || x >= fieldForGamer[0].length || !fieldForGamer[y][x].equals(oldChar)) {
            return;
        }
        fieldForGamer[y][x] = newChar;
        fill(fieldForGamer, y - 1, x, oldChar, newChar);
        fill(fieldForGamer, y + 1, x, oldChar, newChar);
        fill(fieldForGamer, y, x - 1, oldChar, newChar);
        fill(fieldForGamer, y, x + 1, oldChar, newChar);
    }

    public static void floodFillCopy(String[][] field, String[][] fieldForGamer) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j].equals("/")) fieldForGamer[i][j] = field[i][j];
            }
        }
        for (int i = 1; i < field.length - 1; i++) {
            for (int j = 1; j < field[i].length - 1; j++) {
                if (field[i + 1][j].equals("/") || field[i - 1][j].equals("/")
                        || field[i][j + 1].equals("/") || field[i][j - 1].equals("/")
                        || field[i + 1][j + 1].equals("/") || field[i + 1][j - 1].equals("/")
                        || field[i - 1][j - 1].equals("/") || field[i - 1][j + 1].equals("/"))
                    fieldForGamer[i][j] = field[i][j];
            }
        }
    }

    public static void defeatOutput(String[][] field, String[][] fieldForGamer) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j].equals("X")) fieldForGamer[i][j] = field[i][j];
            }
        }
    }
}