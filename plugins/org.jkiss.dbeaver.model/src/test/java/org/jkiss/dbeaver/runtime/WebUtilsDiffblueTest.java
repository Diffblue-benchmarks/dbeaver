package org.jkiss.dbeaver.runtime;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mockStatic;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import org.jkiss.dbeaver.model.connection.DBPAuthInfo;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.DefaultProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LocalCacheProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.runtime.ProgressMonitorWithExceptionContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class WebUtilsDiffblueTest {
  /**
   * Test {@link WebUtils#openURLConnection(DBRProgressMonitor, String, DBPAuthInfo, String, String,
   * int, int, Map)} with {@code monitor}, {@code urlString}, {@code authInfo}, {@code referrer},
   * {@code method}, {@code retryNumber}, {@code timeout}, {@code headers}.
   *
   * <p>Method under test: {@link WebUtils#openURLConnection(DBRProgressMonitor, String,
   * DBPAuthInfo, String, String, int, int, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.net.URLConnection WebUtils.openURLConnection(DBRProgressMonitor, String, DBPAuthInfo, String, String, int, int, Map)"
  })
  public void
      testOpenURLConnectionWithMonitorUrlStringAuthInfoReferrerMethodRetryNumberTimeoutHeaders()
          throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IOException.class,
        () ->
            WebUtils.openURLConnection(
                new ProgressMonitorWithExceptionContext(new LoggingProgressMonitor()),
                "https://example.org/example",
                null,
                null,
                "POST",
                11,
                10,
                null));
  }

  /**
   * Test {@link WebUtils#openURLConnection(String, DBPAuthInfo, String, String, int, int, Map)}
   * with {@code urlString}, {@code authInfo}, {@code referrer}, {@code method}, {@code
   * retryNumber}, {@code timeout}, {@code headers}.
   *
   * <p>Method under test: {@link WebUtils#openURLConnection(String, DBPAuthInfo, String, String,
   * int, int, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.net.URLConnection WebUtils.openURLConnection(String, DBPAuthInfo, String, String, int, int, Map)"
  })
  public void testOpenURLConnectionWithUrlStringAuthInfoReferrerMethodRetryNumberTimeoutHeaders()
      throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IOException.class,
        () ->
            WebUtils.openURLConnection(
                "https://example.org/example", null, null, "POST", 11, 10, null));
  }

  /**
   * Test {@link WebUtils#downloadRemoteFile(DBRProgressMonitor, String, String, Path, DBPAuthInfo)}
   * with {@code monitor}, {@code taskName}, {@code externalURL}, {@code localFile}, {@code
   * authInfo}.
   *
   * <p>Method under test: {@link WebUtils#downloadRemoteFile(DBRProgressMonitor, String, String,
   * Path, DBPAuthInfo)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "long WebUtils.downloadRemoteFile(DBRProgressMonitor, String, String, Path, DBPAuthInfo)"
  })
  public void testDownloadRemoteFileWithMonitorTaskNameExternalURLLocalFileAuthInfo()
      throws IOException, InterruptedException {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles
          .when(() -> Files.newOutputStream(Mockito.<Path>any(), isA(OpenOption[].class)))
          .thenThrow(new IOException());
      LoggingProgressMonitor monitor = new LoggingProgressMonitor();
      Path localFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
      DBPAuthInfo authInfo = new DBPAuthInfo("janedoe", "iloveyou", true);

      // Act and Assert
      assertThrows(
          IOException.class,
          () ->
              WebUtils.downloadRemoteFile(
                  monitor, "Task Name", "https://example.org/example", localFile, authInfo));
      mockFiles.verify(() -> Files.newOutputStream(Mockito.<Path>any(), isA(OpenOption[].class)));
    }
  }

  /**
   * Test {@link WebUtils#downloadRemoteFile(DBRProgressMonitor, String, String, Path, DBPAuthInfo)}
   * with {@code monitor}, {@code taskName}, {@code externalURL}, {@code localFile}, {@code
   * authInfo}.
   *
   * <p>Method under test: {@link WebUtils#downloadRemoteFile(DBRProgressMonitor, String, String,
   * Path, DBPAuthInfo)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "long WebUtils.downloadRemoteFile(DBRProgressMonitor, String, String, Path, DBPAuthInfo)"
  })
  public void testDownloadRemoteFileWithMonitorTaskNameExternalURLLocalFileAuthInfo2()
      throws IOException, InterruptedException {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles
          .when(() -> Files.newOutputStream(Mockito.<Path>any(), isA(OpenOption[].class)))
          .thenThrow(new IOException());
      DefaultProgressMonitor monitor =
          new DefaultProgressMonitor(new LocalCacheProgressMonitor(new LoggingProgressMonitor()));
      Path localFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
      DBPAuthInfo authInfo = new DBPAuthInfo("janedoe", "iloveyou", true);

      // Act and Assert
      assertThrows(
          IOException.class,
          () ->
              WebUtils.downloadRemoteFile(
                  monitor, "Task Name", "https://example.org/example", localFile, authInfo));
      mockFiles.verify(() -> Files.newOutputStream(Mockito.<Path>any(), isA(OpenOption[].class)));
    }
  }
}
