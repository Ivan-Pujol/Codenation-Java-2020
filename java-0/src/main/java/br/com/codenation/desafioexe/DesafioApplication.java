package br.com.codenation.desafioexe;


import java.util.*;

public class DesafioApplication {

	public static List<Integer> fibonacci() {
		List<Integer> fibo = new ArrayList<Integer>();
		int acc = 0;
		int cur = 1;
		int next;
		int controle = 0;
		int limit = 351;
		fibo.add(0);
		fibo.add(1);
		while (controle < limit ){
			next = acc + cur;
			acc = cur;
			cur = next;
			fibo.add(next);
			controle = next;
		}
		return fibo;
	}

	public static Boolean isFibonacci(Integer a) {
		return fibonacci().contains(a);
	}

}