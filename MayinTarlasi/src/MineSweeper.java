import java.util.Random;
import java.util.Scanner;

//Değerlendirme Formu 5:
public class MineSweeper {
    //Değerlendirme Formu 1:
    private String[][] bombaliMayinTarlasi;
    private String[][] bombasizMayinTarlasi;
    int row;
    int column;
    int bombaSayac;

    //Kurucu Metotumu oluşturarak temel metotlarımı burada çağırdık.
    public MineSweeper(int row, int column) {

        this.row = row;
        this.column = column;

        bombaliMayinTarlasi = new String[row][column];
        bombasizMayinTarlasi = new String[row][column];

        this.diziDegerAtama();
        this.diziMayinAtama();
        this.bombaliMayinBastir();
    }


    //Oluşturulan dizilerin içini doldurmak için bu metotu oluşturduk.
    private void diziDegerAtama() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                bombaliMayinTarlasi[i][j] = "- ";
                bombasizMayinTarlasi[i][j] = "- ";
            }
        }
    }

    /*Oluşturulan dizinin değerlerini mayın ("*") değerini atamak için bu metotu oluşturduk.
    Burada random metotunu kullaranak mayının atandığı yeri rastgele belirlemesini sağladık.
    Mayın sayımızı da kullanıcıdan aldığımız satır ve sütun sayılarına bölerek bulduk */

    //Değerlendirme Formu 8:
    private void diziMayinAtama() {

        Random rand = new Random();
        int mayinSayisi = (row * column) / 4;
        int placedMines = 0;

        while (placedMines < mayinSayisi) {

            int randomRow = rand.nextInt(row);
            int randomColumn = rand.nextInt(column);

            if (bombaliMayinTarlasi[randomRow][randomColumn].equals("- ")) {
                bombaliMayinTarlasi[randomRow][randomColumn] = "* ";
                placedMines++;
            }

        }

    }

    //Burada mayın atamadığımız dizi değerlerini bastırmak için bu metotu kullandık.
    void bombasizMayinBastir() {

        System.out.println("===============");

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(bombasizMayinTarlasi[i][j]);
            }
            System.out.println();
        }

    }

    // Burada mayın atadığımız dizi değerlerini bastırmak için bu metotu kullandık.
    void bombaliMayinBastir() {

        System.out.println("==================");

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(bombaliMayinTarlasi[i][j]);
            }
            System.out.println();
        }

    }

    /*  Bu metot kullanıcıdan alınan koordinatları matrise işlenmesi için oluşturuldu.
        Değerlendirme Formu 6:*/
    void baslat() {

        Scanner input = new Scanner(System.in);
        int hucre = (row * column) - ((row * column) / 4);
        boolean[][] doluHucre = new boolean[row][column];

        while (true) {
            //Değerlendirme Formu 9:
            System.out.println("Satır giriniz: ");
            int rowDeger = input.nextInt();

            System.out.println("Sütun giriniz: ");
            int columnDeger = input.nextInt();

            --rowDeger;
            --columnDeger;

            //Girilen değerler mayına eşitse oyunu durdur ve bombanın konumlarını bas.
            //Değerlendirme Formu 13:
            if (rowDeger >= 0 && rowDeger < row && columnDeger >= 0 && columnDeger < column) {

                if (bombaliMayinTarlasi[rowDeger][columnDeger].equals("* ")) {
                    System.out.println("BUUMMM!!! :((");
                    this.tekrarBaslat();
                }

                //Bombaların konumunu saptayarak, etrafında ki mayın sayını saydırmak için yazdık.
                //Değerlendirme Formu 12:
                else {
                    this.bombaSayac = 0;
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            int newRow = rowDeger + i;
                            int newColumn = columnDeger + j;

                            if (newRow >= 0 && newRow < row && newColumn >= 0 && newColumn < column) {
                                if (bombaliMayinTarlasi[newRow][newColumn].equals("* ")) {
                                    this.bombaSayac++;
                                }
                            }
                        }
                    }

                    bombasizMayinTarlasi[rowDeger][columnDeger] = this.bombaSayac + " ";
                }
                //Değerlendirme Formu 11:
                this.bombasizMayinBastir();

            } else {
                System.out.println("Sınırları Aşan Değer Girdiniz.");
                this.baslat();
            }
            //Değerlendirme Formu 10:
            if (!doluHucre[rowDeger][columnDeger]) {
                hucre--;
                doluHucre[rowDeger][columnDeger] = true;
            } else {
                System.out.println("Dolu Alana işlem yaptınız.");
            }

            //Değerlendirme Formu 14:
            if (hucre == 0) {
                System.out.println("Tebrikleeeer Kazandınız!!! Hehe");
                this.bombaliMayinBastir();
                System.exit(0);
            }

        }
    }

    //Kullanıcı Oyunu kaybettiğinde tekrardan başlaması için metotları tekrardan çalıştırmak için bu metotu yazdık.
    public void tekrarBaslat() {
        Scanner input = new Scanner(System.in);

        System.out.println("Tekrar Oynamak için 1'e basınız.\n\nÇıkmak için 2'ye basınız.");
        int hak = input.nextInt();

        if (hak == 1) {
            //Değerlendirme Formu 7:
            System.out.println("Yeni satır sayısını girin: ");
            int yeniSatir = input.nextInt();

            System.out.println("Yeni sütun sayısını girin: ");
            int yeniSutun = input.nextInt();

            this.row = yeniSatir;
            this.column = yeniSutun;

            bombaliMayinTarlasi = new String[row][column];
            bombasizMayinTarlasi = new String[row][column];

            this.diziDegerAtama();
            this.diziMayinAtama();
            this.bombaliMayinBastir();
            this.baslat();


        } else if (hak == 2) {

            System.out.println(" == Tekrar bekleriz. ^^ ==");
            System.exit(0);

        } else {

            System.out.println("Geçersiz Değer girdiniz.");
            System.exit(0);

        }

    }
}