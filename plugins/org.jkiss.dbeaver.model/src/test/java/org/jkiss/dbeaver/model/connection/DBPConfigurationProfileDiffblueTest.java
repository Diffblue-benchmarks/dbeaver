package org.jkiss.dbeaver.model.connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.model.access.DBAAuthProfile;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.net.DBWNetworkProfile;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBPConfigurationProfileDiffblueTest {
  /**
   * Test {@link DBPConfigurationProfile#getProject()}.
   *
   * <p>Method under test: {@link DBPConfigurationProfile#getProject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPProject DBPConfigurationProfile.getProject()"})
  public void testGetProject() {
    // Arrange, Act and Assert
    assertNull(new DBWNetworkProfile().getProject());
  }

  /**
   * Test {@link DBPConfigurationProfile#isExternallyProvided()}.
   *
   * <ul>
   *   <li>Given {@link DBWNetworkProfile#DBWNetworkProfile()}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConfigurationProfile#isExternallyProvided()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConfigurationProfile.isExternallyProvided()"})
  public void testIsExternallyProvided_givenDBWNetworkProfile_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new DBWNetworkProfile().isExternallyProvided());
  }

  /**
   * Test {@link DBPConfigurationProfile#isExternallyProvided()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConfigurationProfile#isExternallyProvided()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConfigurationProfile.isExternallyProvided()"})
  public void testIsExternallyProvided_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DBWNetworkProfile(mock(DBPProject.class)).isExternallyProvided());
  }

  /**
   * Test {@link DBPConfigurationProfile#getProfileId()}.
   *
   * <p>Method under test: {@link DBPConfigurationProfile#getProfileId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBPConfigurationProfile.getProfileId()"})
  public void testGetProfileId() {
    // Arrange
    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile(mock(DBPProject.class));
    dbwNetworkProfile.setProfileId("");

    // Act and Assert
    assertNull(dbwNetworkProfile.getProfileId());
  }

  /**
   * Test {@link DBPConfigurationProfile#getProfileId()}.
   *
   * <ul>
   *   <li>Given {@link DBWNetworkProfile#DBWNetworkProfile()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConfigurationProfile#getProfileId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBPConfigurationProfile.getProfileId()"})
  public void testGetProfileId_givenDBWNetworkProfile_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DBWNetworkProfile().getProfileId());
  }

  /**
   * Test {@link DBPConfigurationProfile#getProfileId()}.
   *
   * <ul>
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConfigurationProfile#getProfileId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBPConfigurationProfile.getProfileId()"})
  public void testGetProfileId_thenReturnFoo() {
    // Arrange
    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile(mock(DBPProject.class));
    dbwNetworkProfile.setProfileId("foo");

    // Act and Assert
    assertEquals("foo", dbwNetworkProfile.getProfileId());
  }

  /**
   * Test {@link DBPConfigurationProfile#getProfileSource()}.
   *
   * <p>Method under test: {@link DBPConfigurationProfile#getProfileSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBPConfigurationProfile.getProfileSource()"})
  public void testGetProfileSource() {
    // Arrange, Act and Assert
    assertNull(new DBAAuthProfile(mock(DBPProject.class)).getProfileSource());
  }

  /**
   * Test {@link DBPConfigurationProfile#setProfileId(String)}.
   *
   * <p>Method under test: {@link DBPConfigurationProfile#setProfileId(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConfigurationProfile.setProfileId(String)"})
  public void testSetProfileId() {
    // Arrange
    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile();

    // Act
    dbwNetworkProfile.setProfileId("42");

    // Assert
    assertEquals("42", dbwNetworkProfile.getProfileId());
    assertEquals("global/network-profile/42", dbwNetworkProfile.getSecretKeyId());
  }

  /**
   * Test {@link DBPConfigurationProfile#getProfileName()}.
   *
   * <p>Method under test: {@link DBPConfigurationProfile#getProfileName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBPConfigurationProfile.getProfileName()"})
  public void testGetProfileName() {
    // Arrange, Act and Assert
    assertNull(new DBWNetworkProfile().getProfileName());
  }

  /**
   * Test {@link DBPConfigurationProfile#setProfileName(String)}.
   *
   * <p>Method under test: {@link DBPConfigurationProfile#setProfileName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConfigurationProfile.setProfileName(String)"})
  public void testSetProfileName() {
    // Arrange
    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile();

    // Act
    dbwNetworkProfile.setProfileName("foo.txt");

    // Assert
    assertEquals("foo.txt", dbwNetworkProfile.getProfileId());
    assertEquals("foo.txt", dbwNetworkProfile.getProfileName());
    assertEquals("foo.txt", dbwNetworkProfile.toString());
    assertEquals("global/network-profile/foo.txt", dbwNetworkProfile.getSecretKeyId());
  }

  /**
   * Test {@link DBPConfigurationProfile#getProfileDescription()}.
   *
   * <p>Method under test: {@link DBPConfigurationProfile#getProfileDescription()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBPConfigurationProfile.getProfileDescription()"})
  public void testGetProfileDescription() {
    // Arrange, Act and Assert
    assertNull(new DBWNetworkProfile().getProfileDescription());
  }

  /**
   * Test {@link DBPConfigurationProfile#setProfileDescription(String)}.
   *
   * <p>Method under test: {@link DBPConfigurationProfile#setProfileDescription(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConfigurationProfile.setProfileDescription(String)"})
  public void testSetProfileDescription() {
    // Arrange
    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile();

    // Act
    dbwNetworkProfile.setProfileDescription("Profile Description");

    // Assert
    assertEquals("Profile Description", dbwNetworkProfile.getProfileDescription());
  }

  /**
   * Test {@link DBPConfigurationProfile#getProperties()}.
   *
   * <p>Method under test: {@link DBPConfigurationProfile#getProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBPConfigurationProfile.getProperties()"})
  public void testGetProperties() {
    // Arrange, Act and Assert
    assertTrue(new DBWNetworkProfile().getProperties().isEmpty());
  }

  /**
   * Test {@link DBPConfigurationProfile#setProperties(Map)}.
   *
   * <p>Method under test: {@link DBPConfigurationProfile#setProperties(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConfigurationProfile.setProperties(Map)"})
  public void testSetProperties() {
    // Arrange
    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile();
    HashMap<String, String> properties = new HashMap<>();

    // Act
    dbwNetworkProfile.setProperties(properties);

    // Assert
    assertSame(properties, dbwNetworkProfile.getProperties());
  }

  /**
   * Test {@link DBPConfigurationProfile#toString()}.
   *
   * <p>Method under test: {@link DBPConfigurationProfile#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBPConfigurationProfile.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertNull(new DBWNetworkProfile().toString());
  }
}
