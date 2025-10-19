package org.jkiss.dbeaver.model.net;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.app.DBPDataSourceRegistry;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.jkiss.dbeaver.model.connection.DBPDriverConfigurationType;
import org.jkiss.dbeaver.model.net.DBWUtils.ConnectivityParameters;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBWUtilsDiffblueTest {
  /**
   * Test {@link DBWUtils#updateConfigWithTunnelInfo(DBWHandlerConfiguration,
   * DBPConnectionConfiguration, String, int)}.
   *
   * <p>Method under test: {@link DBWUtils#updateConfigWithTunnelInfo(DBWHandlerConfiguration,
   * DBPConnectionConfiguration, String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBWUtils.updateConfigWithTunnelInfo(DBWHandlerConfiguration, DBPConnectionConfiguration, String, int)"
  })
  public void testUpdateConfigWithTunnelInfo() {
    // Arrange
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(new DBWHandlerConfiguration());
    configuration.setDataSource(null);

    DBPConnectionConfiguration connectionInfo =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    connectionInfo.setHostName(DBWUtils.LOCALHOST_NAME);

    // Act
    DBWUtils.updateConfigWithTunnelInfo(configuration, connectionInfo, "", 8080);

    // Assert
    assertEquals("8080", connectionInfo.getHostPort());
    assertNull(connectionInfo.getUrl());
    assertEquals(DBWUtils.LOCALHOST_NAME, connectionInfo.getHostName());
  }

  /**
   * Test {@link DBWUtils#updateConfigWithTunnelInfo(DBWHandlerConfiguration,
   * DBPConnectionConfiguration, String, int)}.
   *
   * <p>Method under test: {@link DBWUtils#updateConfigWithTunnelInfo(DBWHandlerConfiguration,
   * DBPConnectionConfiguration, String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBWUtils.updateConfigWithTunnelInfo(DBWHandlerConfiguration, DBPConnectionConfiguration, String, int)"
  })
  public void testUpdateConfigWithTunnelInfo2() {
    // Arrange
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(new DBWHandlerConfiguration());
    configuration.setDataSource(null);

    DBPConnectionConfiguration connectionInfo =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    connectionInfo.setHostName(DBWUtils.LOCALHOST_NAME);

    // Act
    DBWUtils.updateConfigWithTunnelInfo(configuration, connectionInfo, null, 8080);

    // Assert
    assertEquals("8080", connectionInfo.getHostPort());
    assertNull(connectionInfo.getUrl());
    assertEquals(DBWUtils.LOCALHOST_NAME, connectionInfo.getHostName());
  }

  /**
   * Test {@link DBWUtils#updateConfigWithTunnelInfo(DBWHandlerConfiguration,
   * DBPConnectionConfiguration, String, int)}.
   *
   * <p>Method under test: {@link DBWUtils#updateConfigWithTunnelInfo(DBWHandlerConfiguration,
   * DBPConnectionConfiguration, String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBWUtils.updateConfigWithTunnelInfo(DBWHandlerConfiguration, DBPConnectionConfiguration, String, int)"
  })
  public void testUpdateConfigWithTunnelInfo3() {
    // Arrange
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(new DBWHandlerConfiguration());
    configuration.setDataSource(null);

    DBPConnectionConfiguration connectionInfo =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    connectionInfo.setHostName(DBWUtils.LOCAL_NAME);

    // Act
    DBWUtils.updateConfigWithTunnelInfo(configuration, connectionInfo, "", 8080);

    // Assert
    assertEquals("8080", connectionInfo.getHostPort());
    assertNull(connectionInfo.getUrl());
    assertEquals(DBWUtils.LOCAL_NAME, connectionInfo.getHostName());
  }

  /**
   * Test {@link DBWUtils#updateConfigWithTunnelInfo(DBWHandlerConfiguration,
   * DBPConnectionConfiguration, String, int)}.
   *
   * <p>Method under test: {@link DBWUtils#updateConfigWithTunnelInfo(DBWHandlerConfiguration,
   * DBPConnectionConfiguration, String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBWUtils.updateConfigWithTunnelInfo(DBWHandlerConfiguration, DBPConnectionConfiguration, String, int)"
  })
  public void testUpdateConfigWithTunnelInfo4() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.getConnectionURL(Mockito.<DBPConnectionConfiguration>any()))
        .thenReturn("https://example.org/example");

    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getDriver()).thenReturn(dbpDriver);

    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(new DBWHandlerConfiguration());
    configuration.setDataSource(dataSource);

    DBPConnectionConfiguration connectionInfo =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    connectionInfo.setHostName(DBWUtils.LOCALHOST_NAME);

    // Act
    DBWUtils.updateConfigWithTunnelInfo(configuration, connectionInfo, "not empty", 8080);

    // Assert
    verify(dataSource, atLeast(1)).getDriver();
    verify(dbpDriver).getConnectionURL(isA(DBPConnectionConfiguration.class));
    assertEquals("8080", connectionInfo.getHostPort());
    assertEquals("https://example.org/example", connectionInfo.getUrl());
    assertEquals("not empty", connectionInfo.getHostName());
  }

  /**
   * Test {@link DBWUtils#updateConfigWithTunnelInfo(DBWHandlerConfiguration,
   * DBPConnectionConfiguration, String, int)}.
   *
   * <ul>
   *   <li>Given {@code Host Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#updateConfigWithTunnelInfo(DBWHandlerConfiguration,
   * DBPConnectionConfiguration, String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBWUtils.updateConfigWithTunnelInfo(DBWHandlerConfiguration, DBPConnectionConfiguration, String, int)"
  })
  public void testUpdateConfigWithTunnelInfo_givenHostName() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.getConnectionURL(Mockito.<DBPConnectionConfiguration>any()))
        .thenReturn("https://example.org/example");

    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getDriver()).thenReturn(dbpDriver);

    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(new DBWHandlerConfiguration());
    configuration.setDataSource(dataSource);

    DBPConnectionConfiguration connectionInfo =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    connectionInfo.setHostName("Host Name");

    // Act
    DBWUtils.updateConfigWithTunnelInfo(configuration, connectionInfo, "", 8080);

    // Assert
    verify(dataSource, atLeast(1)).getDriver();
    verify(dbpDriver).getConnectionURL(isA(DBPConnectionConfiguration.class));
    assertEquals("8080", connectionInfo.getHostPort());
    assertEquals("https://example.org/example", connectionInfo.getUrl());
  }

  /**
   * Test {@link DBWUtils#updateConfigWithTunnelInfo(DBWHandlerConfiguration,
   * DBPConnectionConfiguration, String, int)}.
   *
   * <ul>
   *   <li>Then {@link DBPConnectionConfiguration#DBPConnectionConfiguration()} HostPort is {@code
   *       8080}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#updateConfigWithTunnelInfo(DBWHandlerConfiguration,
   * DBPConnectionConfiguration, String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBWUtils.updateConfigWithTunnelInfo(DBWHandlerConfiguration, DBPConnectionConfiguration, String, int)"
  })
  public void testUpdateConfigWithTunnelInfo_thenDBPConnectionConfigurationHostPortIs8080() {
    // Arrange
    DBWHandlerConfiguration configuration = new DBWHandlerConfiguration();
    DBPConnectionConfiguration connectionInfo = new DBPConnectionConfiguration();

    // Act
    DBWUtils.updateConfigWithTunnelInfo(
        configuration, connectionInfo, DBWUtils.LOCALHOST_NAME, 8080);

    // Assert
    assertEquals("8080", connectionInfo.getHostPort());
    assertNull(connectionInfo.getUrl());
    assertEquals(DBWUtils.LOCALHOST_NAME, connectionInfo.getHostName());
  }

  /**
   * Test {@link DBWUtils#updateConfigWithTunnelInfo(DBWHandlerConfiguration,
   * DBPConnectionConfiguration, String, int)}.
   *
   * <ul>
   *   <li>Then throw {@link MatchException}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#updateConfigWithTunnelInfo(DBWHandlerConfiguration,
   * DBPConnectionConfiguration, String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBWUtils.updateConfigWithTunnelInfo(DBWHandlerConfiguration, DBPConnectionConfiguration, String, int)"
  })
  public void testUpdateConfigWithTunnelInfo_thenThrowMatchException() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    MatchException matchException = new MatchException("0123456789ABCDEF", new Throwable());
    when(dbpDriver.getConnectionURL(Mockito.<DBPConnectionConfiguration>any()))
        .thenThrow(matchException);

    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getDriver()).thenReturn(dbpDriver);

    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(new DBWHandlerConfiguration());
    configuration.setDataSource(dataSource);

    DBPConnectionConfiguration connectionInfo =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    connectionInfo.setHostName(DBWUtils.LOCALHOST_NAME);

    // Act and Assert
    assertThrows(
        MatchException.class,
        () ->
            DBWUtils.updateConfigWithTunnelInfo(configuration, connectionInfo, "not empty", 8080));
    verify(dataSource, atLeast(1)).getDriver();
    verify(dbpDriver).getConnectionURL(isA(DBPConnectionConfiguration.class));
  }

  /**
   * Test {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}.
   *
   * <p>Method under test: {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBWUtils.getTargetTunnelHostName(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testGetTargetTunnelHostName() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    dbpConnectionConfiguration.setConfigProfileName("");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);

    // Act
    String actualTargetTunnelHostName =
        DBWUtils.getTargetTunnelHostName(dataSourceContainer, new DBPConnectionConfiguration());

    // Assert
    verify(dataSourceContainer).getConnectionConfiguration();
    assertEquals("", actualTargetTunnelHostName);
  }

  /**
   * Test {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}.
   *
   * <p>Method under test: {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBWUtils.getTargetTunnelHostName(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testGetTargetTunnelHostName2() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    MatchException matchException = new MatchException("0123456789ABCDEF", new Throwable());
    when(dataSourceContainer.getConnectionConfiguration()).thenThrow(matchException);

    // Act and Assert
    assertThrows(
        MatchException.class,
        () ->
            DBWUtils.getTargetTunnelHostName(
                dataSourceContainer, new DBPConnectionConfiguration()));
    verify(dataSourceContainer).getConnectionConfiguration();
  }

  /**
   * Test {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}.
   *
   * <p>Method under test: {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBWUtils.getTargetTunnelHostName(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testGetTargetTunnelHostName3() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    dbpConnectionConfiguration.setConfigProfileName("Connection Configuration");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    MatchException matchException = new MatchException("0123456789ABCDEF", new Throwable());
    when(dataSourceContainer.getRegistry()).thenThrow(matchException);
    when(dataSourceContainer.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);

    // Act and Assert
    assertThrows(
        MatchException.class,
        () ->
            DBWUtils.getTargetTunnelHostName(
                dataSourceContainer, new DBPConnectionConfiguration()));
    verify(dataSourceContainer).getConnectionConfiguration();
    verify(dataSourceContainer).getRegistry();
  }

  /**
   * Test {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}.
   *
   * <p>Method under test: {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBWUtils.getTargetTunnelHostName(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testGetTargetTunnelHostName4() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    dbpConnectionConfiguration.setConfigProfileName("Connection Configuration");

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    MatchException matchException = new MatchException("0123456789ABCDEF", new Throwable());
    when(dbpDataSourceRegistry.getNetworkProfile(Mockito.<String>any(), Mockito.<String>any()))
        .thenThrow(matchException);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);
    when(dataSourceContainer.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);

    // Act and Assert
    assertThrows(
        MatchException.class,
        () ->
            DBWUtils.getTargetTunnelHostName(
                dataSourceContainer, new DBPConnectionConfiguration()));
    verify(dataSourceContainer).getConnectionConfiguration();
    verify(dataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getNetworkProfile(null, "Connection Configuration");
  }

  /**
   * Test {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}.
   *
   * <ul>
   *   <li>Given {@link DBWHandlerConfiguration#DBWHandlerConfiguration()}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBWUtils.getTargetTunnelHostName(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testGetTargetTunnelHostName_givenDBWHandlerConfiguration() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    dbpConnectionConfiguration.setConfigProfileName("Connection Configuration");

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getNetworkProfile(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);
    when(dataSourceContainer.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);

    DBPConnectionConfiguration cfg = new DBPConnectionConfiguration();
    cfg.updateHandler(new DBWHandlerConfiguration());

    // Act
    String actualTargetTunnelHostName = DBWUtils.getTargetTunnelHostName(dataSourceContainer, cfg);

    // Assert
    verify(dataSourceContainer).getConnectionConfiguration();
    verify(dataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getNetworkProfile(null, "Connection Configuration");
    assertEquals("", actualTargetTunnelHostName);
  }

  /**
   * Test {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}.
   *
   * <ul>
   *   <li>Given {@code Host Name}.
   *   <li>Then return {@code Host Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBWUtils.getTargetTunnelHostName(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testGetTargetTunnelHostName_givenHostName_thenReturnHostName() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);

    DBPConnectionConfiguration cfg = new DBPConnectionConfiguration();
    cfg.setHostName("Host Name");

    // Act and Assert
    assertEquals("Host Name", DBWUtils.getTargetTunnelHostName(dataSourceContainer, cfg));
  }

  /**
   * Test {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}.
   *
   * <ul>
   *   <li>Given {@link DBWUtils#LOCAL_NAME}.
   *   <li>Then return {@link DBWUtils#LOCAL_NAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBWUtils.getTargetTunnelHostName(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testGetTargetTunnelHostName_givenLocal_name_thenReturnLocal_name() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    dbpConnectionConfiguration.setConfigProfileName("Connection Configuration");

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getNetworkProfile(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);
    when(dataSourceContainer.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);

    DBPConnectionConfiguration cfg = new DBPConnectionConfiguration();
    cfg.setHostName(DBWUtils.LOCAL_NAME);

    // Act
    String actualTargetTunnelHostName = DBWUtils.getTargetTunnelHostName(dataSourceContainer, cfg);

    // Assert
    verify(dataSourceContainer).getConnectionConfiguration();
    verify(dataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getNetworkProfile(null, "Connection Configuration");
    assertEquals(DBWUtils.LOCAL_NAME, actualTargetTunnelHostName);
  }

  /**
   * Test {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}.
   *
   * <ul>
   *   <li>Given {@link DBWUtils#LOCALHOST_NAME}.
   *   <li>Then return {@link DBWUtils#LOCALHOST_NAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBWUtils.getTargetTunnelHostName(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testGetTargetTunnelHostName_givenLocalhost_name_thenReturnLocalhost_name() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    dbpConnectionConfiguration.setConfigProfileName("Connection Configuration");

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getNetworkProfile(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);
    when(dataSourceContainer.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);

    DBPConnectionConfiguration cfg = new DBPConnectionConfiguration();
    cfg.setHostName(DBWUtils.LOCALHOST_NAME);

    // Act
    String actualTargetTunnelHostName = DBWUtils.getTargetTunnelHostName(dataSourceContainer, cfg);

    // Assert
    verify(dataSourceContainer).getConnectionConfiguration();
    verify(dataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getNetworkProfile(null, "Connection Configuration");
    assertEquals(DBWUtils.LOCALHOST_NAME, actualTargetTunnelHostName);
  }

  /**
   * Test {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}.
   *
   * <ul>
   *   <li>Given {@link DBWUtils#LOOPBACK_HOST_NAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBWUtils.getTargetTunnelHostName(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testGetTargetTunnelHostName_givenLoopback_host_name() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    dbpConnectionConfiguration.setConfigProfileName("Connection Configuration");

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getNetworkProfile(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);
    when(dataSourceContainer.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);

    DBPConnectionConfiguration cfg = new DBPConnectionConfiguration();
    cfg.setHostName(DBWUtils.LOOPBACK_HOST_NAME);

    // Act
    DBWUtils.getTargetTunnelHostName(dataSourceContainer, cfg);

    // Assert
    verify(dataSourceContainer).getConnectionConfiguration();
    verify(dataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getNetworkProfile(null, "Connection Configuration");
  }

  /**
   * Test {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}.
   *
   * <ul>
   *   <li>Then return {@link DBWUtils#LOOPBACK_IPV6_FULL_HOST_NAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBWUtils.getTargetTunnelHostName(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testGetTargetTunnelHostName_thenReturnLoopback_ipv6_full_host_name() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    dbpConnectionConfiguration.setConfigProfileName("Connection Configuration");

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getNetworkProfile(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);
    when(dataSourceContainer.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);

    DBPConnectionConfiguration cfg = new DBPConnectionConfiguration();
    cfg.setHostName(DBWUtils.LOOPBACK_IPV6_FULL_HOST_NAME);

    // Act
    String actualTargetTunnelHostName = DBWUtils.getTargetTunnelHostName(dataSourceContainer, cfg);

    // Assert
    verify(dataSourceContainer).getConnectionConfiguration();
    verify(dataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getNetworkProfile(null, "Connection Configuration");
    assertEquals(DBWUtils.LOOPBACK_IPV6_FULL_HOST_NAME, actualTargetTunnelHostName);
  }

  /**
   * Test {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}.
   *
   * <ul>
   *   <li>Then return {@link DBWUtils#LOOPBACK_IPV6_HOST_NAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBWUtils.getTargetTunnelHostName(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testGetTargetTunnelHostName_thenReturnLoopback_ipv6_host_name() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    dbpConnectionConfiguration.setConfigProfileName("Connection Configuration");

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getNetworkProfile(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);
    when(dataSourceContainer.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);

    DBPConnectionConfiguration cfg = new DBPConnectionConfiguration();
    cfg.setHostName(DBWUtils.LOOPBACK_IPV6_HOST_NAME);

    // Act
    String actualTargetTunnelHostName = DBWUtils.getTargetTunnelHostName(dataSourceContainer, cfg);

    // Assert
    verify(dataSourceContainer).getConnectionConfiguration();
    verify(dataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getNetworkProfile(null, "Connection Configuration");
    assertEquals(DBWUtils.LOOPBACK_IPV6_HOST_NAME, actualTargetTunnelHostName);
  }

  /**
   * Test {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}.
   *
   * <ul>
   *   <li>When {@link DBPConnectionConfiguration#DBPConnectionConfiguration()}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#getTargetTunnelHostName(DBPDataSourceContainer,
   * DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBWUtils.getTargetTunnelHostName(DBPDataSourceContainer, DBPConnectionConfiguration)"
  })
  public void testGetTargetTunnelHostName_whenDBPConnectionConfiguration_thenReturnEmptyString() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    dbpConnectionConfiguration.setConfigProfileName("Connection Configuration");

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getNetworkProfile(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);
    when(dataSourceContainer.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);

    // Act
    String actualTargetTunnelHostName =
        DBWUtils.getTargetTunnelHostName(dataSourceContainer, new DBPConnectionConfiguration());

    // Assert
    verify(dataSourceContainer).getConnectionConfiguration();
    verify(dataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getNetworkProfile(null, "Connection Configuration");
    assertEquals("", actualTargetTunnelHostName);
  }

  /**
   * Test {@link DBWUtils#getTunnelHostFromConfig(DBWHandlerConfiguration)}.
   *
   * <ul>
   *   <li>When {@link DBWHandlerConfiguration#DBWHandlerConfiguration()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#getTunnelHostFromConfig(DBWHandlerConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBWUtils.getTunnelHostFromConfig(DBWHandlerConfiguration)"})
  public void testGetTunnelHostFromConfig_whenDBWHandlerConfiguration_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DBWUtils.getTunnelHostFromConfig(new DBWHandlerConfiguration()));
  }

  /**
   * Test {@link DBWUtils#isLocalAddress(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#isLocalAddress(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBWUtils.isLocalAddress(String)"})
  public void testIsLocalAddress_whenEmptyString_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(DBWUtils.isLocalAddress(""));
  }

  /**
   * Test {@link DBWUtils#isLocalAddress(String)}.
   *
   * <ul>
   *   <li>When {@code Host Text}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#isLocalAddress(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBWUtils.isLocalAddress(String)"})
  public void testIsLocalAddress_whenHostText_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DBWUtils.isLocalAddress("Host Text"));
  }

  /**
   * Test {@link DBWUtils#isLocalAddress(String)}.
   *
   * <ul>
   *   <li>When {@link DBWUtils#LOCAL_NAME}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#isLocalAddress(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBWUtils.isLocalAddress(String)"})
  public void testIsLocalAddress_whenLocal_name_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(DBWUtils.isLocalAddress(DBWUtils.LOCAL_NAME));
  }

  /**
   * Test {@link DBWUtils#isLocalAddress(String)}.
   *
   * <ul>
   *   <li>When {@link DBWUtils#LOCALHOST_NAME}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#isLocalAddress(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBWUtils.isLocalAddress(String)"})
  public void testIsLocalAddress_whenLocalhost_name_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(DBWUtils.isLocalAddress(DBWUtils.LOCALHOST_NAME));
  }

  /**
   * Test {@link DBWUtils#isLocalAddress(String)}.
   *
   * <ul>
   *   <li>When {@link DBWUtils#LOOPBACK_HOST_NAME}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#isLocalAddress(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBWUtils.isLocalAddress(String)"})
  public void testIsLocalAddress_whenLoopback_host_name_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(DBWUtils.isLocalAddress(DBWUtils.LOOPBACK_HOST_NAME));
  }

  /**
   * Test {@link DBWUtils#isLocalAddress(String)}.
   *
   * <ul>
   *   <li>When {@link DBWUtils#LOOPBACK_IPV6_FULL_HOST_NAME}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#isLocalAddress(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBWUtils.isLocalAddress(String)"})
  public void testIsLocalAddress_whenLoopback_ipv6_full_host_name_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(DBWUtils.isLocalAddress(DBWUtils.LOOPBACK_IPV6_FULL_HOST_NAME));
  }

  /**
   * Test {@link DBWUtils#isLocalAddress(String)}.
   *
   * <ul>
   *   <li>When {@link DBWUtils#LOOPBACK_IPV6_HOST_NAME}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#isLocalAddress(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBWUtils.isLocalAddress(String)"})
  public void testIsLocalAddress_whenLoopback_ipv6_host_name_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(DBWUtils.isLocalAddress(DBWUtils.LOOPBACK_IPV6_HOST_NAME));
  }

  /**
   * Test {@link DBWUtils#isLocalAddress(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#isLocalAddress(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBWUtils.isLocalAddress(String)"})
  public void testIsLocalAddress_whenNull_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(DBWUtils.isLocalAddress(null));
  }

  /**
   * Test {@link DBWUtils#getNetworkProfile(DBPDataSourceContainer)}.
   *
   * <p>Method under test: {@link DBWUtils#getNetworkProfile(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBWNetworkProfile DBWUtils.getNetworkProfile(DBPDataSourceContainer)"})
  public void testGetNetworkProfile() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    dbpConnectionConfiguration.setConfigProfileName("");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);

    // Act
    DBWNetworkProfile actualNetworkProfile = DBWUtils.getNetworkProfile(dataSourceContainer);

    // Assert
    verify(dataSourceContainer).getConnectionConfiguration();
    assertNull(actualNetworkProfile);
  }

  /**
   * Test {@link DBWUtils#getNetworkProfile(DBPDataSourceContainer)}.
   *
   * <p>Method under test: {@link DBWUtils#getNetworkProfile(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBWNetworkProfile DBWUtils.getNetworkProfile(DBPDataSourceContainer)"})
  public void testGetNetworkProfile2() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    dbpConnectionConfiguration.setConfigProfileName(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);

    // Act
    DBWNetworkProfile actualNetworkProfile = DBWUtils.getNetworkProfile(dataSourceContainer);

    // Assert
    verify(dataSourceContainer).getConnectionConfiguration();
    assertNull(actualNetworkProfile);
  }

  /**
   * Test {@link DBWUtils#getNetworkProfile(DBPDataSourceContainer)}.
   *
   * <p>Method under test: {@link DBWUtils#getNetworkProfile(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBWNetworkProfile DBWUtils.getNetworkProfile(DBPDataSourceContainer)"})
  public void testGetNetworkProfile3() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    MatchException matchException = new MatchException("0123456789ABCDEF", new Throwable());
    when(dataSourceContainer.getConnectionConfiguration()).thenThrow(matchException);

    // Act and Assert
    assertThrows(MatchException.class, () -> DBWUtils.getNetworkProfile(dataSourceContainer));
    verify(dataSourceContainer).getConnectionConfiguration();
  }

  /**
   * Test {@link DBWUtils#getNetworkProfile(DBPDataSourceContainer)}.
   *
   * <p>Method under test: {@link DBWUtils#getNetworkProfile(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBWNetworkProfile DBWUtils.getNetworkProfile(DBPDataSourceContainer)"})
  public void testGetNetworkProfile4() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    dbpConnectionConfiguration.setConfigProfileName("Connection Configuration");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    MatchException matchException = new MatchException("0123456789ABCDEF", new Throwable());
    when(dataSourceContainer.getRegistry()).thenThrow(matchException);
    when(dataSourceContainer.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);

    // Act and Assert
    assertThrows(MatchException.class, () -> DBWUtils.getNetworkProfile(dataSourceContainer));
    verify(dataSourceContainer).getConnectionConfiguration();
    verify(dataSourceContainer).getRegistry();
  }

  /**
   * Test {@link DBWUtils#getNetworkProfile(DBPDataSourceContainer)}.
   *
   * <p>Method under test: {@link DBWUtils#getNetworkProfile(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBWNetworkProfile DBWUtils.getNetworkProfile(DBPDataSourceContainer)"})
  public void testGetNetworkProfile5() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    dbpConnectionConfiguration.setConfigProfileName("Connection Configuration");

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    MatchException matchException = new MatchException("0123456789ABCDEF", new Throwable());
    when(dbpDataSourceRegistry.getNetworkProfile(Mockito.<String>any(), Mockito.<String>any()))
        .thenThrow(matchException);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);
    when(dataSourceContainer.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);

    // Act and Assert
    assertThrows(MatchException.class, () -> DBWUtils.getNetworkProfile(dataSourceContainer));
    verify(dataSourceContainer).getConnectionConfiguration();
    verify(dataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getNetworkProfile(null, "Connection Configuration");
  }

  /**
   * Test {@link DBWUtils#getNetworkProfile(DBPDataSourceContainer)}.
   *
   * <ul>
   *   <li>Then return SecretKeyId is {@code global/network-profile/null}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#getNetworkProfile(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBWNetworkProfile DBWUtils.getNetworkProfile(DBPDataSourceContainer)"})
  public void testGetNetworkProfile_thenReturnSecretKeyIdIsGlobalNetworkProfileNull() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    dbpConnectionConfiguration.setConfigProfileName("Connection Configuration");

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getNetworkProfile(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new DBWNetworkProfile());

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);
    when(dataSourceContainer.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);

    // Act
    DBWNetworkProfile actualNetworkProfile = DBWUtils.getNetworkProfile(dataSourceContainer);

    // Assert
    verify(dataSourceContainer).getConnectionConfiguration();
    verify(dataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getNetworkProfile(null, "Connection Configuration");
    assertEquals("global/network-profile/null", actualNetworkProfile.getSecretKeyId());
    assertNull(actualNetworkProfile.getProfileDescription());
    assertNull(actualNetworkProfile.getProfileId());
    assertNull(actualNetworkProfile.getProfileName());
    assertNull(actualNetworkProfile.toString());
    assertNull(actualNetworkProfile.getProfileSource());
    assertNull(actualNetworkProfile.getProject());
    assertTrue(actualNetworkProfile.getConfigurations().isEmpty());
    assertTrue(actualNetworkProfile.getProperties().isEmpty());
    assertTrue(actualNetworkProfile.isExternallyProvided());
  }

  /**
   * Test {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)}.
   *
   * <p>Method under test: {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration,
   * DBPDriver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConnectivityParameters DBWUtils.getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)"
  })
  public void testGetConnectivityParameters() throws DBException {
    // Arrange
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setConfigurationType(DBPDriverConfigurationType.URL);

    DBPDriver driver = mock(DBPDriver.class);
    MatchException matchException = new MatchException("0123456789ABCDEF", new Throwable());
    when(driver.getConnectionURL(Mockito.<DBPConnectionConfiguration>any()))
        .thenThrow(matchException);

    // Act and Assert
    assertThrows(
        MatchException.class, () -> DBWUtils.getConnectivityParameters(configuration, driver));
    verify(driver).getConnectionURL(isA(DBPConnectionConfiguration.class));
  }

  /**
   * Test {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)}.
   *
   * <p>Method under test: {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration,
   * DBPDriver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConnectivityParameters DBWUtils.getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)"
  })
  public void testGetConnectivityParameters2() throws DBException {
    // Arrange
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setConfigurationType(DBPDriverConfigurationType.URL);

    DBPDriver driver = mock(DBPDriver.class);
    MatchException matchException = new MatchException("0123456789ABCDEF", new Throwable());
    when(driver.getSampleURL()).thenThrow(matchException);
    when(driver.getConnectionURL(Mockito.<DBPConnectionConfiguration>any()))
        .thenReturn("not empty");

    // Act and Assert
    assertThrows(
        MatchException.class, () -> DBWUtils.getConnectivityParameters(configuration, driver));
    verify(driver).getConnectionURL(isA(DBPConnectionConfiguration.class));
    verify(driver).getSampleURL();
  }

  /**
   * Test {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)}.
   *
   * <p>Method under test: {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration,
   * DBPDriver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConnectivityParameters DBWUtils.getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)"
  })
  public void testGetConnectivityParameters3() throws DBException {
    // Arrange
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setConfigurationType(DBPDriverConfigurationType.URL);

    DBPDriver driver = mock(DBPDriver.class);
    when(driver.getSampleURL()).thenReturn("https://example.org/example");
    when(driver.getConnectionURL(Mockito.<DBPConnectionConfiguration>any()))
        .thenReturn("https://example.org/example");

    // Act
    ConnectivityParameters actualConnectivityParameters =
        DBWUtils.getConnectivityParameters(configuration, driver);

    // Assert
    verify(driver).getConnectionURL(isA(DBPConnectionConfiguration.class));
    verify(driver, atLeast(1)).getSampleURL();
    assertEquals("example", actualConnectivityParameters.databaseName());
    assertEquals("example.org", actualConnectivityParameters.hostName());
    assertNull(actualConnectivityParameters.hostPort());
    assertNull(actualConnectivityParameters.server());
    assertNull(actualConnectivityParameters.userName());
  }

  /**
   * Test {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>Then return databaseName is {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration,
   * DBPDriver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConnectivityParameters DBWUtils.getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)"
  })
  public void testGetConnectivityParameters_givenFoo_thenReturnDatabaseNameIsFoo()
      throws DBException {
    // Arrange
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setConfigurationType(DBPDriverConfigurationType.URL);

    DBPDriver driver = mock(DBPDriver.class);
    when(driver.getSampleURL()).thenReturn("not empty");
    when(driver.getConnectionURL(Mockito.<DBPConnectionConfiguration>any())).thenReturn("foo");

    // Act
    ConnectivityParameters actualConnectivityParameters =
        DBWUtils.getConnectivityParameters(configuration, driver);

    // Assert
    verify(driver).getConnectionURL(isA(DBPConnectionConfiguration.class));
    verify(driver, atLeast(1)).getSampleURL();
    assertEquals("foo", actualConnectivityParameters.databaseName());
    assertNull(actualConnectivityParameters.hostName());
    assertNull(actualConnectivityParameters.hostPort());
    assertNull(actualConnectivityParameters.server());
    assertNull(actualConnectivityParameters.userName());
  }

  /**
   * Test {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)}.
   *
   * <ul>
   *   <li>Given {@code jdbc:U://U:U@U:U/U}.
   *   <li>Then return databaseName is {@code U}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration,
   * DBPDriver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConnectivityParameters DBWUtils.getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)"
  })
  public void testGetConnectivityParameters_givenJdbcUUUUUU_thenReturnDatabaseNameIsU()
      throws DBException {
    // Arrange
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setConfigurationType(DBPDriverConfigurationType.URL);

    DBPDriver driver = mock(DBPDriver.class);
    when(driver.getSampleURL()).thenReturn("not empty");
    when(driver.getConnectionURL(Mockito.<DBPConnectionConfiguration>any()))
        .thenReturn("jdbc:U://U:U@U:U/U");

    // Act
    ConnectivityParameters actualConnectivityParameters =
        DBWUtils.getConnectivityParameters(configuration, driver);

    // Assert
    verify(driver).getConnectionURL(isA(DBPConnectionConfiguration.class));
    verify(driver, atLeast(1)).getSampleURL();
    assertEquals("U", actualConnectivityParameters.databaseName());
    assertEquals("U", actualConnectivityParameters.hostName());
    assertEquals("U", actualConnectivityParameters.hostPort());
    assertEquals("U", actualConnectivityParameters.userName());
  }

  /**
   * Test {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)}.
   *
   * <ul>
   *   <li>Given {@code jdbc:}.
   *   <li>When {@link DBPDriver} {@link DBPDriver#getConnectionURL(DBPConnectionConfiguration)}
   *       return {@code jdbc:}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration,
   * DBPDriver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConnectivityParameters DBWUtils.getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)"
  })
  public void testGetConnectivityParameters_givenJdbc_whenDBPDriverGetConnectionURLReturnJdbc()
      throws DBException {
    // Arrange
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setConfigurationType(DBPDriverConfigurationType.URL);

    DBPDriver driver = mock(DBPDriver.class);
    when(driver.getSampleURL()).thenReturn("not empty");
    when(driver.getConnectionURL(Mockito.<DBPConnectionConfiguration>any())).thenReturn("jdbc:");

    // Act
    ConnectivityParameters actualConnectivityParameters =
        DBWUtils.getConnectivityParameters(configuration, driver);

    // Assert
    verify(driver).getConnectionURL(isA(DBPConnectionConfiguration.class));
    verify(driver, atLeast(1)).getSampleURL();
    assertNull(actualConnectivityParameters.databaseName());
    assertNull(actualConnectivityParameters.hostName());
    assertNull(actualConnectivityParameters.hostPort());
    assertNull(actualConnectivityParameters.server());
    assertNull(actualConnectivityParameters.userName());
  }

  /**
   * Test {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)}.
   *
   * <ul>
   *   <li>Given {@code jdbc:}.
   *   <li>When {@link DBPDriver} {@link DBPDriver#getConnectionURL(DBPConnectionConfiguration)}
   *       return {@code jdbc:}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration,
   * DBPDriver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConnectivityParameters DBWUtils.getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)"
  })
  public void testGetConnectivityParameters_givenJdbc_whenDBPDriverGetConnectionURLReturnJdbc2()
      throws DBException {
    // Arrange
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setConfigurationType(DBPDriverConfigurationType.URL);

    DBPDriver driver = mock(DBPDriver.class);
    when(driver.getSampleURL()).thenReturn("");
    when(driver.getConnectionURL(Mockito.<DBPConnectionConfiguration>any())).thenReturn("jdbc:");

    // Act
    ConnectivityParameters actualConnectivityParameters =
        DBWUtils.getConnectivityParameters(configuration, driver);

    // Assert
    verify(driver).getConnectionURL(isA(DBPConnectionConfiguration.class));
    verify(driver).getSampleURL();
    assertNull(actualConnectivityParameters.databaseName());
    assertNull(actualConnectivityParameters.hostName());
    assertNull(actualConnectivityParameters.hostPort());
    assertNull(actualConnectivityParameters.server());
    assertNull(actualConnectivityParameters.userName());
  }

  /**
   * Test {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)}.
   *
   * <ul>
   *   <li>Given {@code Server Name}.
   *   <li>Then return server is {@code Server Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration,
   * DBPDriver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConnectivityParameters DBWUtils.getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)"
  })
  public void testGetConnectivityParameters_givenServerName_thenReturnServerIsServerName()
      throws DBException {
    // Arrange
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setServerName("Server Name");

    // Act
    ConnectivityParameters actualConnectivityParameters =
        DBWUtils.getConnectivityParameters(configuration, mock(DBPDriver.class));

    // Assert
    assertEquals("Server Name", actualConnectivityParameters.server());
    assertNull(actualConnectivityParameters.databaseName());
    assertNull(actualConnectivityParameters.hostName());
    assertNull(actualConnectivityParameters.hostPort());
  }

  /**
   * Test {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)}.
   *
   * <ul>
   *   <li>Given {@code /}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration,
   * DBPDriver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConnectivityParameters DBWUtils.getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)"
  })
  public void testGetConnectivityParameters_givenSlash() throws DBException {
    // Arrange
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setConfigurationType(DBPDriverConfigurationType.URL);

    DBPDriver driver = mock(DBPDriver.class);
    when(driver.getSampleURL()).thenReturn("");
    when(driver.getConnectionURL(Mockito.<DBPConnectionConfiguration>any())).thenReturn("/");

    // Act
    ConnectivityParameters actualConnectivityParameters =
        DBWUtils.getConnectivityParameters(configuration, driver);

    // Assert
    verify(driver).getConnectionURL(isA(DBPConnectionConfiguration.class));
    verify(driver).getSampleURL();
    assertNull(actualConnectivityParameters.databaseName());
    assertNull(actualConnectivityParameters.hostName());
    assertNull(actualConnectivityParameters.hostPort());
    assertNull(actualConnectivityParameters.server());
    assertNull(actualConnectivityParameters.userName());
  }

  /**
   * Test {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)}.
   *
   * <ul>
   *   <li>Given {@code {UU}}.
   *   <li>When {@link DBPDriver} {@link DBPDriver#getSampleURL()} return {@code {UU}}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration,
   * DBPDriver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConnectivityParameters DBWUtils.getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)"
  })
  public void testGetConnectivityParameters_givenUu_whenDBPDriverGetSampleURLReturnUu()
      throws DBException {
    // Arrange
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setConfigurationType(DBPDriverConfigurationType.URL);

    DBPDriver driver = mock(DBPDriver.class);
    when(driver.getSampleURL()).thenReturn("{UU}");
    when(driver.getConnectionURL(Mockito.<DBPConnectionConfiguration>any()))
        .thenReturn("not empty");

    // Act
    ConnectivityParameters actualConnectivityParameters =
        DBWUtils.getConnectivityParameters(configuration, driver);

    // Assert
    verify(driver).getConnectionURL(isA(DBPConnectionConfiguration.class));
    verify(driver, atLeast(1)).getSampleURL();
    assertNull(actualConnectivityParameters.databaseName());
    assertNull(actualConnectivityParameters.hostName());
    assertNull(actualConnectivityParameters.hostPort());
    assertNull(actualConnectivityParameters.server());
    assertNull(actualConnectivityParameters.userName());
  }

  /**
   * Test {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)}.
   *
   * <ul>
   *   <li>Then return databaseName is {@code example}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration,
   * DBPDriver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConnectivityParameters DBWUtils.getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)"
  })
  public void testGetConnectivityParameters_thenReturnDatabaseNameIsExample() throws DBException {
    // Arrange
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setConfigurationType(DBPDriverConfigurationType.URL);

    DBPDriver driver = mock(DBPDriver.class);
    when(driver.getSampleURL()).thenReturn("not empty");
    when(driver.getConnectionURL(Mockito.<DBPConnectionConfiguration>any()))
        .thenReturn("https://example.org/example");

    // Act
    ConnectivityParameters actualConnectivityParameters =
        DBWUtils.getConnectivityParameters(configuration, driver);

    // Assert
    verify(driver).getConnectionURL(isA(DBPConnectionConfiguration.class));
    verify(driver, atLeast(1)).getSampleURL();
    assertEquals("example", actualConnectivityParameters.databaseName());
    assertEquals("example.org", actualConnectivityParameters.hostName());
    assertNull(actualConnectivityParameters.hostPort());
    assertNull(actualConnectivityParameters.server());
    assertNull(actualConnectivityParameters.userName());
  }

  /**
   * Test {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)}.
   *
   * <ul>
   *   <li>Then return hostPort is {@code Default Port}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration,
   * DBPDriver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConnectivityParameters DBWUtils.getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)"
  })
  public void testGetConnectivityParameters_thenReturnHostPortIsDefaultPort() throws DBException {
    // Arrange
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setConfigurationType(DBPDriverConfigurationType.URL);

    DBPDriver driver = mock(DBPDriver.class);
    when(driver.getConnectionURL(Mockito.<DBPConnectionConfiguration>any())).thenReturn("");
    when(driver.getDefaultHost()).thenReturn(DBWUtils.LOCALHOST_NAME);
    when(driver.getDefaultPort()).thenReturn("Default Port");

    // Act
    ConnectivityParameters actualConnectivityParameters =
        DBWUtils.getConnectivityParameters(configuration, driver);

    // Assert
    verify(driver).getConnectionURL(isA(DBPConnectionConfiguration.class));
    verify(driver).getDefaultHost();
    verify(driver).getDefaultPort();
    assertEquals("Default Port", actualConnectivityParameters.hostPort());
    assertNull(actualConnectivityParameters.databaseName());
    assertNull(actualConnectivityParameters.server());
    assertNull(actualConnectivityParameters.userName());
    assertEquals(DBWUtils.LOCALHOST_NAME, actualConnectivityParameters.hostName());
  }

  /**
   * Test {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)}.
   *
   * <ul>
   *   <li>When {@link DBPConnectionConfiguration#DBPConnectionConfiguration()}.
   * </ul>
   *
   * <p>Method under test: {@link DBWUtils#getConnectivityParameters(DBPConnectionConfiguration,
   * DBPDriver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConnectivityParameters DBWUtils.getConnectivityParameters(DBPConnectionConfiguration, DBPDriver)"
  })
  public void testGetConnectivityParameters_whenDBPConnectionConfiguration() throws DBException {
    // Arrange and Act
    ConnectivityParameters actualConnectivityParameters =
        DBWUtils.getConnectivityParameters(new DBPConnectionConfiguration(), mock(DBPDriver.class));

    // Assert
    assertNull(actualConnectivityParameters.databaseName());
    assertNull(actualConnectivityParameters.hostName());
    assertNull(actualConnectivityParameters.hostPort());
    assertNull(actualConnectivityParameters.server());
    assertNull(actualConnectivityParameters.userName());
  }
}
