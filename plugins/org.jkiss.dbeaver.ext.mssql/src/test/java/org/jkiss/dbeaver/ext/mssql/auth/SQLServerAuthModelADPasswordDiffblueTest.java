package org.jkiss.dbeaver.ext.mssql.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Properties;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.impl.auth.AuthModelDatabaseNativeCredentials;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLServerAuthModelADPasswordDiffblueTest {
  /**
   * Test {@link SQLServerAuthModelADPassword#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelDatabaseNativeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link
   * SQLServerAuthModelADPassword#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SQLServerAuthModelADPassword.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties()
          throws DBException {
    // Arrange
    SQLServerAuthModelADPassword sqlServerAuthModelADPassword = new SQLServerAuthModelADPassword();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelDatabaseNativeCredentials credentials = new AuthModelDatabaseNativeCredentials();
    credentials.setUserName("janedoe");
    credentials.setUserPassword("iloveyou");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        sqlServerAuthModelADPassword.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    assertEquals(2, connProperties.size());
    assertEquals("ActiveDirectoryPassword", connProperties.get("authentication"));
    assertEquals(Boolean.FALSE.toString(), connProperties.get("integratedSecurity"));
    assertSame(credentials, actualInitAuthenticationResult);
  }

  /**
   * Test {@link SQLServerAuthModelADPassword#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelDatabaseNativeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link
   * SQLServerAuthModelADPassword#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SQLServerAuthModelADPassword.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties2()
          throws DBException {
    // Arrange
    SQLServerAuthModelADPassword sqlServerAuthModelADPassword = new SQLServerAuthModelADPassword();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelDatabaseNativeCredentials credentials = new AuthModelDatabaseNativeCredentials();
    credentials.setUserName("janedoe");
    credentials.setUserPassword("iloveyou");

    DBPConnectionConfiguration configuration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    configuration.setUserName("");
    configuration.setUserPassword("");
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        sqlServerAuthModelADPassword.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    assertEquals(2, connProperties.size());
    assertEquals("ActiveDirectoryPassword", connProperties.get("authentication"));
    assertEquals(Boolean.FALSE.toString(), connProperties.get("integratedSecurity"));
    assertSame(credentials, actualInitAuthenticationResult);
  }

  /**
   * Test {@link SQLServerAuthModelADPassword#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelDatabaseNativeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link
   * SQLServerAuthModelADPassword#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SQLServerAuthModelADPassword.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties3()
          throws DBException {
    // Arrange
    SQLServerAuthModelADPassword sqlServerAuthModelADPassword = new SQLServerAuthModelADPassword();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelDatabaseNativeCredentials credentials = new AuthModelDatabaseNativeCredentials();
    credentials.setUserName("janedoe");
    credentials.setUserPassword("iloveyou");

    DBPConnectionConfiguration configuration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    configuration.setUserName("");
    configuration.setUserPassword("integratedSecurity");
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        sqlServerAuthModelADPassword.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    assertEquals(3, connProperties.size());
    assertEquals("ActiveDirectoryPassword", connProperties.get("authentication"));
    assertEquals("integratedSecurity", connProperties.get("Password"));
    assertEquals(Boolean.FALSE.toString(), connProperties.get("integratedSecurity"));
    assertSame(credentials, actualInitAuthenticationResult);
  }

  /**
   * Test {@link SQLServerAuthModelADPassword#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelDatabaseNativeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link
   * SQLServerAuthModelADPassword#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SQLServerAuthModelADPassword.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties4()
          throws DBException {
    // Arrange
    SQLServerAuthModelADPassword sqlServerAuthModelADPassword = new SQLServerAuthModelADPassword();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelDatabaseNativeCredentials credentials = new AuthModelDatabaseNativeCredentials();
    credentials.setUserName("janedoe");
    credentials.setUserPassword("iloveyou");

    DBPConnectionConfiguration configuration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    configuration.setUserName("integratedSecurity");
    configuration.setUserPassword("");
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        sqlServerAuthModelADPassword.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    assertEquals(3, connProperties.size());
    assertEquals("ActiveDirectoryPassword", connProperties.get("authentication"));
    assertEquals("integratedSecurity", connProperties.get("UserName"));
    assertEquals(Boolean.FALSE.toString(), connProperties.get("integratedSecurity"));
    assertSame(credentials, actualInitAuthenticationResult);
  }

  /**
   * Test new {@link SQLServerAuthModelADPassword} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * SQLServerAuthModelADPassword}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLServerAuthModelADPassword.<init>()"})
  public void testNewSQLServerAuthModelADPassword() {
    // Arrange and Act
    SQLServerAuthModelADPassword actualSqlServerAuthModelADPassword =
        new SQLServerAuthModelADPassword();

    // Assert
    assertTrue(actualSqlServerAuthModelADPassword.isUserNameApplicable());
    assertTrue(actualSqlServerAuthModelADPassword.isUserPasswordApplicable());
  }
}
