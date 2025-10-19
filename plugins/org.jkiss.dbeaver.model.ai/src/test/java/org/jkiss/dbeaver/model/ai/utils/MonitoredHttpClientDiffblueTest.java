package org.jkiss.dbeaver.model.ai.utils;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.http.HttpClient;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MonitoredHttpClientDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MonitoredHttpClient#MonitoredHttpClient(HttpClient)}
   *   <li>{@link MonitoredHttpClient#getHttpClient()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MonitoredHttpClient.<init>(HttpClient)",
    "HttpClient MonitoredHttpClient.getHttpClient()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull(new MonitoredHttpClient(null).getHttpClient());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MonitoredHttpClient#MonitoredHttpClient(HttpClient)}
   *   <li>{@link MonitoredHttpClient#getHttpClient()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MonitoredHttpClient.<init>(HttpClient)",
    "HttpClient MonitoredHttpClient.getHttpClient()"
  })
  public void testGettersAndSetters2() {
    // Arrange, Act and Assert
    assertNull(new MonitoredHttpClient(null).getHttpClient());
  }
}
