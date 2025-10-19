package org.jkiss.dbeaver.model.dashboard.registry;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.internal.registry.RegistryObjectManager;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.jkiss.dbeaver.model.dashboard.registry.DashboardItemConfiguration.QueryMapping;
import org.jkiss.utils.xml.XMLBuilder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DashboardItemConfigurationDiffblueTest {
  /**
   * Test QueryMapping getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link QueryMapping#QueryMapping(String)}
   *   <li>{@link QueryMapping#getQueryText()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QueryMapping.<init>(String)", "String QueryMapping.getQueryText()"})
  public void testQueryMappingGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("Query Text", new QueryMapping("Query Text").getQueryText());
  }

  /**
   * Test QueryMapping {@link QueryMapping#QueryMapping(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return QueryText is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link QueryMapping#QueryMapping(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QueryMapping.<init>(IConfigurationElement)"})
  public void testQueryMappingNewQueryMapping_thenReturnQueryTextIs42() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, "Master Token", "User Token");
    new RegistryObjectManager(registry);

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getValue()).thenReturn("42");

    // Act
    String actualQueryText = new QueryMapping(config).getQueryText();

    // Assert
    verify(config).getValue();
    assertEquals("42", actualQueryText);
  }

  /**
   * Test QueryMapping {@link QueryMapping#serialize(XMLBuilder)}.
   *
   * <ul>
   *   <li>Then calls {@link XMLBuilder#addText(CharSequence)}.
   * </ul>
   *
   * <p>Method under test: {@link QueryMapping#serialize(XMLBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QueryMapping.serialize(XMLBuilder)"})
  public void testQueryMappingSerialize_thenCallsAddText() throws IOException {
    // Arrange
    QueryMapping queryMapping = new QueryMapping("Query Text");

    XMLBuilder xml = mock(XMLBuilder.class);
    when(xml.addText(Mockito.<CharSequence>any()))
        .thenReturn(new XMLBuilder(new ByteArrayOutputStream(), "UTF-8"));

    // Act
    queryMapping.serialize(xml);

    // Assert
    verify(xml).addText(isA(CharSequence.class));
  }
}
