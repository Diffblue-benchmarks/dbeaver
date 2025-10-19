package org.jkiss.dbeaver.model.ai.engine.openai.dto;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OAIResponsesTextDiffblueTest {
  /**
   * Test new {@link OAIResponsesText} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link OAIResponsesText}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OAIResponsesText.<init>()"})
  public void testNewOAIResponsesText() {
    // Arrange and Act
    OAIResponsesText actualOaiResponsesText = new OAIResponsesText();

    // Assert
    assertNull(actualOaiResponsesText.verbosity);
    assertNull(actualOaiResponsesText.format);
  }
}
