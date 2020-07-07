package br.com.codenation.calculadora;


public class CalculadoraSalario {
	public double calculusBase = 1039.00;
	public long calcularSalarioLiquido(double salarioBase) {
		//Use o Math.round apenas no final do método para arredondar o valor final.
		//Documentação do método: https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#round-double-

		double newBaseSalary =  calcularInss(salarioBase);

		if (newBaseSalary < 3000.01){
			return Math.round(newBaseSalary);
		}
		if (newBaseSalary > 3000.00 && newBaseSalary < 6000.01){
			double irpf = getIRPF(newBaseSalary);
			return Math.round(newBaseSalary - irpf);
		}
		double irpf = getIRPF(newBaseSalary);
		return Math.round(newBaseSalary - irpf);
	}
	//Exemplo de método que pode ser criado para separar melhor as responsábilidades de seu algorítmo
	private double calcularInss(double salarioBase) {
		if (salarioBase < calculusBase){
			return 0.0;
		}
		if(salarioBase >= calculusBase && salarioBase< 1500.01){
			double inss = salarioBase * 8/100;
			return salarioBase-inss;
		}
		if(salarioBase > 1500.00 && salarioBase< 4000.01){
			double inss = salarioBase* 9/100;
			return salarioBase-inss;
		}
		double inss = salarioBase * 11/100;
		return salarioBase-inss;
	}
	private double getIRPF( double newBaseSalary){
		if (newBaseSalary > 3000.00 && newBaseSalary < 6000.01){
			double irpf = newBaseSalary * 7.5/100;
			return irpf;
		}
		if (newBaseSalary > 6000) {
			double irpf = newBaseSalary * 15 / 100;
			return irpf;
		}
		return newBaseSalary;
	}

}
/*Dúvidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar! 
*/