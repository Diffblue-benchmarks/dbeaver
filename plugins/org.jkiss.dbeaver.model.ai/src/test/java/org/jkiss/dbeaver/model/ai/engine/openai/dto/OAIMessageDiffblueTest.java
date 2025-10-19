package org.jkiss.dbeaver.model.ai.engine.openai.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.model.ai.AIMessage;
import org.jkiss.dbeaver.model.ai.AIMessageType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OAIMessageDiffblueTest {
  /**
   * Test {@link OAIMessage#OAIMessage()}.
   *
   * <p>Method under test: {@link OAIMessage#OAIMessage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OAIMessage.<init>()"})
  public void testNewOAIMessage() {
    // Arrange and Act
    OAIMessage actualOaiMessage = new OAIMessage();

    // Assert
    assertEquals("", actualOaiMessage.getFullText());
    assertNull(actualOaiMessage.arguments);
    assertNull(actualOaiMessage.callId);
    assertNull(actualOaiMessage.id);
    assertNull(actualOaiMessage.name);
    assertNull(actualOaiMessage.role);
    assertNull(actualOaiMessage.status);
    assertNull(actualOaiMessage.type);
    assertNull(actualOaiMessage.content);
  }

  /**
   * Test {@link OAIMessage#OAIMessage(AIMessage)}.
   *
   * <ul>
   *   <li>Then return {@link OAIMessage#role} is {@code assistant}.
   * </ul>
   *
   * <p>Method under test: {@link OAIMessage#OAIMessage(AIMessage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OAIMessage.<init>(AIMessage)"})
  public void testNewOAIMessage_thenReturnRoleIsAssistant() {
    // Arrange and Act
    OAIMessage actualOaiMessage =
        new OAIMessage(AIMessage.assistantMessage("Not all who wander are lost"));

    // Assert
    assertEquals("Not all who wander are lost", actualOaiMessage.getFullText());
    List<OAIMessageContent> oaiMessageContentList = actualOaiMessage.content;
    assertEquals(1, oaiMessageContentList.size());
    OAIMessageContent getResult = oaiMessageContentList.get(0);
    assertEquals("Not all who wander are lost", getResult.text);
    assertEquals("assistant", actualOaiMessage.role);
    assertNull(getResult.annotations);
    assertNull(getResult.logprobs);
    assertNull(actualOaiMessage.arguments);
    assertNull(actualOaiMessage.callId);
    assertNull(actualOaiMessage.id);
    assertNull(actualOaiMessage.name);
    assertNull(actualOaiMessage.status);
    assertEquals(OAIMessage.TYPE_MESSAGE, actualOaiMessage.type);
    assertEquals(OAIMessageContent.TYPE_OUTPUT_TEXT, getResult.type);
  }

  /**
   * Test {@link OAIMessage#OAIMessage(AIMessage)}.
   *
   * <ul>
   *   <li>Then return {@link OAIMessage#role} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OAIMessage#OAIMessage(AIMessage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OAIMessage.<init>(AIMessage)"})
  public void testNewOAIMessage_thenReturnRoleIsNull() {
    // Arrange and Act
    OAIMessage actualOaiMessage =
        new OAIMessage(new AIMessage(AIMessageType.FUNCTION, "Not all who wander are lost"));

    // Assert
    assertEquals("Not all who wander are lost", actualOaiMessage.getFullText());
    List<OAIMessageContent> oaiMessageContentList = actualOaiMessage.content;
    assertEquals(1, oaiMessageContentList.size());
    OAIMessageContent getResult = oaiMessageContentList.get(0);
    assertEquals("Not all who wander are lost", getResult.text);
    assertNull(getResult.annotations);
    assertNull(getResult.logprobs);
    assertNull(actualOaiMessage.arguments);
    assertNull(actualOaiMessage.callId);
    assertNull(actualOaiMessage.id);
    assertNull(actualOaiMessage.name);
    assertNull(actualOaiMessage.role);
    assertNull(actualOaiMessage.status);
    assertEquals(OAIMessage.TYPE_MESSAGE, actualOaiMessage.type);
    assertEquals(OAIMessageContent.TYPE_OUTPUT_TEXT, getResult.type);
  }

  /**
   * Test {@link OAIMessage#OAIMessage(AIMessage)}.
   *
   * <ul>
   *   <li>Then return {@link OAIMessage#role} is {@code system}.
   * </ul>
   *
   * <p>Method under test: {@link OAIMessage#OAIMessage(AIMessage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OAIMessage.<init>(AIMessage)"})
  public void testNewOAIMessage_thenReturnRoleIsSystem() {
    // Arrange and Act
    OAIMessage actualOaiMessage =
        new OAIMessage(new AIMessage(AIMessageType.SYSTEM, "Not all who wander are lost"));

    // Assert
    assertEquals("Not all who wander are lost", actualOaiMessage.getFullText());
    List<OAIMessageContent> oaiMessageContentList = actualOaiMessage.content;
    assertEquals(1, oaiMessageContentList.size());
    OAIMessageContent getResult = oaiMessageContentList.get(0);
    assertEquals("Not all who wander are lost", getResult.text);
    assertEquals("system", actualOaiMessage.role);
    assertNull(getResult.annotations);
    assertNull(getResult.logprobs);
    assertNull(actualOaiMessage.arguments);
    assertNull(actualOaiMessage.callId);
    assertNull(actualOaiMessage.id);
    assertNull(actualOaiMessage.name);
    assertNull(actualOaiMessage.status);
    assertEquals(OAIMessage.TYPE_MESSAGE, actualOaiMessage.type);
    assertEquals(OAIMessageContent.TYPE_INPUT_TEXT, getResult.type);
  }

  /**
   * Test {@link OAIMessage#OAIMessage(AIMessage)}.
   *
   * <ul>
   *   <li>Then return {@link OAIMessage#role} is {@code user}.
   * </ul>
   *
   * <p>Method under test: {@link OAIMessage#OAIMessage(AIMessage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OAIMessage.<init>(AIMessage)"})
  public void testNewOAIMessage_thenReturnRoleIsUser() {
    // Arrange and Act
    OAIMessage actualOaiMessage =
        new OAIMessage(new AIMessage(AIMessageType.USER, "Not all who wander are lost"));

    // Assert
    assertEquals("Not all who wander are lost", actualOaiMessage.getFullText());
    List<OAIMessageContent> oaiMessageContentList = actualOaiMessage.content;
    assertEquals(1, oaiMessageContentList.size());
    OAIMessageContent getResult = oaiMessageContentList.get(0);
    assertEquals("Not all who wander are lost", getResult.text);
    assertEquals("user", actualOaiMessage.role);
    assertNull(getResult.annotations);
    assertNull(getResult.logprobs);
    assertNull(actualOaiMessage.arguments);
    assertNull(actualOaiMessage.callId);
    assertNull(actualOaiMessage.id);
    assertNull(actualOaiMessage.name);
    assertNull(actualOaiMessage.status);
    assertEquals(OAIMessage.TYPE_MESSAGE, actualOaiMessage.type);
    assertEquals(OAIMessageContent.TYPE_INPUT_TEXT, getResult.type);
  }

  /**
   * Test {@link OAIMessage#getFullText()}.
   *
   * <ul>
   *   <li>Given {@link OAIMessage#OAIMessage()}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link OAIMessage#getFullText()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String OAIMessage.getFullText()"})
  public void testGetFullText_givenOAIMessage_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", new OAIMessage().getFullText());
  }

  /**
   * Test {@link OAIMessage#getFullText()}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link OAIMessage#getFullText()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String OAIMessage.getFullText()"})
  public void testGetFullText_thenReturnEmptyString() {
    // Arrange
    OAIMessage oaiMessage =
        new OAIMessage(AIMessage.assistantMessage("Not all who wander are lost"));
    oaiMessage.content = new ArrayList<>();

    // Act and Assert
    assertEquals("", oaiMessage.getFullText());
  }

  /**
   * Test {@link OAIMessage#getFullText()}.
   *
   * <ul>
   *   <li>Then return {@code Not all who wander are lost}.
   * </ul>
   *
   * <p>Method under test: {@link OAIMessage#getFullText()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String OAIMessage.getFullText()"})
  public void testGetFullText_thenReturnNotAllWhoWanderAreLost() {
    // Arrange, Act and Assert
    assertEquals(
        "Not all who wander are lost",
        new OAIMessage(AIMessage.assistantMessage("Not all who wander are lost")).getFullText());
  }

  /**
   * Test {@link OAIMessage#getFullText()}.
   *
   * <ul>
   *   <li>Then return {@code TextText}.
   * </ul>
   *
   * <p>Method under test: {@link OAIMessage#getFullText()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String OAIMessage.getFullText()"})
  public void testGetFullText_thenReturnTextText() {
    // Arrange
    ArrayList<OAIMessageContent> oaiMessageContentList = new ArrayList<>();
    oaiMessageContentList.add(new OAIMessageContent(true, "Text"));
    oaiMessageContentList.add(new OAIMessageContent(true, "Text"));
    OAIMessage oaiMessage =
        new OAIMessage(AIMessage.assistantMessage("Not all who wander are lost"));
    oaiMessage.content = oaiMessageContentList;

    // Act and Assert
    assertEquals("TextText", oaiMessage.getFullText());
  }
}
