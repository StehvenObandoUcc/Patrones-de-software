package store;


public interface IPCBuilder {

    IPCBuilder setCPU(String cpu);
    IPCBuilder setRAM(String ram);
    IPCBuilder setGPU(String gpu);
    IPCBuilder setStorage(String storage);
    IPCBuilder setMotherboard(String motherboard);

    Computer build();
}
