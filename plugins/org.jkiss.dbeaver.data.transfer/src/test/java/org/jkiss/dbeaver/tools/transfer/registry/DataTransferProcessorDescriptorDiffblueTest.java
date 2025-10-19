package org.jkiss.dbeaver.tools.transfer.registry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.impl.PropertyDescriptor;
import org.jkiss.dbeaver.model.preferences.DBPPropertyDescriptor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DataTransferProcessorDescriptorDiffblueTest {
  /**
   * Test {@link
   * DataTransferProcessorDescriptor#DataTransferProcessorDescriptor(DataTransferNodeDescriptor,
   * IConfigurationElement)}.
   *
   * <p>Method under test: {@link
   * DataTransferProcessorDescriptor#DataTransferProcessorDescriptor(DataTransferNodeDescriptor,
   * IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTransferProcessorDescriptor.<init>(DataTransferNodeDescriptor, IConfigurationElement)"
  })
  public void testNewDataTransferProcessorDescriptor() {
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
    assertThrows(
        IllegalStateException.class, () -> new DataTransferProcessorDescriptor(null, config));
    verify(config).getContributor();
    verify(iContributor).getName();
  }

  /**
   * Test {@link
   * DataTransferProcessorDescriptor#DataTransferProcessorDescriptor(DataTransferNodeDescriptor,
   * IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then return AppFileExtension is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DataTransferProcessorDescriptor#DataTransferProcessorDescriptor(DataTransferNodeDescriptor,
   * IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTransferProcessorDescriptor.<init>(DataTransferNodeDescriptor, IConfigurationElement)"
  })
  public void testNewDataTransferProcessorDescriptor_given42_thenReturnAppFileExtensionIs42() {
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

    ConfigurationElementHandle configurationElementHandle2 = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle2.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle2.getName()).thenReturn("Name");
    when(configurationElementHandle2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("42");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle2});
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
    DataTransferProcessorDescriptor actualDataTransferProcessorDescriptor =
        new DataTransferProcessorDescriptor(null, config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle2).getAttribute("type");
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getContributor();
    verify(configurationElementHandle2).getName();
    verify(iContributor).getName();
    DBPImage icon = actualDataTransferProcessorDescriptor.getIcon();
    assertTrue(icon instanceof DBIcon);
    assertEquals("42", actualDataTransferProcessorDescriptor.getAppFileExtension());
    assertEquals("42", actualDataTransferProcessorDescriptor.getAppName());
    assertEquals("42", actualDataTransferProcessorDescriptor.getContentType());
    assertEquals("42", actualDataTransferProcessorDescriptor.getDescription());
    assertEquals("42", actualDataTransferProcessorDescriptor.getId());
    assertEquals("42", actualDataTransferProcessorDescriptor.getName());
    assertEquals("42", actualDataTransferProcessorDescriptor.getProcessorFileExtension());
    assertEquals("platform:/plugin/Name/42", icon.getLocation());
    assertEquals(42, actualDataTransferProcessorDescriptor.getOrder());
  }

  /**
   * Test {@link
   * DataTransferProcessorDescriptor#DataTransferProcessorDescriptor(DataTransferNodeDescriptor,
   * IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@link IllegalStateException#IllegalStateException()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DataTransferProcessorDescriptor#DataTransferProcessorDescriptor(DataTransferNodeDescriptor,
   * IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTransferProcessorDescriptor.<init>(DataTransferNodeDescriptor, IConfigurationElement)"
  })
  public void testNewDataTransferProcessorDescriptor_givenIllegalStateException() {
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
    assertThrows(
        IllegalStateException.class, () -> new DataTransferProcessorDescriptor(null, config));
    verify(config).getAttribute("id");
    verify(config).getContributor();
    verify(iContributor).getName();
  }

  /**
   * Test {@link
   * DataTransferProcessorDescriptor#DataTransferProcessorDescriptor(DataTransferNodeDescriptor,
   * IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then first element return {@link PropertyDescriptor}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DataTransferProcessorDescriptor#DataTransferProcessorDescriptor(DataTransferNodeDescriptor,
   * IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTransferProcessorDescriptor.<init>(DataTransferNodeDescriptor, IConfigurationElement)"
  })
  public void testNewDataTransferProcessorDescriptor_thenFirstElementReturnPropertyDescriptor() {
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

    ConfigurationElementHandle configurationElementHandle2 = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle2.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle2.getName()).thenReturn("Name");
    when(configurationElementHandle2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});

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
    DataTransferProcessorDescriptor actualDataTransferProcessorDescriptor =
        new DataTransferProcessorDescriptor(null, config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle2).getAttribute("type");
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getContributor();
    verify(configurationElementHandle2).getName();
    verify(iContributor).getName();
    DBPPropertyDescriptor[] properties = actualDataTransferProcessorDescriptor.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("", dbpPropertyDescriptor.getDescription());
    assertEquals("", dbpPropertyDescriptor.getDisplayName());
    assertEquals("", dbpPropertyDescriptor.getHint());
    assertEquals("", dbpPropertyDescriptor.getId());
    assertNull(dbpPropertyDescriptor.getDefaultValue());
    assertNull(dbpPropertyDescriptor.getFeatures());
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link
   * DataTransferProcessorDescriptor#DataTransferProcessorDescriptor(DataTransferNodeDescriptor,
   * IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return AppFileExtension is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DataTransferProcessorDescriptor#DataTransferProcessorDescriptor(DataTransferNodeDescriptor,
   * IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTransferProcessorDescriptor.<init>(DataTransferNodeDescriptor, IConfigurationElement)"
  })
  public void testNewDataTransferProcessorDescriptor_thenReturnAppFileExtensionIsAttribute() {
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

    ConfigurationElementHandle configurationElementHandle2 = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle2.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle2.getName()).thenReturn("Name");
    when(configurationElementHandle2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});

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
    DataTransferProcessorDescriptor actualDataTransferProcessorDescriptor =
        new DataTransferProcessorDescriptor(null, config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle2).getAttribute("type");
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getContributor();
    verify(configurationElementHandle2).getName();
    verify(iContributor).getName();
    DBPImage icon = actualDataTransferProcessorDescriptor.getIcon();
    assertTrue(icon instanceof DBIcon);
    assertEquals("Attribute", actualDataTransferProcessorDescriptor.getAppFileExtension());
    assertEquals("Attribute", actualDataTransferProcessorDescriptor.getAppName());
    assertEquals("Attribute", actualDataTransferProcessorDescriptor.getContentType());
    assertEquals("Attribute", actualDataTransferProcessorDescriptor.getDescription());
    assertEquals("Attribute", actualDataTransferProcessorDescriptor.getId());
    assertEquals("Attribute", actualDataTransferProcessorDescriptor.getName());
    assertEquals("Attribute", actualDataTransferProcessorDescriptor.getProcessorFileExtension());
    assertEquals("platform:/plugin/Name/Attribute", icon.getLocation());
  }

  /**
   * Test {@link
   * DataTransferProcessorDescriptor#DataTransferProcessorDescriptor(DataTransferNodeDescriptor,
   * IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return AppFileExtension is empty string.
   * </ul>
   *
   * <p>Method under test: {@link
   * DataTransferProcessorDescriptor#DataTransferProcessorDescriptor(DataTransferNodeDescriptor,
   * IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTransferProcessorDescriptor.<init>(DataTransferNodeDescriptor, IConfigurationElement)"
  })
  public void testNewDataTransferProcessorDescriptor_thenReturnAppFileExtensionIsEmptyString() {
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

    ConfigurationElementHandle configurationElementHandle2 = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle2.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle2.getName()).thenReturn("Name");
    when(configurationElementHandle2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle2});
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
    DataTransferProcessorDescriptor actualDataTransferProcessorDescriptor =
        new DataTransferProcessorDescriptor(null, config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle2).getAttribute("type");
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getContributor();
    verify(configurationElementHandle2).getName();
    verify(iContributor).getName();
    assertEquals("", actualDataTransferProcessorDescriptor.getAppFileExtension());
    assertEquals("", actualDataTransferProcessorDescriptor.getAppName());
    assertEquals("", actualDataTransferProcessorDescriptor.getContentType());
    assertEquals("", actualDataTransferProcessorDescriptor.getDescription());
    assertEquals("", actualDataTransferProcessorDescriptor.getId());
    assertEquals("", actualDataTransferProcessorDescriptor.getName());
    assertEquals("data", actualDataTransferProcessorDescriptor.getProcessorFileExtension());
  }

  /**
   * Test {@link
   * DataTransferProcessorDescriptor#DataTransferProcessorDescriptor(DataTransferNodeDescriptor,
   * IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return AppFileExtension is {@code #}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DataTransferProcessorDescriptor#DataTransferProcessorDescriptor(DataTransferNodeDescriptor,
   * IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTransferProcessorDescriptor.<init>(DataTransferNodeDescriptor, IConfigurationElement)"
  })
  public void testNewDataTransferProcessorDescriptor_thenReturnAppFileExtensionIsNumberSign() {
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

    ConfigurationElementHandle configurationElementHandle2 = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle2.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle2.getName()).thenReturn("Name");
    when(configurationElementHandle2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("#");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle2});
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
    DataTransferProcessorDescriptor actualDataTransferProcessorDescriptor =
        new DataTransferProcessorDescriptor(null, config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle2).getAttribute("type");
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getContributor();
    verify(configurationElementHandle2).getName();
    verify(iContributor).getName();
    assertEquals("#", actualDataTransferProcessorDescriptor.getAppFileExtension());
    assertEquals("#", actualDataTransferProcessorDescriptor.getAppName());
    assertEquals("#", actualDataTransferProcessorDescriptor.getContentType());
    assertEquals("#", actualDataTransferProcessorDescriptor.getDescription());
    assertEquals("#", actualDataTransferProcessorDescriptor.getId());
    assertEquals("#", actualDataTransferProcessorDescriptor.getName());
    assertEquals("#", actualDataTransferProcessorDescriptor.getProcessorFileExtension());
  }

  /**
   * Test {@link
   * DataTransferProcessorDescriptor#DataTransferProcessorDescriptor(DataTransferNodeDescriptor,
   * IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return Icon Location is {@code platform:}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DataTransferProcessorDescriptor#DataTransferProcessorDescriptor(DataTransferNodeDescriptor,
   * IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTransferProcessorDescriptor.<init>(DataTransferNodeDescriptor, IConfigurationElement)"
  })
  public void testNewDataTransferProcessorDescriptor_thenReturnIconLocationIsPlatform() {
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

    ConfigurationElementHandle configurationElementHandle2 = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle2.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle2.getName()).thenReturn("Name");
    when(configurationElementHandle2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("platform:");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle2});
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
    DataTransferProcessorDescriptor actualDataTransferProcessorDescriptor =
        new DataTransferProcessorDescriptor(null, config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle2).getAttribute("type");
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getContributor();
    verify(configurationElementHandle2).getName();
    verify(iContributor).getName();
    DBPImage icon = actualDataTransferProcessorDescriptor.getIcon();
    assertTrue(icon instanceof DBIcon);
    assertEquals("platform:", icon.getLocation());
    assertEquals("platform:", actualDataTransferProcessorDescriptor.getAppFileExtension());
    assertEquals("platform:", actualDataTransferProcessorDescriptor.getAppName());
    assertEquals("platform:", actualDataTransferProcessorDescriptor.getContentType());
    assertEquals("platform:", actualDataTransferProcessorDescriptor.getDescription());
    assertEquals("platform:", actualDataTransferProcessorDescriptor.getId());
    assertEquals("platform:", actualDataTransferProcessorDescriptor.getName());
    assertEquals("platform:", actualDataTransferProcessorDescriptor.getProcessorFileExtension());
  }
}
