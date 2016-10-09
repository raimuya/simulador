package modelo.distribuicoes;

public final class Distribuicao {
	
    public static double unif(double a, double b){
    	double aleatorio = Math.random();
        return a+(b-a)*aleatorio;
    }
    
    public static double tria(double a, double b, double c){
    	double aleatorio = Math.random();
        if((b-a)/(c-a)<=aleatorio && aleatorio>=0){
            return a+Math.sqrt(aleatorio*(b-a)*(c-a));
        }else{
            return c-Math.sqrt((1-aleatorio)*(c-b)*(c-a));
        }
    }
    public static double expo(double lambda){
    	double aleatorio = Math.random();
        return -lambda*Math.log(1-aleatorio);
    }
    
    public static double norm(double media, double desvio_padrao){
    	double aleatorio = Math.random();
        double aleatorio2 = Math.random();
        double z1 = Math.sqrt(-2*Math.log(aleatorio))*Math.cos(2*Math.PI*aleatorio2);
        return media+desvio_padrao*z1;
    }
}
