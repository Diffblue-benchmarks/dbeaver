package org.jkiss.dbeaver.ext.hsqldb.model.plan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class HSQLExecutionPlanDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link HSQLExecutionPlan#HSQLExecutionPlan(String)}
   *   <li>{@link HSQLExecutionPlan#getQueryString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HSQLExecutionPlan.<init>(String)",
    "String HSQLExecutionPlan.getQueryString()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("Query", new HSQLExecutionPlan("Query").getQueryString());
  }

  /**
   * Test {@link HSQLExecutionPlan#getPlanQueryString()}.
   *
   * <p>Method under test: {@link HSQLExecutionPlan#getPlanQueryString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String HSQLExecutionPlan.getPlanQueryString()"})
  public void testGetPlanQueryString() {
    // Arrange, Act and Assert
    assertEquals("EXPLAIN PLAN FOR Query", new HSQLExecutionPlan("Query").getPlanQueryString());
  }

  /**
   * Test {@link HSQLExecutionPlan#getPlanNodes(Map)}.
   *
   * <p>Method under test: {@link HSQLExecutionPlan#getPlanNodes(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.List HSQLExecutionPlan.getPlanNodes(Map)"})
  public void testGetPlanNodes() {
    // Arrange
    HSQLExecutionPlan hsqlExecutionPlan = new HSQLExecutionPlan("Query");

    // Act and Assert
    assertTrue(hsqlExecutionPlan.getPlanNodes(new HashMap<>()).isEmpty());
  }
}
