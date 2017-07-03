1、首先安装rabbitmq
需要进入rabbit_install_windows_64目录：
①先安装otp_win64_20.0.exe(erlang的这个东西)
②再安装rabbitmq-server-3.6.10.exe
③最后写代码时候需要导入amqp-client-4.1.1.jar和slf4j-api-1.7.21.jar
2、运行程序前先要启动rabbitmq-server
可以双击rabbitmq-server-3.6.10.exe安装后sbin目录下的rabbitmq-service.bat
也可以在服务目录下找到RabbitMQ去启动
