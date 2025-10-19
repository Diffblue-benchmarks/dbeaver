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

public class SQLServerAuthModelDatabaseDiffblueTest {
  /**
   * Test {@link SQLServerAuthModelDatabase#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelDatabaseNativeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link SQLServerAuthModelDatabase#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SQLServerAuthModelDatabase.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties()
          throws DBException {
    // Arrange
    SQLServerAuthModelDatabase sqlServerAuthModelDatabase = new SQLServerAuthModelDatabase();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelDatabaseNativeCredentials credentials = new AuthModelDatabaseNativeCredentials();
    credentials.setUserName("janedoe");
    credentials.setUserPassword("iloveyou");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        sqlServerAuthModelDatabase.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    assertEquals(3, connProperties.size());
    assertEquals("iloveyou", connProperties.get("password"));
    assertEquals("janedoe", connProperties.get("user"));
    assertEquals(Boolean.FALSE.toString(), connProperties.get("integratedSecurity"));
    assertSame(credentials, actualInitAuthenticationResult);
  }

  /**
   * Test new {@link SQLServerAuthModelDatabase} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * SQLServerAuthModelDatabase}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLServerAuthModelDatabase.<init>()"})
  public void testNewSQLServerAuthModelDatabase() {
    // Arrange and Act
    SQLServerAuthModelDatabase actualSqlServerAuthModelDatabase = new SQLServerAuthModelDatabase();

    // Assert
    assertTrue(actualSqlServerAuthModelDatabase.isUserNameApplicable());
    assertTrue(actualSqlServerAuthModelDatabase.isUserPasswordApplicable());
  }
}
