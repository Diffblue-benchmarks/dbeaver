package org.jkiss.dbeaver.model.ai.engine.openai;

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

public class OpenAIModelsDiffblueTest {
  /**
   * Test {@link OpenAIModels#getEffectiveModelName(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@link OpenAIModels#DEFAULT_MODEL}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIModels#getEffectiveModelName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OpenAIModels.getEffectiveModelName(String)"})
  public void testGetEffectiveModelName_whenEmptyString_thenReturnDefault_model() {
    // Arrange, Act and Assert
    assertEquals(OpenAIModels.DEFAULT_MODEL, OpenAIModels.getEffectiveModelName(""));
  }

  /**
   * Test {@link OpenAIModels#getEffectiveModelName(String)}.
   *
   * <ul>
   *   <li>When {@code gpt-3.5-turbo}.
   *   <li>Then return {@code gpt-3.5-turbo}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIModels#getEffectiveModelName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OpenAIModels.getEffectiveModelName(String)"})
  public void testGetEffectiveModelName_whenGpt35Turbo_thenReturnGpt35Turbo() {
    // Arrange, Act and Assert
    assertEquals("gpt-3.5-turbo", OpenAIModels.getEffectiveModelName("gpt-3.5-turbo"));
  }

  /**
   * Test {@link OpenAIModels#getEffectiveModelName(String)}.
   *
   * <ul>
   *   <li>When {@code Model Name}.
   *   <li>Then return {@code Model Name}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIModels#getEffectiveModelName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OpenAIModels.getEffectiveModelName(String)"})
  public void testGetEffectiveModelName_whenModelName_thenReturnModelName() {
    // Arrange, Act and Assert
    assertEquals("Model Name", OpenAIModels.getEffectiveModelName("Model Name"));
  }

  /**
   * Test {@link OpenAIModels#getEffectiveModelName(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link OpenAIModels#DEFAULT_MODEL}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIModels#getEffectiveModelName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OpenAIModels.getEffectiveModelName(String)"})
  public void testGetEffectiveModelName_whenNull_thenReturnDefault_model() {
    // Arrange, Act and Assert
    assertEquals(OpenAIModels.DEFAULT_MODEL, OpenAIModels.getEffectiveModelName(null));
  }

  /**
   * Test {@link OpenAIModels#getModelByName(String)}.
   *
   * <ul>
   *   <li>When empty string.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIModels#getModelByName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional OpenAIModels.getModelByName(String)"})
  public void testGetModelByName_whenEmptyString() {
    // Arrange and Act
    Optional<AIModel> actualModelByName = OpenAIModels.getModelByName("");

    // Assert
    assertFalse(actualModelByName.isPresent());
  }

  /**
   * Test {@link OpenAIModels#getModelByName(String)}.
   *
   * <ul>
   *   <li>When {@code Model Name}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIModels#getModelByName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional OpenAIModels.getModelByName(String)"})
  public void testGetModelByName_whenModelName() {
    // Arrange and Act
    Optional<AIModel> actualModelByName = OpenAIModels.getModelByName("Model Name");

    // Assert
    assertFalse(actualModelByName.isPresent());
  }

  /**
   * Test {@link OpenAIModels#getModelByName(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIModels#getModelByName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Optional OpenAIModels.getModelByName(String)"})
  public void testGetModelByName_whenNull() {
    // Arrange and Act
    Optional<AIModel> actualModelByName = OpenAIModels.getModelByName(null);

    // Assert
    assertFalse(actualModelByName.isPresent());
  }

  /**
   * Test {@link OpenAIModels#detectModelFeatures(String)}.
   *
   * <ul>
   *   <li>When {@code gpt-3.5-turbo}.
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIModels#detectModelFeatures(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set OpenAIModels.detectModelFeatures(String)"})
  public void testDetectModelFeatures_whenGpt35Turbo_thenReturnSizeIsTwo() {
    // Arrange and Act
    Set<AIModelFeature> actualDetectModelFeaturesResult =
        OpenAIModels.detectModelFeatures("gpt-3.5-turbo");

    // Assert
    assertEquals(2, actualDetectModelFeaturesResult.size());
    assertTrue(actualDetectModelFeaturesResult.contains(AIModelFeature.CHAT));
    assertTrue(actualDetectModelFeaturesResult.contains(AIModelFeature.STREAMING));
  }

  /**
   * Test {@link OpenAIModels#detectModelFeatures(String)}.
   *
   * <ul>
   *   <li>When {@code gpt-image}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIModels#detectModelFeatures(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set OpenAIModels.detectModelFeatures(String)"})
  public void testDetectModelFeatures_whenGptImage_thenReturnEmpty() {
    // Arrange and Act
    Set<AIModelFeature> actualDetectModelFeaturesResult =
        OpenAIModels.detectModelFeatures("gpt-image");

    // Assert
    assertTrue(actualDetectModelFeaturesResult.isEmpty());
  }

  /**
   * Test {@link OpenAIModels#detectModelFeatures(String)}.
   *
   * <ul>
   *   <li>When {@code gpt-}.
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIModels#detectModelFeatures(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set OpenAIModels.detectModelFeatures(String)"})
  public void testDetectModelFeatures_whenGpt_thenReturnSizeIsTwo() {
    // Arrange and Act
    Set<AIModelFeature> actualDetectModelFeaturesResult = OpenAIModels.detectModelFeatures("gpt-");

    // Assert
    assertEquals(2, actualDetectModelFeaturesResult.size());
    assertTrue(actualDetectModelFeaturesResult.contains(AIModelFeature.CHAT));
    assertTrue(actualDetectModelFeaturesResult.contains(AIModelFeature.STREAMING));
  }

  /**
   * Test {@link OpenAIModels#detectModelFeatures(String)}.
   *
   * <ul>
   *   <li>When {@code Model Name}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIModels#detectModelFeatures(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set OpenAIModels.detectModelFeatures(String)"})
  public void testDetectModelFeatures_whenModelName_thenReturnEmpty() {
    // Arrange and Act
    Set<AIModelFeature> actualDetectModelFeaturesResult =
        OpenAIModels.detectModelFeatures("Model Name");

    // Assert
    assertTrue(actualDetectModelFeaturesResult.isEmpty());
  }

  /**
   * Test {@link OpenAIModels#detectModelFeatures(String)}.
   *
   * <ul>
   *   <li>When {@code o}.
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIModels#detectModelFeatures(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set OpenAIModels.detectModelFeatures(String)"})
  public void testDetectModelFeatures_whenO_thenReturnSizeIsTwo() {
    // Arrange and Act
    Set<AIModelFeature> actualDetectModelFeaturesResult = OpenAIModels.detectModelFeatures("o");

    // Assert
    assertEquals(2, actualDetectModelFeaturesResult.size());
    assertTrue(actualDetectModelFeaturesResult.contains(AIModelFeature.CHAT));
    assertTrue(actualDetectModelFeaturesResult.contains(AIModelFeature.STREAMING));
  }
}
