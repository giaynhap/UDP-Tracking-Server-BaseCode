/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trackingserverudp.eventinterface;
 
import trackingserverudp.process.MessageAnalysisProcess;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class BaseServerEventReceive {
    private DatagramSocket socket;
    
    public BaseServerEventReceive(DatagramSocket socket){
        this.socket = socket;
    }
    public void onReceive(DatagramPacket header,byte[] data){
        if (header == null) return;
        Logger.getLogger(BaseServerEventReceive.class.getName()).log(Level.SEVERE, "Receive Data from: "+header.getAddress().toString());
        MessageAnalysisProcess.getInstance().AddMessage(header);
    }
    
    
}
