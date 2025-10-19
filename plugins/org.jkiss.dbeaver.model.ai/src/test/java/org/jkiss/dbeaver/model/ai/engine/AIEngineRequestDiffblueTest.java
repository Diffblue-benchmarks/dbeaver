package org.jkiss.dbeaver.model.ai.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.model.ai.AIMessage;
import org.jkiss.dbeaver.model.ai.registry.AIFunctionDescriptor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AIEngineRequestDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AIEngineRequest#AIEngineRequest(List)}
   *   <li>{@link AIEngineRequest#getFunctions()}
   *   <li>{@link AIEngineRequest#getMessages()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AIEngineRequest.<init>(List)",
    "List AIEngineRequest.getFunctions()",
    "List AIEngineRequest.getMessages()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ArrayList<AIMessage> messages = new ArrayList<>();

    // Act
    AIEngineRequest actualAiEngineRequest = new AIEngineRequest(messages);
    List<AIFunctionDescriptor> actualFunctions = actualAiEngineRequest.getFunctions();
    List<AIMessage> actualMessages = actualAiEngineRequest.getMessages();

    // Assert
    assertTrue(actualFunctions.isEmpty());
    assertTrue(actualMessages.isEmpty());
    assertSame(messages, actualMessages);
  }

  /**
   * Test {@link AIEngineRequest#AIEngineRequest(AIMessage)}.
   *
   * <ul>
   *   <li>Then return Messages size is one.
   * </ul>
   *
   * <p>Method under test: {@link AIEngineRequest#AIEngineRequest(AIMessage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AIEngineRequest.<init>(AIMessage)"})
  public void testNewAIEngineRequest_thenReturnMessagesSizeIsOne() {
    // Arrange
    AIMessage message = AIMessage.assistantMessage("Not all who wander are lost");

    // Act
    AIEngineRequest actualAiEngineRequest = new AIEngineRequest(message);

    // Assert
    List<AIMessage> messages = actualAiEngineRequest.getMessages();
    assertEquals(1, messages.size());
    assertTrue(actualAiEngineRequest.getFunctions().isEmpty());
    assertSame(message, messages.get(0));
  }

  /**
   * Test {@link AIEngineRequest#setFunctions(List)}.
   *
   * <ul>
   *   <li>Then {@link AIEngineRequest#AIEngineRequest(List)} with messages is {@link
   *       ArrayList#ArrayList()} Functions Empty.
   * </ul>
   *
   * <p>Method under test: {@link AIEngineRequest#setFunctions(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AIEngineRequest.setFunctions(List)"})
  public void testSetFunctions_thenAIEngineRequestWithMessagesIsArrayListFunctionsEmpty() {
    // Arrange
    AIEngineRequest aiEngineRequest = new AIEngineRequest(new ArrayList<>());

    // Act
    aiEngineRequest.setFunctions(new ArrayList<>());

    // Assert that nothing has changed
    assertTrue(aiEngineRequest.getFunctions().isEmpty());
  }

  /**
   * Test {@link AIEngineRequest#setFunctions(List)}.
   *
   * <ul>
   *   <li>Then {@link AIEngineRequest#AIEngineRequest(List)} with messages is {@link
   *       ArrayList#ArrayList()} Functions is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link AIEngineRequest#setFunctions(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AIEngineRequest.setFunctions(List)"})
  public void testSetFunctions_thenAIEngineRequestWithMessagesIsArrayListFunctionsIsArrayList() {
    // Arrange
    AIEngineRequest aiEngineRequest = new AIEngineRequest(new ArrayList<>());

    ArrayList<AIFunctionDescriptor> functions = new ArrayList<>();
    functions.add(null);

    // Act
    aiEngineRequest.setFunctions(functions);

    // Assert
    assertEquals(functions, aiEngineRequest.getFunctions());
  }

  /**
   * Test {@link AIEngineRequest#setFunctions(List)}.
   *
   * <ul>
   *   <li>Then {@link AIEngineRequest#AIEngineRequest(List)} with messages is {@link
   *       ArrayList#ArrayList()} Functions is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link AIEngineRequest#setFunctions(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AIEngineRequest.setFunctions(List)"})
  public void testSetFunctions_thenAIEngineRequestWithMessagesIsArrayListFunctionsIsArrayList2() {
    // Arrange
    AIEngineRequest aiEngineRequest = new AIEngineRequest(new ArrayList<>());

    ArrayList<AIFunctionDescriptor> functions = new ArrayList<>();
    functions.add(null);
    functions.add(null);

    // Act
    aiEngineRequest.setFunctions(functions);

    // Assert
    assertEquals(functions, aiEngineRequest.getFunctions());
  }
}
