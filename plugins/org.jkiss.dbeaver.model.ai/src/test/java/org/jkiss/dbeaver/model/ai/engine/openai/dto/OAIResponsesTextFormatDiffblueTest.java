package org.jkiss.dbeaver.model.ai.engine.openai.dto;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OAIResponsesTextFormatDiffblueTest {
  /**
   * Test new {@link OAIResponsesTextFormat} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link OAIResponsesTextFormat}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OAIResponsesTextFormat.<init>()"})
  public void testNewOAIResponsesTextFormat() {
    // Arrange, Act and Assert
    assertNull(new OAIResponsesTextFormat().type);
  }
}
