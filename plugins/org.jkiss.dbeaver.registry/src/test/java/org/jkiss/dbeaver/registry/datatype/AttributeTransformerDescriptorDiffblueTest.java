package org.jkiss.dbeaver.registry.datatype;

import static org.junit.Assert.assertArrayEquals;
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
import java.util.List;
import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.internal.registry.RegistryObjectManager;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.impl.PropertyDescriptor;
import org.jkiss.dbeaver.model.preferences.DBPPropertyDescriptor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class AttributeTransformerDescriptorDiffblueTest {
  /**
   * Test {@link
   * AttributeTransformerDescriptor#AttributeTransformerDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return Description is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AttributeTransformerDescriptor#AttributeTransformerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AttributeTransformerDescriptor.<init>(IConfigurationElement)"})
  public void testNewAttributeTransformerDescriptor_thenReturnDescriptionIsAttribute() {
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
    AttributeTransformerDescriptor actualAttributeTransformerDescriptor =
        new AttributeTransformerDescriptor(config);

    // Assert
    verify(configurationElementHandle2, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getContributor();
    verify(configurationElementHandle2).getName();
    verify(iContributor).getName();
    DBPImage icon = actualAttributeTransformerDescriptor.getIcon();
    assertTrue(icon instanceof DBIcon);
    assertEquals("Attribute", actualAttributeTransformerDescriptor.getDescription());
    assertEquals("Attribute", actualAttributeTransformerDescriptor.getName());
    assertEquals("Attribute", actualAttributeTransformerDescriptor.getId());
    assertEquals("platform:/plugin/Name/Attribute", icon.getLocation());
  }

  /**
   * Test {@link
   * AttributeTransformerDescriptor#AttributeTransformerDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return Description is empty string.
   * </ul>
   *
   * <p>Method under test: {@link
   * AttributeTransformerDescriptor#AttributeTransformerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AttributeTransformerDescriptor.<init>(IConfigurationElement)"})
  public void testNewAttributeTransformerDescriptor_thenReturnDescriptionIsEmptyString() {
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
    AttributeTransformerDescriptor actualAttributeTransformerDescriptor =
        new AttributeTransformerDescriptor(config);

    // Assert
    verify(configurationElementHandle2, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getContributor();
    verify(configurationElementHandle2).getName();
    verify(iContributor).getName();
    assertEquals("", actualAttributeTransformerDescriptor.getDescription());
    assertEquals("", actualAttributeTransformerDescriptor.getName());
    assertEquals("", actualAttributeTransformerDescriptor.getId());
    assertNull(actualAttributeTransformerDescriptor.getIcon());
  }

  /**
   * Test {@link
   * AttributeTransformerDescriptor#AttributeTransformerDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return Description is {@code #}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AttributeTransformerDescriptor#AttributeTransformerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AttributeTransformerDescriptor.<init>(IConfigurationElement)"})
  public void testNewAttributeTransformerDescriptor_thenReturnDescriptionIsNumberSign() {
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
    AttributeTransformerDescriptor actualAttributeTransformerDescriptor =
        new AttributeTransformerDescriptor(config);

    // Assert
    verify(configurationElementHandle2, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getContributor();
    verify(configurationElementHandle2).getName();
    verify(iContributor).getName();
    assertEquals("#", actualAttributeTransformerDescriptor.getDescription());
    assertEquals("#", actualAttributeTransformerDescriptor.getName());
    assertEquals("#", actualAttributeTransformerDescriptor.getId());
    assertNull(actualAttributeTransformerDescriptor.getIcon());
  }

  /**
   * Test {@link
   * AttributeTransformerDescriptor#AttributeTransformerDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return Icon Location is {@code platform:}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AttributeTransformerDescriptor#AttributeTransformerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AttributeTransformerDescriptor.<init>(IConfigurationElement)"})
  public void testNewAttributeTransformerDescriptor_thenReturnIconLocationIsPlatform() {
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
    AttributeTransformerDescriptor actualAttributeTransformerDescriptor =
        new AttributeTransformerDescriptor(config);

    // Assert
    verify(configurationElementHandle2, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getContributor();
    verify(configurationElementHandle2).getName();
    verify(iContributor).getName();
    DBPImage icon = actualAttributeTransformerDescriptor.getIcon();
    assertTrue(icon instanceof DBIcon);
    assertEquals("platform:", icon.getLocation());
    assertEquals("platform:", actualAttributeTransformerDescriptor.getDescription());
    assertEquals("platform:", actualAttributeTransformerDescriptor.getName());
    assertEquals("platform:", actualAttributeTransformerDescriptor.getId());
  }

  /**
   * Test {@link
   * AttributeTransformerDescriptor#AttributeTransformerDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return Properties first Name is empty string.
   * </ul>
   *
   * <p>Method under test: {@link
   * AttributeTransformerDescriptor#AttributeTransformerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AttributeTransformerDescriptor.<init>(IConfigurationElement)"})
  public void testNewAttributeTransformerDescriptor_thenReturnPropertiesFirstNameIsEmptyString() {
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
    AttributeTransformerDescriptor actualAttributeTransformerDescriptor =
        new AttributeTransformerDescriptor(config);

    // Assert
    verify(configurationElementHandle2, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getContributor();
    verify(configurationElementHandle2).getName();
    verify(iContributor).getName();
    List<DBPPropertyDescriptor> properties = actualAttributeTransformerDescriptor.getProperties();
    assertEquals(1, properties.size());
    DBPPropertyDescriptor getResult = properties.get(0);
    assertTrue(getResult instanceof PropertyDescriptor);
    assertEquals("", ((PropertyDescriptor) getResult).getName());
    assertEquals("", getResult.getDescription());
    assertEquals("", getResult.getDisplayName());
    assertEquals("", getResult.getHint());
    assertEquals("", getResult.getId());
    assertNull(getResult.getDefaultValue());
    assertNull(getResult.getFeatures());
  }

  /**
   * Test {@link
   * AttributeTransformerDescriptor#AttributeTransformerDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return Properties first Required.
   * </ul>
   *
   * <p>Method under test: {@link
   * AttributeTransformerDescriptor#AttributeTransformerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AttributeTransformerDescriptor.<init>(IConfigurationElement)"})
  public void testNewAttributeTransformerDescriptor_thenReturnPropertiesFirstRequired() {
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
        .thenReturn(Boolean.TRUE.toString());

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
    AttributeTransformerDescriptor actualAttributeTransformerDescriptor =
        new AttributeTransformerDescriptor(config);

    // Assert
    verify(configurationElementHandle2, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getContributor();
    verify(configurationElementHandle2).getName();
    verify(iContributor).getName();
    List<DBPPropertyDescriptor> properties = actualAttributeTransformerDescriptor.getProperties();
    assertEquals(1, properties.size());
    DBPPropertyDescriptor getResult = properties.get(0);
    assertTrue(getResult instanceof PropertyDescriptor);
    assertTrue(getResult.isRequired());
    assertEquals(Boolean.TRUE.toString(), ((PropertyDescriptor) getResult).getName());
    assertEquals(Boolean.TRUE.toString(), getResult.getDescription());
    assertEquals(Boolean.TRUE.toString(), getResult.getDisplayName());
    assertEquals(Boolean.TRUE.toString(), getResult.getHint());
    assertEquals(Boolean.TRUE.toString(), getResult.getId());
    assertEquals(Boolean.TRUE.toString(), getResult.getDefaultValue());
    assertArrayEquals(new String[] {Boolean.TRUE.toString(), "required"}, getResult.getFeatures());
  }

  /**
   * Test {@link
   * AttributeTransformerDescriptor#AttributeTransformerDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return SupportedDataSources size is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * AttributeTransformerDescriptor#AttributeTransformerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AttributeTransformerDescriptor.<init>(IConfigurationElement)"})
  public void testNewAttributeTransformerDescriptor_thenReturnSupportedDataSourcesSizeIsOne() {
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

    ConfigurationElementHandle configurationElementHandle2 = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle2.getAttribute(Mockito.<String>any()))
        .thenReturn(DataTypeAbstractDescriptor.ALL_TYPES_PATTERN);
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
    AttributeTransformerDescriptor actualAttributeTransformerDescriptor =
        new AttributeTransformerDescriptor(config);

    // Assert
    verify(configurationElementHandle2, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getChildren(Mockito.<String>any());
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getContributor();
    verify(configurationElementHandle2).getName();
    verify(iContributor).getName();
    DBPImage icon = actualAttributeTransformerDescriptor.getIcon();
    assertTrue(icon instanceof DBIcon);
    assertEquals("Attribute", actualAttributeTransformerDescriptor.getDescription());
    assertEquals("Attribute", actualAttributeTransformerDescriptor.getName());
    assertEquals("Attribute", actualAttributeTransformerDescriptor.getId());
    assertEquals("platform:/plugin/Name/Attribute", icon.getLocation());
    List<String> supportedDataSources =
        actualAttributeTransformerDescriptor.getSupportedDataSources();
    assertEquals(1, supportedDataSources.size());
    assertTrue(actualAttributeTransformerDescriptor.getSupportedTypes().isEmpty());
    assertEquals(DataTypeAbstractDescriptor.ALL_TYPES_PATTERN, supportedDataSources.get(0));
  }
}
