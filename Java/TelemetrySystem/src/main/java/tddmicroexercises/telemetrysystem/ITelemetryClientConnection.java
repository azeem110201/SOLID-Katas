package tddmicroexercises.telemetrysystem;

public interface ITelemetryClientConnection {
    void connect(String telemetryServerConnectionString) throws Exception;
    void disconnect();
}
