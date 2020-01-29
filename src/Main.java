public class Main {

    public static void main(String[] args) {
	    DLList<Integer> test = new DLList<>();
        test.add(0, 1);
        test.add(1, 2);
        test.add(2, 3);
        test.add(1, 4);
        test.add(4, 5);
        test.add(0, 99);
        test.add(6, 6);
        test.add(7, 7);
        test.add(8, 8);
        test.set(3, 72);
        test.remove(0);
        test.remove(7);

        System.out.println(test.find(69));
     //   System.out.println(test.size());
    }
}
