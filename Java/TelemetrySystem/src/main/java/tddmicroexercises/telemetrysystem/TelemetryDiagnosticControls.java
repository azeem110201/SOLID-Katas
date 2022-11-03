package tddmicroexercises.telemetrysystem;

public class TelemetryDiagnosticControls
{
    private final String DiagnosticChannelConnectionString = "*111#";

    private final ITelemetryClientCommunication iTelemetryClientCommunication;
    private final ITelemetryClientConnection iTelemetryClientConnection;

    private String diagnosticInfo = "";

    public TelemetryDiagnosticControls(ITelemetryClientConnection iTelemetryClientConnection, ITelemetryClientCommunication iTelemetryClientCommunication) {
        this.iTelemetryClientCommunication = iTelemetryClientCommunication;
        this.iTelemetryClientConnection = iTelemetryClientConnection;
    }

        
    public String getDiagnosticInfo(){
            return diagnosticInfo;
        }
        public void setDiagnosticInfo(String diagnosticInfo){
            this.diagnosticInfo = diagnosticInfo;
        }
 
        public void checkTransmission() throws Exception
        {
            diagnosticInfo = "";

            iTelemetryClientConnection.disconnect();
    
            int retryLeft = 3;
            while (!iTelemetryClientCommunication.getOnlineStatus() && retryLeft > 0)
            {
                iTelemetryClientConnection.connect(DiagnosticChannelConnectionString);
                retryLeft -= 1;
            }
             
            if(!iTelemetryClientCommunication.getOnlineStatus())
            {
                throw new Exception("Unable to connect.");
            }
    
            iTelemetryClientCommunication.send(iTelemetryClientCommunication.DIAGNOSTIC_MESSAGE);
            diagnosticInfo = iTelemetryClientCommunication.receive();
    }
}
