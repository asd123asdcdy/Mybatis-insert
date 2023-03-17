package it.dy;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author w5489
 * @Description
 * @date 2022/7/28 4:08
 */

@Slf4j
@SpringBootApplication(scanBasePackages = {"it.dy"})
@MapperScan({"it.dy.mapper"})
public class MybatisInsertApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisInsertApplication.class, args);
    }
}
