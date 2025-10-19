package org.jkiss.dbeaver.model.ai.engine.openai.dto;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OAIToolParameterDiffblueTest {
  /**
   * Test new {@link OAIToolParameter} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link OAIToolParameter}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OAIToolParameter.<init>()"})
  public void testNewOAIToolParameter() {
    // Arrange and Act
    OAIToolParameter actualOaiToolParameter = new OAIToolParameter();

    // Assert
    assertNull(actualOaiToolParameter.description);
    assertNull(actualOaiToolParameter.type);
    assertNull(actualOaiToolParameter.enumItems);
  }
}
