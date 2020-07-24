package siyu.leetcode;

public class No1025 {

    public static void main(String[] args) {
        No1025 no = new No1025();
        for (int i = 1; i < 11; i++) {
            System.out.println(i + "的结果:" + no.divisorGame(i));
        }

    }

    public boolean divisorGame(int N) {

        for (int i = 1; i < N; i++) {
            if (N % i == 0) {
                if ((N - i) % 2 == 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
