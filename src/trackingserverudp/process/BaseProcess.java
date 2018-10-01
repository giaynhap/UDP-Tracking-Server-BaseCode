/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trackingserverudp.process;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Giay Nhap
 */
public class BaseProcess implements Runnable{

    protected LinkedBlockingQueue queue;
    private Thread thread ;
    protected String threadName;
    protected boolean isRunning = false;
    protected Logger logger;
   public BaseProcess(String name)
   {
       threadName = name;
       queue = new LinkedBlockingQueue();
       logger = Logger.getLogger(threadName);
   }
   public void start()
   {
        if (thread!=null) return;
        isRunning = true;
        thread = new Thread(this,this.threadName);
        logger.log(Level.INFO,"Start process "+ threadName);
        thread.start();
   }
    @Override
    public void run() {
       while (isRunning)
       {
           if (queue.isEmpty()){
                 try {
                     Thread.sleep(200);
                 } catch (InterruptedException ex) {
                     logger.log(Level.SEVERE, null, ex);
                 }
                 continue;
             }
           try{
                process();
           }catch(Exception e){
                logger.log(Level.SEVERE, null, e);
           }
       }
    }
    
    public void process(){
        
    }
    public void stop()
    {
        isRunning = false;
    }
    
}
