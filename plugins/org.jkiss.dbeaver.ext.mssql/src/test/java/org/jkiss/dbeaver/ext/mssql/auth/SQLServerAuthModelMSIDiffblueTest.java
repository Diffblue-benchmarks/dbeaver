package org.jkiss.dbeaver.ext.mssql.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
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

public class SQLServerAuthModelMSIDiffblueTest {
  /**
   * Test {@link SQLServerAuthModelMSI#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelDatabaseNativeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link SQLServerAuthModelMSI#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SQLServerAuthModelMSI.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties()
          throws DBException {
    // Arrange
    SQLServerAuthModelMSI sqlServerAuthModelMSI = new SQLServerAuthModelMSI();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelDatabaseNativeCredentials credentials = new AuthModelDatabaseNativeCredentials();
    credentials.setUserName("janedoe");
    credentials.setUserPassword("iloveyou");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        sqlServerAuthModelMSI.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    assertEquals(1, connProperties.size());
    assertEquals("ActiveDirectoryMSI", connProperties.get("authentication"));
    assertSame(credentials, actualInitAuthenticationResult);
  }

  /**
   * Test {@link SQLServerAuthModelMSI#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelDatabaseNativeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link SQLServerAuthModelMSI#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SQLServerAuthModelMSI.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties2()
          throws DBException {
    // Arrange
    SQLServerAuthModelMSI sqlServerAuthModelMSI = new SQLServerAuthModelMSI();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelDatabaseNativeCredentials credentials = new AuthModelDatabaseNativeCredentials();
    credentials.setUserName("janedoe");
    credentials.setUserPassword("iloveyou");

    DBPConnectionConfiguration configuration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    configuration.setUserName("");
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        sqlServerAuthModelMSI.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    assertEquals(1, connProperties.size());
    assertEquals("ActiveDirectoryMSI", connProperties.get("authentication"));
    assertSame(credentials, actualInitAuthenticationResult);
  }

  /**
   * Test {@link SQLServerAuthModelMSI#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelDatabaseNativeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link SQLServerAuthModelMSI#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SQLServerAuthModelMSI.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties3()
          throws DBException {
    // Arrange
    SQLServerAuthModelMSI sqlServerAuthModelMSI = new SQLServerAuthModelMSI();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelDatabaseNativeCredentials credentials = new AuthModelDatabaseNativeCredentials();
    credentials.setUserName("janedoe");
    credentials.setUserPassword("iloveyou");

    DBPConnectionConfiguration configuration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    configuration.setUserName("authentication");
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        sqlServerAuthModelMSI.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    assertEquals(2, connProperties.size());
    assertEquals("ActiveDirectoryMSI", connProperties.get("authentication"));
    assertEquals("authentication", connProperties.get("msiClientId"));
    assertSame(credentials, actualInitAuthenticationResult);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link SQLServerAuthModelMSI}
   *   <li>{@link SQLServerAuthModelMSI#isUserPasswordApplicable()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLServerAuthModelMSI.<init>()",
    "boolean SQLServerAuthModelMSI.isUserPasswordApplicable()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertFalse(new SQLServerAuthModelMSI().isUserPasswordApplicable());
  }
}
