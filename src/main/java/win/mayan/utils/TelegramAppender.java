package win.mayan.utils;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.AppenderBase;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class TelegramAppender extends AppenderBase<LoggingEvent> {

  private String telegramChatId;
  private OkHttpClient httpClient;
  private String telegramToken;
  private String title;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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
    try {
      httpClient.newCall(new Request.Builder().url(String
          .format("https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s", telegramToken,
              telegramChatId, e.getMessage(),
              title + "%0A" + e.getFormattedMessage().replaceAll("[\\r\\n]+", "%0A"))).get()
          .build()).execute();
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
