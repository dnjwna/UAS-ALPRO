import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;

public class BioskoopGUI extends JFrame {
    private JComboBox<String> filmComboBox;
    private JComboBox<String> jamTayangComboBox;
    private JTextField jumlahTiketTextField;
    private JTextArea invoiceTextArea;
    private JPanel kursiPanel;
    private HashSet<String> kursiDipesan;

    private String[] filmNames = { "Inside Out 2", "The Watchers", "Kungu Panda 4", "Ancika: Dia yang Bersamaku 1995",
            "Godzilla x Kong: The New Empire" };
    private int[] filmPrices = { 45000, 40000, 38000, 42000, 40000 };
    private String[] jamTayang = { "12.00", "13.40", "14.50", "19.20", "20.15" };

    public BioskoopGUI() {
        super("Pemesanan Tiket Bioskop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        kursiDipesan = new HashSet<>();

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel filmLabel = new JLabel("Pilih Film:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        mainPanel.add(filmLabel, constraints);

        filmComboBox = new JComboBox<>(filmNames);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(filmComboBox, constraints);

        JLabel jamTayangLabel = new JLabel("Jam Tayang:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        mainPanel.add(jamTayangLabel, constraints);

        jamTayangComboBox = new JComboBox<>(jamTayang);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(jamTayangComboBox, constraints);

        JLabel jumlahTiketLabel = new JLabel("Jumlah Tiket:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.WEST;
        mainPanel.add(jumlahTiketLabel, constraints);

        jumlahTiketTextField = new JTextField(10);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(jumlahTiketTextField, constraints);

        JButton pesanButton = new JButton("Pesan");
        pesanButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                int selectedFilmIndex = filmComboBox.getSelectedIndex();
                int selectedJamIndex = jamTayangComboBox.getSelectedIndex();
                int jumlahTiket = Integer.parseInt(jumlahTiketTextField.getText());

                String kode = filmNames[selectedFilmIndex];
                int harga = filmPrices[selectedFilmIndex];
                String jam = jamTayang[selectedJamIndex];
                int totalHarga = harga * jumlahTiket;

                StringBuilder invoiceBuilder = new StringBuilder();
                invoiceBuilder.append("===== INVOICE PEMESANAN TIKET =====\n");
                invoiceBuilder.append("Kode film\t\t: ").append(kode).append("\n");
                invoiceBuilder.append("Jam tayang\t\t: ").append(jam).append(" WIB\n");
                invoiceBuilder.append("Jumlah Tiket\t\t: ").append(jumlahTiket).append("\n");
                invoiceBuilder.append("Harga tiket (per tiket)\t: Rp. ").append(harga).append("\n");
                invoiceBuilder.append("Total Harga\t\t: Rp. ").append(totalHarga).append("\n");

                if (!kursiDipesan.isEmpty()) {
                    invoiceBuilder.append("Kursi Dipesan\t\t: ");
                    for (String kursi : kursiDipesan) {
                        invoiceBuilder.append(kursi).append(", ");
                    }
                    invoiceBuilder.delete(invoiceBuilder.length() - 2, invoiceBuilder.length());
                    invoiceBuilder.append("\n");
                }

                invoiceBuilder.append("Status Pembayaran\t: Sukses\n");
                invoiceBuilder.append("Terima kasih telah menggunakan layanan kami!\n");
                invoiceBuilder.append("================================================");

                invoiceTextArea.setText(invoiceBuilder.toString());
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        mainPanel.add(pesanButton, constraints);

        invoiceTextArea = new JTextArea(10, 30);
        invoiceTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(invoiceTextArea);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(scrollPane, constraints);

        add(mainPanel, BorderLayout.CENTER);

        kursiPanel = new JPanel(new GridLayout(5, 5));
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(new JScrollPane(kursiPanel), constraints);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void updateKursiPanel() {
        kursiPanel.removeAll();
        for (int i = 1; i <= 25; i++) {
            String kursiLabel = "Kursi " + i;
            JToggleButton kursiButton = new JToggleButton(kursiLabel);
            kursiButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JToggleButton button = (JToggleButton) e.getSource();
                    if (button.isSelected()) {
                        kursiDipesan.add(button.getText());
                    } else {
                        kursiDipesan.remove(button.getText());
                    }
                }
            });
            kursiPanel.add(kursiButton);
        }
        kursiPanel.revalidate();
        kursiPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BioskoopGUI gui = new BioskoopGUI();
                gui.updateKursiPanel();
                gui.setVisible(true);
            }
        });
    }
}