https://docs.spring.io/spring-framework/docs/
https://docs.spring.io/spring-framework/docs/4.3.18.RELEASE/javadoc-api/

1.elasticjob(http://elasticjob.io/docs/elastic-job-lite/00-overview/)

Quartz Examples(http://www.quartz-scheduler.org/documentation/quartz-2.2.x/examples/Example3.html)
(http://www.quartz-scheduler.org/api/2.2.1/index.html)
“*”字符用于指定所有值。
“?”字符用于Day-of-month字段、Day-of-Week字段，它被用来指定“没有特定值”。
“-”字符用于指定范围，例如“10-12”在小时(Hours)字段中，表示“小时10、11和12”。
“,”字符用于指定额外值，例如“MON,WED,FRI”在day-of-week字段中，表示“星期一、星期三和星期五”
“/”字符用于指定增量，例如Seconds字段中的“0/15”，表示“秒0, 15, 30和45”
“L”字符用于Day-of-month字段、Day-of-Week字段，这个字符是“最后(last)”的缩写。例如：在Day-of-month字段中的值“L”意味着“月的最后一天”——1月的第31天，非闰年2月的第28天。如果在Day-of-Week使用，它简单的意思是“7”或“SAT(星期六)”
“W”字符用于Day-of-month字段，用于指定离给定日期最近的工作日Monday-Friday(周一到周五)。例如，Day-of-month字段中的值“15W”，意味着“从最近的工作日到本月15日”。
“LW”字符用于Day-of-month字段，例如：Day-of-month字段中的值“LW”，意味着“每月的最后一个工作日”。
“#”字符用于Day-of-Week字段，用来指定“第n个”XXX日的月份。例如，Day-of-Week字段中的“6#3”的值表示月份的第三个星期五（第6天=星期五(Friday)，3=月份的第三个）。
字段名				允许值					允许特殊字符
Seconds	 			0-59	 				, - * /
Minutes	 			0-59	 				, - * /
Hours	 			0-23	 				, - * /
Day-of-month	 	1-31	 				, - * ? / L W
Month	 			1-12 or JAN-DEC	 		, - * /
Day-of-Week	 		1-7 or SUN-SAT	 		, - * ? / L #
Year (Optional)	 	empty, 1970-2199	 	, - * /
(1):计划每隔20秒运行一次(0/20 * * * * ?)
(2):计划每隔两分钟运行一次，从分钟的15秒开始(15 0/2 * * * ?)
(3):计划每隔两分钟，上午8点(8am)到下午5点(17点/5pm)(0 0/2 8-17 * * ?)
(4):计划每隔三分钟运行一次，但仅在下午5点到晚上11点之间运行(0 0/3 17-23 * * ?)
(5):计划每月的第1天和第15天的上午10点运行(0 0 10am 1,15 * ?)
(6):计划工作日(周一至周五)每30秒运行一次(0,30 * * ? * MON-FRI)
(7):计划周末(周六和周日)每30秒运行一次(0,30 * * ? * SAT,SUN)
(8):计划每天下午12点(12pm)运行一次(0 0 12 * * ?)
(9):计划每天中午10点15分(10:15am)运行一次(0 15 10 ? * *)
(10):计划2018年每天上午10点15分(10:15am)运行一次(0 15 10 * * ? 2018)
(11):计划每个月的最后一天上午10点15分(10:15am)运行一次(0 15 10 L * ?)
(12):计划每个月的最后一天上午10:15分(10:15am)运行一次(0 15 10 L * ?)
(13):计划每个月的最后一个星期五早上10点15分(10:15am)运行一次(0 15 10 ? * 6L)
(14):计划2002年、2003年、2004年的每个月的最后一个星期五上午10:15分(10:15am)运行一次(0 15 10 ? * 6L 2002-2004)
(15):计划每个月的第三个星期五早上10点15分(10:15am)运行一次(0 15 10 ? * 6#3)