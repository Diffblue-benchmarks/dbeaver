package org.jkiss.dbeaver.model.ai.engine.openai.dto;

import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OAIToolParametersDiffblueTest {
  /**
   * Test new {@link OAIToolParameters} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link OAIToolParameters}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OAIToolParameters.<init>()"})
  public void testNewOAIToolParameters() {
    // Arrange, Act and Assert
    assertTrue(new OAIToolParameters().properties.isEmpty());
  }
}
