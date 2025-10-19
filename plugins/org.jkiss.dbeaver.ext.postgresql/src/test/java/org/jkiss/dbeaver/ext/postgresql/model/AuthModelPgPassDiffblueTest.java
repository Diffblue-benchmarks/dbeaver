package org.jkiss.dbeaver.ext.postgresql.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import java.util.Properties;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPDataSourceProvider;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.net.DBWHandlerConfiguration;
import org.jkiss.dbeaver.model.net.DBWHandlerDescriptor;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AuthModelPgPassDiffblueTest {
  /**
   * Test {@link AuthModelPgPass#createCredentials()}.
   *
   * <p>Method under test: {@link AuthModelPgPass#createCredentials()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AuthModelPgPassCredentials AuthModelPgPass.createCredentials()"})
  public void testCreateCredentials() {
    // Arrange and Act
    AuthModelPgPassCredentials actualCreateCredentialsResult =
        new AuthModelPgPass().createCredentials();

    // Assert
    assertNull(actualCreateCredentialsResult.getParseError());
    assertNull(actualCreateCredentialsResult.getUserPassword());
    assertNull(actualCreateCredentialsResult.getUserName());
    assertTrue(actualCreateCredentialsResult.isComplete());
  }

  /**
   * Test {@link AuthModelPgPass#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)} with {@code dataSource}, {@code configuration}.
   *
   * <p>Method under test: {@link AuthModelPgPass#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthModelPgPassCredentials AuthModelPgPass.loadCredentials(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testLoadCredentialsWithDataSourceConfiguration() {
    // Arrange
    AuthModelPgPass authModelPgPass = new AuthModelPgPass();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(true);
    when(dbpDriver.getDataSourceProvider()).thenReturn(mock(DBPDataSourceProvider.class));

    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getActualConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());
    when(dataSource.getConnectionConfiguration()).thenReturn(new DBPConnectionConfiguration());
    when(dataSource.getDriver()).thenReturn(dbpDriver);

    // Act
    AuthModelPgPassCredentials actualLoadCredentialsResult =
        authModelPgPass.loadCredentials(dataSource, new DBPConnectionConfiguration());

    // Assert
    verify(dataSource).getActualConnectionConfiguration();
    verify(dataSource, atLeast(1)).getConnectionConfiguration();
    verify(dataSource, atLeast(1)).getDriver();
    verify(dbpDriver).getDataSourceProvider();
    verify(dbpDriver).isAllowsEmptyPassword();
    Exception parseError = actualLoadCredentialsResult.getParseError();
    assertTrue(parseError instanceof DBException);
    assertEquals("", actualLoadCredentialsResult.getUserPassword());
    assertNull(actualLoadCredentialsResult.getUserName());
    assertNull(parseError.getCause());
    assertEquals(0, parseError.getSuppressed().length);
    assertTrue(actualLoadCredentialsResult.isComplete());
    String expectedLocalizedMessage =
        String.join(
            "",
            "PgPass file '",
            Paths.get(System.getProperty("user.home"), ".pgpass").toString(),
            "' not found");
    assertEquals(expectedLocalizedMessage, parseError.getLocalizedMessage());
    String expectedMessage =
        String.join(
            "",
            "PgPass file '",
            Paths.get(System.getProperty("user.home"), ".pgpass").toString(),
            "' not found");
    assertEquals(expectedMessage, parseError.getMessage());
  }

  /**
   * Test {@link AuthModelPgPass#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)} with {@code dataSource}, {@code configuration}.
   *
   * <p>Method under test: {@link AuthModelPgPass#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthModelPgPassCredentials AuthModelPgPass.loadCredentials(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testLoadCredentialsWithDataSourceConfiguration2() {
    // Arrange
    AuthModelPgPass authModelPgPass = new AuthModelPgPass();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);
    when(dbpDriver.getDataSourceProvider()).thenReturn(mock(DBPDataSourceProvider.class));

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration handler =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.updateHandler(handler);

    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getActualConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);
    when(dataSource.getConnectionConfiguration()).thenReturn(new DBPConnectionConfiguration());
    when(dataSource.getDriver()).thenReturn(dbpDriver);

    // Act
    AuthModelPgPassCredentials actualLoadCredentialsResult =
        authModelPgPass.loadCredentials(dataSource, new DBPConnectionConfiguration());

    // Assert
    verify(dataSource).getActualConnectionConfiguration();
    verify(dataSource, atLeast(1)).getConnectionConfiguration();
    verify(dataSource, atLeast(1)).getDriver();
    verify(dbpDriver).getDataSourceProvider();
    verify(dbpDriver).isAllowsEmptyPassword();
    verify(descriptor).getId();
    Exception parseError = actualLoadCredentialsResult.getParseError();
    assertTrue(parseError instanceof DBException);
    assertNull(actualLoadCredentialsResult.getUserPassword());
    assertNull(actualLoadCredentialsResult.getUserName());
    assertNull(parseError.getCause());
    assertEquals(0, parseError.getSuppressed().length);
    assertTrue(actualLoadCredentialsResult.isComplete());
    String expectedLocalizedMessage =
        String.join(
            "",
            "PgPass file '",
            Paths.get(System.getProperty("user.home"), ".pgpass").toString(),
            "' not found");
    assertEquals(expectedLocalizedMessage, parseError.getLocalizedMessage());
    String expectedMessage =
        String.join(
            "",
            "PgPass file '",
            Paths.get(System.getProperty("user.home"), ".pgpass").toString(),
            "' not found");
    assertEquals(expectedMessage, parseError.getMessage());
  }

  /**
   * Test {@link AuthModelPgPass#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)} with {@code dataSource}, {@code configuration}.
   *
   * <p>Method under test: {@link AuthModelPgPass#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthModelPgPassCredentials AuthModelPgPass.loadCredentials(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testLoadCredentialsWithDataSourceConfiguration3() {
    // Arrange
    AuthModelPgPass authModelPgPass = new AuthModelPgPass();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);
    when(dbpDriver.getDataSourceProvider()).thenReturn(mock(DBPDataSourceProvider.class));

    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.setHostName("Host Name");

    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);
    when(dataSource.getDriver()).thenReturn(dbpDriver);

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));
    DBWHandlerConfiguration handler = new DBWHandlerConfiguration(configuration);
    new DBPConnectionConfiguration().updateHandler(handler);

    // Act
    AuthModelPgPassCredentials actualLoadCredentialsResult =
        authModelPgPass.loadCredentials(dataSource, new DBPConnectionConfiguration());

    // Assert
    verify(dataSource, atLeast(1)).getConnectionConfiguration();
    verify(dataSource, atLeast(1)).getDriver();
    verify(dbpDriver).getDataSourceProvider();
    verify(dbpDriver).isAllowsEmptyPassword();
    verify(descriptor).getId();
    Exception parseError = actualLoadCredentialsResult.getParseError();
    assertTrue(parseError instanceof DBException);
    assertNull(actualLoadCredentialsResult.getUserPassword());
    assertNull(actualLoadCredentialsResult.getUserName());
    assertNull(parseError.getCause());
    assertEquals(0, parseError.getSuppressed().length);
    assertTrue(actualLoadCredentialsResult.isComplete());
    String expectedLocalizedMessage =
        String.join(
            "",
            "PgPass file '",
            Paths.get(System.getProperty("user.home"), ".pgpass").toString(),
            "' not found");
    assertEquals(expectedLocalizedMessage, parseError.getLocalizedMessage());
    String expectedMessage =
        String.join(
            "",
            "PgPass file '",
            Paths.get(System.getProperty("user.home"), ".pgpass").toString(),
            "' not found");
    assertEquals(expectedMessage, parseError.getMessage());
  }

  /**
   * Test {@link AuthModelPgPass#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)} with {@code dataSource}, {@code configuration}.
   *
   * <ul>
   *   <li>Then return UserPassword is {@code iloveyou}.
   * </ul>
   *
   * <p>Method under test: {@link AuthModelPgPass#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthModelPgPassCredentials AuthModelPgPass.loadCredentials(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testLoadCredentialsWithDataSourceConfiguration_thenReturnUserPasswordIsIloveyou() {
    // Arrange
    AuthModelPgPass authModelPgPass = new AuthModelPgPass();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);
    when(dbpDriver.getDataSourceProvider()).thenReturn(mock(DBPDataSourceProvider.class));

    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getActualConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());
    when(dataSource.getConnectionConfiguration()).thenReturn(new DBPConnectionConfiguration());
    when(dataSource.getDriver()).thenReturn(dbpDriver);

    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setUserPassword("iloveyou");

    // Act
    AuthModelPgPassCredentials actualLoadCredentialsResult =
        authModelPgPass.loadCredentials(dataSource, configuration);

    // Assert
    verify(dataSource).getActualConnectionConfiguration();
    verify(dataSource, atLeast(1)).getConnectionConfiguration();
    verify(dataSource, atLeast(1)).getDriver();
    verify(dbpDriver).getDataSourceProvider();
    verify(dbpDriver).isAllowsEmptyPassword();
    Exception parseError = actualLoadCredentialsResult.getParseError();
    assertTrue(parseError instanceof DBException);
    assertEquals("iloveyou", actualLoadCredentialsResult.getUserPassword());
    assertNull(actualLoadCredentialsResult.getUserName());
    assertNull(parseError.getCause());
    assertEquals(0, parseError.getSuppressed().length);
    assertTrue(actualLoadCredentialsResult.isComplete());
    String expectedLocalizedMessage =
        String.join(
            "",
            "PgPass file '",
            Paths.get(System.getProperty("user.home"), ".pgpass").toString(),
            "' not found");
    assertEquals(expectedLocalizedMessage, parseError.getLocalizedMessage());
    String expectedMessage =
        String.join(
            "",
            "PgPass file '",
            Paths.get(System.getProperty("user.home"), ".pgpass").toString(),
            "' not found");
    assertEquals(expectedMessage, parseError.getMessage());
  }

  /**
   * Test {@link AuthModelPgPass#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)} with {@code dataSource}, {@code configuration}.
   *
   * <ul>
   *   <li>Then return UserPassword is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AuthModelPgPass#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthModelPgPassCredentials AuthModelPgPass.loadCredentials(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testLoadCredentialsWithDataSourceConfiguration_thenReturnUserPasswordIsNull() {
    // Arrange
    AuthModelPgPass authModelPgPass = new AuthModelPgPass();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);
    when(dbpDriver.getDataSourceProvider()).thenReturn(mock(DBPDataSourceProvider.class));

    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getActualConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());
    when(dataSource.getConnectionConfiguration()).thenReturn(new DBPConnectionConfiguration());
    when(dataSource.getDriver()).thenReturn(dbpDriver);

    // Act
    AuthModelPgPassCredentials actualLoadCredentialsResult =
        authModelPgPass.loadCredentials(dataSource, new DBPConnectionConfiguration());

    // Assert
    verify(dataSource).getActualConnectionConfiguration();
    verify(dataSource, atLeast(1)).getConnectionConfiguration();
    verify(dataSource, atLeast(1)).getDriver();
    verify(dbpDriver).getDataSourceProvider();
    verify(dbpDriver).isAllowsEmptyPassword();
    Exception parseError = actualLoadCredentialsResult.getParseError();
    assertTrue(parseError instanceof DBException);
    assertNull(actualLoadCredentialsResult.getUserPassword());
    assertNull(actualLoadCredentialsResult.getUserName());
    assertNull(parseError.getCause());
    assertEquals(0, parseError.getSuppressed().length);
    assertTrue(actualLoadCredentialsResult.isComplete());
    String expectedLocalizedMessage =
        String.join(
            "",
            "PgPass file '",
            Paths.get(System.getProperty("user.home"), ".pgpass").toString(),
            "' not found");
    assertEquals(expectedLocalizedMessage, parseError.getLocalizedMessage());
    String expectedMessage =
        String.join(
            "",
            "PgPass file '",
            Paths.get(System.getProperty("user.home"), ".pgpass").toString(),
            "' not found");
    assertEquals(expectedMessage, parseError.getMessage());
  }

  /**
   * Test {@link AuthModelPgPass#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelPgPassCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelPgPassCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link AuthModelPgPass#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelPgPassCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object AuthModelPgPass.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelPgPassCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelPgPassCredentialsDBPConnectionConfigurationProperties()
          throws DBException {
    // Arrange
    AuthModelPgPass authModelPgPass = new AuthModelPgPass();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelPgPassCredentials credentials = new AuthModelPgPassCredentials();
    credentials.setParseError(new Exception());
    credentials.setUserName("janedoe");
    credentials.setUserPassword("iloveyou");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            authModelPgPass.initAuthentication(
                monitor, dataSource, credentials, configuration, new Properties()));
  }

  /**
   * Test {@link AuthModelPgPass#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelPgPassCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelPgPassCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link AuthModelPgPass#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelPgPassCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object AuthModelPgPass.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelPgPassCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelPgPassCredentialsDBPConnectionConfigurationProperties2()
          throws DBException {
    // Arrange
    AuthModelPgPass authModelPgPass = new AuthModelPgPass();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelPgPassCredentials credentials = new AuthModelPgPassCredentials();
    credentials.setParseError(null);
    credentials.setUserName("janedoe");
    credentials.setUserPassword("iloveyou");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connectProps = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        authModelPgPass.initAuthentication(
            monitor, dataSource, credentials, configuration, connectProps);

    // Assert
    assertTrue(actualInitAuthenticationResult instanceof AuthModelPgPassCredentials);
    assertEquals(2, connectProps.size());
    assertEquals(
        "iloveyou",
        ((AuthModelPgPassCredentials) actualInitAuthenticationResult).getUserPassword());
    assertEquals("iloveyou", connectProps.get("password"));
    assertEquals(
        "janedoe", ((AuthModelPgPassCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals("janedoe", connectProps.get("user"));
  }

  /**
   * Test {@link AuthModelPgPass#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelPgPassCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelPgPassCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link AuthModelPgPass#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelPgPassCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object AuthModelPgPass.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelPgPassCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelPgPassCredentialsDBPConnectionConfigurationProperties3()
          throws DBException {
    // Arrange
    AuthModelPgPass authModelPgPass = new AuthModelPgPass();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelPgPassCredentials credentials = new AuthModelPgPassCredentials();
    credentials.setParseError(null);
    credentials.setUserName(null);
    credentials.setUserPassword("iloveyou");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connectProps = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        authModelPgPass.initAuthentication(
            monitor, dataSource, credentials, configuration, connectProps);

    // Assert
    assertTrue(actualInitAuthenticationResult instanceof AuthModelPgPassCredentials);
    assertEquals(1, connectProps.size());
    assertEquals(
        "iloveyou",
        ((AuthModelPgPassCredentials) actualInitAuthenticationResult).getUserPassword());
    assertEquals("iloveyou", connectProps.get("password"));
    assertNull(((AuthModelPgPassCredentials) actualInitAuthenticationResult).getUserName());
  }

  /**
   * Test {@link AuthModelPgPass#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelPgPassCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelPgPassCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link AuthModelPgPass#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelPgPassCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object AuthModelPgPass.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelPgPassCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelPgPassCredentialsDBPConnectionConfigurationProperties4()
          throws DBException {
    // Arrange
    AuthModelPgPass authModelPgPass = new AuthModelPgPass();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelPgPassCredentials credentials = new AuthModelPgPassCredentials();
    credentials.setParseError(null);
    credentials.setUserName("");
    credentials.setUserPassword("iloveyou");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connectProps = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        authModelPgPass.initAuthentication(
            monitor, dataSource, credentials, configuration, connectProps);

    // Assert
    assertTrue(actualInitAuthenticationResult instanceof AuthModelPgPassCredentials);
    assertEquals(1, connectProps.size());
    assertEquals("", ((AuthModelPgPassCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals(
        "iloveyou",
        ((AuthModelPgPassCredentials) actualInitAuthenticationResult).getUserPassword());
    assertEquals("iloveyou", connectProps.get("password"));
  }

  /**
   * Test {@link AuthModelPgPass#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelPgPassCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelPgPassCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link AuthModelPgPass#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelPgPassCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object AuthModelPgPass.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelPgPassCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelPgPassCredentialsDBPConnectionConfigurationProperties5()
          throws DBException {
    // Arrange
    AuthModelPgPass authModelPgPass = new AuthModelPgPass();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    AuthModelPgPassCredentials credentials = new AuthModelPgPassCredentials();
    credentials.setParseError(null);
    credentials.setUserName("janedoe");
    credentials.setUserPassword(null);
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connectProps = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        authModelPgPass.initAuthentication(
            monitor, dataSource, credentials, configuration, connectProps);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof AuthModelPgPassCredentials);
    assertEquals(1, connectProps.size());
    assertEquals(
        "janedoe", ((AuthModelPgPassCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals("janedoe", connectProps.get("user"));
    assertNull(((AuthModelPgPassCredentials) actualInitAuthenticationResult).getUserPassword());
  }

  /**
   * Test {@link AuthModelPgPass#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelPgPassCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelPgPassCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link AuthModelPgPass#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelPgPassCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object AuthModelPgPass.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelPgPassCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelPgPassCredentialsDBPConnectionConfigurationProperties6()
          throws DBException {
    // Arrange
    AuthModelPgPass authModelPgPass = new AuthModelPgPass();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    AuthModelPgPassCredentials credentials = new AuthModelPgPassCredentials();
    credentials.setParseError(null);
    credentials.setUserName(null);
    credentials.setUserPassword(null);
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connectProps = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        authModelPgPass.initAuthentication(
            monitor, dataSource, credentials, configuration, connectProps);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof AuthModelPgPassCredentials);
    assertNull(((AuthModelPgPassCredentials) actualInitAuthenticationResult).getUserPassword());
    assertNull(((AuthModelPgPassCredentials) actualInitAuthenticationResult).getUserName());
    assertTrue(connectProps.isEmpty());
  }

  /**
   * Test {@link AuthModelPgPass#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelPgPassCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelPgPassCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link AuthModelPgPass#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelPgPassCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object AuthModelPgPass.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelPgPassCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelPgPassCredentialsDBPConnectionConfigurationProperties7()
          throws DBException {
    // Arrange
    AuthModelPgPass authModelPgPass = new AuthModelPgPass();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    AuthModelPgPassCredentials credentials = new AuthModelPgPassCredentials();
    credentials.setParseError(null);
    credentials.setUserName("janedoe");
    credentials.setUserPassword("");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connectProps = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        authModelPgPass.initAuthentication(
            monitor, dataSource, credentials, configuration, connectProps);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof AuthModelPgPassCredentials);
    assertEquals(2, connectProps.size());
    assertEquals(
        "", ((AuthModelPgPassCredentials) actualInitAuthenticationResult).getUserPassword());
    assertEquals("", connectProps.get("password"));
    assertEquals(
        "janedoe", ((AuthModelPgPassCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals("janedoe", connectProps.get("user"));
  }

  /**
   * Test new {@link AuthModelPgPass} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link AuthModelPgPass}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AuthModelPgPass.<init>()"})
  public void testNewAuthModelPgPass() {
    // Arrange and Act
    AuthModelPgPass actualAuthModelPgPass = new AuthModelPgPass();

    // Assert
    assertTrue(actualAuthModelPgPass.isUserNameApplicable());
    assertTrue(actualAuthModelPgPass.isUserPasswordApplicable());
  }
}
