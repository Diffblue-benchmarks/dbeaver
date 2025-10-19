package org.jkiss.dbeaver.model.connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.app.DBPDataSourceRegistry;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.net.DBWHandlerConfiguration;
import org.jkiss.dbeaver.model.net.DBWHandlerDescriptor;
import org.jkiss.dbeaver.model.net.DBWHandlerType;
import org.jkiss.dbeaver.model.net.DBWNetworkProfile;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DataSourceVariableResolverDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DataSourceVariableResolver#DataSourceVariableResolver(DBPDataSourceContainer,
   *       DBPConnectionConfiguration)}
   *   <li>{@link DataSourceVariableResolver#getConfiguration()}
   *   <li>{@link DataSourceVariableResolver#getDataSourceContainer()}
   *   <li>{@link DataSourceVariableResolver#isSecure()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataSourceVariableResolver.<init>(DBPDataSourceContainer, DBPConnectionConfiguration)",
    "DBPConnectionConfiguration DataSourceVariableResolver.getConfiguration()",
    "DBPDataSourceContainer DataSourceVariableResolver.getDataSourceContainer()",
    "boolean DataSourceVariableResolver.isSecure()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();

    // Act
    DataSourceVariableResolver actualDataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, configuration);
    DBPConnectionConfiguration actualConfiguration =
        actualDataSourceVariableResolver.getConfiguration();
    DBPDataSourceContainer actualDataSourceContainer =
        actualDataSourceVariableResolver.getDataSourceContainer();

    // Assert
    assertFalse(actualDataSourceVariableResolver.isSecure());
    assertSame(configuration, actualConfiguration);
    assertSame(dataSourceContainer, actualDataSourceContainer);
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.setConfigProfileName("foo.txt");

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration cfg =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    DBWNetworkProfile dbwNetworkProfile = new DBWNetworkProfile();
    dbwNetworkProfile.updateConfiguration(cfg);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getNetworkProfile(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(dbwNetworkProfile);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);
    when(dataSourceContainer.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);

    DBWHandlerDescriptor descriptor2 = mock(DBWHandlerDescriptor.class);
    when(descriptor2.getId()).thenReturn("42");
    when(descriptor2.getType()).thenReturn(DBWHandlerType.TUNNEL);

    DBWHandlerConfiguration handler =
        new DBWHandlerConfiguration(descriptor2, mock(DBPDataSourceContainer.class));
    handler.setEnabled(true);

    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.updateHandler(handler);

    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, configuration);

    // Act
    String actualGetResult =
        dataSourceVariableResolver.get(DBPConnectionConfiguration.VARIABLE_HOST_TUNNEL);

    // Assert
    verify(dataSourceContainer).getConnectionConfiguration();
    verify(dataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getNetworkProfile(null, "foo.txt");
    verify(descriptor).getId();
    verify(descriptor2, atLeast(1)).getId();
    verify(descriptor2).getType();
    assertEquals("", actualGetResult);
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>Given {@link DBPConnectionConfiguration#DBPConnectionConfiguration()} AuthProperties is
   *       {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_givenDBPConnectionConfigurationAuthPropertiesIsHashMap() {
    // Arrange
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setAuthProperties(new HashMap<>());
    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(mock(DBPDataSourceContainer.class), configuration);

    // Act and Assert
    assertNull(dataSourceVariableResolver.get(DBPConnectionConfiguration.VARIABLE_PREFIX_AUTH));
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>Given {@link DBPConnectionConfiguration#DBPConnectionConfiguration()} ConfigProfileName
   *       is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_givenDBPConnectionConfigurationConfigProfileNameIsEmptyString() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.setConfigProfileName("");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);
    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, new DBPConnectionConfiguration());

    // Act
    String actualGetResult =
        dataSourceVariableResolver.get(DBPConnectionConfiguration.VARIABLE_HOST_TUNNEL);

    // Assert
    verify(dataSourceContainer).getConnectionConfiguration();
    assertEquals("", actualGetResult);
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer} {@link DBPDataSourceContainer#getName()} return
   *       {@code Name}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_givenDBPDataSourceContainerGetNameReturnName_thenReturnName() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getName()).thenReturn("Name");
    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, null);

    // Act
    String actualGetResult =
        dataSourceVariableResolver.get(DBPConnectionConfiguration.VARIABLE_DATASOURCE);

    // Assert
    verify(dataSourceContainer).getName();
    assertEquals("Name", actualGetResult);
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer} {@link DBPDataSourceContainer#getName()} return
   *       {@code Name}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_givenDBPDataSourceContainerGetNameReturnName_thenReturnName2() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getName()).thenReturn("Name");
    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, new DBPConnectionConfiguration());

    // Act
    String actualGetResult =
        dataSourceVariableResolver.get(DBPConnectionConfiguration.VARIABLE_DATASOURCE);

    // Assert
    verify(dataSourceContainer).getName();
    assertEquals("Name", actualGetResult);
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer} {@link DBPDataSourceContainer#getName()} throw
   *       {@link RuntimeException#RuntimeException()}.
   *   <li>Then calls {@link DBPDataSourceContainer#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_givenDBPDataSourceContainerGetNameThrowRuntimeException_thenCallsGetName() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getName()).thenThrow(new RuntimeException());
    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, null);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> dataSourceVariableResolver.get(DBPConnectionConfiguration.VARIABLE_DATASOURCE));
    verify(dataSourceContainer).getName();
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer} {@link DBPDataSourceContainer#getProject()} throw
   *       {@link RuntimeException#RuntimeException()}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_givenDBPDataSourceContainerGetProjectThrowRuntimeException() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getProject()).thenThrow(new RuntimeException());
    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, null);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> dataSourceVariableResolver.get(DBPConnectionConfiguration.VAR_PROJECT_NAME));
    verify(dataSourceContainer).getProject();
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceRegistry} {@link
   *       DBPDataSourceRegistry#getNetworkProfile(String, String)} return {@code null}.
   *   <li>Then calls {@link DBWHandlerDescriptor#getType()}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_givenDBPDataSourceRegistryGetNetworkProfileReturnNull_thenCallsGetType() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.setConfigProfileName("foo.txt");

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getNetworkProfile(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);
    when(dataSourceContainer.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getType()).thenReturn(DBWHandlerType.TUNNEL);
    when(descriptor.getId()).thenReturn("42");

    DBWHandlerConfiguration handler =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));
    handler.setEnabled(true);

    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.updateHandler(handler);

    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, configuration);

    // Act
    String actualGetResult =
        dataSourceVariableResolver.get(DBPConnectionConfiguration.VARIABLE_HOST_TUNNEL);

    // Assert
    verify(dataSourceContainer).getConnectionConfiguration();
    verify(dataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getNetworkProfile(null, "foo.txt");
    verify(descriptor).getId();
    verify(descriptor).getType();
    assertEquals("", actualGetResult);
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>Given {@link DBPProject} {@link DBPProject#getName()} return {@code Name}.
   *   <li>When {@link DBPConnectionConfiguration#VAR_PROJECT_NAME}.
   *   <li>Then calls {@link DBPProject#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_givenDBPProjectGetNameReturnName_whenVar_project_name_thenCallsGetName() {
    // Arrange
    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getName()).thenReturn("Name");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getProject()).thenReturn(dbpProject);
    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, null);

    // Act
    String actualGetResult =
        dataSourceVariableResolver.get(DBPConnectionConfiguration.VAR_PROJECT_NAME);

    // Assert
    verify(dataSourceContainer).getProject();
    verify(dbpProject).getName();
    assertEquals("Name", actualGetResult);
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>Given {@link DBPProject} {@link DBPProject#getName()} throw {@link
   *       RuntimeException#RuntimeException()}.
   *   <li>Then calls {@link DBPProject#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_givenDBPProjectGetNameThrowRuntimeException_thenCallsGetName() {
    // Arrange
    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getName()).thenThrow(new RuntimeException());

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getProject()).thenReturn(dbpProject);
    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, null);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> dataSourceVariableResolver.get(DBPConnectionConfiguration.VAR_PROJECT_NAME));
    verify(dataSourceContainer).getProject();
    verify(dbpProject).getName();
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>Given {@link DBWHandlerDescriptor} {@link DBWHandlerDescriptor#getId()} return {@code
   *       42}.
   *   <li>Then calls {@link DBWHandlerDescriptor#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_givenDBWHandlerDescriptorGetIdReturn42_thenCallsGetId() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.setConfigProfileName("foo.txt");

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getNetworkProfile(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new DBWNetworkProfile());

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);
    when(dataSourceContainer.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration handler =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.updateHandler(handler);

    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, configuration);

    // Act
    String actualGetResult =
        dataSourceVariableResolver.get(DBPConnectionConfiguration.VARIABLE_HOST_TUNNEL);

    // Assert
    verify(dataSourceContainer).getConnectionConfiguration();
    verify(dataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getNetworkProfile(null, "foo.txt");
    verify(descriptor).getId();
    assertEquals("", actualGetResult);
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>Given {@link DBWHandlerDescriptor} {@link DBWHandlerDescriptor#getType()} return {@code
   *       TUNNEL}.
   *   <li>Then calls {@link DBWHandlerDescriptor#getType()}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_givenDBWHandlerDescriptorGetTypeReturnTunnel_thenCallsGetType() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.setConfigProfileName("foo.txt");

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getNetworkProfile(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new DBWNetworkProfile());

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);
    when(dataSourceContainer.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getType()).thenReturn(DBWHandlerType.TUNNEL);
    when(descriptor.getId()).thenReturn("42");

    DBWHandlerConfiguration handler =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));
    handler.setEnabled(true);

    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.updateHandler(handler);

    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, configuration);

    // Act
    String actualGetResult =
        dataSourceVariableResolver.get(DBPConnectionConfiguration.VARIABLE_HOST_TUNNEL);

    // Assert
    verify(dataSourceContainer).getConnectionConfiguration();
    verify(dataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getNetworkProfile(null, "foo.txt");
    verify(descriptor).getId();
    verify(descriptor).getType();
    assertEquals("", actualGetResult);
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getRegistry()}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_thenCallsGetRegistry() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration = new DBPConnectionConfiguration();
    dbpConnectionConfiguration.setConfigProfileName("foo.txt");

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getNetworkProfile(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new DBWNetworkProfile());

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);
    when(dataSourceContainer.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);
    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, new DBPConnectionConfiguration());

    // Act
    String actualGetResult =
        dataSourceVariableResolver.get(DBPConnectionConfiguration.VARIABLE_HOST_TUNNEL);

    // Assert
    verify(dataSourceContainer).getConnectionConfiguration();
    verify(dataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getNetworkProfile(null, "foo.txt");
    assertEquals("", actualGetResult);
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_thenReturnEmptyString() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());
    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, new DBPConnectionConfiguration());

    // Act
    String actualGetResult =
        dataSourceVariableResolver.get(DBPConnectionConfiguration.VARIABLE_HOST_TUNNEL);

    // Assert
    verify(dataSourceContainer).getConnectionConfiguration();
    assertEquals("", actualGetResult);
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>Then return {@link DBPConnectionConfiguration#VARIABLE_HOST_TUNNEL}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_thenReturnVariable_host_tunnel() {
    // Arrange
    DBPConnectionConfiguration configuration = new DBPConnectionConfiguration();
    configuration.setHostName(DBPConnectionConfiguration.VARIABLE_HOST_TUNNEL);
    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(mock(DBPDataSourceContainer.class), configuration);

    // Act and Assert
    assertEquals(
        DBPConnectionConfiguration.VARIABLE_HOST_TUNNEL,
        dataSourceVariableResolver.get(DBPConnectionConfiguration.VARIABLE_HOST_TUNNEL));
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>When {@code home}.
   *   <li>Then return Property is {@code user.home}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_whenHome_thenReturnPropertyIsUserHome() {
    // Arrange
    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(null, null);

    // Act and Assert
    assertEquals(System.getProperty("user.home"), dataSourceVariableResolver.get("home"));
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>When {@code home}.
   *   <li>Then return Property is {@code user.home}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_whenHome_thenReturnPropertyIsUserHome2() {
    // Arrange
    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(mock(DBPDataSourceContainer.class), null);

    // Act and Assert
    assertEquals(System.getProperty("user.home"), dataSourceVariableResolver.get("home"));
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>When {@link DBPConnectionConfiguration#VARIABLE_CONN_TYPE_LEGACY}.
   *   <li>Then return {@code dev}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_whenVariable_conn_type_legacy_thenReturnDev() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, new DBPConnectionConfiguration());

    // Act and Assert
    assertEquals(
        "dev",
        dataSourceVariableResolver.get(DBPConnectionConfiguration.VARIABLE_CONN_TYPE_LEGACY));
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>When {@link DBPConnectionConfiguration#VARIABLE_CONN_TYPE}.
   *   <li>Then return {@code dev}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_whenVariable_conn_type_thenReturnDev() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, new DBPConnectionConfiguration());

    // Act and Assert
    assertEquals(
        "dev", dataSourceVariableResolver.get(DBPConnectionConfiguration.VARIABLE_CONN_TYPE));
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>When {@link DBPConnectionConfiguration#VARIABLE_HOST}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_whenVariable_host_thenReturnNull() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, new DBPConnectionConfiguration());

    // Act and Assert
    assertNull(dataSourceVariableResolver.get(DBPConnectionConfiguration.VARIABLE_HOST));
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>When {@link DBPConnectionConfiguration#VARIABLE_PORT}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_whenVariable_port_thenReturnNull() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, new DBPConnectionConfiguration());

    // Act and Assert
    assertNull(dataSourceVariableResolver.get(DBPConnectionConfiguration.VARIABLE_PORT));
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>When {@link DBPConnectionConfiguration#VARIABLE_PREFIX_AUTH}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_whenVariable_prefix_auth_thenReturnNull() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, new DBPConnectionConfiguration());

    // Act and Assert
    assertNull(dataSourceVariableResolver.get(DBPConnectionConfiguration.VARIABLE_PREFIX_AUTH));
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>When {@link DBPConnectionConfiguration#VARIABLE_PREFIX_ORIGIN}.
   *   <li>Then calls {@link DBPDataSourceContainer#getOrigin()}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_whenVariable_prefix_origin_thenCallsGetOrigin() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getOrigin()).thenThrow(new RuntimeException());
    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, null);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> dataSourceVariableResolver.get(DBPConnectionConfiguration.VARIABLE_PREFIX_ORIGIN));
    verify(dataSourceContainer).getOrigin();
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>When {@link DBPConnectionConfiguration#VARIABLE_PREFIX_PROPERTIES}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_whenVariable_prefix_properties_thenReturnNull() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, new DBPConnectionConfiguration());

    // Act and Assert
    assertNull(
        dataSourceVariableResolver.get(DBPConnectionConfiguration.VARIABLE_PREFIX_PROPERTIES));
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>When {@link DBPConnectionConfiguration#VARIABLE_SERVER}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_whenVariable_server_thenReturnNull() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, new DBPConnectionConfiguration());

    // Act and Assert
    assertNull(dataSourceVariableResolver.get(DBPConnectionConfiguration.VARIABLE_SERVER));
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>When {@link DBPConnectionConfiguration#VARIABLE_URL}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_whenVariable_url_thenReturnNull() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, new DBPConnectionConfiguration());

    // Act and Assert
    assertNull(dataSourceVariableResolver.get(DBPConnectionConfiguration.VARIABLE_URL));
  }

  /**
   * Test {@link DataSourceVariableResolver#get(String)}.
   *
   * <ul>
   *   <li>When {@link DBPConnectionConfiguration#VARIABLE_USER}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceVariableResolver#get(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceVariableResolver.get(String)"})
  public void testGet_whenVariable_user_thenReturnNull() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DataSourceVariableResolver dataSourceVariableResolver =
        new DataSourceVariableResolver(dataSourceContainer, new DBPConnectionConfiguration());

    // Act and Assert
    assertNull(dataSourceVariableResolver.get(DBPConnectionConfiguration.VARIABLE_USER));
  }
}
