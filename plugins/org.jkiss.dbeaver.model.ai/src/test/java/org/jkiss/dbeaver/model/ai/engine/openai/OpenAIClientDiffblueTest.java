package org.jkiss.dbeaver.model.ai.engine.openai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.ai.AIMessage;
import org.jkiss.dbeaver.model.ai.engine.AIFunctionCall;
import org.jkiss.dbeaver.model.ai.engine.openai.OpenAIClient.HttpRequestFilter;
import org.jkiss.dbeaver.model.ai.engine.openai.dto.OAIMessage;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.runtime.SubTaskProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class OpenAIClientDiffblueTest {
  /**
   * Test {@link OpenAIClient#OpenAIClient(String, List)}.
   *
   * <ul>
   *   <li>Given {@link HttpRequestFilter}.
   *   <li>Then return {@link OpenAIClient#requestFilters} size is two.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIClient#OpenAIClient(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OpenAIClient.<init>(String, List)"})
  public void testNewOpenAIClient_givenHttpRequestFilter_thenReturnRequestFiltersSizeIsTwo() {
    // Arrange
    ArrayList<HttpRequestFilter> requestFilters = new ArrayList<>();
    requestFilters.add(mock(HttpRequestFilter.class));
    requestFilters.add(mock(HttpRequestFilter.class));

    // Act
    OpenAIClient actualOpenAIClient =
        new OpenAIClient("https://example.org/example", requestFilters);

    // Assert
    assertEquals("https://example.org/example/", actualOpenAIClient.baseUrl);
    assertEquals(2, actualOpenAIClient.requestFilters.size());
    HttpClient expectedHttpClient = actualOpenAIClient.getHttpClient();
    assertSame(expectedHttpClient, actualOpenAIClient.client.getHttpClient());
  }

  /**
   * Test {@link OpenAIClient#OpenAIClient(String, List)}.
   *
   * <ul>
   *   <li>Then return ResponsesEndpoint is {@code responses}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIClient#OpenAIClient(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OpenAIClient.<init>(String, List)"})
  public void testNewOpenAIClient_thenReturnResponsesEndpointIsResponses() {
    // Arrange
    ArrayList<HttpRequestFilter> requestFilters = new ArrayList<>();
    requestFilters.add(mock(HttpRequestFilter.class));

    // Act
    OpenAIClient actualOpenAIClient =
        new OpenAIClient("https://example.org/example", requestFilters);

    // Assert
    assertEquals("https://example.org/example/", actualOpenAIClient.baseUrl);
    assertEquals("responses", actualOpenAIClient.getResponsesEndpoint());
    assertEquals(1, actualOpenAIClient.requestFilters.size());
  }

  /**
   * Test {@link OpenAIClient#OpenAIClient(String, List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@link OpenAIClient#requestFilters} Empty.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIClient#OpenAIClient(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OpenAIClient.<init>(String, List)"})
  public void testNewOpenAIClient_whenArrayList_thenReturnRequestFiltersEmpty() {
    // Arrange and Act
    OpenAIClient actualOpenAIClient =
        new OpenAIClient("https://example.org/example", new ArrayList<>());

    // Assert
    assertEquals("https://example.org/example/", actualOpenAIClient.baseUrl);
    assertTrue(actualOpenAIClient.requestFilters.isEmpty());
    HttpClient expectedHttpClient = actualOpenAIClient.getHttpClient();
    assertSame(expectedHttpClient, actualOpenAIClient.client.getHttpClient());
  }

  /**
   * Test {@link OpenAIClient#OpenAIClient(String, List)}.
   *
   * <ul>
   *   <li>When {@code /}.
   *   <li>Then return {@link OpenAIClient#baseUrl} is {@code /}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIClient#OpenAIClient(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OpenAIClient.<init>(String, List)"})
  public void testNewOpenAIClient_whenSlash_thenReturnBaseUrlIsSlash() {
    // Arrange and Act
    OpenAIClient actualOpenAIClient = new OpenAIClient("/", new ArrayList<>());

    // Assert
    assertEquals("/", actualOpenAIClient.baseUrl);
    assertTrue(actualOpenAIClient.requestFilters.isEmpty());
    HttpClient expectedHttpClient = actualOpenAIClient.getHttpClient();
    assertSame(expectedHttpClient, actualOpenAIClient.client.getHttpClient());
  }

  /**
   * Test {@link OpenAIClient#createFunctionCall(OAIMessage)}.
   *
   * <p>Method under test: {@link OpenAIClient#createFunctionCall(OAIMessage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIFunctionCall OpenAIClient.createFunctionCall(OAIMessage)"})
  public void testCreateFunctionCall() throws DBException {
    // Arrange
    OAIMessage message = new OAIMessage(AIMessage.assistantMessage("Not all who wander are lost"));
    message.arguments = "Not all who wander are lost";

    // Act and Assert
    assertThrows(DBException.class, () -> OpenAIClient.createFunctionCall(message));
  }

  /**
   * Test {@link OpenAIClient#createFunctionCall(OAIMessage)}.
   *
   * <p>Method under test: {@link OpenAIClient#createFunctionCall(OAIMessage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIFunctionCall OpenAIClient.createFunctionCall(OAIMessage)"})
  public void testCreateFunctionCall2() throws DBException {
    // Arrange
    OAIMessage message =
        new OAIMessage(AIMessage.assistantMessage("Error parsing function call arguments"));
    message.arguments = "Not all who wander are lost";

    // Act and Assert
    assertThrows(DBException.class, () -> OpenAIClient.createFunctionCall(message));
  }

  /**
   * Test {@link OpenAIClient#createFunctionCall(OAIMessage)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIClient#createFunctionCall(OAIMessage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIFunctionCall OpenAIClient.createFunctionCall(OAIMessage)"})
  public void testCreateFunctionCall_given42() throws DBException {
    // Arrange
    OAIMessage message = new OAIMessage(AIMessage.assistantMessage("Not all who wander are lost"));
    message.arguments = "42";

    // Act and Assert
    assertThrows(DBException.class, () -> OpenAIClient.createFunctionCall(message));
  }

  /**
   * Test {@link OpenAIClient#createFunctionCall(OAIMessage)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIClient#createFunctionCall(OAIMessage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIFunctionCall OpenAIClient.createFunctionCall(OAIMessage)"})
  public void testCreateFunctionCall_givenEmptyString() throws DBException {
    // Arrange
    OAIMessage message = new OAIMessage(AIMessage.assistantMessage("Not all who wander are lost"));
    message.arguments = "";

    // Act
    AIFunctionCall actualCreateFunctionCallResult = OpenAIClient.createFunctionCall(message);

    // Assert
    assertNull(actualCreateFunctionCallResult.getFunctionName());
    assertNull(actualCreateFunctionCallResult.getHint());
    assertNull(actualCreateFunctionCallResult.getArguments());
    assertNull(actualCreateFunctionCallResult.getFunction());
  }

  /**
   * Test {@link OpenAIClient#createFunctionCall(OAIMessage)}.
   *
   * <ul>
   *   <li>Given {@code Error parsing function call arguments}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIClient#createFunctionCall(OAIMessage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIFunctionCall OpenAIClient.createFunctionCall(OAIMessage)"})
  public void testCreateFunctionCall_givenErrorParsingFunctionCallArguments() throws DBException {
    // Arrange
    OAIMessage message = new OAIMessage(AIMessage.assistantMessage("Not all who wander are lost"));
    message.arguments = "Error parsing function call arguments";

    // Act and Assert
    assertThrows(DBException.class, () -> OpenAIClient.createFunctionCall(message));
  }

  /**
   * Test {@link OpenAIClient#createFunctionCall(OAIMessage)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIClient#createFunctionCall(OAIMessage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIFunctionCall OpenAIClient.createFunctionCall(OAIMessage)"})
  public void testCreateFunctionCall_givenFoo() throws DBException {
    // Arrange
    OAIMessage message = new OAIMessage(AIMessage.assistantMessage("Not all who wander are lost"));
    message.arguments = "foo";

    // Act and Assert
    assertThrows(DBException.class, () -> OpenAIClient.createFunctionCall(message));
  }

  /**
   * Test {@link OpenAIClient#createFunctionCall(OAIMessage)}.
   *
   * <ul>
   *   <li>When {@link OAIMessage#OAIMessage()}.
   *   <li>Then return FunctionName is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIClient#createFunctionCall(OAIMessage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIFunctionCall OpenAIClient.createFunctionCall(OAIMessage)"})
  public void testCreateFunctionCall_whenOAIMessage_thenReturnFunctionNameIsNull()
      throws DBException {
    // Arrange and Act
    AIFunctionCall actualCreateFunctionCallResult =
        OpenAIClient.createFunctionCall(new OAIMessage());

    // Assert
    assertNull(actualCreateFunctionCallResult.getFunctionName());
    assertNull(actualCreateFunctionCallResult.getHint());
    assertNull(actualCreateFunctionCallResult.getArguments());
    assertNull(actualCreateFunctionCallResult.getFunction());
  }

  /**
   * Test {@link OpenAIClient#createClient(String, String)}.
   *
   * <ul>
   *   <li>Then return {@link OpenAIClient#baseUrl} is {@code https://example.org/example/}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIClient#createClient(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OpenAIClient OpenAIClient.createClient(String, String)"})
  public void testCreateClient_thenReturnBaseUrlIsHttpsExampleOrgExample() {
    // Arrange and Act
    OpenAIClient actualCreateClientResult =
        OpenAIClient.createClient("https://example.org/example", "ABC123");

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
   * Test {@link OpenAIClient#createClient(String, String)}.
   *
   * <ul>
   *   <li>When {@code /}.
   *   <li>Then return {@link OpenAIClient#baseUrl} is {@code /}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIClient#createClient(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OpenAIClient OpenAIClient.createClient(String, String)"})
  public void testCreateClient_whenSlash_thenReturnBaseUrlIsSlash() {
    // Arrange and Act
    OpenAIClient actualCreateClientResult = OpenAIClient.createClient("/", "ABC123");

    // Assert
    List<HttpRequestFilter> httpRequestFilterList = actualCreateClientResult.requestFilters;
    assertEquals(1, httpRequestFilterList.size());
    assertTrue(httpRequestFilterList.get(0) instanceof OpenAIRequestFilter);
    assertEquals("/", actualCreateClientResult.baseUrl);
    assertEquals("responses", actualCreateClientResult.getResponsesEndpoint());
    HttpClient expectedHttpClient = actualCreateClientResult.getHttpClient();
    assertSame(expectedHttpClient, actualCreateClientResult.client.getHttpClient());
  }

  /**
   * Test {@link OpenAIClient#getModels(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIClient#getModels(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List OpenAIClient.getModels(DBRProgressMonitor)"})
  public void testGetModels_thenThrowDBException() throws DBException {
    // Arrange
    HttpRequestFilter httpRequestFilter = mock(HttpRequestFilter.class);
    when(httpRequestFilter.filter(Mockito.<HttpRequest>any(), anyBoolean()))
        .thenThrow(new DBException("An error occurred"));

    ArrayList<HttpRequestFilter> requestFilters = new ArrayList<>();
    requestFilters.add(httpRequestFilter);
    OpenAIClient openAIClient = new OpenAIClient("https://example.org/example", requestFilters);

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> openAIClient.getModels(new SubTaskProgressMonitor(new LoggingProgressMonitor())));
    verify(httpRequestFilter).filter(isA(HttpRequest.class), eq(true));
  }

  /**
   * Test {@link OpenAIClient#getResponsesEndpoint()}.
   *
   * <p>Method under test: {@link OpenAIClient#getResponsesEndpoint()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OpenAIClient.getResponsesEndpoint()"})
  public void testGetResponsesEndpoint() {
    // Arrange, Act and Assert
    assertEquals(
        "responses",
        OpenAIClient.createClient("https://example.org/example", "ABC123").getResponsesEndpoint());
  }

  /**
   * Test {@link OpenAIClient#applyFilters(HttpRequest, boolean)} with {@code request}, {@code
   * setContentType}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIClient#applyFilters(HttpRequest, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"HttpRequest OpenAIClient.applyFilters(HttpRequest, boolean)"})
  public void testApplyFiltersWithRequestSetContentType_thenReturnNull() throws DBException {
    // Arrange
    HttpRequestFilter httpRequestFilter = mock(HttpRequestFilter.class);
    when(httpRequestFilter.filter(Mockito.<HttpRequest>any(), anyBoolean())).thenReturn(null);

    ArrayList<HttpRequestFilter> requestFilters = new ArrayList<>();
    requestFilters.add(httpRequestFilter);
    OpenAIClient openAIClient = new OpenAIClient("https://example.org/example", requestFilters);

    // Act
    HttpRequest actualApplyFiltersResult = openAIClient.applyFilters(null, true);

    // Assert
    verify(httpRequestFilter).filter(isNull(), eq(true));
    assertNull(actualApplyFiltersResult);
  }

  /**
   * Test {@link OpenAIClient#applyFilters(HttpRequest)} with {@code request}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIClient#applyFilters(HttpRequest)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"HttpRequest OpenAIClient.applyFilters(HttpRequest)"})
  public void testApplyFiltersWithRequest_thenReturnNull() throws DBException {
    // Arrange
    HttpRequestFilter httpRequestFilter = mock(HttpRequestFilter.class);
    when(httpRequestFilter.filter(Mockito.<HttpRequest>any(), anyBoolean())).thenReturn(null);

    ArrayList<HttpRequestFilter> requestFilters = new ArrayList<>();
    requestFilters.add(httpRequestFilter);
    OpenAIClient openAIClient = new OpenAIClient("https://example.org/example", requestFilters);

    // Act
    HttpRequest actualApplyFiltersResult = openAIClient.applyFilters(null);

    // Assert
    verify(httpRequestFilter).filter(isNull(), eq(true));
    assertNull(actualApplyFiltersResult);
  }

  /**
   * Test {@link OpenAIClient#serializeValue(Object)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code ""}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIClient#serializeValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OpenAIClient.serializeValue(Object)"})
  public void testSerializeValue_whenEmptyString_thenReturnQuotationMarkQuotationMark()
      throws DBException {
    // Arrange, Act and Assert
    assertEquals("\"\"", OpenAIClient.serializeValue(""));
  }

  /**
   * Test {@link OpenAIClient#serializeValue(Object)}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIClient#serializeValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OpenAIClient.serializeValue(Object)"})
  public void testSerializeValue_whenFortyTwo_thenReturn42() throws DBException {
    // Arrange, Act and Assert
    assertEquals("42", OpenAIClient.serializeValue(42));
  }

  /**
   * Test {@link OpenAIClient#serializeValue(Object)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIClient#serializeValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OpenAIClient.serializeValue(Object)"})
  public void testSerializeValue_whenNull_thenReturnNull() throws DBException {
    // Arrange, Act and Assert
    assertEquals("null", OpenAIClient.serializeValue(null));
  }

  /**
   * Test {@link OpenAIClient#serializeValue(Object)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then return {@code "Value"}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIClient#serializeValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OpenAIClient.serializeValue(Object)"})
  public void testSerializeValue_whenValue_thenReturnValue() throws DBException {
    // Arrange, Act and Assert
    assertEquals("\"Value\"", OpenAIClient.serializeValue("Value"));
  }
}
