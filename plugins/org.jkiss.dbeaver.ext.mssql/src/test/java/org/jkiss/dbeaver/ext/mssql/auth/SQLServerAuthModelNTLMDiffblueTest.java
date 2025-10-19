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

public class SQLServerAuthModelNTLMDiffblueTest {
  /**
   * Test {@link SQLServerAuthModelNTLM#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelDatabaseNativeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link SQLServerAuthModelNTLM#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SQLServerAuthModelNTLM.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties()
          throws DBException {
    // Arrange
    SQLServerAuthModelNTLM sqlServerAuthModelNTLM = new SQLServerAuthModelNTLM();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelDatabaseNativeCredentials credentials = new AuthModelDatabaseNativeCredentials();
    credentials.setUserName("janedoe");
    credentials.setUserPassword("iloveyou");

    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setUserName("janedoe");
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        sqlServerAuthModelNTLM.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    assertTrue(actualInitAuthenticationResult instanceof AuthModelDatabaseNativeCredentials);
    assertEquals(4, connProperties.size());
    assertEquals(
        "janedoe",
        ((AuthModelDatabaseNativeCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals("janedoe", connProperties.get("user"));
    assertTrue(connProperties.containsKey("authenticationScheme"));
    assertTrue(connProperties.containsKey("password"));
    assertEquals(Boolean.TRUE.toString(), connProperties.get("integratedSecurity"));
  }

  /**
   * Test {@link SQLServerAuthModelNTLM#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code AuthModelDatabaseNativeCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link SQLServerAuthModelNTLM#initAuthentication(DBRProgressMonitor,
   * DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SQLServerAuthModelNTLM.initAuthentication(DBRProgressMonitor, DBPDataSource, AuthModelDatabaseNativeCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceAuthModelDatabaseNativeCredentialsDBPConnectionConfigurationProperties2()
          throws DBException {
    // Arrange
    SQLServerAuthModelNTLM sqlServerAuthModelNTLM = new SQLServerAuthModelNTLM();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    AuthModelDatabaseNativeCredentials credentials = new AuthModelDatabaseNativeCredentials();
    credentials.setUserName("integratedSecurity");
    credentials.setUserPassword("iloveyou");

    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setUserName("janedoe");
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        sqlServerAuthModelNTLM.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    assertEquals(3, connProperties.size());
    assertEquals("NTLM", connProperties.get("authenticationScheme"));
    assertEquals("iloveyou", connProperties.get("password"));
    assertEquals("integratedSecurity", connProperties.get("user"));
    assertSame(credentials, actualInitAuthenticationResult);
  }

  /**
   * Test new {@link SQLServerAuthModelNTLM} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SQLServerAuthModelNTLM}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLServerAuthModelNTLM.<init>()"})
  public void testNewSQLServerAuthModelNTLM() {
    // Arrange and Act
    SQLServerAuthModelNTLM actualSqlServerAuthModelNTLM = new SQLServerAuthModelNTLM();

    // Assert
    assertTrue(actualSqlServerAuthModelNTLM.isUserNameApplicable());
    assertTrue(actualSqlServerAuthModelNTLM.isUserPasswordApplicable());
  }
}
