
import java.util.Scanner;

/*
 * Trabalhos da cadeira de Labprat�rio de Progrma��o 2
 * Instituto Militar de Engenharia
 */

/**
 *
 * @author Lucas
 */
public class Triangulo {
    
    int linha[][] = null;
    String imprimir = null;
    
    public void desenha(){        
        Scanner in = new Scanner(System.in);
        System.out.println("Qual o tamanho do Triângulo de Pascal?");
        int nivel = in.nextInt();
        if( nivel > 1){
            linha = new int[2][nivel];
            linha[0][0] = 1;
            linha[1][0] = 1;
            linha[0][1] = 0;
            System.out.println(linha[0][0]);
            for(int i = 1; i < nivel; i++){
                imprimir = new String();
                int j = 1;
                for(; j < i; j++){
                    linha[1][j] = linha[0][j-1] + linha[0][j]; 
                }
                linha[1][j] = 1;
                for(int k = 0; k < j + 1; k++){
                    imprimir = imprimir.concat(linha[1][k] + " ");
                    linha[0][k] = linha[1][k];
                }
                System.out.println(imprimir);
            }
        }else{
            System.out.println("1");
        }
       
    }
    
}
