package seminar2;

import java.util.Scanner;

class backwardList {
    static class Node {
        private Node next;
        private String data;

        public Node(int data) {
            this.data = String.valueOf(data);
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private Node head;

    public boolean isEmpty() {
        return length() == 0;
    }

    public void append(int data) {
        if (head == null) {
            head = new Node(data);
            return;
        }
        tail().next = new Node(data);
    }

    private Node tail() {
        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        return tail;
    }

    public int length() {
        int length = 0;
        Node current = head;

        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    public String getLastNode(int n) {
        Node fast = head;
        Node slow = head;
        int start = 1;

        while (fast.next != null) {
            fast = fast.next;
            start++;

            if (start > n) {
                slow = slow.next;
            }
        }

        return slow.data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Node current = head;
        while (current != null) {
            sb.append(current).append(" ");
            current = current.next;
        }

        if (sb.length() >= 3) {
            sb.delete(sb.length() - 3, sb.length());

        }
        return sb.toString();
    }

}

public class backwardElement {
    public static final String reset = "\u001B[0m";
    public static final String red = "\u001B[31m";
    public static final String yellow = "\u001B[33m";
    public static final String cyan = "\u001B[36m";

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

    public static backwardList listCreator(int listSize) {
        backwardList list = new backwardList();
        for (int i = 0; i <= listSize; i++) {
            list.append(i + 1);
        }
        return list;
    }

    public static void main(String args[]) {
        System.out.println("n-е число с конца односвязного списка\n");
        int listSize = userInput(cyan + "Введите размер списка: " + reset);
        backwardList list = listCreator(listSize);
        System.out.println(yellow + "Связный список : " + list + reset);
        int node = userInput(cyan + "\nВведите узел с конца списка: " + reset);
        System.out.println(yellow + "Выбранный узел: " + list.getLastNode(node + 1) + reset);
    }


}