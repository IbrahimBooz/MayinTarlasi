
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
//Değerlendirme Formu 7:
        System.out.println("Satır sayısını giriniz: ");
        int row = input.nextInt();

        System.out.println("Sütun sayısını giriniz: ");
        int column = input.nextInt();

        MineSweeper oyunaBasla = new MineSweeper(row,column);

        oyunaBasla.baslat();


    }
}

