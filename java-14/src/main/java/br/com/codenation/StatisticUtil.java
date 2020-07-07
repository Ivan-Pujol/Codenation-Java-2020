package br.com.codenation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StatisticUtil {
	public static int getStarted(int number){
		boolean isEven;
		int start;
		isEven = getEvenOdd(number);
		if (isEven){
			start = (number - 2) / 2;
		}else{
			start = (number - 1) / 2;
		}
		return start;
	}
	public static boolean getEvenOdd(int number){
		if (number%2==0){
			return true;
		}
		return false;
	}

	public static int average(int[] elements) {
		int control = 0;
		for (int i = 0; i < elements.length; i++) {
			control += elements[i];
		}
		return control/elements.length;
	}

	public static int mode(int[] elements) {
		int moda= 0;
		int modaCount = 0;
		int objectElement = 0;
		int repetition = 0;

		for (int i = 0; i <elements.length ; i++) {
			objectElement = elements[i];
			for (int j = 0; j <elements.length ; j++) {
				if (objectElement == elements[j]){
					repetition++;
				}
			}
			if (repetition > modaCount){
				modaCount = repetition;
				moda = objectElement;
			}
			repetition =0;
		}
		return moda;
	}

	public static int median(int[] elements) {
		int start = 0;
		int media =0;
		int width = elements.length;
		start = getStarted(width);
		boolean isEven = getEvenOdd(width);
		Arrays.sort(elements);
		if (!isEven){
			media = elements[start];
		}

		if (isEven){
			media = (elements[start] + elements[start+1]) / 2;
		}
		return media;
	}

}