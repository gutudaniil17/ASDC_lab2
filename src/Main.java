import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        File fileInput = new File("ASDC_lab2/src/MOCK_DATA.csv");
        ArrayList<Competitor> array = Competitor.input(fileInput);

        Collections.shuffle(array);
        System.out.println("Сортировка пузырьком");
        System.out.println("Сложность алгоритма и теоретическое время выполнения - O(n^2)");
        Helper bubleHelper = Alghoritms.bubleSort(array);
        System.out.println("Кол-во сравнений ==> " + bubleHelper.counterOfCompares);
        System.out.println("Кол-во перестановок ==> " + bubleHelper.counterOfSwaps);
        System.out.println("Скорость выполнения в наносекундах ==> " + (bubleHelper.after-bubleHelper.before));

        Collections.shuffle(array);
        System.out.println("\nБытрая сортировка");
        System.out.println("Сложность алгоритма и теоретическое время выполнения - O(n*log(n))");
        Helper quickHelper = new Helper();
        quickHelper.before = System.nanoTime();
        Alghoritms.quickSort(array,0,array.size() - 1,quickHelper);
        quickHelper.after = System.nanoTime();
        System.out.println("Кол-во сравнений ==> " + quickHelper.counterOfCompares);
        System.out.println("Кол-во перестановок ==> " + quickHelper.counterOfSwaps);
        System.out.println("Скорость выполнения в наносекундах ==> " + (quickHelper.after-quickHelper.before));

        Collections.shuffle(array);
        System.out.println("\nСортировка вставками");
        System.out.println("Сложность алгоритма и теоретическое время выполнения - O(n^2)");
        Helper insertionHelper = Alghoritms.insertionSort(array);
        System.out.println("Кол-во сравнений ==> " + insertionHelper.counterOfCompares);
        System.out.println("Кол-во перестановок ==> " + insertionHelper.counterOfSwaps);
        System.out.println("Скорость выполнения в наносекундах ==> " + (insertionHelper.after-insertionHelper.before));


    }
}