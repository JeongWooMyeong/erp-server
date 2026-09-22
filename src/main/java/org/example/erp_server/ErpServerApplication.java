package org.example.erp_server;

import org.example.erp_server.util.NetWorkUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication(scanBasePackages = {
        "org.example.erp_server",
        "org.example.erp"
})
@EntityScan(basePackages = {
        "org.example.erp_server",
        "org.example.erp"
})
@EnableElasticsearchRepositories(
        basePackages = {
                "org.example.erp_server",
                "org.example.erp.search"
        }
)
public class ErpServerApplication {

    public static void main(String[] args) throws Exception{
//        String ip = NetWorkUtil.getWifiIp();
//
//        System.out.println("현재 서버 IP : " + ip);
//
//        System.setProperty("server-host", ip);

        SpringApplication.run(ErpServerApplication.class, args);
    }

}
