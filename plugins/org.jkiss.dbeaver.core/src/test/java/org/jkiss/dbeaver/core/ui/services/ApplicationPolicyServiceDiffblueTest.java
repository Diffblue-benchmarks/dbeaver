package org.jkiss.dbeaver.core.ui.services;

import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ApplicationPolicyServiceDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Method under test: {@link ApplicationPolicyService#getInstance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ApplicationPolicyService ApplicationPolicyService.getInstance()"})
  public void testGettersAndSetters() {
    // Arrange and Act
    ApplicationPolicyService actualInstance = ApplicationPolicyService.getInstance();
    ApplicationPolicyService actualInstance2 = actualInstance.getInstance();

    // Assert
    assertSame(actualInstance, actualInstance2);
  }
}
