import java.util.Scanner;
import java.util.HashSet;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean lanjutPembelian;

        String[] filmNames = { "Inside Out 2", "The Watchers", "Kungu Panda 4", "Ancika: Dia yang Bersamaku 1995",
                "Godzilla x Kong: The New Empire" };
        int[] filmPrices = { 45000, 40000, 38000, 42000, 40000 };
        String[] jamTayang = { "12.00", "13.40", "14.50", "19.20", "20.15" };

        // Menyimpan kursi yang sudah dipesan1

        HashSet<String> kursiDipesan = new HashSet<>();

        new BioskoopGUI().setVisible(true);

        do {
            System.out.println("********** PEMESANAN TIKET BIOSKOP **********");
            System.out.println("Pilih menu:");
            System.out.println("1. Daftar Film");
            System.out.println("2. Keluar");

            System.out.print("Masukkan pilihan (1/2): ");
            int menuChoice = input.nextInt();
            input.nextLine(); // Consume the newline character

            switch (menuChoice) {
                case 1:
                    // Menampilkan daftar film
                    System.out.println(">>> List Nama Film <<<");
                    for (int i = 0; i < filmNames.length; i++) {
                        System.out.println((i + 1) + ". " + filmNames[i]);
                    }
                    break;
                case 2:
                    lanjutPembelian = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }

            System.out.print("Apakah ingin melanjutkan? (y/n): ");
            char lanjutPilihanChar = input.next().charAt(0);
            lanjutPembelian = (lanjutPilihanChar == 'y' || lanjutPilihanChar == 'Y');
        } while (lanjutPembelian);

    }

    // Metode untuk menampilkan invoice
    private static void tampilkanInvoice(Pelanggan pelanggan, String kode, String jam, String nomorKursi,
            int jumlahTiket, int harga, int totalHarga) {
        System.out.println("\n\n===== INVOICE PEMESANAN TIKET =====");
        System.out.println("Nama Pelanggan\t\t: " + pelanggan.getNama());
        System.out.println("No. Telp\t\t: " + pelanggan.getNomor());
        System.out.println("------------------------------------------");
        System.out.println("Kode film\t\t: " + kode);
        System.out.println("Jam tayang\t\t: " + jam + " WIB");
        System.out.println("Nomor Kursi\t\t: " + nomorKursi);
        System.out.println("Jumlah Tiket\t\t: " + jumlahTiket);
        System.out.println("Harga tiket (per tiket)\t: Rp. " + harga);
        System.out.println("Total Harga\t\t: Rp. " + totalHarga);
        System.out.println("Status Pembayaran\t: Sukses");
        System.out.println("Terima kasih telah menggunakan layanan kami!");
        System.out.println("================================================");
    }
}
