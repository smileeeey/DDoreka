import java.util.Scanner;

public class KNum {
    public static void main(String[] args) {
        new KNum().service();
    }

    private void service() {
        Scanner scn = new Scanner(System.in);

        int N = scn.nextInt();
        long K = scn.nextLong();

        long start = 0;
        long end = N*N + 1;
        long mid = 0;

        while(start + 1 < end) {
            mid = (start + end) / 2;

            long sum = 0;
            for(int i = 1; i <= N; i++) {
                sum += binarySearch(N, i, mid);
            }
            if(sum > K) {
                end = mid;
                continue;
            }

            sum = 0;
            for(int i = 1; i <= N; i++) {
                sum += binarySearch2(N, i, mid);
            }
            if(sum <= K) {
                start = mid;
                continue;
            }
            break;
        }
        System.out.println(mid);
        scn.close();
    }

    private long binarySearch(int N, int n, long value) {
        long start = n;
        long end = n * N+1;

        while(start + 1 < end) {
            long mid = (start + end) / 2;
            if(mid <= value) {
                start = mid;
            } else {
                end = mid;
            }
        }

        return (end-1) / n;
    }
    private long binarySearch2(int N, int n, long value) {
        long start = n;
        long end = n * N + 1;

        while(start + 1 < end) {
            long mid = (start + end) / 2;
            if(mid <= value) {
                start = mid;
            } else {
                end = mid;
            }
        }

        return N - end / n;
    }
}
