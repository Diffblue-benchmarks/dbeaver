package org.jkiss.dbeaver.registry.fs;

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
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.DBPImage;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class FileSystemProviderDescriptorDiffblueTest {
  /**
   * Test {@link FileSystemProviderDescriptor#FileSystemProviderDescriptor(IConfigurationElement)}.
   *
   * <p>Method under test: {@link
   * FileSystemProviderDescriptor#FileSystemProviderDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FileSystemProviderDescriptor.<init>(IConfigurationElement)"})
  public void testNewFileSystemProviderDescriptor() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenThrow(new IllegalStateException());

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getContributor()).thenReturn(iContributor);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> new FileSystemProviderDescriptor(config));
    verify(config).getContributor();
    verify(iContributor).getName();
  }

  /**
   * Test {@link FileSystemProviderDescriptor#FileSystemProviderDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@link IllegalStateException#IllegalStateException()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * FileSystemProviderDescriptor#FileSystemProviderDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FileSystemProviderDescriptor.<init>(IConfigurationElement)"})
  public void testNewFileSystemProviderDescriptor_givenIllegalStateException() {
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
    when(config.getAttribute(Mockito.<String>any())).thenThrow(new IllegalStateException());
    when(config.getContributor()).thenReturn(iContributor);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> new FileSystemProviderDescriptor(config));
    verify(config).getAttribute("id");
    verify(config).getContributor();
    verify(iContributor).getName();
  }

  /**
   * Test {@link FileSystemProviderDescriptor#FileSystemProviderDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return Description is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link
   * FileSystemProviderDescriptor#FileSystemProviderDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FileSystemProviderDescriptor.<init>(IConfigurationElement)"})
  public void testNewFileSystemProviderDescriptor_thenReturnDescriptionIsAttribute() {
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
    when(config.getContributor()).thenReturn(iContributor);

    // Act
    FileSystemProviderDescriptor actualFileSystemProviderDescriptor =
        new FileSystemProviderDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getContributor();
    verify(iContributor).getName();
    DBPImage icon = actualFileSystemProviderDescriptor.getIcon();
    assertTrue(icon instanceof DBIcon);
    assertEquals("Attribute", actualFileSystemProviderDescriptor.getDescription());
    assertEquals("Attribute", actualFileSystemProviderDescriptor.getId());
    assertEquals("Attribute", actualFileSystemProviderDescriptor.getLabel());
    assertEquals("Attribute", actualFileSystemProviderDescriptor.getRequiredAuth());
    assertEquals("Attribute", actualFileSystemProviderDescriptor.getSchema());
    assertEquals("platform:/plugin/Name/Attribute", icon.getLocation());
  }

  /**
   * Test {@link FileSystemProviderDescriptor#FileSystemProviderDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return Description is empty string.
   * </ul>
   *
   * <p>Method under test: {@link
   * FileSystemProviderDescriptor#FileSystemProviderDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FileSystemProviderDescriptor.<init>(IConfigurationElement)"})
  public void testNewFileSystemProviderDescriptor_thenReturnDescriptionIsEmptyString() {
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
    when(config.getAttribute(Mockito.<String>any())).thenReturn("");
    when(config.getContributor()).thenReturn(iContributor);

    // Act
    FileSystemProviderDescriptor actualFileSystemProviderDescriptor =
        new FileSystemProviderDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getContributor();
    verify(iContributor).getName();
    assertEquals("", actualFileSystemProviderDescriptor.getDescription());
    assertEquals("", actualFileSystemProviderDescriptor.getId());
    assertEquals("", actualFileSystemProviderDescriptor.getLabel());
    assertEquals("", actualFileSystemProviderDescriptor.getSchema());
    assertNull(actualFileSystemProviderDescriptor.getRequiredAuth());
  }

  /**
   * Test {@link FileSystemProviderDescriptor#FileSystemProviderDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return Description is {@code #}.
   * </ul>
   *
   * <p>Method under test: {@link
   * FileSystemProviderDescriptor#FileSystemProviderDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FileSystemProviderDescriptor.<init>(IConfigurationElement)"})
  public void testNewFileSystemProviderDescriptor_thenReturnDescriptionIsNumberSign() {
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
    when(config.getAttribute(Mockito.<String>any())).thenReturn("#");
    when(config.getContributor()).thenReturn(iContributor);

    // Act
    FileSystemProviderDescriptor actualFileSystemProviderDescriptor =
        new FileSystemProviderDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getContributor();
    verify(iContributor).getName();
    assertEquals("#", actualFileSystemProviderDescriptor.getDescription());
    assertEquals("#", actualFileSystemProviderDescriptor.getId());
    assertEquals("#", actualFileSystemProviderDescriptor.getLabel());
    assertEquals("#", actualFileSystemProviderDescriptor.getRequiredAuth());
    assertEquals("#", actualFileSystemProviderDescriptor.getSchema());
  }

  /**
   * Test {@link FileSystemProviderDescriptor#FileSystemProviderDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return Icon Location is {@code platform:}.
   * </ul>
   *
   * <p>Method under test: {@link
   * FileSystemProviderDescriptor#FileSystemProviderDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FileSystemProviderDescriptor.<init>(IConfigurationElement)"})
  public void testNewFileSystemProviderDescriptor_thenReturnIconLocationIsPlatform() {
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
    when(config.getAttribute(Mockito.<String>any())).thenReturn("platform:");
    when(config.getContributor()).thenReturn(iContributor);

    // Act
    FileSystemProviderDescriptor actualFileSystemProviderDescriptor =
        new FileSystemProviderDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getContributor();
    verify(iContributor).getName();
    DBPImage icon = actualFileSystemProviderDescriptor.getIcon();
    assertTrue(icon instanceof DBIcon);
    assertEquals("platform:", icon.getLocation());
    assertEquals("platform:", actualFileSystemProviderDescriptor.getDescription());
    assertEquals("platform:", actualFileSystemProviderDescriptor.getId());
    assertEquals("platform:", actualFileSystemProviderDescriptor.getLabel());
    assertEquals("platform:", actualFileSystemProviderDescriptor.getRequiredAuth());
    assertEquals("platform:", actualFileSystemProviderDescriptor.getSchema());
  }

  /**
   * Test {@link FileSystemProviderDescriptor#getInstance()}.
   *
   * <ul>
   *   <li>Given {@link ConfigurationElementHandle} {@link
   *       ConfigurationElementHandle#getAttribute(String)} return {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link FileSystemProviderDescriptor#getInstance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.fs.DBFFileSystemProvider FileSystemProviderDescriptor.getInstance()"
  })
  public void testGetInstance_givenConfigurationElementHandleGetAttributeReturnAttribute() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getContributor()).thenReturn(iContributor);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new FileSystemProviderDescriptor(config).getInstance());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getContributor();
    verify(iContributor).getName();
  }

  /**
   * Test {@link FileSystemProviderDescriptor#getInstance()}.
   *
   * <ul>
   *   <li>Given {@link ConfigurationElementHandle} {@link
   *       ConfigurationElementHandle#getAttribute(String)} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FileSystemProviderDescriptor#getInstance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.fs.DBFFileSystemProvider FileSystemProviderDescriptor.getInstance()"
  })
  public void testGetInstance_givenConfigurationElementHandleGetAttributeReturnNull() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn(null);
    when(config.getContributor()).thenReturn(iContributor);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new FileSystemProviderDescriptor(config).getInstance());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getContributor();
    verify(iContributor).getName();
  }
}
