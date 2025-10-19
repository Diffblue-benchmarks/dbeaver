package org.jkiss.dbeaver.model.net;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.jkiss.dbeaver.model.impl.net.HTTPTunnelImpl;
import org.jkiss.dbeaver.runtime.IVariableResolver;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBWHandlerConfigurationDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBWHandlerConfiguration#DBWHandlerConfiguration()}
   *   <li>{@link DBWHandlerConfiguration#setDataSource(DBPDataSourceContainer)}
   *   <li>{@link DBWHandlerConfiguration#setEnabled(boolean)}
   *   <li>{@link DBWHandlerConfiguration#setPassword(String)}
   *   <li>{@link DBWHandlerConfiguration#setSavePassword(boolean)}
   *   <li>{@link DBWHandlerConfiguration#setUserName(String)}
   *   <li>{@link DBWHandlerConfiguration#getDataSource()}
   *   <li>{@link DBWHandlerConfiguration#getId()}
   *   <li>{@link DBWHandlerConfiguration#getPassword()}
   *   <li>{@link DBWHandlerConfiguration#getProperties()}
   *   <li>{@link DBWHandlerConfiguration#getSecureProperties()}
   *   <li>{@link DBWHandlerConfiguration#getUserName()}
   *   <li>{@link DBWHandlerConfiguration#isEnabled()}
   *   <li>{@link DBWHandlerConfiguration#isSavePassword()}
   *   <li>{@link DBWHandlerConfiguration#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBWHandlerConfiguration.<init>()",
    "DBPDataSourceContainer DBWHandlerConfiguration.getDataSource()",
    "String DBWHandlerConfiguration.getId()",
    "String DBWHandlerConfiguration.getPassword()",
    "Map DBWHandlerConfiguration.getProperties()",
    "Map DBWHandlerConfiguration.getSecureProperties()",
    "String DBWHandlerConfiguration.getUserName()",
    "boolean DBWHandlerConfiguration.isEnabled()",
    "boolean DBWHandlerConfiguration.isSavePassword()",
    "void DBWHandlerConfiguration.setDataSource(DBPDataSourceContainer)",
    "void DBWHandlerConfiguration.setEnabled(boolean)",
    "void DBWHandlerConfiguration.setPassword(String)",
    "void DBWHandlerConfiguration.setSavePassword(boolean)",
    "void DBWHandlerConfiguration.setUserName(String)",
    "String DBWHandlerConfiguration.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBWHandlerConfiguration actualDbwHandlerConfiguration = new DBWHandlerConfiguration();
    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    actualDbwHandlerConfiguration.setDataSource(dataSource);
    actualDbwHandlerConfiguration.setEnabled(true);
    actualDbwHandlerConfiguration.setPassword("iloveyou");
    actualDbwHandlerConfiguration.setSavePassword(true);
    actualDbwHandlerConfiguration.setUserName("janedoe");
    DBPDataSourceContainer actualDataSource = actualDbwHandlerConfiguration.getDataSource();
    String actualId = actualDbwHandlerConfiguration.getId();
    String actualPassword = actualDbwHandlerConfiguration.getPassword();
    Map<String, Object> actualProperties = actualDbwHandlerConfiguration.getProperties();
    Map<String, String> actualSecureProperties =
        actualDbwHandlerConfiguration.getSecureProperties();
    String actualUserName = actualDbwHandlerConfiguration.getUserName();
    boolean actualIsEnabledResult = actualDbwHandlerConfiguration.isEnabled();
    boolean actualIsSavePasswordResult = actualDbwHandlerConfiguration.isSavePassword();

    // Assert
    assertEquals("iloveyou", actualPassword);
    assertEquals("janedoe", actualUserName);
    assertNull(actualId);
    assertNull(actualDbwHandlerConfiguration.toString());
    assertTrue(actualProperties.isEmpty());
    assertTrue(actualSecureProperties.isEmpty());
    assertTrue(actualIsEnabledResult);
    assertTrue(actualIsSavePasswordResult);
    assertSame(dataSource, actualDataSource);
  }

  /**
   * Test {@link DBWHandlerConfiguration#DBWHandlerConfiguration(DBWHandlerDescriptor,
   * DBPDataSourceContainer)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then return Id is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBWHandlerConfiguration#DBWHandlerConfiguration(DBWHandlerDescriptor, DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBWHandlerConfiguration.<init>(DBWHandlerDescriptor, DBPDataSourceContainer)"
  })
  public void testNewDBWHandlerConfiguration_given42_thenReturnIdIs42() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);

    // Act
    DBWHandlerConfiguration actualDbwHandlerConfiguration =
        new DBWHandlerConfiguration(descriptor, dataSource);

    // Assert
    verify(descriptor).getId();
    assertEquals("42", actualDbwHandlerConfiguration.getId());
    assertEquals("42", actualDbwHandlerConfiguration.toString());
    assertNull(actualDbwHandlerConfiguration.getPassword());
    assertNull(actualDbwHandlerConfiguration.getTitle());
    assertNull(actualDbwHandlerConfiguration.getUserName());
    assertNull(actualDbwHandlerConfiguration.getDriver());
    assertNull(actualDbwHandlerConfiguration.getType());
    assertFalse(actualDbwHandlerConfiguration.hasValuableInfo());
    assertFalse(actualDbwHandlerConfiguration.isEnabled());
    assertFalse(actualDbwHandlerConfiguration.isSecured());
    assertTrue(actualDbwHandlerConfiguration.getProperties().isEmpty());
    assertTrue(actualDbwHandlerConfiguration.getSecureProperties().isEmpty());
    assertTrue(actualDbwHandlerConfiguration.isSavePassword());
    assertSame(dataSource, actualDbwHandlerConfiguration.getDataSource());
    assertSame(descriptor, actualDbwHandlerConfiguration.getHandlerDescriptor());
  }

  /**
   * Test {@link DBWHandlerConfiguration#DBWHandlerConfiguration(DBWHandlerDescriptor,
   * DBPDataSourceContainer)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBWHandlerConfiguration#DBWHandlerConfiguration(DBWHandlerDescriptor, DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBWHandlerConfiguration.<init>(DBWHandlerDescriptor, DBPDataSourceContainer)"
  })
  public void testNewDBWHandlerConfiguration_thenThrowIllegalStateException() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenThrow(new IllegalStateException());

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class)));
    verify(descriptor).getId();
  }

  /**
   * Test {@link DBWHandlerConfiguration#DBWHandlerConfiguration(DBWHandlerConfiguration)}.
   *
   * <ul>
   *   <li>When {@link DBWHandlerConfiguration#DBWHandlerConfiguration()}.
   *   <li>Then return Id is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBWHandlerConfiguration#DBWHandlerConfiguration(DBWHandlerConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWHandlerConfiguration.<init>(DBWHandlerConfiguration)"})
  public void testNewDBWHandlerConfiguration_whenDBWHandlerConfiguration_thenReturnIdIsNull() {
    // Arrange and Act
    DBWHandlerConfiguration actualDbwHandlerConfiguration =
        new DBWHandlerConfiguration(new DBWHandlerConfiguration());

    // Assert
    assertNull(actualDbwHandlerConfiguration.getId());
    assertNull(actualDbwHandlerConfiguration.getPassword());
    assertNull(actualDbwHandlerConfiguration.getUserName());
    assertNull(actualDbwHandlerConfiguration.toString());
    assertNull(actualDbwHandlerConfiguration.getDataSource());
    assertNull(actualDbwHandlerConfiguration.getDriver());
    assertFalse(actualDbwHandlerConfiguration.hasValuableInfo());
    assertFalse(actualDbwHandlerConfiguration.isEnabled());
    assertTrue(actualDbwHandlerConfiguration.getProperties().isEmpty());
    assertTrue(actualDbwHandlerConfiguration.getSecureProperties().isEmpty());
    assertTrue(actualDbwHandlerConfiguration.isSavePassword());
  }

  /**
   * Test {@link DBWHandlerConfiguration#getHandlerDescriptor()}.
   *
   * <ul>
   *   <li>Given {@link DBWHandlerDescriptor} {@link DBWHandlerDescriptor#getId()} return {@code
   *       42}.
   *   <li>Then calls {@link DBWHandlerDescriptor#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#getHandlerDescriptor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBWHandlerDescriptor DBWHandlerConfiguration.getHandlerDescriptor()"})
  public void testGetHandlerDescriptor_givenDBWHandlerDescriptorGetIdReturn42_thenCallsGetId() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration dbwHandlerConfiguration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act
    dbwHandlerConfiguration.getHandlerDescriptor();

    // Assert
    verify(descriptor).getId();
  }

  /**
   * Test {@link DBWHandlerConfiguration#createHandler(Class)}.
   *
   * <ul>
   *   <li>Then return {@link HTTPTunnelImpl} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#createHandler(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBWNetworkHandler DBWHandlerConfiguration.createHandler(Class)"})
  public void testCreateHandler_thenReturnHTTPTunnelImpl() throws DBException {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    HTTPTunnelImpl httpTunnelImpl = new HTTPTunnelImpl();
    when(descriptor.createHandler(DBWNetworkHandler.class)).thenReturn(httpTunnelImpl);
    DBWHandlerConfiguration dbwHandlerConfiguration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));
    Class<DBWNetworkHandler> type = DBWNetworkHandler.class;

    // Act
    DBWNetworkHandler actualCreateHandlerResult = dbwHandlerConfiguration.createHandler(type);

    // Assert
    verify(descriptor).createHandler(isA(Class.class));
    verify(descriptor).getId();
    assertSame(httpTunnelImpl, actualCreateHandlerResult);
  }

  /**
   * Test {@link DBWHandlerConfiguration#getDriver()}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer} {@link DBPDataSourceContainer#getDriver()} return
   *       {@link DBPDriver}.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#getDriver()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDriver DBWHandlerConfiguration.getDriver()"})
  public void testGetDriver_givenDBPDataSourceContainerGetDriverReturnDBPDriver() {
    // Arrange
    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getDriver()).thenReturn(mock(DBPDriver.class));

    DBWHandlerConfiguration dbwHandlerConfiguration =
        new DBWHandlerConfiguration(new DBWHandlerConfiguration());
    dbwHandlerConfiguration.setDataSource(dataSource);

    // Act
    dbwHandlerConfiguration.getDriver();

    // Assert
    verify(dataSource).getDriver();
  }

  /**
   * Test {@link DBWHandlerConfiguration#getDriver()}.
   *
   * <ul>
   *   <li>Given {@link DBWHandlerConfiguration#DBWHandlerConfiguration()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#getDriver()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDriver DBWHandlerConfiguration.getDriver()"})
  public void testGetDriver_givenDBWHandlerConfiguration_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DBWHandlerConfiguration().getDriver());
  }

  /**
   * Test {@link DBWHandlerConfiguration#getDriver()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#getDriver()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDriver DBWHandlerConfiguration.getDriver()"})
  public void testGetDriver_thenThrowIllegalStateException() {
    // Arrange
    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getDriver()).thenThrow(new IllegalStateException());

    DBWHandlerConfiguration dbwHandlerConfiguration =
        new DBWHandlerConfiguration(new DBWHandlerConfiguration());
    dbwHandlerConfiguration.setDataSource(dataSource);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dbwHandlerConfiguration.getDriver());
    verify(dataSource).getDriver();
  }

  /**
   * Test {@link DBWHandlerConfiguration#getType()}.
   *
   * <ul>
   *   <li>Given {@link DBWHandlerDescriptor} {@link DBWHandlerDescriptor#getId()} return {@code
   *       42}.
   *   <li>Then return {@code TUNNEL}.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#getType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBWHandlerType DBWHandlerConfiguration.getType()"})
  public void testGetType_givenDBWHandlerDescriptorGetIdReturn42_thenReturnTunnel() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    when(descriptor.getType()).thenReturn(DBWHandlerType.TUNNEL);
    DBWHandlerConfiguration dbwHandlerConfiguration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act
    DBWHandlerType actualType = dbwHandlerConfiguration.getType();

    // Assert
    verify(descriptor).getId();
    verify(descriptor).getType();
    assertEquals(DBWHandlerType.TUNNEL, actualType);
  }

  /**
   * Test {@link DBWHandlerConfiguration#isSecured()}.
   *
   * <ul>
   *   <li>Given {@link DBWHandlerDescriptor} {@link DBWHandlerDescriptor#isSecured()} return {@code
   *       false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#isSecured()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBWHandlerConfiguration.isSecured()"})
  public void testIsSecured_givenDBWHandlerDescriptorIsSecuredReturnFalse_thenReturnFalse() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.isSecured()).thenReturn(false);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration dbwHandlerConfiguration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act
    boolean actualIsSecuredResult = dbwHandlerConfiguration.isSecured();

    // Assert
    verify(descriptor).getId();
    verify(descriptor).isSecured();
    assertFalse(actualIsSecuredResult);
  }

  /**
   * Test {@link DBWHandlerConfiguration#isSecured()}.
   *
   * <ul>
   *   <li>Given {@link DBWHandlerDescriptor} {@link DBWHandlerDescriptor#isSecured()} return {@code
   *       true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#isSecured()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBWHandlerConfiguration.isSecured()"})
  public void testIsSecured_givenDBWHandlerDescriptorIsSecuredReturnTrue_thenReturnTrue() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.isSecured()).thenReturn(true);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration dbwHandlerConfiguration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act
    boolean actualIsSecuredResult = dbwHandlerConfiguration.isSecured();

    // Assert
    verify(descriptor).getId();
    verify(descriptor).isSecured();
    assertTrue(actualIsSecuredResult);
  }

  /**
   * Test {@link DBWHandlerConfiguration#getTitle()}.
   *
   * <ul>
   *   <li>Given {@link DBWHandlerDescriptor} {@link DBWHandlerDescriptor#getId()} return {@code
   *       42}.
   *   <li>Then return {@code Label}.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#getTitle()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBWHandlerConfiguration.getTitle()"})
  public void testGetTitle_givenDBWHandlerDescriptorGetIdReturn42_thenReturnLabel() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    when(descriptor.getLabel()).thenReturn("Label");
    DBWHandlerConfiguration dbwHandlerConfiguration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act
    String actualTitle = dbwHandlerConfiguration.getTitle();

    // Assert
    verify(descriptor).getId();
    verify(descriptor).getLabel();
    assertEquals("Label", actualTitle);
  }

  /**
   * Test {@link DBWHandlerConfiguration#getProperty(String)}.
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#getProperty(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object DBWHandlerConfiguration.getProperty(String)"})
  public void testGetProperty() {
    // Arrange, Act and Assert
    assertNull(new DBWHandlerConfiguration().getProperty("Name"));
  }

  /**
   * Test {@link DBWHandlerConfiguration#getStringProperty(String)}.
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#getStringProperty(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBWHandlerConfiguration.getStringProperty(String)"})
  public void testGetStringProperty() {
    // Arrange, Act and Assert
    assertNull(new DBWHandlerConfiguration().getStringProperty("Name"));
  }

  /**
   * Test {@link DBWHandlerConfiguration#getIntProperty(String)} with {@code name}.
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#getIntProperty(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBWHandlerConfiguration.getIntProperty(String)"})
  public void testGetIntPropertyWithName() {
    // Arrange, Act and Assert
    assertEquals(0, new DBWHandlerConfiguration().getIntProperty("Name"));
  }

  /**
   * Test {@link DBWHandlerConfiguration#getIntProperty(String, int)} with {@code name}, {@code
   * defValue}.
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#getIntProperty(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBWHandlerConfiguration.getIntProperty(String, int)"})
  public void testGetIntPropertyWithNameDefValue() {
    // Arrange, Act and Assert
    assertEquals(42, new DBWHandlerConfiguration().getIntProperty("Name", 42));
  }

  /**
   * Test {@link DBWHandlerConfiguration#getBooleanProperty(String)} with {@code name}.
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#getBooleanProperty(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBWHandlerConfiguration.getBooleanProperty(String)"})
  public void testGetBooleanPropertyWithName() {
    // Arrange, Act and Assert
    assertFalse(new DBWHandlerConfiguration().getBooleanProperty("Name"));
  }

  /**
   * Test {@link DBWHandlerConfiguration#getBooleanProperty(String, boolean)} with {@code name},
   * {@code defValue}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#getBooleanProperty(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBWHandlerConfiguration.getBooleanProperty(String, boolean)"})
  public void testGetBooleanPropertyWithNameDefValue_whenFalse_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DBWHandlerConfiguration().getBooleanProperty("Name", false));
  }

  /**
   * Test {@link DBWHandlerConfiguration#getBooleanProperty(String, boolean)} with {@code name},
   * {@code defValue}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#getBooleanProperty(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBWHandlerConfiguration.getBooleanProperty(String, boolean)"})
  public void testGetBooleanPropertyWithNameDefValue_whenTrue_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new DBWHandlerConfiguration().getBooleanProperty("Name", true));
  }

  /**
   * Test {@link DBWHandlerConfiguration#setProperty(String, Object)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then not {@link DBWHandlerConfiguration#DBWHandlerConfiguration()} hasValuableInfo.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#setProperty(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWHandlerConfiguration.setProperty(String, Object)"})
  public void testSetProperty_whenNull_thenNotDBWHandlerConfigurationHasValuableInfo() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();

    // Act
    dbwHandlerConfiguration.setProperty("Name", null);

    // Assert that nothing has changed
    assertFalse(dbwHandlerConfiguration.hasValuableInfo());
    assertTrue(dbwHandlerConfiguration.getProperties().isEmpty());
  }

  /**
   * Test {@link DBWHandlerConfiguration#setProperty(String, Object)}.
   *
   * <ul>
   *   <li>When {@link DBPEvent#RENAME}.
   *   <li>Then {@link DBWHandlerConfiguration#DBWHandlerConfiguration()} Properties size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#setProperty(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWHandlerConfiguration.setProperty(String, Object)"})
  public void testSetProperty_whenRename_thenDBWHandlerConfigurationPropertiesSizeIsOne() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();
    Object object = DBPEvent.RENAME;

    // Act
    dbwHandlerConfiguration.setProperty("Name", object);

    // Assert
    Map<String, Object> properties = dbwHandlerConfiguration.getProperties();
    assertEquals(1, properties.size());
    assertTrue(dbwHandlerConfiguration.hasValuableInfo());
    assertSame(object, properties.get("Name"));
  }

  /**
   * Test {@link DBWHandlerConfiguration#getSecureProperty(String)}.
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#getSecureProperty(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBWHandlerConfiguration.getSecureProperty(String)"})
  public void testGetSecureProperty() {
    // Arrange, Act and Assert
    assertNull(new DBWHandlerConfiguration().getSecureProperty("Name"));
  }

  /**
   * Test {@link DBWHandlerConfiguration#setSecureProperty(String, String)}.
   *
   * <ul>
   *   <li>Then {@link DBWHandlerConfiguration#DBWHandlerConfiguration()} SecureProperties size is
   *       one.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#setSecureProperty(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWHandlerConfiguration.setSecureProperty(String, String)"})
  public void testSetSecureProperty_thenDBWHandlerConfigurationSecurePropertiesSizeIsOne() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();

    // Act
    dbwHandlerConfiguration.setSecureProperty("Name", "42");

    // Assert
    Map<String, String> secureProperties = dbwHandlerConfiguration.getSecureProperties();
    assertEquals(1, secureProperties.size());
    assertEquals("42", secureProperties.get("Name"));
    assertTrue(dbwHandlerConfiguration.hasValuableInfo());
  }

  /**
   * Test {@link DBWHandlerConfiguration#setSecureProperty(String, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then not {@link DBWHandlerConfiguration#DBWHandlerConfiguration()} hasValuableInfo.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#setSecureProperty(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWHandlerConfiguration.setSecureProperty(String, String)"})
  public void testSetSecureProperty_whenNull_thenNotDBWHandlerConfigurationHasValuableInfo() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();

    // Act
    dbwHandlerConfiguration.setSecureProperty("Name", null);

    // Assert that nothing has changed
    assertFalse(dbwHandlerConfiguration.hasValuableInfo());
    assertTrue(dbwHandlerConfiguration.getSecureProperties().isEmpty());
  }

  /**
   * Test {@link DBWHandlerConfiguration#saveToMap()}.
   *
   * <ul>
   *   <li>Given {@link DBWHandlerConfiguration#DBWHandlerConfiguration()} SavePassword is {@code
   *       false}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#saveToMap()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBWHandlerConfiguration.saveToMap()"})
  public void testSaveToMap_givenDBWHandlerConfigurationSavePasswordIsFalse_thenReturnEmpty() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();
    dbwHandlerConfiguration.setSavePassword(false);

    // Act and Assert
    assertTrue(dbwHandlerConfiguration.saveToMap().isEmpty());
  }

  /**
   * Test {@link DBWHandlerConfiguration#saveToMap()}.
   *
   * <ul>
   *   <li>Given {@link DBWHandlerConfiguration#DBWHandlerConfiguration()} UserName is empty string.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#saveToMap()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBWHandlerConfiguration.saveToMap()"})
  public void testSaveToMap_givenDBWHandlerConfigurationUserNameIsEmptyString_thenReturnEmpty() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();
    dbwHandlerConfiguration.setUserName("");

    // Act and Assert
    assertTrue(dbwHandlerConfiguration.saveToMap().isEmpty());
  }

  /**
   * Test {@link DBWHandlerConfiguration#saveToMap()}.
   *
   * <ul>
   *   <li>Given {@link DBWHandlerConfiguration#DBWHandlerConfiguration()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#saveToMap()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBWHandlerConfiguration.saveToMap()"})
  public void testSaveToMap_givenDBWHandlerConfiguration_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(new DBWHandlerConfiguration().saveToMap().isEmpty());
  }

  /**
   * Test {@link DBWHandlerConfiguration#saveToMap()}.
   *
   * <ul>
   *   <li>Then return {@code password} is {@code iloveyou}.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#saveToMap()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBWHandlerConfiguration.saveToMap()"})
  public void testSaveToMap_thenReturnPasswordIsIloveyou() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();
    dbwHandlerConfiguration.setPassword("iloveyou");

    // Act
    Map<String, Object> actualSaveToMapResult = dbwHandlerConfiguration.saveToMap();

    // Assert
    assertEquals(1, actualSaveToMapResult.size());
    assertEquals("iloveyou", actualSaveToMapResult.get("password"));
  }

  /**
   * Test {@link DBWHandlerConfiguration#saveToMap()}.
   *
   * <ul>
   *   <li>Then return {@code user} is {@code janedoe}.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#saveToMap()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBWHandlerConfiguration.saveToMap()"})
  public void testSaveToMap_thenReturnUserIsJanedoe() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();
    dbwHandlerConfiguration.setUserName("janedoe");

    // Act
    Map<String, Object> actualSaveToMapResult = dbwHandlerConfiguration.saveToMap();

    // Assert
    assertEquals(1, actualSaveToMapResult.size());
    assertEquals("janedoe", actualSaveToMapResult.get("user"));
  }

  /**
   * Test {@link DBWHandlerConfiguration#saveToSecret()}.
   *
   * <ul>
   *   <li>Given {@link DBWHandlerConfiguration#DBWHandlerConfiguration()} SavePassword is {@code
   *       false}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#saveToSecret()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBWHandlerConfiguration.saveToSecret()"})
  public void testSaveToSecret_givenDBWHandlerConfigurationSavePasswordIsFalse_thenReturnEmpty() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();
    dbwHandlerConfiguration.setSavePassword(false);

    // Act and Assert
    assertTrue(dbwHandlerConfiguration.saveToSecret().isEmpty());
  }

  /**
   * Test {@link DBWHandlerConfiguration#saveToSecret()}.
   *
   * <ul>
   *   <li>Given {@link DBWHandlerConfiguration#DBWHandlerConfiguration()} UserName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#saveToSecret()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBWHandlerConfiguration.saveToSecret()"})
  public void testSaveToSecret_givenDBWHandlerConfigurationUserNameIsEmptyString() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();
    dbwHandlerConfiguration.setUserName("");

    // Act and Assert
    assertTrue(dbwHandlerConfiguration.saveToSecret().isEmpty());
  }

  /**
   * Test {@link DBWHandlerConfiguration#saveToSecret()}.
   *
   * <ul>
   *   <li>Given {@link DBWHandlerConfiguration#DBWHandlerConfiguration()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#saveToSecret()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBWHandlerConfiguration.saveToSecret()"})
  public void testSaveToSecret_givenDBWHandlerConfiguration_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(new DBWHandlerConfiguration().saveToSecret().isEmpty());
  }

  /**
   * Test {@link DBWHandlerConfiguration#saveToSecret()}.
   *
   * <ul>
   *   <li>Then return {@code password} is {@code iloveyou}.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#saveToSecret()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBWHandlerConfiguration.saveToSecret()"})
  public void testSaveToSecret_thenReturnPasswordIsIloveyou() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();
    dbwHandlerConfiguration.setPassword("iloveyou");

    // Act
    Map<String, Object> actualSaveToSecretResult = dbwHandlerConfiguration.saveToSecret();

    // Assert
    assertEquals(1, actualSaveToSecretResult.size());
    assertEquals("iloveyou", actualSaveToSecretResult.get("password"));
  }

  /**
   * Test {@link DBWHandlerConfiguration#saveToSecret()}.
   *
   * <ul>
   *   <li>Then return {@code user} is {@code janedoe}.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#saveToSecret()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBWHandlerConfiguration.saveToSecret()"})
  public void testSaveToSecret_thenReturnUserIsJanedoe() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();
    dbwHandlerConfiguration.setUserName("janedoe");

    // Act
    Map<String, Object> actualSaveToSecretResult = dbwHandlerConfiguration.saveToSecret();

    // Assert
    assertEquals(1, actualSaveToSecretResult.size());
    assertEquals("janedoe", actualSaveToSecretResult.get("user"));
  }

  /**
   * Test {@link DBWHandlerConfiguration#loadFromMap(Map)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code null} is {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#loadFromMap(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWHandlerConfiguration.loadFromMap(Map)"})
  public void testLoadFromMap_givenHashMapNullIsRename() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();

    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put(null, DBPEvent.RENAME);

    HashMap<String, Object> handlerMap = new HashMap<>();
    handlerMap.put("user", null);
    handlerMap.put("password", null);
    handlerMap.put("properties", objectObjectMap);

    // Act
    dbwHandlerConfiguration.loadFromMap(handlerMap);

    // Assert
    assertNull(dbwHandlerConfiguration.getPassword());
    assertEquals(1, dbwHandlerConfiguration.getSecureProperties().size());
    assertTrue(dbwHandlerConfiguration.hasValuableInfo());
  }

  /**
   * Test {@link DBWHandlerConfiguration#loadFromMap(Map)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@link DBPEvent#RENAME} is {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#loadFromMap(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWHandlerConfiguration.loadFromMap(Map)"})
  public void testLoadFromMap_givenHashMapRenameIsRename() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();

    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put(DBPEvent.RENAME, DBPEvent.RENAME);

    HashMap<String, Object> handlerMap = new HashMap<>();
    handlerMap.put("user", null);
    handlerMap.put("password", null);
    handlerMap.put("properties", objectObjectMap);

    // Act
    dbwHandlerConfiguration.loadFromMap(handlerMap);

    // Assert
    assertNull(dbwHandlerConfiguration.getPassword());
    assertEquals(1, dbwHandlerConfiguration.getSecureProperties().size());
    assertTrue(dbwHandlerConfiguration.hasValuableInfo());
  }

  /**
   * Test {@link DBWHandlerConfiguration#loadFromMap(Map)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code user} is {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#loadFromMap(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWHandlerConfiguration.loadFromMap(Map)"})
  public void testLoadFromMap_givenHashMapUserIsRename() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();

    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put("user", DBPEvent.RENAME);

    HashMap<String, Object> handlerMap = new HashMap<>();
    handlerMap.put("user", null);
    handlerMap.put("password", null);
    handlerMap.put("properties", objectObjectMap);

    // Act
    dbwHandlerConfiguration.loadFromMap(handlerMap);

    // Assert
    assertNull(dbwHandlerConfiguration.getPassword());
    assertEquals(1, dbwHandlerConfiguration.getSecureProperties().size());
    assertTrue(dbwHandlerConfiguration.hasValuableInfo());
  }

  /**
   * Test {@link DBWHandlerConfiguration#loadFromMap(Map)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()}.
   *   <li>Then not {@link DBWHandlerConfiguration#DBWHandlerConfiguration()} hasValuableInfo.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#loadFromMap(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWHandlerConfiguration.loadFromMap(Map)"})
  public void testLoadFromMap_givenHashMap_thenNotDBWHandlerConfigurationHasValuableInfo() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();

    HashMap<String, Object> handlerMap = new HashMap<>();
    handlerMap.put("user", null);
    handlerMap.put("password", null);
    handlerMap.put("properties", new HashMap<>());

    // Act
    dbwHandlerConfiguration.loadFromMap(handlerMap);

    // Assert that nothing has changed
    assertFalse(dbwHandlerConfiguration.hasValuableInfo());
    assertTrue(dbwHandlerConfiguration.getSecureProperties().isEmpty());
  }

  /**
   * Test {@link DBWHandlerConfiguration#loadFromMap(Map)}.
   *
   * <ul>
   *   <li>Given {@link DBPEvent#RENAME}.
   *   <li>When {@link HashMap#HashMap()} {@code password} is {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#loadFromMap(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWHandlerConfiguration.loadFromMap(Map)"})
  public void testLoadFromMap_givenRename_whenHashMapPasswordIsRename() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();

    HashMap<String, Object> handlerMap = new HashMap<>();
    handlerMap.put("user", null);
    handlerMap.put("password", DBPEvent.RENAME);
    handlerMap.put("properties", new HashMap<>());

    // Act
    dbwHandlerConfiguration.loadFromMap(handlerMap);

    // Assert
    assertTrue(dbwHandlerConfiguration.getSecureProperties().isEmpty());
    assertTrue(dbwHandlerConfiguration.hasValuableInfo());
  }

  /**
   * Test {@link DBWHandlerConfiguration#loadFromMap(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then not {@link DBWHandlerConfiguration#DBWHandlerConfiguration()} hasValuableInfo.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#loadFromMap(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWHandlerConfiguration.loadFromMap(Map)"})
  public void testLoadFromMap_whenHashMap_thenNotDBWHandlerConfigurationHasValuableInfo() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();

    // Act
    dbwHandlerConfiguration.loadFromMap(new HashMap<>());

    // Assert that nothing has changed
    assertFalse(dbwHandlerConfiguration.hasValuableInfo());
    assertTrue(dbwHandlerConfiguration.getSecureProperties().isEmpty());
  }

  /**
   * Test {@link DBWHandlerConfiguration#equals(Object)}, and {@link
   * DBWHandlerConfiguration#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBWHandlerConfiguration.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();
    DBWHandlerConfiguration dbwHandlerConfiguration2 = new DBWHandlerConfiguration();

    // Act and Assert
    assertEquals(dbwHandlerConfiguration, dbwHandlerConfiguration2);
    assertNotEquals(dbwHandlerConfiguration.hashCode(), dbwHandlerConfiguration2.hashCode());
  }

  /**
   * Test {@link DBWHandlerConfiguration#equals(Object)}, and {@link
   * DBWHandlerConfiguration#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBWHandlerConfiguration.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();

    // Act and Assert
    assertEquals(dbwHandlerConfiguration, dbwHandlerConfiguration);
    int expectedHashCodeResult = dbwHandlerConfiguration.hashCode();
    assertEquals(expectedHashCodeResult, dbwHandlerConfiguration.hashCode());
  }

  /**
   * Test {@link DBWHandlerConfiguration#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBWHandlerConfiguration.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration dbwHandlerConfiguration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act and Assert
    assertNotEquals(dbwHandlerConfiguration, new DBWHandlerConfiguration());
  }

  /**
   * Test {@link DBWHandlerConfiguration#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBWHandlerConfiguration.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration dbwHandlerConfiguration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    DBWHandlerDescriptor descriptor2 = mock(DBWHandlerDescriptor.class);
    when(descriptor2.getId()).thenReturn("42");

    // Act and Assert
    assertNotEquals(
        dbwHandlerConfiguration,
        new DBWHandlerConfiguration(descriptor2, mock(DBPDataSourceContainer.class)));
  }

  /**
   * Test {@link DBWHandlerConfiguration#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBWHandlerConfiguration.equals(Object)"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DBWHandlerConfiguration(), null);
  }

  /**
   * Test {@link DBWHandlerConfiguration#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBWHandlerConfiguration.equals(Object)"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DBWHandlerConfiguration(), "Different type to DBWHandlerConfiguration");
  }

  /**
   * Test {@link DBWHandlerConfiguration#resolveDynamicVariables(IVariableResolver)}.
   *
   * <ul>
   *   <li>Given {@code Get}.
   *   <li>Then {@link DBWHandlerConfiguration#DBWHandlerConfiguration()} UserName is {@code Get}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBWHandlerConfiguration#resolveDynamicVariables(IVariableResolver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWHandlerConfiguration.resolveDynamicVariables(IVariableResolver)"})
  public void testResolveDynamicVariables_givenGet_thenDBWHandlerConfigurationUserNameIsGet() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();
    dbwHandlerConfiguration.setUserName("${U:U}");

    IVariableResolver variableResolver = mock(IVariableResolver.class);
    when(variableResolver.get(Mockito.<String>any())).thenReturn("Get");

    // Act
    dbwHandlerConfiguration.resolveDynamicVariables(variableResolver);

    // Assert
    verify(variableResolver).get("U");
    assertEquals("Get", dbwHandlerConfiguration.getUserName());
  }

  /**
   * Test {@link DBWHandlerConfiguration#resolveDynamicVariables(IVariableResolver)}.
   *
   * <ul>
   *   <li>Then {@link DBWHandlerConfiguration#DBWHandlerConfiguration()} UserName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBWHandlerConfiguration#resolveDynamicVariables(IVariableResolver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWHandlerConfiguration.resolveDynamicVariables(IVariableResolver)"})
  public void testResolveDynamicVariables_thenDBWHandlerConfigurationUserNameIsEmptyString() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();
    dbwHandlerConfiguration.setUserName("");

    // Act
    dbwHandlerConfiguration.resolveDynamicVariables(mock(IVariableResolver.class));

    // Assert that nothing has changed
    assertEquals("", dbwHandlerConfiguration.getUserName());
  }

  /**
   * Test {@link DBWHandlerConfiguration#resolveDynamicVariables(IVariableResolver)}.
   *
   * <ul>
   *   <li>Then {@link DBWHandlerConfiguration#DBWHandlerConfiguration()} UserName is {@code
   *       janedoe}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBWHandlerConfiguration#resolveDynamicVariables(IVariableResolver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWHandlerConfiguration.resolveDynamicVariables(IVariableResolver)"})
  public void testResolveDynamicVariables_thenDBWHandlerConfigurationUserNameIsJanedoe() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();
    dbwHandlerConfiguration.setUserName("janedoe");

    // Act
    dbwHandlerConfiguration.resolveDynamicVariables(mock(IVariableResolver.class));

    // Assert that nothing has changed
    assertEquals("janedoe", dbwHandlerConfiguration.getUserName());
  }

  /**
   * Test {@link DBWHandlerConfiguration#resolveDynamicVariables(IVariableResolver)}.
   *
   * <ul>
   *   <li>Then {@link DBWHandlerConfiguration#DBWHandlerConfiguration()} UserName is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBWHandlerConfiguration#resolveDynamicVariables(IVariableResolver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWHandlerConfiguration.resolveDynamicVariables(IVariableResolver)"})
  public void testResolveDynamicVariables_thenDBWHandlerConfigurationUserNameIsNull() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();

    // Act
    dbwHandlerConfiguration.resolveDynamicVariables(mock(IVariableResolver.class));

    // Assert that nothing has changed
    assertNull(dbwHandlerConfiguration.getUserName());
  }

  /**
   * Test {@link DBWHandlerConfiguration#resolveDynamicVariables(IVariableResolver)}.
   *
   * <ul>
   *   <li>Then {@link DBWHandlerConfiguration#DBWHandlerConfiguration()} UserName is {@code
   *       ${U:U}}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBWHandlerConfiguration#resolveDynamicVariables(IVariableResolver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBWHandlerConfiguration.resolveDynamicVariables(IVariableResolver)"})
  public void testResolveDynamicVariables_thenDBWHandlerConfigurationUserNameIsUU() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();
    dbwHandlerConfiguration.setUserName("${U:U}");

    IVariableResolver variableResolver = mock(IVariableResolver.class);
    when(variableResolver.get(Mockito.<String>any())).thenThrow(new IllegalStateException());

    // Act
    dbwHandlerConfiguration.resolveDynamicVariables(variableResolver);

    // Assert that nothing has changed
    verify(variableResolver).get("U");
    assertEquals("${U:U}", dbwHandlerConfiguration.getUserName());
  }

  /**
   * Test {@link DBWHandlerConfiguration#hasValuableInfo()}.
   *
   * <ul>
   *   <li>Given {@link DBWHandlerConfiguration#DBWHandlerConfiguration()} Password is {@code
   *       iloveyou}.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#hasValuableInfo()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBWHandlerConfiguration.hasValuableInfo()"})
  public void testHasValuableInfo_givenDBWHandlerConfigurationPasswordIsIloveyou() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();
    dbwHandlerConfiguration.setPassword("iloveyou");

    // Act and Assert
    assertTrue(dbwHandlerConfiguration.hasValuableInfo());
  }

  /**
   * Test {@link DBWHandlerConfiguration#hasValuableInfo()}.
   *
   * <ul>
   *   <li>Given {@link DBWHandlerConfiguration#DBWHandlerConfiguration()} UserName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#hasValuableInfo()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBWHandlerConfiguration.hasValuableInfo()"})
  public void testHasValuableInfo_givenDBWHandlerConfigurationUserNameIsEmptyString() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();
    dbwHandlerConfiguration.setUserName("");

    // Act and Assert
    assertFalse(dbwHandlerConfiguration.hasValuableInfo());
  }

  /**
   * Test {@link DBWHandlerConfiguration#hasValuableInfo()}.
   *
   * <ul>
   *   <li>Given {@link DBWHandlerConfiguration#DBWHandlerConfiguration()} UserName is {@code
   *       janedoe}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#hasValuableInfo()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBWHandlerConfiguration.hasValuableInfo()"})
  public void testHasValuableInfo_givenDBWHandlerConfigurationUserNameIsJanedoe_thenReturnTrue() {
    // Arrange
    DBWHandlerConfiguration dbwHandlerConfiguration = new DBWHandlerConfiguration();
    dbwHandlerConfiguration.setUserName("janedoe");

    // Act and Assert
    assertTrue(dbwHandlerConfiguration.hasValuableInfo());
  }

  /**
   * Test {@link DBWHandlerConfiguration#hasValuableInfo()}.
   *
   * <ul>
   *   <li>Given {@link DBWHandlerConfiguration#DBWHandlerConfiguration()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBWHandlerConfiguration#hasValuableInfo()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBWHandlerConfiguration.hasValuableInfo()"})
  public void testHasValuableInfo_givenDBWHandlerConfiguration_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DBWHandlerConfiguration().hasValuableInfo());
  }
}
