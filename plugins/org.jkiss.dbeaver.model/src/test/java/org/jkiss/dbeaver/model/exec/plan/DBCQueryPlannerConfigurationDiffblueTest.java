package org.jkiss.dbeaver.model.exec.plan;

import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBCQueryPlannerConfigurationDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DBCQueryPlannerConfiguration}
   *   <li>{@link DBCQueryPlannerConfiguration#getParameters()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBCQueryPlannerConfiguration.<init>()",
    "java.util.Map DBCQueryPlannerConfiguration.getParameters()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertTrue(new DBCQueryPlannerConfiguration().getParameters().isEmpty());
  }
}
