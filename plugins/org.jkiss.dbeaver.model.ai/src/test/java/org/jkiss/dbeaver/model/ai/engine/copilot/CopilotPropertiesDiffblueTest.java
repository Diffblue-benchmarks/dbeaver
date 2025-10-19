package org.jkiss.dbeaver.model.ai.engine.copilot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CopilotPropertiesDiffblueTest {
  /**
   * Test {@link CopilotProperties#getContextWindowSize()}.
   *
   * <ul>
   *   <li>Given {@link CopilotProperties} (default constructor) Model is empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CopilotProperties#getContextWindowSize()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Integer CopilotProperties.getContextWindowSize()"})
  public void testGetContextWindowSize_givenCopilotPropertiesModelIsEmptyString_thenReturnNull() {
    // Arrange
    CopilotProperties copilotProperties = new CopilotProperties();
    copilotProperties.setContextWindowSize(null);
    copilotProperties.setModel("");

    // Act and Assert
    assertNull(copilotProperties.getContextWindowSize());
  }

  /**
   * Test {@link CopilotProperties#getContextWindowSize()}.
   *
   * <ul>
   *   <li>Given {@link CopilotProperties} (default constructor) Model is {@code foo}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CopilotProperties#getContextWindowSize()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Integer CopilotProperties.getContextWindowSize()"})
  public void testGetContextWindowSize_givenCopilotPropertiesModelIsFoo_thenReturnNull() {
    // Arrange
    CopilotProperties copilotProperties = new CopilotProperties();
    copilotProperties.setContextWindowSize(null);
    copilotProperties.setModel("foo");

    // Act and Assert
    assertNull(copilotProperties.getContextWindowSize());
  }

  /**
   * Test {@link CopilotProperties#getContextWindowSize()}.
   *
   * <ul>
   *   <li>Given {@link CopilotProperties} (default constructor).
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CopilotProperties#getContextWindowSize()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Integer CopilotProperties.getContextWindowSize()"})
  public void testGetContextWindowSize_givenCopilotProperties_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new CopilotProperties().getContextWindowSize());
  }

  /**
   * Test {@link CopilotProperties#getContextWindowSize()}.
   *
   * <ul>
   *   <li>Then return intValue is one.
   * </ul>
   *
   * <p>Method under test: {@link CopilotProperties#getContextWindowSize()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Integer CopilotProperties.getContextWindowSize()"})
  public void testGetContextWindowSize_thenReturnIntValueIsOne() {
    // Arrange
    CopilotProperties copilotProperties = new CopilotProperties();
    copilotProperties.setContextWindowSize(1);
    copilotProperties.setModel(null);

    // Act and Assert
    assertEquals(1, copilotProperties.getContextWindowSize().intValue());
  }

  /**
   * Test {@link CopilotProperties#isValidConfiguration()}.
   *
   * <ul>
   *   <li>Given {@link CopilotProperties} (default constructor) Token is empty string.
   * </ul>
   *
   * <p>Method under test: {@link CopilotProperties#isValidConfiguration()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CopilotProperties.isValidConfiguration()"})
  public void testIsValidConfiguration_givenCopilotPropertiesTokenIsEmptyString() {
    // Arrange
    CopilotProperties copilotProperties = new CopilotProperties();
    copilotProperties.setToken("");

    // Act and Assert
    assertFalse(copilotProperties.isValidConfiguration());
  }

  /**
   * Test {@link CopilotProperties#isValidConfiguration()}.
   *
   * <ul>
   *   <li>Given {@link CopilotProperties} (default constructor) Token is {@code foo}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link CopilotProperties#isValidConfiguration()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CopilotProperties.isValidConfiguration()"})
  public void testIsValidConfiguration_givenCopilotPropertiesTokenIsFoo_thenReturnTrue() {
    // Arrange
    CopilotProperties copilotProperties = new CopilotProperties();
    copilotProperties.setToken("foo");

    // Act and Assert
    assertTrue(copilotProperties.isValidConfiguration());
  }

  /**
   * Test {@link CopilotProperties#isValidConfiguration()}.
   *
   * <ul>
   *   <li>Given {@link CopilotProperties} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link CopilotProperties#isValidConfiguration()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CopilotProperties.isValidConfiguration()"})
  public void testIsValidConfiguration_givenCopilotProperties_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new CopilotProperties().isValidConfiguration());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link CopilotProperties}
   *   <li>{@link CopilotProperties#setContextWindowSize(Integer)}
   *   <li>{@link CopilotProperties#setLoggingEnabled(boolean)}
   *   <li>{@link CopilotProperties#setModel(String)}
   *   <li>{@link CopilotProperties#setTemperature(double)}
   *   <li>{@link CopilotProperties#setToken(String)}
   *   <li>{@link CopilotProperties#getModel()}
   *   <li>{@link CopilotProperties#getTemperature()}
   *   <li>{@link CopilotProperties#getToken()}
   *   <li>{@link CopilotProperties#isLoggingEnabled()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CopilotProperties.<init>()",
    "String CopilotProperties.getModel()",
    "double CopilotProperties.getTemperature()",
    "String CopilotProperties.getToken()",
    "boolean CopilotProperties.isLoggingEnabled()",
    "void CopilotProperties.setContextWindowSize(Integer)",
    "void CopilotProperties.setLoggingEnabled(boolean)",
    "void CopilotProperties.setModel(String)",
    "void CopilotProperties.setTemperature(double)",
    "void CopilotProperties.setToken(String)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    CopilotProperties actualCopilotProperties = new CopilotProperties();
    actualCopilotProperties.setContextWindowSize(3);
    actualCopilotProperties.setLoggingEnabled(true);
    actualCopilotProperties.setModel("Model");
    actualCopilotProperties.setTemperature(10.0d);
    actualCopilotProperties.setToken("ABC123");
    String actualModel = actualCopilotProperties.getModel();
    double actualTemperature = actualCopilotProperties.getTemperature();
    String actualToken = actualCopilotProperties.getToken();

    // Assert
    assertEquals("ABC123", actualToken);
    assertEquals("Model", actualModel);
    assertEquals(10.0d, actualTemperature, 0.0);
    assertTrue(actualCopilotProperties.isLoggingEnabled());
  }
}
