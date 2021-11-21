package cn.yangwanhao.springsecurity.config.run;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.err.println("------------------------启动成功，线程休眠5秒钟---------------------------------");
        Thread.sleep(5000);
        System.err.println("------------------------启动成功，线程休眠结束---------------------------------");
    }
}
