/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.jumpingbean.rabbitproducer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author mark
 */
public class Main {

    public static void main(String[] args) throws IOException, TimeoutException {

        HashMap<String,String> map = new HashMap<>();
        map.put("rabbit1","172.20.0.4");
        map.put("rabbit2", "172.20.0.2");
        map.put("rabbit3","172.20.0.3");
        String EXCHANGE_NAME = "Direct1";

        String HOST=map.get("rabbit1");
        if (args.length>0) {
            HOST = map.get(args[0]);
        }
        ConnectionFactory cf = new ConnectionFactory();
        cf.setHost(HOST);
        cf.setUsername("mark");
        cf.setPassword("pass");
        Connection con = cf.newConnection();
        Channel chan = con.createChannel();
        chan.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String message1 = "Hello World!";
        String message2 = "Hozwit World";
        chan.basicPublish("Direct1", "orange", null, message1.getBytes());
        chan.basicPublish("Direct1", "purple", null, message2.getBytes());
        System.out.println(" [x] Sent '" + message1 + "'");
        System.out.println(" [x] Sent '" + message2 + "'");
        chan.close();
        con.close();

    }
}
