package br.com.bradesco.frete.api.helpers;

public class RegiaoHelper {

	
	public static boolean isSudeste(Integer cepNumerico){
		return cepNumerico>=1000000 && cepNumerico<=39999999;
	}
	
	public static boolean isNordeste(Integer cepNumerico){
		return cepNumerico>=40000000 && cepNumerico<=65999999;
	}
	
	public static boolean isSul(Integer cepNumerico){
		return cepNumerico>=80000000 && cepNumerico<=99999999;
	}
	
	public static boolean isNorte(Integer cepNumerico){
		return ((cepNumerico>=66000000 && cepNumerico<=69999999) || (cepNumerico>=76800000 && cepNumerico<=77999999));
	}
}
