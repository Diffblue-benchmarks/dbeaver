package org.jkiss.dbeaver.tools.transfer.registry;

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
import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.internal.registry.RegistryObjectManager;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.tools.transfer.registry.DataTransferNodeDescriptor.NodeType;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DataTransferNodeDescriptorDiffblueTest {
  /**
   * Test {@link DataTransferNodeDescriptor#DataTransferNodeDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then Icon return {@link DBIcon}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DataTransferNodeDescriptor#DataTransferNodeDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataTransferNodeDescriptor.<init>(IConfigurationElement)"})
  public void testNewDataTransferNodeDescriptor_thenIconReturnDBIcon() {
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

    IContributor iContributor2 = mock(IContributor.class);
    when(iContributor2.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle configurationElementHandle2 = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle2.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle2.getName()).thenReturn("Name");
    when(configurationElementHandle2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});

    ConfigurationElementHandle configurationElementHandle3 = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle3.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle2});
    when(configurationElementHandle3.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle3.getContributor()).thenReturn(iContributor2);

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle3});
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, "Master Token", "User Token");
    new RegistryObjectManager(registry2);
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, "Master Token", "User Token");
    new RegistryObjectManager(registry3);
    File[] storageDirs4 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy4 =
        new RegistryStrategy(storageDirs4, new boolean[] {true, false, true, false});
    ExtensionRegistry registry4 =
        new ExtensionRegistry(registryStrategy4, "Master Token", "User Token");
    new RegistryObjectManager(registry4);

    // Act
    DataTransferNodeDescriptor actualDataTransferNodeDescriptor =
        new DataTransferNodeDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle3, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle2).getAttribute("type");
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(configurationElementHandle3, atLeast(1)).getChildren(Mockito.<String>any());
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getContributor();
    verify(configurationElementHandle3).getContributor();
    verify(configurationElementHandle2).getName();
    verify(iContributor2).getName();
    verify(iContributor).getName();
    DBPImage icon = actualDataTransferNodeDescriptor.getIcon();
    assertTrue(icon instanceof DBIcon);
    assertEquals("Attribute", actualDataTransferNodeDescriptor.getDescription());
    assertEquals("Attribute", actualDataTransferNodeDescriptor.getId());
    assertEquals("Attribute", actualDataTransferNodeDescriptor.getName());
    assertEquals("Attribute", actualDataTransferNodeDescriptor.toString());
    assertEquals("Name", actualDataTransferNodeDescriptor.getPluginId());
    assertEquals("platform:/plugin/Name/Attribute", icon.getLocation());
    assertNull(((DBIcon) icon).getToken());
    assertEquals(1, actualDataTransferNodeDescriptor.getProcessors().length);
    assertEquals(NodeType.PRODUCER, actualDataTransferNodeDescriptor.getNodeType());
    assertFalse(actualDataTransferNodeDescriptor.isAdvancedNode());
    assertTrue(actualDataTransferNodeDescriptor.hasProcessors());
  }
}
