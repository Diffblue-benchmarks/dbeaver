package org.jkiss.dbeaver.model.impl.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Properties;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPDataSourceProvider;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AuthModelDatabaseNativeDiffblueTest {
  /**
   * Test {@link AuthModelDatabaseNative#createCredentials()}.
   *
   * <p>Method under test: {@link AuthModelDatabaseNative#createCredentials()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthModelDatabaseNativeCredentials AuthModelDatabaseNative.createCredentials()"
  })
  public void testCreateCredentials() {
    // Arrange
    AuthModelDatabaseNative<AuthModelDatabaseNativeCredentials> authModelDatabaseNative =
        new AuthModelDatabaseNative<>();

    // Act
    AuthModelDatabaseNativeCredentials actualCreateCredentialsResult =
        authModelDatabaseNative.createCredentials();

    // Assert
    assertNull(actualCreateCredentialsResult.getUserName());
    assertNull(actualCreateCredentialsResult.getUserPassword());
    assertTrue(actualCreateCredentialsResult.isComplete());
  }

  /**
   * Test {@link AuthModelDatabaseNative#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)} with {@code dataSource}, {@code configuration}.
   *
   * <p>Method under test: {@link AuthModelDatabaseNative#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthModelDatabaseNativeCredentials AuthModelDatabaseNative.loadCredentials(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testLoadCredentialsWithDataSourceConfiguration() {
    // Arrange
    AuthModelDatabaseNative<AuthModelDatabaseNativeCredentials> authModelDatabaseNative =
        new AuthModelDatabaseNative<>();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(true);
    when(dbpDriver.getDataSourceProvider()).thenReturn(mock(DBPDataSourceProvider.class));

    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getDriver()).thenReturn(dbpDriver);

    // Act
    AuthModelDatabaseNativeCredentials actualLoadCredentialsResult =
        authModelDatabaseNative.loadCredentials(dataSource, new DBPConnectionConfiguration());

    // Assert
    verify(dataSource, atLeast(1)).getDriver();
    verify(dbpDriver).getDataSourceProvider();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertEquals("", actualLoadCredentialsResult.getUserPassword());
    assertNull(actualLoadCredentialsResult.getUserName());
    assertTrue(actualLoadCredentialsResult.isComplete());
  }

  /**
   * Test {@link AuthModelDatabaseNative#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration, AuthModelDatabaseNativeCredentials)} with {@code dataSource},
   * {@code configuration}, {@code credentials}.
   *
   * <p>Method under test: {@link AuthModelDatabaseNative#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration, AuthModelDatabaseNativeCredentials)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AuthModelDatabaseNative.loadCredentials(DBPDataSourceContainer, DBPConnectionConfiguration, AuthModelDatabaseNativeCredentials)"
  })
  public void testLoadCredentialsWithDataSourceConfigurationCredentials() {
    // Arrange
    AuthModelDatabaseNative<AuthModelDatabaseNativeCredentials> authModelDatabaseNative =
        new AuthModelDatabaseNative<>();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);
    when(dbpDriver.getDataSourceProvider()).thenReturn(mock(DBPDataSourceProvider.class));

    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getDriver()).thenReturn(dbpDriver);
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();

    AuthModelDatabaseNativeCredentials authModelDatabaseNativeCredentials =
        new AuthModelDatabaseNativeCredentials();
    authModelDatabaseNativeCredentials.setUserName("janedoe");
    authModelDatabaseNativeCredentials.setUserPassword("iloveyou");

    // Act
    authModelDatabaseNative.loadCredentials(
        dataSource, configuration, authModelDatabaseNativeCredentials);

    // Assert
    verify(dataSource, atLeast(1)).getDriver();
    verify(dbpDriver).getDataSourceProvider();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertNull(authModelDatabaseNativeCredentials.getUserName());
    assertNull(authModelDatabaseNativeCredentials.getUserPassword());
  }

  /**
   * Test {@link AuthModelDatabaseNative#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration, AuthModelDatabaseNativeCredentials)} with {@code dataSource},
   * {@code configuration}, {@code credentials}.
   *
   * <p>Method under test: {@link AuthModelDatabaseNative#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration, AuthModelDatabaseNativeCredentials)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AuthModelDatabaseNative.loadCredentials(DBPDataSourceContainer, DBPConnectionConfiguration, AuthModelDatabaseNativeCredentials)"
  })
  public void testLoadCredentialsWithDataSourceConfigurationCredentials2() {
    // Arrange
    AuthModelDatabaseNative<AuthModelDatabaseNativeCredentials> authModelDatabaseNative =
        new AuthModelDatabaseNative<>();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(true);
    when(dbpDriver.getDataSourceProvider()).thenReturn(mock(DBPDataSourceProvider.class));

    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getDriver()).thenReturn(dbpDriver);
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();

    AuthModelDatabaseNativeCredentials authModelDatabaseNativeCredentials =
        new AuthModelDatabaseNativeCredentials();
    authModelDatabaseNativeCredentials.setUserName("janedoe");
    authModelDatabaseNativeCredentials.setUserPassword("iloveyou");

    // Act
    authModelDatabaseNative.loadCredentials(
        dataSource, configuration, authModelDatabaseNativeCredentials);

    // Assert
    verify(dataSource, atLeast(1)).getDriver();
    verify(dbpDriver).getDataSourceProvider();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertEquals("", authModelDatabaseNativeCredentials.getUserPassword());
    assertNull(authModelDatabaseNativeCredentials.getUserName());
  }

  /**
   * Test {@link AuthModelDatabaseNative#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration, AuthModelDatabaseNativeCredentials)} with {@code dataSource},
   * {@code configuration}, {@code credentials}.
   *
   * <p>Method under test: {@link AuthModelDatabaseNative#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration, AuthModelDatabaseNativeCredentials)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AuthModelDatabaseNative.loadCredentials(DBPDataSourceContainer, DBPConnectionConfiguration, AuthModelDatabaseNativeCredentials)"
  })
  public void testLoadCredentialsWithDataSourceConfigurationCredentials3() {
    // Arrange
    AuthModelDatabaseNative<AuthModelDatabaseNativeCredentials> authModelDatabaseNative =
        new AuthModelDatabaseNative<>();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);
    when(dbpDriver.getDataSourceProvider()).thenReturn(mock(DBPDataSourceProvider.class));

    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getDriver()).thenReturn(dbpDriver);

    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setUserPassword("iloveyou");

    AuthModelDatabaseNativeCredentials authModelDatabaseNativeCredentials =
        new AuthModelDatabaseNativeCredentials();
    authModelDatabaseNativeCredentials.setUserName("janedoe");
    authModelDatabaseNativeCredentials.setUserPassword("iloveyou");

    // Act
    authModelDatabaseNative.loadCredentials(
        dataSource, configuration, authModelDatabaseNativeCredentials);

    // Assert
    verify(dataSource, atLeast(1)).getDriver();
    verify(dbpDriver).getDataSourceProvider();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertEquals("iloveyou", authModelDatabaseNativeCredentials.getUserPassword());
    assertNull(authModelDatabaseNativeCredentials.getUserName());
  }

  /**
   * Test {@link AuthModelDatabaseNative#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)} with {@code dataSource}, {@code configuration}.
   *
   * <ul>
   *   <li>Then return UserPassword is {@code iloveyou}.
   * </ul>
   *
   * <p>Method under test: {@link AuthModelDatabaseNative#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthModelDatabaseNativeCredentials AuthModelDatabaseNative.loadCredentials(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testLoadCredentialsWithDataSourceConfiguration_thenReturnUserPasswordIsIloveyou() {
    // Arrange
    AuthModelDatabaseNative<AuthModelDatabaseNativeCredentials> authModelDatabaseNative =
        new AuthModelDatabaseNative<>();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);
    when(dbpDriver.getDataSourceProvider()).thenReturn(mock(DBPDataSourceProvider.class));

    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getDriver()).thenReturn(dbpDriver);

    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setUserPassword("iloveyou");

    // Act
    AuthModelDatabaseNativeCredentials actualLoadCredentialsResult =
        authModelDatabaseNative.loadCredentials(dataSource, configuration);

    // Assert
    verify(dataSource, atLeast(1)).getDriver();
    verify(dbpDriver).getDataSourceProvider();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertEquals("iloveyou", actualLoadCredentialsResult.getUserPassword());
    assertNull(actualLoadCredentialsResult.getUserName());
    assertTrue(actualLoadCredentialsResult.isComplete());
  }

  /**
   * Test {@link AuthModelDatabaseNative#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)} with {@code dataSource}, {@code configuration}.
   *
   * <ul>
   *   <li>Then return UserPassword is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AuthModelDatabaseNative#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthModelDatabaseNativeCredentials AuthModelDatabaseNative.loadCredentials(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testLoadCredentialsWithDataSourceConfiguration_thenReturnUserPasswordIsNull() {
    // Arrange
    AuthModelDatabaseNative<AuthModelDatabaseNativeCredentials> authModelDatabaseNative =
        new AuthModelDatabaseNative<>();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);
    when(dbpDriver.getDataSourceProvider()).thenReturn(mock(DBPDataSourceProvider.class));

    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getDriver()).thenReturn(dbpDriver);

    // Act
    AuthModelDatabaseNativeCredentials actualLoadCredentialsResult =
        authModelDatabaseNative.loadCredentials(dataSource, new DBPConnectionConfiguration());

    // Assert
    verify(dataSource, atLeast(1)).getDriver();
    verify(dbpDriver).getDataSourceProvider();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertNull(actualLoadCredentialsResult.getUserName());
    assertNull(actualLoadCredentialsResult.getUserPassword());
    assertTrue(actualLoadCredentialsResult.isComplete());
  }

  /**
   * Test {@link AuthModelDatabaseNative#saveCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration, AuthModelDatabaseNativeCredentials)} with {@code
   * DBPDataSourceContainer}, {@code DBPConnectionConfiguration}, {@code
   * AuthModelDatabaseNativeCredentials}.
   *
   * <p>Method under test: {@link AuthModelDatabaseNative#saveCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration, AuthModelDatabaseNativeCredentials)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AuthModelDatabaseNative.saveCredentials(DBPDataSourceContainer, DBPConnectionConfiguration, AuthModelDatabaseNativeCredentials)"
  })
  public void
      testSaveCredentialsWithDBPDataSourceContainerDBPConnectionConfigurationAuthModelDatabaseNativeCredentials() {
    // Arrange
    AuthModelDatabaseNative<AuthModelDatabaseNativeCredentials> authModelDatabaseNative =
        new AuthModelDatabaseNative<>();
    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();

    AuthModelDatabaseNativeCredentials authModelDatabaseNativeCredentials =
        new AuthModelDatabaseNativeCredentials();
    authModelDatabaseNativeCredentials.setUserName("janedoe");
    authModelDatabaseNativeCredentials.setUserPassword("iloveyou");

    // Act
    authModelDatabaseNative.saveCredentials(
        dataSource, configuration, authModelDatabaseNativeCredentials);

    // Assert
    assertEquals("iloveyou", configuration.getUserPassword());
    assertEquals("janedoe", configuration.getUserName());
  }

  /**
   * Test {@link AuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelDatabaseNativeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link AuthModelDatabaseNative#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object AuthModelDatabaseNative.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties()
          throws DBException {
    // Arrange
    AuthModelDatabaseNative<AuthModelDatabaseNativeCredentials> authModelDatabaseNative =
        new AuthModelDatabaseNative<>();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelDatabaseNativeCredentials authModelDatabaseNativeCredentials =
        new AuthModelDatabaseNativeCredentials();
    authModelDatabaseNativeCredentials.setUserName("janedoe");
    authModelDatabaseNativeCredentials.setUserPassword("iloveyou");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connectProps = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        authModelDatabaseNative.initAuthentication(
            monitor, dataSource, authModelDatabaseNativeCredentials, configuration, connectProps);

    // Assert
    assertTrue(actualInitAuthenticationResult instanceof AuthModelDatabaseNativeCredentials);
    assertEquals(2, connectProps.size());
    assertEquals(
        "iloveyou",
        ((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserPassword());
    assertEquals("iloveyou", connectProps.get("password"));
    assertEquals(
        "janedoe",
        ((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals("janedoe", connectProps.get("user"));
  }

  /**
   * Test {@link AuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelDatabaseNativeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link AuthModelDatabaseNative#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object AuthModelDatabaseNative.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties2()
          throws DBException {
    // Arrange
    AuthModelDatabaseNative<AuthModelDatabaseNativeCredentials> authModelDatabaseNative =
        new AuthModelDatabaseNative<>();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelDatabaseNativeCredentials authModelDatabaseNativeCredentials =
        new AuthModelDatabaseNativeCredentials();
    authModelDatabaseNativeCredentials.setUserName("");
    authModelDatabaseNativeCredentials.setUserPassword("iloveyou");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connectProps = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        authModelDatabaseNative.initAuthentication(
            monitor, dataSource, authModelDatabaseNativeCredentials, configuration, connectProps);

    // Assert
    assertTrue(actualInitAuthenticationResult instanceof AuthModelDatabaseNativeCredentials);
    assertEquals(1, connectProps.size());
    assertEquals(
        "", ((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals(
        "iloveyou",
        ((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserPassword());
    assertEquals("iloveyou", connectProps.get("password"));
  }

  /**
   * Test {@link AuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelDatabaseNativeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link AuthModelDatabaseNative#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object AuthModelDatabaseNative.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties3()
          throws DBException {
    // Arrange
    AuthModelDatabaseNative<AuthModelDatabaseNativeCredentials> authModelDatabaseNative =
        new AuthModelDatabaseNative<>();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelDatabaseNativeCredentials authModelDatabaseNativeCredentials =
        new AuthModelDatabaseNativeCredentials();
    authModelDatabaseNativeCredentials.setUserName(null);
    authModelDatabaseNativeCredentials.setUserPassword("iloveyou");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connectProps = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        authModelDatabaseNative.initAuthentication(
            monitor, dataSource, authModelDatabaseNativeCredentials, configuration, connectProps);

    // Assert
    assertTrue(actualInitAuthenticationResult instanceof AuthModelDatabaseNativeCredentials);
    assertEquals(1, connectProps.size());
    assertEquals(
        "iloveyou",
        ((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserPassword());
    assertEquals("iloveyou", connectProps.get("password"));
    assertNull(((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserName());
  }

  /**
   * Test {@link AuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelDatabaseNativeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link AuthModelDatabaseNative#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object AuthModelDatabaseNative.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties4()
          throws DBException {
    // Arrange
    AuthModelDatabaseNative<AuthModelDatabaseNativeCredentials> authModelDatabaseNative =
        new AuthModelDatabaseNative<>();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    AuthModelDatabaseNativeCredentials authModelDatabaseNativeCredentials =
        new AuthModelDatabaseNativeCredentials();
    authModelDatabaseNativeCredentials.setUserName("janedoe");
    authModelDatabaseNativeCredentials.setUserPassword("");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connectProps = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        authModelDatabaseNative.initAuthentication(
            monitor, dataSource, authModelDatabaseNativeCredentials, configuration, connectProps);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof AuthModelDatabaseNativeCredentials);
    assertEquals(1, connectProps.size());
    assertEquals(
        "",
        ((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserPassword());
    assertEquals(
        "janedoe",
        ((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals("janedoe", connectProps.get("user"));
  }

  /**
   * Test {@link AuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelDatabaseNativeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link AuthModelDatabaseNative#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object AuthModelDatabaseNative.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties5()
          throws DBException {
    // Arrange
    AuthModelDatabaseNative<AuthModelDatabaseNativeCredentials> authModelDatabaseNative =
        new AuthModelDatabaseNative<>();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    AuthModelDatabaseNativeCredentials authModelDatabaseNativeCredentials =
        new AuthModelDatabaseNativeCredentials();
    authModelDatabaseNativeCredentials.setUserName("janedoe");
    authModelDatabaseNativeCredentials.setUserPassword("");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connectProps = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        authModelDatabaseNative.initAuthentication(
            monitor, dataSource, authModelDatabaseNativeCredentials, configuration, connectProps);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof AuthModelDatabaseNativeCredentials);
    assertEquals(2, connectProps.size());
    assertEquals(
        "",
        ((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserPassword());
    assertEquals("", connectProps.get("password"));
    assertEquals(
        "janedoe",
        ((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals("janedoe", connectProps.get("user"));
  }

  /**
   * Test {@link AuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelDatabaseNativeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link AuthModelDatabaseNative#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object AuthModelDatabaseNative.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties6()
          throws DBException {
    // Arrange
    AuthModelDatabaseNative<AuthModelDatabaseNativeCredentials> authModelDatabaseNative =
        new AuthModelDatabaseNative<>();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    AuthModelDatabaseNativeCredentials authModelDatabaseNativeCredentials =
        new AuthModelDatabaseNativeCredentials();
    authModelDatabaseNativeCredentials.setUserName("");
    authModelDatabaseNativeCredentials.setUserPassword("");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connectProps = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        authModelDatabaseNative.initAuthentication(
            monitor, dataSource, authModelDatabaseNativeCredentials, configuration, connectProps);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof AuthModelDatabaseNativeCredentials);
    assertEquals(
        "", ((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals(
        "",
        ((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserPassword());
    assertTrue(connectProps.isEmpty());
  }

  /**
   * Test {@link AuthModelDatabaseNative#isUserNameNeeded(DBPDataSource)}.
   *
   * <p>Method under test: {@link AuthModelDatabaseNative#isUserNameNeeded(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AuthModelDatabaseNative.isUserNameNeeded(DBPDataSource)"})
  public void testIsUserNameNeeded() {
    // Arrange
    AuthModelDatabaseNative<AuthModelDatabaseNativeCredentials> authModelDatabaseNative =
        new AuthModelDatabaseNative<>();

    // Act and Assert
    assertTrue(authModelDatabaseNative.isUserNameNeeded(mock(DBPDataSource.class)));
  }

  /**
   * Test {@link AuthModelDatabaseNative#isUserPasswordNeeded(DBPDataSource)}.
   *
   * <p>Method under test: {@link AuthModelDatabaseNative#isUserPasswordNeeded(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AuthModelDatabaseNative.isUserPasswordNeeded(DBPDataSource)"})
  public void testIsUserPasswordNeeded() {
    // Arrange
    AuthModelDatabaseNative<AuthModelDatabaseNativeCredentials> authModelDatabaseNative =
        new AuthModelDatabaseNative<>();

    // Act and Assert
    assertTrue(authModelDatabaseNative.isUserPasswordNeeded(mock(DBPDataSource.class)));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link AuthModelDatabaseNative}
   *   <li>{@link AuthModelDatabaseNative#endAuthentication(DBPDataSourceContainer,
   *       DBPConnectionConfiguration, Properties)}
   *   <li>{@link AuthModelDatabaseNative#refreshCredentials(DBRProgressMonitor,
   *       DBPDataSourceContainer, DBPConnectionConfiguration, AuthModelDatabaseNativeCredentials)}
   *   <li>{@link AuthModelDatabaseNative#isUserNameApplicable()}
   *   <li>{@link AuthModelDatabaseNative#isUserPasswordApplicable()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AuthModelDatabaseNative.<init>()",
    "void AuthModelDatabaseNative.endAuthentication(DBPDataSourceContainer, DBPConnectionConfiguration, Properties)",
    "boolean AuthModelDatabaseNative.isUserNameApplicable()",
    "boolean AuthModelDatabaseNative.isUserPasswordApplicable()",
    "void AuthModelDatabaseNative.refreshCredentials(DBRProgressMonitor, DBPDataSourceContainer, DBPConnectionConfiguration, AuthModelDatabaseNativeCredentials)"
  })
  public void testGettersAndSetters() throws DBException {
    // Arrange and Act
    AuthModelDatabaseNative<AuthModelDatabaseNativeCredentials> actualAuthModelDatabaseNative =
        new AuthModelDatabaseNative<>();
    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    actualAuthModelDatabaseNative.endAuthentication(dataSource, configuration, new Properties());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSource2 = mock(DBPDataSourceContainer.class);
    DBPConnectionConfiguration configuration2 = new DBPConnectionConfiguration();
    AuthModelDatabaseNativeCredentials authModelDatabaseNativeCredentials =
        new AuthModelDatabaseNativeCredentials();
    authModelDatabaseNativeCredentials.setUserName("janedoe");
    authModelDatabaseNativeCredentials.setUserPassword("iloveyou");
    actualAuthModelDatabaseNative.refreshCredentials(
        monitor, dataSource2, configuration2, authModelDatabaseNativeCredentials);
    boolean actualIsUserNameApplicableResult = actualAuthModelDatabaseNative.isUserNameApplicable();

    // Assert
    assertTrue(actualIsUserNameApplicableResult);
    assertTrue(actualAuthModelDatabaseNative.isUserPasswordApplicable());
  }
}
