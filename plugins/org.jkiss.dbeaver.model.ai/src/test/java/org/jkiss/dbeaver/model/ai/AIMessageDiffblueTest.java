package org.jkiss.dbeaver.model.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.jkiss.dbeaver.model.ai.AIFunctionResult.FunctionType;
import org.jkiss.dbeaver.model.ai.engine.AIFunctionCall;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AIMessageDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AIMessage#AIMessage(AIMessageType, String, String, LocalDateTime)}
   *   <li>{@link AIMessage#toString()}
   *   <li>{@link AIMessage#getContent()}
   *   <li>{@link AIMessage#getFunctionCall()}
   *   <li>{@link AIMessage#getFunctionResult()}
   *   <li>{@link AIMessage#getRawDisplayMessage()}
   *   <li>{@link AIMessage#getRole()}
   *   <li>{@link AIMessage#getTime()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AIMessage.<init>(AIMessageType, String, String, LocalDateTime)",
    "String AIMessage.getContent()",
    "AIFunctionCall AIMessage.getFunctionCall()",
    "AIFunctionResult AIMessage.getFunctionResult()",
    "String AIMessage.getRawDisplayMessage()",
    "AIMessageType AIMessage.getRole()",
    "LocalDateTime AIMessage.getTime()",
    "String AIMessage.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDateTime time = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act
    AIMessage actualAiMessage =
        new AIMessage(AIMessageType.SYSTEM, "Not all who wander are lost", "Display Message", time);
    String actualToStringResult = actualAiMessage.toString();
    String actualContent = actualAiMessage.getContent();
    AIFunctionCall actualFunctionCall = actualAiMessage.getFunctionCall();
    AIFunctionResult actualFunctionResult = actualAiMessage.getFunctionResult();
    String actualRawDisplayMessage = actualAiMessage.getRawDisplayMessage();
    AIMessageType actualRole = actualAiMessage.getRole();

    // Assert
    assertEquals("Display Message", actualRawDisplayMessage);
    assertEquals("Message (SYSTEM): Not all who wander are lost", actualToStringResult);
    assertEquals("Not all who wander are lost", actualContent);
    assertNull(actualFunctionResult);
    assertNull(actualFunctionCall);
    assertEquals(AIMessageType.SYSTEM, actualRole);
    assertSame(time, actualAiMessage.getTime());
  }

  /**
   * Test {@link AIMessage#AIMessage(AIMessageType, String)}.
   *
   * <p>Method under test: {@link AIMessage#AIMessage(AIMessageType, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AIMessage.<init>(AIMessageType, String)"})
  public void testNewAIMessage() {
    // Arrange and Act
    AIMessage actualAiMessage = new AIMessage(AIMessageType.SYSTEM, "Not all who wander are lost");

    // Assert
    assertEquals("Not all who wander are lost", actualAiMessage.getContent());
    assertEquals("Not all who wander are lost", actualAiMessage.getDisplayMessage());
    assertEquals("Not all who wander are lost", actualAiMessage.getRawDisplayMessage());
    assertNull(actualAiMessage.getFunctionResult());
    assertNull(actualAiMessage.getFunctionCall());
    assertEquals(AIMessageType.SYSTEM, actualAiMessage.getRole());
  }

  /**
   * Test {@link AIMessage#systemMessage(String)}.
   *
   * <p>Method under test: {@link AIMessage#systemMessage(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIMessage AIMessage.systemMessage(String)"})
  public void testSystemMessage() {
    // Arrange and Act
    AIMessage actualSystemMessageResult = AIMessage.systemMessage("Not all who wander are lost");

    // Assert
    assertEquals("Not all who wander are lost", actualSystemMessageResult.getContent());
    assertEquals("Not all who wander are lost", actualSystemMessageResult.getDisplayMessage());
    assertEquals("Not all who wander are lost", actualSystemMessageResult.getRawDisplayMessage());
    assertNull(actualSystemMessageResult.getFunctionResult());
    assertNull(actualSystemMessageResult.getFunctionCall());
    assertEquals(AIMessageType.SYSTEM, actualSystemMessageResult.getRole());
  }

  /**
   * Test {@link AIMessage#userMessage(String)}.
   *
   * <p>Method under test: {@link AIMessage#userMessage(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIMessage AIMessage.userMessage(String)"})
  public void testUserMessage() {
    // Arrange and Act
    AIMessage actualUserMessageResult = AIMessage.userMessage("Not all who wander are lost");

    // Assert
    assertEquals("Not all who wander are lost", actualUserMessageResult.getContent());
    assertEquals("Not all who wander are lost", actualUserMessageResult.getDisplayMessage());
    assertEquals("Not all who wander are lost", actualUserMessageResult.getRawDisplayMessage());
    assertNull(actualUserMessageResult.getFunctionResult());
    assertNull(actualUserMessageResult.getFunctionCall());
    assertEquals(AIMessageType.USER, actualUserMessageResult.getRole());
  }

  /**
   * Test {@link AIMessage#isAutoGenerated()}.
   *
   * <p>Method under test: {@link AIMessage#isAutoGenerated()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AIMessage.isAutoGenerated()"})
  public void testIsAutoGenerated() {
    // Arrange
    AIMessage aiMessage =
        new AIMessage(
            AIMessageType.SYSTEM, null, "Display Message", LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act and Assert
    assertTrue(aiMessage.isAutoGenerated());
  }

  /**
   * Test {@link AIMessage#isAutoGenerated()}.
   *
   * <p>Method under test: {@link AIMessage#isAutoGenerated()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AIMessage.isAutoGenerated()"})
  public void testIsAutoGenerated2() {
    // Arrange
    AIMessage aiMessage =
        new AIMessage(
            AIMessageType.SYSTEM,
            "Not all who wander are lost",
            "Display Message",
            LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act and Assert
    assertTrue(aiMessage.isAutoGenerated());
  }

  /**
   * Test {@link AIMessage#isAutoGenerated()}.
   *
   * <ul>
   *   <li>Given {@link AIMessage#AIMessage(AIMessageType, String)} with role is {@code SYSTEM} and
   *       content is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AIMessage#isAutoGenerated()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AIMessage.isAutoGenerated()"})
  public void testIsAutoGenerated_givenAIMessageWithRoleIsSystemAndContentIsNull() {
    // Arrange, Act and Assert
    assertFalse(new AIMessage(AIMessageType.SYSTEM, null).isAutoGenerated());
  }

  /**
   * Test {@link AIMessage#isAutoGenerated()}.
   *
   * <ul>
   *   <li>Given assistantMessage {@code Not all who wander are lost}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AIMessage#isAutoGenerated()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AIMessage.isAutoGenerated()"})
  public void testIsAutoGenerated_givenAssistantMessageNotAllWhoWanderAreLost_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(AIMessage.assistantMessage("Not all who wander are lost").isAutoGenerated());
  }

  /**
   * Test {@link AIMessage#getDisplayMessage()}.
   *
   * <ul>
   *   <li>Then return {@code Not all who wander are lost}.
   * </ul>
   *
   * <p>Method under test: {@link AIMessage#getDisplayMessage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AIMessage.getDisplayMessage()"})
  public void testGetDisplayMessage_thenReturnNotAllWhoWanderAreLost() {
    // Arrange, Act and Assert
    assertEquals(
        "Not all who wander are lost",
        AIMessage.assistantMessage("Not all who wander are lost").getDisplayMessage());
  }

  /**
   * Test {@link AIMessage#getDisplayMessage()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AIMessage#getDisplayMessage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AIMessage.getDisplayMessage()"})
  public void testGetDisplayMessage_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new AIMessage(AIMessageType.SYSTEM, null).getDisplayMessage());
  }

  /**
   * Test {@link AIMessage#withContent(String)}.
   *
   * <p>Method under test: {@link AIMessage#withContent(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIMessage AIMessage.withContent(String)"})
  public void testWithContent() {
    // Arrange and Act
    AIMessage actualWithContentResult =
        AIMessage.assistantMessage("Not all who wander are lost")
            .withContent("Not all who wander are lost");

    // Assert
    assertEquals("Not all who wander are lost", actualWithContentResult.getContent());
    assertEquals("Not all who wander are lost", actualWithContentResult.getDisplayMessage());
    assertEquals("Not all who wander are lost", actualWithContentResult.getRawDisplayMessage());
    assertNull(actualWithContentResult.getFunctionResult());
    assertNull(actualWithContentResult.getFunctionCall());
    assertEquals(AIMessageType.ASSISTANT, actualWithContentResult.getRole());
  }

  /**
   * Test {@link AIMessage#assistantMessage(String)}.
   *
   * <p>Method under test: {@link AIMessage#assistantMessage(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIMessage AIMessage.assistantMessage(String)"})
  public void testAssistantMessage() {
    // Arrange and Act
    AIMessage actualAssistantMessageResult =
        AIMessage.assistantMessage("Not all who wander are lost");

    // Assert
    assertEquals("Not all who wander are lost", actualAssistantMessageResult.getContent());
    assertEquals("Not all who wander are lost", actualAssistantMessageResult.getDisplayMessage());
    assertEquals(
        "Not all who wander are lost", actualAssistantMessageResult.getRawDisplayMessage());
    assertNull(actualAssistantMessageResult.getFunctionResult());
    assertNull(actualAssistantMessageResult.getFunctionCall());
    assertEquals(AIMessageType.ASSISTANT, actualAssistantMessageResult.getRole());
  }

  /**
   * Test {@link AIMessage#functionCall(AIFunctionCall, AIFunctionResult)}.
   *
   * <ul>
   *   <li>Then return DisplayMessage is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link AIMessage#functionCall(AIFunctionCall, AIFunctionResult)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIMessage AIMessage.functionCall(AIFunctionCall, AIFunctionResult)"})
  public void testFunctionCall_thenReturnDisplayMessageIs42() {
    // Arrange
    AIFunctionCall functionCall = new AIFunctionCall();
    AIFunctionResult result = new AIFunctionResult(FunctionType.INFORMATION, "42");

    // Act
    AIMessage actualFunctionCallResult = AIMessage.functionCall(functionCall, result);

    // Assert
    assertEquals("42", actualFunctionCallResult.getDisplayMessage());
    assertEquals("42", actualFunctionCallResult.getRawDisplayMessage());
    assertEquals("null(null)", actualFunctionCallResult.getContent());
    assertEquals(AIMessageType.FUNCTION, actualFunctionCallResult.getRole());
    assertSame(result, actualFunctionCallResult.getFunctionResult());
    assertSame(functionCall, actualFunctionCallResult.getFunctionCall());
  }

  /**
   * Test {@link AIMessage#functionCall(AIFunctionCall, AIFunctionResult)}.
   *
   * <ul>
   *   <li>Then return DisplayMessage is empty string.
   * </ul>
   *
   * <p>Method under test: {@link AIMessage#functionCall(AIFunctionCall, AIFunctionResult)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIMessage AIMessage.functionCall(AIFunctionCall, AIFunctionResult)"})
  public void testFunctionCall_thenReturnDisplayMessageIsEmptyString() {
    // Arrange
    AIFunctionCall functionCall = new AIFunctionCall();
    AIFunctionResult result = new AIFunctionResult(FunctionType.INFORMATION, null);

    // Act
    AIMessage actualFunctionCallResult = AIMessage.functionCall(functionCall, result);

    // Assert
    assertEquals("", actualFunctionCallResult.getDisplayMessage());
    assertEquals("", actualFunctionCallResult.getRawDisplayMessage());
    assertEquals("null(null)", actualFunctionCallResult.getContent());
    assertEquals(AIMessageType.FUNCTION, actualFunctionCallResult.getRole());
    assertSame(result, actualFunctionCallResult.getFunctionResult());
    assertSame(functionCall, actualFunctionCallResult.getFunctionCall());
  }

  /**
   * Test {@link AIMessage#errorMessage(Throwable)}.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable(String, Throwable)} with {@code :} and {@link
   *       IOException#IOException(String)}.
   *   <li>Then return Content is {@code : : foo}.
   * </ul>
   *
   * <p>Method under test: {@link AIMessage#errorMessage(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIMessage AIMessage.errorMessage(Throwable)"})
  public void testErrorMessage_whenThrowableWithColonAndIOException_thenReturnContentIsFoo() {
    // Arrange
    Throwable throwable = new Throwable(":\n", new IOException("foo"));

    // Act
    AIMessage actualErrorMessageResult = AIMessage.errorMessage(throwable);

    // Assert
    assertEquals(":\n:\nfoo", actualErrorMessageResult.getContent());
    assertEquals(":\n:\nfoo", actualErrorMessageResult.getDisplayMessage());
    assertEquals(":\n:\nfoo", actualErrorMessageResult.getRawDisplayMessage());
    assertNull(actualErrorMessageResult.getFunctionResult());
    assertNull(actualErrorMessageResult.getFunctionCall());
    assertEquals(AIMessageType.ERROR, actualErrorMessageResult.getRole());
  }

  /**
   * Test {@link AIMessage#errorMessage(Throwable)}.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable(String)} with {@code :}.
   *   <li>Then return Content is {@code :}.
   * </ul>
   *
   * <p>Method under test: {@link AIMessage#errorMessage(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIMessage AIMessage.errorMessage(Throwable)"})
  public void testErrorMessage_whenThrowableWithColon_thenReturnContentIsColon() {
    // Arrange and Act
    AIMessage actualErrorMessageResult = AIMessage.errorMessage(new Throwable(":\n"));

    // Assert
    assertEquals(":\n", actualErrorMessageResult.getContent());
    assertEquals(":\n", actualErrorMessageResult.getDisplayMessage());
    assertEquals(":\n", actualErrorMessageResult.getRawDisplayMessage());
    assertNull(actualErrorMessageResult.getFunctionResult());
    assertNull(actualErrorMessageResult.getFunctionCall());
    assertEquals(AIMessageType.ERROR, actualErrorMessageResult.getRole());
  }

  /**
   * Test {@link AIMessage#errorMessage(Throwable)}.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable(String, Throwable)} with {@code foo} and {@link
   *       IOException#IOException(String)}.
   *   <li>Then return Content is {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link AIMessage#errorMessage(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIMessage AIMessage.errorMessage(Throwable)"})
  public void testErrorMessage_whenThrowableWithFooAndIOException_thenReturnContentIsFoo() {
    // Arrange
    Throwable throwable = new Throwable("foo", new IOException("foo"));

    // Act
    AIMessage actualErrorMessageResult = AIMessage.errorMessage(throwable);

    // Assert
    assertEquals("foo", actualErrorMessageResult.getContent());
    assertEquals("foo", actualErrorMessageResult.getDisplayMessage());
    assertEquals("foo", actualErrorMessageResult.getRawDisplayMessage());
    assertNull(actualErrorMessageResult.getFunctionResult());
    assertNull(actualErrorMessageResult.getFunctionCall());
    assertEquals(AIMessageType.ERROR, actualErrorMessageResult.getRole());
  }

  /**
   * Test {@link AIMessage#errorMessage(Throwable)}.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.
   *   <li>Then return Content is empty string.
   * </ul>
   *
   * <p>Method under test: {@link AIMessage#errorMessage(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIMessage AIMessage.errorMessage(Throwable)"})
  public void testErrorMessage_whenThrowable_thenReturnContentIsEmptyString() {
    // Arrange and Act
    AIMessage actualErrorMessageResult = AIMessage.errorMessage(new Throwable());

    // Assert
    assertEquals("", actualErrorMessageResult.getContent());
    assertEquals("", actualErrorMessageResult.getDisplayMessage());
    assertEquals("", actualErrorMessageResult.getRawDisplayMessage());
    assertNull(actualErrorMessageResult.getFunctionResult());
    assertNull(actualErrorMessageResult.getFunctionCall());
    assertEquals(AIMessageType.ERROR, actualErrorMessageResult.getRole());
  }

  /**
   * Test {@link AIMessage#userAutoMessage(String, String)}.
   *
   * <p>Method under test: {@link AIMessage#userAutoMessage(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIMessage AIMessage.userAutoMessage(String, String)"})
  public void testUserAutoMessage() {
    // Arrange and Act
    AIMessage actualUserAutoMessageResult = AIMessage.userAutoMessage("Prompt", "Ui Message");

    // Assert
    assertEquals("Prompt", actualUserAutoMessageResult.getContent());
    assertEquals("Ui Message", actualUserAutoMessageResult.getDisplayMessage());
    assertEquals("Ui Message", actualUserAutoMessageResult.getRawDisplayMessage());
    assertNull(actualUserAutoMessageResult.getFunctionResult());
    assertNull(actualUserAutoMessageResult.getFunctionCall());
    assertEquals(AIMessageType.USER, actualUserAutoMessageResult.getRole());
  }
}
