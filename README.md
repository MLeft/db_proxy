# 动态切换数据源示例demo

无需依赖任何第三方框架，轻松搞定动态数据源切换.

1. 数据库初始化

   ```sql
   create database db1;
   use db1;
   CREATE TABLE `t_test` (
     `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
     `name` varchar(50) DEFAULT NULL,
     PRIMARY KEY (`id`)
   ) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;


   create database db2;
   use db2;
   CREATE TABLE `t_test` (
     `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
     `name` varchar(50) DEFAULT NULL,
     PRIMARY KEY (`id`)
   ) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
   ```

2. 配置db.properties

   ```properties
   #jdbc连接配置
   db.name=db1,db2,db3
   db1.datasource.url = jdbc:mysql://localhost:3306/db1
   db1.datasource.username = root
   db1.datasource.password = mysql

   db2.datasource.url = jdbc:mysql://localhost:3306/db2
   db2.datasource.username = root
   db2.datasource.password = mysql
   ```

3. 启动运行

   ServerApplication.java

4. 接口查阅

   访问 http://127.0.0.1:8088/swagger-ui.html

5. 数据插入

   访问 http://127.0.0.1:8088/db/db_db1/save?name=hello 将插入数据db1 hello

   访问 http://127.0.0.1:8088/db/db_db2/save?name=hello 将插入数据db2 hello

   访问 http://127.0.0.1:8088/db/db_db3/save?name=hello 将报错`db db3 不存在`

修改[20171130]:

1. 修复了一些eclipse下项目报错的问题

2. 加入了数据源动态刷新机制

   新增数据库db3

   ```sql
   create database db3;
   use db3;
   CREATE TABLE `t_test` (
     `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
     `name` varchar(50) DEFAULT NULL,
     PRIMARY KEY (`id`)
   ) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
   ```

   配置db.properties

   ```properties
   db3.datasource.url = jdbc:mysql://localhost:3306/db3
   db3.datasource.username = root
   db3.datasource.password = mysql
   ```

   访问 http://127.0.0.1:8088/db/db_db2/reload 将刷新数据源

   访问 http://127.0.0.1:8088/db/db_db3/save?name=hello 将插入数据db3 hello