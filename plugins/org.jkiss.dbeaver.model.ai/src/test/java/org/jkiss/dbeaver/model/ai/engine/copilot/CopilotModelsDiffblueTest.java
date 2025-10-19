package org.jkiss.dbeaver.model.ai.engine.copilot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Optional;
import java.util.Set;
import org.jkiss.dbeaver.model.ai.engine.AIModel;
import org.jkiss.dbeaver.model.ai.engine.AIModelFeature;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CopilotModelsDiffblueTest {
  /**
   * Test {@link CopilotModels#getModelByName(String)}.
   *
   * <ul>
   *   <li>When {@code claude-3.5-sonnet}.
   *   <li>Then return {@link Optional#get()} name is {@code claude-3.5-sonnet}.
   * </ul>
   *
   * <p>Method under test: {@link CopilotModels#getModelByName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CopilotModels.getModelByName(String)"})
  public void testGetModelByName_whenClaude35Sonnet_thenReturnGetNameIsClaude35Sonnet() {
    // Arrange and Act
    Optional<AIModel> actualModelByName = CopilotModels.getModelByName("claude-3.5-sonnet");

    // Assert
    AIModel getResult = actualModelByName.get();
    assertEquals("claude-3.5-sonnet", getResult.name());
    assertEquals(0.0d, getResult.defaultTemperature(), 0.0);
    Set<AIModelFeature> featuresResult = getResult.features();
    assertEquals(1, featuresResult.size());
    assertEquals(200000, getResult.contextWindowSize().intValue());
    assertTrue(actualModelByName.isPresent());
    assertTrue(featuresResult.contains(AIModelFeature.CHAT));
  }

  /**
   * Test {@link CopilotModels#getModelByName(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return not Present.
   * </ul>
   *
   * <p>Method under test: {@link CopilotModels#getModelByName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CopilotModels.getModelByName(String)"})
  public void testGetModelByName_whenEmptyString_thenReturnNotPresent() {
    // Arrange and Act
    Optional<AIModel> actualModelByName = CopilotModels.getModelByName("");

    // Assert
    assertFalse(actualModelByName.isPresent());
  }

  /**
   * Test {@link CopilotModels#getModelByName(String)}.
   *
   * <ul>
   *   <li>When {@code Model}.
   *   <li>Then return not Present.
   * </ul>
   *
   * <p>Method under test: {@link CopilotModels#getModelByName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CopilotModels.getModelByName(String)"})
  public void testGetModelByName_whenModel_thenReturnNotPresent() {
    // Arrange and Act
    Optional<AIModel> actualModelByName = CopilotModels.getModelByName("Model");

    // Assert
    assertFalse(actualModelByName.isPresent());
  }

  /**
   * Test {@link CopilotModels#getModelByName(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return not Present.
   * </ul>
   *
   * <p>Method under test: {@link CopilotModels#getModelByName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional CopilotModels.getModelByName(String)"})
  public void testGetModelByName_whenNull_thenReturnNotPresent() {
    // Arrange and Act
    Optional<AIModel> actualModelByName = CopilotModels.getModelByName(null);

    // Assert
    assertFalse(actualModelByName.isPresent());
  }
}
