package org.jkiss.dbeaver.model.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.model.ai.AIAssistantResponse.Type;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AIAssistantResponseDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AIAssistantResponse#AIAssistantResponse(Type, Object)}
   *   <li>{@link AIAssistantResponse#setFunctionsRefs(List)}
   *   <li>{@link AIAssistantResponse#getFunctionsRefs()}
   *   <li>{@link AIAssistantResponse#getType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AIAssistantResponse.<init>(Type, Object)",
    "List AIAssistantResponse.getFunctionsRefs()",
    "Type AIAssistantResponse.getType()",
    "void AIAssistantResponse.setFunctionsRefs(List)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    AIAssistantResponse actualAiAssistantResponse = new AIAssistantResponse(Type.TEXT, "Result");
    ArrayList<AIFunctionReference> functionsRefs = new ArrayList<>();
    actualAiAssistantResponse.setFunctionsRefs(functionsRefs);
    List<AIFunctionReference> actualFunctionsRefs = actualAiAssistantResponse.getFunctionsRefs();

    // Assert
    assertEquals(Type.TEXT, actualAiAssistantResponse.getType());
    assertTrue(actualFunctionsRefs.isEmpty());
    assertSame(functionsRefs, actualFunctionsRefs);
  }

  /**
   * Test {@link AIAssistantResponse#isText()}.
   *
   * <ul>
   *   <li>Given {@link AIAssistantResponse#AIAssistantResponse(Type, Object)} with type is {@code
   *       FUNCTION} and {@code Result}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AIAssistantResponse#isText()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AIAssistantResponse.isText()"})
  public void testIsText_givenAIAssistantResponseWithTypeIsFunctionAndResult_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new AIAssistantResponse(Type.FUNCTION, "Result").isText());
  }

  /**
   * Test {@link AIAssistantResponse#isText()}.
   *
   * <ul>
   *   <li>Given {@link AIAssistantResponse#AIAssistantResponse(Type, Object)} with type is {@code
   *       TEXT} and {@code Result}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AIAssistantResponse#isText()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AIAssistantResponse.isText()"})
  public void testIsText_givenAIAssistantResponseWithTypeIsTextAndResult_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new AIAssistantResponse(Type.TEXT, "Result").isText());
  }

  /**
   * Test {@link AIAssistantResponse#isError()}.
   *
   * <ul>
   *   <li>Given {@link AIAssistantResponse#AIAssistantResponse(Type, Object)} with type is {@link
   *       Type#ERROR} and {@code Result}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AIAssistantResponse#isError()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AIAssistantResponse.isError()"})
  public void testIsError_givenAIAssistantResponseWithTypeIsErrorAndResult_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new AIAssistantResponse(Type.ERROR, "Result").isError());
  }

  /**
   * Test {@link AIAssistantResponse#isError()}.
   *
   * <ul>
   *   <li>Given {@link AIAssistantResponse#AIAssistantResponse(Type, Object)} with type is {@code
   *       TEXT} and {@code Result}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AIAssistantResponse#isError()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AIAssistantResponse.isError()"})
  public void testIsError_givenAIAssistantResponseWithTypeIsTextAndResult_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new AIAssistantResponse(Type.TEXT, "Result").isError());
  }

  /**
   * Test {@link AIAssistantResponse#getText()}.
   *
   * <ul>
   *   <li>Given {@link AIAssistantResponse#AIAssistantResponse(Type, Object)} with type is {@code
   *       TEXT} and result is one.
   *   <li>Then return {@code 1}.
   * </ul>
   *
   * <p>Method under test: {@link AIAssistantResponse#getText()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AIAssistantResponse.getText()"})
  public void testGetText_givenAIAssistantResponseWithTypeIsTextAndResultIsOne_thenReturn1() {
    // Arrange, Act and Assert
    assertEquals("1", new AIAssistantResponse(Type.TEXT, 1).getText());
  }

  /**
   * Test {@link AIAssistantResponse#getText()}.
   *
   * <ul>
   *   <li>Given {@link AIAssistantResponse#AIAssistantResponse(Type, Object)} with type is {@code
   *       TEXT} and {@code Result}.
   *   <li>Then return {@code Result}.
   * </ul>
   *
   * <p>Method under test: {@link AIAssistantResponse#getText()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AIAssistantResponse.getText()"})
  public void testGetText_givenAIAssistantResponseWithTypeIsTextAndResult_thenReturnResult() {
    // Arrange, Act and Assert
    assertEquals("Result", new AIAssistantResponse(Type.TEXT, "Result").getText());
  }

  /**
   * Test {@link AIAssistantResponse#getText()}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link AIAssistantResponse#getText()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AIAssistantResponse.getText()"})
  public void testGetText_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", new AIAssistantResponse(Type.TEXT, null).getText());
  }
}
