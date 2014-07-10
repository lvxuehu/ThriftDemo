package com.lly.thrift.server;

import com.lly.thrift.demo.DemoService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

import java.nio.ByteBuffer;


/**
 * Created with IntelliJ IDEA.
 * User: liliangyang
 * Date: 14-7-10
 * Time: 上午11:13
 * ThriftDemo
 */
public class Server implements DemoService.Iface {
    private final int port ;

    private final TThreadPoolServer tr_server;

    public Server(int _port) throws TTransportException{
        this.port = _port;
        Factory protoFactory = new TBinaryProtocol.Factory(true, true);
        TServerTransport serverTransport = new TServerSocket(port);
        DemoService.Processor processor = new DemoService.Processor(this);
        tr_server = new TThreadPoolServer(new Args(serverTransport).processor(processor)
                .protocolFactory(protoFactory));
    }

    public void run(){
        tr_server.serve();
    }

    public synchronized void close(){
        tr_server.stop();
    }

    public void add(String key, ByteBuffer value) throws TException {
        System.out.println("invoke 'add'("+key+","+new String(value.array())+")");

    }

    public ByteBuffer get(String key) throws TException {
        System.out.println("invoke 'set'("+key+")");
        ByteBuffer bb = ByteBuffer.wrap("get success".getBytes());
        return bb;
    }


    public static void main(String[] args) throws TTransportException {
        Server server = new Server(8900);
        server.run();
    }
}
