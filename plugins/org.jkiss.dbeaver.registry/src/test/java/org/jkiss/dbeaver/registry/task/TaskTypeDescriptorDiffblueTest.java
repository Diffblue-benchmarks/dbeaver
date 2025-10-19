package org.jkiss.dbeaver.registry.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
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
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.impl.AbstractDescriptor;
import org.jkiss.dbeaver.model.impl.AbstractDescriptor.ObjectType;
import org.jkiss.dbeaver.model.impl.PropertyDescriptor;
import org.jkiss.dbeaver.model.preferences.DBPPropertyDescriptor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class TaskTypeDescriptorDiffblueTest {
  /**
   * Test {@link TaskTypeDescriptor#TaskTypeDescriptor(TaskCategoryDescriptor,
   * IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return first element Category is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link TaskTypeDescriptor#TaskTypeDescriptor(TaskCategoryDescriptor,
   * IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TaskTypeDescriptor.<init>(TaskCategoryDescriptor, IConfigurationElement)"
  })
  public void testNewTaskTypeDescriptor_thenReturnFirstElementCategoryIsAttribute() {
    // Arrange
    TaskCategoryDescriptor category = mock(TaskCategoryDescriptor.class);
    doNothing().when(category).addTask(Mockito.<TaskTypeDescriptor>any());
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

    ConfigurationElementHandle configurationElementHandle2 = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle2.getName()).thenReturn("propertyGroup");
    when(configurationElementHandle2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    when(configurationElementHandle2.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle2});
    when(config.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry3);

    // Act
    TaskTypeDescriptor actualTaskTypeDescriptor = new TaskTypeDescriptor(category, config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle2, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getContributor();
    verify(configurationElementHandle2).getName();
    verify(iContributor).getName();
    verify(category).addTask(isA(TaskTypeDescriptor.class));
    DBPPropertyDescriptor[] configurationProperties =
        actualTaskTypeDescriptor.getConfigurationProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = configurationProperties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    ObjectType[] objectTypes = actualTaskTypeDescriptor.getObjectTypes();
    ObjectType objectType = objectTypes[1];
    assertEquals("Attribute", objectType.getImplName());
    assertEquals("Attribute", objectType.toString());
    assertEquals("Attribute", dbpPropertyDescriptor.getCategory());
    assertEquals(1, configurationProperties.length);
    assertEquals(2, objectTypes.length);
  }

  /**
   * Test {@link TaskTypeDescriptor#TaskTypeDescriptor(TaskCategoryDescriptor,
   * IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return first element Name is empty string.
   * </ul>
   *
   * <p>Method under test: {@link TaskTypeDescriptor#TaskTypeDescriptor(TaskCategoryDescriptor,
   * IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TaskTypeDescriptor.<init>(TaskCategoryDescriptor, IConfigurationElement)"
  })
  public void testNewTaskTypeDescriptor_thenReturnFirstElementNameIsEmptyString() {
    // Arrange
    TaskCategoryDescriptor category = mock(TaskCategoryDescriptor.class);
    doNothing().when(category).addTask(Mockito.<TaskTypeDescriptor>any());
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
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("");

    ConfigurationElementHandle configurationElementHandle2 = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle2.getName()).thenReturn("Name");
    when(configurationElementHandle2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    when(configurationElementHandle2.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle2});
    when(config.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry3);

    // Act
    TaskTypeDescriptor actualTaskTypeDescriptor = new TaskTypeDescriptor(category, config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle2, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getContributor();
    verify(configurationElementHandle2).getName();
    verify(iContributor).getName();
    verify(category).addTask(isA(TaskTypeDescriptor.class));
    DBPPropertyDescriptor[] configurationProperties =
        actualTaskTypeDescriptor.getConfigurationProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = configurationProperties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("", dbpPropertyDescriptor.getDescription());
    assertEquals("", dbpPropertyDescriptor.getDisplayName());
    assertEquals("", dbpPropertyDescriptor.getHint());
    assertEquals("", dbpPropertyDescriptor.getId());
    assertNull(dbpPropertyDescriptor.getDefaultValue());
    assertNull(dbpPropertyDescriptor.getFeatures());
    assertEquals(1, configurationProperties.length);
  }
}
