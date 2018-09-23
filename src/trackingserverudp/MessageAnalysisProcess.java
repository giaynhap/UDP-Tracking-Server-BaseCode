/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trackingserverudp;
 
import java.net.DatagramPacket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageAnalysisProcess  implements Runnable {
    public static MessageAnalysisProcess instance;
    private LinkedBlockingQueue queue;
    private Thread thread;
    private boolean isRunning = false;
    public static MessageAnalysisProcess getInstance()
    {
        if(instance == null)
              instance = new MessageAnalysisProcess();
        return instance;
    }
    private MessageAnalysisProcess()
    {
        Logger.getLogger(MessageAnalysisProcess.class.getName()).log(Level.SEVERE,
                  null, "Init MessageAnalysisProcess");
        queue = new LinkedBlockingQueue(); 
    }
    
    public void AddMessage(DatagramPacket input)
    {
        queue.add(input);
        this.start();
    }

    public void start()
    {
        isRunning = true;
        if (thread==null);
        thread = new Thread(this,"MessageAnalysisProcess");
        Logger.getLogger(MessageAnalysisProcess.class.getName()).log(Level.SEVERE, "Start Thread");
        thread.start();
    }
    public void stop ()
    {
        isRunning = false;
    }
    @Override
    public void run() {
         while(isRunning)
         {
             if (queue.isEmpty()){
                 try {
                     Thread.sleep(200);
                 } catch (InterruptedException ex) {
                     Logger.getLogger(MessageAnalysisProcess.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 continue;
             }
             DatagramPacket data = (DatagramPacket)queue.poll();
             if (data==null) continue;
             HandlingRawData(data);
             
         }
    }
    private void HandlingRawData(DatagramPacket data)     
    {
        
    }
}
