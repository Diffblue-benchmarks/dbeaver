package org.jkiss.dbeaver.ui.actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.internal.registry.RegistryObjectManager;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.ui.actions.ToolBarConfigurationDescriptor.Item;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ToolBarConfigurationDescriptorDiffblueTest {
  /**
   * Test Item {@link Item#Item(ToolBarConfigurationDescriptor, IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code Attribute}.
   *   <li>Then return CommandId is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link Item#Item(ToolBarConfigurationDescriptor, IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Item.<init>(ToolBarConfigurationDescriptor, IConfigurationElement)"})
  public void testItemNewItem_givenAttribute_thenReturnCommandIdIsAttribute() {
    // Arrange
    ToolBarConfigurationDescriptor toolBarConfigurationDescriptor =
        new ToolBarConfigurationDescriptor("Key", new ArrayList<>());
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    // Act
    Item actualItem = toolBarConfigurationDescriptor.new Item(configurationElementHandle);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    assertEquals("Attribute", actualItem.getCommandId());
    assertEquals("Attribute", actualItem.getKey());
    assertEquals("Attribute", actualItem.getName());
    assertFalse(actualItem.isVisibleByDefault());
  }

  /**
   * Test Item {@link Item#Item(ToolBarConfigurationDescriptor, IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link Item#Item(ToolBarConfigurationDescriptor, IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Item.<init>(ToolBarConfigurationDescriptor, IConfigurationElement)"})
  public void testItemNewItem_givenEmptyString() {
    // Arrange
    ToolBarConfigurationDescriptor toolBarConfigurationDescriptor =
        new ToolBarConfigurationDescriptor("Key", new ArrayList<>());
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("");

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> toolBarConfigurationDescriptor.new Item(configurationElementHandle));
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
  }

  /**
   * Test Item {@link Item#Item(ToolBarConfigurationDescriptor, IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link ConfigurationElementHandle} {@link
   *       ConfigurationElementHandle#getAttribute(String)} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Item#Item(ToolBarConfigurationDescriptor, IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Item.<init>(ToolBarConfigurationDescriptor, IConfigurationElement)"})
  public void testItemNewItem_givenNull_whenConfigurationElementHandleGetAttributeReturnNull() {
    // Arrange
    ToolBarConfigurationDescriptor toolBarConfigurationDescriptor =
        new ToolBarConfigurationDescriptor("Key", new ArrayList<>());
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn(null);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> toolBarConfigurationDescriptor.new Item(configurationElementHandle));
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
  }

  /**
   * Test Item {@link Item#Item(ToolBarConfigurationDescriptor, IConfigurationElement)}.
   *
   * <ul>
   *   <li>When {@link ToolBarConfigurationDescriptor#ToolBarConfigurationDescriptor(String, List)}
   *       with {@code key} and elements is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link Item#Item(ToolBarConfigurationDescriptor, IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Item.<init>(ToolBarConfigurationDescriptor, IConfigurationElement)"})
  public void testItemNewItem_whenToolBarConfigurationDescriptorWithKeyAndElementsIsArrayList() {
    // Arrange
    ToolBarConfigurationDescriptor toolBarConfigurationDescriptor =
        new ToolBarConfigurationDescriptor("key", new ArrayList<>());
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    // Act
    Item actualItem = toolBarConfigurationDescriptor.new Item(configurationElementHandle);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    assertEquals("Attribute", actualItem.getCommandId());
    assertEquals("Attribute", actualItem.getKey());
    assertEquals("Attribute", actualItem.getName());
    assertFalse(actualItem.isVisibleByDefault());
  }

  /**
   * Test {@link ToolBarConfigurationDescriptor#ToolBarConfigurationDescriptor(String, List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code Key}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ToolBarConfigurationDescriptor#ToolBarConfigurationDescriptor(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ToolBarConfigurationDescriptor.<init>(String, List)"})
  public void testNewToolBarConfigurationDescriptor_whenArrayList_thenReturnKey() {
    // Arrange and Act
    ToolBarConfigurationDescriptor actualToolBarConfigurationDescriptor =
        new ToolBarConfigurationDescriptor("Key", new ArrayList<>());

    // Assert
    assertEquals("Key", actualToolBarConfigurationDescriptor.getKey());
    assertEquals("Key", actualToolBarConfigurationDescriptor.getName());
    assertTrue(actualToolBarConfigurationDescriptor.getItems().isEmpty());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ToolBarConfigurationDescriptor#getKey()}
   *   <li>{@link ToolBarConfigurationDescriptor#getName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String ToolBarConfigurationDescriptor.getKey()",
    "String ToolBarConfigurationDescriptor.getName()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ToolBarConfigurationDescriptor toolBarConfigurationDescriptor =
        new ToolBarConfigurationDescriptor("Key", new ArrayList<>());

    // Act
    String actualKey = toolBarConfigurationDescriptor.getKey();

    // Assert
    assertEquals("Key", actualKey);
    assertEquals("Key", toolBarConfigurationDescriptor.getName());
  }

  /**
   * Test {@link ToolBarConfigurationDescriptor#isItemVisible(String)}.
   *
   * <p>Method under test: {@link ToolBarConfigurationDescriptor#isItemVisible(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ToolBarConfigurationDescriptor.isItemVisible(String)"})
  public void testIsItemVisible() {
    // Arrange
    ToolBarConfigurationDescriptor toolBarConfigurationDescriptor =
        new ToolBarConfigurationDescriptor("Key", new ArrayList<>());

    // Act and Assert
    assertFalse(toolBarConfigurationDescriptor.isItemVisible("Item Key"));
  }

  /**
   * Test {@link ToolBarConfigurationDescriptor#getItems()}.
   *
   * <p>Method under test: {@link ToolBarConfigurationDescriptor#getItems()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Collection ToolBarConfigurationDescriptor.getItems()"})
  public void testGetItems() {
    // Arrange
    ToolBarConfigurationDescriptor toolBarConfigurationDescriptor =
        new ToolBarConfigurationDescriptor("Key", new ArrayList<>());

    // Act and Assert
    assertTrue(toolBarConfigurationDescriptor.getItems().isEmpty());
  }
}
