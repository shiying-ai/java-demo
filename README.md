# Java Demo (Spring Boot + SQLite)

单体 Spring Boot 应用，演示基础 Java 语法与 REST CRUD（SQLite）。已包含 Maven Wrapper，支持 Docker 运行。

## 快速开始（本机）
```
./mvnw clean package -DskipTests
java -jar target/java-demo-1.0.0-SNAPSHOT.jar
# 或
./mvnw spring-boot:run
```

接口：`http://localhost:8080/api/users`
- 新增：POST，JSON 示例 `{ "name": "Alice", "age": 26 }`
- 查询：GET `/api/users`
- 按 ID：GET `/api/users/{id}`
- 更新：PUT `/api/users/{id}`
- 删除：DELETE `/api/users/{id}`

## 配置
- SQLite 文件默认在 `./data/demo.db`
- 可用环境变量覆盖：`SPRING_DATASOURCE_URL=jdbc:sqlite:/app/data/demo.db`

## Docker 运行
```
docker build -t java-demo-sqlite:latest .
docker run -d -p 8080:8080 -v $(pwd)/data:/app/data --name java-demo-sqlite java-demo-sqlite:latest
```

## 主要文件
- `src/main/java/com/demo/web/...`：Spring Boot 启动与 CRUD
- `src/main/java/com/demo/basics/App.java`：基础语法示例
- `src/main/resources/application.properties`：数据源配置
- `Dockerfile`：多阶段构建，支持自定义基础镜像
