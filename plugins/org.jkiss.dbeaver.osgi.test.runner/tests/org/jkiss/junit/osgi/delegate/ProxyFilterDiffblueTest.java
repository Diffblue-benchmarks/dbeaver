package org.jkiss.junit.osgi.delegate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ProxyFilterDiffblueTest {
  /**
   * Test {@link ProxyFilter#ProxyFilter(Object)}.
   *
   * <p>Method under test: {@link ProxyFilter#ProxyFilter(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProxyFilter.<init>(Object)"})
  public void testNewProxyFilter() {
    // Arrange, Act and Assert
    assertEquals("Filter", new ProxyFilter("Filter").filter);
  }

  /**
   * Test {@link ProxyFilter#describe()}.
   *
   * <ul>
   *   <li>Given {@link ProxyFilter#ProxyFilter(Object)} with {@code Filter}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyFilter#describe()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String ProxyFilter.describe()"})
  public void testDescribe_givenProxyFilterWithFilter_thenThrowRuntimeException() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> new ProxyFilter("Filter").describe());
  }
}
