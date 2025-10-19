package org.jkiss.dbeaver.ext.snowflake.model.auth;

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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPDataSourceProvider;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.jkiss.dbeaver.model.impl.auth.AuthModelDatabaseNativeCredentials;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SnowflakeAuthModelSnowflakeDiffblueTest {
  /**
   * Test {@link SnowflakeAuthModelSnowflake#createCredentials()}.
   *
   * <p>Method under test: {@link SnowflakeAuthModelSnowflake#createCredentials()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthModelSnowflakeCredentials SnowflakeAuthModelSnowflake.createCredentials()"
  })
  public void testCreateCredentials() {
    // Arrange
    SnowflakeAuthModelSnowflake<AuthModelSnowflakeCredentials> snowflakeAuthModelSnowflake =
        new SnowflakeAuthModelSnowflake<>();

    // Act
    AuthModelSnowflakeCredentials actualCreateCredentialsResult =
        snowflakeAuthModelSnowflake.createCredentials();

    // Assert
    assertNull(actualCreateCredentialsResult.getRole());
    assertNull(actualCreateCredentialsResult.getUserName());
    assertNull(actualCreateCredentialsResult.getUserPassword());
    assertTrue(actualCreateCredentialsResult.isComplete());
  }

  /**
   * Test {@link SnowflakeAuthModelSnowflake#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelSnowflakeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link SnowflakeAuthModelSnowflake#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SnowflakeAuthModelSnowflake.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelSnowflakeCredentialsDBPConnectionConfigurationProperties()
          throws DBException {
    // Arrange
    SnowflakeAuthModelSnowflake<AuthModelSnowflakeCredentials> snowflakeAuthModelSnowflake =
        new SnowflakeAuthModelSnowflake<>();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelSnowflakeCredentials authModelSnowflakeCredentials =
        new AuthModelSnowflakeCredentials();
    authModelSnowflakeCredentials.setRole("Role");
    authModelSnowflakeCredentials.setUserName("janedoe");
    authModelSnowflakeCredentials.setUserPassword("iloveyou");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        snowflakeAuthModelSnowflake.initAuthentication(
            monitor, dataSource, authModelSnowflakeCredentials, configuration, connProperties);

    // Assert
    assertTrue(actualInitAuthenticationResult instanceof AuthModelSnowflakeCredentials);
    assertEquals(2, connProperties.size());
    assertEquals(
        "iloveyou",
        ((AuthModelSnowflakeCredentials) actualInitAuthenticationResult).getUserPassword());
    assertEquals("iloveyou", connProperties.get("password"));
    assertTrue(connProperties.containsKey("user"));
  }

  /**
   * Test {@link SnowflakeAuthModelSnowflake#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelSnowflakeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link SnowflakeAuthModelSnowflake#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SnowflakeAuthModelSnowflake.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelSnowflakeCredentialsDBPConnectionConfigurationProperties2()
          throws DBException {
    // Arrange
    SnowflakeAuthModelSnowflake<AuthModelSnowflakeCredentials> snowflakeAuthModelSnowflake =
        new SnowflakeAuthModelSnowflake<>();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelSnowflakeCredentials authModelSnowflakeCredentials =
        new AuthModelSnowflakeCredentials();
    authModelSnowflakeCredentials.setRole("Role");
    authModelSnowflakeCredentials.setUserName(null);
    authModelSnowflakeCredentials.setUserPassword("iloveyou");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        snowflakeAuthModelSnowflake.initAuthentication(
            monitor, dataSource, authModelSnowflakeCredentials, configuration, connProperties);

    // Assert
    assertTrue(actualInitAuthenticationResult instanceof AuthModelSnowflakeCredentials);
    assertEquals(1, connProperties.size());
    assertEquals(
        "iloveyou",
        ((AuthModelSnowflakeCredentials) actualInitAuthenticationResult).getUserPassword());
    assertEquals("iloveyou", connProperties.get("password"));
    assertNull(((AuthModelSnowflakeCredentials) actualInitAuthenticationResult).getUserName());
  }

  /**
   * Test {@link SnowflakeAuthModelSnowflake#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelSnowflakeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link SnowflakeAuthModelSnowflake#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SnowflakeAuthModelSnowflake.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelSnowflakeCredentialsDBPConnectionConfigurationProperties3()
          throws DBException {
    // Arrange
    SnowflakeAuthModelSnowflake<AuthModelSnowflakeCredentials> snowflakeAuthModelSnowflake =
        new SnowflakeAuthModelSnowflake<>();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelSnowflakeCredentials authModelSnowflakeCredentials =
        new AuthModelSnowflakeCredentials();
    authModelSnowflakeCredentials.setRole("Role");
    authModelSnowflakeCredentials.setUserName("");
    authModelSnowflakeCredentials.setUserPassword("iloveyou");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        snowflakeAuthModelSnowflake.initAuthentication(
            monitor, dataSource, authModelSnowflakeCredentials, configuration, connProperties);

    // Assert
    assertTrue(actualInitAuthenticationResult instanceof AuthModelSnowflakeCredentials);
    assertEquals(1, connProperties.size());
    assertEquals(
        "", ((AuthModelSnowflakeCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals(
        "iloveyou",
        ((AuthModelSnowflakeCredentials) actualInitAuthenticationResult).getUserPassword());
    assertEquals("iloveyou", connProperties.get("password"));
  }

  /**
   * Test {@link SnowflakeAuthModelSnowflake#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelSnowflakeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link SnowflakeAuthModelSnowflake#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SnowflakeAuthModelSnowflake.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelSnowflakeCredentialsDBPConnectionConfigurationProperties4()
          throws DBException {
    // Arrange
    SnowflakeAuthModelSnowflake<AuthModelSnowflakeCredentials> snowflakeAuthModelSnowflake =
        new SnowflakeAuthModelSnowflake<>();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    AuthModelSnowflakeCredentials authModelSnowflakeCredentials =
        new AuthModelSnowflakeCredentials();
    authModelSnowflakeCredentials.setRole("Role");
    authModelSnowflakeCredentials.setUserName("janedoe");
    authModelSnowflakeCredentials.setUserPassword(null);
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        snowflakeAuthModelSnowflake.initAuthentication(
            monitor, dataSource, authModelSnowflakeCredentials, configuration, connProperties);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof AuthModelSnowflakeCredentials);
    assertEquals(1, connProperties.size());
    assertEquals(
        "janedoe", ((AuthModelSnowflakeCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals("janedoe", connProperties.get("user"));
  }

  /**
   * Test {@link SnowflakeAuthModelSnowflake#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelSnowflakeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link SnowflakeAuthModelSnowflake#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SnowflakeAuthModelSnowflake.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelSnowflakeCredentialsDBPConnectionConfigurationProperties5()
          throws DBException {
    // Arrange
    SnowflakeAuthModelSnowflake<AuthModelSnowflakeCredentials> snowflakeAuthModelSnowflake =
        new SnowflakeAuthModelSnowflake<>();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    AuthModelSnowflakeCredentials authModelSnowflakeCredentials =
        new AuthModelSnowflakeCredentials();
    authModelSnowflakeCredentials.setRole("Role");
    authModelSnowflakeCredentials.setUserName("janedoe");
    authModelSnowflakeCredentials.setUserPassword(null);

    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setAuthProperties(new HashMap<>());
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        snowflakeAuthModelSnowflake.initAuthentication(
            monitor, dataSource, authModelSnowflakeCredentials, configuration, connProperties);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof AuthModelSnowflakeCredentials);
    assertEquals(1, connProperties.size());
    assertEquals(
        "janedoe", ((AuthModelSnowflakeCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals("janedoe", connProperties.get("user"));
  }

  /**
   * Test {@link SnowflakeAuthModelSnowflake#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelSnowflakeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link SnowflakeAuthModelSnowflake#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SnowflakeAuthModelSnowflake.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelSnowflakeCredentialsDBPConnectionConfigurationProperties6()
          throws DBException {
    // Arrange
    SnowflakeAuthModelSnowflake<AuthModelSnowflakeCredentials> snowflakeAuthModelSnowflake =
        new SnowflakeAuthModelSnowflake<>();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    AuthModelSnowflakeCredentials authModelSnowflakeCredentials =
        new AuthModelSnowflakeCredentials();
    authModelSnowflakeCredentials.setRole("Role");
    authModelSnowflakeCredentials.setUserName("janedoe");
    authModelSnowflakeCredentials.setUserPassword(null);

    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setAuthProperty("authenticator", "42");
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        snowflakeAuthModelSnowflake.initAuthentication(
            monitor, dataSource, authModelSnowflakeCredentials, configuration, connProperties);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof AuthModelSnowflakeCredentials);
    assertEquals(2, connProperties.size());
    assertEquals("42", connProperties.get("authenticator"));
    assertNull(((AuthModelSnowflakeCredentials) actualInitAuthenticationResult).getUserPassword());
    assertTrue(connProperties.containsKey("user"));
  }

  /**
   * Test {@link SnowflakeAuthModelSnowflake#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelSnowflakeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link SnowflakeAuthModelSnowflake#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SnowflakeAuthModelSnowflake.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelSnowflakeCredentialsDBPConnectionConfigurationProperties7()
          throws DBException {
    // Arrange
    SnowflakeAuthModelSnowflake<AuthModelSnowflakeCredentials> snowflakeAuthModelSnowflake =
        new SnowflakeAuthModelSnowflake<>();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    AuthModelSnowflakeCredentials authModelSnowflakeCredentials =
        new AuthModelSnowflakeCredentials();
    authModelSnowflakeCredentials.setRole("Role");
    authModelSnowflakeCredentials.setUserName("janedoe");
    authModelSnowflakeCredentials.setUserPassword(null);

    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setAuthProperty("role", "42");
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        snowflakeAuthModelSnowflake.initAuthentication(
            monitor, dataSource, authModelSnowflakeCredentials, configuration, connProperties);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof AuthModelSnowflakeCredentials);
    assertEquals(2, connProperties.size());
    assertEquals("42", connProperties.get("role"));
    assertNull(((AuthModelSnowflakeCredentials) actualInitAuthenticationResult).getUserPassword());
    assertTrue(connProperties.containsKey("user"));
  }

  /**
   * Test {@link SnowflakeAuthModelSnowflake#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelSnowflakeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link SnowflakeAuthModelSnowflake#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SnowflakeAuthModelSnowflake.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelSnowflakeCredentialsDBPConnectionConfigurationProperties8()
          throws DBException {
    // Arrange
    SnowflakeAuthModelSnowflake<AuthModelSnowflakeCredentials> snowflakeAuthModelSnowflake =
        new SnowflakeAuthModelSnowflake<>();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    AuthModelSnowflakeCredentials authModelSnowflakeCredentials =
        new AuthModelSnowflakeCredentials();
    authModelSnowflakeCredentials.setRole("Role");
    authModelSnowflakeCredentials.setUserName(null);
    authModelSnowflakeCredentials.setUserPassword(null);
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        snowflakeAuthModelSnowflake.initAuthentication(
            monitor, dataSource, authModelSnowflakeCredentials, configuration, connProperties);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof AuthModelSnowflakeCredentials);
    assertNull(((AuthModelSnowflakeCredentials) actualInitAuthenticationResult).getUserName());
    assertNull(((AuthModelSnowflakeCredentials) actualInitAuthenticationResult).getUserPassword());
    assertTrue(connProperties.isEmpty());
  }

  /**
   * Test {@link SnowflakeAuthModelSnowflake#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelSnowflakeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link SnowflakeAuthModelSnowflake#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SnowflakeAuthModelSnowflake.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelSnowflakeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelSnowflakeCredentialsDBPConnectionConfigurationProperties9()
          throws DBException {
    // Arrange
    SnowflakeAuthModelSnowflake<AuthModelSnowflakeCredentials> snowflakeAuthModelSnowflake =
        new SnowflakeAuthModelSnowflake<>();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    AuthModelSnowflakeCredentials authModelSnowflakeCredentials =
        new AuthModelSnowflakeCredentials();
    authModelSnowflakeCredentials.setRole("Role");
    authModelSnowflakeCredentials.setUserName("janedoe");
    authModelSnowflakeCredentials.setUserPassword("");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        snowflakeAuthModelSnowflake.initAuthentication(
            monitor, dataSource, authModelSnowflakeCredentials, configuration, connProperties);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof AuthModelSnowflakeCredentials);
    assertEquals(2, connProperties.size());
    assertEquals(
        "", ((AuthModelSnowflakeCredentials) actualInitAuthenticationResult).getUserPassword());
    assertEquals("", connProperties.get("password"));
    assertTrue(connProperties.containsKey("user"));
  }

  /**
   * Test {@link SnowflakeAuthModelSnowflake#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)} with {@code dataSource}, {@code configuration}.
   *
   * <p>Method under test: {@link
   * SnowflakeAuthModelSnowflake#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthModelSnowflakeCredentials SnowflakeAuthModelSnowflake.loadCredentials(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testLoadCredentialsWithDataSourceConfiguration() {
    // Arrange
    SnowflakeAuthModelSnowflake<AuthModelSnowflakeCredentials> snowflakeAuthModelSnowflake =
        new SnowflakeAuthModelSnowflake<>();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(true);
    when(dbpDriver.getDataSourceProvider()).thenReturn(mock(DBPDataSourceProvider.class));

    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getDriver()).thenReturn(dbpDriver);

    // Act
    AuthModelSnowflakeCredentials actualLoadCredentialsResult =
        snowflakeAuthModelSnowflake.loadCredentials(dataSource, new DBPConnectionConfiguration());

    // Assert
    verify(dataSource, atLeast(1)).getDriver();
    verify(dbpDriver).getDataSourceProvider();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertEquals("", actualLoadCredentialsResult.getUserPassword());
    assertNull(actualLoadCredentialsResult.getRole());
    assertNull(actualLoadCredentialsResult.getUserName());
    assertTrue(actualLoadCredentialsResult.isComplete());
  }

  /**
   * Test {@link SnowflakeAuthModelSnowflake#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)} with {@code dataSource}, {@code configuration}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SnowflakeAuthModelSnowflake#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthModelSnowflakeCredentials SnowflakeAuthModelSnowflake.loadCredentials(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testLoadCredentialsWithDataSourceConfiguration_givenHashMap() {
    // Arrange
    SnowflakeAuthModelSnowflake<AuthModelSnowflakeCredentials> snowflakeAuthModelSnowflake =
        new SnowflakeAuthModelSnowflake<>();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);
    when(dbpDriver.getDataSourceProvider()).thenReturn(mock(DBPDataSourceProvider.class));

    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getDriver()).thenReturn(dbpDriver);

    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setAuthProperties(new HashMap<>());

    // Act
    AuthModelSnowflakeCredentials actualLoadCredentialsResult =
        snowflakeAuthModelSnowflake.loadCredentials(dataSource, configuration);

    // Assert
    verify(dataSource, atLeast(1)).getDriver();
    verify(dbpDriver).getDataSourceProvider();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertNull(actualLoadCredentialsResult.getRole());
    assertNull(actualLoadCredentialsResult.getUserName());
    assertNull(actualLoadCredentialsResult.getUserPassword());
    assertTrue(actualLoadCredentialsResult.isComplete());
  }

  /**
   * Test {@link SnowflakeAuthModelSnowflake#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)} with {@code dataSource}, {@code configuration}.
   *
   * <ul>
   *   <li>Then return UserPassword is {@code iloveyou}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SnowflakeAuthModelSnowflake#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthModelSnowflakeCredentials SnowflakeAuthModelSnowflake.loadCredentials(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testLoadCredentialsWithDataSourceConfiguration_thenReturnUserPasswordIsIloveyou() {
    // Arrange
    SnowflakeAuthModelSnowflake<AuthModelSnowflakeCredentials> snowflakeAuthModelSnowflake =
        new SnowflakeAuthModelSnowflake<>();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);
    when(dbpDriver.getDataSourceProvider()).thenReturn(mock(DBPDataSourceProvider.class));

    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getDriver()).thenReturn(dbpDriver);

    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setUserPassword("iloveyou");

    // Act
    AuthModelSnowflakeCredentials actualLoadCredentialsResult =
        snowflakeAuthModelSnowflake.loadCredentials(dataSource, configuration);

    // Assert
    verify(dataSource, atLeast(1)).getDriver();
    verify(dbpDriver).getDataSourceProvider();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertEquals("iloveyou", actualLoadCredentialsResult.getUserPassword());
    assertNull(actualLoadCredentialsResult.getRole());
    assertNull(actualLoadCredentialsResult.getUserName());
    assertTrue(actualLoadCredentialsResult.isComplete());
  }

  /**
   * Test {@link SnowflakeAuthModelSnowflake#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)} with {@code dataSource}, {@code configuration}.
   *
   * <ul>
   *   <li>Then return UserPassword is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SnowflakeAuthModelSnowflake#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthModelSnowflakeCredentials SnowflakeAuthModelSnowflake.loadCredentials(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testLoadCredentialsWithDataSourceConfiguration_thenReturnUserPasswordIsNull() {
    // Arrange
    SnowflakeAuthModelSnowflake<AuthModelSnowflakeCredentials> snowflakeAuthModelSnowflake =
        new SnowflakeAuthModelSnowflake<>();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);
    when(dbpDriver.getDataSourceProvider()).thenReturn(mock(DBPDataSourceProvider.class));

    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getDriver()).thenReturn(dbpDriver);

    // Act
    AuthModelSnowflakeCredentials actualLoadCredentialsResult =
        snowflakeAuthModelSnowflake.loadCredentials(dataSource, new DBPConnectionConfiguration());

    // Assert
    verify(dataSource, atLeast(1)).getDriver();
    verify(dbpDriver).getDataSourceProvider();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertNull(actualLoadCredentialsResult.getRole());
    assertNull(actualLoadCredentialsResult.getUserName());
    assertNull(actualLoadCredentialsResult.getUserPassword());
    assertTrue(actualLoadCredentialsResult.isComplete());
  }

  /**
   * Test {@link SnowflakeAuthModelSnowflake#saveCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration, AuthModelSnowflakeCredentials)} with {@code
   * DBPDataSourceContainer}, {@code DBPConnectionConfiguration}, {@code
   * AuthModelSnowflakeCredentials}.
   *
   * <p>Method under test: {@link
   * SnowflakeAuthModelSnowflake#saveCredentials(DBPDataSourceContainer, DBPConnectionConfiguration,
   * AuthModelSnowflakeCredentials)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SnowflakeAuthModelSnowflake.saveCredentials(DBPDataSourceContainer, DBPConnectionConfiguration, AuthModelSnowflakeCredentials)"
  })
  public void
      testSaveCredentialsWithDBPDataSourceContainerDBPConnectionConfigurationAuthModelSnowflakeCredentials() {
    // Arrange
    SnowflakeAuthModelSnowflake<AuthModelSnowflakeCredentials> snowflakeAuthModelSnowflake =
        new SnowflakeAuthModelSnowflake<>();
    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();

    AuthModelSnowflakeCredentials authModelSnowflakeCredentials =
        new AuthModelSnowflakeCredentials();
    authModelSnowflakeCredentials.setRole("Role");
    authModelSnowflakeCredentials.setUserName("janedoe");
    authModelSnowflakeCredentials.setUserPassword("iloveyou");

    // Act
    snowflakeAuthModelSnowflake.saveCredentials(
        dataSource, configuration, authModelSnowflakeCredentials);

    // Assert
    Map<String, String> authProperties = configuration.getAuthProperties();
    assertEquals(1, authProperties.size());
    assertEquals("Role", authProperties.get("role"));
    assertEquals("iloveyou", configuration.getUserPassword());
    assertEquals("janedoe", configuration.getUserName());
  }

  /**
   * Test {@link SnowflakeAuthModelSnowflake#saveCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration, AuthModelSnowflakeCredentials)} with {@code
   * DBPDataSourceContainer}, {@code DBPConnectionConfiguration}, {@code
   * AuthModelSnowflakeCredentials}.
   *
   * <p>Method under test: {@link
   * SnowflakeAuthModelSnowflake#saveCredentials(DBPDataSourceContainer, DBPConnectionConfiguration,
   * AuthModelSnowflakeCredentials)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SnowflakeAuthModelSnowflake.saveCredentials(DBPDataSourceContainer, DBPConnectionConfiguration, AuthModelSnowflakeCredentials)"
  })
  public void
      testSaveCredentialsWithDBPDataSourceContainerDBPConnectionConfigurationAuthModelSnowflakeCredentials2() {
    // Arrange
    SnowflakeAuthModelSnowflake<AuthModelSnowflakeCredentials> snowflakeAuthModelSnowflake =
        new SnowflakeAuthModelSnowflake<>();
    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);

    DBPConnectionConfiguration configuration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    configuration.setAuthProperties(null);

    AuthModelSnowflakeCredentials authModelSnowflakeCredentials =
        new AuthModelSnowflakeCredentials();
    authModelSnowflakeCredentials.setUserName("janedoe");
    authModelSnowflakeCredentials.setUserPassword("iloveyou");
    authModelSnowflakeCredentials.setRole(null);

    // Act
    snowflakeAuthModelSnowflake.saveCredentials(
        dataSource, configuration, authModelSnowflakeCredentials);

    // Assert
    assertEquals("iloveyou", configuration.getUserPassword());
    assertEquals("janedoe", configuration.getUserName());
    assertTrue(configuration.getAuthProperties().isEmpty());
  }

  /**
   * Test {@link SnowflakeAuthModelSnowflake#saveCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration, AuthModelSnowflakeCredentials)} with {@code
   * DBPDataSourceContainer}, {@code DBPConnectionConfiguration}, {@code
   * AuthModelSnowflakeCredentials}.
   *
   * <p>Method under test: {@link
   * SnowflakeAuthModelSnowflake#saveCredentials(DBPDataSourceContainer, DBPConnectionConfiguration,
   * AuthModelSnowflakeCredentials)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SnowflakeAuthModelSnowflake.saveCredentials(DBPDataSourceContainer, DBPConnectionConfiguration, AuthModelSnowflakeCredentials)"
  })
  public void
      testSaveCredentialsWithDBPDataSourceContainerDBPConnectionConfigurationAuthModelSnowflakeCredentials3() {
    // Arrange
    SnowflakeAuthModelSnowflake<AuthModelSnowflakeCredentials> snowflakeAuthModelSnowflake =
        new SnowflakeAuthModelSnowflake<>();
    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);

    DBPConnectionConfiguration configuration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    configuration.setAuthProperties(new HashMap<>());

    AuthModelSnowflakeCredentials authModelSnowflakeCredentials =
        new AuthModelSnowflakeCredentials();
    authModelSnowflakeCredentials.setUserName("janedoe");
    authModelSnowflakeCredentials.setUserPassword("iloveyou");
    authModelSnowflakeCredentials.setRole(null);

    // Act
    snowflakeAuthModelSnowflake.saveCredentials(
        dataSource, configuration, authModelSnowflakeCredentials);

    // Assert
    assertEquals("iloveyou", configuration.getUserPassword());
    assertEquals("janedoe", configuration.getUserName());
    assertTrue(configuration.getAuthProperties().isEmpty());
  }

  /**
   * Test {@link SnowflakeAuthModelSnowflake#getAuthenticator(DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link SnowflakeAuthModelSnowflake#getAuthenticator(DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SnowflakeAuthModelSnowflake.getAuthenticator(DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration)"
  })
  public void testGetAuthenticator_givenHashMap() {
    // Arrange
    SnowflakeAuthModelSnowflake<AuthModelSnowflakeCredentials> snowflakeAuthModelSnowflake =
        new SnowflakeAuthModelSnowflake<>();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelDatabaseNativeCredentials credentials = new AuthModelDatabaseNativeCredentials();
    credentials.setUserName("janedoe");
    credentials.setUserPassword("iloveyou");

    DBPConnectionConfiguration configuration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    configuration.setAuthProperties(new HashMap<>());

    // Act and Assert
    assertNull(
        snowflakeAuthModelSnowflake.getAuthenticator(dataSource, credentials, configuration));
  }

  /**
   * Test {@link SnowflakeAuthModelSnowflake#getAuthenticator(DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration)}.
   *
   * <ul>
   *   <li>When {@link DBPConnectionConfiguration#DBPConnectionConfiguration()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SnowflakeAuthModelSnowflake#getAuthenticator(DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SnowflakeAuthModelSnowflake.getAuthenticator(DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration)"
  })
  public void testGetAuthenticator_whenDBPConnectionConfiguration_thenReturnNull() {
    // Arrange
    SnowflakeAuthModelSnowflake<AuthModelSnowflakeCredentials> snowflakeAuthModelSnowflake =
        new SnowflakeAuthModelSnowflake<>();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelDatabaseNativeCredentials credentials = new AuthModelDatabaseNativeCredentials();
    credentials.setUserName("janedoe");
    credentials.setUserPassword("iloveyou");

    // Act and Assert
    assertNull(
        snowflakeAuthModelSnowflake.getAuthenticator(
            dataSource, credentials, new DBPConnectionConfiguration()));
  }

  /**
   * Test new {@link SnowflakeAuthModelSnowflake} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * SnowflakeAuthModelSnowflake}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SnowflakeAuthModelSnowflake.<init>()"})
  public void testNewSnowflakeAuthModelSnowflake() {
    // Arrange and Act
    SnowflakeAuthModelSnowflake<AuthModelSnowflakeCredentials> actualSnowflakeAuthModelSnowflake =
        new SnowflakeAuthModelSnowflake<>();

    // Assert
    assertTrue(actualSnowflakeAuthModelSnowflake.isUserNameApplicable());
    assertTrue(actualSnowflakeAuthModelSnowflake.isUserPasswordApplicable());
  }
}
