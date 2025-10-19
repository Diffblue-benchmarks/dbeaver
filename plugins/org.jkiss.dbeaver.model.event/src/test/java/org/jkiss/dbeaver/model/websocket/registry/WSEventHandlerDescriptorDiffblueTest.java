package org.jkiss.dbeaver.model.websocket.registry;

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
import java.util.List;
import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.internal.registry.RegistryObjectManager;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.jkiss.dbeaver.model.websocket.event.WSEventController;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class WSEventHandlerDescriptorDiffblueTest {
  /**
   * Test {@link WSEventHandlerDescriptor#WSEventHandlerDescriptor(IConfigurationElement)}.
   *
   * <p>Method under test: {@link
   * WSEventHandlerDescriptor#WSEventHandlerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSEventHandlerDescriptor.<init>(IConfigurationElement)"})
  public void testNewWSEventHandlerDescriptor() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(
            registryStrategy,
            WSEventController.JOB_EVENT_HANDLER_FAMILY,
            WSEventController.JOB_EVENT_HANDLER_FAMILY);
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenThrow(new IllegalStateException());

    ConfigurationElementHandle contributorConfig = mock(ConfigurationElementHandle.class);
    when(contributorConfig.getContributor()).thenReturn(iContributor);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new WSEventHandlerDescriptor(contributorConfig));
    verify(contributorConfig).getContributor();
    verify(iContributor).getName();
  }

  /**
   * Test {@link WSEventHandlerDescriptor#WSEventHandlerDescriptor(IConfigurationElement)}.
   *
   * <p>Method under test: {@link
   * WSEventHandlerDescriptor#WSEventHandlerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSEventHandlerDescriptor.<init>(IConfigurationElement)"})
  public void testNewWSEventHandlerDescriptor2() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(
            registryStrategy,
            WSEventController.JOB_EVENT_HANDLER_FAMILY,
            WSEventController.JOB_EVENT_HANDLER_FAMILY);
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle contributorConfig = mock(ConfigurationElementHandle.class);
    when(contributorConfig.getAttribute(Mockito.<String>any())).thenReturn(null);
    when(contributorConfig.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    when(contributorConfig.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(
            registryStrategy2,
            WSEventController.JOB_EVENT_HANDLER_FAMILY,
            WSEventController.JOB_EVENT_HANDLER_FAMILY);
    new RegistryObjectManager(registry2);

    // Act
    WSEventHandlerDescriptor actualWsEventHandlerDescriptor =
        new WSEventHandlerDescriptor(contributorConfig);

    // Assert
    verify(contributorConfig, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle).getAttribute("id");
    verify(contributorConfig).getChildren("topic");
    verify(contributorConfig).getContributor();
    verify(iContributor).getName();
    List<String> supportedTopics = actualWsEventHandlerDescriptor.getSupportedTopics();
    assertEquals(1, supportedTopics.size());
    assertEquals("Attribute", supportedTopics.get(0));
    assertEquals("Name", actualWsEventHandlerDescriptor.getPluginId());
  }

  /**
   * Test {@link WSEventHandlerDescriptor#WSEventHandlerDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link
   * WSEventHandlerDescriptor#WSEventHandlerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSEventHandlerDescriptor.<init>(IConfigurationElement)"})
  public void testNewWSEventHandlerDescriptor_givenAttribute() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(
            registryStrategy,
            WSEventController.JOB_EVENT_HANDLER_FAMILY,
            WSEventController.JOB_EVENT_HANDLER_FAMILY);
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle contributorConfig = mock(ConfigurationElementHandle.class);
    when(contributorConfig.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(contributorConfig.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    when(contributorConfig.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(
            registryStrategy2,
            WSEventController.JOB_EVENT_HANDLER_FAMILY,
            WSEventController.JOB_EVENT_HANDLER_FAMILY);
    new RegistryObjectManager(registry2);

    // Act
    WSEventHandlerDescriptor actualWsEventHandlerDescriptor =
        new WSEventHandlerDescriptor(contributorConfig);

    // Assert
    verify(contributorConfig, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle).getAttribute("id");
    verify(contributorConfig).getChildren("topic");
    verify(contributorConfig).getContributor();
    verify(iContributor).getName();
    List<String> supportedTopics = actualWsEventHandlerDescriptor.getSupportedTopics();
    assertEquals(1, supportedTopics.size());
    assertEquals("Attribute", supportedTopics.get(0));
    assertEquals("Name", actualWsEventHandlerDescriptor.getPluginId());
  }

  /**
   * Test {@link WSEventHandlerDescriptor#WSEventHandlerDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link
   * WSEventHandlerDescriptor#WSEventHandlerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSEventHandlerDescriptor.<init>(IConfigurationElement)"})
  public void testNewWSEventHandlerDescriptor_givenEmptyString() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(
            registryStrategy,
            WSEventController.JOB_EVENT_HANDLER_FAMILY,
            WSEventController.JOB_EVENT_HANDLER_FAMILY);
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle contributorConfig = mock(ConfigurationElementHandle.class);
    when(contributorConfig.getAttribute(Mockito.<String>any())).thenReturn("");
    when(contributorConfig.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    when(contributorConfig.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(
            registryStrategy2,
            WSEventController.JOB_EVENT_HANDLER_FAMILY,
            WSEventController.JOB_EVENT_HANDLER_FAMILY);
    new RegistryObjectManager(registry2);

    // Act
    WSEventHandlerDescriptor actualWsEventHandlerDescriptor =
        new WSEventHandlerDescriptor(contributorConfig);

    // Assert
    verify(contributorConfig, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle).getAttribute("id");
    verify(contributorConfig).getChildren("topic");
    verify(contributorConfig).getContributor();
    verify(iContributor).getName();
    List<String> supportedTopics = actualWsEventHandlerDescriptor.getSupportedTopics();
    assertEquals(1, supportedTopics.size());
    assertEquals("Attribute", supportedTopics.get(0));
    assertEquals("Name", actualWsEventHandlerDescriptor.getPluginId());
  }

  /**
   * Test {@link WSEventHandlerDescriptor#WSEventHandlerDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code if}.
   * </ul>
   *
   * <p>Method under test: {@link
   * WSEventHandlerDescriptor#WSEventHandlerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSEventHandlerDescriptor.<init>(IConfigurationElement)"})
  public void testNewWSEventHandlerDescriptor_givenIf() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(
            registryStrategy,
            WSEventController.JOB_EVENT_HANDLER_FAMILY,
            WSEventController.JOB_EVENT_HANDLER_FAMILY);
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle contributorConfig = mock(ConfigurationElementHandle.class);
    when(contributorConfig.getAttribute(Mockito.<String>any())).thenReturn("if");
    when(contributorConfig.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    when(contributorConfig.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(
            registryStrategy2,
            WSEventController.JOB_EVENT_HANDLER_FAMILY,
            WSEventController.JOB_EVENT_HANDLER_FAMILY);
    new RegistryObjectManager(registry2);

    // Act
    WSEventHandlerDescriptor actualWsEventHandlerDescriptor =
        new WSEventHandlerDescriptor(contributorConfig);

    // Assert
    verify(contributorConfig, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle).getAttribute("id");
    verify(contributorConfig).getChildren("topic");
    verify(contributorConfig).getContributor();
    verify(iContributor).getName();
    List<String> supportedTopics = actualWsEventHandlerDescriptor.getSupportedTopics();
    assertEquals(1, supportedTopics.size());
    assertEquals("Attribute", supportedTopics.get(0));
    assertEquals("Name", actualWsEventHandlerDescriptor.getPluginId());
  }

  /**
   * Test {@link WSEventHandlerDescriptor#WSEventHandlerDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@link IllegalStateException#IllegalStateException()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * WSEventHandlerDescriptor#WSEventHandlerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSEventHandlerDescriptor.<init>(IConfigurationElement)"})
  public void testNewWSEventHandlerDescriptor_givenIllegalStateException() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(
            registryStrategy,
            WSEventController.JOB_EVENT_HANDLER_FAMILY,
            WSEventController.JOB_EVENT_HANDLER_FAMILY);
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle contributorConfig = mock(ConfigurationElementHandle.class);
    when(contributorConfig.getAttribute(Mockito.<String>any()))
        .thenThrow(new IllegalStateException());
    when(contributorConfig.getContributor()).thenReturn(iContributor);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new WSEventHandlerDescriptor(contributorConfig));
    verify(contributorConfig).getAttribute("class");
    verify(contributorConfig).getContributor();
    verify(iContributor).getName();
  }

  /**
   * Test {@link WSEventHandlerDescriptor#WSEventHandlerDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code topic}.
   * </ul>
   *
   * <p>Method under test: {@link
   * WSEventHandlerDescriptor#WSEventHandlerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSEventHandlerDescriptor.<init>(IConfigurationElement)"})
  public void testNewWSEventHandlerDescriptor_givenTopic() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(
            registryStrategy,
            WSEventController.JOB_EVENT_HANDLER_FAMILY,
            WSEventController.JOB_EVENT_HANDLER_FAMILY);
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle contributorConfig = mock(ConfigurationElementHandle.class);
    when(contributorConfig.getAttribute(Mockito.<String>any())).thenReturn("topic");
    when(contributorConfig.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    when(contributorConfig.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(
            registryStrategy2,
            WSEventController.JOB_EVENT_HANDLER_FAMILY,
            WSEventController.JOB_EVENT_HANDLER_FAMILY);
    new RegistryObjectManager(registry2);

    // Act
    WSEventHandlerDescriptor actualWsEventHandlerDescriptor =
        new WSEventHandlerDescriptor(contributorConfig);

    // Assert
    verify(contributorConfig, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle).getAttribute("id");
    verify(contributorConfig).getChildren("topic");
    verify(contributorConfig).getContributor();
    verify(iContributor).getName();
    List<String> supportedTopics = actualWsEventHandlerDescriptor.getSupportedTopics();
    assertEquals(1, supportedTopics.size());
    assertEquals("Attribute", supportedTopics.get(0));
    assertEquals("Name", actualWsEventHandlerDescriptor.getPluginId());
  }

  /**
   * Test {@link WSEventHandlerDescriptor#WSEventHandlerDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return SupportedTopics Empty.
   * </ul>
   *
   * <p>Method under test: {@link
   * WSEventHandlerDescriptor#WSEventHandlerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSEventHandlerDescriptor.<init>(IConfigurationElement)"})
  public void testNewWSEventHandlerDescriptor_thenReturnSupportedTopicsEmpty() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(
            registryStrategy,
            WSEventController.JOB_EVENT_HANDLER_FAMILY,
            WSEventController.JOB_EVENT_HANDLER_FAMILY);
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn(null);

    ConfigurationElementHandle contributorConfig = mock(ConfigurationElementHandle.class);
    when(contributorConfig.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(contributorConfig.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    when(contributorConfig.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(
            registryStrategy2,
            WSEventController.JOB_EVENT_HANDLER_FAMILY,
            WSEventController.JOB_EVENT_HANDLER_FAMILY);
    new RegistryObjectManager(registry2);

    // Act
    WSEventHandlerDescriptor actualWsEventHandlerDescriptor =
        new WSEventHandlerDescriptor(contributorConfig);

    // Assert
    verify(contributorConfig, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle).getAttribute("id");
    verify(contributorConfig).getChildren("topic");
    verify(contributorConfig).getContributor();
    verify(iContributor).getName();
    assertEquals("Name", actualWsEventHandlerDescriptor.getPluginId());
    assertTrue(actualWsEventHandlerDescriptor.getSupportedTopics().isEmpty());
  }
}
