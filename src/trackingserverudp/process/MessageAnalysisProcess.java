/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trackingserverudp.process;
 
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import trackingserverudp.eventinterface.IEventReceive;

public class MessageAnalysisProcess  extends BaseProcess {
    private static MessageAnalysisProcess instance;
    private ArrayList<IEventReceive> events;
    public static MessageAnalysisProcess getInstance()
    {
        if(instance == null)
              instance = new MessageAnalysisProcess();
        return instance;
    }
    private MessageAnalysisProcess()
    {
        super("MessageAnalysisProcess");
        if (events==null)
        events = new ArrayList<>();
        logger.log(Level.INFO,
                  null, "Init MessageAnalysisProcess");
       
        this.start();
    }
    public void addEventReveiceData(IEventReceive event)
    {
        logger.log(Level.INFO, "Add new Event");
        this.events.add(event);
    }
    public void AddMessage(DatagramPacket input)
    {
        queue.add(input);
    }

    public void stop ()
    {
        isRunning = false;
    }
    @Override
    public void process() {
 
             
             DatagramPacket data = (DatagramPacket)queue.poll();
             if (data==null) return;
             HandlingRawData(data);   
 
    }
    private void HandlingRawData(DatagramPacket data)     
    {
         byte[] bytes = data.getData();
         ByteArrayInputStream stream  = new ByteArrayInputStream(bytes);
         DataInputStream dataStream = new DataInputStream(stream);
         for (IEventReceive e : events){
             e.onReceive(dataStream);
         }
    }
}
