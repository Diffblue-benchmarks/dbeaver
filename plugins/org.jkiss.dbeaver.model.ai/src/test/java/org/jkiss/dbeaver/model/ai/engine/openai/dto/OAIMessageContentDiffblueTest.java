package org.jkiss.dbeaver.model.ai.engine.openai.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OAIMessageContentDiffblueTest {
  /**
   * Test {@link OAIMessageContent#OAIMessageContent()}.
   *
   * <p>Method under test: {@link OAIMessageContent#OAIMessageContent()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OAIMessageContent.<init>()"})
  public void testNewOAIMessageContent() {
    // Arrange and Act
    OAIMessageContent actualOaiMessageContent = new OAIMessageContent();

    // Assert
    assertNull(actualOaiMessageContent.annotations);
    assertNull(actualOaiMessageContent.logprobs);
    assertNull(actualOaiMessageContent.text);
    assertNull(actualOaiMessageContent.type);
  }

  /**
   * Test {@link OAIMessageContent#OAIMessageContent(boolean, String)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return {@link OAIMessageContent#type} is {@link OAIMessageContent#TYPE_OUTPUT_TEXT}.
   * </ul>
   *
   * <p>Method under test: {@link OAIMessageContent#OAIMessageContent(boolean, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OAIMessageContent.<init>(boolean, String)"})
  public void testNewOAIMessageContent_whenFalse_thenReturnTypeIsType_output_text() {
    // Arrange and Act
    OAIMessageContent actualOaiMessageContent = new OAIMessageContent(false, "Text");

    // Assert
    assertEquals("Text", actualOaiMessageContent.text);
    assertNull(actualOaiMessageContent.annotations);
    assertNull(actualOaiMessageContent.logprobs);
    assertEquals(OAIMessageContent.TYPE_OUTPUT_TEXT, actualOaiMessageContent.type);
  }

  /**
   * Test {@link OAIMessageContent#OAIMessageContent(boolean, String)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return {@link OAIMessageContent#type} is {@link OAIMessageContent#TYPE_INPUT_TEXT}.
   * </ul>
   *
   * <p>Method under test: {@link OAIMessageContent#OAIMessageContent(boolean, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OAIMessageContent.<init>(boolean, String)"})
  public void testNewOAIMessageContent_whenTrue_thenReturnTypeIsType_input_text() {
    // Arrange and Act
    OAIMessageContent actualOaiMessageContent = new OAIMessageContent(true, "Text");

    // Assert
    assertEquals("Text", actualOaiMessageContent.text);
    assertNull(actualOaiMessageContent.annotations);
    assertNull(actualOaiMessageContent.logprobs);
    assertEquals(OAIMessageContent.TYPE_INPUT_TEXT, actualOaiMessageContent.type);
  }
}
