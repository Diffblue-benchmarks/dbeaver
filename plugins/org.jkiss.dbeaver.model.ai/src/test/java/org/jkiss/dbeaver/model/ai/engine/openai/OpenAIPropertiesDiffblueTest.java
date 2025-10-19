package org.jkiss.dbeaver.model.ai.engine.openai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.ai.engine.openai.OpenAIProperties.OpenAIModelListProvider;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OpenAIPropertiesDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link OpenAIProperties}
   *   <li>{@link OpenAIProperties#setBaseUrl(String)}
   *   <li>{@link OpenAIProperties#setContextWindowSize(Integer)}
   *   <li>{@link OpenAIProperties#setModel(String)}
   *   <li>{@link OpenAIProperties#setToken(String)}
   *   <li>{@link OpenAIProperties#getBaseUrl()}
   *   <li>{@link OpenAIProperties#getToken()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OpenAIProperties.<init>()",
    "String OpenAIProperties.getBaseUrl()",
    "String OpenAIProperties.getToken()",
    "void OpenAIProperties.setBaseUrl(String)",
    "void OpenAIProperties.setContextWindowSize(Integer)",
    "void OpenAIProperties.setModel(String)",
    "void OpenAIProperties.setToken(String)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    OpenAIProperties actualOpenAIProperties = new OpenAIProperties();
    actualOpenAIProperties.setBaseUrl("https://example.org/example");
    actualOpenAIProperties.setContextWindowSize(3);
    actualOpenAIProperties.setModel("Model");
    actualOpenAIProperties.setToken("ABC123");
    String actualBaseUrl = actualOpenAIProperties.getBaseUrl();

    // Assert
    assertEquals("ABC123", actualOpenAIProperties.getToken());
    assertEquals("https://example.org/example", actualBaseUrl);
  }

  /**
   * Test {@link OpenAIProperties#getModel()}.
   *
   * <ul>
   *   <li>Given {@link OpenAIProperties} (default constructor) Model is {@link
   *       OpenAIConstants#DEFAULT_MODEL}.
   *   <li>Then return {@link OpenAIConstants#DEFAULT_MODEL}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIProperties#getModel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OpenAIProperties.getModel()"})
  public void testGetModel_givenOpenAIPropertiesModelIsDefault_model_thenReturnDefault_model() {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setModel(OpenAIConstants.DEFAULT_MODEL);

    // Act and Assert
    assertEquals(OpenAIConstants.DEFAULT_MODEL, openAIProperties.getModel());
  }

  /**
   * Test {@link OpenAIProperties#getModel()}.
   *
   * <ul>
   *   <li>Given {@link OpenAIProperties} (default constructor) Model is empty string.
   *   <li>Then return {@link OpenAIConstants#DEFAULT_MODEL}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIProperties#getModel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OpenAIProperties.getModel()"})
  public void testGetModel_givenOpenAIPropertiesModelIsEmptyString_thenReturnDefault_model() {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setModel("");

    // Act and Assert
    assertEquals(OpenAIConstants.DEFAULT_MODEL, openAIProperties.getModel());
  }

  /**
   * Test {@link OpenAIProperties#getModel()}.
   *
   * <ul>
   *   <li>Given {@link OpenAIProperties} (default constructor) Model is {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIProperties#getModel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OpenAIProperties.getModel()"})
  public void testGetModel_givenOpenAIPropertiesModelIsFoo_thenReturnFoo() {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setModel("foo");

    // Act and Assert
    assertEquals("foo", openAIProperties.getModel());
  }

  /**
   * Test {@link OpenAIProperties#getTemperature()}.
   *
   * <ul>
   *   <li>Given {@link OpenAIProperties} (default constructor) Temperature is ten.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIProperties#getTemperature()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double OpenAIProperties.getTemperature()"})
  public void testGetTemperature_givenOpenAIPropertiesTemperatureIsTen_thenReturnTen() {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setTemperature(10.0d);

    // Act and Assert
    assertEquals(10.0d, openAIProperties.getTemperature(), 0.0);
  }

  /**
   * Test OpenAIModelListProvider {@link OpenAIModelListProvider#allowCustomValue()}.
   *
   * <p>Method under test: {@link OpenAIModelListProvider#allowCustomValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OpenAIModelListProvider.allowCustomValue()"})
  public void testOpenAIModelListProviderAllowCustomValue() {
    // Arrange, Act and Assert
    assertFalse(new OpenAIModelListProvider().allowCustomValue());
  }

  /**
   * Test OpenAIModelListProvider {@link
   * OpenAIModelListProvider#getPossibleValues(OpenAIProperties)} with {@code OpenAIProperties}.
   *
   * <p>Method under test: {@link OpenAIModelListProvider#getPossibleValues(OpenAIProperties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object[] OpenAIModelListProvider.getPossibleValues(OpenAIProperties)"})
  public void testOpenAIModelListProviderGetPossibleValuesWithOpenAIProperties() {
    // Arrange
    OpenAIModelListProvider openAIModelListProvider = new OpenAIModelListProvider();

    // Act
    Object[] actualPossibleValues =
        openAIModelListProvider.getPossibleValues(new OpenAIProperties());

    // Assert
    assertEquals("gpt-3.5-turbo", actualPossibleValues[13]);
    assertEquals("gpt-4", actualPossibleValues[11]);
    assertEquals("gpt-4-turbo", actualPossibleValues[3]);
    assertEquals("gpt-4.1", actualPossibleValues[7]);
    assertEquals("gpt-4o-mini", actualPossibleValues[9]);
    assertEquals("gpt-5", actualPossibleValues[12]);
    assertEquals("gpt-5-mini", actualPossibleValues[6]);
    assertEquals("gpt-5-nano", actualPossibleValues[10]);
    assertEquals("o1", actualPossibleValues[0]);
    assertEquals("o1-mini", actualPossibleValues[14]);
    assertEquals("o1-pro", actualPossibleValues[8]);
    assertEquals("o3", actualPossibleValues[2]);
    assertEquals("o3-mini", actualPossibleValues[4]);
    assertEquals("o3-pro", actualPossibleValues[5]);
    assertEquals("o4-mini", actualPossibleValues[1]);
    assertEquals(Short.SIZE, actualPossibleValues.length);
    assertEquals(OpenAIConstants.DEFAULT_MODEL, actualPossibleValues[15]);
  }

  /**
   * Test {@link OpenAIProperties#setTemperature(double)}.
   *
   * <p>Method under test: {@link OpenAIProperties#setTemperature(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OpenAIProperties.setTemperature(double)"})
  public void testSetTemperature() {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();

    // Act
    openAIProperties.setTemperature(10.0d);

    // Assert
    assertEquals(10.0d, openAIProperties.getTemperature(), 0.0);
  }

  /**
   * Test {@link OpenAIProperties#isLoggingEnabled()}.
   *
   * <ul>
   *   <li>Given {@link OpenAIProperties} (default constructor) LoggingEnabled is {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIProperties#isLoggingEnabled()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OpenAIProperties.isLoggingEnabled()"})
  public void testIsLoggingEnabled_givenOpenAIPropertiesLoggingEnabledIsFalse_thenReturnFalse() {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setLoggingEnabled(false);

    // Act and Assert
    assertFalse(openAIProperties.isLoggingEnabled());
  }

  /**
   * Test {@link OpenAIProperties#isLoggingEnabled()}.
   *
   * <ul>
   *   <li>Given {@link OpenAIProperties} (default constructor) LoggingEnabled is {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIProperties#isLoggingEnabled()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OpenAIProperties.isLoggingEnabled()"})
  public void testIsLoggingEnabled_givenOpenAIPropertiesLoggingEnabledIsTrue_thenReturnTrue() {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setLoggingEnabled(true);

    // Act and Assert
    assertTrue(openAIProperties.isLoggingEnabled());
  }

  /**
   * Test {@link OpenAIProperties#getContextWindowSize()}.
   *
   * <ul>
   *   <li>Given {@link OpenAIProperties} (default constructor) Model is {@link
   *       OpenAIConstants#DEFAULT_MODEL}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIProperties#getContextWindowSize()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Integer OpenAIProperties.getContextWindowSize()"})
  public void testGetContextWindowSize_givenOpenAIPropertiesModelIsDefault_model() {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setContextWindowSize(null);
    openAIProperties.setModel(OpenAIConstants.DEFAULT_MODEL);

    // Act and Assert
    assertEquals(128000, openAIProperties.getContextWindowSize().intValue());
  }

  /**
   * Test {@link OpenAIProperties#getContextWindowSize()}.
   *
   * <ul>
   *   <li>Given {@link OpenAIProperties} (default constructor) Model is empty string.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIProperties#getContextWindowSize()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Integer OpenAIProperties.getContextWindowSize()"})
  public void testGetContextWindowSize_givenOpenAIPropertiesModelIsEmptyString() {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setContextWindowSize(null);
    openAIProperties.setModel("");

    // Act and Assert
    assertEquals(128000, openAIProperties.getContextWindowSize().intValue());
  }

  /**
   * Test {@link OpenAIProperties#getContextWindowSize()}.
   *
   * <ul>
   *   <li>Given {@link OpenAIProperties} (default constructor) Model is {@code foo}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIProperties#getContextWindowSize()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Integer OpenAIProperties.getContextWindowSize()"})
  public void testGetContextWindowSize_givenOpenAIPropertiesModelIsFoo_thenReturnNull() {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setContextWindowSize(null);
    openAIProperties.setModel("foo");

    // Act and Assert
    assertNull(openAIProperties.getContextWindowSize());
  }

  /**
   * Test {@link OpenAIProperties#getContextWindowSize()}.
   *
   * <ul>
   *   <li>Then return intValue is one.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIProperties#getContextWindowSize()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Integer OpenAIProperties.getContextWindowSize()"})
  public void testGetContextWindowSize_thenReturnIntValueIsOne() {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setContextWindowSize(1);
    openAIProperties.setModel(null);

    // Act and Assert
    assertEquals(1, openAIProperties.getContextWindowSize().intValue());
  }
}
