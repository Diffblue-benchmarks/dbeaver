package org.jkiss.dbeaver.registry.maven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.internal.registry.RegistryObjectManager;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.connection.DBPAuthInfo;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.runtime.ProxyProgressMonitor;
import org.jkiss.dbeaver.model.task.DBTTask;
import org.jkiss.dbeaver.registry.maven.MavenRepository.RepositoryType;
import org.jkiss.dbeaver.registry.task.TaskLoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class MavenRepositoryDiffblueTest {
  /**
   * Test {@link MavenRepository#MavenRepository(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then return Id is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link MavenRepository#MavenRepository(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenRepository.<init>(IConfigurationElement)"})
  public void testNewMavenRepository_given42_thenReturnIdIs42()
      throws InvalidRegistryObjectException {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getAttribute(Mockito.<String>any())).thenReturn("");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});
    when(config.getAttribute(Mockito.<String>any())).thenReturn("42");
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);

    // Act
    MavenRepository actualMavenRepository = new MavenRepository(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("scope");
    verify(iConfigurationElement).getAttribute("group");
    assertEquals("42", actualMavenRepository.getId());
    assertEquals("42", actualMavenRepository.getName());
    assertEquals("42/", actualMavenRepository.getUrl());
    assertEquals("42/", actualMavenRepository.toString());
    assertEquals(42, actualMavenRepository.getOrder());
  }

  /**
   * Test {@link MavenRepository#MavenRepository(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given empty array of {@link IConfigurationElement}.
   * </ul>
   *
   * <p>Method under test: {@link MavenRepository#MavenRepository(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenRepository.<init>(IConfigurationElement)"})
  public void testNewMavenRepository_givenEmptyArrayOfIConfigurationElement() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any())).thenReturn(new IConfigurationElement[] {});
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new ConfigurationElementHandle(new RegistryObjectManager(registry2), 1);

    // Act
    MavenRepository actualMavenRepository = new MavenRepository(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("scope");
    assertEquals("Attribute", actualMavenRepository.getId());
    assertEquals("Attribute", actualMavenRepository.getName());
    assertEquals("Attribute/", actualMavenRepository.getUrl());
    assertEquals("Attribute/", actualMavenRepository.toString());
  }

  /**
   * Test {@link MavenRepository#MavenRepository(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>Then return Id is empty string.
   * </ul>
   *
   * <p>Method under test: {@link MavenRepository#MavenRepository(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenRepository.<init>(IConfigurationElement)"})
  public void testNewMavenRepository_givenEmptyString_thenReturnIdIsEmptyString()
      throws InvalidRegistryObjectException {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getAttribute(Mockito.<String>any())).thenReturn("");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});
    when(config.getAttribute(Mockito.<String>any())).thenReturn("");
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);

    // Act
    MavenRepository actualMavenRepository = new MavenRepository(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("scope");
    verify(iConfigurationElement).getAttribute("group");
    assertEquals("", actualMavenRepository.getId());
    assertEquals("", actualMavenRepository.getName());
    assertEquals("/", actualMavenRepository.getUrl());
    assertEquals("/", actualMavenRepository.toString());
  }

  /**
   * Test {@link MavenRepository#MavenRepository(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@link IConfigurationElement} {@link IConfigurationElement#getAttribute(String)}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link MavenRepository#MavenRepository(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenRepository.<init>(IConfigurationElement)"})
  public void testNewMavenRepository_givenIConfigurationElementGetAttributeReturnNull()
      throws InvalidRegistryObjectException {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getAttribute(Mockito.<String>any())).thenReturn(null);

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);

    // Act
    MavenRepository actualMavenRepository = new MavenRepository(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("scope");
    verify(iConfigurationElement).getAttribute("group");
    assertEquals("Attribute", actualMavenRepository.getId());
    assertEquals("Attribute", actualMavenRepository.getName());
    assertEquals("Attribute/", actualMavenRepository.getUrl());
    assertEquals("Attribute/", actualMavenRepository.toString());
  }

  /**
   * Test {@link MavenRepository#MavenRepository(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code /}.
   *   <li>Then return Id is {@code /}.
   * </ul>
   *
   * <p>Method under test: {@link MavenRepository#MavenRepository(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenRepository.<init>(IConfigurationElement)"})
  public void testNewMavenRepository_givenSlash_thenReturnIdIsSlash()
      throws InvalidRegistryObjectException {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getAttribute(Mockito.<String>any())).thenReturn("");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});
    when(config.getAttribute(Mockito.<String>any())).thenReturn("/");
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);

    // Act
    MavenRepository actualMavenRepository = new MavenRepository(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("scope");
    verify(iConfigurationElement).getAttribute("group");
    assertEquals("/", actualMavenRepository.getId());
    assertEquals("/", actualMavenRepository.getName());
    assertEquals("/", actualMavenRepository.getUrl());
    assertEquals("/", actualMavenRepository.toString());
  }

  /**
   * Test {@link MavenRepository#MavenRepository(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return Id is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link MavenRepository#MavenRepository(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenRepository.<init>(IConfigurationElement)"})
  public void testNewMavenRepository_thenReturnIdIsAttribute()
      throws InvalidRegistryObjectException {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getAttribute(Mockito.<String>any())).thenReturn("");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);

    // Act
    MavenRepository actualMavenRepository = new MavenRepository(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("scope");
    verify(iConfigurationElement).getAttribute("group");
    assertEquals("Attribute", actualMavenRepository.getId());
    assertEquals("Attribute", actualMavenRepository.getName());
    assertEquals("Attribute/", actualMavenRepository.getUrl());
    assertEquals("Attribute/", actualMavenRepository.toString());
  }

  /**
   * Test {@link MavenRepository#MavenRepository(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return Scopes size is one.
   * </ul>
   *
   * <p>Method under test: {@link MavenRepository#MavenRepository(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenRepository.<init>(IConfigurationElement)"})
  public void testNewMavenRepository_thenReturnScopesSizeIsOne()
      throws InvalidRegistryObjectException {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);

    // Act
    MavenRepository actualMavenRepository = new MavenRepository(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("scope");
    verify(iConfigurationElement).getAttribute("group");
    List<String> scopes = actualMavenRepository.getScopes();
    assertEquals(1, scopes.size());
    assertEquals("Attribute", scopes.get(0));
    assertEquals("Attribute", actualMavenRepository.getId());
    assertEquals("Attribute", actualMavenRepository.getName());
    assertEquals("Attribute/", actualMavenRepository.getUrl());
    assertEquals("Attribute/", actualMavenRepository.toString());
  }

  /**
   * Test {@link MavenRepository#MavenRepository(MavenRepository)}.
   *
   * <ul>
   *   <li>Then return Url is {@code https://repo1.maven.org/maven2/}.
   * </ul>
   *
   * <p>Method under test: {@link MavenRepository#MavenRepository(MavenRepository)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenRepository.<init>(MavenRepository)"})
  public void testNewMavenRepository_thenReturnUrlIsHttpsRepo1MavenOrgMaven2() {
    // Arrange and Act
    MavenRepository actualMavenRepository = new MavenRepository(MavenRepository.UnknownRepository);

    // Assert
    assertEquals("https://repo1.maven.org/maven2/", actualMavenRepository.getUrl());
    assertEquals("https://repo1.maven.org/maven2/", actualMavenRepository.toString());
    assertEquals("unknown", actualMavenRepository.getId());
    assertEquals("unknown", actualMavenRepository.getName());
    DBPAuthInfo authInfo = actualMavenRepository.getAuthInfo();
    assertNull(authInfo.getUserName());
    assertNull(authInfo.getUserPassword());
    assertNull(actualMavenRepository.getDescription());
    assertEquals(0, actualMavenRepository.getOrder());
    assertEquals(RepositoryType.GLOBAL, actualMavenRepository.getType());
    assertFalse(authInfo.isSavePassword());
    assertFalse(actualMavenRepository.isSnapshot());
    assertTrue(actualMavenRepository.getScopes().isEmpty());
    assertTrue(actualMavenRepository.isEnabled());
  }

  /**
   * Test {@link MavenRepository#MavenRepository(String, String, String, RepositoryType)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return Url is {@code /}.
   * </ul>
   *
   * <p>Method under test: {@link MavenRepository#MavenRepository(String, String, String,
   * RepositoryType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenRepository.<init>(String, String, String, RepositoryType)"})
  public void testNewMavenRepository_whenEmptyString_thenReturnUrlIsSlash() {
    // Arrange and Act
    MavenRepository actualMavenRepository =
        new MavenRepository("42", "", "/", RepositoryType.GLOBAL);

    // Assert
    assertEquals("/", actualMavenRepository.getUrl());
    assertEquals("/", actualMavenRepository.toString());
    assertEquals("42", actualMavenRepository.getId());
    assertEquals("42", actualMavenRepository.getName());
    DBPAuthInfo authInfo = actualMavenRepository.getAuthInfo();
    assertNull(authInfo.getUserName());
    assertNull(authInfo.getUserPassword());
    assertNull(actualMavenRepository.getDescription());
    assertEquals(0, actualMavenRepository.getOrder());
    assertEquals(RepositoryType.GLOBAL, actualMavenRepository.getType());
    assertFalse(authInfo.isSavePassword());
    assertFalse(actualMavenRepository.isSnapshot());
    assertTrue(actualMavenRepository.getScopes().isEmpty());
    assertTrue(actualMavenRepository.isEnabled());
  }

  /**
   * Test {@link MavenRepository#MavenRepository(String, String, String, RepositoryType)}.
   *
   * <ul>
   *   <li>When {@code Name}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link MavenRepository#MavenRepository(String, String, String,
   * RepositoryType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenRepository.<init>(String, String, String, RepositoryType)"})
  public void testNewMavenRepository_whenName_thenReturnName() {
    // Arrange and Act
    MavenRepository actualMavenRepository =
        new MavenRepository("42", "Name", "https://example.org/example", RepositoryType.GLOBAL);

    // Assert
    assertEquals("42", actualMavenRepository.getId());
    assertEquals("Name", actualMavenRepository.getName());
    assertEquals("https://example.org/example/", actualMavenRepository.getUrl());
    assertEquals("https://example.org/example/", actualMavenRepository.toString());
    DBPAuthInfo authInfo = actualMavenRepository.getAuthInfo();
    assertNull(authInfo.getUserName());
    assertNull(authInfo.getUserPassword());
    assertNull(actualMavenRepository.getDescription());
    assertEquals(0, actualMavenRepository.getOrder());
    assertEquals(RepositoryType.GLOBAL, actualMavenRepository.getType());
    assertFalse(authInfo.isSavePassword());
    assertFalse(actualMavenRepository.isSnapshot());
    assertTrue(actualMavenRepository.getScopes().isEmpty());
    assertTrue(actualMavenRepository.isEnabled());
  }

  /**
   * Test {@link MavenRepository#MavenRepository(String, String, String, RepositoryType)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Url is {@code /}.
   * </ul>
   *
   * <p>Method under test: {@link MavenRepository#MavenRepository(String, String, String,
   * RepositoryType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenRepository.<init>(String, String, String, RepositoryType)"})
  public void testNewMavenRepository_whenNull_thenReturnUrlIsSlash() {
    // Arrange and Act
    MavenRepository actualMavenRepository =
        new MavenRepository("42", null, "/", RepositoryType.GLOBAL);

    // Assert
    assertEquals("/", actualMavenRepository.getUrl());
    assertEquals("/", actualMavenRepository.toString());
    assertEquals("42", actualMavenRepository.getId());
    assertEquals("42", actualMavenRepository.getName());
    DBPAuthInfo authInfo = actualMavenRepository.getAuthInfo();
    assertNull(authInfo.getUserName());
    assertNull(authInfo.getUserPassword());
    assertNull(actualMavenRepository.getDescription());
    assertEquals(0, actualMavenRepository.getOrder());
    assertEquals(RepositoryType.GLOBAL, actualMavenRepository.getType());
    assertFalse(authInfo.isSavePassword());
    assertFalse(actualMavenRepository.isSnapshot());
    assertTrue(actualMavenRepository.getScopes().isEmpty());
    assertTrue(actualMavenRepository.isEnabled());
  }

  /**
   * Test {@link MavenRepository#setScopes(List)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then {@link MavenRepository#UnknownRepository} Scopes is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link MavenRepository#setScopes(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenRepository.setScopes(List)"})
  public void testSetScopes_given42_whenArrayListAdd42_thenUnknownRepositoryScopesIsArrayList() {
    // Arrange
    MavenRepository mavenRepository = MavenRepository.UnknownRepository;

    ArrayList<String> scopes = new ArrayList<>();
    scopes.add("42");
    scopes.add("foo");

    // Act
    mavenRepository.setScopes(scopes);

    // Assert
    assertEquals(scopes, mavenRepository.getScopes());
  }

  /**
   * Test {@link MavenRepository#setScopes(List)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>Then {@link MavenRepository#UnknownRepository} Scopes is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link MavenRepository#setScopes(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenRepository.setScopes(List)"})
  public void testSetScopes_givenFoo_thenUnknownRepositoryScopesIsArrayList() {
    // Arrange
    MavenRepository mavenRepository = MavenRepository.UnknownRepository;

    ArrayList<String> scopes = new ArrayList<>();
    scopes.add("foo");

    // Act
    mavenRepository.setScopes(scopes);

    // Assert
    assertEquals(scopes, mavenRepository.getScopes());
  }

  /**
   * Test {@link MavenRepository#setScopes(List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then {@link MavenRepository#UnknownRepository} Scopes Empty.
   * </ul>
   *
   * <p>Method under test: {@link MavenRepository#setScopes(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenRepository.setScopes(List)"})
  public void testSetScopes_whenArrayList_thenUnknownRepositoryScopesEmpty() {
    // Arrange
    MavenRepository mavenRepository = MavenRepository.UnknownRepository;

    // Act
    mavenRepository.setScopes(new ArrayList<>());

    // Assert
    assertTrue(mavenRepository.getScopes().isEmpty());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MavenRepository#setDescription(String)}
   *   <li>{@link MavenRepository#setEnabled(boolean)}
   *   <li>{@link MavenRepository#setId(String)}
   *   <li>{@link MavenRepository#setIsSnapshot(boolean)}
   *   <li>{@link MavenRepository#setName(String)}
   *   <li>{@link MavenRepository#setOrder(int)}
   *   <li>{@link MavenRepository#setUrl(String)}
   *   <li>{@link MavenRepository#getAuthInfo()}
   *   <li>{@link MavenRepository#getDescription()}
   *   <li>{@link MavenRepository#getId()}
   *   <li>{@link MavenRepository#getName()}
   *   <li>{@link MavenRepository#getOrder()}
   *   <li>{@link MavenRepository#getScopes()}
   *   <li>{@link MavenRepository#getType()}
   *   <li>{@link MavenRepository#getUrl()}
   *   <li>{@link MavenRepository#isEnabled()}
   *   <li>{@link MavenRepository#isSnapshot()}
   *   <li>{@link MavenRepository#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPAuthInfo MavenRepository.getAuthInfo()",
    "String MavenRepository.getDescription()",
    "String MavenRepository.getId()",
    "String MavenRepository.getName()",
    "int MavenRepository.getOrder()",
    "List MavenRepository.getScopes()",
    "RepositoryType MavenRepository.getType()",
    "String MavenRepository.getUrl()",
    "boolean MavenRepository.isEnabled()",
    "boolean MavenRepository.isSnapshot()",
    "void MavenRepository.setDescription(String)",
    "void MavenRepository.setEnabled(boolean)",
    "void MavenRepository.setId(String)",
    "void MavenRepository.setIsSnapshot(boolean)",
    "void MavenRepository.setName(String)",
    "void MavenRepository.setOrder(int)",
    "void MavenRepository.setUrl(String)",
    "String MavenRepository.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    MavenRepository mavenRepository =
        new MavenRepository("42", "Name", "https://example.org/example", RepositoryType.GLOBAL);

    // Act
    mavenRepository.setDescription("The characteristics of someone or something");
    mavenRepository.setEnabled(true);
    mavenRepository.setId("42");
    mavenRepository.setIsSnapshot(true);
    mavenRepository.setName("Name");
    mavenRepository.setOrder(1);
    mavenRepository.setUrl("https://example.org/example");
    DBPAuthInfo actualAuthInfo = mavenRepository.getAuthInfo();
    String actualDescription = mavenRepository.getDescription();
    String actualId = mavenRepository.getId();
    String actualName = mavenRepository.getName();
    int actualOrder = mavenRepository.getOrder();
    List<String> actualScopes = mavenRepository.getScopes();
    RepositoryType actualType = mavenRepository.getType();
    String actualUrl = mavenRepository.getUrl();
    boolean actualIsEnabledResult = mavenRepository.isEnabled();
    boolean actualIsSnapshotResult = mavenRepository.isSnapshot();

    // Assert
    assertEquals("42", actualId);
    assertEquals("Name", actualName);
    assertEquals("The characteristics of someone or something", actualDescription);
    assertEquals("https://example.org/example", actualUrl);
    assertEquals("https://example.org/example", mavenRepository.toString());
    assertNull(actualAuthInfo.getUserName());
    assertNull(actualAuthInfo.getUserPassword());
    assertEquals(1, actualOrder);
    assertEquals(RepositoryType.GLOBAL, actualType);
    assertFalse(actualAuthInfo.isSavePassword());
    assertTrue(actualScopes.isEmpty());
    assertTrue(actualIsEnabledResult);
    assertTrue(actualIsSnapshotResult);
  }

  /**
   * Test {@link MavenRepository#isSecureRepository()}.
   *
   * <p>Method under test: {@link MavenRepository#isSecureRepository()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MavenRepository.isSecureRepository()"})
  public void testIsSecureRepository() {
    // Arrange
    MavenRepository mavenRepository =
        new MavenRepository("42", "https", "https://example.org/example", RepositoryType.GLOBAL);

    // Act and Assert
    assertTrue(mavenRepository.isSecureRepository());
  }

  /**
   * Test {@link MavenRepository#isSecureRepository()}.
   *
   * <p>Method under test: {@link MavenRepository#isSecureRepository()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MavenRepository.isSecureRepository()"})
  public void testIsSecureRepository2() {
    // Arrange
    MavenRepository mavenRepository =
        new MavenRepository("42", "https", "https://example.org/example", RepositoryType.LOCAL);

    // Act and Assert
    assertTrue(mavenRepository.isSecureRepository());
  }

  /**
   * Test {@link MavenRepository#isSecureRepository()}.
   *
   * <p>Method under test: {@link MavenRepository#isSecureRepository()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MavenRepository.isSecureRepository()"})
  public void testIsSecureRepository3() {
    // Arrange
    MavenRepository mavenRepository =
        new MavenRepository("42", "https", "https://example.org/example", RepositoryType.CUSTOM);

    // Act and Assert
    assertTrue(mavenRepository.isSecureRepository());
  }

  /**
   * Test {@link MavenRepository#isSecureRepository()}.
   *
   * <ul>
   *   <li>Given {@link MavenRepository#UnknownRepository}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link MavenRepository#isSecureRepository()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MavenRepository.isSecureRepository()"})
  public void testIsSecureRepository_givenUnknownRepository_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(MavenRepository.UnknownRepository.isSecureRepository());
  }

  /**
   * Test {@link MavenRepository#findArtifact(DBRProgressMonitor, MavenArtifactReference)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link MavenRepository#findArtifact(DBRProgressMonitor,
   * MavenArtifactReference)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifactVersion MavenRepository.findArtifact(DBRProgressMonitor, MavenArtifactReference)"
  })
  public void testFindArtifact_givenEmptyString() {
    // Arrange
    TaskLoggingProgressMonitor original =
        new TaskLoggingProgressMonitor(new LoggingProgressMonitor(), mock(DBTTask.class));
    ProxyProgressMonitor monitor = new ProxyProgressMonitor(original);

    MavenArtifactReference ref = mock(MavenArtifactReference.class);
    when(ref.getVersion()).thenReturn("");
    when(ref.isResolveOptionalDependencies()).thenReturn(true);
    when(ref.getArtifactId()).thenReturn("42");
    when(ref.getClassifier()).thenReturn("Classifier");
    when(ref.getFallbackVersion()).thenReturn("1.0.2");
    when(ref.getGroupId()).thenReturn("42");
    when(ref.getId()).thenReturn("42");

    // Act
    MavenArtifactVersion actualFindArtifactResult =
        MavenRepository.UnknownRepository.findArtifact(monitor, ref);

    // Assert
    verify(ref).getArtifactId();
    verify(ref).getClassifier();
    verify(ref).getFallbackVersion();
    verify(ref).getGroupId();
    verify(ref).getId();
    verify(ref).getVersion();
    verify(ref).isResolveOptionalDependencies();
    assertNull(actualFindArtifactResult);
  }

  /**
   * Test {@link MavenRepository#findArtifact(DBRProgressMonitor, MavenArtifactReference)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link MavenArtifactReference} {@link MavenArtifactReference#getVersion()} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link MavenRepository#findArtifact(DBRProgressMonitor,
   * MavenArtifactReference)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifactVersion MavenRepository.findArtifact(DBRProgressMonitor, MavenArtifactReference)"
  })
  public void testFindArtifact_givenNull_whenMavenArtifactReferenceGetVersionReturnNull() {
    // Arrange
    TaskLoggingProgressMonitor original =
        new TaskLoggingProgressMonitor(new LoggingProgressMonitor(), mock(DBTTask.class));
    ProxyProgressMonitor monitor = new ProxyProgressMonitor(original);

    MavenArtifactReference ref = mock(MavenArtifactReference.class);
    when(ref.getVersion()).thenReturn(null);
    when(ref.isResolveOptionalDependencies()).thenReturn(true);
    when(ref.getArtifactId()).thenReturn("42");
    when(ref.getClassifier()).thenReturn("Classifier");
    when(ref.getFallbackVersion()).thenReturn("1.0.2");
    when(ref.getGroupId()).thenReturn("42");
    when(ref.getId()).thenReturn("42");

    // Act
    MavenArtifactVersion actualFindArtifactResult =
        MavenRepository.UnknownRepository.findArtifact(monitor, ref);

    // Assert
    verify(ref).getArtifactId();
    verify(ref).getClassifier();
    verify(ref).getFallbackVersion();
    verify(ref).getGroupId();
    verify(ref).getId();
    verify(ref).getVersion();
    verify(ref).isResolveOptionalDependencies();
    assertNull(actualFindArtifactResult);
  }

  /**
   * Test {@link MavenRepository#findArtifact(DBRProgressMonitor, MavenArtifactReference)}.
   *
   * <ul>
   *   <li>When {@link MavenArtifactReference} {@link MavenArtifactReference#getArtifactId()} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link MavenRepository#findArtifact(DBRProgressMonitor,
   * MavenArtifactReference)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifactVersion MavenRepository.findArtifact(DBRProgressMonitor, MavenArtifactReference)"
  })
  public void testFindArtifact_whenMavenArtifactReferenceGetArtifactIdReturnNull() {
    // Arrange
    TaskLoggingProgressMonitor original =
        new TaskLoggingProgressMonitor(new LoggingProgressMonitor(), mock(DBTTask.class));
    ProxyProgressMonitor monitor = new ProxyProgressMonitor(original);

    MavenArtifactReference ref = mock(MavenArtifactReference.class);
    when(ref.getVersion()).thenReturn("");
    when(ref.isResolveOptionalDependencies()).thenReturn(true);
    when(ref.getArtifactId()).thenReturn(null);
    when(ref.getClassifier()).thenReturn("Classifier");
    when(ref.getFallbackVersion()).thenReturn("1.0.2");
    when(ref.getGroupId()).thenReturn("42");
    when(ref.getId()).thenReturn("42");

    // Act
    MavenArtifactVersion actualFindArtifactResult =
        MavenRepository.UnknownRepository.findArtifact(monitor, ref);

    // Assert
    verify(ref).getArtifactId();
    verify(ref).getClassifier();
    verify(ref).getFallbackVersion();
    verify(ref).getGroupId();
    verify(ref).getId();
    verify(ref).getVersion();
    verify(ref).isResolveOptionalDependencies();
    assertNull(actualFindArtifactResult);
  }

  /**
   * Test {@link MavenRepository#findArtifact(DBRProgressMonitor, MavenArtifactReference)}.
   *
   * <ul>
   *   <li>When {@link MavenArtifactReference} {@link MavenArtifactReference#getClassifier()} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link MavenRepository#findArtifact(DBRProgressMonitor,
   * MavenArtifactReference)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifactVersion MavenRepository.findArtifact(DBRProgressMonitor, MavenArtifactReference)"
  })
  public void testFindArtifact_whenMavenArtifactReferenceGetClassifierReturnNull() {
    // Arrange
    TaskLoggingProgressMonitor original =
        new TaskLoggingProgressMonitor(new LoggingProgressMonitor(), mock(DBTTask.class));
    ProxyProgressMonitor monitor = new ProxyProgressMonitor(original);

    MavenArtifactReference ref = mock(MavenArtifactReference.class);
    when(ref.getVersion()).thenReturn("");
    when(ref.isResolveOptionalDependencies()).thenReturn(true);
    when(ref.getArtifactId()).thenReturn("42");
    when(ref.getClassifier()).thenReturn(null);
    when(ref.getFallbackVersion()).thenReturn("1.0.2");
    when(ref.getGroupId()).thenReturn("42");
    when(ref.getId()).thenReturn("42");

    // Act
    MavenArtifactVersion actualFindArtifactResult =
        MavenRepository.UnknownRepository.findArtifact(monitor, ref);

    // Assert
    verify(ref).getArtifactId();
    verify(ref).getClassifier();
    verify(ref).getFallbackVersion();
    verify(ref).getGroupId();
    verify(ref).getId();
    verify(ref).getVersion();
    verify(ref).isResolveOptionalDependencies();
    assertNull(actualFindArtifactResult);
  }

  /**
   * Test {@link MavenRepository#findArtifact(DBRProgressMonitor, MavenArtifactReference)}.
   *
   * <ul>
   *   <li>When {@link MavenArtifactReference} {@link MavenArtifactReference#getFallbackVersion()}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link MavenRepository#findArtifact(DBRProgressMonitor,
   * MavenArtifactReference)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifactVersion MavenRepository.findArtifact(DBRProgressMonitor, MavenArtifactReference)"
  })
  public void testFindArtifact_whenMavenArtifactReferenceGetFallbackVersionReturnNull() {
    // Arrange
    TaskLoggingProgressMonitor original =
        new TaskLoggingProgressMonitor(new LoggingProgressMonitor(), mock(DBTTask.class));
    ProxyProgressMonitor monitor = new ProxyProgressMonitor(original);

    MavenArtifactReference ref = mock(MavenArtifactReference.class);
    when(ref.getVersion()).thenReturn("");
    when(ref.isResolveOptionalDependencies()).thenReturn(true);
    when(ref.getArtifactId()).thenReturn("42");
    when(ref.getClassifier()).thenReturn("Classifier");
    when(ref.getFallbackVersion()).thenReturn(null);
    when(ref.getGroupId()).thenReturn("42");
    when(ref.getId()).thenReturn("42");

    // Act
    MavenArtifactVersion actualFindArtifactResult =
        MavenRepository.UnknownRepository.findArtifact(monitor, ref);

    // Assert
    verify(ref).getArtifactId();
    verify(ref).getClassifier();
    verify(ref).getFallbackVersion();
    verify(ref).getGroupId();
    verify(ref).getId();
    verify(ref).getVersion();
    verify(ref).isResolveOptionalDependencies();
    assertNull(actualFindArtifactResult);
  }

  /**
   * Test {@link MavenRepository#resetArtifactCache(MavenArtifactReference)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link MavenArtifactDependency#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link MavenRepository#resetArtifactCache(MavenArtifactReference)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenRepository.resetArtifactCache(MavenArtifactReference)"})
  public void testResetArtifactCache_given42_thenCallsGetId() {
    // Arrange
    MavenArtifactDependency artifactReference = mock(MavenArtifactDependency.class);
    when(artifactReference.getId()).thenReturn("42");

    // Act
    MavenRepository.UnknownRepository.resetArtifactCache(artifactReference);

    // Assert
    verify(artifactReference).getId();
  }
}
