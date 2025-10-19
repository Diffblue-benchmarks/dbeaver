package org.jkiss.dbeaver.model.dashboard.registry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
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
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.impl.AbstractDescriptor;
import org.jkiss.dbeaver.model.impl.AbstractDescriptor.ObjectType;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DashboardProviderDescriptorDiffblueTest {
  /**
   * Test {@link DashboardProviderDescriptor#DashboardProviderDescriptor(IConfigurationElement)}.
   *
   * <p>Method under test: {@link
   * DashboardProviderDescriptor#DashboardProviderDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DashboardProviderDescriptor.<init>(IConfigurationElement)"})
  public void testNewDashboardProviderDescriptor() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, "Master Token", "User Token");
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenThrow(new IllegalStateException());

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getContributor()).thenReturn(iContributor);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> new DashboardProviderDescriptor(config));
    verify(config).getContributor();
    verify(iContributor).getName();
  }

  /**
   * Test {@link DashboardProviderDescriptor#DashboardProviderDescriptor(IConfigurationElement)}.
   *
   * <p>Method under test: {@link
   * DashboardProviderDescriptor#DashboardProviderDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DashboardProviderDescriptor.<init>(IConfigurationElement)"})
  public void testNewDashboardProviderDescriptor2() {
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
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, "Master Token", "User Token");
    when(configurationElementHandle.getChildren())
        .thenReturn(
            new IConfigurationElement[] {
              new ConfigurationElementHandle(new RegistryObjectManager(registry2), 1)
            });

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
        new ExtensionRegistry(registryStrategy3, "Master Token", "User Token");
    new RegistryObjectManager(registry3);

    // Act
    DashboardProviderDescriptor actualDashboardProviderDescriptor =
        new DashboardProviderDescriptor(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle).getChildren();
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(config).getContributor();
    verify(iContributor).getName();
    DBPImage icon = actualDashboardProviderDescriptor.getIcon();
    assertTrue(icon instanceof DBIcon);
    assertEquals("Attribute", actualDashboardProviderDescriptor.getDefaultRenderer());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getDescription());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getId());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getLabel());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getName());
    ObjectType implType = actualDashboardProviderDescriptor.getImplType();
    assertEquals("Attribute", implType.getImplName());
    assertEquals("Attribute", implType.toString());
    assertEquals("platform:/plugin/Name/Attribute", icon.getLocation());
  }

  /**
   * Test {@link DashboardProviderDescriptor#DashboardProviderDescriptor(IConfigurationElement)}.
   *
   * <p>Method under test: {@link
   * DashboardProviderDescriptor#DashboardProviderDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DashboardProviderDescriptor.<init>(IConfigurationElement)"})
  public void testNewDashboardProviderDescriptor3() {
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
    when(configurationElementHandle.getChildren()).thenReturn(new IConfigurationElement[] {});

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
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, "Master Token", "User Token");
    new ConfigurationElementHandle(new RegistryObjectManager(registry3), 1);

    // Act
    DashboardProviderDescriptor actualDashboardProviderDescriptor =
        new DashboardProviderDescriptor(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle).getChildren();
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(config).getContributor();
    verify(iContributor).getName();
    DBPImage icon = actualDashboardProviderDescriptor.getIcon();
    assertTrue(icon instanceof DBIcon);
    assertEquals("Attribute", actualDashboardProviderDescriptor.getDefaultRenderer());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getDescription());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getId());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getLabel());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getName());
    ObjectType implType = actualDashboardProviderDescriptor.getImplType();
    assertEquals("Attribute", implType.getImplName());
    assertEquals("Attribute", implType.toString());
    assertEquals("platform:/plugin/Name/Attribute", icon.getLocation());
  }

  /**
   * Test {@link DashboardProviderDescriptor#DashboardProviderDescriptor(IConfigurationElement)}.
   *
   * <p>Method under test: {@link
   * DashboardProviderDescriptor#DashboardProviderDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DashboardProviderDescriptor.<init>(IConfigurationElement)"})
  public void testNewDashboardProviderDescriptor4() throws InvalidRegistryObjectException {
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

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("if");
    when(configurationElementHandle.getChildren())
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});

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
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, "Master Token", "User Token");
    new RegistryObjectManager(registry3);

    // Act
    DashboardProviderDescriptor actualDashboardProviderDescriptor =
        new DashboardProviderDescriptor(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle).getChildren();
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(config).getContributor();
    verify(iConfigurationElement).getName();
    verify(iContributor).getName();
    DBPImage icon = actualDashboardProviderDescriptor.getIcon();
    assertTrue(icon instanceof DBIcon);
    assertEquals("Attribute", actualDashboardProviderDescriptor.getDefaultRenderer());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getDescription());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getId());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getLabel());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getName());
    ObjectType implType = actualDashboardProviderDescriptor.getImplType();
    assertEquals("Attribute", implType.getImplName());
    assertEquals("Attribute", implType.toString());
    assertEquals("platform:/plugin/Name/Attribute", icon.getLocation());
  }

  /**
   * Test {@link DashboardProviderDescriptor#DashboardProviderDescriptor(IConfigurationElement)}.
   *
   * <p>Method under test: {@link
   * DashboardProviderDescriptor#DashboardProviderDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DashboardProviderDescriptor.<init>(IConfigurationElement)"})
  public void testNewDashboardProviderDescriptor5() throws InvalidRegistryObjectException {
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

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("");
    when(configurationElementHandle.getChildren())
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});

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
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, "Master Token", "User Token");
    new RegistryObjectManager(registry3);

    // Act
    DashboardProviderDescriptor actualDashboardProviderDescriptor =
        new DashboardProviderDescriptor(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle).getChildren();
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(config).getContributor();
    verify(iConfigurationElement).getName();
    verify(iContributor).getName();
    DBPImage icon = actualDashboardProviderDescriptor.getIcon();
    assertTrue(icon instanceof DBIcon);
    assertEquals("Attribute", actualDashboardProviderDescriptor.getDefaultRenderer());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getDescription());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getId());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getLabel());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getName());
    ObjectType implType = actualDashboardProviderDescriptor.getImplType();
    assertEquals("Attribute", implType.getImplName());
    assertEquals("Attribute", implType.toString());
    assertEquals("platform:/plugin/Name/Attribute", icon.getLocation());
  }

  /**
   * Test {@link DashboardProviderDescriptor#DashboardProviderDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@link IllegalStateException#IllegalStateException()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DashboardProviderDescriptor#DashboardProviderDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DashboardProviderDescriptor.<init>(IConfigurationElement)"})
  public void testNewDashboardProviderDescriptor_givenIllegalStateException() {
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

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenThrow(new IllegalStateException());
    when(config.getContributor()).thenReturn(iContributor);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> new DashboardProviderDescriptor(config));
    verify(config).getAttribute("objectType");
    verify(config).getContributor();
    verify(iContributor).getName();
  }

  /**
   * Test {@link DashboardProviderDescriptor#DashboardProviderDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return DefaultRenderer is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DashboardProviderDescriptor#DashboardProviderDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DashboardProviderDescriptor.<init>(IConfigurationElement)"})
  public void testNewDashboardProviderDescriptor_thenReturnDefaultRendererIsAttribute()
      throws InvalidRegistryObjectException {
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

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle.getChildren())
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});

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
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, "Master Token", "User Token");
    new RegistryObjectManager(registry3);

    // Act
    DashboardProviderDescriptor actualDashboardProviderDescriptor =
        new DashboardProviderDescriptor(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle).getChildren();
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(config).getContributor();
    verify(iConfigurationElement).getName();
    verify(iContributor).getName();
    DBPImage icon = actualDashboardProviderDescriptor.getIcon();
    assertTrue(icon instanceof DBIcon);
    assertEquals("Attribute", actualDashboardProviderDescriptor.getDefaultRenderer());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getDescription());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getId());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getLabel());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getName());
    ObjectType implType = actualDashboardProviderDescriptor.getImplType();
    assertEquals("Attribute", implType.getImplName());
    assertEquals("Attribute", implType.toString());
    assertEquals("platform:/plugin/Name/Attribute", icon.getLocation());
  }

  /**
   * Test {@link DashboardProviderDescriptor#DashboardProviderDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return DefaultRenderer is empty string.
   * </ul>
   *
   * <p>Method under test: {@link
   * DashboardProviderDescriptor#DashboardProviderDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DashboardProviderDescriptor.<init>(IConfigurationElement)"})
  public void testNewDashboardProviderDescriptor_thenReturnDefaultRendererIsEmptyString()
      throws InvalidRegistryObjectException {
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

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle.getChildren())
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});

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
        new ExtensionRegistry(registryStrategy2, "Master Token", "User Token");
    new RegistryObjectManager(registry2);
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, "Master Token", "User Token");
    new RegistryObjectManager(registry3);

    // Act
    DashboardProviderDescriptor actualDashboardProviderDescriptor =
        new DashboardProviderDescriptor(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle).getChildren();
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(config).getContributor();
    verify(iConfigurationElement).getName();
    verify(iContributor).getName();
    assertEquals("", actualDashboardProviderDescriptor.getDefaultRenderer());
    assertEquals("", actualDashboardProviderDescriptor.getDescription());
    assertEquals("", actualDashboardProviderDescriptor.getId());
    assertEquals("", actualDashboardProviderDescriptor.getLabel());
    assertEquals("", actualDashboardProviderDescriptor.getName());
    ObjectType implType = actualDashboardProviderDescriptor.getImplType();
    assertEquals("", implType.getImplName());
    ObjectType[] objectTypes = actualDashboardProviderDescriptor.getObjectTypes();
    ObjectType objectType = objectTypes[0];
    assertEquals("", objectType.getImplName());
    assertEquals("", implType.toString());
    assertEquals("", objectType.toString());
    assertEquals(2, objectTypes.length);
  }

  /**
   * Test {@link DashboardProviderDescriptor#DashboardProviderDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return DefaultRenderer is {@code #}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DashboardProviderDescriptor#DashboardProviderDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DashboardProviderDescriptor.<init>(IConfigurationElement)"})
  public void testNewDashboardProviderDescriptor_thenReturnDefaultRendererIsNumberSign()
      throws InvalidRegistryObjectException {
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

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle.getChildren())
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("#");
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
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, "Master Token", "User Token");
    new RegistryObjectManager(registry3);

    // Act
    DashboardProviderDescriptor actualDashboardProviderDescriptor =
        new DashboardProviderDescriptor(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle).getChildren();
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(config).getContributor();
    verify(iConfigurationElement).getName();
    verify(iContributor).getName();
    assertEquals("#", actualDashboardProviderDescriptor.getDefaultRenderer());
    assertEquals("#", actualDashboardProviderDescriptor.getDescription());
    assertEquals("#", actualDashboardProviderDescriptor.getId());
    assertEquals("#", actualDashboardProviderDescriptor.getLabel());
    assertEquals("#", actualDashboardProviderDescriptor.getName());
    ObjectType implType = actualDashboardProviderDescriptor.getImplType();
    assertEquals("#", implType.getImplName());
    ObjectType[] objectTypes = actualDashboardProviderDescriptor.getObjectTypes();
    ObjectType objectType = objectTypes[0];
    assertEquals("#", objectType.getImplName());
    assertEquals("#", implType.toString());
    assertEquals("#", objectType.toString());
    assertEquals(2, objectTypes.length);
  }

  /**
   * Test {@link DashboardProviderDescriptor#DashboardProviderDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return first element ImplName is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DashboardProviderDescriptor#DashboardProviderDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DashboardProviderDescriptor.<init>(IConfigurationElement)"})
  public void testNewDashboardProviderDescriptor_thenReturnFirstElementImplNameIsAttribute() {
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

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any())).thenReturn(new IConfigurationElement[] {});
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

    // Act
    DashboardProviderDescriptor actualDashboardProviderDescriptor =
        new DashboardProviderDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(config).getContributor();
    verify(iContributor).getName();
    DBPImage icon = actualDashboardProviderDescriptor.getIcon();
    assertTrue(icon instanceof DBIcon);
    assertEquals("Attribute", actualDashboardProviderDescriptor.getDefaultRenderer());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getDescription());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getId());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getLabel());
    assertEquals("Attribute", actualDashboardProviderDescriptor.getName());
    ObjectType implType = actualDashboardProviderDescriptor.getImplType();
    assertEquals("Attribute", implType.getImplName());
    ObjectType[] objectTypes = actualDashboardProviderDescriptor.getObjectTypes();
    ObjectType objectType = objectTypes[0];
    assertEquals("Attribute", objectType.getImplName());
    assertEquals("Attribute", implType.toString());
    assertEquals("Attribute", objectType.toString());
    assertEquals("platform:/plugin/Name/Attribute", icon.getLocation());
    assertEquals(1, objectTypes.length);
  }

  /**
   * Test {@link DashboardProviderDescriptor#DashboardProviderDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return Icon Location is {@code platform:}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DashboardProviderDescriptor#DashboardProviderDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DashboardProviderDescriptor.<init>(IConfigurationElement)"})
  public void testNewDashboardProviderDescriptor_thenReturnIconLocationIsPlatform()
      throws InvalidRegistryObjectException {
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

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle.getChildren())
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("platform:");
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
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, "Master Token", "User Token");
    new RegistryObjectManager(registry3);

    // Act
    DashboardProviderDescriptor actualDashboardProviderDescriptor =
        new DashboardProviderDescriptor(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle).getChildren();
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(config).getContributor();
    verify(iConfigurationElement).getName();
    verify(iContributor).getName();
    DBPImage icon = actualDashboardProviderDescriptor.getIcon();
    assertTrue(icon instanceof DBIcon);
    assertEquals("platform:", icon.getLocation());
    assertEquals("platform:", actualDashboardProviderDescriptor.getDefaultRenderer());
    assertEquals("platform:", actualDashboardProviderDescriptor.getDescription());
    assertEquals("platform:", actualDashboardProviderDescriptor.getId());
    assertEquals("platform:", actualDashboardProviderDescriptor.getLabel());
    assertEquals("platform:", actualDashboardProviderDescriptor.getName());
    ObjectType implType = actualDashboardProviderDescriptor.getImplType();
    assertEquals("platform:", implType.getImplName());
    ObjectType[] objectTypes = actualDashboardProviderDescriptor.getObjectTypes();
    ObjectType objectType = objectTypes[0];
    assertEquals("platform:", objectType.getImplName());
    assertEquals("platform:", implType.toString());
    assertEquals("platform:", objectType.toString());
    assertEquals(2, objectTypes.length);
  }
}
