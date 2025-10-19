package org.jkiss.dbeaver.model.ai.engine.openai.dto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OAIToolDiffblueTest {
  /**
   * Test new {@link OAITool} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link OAITool}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OAITool.<init>()"})
  public void testNewOAITool() {
    // Arrange and Act
    OAITool actualOaiTool = new OAITool();

    // Assert
    assertNull(actualOaiTool.description);
    assertNull(actualOaiTool.name);
    assertNull(actualOaiTool.type);
    OAIToolParameters oaiToolParameters = actualOaiTool.parameters;
    assertNull(oaiToolParameters.type);
    assertNull(oaiToolParameters.required);
    assertFalse(actualOaiTool.strict);
    assertFalse(oaiToolParameters.additionalProperties);
    assertTrue(oaiToolParameters.properties.isEmpty());
  }
}
