package org.jkiss.dbeaver.runtime.net;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.ProxySelector;
import java.net.URI;
import java.nio.file.Paths;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GlobalProxySelectorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link GlobalProxySelector#GlobalProxySelector(ProxySelector)}
   *   <li>{@link GlobalProxySelector#getParent()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void GlobalProxySelector.<init>(ProxySelector)",
    "ProxySelector GlobalProxySelector.getParent()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull(new GlobalProxySelector(null).getParent());
  }

  /**
   * Test {@link GlobalProxySelector#getProxiesForDataSource(URI, DBPDataSourceContainer)}.
   *
   * <p>Method under test: {@link GlobalProxySelector#getProxiesForDataSource(URI,
   * DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.util.List GlobalProxySelector.getProxiesForDataSource(URI, DBPDataSourceContainer)"
  })
  public void testGetProxiesForDataSource() {
    // Arrange, Act and Assert
    assertNull(
        new GlobalProxySelector(null)
            .getProxiesForDataSource(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(),
                mock(DBPDataSourceContainer.class)));
  }

  /**
   * Test {@link GlobalProxySelector#getActiveDataSourceContainer(URI)}.
   *
   * <p>Method under test: {@link GlobalProxySelector#getActiveDataSourceContainer(URI)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPDataSourceContainer GlobalProxySelector.getActiveDataSourceContainer(URI)"
  })
  public void testGetActiveDataSourceContainer() {
    // Arrange and Act
    DBPDataSourceContainer actualActiveDataSourceContainer =
        new GlobalProxySelector(null)
            .getActiveDataSourceContainer(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    assertNull(actualActiveDataSourceContainer);
  }
}
