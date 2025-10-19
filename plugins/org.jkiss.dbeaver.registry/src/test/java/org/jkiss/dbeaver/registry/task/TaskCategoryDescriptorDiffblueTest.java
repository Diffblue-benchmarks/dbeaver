package org.jkiss.dbeaver.registry.task;

import static org.junit.Assert.assertEquals;
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
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.DBPImage;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class TaskCategoryDescriptorDiffblueTest {
  /**
   * Test {@link TaskCategoryDescriptor#TaskCategoryDescriptor(TaskRegistry,
   * IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then Icon return {@link DBIcon}.
   * </ul>
   *
   * <p>Method under test: {@link TaskCategoryDescriptor#TaskCategoryDescriptor(TaskRegistry,
   * IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskCategoryDescriptor.<init>(TaskRegistry, IConfigurationElement)"})
  public void testNewTaskCategoryDescriptor_givenNull_thenIconReturnDBIcon() {
    // Arrange
    TaskRegistry registry = mock(TaskRegistry.class);
    when(registry.getTaskCategory(Mockito.<String>any())).thenReturn(null);
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new ConfigurationElementHandle(new RegistryObjectManager(registry2), 1);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry3);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    when(config.getContributor()).thenReturn(iContributor);
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry4 =
        new ExtensionRegistry(registryStrategy3, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry4);

    // Act
    TaskCategoryDescriptor actualTaskCategoryDescriptor =
        new TaskCategoryDescriptor(registry, config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("objectType");
    verify(config).getContributor();
    verify(iContributor).getName();
    verify(registry).getTaskCategory("Attribute");
    DBPImage icon = actualTaskCategoryDescriptor.getIcon();
    assertTrue(icon instanceof DBIcon);
    assertEquals("Attribute", actualTaskCategoryDescriptor.getDescription());
    assertEquals("Attribute", actualTaskCategoryDescriptor.getId());
    assertEquals("Attribute", actualTaskCategoryDescriptor.getName());
    assertEquals("Name", actualTaskCategoryDescriptor.getPluginId());
    assertEquals("platform:/plugin/Name/Attribute", icon.getLocation());
    assertNull(((DBIcon) icon).getToken());
    assertNull(actualTaskCategoryDescriptor.getParent());
    assertEquals(0, actualTaskCategoryDescriptor.getChildren().length);
    assertEquals(0, actualTaskCategoryDescriptor.getTaskTypes().length);
    assertEquals(2, actualTaskCategoryDescriptor.getObjectTypes().length);
    assertTrue(actualTaskCategoryDescriptor.hasObjectTypes());
  }
}
