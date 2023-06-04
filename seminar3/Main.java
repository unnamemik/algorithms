package seminar3;

public class Main {
    public static void main(String[] args) {

        LLRBT<Integer, Integer> tree = new LLRBT<>();
        for (int i = 1; i < 15; i++) {
            tree.add(i, i);
        }
        tree.delete(5);
        System.out.println("Левостороннее красно-черное дерево:");
        tree.print();
    }
}