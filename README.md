# LogbackTelegramAdapter
使用telegram机器人发送日志

# How to use
## 修改你的`pom.xml`文件
### 增加依赖
```xml
<dependency>
  <groupId>com.github.mayan31370</groupId>
  <artifactId>LogbackTelegramAdapter</artifactId>
  <version>v0.9.4</version>
</dependency>
```
### 增加仓库
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```
## 修改你的`logback.xml`文件
```xml
<appender name="TG" class="win.mayan.utils.TelegramAppender">
  <systemName>your_log_title</systemName>
  <telegramChatId>your_channel_id</telegramChatId>
  <telegramToken>your_bot_token</telegramToken>
</appender>
```