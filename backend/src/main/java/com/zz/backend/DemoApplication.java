/*
 * @Date: 2020-03-14 02:55:45
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-15 01:00:33
 * @FilePath: /backend/src/main/java/com/zz/backend/DemoApplication.java
 * @Description: 
 */
package com.zz.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//加载mybatis的mapper接口所在的路径
@MapperScan("com.zz.backend.mapper")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
