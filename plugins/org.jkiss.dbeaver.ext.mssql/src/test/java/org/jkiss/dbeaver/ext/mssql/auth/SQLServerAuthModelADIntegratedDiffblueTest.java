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

public class SQLServerAuthModelADIntegratedDiffblueTest {
  /**
   * Test {@link SQLServerAuthModelADIntegrated#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   * with {@code DBRProgressMonitor}, {@code DBPDataSource}, {@code
   * AuthModelDatabaseNativeCredentials}, {@code DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link
   * SQLServerAuthModelADIntegrated#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SQLServerAuthModelADIntegrated.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties()
          throws DBException {
    // Arrange
    SQLServerAuthModelADIntegrated sqlServerAuthModelADIntegrated =
        new SQLServerAuthModelADIntegrated();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelDatabaseNativeCredentials credentials = new AuthModelDatabaseNativeCredentials();
    credentials.setUserName("janedoe");
    credentials.setUserPassword("iloveyou");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        sqlServerAuthModelADIntegrated.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    assertEquals(1, connProperties.size());
    assertEquals("ActiveDirectoryIntegrated", connProperties.get("authentication"));
    assertSame(credentials, actualInitAuthenticationResult);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link SQLServerAuthModelADIntegrated}
   *   <li>{@link SQLServerAuthModelADIntegrated#isUserNameApplicable()}
   *   <li>{@link SQLServerAuthModelADIntegrated#isUserPasswordApplicable()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLServerAuthModelADIntegrated.<init>()",
    "boolean SQLServerAuthModelADIntegrated.isUserNameApplicable()",
    "boolean SQLServerAuthModelADIntegrated.isUserPasswordApplicable()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    SQLServerAuthModelADIntegrated actualSqlServerAuthModelADIntegrated =
        new SQLServerAuthModelADIntegrated();
    boolean actualIsUserNameApplicableResult =
        actualSqlServerAuthModelADIntegrated.isUserNameApplicable();

    // Assert
    assertFalse(actualIsUserNameApplicableResult);
    assertFalse(actualSqlServerAuthModelADIntegrated.isUserPasswordApplicable());
  }
}
