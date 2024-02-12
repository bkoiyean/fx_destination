package Day2;

public class FibonacciLogn {
	public static int countStep = 0;
	public static void main(String[] args) {
		int num = 100;
		int[] arrayOfFibo = new int[num + 4];
		arrayOfFibo[0] = 0;
		arrayOfFibo[1] = 1;
		arrayOfFibo[2] = 1;
		arrayOfFibo[3] = 2;

		if (num < 3)
			System.out.println("Fibonacci of number " + num + " is: " + arrayOfFibo[num]);
		
		else {
			String binaryStringOfNum = binaryStringOfNum(num);

			int startingNum = startingNum(binaryStringOfNum);
			
			inputFiboUsingBinarySearch(binaryStringOfNum, arrayOfFibo, num, startingNum);
			
			System.out.println("Fibonacci of number " + num + " is: "
					+ arrayOfFibo[num]);
		}
		System.out.println("Step no.: "	+ countStep);

	}
	
	public static void inputFiboUsingBinarySearch(String binaryStringOfNum, int[] arrayOfFibo, int num, int startingNum) {

		while (startingNum<num) {
			int nextNum;
			
			int valueOfBinaryOfNextNum = valueOfBinaryOfNextNum(startingNum, binaryStringOfNum);
			
			if (valueOfBinaryOfNextNum == 0) {
				nextNum = 2 * startingNum;
				arrayOfFibo[nextNum] = arrayOfFibo[startingNum] * arrayOfFibo[startingNum]
						+ 2 * arrayOfFibo[startingNum] * arrayOfFibo[startingNum - 1];
				arrayOfFibo[nextNum - 1] = arrayOfFibo[startingNum] * arrayOfFibo[startingNum]
						+ arrayOfFibo[startingNum - 1] * arrayOfFibo[startingNum - 1];
				startingNum = nextNum;
			} else {
				nextNum = 2 * startingNum + 1;
				arrayOfFibo[nextNum] = 2 * arrayOfFibo[startingNum] * arrayOfFibo[startingNum]
						+ arrayOfFibo[startingNum - 1] * arrayOfFibo[startingNum - 1]
						+ 2 * arrayOfFibo[startingNum] * arrayOfFibo[startingNum - 1];
				arrayOfFibo[nextNum - 1] = arrayOfFibo[startingNum] * arrayOfFibo[startingNum]
						+ 2 * arrayOfFibo[startingNum] * arrayOfFibo[startingNum - 1];
				startingNum = nextNum;
			}
			countStep++;
		}
		
	}

	public static String binaryStringOfNum(int num) {
		int temp = num;
		String result = "";
		while (temp > 0) {
			result += temp % 2;
			temp = temp / 2;
		}

		return result;

	}

	public static int startingNum(String binaryStringOfNum) {
		if (binaryStringOfNum.charAt(binaryStringOfNum.length() - 2) - '0' == 1)
			return 3;
		else
			return 2;

	}

	public static int positionInBinaryMode(int number, String binaryStringOfNum) {

		return binaryStringOfNum.length() - (int) Math.floor(Math.log(number) / Math.log(2)) - 1;

	}

	public static int valueOfBinaryOfNextNum(int number, String binaryStringOfNum) {

		int positionOfNum = positionInBinaryMode(number, binaryStringOfNum);

		int positionOfNextNum = positionOfNum - 1;

		return binaryStringOfNum.charAt(positionOfNextNum) - '0';

	}



}