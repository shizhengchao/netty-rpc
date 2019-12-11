package com.szc.netty.learn.rpc.main;

import com.szc.netty.learn.rpc.remoting.client.ReferenceConfig;
import com.szc.netty.learn.rpc.service.DemoService;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ApplicationConsumer {

  public static void main(String[] args) throws IOException {
    ReferenceConfig<DemoService> reference = new ReferenceConfig<>();
    reference.setInterface(DemoService.class);
    reference.setHost("127.0.0.1");
    reference.setPort(20880);
    reference.setConnectTimeout(300000);
    DemoService service = reference.get();
    CountDownLatch cl = new CountDownLatch(100);
    for(int i = 1; i <= 100; i++) {
      int tmpi = i;
      Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            cl.await();
          } catch (InterruptedException e) {
          }
          System.out.println("request: " + tmpi + ", result: " +  service.sayHello("Shizhengchao" + tmpi));
        }
      });
      t.start();
      cl.countDown();
    }
    System.in.read();
  }
}
