package org.jkiss.dbeaver.model.ai.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.model.ai.AIMessageType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AIEngineResponseDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AIEngineResponse#AIEngineResponse(AIMessageType, List)}
   *   <li>{@link AIEngineResponse#setInputTokensConsumed(int)}
   *   <li>{@link AIEngineResponse#setOutputTokensConsumed(int)}
   *   <li>{@link AIEngineResponse#setProcessingTime(int)}
   *   <li>{@link AIEngineResponse#getFunctionCall()}
   *   <li>{@link AIEngineResponse#getInputTokensConsumed()}
   *   <li>{@link AIEngineResponse#getOutputTokensConsumed()}
   *   <li>{@link AIEngineResponse#getProcessingTime()}
   *   <li>{@link AIEngineResponse#getType()}
   *   <li>{@link AIEngineResponse#getVariants()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AIEngineResponse.<init>(AIMessageType, List)",
    "AIFunctionCall AIEngineResponse.getFunctionCall()",
    "int AIEngineResponse.getInputTokensConsumed()",
    "int AIEngineResponse.getOutputTokensConsumed()",
    "int AIEngineResponse.getProcessingTime()",
    "AIMessageType AIEngineResponse.getType()",
    "List AIEngineResponse.getVariants()",
    "void AIEngineResponse.setInputTokensConsumed(int)",
    "void AIEngineResponse.setOutputTokensConsumed(int)",
    "void AIEngineResponse.setProcessingTime(int)"
  })
  public void testGettersAndSetters() {
    // Arrange
    ArrayList<String> variants = new ArrayList<>();

    // Act
    AIEngineResponse actualAiEngineResponse = new AIEngineResponse(AIMessageType.SYSTEM, variants);
    actualAiEngineResponse.setInputTokensConsumed(1);
    actualAiEngineResponse.setOutputTokensConsumed(1);
    actualAiEngineResponse.setProcessingTime(1);
    AIFunctionCall actualFunctionCall = actualAiEngineResponse.getFunctionCall();
    int actualInputTokensConsumed = actualAiEngineResponse.getInputTokensConsumed();
    int actualOutputTokensConsumed = actualAiEngineResponse.getOutputTokensConsumed();
    int actualProcessingTime = actualAiEngineResponse.getProcessingTime();
    AIMessageType actualType = actualAiEngineResponse.getType();
    List<String> actualVariants = actualAiEngineResponse.getVariants();

    // Assert
    assertNull(actualFunctionCall);
    assertEquals(1, actualInputTokensConsumed);
    assertEquals(1, actualOutputTokensConsumed);
    assertEquals(1, actualProcessingTime);
    assertEquals(AIMessageType.SYSTEM, actualType);
    assertTrue(actualVariants.isEmpty());
    assertSame(variants, actualVariants);
  }

  /**
   * Test {@link AIEngineResponse#AIEngineResponse(AIFunctionCall)}.
   *
   * <p>Method under test: {@link AIEngineResponse#AIEngineResponse(AIFunctionCall)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AIEngineResponse.<init>(AIFunctionCall)"})
  public void testNewAIEngineResponse() {
    // Arrange
    AIFunctionCall functionCall = new AIFunctionCall();

    // Act
    AIEngineResponse actualAiEngineResponse = new AIEngineResponse(functionCall);

    // Assert
    assertNull(actualAiEngineResponse.getVariants());
    assertEquals(0, actualAiEngineResponse.getInputTokensConsumed());
    assertEquals(0, actualAiEngineResponse.getOutputTokensConsumed());
    assertEquals(0, actualAiEngineResponse.getProcessingTime());
    assertEquals(AIMessageType.FUNCTION, actualAiEngineResponse.getType());
    assertSame(functionCall, actualAiEngineResponse.getFunctionCall());
  }

  /**
   * Test {@link AIEngineResponse#toString()}.
   *
   * <ul>
   *   <li>Then return {@code AI response (FUNCTION) null(null)}.
   * </ul>
   *
   * <p>Method under test: {@link AIEngineResponse#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AIEngineResponse.toString()"})
  public void testToString_thenReturnAiResponseFunctionNullNull() {
    // Arrange, Act and Assert
    assertEquals(
        "AI response (FUNCTION) null(null)", new AIEngineResponse(new AIFunctionCall()).toString());
  }

  /**
   * Test {@link AIEngineResponse#toString()}.
   *
   * <ul>
   *   <li>Then return {@code AI response (SYSTEM) []}.
   * </ul>
   *
   * <p>Method under test: {@link AIEngineResponse#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AIEngineResponse.toString()"})
  public void testToString_thenReturnAiResponseSystem() {
    // Arrange, Act and Assert
    assertEquals(
        "AI response (SYSTEM) []",
        new AIEngineResponse(AIMessageType.SYSTEM, new ArrayList<>()).toString());
  }
}
