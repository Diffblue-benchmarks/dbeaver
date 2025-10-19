package org.jkiss.dbeaver.model.ai.engine.copilot.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.ai.AIMessage;
import org.jkiss.dbeaver.model.ai.AIMessageType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CopilotMessageDiffblueTest {
  /**
   * Test {@link CopilotMessage#from(AIMessage)}.
   *
   * <ul>
   *   <li>Then return role is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CopilotMessage#from(AIMessage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CopilotMessage CopilotMessage.from(AIMessage)"})
  public void testFrom_thenReturnRoleIsNull() {
    // Arrange and Act
    CopilotMessage actualFromResult =
        CopilotMessage.from(new AIMessage(AIMessageType.FUNCTION, "Not all who wander are lost"));

    // Assert
    assertEquals("Not all who wander are lost", actualFromResult.content());
    assertNull(actualFromResult.role());
  }

  /**
   * Test {@link CopilotMessage#from(AIMessage)}.
   *
   * <ul>
   *   <li>Then return role is {@code system}.
   * </ul>
   *
   * <p>Method under test: {@link CopilotMessage#from(AIMessage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CopilotMessage CopilotMessage.from(AIMessage)"})
  public void testFrom_thenReturnRoleIsSystem() {
    // Arrange and Act
    CopilotMessage actualFromResult =
        CopilotMessage.from(new AIMessage(AIMessageType.SYSTEM, "Not all who wander are lost"));

    // Assert
    assertEquals("Not all who wander are lost", actualFromResult.content());
    assertEquals("system", actualFromResult.role());
  }

  /**
   * Test {@link CopilotMessage#from(AIMessage)}.
   *
   * <ul>
   *   <li>Then return role is {@code user}.
   * </ul>
   *
   * <p>Method under test: {@link CopilotMessage#from(AIMessage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CopilotMessage CopilotMessage.from(AIMessage)"})
  public void testFrom_thenReturnRoleIsUser() {
    // Arrange and Act
    CopilotMessage actualFromResult =
        CopilotMessage.from(new AIMessage(AIMessageType.USER, "Not all who wander are lost"));

    // Assert
    assertEquals("Not all who wander are lost", actualFromResult.content());
    assertEquals("user", actualFromResult.role());
  }

  /**
   * Test {@link CopilotMessage#from(AIMessage)}.
   *
   * <ul>
   *   <li>When assistantMessage {@code Not all who wander are lost}.
   *   <li>Then return role is {@code assistant}.
   * </ul>
   *
   * <p>Method under test: {@link CopilotMessage#from(AIMessage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CopilotMessage CopilotMessage.from(AIMessage)"})
  public void testFrom_whenAssistantMessageNotAllWhoWanderAreLost_thenReturnRoleIsAssistant() {
    // Arrange and Act
    CopilotMessage actualFromResult =
        CopilotMessage.from(AIMessage.assistantMessage("Not all who wander are lost"));

    // Assert
    assertEquals("Not all who wander are lost", actualFromResult.content());
    assertEquals("assistant", actualFromResult.role());
  }
}
