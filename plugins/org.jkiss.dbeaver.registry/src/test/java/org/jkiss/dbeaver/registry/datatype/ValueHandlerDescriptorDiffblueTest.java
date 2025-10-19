package org.jkiss.dbeaver.registry.datatype;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
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
import java.util.List;
import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.internal.registry.RegistryObjectManager;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ValueHandlerDescriptorDiffblueTest {
  /**
   * Test {@link ValueHandlerDescriptor#ValueHandlerDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return SupportedDataSources first is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ValueHandlerDescriptor#ValueHandlerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ValueHandlerDescriptor.<init>(IConfigurationElement)"})
  public void testNewValueHandlerDescriptor_thenReturnSupportedDataSourcesFirstIsAttribute() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
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
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);

    // Act
    ValueHandlerDescriptor actualValueHandlerDescriptor = new ValueHandlerDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(config).getContributor();
    verify(iContributor).getName();
    List<String> supportedDataSources = actualValueHandlerDescriptor.getSupportedDataSources();
    assertEquals(1, supportedDataSources.size());
    assertEquals("Attribute", supportedDataSources.get(0));
    assertEquals("Attribute", actualValueHandlerDescriptor.getId());
    assertEquals("Attribute", actualValueHandlerDescriptor.getParentProvider());
    assertEquals("Name", actualValueHandlerDescriptor.getPluginId());
    assertNull(actualValueHandlerDescriptor.instance);
    assertEquals(1, actualValueHandlerDescriptor.getSupportedTypes().size());
    assertFalse(actualValueHandlerDescriptor.isGlobal());
  }

  /**
   * Test {@link ValueHandlerDescriptor#ValueHandlerDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return SupportedTypes Empty.
   * </ul>
   *
   * <p>Method under test: {@link
   * ValueHandlerDescriptor#ValueHandlerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ValueHandlerDescriptor.<init>(IConfigurationElement)"})
  public void testNewValueHandlerDescriptor_thenReturnSupportedTypesEmpty() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any()))
        .thenReturn(DataTypeAbstractDescriptor.ALL_TYPES_PATTERN);

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
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);

    // Act
    ValueHandlerDescriptor actualValueHandlerDescriptor = new ValueHandlerDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(config).getContributor();
    verify(iContributor).getName();
    assertEquals("Attribute", actualValueHandlerDescriptor.getId());
    assertEquals("Attribute", actualValueHandlerDescriptor.getParentProvider());
    assertEquals("Name", actualValueHandlerDescriptor.getPluginId());
    assertNull(actualValueHandlerDescriptor.instance);
    List<String> supportedDataSources = actualValueHandlerDescriptor.getSupportedDataSources();
    assertEquals(1, supportedDataSources.size());
    assertFalse(actualValueHandlerDescriptor.isGlobal());
    assertTrue(actualValueHandlerDescriptor.getSupportedTypes().isEmpty());
    assertEquals(DataTypeAbstractDescriptor.ALL_TYPES_PATTERN, supportedDataSources.get(0));
  }
}
