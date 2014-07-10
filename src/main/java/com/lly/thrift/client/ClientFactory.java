package com.lly.thrift.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created with IntelliJ IDEA.
 * User: liliangyang
 * Date: 14-7-10
 * Time: 上午11:08
 * ThriftDemo
 */
public class ClientFactory {
    public static LocalClient getClient(String ip, int port) throws TTransportException, IOException {
        TSocket transport = new TSocket(ip, port);

        TProtocol protocol = new TBinaryProtocol(transport);
        transport.open();
        LocalClient client = new LocalClient(protocol, port, transport);
        return client;
    }


    public static void main(String[] args) throws IOException, TException {
        LocalClient client = ClientFactory.getClient("localhost", 8900);
        ByteBuffer bb = ByteBuffer.wrap("Hello".getBytes());
        client.add("abc", bb);
        System.out.println("ok");

        System.out.println(new String(client.get("aaa").array()));
    }
}
