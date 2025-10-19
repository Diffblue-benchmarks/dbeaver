package org.jkiss.dbeaver.model.access;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import java.util.TreeMap;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.impl.app.LocalSecretController;
import org.jkiss.dbeaver.model.secret.DBSSecretController;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBAAuthProfileDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBAAuthProfile#DBAAuthProfile(DBPProject)}
   *   <li>{@link DBAAuthProfile#setAuthModelId(String)}
   *   <li>{@link DBAAuthProfile#setSavePassword(boolean)}
   *   <li>{@link DBAAuthProfile#setUserName(String)}
   *   <li>{@link DBAAuthProfile#setUserPassword(String)}
   *   <li>{@link DBAAuthProfile#getAuthModelId()}
   *   <li>{@link DBAAuthProfile#getUserName()}
   *   <li>{@link DBAAuthProfile#getUserPassword()}
   *   <li>{@link DBAAuthProfile#isSavePassword()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBAAuthProfile.<init>(DBPProject)",
    "String DBAAuthProfile.getAuthModelId()",
    "String DBAAuthProfile.getUserName()",
    "String DBAAuthProfile.getUserPassword()",
    "boolean DBAAuthProfile.isSavePassword()",
    "void DBAAuthProfile.setAuthModelId(String)",
    "void DBAAuthProfile.setSavePassword(boolean)",
    "void DBAAuthProfile.setUserName(String)",
    "void DBAAuthProfile.setUserPassword(String)"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBPProject project = mock(DBPProject.class);

    // Act
    DBAAuthProfile actualDbaAuthProfile = new DBAAuthProfile(project);
    actualDbaAuthProfile.setAuthModelId("42");
    actualDbaAuthProfile.setSavePassword(true);
    actualDbaAuthProfile.setUserName("janedoe");
    actualDbaAuthProfile.setUserPassword("iloveyou");
    String actualAuthModelId = actualDbaAuthProfile.getAuthModelId();
    String actualUserName = actualDbaAuthProfile.getUserName();
    String actualUserPassword = actualDbaAuthProfile.getUserPassword();
    boolean actualIsSavePasswordResult = actualDbaAuthProfile.isSavePassword();

    // Assert
    assertEquals("42", actualAuthModelId);
    assertEquals("iloveyou", actualUserPassword);
    assertEquals("janedoe", actualUserName);
    assertNull(actualDbaAuthProfile.getProfileDescription());
    assertNull(actualDbaAuthProfile.getProfileName());
    assertNull(actualDbaAuthProfile.toString());
    assertTrue(actualDbaAuthProfile.getProperties().isEmpty());
    assertTrue(actualIsSavePasswordResult);
    assertSame(project, actualDbaAuthProfile.getProject());
  }

  /**
   * Test {@link DBAAuthProfile#DBAAuthProfile(DBAAuthProfile)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBAAuthProfile#DBAAuthProfile(DBAAuthProfile)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBAAuthProfile.<init>(DBAAuthProfile)"})
  public void testNewDBAAuthProfile_givenNull() {
    // Arrange
    DBAAuthProfile source = new DBAAuthProfile(mock(DBPProject.class));
    source.setProperties(null);

    // Act
    DBAAuthProfile actualDbaAuthProfile = new DBAAuthProfile(source);

    // Assert
    assertNull(actualDbaAuthProfile.getAuthModelId());
    assertNull(actualDbaAuthProfile.getUserName());
    assertNull(actualDbaAuthProfile.getUserPassword());
    assertNull(actualDbaAuthProfile.getProfileDescription());
    assertNull(actualDbaAuthProfile.getProfileId());
    assertNull(actualDbaAuthProfile.getProfileName());
    assertNull(actualDbaAuthProfile.getProfileSource());
    assertNull(actualDbaAuthProfile.toString());
    assertFalse(actualDbaAuthProfile.isSavePassword());
    assertFalse(actualDbaAuthProfile.isExternallyProvided());
    assertTrue(actualDbaAuthProfile.getProperties().isEmpty());
  }

  /**
   * Test {@link DBAAuthProfile#DBAAuthProfile(DBAAuthProfile)}.
   *
   * <ul>
   *   <li>Given {@link TreeMap#TreeMap()} {@code foo} is {@code foo}.
   *   <li>Then return Properties size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBAAuthProfile#DBAAuthProfile(DBAAuthProfile)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBAAuthProfile.<init>(DBAAuthProfile)"})
  public void testNewDBAAuthProfile_givenTreeMapFooIsFoo_thenReturnPropertiesSizeIsOne() {
    // Arrange
    TreeMap<String, String> properties = new TreeMap<>();
    properties.put("foo", "foo");

    DBAAuthProfile source = new DBAAuthProfile(mock(DBPProject.class));
    source.setProperties(properties);

    // Act
    DBAAuthProfile actualDbaAuthProfile = new DBAAuthProfile(source);

    // Assert
    Map<String, String> properties2 = actualDbaAuthProfile.getProperties();
    assertEquals(1, properties2.size());
    assertEquals("foo", properties2.get("foo"));
    assertNull(actualDbaAuthProfile.getAuthModelId());
    assertNull(actualDbaAuthProfile.getUserName());
    assertNull(actualDbaAuthProfile.getUserPassword());
    assertNull(actualDbaAuthProfile.getProfileDescription());
    assertNull(actualDbaAuthProfile.getProfileId());
    assertNull(actualDbaAuthProfile.getProfileName());
    assertNull(actualDbaAuthProfile.getProfileSource());
    assertNull(actualDbaAuthProfile.toString());
    assertFalse(actualDbaAuthProfile.isSavePassword());
    assertFalse(actualDbaAuthProfile.isExternallyProvided());
  }

  /**
   * Test {@link DBAAuthProfile#DBAAuthProfile(DBAAuthProfile)}.
   *
   * <ul>
   *   <li>When {@link DBAAuthProfile#DBAAuthProfile(DBPProject)} with project is {@link
   *       DBPProject}.
   * </ul>
   *
   * <p>Method under test: {@link DBAAuthProfile#DBAAuthProfile(DBAAuthProfile)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBAAuthProfile.<init>(DBAAuthProfile)"})
  public void testNewDBAAuthProfile_whenDBAAuthProfileWithProjectIsDBPProject() {
    // Arrange
    DBAAuthProfile source = new DBAAuthProfile(mock(DBPProject.class));

    // Act
    DBAAuthProfile actualDbaAuthProfile = new DBAAuthProfile(source);

    // Assert
    assertNull(actualDbaAuthProfile.getAuthModelId());
    assertNull(actualDbaAuthProfile.getUserName());
    assertNull(actualDbaAuthProfile.getUserPassword());
    assertNull(actualDbaAuthProfile.getProfileDescription());
    assertNull(actualDbaAuthProfile.getProfileId());
    assertNull(actualDbaAuthProfile.getProfileName());
    assertNull(actualDbaAuthProfile.getProfileSource());
    assertNull(actualDbaAuthProfile.toString());
    assertFalse(actualDbaAuthProfile.isSavePassword());
    assertFalse(actualDbaAuthProfile.isExternallyProvided());
    assertTrue(actualDbaAuthProfile.getProperties().isEmpty());
  }

  /**
   * Test {@link DBAAuthProfile#getSecretKeyId()}.
   *
   * <ul>
   *   <li>Given {@link DBPProject} {@link DBPProject#getId()} return {@code 42}.
   *   <li>Then return {@code 42/auth-profile/null}.
   * </ul>
   *
   * <p>Method under test: {@link DBAAuthProfile#getSecretKeyId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBAAuthProfile.getSecretKeyId()"})
  public void testGetSecretKeyId_givenDBPProjectGetIdReturn42_thenReturn42AuthProfileNull() {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");

    // Act
    String actualSecretKeyId = new DBAAuthProfile(project).getSecretKeyId();

    // Assert
    verify(project).getId();
    assertEquals("42/auth-profile/null", actualSecretKeyId);
  }

  /**
   * Test {@link DBAAuthProfile#getSecretKeyId()}.
   *
   * <ul>
   *   <li>Given {@link DBPProject} {@link DBPProject#getId()} return {@code foo}.
   *   <li>Then return {@code foo/auth-profile/null}.
   * </ul>
   *
   * <p>Method under test: {@link DBAAuthProfile#getSecretKeyId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBAAuthProfile.getSecretKeyId()"})
  public void testGetSecretKeyId_givenDBPProjectGetIdReturnFoo_thenReturnFooAuthProfileNull() {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("foo");
    DBAAuthProfile source = new DBAAuthProfile(project);

    // Act
    String actualSecretKeyId = new DBAAuthProfile(source).getSecretKeyId();

    // Assert
    verify(project).getId();
    assertEquals("foo/auth-profile/null", actualSecretKeyId);
  }

  /**
   * Test {@link DBAAuthProfile#getSecretKeyId()}.
   *
   * <ul>
   *   <li>Then return {@code 42/auth-profile/42}.
   * </ul>
   *
   * <p>Method under test: {@link DBAAuthProfile#getSecretKeyId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBAAuthProfile.getSecretKeyId()"})
  public void testGetSecretKeyId_thenReturn42AuthProfile42() {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");
    DBAAuthProfile source = new DBAAuthProfile(project);

    DBAAuthProfile dbaAuthProfile = new DBAAuthProfile(source);
    dbaAuthProfile.setProfileId("42");

    // Act
    String actualSecretKeyId = dbaAuthProfile.getSecretKeyId();

    // Assert
    verify(project).getId();
    assertEquals("42/auth-profile/42", actualSecretKeyId);
  }

  /**
   * Test {@link DBAAuthProfile#persistSecrets(DBSSecretController)}.
   *
   * <p>Method under test: {@link DBAAuthProfile#persistSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBAAuthProfile.persistSecrets(DBSSecretController)"})
  public void testPersistSecrets() throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");
    DBAAuthProfile source = new DBAAuthProfile(project);

    DBAAuthProfile dbaAuthProfile = new DBAAuthProfile(source);
    dbaAuthProfile.setProfileName("");

    LocalSecretController secretController = mock(LocalSecretController.class);
    doNothing().when(secretController).flushChanges();
    doNothing()
        .when(secretController)
        .setPrivateSecretValue(Mockito.<String>any(), Mockito.<String>any());

    // Act
    dbaAuthProfile.persistSecrets(secretController);

    // Assert
    verify(project).getId();
    verify(secretController).flushChanges();
    verify(secretController)
        .setPrivateSecretValue(
            "42/auth-profile/", "{\n  \"profile-id\": \"\",\n  \"profile-name\": \"\"\n}");
  }

  /**
   * Test {@link DBAAuthProfile#persistSecrets(DBSSecretController)}.
   *
   * <p>Method under test: {@link DBAAuthProfile#persistSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBAAuthProfile.persistSecrets(DBSSecretController)"})
  public void testPersistSecrets2() throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");

    DBAAuthProfile dbaAuthProfile = new DBAAuthProfile(project);
    dbaAuthProfile.setUserPassword("iloveyou");
    dbaAuthProfile.setProfileName("");

    LocalSecretController secretController = mock(LocalSecretController.class);
    doNothing().when(secretController).flushChanges();
    doNothing()
        .when(secretController)
        .setPrivateSecretValue(Mockito.<String>any(), Mockito.<String>any());

    // Act
    dbaAuthProfile.persistSecrets(secretController);

    // Assert
    verify(project).getId();
    verify(secretController).flushChanges();
    verify(secretController)
        .setPrivateSecretValue(
            "42/auth-profile/",
            "{\n  \"profile-id\": \"\",\n  \"profile-name\": \"\",\n  \"password\": \"iloveyou\"\n}");
  }

  /**
   * Test {@link DBAAuthProfile#persistSecrets(DBSSecretController)}.
   *
   * <ul>
   *   <li>Given {@link DBAAuthProfile#DBAAuthProfile(DBPProject)} with project is {@link
   *       DBPProject} ProfileId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBAAuthProfile#persistSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBAAuthProfile.persistSecrets(DBSSecretController)"})
  public void testPersistSecrets_givenDBAAuthProfileWithProjectIsDBPProjectProfileIdIs42()
      throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");

    DBAAuthProfile dbaAuthProfile = new DBAAuthProfile(project);
    dbaAuthProfile.setProfileId("42");
    dbaAuthProfile.setProfileName("");

    LocalSecretController secretController = mock(LocalSecretController.class);
    doNothing().when(secretController).flushChanges();
    doNothing()
        .when(secretController)
        .setPrivateSecretValue(Mockito.<String>any(), Mockito.<String>any());

    // Act
    dbaAuthProfile.persistSecrets(secretController);

    // Assert
    verify(project).getId();
    verify(secretController).flushChanges();
    verify(secretController)
        .setPrivateSecretValue(
            "42/auth-profile/42", "{\n  \"profile-id\": \"42\",\n  \"profile-name\": \"\"\n}");
  }

  /**
   * Test {@link DBAAuthProfile#persistSecrets(DBSSecretController)}.
   *
   * <ul>
   *   <li>Given {@link DBAAuthProfile#DBAAuthProfile(DBPProject)} with project is {@link
   *       DBPProject} ProfileName is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBAAuthProfile#persistSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBAAuthProfile.persistSecrets(DBSSecretController)"})
  public void testPersistSecrets_givenDBAAuthProfileWithProjectIsDBPProjectProfileNameIsNull()
      throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");

    DBAAuthProfile dbaAuthProfile = new DBAAuthProfile(project);
    dbaAuthProfile.setProfileName(null);

    LocalSecretController secretController = mock(LocalSecretController.class);
    doNothing().when(secretController).flushChanges();
    doNothing()
        .when(secretController)
        .setPrivateSecretValue(Mockito.<String>any(), Mockito.<String>any());

    // Act
    dbaAuthProfile.persistSecrets(secretController);

    // Assert
    verify(project).getId();
    verify(secretController).flushChanges();
    verify(secretController)
        .setPrivateSecretValue(
            "42/auth-profile/null", "{\n  \"profile-id\": null,\n  \"profile-name\": null\n}");
  }

  /**
   * Test {@link DBAAuthProfile#persistSecrets(DBSSecretController)}.
   *
   * <ul>
   *   <li>Given {@link DBAAuthProfile#DBAAuthProfile(DBPProject)} with project is {@link
   *       DBPProject} UserName is {@code janedoe}.
   * </ul>
   *
   * <p>Method under test: {@link DBAAuthProfile#persistSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBAAuthProfile.persistSecrets(DBSSecretController)"})
  public void testPersistSecrets_givenDBAAuthProfileWithProjectIsDBPProjectUserNameIsJanedoe()
      throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");

    DBAAuthProfile dbaAuthProfile = new DBAAuthProfile(project);
    dbaAuthProfile.setUserName("janedoe");
    dbaAuthProfile.setProfileName("");

    LocalSecretController secretController = mock(LocalSecretController.class);
    doNothing().when(secretController).flushChanges();
    doNothing()
        .when(secretController)
        .setPrivateSecretValue(Mockito.<String>any(), Mockito.<String>any());

    // Act
    dbaAuthProfile.persistSecrets(secretController);

    // Assert
    verify(project).getId();
    verify(secretController).flushChanges();
    verify(secretController)
        .setPrivateSecretValue(
            "42/auth-profile/",
            "{\n  \"profile-id\": \"\",\n  \"profile-name\": \"\",\n  \"user\": \"janedoe\"\n}");
  }

  /**
   * Test {@link DBAAuthProfile#persistSecrets(DBSSecretController)}.
   *
   * <ul>
   *   <li>Given {@link DBPProject} {@link DBPProject#getId()} return {@code foo}.
   *   <li>Then calls {@link DBPProject#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DBAAuthProfile#persistSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBAAuthProfile.persistSecrets(DBSSecretController)"})
  public void testPersistSecrets_givenDBPProjectGetIdReturnFoo_thenCallsGetId() throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("foo");

    DBAAuthProfile dbaAuthProfile = new DBAAuthProfile(project);
    dbaAuthProfile.setProfileName("");

    LocalSecretController secretController = mock(LocalSecretController.class);
    doNothing().when(secretController).flushChanges();
    doNothing()
        .when(secretController)
        .setPrivateSecretValue(Mockito.<String>any(), Mockito.<String>any());

    // Act
    dbaAuthProfile.persistSecrets(secretController);

    // Assert
    verify(project).getId();
    verify(secretController).flushChanges();
    verify(secretController)
        .setPrivateSecretValue(
            "foo/auth-profile/", "{\n  \"profile-id\": \"\",\n  \"profile-name\": \"\"\n}");
  }

  /**
   * Test {@link DBAAuthProfile#resolveSecrets(DBSSecretController)}.
   *
   * <p>Method under test: {@link DBAAuthProfile#resolveSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBAAuthProfile.resolveSecrets(DBSSecretController)"})
  public void testResolveSecrets() throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");

    DBAAuthProfile dbaAuthProfile = new DBAAuthProfile(project);
    dbaAuthProfile.setProfileId("");

    LocalSecretController secretController = mock(LocalSecretController.class);
    when(secretController.getPrivateSecretValue(Mockito.<String>any())).thenReturn(null);

    // Act
    dbaAuthProfile.resolveSecrets(secretController);

    // Assert
    verify(project).getId();
    verify(secretController).getPrivateSecretValue("42/auth-profile/null");
  }

  /**
   * Test {@link DBAAuthProfile#resolveSecrets(DBSSecretController)}.
   *
   * <ul>
   *   <li>Given {@link DBAAuthProfile#DBAAuthProfile(DBPProject)} with project is {@link
   *       DBPProject} ProfileId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBAAuthProfile#resolveSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBAAuthProfile.resolveSecrets(DBSSecretController)"})
  public void testResolveSecrets_givenDBAAuthProfileWithProjectIsDBPProjectProfileIdIs42()
      throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");

    DBAAuthProfile dbaAuthProfile = new DBAAuthProfile(project);
    dbaAuthProfile.setProfileId("42");

    LocalSecretController secretController = mock(LocalSecretController.class);
    when(secretController.getPrivateSecretValue(Mockito.<String>any())).thenReturn(null);

    // Act
    dbaAuthProfile.resolveSecrets(secretController);

    // Assert
    verify(project).getId();
    verify(secretController).getPrivateSecretValue("42/auth-profile/42");
  }

  /**
   * Test {@link DBAAuthProfile#resolveSecrets(DBSSecretController)}.
   *
   * <ul>
   *   <li>Given {@link DBPProject} {@link DBPProject#getId()} return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link DBAAuthProfile#resolveSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBAAuthProfile.resolveSecrets(DBSSecretController)"})
  public void testResolveSecrets_givenDBPProjectGetIdReturnFoo() throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("foo");
    DBAAuthProfile dbaAuthProfile = new DBAAuthProfile(project);

    LocalSecretController secretController = mock(LocalSecretController.class);
    when(secretController.getPrivateSecretValue(Mockito.<String>any())).thenReturn(null);

    // Act
    dbaAuthProfile.resolveSecrets(secretController);

    // Assert
    verify(project).getId();
    verify(secretController).getPrivateSecretValue("foo/auth-profile/null");
  }

  /**
   * Test {@link DBAAuthProfile#resolveSecrets(DBSSecretController)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link DBAAuthProfile#resolveSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBAAuthProfile.resolveSecrets(DBSSecretController)"})
  public void testResolveSecrets_givenEmptyString() throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");
    DBAAuthProfile dbaAuthProfile = new DBAAuthProfile(project);

    LocalSecretController secretController = mock(LocalSecretController.class);
    when(secretController.getPrivateSecretValue(Mockito.<String>any())).thenReturn("");

    // Act
    dbaAuthProfile.resolveSecrets(secretController);

    // Assert
    verify(project).getId();
    verify(secretController).getPrivateSecretValue("42/auth-profile/null");
  }

  /**
   * Test {@link DBAAuthProfile#resolveSecrets(DBSSecretController)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBAAuthProfile#resolveSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBAAuthProfile.resolveSecrets(DBSSecretController)"})
  public void testResolveSecrets_givenNull() throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");
    DBAAuthProfile dbaAuthProfile = new DBAAuthProfile(project);

    LocalSecretController secretController = mock(LocalSecretController.class);
    when(secretController.getPrivateSecretValue(Mockito.<String>any())).thenReturn(null);

    // Act
    dbaAuthProfile.resolveSecrets(secretController);

    // Assert
    verify(project).getId();
    verify(secretController).getPrivateSecretValue("42/auth-profile/null");
  }
}
