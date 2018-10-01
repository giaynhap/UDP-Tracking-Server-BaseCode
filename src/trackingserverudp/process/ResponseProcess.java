package trackingserverudp.process;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Giay Nhap
 */
public class ResponseProcess  extends BaseProcess  {

    private static  ResponseProcess instance = null;
    public static ResponseProcess getInstance()
    {
        if (instance==null) instance = new ResponseProcess();
        return instance;
    }
    private ResponseProcess()
    {
        super("ResponseProcess");
        start();
    }
    @Override
    public void process() {
        
    }
    
}
