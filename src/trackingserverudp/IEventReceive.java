/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trackingserverudp;

import java.io.DataInputStream;

/**
 *
 * @author Giay Nhap
 */
public interface IEventReceive {
    public void onReceive(DataInputStream stream);
}
