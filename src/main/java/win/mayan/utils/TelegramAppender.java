package win.mayan.utils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.net.SMTPAppender;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.AppenderBase;
import java.io.IOException;
import java.net.URLEncoder;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class TelegramAppender extends AppenderBase<LoggingEvent> {

  private String telegramChatId;
  private OkHttpClient httpClient;
  private String telegramToken;
  private String systemName;

  public static void main(String[] args) {
    try {
      new SMTPAppender().start();
    }catch (Throwable e){
      e.getStackTrace();
    }
  }

  public String getSystemName() {
    return systemName;
  }

  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }

  public String getTelegramChatId() {
    return telegramChatId;
  }

  public void setTelegramChatId(String telegramChatId) {
    this.telegramChatId = telegramChatId;
  }

  public String getTelegramToken() {
    return telegramToken;
  }

  public void setTelegramToken(String telegramToken) {
    this.telegramToken = telegramToken;
  }

  @Override
  protected void append(LoggingEvent e) {
    if (e.getLevel() != Level.ERROR) {
      return;
    }
    try {
      StringBuffer msg = new StringBuffer();
      msg.append('*').append(systemName).append('*').append('\n');
      msg.append('_').append(e.getLoggerName()).append("_: ").append(e.getFormattedMessage());
      StringBuffer stackTrace = new StringBuffer();
      stackTrace.append('\n').append("```").append('\n');
      for (StackTraceElement ste : e.getCallerData()) {
        if (ste.getFileName() != null) {
          stackTrace.append(ste.getClassName()).append('.').append(ste.getMethodName()).append('(')
              .append(ste.getFileName()).append(':').append(ste.getLineNumber()).append(')')
              .append('\n');
        }
      }
      stackTrace.append("```");
      String text = URLEncoder.encode(msg.toString(), "utf8");
      String longText = URLEncoder.encode(msg.append(stackTrace).toString(), "utf8");
      httpClient.newCall(new Request.Builder().url(String.format(
          "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&parse_mode=Markdown&text=%s",
          telegramToken, telegramChatId, longText.length() > 4096 ? text : longText)).get().build())
          .execute();
    } catch (IOException ex) {
      //do nothing here
    }
  }

  @Override
  public void start() {
    super.start();
    httpClient = new OkHttpClient.Builder().build();
  }
}
