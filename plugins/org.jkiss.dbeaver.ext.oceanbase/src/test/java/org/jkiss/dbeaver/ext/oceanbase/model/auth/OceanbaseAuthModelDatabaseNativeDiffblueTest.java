package org.jkiss.dbeaver.ext.oceanbase.model.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
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
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.jkiss.dbeaver.model.impl.auth.AuthModelDatabaseNativeCredentials;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OceanbaseAuthModelDatabaseNativeDiffblueTest {
  /**
   * Test {@link OceanbaseAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   * with {@code DBRProgressMonitor}, {@code DBPDataSource}, {@code
   * AuthModelDatabaseNativeCredentials}, {@code DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link
   * OceanbaseAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object OceanbaseAuthModelDatabaseNative.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties()
          throws DBException {
    // Arrange
    OceanbaseAuthModelDatabaseNative oceanbaseAuthModelDatabaseNative =
        new OceanbaseAuthModelDatabaseNative();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelDatabaseNativeCredentials credentials = new AuthModelDatabaseNativeCredentials();
    credentials.setUserName("janedoe");
    credentials.setUserPassword("iloveyou");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        oceanbaseAuthModelDatabaseNative.initAuthentication(
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
   * Test {@link OceanbaseAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   * with {@code DBRProgressMonitor}, {@code DBPDataSource}, {@code
   * AuthModelDatabaseNativeCredentials}, {@code DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link
   * OceanbaseAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object OceanbaseAuthModelDatabaseNative.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties2()
          throws DBException {
    // Arrange
    OceanbaseAuthModelDatabaseNative oceanbaseAuthModelDatabaseNative =
        new OceanbaseAuthModelDatabaseNative();
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
        oceanbaseAuthModelDatabaseNative.initAuthentication(
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
   * Test {@link OceanbaseAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   * with {@code DBRProgressMonitor}, {@code DBPDataSource}, {@code
   * AuthModelDatabaseNativeCredentials}, {@code DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link
   * OceanbaseAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object OceanbaseAuthModelDatabaseNative.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties3()
          throws DBException {
    // Arrange
    OceanbaseAuthModelDatabaseNative oceanbaseAuthModelDatabaseNative =
        new OceanbaseAuthModelDatabaseNative();
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
        oceanbaseAuthModelDatabaseNative.initAuthentication(
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
   * Test {@link OceanbaseAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   * with {@code DBRProgressMonitor}, {@code DBPDataSource}, {@code
   * AuthModelDatabaseNativeCredentials}, {@code DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link
   * OceanbaseAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object OceanbaseAuthModelDatabaseNative.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties4()
          throws DBException {
    // Arrange
    OceanbaseAuthModelDatabaseNative oceanbaseAuthModelDatabaseNative =
        new OceanbaseAuthModelDatabaseNative();
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

    DBPConnectionConfiguration configuration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    configuration.setUserName("@");
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        oceanbaseAuthModelDatabaseNative.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof AuthModelDatabaseNativeCredentials);
    assertEquals("@", credentials.getUserName());
    assertEquals(1, connProperties.size());
    assertEquals(
        "@", ((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals("@", connProperties.get("user"));
  }

  /**
   * Test {@link OceanbaseAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   * with {@code DBRProgressMonitor}, {@code DBPDataSource}, {@code
   * AuthModelDatabaseNativeCredentials}, {@code DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link
   * OceanbaseAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object OceanbaseAuthModelDatabaseNative.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties5()
          throws DBException {
    // Arrange
    OceanbaseAuthModelDatabaseNative oceanbaseAuthModelDatabaseNative =
        new OceanbaseAuthModelDatabaseNative();
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

    DBPConnectionConfiguration configuration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    configuration.setUserName("Configuration");
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        oceanbaseAuthModelDatabaseNative.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof AuthModelDatabaseNativeCredentials);
    assertEquals("Configuration@null", credentials.getUserName());
    assertEquals(1, connProperties.size());
    assertEquals(
        "Configuration@null",
        ((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals("Configuration@null", connProperties.get("user"));
  }

  /**
   * Test {@link OceanbaseAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   * with {@code DBRProgressMonitor}, {@code DBPDataSource}, {@code
   * AuthModelDatabaseNativeCredentials}, {@code DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link
   * OceanbaseAuthModelDatabaseNative#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object OceanbaseAuthModelDatabaseNative.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties6()
          throws DBException {
    // Arrange
    OceanbaseAuthModelDatabaseNative oceanbaseAuthModelDatabaseNative =
        new OceanbaseAuthModelDatabaseNative();
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

    DBPConnectionConfiguration configuration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    configuration.setUserName("@");
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        oceanbaseAuthModelDatabaseNative.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof AuthModelDatabaseNativeCredentials);
    assertEquals(2, connProperties.size());
    assertEquals("", connProperties.get("password"));
    assertEquals("@", credentials.getUserName());
    assertEquals(
        "@", ((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals("@", connProperties.get("user"));
  }

  /**
   * Test new {@link OceanbaseAuthModelDatabaseNative} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * OceanbaseAuthModelDatabaseNative}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OceanbaseAuthModelDatabaseNative.<init>()"})
  public void testNewOceanbaseAuthModelDatabaseNative() {
    // Arrange and Act
    OceanbaseAuthModelDatabaseNative actualOceanbaseAuthModelDatabaseNative =
        new OceanbaseAuthModelDatabaseNative();

    // Assert
    assertTrue(actualOceanbaseAuthModelDatabaseNative.isUserNameApplicable());
    assertTrue(actualOceanbaseAuthModelDatabaseNative.isUserPasswordApplicable());
  }
}
