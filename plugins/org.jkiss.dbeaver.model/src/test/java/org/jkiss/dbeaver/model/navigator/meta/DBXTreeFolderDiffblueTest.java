package org.jkiss.dbeaver.model.navigator.meta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
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
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.internal.Engine;
import org.apache.commons.jexl3.internal.Script;
import org.apache.commons.jexl3.internal.introspection.Uberspect;
import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.internal.registry.RegistryObjectManager;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.impl.AbstractDescriptor;
import org.jkiss.dbeaver.model.impl.PropertyDescriptor;
import org.jkiss.dbeaver.model.impl.PropertyGroupDescriptor;
import org.jkiss.dbeaver.model.navigator.meta.DBXTreeFolder.ItemType;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBXTreeFolderDiffblueTest {
  /**
   * Test {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode, IConfigurationElement,
   * String, boolean, boolean, String, boolean)}.
   *
   * <ul>
   *   <li>Given {@link ConfigurationElementHandle} {@link
   *       ConfigurationElementHandle#getAttribute(String)} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode,
   * IConfigurationElement, String, boolean, boolean, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBXTreeFolder.<init>(AbstractDescriptor, DBXTreeNode, IConfigurationElement, String, boolean, boolean, String, boolean)"
  })
  public void testNewDBXTreeFolder_givenConfigurationElementHandleGetAttributeReturnNull() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> source = new PropertyGroupDescriptor<>(cfg);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn(null);

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    // Act
    DBXTreeFolder actualDbxTreeFolder =
        new DBXTreeFolder(source, null, config, "Type", true, true, "", true);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle).getAttribute("type");
    verify(config).getChildren("itemType");
    verify(cfg).getContributor();
    verify(iContributor).getName();
    assertEquals("Attribute", actualDbxTreeFolder.getDescription());
    assertEquals("Attribute", actualDbxTreeFolder.getHumanReadableId());
    assertEquals("Attribute", actualDbxTreeFolder.getIdOrType());
    assertEquals("Attribute", actualDbxTreeFolder.getOptionalItem());
    assertEquals("Attribute", actualDbxTreeFolder.getId());
    assertNull(actualDbxTreeFolder.getItemTypes());
  }

  /**
   * Test {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode, IConfigurationElement,
   * String, boolean, boolean, String, boolean)}.
   *
   * <ul>
   *   <li>Given empty array of {@link IConfigurationElement}.
   * </ul>
   *
   * <p>Method under test: {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode,
   * IConfigurationElement, String, boolean, boolean, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBXTreeFolder.<init>(AbstractDescriptor, DBXTreeNode, IConfigurationElement, String, boolean, boolean, String, boolean)"
  })
  public void testNewDBXTreeFolder_givenEmptyArrayOfIConfigurationElement() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> source = new PropertyGroupDescriptor<>(cfg);

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any())).thenReturn(new IConfigurationElement[] {});
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    // Act
    DBXTreeFolder actualDbxTreeFolder =
        new DBXTreeFolder(source, null, config, "Type", true, true, "", true);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("itemType");
    verify(cfg).getContributor();
    verify(iContributor).getName();
    assertEquals("Attribute", actualDbxTreeFolder.getDescription());
    assertEquals("Attribute", actualDbxTreeFolder.getHumanReadableId());
    assertEquals("Attribute", actualDbxTreeFolder.getIdOrType());
    assertEquals("Attribute", actualDbxTreeFolder.getOptionalItem());
    assertEquals("Attribute", actualDbxTreeFolder.getId());
    assertNull(actualDbxTreeFolder.getItemTypes());
  }

  /**
   * Test {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode, IConfigurationElement,
   * String, boolean, boolean, String, boolean)}.
   *
   * <ul>
   *   <li>Then return first element ClassName is {@code #}.
   * </ul>
   *
   * <p>Method under test: {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode,
   * IConfigurationElement, String, boolean, boolean, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBXTreeFolder.<init>(AbstractDescriptor, DBXTreeNode, IConfigurationElement, String, boolean, boolean, String, boolean)"
  })
  public void testNewDBXTreeFolder_thenReturnFirstElementClassNameIsNumberSign() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> source = new PropertyGroupDescriptor<>(cfg);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("#");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    // Act
    DBXTreeFolder actualDbxTreeFolder =
        new DBXTreeFolder(source, null, config, "Type", true, true, "", true);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("itemType");
    verify(cfg).getContributor();
    verify(iContributor).getName();
    ItemType[] itemTypes = actualDbxTreeFolder.getItemTypes();
    ItemType itemType = itemTypes[0];
    assertEquals("#", itemType.getClassName());
    assertEquals("#", itemType.getItemType());
    assertNull(actualDbxTreeFolder.getVisibleIf());
    assertNull(itemType.getItemIcon());
    assertNull(actualDbxTreeFolder.getParent());
    assertEquals(1, itemTypes.length);
  }

  /**
   * Test {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode, IConfigurationElement,
   * String, boolean, boolean, String, boolean)}.
   *
   * <ul>
   *   <li>Then return first element ItemIcon Location is {@code platform:}.
   * </ul>
   *
   * <p>Method under test: {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode,
   * IConfigurationElement, String, boolean, boolean, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBXTreeFolder.<init>(AbstractDescriptor, DBXTreeNode, IConfigurationElement, String, boolean, boolean, String, boolean)"
  })
  public void testNewDBXTreeFolder_thenReturnFirstElementItemIconLocationIsPlatform() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> source = new PropertyGroupDescriptor<>(cfg);

    DBXTreeNode parent = mock(DBXTreeNode.class);
    doNothing().when(parent).addChild(Mockito.<DBXTreeNode>any());

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("platform:");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    // Act
    DBXTreeFolder actualDbxTreeFolder =
        new DBXTreeFolder(source, parent, config, "Type", true, true, "", true);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("itemType");
    verify(cfg).getContributor();
    verify(iContributor).getName();
    verify(parent).addChild(isA(DBXTreeNode.class));
    ItemType[] itemTypes = actualDbxTreeFolder.getItemTypes();
    ItemType itemType = itemTypes[0];
    DBPImage itemIcon = itemType.getItemIcon();
    assertTrue(itemIcon instanceof DBIcon);
    assertEquals("platform:", itemIcon.getLocation());
    assertEquals("platform:", itemType.getClassName());
    assertEquals("platform:", itemType.getItemType());
    assertEquals(1, itemTypes.length);
    assertSame(parent, actualDbxTreeFolder.getParent());
  }

  /**
   * Test {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode, IConfigurationElement,
   * String, boolean, boolean, String, boolean)}.
   *
   * <ul>
   *   <li>Then return first element ItemIcon Token is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode,
   * IConfigurationElement, String, boolean, boolean, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBXTreeFolder.<init>(AbstractDescriptor, DBXTreeNode, IConfigurationElement, String, boolean, boolean, String, boolean)"
  })
  public void testNewDBXTreeFolder_thenReturnFirstElementItemIconTokenIsNull() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> source = new PropertyGroupDescriptor<>(cfg);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    // Act
    DBXTreeFolder actualDbxTreeFolder =
        new DBXTreeFolder(source, null, config, "Type", true, true, "", true);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("itemType");
    verify(cfg).getContributor();
    verify(iContributor).getName();
    ItemType[] itemTypes = actualDbxTreeFolder.getItemTypes();
    ItemType itemType = itemTypes[0];
    DBPImage itemIcon = itemType.getItemIcon();
    assertTrue(itemIcon instanceof DBIcon);
    assertEquals("Attribute", itemType.getClassName());
    assertEquals("Attribute", itemType.getItemType());
    assertEquals("platform:/plugin/Name/Attribute", itemIcon.getLocation());
    assertNull(((DBIcon) itemIcon).getToken());
    assertEquals(1, itemTypes.length);
  }

  /**
   * Test {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode, IConfigurationElement,
   * String, boolean, boolean, String, boolean)}.
   *
   * <ul>
   *   <li>Then return HumanReadableId is {@code Type}.
   * </ul>
   *
   * <p>Method under test: {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode,
   * IConfigurationElement, String, boolean, boolean, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBXTreeFolder.<init>(AbstractDescriptor, DBXTreeNode, IConfigurationElement, String, boolean, boolean, String, boolean)"
  })
  public void testNewDBXTreeFolder_thenReturnHumanReadableIdIsType() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> source = new PropertyGroupDescriptor<>(cfg);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn(null);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    // Act
    DBXTreeFolder actualDbxTreeFolder =
        new DBXTreeFolder(source, null, config, "Type", true, true, "", true);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("itemType");
    verify(cfg).getContributor();
    verify(iContributor).getName();
    assertEquals("Type", actualDbxTreeFolder.getHumanReadableId());
    assertEquals("Type", actualDbxTreeFolder.getIdOrType());
    assertNull(actualDbxTreeFolder.getDescription());
    assertNull(actualDbxTreeFolder.getOptionalItem());
    assertNull(actualDbxTreeFolder.getId());
  }

  /**
   * Test {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode, IConfigurationElement,
   * String, boolean, boolean, String, boolean)}.
   *
   * <ul>
   *   <li>Then return Parent is {@link DBXTreeNode}.
   * </ul>
   *
   * <p>Method under test: {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode,
   * IConfigurationElement, String, boolean, boolean, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBXTreeFolder.<init>(AbstractDescriptor, DBXTreeNode, IConfigurationElement, String, boolean, boolean, String, boolean)"
  })
  public void testNewDBXTreeFolder_thenReturnParentIsDBXTreeNode() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> source = new PropertyGroupDescriptor<>(cfg);

    DBXTreeNode parent = mock(DBXTreeNode.class);
    doNothing().when(parent).addChild(Mockito.<DBXTreeNode>any());

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    // Act
    DBXTreeFolder actualDbxTreeFolder =
        new DBXTreeFolder(source, parent, config, "Type", true, true, "", true);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("itemType");
    verify(cfg).getContributor();
    verify(iContributor).getName();
    verify(parent).addChild(isA(DBXTreeNode.class));
    ItemType[] itemTypes = actualDbxTreeFolder.getItemTypes();
    ItemType itemType = itemTypes[0];
    DBPImage itemIcon = itemType.getItemIcon();
    assertTrue(itemIcon instanceof DBIcon);
    assertEquals("Attribute", itemType.getClassName());
    assertEquals("Attribute", itemType.getItemType());
    assertEquals("platform:/plugin/Name/Attribute", itemIcon.getLocation());
    assertEquals(1, itemTypes.length);
    assertSame(parent, actualDbxTreeFolder.getParent());
  }

  /**
   * Test {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode, IConfigurationElement,
   * String, boolean, boolean, String, boolean)}.
   *
   * <ul>
   *   <li>When {@link ConfigurationElementHandle} {@link
   *       ConfigurationElementHandle#getChildren(String)} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode,
   * IConfigurationElement, String, boolean, boolean, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBXTreeFolder.<init>(AbstractDescriptor, DBXTreeNode, IConfigurationElement, String, boolean, boolean, String, boolean)"
  })
  public void testNewDBXTreeFolder_whenConfigurationElementHandleGetChildrenReturnNull() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> source = new PropertyGroupDescriptor<>(cfg);

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any())).thenReturn(null);
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    // Act
    DBXTreeFolder actualDbxTreeFolder =
        new DBXTreeFolder(source, null, config, "Type", true, true, "", true);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("itemType");
    verify(cfg).getContributor();
    verify(iContributor).getName();
    assertEquals("Attribute", actualDbxTreeFolder.getDescription());
    assertEquals("Attribute", actualDbxTreeFolder.getHumanReadableId());
    assertEquals("Attribute", actualDbxTreeFolder.getIdOrType());
    assertEquals("Attribute", actualDbxTreeFolder.getOptionalItem());
    assertEquals("Attribute", actualDbxTreeFolder.getId());
    assertNull(actualDbxTreeFolder.getItemTypes());
  }

  /**
   * Test {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode, IConfigurationElement,
   * String, boolean, boolean, String, boolean)}.
   *
   * <ul>
   *   <li>When {@code id}.
   *   <li>Then return VisibleIf ParsedText is {@code id}.
   * </ul>
   *
   * <p>Method under test: {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode,
   * IConfigurationElement, String, boolean, boolean, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBXTreeFolder.<init>(AbstractDescriptor, DBXTreeNode, IConfigurationElement, String, boolean, boolean, String, boolean)"
  })
  public void testNewDBXTreeFolder_whenId_thenReturnVisibleIfParsedTextIsId() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> source = new PropertyGroupDescriptor<>(cfg);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    // Act
    DBXTreeFolder actualDbxTreeFolder =
        new DBXTreeFolder(source, null, config, "Type", true, true, "id", true);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("itemType");
    verify(cfg).getContributor();
    verify(iContributor).getName();
    JexlExpression visibleIf = actualDbxTreeFolder.getVisibleIf();
    JexlEngine engine = ((Script) visibleIf).getEngine();
    assertTrue(engine instanceof Engine);
    assertTrue(visibleIf instanceof Script);
    assertTrue(engine.getUberspect() instanceof Uberspect);
    assertEquals("id", visibleIf.getParsedText());
    assertEquals("id", visibleIf.getSourceText());
    assertNull(((Script) visibleIf).getLocalVariables());
    assertNull(((Script) visibleIf).getParameters());
    assertEquals(1, ((Script) visibleIf).getVariables().size());
    assertFalse(engine.isSilent());
    assertTrue(((Script) visibleIf).getPragmas().isEmpty());
    assertTrue(engine.isCancellable());
    assertTrue(engine.isDebug());
    assertTrue(engine.isStrict());
  }

  /**
   * Test {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode, IConfigurationElement,
   * String, boolean, boolean, String, boolean)}.
   *
   * <ul>
   *   <li>When {@code id}.
   *   <li>Then return VisibleIf ParsedText is {@code id}.
   * </ul>
   *
   * <p>Method under test: {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode,
   * IConfigurationElement, String, boolean, boolean, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBXTreeFolder.<init>(AbstractDescriptor, DBXTreeNode, IConfigurationElement, String, boolean, boolean, String, boolean)"
  })
  public void testNewDBXTreeFolder_whenId_thenReturnVisibleIfParsedTextIsId2() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> source = new PropertyGroupDescriptor<>(cfg);

    DBXTreeNode parent = mock(DBXTreeNode.class);
    doNothing().when(parent).addChild(Mockito.<DBXTreeNode>any());

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    // Act
    DBXTreeFolder actualDbxTreeFolder =
        new DBXTreeFolder(source, parent, config, "Type", true, true, "id", true);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("itemType");
    verify(cfg).getContributor();
    verify(iContributor).getName();
    verify(parent).addChild(isA(DBXTreeNode.class));
    JexlExpression visibleIf = actualDbxTreeFolder.getVisibleIf();
    JexlEngine engine = ((Script) visibleIf).getEngine();
    assertTrue(engine instanceof Engine);
    assertTrue(visibleIf instanceof Script);
    assertTrue(engine.getUberspect() instanceof Uberspect);
    assertEquals("id", visibleIf.getParsedText());
    assertEquals("id", visibleIf.getSourceText());
    assertNull(((Script) visibleIf).getLocalVariables());
    assertNull(((Script) visibleIf).getParameters());
    assertEquals(1, ((Script) visibleIf).getVariables().size());
    assertFalse(engine.isSilent());
    assertTrue(((Script) visibleIf).getPragmas().isEmpty());
    assertTrue(engine.isCancellable());
    assertTrue(engine.isDebug());
    assertTrue(engine.isStrict());
    assertSame(parent, actualDbxTreeFolder.getParent());
  }

  /**
   * Test {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode, IConfigurationElement,
   * String, boolean, boolean, String, boolean)}.
   *
   * <ul>
   *   <li>When {@code label}.
   *   <li>Then return VisibleIf ParsedText is {@code label}.
   * </ul>
   *
   * <p>Method under test: {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode,
   * IConfigurationElement, String, boolean, boolean, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBXTreeFolder.<init>(AbstractDescriptor, DBXTreeNode, IConfigurationElement, String, boolean, boolean, String, boolean)"
  })
  public void testNewDBXTreeFolder_whenLabel_thenReturnVisibleIfParsedTextIsLabel() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> source = new PropertyGroupDescriptor<>(cfg);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    // Act
    DBXTreeFolder actualDbxTreeFolder =
        new DBXTreeFolder(source, null, config, "Type", true, true, "label", true);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("itemType");
    verify(cfg).getContributor();
    verify(iContributor).getName();
    JexlExpression visibleIf = actualDbxTreeFolder.getVisibleIf();
    JexlEngine engine = ((Script) visibleIf).getEngine();
    assertTrue(engine instanceof Engine);
    assertTrue(visibleIf instanceof Script);
    assertTrue(engine.getUberspect() instanceof Uberspect);
    assertEquals("label", visibleIf.getParsedText());
    assertEquals("label", visibleIf.getSourceText());
    assertNull(((Script) visibleIf).getLocalVariables());
    assertNull(((Script) visibleIf).getParameters());
    assertEquals(1, ((Script) visibleIf).getVariables().size());
    assertFalse(engine.isSilent());
    assertTrue(((Script) visibleIf).getPragmas().isEmpty());
    assertTrue(engine.isCancellable());
    assertTrue(engine.isDebug());
    assertTrue(engine.isStrict());
  }

  /**
   * Test {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode, IConfigurationElement,
   * String, boolean, boolean, String, boolean)}.
   *
   * <ul>
   *   <li>When {@code #}.
   *   <li>Then return first element ItemIcon Token is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode,
   * IConfigurationElement, String, boolean, boolean, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBXTreeFolder.<init>(AbstractDescriptor, DBXTreeNode, IConfigurationElement, String, boolean, boolean, String, boolean)"
  })
  public void testNewDBXTreeFolder_whenNumberSign_thenReturnFirstElementItemIconTokenIsNull() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> source = new PropertyGroupDescriptor<>(cfg);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    // Act
    DBXTreeFolder actualDbxTreeFolder =
        new DBXTreeFolder(source, null, config, "Type", true, true, "#", true);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("itemType");
    verify(cfg).getContributor();
    verify(iContributor).getName();
    ItemType[] itemTypes = actualDbxTreeFolder.getItemTypes();
    ItemType itemType = itemTypes[0];
    DBPImage itemIcon = itemType.getItemIcon();
    assertTrue(itemIcon instanceof DBIcon);
    assertEquals("Attribute", itemType.getClassName());
    assertEquals("Attribute", itemType.getItemType());
    assertEquals("platform:/plugin/Name/Attribute", itemIcon.getLocation());
    assertNull(((DBIcon) itemIcon).getToken());
    assertEquals(1, itemTypes.length);
  }

  /**
   * Test {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode, IConfigurationElement,
   * String, boolean, boolean, String, boolean)}.
   *
   * <ul>
   *   <li>When {@code Visible If}.
   *   <li>Then return first element ItemIcon Token is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBXTreeFolder#DBXTreeFolder(AbstractDescriptor, DBXTreeNode,
   * IConfigurationElement, String, boolean, boolean, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBXTreeFolder.<init>(AbstractDescriptor, DBXTreeNode, IConfigurationElement, String, boolean, boolean, String, boolean)"
  })
  public void testNewDBXTreeFolder_whenVisibleIf_thenReturnFirstElementItemIconTokenIsNull() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> source = new PropertyGroupDescriptor<>(cfg);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    // Act
    DBXTreeFolder actualDbxTreeFolder =
        new DBXTreeFolder(source, null, config, "Type", true, true, "Visible If", true);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("itemType");
    verify(cfg).getContributor();
    verify(iContributor).getName();
    ItemType[] itemTypes = actualDbxTreeFolder.getItemTypes();
    ItemType itemType = itemTypes[0];
    DBPImage itemIcon = itemType.getItemIcon();
    assertTrue(itemIcon instanceof DBIcon);
    assertEquals("Attribute", itemType.getClassName());
    assertEquals("Attribute", itemType.getItemType());
    assertEquals("platform:/plugin/Name/Attribute", itemIcon.getLocation());
    assertNull(((DBIcon) itemIcon).getToken());
    assertEquals(1, itemTypes.length);
  }
}
