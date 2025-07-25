import java.util.Random;
import java.util.Arrays;

public class AlgorithmsSJW {

	public static void swap(int[] values, int index1, int index2) {
		int temp = values[index1];
		values[index1] = values[index2];
		values[index2] = temp;
	}

	public static void reverse1(int[] values) {
		for (int i=0; i<values.length/2; i++) {
			int temp = values[i];
			values[i] = values[values.length-1-i];
			values[values.length-1-i] = temp;
		}
	}

    private static void reverse2(int[] values) {
        int temp;
        for (int i=0; i<values.length/2; i++) {
            temp = values[i];
            values[i] = values[values.length-1-i];
            values[values.length-1-i] = temp;
        }
    }

    private static void reverse3(int[] values) {
        for (int i = 0; i < values.length/2; i++) {
            swap(values, i, values.length - 1 - i);
        }
    }

	// merge1: takes two lists and returns sorted list with their contents
	// side effect, original input arrays are sorted also
	public static int[] merge1(int[] list1, int[] list2) {
		Arrays.sort(list1);
		Arrays.sort(list2);
		int[] output = new int[list1.length + list2.length];
		int outIndex = 0, index1 = 0, index2 = 0;
		while (index1<list1.length && index2<list2.length) {
			if (list1[index1]<list2[index2]) output[outIndex++] = list1[index1++];
			else output[outIndex++] = list2[index2++];
		}
		for (int i=index1; i<list1.length; i++) output[outIndex++]=list1[i];
		for (int i=index2; i<list2.length; i++) output[outIndex++]=list2[i];
		return output;
	}

	public static int[] merge2(int[] list1, int[] list2) {
		int[] output = new int[list1.length + list2.length];

		for (int i=0; i<list1.length; i++) output[i] = list1[i];
		for (int i=0; i<list2.length; i++) output[i+list1.length] = list2[i];

		Arrays.sort(output);

		return output;
	}

	public static void randomFill(int[] list) {
		Random gen = new Random();
		for (int i=0; i<list.length; i++) list[i]=gen.nextInt(list.length);
	}

	public static void printArray(int[] list) {
		for (int i=0; i<list.length; i++) {
			if (i%50 == 0) System.out.println();
			System.out.print(list[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {

		int[] reverseLen = {100000000, 200000000, 350000000, 500000000, 550000000, 780000000};
		for (int i=0; i<reverseLen.length; i++) {

            long reverse1total = 0;
            long reverse2total = 0;
            long reverse3total = 0;

			for (int j = 0; j < 5; j++) {

				System.out.println("Reversing an array...");
				int[] testArray = new int[reverseLen[i]];
				randomFill(testArray);
				//printArray(testArray);

				long reverse1Start = System.currentTimeMillis();
				reverse1(testArray);
				long reverse1End = System.currentTimeMillis();
				long reverse1Elapsed = reverse1End - reverse1Start;
                reverse1total += reverse1Elapsed;
				//printArray(testArray);

				long reverse2Start = System.currentTimeMillis();
				reverse2(testArray);
				long reverse2End = System.currentTimeMillis();
				long reverse2Elapsed = reverse2End - reverse2Start;
                reverse2total += reverse2Elapsed;
				//printArray(testArray);

				long reverse3Start = System.currentTimeMillis();
				reverse3(testArray);
				long reverse3End = System.currentTimeMillis();
				long reverse3Elapsed = reverse3End - reverse3Start;
                reverse3total += reverse3Elapsed;
				//printArray(testArray);

				//printArray(testArray);
				System.out.println("reverse1: run " + (j+1) + " of n = " + reverseLen[i] + " complete in " +
						(reverse1Elapsed) + "ms");
				System.out.println("reverse2: run " + (j+1) + " of n = " + reverseLen[i] + " complete in " +
						(reverse2Elapsed) + "ms");
				System.out.println("reverse3: run " + (j+1) + " of n = " + reverseLen[i] + " complete in " +
						(reverse3Elapsed) + "ms");
				System.out.println();
			}

            System.out.println("reverse1 - Average Time for n = " + reverseLen[i] + ": " + (reverse1total/5) + "ms");
            System.out.println("reverse2 - Average Time for n = " + reverseLen[i] + ": " + (reverse2total/5) + "ms");
            System.out.println("reverse3 - Average Time for n = " + reverseLen[i] + ": " + (reverse3total/5) + "ms");
            System.out.println();

		}

		int[] mergeLen = {500000, 10000000, 30000000, 50000000, 80000000, 95000000};
		for (int i=0; i<mergeLen.length; i++) {

            long merge1total = 0;
            long merge2total = 0;

			for (int j = 0; j < 5; j++) {
				System.out.println("Combining two arrays into a larger, sorted array...");
				int[] array1 = new int[mergeLen[i]];
				randomFill(array1);
				//printArray(array1);
				int[] array2 = new int[mergeLen[i]];
				randomFill(array2);
				//printArray(array2);

				long merge2Start = System.currentTimeMillis();
				int[] merged2 = merge2(array1, array2);
				long merge2End = System.currentTimeMillis();
				long merge2Elapsed = merge2End - merge2Start;
                merge2total += merge2Elapsed;

				//printArray(array1);
				//printArray(array2);

				long merge1Start = System.currentTimeMillis();
				int[] merged1 = merge1(array1, array2);
				long merge1End = System.currentTimeMillis();
				long merge1Elapsed = merge1End - merge1Start;
                merge1total += merge1Elapsed;

				//printArray(merged1);
				//printArray(merged2);
				System.out.println("merge1: run " + (j+1) + " of n = " + mergeLen[i] + " complete in " +
						(merge1Elapsed) + "ms");
				System.out.println("merge2: run " + (j+1) + " of n = " + mergeLen[i] + " complete in " +
						(merge2Elapsed) + "ms");
				System.out.println();
			}
            System.out.println("merge1 - Average Time for n = " + mergeLen[i] + ": " + (merge1total/5) + "ms");
            System.out.println("merge2 - Average Time for n = " + mergeLen[i] + ": " + (merge2total/5) + "ms");
            System.out.println();
		}

	}
}