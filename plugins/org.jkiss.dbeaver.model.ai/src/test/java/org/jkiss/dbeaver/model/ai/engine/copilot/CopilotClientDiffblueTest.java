package org.jkiss.dbeaver.model.ai.engine.copilot;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.ai.engine.copilot.CopilotClient.DeviceCodeResponse;
import org.jkiss.dbeaver.model.ai.engine.copilot.dto.CopilotChatRequest;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class CopilotClientDiffblueTest {
  /**
   * Test {@link CopilotClient#requestAccessToken(DBRProgressMonitor, DeviceCodeResponse, Future)}.
   *
   * <p>Method under test: {@link CopilotClient#requestAccessToken(DBRProgressMonitor,
   * DeviceCodeResponse, Future)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String CopilotClient.requestAccessToken(DBRProgressMonitor, DeviceCodeResponse, Future)"
  })
  public void testRequestAccessToken() throws InterruptedException, DBException {
    // Arrange
    CopilotClient copilotClient = new CopilotClient();

    DBRProgressMonitor monitor = mock(DBRProgressMonitor.class);
    when(monitor.isCanceled()).thenReturn(true);
    DeviceCodeResponse deviceCodeResponse =
        new DeviceCodeResponse("Device Code", "User Code", "Verification Uri", 1, 42);

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            copilotClient.requestAccessToken(
                monitor, deviceCodeResponse, new CompletableFuture<>()));
    verify(monitor, atLeast(1)).isCanceled();
  }

  /**
   * Test {@link CopilotClient#requestAccessToken(DBRProgressMonitor, DeviceCodeResponse, Future)}.
   *
   * <p>Method under test: {@link CopilotClient#requestAccessToken(DBRProgressMonitor,
   * DeviceCodeResponse, Future)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String CopilotClient.requestAccessToken(DBRProgressMonitor, DeviceCodeResponse, Future)"
  })
  public void testRequestAccessToken2() throws InterruptedException, DBException {
    // Arrange
    CopilotClient copilotClient = new CopilotClient();

    DBRProgressMonitor monitor = mock(DBRProgressMonitor.class);
    when(monitor.isCanceled()).thenReturn(true);
    DeviceCodeResponse deviceCodeResponse =
        new DeviceCodeResponse("", "User Code", "Verification Uri", 1, 42);

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            copilotClient.requestAccessToken(
                monitor, deviceCodeResponse, new CompletableFuture<>()));
    verify(monitor, atLeast(1)).isCanceled();
  }

  /**
   * Test {@link CopilotClient#requestAccessToken(DBRProgressMonitor, DeviceCodeResponse, Future)}.
   *
   * <p>Method under test: {@link CopilotClient#requestAccessToken(DBRProgressMonitor,
   * DeviceCodeResponse, Future)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String CopilotClient.requestAccessToken(DBRProgressMonitor, DeviceCodeResponse, Future)"
  })
  public void testRequestAccessToken3() throws InterruptedException, DBException {
    // Arrange
    CopilotClient copilotClient = new CopilotClient();

    DBRProgressMonitor monitor = mock(DBRProgressMonitor.class);
    when(monitor.isCanceled()).thenReturn(true);
    DeviceCodeResponse deviceCodeResponse =
        new DeviceCodeResponse("Device Code", "User Code", "Verification Uri", 0, 42);

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            copilotClient.requestAccessToken(
                monitor, deviceCodeResponse, new CompletableFuture<>()));
    verify(monitor).isCanceled();
  }

  /**
   * Test {@link CopilotClient#requestSessionToken(DBRProgressMonitor, String)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link CopilotClient#requestSessionToken(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.ai.engine.copilot.dto.CopilotSessionToken CopilotClient.requestSessionToken(DBRProgressMonitor, String)"
  })
  public void testRequestSessionToken_givenFalse_thenThrowDBException() throws DBException {
    // Arrange
    CopilotClient copilotClient = new CopilotClient();

    DBRProgressMonitor monitor = mock(DBRProgressMonitor.class);
    when(monitor.isCanceled()).thenReturn(false);
    doNothing().when(monitor).beginTask(Mockito.<String>any(), anyInt());
    doNothing().when(monitor).done();
    doNothing().when(monitor).subTask(Mockito.<String>any());

    // Act and Assert
    assertThrows(DBException.class, () -> copilotClient.requestSessionToken(monitor, "ABC123"));
    verify(monitor).beginTask("Request AI completion", 1);
    verify(monitor).done();
    verify(monitor, atLeast(1)).isCanceled();
    verify(monitor).subTask("Sending request to https://api.github.com/copilot_internal/v2/token");
  }

  /**
   * Test {@link CopilotClient#chat(DBRProgressMonitor, String, CopilotChatRequest)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link CopilotClient#chat(DBRProgressMonitor, String,
   * CopilotChatRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.ai.engine.copilot.dto.CopilotChatResponse CopilotClient.chat(DBRProgressMonitor, String, CopilotChatRequest)"
  })
  public void testChat_givenFalse_thenThrowDBException() throws DBException {
    // Arrange
    CopilotClient copilotClient = new CopilotClient();

    DBRProgressMonitor monitor = mock(DBRProgressMonitor.class);
    when(monitor.isCanceled()).thenReturn(false);
    doNothing().when(monitor).beginTask(Mockito.<String>any(), anyInt());
    doNothing().when(monitor).done();
    doNothing().when(monitor).subTask(Mockito.<String>any());
    CopilotChatRequest chatRequest =
        new CopilotChatRequest("Model", true, new ArrayList<>(), true, 1, 1, 10.0d);

    // Act and Assert
    assertThrows(DBException.class, () -> copilotClient.chat(monitor, "ABC123", chatRequest));
    verify(monitor).beginTask("Request AI completion", 1);
    verify(monitor).done();
    verify(monitor, atLeast(1)).isCanceled();
    verify(monitor).subTask("Sending request to https://api.githubcopilot.com/chat/completions");
  }

  /**
   * Test {@link CopilotClient#getModels(DBRProgressMonitor, String, boolean)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link CopilotClient#getModels(DBRProgressMonitor, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CopilotClient.getModels(DBRProgressMonitor, String, boolean)"})
  public void testGetModels_whenEmptyString_thenReturnEmpty() {
    // Arrange and Act
    List<String> actualModels = CopilotClient.getModels(new LoggingProgressMonitor(), "", true);

    // Assert
    assertTrue(actualModels.isEmpty());
  }

  /**
   * Test {@link CopilotClient#getModels(DBRProgressMonitor, String, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link CopilotClient#getModels(DBRProgressMonitor, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CopilotClient.getModels(DBRProgressMonitor, String, boolean)"})
  public void testGetModels_whenNull_thenReturnEmpty() {
    // Arrange and Act
    List<String> actualModels = CopilotClient.getModels(new LoggingProgressMonitor(), null, true);

    // Assert
    assertTrue(actualModels.isEmpty());
  }

  /**
   * Test {@link CopilotClient#loadModels(DBRProgressMonitor, String)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link CopilotClient#loadModels(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CopilotClient.loadModels(DBRProgressMonitor, String)"})
  public void testLoadModels_givenFalse_thenThrowDBException() throws DBException {
    // Arrange
    CopilotClient copilotClient = new CopilotClient();

    DBRProgressMonitor monitor = mock(DBRProgressMonitor.class);
    when(monitor.isCanceled()).thenReturn(false);
    doNothing().when(monitor).beginTask(Mockito.<String>any(), anyInt());
    doNothing().when(monitor).done();
    doNothing().when(monitor).subTask(Mockito.<String>any());

    // Act and Assert
    assertThrows(DBException.class, () -> copilotClient.loadModels(monitor, "ABC123"));
    verify(monitor).beginTask("Request AI completion", 1);
    verify(monitor).done();
    verify(monitor, atLeast(1)).isCanceled();
    verify(monitor).subTask("Sending request to https://api.githubcopilot.com/models");
  }
}
