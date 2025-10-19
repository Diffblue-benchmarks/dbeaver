package org.jkiss.dbeaver.model.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.model.auth.SMAuthConfigurationReference;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SMAuthProviderCustomConfigurationDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMAuthProviderCustomConfiguration#SMAuthProviderCustomConfiguration(String)}
   *   <li>{@link SMAuthProviderCustomConfiguration#setDescription(String)}
   *   <li>{@link SMAuthProviderCustomConfiguration#setDisabled(boolean)}
   *   <li>{@link SMAuthProviderCustomConfiguration#setDisplayName(String)}
   *   <li>{@link SMAuthProviderCustomConfiguration#setIconURL(String)}
   *   <li>{@link SMAuthProviderCustomConfiguration#setId(String)}
   *   <li>{@link SMAuthProviderCustomConfiguration#setParameters(Map)}
   *   <li>{@link SMAuthProviderCustomConfiguration#setProvider(String)}
   *   <li>{@link SMAuthProviderCustomConfiguration#getDescription()}
   *   <li>{@link SMAuthProviderCustomConfiguration#getDisplayName()}
   *   <li>{@link SMAuthProviderCustomConfiguration#getIconURL()}
   *   <li>{@link SMAuthProviderCustomConfiguration#getId()}
   *   <li>{@link SMAuthProviderCustomConfiguration#getParameters()}
   *   <li>{@link SMAuthProviderCustomConfiguration#getProvider()}
   *   <li>{@link SMAuthProviderCustomConfiguration#isDisabled()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SMAuthProviderCustomConfiguration.<init>(String)",
    "String SMAuthProviderCustomConfiguration.getDescription()",
    "String SMAuthProviderCustomConfiguration.getDisplayName()",
    "String SMAuthProviderCustomConfiguration.getIconURL()",
    "String SMAuthProviderCustomConfiguration.getId()",
    "Map SMAuthProviderCustomConfiguration.getParameters()",
    "String SMAuthProviderCustomConfiguration.getProvider()",
    "boolean SMAuthProviderCustomConfiguration.isDisabled()",
    "void SMAuthProviderCustomConfiguration.setDescription(String)",
    "void SMAuthProviderCustomConfiguration.setDisabled(boolean)",
    "void SMAuthProviderCustomConfiguration.setDisplayName(String)",
    "void SMAuthProviderCustomConfiguration.setIconURL(String)",
    "void SMAuthProviderCustomConfiguration.setId(String)",
    "void SMAuthProviderCustomConfiguration.setParameters(Map)",
    "void SMAuthProviderCustomConfiguration.setProvider(String)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    SMAuthProviderCustomConfiguration actualSmAuthProviderCustomConfiguration =
        new SMAuthProviderCustomConfiguration("42");
    actualSmAuthProviderCustomConfiguration.setDescription(
        "The characteristics of someone or something");
    actualSmAuthProviderCustomConfiguration.setDisabled(true);
    actualSmAuthProviderCustomConfiguration.setDisplayName("Display Name");
    actualSmAuthProviderCustomConfiguration.setIconURL("https://example.org/example");
    actualSmAuthProviderCustomConfiguration.setId("42");
    HashMap<String, Object> parameters = new HashMap<>();
    actualSmAuthProviderCustomConfiguration.setParameters(parameters);
    actualSmAuthProviderCustomConfiguration.setProvider("Provider");
    String actualDescription = actualSmAuthProviderCustomConfiguration.getDescription();
    String actualDisplayName = actualSmAuthProviderCustomConfiguration.getDisplayName();
    String actualIconURL = actualSmAuthProviderCustomConfiguration.getIconURL();
    String actualId = actualSmAuthProviderCustomConfiguration.getId();
    Map<String, Object> actualParameters = actualSmAuthProviderCustomConfiguration.getParameters();
    String actualProvider = actualSmAuthProviderCustomConfiguration.getProvider();
    boolean actualIsDisabledResult = actualSmAuthProviderCustomConfiguration.isDisabled();

    // Assert
    assertEquals("42", actualId);
    assertEquals("Display Name", actualDisplayName);
    assertEquals("Provider", actualProvider);
    assertEquals("The characteristics of someone or something", actualDescription);
    assertEquals("https://example.org/example", actualIconURL);
    assertTrue(actualParameters.isEmpty());
    assertTrue(actualIsDisabledResult);
    assertSame(parameters, actualParameters);
  }

  /**
   * Test {@link
   * SMAuthProviderCustomConfiguration#SMAuthProviderCustomConfiguration(SMAuthProviderCustomConfiguration)}.
   *
   * <p>Method under test: {@link
   * SMAuthProviderCustomConfiguration#SMAuthProviderCustomConfiguration(SMAuthProviderCustomConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SMAuthProviderCustomConfiguration.<init>(SMAuthProviderCustomConfiguration)"
  })
  public void testNewSMAuthProviderCustomConfiguration() {
    // Arrange
    SMAuthProviderCustomConfiguration src = new SMAuthProviderCustomConfiguration("42");

    // Act
    SMAuthProviderCustomConfiguration actualSmAuthProviderCustomConfiguration =
        new SMAuthProviderCustomConfiguration(src);

    // Assert
    assertEquals(src, actualSmAuthProviderCustomConfiguration);
  }

  /**
   * Test {@link SMAuthProviderCustomConfiguration#getParameter(String)}.
   *
   * <p>Method under test: {@link SMAuthProviderCustomConfiguration#getParameter(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object SMAuthProviderCustomConfiguration.getParameter(String)"})
  public void testGetParameter() {
    // Arrange, Act and Assert
    assertNull(new SMAuthProviderCustomConfiguration("42").getParameter("Name"));
  }

  /**
   * Test {@link SMAuthProviderCustomConfiguration#getParameterOrDefault(String, Object)}.
   *
   * <p>Method under test: {@link SMAuthProviderCustomConfiguration#getParameterOrDefault(String,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SMAuthProviderCustomConfiguration.getParameterOrDefault(String, Object)"
  })
  public void testGetParameterOrDefault() {
    // Arrange, Act and Assert
    assertEquals(
        "Default Value",
        new SMAuthProviderCustomConfiguration("42").getParameterOrDefault("Name", "Default Value"));
  }

  /**
   * Test {@link SMAuthProviderCustomConfiguration#equals(Object)}, and {@link
   * SMAuthProviderCustomConfiguration#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMAuthProviderCustomConfiguration#equals(Object)}
   *   <li>{@link SMAuthProviderCustomConfiguration#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SMAuthProviderCustomConfiguration.equals(Object)",
    "int SMAuthProviderCustomConfiguration.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    SMAuthProviderCustomConfiguration smAuthProviderCustomConfiguration =
        new SMAuthProviderCustomConfiguration("42");
    SMAuthProviderCustomConfiguration smAuthProviderCustomConfiguration2 =
        new SMAuthProviderCustomConfiguration("42");

    // Act and Assert
    assertEquals(smAuthProviderCustomConfiguration, smAuthProviderCustomConfiguration2);
    assertEquals(
        smAuthProviderCustomConfiguration.hashCode(),
        smAuthProviderCustomConfiguration2.hashCode());
  }

  /**
   * Test {@link SMAuthProviderCustomConfiguration#equals(Object)}, and {@link
   * SMAuthProviderCustomConfiguration#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMAuthProviderCustomConfiguration#equals(Object)}
   *   <li>{@link SMAuthProviderCustomConfiguration#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SMAuthProviderCustomConfiguration.equals(Object)",
    "int SMAuthProviderCustomConfiguration.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    SMAuthProviderCustomConfiguration smAuthProviderCustomConfiguration =
        new SMAuthProviderCustomConfiguration("42");

    // Act and Assert
    assertEquals(smAuthProviderCustomConfiguration, smAuthProviderCustomConfiguration);
    int expectedHashCodeResult = smAuthProviderCustomConfiguration.hashCode();
    assertEquals(expectedHashCodeResult, smAuthProviderCustomConfiguration.hashCode());
  }

  /**
   * Test {@link SMAuthProviderCustomConfiguration#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SMAuthProviderCustomConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SMAuthProviderCustomConfiguration.equals(Object)",
    "int SMAuthProviderCustomConfiguration.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    SMAuthProviderCustomConfiguration smAuthProviderCustomConfiguration =
        new SMAuthProviderCustomConfiguration("Id");

    // Act and Assert
    assertNotEquals(smAuthProviderCustomConfiguration, new SMAuthProviderCustomConfiguration("42"));
  }

  /**
   * Test {@link SMAuthProviderCustomConfiguration#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SMAuthProviderCustomConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SMAuthProviderCustomConfiguration.equals(Object)",
    "int SMAuthProviderCustomConfiguration.hashCode()"
  })
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new SMAuthProviderCustomConfiguration("42"), null);
  }

  /**
   * Test {@link SMAuthProviderCustomConfiguration#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SMAuthProviderCustomConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SMAuthProviderCustomConfiguration.equals(Object)",
    "int SMAuthProviderCustomConfiguration.hashCode()"
  })
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new SMAuthProviderCustomConfiguration("42"),
        "Different type to SMAuthProviderCustomConfiguration");
  }

  /**
   * Test {@link SMAuthProviderCustomConfiguration#getAuthConfigurationReference()}.
   *
   * <p>Method under test: {@link SMAuthProviderCustomConfiguration#getAuthConfigurationReference()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SMAuthConfigurationReference SMAuthProviderCustomConfiguration.getAuthConfigurationReference()"
  })
  public void testGetAuthConfigurationReference() {
    // Arrange and Act
    SMAuthConfigurationReference actualAuthConfigurationReference =
        new SMAuthProviderCustomConfiguration("42").getAuthConfigurationReference();

    // Assert
    assertEquals("42", actualAuthConfigurationReference.getAuthProviderConfigurationId());
    assertNull(actualAuthConfigurationReference.getAuthProviderId());
  }
}
