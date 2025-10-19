package org.jkiss.dbeaver.model.ai.engine.openai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.ai.engine.AIEngineRequest;
import org.jkiss.dbeaver.model.ai.engine.AIEngineResponseConsumer;
import org.jkiss.dbeaver.model.ai.engine.openai.OpenAIClient.HttpRequestFilter;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OpenAIEngineDiffblueTest {
  /**
   * Test {@link OpenAIEngine#OpenAIEngine(OpenAIBaseProperties)}.
   *
   * <p>Method under test: {@link OpenAIEngine#OpenAIEngine(OpenAIBaseProperties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OpenAIEngine.<init>(OpenAIBaseProperties)"})
  public void testNewOpenAIEngine() {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();

    // Act
    OpenAIEngine<OpenAIBaseProperties> actualOpenAIEngine = new OpenAIEngine<>(openAIProperties);

    // Assert
    assertSame(openAIProperties, actualOpenAIEngine.getProperties());
  }

  /**
   * Test {@link OpenAIEngine#getModels(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link OpenAIProperties} (default constructor) Token is empty string.
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIEngine#getModels(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List OpenAIEngine.getModels(DBRProgressMonitor)"})
  public void testGetModels_givenOpenAIPropertiesTokenIsEmptyString_thenThrowDBException()
      throws DBException {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setToken("");
    OpenAIEngine<OpenAIBaseProperties> openAIEngine = new OpenAIEngine<>(openAIProperties);

    // Act and Assert
    assertThrows(DBException.class, () -> openAIEngine.getModels(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link OpenAIEngine#getModels(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>When {@link LoggingProgressMonitor#LoggingProgressMonitor()}.
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIEngine#getModels(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List OpenAIEngine.getModels(DBRProgressMonitor)"})
  public void testGetModels_whenLoggingProgressMonitor_thenThrowDBException() throws DBException {
    // Arrange
    OpenAIEngine<OpenAIBaseProperties> openAIEngine = new OpenAIEngine<>(new OpenAIProperties());

    // Act and Assert
    assertThrows(DBException.class, () -> openAIEngine.getModels(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link OpenAIEngine#requestCompletion(DBRProgressMonitor, AIEngineRequest)}.
   *
   * <ul>
   *   <li>Given {@link OpenAIProperties} (default constructor) Model is {@code
   *       org.jkiss.dbeaver.application}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIEngine#requestCompletion(DBRProgressMonitor,
   * AIEngineRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.ai.engine.AIEngineResponse OpenAIEngine.requestCompletion(DBRProgressMonitor, AIEngineRequest)"
  })
  public void testRequestCompletion_givenOpenAIPropertiesModelIsOrgJkissDbeaverApplication()
      throws DBException {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setModel("org.jkiss.dbeaver.application");
    openAIProperties.setTemperature(10.0d);
    OpenAIEngine<OpenAIBaseProperties> openAIEngine = new OpenAIEngine<>(openAIProperties);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> openAIEngine.requestCompletion(monitor, new AIEngineRequest(new ArrayList<>())));
  }

  /**
   * Test {@link OpenAIEngine#requestCompletion(DBRProgressMonitor, AIEngineRequest)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIEngine#requestCompletion(DBRProgressMonitor,
   * AIEngineRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.ai.engine.AIEngineResponse OpenAIEngine.requestCompletion(DBRProgressMonitor, AIEngineRequest)"
  })
  public void testRequestCompletion_thenThrowDBException() throws DBException {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setModel("");
    openAIProperties.setTemperature(10.0d);
    OpenAIEngine<OpenAIBaseProperties> openAIEngine = new OpenAIEngine<>(openAIProperties);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> openAIEngine.requestCompletion(monitor, new AIEngineRequest(new ArrayList<>())));
  }

  /**
   * Test {@link OpenAIEngine#requestCompletionStream(DBRProgressMonitor, AIEngineRequest,
   * AIEngineResponseConsumer)}.
   *
   * <p>Method under test: {@link OpenAIEngine#requestCompletionStream(DBRProgressMonitor,
   * AIEngineRequest, AIEngineResponseConsumer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OpenAIEngine.requestCompletionStream(DBRProgressMonitor, AIEngineRequest, AIEngineResponseConsumer)"
  })
  public void testRequestCompletionStream() throws DBException {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setModel("org.jkiss.dbeaver.application");
    openAIProperties.setTemperature(10.0d);
    OpenAIEngine<OpenAIBaseProperties> openAIEngine = new OpenAIEngine<>(openAIProperties);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            openAIEngine.requestCompletionStream(
                monitor,
                new AIEngineRequest(new ArrayList<>()),
                mock(AIEngineResponseConsumer.class)));
  }

  /**
   * Test {@link OpenAIEngine#requestCompletionStream(DBRProgressMonitor, AIEngineRequest,
   * AIEngineResponseConsumer)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIEngine#requestCompletionStream(DBRProgressMonitor,
   * AIEngineRequest, AIEngineResponseConsumer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OpenAIEngine.requestCompletionStream(DBRProgressMonitor, AIEngineRequest, AIEngineResponseConsumer)"
  })
  public void testRequestCompletionStream_thenThrowDBException() throws DBException {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setModel("");
    openAIProperties.setTemperature(10.0d);
    OpenAIEngine<OpenAIBaseProperties> openAIEngine = new OpenAIEngine<>(openAIProperties);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            openAIEngine.requestCompletionStream(
                monitor,
                new AIEngineRequest(new ArrayList<>()),
                mock(AIEngineResponseConsumer.class)));
  }

  /**
   * Test {@link OpenAIEngine#getContextWindowSize(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return {@code 128000}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIEngine#getContextWindowSize(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int OpenAIEngine.getContextWindowSize(DBRProgressMonitor)"})
  public void testGetContextWindowSize_thenReturn128000() throws DBException {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setModel("");
    OpenAIEngine<OpenAIBaseProperties> openAIEngine = new OpenAIEngine<>(openAIProperties);

    // Act and Assert
    assertEquals(128000, openAIEngine.getContextWindowSize(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link OpenAIEngine#getContextWindowSize(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIEngine#getContextWindowSize(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int OpenAIEngine.getContextWindowSize(DBRProgressMonitor)"})
  public void testGetContextWindowSize_thenReturnThree() throws DBException {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setContextWindowSize(3);
    OpenAIEngine<OpenAIBaseProperties> openAIEngine = new OpenAIEngine<>(openAIProperties);

    // Act and Assert
    assertEquals(3, openAIEngine.getContextWindowSize(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link OpenAIEngine#getContextWindowSize(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIEngine#getContextWindowSize(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int OpenAIEngine.getContextWindowSize(DBRProgressMonitor)"})
  public void testGetContextWindowSize_thenThrowDBException() throws DBException {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setModel("org.jkiss.dbeaver.application");
    OpenAIEngine<OpenAIBaseProperties> openAIEngine = new OpenAIEngine<>(openAIProperties);

    // Act and Assert
    assertThrows(
        DBException.class, () -> openAIEngine.getContextWindowSize(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link OpenAIEngine#complete(DBRProgressMonitor, AIEngineRequest)}.
   *
   * <ul>
   *   <li>Given {@link OpenAIProperties} (default constructor) Model is empty string.
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIEngine#complete(DBRProgressMonitor, AIEngineRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.ai.engine.openai.dto.OAIResponsesResponse OpenAIEngine.complete(DBRProgressMonitor, AIEngineRequest)"
  })
  public void testComplete_givenOpenAIPropertiesModelIsEmptyString_thenThrowDBException()
      throws DBException {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setModel("");
    openAIProperties.setTemperature(10.0d);
    OpenAIEngine<OpenAIBaseProperties> openAIEngine = new OpenAIEngine<>(openAIProperties);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> openAIEngine.complete(monitor, new AIEngineRequest(new ArrayList<>())));
  }

  /**
   * Test {@link OpenAIEngine#complete(DBRProgressMonitor, AIEngineRequest)}.
   *
   * <ul>
   *   <li>Given {@link OpenAIProperties} (default constructor) Model is {@code
   *       org.jkiss.dbeaver.application}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIEngine#complete(DBRProgressMonitor, AIEngineRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.ai.engine.openai.dto.OAIResponsesResponse OpenAIEngine.complete(DBRProgressMonitor, AIEngineRequest)"
  })
  public void testComplete_givenOpenAIPropertiesModelIsOrgJkissDbeaverApplication()
      throws DBException {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setModel("org.jkiss.dbeaver.application");
    openAIProperties.setTemperature(10.0d);
    OpenAIEngine<OpenAIBaseProperties> openAIEngine = new OpenAIEngine<>(openAIProperties);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> openAIEngine.complete(monitor, new AIEngineRequest(new ArrayList<>())));
  }

  /**
   * Test {@link OpenAIEngine#createClient()}.
   *
   * <ul>
   *   <li>Given {@link OpenAIProperties} (default constructor) BaseUrl is empty string.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIEngine#createClient()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OpenAIClient OpenAIEngine.createClient()"})
  public void testCreateClient_givenOpenAIPropertiesBaseUrlIsEmptyString() throws DBException {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setBaseUrl("");
    openAIProperties.setToken("ABC123");
    OpenAIEngine<OpenAIBaseProperties> openAIEngine = new OpenAIEngine<>(openAIProperties);

    // Act
    OpenAIClient actualCreateClientResult = openAIEngine.createClient();

    // Assert
    List<HttpRequestFilter> httpRequestFilterList = actualCreateClientResult.requestFilters;
    assertEquals(1, httpRequestFilterList.size());
    assertTrue(httpRequestFilterList.get(0) instanceof OpenAIRequestFilter);
    assertEquals("responses", actualCreateClientResult.getResponsesEndpoint());
    assertEquals(OpenAIClient.OPENAI_ENDPOINT, actualCreateClientResult.baseUrl);
    HttpClient expectedHttpClient = actualCreateClientResult.getHttpClient();
    assertSame(expectedHttpClient, actualCreateClientResult.client.getHttpClient());
  }

  /**
   * Test {@link OpenAIEngine#createClient()}.
   *
   * <ul>
   *   <li>Given {@link OpenAIProperties} (default constructor) Token is empty string.
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIEngine#createClient()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OpenAIClient OpenAIEngine.createClient()"})
  public void testCreateClient_givenOpenAIPropertiesTokenIsEmptyString_thenThrowDBException()
      throws DBException {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setToken("");
    OpenAIEngine<OpenAIBaseProperties> openAIEngine = new OpenAIEngine<>(openAIProperties);

    // Act and Assert
    assertThrows(DBException.class, () -> openAIEngine.createClient());
  }

  /**
   * Test {@link OpenAIEngine#createClient()}.
   *
   * <ul>
   *   <li>Then return {@link OpenAIClient#baseUrl} is {@code https://example.org/example/}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIEngine#createClient()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OpenAIClient OpenAIEngine.createClient()"})
  public void testCreateClient_thenReturnBaseUrlIsHttpsExampleOrgExample() throws DBException {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setBaseUrl("https://example.org/example");
    openAIProperties.setToken("ABC123");
    OpenAIEngine<OpenAIBaseProperties> openAIEngine = new OpenAIEngine<>(openAIProperties);

    // Act
    OpenAIClient actualCreateClientResult = openAIEngine.createClient();

    // Assert
    List<HttpRequestFilter> httpRequestFilterList = actualCreateClientResult.requestFilters;
    assertEquals(1, httpRequestFilterList.size());
    assertTrue(httpRequestFilterList.get(0) instanceof OpenAIRequestFilter);
    assertEquals("https://example.org/example/", actualCreateClientResult.baseUrl);
    assertEquals("responses", actualCreateClientResult.getResponsesEndpoint());
    HttpClient expectedHttpClient = actualCreateClientResult.getHttpClient();
    assertSame(expectedHttpClient, actualCreateClientResult.client.getHttpClient());
  }

  /**
   * Test {@link OpenAIEngine#createClient()}.
   *
   * <ul>
   *   <li>Then return {@link OpenAIClient#baseUrl} is {@link OpenAIClient#OPENAI_ENDPOINT}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIEngine#createClient()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OpenAIClient OpenAIEngine.createClient()"})
  public void testCreateClient_thenReturnBaseUrlIsOpenai_endpoint() throws DBException {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setToken("ABC123");
    OpenAIEngine<OpenAIBaseProperties> openAIEngine = new OpenAIEngine<>(openAIProperties);

    // Act
    OpenAIClient actualCreateClientResult = openAIEngine.createClient();

    // Assert
    List<HttpRequestFilter> httpRequestFilterList = actualCreateClientResult.requestFilters;
    assertEquals(1, httpRequestFilterList.size());
    assertTrue(httpRequestFilterList.get(0) instanceof OpenAIRequestFilter);
    assertEquals("responses", actualCreateClientResult.getResponsesEndpoint());
    assertEquals(OpenAIClient.OPENAI_ENDPOINT, actualCreateClientResult.baseUrl);
    HttpClient expectedHttpClient = actualCreateClientResult.getHttpClient();
    assertSame(expectedHttpClient, actualCreateClientResult.client.getHttpClient());
  }

  /**
   * Test {@link OpenAIEngine#createClient()}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIEngine#createClient()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OpenAIClient OpenAIEngine.createClient()"})
  public void testCreateClient_thenThrowDBException() throws DBException {
    // Arrange
    OpenAIEngine<OpenAIBaseProperties> openAIEngine = new OpenAIEngine<>(new OpenAIProperties());

    // Act and Assert
    assertThrows(DBException.class, () -> openAIEngine.createClient());
  }

  /**
   * Test {@link OpenAIEngine#model()}.
   *
   * <ul>
   *   <li>Given {@link OpenAIProperties} (default constructor) Model is empty string.
   *   <li>Then return {@link OpenAIConstants#DEFAULT_MODEL}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIEngine#model()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String OpenAIEngine.model()"})
  public void testModel_givenOpenAIPropertiesModelIsEmptyString_thenReturnDefault_model()
      throws DBException {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setModel("");
    OpenAIEngine<OpenAIBaseProperties> openAIEngine = new OpenAIEngine<>(openAIProperties);

    // Act and Assert
    assertEquals(OpenAIConstants.DEFAULT_MODEL, openAIEngine.model());
  }

  /**
   * Test {@link OpenAIEngine#model()}.
   *
   * <ul>
   *   <li>Then return {@code org.jkiss.dbeaver.application}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIEngine#model()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String OpenAIEngine.model()"})
  public void testModel_thenReturnOrgJkissDbeaverApplication() throws DBException {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setModel("org.jkiss.dbeaver.application");
    OpenAIEngine<OpenAIBaseProperties> openAIEngine = new OpenAIEngine<>(openAIProperties);

    // Act and Assert
    assertEquals("org.jkiss.dbeaver.application", openAIEngine.model());
  }

  /**
   * Test {@link OpenAIEngine#temperature()}.
   *
   * <ul>
   *   <li>Given {@link OpenAIProperties} (default constructor) Temperature is ten.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIEngine#temperature()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double OpenAIEngine.temperature()"})
  public void testTemperature_givenOpenAIPropertiesTemperatureIsTen_thenReturnTen()
      throws DBException {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setTemperature(10.0d);
    OpenAIEngine<OpenAIBaseProperties> openAIEngine = new OpenAIEngine<>(openAIProperties);

    // Act and Assert
    assertEquals(10.0d, openAIEngine.temperature(), 0.0);
  }
}
