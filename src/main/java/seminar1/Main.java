package seminar1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static final String reset = "\u001B[0m";
    public static final String red = "\u001B[31m";
    public static final String yellow = "\u001B[33m";
    public static final String cyan = "\u001B[36m";
    public static Random rand = new Random();
    private static int userInput(String msg) {
        Scanner sc = new Scanner(System.in);
        int inputStr;
        while (true) {
            System.out.print(msg);
            try {
                inputStr = Integer.parseInt(sc.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println(red + "Неверный ввод! Введите целое число!" + reset);
            }
        }
        return inputStr;
    }

    public static int[] arrCreator() {
        int arrSize = userInput("\nВведите размер массива: ");
        int[] arr = new int[arrSize];

        int maxVal = userInput("Введите максимальное значение элементов массива: ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (rand.nextInt(maxVal));
        }
        System.out.println(yellow + "---------------------------------------------" + reset + "\n");
        System.out.println("Исходный массив: " + Arrays.toString(arr));
        System.out.println(yellow + "---------------------------------------------" + reset + "\n");
        return arr;
    }

    private static int LEFT(int i) {
        return (2 * i + 1);
    }

    private static int RIGHT(int i) {
        return (2 * i + 2);
    }

    private static void swap(int[] sortArr, int i, int j) {
        int swap = sortArr[i];
        sortArr[i] = sortArr[j];
        sortArr[j] = swap;
    }

    private static void heapify(int[] sortArr, int i, int size) {
        int left = LEFT(i);
        int right = RIGHT(i);
        int largest = i;

        if (left < size && sortArr[left] > sortArr[i]) largest = left;
        if (right < size && sortArr[right] > sortArr[largest]) largest = right;

        if (largest != i) {
            swap(sortArr, i, largest);
            heapify(sortArr, largest, size);
        }
    }

    public static int pop(int[] sortArr, int size) {
        if (size <= 0) {
            return -1;
        }
        int top = sortArr[0];
        sortArr[0] = sortArr[size - 1];
        heapify(sortArr, 0, size - 1);
        return top;
    }

    public static int[] heapSort(int[] sortArr) {
        int n = sortArr.length;
        int i = (n - 2) / 2;
        while (i >= 0) {
            heapify(sortArr, i--, n);
        }

        while (n > 0) {
            sortArr[n - 1] = pop(sortArr, n);
            n--;
        }
        return sortArr;
    }

    private static void userOutput(int[] array) {
        System.out.println("Сортированный массив: " + Arrays.toString(array));
        System.out.println(yellow + "---------------------------------------------" + reset + "\n");
    }

    public static void main(String args[]) {
        System.out.println(cyan + "Пирамидальная сортировка" + reset);
        userOutput(heapSort(arrCreator()));

    }
}