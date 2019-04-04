/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trackingserverudp;

import trackingserverudp.eventinterface.BaseServerEventReceive;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import trackingserverudp.process.MessageAnalysisProcess;
import trackingserverudp.process.ResponseProcess;

/**
 * Trung ăn cứt
 * @author Giay Nhap
 */
public class Server {
      private DatagramSocket socket;
      private boolean isStop = false;
      private final int MaxBUFFSize = 1024;
      private BaseServerEventReceive receiveProcess;
      private  static Server instance;
      public static Server getInstance()
      {
          if (instance == null) 
              instance = new  Server();
          return instance;
      }
      
      private  Server()  
      {
        
      }
      public static void start(int port) throws SocketException
      {
          if (getInstance().socket!=null) return;
          Logger.getLogger(Server.class.getName()).log(Level.INFO , "Start Server at port:"+ port);
          getInstance().socket = new DatagramSocket(port);
          getInstance().receiveProcess = new BaseServerEventReceive(getInstance().socket);
          MessageAnalysisProcess.getInstance();
          ResponseProcess.getInstance();
      }
 
      public void listen() throws IOException
      {
          while (!isStop)
          {
            byte[] data = new byte[MaxBUFFSize];
            DatagramPacket  request = new DatagramPacket(data, MaxBUFFSize);
            socket.receive(request);
            receiveProcess.onReceive(request,data);
          }
      }
      public void stop()
      {
          this.isStop = true;
      }
      public void asyncListen()
      {
          Thread t = new Thread("TrackingServer")
          {
              @Override
              public void run()
              {
                  try {
                      Server.this.listen();
                  } catch (IOException ex) {
                      Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
          };
          t.start();
      }
}
