package org.jkiss.dbeaver.model.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.jkiss.dbeaver.model.ai.engine.AIEngineProperties;
import org.jkiss.dbeaver.model.ai.engine.copilot.CopilotProperties;
import org.jkiss.dbeaver.model.ai.engine.openai.OpenAIProperties;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AISettingsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link AISettings}
   *   <li>{@link AISettings#setAiDisabled(boolean)}
   *   <li>{@link AISettings#activeEngine()}
   *   <li>{@link AISettings#getEngineConfigurations()}
   *   <li>{@link AISettings#isAiDisabled()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AISettings.<init>()",
    "String AISettings.activeEngine()",
    "Map AISettings.getEngineConfigurations()",
    "boolean AISettings.isAiDisabled()",
    "void AISettings.setAiDisabled(boolean)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    AISettings actualAiSettings = new AISettings();
    actualAiSettings.setAiDisabled(true);
    String actualActiveEngineResult = actualAiSettings.activeEngine();
    Map<String, AIEngineProperties> actualEngineConfigurations =
        actualAiSettings.getEngineConfigurations();
    boolean actualIsAiDisabledResult = actualAiSettings.isAiDisabled();

    // Assert
    assertNull(actualActiveEngineResult);
    assertTrue(actualEngineConfigurations.isEmpty());
    assertTrue(actualIsAiDisabledResult);
  }

  /**
   * Test {@link AISettings#getEnabledFunctions()}.
   *
   * <p>Method under test: {@link AISettings#getEnabledFunctions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set AISettings.getEnabledFunctions()"})
  public void testGetEnabledFunctions() {
    // Arrange, Act and Assert
    assertTrue(new AISettings().getEnabledFunctions().isEmpty());
  }

  /**
   * Test {@link AISettings#setEnabledFunctions(Set)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link HashSet#HashSet()} add {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link AISettings#setEnabledFunctions(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AISettings.setEnabledFunctions(Set)"})
  public void testSetEnabledFunctions_given42_whenHashSetAdd42() {
    // Arrange
    AISettings aiSettings = new AISettings();

    HashSet<String> functions = new HashSet<>();
    functions.add("42");
    functions.add("foo");

    // Act
    aiSettings.setEnabledFunctions(functions);

    // Assert
    assertEquals(functions, aiSettings.getEnabledFunctions());
  }

  /**
   * Test {@link AISettings#setEnabledFunctions(Set)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>Then {@link AISettings} (default constructor) EnabledFunctions is {@link
   *       HashSet#HashSet()}.
   * </ul>
   *
   * <p>Method under test: {@link AISettings#setEnabledFunctions(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AISettings.setEnabledFunctions(Set)"})
  public void testSetEnabledFunctions_givenFoo_thenAISettingsEnabledFunctionsIsHashSet() {
    // Arrange
    AISettings aiSettings = new AISettings();

    HashSet<String> functions = new HashSet<>();
    functions.add("foo");

    // Act
    aiSettings.setEnabledFunctions(functions);

    // Assert
    assertEquals(functions, aiSettings.getEnabledFunctions());
  }

  /**
   * Test {@link AISettings#setEnabledFunctions(Set)}.
   *
   * <ul>
   *   <li>When {@link HashSet#HashSet()}.
   *   <li>Then {@link AISettings} (default constructor) EnabledFunctions Empty.
   * </ul>
   *
   * <p>Method under test: {@link AISettings#setEnabledFunctions(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AISettings.setEnabledFunctions(Set)"})
  public void testSetEnabledFunctions_whenHashSet_thenAISettingsEnabledFunctionsEmpty() {
    // Arrange
    AISettings aiSettings = new AISettings();

    // Act
    aiSettings.setEnabledFunctions(new HashSet<>());

    // Assert that nothing has changed
    assertTrue(aiSettings.getEnabledFunctions().isEmpty());
  }

  /**
   * Test {@link AISettings#setEnabledFunctions(Set)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link AISettings} (default constructor) EnabledFunctions Empty.
   * </ul>
   *
   * <p>Method under test: {@link AISettings#setEnabledFunctions(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AISettings.setEnabledFunctions(Set)"})
  public void testSetEnabledFunctions_whenNull_thenAISettingsEnabledFunctionsEmpty() {
    // Arrange
    AISettings aiSettings = new AISettings();

    // Act
    aiSettings.setEnabledFunctions(null);

    // Assert that nothing has changed
    assertTrue(aiSettings.getEnabledFunctions().isEmpty());
  }

  /**
   * Test {@link AISettings#isFunctionEnabled(String)}.
   *
   * <p>Method under test: {@link AISettings#isFunctionEnabled(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AISettings.isFunctionEnabled(String)"})
  public void testIsFunctionEnabled() {
    // Arrange, Act and Assert
    assertFalse(new AISettings().isFunctionEnabled("42"));
  }

  /**
   * Test {@link AISettings#enableFunction(String)}.
   *
   * <p>Method under test: {@link AISettings#enableFunction(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AISettings.enableFunction(String)"})
  public void testEnableFunction() {
    // Arrange
    AISettings aiSettings = new AISettings();

    // Act
    aiSettings.enableFunction("42");

    // Assert
    Set<String> enabledFunctions = aiSettings.getEnabledFunctions();
    assertEquals(1, enabledFunctions.size());
    assertTrue(enabledFunctions.contains("42"));
  }

  /**
   * Test {@link AISettings#getEnabledFunctionCategories()}.
   *
   * <p>Method under test: {@link AISettings#getEnabledFunctionCategories()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set AISettings.getEnabledFunctionCategories()"})
  public void testGetEnabledFunctionCategories() {
    // Arrange, Act and Assert
    assertTrue(new AISettings().getEnabledFunctionCategories().isEmpty());
  }

  /**
   * Test {@link AISettings#setEnabledFunctionCategories(Set)}.
   *
   * <p>Method under test: {@link AISettings#setEnabledFunctionCategories(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AISettings.setEnabledFunctionCategories(Set)"})
  public void testSetEnabledFunctionCategories() {
    // Arrange
    AISettings aiSettings = new AISettings();

    HashSet<String> categories = new HashSet<>();
    categories.add("foo");

    // Act
    aiSettings.setEnabledFunctionCategories(categories);

    // Assert
    assertEquals(categories, aiSettings.getEnabledFunctionCategories());
  }

  /**
   * Test {@link AISettings#setEnabledFunctionCategories(Set)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link HashSet#HashSet()} add {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link AISettings#setEnabledFunctionCategories(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AISettings.setEnabledFunctionCategories(Set)"})
  public void testSetEnabledFunctionCategories_given42_whenHashSetAdd42() {
    // Arrange
    AISettings aiSettings = new AISettings();

    HashSet<String> categories = new HashSet<>();
    categories.add("42");
    categories.add("foo");

    // Act
    aiSettings.setEnabledFunctionCategories(categories);

    // Assert
    assertEquals(categories, aiSettings.getEnabledFunctionCategories());
  }

  /**
   * Test {@link AISettings#setEnabledFunctionCategories(Set)}.
   *
   * <ul>
   *   <li>When {@link HashSet#HashSet()}.
   * </ul>
   *
   * <p>Method under test: {@link AISettings#setEnabledFunctionCategories(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AISettings.setEnabledFunctionCategories(Set)"})
  public void testSetEnabledFunctionCategories_whenHashSet() {
    // Arrange
    AISettings aiSettings = new AISettings();

    // Act
    aiSettings.setEnabledFunctionCategories(new HashSet<>());

    // Assert that nothing has changed
    assertTrue(aiSettings.getEnabledFunctionCategories().isEmpty());
  }

  /**
   * Test {@link AISettings#setEnabledFunctionCategories(Set)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AISettings#setEnabledFunctionCategories(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AISettings.setEnabledFunctionCategories(Set)"})
  public void testSetEnabledFunctionCategories_whenNull() {
    // Arrange
    AISettings aiSettings = new AISettings();

    // Act
    aiSettings.setEnabledFunctionCategories(null);

    // Assert that nothing has changed
    assertTrue(aiSettings.getEnabledFunctionCategories().isEmpty());
  }

  /**
   * Test {@link AISettings#isFunctionCategoryEnabled(String)}.
   *
   * <p>Method under test: {@link AISettings#isFunctionCategoryEnabled(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AISettings.isFunctionCategoryEnabled(String)"})
  public void testIsFunctionCategoryEnabled() {
    // Arrange, Act and Assert
    assertFalse(new AISettings().isFunctionCategoryEnabled("Category"));
  }

  /**
   * Test {@link AISettings#enableFunctionCategory(String)}.
   *
   * <p>Method under test: {@link AISettings#enableFunctionCategory(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AISettings.enableFunctionCategory(String)"})
  public void testEnableFunctionCategory() {
    // Arrange
    AISettings aiSettings = new AISettings();

    // Act
    aiSettings.enableFunctionCategory("Category");

    // Assert
    Set<String> enabledFunctionCategories = aiSettings.getEnabledFunctionCategories();
    assertEquals(1, enabledFunctionCategories.size());
    assertTrue(enabledFunctionCategories.contains("Category"));
  }

  /**
   * Test {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}.
   *
   * <ul>
   *   <li>Given {@code -0.5}.
   *   <li>Then return Temperature is {@code -0.5}.
   * </ul>
   *
   * <p>Method under test: {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AIEngineProperties AISettings.updatePropertiesFromMap(AIEngineProperties, Map)"
  })
  public void testUpdatePropertiesFromMap_given05_thenReturnTemperatureIs05() {
    // Arrange
    CopilotProperties configuration = new CopilotProperties();
    configuration.setTemperature(-0.5d);

    // Act
    AIEngineProperties actualUpdatePropertiesFromMapResult =
        AISettings.updatePropertiesFromMap(configuration, new HashMap<>());

    // Assert
    assertTrue(actualUpdatePropertiesFromMapResult instanceof CopilotProperties);
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getContextWindowSize());
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getToken());
    assertEquals(
        -0.5d, ((CopilotProperties) actualUpdatePropertiesFromMapResult).getTemperature(), 0.0);
    assertFalse(actualUpdatePropertiesFromMapResult.isLoggingEnabled());
    assertFalse(actualUpdatePropertiesFromMapResult.isValidConfiguration());
  }

  /**
   * Test {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link HashMap#HashMap()} {@code foo} is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AIEngineProperties AISettings.updatePropertiesFromMap(AIEngineProperties, Map)"
  })
  public void testUpdatePropertiesFromMap_given42_whenHashMapFooIs42() {
    // Arrange
    CopilotProperties configuration = new CopilotProperties();

    HashMap<String, Object> properties = new HashMap<>();
    properties.put("foo", "42");

    // Act
    AIEngineProperties actualUpdatePropertiesFromMapResult =
        AISettings.updatePropertiesFromMap(configuration, properties);

    // Assert
    assertTrue(actualUpdatePropertiesFromMapResult instanceof CopilotProperties);
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getContextWindowSize());
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getToken());
    assertEquals(
        0.0d, ((CopilotProperties) actualUpdatePropertiesFromMapResult).getTemperature(), 0.0);
    assertFalse(actualUpdatePropertiesFromMapResult.isLoggingEnabled());
    assertFalse(actualUpdatePropertiesFromMapResult.isValidConfiguration());
  }

  /**
   * Test {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}.
   *
   * <ul>
   *   <li>Given {@code ABC123}.
   *   <li>Then return Token is {@code ABC123}.
   * </ul>
   *
   * <p>Method under test: {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AIEngineProperties AISettings.updatePropertiesFromMap(AIEngineProperties, Map)"
  })
  public void testUpdatePropertiesFromMap_givenAbc123_thenReturnTokenIsAbc123() {
    // Arrange
    CopilotProperties configuration = new CopilotProperties();
    configuration.setToken("ABC123");

    // Act
    AIEngineProperties actualUpdatePropertiesFromMapResult =
        AISettings.updatePropertiesFromMap(configuration, new HashMap<>());

    // Assert
    assertTrue(actualUpdatePropertiesFromMapResult instanceof CopilotProperties);
    assertEquals("ABC123", ((CopilotProperties) actualUpdatePropertiesFromMapResult).getToken());
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getContextWindowSize());
    assertEquals(
        0.0d, ((CopilotProperties) actualUpdatePropertiesFromMapResult).getTemperature(), 0.0);
    assertTrue(actualUpdatePropertiesFromMapResult.isValidConfiguration());
  }

  /**
   * Test {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}.
   *
   * <ul>
   *   <li>Given {@link CopilotProperties} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AIEngineProperties AISettings.updatePropertiesFromMap(AIEngineProperties, Map)"
  })
  public void testUpdatePropertiesFromMap_givenCopilotProperties() {
    // Arrange
    CopilotProperties configuration = new CopilotProperties();

    HashMap<String, Object> properties = new HashMap<>();
    properties.put("foo", new CopilotProperties());

    // Act
    AIEngineProperties actualUpdatePropertiesFromMapResult =
        AISettings.updatePropertiesFromMap(configuration, properties);

    // Assert
    assertTrue(actualUpdatePropertiesFromMapResult instanceof CopilotProperties);
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getContextWindowSize());
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getToken());
    assertEquals(
        0.0d, ((CopilotProperties) actualUpdatePropertiesFromMapResult).getTemperature(), 0.0);
    assertFalse(actualUpdatePropertiesFromMapResult.isLoggingEnabled());
    assertFalse(actualUpdatePropertiesFromMapResult.isValidConfiguration());
  }

  /**
   * Test {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>Then return Token is empty string.
   * </ul>
   *
   * <p>Method under test: {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AIEngineProperties AISettings.updatePropertiesFromMap(AIEngineProperties, Map)"
  })
  public void testUpdatePropertiesFromMap_givenEmptyString_thenReturnTokenIsEmptyString() {
    // Arrange
    CopilotProperties configuration = new CopilotProperties();
    configuration.setToken("");

    // Act
    AIEngineProperties actualUpdatePropertiesFromMapResult =
        AISettings.updatePropertiesFromMap(configuration, new HashMap<>());

    // Assert
    assertTrue(actualUpdatePropertiesFromMapResult instanceof CopilotProperties);
    assertEquals("", ((CopilotProperties) actualUpdatePropertiesFromMapResult).getToken());
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getContextWindowSize());
    assertEquals(
        0.0d, ((CopilotProperties) actualUpdatePropertiesFromMapResult).getTemperature(), 0.0);
    assertFalse(actualUpdatePropertiesFromMapResult.isLoggingEnabled());
    assertFalse(actualUpdatePropertiesFromMapResult.isValidConfiguration());
  }

  /**
   * Test {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()}.
   *   <li>When {@link HashMap#HashMap()} {@code foo} is {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AIEngineProperties AISettings.updatePropertiesFromMap(AIEngineProperties, Map)"
  })
  public void testUpdatePropertiesFromMap_givenHashMap_whenHashMapFooIsHashMap() {
    // Arrange
    CopilotProperties configuration = new CopilotProperties();

    HashMap<String, Object> properties = new HashMap<>();
    properties.put("foo", new HashMap<>());

    // Act
    AIEngineProperties actualUpdatePropertiesFromMapResult =
        AISettings.updatePropertiesFromMap(configuration, properties);

    // Assert
    assertTrue(actualUpdatePropertiesFromMapResult instanceof CopilotProperties);
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getContextWindowSize());
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getToken());
    assertEquals(
        0.0d, ((CopilotProperties) actualUpdatePropertiesFromMapResult).getTemperature(), 0.0);
    assertFalse(actualUpdatePropertiesFromMapResult.isLoggingEnabled());
    assertFalse(actualUpdatePropertiesFromMapResult.isValidConfiguration());
  }

  /**
   * Test {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link HashMap#HashMap()} {@code foo} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AIEngineProperties AISettings.updatePropertiesFromMap(AIEngineProperties, Map)"
  })
  public void testUpdatePropertiesFromMap_givenNull_whenHashMapFooIsNull() {
    // Arrange
    CopilotProperties configuration = new CopilotProperties();

    HashMap<String, Object> properties = new HashMap<>();
    properties.put("foo", null);

    // Act
    AIEngineProperties actualUpdatePropertiesFromMapResult =
        AISettings.updatePropertiesFromMap(configuration, properties);

    // Assert
    assertTrue(actualUpdatePropertiesFromMapResult instanceof CopilotProperties);
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getContextWindowSize());
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getToken());
    assertEquals(
        0.0d, ((CopilotProperties) actualUpdatePropertiesFromMapResult).getTemperature(), 0.0);
    assertFalse(actualUpdatePropertiesFromMapResult.isLoggingEnabled());
    assertFalse(actualUpdatePropertiesFromMapResult.isValidConfiguration());
  }

  /**
   * Test {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link HashMap#HashMap()} {@code foo} is one.
   * </ul>
   *
   * <p>Method under test: {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AIEngineProperties AISettings.updatePropertiesFromMap(AIEngineProperties, Map)"
  })
  public void testUpdatePropertiesFromMap_givenOne_whenHashMapFooIsOne() {
    // Arrange
    CopilotProperties configuration = new CopilotProperties();

    HashMap<String, Object> properties = new HashMap<>();
    properties.put("foo", 1);

    // Act
    AIEngineProperties actualUpdatePropertiesFromMapResult =
        AISettings.updatePropertiesFromMap(configuration, properties);

    // Assert
    assertTrue(actualUpdatePropertiesFromMapResult instanceof CopilotProperties);
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getContextWindowSize());
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getToken());
    assertEquals(
        0.0d, ((CopilotProperties) actualUpdatePropertiesFromMapResult).getTemperature(), 0.0);
    assertFalse(actualUpdatePropertiesFromMapResult.isLoggingEnabled());
    assertFalse(actualUpdatePropertiesFromMapResult.isValidConfiguration());
  }

  /**
   * Test {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}.
   *
   * <ul>
   *   <li>Given {@link OpenAIProperties} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AIEngineProperties AISettings.updatePropertiesFromMap(AIEngineProperties, Map)"
  })
  public void testUpdatePropertiesFromMap_givenOpenAIProperties() {
    // Arrange
    CopilotProperties configuration = new CopilotProperties();

    HashMap<String, Object> properties = new HashMap<>();
    properties.put("foo", new OpenAIProperties());

    // Act
    AIEngineProperties actualUpdatePropertiesFromMapResult =
        AISettings.updatePropertiesFromMap(configuration, properties);

    // Assert
    assertTrue(actualUpdatePropertiesFromMapResult instanceof CopilotProperties);
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getContextWindowSize());
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getToken());
    assertEquals(
        0.0d, ((CopilotProperties) actualUpdatePropertiesFromMapResult).getTemperature(), 0.0);
    assertFalse(actualUpdatePropertiesFromMapResult.isLoggingEnabled());
    assertFalse(actualUpdatePropertiesFromMapResult.isValidConfiguration());
  }

  /**
   * Test {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>Then return LoggingEnabled.
   * </ul>
   *
   * <p>Method under test: {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AIEngineProperties AISettings.updatePropertiesFromMap(AIEngineProperties, Map)"
  })
  public void testUpdatePropertiesFromMap_givenTrue_thenReturnLoggingEnabled() {
    // Arrange
    CopilotProperties configuration = new CopilotProperties();
    configuration.setLoggingEnabled(true);

    // Act
    AIEngineProperties actualUpdatePropertiesFromMapResult =
        AISettings.updatePropertiesFromMap(configuration, new HashMap<>());

    // Assert
    assertTrue(actualUpdatePropertiesFromMapResult instanceof CopilotProperties);
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getContextWindowSize());
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getToken());
    assertEquals(
        0.0d, ((CopilotProperties) actualUpdatePropertiesFromMapResult).getTemperature(), 0.0);
    assertTrue(actualUpdatePropertiesFromMapResult.isLoggingEnabled());
  }

  /**
   * Test {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}.
   *
   * <ul>
   *   <li>Given zero.
   *   <li>Then return ContextWindowSize intValue is zero.
   * </ul>
   *
   * <p>Method under test: {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AIEngineProperties AISettings.updatePropertiesFromMap(AIEngineProperties, Map)"
  })
  public void testUpdatePropertiesFromMap_givenZero_thenReturnContextWindowSizeIntValueIsZero() {
    // Arrange
    CopilotProperties configuration = new CopilotProperties();
    configuration.setContextWindowSize(0);

    // Act
    AIEngineProperties actualUpdatePropertiesFromMapResult =
        AISettings.updatePropertiesFromMap(configuration, new HashMap<>());

    // Assert
    assertTrue(actualUpdatePropertiesFromMapResult instanceof CopilotProperties);
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getToken());
    assertEquals(
        0,
        ((CopilotProperties) actualUpdatePropertiesFromMapResult)
            .getContextWindowSize()
            .intValue());
    assertEquals(
        0.0d, ((CopilotProperties) actualUpdatePropertiesFromMapResult).getTemperature(), 0.0);
    assertFalse(actualUpdatePropertiesFromMapResult.isLoggingEnabled());
    assertFalse(actualUpdatePropertiesFromMapResult.isValidConfiguration());
  }

  /**
   * Test {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}.
   *
   * <ul>
   *   <li>Then return ContextWindowSize intValue is minus one.
   * </ul>
   *
   * <p>Method under test: {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AIEngineProperties AISettings.updatePropertiesFromMap(AIEngineProperties, Map)"
  })
  public void testUpdatePropertiesFromMap_thenReturnContextWindowSizeIntValueIsMinusOne() {
    // Arrange
    CopilotProperties configuration = new CopilotProperties();
    configuration.setContextWindowSize(-1);

    // Act
    AIEngineProperties actualUpdatePropertiesFromMapResult =
        AISettings.updatePropertiesFromMap(configuration, new HashMap<>());

    // Assert
    assertTrue(actualUpdatePropertiesFromMapResult instanceof CopilotProperties);
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getToken());
    assertEquals(
        -1,
        ((CopilotProperties) actualUpdatePropertiesFromMapResult)
            .getContextWindowSize()
            .intValue());
    assertEquals(
        0.0d, ((CopilotProperties) actualUpdatePropertiesFromMapResult).getTemperature(), 0.0);
    assertFalse(actualUpdatePropertiesFromMapResult.isLoggingEnabled());
    assertFalse(actualUpdatePropertiesFromMapResult.isValidConfiguration());
  }

  /**
   * Test {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}.
   *
   * <ul>
   *   <li>Then return ContextWindowSize intValue is three.
   * </ul>
   *
   * <p>Method under test: {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AIEngineProperties AISettings.updatePropertiesFromMap(AIEngineProperties, Map)"
  })
  public void testUpdatePropertiesFromMap_thenReturnContextWindowSizeIntValueIsThree() {
    // Arrange
    CopilotProperties configuration = new CopilotProperties();
    configuration.setContextWindowSize(3);

    // Act
    AIEngineProperties actualUpdatePropertiesFromMapResult =
        AISettings.updatePropertiesFromMap(configuration, new HashMap<>());

    // Assert
    assertTrue(actualUpdatePropertiesFromMapResult instanceof CopilotProperties);
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getToken());
    assertEquals(
        0.0d, ((CopilotProperties) actualUpdatePropertiesFromMapResult).getTemperature(), 0.0);
    assertEquals(
        3,
        ((CopilotProperties) actualUpdatePropertiesFromMapResult)
            .getContextWindowSize()
            .intValue());
    assertFalse(actualUpdatePropertiesFromMapResult.isLoggingEnabled());
    assertFalse(actualUpdatePropertiesFromMapResult.isValidConfiguration());
  }

  /**
   * Test {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}.
   *
   * <ul>
   *   <li>When {@link CopilotProperties} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AIEngineProperties AISettings.updatePropertiesFromMap(AIEngineProperties, Map)"
  })
  public void testUpdatePropertiesFromMap_whenCopilotProperties() {
    // Arrange
    CopilotProperties configuration = new CopilotProperties();

    // Act
    AIEngineProperties actualUpdatePropertiesFromMapResult =
        AISettings.updatePropertiesFromMap(configuration, new HashMap<>());

    // Assert
    assertTrue(actualUpdatePropertiesFromMapResult instanceof CopilotProperties);
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getContextWindowSize());
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getToken());
    assertEquals(
        0.0d, ((CopilotProperties) actualUpdatePropertiesFromMapResult).getTemperature(), 0.0);
    assertFalse(actualUpdatePropertiesFromMapResult.isLoggingEnabled());
    assertFalse(actualUpdatePropertiesFromMapResult.isValidConfiguration());
  }

  /**
   * Test {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}.
   *
   * <ul>
   *   <li>When {@link CopilotProperties} (default constructor) Temperature is ten.
   * </ul>
   *
   * <p>Method under test: {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AIEngineProperties AISettings.updatePropertiesFromMap(AIEngineProperties, Map)"
  })
  public void testUpdatePropertiesFromMap_whenCopilotPropertiesTemperatureIsTen() {
    // Arrange
    CopilotProperties configuration = new CopilotProperties();
    configuration.setTemperature(10.0d);

    // Act
    AIEngineProperties actualUpdatePropertiesFromMapResult =
        AISettings.updatePropertiesFromMap(configuration, new HashMap<>());

    // Assert
    assertTrue(actualUpdatePropertiesFromMapResult instanceof CopilotProperties);
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getContextWindowSize());
    assertNull(((CopilotProperties) actualUpdatePropertiesFromMapResult).getToken());
    assertEquals(
        10.0d, ((CopilotProperties) actualUpdatePropertiesFromMapResult).getTemperature(), 0.0);
    assertFalse(actualUpdatePropertiesFromMapResult.isLoggingEnabled());
    assertFalse(actualUpdatePropertiesFromMapResult.isValidConfiguration());
  }

  /**
   * Test {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}.
   *
   * <ul>
   *   <li>When {@link OpenAIProperties} (default constructor) Temperature is ten.
   * </ul>
   *
   * <p>Method under test: {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AIEngineProperties AISettings.updatePropertiesFromMap(AIEngineProperties, Map)"
  })
  public void testUpdatePropertiesFromMap_whenOpenAIPropertiesTemperatureIsTen() {
    // Arrange
    OpenAIProperties configuration = new OpenAIProperties();
    configuration.setTemperature(10.0d);

    // Act
    AIEngineProperties actualUpdatePropertiesFromMapResult =
        AISettings.updatePropertiesFromMap(configuration, new HashMap<>());

    // Assert
    assertTrue(actualUpdatePropertiesFromMapResult instanceof OpenAIProperties);
    assertNull(((OpenAIProperties) actualUpdatePropertiesFromMapResult).getBaseUrl());
    assertNull(((OpenAIProperties) actualUpdatePropertiesFromMapResult).getToken());
    assertEquals(
        10.0d, ((OpenAIProperties) actualUpdatePropertiesFromMapResult).getTemperature(), 0.0);
    assertFalse(actualUpdatePropertiesFromMapResult.isValidConfiguration());
    assertTrue(((OpenAIProperties) actualUpdatePropertiesFromMapResult).isStreamingEnabled());
  }

  /**
   * Test {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}.
   *
   * <ul>
   *   <li>When {@link OpenAIProperties} (default constructor).
   *   <li>Then return {@link OpenAIProperties}.
   * </ul>
   *
   * <p>Method under test: {@link AISettings#updatePropertiesFromMap(AIEngineProperties, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AIEngineProperties AISettings.updatePropertiesFromMap(AIEngineProperties, Map)"
  })
  public void testUpdatePropertiesFromMap_whenOpenAIProperties_thenReturnOpenAIProperties() {
    // Arrange
    OpenAIProperties configuration = new OpenAIProperties();

    // Act
    AIEngineProperties actualUpdatePropertiesFromMapResult =
        AISettings.updatePropertiesFromMap(configuration, new HashMap<>());

    // Assert
    assertTrue(actualUpdatePropertiesFromMapResult instanceof OpenAIProperties);
    assertNull(((OpenAIProperties) actualUpdatePropertiesFromMapResult).getBaseUrl());
    assertNull(((OpenAIProperties) actualUpdatePropertiesFromMapResult).getToken());
    assertFalse(actualUpdatePropertiesFromMapResult.isValidConfiguration());
    assertTrue(((OpenAIProperties) actualUpdatePropertiesFromMapResult).isStreamingEnabled());
  }

  /**
   * Test {@link AISettings#hasConfiguration(String)}.
   *
   * <p>Method under test: {@link AISettings#hasConfiguration(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AISettings.hasConfiguration(String)"})
  public void testHasConfiguration() {
    // Arrange, Act and Assert
    assertFalse(new AISettings().hasConfiguration("42"));
  }

  /**
   * Test {@link AISettings#setEngineConfiguration(String, AIEngineProperties)}.
   *
   * <p>Method under test: {@link AISettings#setEngineConfiguration(String, AIEngineProperties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AISettings.setEngineConfiguration(String, AIEngineProperties)"})
  public void testSetEngineConfiguration() {
    // Arrange
    AISettings aiSettings = new AISettings();
    CopilotProperties engineConfiguration = new CopilotProperties();

    // Act
    aiSettings.setEngineConfiguration("42", engineConfiguration);

    // Assert
    Map<String, AIEngineProperties> engineConfigurations = aiSettings.getEngineConfigurations();
    assertEquals(1, engineConfigurations.size());
    assertSame(engineConfiguration, engineConfigurations.get("42"));
  }

  /**
   * Test {@link AISettings#getAdapter(Class)}.
   *
   * <p>Method under test: {@link AISettings#getAdapter(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object AISettings.getAdapter(Class)"})
  public void testGetAdapter() {
    // Arrange
    AISettings aiSettings = new AISettings();
    Class<Object> adapter = Object.class;

    // Act and Assert
    assertNull(aiSettings.getAdapter(adapter));
  }
}
