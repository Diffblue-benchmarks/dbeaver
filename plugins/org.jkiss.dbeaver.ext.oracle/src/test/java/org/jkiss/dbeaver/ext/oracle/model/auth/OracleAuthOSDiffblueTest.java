package org.jkiss.dbeaver.ext.oracle.model.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
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

public class OracleAuthOSDiffblueTest {
  /**
   * Test {@link OracleAuthOS#createCredentials()}.
   *
   * <p>Method under test: {@link OracleAuthOS#createCredentials()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OracleAuthOSCredentials OracleAuthOS.createCredentials()"})
  public void testCreateCredentials() {
    // Arrange and Act
    OracleAuthOSCredentials actualCreateCredentialsResult = new OracleAuthOS().createCredentials();

    // Assert
    assertNull(actualCreateCredentialsResult.getUserName());
    assertNull(actualCreateCredentialsResult.getUserPassword());
    assertTrue(actualCreateCredentialsResult.isComplete());
  }

  /**
   * Test {@link OracleAuthOS#loadCredentials(DBPDataSourceContainer, DBPConnectionConfiguration)}
   * with {@code dataSource}, {@code configuration}.
   *
   * <p>Method under test: {@link OracleAuthOS#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "OracleAuthOSCredentials OracleAuthOS.loadCredentials(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testLoadCredentialsWithDataSourceConfiguration() {
    // Arrange
    OracleAuthOS oracleAuthOS = new OracleAuthOS();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(true);
    when(dbpDriver.getDataSourceProvider()).thenReturn(mock(DBPDataSourceProvider.class));

    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getDriver()).thenReturn(dbpDriver);

    // Act
    OracleAuthOSCredentials actualLoadCredentialsResult =
        oracleAuthOS.loadCredentials(dataSource, new DBPConnectionConfiguration());

    // Assert
    verify(dataSource, atLeast(1)).getDriver();
    verify(dbpDriver).getDataSourceProvider();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertNull(actualLoadCredentialsResult.getUserName());
    assertNull(actualLoadCredentialsResult.getUserPassword());
    assertTrue(actualLoadCredentialsResult.isComplete());
  }

  /**
   * Test {@link OracleAuthOS#loadCredentials(DBPDataSourceContainer, DBPConnectionConfiguration)}
   * with {@code dataSource}, {@code configuration}.
   *
   * <ul>
   *   <li>Given {@code iloveyou}.
   * </ul>
   *
   * <p>Method under test: {@link OracleAuthOS#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "OracleAuthOSCredentials OracleAuthOS.loadCredentials(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testLoadCredentialsWithDataSourceConfiguration_givenIloveyou() {
    // Arrange
    OracleAuthOS oracleAuthOS = new OracleAuthOS();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);
    when(dbpDriver.getDataSourceProvider()).thenReturn(mock(DBPDataSourceProvider.class));

    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getDriver()).thenReturn(dbpDriver);

    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setUserPassword("iloveyou");

    // Act
    OracleAuthOSCredentials actualLoadCredentialsResult =
        oracleAuthOS.loadCredentials(dataSource, configuration);

    // Assert
    verify(dataSource, atLeast(1)).getDriver();
    verify(dbpDriver).getDataSourceProvider();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertNull(actualLoadCredentialsResult.getUserName());
    assertNull(actualLoadCredentialsResult.getUserPassword());
    assertTrue(actualLoadCredentialsResult.isComplete());
  }

  /**
   * Test {@link OracleAuthOS#loadCredentials(DBPDataSourceContainer, DBPConnectionConfiguration)}
   * with {@code dataSource}, {@code configuration}.
   *
   * <ul>
   *   <li>When {@link DBPConnectionConfiguration#DBPConnectionConfiguration()}.
   * </ul>
   *
   * <p>Method under test: {@link OracleAuthOS#loadCredentials(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "OracleAuthOSCredentials OracleAuthOS.loadCredentials(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testLoadCredentialsWithDataSourceConfiguration_whenDBPConnectionConfiguration() {
    // Arrange
    OracleAuthOS oracleAuthOS = new OracleAuthOS();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);
    when(dbpDriver.getDataSourceProvider()).thenReturn(mock(DBPDataSourceProvider.class));

    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getDriver()).thenReturn(dbpDriver);

    // Act
    OracleAuthOSCredentials actualLoadCredentialsResult =
        oracleAuthOS.loadCredentials(dataSource, new DBPConnectionConfiguration());

    // Assert
    verify(dataSource, atLeast(1)).getDriver();
    verify(dbpDriver).getDataSourceProvider();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertNull(actualLoadCredentialsResult.getUserName());
    assertNull(actualLoadCredentialsResult.getUserPassword());
    assertTrue(actualLoadCredentialsResult.isComplete());
  }

  /**
   * Test {@link OracleAuthOS#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * OracleAuthOSCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code OracleAuthOSCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link OracleAuthOS#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * OracleAuthOSCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object OracleAuthOS.initAuthentication(DBRProgressMonitor, DBPDataSource, OracleAuthOSCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceOracleAuthOSCredentialsDBPConnectionConfigurationProperties()
          throws DBException {
    // Arrange
    OracleAuthOS oracleAuthOS = new OracleAuthOS();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    OracleAuthOSCredentials credentials = new OracleAuthOSCredentials();
    credentials.setUserName("janedoe");
    credentials.setUserPassword("iloveyou");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        oracleAuthOS.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    assertTrue(actualInitAuthenticationResult instanceof OracleAuthOSCredentials);
    assertEquals(3, connProperties.size());
    assertEquals(
        "iloveyou", ((OracleAuthOSCredentials) actualInitAuthenticationResult).getUserPassword());
    assertEquals("iloveyou", connProperties.get("password"));
    assertEquals(
        "janedoe", ((OracleAuthOSCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals("janedoe", connProperties.get("user"));
    assertTrue(connProperties.containsKey("v$session.osuser"));
  }

  /**
   * Test {@link OracleAuthOS#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * OracleAuthOSCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code OracleAuthOSCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link OracleAuthOS#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * OracleAuthOSCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object OracleAuthOS.initAuthentication(DBRProgressMonitor, DBPDataSource, OracleAuthOSCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceOracleAuthOSCredentialsDBPConnectionConfigurationProperties2()
          throws DBException {
    // Arrange
    OracleAuthOS oracleAuthOS = new OracleAuthOS();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    OracleAuthOSCredentials credentials = new OracleAuthOSCredentials();
    credentials.setUserName("");
    credentials.setUserPassword("iloveyou");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        oracleAuthOS.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    assertTrue(actualInitAuthenticationResult instanceof OracleAuthOSCredentials);
    assertEquals(2, connProperties.size());
    assertEquals("", ((OracleAuthOSCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals(
        "iloveyou", ((OracleAuthOSCredentials) actualInitAuthenticationResult).getUserPassword());
    assertEquals("iloveyou", connProperties.get("password"));
    assertTrue(connProperties.containsKey("v$session.osuser"));
  }

  /**
   * Test {@link OracleAuthOS#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * OracleAuthOSCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code OracleAuthOSCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link OracleAuthOS#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * OracleAuthOSCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object OracleAuthOS.initAuthentication(DBRProgressMonitor, DBPDataSource, OracleAuthOSCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceOracleAuthOSCredentialsDBPConnectionConfigurationProperties3()
          throws DBException {
    // Arrange
    OracleAuthOS oracleAuthOS = new OracleAuthOS();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    OracleAuthOSCredentials credentials = new OracleAuthOSCredentials();
    credentials.setUserName(null);
    credentials.setUserPassword("iloveyou");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        oracleAuthOS.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    assertTrue(actualInitAuthenticationResult instanceof OracleAuthOSCredentials);
    assertEquals(2, connProperties.size());
    assertEquals(
        "iloveyou", ((OracleAuthOSCredentials) actualInitAuthenticationResult).getUserPassword());
    assertEquals("iloveyou", connProperties.get("password"));
    assertNull(((OracleAuthOSCredentials) actualInitAuthenticationResult).getUserName());
    assertTrue(connProperties.containsKey("v$session.osuser"));
  }

  /**
   * Test {@link OracleAuthOS#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * OracleAuthOSCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code OracleAuthOSCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link OracleAuthOS#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * OracleAuthOSCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object OracleAuthOS.initAuthentication(DBRProgressMonitor, DBPDataSource, OracleAuthOSCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceOracleAuthOSCredentialsDBPConnectionConfigurationProperties4()
          throws DBException {
    // Arrange
    OracleAuthOS oracleAuthOS = new OracleAuthOS();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    OracleAuthOSCredentials credentials = new OracleAuthOSCredentials();
    credentials.setUserName("janedoe");
    credentials.setUserPassword("");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        oracleAuthOS.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof OracleAuthOSCredentials);
    assertEquals(2, connProperties.size());
    assertEquals("", ((OracleAuthOSCredentials) actualInitAuthenticationResult).getUserPassword());
    assertEquals(
        "janedoe", ((OracleAuthOSCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals("janedoe", connProperties.get("user"));
    assertTrue(connProperties.containsKey("v$session.osuser"));
  }

  /**
   * Test {@link OracleAuthOS#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * OracleAuthOSCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code OracleAuthOSCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link OracleAuthOS#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * OracleAuthOSCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object OracleAuthOS.initAuthentication(DBRProgressMonitor, DBPDataSource, OracleAuthOSCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceOracleAuthOSCredentialsDBPConnectionConfigurationProperties5()
          throws DBException {
    // Arrange
    OracleAuthOS oracleAuthOS = new OracleAuthOS();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    OracleAuthOSCredentials credentials = new OracleAuthOSCredentials();
    credentials.setUserName("janedoe");
    credentials.setUserPassword("");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        oracleAuthOS.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof OracleAuthOSCredentials);
    assertEquals(3, connProperties.size());
    assertEquals("", ((OracleAuthOSCredentials) actualInitAuthenticationResult).getUserPassword());
    assertEquals("", connProperties.get("password"));
    assertEquals(
        "janedoe", ((OracleAuthOSCredentials) actualInitAuthenticationResult).getUserName());
    assertEquals("janedoe", connProperties.get("user"));
    assertTrue(connProperties.containsKey("v$session.osuser"));
  }

  /**
   * Test {@link OracleAuthOS#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * OracleAuthOSCredentials, DBPConnectionConfiguration, Properties)} with {@code
   * DBRProgressMonitor}, {@code DBPDataSource}, {@code OracleAuthOSCredentials}, {@code
   * DBPConnectionConfiguration}, {@code Properties}.
   *
   * <p>Method under test: {@link OracleAuthOS#initAuthentication(DBRProgressMonitor, DBPDataSource,
   * OracleAuthOSCredentials, DBPConnectionConfiguration, Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object OracleAuthOS.initAuthentication(DBRProgressMonitor, DBPDataSource, OracleAuthOSCredentials, DBPConnectionConfiguration, Properties)"
  })
  public void
      testInitAuthenticationWithDBRProgressMonitorDBPDataSourceOracleAuthOSCredentialsDBPConnectionConfigurationProperties6()
          throws DBException {
    // Arrange
    OracleAuthOS oracleAuthOS = new OracleAuthOS();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isAllowsEmptyPassword()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    OracleAuthOSCredentials credentials = new OracleAuthOSCredentials();
    credentials.setUserName("");
    credentials.setUserPassword("");
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    Properties connProperties = new Properties();

    // Act
    Object actualInitAuthenticationResult =
        oracleAuthOS.initAuthentication(
            monitor, dataSource, credentials, configuration, connProperties);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isAllowsEmptyPassword();
    assertTrue(actualInitAuthenticationResult instanceof OracleAuthOSCredentials);
    assertEquals(1, connProperties.size());
    assertTrue(((OracleAuthOSCredentials) actualInitAuthenticationResult).isComplete());
    assertEquals(System.getProperty("user.name"), connProperties.get("v$session.osuser"));
    assertSame(credentials, actualInitAuthenticationResult);
  }

  /**
   * Test new {@link OracleAuthOS} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link OracleAuthOS}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OracleAuthOS.<init>()"})
  public void testNewOracleAuthOS() {
    // Arrange and Act
    OracleAuthOS actualOracleAuthOS = new OracleAuthOS();

    // Assert
    assertTrue(actualOracleAuthOS.isUserNameApplicable());
    assertTrue(actualOracleAuthOS.isUserPasswordApplicable());
  }
}
