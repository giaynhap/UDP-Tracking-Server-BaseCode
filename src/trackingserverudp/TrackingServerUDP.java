/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trackingserverudp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Giay Nhap
 */
public class TrackingServerUDP {
    public static void main(String[] args) {
        
        try {
            Server server = new Server(40);
            server.listen();
            initEvent();
        } catch (SocketException ex) {
            Logger.getLogger(TrackingServerUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TrackingServerUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void initEvent()
    {
        MessageAnalysisProcess.getInstance().addEventReveiceData(new IEventReceive() {
            @Override
            public void onReceive(DataInputStream stream) {
                try {
                    
                    int command = stream.readInt();
                    Logger.getLogger("Reveice").log(Level.SEVERE, "Command %d", command);
                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(TrackingServerUDP.class.getName()).log(Level.SEVERE, null, ex);
                }
                  
            }
        });
    }
}


/* 
Luồng sử lý 
--------------.
.             .    đẩy dữ liệu nhận dc
. SERVER.java .   --------------------> BaseServerEventReceive.Java 
.             .                                     |
--------------                                      | triger và đưa dữ liệu qua MesageAnalysisProcess 
   Thread 1                                         | để tiến hành sử lý dữ liệu 
                                            -------------------------
                                            |                       |
              Response        <-------------| MesageAnalysisProcess |
                                            |                       |
                                             ------------------------
                                                    Thread 2
*/