package org.jkiss.dbeaver.model.ai.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.model.ai.registry.AIFunctionDescriptor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AIFunctionCallDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AIFunctionCall#AIFunctionCall()}
   *   <li>{@link AIFunctionCall#setArguments(Map)}
   *   <li>{@link AIFunctionCall#setFunctionName(String)}
   *   <li>{@link AIFunctionCall#setHint(String)}
   *   <li>{@link AIFunctionCall#toString()}
   *   <li>{@link AIFunctionCall#getArguments()}
   *   <li>{@link AIFunctionCall#getFunction()}
   *   <li>{@link AIFunctionCall#getFunctionName()}
   *   <li>{@link AIFunctionCall#getHint()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AIFunctionCall.<init>()",
    "void AIFunctionCall.<init>(String, Map)",
    "Map AIFunctionCall.getArguments()",
    "AIFunctionDescriptor AIFunctionCall.getFunction()",
    "String AIFunctionCall.getFunctionName()",
    "String AIFunctionCall.getHint()",
    "void AIFunctionCall.setArguments(Map)",
    "void AIFunctionCall.setFunction(AIFunctionDescriptor)",
    "void AIFunctionCall.setFunctionName(String)",
    "void AIFunctionCall.setHint(String)",
    "String AIFunctionCall.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    AIFunctionCall actualAiFunctionCall = new AIFunctionCall();
    HashMap<String, Object> arguments = new HashMap<>();
    actualAiFunctionCall.setArguments(arguments);
    actualAiFunctionCall.setFunctionName("Function Name");
    actualAiFunctionCall.setHint("Hint");
    String actualToStringResult = actualAiFunctionCall.toString();
    Map<String, Object> actualArguments = actualAiFunctionCall.getArguments();
    AIFunctionDescriptor actualFunction = actualAiFunctionCall.getFunction();
    String actualFunctionName = actualAiFunctionCall.getFunctionName();

    // Assert
    assertEquals("Function Name", actualFunctionName);
    assertEquals("Function Name({})", actualToStringResult);
    assertEquals("Hint", actualAiFunctionCall.getHint());
    assertNull(actualFunction);
    assertTrue(actualArguments.isEmpty());
    assertSame(arguments, actualArguments);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Function Name}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AIFunctionCall#AIFunctionCall(String, Map)}
   *   <li>{@link AIFunctionCall#setArguments(Map)}
   *   <li>{@link AIFunctionCall#setFunctionName(String)}
   *   <li>{@link AIFunctionCall#setHint(String)}
   *   <li>{@link AIFunctionCall#toString()}
   *   <li>{@link AIFunctionCall#getArguments()}
   *   <li>{@link AIFunctionCall#getFunction()}
   *   <li>{@link AIFunctionCall#getFunctionName()}
   *   <li>{@link AIFunctionCall#getHint()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AIFunctionCall.<init>()",
    "void AIFunctionCall.<init>(String, Map)",
    "Map AIFunctionCall.getArguments()",
    "AIFunctionDescriptor AIFunctionCall.getFunction()",
    "String AIFunctionCall.getFunctionName()",
    "String AIFunctionCall.getHint()",
    "void AIFunctionCall.setArguments(Map)",
    "void AIFunctionCall.setFunction(AIFunctionDescriptor)",
    "void AIFunctionCall.setFunctionName(String)",
    "void AIFunctionCall.setHint(String)",
    "String AIFunctionCall.toString()"
  })
  public void testGettersAndSetters_whenFunctionName() {
    // Arrange and Act
    AIFunctionCall actualAiFunctionCall = new AIFunctionCall("Function Name", new HashMap<>());
    HashMap<String, Object> arguments = new HashMap<>();
    actualAiFunctionCall.setArguments(arguments);
    actualAiFunctionCall.setFunctionName("Function Name");
    actualAiFunctionCall.setHint("Hint");
    String actualToStringResult = actualAiFunctionCall.toString();
    Map<String, Object> actualArguments = actualAiFunctionCall.getArguments();
    AIFunctionDescriptor actualFunction = actualAiFunctionCall.getFunction();
    String actualFunctionName = actualAiFunctionCall.getFunctionName();

    // Assert
    assertEquals("Function Name", actualFunctionName);
    assertEquals("Function Name({})", actualToStringResult);
    assertEquals("Hint", actualAiFunctionCall.getHint());
    assertNull(actualFunction);
    assertTrue(actualArguments.isEmpty());
    assertSame(arguments, actualArguments);
  }
}
