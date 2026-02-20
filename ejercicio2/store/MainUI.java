package store;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MainUI extends JFrame {

    // ── Design Tokens ─────────────────────────────────────────
    private static final Color BG_DARK = new Color(18, 18, 28);
    private static final Color BG_PANEL = new Color(28, 28, 45);
    private static final Color BG_CARD = new Color(38, 38, 58);
    private static final Color ACCENT = new Color(99, 102, 241); // indigo
    private static final Color ACCENT2 = new Color(168, 85, 247); // purple
    private static final Color ACCENT3 = new Color(34, 211, 238); // cyan
    private static final Color ACCENT4 = new Color(52, 211, 153); // green
    private static final Color TEXT_MAIN = new Color(240, 240, 255);
    private static final Color TEXT_MUTED = new Color(140, 140, 170);
    private static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 13);
    private static final Font FONT_LABEL = new Font("Segoe UI", Font.PLAIN, 12);
    private static final Font FONT_BTN = new Font("Segoe UI", Font.BOLD, 12);
    private static final Font FONT_MONO = new Font("Consolas", Font.PLAIN, 13);

    // ── Prototype Templates ───────────────────────────────────
    private final Computer masterGamer;
    private final Computer masterOffice;
    private final Computer masterProArtist;

    // ── Builder ───────────────────────────────────────────────
    private final IPCBuilder builder = new PCBuilder();

    // ── Inventory ─────────────────────────────────────────────
    private final List<Computer> inventoryPCs = new ArrayList<>();
    private final DefaultListModel<String> inventoryModel = new DefaultListModel<>();
    private JList<String> inventoryList;
    private int inventoryCounter = 0;

    // ── UI Components ─────────────────────────────────────────
    private JComboBox<String> comboCPU, comboRAM, comboGPU,
            comboStorage, comboMotherboard;
    private JTextArea receiptArea;

    // ─────────────────────────────────────────────────────────
    public MainUI() {
        super("🖥  PC Configurator");

        // ── Initialize Prototype master templates ─────────────
        masterGamer = new Computer(
                "Intel Core i9-14900K",
                "32GB DDR5-6000",
                "NVIDIA RTX 4090",
                "2TB NVMe Gen5",
                "ASUS ROG Maximus Z790");
        masterOffice = new Computer(
                "Intel Core i5-13400",
                "16GB DDR4-3200",
                "Intel UHD Graphics 730",
                "512GB NVMe Gen4",
                "MSI PRO B660M-A");
        masterProArtist = new Computer(
                "AMD Ryzen 9 7950X",
                "64GB DDR5-5200",
                "NVIDIA RTX 4080",
                "4TB NVMe Gen4 RAID",
                "ASUS ProArt X670E-Creator");

        // ── Frame setup ───────────────────────────────────────
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG_DARK);
        setLayout(new BorderLayout(12, 12));

        // ── Build layout ──────────────────────────────────────
        add(buildTopBar(), BorderLayout.NORTH);
        add(buildCenterPanel(), BorderLayout.CENTER);
        add(buildBottomPanel(), BorderLayout.SOUTH);
    }

    // ── TOP BAR ───────────────────────────────────────────────
    private JPanel buildTopBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 10));
        bar.setBackground(BG_PANEL);
        bar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(60, 60, 90)));

        JLabel icon = new JLabel("🖥");
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));

        JLabel title = new JLabel("PC Configurator");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(TEXT_MAIN);

        JLabel sub = new JLabel("  |  Builder Pattern   +   Prototype Pattern");
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        sub.setForeground(TEXT_MUTED);

        bar.add(icon);
        bar.add(title);
        bar.add(sub);
        return bar;
    }

    // ── CENTER SPLIT PANEL ────────────────────────────────────
    private JSplitPane buildCenterPanel() {
        JSplitPane split = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                buildBuilderPanel(),
                buildPrototypePanel());
        split.setDividerLocation(480);
        split.setResizeWeight(0.55);
        split.setBackground(BG_DARK);
        split.setBorder(BorderFactory.createEmptyBorder(8, 12, 4, 12));
        split.setDividerSize(6);
        return split;
    }

    // ── LEFT: BUILDER PANEL ───────────────────────────────────
    private JPanel buildBuilderPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(BG_PANEL);
        panel.setBorder(new CompoundBorder(
                new LineBorder(ACCENT, 1, true),
                new EmptyBorder(14, 14, 14, 14)));

        JLabel hdr = new JLabel("⚙  Custom Builder");
        hdr.setFont(new Font("Segoe UI", Font.BOLD, 15));
        hdr.setForeground(ACCENT);
        hdr.setBorder(new EmptyBorder(0, 0, 6, 0));
        panel.add(hdr, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(0, 2, 8, 10));
        grid.setBackground(BG_PANEL);

        String[] cpus = {
                "Intel Core i3-13100", "Intel Core i5-13400",
                "Intel Core i7-13700K", "Intel Core i9-14900K",
                "AMD Ryzen 5 7600X", "AMD Ryzen 7 7800X3D",
                "AMD Ryzen 9 7950X"
        };
        String[] rams = {
                "8GB DDR4-3200", "16GB DDR4-3200", "16GB DDR5-5200",
                "32GB DDR5-6000", "64GB DDR5-5200"
        };
        String[] gpus = {
                "Intel UHD Graphics 730", "AMD Radeon RX 7600",
                "NVIDIA RTX 3060", "NVIDIA RTX 4070",
                "NVIDIA RTX 4080", "NVIDIA RTX 4090",
                "AMD Radeon RX 7900 XTX"
        };
        String[] storages = {
                "256GB SATA SSD", "512GB NVMe Gen4",
                "1TB NVMe Gen4", "2TB NVMe Gen5",
                "4TB NVMe Gen4 RAID"
        };
        String[] mobos = {
                "MSI PRO B660M-A", "Gigabyte B550M DS3H",
                "ASUS TUF Gaming X570-Plus", "MSI MAG B650 Tomahawk",
                "ASUS ROG Maximus Z790", "ASUS ProArt X670E-Creator"
        };

        comboCPU = styleCombo(new JComboBox<>(cpus));
        comboRAM = styleCombo(new JComboBox<>(rams));
        comboGPU = styleCombo(new JComboBox<>(gpus));
        comboStorage = styleCombo(new JComboBox<>(storages));
        comboMotherboard = styleCombo(new JComboBox<>(mobos));

        addRow(grid, "🖥  CPU", comboCPU);
        addRow(grid, "🧠  RAM", comboRAM);
        addRow(grid, "🎮  GPU", comboGPU);
        addRow(grid, "💾  Storage", comboStorage);
        addRow(grid, "🔌  Motherboard", comboMotherboard);

        panel.add(grid, BorderLayout.CENTER);

        JButton btnBuild = createButton("⚙  Build Custom PC", ACCENT);
        btnBuild.addActionListener(e -> handleBuild());
        JPanel btnWrap = new JPanel(new BorderLayout());
        btnWrap.setBackground(BG_PANEL);
        btnWrap.add(btnBuild);
        panel.add(btnWrap, BorderLayout.SOUTH);

        return panel;
    }

    // ── RIGHT: PROTOTYPE PANEL ────────────────────────────────
    private JPanel buildPrototypePanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(BG_PANEL);
        panel.setBorder(new CompoundBorder(
                new LineBorder(ACCENT2, 1, true),
                new EmptyBorder(14, 14, 14, 14)));

        JLabel hdr = new JLabel("⚡  Quick Clone (Prototype)");
        hdr.setFont(new Font("Segoe UI", Font.BOLD, 15));
        hdr.setForeground(ACCENT2);
        hdr.setBorder(new EmptyBorder(0, 0, 4, 0));
        panel.add(hdr, BorderLayout.NORTH);

        JPanel cards = new JPanel(new GridLayout(3, 1, 0, 12));
        cards.setBackground(BG_PANEL);

        cards.add(buildPresetCard(
                "🎮  Quick Clone: Gamer",
                "i9-14900K · RTX 4090 · 32GB DDR5",
                new Color(220, 38, 38),
                e -> handleClone("Gamer", masterGamer)));
        cards.add(buildPresetCard(
                "🏢  Quick Clone: Office",
                "i5-13400 · UHD 730 · 16GB DDR4",
                new Color(22, 163, 74),
                e -> handleClone("Office", masterOffice)));
        cards.add(buildPresetCard(
                "🎨  Quick Clone: Pro Artist",
                "Ryzen 9 7950X · RTX 4080 · 64GB DDR5",
                new Color(234, 88, 12),
                e -> handleClone("Pro Artist", masterProArtist)));

        panel.add(cards, BorderLayout.CENTER);

        JLabel note = new JLabel(
                "<html><font color='#888899'>.</font></html>");
        note.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        note.setBorder(new EmptyBorder(6, 2, 0, 2));
        panel.add(note, BorderLayout.SOUTH);

        return panel;
    }

    // ── BOTTOM SPLIT: RECEIPT + INVENTORY ────────────────────
    private JSplitPane buildBottomPanel() {
        JSplitPane split = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                buildReceiptPanel(),
                buildInventoryPanel());
        split.setDividerLocation(620);
        split.setResizeWeight(0.60);
        split.setBackground(BG_DARK);
        split.setBorder(new EmptyBorder(0, 12, 10, 12));
        split.setDividerSize(6);
        split.setPreferredSize(new Dimension(0, 190));
        return split;
    }

    // ── Receipt / Specs ───────────────────────────────────────
    private JPanel buildReceiptPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 6));
        panel.setBackground(BG_DARK);

        JLabel lbl = new JLabel("📋  Specification Sheet");
        lbl.setFont(FONT_TITLE);
        lbl.setForeground(ACCENT3);
        panel.add(lbl, BorderLayout.NORTH);

        receiptArea = new JTextArea();
        receiptArea.setEditable(false);
        receiptArea.setBackground(BG_CARD);
        receiptArea.setForeground(TEXT_MAIN);
        receiptArea.setFont(FONT_MONO);
        receiptArea.setBorder(new EmptyBorder(8, 12, 8, 12));
        receiptArea.setText(
                "Build or clone a PC above to see the specs here...");

        JScrollPane scroll = new JScrollPane(receiptArea);
        scroll.setBorder(new LineBorder(ACCENT3, 1, true));
        scroll.setBackground(BG_CARD);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    // ── Inventory Panel ───────────────────────────────────────
    private JPanel buildInventoryPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 6));
        panel.setBackground(BG_DARK);

        // Header row with counter label and clear button
        JPanel hdrRow = new JPanel(new BorderLayout());
        hdrRow.setBackground(BG_DARK);

        JLabel lbl = new JLabel("📦  Inventory  ");
        lbl.setFont(FONT_TITLE);
        lbl.setForeground(ACCENT4);
        hdrRow.add(lbl, BorderLayout.WEST);

        JButton btnClear = new JButton("🗑 Clear");
        btnClear.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btnClear.setForeground(TEXT_MUTED);
        btnClear.setBackground(BG_CARD);
        btnClear.setBorder(new EmptyBorder(3, 8, 3, 8));
        btnClear.setFocusPainted(false);
        btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnClear.addActionListener(e -> clearInventory());
        hdrRow.add(btnClear, BorderLayout.EAST);

        panel.add(hdrRow, BorderLayout.NORTH);

        // List
        inventoryList = new JList<>(inventoryModel);
        inventoryList.setBackground(BG_CARD);
        inventoryList.setForeground(TEXT_MAIN);
        inventoryList.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        inventoryList.setSelectionBackground(ACCENT);
        inventoryList.setSelectionForeground(Color.WHITE);
        inventoryList.setBorder(new EmptyBorder(6, 8, 6, 8));
        inventoryList.setFixedCellHeight(26);

        // Click → show that PC's specs in the receipt area
        inventoryList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int idx = inventoryList.locationToIndex(e.getPoint());
                if (idx >= 0 && idx < inventoryPCs.size()) {
                    Computer selected = inventoryPCs.get(idx);
                    String label = inventoryModel.get(idx);
                    displayReceipt("INVENTORY VIEW  →  " + label, selected);
                }
            }
        });

        JScrollPane scroll = new JScrollPane(inventoryList);
        scroll.setBorder(new LineBorder(ACCENT4, 1, true));
        scroll.setBackground(BG_CARD);
        panel.add(scroll, BorderLayout.CENTER);

        // Hint label
        JLabel hint = new JLabel("  Click any entry to view specs");
        hint.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        hint.setForeground(TEXT_MUTED);
        panel.add(hint, BorderLayout.SOUTH);

        return panel;
    }

    // ── Event Handlers ────────────────────────────────────────

    /** Builder flow: read combos → chain builder → display + log. */
    private void handleBuild() {
        Computer pc = builder
                .setCPU((String) comboCPU.getSelectedItem())
                .setRAM((String) comboRAM.getSelectedItem())
                .setGPU((String) comboGPU.getSelectedItem())
                .setStorage((String) comboStorage.getSelectedItem())
                .setMotherboard((String) comboMotherboard.getSelectedItem())
                .build();

        String label = "⚙ Custom #" + (++inventoryCounter)
                + "  [" + pc.getCpu() + "]";
        addToInventory(label, pc);
        displayReceipt("CUSTOM BUILD  [Builder Pattern]  →  " + label, pc);
    }

    /** Prototype flow: call clone() on the master template. */
    private void handleClone(String templateName, Computer master) {
        Computer cloned = master.clone(); // <-- THE PROTOTYPE call
        String label = "⚡ Clone: " + templateName
                + " #" + (++inventoryCounter);
        addToInventory(label, cloned);
        displayReceipt("QUICK CLONE: " + templateName.toUpperCase()
                + "  [Prototype Pattern]  →  " + label, cloned);
    }

    /** Adds a PC to the inventory list and data store. */
    private void addToInventory(String label, Computer pc) {
        inventoryPCs.add(pc);
        inventoryModel.addElement(label);
        // Auto-scroll list to the newest entry
        int last = inventoryModel.size() - 1;
        inventoryList.ensureIndexIsVisible(last);
    }

    /** Clears all inventory entries. */
    private void clearInventory() {
        inventoryPCs.clear();
        inventoryModel.clear();
        inventoryCounter = 0;
        receiptArea.setText(
                "Inventory cleared. Build or clone a PC to start again...");
    }

    private void displayReceipt(String header, Computer pc) {
        receiptArea.setText("  ▶  " + header + "\n\n" + pc.toString());
        receiptArea.setCaretPosition(0);
    }

    // ── UI Helpers ────────────────────────────────────────────

    private void addRow(JPanel grid, String labelText, JComponent comp) {
        JLabel lbl = new JLabel(labelText);
        lbl.setFont(FONT_LABEL);
        lbl.setForeground(TEXT_MUTED);
        grid.add(lbl);
        grid.add(comp);
    }

    private JComboBox<String> styleCombo(JComboBox<String> cb) {
        cb.setBackground(BG_CARD);
        cb.setForeground(TEXT_MAIN);
        cb.setFont(FONT_LABEL);
        cb.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 100), 1));
        return cb;
    }

    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(FONT_BTN);
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.setBorder(new EmptyBorder(10, 18, 10, 18));
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(color.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(color);
            }
        });
        return btn;
    }

    private JPanel buildPresetCard(String btnText, String desc,
                                   Color color, ActionListener action) {
        JPanel card = new JPanel(new BorderLayout(0, 6));
        card.setBackground(BG_CARD);
        card.setBorder(new CompoundBorder(
                new LineBorder(color.darker(), 1, true),
                new EmptyBorder(10, 12, 10, 12)));

        JButton btn = createButton(btnText, color);
        btn.addActionListener(action);

        JLabel descLbl = new JLabel(desc);
        descLbl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        descLbl.setForeground(TEXT_MUTED);

        card.add(btn, BorderLayout.CENTER);
        card.add(descLbl, BorderLayout.SOUTH);
        return card;
    }

    // ── Entry Point ───────────────────────────────────────────
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> {
            MainUI window = new MainUI();
            window.setVisible(true);
        });
    }
}
