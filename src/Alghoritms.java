import java.sql.SQLData;
import java.util.ArrayList;

public class Alghoritms {
    public static Helper bubleSort(ArrayList<Competitor> array) {
        Helper helper = new Helper();
        helper.counterOfSwaps = 0;
        helper.counterOfCompares = 0;
        helper.before = System.nanoTime();
        for (int i = 0; i < array.size(); i++) {
            for (int j = 0; j < array.size() - i - 1; j++) {
                helper.counterOfCompares++;
                if (array.get(j + 1).getNumberInTable() < array.get(j).getNumberInTable()) {
                    helper.counterOfSwaps++;
                    Competitor swap = array.get(j);
                    array.set(j, array.get(j + 1));
                    array.set(j + 1, swap);

                }
            }
        }
        helper.after = System.nanoTime();
        return helper;
    }


    public static void quickSort(ArrayList<Competitor> array, int low, int high, Helper helper) {
        helper.counterOfCompares++;
        //завершить,если массив пуст или уже нечего делить
        if (array.size() == 0 || low >= high) return;
        //выбираем опорный элемент
        int middle = low + (high - low) / 2;
        int border = array.get(middle).getNumberInTable();
        //разделияем на подмассивы и меняем местами
        int i = low, j = high;
        while (i <= j) {
            helper.counterOfCompares++;
            while (array.get(i).getNumberInTable() < border) i++;
            while (array.get(j).getNumberInTable() > border) j--;
            helper.counterOfCompares++;
            if (i <= j) {
                helper.counterOfSwaps++;
                Competitor swap = array.get(i);
                array.set(i, array.get(j));
                array.set(j, swap);
                i++;
                j--;
            }
        }
        //рекурсия для сортировки левой и правой части
        helper.counterOfCompares++;
        if (low < j) quickSort(array, low, j, helper);
        helper.counterOfCompares++;
        if (high > i) quickSort(array, i, high, helper);
    }

    public static Helper insertionSort(ArrayList<Competitor> array) {
        Helper helper = new Helper();
        helper.before = System.nanoTime();
        int j;
        //сортировку начинаем со второго элемента, т.к. считается, что первый элемент уже отсортирован
        for (int i = 1; i < array.size(); i++) {
            //сохраняем ссылку на индекс предыдущего элемента
            Competitor swap = array.get(i);
            helper.counterOfCompares++;
            for (j = i; j > 0 && swap.getNumberInTable() < array.get(j-1).getNumberInTable(); j--) {
                //элементы отсортированного сегмента перемещаем вперёд, если они больше элемента для вставки
                helper.counterOfSwaps++;
                array.set(j,array.get(j-1));
            }
            helper.counterOfSwaps++;
            array.set(j,swap);
        }
        helper.after = System.nanoTime();
        return helper;
    }



}
