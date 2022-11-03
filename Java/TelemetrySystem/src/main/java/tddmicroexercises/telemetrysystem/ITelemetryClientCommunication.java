package tddmicroexercises.telemetrysystem;

public interface ITelemetryClientCommunication {
    public static final String DIAGNOSTIC_MESSAGE = "AT#UD";
    void send(String message);
    String receive();
    boolean getOnlineStatus();
}
