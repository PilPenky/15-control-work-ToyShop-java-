import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ToyService implements DataService {
    @Override
    public void addNewToy(Toy toy) throws IOException {
        List<String> arrData = new ArrayList();
        if (toy.toyWeight < 1) {
            throw new MyLessThanZeroException("Ошибка! Указан недопустимый вес игрушки: ", toy.toyWeight);
        }
        if (toy.toyCount < 1) {
            throw new MyLessThanZeroException("Ошибка! Указано недопустимое количество игрушек: ", (double) toy.toyCount);
        }
        arrData.add(String.valueOf(toy.toyId));
        arrData.add(toy.toyName);
        arrData.add(String.valueOf(toy.toyCount));
        arrData.add(String.valueOf(toy.toyWeight));
        writeFile("AllToys.txt", arrData);
    }

    @Override
    public void changesWeightToy(Integer id, Double weight) throws IOException {
        String[][] allToysArray = readFile("AllToys.txt");
        if (weight < 1) {
            throw new MyLessThanZeroException("Ошибка! Указан недопустимый вес игрушки: ", weight);
        }
        try {
            allToysArray[id][3] = String.valueOf(weight);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка! Указан не существующий ID игрушки: " + id);
        }
        overwriting("AllToys.txt", allToysArray);
    }

    public void changesCountToy(String file, int ind) throws IOException {
        String[][] allToysArray = readFile(file);
        allToysArray[ind][2] = String.valueOf(Integer.valueOf(allToysArray[ind][2]) - 1);
        overwriting(file, allToysArray);
    }

    public void overwriting(String file, String[][] arrData) throws IOException {
        FileWriter writer = new FileWriter(file);
        for (int i = 0; i < arrData.length; i++) {
            if (i != arrData.length - 1) {
                writer.write(Arrays.toString(arrData[i]).replaceAll("^\\[|\\]$", "").replace(" ", "") + "\n");
            } else {
                writer.write(Arrays.toString(arrData[i]).replaceAll("^\\[|\\]$", "").replace(" ", ""));
            }
        }
        writer.flush();
        writer.close();
    }

    public Integer searchId(String file) throws FileNotFoundException {
        String[][] allToysArray = readFile(file);
        Integer nextId;
        if (allToysArray.length == 0) {
            nextId = 1;
        } else {
            nextId = Integer.valueOf(allToysArray[allToysArray.length - 1][0]) + 1;
        }
        return nextId;
    }

    public void changeId(String file) throws IOException {
        String[][] allToysArray = readFile(file);
        for (int i = 0; i < allToysArray.length; i++) {
            for (int j = 0; j < 1; j++) {
                int id = i + 1;
                allToysArray[i][j] = String.valueOf(id);
            }
        }
        overwriting("Waiting list.txt", allToysArray);
    }

    public void getToy() throws IOException {
        String[][] allToysArray = readFile("Waiting list.txt");

        if (allToysArray.length == 0) {
            throw new MyIsEmptyException();
        } else {
            int nextId = searchId("Received toys.txt");
            List<String> arrData = new ArrayList();
            for (int i = 0; i < 4; i++) {
                if (i == 0) {
                    arrData.add(String.valueOf(nextId));
                } else if (i == 2) {
                    arrData.add(String.valueOf(1));
                } else arrData.add(allToysArray[0][i]);
            }
            writeFile("Received toys.txt", arrData);
            deleteFirstLine();
        }
    }

    public void deleteFirstLine() throws IOException {
        String[][] allToysArray = readFile("Waiting list.txt");
        String[][] newAllToysArray = new String[allToysArray.length - 1][allToysArray[0].length];

        for (int i = 0; i < newAllToysArray.length; i++) {
            for (int j = 0; j < newAllToysArray[0].length; j++) {
                newAllToysArray[i][j] = allToysArray[i + 1][j];
            }
        }
        overwriting("Waiting list.txt", newAllToysArray);
        changeId("Waiting list.txt");
    }

    public void writeFile(String file, List arrData) throws IOException {
        String[][] allToysArray = readFile(file);
        Path path = Paths.get(file);

        if (allToysArray.length != 0) {
            Files.write(path, "\n".getBytes(), StandardOpenOption.APPEND);
        }
        Files.write(path, (arrData.get(0) + ",").getBytes(), StandardOpenOption.APPEND);
        Files.write(path, (arrData.get(1) + ",").getBytes(), StandardOpenOption.APPEND);
        Files.write(path, (arrData.get(2) + ",").getBytes(), StandardOpenOption.APPEND);
        Files.write(path, (arrData.get(3) + "").getBytes(), StandardOpenOption.APPEND);
    }

    public String[][] readFile(String file) throws FileNotFoundException {
        Scanner scannerCount = new Scanner(new File(file));
        int x = 0;
        while (scannerCount.hasNextLine()) {
            String line = scannerCount.nextLine();
            x += 1;
        }
        scannerCount.close();

        Scanner scanner = new Scanner(new File(file));
        String[][] twoDimArray = new String[x][4];
        int i = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] temp = line.split(",");
            for (int j = 0; j < temp.length; j++) {
                twoDimArray[i][j] = temp[j];
            }
            i += 1;
        }
        scanner.close();
        return twoDimArray;
    }

    public void toString(String[][] twoDimArray) {
        for (String[] strings : twoDimArray) {
            System.out.println(Arrays.deepToString(strings).replaceAll("^\\[|\\]$", "").replace(",", ""));
        }
    }
}