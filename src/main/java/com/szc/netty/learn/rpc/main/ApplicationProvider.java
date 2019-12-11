package com.szc.netty.learn.rpc.main;

import com.szc.netty.learn.rpc.remoting.server.ServiceConfig;
import com.szc.netty.learn.rpc.service.DemoService;
import com.szc.netty.learn.rpc.service.DemoServiceImpl;
import java.io.IOException;

public class ApplicationProvider {

  public static void main(String[] args) throws IOException {
    ServiceConfig<DemoServiceImpl> service = new ServiceConfig<>();
    service.setInterface(DemoService.class);
    service.setRef(new DemoServiceImpl());
    service.export();

    System.in.read();
  }
}
