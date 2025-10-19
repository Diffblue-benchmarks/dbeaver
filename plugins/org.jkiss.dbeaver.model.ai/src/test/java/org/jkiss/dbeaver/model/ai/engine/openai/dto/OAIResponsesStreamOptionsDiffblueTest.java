package org.jkiss.dbeaver.model.ai.engine.openai.dto;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OAIResponsesStreamOptionsDiffblueTest {
  /**
   * Test new {@link OAIResponsesStreamOptions} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link OAIResponsesStreamOptions}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OAIResponsesStreamOptions.<init>()"})
  public void testNewOAIResponsesStreamOptions() {
    // Arrange, Act and Assert
    assertNull(new OAIResponsesStreamOptions().includeObfuscation);
  }
}
