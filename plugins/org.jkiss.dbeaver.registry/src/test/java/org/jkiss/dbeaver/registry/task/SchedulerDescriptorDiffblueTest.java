package org.jkiss.dbeaver.registry.task;

import static org.junit.Assert.assertEquals;
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
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.impl.AbstractDescriptor;
import org.jkiss.dbeaver.model.impl.AbstractDescriptor.ObjectType;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SchedulerDescriptorDiffblueTest {
  /**
   * Test {@link SchedulerDescriptor#SchedulerDescriptor(IConfigurationElement)}.
   *
   * <p>Method under test: {@link SchedulerDescriptor#SchedulerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SchedulerDescriptor.<init>(IConfigurationElement)"})
  public void testNewSchedulerDescriptor() {
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
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    when(configurationElementHandle.getChildren())
        .thenReturn(
            new IConfigurationElement[] {
              new ConfigurationElementHandle(new RegistryObjectManager(registry2), 1)
            });
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle.getName()).thenReturn("Name");
    when(configurationElementHandle.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    when(config.getContributor()).thenReturn(iContributor);
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry3);
    File[] storageDirs4 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy4 =
        new RegistryStrategy(storageDirs4, new boolean[] {true, false, true, false});
    ExtensionRegistry registry4 =
        new ExtensionRegistry(registryStrategy4, DBPEvent.RENAME, DBPEvent.RENAME);
    new ConfigurationElementHandle(new RegistryObjectManager(registry4), 1);

    // Act
    SchedulerDescriptor actualSchedulerDescriptor = new SchedulerDescriptor(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle).getChildren();
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(configurationElementHandle).getChildren("property");
    verify(config).getContributor();
    verify(configurationElementHandle).getName();
    verify(iContributor).getName();
    ObjectType[] objectTypes = actualSchedulerDescriptor.getObjectTypes();
    ObjectType objectType = objectTypes[0];
    assertEquals("Attribute", objectType.getImplName());
    assertEquals("Attribute", objectType.toString());
    assertEquals("Attribute", actualSchedulerDescriptor.getDescription());
    assertEquals("Attribute", actualSchedulerDescriptor.getName());
    assertEquals(2, objectTypes.length);
  }

  /**
   * Test {@link SchedulerDescriptor#SchedulerDescriptor(IConfigurationElement)}.
   *
   * <p>Method under test: {@link SchedulerDescriptor#SchedulerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SchedulerDescriptor.<init>(IConfigurationElement)"})
  public void testNewSchedulerDescriptor2() {
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
    when(configurationElementHandle.getChildren()).thenReturn(new IConfigurationElement[] {});
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle.getName()).thenReturn("Name");
    when(configurationElementHandle.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {});

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
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, DBPEvent.RENAME, DBPEvent.RENAME);
    new ConfigurationElementHandle(new RegistryObjectManager(registry3), 1);
    File[] storageDirs4 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy4 =
        new RegistryStrategy(storageDirs4, new boolean[] {true, false, true, false});
    ExtensionRegistry registry4 =
        new ExtensionRegistry(registryStrategy4, DBPEvent.RENAME, DBPEvent.RENAME);
    new ConfigurationElementHandle(new RegistryObjectManager(registry4), 1);

    // Act
    SchedulerDescriptor actualSchedulerDescriptor = new SchedulerDescriptor(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle).getChildren();
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(configurationElementHandle).getChildren("property");
    verify(config).getContributor();
    verify(configurationElementHandle).getName();
    verify(iContributor).getName();
    ObjectType[] objectTypes = actualSchedulerDescriptor.getObjectTypes();
    ObjectType objectType = objectTypes[0];
    assertEquals("Attribute", objectType.getImplName());
    assertEquals("Attribute", objectType.toString());
    assertEquals("Attribute", actualSchedulerDescriptor.getDescription());
    assertEquals("Attribute", actualSchedulerDescriptor.getName());
    assertEquals(2, objectTypes.length);
  }

  /**
   * Test {@link SchedulerDescriptor#SchedulerDescriptor(IConfigurationElement)}.
   *
   * <p>Method under test: {@link SchedulerDescriptor#SchedulerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SchedulerDescriptor.<init>(IConfigurationElement)"})
  public void testNewSchedulerDescriptor3() throws InvalidRegistryObjectException {
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

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getChildren())
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle.getName()).thenReturn("propertyGroup");
    when(configurationElementHandle.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {});

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
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry3);
    File[] storageDirs4 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy4 =
        new RegistryStrategy(storageDirs4, new boolean[] {true, false, true, false});
    ExtensionRegistry registry4 =
        new ExtensionRegistry(registryStrategy4, DBPEvent.RENAME, DBPEvent.RENAME);
    new ConfigurationElementHandle(new RegistryObjectManager(registry4), 1);

    // Act
    SchedulerDescriptor actualSchedulerDescriptor = new SchedulerDescriptor(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle).getChildren();
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(configurationElementHandle).getChildren("property");
    verify(config).getContributor();
    verify(configurationElementHandle).getName();
    verify(iConfigurationElement).getName();
    verify(iContributor).getName();
    ObjectType[] objectTypes = actualSchedulerDescriptor.getObjectTypes();
    ObjectType objectType = objectTypes[0];
    assertEquals("Attribute", objectType.getImplName());
    assertEquals("Attribute", objectType.toString());
    assertEquals("Attribute", actualSchedulerDescriptor.getDescription());
    assertEquals("Attribute", actualSchedulerDescriptor.getName());
    assertEquals(2, objectTypes.length);
  }

  /**
   * Test {@link SchedulerDescriptor#SchedulerDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code if}.
   *   <li>Then return first element ImplName is {@code if}.
   * </ul>
   *
   * <p>Method under test: {@link SchedulerDescriptor#SchedulerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SchedulerDescriptor.<init>(IConfigurationElement)"})
  public void testNewSchedulerDescriptor_givenIf_thenReturnFirstElementImplNameIsIf()
      throws InvalidRegistryObjectException {
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

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getChildren())
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle.getName()).thenReturn("Name");
    when(configurationElementHandle.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("if");
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
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry3);
    File[] storageDirs4 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy4 =
        new RegistryStrategy(storageDirs4, new boolean[] {true, false, true, false});
    ExtensionRegistry registry4 =
        new ExtensionRegistry(registryStrategy4, DBPEvent.RENAME, DBPEvent.RENAME);
    new ConfigurationElementHandle(new RegistryObjectManager(registry4), 1);

    // Act
    SchedulerDescriptor actualSchedulerDescriptor = new SchedulerDescriptor(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle).getChildren();
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(configurationElementHandle).getChildren("property");
    verify(config).getContributor();
    verify(configurationElementHandle).getName();
    verify(iConfigurationElement).getName();
    verify(iContributor).getName();
    ObjectType[] objectTypes = actualSchedulerDescriptor.getObjectTypes();
    ObjectType objectType = objectTypes[0];
    assertEquals("if", objectType.getImplName());
    assertEquals("if", objectType.toString());
    assertEquals("if", actualSchedulerDescriptor.getDescription());
    assertEquals("if", actualSchedulerDescriptor.getName());
    assertEquals(2, objectTypes.length);
  }

  /**
   * Test {@link SchedulerDescriptor#SchedulerDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then calls {@link IConfigurationElement#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link SchedulerDescriptor#SchedulerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SchedulerDescriptor.<init>(IConfigurationElement)"})
  public void testNewSchedulerDescriptor_thenCallsGetName() throws InvalidRegistryObjectException {
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

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getChildren())
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle.getName()).thenReturn("Name");
    when(configurationElementHandle.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {});

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
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry3);
    File[] storageDirs4 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy4 =
        new RegistryStrategy(storageDirs4, new boolean[] {true, false, true, false});
    ExtensionRegistry registry4 =
        new ExtensionRegistry(registryStrategy4, DBPEvent.RENAME, DBPEvent.RENAME);
    new ConfigurationElementHandle(new RegistryObjectManager(registry4), 1);

    // Act
    SchedulerDescriptor actualSchedulerDescriptor = new SchedulerDescriptor(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle).getChildren();
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(configurationElementHandle).getChildren("property");
    verify(config).getContributor();
    verify(configurationElementHandle).getName();
    verify(iConfigurationElement).getName();
    verify(iContributor).getName();
    ObjectType[] objectTypes = actualSchedulerDescriptor.getObjectTypes();
    ObjectType objectType = objectTypes[0];
    assertEquals("Attribute", objectType.getImplName());
    assertEquals("Attribute", objectType.toString());
    assertEquals("Attribute", actualSchedulerDescriptor.getDescription());
    assertEquals("Attribute", actualSchedulerDescriptor.getName());
    assertEquals(2, objectTypes.length);
  }

  /**
   * Test {@link SchedulerDescriptor#SchedulerDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link SchedulerDescriptor#SchedulerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SchedulerDescriptor.<init>(IConfigurationElement)"})
  public void testNewSchedulerDescriptor_thenReturnArrayLengthIsOne() {
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

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any())).thenReturn(new IConfigurationElement[] {});
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
    File[] storageDirs4 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy4 =
        new RegistryStrategy(storageDirs4, new boolean[] {true, false, true, false});
    ExtensionRegistry registry4 =
        new ExtensionRegistry(registryStrategy4, DBPEvent.RENAME, DBPEvent.RENAME);
    new ConfigurationElementHandle(new RegistryObjectManager(registry4), 1);

    // Act
    SchedulerDescriptor actualSchedulerDescriptor = new SchedulerDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(config).getContributor();
    verify(iContributor).getName();
    ObjectType[] objectTypes = actualSchedulerDescriptor.getObjectTypes();
    ObjectType objectType = objectTypes[0];
    assertEquals("Attribute", objectType.getImplName());
    assertEquals("Attribute", objectType.toString());
    assertEquals("Attribute", actualSchedulerDescriptor.getDescription());
    assertEquals("Attribute", actualSchedulerDescriptor.getName());
    assertEquals(1, objectTypes.length);
  }

  /**
   * Test {@link SchedulerDescriptor#SchedulerDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return first element ImplName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link SchedulerDescriptor#SchedulerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SchedulerDescriptor.<init>(IConfigurationElement)"})
  public void testNewSchedulerDescriptor_thenReturnFirstElementImplNameIsEmptyString()
      throws InvalidRegistryObjectException {
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

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getChildren())
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle.getName()).thenReturn("Name");
    when(configurationElementHandle.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("");
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
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry3);
    File[] storageDirs4 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy4 =
        new RegistryStrategy(storageDirs4, new boolean[] {true, false, true, false});
    ExtensionRegistry registry4 =
        new ExtensionRegistry(registryStrategy4, DBPEvent.RENAME, DBPEvent.RENAME);
    new ConfigurationElementHandle(new RegistryObjectManager(registry4), 1);

    // Act
    SchedulerDescriptor actualSchedulerDescriptor = new SchedulerDescriptor(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle).getChildren();
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(configurationElementHandle).getChildren("property");
    verify(config).getContributor();
    verify(configurationElementHandle).getName();
    verify(iConfigurationElement).getName();
    verify(iContributor).getName();
    ObjectType[] objectTypes = actualSchedulerDescriptor.getObjectTypes();
    ObjectType objectType = objectTypes[0];
    assertEquals("", objectType.getImplName());
    assertEquals("", objectType.toString());
    assertEquals("", actualSchedulerDescriptor.getDescription());
    assertEquals("", actualSchedulerDescriptor.getName());
    assertEquals(2, objectTypes.length);
  }
}
