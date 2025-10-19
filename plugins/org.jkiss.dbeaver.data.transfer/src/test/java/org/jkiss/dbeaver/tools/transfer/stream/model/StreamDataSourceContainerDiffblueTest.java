package org.jkiss.dbeaver.tools.transfer.stream.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.app.DBPDataSourceRegistry;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.connection.DBPDriverConfigurationType;
import org.jkiss.dbeaver.model.exec.DBCFeatureNotSupportedException;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class StreamDataSourceContainerDiffblueTest {
  /**
   * Test {@link StreamDataSourceContainer#getId()}.
   *
   * <p>Method under test: {@link StreamDataSourceContainer#getId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StreamDataSourceContainer.getId()"})
  public void testGetId() {
    // Arrange, Act and Assert
    assertEquals("Name", new StreamDataSourceContainer("Name").getId());
  }

  /**
   * Test {@link StreamDataSourceContainer#getDriver()}.
   *
   * <p>Method under test: {@link StreamDataSourceContainer#getDriver()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.connection.DBPDriver StreamDataSourceContainer.getDriver()"
  })
  public void testGetDriver() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new StreamDataSourceContainer("Name").getDriver());
  }

  /**
   * Test {@link StreamDataSourceContainer#getConfigurationStorage()}.
   *
   * <p>Method under test: {@link StreamDataSourceContainer#getConfigurationStorage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.DBPDataSourceConfigurationStorage StreamDataSourceContainer.getConfigurationStorage()"
  })
  public void testGetConfigurationStorage() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new StreamDataSourceContainer("Name").getConfigurationStorage());
  }

  /**
   * Test {@link StreamDataSourceContainer#getOrigin()}.
   *
   * <p>Method under test: {@link StreamDataSourceContainer#getOrigin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.DBPDataSourceOrigin StreamDataSourceContainer.getOrigin()"
  })
  public void testGetOrigin() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new StreamDataSourceContainer("Name").getOrigin());
  }

  /**
   * Test {@link StreamDataSourceContainer#getConnectionConfiguration()}.
   *
   * <p>Method under test: {@link StreamDataSourceContainer#getConnectionConfiguration()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPConnectionConfiguration StreamDataSourceContainer.getConnectionConfiguration()"
  })
  public void testGetConnectionConfiguration() {
    // Arrange and Act
    DBPConnectionConfiguration actualConnectionConfiguration =
        new StreamDataSourceContainer("Name").getConnectionConfiguration();

    // Assert
    assertNull(actualConnectionConfiguration.getAuthModelId());
    assertNull(actualConnectionConfiguration.getClientHomeId());
    assertNull(actualConnectionConfiguration.getConfigProfileName());
    assertNull(actualConnectionConfiguration.getConfigProfileSource());
    assertNull(actualConnectionConfiguration.getConnectionColor());
    assertNull(actualConnectionConfiguration.getDatabaseName());
    assertNull(actualConnectionConfiguration.getHostName());
    assertNull(actualConnectionConfiguration.getHostPort());
    assertNull(actualConnectionConfiguration.getServerName());
    assertNull(actualConnectionConfiguration.getUrl());
    assertNull(actualConnectionConfiguration.getUserName());
    assertNull(actualConnectionConfiguration.getUserPassword());
    assertNull(actualConnectionConfiguration.getAuthProperties());
    assertEquals(0, actualConnectionConfiguration.getCloseIdleInterval());
    assertEquals(0, actualConnectionConfiguration.getKeepAliveInterval());
    assertEquals(0, actualConnectionConfiguration.getDeclaredEvents().length);
    assertEquals(
        DBPDriverConfigurationType.MANUAL, actualConnectionConfiguration.getConfigurationType());
    assertTrue(actualConnectionConfiguration.getHandlers().isEmpty());
    assertTrue(actualConnectionConfiguration.getProperties().isEmpty());
    assertTrue(actualConnectionConfiguration.getProviderProperties().isEmpty());
    assertTrue(actualConnectionConfiguration.getRuntimeAttribute().isEmpty());
    assertTrue(actualConnectionConfiguration.isCloseIdleConnection());
  }

  /**
   * Test {@link StreamDataSourceContainer#getActualConnectionConfiguration()}.
   *
   * <p>Method under test: {@link StreamDataSourceContainer#getActualConnectionConfiguration()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPConnectionConfiguration StreamDataSourceContainer.getActualConnectionConfiguration()"
  })
  public void testGetActualConnectionConfiguration() {
    // Arrange and Act
    DBPConnectionConfiguration actualActualConnectionConfiguration =
        new StreamDataSourceContainer("Name").getActualConnectionConfiguration();

    // Assert
    assertNull(actualActualConnectionConfiguration.getAuthModelId());
    assertNull(actualActualConnectionConfiguration.getClientHomeId());
    assertNull(actualActualConnectionConfiguration.getConfigProfileName());
    assertNull(actualActualConnectionConfiguration.getConfigProfileSource());
    assertNull(actualActualConnectionConfiguration.getConnectionColor());
    assertNull(actualActualConnectionConfiguration.getDatabaseName());
    assertNull(actualActualConnectionConfiguration.getHostName());
    assertNull(actualActualConnectionConfiguration.getHostPort());
    assertNull(actualActualConnectionConfiguration.getServerName());
    assertNull(actualActualConnectionConfiguration.getUrl());
    assertNull(actualActualConnectionConfiguration.getUserName());
    assertNull(actualActualConnectionConfiguration.getUserPassword());
    assertNull(actualActualConnectionConfiguration.getAuthProperties());
    assertEquals(0, actualActualConnectionConfiguration.getCloseIdleInterval());
    assertEquals(0, actualActualConnectionConfiguration.getKeepAliveInterval());
    assertEquals(0, actualActualConnectionConfiguration.getDeclaredEvents().length);
    assertEquals(
        DBPDriverConfigurationType.MANUAL,
        actualActualConnectionConfiguration.getConfigurationType());
    assertTrue(actualActualConnectionConfiguration.getHandlers().isEmpty());
    assertTrue(actualActualConnectionConfiguration.getProperties().isEmpty());
    assertTrue(actualActualConnectionConfiguration.getProviderProperties().isEmpty());
    assertTrue(actualActualConnectionConfiguration.getRuntimeAttribute().isEmpty());
    assertTrue(actualActualConnectionConfiguration.isCloseIdleConnection());
  }

  /**
   * Test {@link StreamDataSourceContainer#getNavigatorSettings()}.
   *
   * <p>Method under test: {@link StreamDataSourceContainer#getNavigatorSettings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.navigator.DBNBrowseSettings StreamDataSourceContainer.getNavigatorSettings()"
  })
  public void testGetNavigatorSettings() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new StreamDataSourceContainer("Name").getNavigatorSettings());
  }

  /**
   * Test {@link StreamDataSourceContainer#isAccessCheckRequired()}.
   *
   * <p>Method under test: {@link StreamDataSourceContainer#isAccessCheckRequired()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StreamDataSourceContainer.isAccessCheckRequired()"})
  public void testIsAccessCheckRequired() {
    // Arrange, Act and Assert
    assertFalse(new StreamDataSourceContainer("Name").isAccessCheckRequired());
  }

  /**
   * Test {@link StreamDataSourceContainer#getObjectFilter(Class, DBSObject, boolean)}.
   *
   * <p>Method under test: {@link StreamDataSourceContainer#getObjectFilter(Class, DBSObject,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObjectFilter StreamDataSourceContainer.getObjectFilter(Class, DBSObject, boolean)"
  })
  public void testGetObjectFilter() {
    // Arrange
    StreamDataSourceContainer streamDataSourceContainer = new StreamDataSourceContainer("Name");
    Class<Object> type = Object.class;

    // Act and Assert
    assertNull(
        streamDataSourceContainer.getObjectFilter(type, new StreamDataSource("Input Name"), true));
  }

  /**
   * Test {@link StreamDataSourceContainer#getActiveNetworkHandlers()}.
   *
   * <p>Method under test: {@link StreamDataSourceContainer#getActiveNetworkHandlers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.net.DBWNetworkHandler[] StreamDataSourceContainer.getActiveNetworkHandlers()"
  })
  public void testGetActiveNetworkHandlers() {
    // Arrange, Act and Assert
    assertEquals(0, new StreamDataSourceContainer("Name").getActiveNetworkHandlers().length);
  }

  /**
   * Test {@link StreamDataSourceContainer#connect(DBRProgressMonitor, boolean, boolean)}.
   *
   * <p>Method under test: {@link StreamDataSourceContainer#connect(DBRProgressMonitor, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean StreamDataSourceContainer.connect(DBRProgressMonitor, boolean, boolean)"
  })
  public void testConnect() throws DBException {
    // Arrange
    StreamDataSourceContainer streamDataSourceContainer = new StreamDataSourceContainer("Name");

    // Act and Assert
    assertThrows(
        DBCFeatureNotSupportedException.class,
        () -> streamDataSourceContainer.connect(new LoggingProgressMonitor(), true, true));
  }

  /**
   * Test {@link StreamDataSourceContainer#disconnect(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link StreamDataSourceContainer#disconnect(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StreamDataSourceContainer.disconnect(DBRProgressMonitor)"})
  public void testDisconnect() throws DBException {
    // Arrange
    StreamDataSourceContainer streamDataSourceContainer = new StreamDataSourceContainer("Name");

    // Act and Assert
    assertThrows(
        DBCFeatureNotSupportedException.class,
        () -> streamDataSourceContainer.disconnect(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link StreamDataSourceContainer#reconnect(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link StreamDataSourceContainer#reconnect(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StreamDataSourceContainer.reconnect(DBRProgressMonitor)"})
  public void testReconnect() throws DBException {
    // Arrange
    StreamDataSourceContainer streamDataSourceContainer = new StreamDataSourceContainer("Name");

    // Act and Assert
    assertThrows(
        DBCFeatureNotSupportedException.class,
        () -> streamDataSourceContainer.reconnect(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link StreamDataSourceContainer#getExtension(String)}.
   *
   * <p>Method under test: {@link StreamDataSourceContainer#getExtension(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object StreamDataSourceContainer.getExtension(String)"})
  public void testGetExtension() {
    // Arrange, Act and Assert
    assertNull(new StreamDataSourceContainer("Name").getExtension("Name"));
  }

  /**
   * Test {@link StreamDataSourceContainer#persistConfiguration()}.
   *
   * <p>Method under test: {@link StreamDataSourceContainer#persistConfiguration()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StreamDataSourceContainer.persistConfiguration()"})
  public void testPersistConfiguration() {
    // Arrange, Act and Assert
    assertTrue(new StreamDataSourceContainer("Name").persistConfiguration());
  }

  /**
   * Test {@link StreamDataSourceContainer#getVariablesResolver(boolean)}.
   *
   * <p>Method under test: {@link StreamDataSourceContainer#getVariablesResolver(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.runtime.IVariableResolver StreamDataSourceContainer.getVariablesResolver(boolean)"
  })
  public void testGetVariablesResolver() {
    // Arrange, Act and Assert
    assertNull(new StreamDataSourceContainer("Name").getVariablesResolver(true));
  }

  /**
   * Test {@link StreamDataSourceContainer#createCopy(DBPDataSourceRegistry)}.
   *
   * <p>Method under test: {@link StreamDataSourceContainer#createCopy(DBPDataSourceRegistry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.DBPDataSourceContainer StreamDataSourceContainer.createCopy(DBPDataSourceRegistry)"
  })
  public void testCreateCopy() {
    // Arrange, Act and Assert
    assertNull(new StreamDataSourceContainer("Name").createCopy(mock(DBPDataSourceRegistry.class)));
  }

  /**
   * Test {@link StreamDataSourceContainer#getName()}.
   *
   * <p>Method under test: {@link StreamDataSourceContainer#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StreamDataSourceContainer.getName()"})
  public void testGetName() {
    // Arrange, Act and Assert
    assertEquals("Name", new StreamDataSourceContainer("Name").getName());
  }

  /**
   * Test {@link StreamDataSourceContainer#listSharedCredentials()}.
   *
   * <p>Method under test: {@link StreamDataSourceContainer#listSharedCredentials()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.List StreamDataSourceContainer.listSharedCredentials()"})
  public void testListSharedCredentials() throws DBException {
    // Arrange, Act and Assert
    assertTrue(new StreamDataSourceContainer("Name").listSharedCredentials().isEmpty());
  }

  /**
   * Test {@link StreamDataSourceContainer#setForceUseSingleConnection(boolean)}.
   *
   * <p>Method under test: {@link StreamDataSourceContainer#setForceUseSingleConnection(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamDataSourceContainer.setForceUseSingleConnection(boolean)"})
  public void testSetForceUseSingleConnection() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new StreamDataSourceContainer("Name").setForceUseSingleConnection(true));
  }

  /**
   * Test {@link StreamDataSourceContainer#getTagValue(String)}.
   *
   * <p>Method under test: {@link StreamDataSourceContainer#getTagValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StreamDataSourceContainer.getTagValue(String)"})
  public void testGetTagValue() {
    // Arrange, Act and Assert
    assertNull(new StreamDataSourceContainer("Name").getTagValue("Tag Name"));
  }
}
