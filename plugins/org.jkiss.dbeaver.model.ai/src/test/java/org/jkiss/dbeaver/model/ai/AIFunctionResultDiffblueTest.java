package org.jkiss.dbeaver.model.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.ai.AIFunctionResult.FunctionType;
import org.jkiss.dbeaver.model.runtime.DBRRunnableWithReturn;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AIFunctionResultDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return Callback is {@link DBRRunnableWithReturn}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AIFunctionResult#AIFunctionResult(FunctionType, String, DBRRunnableWithReturn)}
   *   <li>{@link AIFunctionResult#getCallback()}
   *   <li>{@link AIFunctionResult#getType()}
   *   <li>{@link AIFunctionResult#getValue()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AIFunctionResult.<init>(FunctionType, String)",
    "void AIFunctionResult.<init>(FunctionType, String, DBRRunnableWithReturn)",
    "DBRRunnableWithReturn AIFunctionResult.getCallback()",
    "FunctionType AIFunctionResult.getType()",
    "Object AIFunctionResult.getValue()"
  })
  public void testGettersAndSetters_thenReturnCallbackIsDBRRunnableWithReturn() {
    // Arrange
    DBRRunnableWithReturn<Object> callback = mock(DBRRunnableWithReturn.class);

    // Act
    AIFunctionResult actualAiFunctionResult =
        new AIFunctionResult(FunctionType.INFORMATION, "42", callback);
    DBRRunnableWithReturn<?> actualCallback = actualAiFunctionResult.getCallback();
    FunctionType actualType = actualAiFunctionResult.getType();

    // Assert
    assertEquals("42", actualAiFunctionResult.getValue());
    assertEquals(FunctionType.INFORMATION, actualType);
    assertSame(callback, actualCallback);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code INFORMATION}.
   *   <li>Then return Callback is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AIFunctionResult#AIFunctionResult(FunctionType, String)}
   *   <li>{@link AIFunctionResult#getCallback()}
   *   <li>{@link AIFunctionResult#getType()}
   *   <li>{@link AIFunctionResult#getValue()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AIFunctionResult.<init>(FunctionType, String)",
    "void AIFunctionResult.<init>(FunctionType, String, DBRRunnableWithReturn)",
    "DBRRunnableWithReturn AIFunctionResult.getCallback()",
    "FunctionType AIFunctionResult.getType()",
    "Object AIFunctionResult.getValue()"
  })
  public void testGettersAndSetters_whenInformation_thenReturnCallbackIsNull() {
    // Arrange and Act
    AIFunctionResult actualAiFunctionResult = new AIFunctionResult(FunctionType.INFORMATION, "42");
    DBRRunnableWithReturn<?> actualCallback = actualAiFunctionResult.getCallback();
    FunctionType actualType = actualAiFunctionResult.getType();

    // Assert
    assertEquals("42", actualAiFunctionResult.getValue());
    assertNull(actualCallback);
    assertEquals(FunctionType.INFORMATION, actualType);
  }
}
