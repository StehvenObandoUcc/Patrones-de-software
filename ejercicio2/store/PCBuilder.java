package store;

public class PCBuilder implements IPCBuilder {

    // Internal product being assembled
    private Computer computer;

    public PCBuilder() {
        reset();
    }

    private void reset() {
        this.computer = new Computer();
    }

    // ── Builder steps ─────────────────────────────────────────

    @Override
    public IPCBuilder setCPU(String cpu) {
        computer.setCpu(cpu);
        return this; // fluent chaining
    }

    @Override
    public IPCBuilder setRAM(String ram) {
        computer.setRam(ram);
        return this;
    }

    @Override
    public IPCBuilder setGPU(String gpu) {
        computer.setGpu(gpu);
        return this;
    }

    @Override
    public IPCBuilder setStorage(String storage) {
        computer.setStorage(storage);
        return this;
    }

    @Override
    public IPCBuilder setMotherboard(String motherboard) {
        computer.setMotherboard(motherboard);
        return this;
    }

    // ── Finalize ──────────────────────────────────────────────

    @Override
    public Computer build() {
        Computer result = this.computer;
        reset(); // ready for the next build
        return result;
    }
}
