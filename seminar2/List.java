package seminar2;

import java.util.Scanner;

class ListElement {
    ListElement next;
    int data;
}

public class List {
    public static final String reset = "\u001B[0m";
    public static final String red = "\u001B[31m";
    public static final String yellow = "\u001B[33m";
    public static final String cyan = "\u001B[36m";

    private ListElement head;
    private ListElement tail;
    private boolean empty;

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

    public void addElement(int data) {
        ListElement a = new ListElement();
        a.data = data;
        if (head == null) {
            head = a;
            tail = a;
        } else {
            a.next = head;
            head = a;
        }
    }
    public void printList() {
        ListElement t = head;
        while (t != null) {
            System.out.print(t.data + " ");
            t = t.next;
        }
    }

    public void reverseList() {
        if (isEmpty())
            return;

        ListElement reversedPart = null;
        ListElement current = head;
        while (current != null) {
            ListElement next = current.next;
            current.next = reversedPart;
            reversedPart = current;
            current = next;
        }
        head = reversedPart;
    }

    public static List listCreator(int listSize) {
        List list = new List();
        for (int i = 1; i<=listSize; i++){
            list.addElement(i);
        }
        return list;
    }

    public boolean isEmpty() {
        return empty;
    }

    public static void main(String[] args) {
        System.out.println("Разворот связного списка\n");
        int listSize = userInput(cyan + "Введите размер списка: " + reset);
        List myList = listCreator(listSize);
        System.out.println(yellow + "До разворота " + reset);
        myList.printList();
        myList.reverseList();
        System.out.println(yellow + "\nПосле" + reset);
        myList.printList();
    }
}


