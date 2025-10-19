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
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AIEngineResponseChunkDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AIEngineResponseChunk#AIEngineResponseChunk(List)}
   *   <li>{@link AIEngineResponseChunk#getChoices()}
   *   <li>{@link AIEngineResponseChunk#getFunctionCall()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AIEngineResponseChunk.<init>(List)",
    "List AIEngineResponseChunk.getChoices()",
    "AIFunctionCall AIEngineResponseChunk.getFunctionCall()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ArrayList<String> choices = new ArrayList<>();

    // Act
    AIEngineResponseChunk actualAiEngineResponseChunk = new AIEngineResponseChunk(choices);
    List<String> actualChoices = actualAiEngineResponseChunk.getChoices();

    // Assert
    assertNull(actualAiEngineResponseChunk.getFunctionCall());
    assertTrue(actualChoices.isEmpty());
    assertSame(choices, actualChoices);
  }

  /**
   * Test {@link AIEngineResponseChunk#AIEngineResponseChunk(AIFunctionCall)}.
   *
   * <p>Method under test: {@link AIEngineResponseChunk#AIEngineResponseChunk(AIFunctionCall)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AIEngineResponseChunk.<init>(AIFunctionCall)"})
  public void testNewAIEngineResponseChunk() {
    // Arrange
    AIFunctionCall functionCall = new AIFunctionCall();

    // Act
    AIEngineResponseChunk actualAiEngineResponseChunk = new AIEngineResponseChunk(functionCall);

    // Assert
    assertTrue(actualAiEngineResponseChunk.getChoices().isEmpty());
    assertSame(functionCall, actualAiEngineResponseChunk.getFunctionCall());
  }

  /**
   * Test {@link AIEngineResponseChunk#toString()}.
   *
   * <ul>
   *   <li>Then return {@code []}.
   * </ul>
   *
   * <p>Method under test: {@link AIEngineResponseChunk#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AIEngineResponseChunk.toString()"})
  public void testToString_thenReturnLeftSquareBracketRightSquareBracket() {
    // Arrange, Act and Assert
    assertEquals("[]", new AIEngineResponseChunk(new ArrayList<>()).toString());
  }

  /**
   * Test {@link AIEngineResponseChunk#toString()}.
   *
   * <ul>
   *   <li>Then return {@code null(null)}.
   * </ul>
   *
   * <p>Method under test: {@link AIEngineResponseChunk#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AIEngineResponseChunk.toString()"})
  public void testToString_thenReturnNullNull() {
    // Arrange, Act and Assert
    assertEquals("null(null)", new AIEngineResponseChunk(new AIFunctionCall()).toString());
  }
}
