package org.jkiss.dbeaver.model.net;

import static org.junit.Assert.assertEquals;
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
import java.util.HashMap;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.secret.DBSSecretController;
import org.jkiss.dbeaver.model.secret.DBSSecretSubject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBWNetworkProfileDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return Project is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBWNetworkProfile#DBWNetworkProfile()}
   *   <li>{@link DBWNetworkProfile#setSecretSubject(DBSSecretSubject)}
   *   <li>{@link DBWNetworkProfile#getConfigurations()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBWNetworkProfile.<init>()",
    "void DBWNetworkProfile.<init>(DBPProject)",
    "List DBWNetworkProfile.getConfigurations()",
    "void DBWNetworkProfile.setSecretSubject(DBSSecretSubject)"
  })
  public void testGettersAndSetters_thenReturnProjectIsNull() {
    // Arrange and Act
    DBWNetworkProfile actualDbwNetworkProfile = new DBWNetworkProfile();
    actualDbwNetworkProfile.setSecretSubject(mock(DBSSecretSubject.class));
    List<DBWHandlerConfiguration> actualConfigurations =
        actualDbwNetworkProfile.getConfigurations();

    // Assert
    assertNull(actualDbwNetworkProfile.getProfileDescription());
    assertNull(actualDbwNetworkProfile.getProfileName());
    assertNull(actualDbwNetworkProfile.toString());
    assertNull(actualDbwNetworkProfile.getProject());
    assertTrue(actualConfigurations.isEmpty());
    assertTrue(actualDbwNetworkProfile.getProperties().isEmpty());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link DBPProject}.
   *   <li>Then return Project is {@link DBPProject}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBWNetworkProfile#DBWNetworkProfile(DBPProject)}
   *   <li>{@link DBWNetworkProfile#setSecretSubject(DBSSecretSubject)}
   *   <li>{@link DBWNetworkProfile#getConfigurations()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBWNetworkProfile.<init>()",
    "void DBWNetworkProfile.<init>(DBPProject)",
    "List DBWNetworkProfile.getConfigurations()",
    "void DBWNetworkProfile.setSecretSubject(DBSSecretSubject)"
  })
  public void testGettersAndSetters_whenDBPProject_thenReturnProjectIsDBPProject() {
    // Arrange
    DBPProject project = mock(DBPProject.class);

    // Act
    DBWNetworkProfile actualDbwNetworkProfile = new DBWNetworkProfile(project);
    actualDbwNetworkProfile.setSecretSubject(mock(DBSSecretSubject.class));
    List<DBWHandlerConfiguration> actualConfigurations =
        actualDbwNetworkProfile.getConfigurations();

    // Assert
    assertNull(actualDbwNetworkProfile.getProfileDescription());
    assertNull(actualDbwNetworkProfile.getProfileName());
    assertNull(actualDbwNetworkProfile.toString());
    assertTrue(actualConfigurations.isEmpty());
    assertTrue(actualDbwNetworkProfile.getProperties().isEmpty());
    assertSame(project, actualDbwNetworkProfile.getProject());
  }

  /**
   * Test {@link DBWNetworkProfile#getProfileSource()}.
   *
   * <ul>
   *   <li>Given {@link DBWNetworkProfile#DBWNetworkProfile()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBWNetworkProfile#getProfileSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBWNetworkProfile.getProfileSource()"})
  public void testGetProfileSource_givenDBWNetworkProfile_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DBWNetworkProfile().getProfileSource());
  }

  /**
   * Test {@link DBWNetworkProfile#getProfileSource()}.
   *
   * <ul>
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBWNetworkProfile#getProfileSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBWNetworkProfile.getProfileSource()"})
  public void testGetProfileSource_thenReturn42() {
    // Arrange
    DBSSecretSubject secretSubject = mock(DBSSecretSubject.class);
    when(secretSubject.getSecretSubjectId()).thenReturn("42");

    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile(mock(DBPProject.class));
    dbwNetworkProfile.setSecretSubject(secretSubject);

    // Act
    String actualProfileSource = dbwNetworkProfile.getProfileSource();

    // Assert
    verify(secretSubject).getSecretSubjectId();
    assertEquals("42", actualProfileSource);
  }

  /**
   * Test {@link DBWNetworkProfile#updateConfiguration(DBWHandlerConfiguration)}.
   *
   * <p>Method under test: {@link DBWNetworkProfile#updateConfiguration(DBWHandlerConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWNetworkProfile.updateConfiguration(DBWHandlerConfiguration)"})
  public void testUpdateConfiguration() {
    // Arrange
    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile();
    DBWHandlerConfiguration cfg = new DBWHandlerConfiguration();

    // Act
    dbwNetworkProfile.updateConfiguration(cfg);

    // Assert
    List<DBWHandlerConfiguration> configurations = dbwNetworkProfile.getConfigurations();
    assertEquals(1, configurations.size());
    assertSame(cfg, configurations.get(0));
  }

  /**
   * Test {@link DBWNetworkProfile#getConfiguration(String)} with {@code configId}.
   *
   * <p>Method under test: {@link DBWNetworkProfile#getConfiguration(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBWHandlerConfiguration DBWNetworkProfile.getConfiguration(String)"})
  public void testGetConfigurationWithConfigId() {
    // Arrange
    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile();
    dbwNetworkProfile.updateConfiguration(new DBWHandlerConfiguration());

    // Act and Assert
    assertNull(dbwNetworkProfile.getConfiguration("42"));
  }

  /**
   * Test {@link DBWNetworkProfile#getConfiguration(String)} with {@code configId}.
   *
   * <p>Method under test: {@link DBWNetworkProfile#getConfiguration(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBWHandlerConfiguration DBWNetworkProfile.getConfiguration(String)"})
  public void testGetConfigurationWithConfigId2() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration cfg =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile();
    dbwNetworkProfile.updateConfiguration(cfg);

    // Act
    DBWHandlerConfiguration actualConfiguration = dbwNetworkProfile.getConfiguration("42");

    // Assert
    verify(descriptor).getId();
    assertSame(cfg, actualConfiguration);
  }

  /**
   * Test {@link DBWNetworkProfile#getConfiguration(String)} with {@code configId}.
   *
   * <ul>
   *   <li>Given {@link DBWNetworkProfile#DBWNetworkProfile()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBWNetworkProfile#getConfiguration(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBWHandlerConfiguration DBWNetworkProfile.getConfiguration(String)"})
  public void testGetConfigurationWithConfigId_givenDBWNetworkProfile_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DBWNetworkProfile().getConfiguration("42"));
  }

  /**
   * Test {@link DBWNetworkProfile#getConfiguration(DBWHandlerDescriptor)} with {@code handler}.
   *
   * <p>Method under test: {@link DBWNetworkProfile#getConfiguration(DBWHandlerDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBWHandlerConfiguration DBWNetworkProfile.getConfiguration(DBWHandlerDescriptor)"
  })
  public void testGetConfigurationWithHandler() {
    // Arrange
    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile();
    dbwNetworkProfile.updateConfiguration(new DBWHandlerConfiguration());

    DBWHandlerDescriptor handler = mock(DBWHandlerDescriptor.class);
    when(handler.getId()).thenReturn("42");

    // Act
    DBWHandlerConfiguration actualConfiguration = dbwNetworkProfile.getConfiguration(handler);

    // Assert
    verify(handler).getId();
    assertNull(actualConfiguration);
  }

  /**
   * Test {@link DBWNetworkProfile#getConfiguration(DBWHandlerDescriptor)} with {@code handler}.
   *
   * <p>Method under test: {@link DBWNetworkProfile#getConfiguration(DBWHandlerDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBWHandlerConfiguration DBWNetworkProfile.getConfiguration(DBWHandlerDescriptor)"
  })
  public void testGetConfigurationWithHandler2() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration cfg =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile();
    dbwNetworkProfile.updateConfiguration(cfg);

    DBWHandlerDescriptor handler = mock(DBWHandlerDescriptor.class);
    when(handler.getId()).thenReturn("42");

    // Act
    DBWHandlerConfiguration actualConfiguration = dbwNetworkProfile.getConfiguration(handler);

    // Assert
    verify(descriptor).getId();
    verify(handler).getId();
    assertSame(cfg, actualConfiguration);
  }

  /**
   * Test {@link DBWNetworkProfile#getConfiguration(DBWHandlerDescriptor)} with {@code handler}.
   *
   * <ul>
   *   <li>Given {@link DBWNetworkProfile#DBWNetworkProfile()}.
   *   <li>When {@link DBWHandlerDescriptor}.
   * </ul>
   *
   * <p>Method under test: {@link DBWNetworkProfile#getConfiguration(DBWHandlerDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBWHandlerConfiguration DBWNetworkProfile.getConfiguration(DBWHandlerDescriptor)"
  })
  public void testGetConfigurationWithHandler_givenDBWNetworkProfile_whenDBWHandlerDescriptor() {
    // Arrange, Act and Assert
    assertNull(new DBWNetworkProfile().getConfiguration(mock(DBWHandlerDescriptor.class)));
  }

  /**
   * Test {@link DBWNetworkProfile#getSecretKeyId()}.
   *
   * <p>Method under test: {@link DBWNetworkProfile#getSecretKeyId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBWNetworkProfile.getSecretKeyId()"})
  public void testGetSecretKeyId() {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");

    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile(project);
    dbwNetworkProfile.setProfileId("");

    // Act
    String actualSecretKeyId = dbwNetworkProfile.getSecretKeyId();

    // Assert
    verify(project).getId();
    assertEquals("42/network-profile/null", actualSecretKeyId);
  }

  /**
   * Test {@link DBWNetworkProfile#getSecretKeyId()}.
   *
   * <ul>
   *   <li>Given {@link DBPProject} {@link DBPProject#getId()} return {@code 42}.
   *   <li>Then return {@code 42/network-profile/null}.
   * </ul>
   *
   * <p>Method under test: {@link DBWNetworkProfile#getSecretKeyId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBWNetworkProfile.getSecretKeyId()"})
  public void testGetSecretKeyId_givenDBPProjectGetIdReturn42_thenReturn42NetworkProfileNull() {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");

    // Act
    String actualSecretKeyId = new DBWNetworkProfile(project).getSecretKeyId();

    // Assert
    verify(project).getId();
    assertEquals("42/network-profile/null", actualSecretKeyId);
  }

  /**
   * Test {@link DBWNetworkProfile#getSecretKeyId()}.
   *
   * <ul>
   *   <li>Given {@link DBPProject} {@link DBPProject#getId()} return {@code foo}.
   *   <li>Then return {@code foo/network-profile/null}.
   * </ul>
   *
   * <p>Method under test: {@link DBWNetworkProfile#getSecretKeyId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBWNetworkProfile.getSecretKeyId()"})
  public void testGetSecretKeyId_givenDBPProjectGetIdReturnFoo_thenReturnFooNetworkProfileNull() {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("foo");

    // Act
    String actualSecretKeyId = new DBWNetworkProfile(project).getSecretKeyId();

    // Assert
    verify(project).getId();
    assertEquals("foo/network-profile/null", actualSecretKeyId);
  }

  /**
   * Test {@link DBWNetworkProfile#getSecretKeyId()}.
   *
   * <ul>
   *   <li>Given {@link DBWNetworkProfile#DBWNetworkProfile()}.
   *   <li>Then return {@code global/network-profile/null}.
   * </ul>
   *
   * <p>Method under test: {@link DBWNetworkProfile#getSecretKeyId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBWNetworkProfile.getSecretKeyId()"})
  public void testGetSecretKeyId_givenDBWNetworkProfile_thenReturnGlobalNetworkProfileNull() {
    // Arrange, Act and Assert
    assertEquals("global/network-profile/null", new DBWNetworkProfile().getSecretKeyId());
  }

  /**
   * Test {@link DBWNetworkProfile#getSecretKeyId()}.
   *
   * <ul>
   *   <li>Then calls {@link DBSSecretSubject#getSecretSubjectId()}.
   * </ul>
   *
   * <p>Method under test: {@link DBWNetworkProfile#getSecretKeyId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBWNetworkProfile.getSecretKeyId()"})
  public void testGetSecretKeyId_thenCallsGetSecretSubjectId() {
    // Arrange
    DBSSecretSubject secretSubject = mock(DBSSecretSubject.class);
    when(secretSubject.getSecretSubjectId()).thenReturn("42");

    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile();
    dbwNetworkProfile.setSecretSubject(secretSubject);
    dbwNetworkProfile.setProfileId("");

    // Act
    String actualSecretKeyId = dbwNetworkProfile.getSecretKeyId();

    // Assert
    verify(secretSubject).getSecretSubjectId();
    assertEquals("42/network-profile/null", actualSecretKeyId);
  }

  /**
   * Test {@link DBWNetworkProfile#getSecretKeyId()}.
   *
   * <ul>
   *   <li>Then return {@code 42/network-profile/42}.
   * </ul>
   *
   * <p>Method under test: {@link DBWNetworkProfile#getSecretKeyId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBWNetworkProfile.getSecretKeyId()"})
  public void testGetSecretKeyId_thenReturn42NetworkProfile42() {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");

    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile(project);
    dbwNetworkProfile.setProfileId("42");

    // Act
    String actualSecretKeyId = dbwNetworkProfile.getSecretKeyId();

    // Assert
    verify(project).getId();
    assertEquals("42/network-profile/42", actualSecretKeyId);
  }

  /**
   * Test {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}.
   *
   * <p>Method under test: {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWNetworkProfile.persistSecrets(DBSSecretController)"})
  public void testPersistSecrets() throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");

    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile(project);
    dbwNetworkProfile.setProfileName(null);

    DBSSecretController secretController = mock(DBSSecretController.class);
    doNothing()
        .when(secretController)
        .setPrivateSecretValue(Mockito.<String>any(), Mockito.<String>any());

    // Act
    dbwNetworkProfile.persistSecrets(secretController);

    // Assert
    verify(project).getId();
    verify(secretController)
        .setPrivateSecretValue(
            "42/network-profile/null", "{\n  \"profile-id\": null,\n  \"profile-name\": null\n}");
  }

  /**
   * Test {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}.
   *
   * <p>Method under test: {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWNetworkProfile.persistSecrets(DBSSecretController)"})
  public void testPersistSecrets2() throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");

    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile(project);
    dbwNetworkProfile.updateConfiguration(new DBWHandlerConfiguration());
    dbwNetworkProfile.setProfileName("");

    DBSSecretController secretController = mock(DBSSecretController.class);
    doNothing()
        .when(secretController)
        .setPrivateSecretValue(Mockito.<String>any(), Mockito.<String>any());

    // Act
    dbwNetworkProfile.persistSecrets(secretController);

    // Assert
    verify(project).getId();
    verify(secretController)
        .setPrivateSecretValue(
            "42/network-profile/", "{\n  \"profile-id\": \"\",\n  \"profile-name\": \"\"\n}");
  }

  /**
   * Test {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}.
   *
   * <p>Method under test: {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWNetworkProfile.persistSecrets(DBSSecretController)"})
  public void testPersistSecrets3() throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("profile-id", DBPEvent.RENAME);

    DBWHandlerConfiguration cfg = mock(DBWHandlerConfiguration.class);
    when(cfg.getId()).thenReturn("42");
    when(cfg.saveToMap()).thenReturn(stringObjectMap);

    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile(project);
    dbwNetworkProfile.setProfileId("");
    dbwNetworkProfile.updateConfiguration(cfg);
    dbwNetworkProfile.setProfileName("");

    DBSSecretController secretController = mock(DBSSecretController.class);
    doNothing()
        .when(secretController)
        .setPrivateSecretValue(Mockito.<String>any(), Mockito.<String>any());

    // Act
    dbwNetworkProfile.persistSecrets(secretController);

    // Assert
    verify(project).getId();
    verify(cfg).getId();
    verify(cfg).saveToMap();
    verify(secretController)
        .setPrivateSecretValue(
            "42/network-profile/",
            "{\n  \"profile-id\": \"\",\n  \"profile-name\": \"\",\n  \"handlers\": [\n    {\n      \"profile-id\": {},\n      \"id\": \"42\"\n    }\n  ]\n}");
  }

  /**
   * Test {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}.
   *
   * <ul>
   *   <li>Given {@link DBPProject} {@link DBPProject#getId()} return {@code 42}.
   *   <li>Then calls {@link DBPProject#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWNetworkProfile.persistSecrets(DBSSecretController)"})
  public void testPersistSecrets_givenDBPProjectGetIdReturn42_thenCallsGetId() throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");

    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile(project);
    dbwNetworkProfile.setProfileName("");

    DBSSecretController secretController = mock(DBSSecretController.class);
    doNothing()
        .when(secretController)
        .setPrivateSecretValue(Mockito.<String>any(), Mockito.<String>any());

    // Act
    dbwNetworkProfile.persistSecrets(secretController);

    // Assert
    verify(project).getId();
    verify(secretController)
        .setPrivateSecretValue(
            "42/network-profile/", "{\n  \"profile-id\": \"\",\n  \"profile-name\": \"\"\n}");
  }

  /**
   * Test {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}.
   *
   * <ul>
   *   <li>Given {@link DBPProject} {@link DBPProject#getId()} return {@code profile-id}.
   *   <li>Then calls {@link DBPProject#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWNetworkProfile.persistSecrets(DBSSecretController)"})
  public void testPersistSecrets_givenDBPProjectGetIdReturnProfileId_thenCallsGetId()
      throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("profile-id");

    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile(project);
    dbwNetworkProfile.setProfileName("");

    DBSSecretController secretController = mock(DBSSecretController.class);
    doNothing()
        .when(secretController)
        .setPrivateSecretValue(Mockito.<String>any(), Mockito.<String>any());

    // Act
    dbwNetworkProfile.persistSecrets(secretController);

    // Assert
    verify(project).getId();
    verify(secretController)
        .setPrivateSecretValue(
            "profile-id/network-profile/",
            "{\n  \"profile-id\": \"\",\n  \"profile-name\": \"\"\n}");
  }

  /**
   * Test {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}.
   *
   * <ul>
   *   <li>Given {@link DBWNetworkProfile#DBWNetworkProfile()} ProfileName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWNetworkProfile.persistSecrets(DBSSecretController)"})
  public void testPersistSecrets_givenDBWNetworkProfileProfileNameIsEmptyString()
      throws DBException {
    // Arrange
    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile();
    dbwNetworkProfile.setProfileName("");

    DBSSecretController secretController = mock(DBSSecretController.class);
    doNothing()
        .when(secretController)
        .setPrivateSecretValue(Mockito.<String>any(), Mockito.<String>any());

    // Act
    dbwNetworkProfile.persistSecrets(secretController);

    // Assert
    verify(secretController)
        .setPrivateSecretValue(
            "global/network-profile/", "{\n  \"profile-id\": \"\",\n  \"profile-name\": \"\"\n}");
  }

  /**
   * Test {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}.
   *
   * <ul>
   *   <li>Given {@link DBWNetworkProfile#DBWNetworkProfile(DBPProject)} with project is {@link
   *       DBPProject} ProfileId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWNetworkProfile.persistSecrets(DBSSecretController)"})
  public void testPersistSecrets_givenDBWNetworkProfileWithProjectIsDBPProjectProfileIdIs42()
      throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");

    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile(project);
    dbwNetworkProfile.setProfileId("42");
    dbwNetworkProfile.setProfileName("");

    DBSSecretController secretController = mock(DBSSecretController.class);
    doNothing()
        .when(secretController)
        .setPrivateSecretValue(Mockito.<String>any(), Mockito.<String>any());

    // Act
    dbwNetworkProfile.persistSecrets(secretController);

    // Assert
    verify(project).getId();
    verify(secretController)
        .setPrivateSecretValue(
            "42/network-profile/42", "{\n  \"profile-id\": \"42\",\n  \"profile-name\": \"\"\n}");
  }

  /**
   * Test {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code profile-id} is {@link
   *       DBWHandlerConfiguration#DBWHandlerConfiguration()}.
   *   <li>Then calls {@link DBWHandlerConfiguration#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWNetworkProfile.persistSecrets(DBSSecretController)"})
  public void testPersistSecrets_givenHashMapProfileIdIsDBWHandlerConfiguration_thenCallsGetId()
      throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("profile-id", new DBWHandlerConfiguration());

    DBWHandlerConfiguration cfg = mock(DBWHandlerConfiguration.class);
    when(cfg.getId()).thenReturn("42");
    when(cfg.saveToMap()).thenReturn(stringObjectMap);

    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile(project);
    dbwNetworkProfile.updateConfiguration(cfg);
    dbwNetworkProfile.setProfileName("");

    DBSSecretController secretController = mock(DBSSecretController.class);
    doNothing()
        .when(secretController)
        .setPrivateSecretValue(Mockito.<String>any(), Mockito.<String>any());

    // Act
    dbwNetworkProfile.persistSecrets(secretController);

    // Assert
    verify(project).getId();
    verify(cfg).getId();
    verify(cfg).saveToMap();
    verify(secretController)
        .setPrivateSecretValue(
            "42/network-profile/",
            "{\n  \"profile-id\": \"\",\n  \"profile-name\": \"\",\n  \"handlers\": [\n    {\n      \"profile-id\": {\n        \"id\": null,\n        \"enabled\": false,\n        \"userName\": null,\n        \"password\": null,\n        \"savePassword\": true,\n        \"properties\": {},\n        \"secureProperties\": {}\n      },\n      \"id\": \"42\"\n    }\n  ]\n}");
  }

  /**
   * Test {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code profile-id} is {@link DBWHandlerConfiguration}.
   *   <li>Then calls {@link DBWHandlerConfiguration#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWNetworkProfile.persistSecrets(DBSSecretController)"})
  public void testPersistSecrets_givenHashMapProfileIdIsDBWHandlerConfiguration_thenCallsGetId2()
      throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("profile-id", mock(DBWHandlerConfiguration.class));

    DBWHandlerConfiguration cfg = mock(DBWHandlerConfiguration.class);
    when(cfg.getId()).thenReturn("42");
    when(cfg.saveToMap()).thenReturn(stringObjectMap);

    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile(project);
    dbwNetworkProfile.updateConfiguration(cfg);
    dbwNetworkProfile.setProfileName("");

    DBSSecretController secretController = mock(DBSSecretController.class);
    doNothing()
        .when(secretController)
        .setPrivateSecretValue(Mockito.<String>any(), Mockito.<String>any());

    // Act
    dbwNetworkProfile.persistSecrets(secretController);

    // Assert
    verify(project).getId();
    verify(cfg).getId();
    verify(cfg).saveToMap();
    verify(secretController)
        .setPrivateSecretValue(
            "42/network-profile/",
            "{\n  \"profile-id\": \"\",\n  \"profile-name\": \"\",\n  \"handlers\": [\n    {\n      \"profile-id\": {\n        \"id\": null,\n        \"enabled\": false,\n        \"userName\": null,\n        \"password\": null,\n        \"savePassword\": false,\n        \"properties\": null,\n        \"secureProperties\": null\n      },\n      \"id\": \"42\"\n    }\n  ]\n}");
  }

  /**
   * Test {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code profile-id} is one.
   *   <li>Then calls {@link DBWHandlerConfiguration#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWNetworkProfile.persistSecrets(DBSSecretController)"})
  public void testPersistSecrets_givenHashMapProfileIdIsOne_thenCallsGetId() throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("profile-id", 1);

    DBWHandlerConfiguration cfg = mock(DBWHandlerConfiguration.class);
    when(cfg.getId()).thenReturn("42");
    when(cfg.saveToMap()).thenReturn(stringObjectMap);

    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile(project);
    dbwNetworkProfile.updateConfiguration(cfg);
    dbwNetworkProfile.setProfileName("");

    DBSSecretController secretController = mock(DBSSecretController.class);
    doNothing()
        .when(secretController)
        .setPrivateSecretValue(Mockito.<String>any(), Mockito.<String>any());

    // Act
    dbwNetworkProfile.persistSecrets(secretController);

    // Assert
    verify(project).getId();
    verify(cfg).getId();
    verify(cfg).saveToMap();
    verify(secretController)
        .setPrivateSecretValue(
            "42/network-profile/",
            "{\n  \"profile-id\": \"\",\n  \"profile-name\": \"\",\n  \"handlers\": [\n    {\n      \"profile-id\": 1,\n      \"id\": \"42\"\n    }\n  ]\n}");
  }

  /**
   * Test {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code profile-id} is {@link DBPEvent#RENAME}.
   *   <li>Then calls {@link DBWHandlerConfiguration#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWNetworkProfile.persistSecrets(DBSSecretController)"})
  public void testPersistSecrets_givenHashMapProfileIdIsRename_thenCallsGetId() throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("profile-id", DBPEvent.RENAME);

    DBWHandlerConfiguration cfg = mock(DBWHandlerConfiguration.class);
    when(cfg.getId()).thenReturn("42");
    when(cfg.saveToMap()).thenReturn(stringObjectMap);

    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile(project);
    dbwNetworkProfile.updateConfiguration(cfg);
    dbwNetworkProfile.setProfileName("");

    DBSSecretController secretController = mock(DBSSecretController.class);
    doNothing()
        .when(secretController)
        .setPrivateSecretValue(Mockito.<String>any(), Mockito.<String>any());

    // Act
    dbwNetworkProfile.persistSecrets(secretController);

    // Assert
    verify(project).getId();
    verify(cfg).getId();
    verify(cfg).saveToMap();
    verify(secretController)
        .setPrivateSecretValue(
            "42/network-profile/",
            "{\n  \"profile-id\": \"\",\n  \"profile-name\": \"\",\n  \"handlers\": [\n    {\n      \"profile-id\": {},\n      \"id\": \"42\"\n    }\n  ]\n}");
  }

  /**
   * Test {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}.
   *
   * <ul>
   *   <li>Then calls {@link DBWHandlerConfiguration#saveToMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DBWNetworkProfile#persistSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWNetworkProfile.persistSecrets(DBSSecretController)"})
  public void testPersistSecrets_thenCallsSaveToMap() throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");

    DBWHandlerConfiguration cfg = mock(DBWHandlerConfiguration.class);
    when(cfg.saveToMap()).thenReturn(new HashMap<>());

    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile(project);
    dbwNetworkProfile.updateConfiguration(cfg);
    dbwNetworkProfile.setProfileName("");

    DBSSecretController secretController = mock(DBSSecretController.class);
    doNothing()
        .when(secretController)
        .setPrivateSecretValue(Mockito.<String>any(), Mockito.<String>any());

    // Act
    dbwNetworkProfile.persistSecrets(secretController);

    // Assert
    verify(project).getId();
    verify(cfg).saveToMap();
    verify(secretController)
        .setPrivateSecretValue(
            "42/network-profile/", "{\n  \"profile-id\": \"\",\n  \"profile-name\": \"\"\n}");
  }

  /**
   * Test {@link DBWNetworkProfile#resolveSecrets(DBSSecretController)}.
   *
   * <ul>
   *   <li>Given {@link DBWNetworkProfile#DBWNetworkProfile()} ProfileId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBWNetworkProfile#resolveSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWNetworkProfile.resolveSecrets(DBSSecretController)"})
  public void testResolveSecrets_givenDBWNetworkProfileProfileIdIs42() throws DBException {
    // Arrange
    DBSSecretSubject secretSubject = mock(DBSSecretSubject.class);
    when(secretSubject.getSecretSubjectId()).thenReturn("42");

    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile();
    dbwNetworkProfile.setProfileId("42");
    dbwNetworkProfile.setSecretSubject(secretSubject);

    DBSSecretController secretController = mock(DBSSecretController.class);
    when(secretController.getPrivateSecretValue(Mockito.<String>any())).thenReturn("");

    // Act
    dbwNetworkProfile.resolveSecrets(secretController);

    // Assert
    verify(secretController).getPrivateSecretValue("42/network-profile/42");
    verify(secretSubject).getSecretSubjectId();
  }

  /**
   * Test {@link DBWNetworkProfile#resolveSecrets(DBSSecretController)}.
   *
   * <ul>
   *   <li>Given {@link DBWNetworkProfile#DBWNetworkProfile()} ProfileId is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DBWNetworkProfile#resolveSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWNetworkProfile.resolveSecrets(DBSSecretController)"})
  public void testResolveSecrets_givenDBWNetworkProfileProfileIdIsEmptyString() throws DBException {
    // Arrange
    DBSSecretSubject secretSubject = mock(DBSSecretSubject.class);
    when(secretSubject.getSecretSubjectId()).thenReturn("42");

    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile();
    dbwNetworkProfile.setProfileId("");
    dbwNetworkProfile.setSecretSubject(secretSubject);

    DBSSecretController secretController = mock(DBSSecretController.class);
    when(secretController.getPrivateSecretValue(Mockito.<String>any())).thenReturn("");

    // Act
    dbwNetworkProfile.resolveSecrets(secretController);

    // Assert
    verify(secretController).getPrivateSecretValue("42/network-profile/null");
    verify(secretSubject).getSecretSubjectId();
  }

  /**
   * Test {@link DBWNetworkProfile#resolveSecrets(DBSSecretController)}.
   *
   * <ul>
   *   <li>Given {@link DBWNetworkProfile#DBWNetworkProfile()}.
   *   <li>Then calls {@link DBSSecretController#getPrivateSecretValue(String)}.
   * </ul>
   *
   * <p>Method under test: {@link DBWNetworkProfile#resolveSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWNetworkProfile.resolveSecrets(DBSSecretController)"})
  public void testResolveSecrets_givenDBWNetworkProfile_thenCallsGetPrivateSecretValue()
      throws DBException {
    // Arrange
    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile();

    DBSSecretController secretController = mock(DBSSecretController.class);
    when(secretController.getPrivateSecretValue(Mockito.<String>any())).thenReturn("");

    // Act
    dbwNetworkProfile.resolveSecrets(secretController);

    // Assert
    verify(secretController).getPrivateSecretValue("global/network-profile/null");
  }

  /**
   * Test {@link DBWNetworkProfile#resolveSecrets(DBSSecretController)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>Then calls {@link DBPProject#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DBWNetworkProfile#resolveSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWNetworkProfile.resolveSecrets(DBSSecretController)"})
  public void testResolveSecrets_givenEmptyString_thenCallsGetId() throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getId()).thenReturn("42");
    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile(project);

    DBSSecretController secretController = mock(DBSSecretController.class);
    when(secretController.getPrivateSecretValue(Mockito.<String>any())).thenReturn("");

    // Act
    dbwNetworkProfile.resolveSecrets(secretController);

    // Assert
    verify(project).getId();
    verify(secretController).getPrivateSecretValue("42/network-profile/null");
  }

  /**
   * Test {@link DBWNetworkProfile#resolveSecrets(DBSSecretController)}.
   *
   * <ul>
   *   <li>Then calls {@link DBSSecretSubject#getSecretSubjectId()}.
   * </ul>
   *
   * <p>Method under test: {@link DBWNetworkProfile#resolveSecrets(DBSSecretController)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWNetworkProfile.resolveSecrets(DBSSecretController)"})
  public void testResolveSecrets_thenCallsGetSecretSubjectId() throws DBException {
    // Arrange
    DBSSecretSubject secretSubject = mock(DBSSecretSubject.class);
    when(secretSubject.getSecretSubjectId()).thenReturn("42");

    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile();
    dbwNetworkProfile.setSecretSubject(secretSubject);

    DBSSecretController secretController = mock(DBSSecretController.class);
    when(secretController.getPrivateSecretValue(Mockito.<String>any())).thenReturn("");

    // Act
    dbwNetworkProfile.resolveSecrets(secretController);

    // Assert
    verify(secretController).getPrivateSecretValue("42/network-profile/null");
    verify(secretSubject).getSecretSubjectId();
  }
}
