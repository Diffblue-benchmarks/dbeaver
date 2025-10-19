package org.jkiss.dbeaver.ext.oracle.model.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Properties;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.jkiss.dbeaver.model.impl.auth.AuthModelDatabaseNativeCredentials;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleAuthModelDatabaseNativeDiffblueTest {
  /**
   * Test {@link OracleAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelDatabaseNativeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link
   * OracleAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object OracleAuthModelDatabaseNative.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties()
          throws DBException {
    // Arrange
    OracleAuthModelDatabaseNative oracleAuthModelDatabaseNative =
        new OracleAuthModelDatabaseNative();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelDatabaseNativeCredentials credentials = new AuthModelDatabaseNativeCredentials();
    credentials.setUserName("janedoe");
    credentials.setUserPassword("iloveyou");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        oracleAuthModelDatabaseNative.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    assertTrue(actualInitAuthenticationResult instanceof AuthModelDatabaseNativeCredentials);
    assertEquals(1, connProperties.size());
    assertEquals(
        "iloveyou",
        ((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserPassword());
    assertEquals("iloveyou", connProperties.get("password"));
    assertNull(credentials.getUserName());
    assertNull(((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserName());
  }

  /**
   * Test {@link OracleAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelDatabaseNativeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link
   * OracleAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object OracleAuthModelDatabaseNative.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties2()
          throws DBException {
    // Arrange
    OracleAuthModelDatabaseNative oracleAuthModelDatabaseNative =
        new OracleAuthModelDatabaseNative();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    AuthModelDatabaseNativeCredentials credentials = new AuthModelDatabaseNativeCredentials();
    credentials.setUserName("janedoe");
    credentials.setUserPassword("");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        oracleAuthModelDatabaseNative.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof AuthModelDatabaseNativeCredentials);
    assertNull(credentials.getUserName());
    assertNull(((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserName());
    assertTrue(connProperties.isEmpty());
  }

  /**
   * Test {@link OracleAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelDatabaseNativeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link
   * OracleAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object OracleAuthModelDatabaseNative.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties3()
          throws DBException {
    // Arrange
    OracleAuthModelDatabaseNative oracleAuthModelDatabaseNative =
        new OracleAuthModelDatabaseNative();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    AuthModelDatabaseNativeCredentials credentials = new AuthModelDatabaseNativeCredentials();
    credentials.setUserName("janedoe");
    credentials.setUserPassword("");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        oracleAuthModelDatabaseNative.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof AuthModelDatabaseNativeCredentials);
    assertNull(credentials.getUserName());
    assertNull(((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserName());
    assertTrue(connProperties.isEmpty());
  }

  /**
   * Test {@link OracleAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelDatabaseNativeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link
   * OracleAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object OracleAuthModelDatabaseNative.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties4()
          throws DBException {
    // Arrange
    OracleAuthModelDatabaseNative oracleAuthModelDatabaseNative =
        new OracleAuthModelDatabaseNative();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    AuthModelDatabaseNativeCredentials credentials = new AuthModelDatabaseNativeCredentials();
    credentials.setUserName("janedoe");
    credentials.setUserPassword("");

    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setUserName("janedoe");
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        oracleAuthModelDatabaseNative.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof AuthModelDatabaseNativeCredentials);
    assertEquals("janedoe", credentials.getUserName());
    assertEquals(1, connProperties.size());
    assertEquals(
        "janedoe",
        ((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals("janedoe", connProperties.get("user"));
  }

  /**
   * Test {@link OracleAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelDatabaseNativeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link
   * OracleAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object OracleAuthModelDatabaseNative.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties5()
          throws DBException {
    // Arrange
    OracleAuthModelDatabaseNative oracleAuthModelDatabaseNative =
        new OracleAuthModelDatabaseNative();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    AuthModelDatabaseNativeCredentials credentials = new AuthModelDatabaseNativeCredentials();
    credentials.setUserName("janedoe");
    credentials.setUserPassword("");

    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setUserName("janedoe");
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        oracleAuthModelDatabaseNative.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof AuthModelDatabaseNativeCredentials);
    assertEquals(2, connProperties.size());
    assertEquals("", connProperties.get("password"));
    assertEquals("janedoe", credentials.getUserName());
    assertEquals(
        "janedoe",
        ((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals("janedoe", connProperties.get("user"));
  }

  /**
   * Test {@link OracleAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelDatabaseNativeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link
   * OracleAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object OracleAuthModelDatabaseNative.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties6()
          throws DBException {
    // Arrange
    OracleAuthModelDatabaseNative oracleAuthModelDatabaseNative =
        new OracleAuthModelDatabaseNative();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    AuthModelDatabaseNativeCredentials credentials = new AuthModelDatabaseNativeCredentials();
    credentials.setUserName("janedoe");
    credentials.setUserPassword("");

    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setUserName(" AS ");
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        oracleAuthModelDatabaseNative.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof AuthModelDatabaseNativeCredentials);
    assertEquals(" AS ", credentials.getUserName());
    assertEquals(1, connProperties.size());
    assertEquals(
        " AS ",
        ((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals(" AS ", connProperties.get("user"));
  }

  /**
   * Test {@link OracleAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelDatabaseNativeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link
   * OracleAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object OracleAuthModelDatabaseNative.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties7()
          throws DBException {
    // Arrange
    OracleAuthModelDatabaseNative oracleAuthModelDatabaseNative =
        new OracleAuthModelDatabaseNative();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    AuthModelDatabaseNativeCredentials credentials = new AuthModelDatabaseNativeCredentials();
    credentials.setUserName("janedoe");
    credentials.setUserPassword("");

    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setAuthProperties(new HashMap<>());
    configuration.setUserName("janedoe");
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        oracleAuthModelDatabaseNative.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof AuthModelDatabaseNativeCredentials);
    assertEquals("janedoe", credentials.getUserName());
    assertEquals(1, connProperties.size());
    assertEquals(
        "janedoe",
        ((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals("janedoe", connProperties.get("user"));
  }

  /**
   * Test new {@link OracleAuthModelDatabaseNative} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * OracleAuthModelDatabaseNative}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OracleAuthModelDatabaseNative.<init>()"})
  public void testNewOracleAuthModelDatabaseNative() {
    // Arrange and Act
    OracleAuthModelDatabaseNative actualOracleAuthModelDatabaseNative =
        new OracleAuthModelDatabaseNative();

    // Assert
    assertTrue(actualOracleAuthModelDatabaseNative.isUserNameApplicable());
    assertTrue(actualOracleAuthModelDatabaseNative.isUserPasswordApplicable());
  }
}
