import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FindNum {
    public static void main(String[] args) {
        try {
            new FindNum().service();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void service() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int[] origins = new int[N];
        for(int i = 0; i < N; i++) {
            origins[i] = Integer.parseInt(input[i]);
        }
        int M = Integer.parseInt(br.readLine());
        input = br.readLine().split(" ");
        int[] findNums = new int[M];
        for(int i = 0; i < M; i++) {
            findNums[i] = Integer.parseInt(input[i]);
        }

        int[] tmpOrigins = new int[N];
        mergeSort(origins, tmpOrigins, 0, N-1);

        int[] res = binarySearch(origins, findNums);

        print(res);

        br.close();
    }

    private void mergeSort(int[] origins, int[] tmpOrigins, int start, int end) {
        if(start >= end) {
            tmpOrigins[start] = origins[start];
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(origins, tmpOrigins, start, mid);
        mergeSort(origins, tmpOrigins, mid+1, end);

        int startP = start; int endP = mid+1; int resP = start;

        while(startP <= mid && endP <= end) {
            if(tmpOrigins[startP] >= tmpOrigins[endP]) {
                origins[resP++] = tmpOrigins[endP++];
            } else {
                origins[resP++] = tmpOrigins[startP++];
            }
        }
        while(startP <= mid) {
            origins[resP++] = tmpOrigins[startP++];
        }
        while(endP <= end) {
            origins[resP++] = tmpOrigins[endP++];
        }

        for(int i = start; i <= end; i++) {
            tmpOrigins[i] = origins[i];
        }
    }

    private int[] binarySearch(int[] origins, int[] findNums) {

        int len = findNums.length;

        int[] res = new int[len];

        for(int i = 0; i < len; i++) {
            int start = -1;
            int end = origins.length-1;
            int mid = 0;
            while(start + 1 < end) {
                mid = (start + end) / 2;

                if(origins[mid] < findNums[i]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }

            if(origins[end] == findNums[i]) {
                res[i] = 1;
            } else {
                res[i] = 0;
            }
        }

        return res;
    }

    private void print(int[] res) {
        int len = res.length;
        for(int i = 0; i < len; i++) {
            System.out.println(res[i]);
        }
    }
}
