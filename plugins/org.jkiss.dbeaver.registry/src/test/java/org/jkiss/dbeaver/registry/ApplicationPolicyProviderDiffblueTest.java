package org.jkiss.dbeaver.registry;

import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ApplicationPolicyProviderDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link ApplicationPolicyProvider}
   *   <li>{@link ApplicationPolicyProvider#getInstance()}
   *   <li>{@link ApplicationPolicyProvider#setInstance(ApplicationPolicyProvider)}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ApplicationPolicyProvider.<init>()",
    "ApplicationPolicyProvider ApplicationPolicyProvider.getInstance()",
    "void ApplicationPolicyProvider.setInstance(ApplicationPolicyProvider)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    ApplicationPolicyProvider actualApplicationPolicyProvider = new ApplicationPolicyProvider();
    ApplicationPolicyProvider actualInstance = actualApplicationPolicyProvider.getInstance();
    ApplicationPolicyProvider instance = ApplicationPolicyProvider.getInstance();
    actualApplicationPolicyProvider.setInstance(instance);

    // Assert
    assertSame(instance, actualInstance);
  }
}
