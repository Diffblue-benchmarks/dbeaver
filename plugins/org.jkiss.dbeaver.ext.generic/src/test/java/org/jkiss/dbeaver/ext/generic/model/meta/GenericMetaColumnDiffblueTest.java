package org.jkiss.dbeaver.ext.generic.model.meta;

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
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class GenericMetaColumnDiffblueTest {
  /**
   * Test {@link GenericMetaColumn#GenericMetaColumn(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then return ColumnName is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link GenericMetaColumn#GenericMetaColumn(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GenericMetaColumn.<init>(IConfigurationElement)"})
  public void testNewGenericMetaColumn_given42_thenReturnColumnNameIs42() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, "Master Token", "User Token");
    new RegistryObjectManager(registry);

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("42");

    // Act
    GenericMetaColumn actualGenericMetaColumn = new GenericMetaColumn(cfg);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    assertEquals("42", actualGenericMetaColumn.getColumnName());
    assertEquals("42", actualGenericMetaColumn.getId());
    assertEquals(42, ((Integer) actualGenericMetaColumn.getColumnIdentifier()).intValue());
    assertEquals(42, actualGenericMetaColumn.getColumnIndex());
  }

  /**
   * Test {@link GenericMetaColumn#GenericMetaColumn(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>Then return ColumnName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link GenericMetaColumn#GenericMetaColumn(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GenericMetaColumn.<init>(IConfigurationElement)"})
  public void testNewGenericMetaColumn_givenEmptyString_thenReturnColumnNameIsEmptyString() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, "Master Token", "User Token");
    new RegistryObjectManager(registry);

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("");

    // Act
    GenericMetaColumn actualGenericMetaColumn = new GenericMetaColumn(cfg);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    assertEquals("", actualGenericMetaColumn.getColumnName());
    assertEquals("", actualGenericMetaColumn.getId());
    assertEquals("", actualGenericMetaColumn.getColumnIdentifier());
  }

  /**
   * Test {@link GenericMetaColumn#getColumnIndex()}.
   *
   * <ul>
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link GenericMetaColumn#getColumnIndex()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int GenericMetaColumn.getColumnIndex()"})
  public void testGetColumnIndex_thenReturnFortyTwo() {
    // Arrange
    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("42");

    // Act
    int actualColumnIndex = new GenericMetaColumn(cfg).getColumnIndex();

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    assertEquals(42, actualColumnIndex);
  }

  /**
   * Test {@link GenericMetaColumn#getColumnIdentifier()}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link GenericMetaColumn#getColumnIdentifier()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GenericMetaColumn.getColumnIdentifier()"})
  public void testGetColumnIdentifier_thenReturnEmptyString() {
    // Arrange
    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("");

    // Act
    Object actualColumnIdentifier = new GenericMetaColumn(cfg).getColumnIdentifier();

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    assertEquals("", actualColumnIdentifier);
  }

  /**
   * Test {@link GenericMetaColumn#getColumnIdentifier()}.
   *
   * <ul>
   *   <li>Then return intValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link GenericMetaColumn#getColumnIdentifier()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GenericMetaColumn.getColumnIdentifier()"})
  public void testGetColumnIdentifier_thenReturnIntValueIsFortyTwo() {
    // Arrange
    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("42");

    // Act
    Object actualColumnIdentifier = new GenericMetaColumn(cfg).getColumnIdentifier();

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    assertEquals(42, ((Integer) actualColumnIdentifier).intValue());
  }
}
