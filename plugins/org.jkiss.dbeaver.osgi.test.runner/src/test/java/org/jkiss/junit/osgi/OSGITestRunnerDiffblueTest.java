package org.jkiss.junit.osgi;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.junit.osgi.behaviors.IAsyncApplication;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.model.InvalidTestClassError;

public class OSGITestRunnerDiffblueTest {
  /**
   * Test {@link OSGITestRunner#OSGITestRunner(Class)}.
   *
   * <ul>
   *   <li>When {@code IAsyncApplication}.
   * </ul>
   *
   * <p>Method under test: {@link OSGITestRunner#OSGITestRunner(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OSGITestRunner.<init>(Class)"})
  public void testNewOSGITestRunner_whenOrgJkissJunitOsgiBehaviorsIAsyncApplication()
      throws Exception {
    // Arrange
    Class<IAsyncApplication> testClass = IAsyncApplication.class;

    // Act and Assert
    assertThrows(InvalidTestClassError.class, () -> new OSGITestRunner(testClass));
  }
}
