package com.fxdestination.util;

public class GetLevel {
	public static int getLevel(double totalAUD) {
		if (totalAUD < 10000) {
			return 0;
		} else if (totalAUD >= 10000 && totalAUD < 20000) {
			return 1;
		} else if (totalAUD >= 20000 && totalAUD < 50000) {
			return 2;
		} else if (totalAUD >= 50000 && totalAUD < 100000) {
			return 3;
		} else if (totalAUD >= 100000 && totalAUD < 200000) {
			return 4;
		} else {
			return 5;
		}
	}

}
