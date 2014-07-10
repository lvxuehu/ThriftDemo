package com.lly.thrift.client;

import com.lly.thrift.demo.DemoService;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Created with IntelliJ IDEA.
 * User: liliangyang
 * Date: 14-7-10
 * Time: 上午11:07
 * ThriftDemo
 */
public class LocalClient extends DemoService.Client {
    private TTransport transport;

    public LocalClient(TProtocol prot, int port, TTransport ftransport) throws TTransportException {
        super(prot);
        this.transport = ftransport;
    }

    public void close() {
        this.transport.close();
    }
}
