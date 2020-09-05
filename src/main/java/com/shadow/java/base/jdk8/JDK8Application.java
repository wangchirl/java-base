package com.shadow.java.base.jdk8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author shadow
 * @create 2020-08-23
 * @description
 */
public class JDK8Application {
	public static void main(String[] args) {

		// 1、函数式接口
		FunctionInterface functionInterface = () ->{
			System.out.println("say");
		};

		// 2、lambda 表达式
		new Thread(() ->{
			System.out.println("run");
		}).start();

		// 3、stream api
		Integer[] arr = {1, 2, 3, 4, 5};
		List<Integer> list = Arrays.asList(arr);
		list.stream().map(item ->{
			return item % 2;
		}).forEach(System.out::println);

		// 4、Optional 断言
		Optional.of(arr).ifPresent(item ->{
			System.out.println(Arrays.toString(item));
		});

		// 5、Date api
		ZoneId zoneId = ZoneId.of("Asia/Shanghai");
		LocalTime localTime = LocalTime.now();
		LocalDate localDate = LocalDate.now();
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(zoneId);
		System.out.println(localTime);
		System.out.println(localDate);
		System.out.println(localDateTime);


	}
}
