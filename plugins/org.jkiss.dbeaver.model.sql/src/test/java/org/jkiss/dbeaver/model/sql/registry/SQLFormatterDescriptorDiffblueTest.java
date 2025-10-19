package org.jkiss.dbeaver.model.sql.registry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Paths;
import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.internal.registry.RegistryObjectManager;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.jkiss.dbeaver.model.impl.AbstractDescriptor;
import org.jkiss.dbeaver.model.impl.AbstractDescriptor.ObjectType;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLFormatterDescriptorDiffblueTest {
  /**
   * Test {@link SQLFormatterDescriptor#SQLFormatterDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return second element ImplName is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLFormatterDescriptor#SQLFormatterDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLFormatterDescriptor.<init>(IConfigurationElement)"})
  public void testNewSQLFormatterDescriptor_thenReturnSecondElementImplNameIsAttribute() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, "Master Token", "User Token");
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    when(config.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, "Master Token", "User Token");
    new RegistryObjectManager(registry2);

    // Act
    SQLFormatterDescriptor actualSqlFormatterDescriptor = new SQLFormatterDescriptor(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("objectType");
    verify(config).getContributor();
    verify(iContributor).getName();
    ObjectType[] objectTypes = actualSqlFormatterDescriptor.getObjectTypes();
    ObjectType objectType = objectTypes[0];
    assertEquals("Attribute", objectType.getImplName());
    ObjectType objectType2 = objectTypes[1];
    assertEquals("Attribute", objectType2.getImplName());
    assertEquals("Attribute", objectType.toString());
    assertEquals("Attribute", objectType2.toString());
    assertEquals("Attribute", actualSqlFormatterDescriptor.getDescription());
    assertEquals("Attribute", actualSqlFormatterDescriptor.getId());
    assertEquals("Attribute", actualSqlFormatterDescriptor.getLabel());
    assertEquals("Name", actualSqlFormatterDescriptor.getPluginId());
    assertEquals(2, objectTypes.length);
    assertTrue(actualSqlFormatterDescriptor.hasObjectTypes());
  }

  /**
   * Test {@link SQLFormatterDescriptor#SQLFormatterDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return second element ImplName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLFormatterDescriptor#SQLFormatterDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLFormatterDescriptor.<init>(IConfigurationElement)"})
  public void testNewSQLFormatterDescriptor_thenReturnSecondElementImplNameIsEmptyString() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, "Master Token", "User Token");
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    when(config.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, "Master Token", "User Token");
    new RegistryObjectManager(registry2);

    // Act
    SQLFormatterDescriptor actualSqlFormatterDescriptor = new SQLFormatterDescriptor(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("objectType");
    verify(config).getContributor();
    verify(iContributor).getName();
    ObjectType[] objectTypes = actualSqlFormatterDescriptor.getObjectTypes();
    ObjectType objectType = objectTypes[1];
    assertEquals("", objectType.getImplName());
    assertEquals("", objectType.toString());
    ObjectType objectType2 = objectTypes[0];
    assertEquals("Attribute", objectType2.getImplName());
    assertEquals("Attribute", objectType2.toString());
    assertEquals("Attribute", actualSqlFormatterDescriptor.getDescription());
    assertEquals("Attribute", actualSqlFormatterDescriptor.getId());
    assertEquals("Attribute", actualSqlFormatterDescriptor.getLabel());
    assertEquals("Name", actualSqlFormatterDescriptor.getPluginId());
    assertEquals(2, objectTypes.length);
    assertTrue(actualSqlFormatterDescriptor.hasObjectTypes());
  }

  /**
   * Test {@link SQLFormatterDescriptor#SQLFormatterDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return second element ImplName is {@code if}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLFormatterDescriptor#SQLFormatterDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLFormatterDescriptor.<init>(IConfigurationElement)"})
  public void testNewSQLFormatterDescriptor_thenReturnSecondElementImplNameIsIf() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, "Master Token", "User Token");
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("if");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    when(config.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, "Master Token", "User Token");
    new RegistryObjectManager(registry2);

    // Act
    SQLFormatterDescriptor actualSqlFormatterDescriptor = new SQLFormatterDescriptor(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("objectType");
    verify(config).getContributor();
    verify(iContributor).getName();
    ObjectType[] objectTypes = actualSqlFormatterDescriptor.getObjectTypes();
    ObjectType objectType = objectTypes[0];
    assertEquals("Attribute", objectType.getImplName());
    assertEquals("Attribute", objectType.toString());
    assertEquals("Attribute", actualSqlFormatterDescriptor.getDescription());
    assertEquals("Attribute", actualSqlFormatterDescriptor.getId());
    assertEquals("Attribute", actualSqlFormatterDescriptor.getLabel());
    assertEquals("Name", actualSqlFormatterDescriptor.getPluginId());
    ObjectType objectType2 = objectTypes[1];
    assertEquals("if", objectType2.getImplName());
    assertEquals("if", objectType2.toString());
    assertEquals(2, objectTypes.length);
    assertTrue(actualSqlFormatterDescriptor.hasObjectTypes());
  }
}
