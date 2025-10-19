package org.jkiss.dbeaver.model.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SMAuthProviderDescriptorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link SMAuthProviderDescriptor}
   *   <li>{@link SMAuthProviderDescriptor#setCredentialProfiles(List)}
   *   <li>{@link SMAuthProviderDescriptor#setCustomConfigurations(List)}
   *   <li>{@link SMAuthProviderDescriptor#setDescription(String)}
   *   <li>{@link SMAuthProviderDescriptor#setIcon(String)}
   *   <li>{@link SMAuthProviderDescriptor#setId(String)}
   *   <li>{@link SMAuthProviderDescriptor#setLabel(String)}
   *   <li>{@link SMAuthProviderDescriptor#toString()}
   *   <li>{@link SMAuthProviderDescriptor#getCredentialProfiles()}
   *   <li>{@link SMAuthProviderDescriptor#getCustomConfigurations()}
   *   <li>{@link SMAuthProviderDescriptor#getDescription()}
   *   <li>{@link SMAuthProviderDescriptor#getIcon()}
   *   <li>{@link SMAuthProviderDescriptor#getId()}
   *   <li>{@link SMAuthProviderDescriptor#getLabel()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SMAuthProviderDescriptor.<init>()",
    "List SMAuthProviderDescriptor.getCredentialProfiles()",
    "List SMAuthProviderDescriptor.getCustomConfigurations()",
    "String SMAuthProviderDescriptor.getDescription()",
    "String SMAuthProviderDescriptor.getIcon()",
    "String SMAuthProviderDescriptor.getId()",
    "String SMAuthProviderDescriptor.getLabel()",
    "void SMAuthProviderDescriptor.setCredentialProfiles(List)",
    "void SMAuthProviderDescriptor.setCustomConfigurations(List)",
    "void SMAuthProviderDescriptor.setDescription(String)",
    "void SMAuthProviderDescriptor.setIcon(String)",
    "void SMAuthProviderDescriptor.setId(String)",
    "void SMAuthProviderDescriptor.setLabel(String)",
    "String SMAuthProviderDescriptor.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    SMAuthProviderDescriptor actualSmAuthProviderDescriptor = new SMAuthProviderDescriptor();
    ArrayList<SMAuthCredentialsProfile> credentialProfiles = new ArrayList<>();
    actualSmAuthProviderDescriptor.setCredentialProfiles(credentialProfiles);
    ArrayList<SMAuthProviderCustomConfiguration> customConfigurations = new ArrayList<>();
    actualSmAuthProviderDescriptor.setCustomConfigurations(customConfigurations);
    actualSmAuthProviderDescriptor.setDescription("The characteristics of someone or something");
    actualSmAuthProviderDescriptor.setIcon("Icon");
    actualSmAuthProviderDescriptor.setId("42");
    actualSmAuthProviderDescriptor.setLabel("Label");
    String actualToStringResult = actualSmAuthProviderDescriptor.toString();
    List<SMAuthCredentialsProfile> actualCredentialProfiles =
        actualSmAuthProviderDescriptor.getCredentialProfiles();
    List<SMAuthProviderCustomConfiguration> actualCustomConfigurations =
        actualSmAuthProviderDescriptor.getCustomConfigurations();
    String actualDescription = actualSmAuthProviderDescriptor.getDescription();
    String actualIcon = actualSmAuthProviderDescriptor.getIcon();
    String actualId = actualSmAuthProviderDescriptor.getId();

    // Assert
    assertEquals("42 (Label)", actualToStringResult);
    assertEquals("42", actualId);
    assertEquals("Icon", actualIcon);
    assertEquals("Label", actualSmAuthProviderDescriptor.getLabel());
    assertEquals("The characteristics of someone or something", actualDescription);
    assertTrue(actualCredentialProfiles.isEmpty());
    assertTrue(actualCustomConfigurations.isEmpty());
    assertSame(credentialProfiles, actualCredentialProfiles);
    assertSame(customConfigurations, actualCustomConfigurations);
  }

  /**
   * Test {@link
   * SMAuthProviderDescriptor#addCustomConfiguration(SMAuthProviderCustomConfiguration)}.
   *
   * <p>Method under test: {@link
   * SMAuthProviderDescriptor#addCustomConfiguration(SMAuthProviderCustomConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SMAuthProviderDescriptor.addCustomConfiguration(SMAuthProviderCustomConfiguration)"
  })
  public void testAddCustomConfiguration() {
    // Arrange
    SMAuthProviderDescriptor smAuthProviderDescriptor = new SMAuthProviderDescriptor();
    ArrayList<SMAuthProviderCustomConfiguration> customConfigurations = new ArrayList<>();
    smAuthProviderDescriptor.setCustomConfigurations(customConfigurations);
    SMAuthProviderCustomConfiguration customConfiguration =
        new SMAuthProviderCustomConfiguration("42");

    // Act
    smAuthProviderDescriptor.addCustomConfiguration(customConfiguration);

    // Assert
    List<SMAuthProviderCustomConfiguration> customConfigurations2 =
        smAuthProviderDescriptor.getCustomConfigurations();
    assertEquals(1, customConfigurations2.size());
    assertSame(customConfigurations, customConfigurations2);
    assertSame(customConfiguration, customConfigurations2.get(0));
  }

  /**
   * Test {@link
   * SMAuthProviderDescriptor#addCustomConfiguration(SMAuthProviderCustomConfiguration)}.
   *
   * <ul>
   *   <li>Given {@link SMAuthProviderDescriptor} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link
   * SMAuthProviderDescriptor#addCustomConfiguration(SMAuthProviderCustomConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SMAuthProviderDescriptor.addCustomConfiguration(SMAuthProviderCustomConfiguration)"
  })
  public void testAddCustomConfiguration_givenSMAuthProviderDescriptor() {
    // Arrange
    SMAuthProviderDescriptor smAuthProviderDescriptor = new SMAuthProviderDescriptor();
    SMAuthProviderCustomConfiguration customConfiguration =
        new SMAuthProviderCustomConfiguration("42");

    // Act
    smAuthProviderDescriptor.addCustomConfiguration(customConfiguration);

    // Assert
    List<SMAuthProviderCustomConfiguration> customConfigurations =
        smAuthProviderDescriptor.getCustomConfigurations();
    assertEquals(1, customConfigurations.size());
    assertSame(customConfiguration, customConfigurations.get(0));
  }

  /**
   * Test {@link SMAuthProviderDescriptor#hasUserParameters()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SMAuthProviderDescriptor#hasUserParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SMAuthProviderDescriptor.hasUserParameters()"})
  public void testHasUserParameters_thenReturnFalse() {
    // Arrange
    SMAuthProviderDescriptor smAuthProviderDescriptor = new SMAuthProviderDescriptor();
    smAuthProviderDescriptor.setCredentialProfiles(new ArrayList<>());

    // Act and Assert
    assertFalse(smAuthProviderDescriptor.hasUserParameters());
  }
}
