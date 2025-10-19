package org.jkiss.dbeaver.model.sql.backup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
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
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class JDBCDatabaseBackupDescriptorDiffblueTest {
  /**
   * Test {@link JDBCDatabaseBackupDescriptor#JDBCDatabaseBackupDescriptor(IConfigurationElement)}.
   *
   * <p>Method under test: {@link
   * JDBCDatabaseBackupDescriptor#JDBCDatabaseBackupDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCDatabaseBackupDescriptor.<init>(IConfigurationElement)"})
  public void testNewJDBCDatabaseBackupDescriptor() {
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

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getContributor()).thenReturn(iContributor);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> new JDBCDatabaseBackupDescriptor(cfg));
    verify(cfg).getContributor();
    verify(iContributor).getName();
  }

  /**
   * Test {@link JDBCDatabaseBackupDescriptor#JDBCDatabaseBackupDescriptor(IConfigurationElement)}.
   *
   * <p>Method under test: {@link
   * JDBCDatabaseBackupDescriptor#JDBCDatabaseBackupDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCDatabaseBackupDescriptor.<init>(IConfigurationElement)"})
  public void testNewJDBCDatabaseBackupDescriptor2() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "class").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, "Master Token", "User Token");
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);

    // Act
    JDBCDatabaseBackupDescriptor actualJdbcDatabaseBackupDescriptor =
        new JDBCDatabaseBackupDescriptor(cfg);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
    assertEquals("Attribute", actualJdbcDatabaseBackupDescriptor.getDialect());
    assertEquals("Name", actualJdbcDatabaseBackupDescriptor.getPluginId());
  }

  /**
   * Test {@link JDBCDatabaseBackupDescriptor#JDBCDatabaseBackupDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code Attribute}.
   *   <li>Then return Dialect is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link
   * JDBCDatabaseBackupDescriptor#JDBCDatabaseBackupDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCDatabaseBackupDescriptor.<init>(IConfigurationElement)"})
  public void testNewJDBCDatabaseBackupDescriptor_givenAttribute_thenReturnDialectIsAttribute() {
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

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);

    // Act
    JDBCDatabaseBackupDescriptor actualJdbcDatabaseBackupDescriptor =
        new JDBCDatabaseBackupDescriptor(cfg);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
    assertEquals("Attribute", actualJdbcDatabaseBackupDescriptor.getDialect());
    assertEquals("Name", actualJdbcDatabaseBackupDescriptor.getPluginId());
  }

  /**
   * Test {@link JDBCDatabaseBackupDescriptor#JDBCDatabaseBackupDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code if}.
   *   <li>Then return Dialect is {@code if}.
   * </ul>
   *
   * <p>Method under test: {@link
   * JDBCDatabaseBackupDescriptor#JDBCDatabaseBackupDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCDatabaseBackupDescriptor.<init>(IConfigurationElement)"})
  public void testNewJDBCDatabaseBackupDescriptor_givenIf_thenReturnDialectIsIf() {
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

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("if");
    when(cfg.getContributor()).thenReturn(iContributor);

    // Act
    JDBCDatabaseBackupDescriptor actualJdbcDatabaseBackupDescriptor =
        new JDBCDatabaseBackupDescriptor(cfg);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
    assertEquals("Name", actualJdbcDatabaseBackupDescriptor.getPluginId());
    assertEquals("if", actualJdbcDatabaseBackupDescriptor.getDialect());
  }

  /**
   * Test {@link JDBCDatabaseBackupDescriptor#JDBCDatabaseBackupDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@link IllegalStateException#IllegalStateException()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * JDBCDatabaseBackupDescriptor#JDBCDatabaseBackupDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCDatabaseBackupDescriptor.<init>(IConfigurationElement)"})
  public void testNewJDBCDatabaseBackupDescriptor_givenIllegalStateException() {
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

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenThrow(new IllegalStateException());
    when(cfg.getContributor()).thenReturn(iContributor);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> new JDBCDatabaseBackupDescriptor(cfg));
    verify(cfg).getAttribute("class");
    verify(cfg).getContributor();
    verify(iContributor).getName();
  }

  /**
   * Test {@link JDBCDatabaseBackupDescriptor#JDBCDatabaseBackupDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return Dialect is empty string.
   * </ul>
   *
   * <p>Method under test: {@link
   * JDBCDatabaseBackupDescriptor#JDBCDatabaseBackupDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCDatabaseBackupDescriptor.<init>(IConfigurationElement)"})
  public void testNewJDBCDatabaseBackupDescriptor_thenReturnDialectIsEmptyString() {
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

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("");
    when(cfg.getContributor()).thenReturn(iContributor);

    // Act
    JDBCDatabaseBackupDescriptor actualJdbcDatabaseBackupDescriptor =
        new JDBCDatabaseBackupDescriptor(cfg);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
    assertEquals("", actualJdbcDatabaseBackupDescriptor.getDialect());
    assertEquals("Name", actualJdbcDatabaseBackupDescriptor.getPluginId());
  }
}
