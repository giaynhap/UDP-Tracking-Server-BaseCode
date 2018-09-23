/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trackingserverudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Giay Nhap
 */
public class Server {
      private DatagramSocket socket;
      private boolean isStop = false;
      private final int MaxBUFFSize = 1024;
      private BaseServerEventReceive receiveProcess;
      public  Server(int port) throws SocketException
      {
          Logger.getLogger(Server.class.getName()).log(Level.SEVERE , "Start Server at port:"+ port);
              
          socket = new DatagramSocket(port);
          receiveProcess =new BaseServerEventReceive(socket);
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
