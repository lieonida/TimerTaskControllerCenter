package cn.com.voc.ControlCenter.TimerTask;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("cn.com.voc.ControlCenter.TimerTask.mapper")
@EnableSwagger2
public class TimerTaskControlCenterApplication {

	private static final Logger log = Logger.getLogger(TimerTaskControlCenterApplication.class);

	public TimerTaskControlCenterApplication(){
	}

	public void init() throws Exception{
	}

	public static void main(String[] args) {
		SpringApplication.run(TimerTaskControlCenterApplication.class, args);
	}

}
