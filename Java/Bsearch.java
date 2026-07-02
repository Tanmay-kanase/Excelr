import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Bsearch {
	public static boolean binaryS(int[] arr, int ele) {
		int start = 0;
		int end = arr.length - 1;

		while (start <= end) {
			int mid = start + (end - start) / 2;

			if (arr[mid] == ele)
				return true;

			if (arr[mid] > ele) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return false;
	}

	public static boolean isPrime(int num) {
		if (num <= 1)
			return false;
		if (num == 2)
			return true;

		for (int i = 3; i * i <= num; i++) {
			if (num % i == 0)
				return false;
		}

		return true;
	}

	public static int MaxSumSubArray(int[] arr, int k) {
		int maxSum = 0;
		int winSum = 0;

		for (int i = 0; i < k; i++) {
			maxSum += arr[i];
		}
		winSum = maxSum;
		for (int i = k; i < arr.length; i++) {
			winSum += arr[i] - arr[i - k];
			maxSum = Math.max(maxSum, winSum);
		}

		return maxSum;
	}

	public static boolean isVowel(char ch) {
		Set<Character> set = new HashSet<>();

		set.add('a');
		set.add('i');
		set.add('o');
		set.add('u');
		set.add('e');

		return set.contains(ch);
	}

	public static int maxSubStringVowlels(String s, int k) {
		int max = 0;
		int count = 0;
		for (int i = 0; i < k; i++) {
			if (isVowel(s.charAt(i))) {
				count++;
			}
		}
		for (int i = k; i < s.length(); i++) {
			if (isVowel(s.charAt(i - k))) {
				count--;
			}

			if (isVowel(s.charAt(i))) {
				count++;
			}

			max = Math.max(max, count);
		}
		return max;
	}

	public static int MaxSumSub(String s) {
		Set<Character> set = new HashSet<>();
		int maxLen = 0;
		int left = 0;

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			while (set.contains(ch)) {
				set.remove(s.charAt(left));
				left++;
			}
			set.add(ch);
			maxLen = Math.max(maxLen, i - left + 1);
		}
		return maxLen;
	}

	public static int firstNeg(int[] arr, int start, int end) {
		for (int i = start; i < end; i++) {
			if (arr[i] < 0) {
				return arr[i];
			}
		}
		return 0;
	}

	public static int[] FirstNegativeInWindow(int[] arr, int k) {

		Queue<Integer> qu = new LinkedList<>();
		List<Integer> temp = new ArrayList<>();
		int[] ans = new int[arr.length - k + 1];
		int idx = 0;
		// first window
		for (int i = 0; i < k; i++) {
			if (arr[i] < 0) {
				qu.offer(i);
			}
		}
		if (!qu.isEmpty()) {
			ans[idx++] = arr[qu.peek()];
		} else {
			ans[idx++] = 0;
		}
		for (int i = k; i < arr.length; i++) {
			if (!qu.isEmpty() && i - k == qu.peek()) {
				qu.poll();
			}
			if (arr[i] < 0) {
				qu.offer(i);
			}
			if (!qu.isEmpty()) {
				ans[idx++] = arr[qu.peek()];
			} else {
				ans[idx++] = 0;
			}
		}
		return ans;
	}

	// first window int[] arr = { 12, -1, -7, 8, -15, 30, 16, 24 };
	// Queue<Integer> q = new LinkedList<>();
	// int[] ans = new int[arr.length - k + 1];
	// for (int i = 0; i < k; i++) {
	// if (arr[i] < 0)
	// q.offer(i);
	// }

	// for (int i = k; i <= arr.length; i++) {
	// if (q.isEmpty()) {
	// ans[i - k] = 0;
	// } else {
	// ans[i - k] = arr[q.peek()];
	// }

	// while (!q.isEmpty() && q.peek() <= (i - k)) {
	// q.poll();
	// }

	// if (i < arr.length && arr[i] < 0) {
	// q.offer(i);
	// }
	// }

	// return ans;

	public static void BubbleSort(int[] arr) {

	}

	public static void MergeSort(int[] arr, int start, int end) {
		if (start >= end) {
			return;
		}
		int mid = start + (end - start) / 2;

		// left half
		MergeSort(arr, start, mid);

		// right half
		MergeSort(arr, mid + 1, end);

		Merge(arr, start, end, mid);
	}

	public static void Merge(int[] arr, int start, int end, int mid) {
		List<Integer> temp = new ArrayList<>();
		int i = start;
		int j = mid + 1;
		while (i <= mid && j <= end) {
			if (arr[i] <= arr[j]) {
				temp.add(arr[i]);
				i++;
			} else {
				temp.add(arr[j]);
				j++;
			}
		}
		while (i <= mid) {
			temp.add(arr[i]);
			i++;
		}
		while (j <= end) {
			temp.add(arr[j]);
			j++;
		}

		Iterator it = temp.iterator();

		for (int k = 0; k < temp.size(); k++) {
			arr[start + k] = temp.get(k);
		}
	}

	public static void main(String[] args) {
		int[] arr = { 12, -1, -7, 8, -15, 30, 16, 24, -12 };
		int[] arr1 = { 30, 16, 10, 24 };
		int k = 3;
		String str = "abcabcbbooo";
		// System.out.println("Maxlen of vowel " + maxSubStringVowlels(str, k));
		// System.out.println(Arrays.toString(FirstNegativeInWindow(arr, k)));
		// int[] arr =
		int[] newarr = FirstNegativeInWindow(arr, k);
		for (int i = 0; i < newarr.length; i++) {
			System.out.print(newarr[i] + " ");
		}
		// System.out.println("Maximum sum of sub array : " + MaxSumSubArray(arr, k));
		// System.out.print("Maximum Sum : " + MaxSumSub(str));
		// MergeSort(arr1, 0, arr1.length - 1);
		// System.out.println(Arrays.toString(arr1));
		// if (isPrime(17)) {
		// System.out.println("21 isdfsfaf prime");
		// } else {
		// System.out.println("Not prsdfdfasfime");
		// }
	}

}
