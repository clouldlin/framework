1. mvn clean package tomcat7:deploy
2. jenkins trigger
	사용 예는 다음과 같다.
	# 매일 12시에 실행
	00 12 * * *
	# 매주 일요일 1시에 실행
	00 01 * * 7
	# 매일 12시와 5시에 실행
	00 05 * * *
	00 12 * * *