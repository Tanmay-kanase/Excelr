
public class Average {

    public static double[] AverageSubarray(int[] arr, int k) {
        double[] ans = new double[arr.length - k + 1];
        double sum = 0;
        double avg = 0.0;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
            avg = sum / k;
        }
        ans[0] = avg;
        for (int i = k; i < arr.length; i++) {
            sum += arr[i] - arr[i - k];
            avg = sum / k;
            ans[i - k + 1] = avg;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 2, 6, -1, 4, 1, 8, 2 };
        int k = 5;
        double[] newarr = AverageSubarray(arr, k);
        for (int i = 0; i < newarr.length; i++) {
            System.out.print(newarr[i] + " ");
        }
    }
}
