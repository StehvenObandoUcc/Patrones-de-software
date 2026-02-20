package store;

public class Computer implements IPrototype {

    // ── Fields ──────────────────────────────────────────────
    private String cpu;
    private String ram;
    private String gpu;
    private String storage;
    private String motherboard;

    // ── Constructors ─────────────────────────────────────────
    /** Default constructor — used internally by clone() and PCBuilder. */
    public Computer() {}

    /** Convenience constructor for creating preset templates. */
    public Computer(String cpu, String ram, String gpu,
                    String storage, String motherboard) {
        this.cpu         = cpu;
        this.ram         = ram;
        this.gpu         = gpu;
        this.storage     = storage;
        this.motherboard = motherboard;
    }

    // ── Prototype: clone() ───────────────────────────────────
    /**
     * Returns a *new* Computer with the same specs as this one.
     * The original template is never modified.
     */
    @Override
    public Computer clone() {
        return new Computer(this.cpu, this.ram, this.gpu,
                            this.storage, this.motherboard);
    }

    // ── Getters & Setters ─────────────────────────────────────
    public String getCpu()         { return cpu; }
    public void   setCpu(String v) { this.cpu = v; }

    public String getRam()         { return ram; }
    public void   setRam(String v) { this.ram = v; }

    public String getGpu()         { return gpu; }
    public void   setGpu(String v) { this.gpu = v; }

    public String getStorage()         { return storage; }
    public void   setStorage(String v) { this.storage = v; }

    public String getMotherboard()         { return motherboard; }
    public void   setMotherboard(String v) { this.motherboard = v; }

    // ── Receipt Formatter ─────────────────────────────────────
    @Override
    public String toString() {
        return String.format(
            "╔══════════════════════════════════════╗%n" +
            "║         PC SPECIFICATIONS SHEET      ║%n" +
            "╠══════════════════════════════════════╣%n" +
            "║  🖥  CPU          : %-18s ║%n" +
            "║  🧠  RAM          : %-18s ║%n" +
            "║  🎮  GPU          : %-18s ║%n" +
            "║  💾  Storage      : %-18s ║%n" +
            "║  🔌  Motherboard  : %-18s ║%n" +
            "╚══════════════════════════════════════╝",
            cpu, ram, gpu, storage, motherboard);
    }
}
