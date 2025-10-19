package org.jkiss.dbeaver.model.connection;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.access.DBAAuthCredentials;
import org.jkiss.dbeaver.model.access.DBAAuthModel;
import org.jkiss.dbeaver.model.impl.auth.AuthModelDatabaseNative;
import org.jkiss.dbeaver.model.net.DBWHandlerConfiguration;
import org.jkiss.dbeaver.model.net.DBWHandlerDescriptor;
import org.jkiss.dbeaver.model.net.DBWNetworkProfile;
import org.jkiss.dbeaver.model.runtime.DBRShellCommand;
import org.jkiss.dbeaver.runtime.IVariableResolver;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBPConnectionConfigurationDiffblueTest {
  /**
   * Test {@link DBPConnectionConfiguration#DBPConnectionConfiguration()}.
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#DBPConnectionConfiguration()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.<init>()"})
  public void testNewDBPConnectionConfiguration() {
    // Arrange and Act
    DBPConnectionConfiguration actualDbpConnectionConfiguration = new DBPConnectionConfiguration();

    // Assert
    assertNull(actualDbpConnectionConfiguration.getAuthModelId());
    assertNull(actualDbpConnectionConfiguration.getClientHomeId());
    assertNull(actualDbpConnectionConfiguration.getConfigProfileName());
    assertNull(actualDbpConnectionConfiguration.getConfigProfileSource());
    assertNull(actualDbpConnectionConfiguration.getConnectionColor());
    assertNull(actualDbpConnectionConfiguration.getDatabaseName());
    assertNull(actualDbpConnectionConfiguration.getHostName());
    assertNull(actualDbpConnectionConfiguration.getHostPort());
    assertNull(actualDbpConnectionConfiguration.getServerName());
    assertNull(actualDbpConnectionConfiguration.getUrl());
    assertNull(actualDbpConnectionConfiguration.getUserName());
    assertNull(actualDbpConnectionConfiguration.getUserPassword());
    assertNull(actualDbpConnectionConfiguration.getAuthProperties());
    assertEquals(0, actualDbpConnectionConfiguration.getCloseIdleInterval());
    assertEquals(0, actualDbpConnectionConfiguration.getKeepAliveInterval());
    assertEquals(0, actualDbpConnectionConfiguration.getDeclaredEvents().length);
    assertEquals(
        DBPDriverConfigurationType.MANUAL, actualDbpConnectionConfiguration.getConfigurationType());
    assertTrue(actualDbpConnectionConfiguration.getHandlers().isEmpty());
    assertTrue(actualDbpConnectionConfiguration.getProperties().isEmpty());
    assertTrue(actualDbpConnectionConfiguration.getProviderProperties().isEmpty());
    assertTrue(actualDbpConnectionConfiguration.getRuntimeAttribute().isEmpty());
    assertTrue(actualDbpConnectionConfiguration.isCloseIdleConnection());
  }

  /**
   * Test {@link DBPConnectionConfiguration#DBPConnectionConfiguration(DBPConnectionConfiguration)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()}.
   *   <li>Then return AuthProperties Empty.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBPConnectionConfiguration#DBPConnectionConfiguration(DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.<init>(DBPConnectionConfiguration)"})
  public void testNewDBPConnectionConfiguration_givenHashMap_thenReturnAuthPropertiesEmpty() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration handler =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    DBPConnectionConfiguration info = new DBPConnectionConfiguration();
    info.setAuthProperties(new HashMap<>());
    info.updateHandler(handler);

    // Act
    DBPConnectionConfiguration actualDbpConnectionConfiguration =
        new DBPConnectionConfiguration(info);

    // Assert
    verify(descriptor).getId();
    List<DBWHandlerConfiguration> handlers = actualDbpConnectionConfiguration.getHandlers();
    assertEquals(1, handlers.size());
    DBWHandlerConfiguration getResult = handlers.get(0);
    assertEquals("42", getResult.getId());
    assertEquals("42", getResult.toString());
    assertNull(getResult.getPassword());
    assertNull(getResult.getTitle());
    assertNull(getResult.getUserName());
    assertNull(getResult.getDriver());
    assertNull(getResult.getType());
    assertFalse(getResult.hasValuableInfo());
    assertFalse(getResult.isEnabled());
    assertFalse(getResult.isSecured());
    assertTrue(actualDbpConnectionConfiguration.getAuthProperties().isEmpty());
    assertTrue(getResult.getProperties().isEmpty());
    assertTrue(getResult.getSecureProperties().isEmpty());
    assertTrue(getResult.isSavePassword());
  }

  /**
   * Test {@link DBPConnectionConfiguration#DBPConnectionConfiguration(DBPConnectionConfiguration)}.
   *
   * <ul>
   *   <li>Then return AuthModelId is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBPConnectionConfiguration#DBPConnectionConfiguration(DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.<init>(DBPConnectionConfiguration)"})
  public void testNewDBPConnectionConfiguration_thenReturnAuthModelIdIsNull() {
    // Arrange and Act
    DBPConnectionConfiguration actualDbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());

    // Assert
    assertNull(actualDbpConnectionConfiguration.getAuthModelId());
    assertNull(actualDbpConnectionConfiguration.getClientHomeId());
    assertNull(actualDbpConnectionConfiguration.getConfigProfileName());
    assertNull(actualDbpConnectionConfiguration.getConfigProfileSource());
    assertNull(actualDbpConnectionConfiguration.getConnectionColor());
    assertNull(actualDbpConnectionConfiguration.getDatabaseName());
    assertNull(actualDbpConnectionConfiguration.getHostName());
    assertNull(actualDbpConnectionConfiguration.getHostPort());
    assertNull(actualDbpConnectionConfiguration.getServerName());
    assertNull(actualDbpConnectionConfiguration.getUrl());
    assertNull(actualDbpConnectionConfiguration.getUserName());
    assertNull(actualDbpConnectionConfiguration.getUserPassword());
    assertEquals(0, actualDbpConnectionConfiguration.getCloseIdleInterval());
    assertEquals(0, actualDbpConnectionConfiguration.getKeepAliveInterval());
    assertEquals(0, actualDbpConnectionConfiguration.getDeclaredEvents().length);
    assertEquals(
        DBPDriverConfigurationType.MANUAL, actualDbpConnectionConfiguration.getConfigurationType());
    assertTrue(actualDbpConnectionConfiguration.getHandlers().isEmpty());
    assertTrue(actualDbpConnectionConfiguration.getProperties().isEmpty());
    assertTrue(actualDbpConnectionConfiguration.getProviderProperties().isEmpty());
    assertTrue(actualDbpConnectionConfiguration.getRuntimeAttribute().isEmpty());
    assertTrue(actualDbpConnectionConfiguration.isCloseIdleConnection());
  }

  /**
   * Test {@link DBPConnectionConfiguration#DBPConnectionConfiguration(DBPConnectionConfiguration)}.
   *
   * <ul>
   *   <li>Then return AuthProperties is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBPConnectionConfiguration#DBPConnectionConfiguration(DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.<init>(DBPConnectionConfiguration)"})
  public void testNewDBPConnectionConfiguration_thenReturnAuthPropertiesIsNull() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration handler =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    DBPConnectionConfiguration info = new DBPConnectionConfiguration();
    info.updateHandler(handler);

    // Act
    DBPConnectionConfiguration actualDbpConnectionConfiguration =
        new DBPConnectionConfiguration(info);

    // Assert
    verify(descriptor).getId();
    List<DBWHandlerConfiguration> handlers = actualDbpConnectionConfiguration.getHandlers();
    assertEquals(1, handlers.size());
    DBWHandlerConfiguration getResult = handlers.get(0);
    assertEquals("42", getResult.getId());
    assertEquals("42", getResult.toString());
    assertNull(getResult.getPassword());
    assertNull(getResult.getTitle());
    assertNull(getResult.getUserName());
    assertNull(actualDbpConnectionConfiguration.getAuthProperties());
    assertNull(getResult.getDriver());
    assertNull(getResult.getType());
    assertFalse(getResult.hasValuableInfo());
    assertFalse(getResult.isEnabled());
    assertFalse(getResult.isSecured());
    assertTrue(getResult.getProperties().isEmpty());
    assertTrue(getResult.getSecureProperties().isEmpty());
    assertTrue(getResult.isSavePassword());
  }

  /**
   * Test {@link DBPConnectionConfiguration#hasProperty(String)}.
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#hasProperty(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionConfiguration.hasProperty(String)"})
  public void testHasProperty() {
    // Arrange, Act and Assert
    assertFalse(new DBPConnectionConfiguration().hasProperty("Name"));
  }

  /**
   * Test {@link DBPConnectionConfiguration#getProperty(String)}.
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#getProperty(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBPConnectionConfiguration.getProperty(String)"})
  public void testGetProperty() {
    // Arrange, Act and Assert
    assertNull(new DBPConnectionConfiguration().getProperty("Name"));
  }

  /**
   * Test {@link DBPConnectionConfiguration#setProperty(String, String)}.
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#setProperty(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.setProperty(String, String)"})
  public void testSetProperty() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();

    // Act
    dbpConnectionConfiguration.setProperty("Name", "42");

    // Assert
    Map<String, String> properties = dbpConnectionConfiguration.getProperties();
    assertEquals(1, properties.size());
    assertEquals("42", properties.get("Name"));
  }

  /**
   * Test {@link DBPConnectionConfiguration#getProviderProperty(String)}.
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#getProviderProperty(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBPConnectionConfiguration.getProviderProperty(String)"})
  public void testGetProviderProperty() {
    // Arrange, Act and Assert
    assertNull(new DBPConnectionConfiguration().getProviderProperty("Name"));
  }

  /**
   * Test {@link DBPConnectionConfiguration#setProviderProperty(String, String)}.
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#setProviderProperty(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.setProviderProperty(String, String)"})
  public void testSetProviderProperty() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();

    // Act
    dbpConnectionConfiguration.setProviderProperty("Name", "42");

    // Assert
    Map<String, String> providerProperties = dbpConnectionConfiguration.getProviderProperties();
    assertEquals(1, providerProperties.size());
    assertEquals("42", providerProperties.get("Name"));
  }

  /**
   * Test {@link DBPConnectionConfiguration#getRuntimeAttribute(String)} with {@code String}.
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#getRuntimeAttribute(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object DBPConnectionConfiguration.getRuntimeAttribute(String)"})
  public void testGetRuntimeAttributeWithString() {
    // Arrange, Act and Assert
    assertNull(new DBPConnectionConfiguration().getRuntimeAttribute("Name"));
  }

  /**
   * Test {@link DBPConnectionConfiguration#setRuntimeAttribute(String, Object)}.
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#setRuntimeAttribute(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.setRuntimeAttribute(String, Object)"})
  public void testSetRuntimeAttribute() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    Object object = DBPEvent.RENAME;

    // Act
    dbpConnectionConfiguration.setRuntimeAttribute("Name", object);

    // Assert
    Map<String, Object> runtimeAttribute = dbpConnectionConfiguration.getRuntimeAttribute();
    assertEquals(1, runtimeAttribute.size());
    assertSame(object, runtimeAttribute.get("Name"));
  }

  /**
   * Test {@link DBPConnectionConfiguration#getEvent(DBPConnectionEventType)}.
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#getEvent(DBPConnectionEventType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBRShellCommand DBPConnectionConfiguration.getEvent(DBPConnectionEventType)"})
  public void testGetEvent() {
    // Arrange, Act and Assert
    assertNull(new DBPConnectionConfiguration().getEvent(DBPConnectionEventType.BEFORE_CONNECT));
  }

  /**
   * Test {@link DBPConnectionConfiguration#setEvent(DBPConnectionEventType, DBRShellCommand)}.
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#setEvent(DBPConnectionEventType,
   * DBRShellCommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBPConnectionConfiguration.setEvent(DBPConnectionEventType, DBRShellCommand)"
  })
  public void testSetEvent() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();

    // Act
    dbpConnectionConfiguration.setEvent(
        DBPConnectionEventType.BEFORE_CONNECT, new DBRShellCommand("Command"));

    // Assert
    assertArrayEquals(
        new DBPConnectionEventType[] {DBPConnectionEventType.BEFORE_CONNECT},
        dbpConnectionConfiguration.getDeclaredEvents());
  }

  /**
   * Test {@link DBPConnectionConfiguration#setEvent(DBPConnectionEventType, DBRShellCommand)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#setEvent(DBPConnectionEventType,
   * DBRShellCommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBPConnectionConfiguration.setEvent(DBPConnectionEventType, DBRShellCommand)"
  })
  public void testSetEvent_whenNull_thenArrayLengthIsZero() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();

    // Act
    dbpConnectionConfiguration.setEvent(DBPConnectionEventType.BEFORE_CONNECT, null);

    // Assert that nothing has changed
    assertEquals(0, dbpConnectionConfiguration.getDeclaredEvents().length);
  }

  /**
   * Test {@link DBPConnectionConfiguration#getDeclaredEvents()}.
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#getDeclaredEvents()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPConnectionEventType[] DBPConnectionConfiguration.getDeclaredEvents()"})
  public void testGetDeclaredEvents() {
    // Arrange, Act and Assert
    assertEquals(0, new DBPConnectionConfiguration().getDeclaredEvents().length);
  }

  /**
   * Test {@link DBPConnectionConfiguration#updateHandler(DBWHandlerConfiguration)}.
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#updateHandler(DBWHandlerConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.updateHandler(DBWHandlerConfiguration)"})
  public void testUpdateHandler() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration handler =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act
    dbpConnectionConfiguration.updateHandler(handler);

    // Assert
    verify(descriptor).getId();
    List<DBWHandlerConfiguration> handlers = dbpConnectionConfiguration.getHandlers();
    assertEquals(1, handlers.size());
    assertSame(handler, handlers.get(0));
  }

  /**
   * Test {@link DBPConnectionConfiguration#getHandler(String)}.
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#getHandler(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBWHandlerConfiguration DBPConnectionConfiguration.getHandler(String)"})
  public void testGetHandler() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration handler =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.updateHandler(handler);

    // Act
    DBWHandlerConfiguration actualHandler = dbpConnectionConfiguration.getHandler("42");

    // Assert
    verify(descriptor).getId();
    assertSame(handler, actualHandler);
  }

  /**
   * Test {@link DBPConnectionConfiguration#getHandler(String)}.
   *
   * <ul>
   *   <li>Given {@link DBPConnectionConfiguration#DBPConnectionConfiguration()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#getHandler(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBWHandlerConfiguration DBPConnectionConfiguration.getHandler(String)"})
  public void testGetHandler_givenDBPConnectionConfiguration_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DBPConnectionConfiguration().getHandler("42"));
  }

  /**
   * Test {@link DBPConnectionConfiguration#getHandler(String)}.
   *
   * <ul>
   *   <li>Given {@link DBWHandlerDescriptor} {@link DBWHandlerDescriptor#getId()} return {@code
   *       foo}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#getHandler(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBWHandlerConfiguration DBPConnectionConfiguration.getHandler(String)"})
  public void testGetHandler_givenDBWHandlerDescriptorGetIdReturnFoo_thenReturnNull() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("foo");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));
    DBWHandlerConfiguration handler = new DBWHandlerConfiguration(configuration);

    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.updateHandler(handler);

    // Act
    DBWHandlerConfiguration actualHandler = dbpConnectionConfiguration.getHandler("42");

    // Assert
    verify(descriptor).getId();
    assertNull(actualHandler);
  }

  /**
   * Test {@link DBPConnectionConfiguration#removeHandler(String)}.
   *
   * <ul>
   *   <li>Given {@link DBPConnectionConfiguration#DBPConnectionConfiguration()}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#removeHandler(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.removeHandler(String)"})
  public void testRemoveHandler_givenDBPConnectionConfiguration() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();

    // Act
    dbpConnectionConfiguration.removeHandler("42");

    // Assert that nothing has changed
    assertTrue(dbpConnectionConfiguration.getHandlers().isEmpty());
  }

  /**
   * Test {@link DBPConnectionConfiguration#removeHandler(String)}.
   *
   * <ul>
   *   <li>Given {@link DBWHandlerDescriptor} {@link DBWHandlerDescriptor#getId()} return {@code
   *       42}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#removeHandler(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.removeHandler(String)"})
  public void testRemoveHandler_givenDBWHandlerDescriptorGetIdReturn42() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration handler =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.updateHandler(handler);

    // Act
    dbpConnectionConfiguration.removeHandler("42");

    // Assert
    verify(descriptor).getId();
    assertTrue(dbpConnectionConfiguration.getHandlers().isEmpty());
  }

  /**
   * Test {@link DBPConnectionConfiguration#removeHandler(String)}.
   *
   * <ul>
   *   <li>Then {@link DBPConnectionConfiguration#DBPConnectionConfiguration()} Handlers size is
   *       one.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#removeHandler(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.removeHandler(String)"})
  public void testRemoveHandler_thenDBPConnectionConfigurationHandlersSizeIsOne() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("foo");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));
    DBWHandlerConfiguration handler = new DBWHandlerConfiguration(configuration);

    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.updateHandler(handler);

    // Act
    dbpConnectionConfiguration.removeHandler("42");

    // Assert that nothing has changed
    verify(descriptor).getId();
    assertEquals(1, dbpConnectionConfiguration.getHandlers().size());
  }

  /**
   * Test {@link DBPConnectionConfiguration#hasHandler(String)}.
   *
   * <ul>
   *   <li>Given {@link DBPConnectionConfiguration#DBPConnectionConfiguration()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#hasHandler(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionConfiguration.hasHandler(String)"})
  public void testHasHandler_givenDBPConnectionConfiguration_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DBPConnectionConfiguration().hasHandler("42"));
  }

  /**
   * Test {@link DBPConnectionConfiguration#hasHandler(String)}.
   *
   * <ul>
   *   <li>Given {@link DBWHandlerDescriptor} {@link DBWHandlerDescriptor#getId()} return {@code
   *       42}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#hasHandler(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionConfiguration.hasHandler(String)"})
  public void testHasHandler_givenDBWHandlerDescriptorGetIdReturn42_thenReturnTrue() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration handler =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.updateHandler(handler);

    // Act
    boolean actualHasHandlerResult = dbpConnectionConfiguration.hasHandler("42");

    // Assert
    verify(descriptor).getId();
    assertTrue(actualHasHandlerResult);
  }

  /**
   * Test {@link DBPConnectionConfiguration#hasHandler(String)}.
   *
   * <ul>
   *   <li>Given {@link DBWHandlerDescriptor} {@link DBWHandlerDescriptor#getId()} return {@code
   *       foo}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#hasHandler(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionConfiguration.hasHandler(String)"})
  public void testHasHandler_givenDBWHandlerDescriptorGetIdReturnFoo_thenReturnFalse() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("foo");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));
    DBWHandlerConfiguration handler = new DBWHandlerConfiguration(configuration);

    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.updateHandler(handler);

    // Act
    boolean actualHasHandlerResult = dbpConnectionConfiguration.hasHandler("42");

    // Assert
    verify(descriptor).getId();
    assertFalse(actualHasHandlerResult);
  }

  /**
   * Test {@link DBPConnectionConfiguration#setConfigProfile(DBWNetworkProfile)}.
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#setConfigProfile(DBWNetworkProfile)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.setConfigProfile(DBWNetworkProfile)"})
  public void testSetConfigProfile() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration cfg =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    DBWNetworkProfile profile = new DBWNetworkProfile();
    profile.updateConfiguration(cfg);

    // Act
    dbpConnectionConfiguration.setConfigProfile(profile);

    // Assert that nothing has changed
    verify(descriptor).getId();
    assertTrue(dbpConnectionConfiguration.getHandlers().isEmpty());
  }

  /**
   * Test {@link DBPConnectionConfiguration#setConfigProfile(DBWNetworkProfile)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then {@link DBPConnectionConfiguration#DBPConnectionConfiguration()} ConfigProfileName is
   *       {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#setConfigProfile(DBWNetworkProfile)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.setConfigProfile(DBWNetworkProfile)"})
  public void testSetConfigProfile_given42_thenDBPConnectionConfigurationConfigProfileNameIs42() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration cfg =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    DBWNetworkProfile profile = new DBWNetworkProfile();
    profile.setProfileId("42");
    profile.updateConfiguration(cfg);

    // Act
    dbpConnectionConfiguration.setConfigProfile(profile);

    // Assert
    verify(descriptor).getId();
    assertEquals("42", dbpConnectionConfiguration.getConfigProfileName());
    assertTrue(dbpConnectionConfiguration.getHandlers().isEmpty());
  }

  /**
   * Test {@link DBPConnectionConfiguration#setConfigProfile(DBWNetworkProfile)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#setConfigProfile(DBWNetworkProfile)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.setConfigProfile(DBWNetworkProfile)"})
  public void testSetConfigProfile_givenEmptyString() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration cfg =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    DBWNetworkProfile profile = new DBWNetworkProfile();
    profile.setProfileId("");
    profile.updateConfiguration(cfg);

    // Act
    dbpConnectionConfiguration.setConfigProfile(profile);

    // Assert that nothing has changed
    verify(descriptor).getId();
    assertTrue(dbpConnectionConfiguration.getHandlers().isEmpty());
  }

  /**
   * Test {@link DBPConnectionConfiguration#setConfigProfile(DBWNetworkProfile)}.
   *
   * <ul>
   *   <li>Then {@link DBPConnectionConfiguration#DBPConnectionConfiguration()} Handlers size is
   *       one.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#setConfigProfile(DBWNetworkProfile)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.setConfigProfile(DBWNetworkProfile)"})
  public void testSetConfigProfile_thenDBPConnectionConfigurationHandlersSizeIsOne() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");

    DBWHandlerConfiguration cfg =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));
    cfg.setEnabled(true);

    DBWNetworkProfile profile = new DBWNetworkProfile();
    profile.updateConfiguration(cfg);

    // Act
    dbpConnectionConfiguration.setConfigProfile(profile);

    // Assert
    verify(descriptor).getId();
    List<DBWHandlerConfiguration> handlers = dbpConnectionConfiguration.getHandlers();
    assertEquals(1, handlers.size());
    DBWHandlerConfiguration getResult = handlers.get(0);
    assertEquals("42", getResult.getId());
    assertEquals("42", getResult.toString());
    assertNull(getResult.getPassword());
    assertNull(getResult.getTitle());
    assertNull(getResult.getUserName());
    assertNull(getResult.getDriver());
    assertNull(getResult.getType());
    assertFalse(getResult.hasValuableInfo());
    assertFalse(getResult.isSecured());
    assertTrue(getResult.getProperties().isEmpty());
    assertTrue(getResult.getSecureProperties().isEmpty());
    assertTrue(getResult.isEnabled());
    assertTrue(getResult.isSavePassword());
  }

  /**
   * Test {@link DBPConnectionConfiguration#setConfigProfile(DBWNetworkProfile)}.
   *
   * <ul>
   *   <li>When {@link DBWNetworkProfile#DBWNetworkProfile()}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#setConfigProfile(DBWNetworkProfile)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.setConfigProfile(DBWNetworkProfile)"})
  public void testSetConfigProfile_whenDBWNetworkProfile() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();

    // Act
    dbpConnectionConfiguration.setConfigProfile(new DBWNetworkProfile());

    // Assert that nothing has changed
    assertTrue(dbpConnectionConfiguration.getHandlers().isEmpty());
  }

  /**
   * Test {@link DBPConnectionConfiguration#setConfigProfile(DBWNetworkProfile)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link DBPConnectionConfiguration#DBPConnectionConfiguration()} Handlers Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#setConfigProfile(DBWNetworkProfile)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.setConfigProfile(DBWNetworkProfile)"})
  public void testSetConfigProfile_whenNull_thenDBPConnectionConfigurationHandlersEmpty() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();

    // Act
    dbpConnectionConfiguration.setConfigProfile(null);

    // Assert that nothing has changed
    assertTrue(dbpConnectionConfiguration.getHandlers().isEmpty());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBPConnectionConfiguration#setAuthModelId(String)}
   *   <li>{@link DBPConnectionConfiguration#setClientHomeId(String)}
   *   <li>{@link DBPConnectionConfiguration#setCloseIdleConnection(boolean)}
   *   <li>{@link DBPConnectionConfiguration#setCloseIdleInterval(int)}
   *   <li>{@link DBPConnectionConfiguration#setConfigProfileName(String)}
   *   <li>{@link DBPConnectionConfiguration#setConfigProfileSource(String)}
   *   <li>{@link DBPConnectionConfiguration#setConfigurationType(DBPDriverConfigurationType)}
   *   <li>{@link DBPConnectionConfiguration#setConnectionColor(String)}
   *   <li>{@link DBPConnectionConfiguration#setConnectionType(DBPConnectionType)}
   *   <li>{@link DBPConnectionConfiguration#setDatabaseName(String)}
   *   <li>{@link DBPConnectionConfiguration#setHostName(String)}
   *   <li>{@link DBPConnectionConfiguration#setHostPort(String)}
   *   <li>{@link DBPConnectionConfiguration#setKeepAliveInterval(int)}
   *   <li>{@link DBPConnectionConfiguration#setServerName(String)}
   *   <li>{@link DBPConnectionConfiguration#setUrl(String)}
   *   <li>{@link DBPConnectionConfiguration#setUserName(String)}
   *   <li>{@link DBPConnectionConfiguration#setUserPassword(String)}
   *   <li>{@link DBPConnectionConfiguration#getAuthModelId()}
   *   <li>{@link DBPConnectionConfiguration#getAuthProperties()}
   *   <li>{@link DBPConnectionConfiguration#getBootstrap()}
   *   <li>{@link DBPConnectionConfiguration#getClientHomeId()}
   *   <li>{@link DBPConnectionConfiguration#getCloseIdleInterval()}
   *   <li>{@link DBPConnectionConfiguration#getConfigProfileName()}
   *   <li>{@link DBPConnectionConfiguration#getConfigProfileSource()}
   *   <li>{@link DBPConnectionConfiguration#getConfigurationType()}
   *   <li>{@link DBPConnectionConfiguration#getConnectionColor()}
   *   <li>{@link DBPConnectionConfiguration#getConnectionType()}
   *   <li>{@link DBPConnectionConfiguration#getDatabaseName()}
   *   <li>{@link DBPConnectionConfiguration#getHandlers()}
   *   <li>{@link DBPConnectionConfiguration#getHostName()}
   *   <li>{@link DBPConnectionConfiguration#getHostPort()}
   *   <li>{@link DBPConnectionConfiguration#getKeepAliveInterval()}
   *   <li>{@link DBPConnectionConfiguration#getProperties()}
   *   <li>{@link DBPConnectionConfiguration#getProviderProperties()}
   *   <li>{@link DBPConnectionConfiguration#getRuntimeAttribute()}
   *   <li>{@link DBPConnectionConfiguration#getServerName()}
   *   <li>{@link DBPConnectionConfiguration#getUrl()}
   *   <li>{@link DBPConnectionConfiguration#getUserName()}
   *   <li>{@link DBPConnectionConfiguration#getUserPassword()}
   *   <li>{@link DBPConnectionConfiguration#isCloseIdleConnection()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBPConnectionConfiguration.getAuthModelId()",
    "Map DBPConnectionConfiguration.getAuthProperties()",
    "DBPConnectionBootstrap DBPConnectionConfiguration.getBootstrap()",
    "String DBPConnectionConfiguration.getClientHomeId()",
    "int DBPConnectionConfiguration.getCloseIdleInterval()",
    "String DBPConnectionConfiguration.getConfigProfileName()",
    "String DBPConnectionConfiguration.getConfigProfileSource()",
    "DBPDriverConfigurationType DBPConnectionConfiguration.getConfigurationType()",
    "String DBPConnectionConfiguration.getConnectionColor()",
    "DBPConnectionType DBPConnectionConfiguration.getConnectionType()",
    "String DBPConnectionConfiguration.getDatabaseName()",
    "List DBPConnectionConfiguration.getHandlers()",
    "String DBPConnectionConfiguration.getHostName()",
    "String DBPConnectionConfiguration.getHostPort()",
    "int DBPConnectionConfiguration.getKeepAliveInterval()",
    "Map DBPConnectionConfiguration.getProperties()",
    "Map DBPConnectionConfiguration.getProviderProperties()",
    "Map DBPConnectionConfiguration.getRuntimeAttribute()",
    "String DBPConnectionConfiguration.getServerName()",
    "String DBPConnectionConfiguration.getUrl()",
    "String DBPConnectionConfiguration.getUserName()",
    "String DBPConnectionConfiguration.getUserPassword()",
    "boolean DBPConnectionConfiguration.isCloseIdleConnection()",
    "void DBPConnectionConfiguration.setAuthModelId(String)",
    "void DBPConnectionConfiguration.setClientHomeId(String)",
    "void DBPConnectionConfiguration.setCloseIdleConnection(boolean)",
    "void DBPConnectionConfiguration.setCloseIdleInterval(int)",
    "void DBPConnectionConfiguration.setConfigProfileName(String)",
    "void DBPConnectionConfiguration.setConfigProfileSource(String)",
    "void DBPConnectionConfiguration.setConfigurationType(DBPDriverConfigurationType)",
    "void DBPConnectionConfiguration.setConnectionColor(String)",
    "void DBPConnectionConfiguration.setConnectionType(DBPConnectionType)",
    "void DBPConnectionConfiguration.setDatabaseName(String)",
    "void DBPConnectionConfiguration.setHostName(String)",
    "void DBPConnectionConfiguration.setHostPort(String)",
    "void DBPConnectionConfiguration.setKeepAliveInterval(int)",
    "void DBPConnectionConfiguration.setServerName(String)",
    "void DBPConnectionConfiguration.setUrl(String)",
    "void DBPConnectionConfiguration.setUserName(String)",
    "void DBPConnectionConfiguration.setUserPassword(String)"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();

    // Act
    dbpConnectionConfiguration.setAuthModelId("42");
    dbpConnectionConfiguration.setClientHomeId("42");
    dbpConnectionConfiguration.setCloseIdleConnection(true);
    dbpConnectionConfiguration.setCloseIdleInterval(1);
    dbpConnectionConfiguration.setConfigProfileName("foo.txt");
    dbpConnectionConfiguration.setConfigProfileSource("Config Profile Source");
    dbpConnectionConfiguration.setConfigurationType(DBPDriverConfigurationType.MANUAL);
    dbpConnectionConfiguration.setConnectionColor("Color");
    dbpConnectionConfiguration.setConnectionType(DBPConnectionType.DEFAULT_TYPE);
    dbpConnectionConfiguration.setDatabaseName("Database Name");
    dbpConnectionConfiguration.setHostName("Host Name");
    dbpConnectionConfiguration.setHostPort("Host Port");
    dbpConnectionConfiguration.setKeepAliveInterval(42);
    dbpConnectionConfiguration.setServerName("Server Name");
    dbpConnectionConfiguration.setUrl("https://example.org/example");
    dbpConnectionConfiguration.setUserName("janedoe");
    dbpConnectionConfiguration.setUserPassword("iloveyou");
    String actualAuthModelId = dbpConnectionConfiguration.getAuthModelId();
    Map<String, String> actualAuthProperties = dbpConnectionConfiguration.getAuthProperties();
    DBPConnectionBootstrap actualBootstrap = dbpConnectionConfiguration.getBootstrap();
    String actualClientHomeId = dbpConnectionConfiguration.getClientHomeId();
    int actualCloseIdleInterval = dbpConnectionConfiguration.getCloseIdleInterval();
    String actualConfigProfileName = dbpConnectionConfiguration.getConfigProfileName();
    String actualConfigProfileSource = dbpConnectionConfiguration.getConfigProfileSource();
    DBPDriverConfigurationType actualConfigurationType =
        dbpConnectionConfiguration.getConfigurationType();
    String actualConnectionColor = dbpConnectionConfiguration.getConnectionColor();
    DBPConnectionType actualConnectionType = dbpConnectionConfiguration.getConnectionType();
    String actualDatabaseName = dbpConnectionConfiguration.getDatabaseName();
    List<DBWHandlerConfiguration> actualHandlers = dbpConnectionConfiguration.getHandlers();
    String actualHostName = dbpConnectionConfiguration.getHostName();
    String actualHostPort = dbpConnectionConfiguration.getHostPort();
    int actualKeepAliveInterval = dbpConnectionConfiguration.getKeepAliveInterval();
    Map<String, String> actualProperties = dbpConnectionConfiguration.getProperties();
    Map<String, String> actualProviderProperties =
        dbpConnectionConfiguration.getProviderProperties();
    Map<String, Object> actualRuntimeAttribute = dbpConnectionConfiguration.getRuntimeAttribute();
    String actualServerName = dbpConnectionConfiguration.getServerName();
    String actualUrl = dbpConnectionConfiguration.getUrl();
    String actualUserName = dbpConnectionConfiguration.getUserName();
    String actualUserPassword = dbpConnectionConfiguration.getUserPassword();
    boolean actualIsCloseIdleConnectionResult = dbpConnectionConfiguration.isCloseIdleConnection();

    // Assert
    assertEquals("42", actualAuthModelId);
    assertEquals("42", actualClientHomeId);
    assertEquals("Color", actualConnectionColor);
    assertEquals("Config Profile Source", actualConfigProfileSource);
    assertEquals("Database Name", actualDatabaseName);
    assertEquals("Host Name", actualHostName);
    assertEquals("Host Port", actualHostPort);
    assertEquals("Server Name", actualServerName);
    assertEquals("foo.txt", actualConfigProfileName);
    assertEquals("https://example.org/example", actualUrl);
    assertEquals("iloveyou", actualUserPassword);
    assertEquals("janedoe", actualUserName);
    assertNull(actualBootstrap.getDefaultAutoCommit());
    assertNull(actualBootstrap.getDefaultTransactionIsolation());
    assertNull(actualBootstrap.getDefaultCatalogName());
    assertNull(actualBootstrap.getDefaultSchemaName());
    assertNull(actualAuthProperties);
    assertEquals(1, actualCloseIdleInterval);
    assertEquals(42, actualKeepAliveInterval);
    assertEquals(DBPDriverConfigurationType.MANUAL, actualConfigurationType);
    assertFalse(actualBootstrap.hasData());
    assertFalse(actualBootstrap.isIgnoreErrors());
    assertTrue(actualBootstrap.getInitQueries().isEmpty());
    assertTrue(actualHandlers.isEmpty());
    assertTrue(actualProperties.isEmpty());
    assertTrue(actualProviderProperties.isEmpty());
    assertTrue(actualRuntimeAttribute.isEmpty());
    assertTrue(actualIsCloseIdleConnectionResult);
    assertSame(DBPConnectionType.DEFAULT_TYPE, actualConnectionType);
  }

  /**
   * Test {@link DBPConnectionConfiguration#getAuthModel()}.
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#getAuthModel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBAAuthModel DBPConnectionConfiguration.getAuthModel()"})
  public void testGetAuthModel() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    dbpConnectionConfiguration.setAuthModelId("");

    // Act
    DBAAuthModel<DBAAuthCredentials> actualAuthModel = dbpConnectionConfiguration.getAuthModel();

    // Assert
    assertSame(((AuthModelDatabaseNative) actualAuthModel).INSTANCE, actualAuthModel);
  }

  /**
   * Test {@link DBPConnectionConfiguration#getAuthModel()}.
   *
   * <ul>
   *   <li>Given {@link DBPConnectionConfiguration#DBPConnectionConfiguration()}.
   *   <li>Then return {@link AuthModelDatabaseNative#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#getAuthModel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBAAuthModel DBPConnectionConfiguration.getAuthModel()"})
  public void testGetAuthModel_givenDBPConnectionConfiguration_thenReturnInstance() {
    // Arrange and Act
    DBAAuthModel<DBAAuthCredentials> actualAuthModel =
        new DBPConnectionConfiguration().getAuthModel();

    // Assert
    assertSame(((AuthModelDatabaseNative) actualAuthModel).INSTANCE, actualAuthModel);
  }

  /**
   * Test {@link DBPConnectionConfiguration#getAuthProperty(String)}.
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#getAuthProperty(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBPConnectionConfiguration.getAuthProperty(String)"})
  public void testGetAuthProperty() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    dbpConnectionConfiguration.setAuthProperties(new HashMap<>());

    // Act and Assert
    assertNull(dbpConnectionConfiguration.getAuthProperty("Name"));
  }

  /**
   * Test {@link DBPConnectionConfiguration#getAuthProperty(String)}.
   *
   * <ul>
   *   <li>Given {@link DBPConnectionConfiguration#DBPConnectionConfiguration()}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#getAuthProperty(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBPConnectionConfiguration.getAuthProperty(String)"})
  public void testGetAuthProperty_givenDBPConnectionConfiguration() {
    // Arrange, Act and Assert
    assertNull(new DBPConnectionConfiguration().getAuthProperty("Name"));
  }

  /**
   * Test {@link DBPConnectionConfiguration#setAuthProperties(Map)}.
   *
   * <ul>
   *   <li>Then {@link DBPConnectionConfiguration#DBPConnectionConfiguration()} AuthProperties
   *       Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#setAuthProperties(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.setAuthProperties(Map)"})
  public void testSetAuthProperties_thenDBPConnectionConfigurationAuthPropertiesEmpty() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();

    // Act
    dbpConnectionConfiguration.setAuthProperties(new HashMap<>());

    // Assert
    assertTrue(dbpConnectionConfiguration.getAuthProperties().isEmpty());
  }

  /**
   * Test {@link DBPConnectionConfiguration#setAuthProperties(Map)}.
   *
   * <ul>
   *   <li>Then {@link DBPConnectionConfiguration#DBPConnectionConfiguration()} AuthProperties is
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#setAuthProperties(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.setAuthProperties(Map)"})
  public void testSetAuthProperties_thenDBPConnectionConfigurationAuthPropertiesIsNull() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();

    // Act
    dbpConnectionConfiguration.setAuthProperties(null);

    // Assert that nothing has changed
    assertNull(dbpConnectionConfiguration.getAuthProperties());
  }

  /**
   * Test {@link DBPConnectionConfiguration#setAuthProperty(String, String)}.
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#setAuthProperty(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.setAuthProperty(String, String)"})
  public void testSetAuthProperty() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    dbpConnectionConfiguration.setAuthProperties(null);

    // Act
    dbpConnectionConfiguration.setAuthProperty("Name", null);

    // Assert
    assertTrue(dbpConnectionConfiguration.getAuthProperties().isEmpty());
  }

  /**
   * Test {@link DBPConnectionConfiguration#setAuthProperty(String, String)}.
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#setAuthProperty(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.setAuthProperty(String, String)"})
  public void testSetAuthProperty2() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    dbpConnectionConfiguration.setAuthProperties(new HashMap<>());

    // Act
    dbpConnectionConfiguration.setAuthProperty("Name", null);

    // Assert that nothing has changed
    assertTrue(dbpConnectionConfiguration.getAuthProperties().isEmpty());
  }

  /**
   * Test {@link DBPConnectionConfiguration#setAuthProperty(String, String)}.
   *
   * <ul>
   *   <li>Then {@link DBPConnectionConfiguration#DBPConnectionConfiguration()} AuthProperties size
   *       is one.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#setAuthProperty(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.setAuthProperty(String, String)"})
  public void testSetAuthProperty_thenDBPConnectionConfigurationAuthPropertiesSizeIsOne() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();

    // Act
    dbpConnectionConfiguration.setAuthProperty("Name", "42");

    // Assert
    Map<String, String> authProperties = dbpConnectionConfiguration.getAuthProperties();
    assertEquals(1, authProperties.size());
    assertEquals("42", authProperties.get("Name"));
  }

  /**
   * Test {@link DBPConnectionConfiguration#toString()}.
   *
   * <ul>
   *   <li>Then return {@code ConnectionConfiguration: foo}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBPConnectionConfiguration.toString()"})
  public void testToString_thenReturnConnectionConfigurationFoo() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    dbpConnectionConfiguration.setUrl("foo");

    // Act and Assert
    assertEquals("ConnectionConfiguration: foo", dbpConnectionConfiguration.toString());
  }

  /**
   * Test {@link DBPConnectionConfiguration#toString()}.
   *
   * <ul>
   *   <li>Then return {@code ConnectionConfiguration: null}.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBPConnectionConfiguration.toString()"})
  public void testToString_thenReturnConnectionConfigurationNull() {
    // Arrange, Act and Assert
    assertEquals("ConnectionConfiguration: null", new DBPConnectionConfiguration().toString());
  }

  /**
   * Test {@link DBPConnectionConfiguration#equals(Object)}, and {@link
   * DBPConnectionConfiguration#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionConfiguration.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    DBPConnectionConfiguration dbpConnectionConfiguration2 = new DBPConnectionConfiguration();

    // Act and Assert
    assertEquals(dbpConnectionConfiguration, dbpConnectionConfiguration2);
    assertNotEquals(dbpConnectionConfiguration.hashCode(), dbpConnectionConfiguration2.hashCode());
  }

  /**
   * Test {@link DBPConnectionConfiguration#equals(Object)}, and {@link
   * DBPConnectionConfiguration#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionConfiguration.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();

    // Act and Assert
    assertEquals(dbpConnectionConfiguration, dbpConnectionConfiguration);
    int expectedHashCodeResult = dbpConnectionConfiguration.hashCode();
    assertEquals(expectedHashCodeResult, dbpConnectionConfiguration.hashCode());
  }

  /**
   * Test {@link DBPConnectionConfiguration#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionConfiguration.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DBPConnectionConfiguration(), 1);
  }

  /**
   * Test {@link DBPConnectionConfiguration#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionConfiguration.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.setClientHomeId("42");

    // Act and Assert
    assertNotEquals(dbpConnectionConfiguration, new DBPConnectionConfiguration());
  }

  /**
   * Test {@link DBPConnectionConfiguration#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionConfiguration.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.setHostName("Host Name");

    // Act and Assert
    assertNotEquals(dbpConnectionConfiguration, new DBPConnectionConfiguration());
  }

  /**
   * Test {@link DBPConnectionConfiguration#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionConfiguration.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.setHostPort("Host Port");

    // Act and Assert
    assertNotEquals(dbpConnectionConfiguration, new DBPConnectionConfiguration());
  }

  /**
   * Test {@link DBPConnectionConfiguration#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionConfiguration.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.setServerName("Server Name");

    // Act and Assert
    assertNotEquals(dbpConnectionConfiguration, new DBPConnectionConfiguration());
  }

  /**
   * Test {@link DBPConnectionConfiguration#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionConfiguration.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.setDatabaseName("Database Name");

    // Act and Assert
    assertNotEquals(dbpConnectionConfiguration, new DBPConnectionConfiguration());
  }

  /**
   * Test {@link DBPConnectionConfiguration#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionConfiguration.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual7() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.setUrl("https://example.org/example");

    // Act and Assert
    assertNotEquals(dbpConnectionConfiguration, new DBPConnectionConfiguration());
  }

  /**
   * Test {@link DBPConnectionConfiguration#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionConfiguration.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual8() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.setUserName("janedoe");

    // Act and Assert
    assertNotEquals(dbpConnectionConfiguration, new DBPConnectionConfiguration());
  }

  /**
   * Test {@link DBPConnectionConfiguration#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionConfiguration.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual9() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.setUserPassword("iloveyou");

    // Act and Assert
    assertNotEquals(dbpConnectionConfiguration, new DBPConnectionConfiguration());
  }

  /**
   * Test {@link DBPConnectionConfiguration#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionConfiguration.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual10() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration handler =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.updateHandler(handler);

    // Act and Assert
    assertNotEquals(dbpConnectionConfiguration, new DBPConnectionConfiguration());
  }

  /**
   * Test {@link DBPConnectionConfiguration#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionConfiguration.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual11() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration handler =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.setConfigProfileSource("Config Profile Source");
    dbpConnectionConfiguration.updateHandler(handler);

    // Act and Assert
    assertNotEquals(dbpConnectionConfiguration, new DBPConnectionConfiguration());
  }

  /**
   * Test {@link DBPConnectionConfiguration#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionConfiguration.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual12() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration handler =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.setConfigProfileName("foo.txt");
    dbpConnectionConfiguration.updateHandler(handler);

    // Act and Assert
    assertNotEquals(dbpConnectionConfiguration, new DBPConnectionConfiguration());
  }

  /**
   * Test {@link DBPConnectionConfiguration#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionConfiguration.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual13() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration handler =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.setAuthModelId("42");
    dbpConnectionConfiguration.updateHandler(handler);

    // Act and Assert
    assertNotEquals(dbpConnectionConfiguration, new DBPConnectionConfiguration());
  }

  /**
   * Test {@link DBPConnectionConfiguration#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionConfiguration.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual14() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration handler =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.setAuthProperties(new HashMap<>());
    dbpConnectionConfiguration.updateHandler(handler);

    // Act and Assert
    assertNotEquals(dbpConnectionConfiguration, new DBPConnectionConfiguration());
  }

  /**
   * Test {@link DBPConnectionConfiguration#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionConfiguration.equals(Object)"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DBPConnectionConfiguration(), null);
  }

  /**
   * Test {@link DBPConnectionConfiguration#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBPConnectionConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPConnectionConfiguration.equals(Object)"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new DBPConnectionConfiguration(), "Different type to DBPConnectionConfiguration");
  }

  /**
   * Test {@link DBPConnectionConfiguration#resolveDynamicVariables(IVariableResolver)}.
   *
   * <p>Method under test: {@link
   * DBPConnectionConfiguration#resolveDynamicVariables(IVariableResolver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.resolveDynamicVariables(IVariableResolver)"})
  public void testResolveDynamicVariables() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");

    DBWHandlerConfiguration handler =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));
    handler.setEnabled(true);

    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.updateHandler(handler);

    // Act
    dbpConnectionConfiguration.resolveDynamicVariables(mock(IVariableResolver.class));

    // Assert
    verify(descriptor).getId();
  }

  /**
   * Test {@link DBPConnectionConfiguration#resolveDynamicVariables(IVariableResolver)}.
   *
   * <p>Method under test: {@link
   * DBPConnectionConfiguration#resolveDynamicVariables(IVariableResolver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.resolveDynamicVariables(IVariableResolver)"})
  public void testResolveDynamicVariables2() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration handler =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.setAuthProperty("Name", "${U:U}");
    dbpConnectionConfiguration.updateHandler(handler);

    IVariableResolver variableResolver = mock(IVariableResolver.class);
    when(variableResolver.get(Mockito.<String>any())).thenReturn("Get");

    // Act
    dbpConnectionConfiguration.resolveDynamicVariables(variableResolver);

    // Assert
    verify(descriptor).getId();
    verify(variableResolver).get("U");
    Map<String, String> authProperties = dbpConnectionConfiguration.getAuthProperties();
    assertEquals(1, authProperties.size());
    assertEquals("Get", authProperties.get("Name"));
  }

  /**
   * Test {@link DBPConnectionConfiguration#resolveDynamicVariables(IVariableResolver)}.
   *
   * <p>Method under test: {@link
   * DBPConnectionConfiguration#resolveDynamicVariables(IVariableResolver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.resolveDynamicVariables(IVariableResolver)"})
  public void testResolveDynamicVariables3() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration handler =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.setAuthProperty("Name", "");
    dbpConnectionConfiguration.updateHandler(handler);

    // Act
    dbpConnectionConfiguration.resolveDynamicVariables(mock(IVariableResolver.class));

    // Assert that nothing has changed
    verify(descriptor).getId();
    Map<String, String> authProperties = dbpConnectionConfiguration.getAuthProperties();
    assertEquals(1, authProperties.size());
    assertEquals("", authProperties.get("Name"));
  }

  /**
   * Test {@link DBPConnectionConfiguration#resolveDynamicVariables(IVariableResolver)}.
   *
   * <ul>
   *   <li>Then calls {@link DBWHandlerDescriptor#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBPConnectionConfiguration#resolveDynamicVariables(IVariableResolver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.resolveDynamicVariables(IVariableResolver)"})
  public void testResolveDynamicVariables_thenCallsGetId() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration handler =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.updateHandler(handler);

    // Act
    dbpConnectionConfiguration.resolveDynamicVariables(mock(IVariableResolver.class));

    // Assert
    verify(descriptor).getId();
  }

  /**
   * Test {@link DBPConnectionConfiguration#resolveDynamicVariables(IVariableResolver)}.
   *
   * <ul>
   *   <li>Then {@link DBPConnectionConfiguration#DBPConnectionConfiguration()} AuthProperties
   *       {@code Name} is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBPConnectionConfiguration#resolveDynamicVariables(IVariableResolver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBPConnectionConfiguration.resolveDynamicVariables(IVariableResolver)"})
  public void testResolveDynamicVariables_thenDBPConnectionConfigurationAuthPropertiesNameIs42() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration handler =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.setAuthProperty("Name", "42");
    dbpConnectionConfiguration.updateHandler(handler);

    // Act
    dbpConnectionConfiguration.resolveDynamicVariables(mock(IVariableResolver.class));

    // Assert that nothing has changed
    verify(descriptor).getId();
    Map<String, String> authProperties = dbpConnectionConfiguration.getAuthProperties();
    assertEquals(1, authProperties.size());
    assertEquals("42", authProperties.get("Name"));
  }
}
