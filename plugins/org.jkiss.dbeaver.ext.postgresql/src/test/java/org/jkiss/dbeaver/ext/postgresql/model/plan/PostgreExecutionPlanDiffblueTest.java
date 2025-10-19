package org.jkiss.dbeaver.ext.postgresql.model.plan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jkiss.dbeaver.model.exec.plan.DBCQueryPlannerConfiguration;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostgreExecutionPlanDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PostgreExecutionPlan#PostgreExecutionPlan(boolean, boolean, String,
   *       DBCQueryPlannerConfiguration)}
   *   <li>{@link PostgreExecutionPlan#getQueryString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PostgreExecutionPlan.<init>(boolean, boolean, String, DBCQueryPlannerConfiguration)",
    "String PostgreExecutionPlan.getQueryString()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(
        "Query",
        new PostgreExecutionPlan(true, true, "Query", new DBCQueryPlannerConfiguration())
            .getQueryString());
  }

  /**
   * Test {@link PostgreExecutionPlan#PostgreExecutionPlan(String, List)}.
   *
   * <p>Method under test: {@link PostgreExecutionPlan#PostgreExecutionPlan(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostgreExecutionPlan.<init>(String, List)"})
  public void testNewPostgreExecutionPlan() {
    // Arrange and Act
    PostgreExecutionPlan actualPostgreExecutionPlan =
        new PostgreExecutionPlan("Query", new ArrayList<>());

    // Assert
    assertEquals("EXPLAIN (FORMAT XML) Query", actualPostgreExecutionPlan.getPlanQueryString());
    assertEquals("Query", actualPostgreExecutionPlan.getQueryString());
  }

  /**
   * Test {@link PostgreExecutionPlan#getPlanFeature(String)}.
   *
   * <ul>
   *   <li>When {@code Feature}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreExecutionPlan#getPlanFeature(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object PostgreExecutionPlan.getPlanFeature(String)"})
  public void testGetPlanFeature_whenFeature_thenReturnNull() {
    // Arrange
    PostgreExecutionPlan postgreExecutionPlan =
        new PostgreExecutionPlan("Query", new ArrayList<>());

    // Act and Assert
    assertNull(postgreExecutionPlan.getPlanFeature("Feature"));
  }

  /**
   * Test {@link PostgreExecutionPlan#getPlanFeature(String)}.
   *
   * <ul>
   *   <li>When {@code plan.cost}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreExecutionPlan#getPlanFeature(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object PostgreExecutionPlan.getPlanFeature(String)"})
  public void testGetPlanFeature_whenPlanCost_thenReturnTrue() {
    // Arrange
    PostgreExecutionPlan postgreExecutionPlan =
        new PostgreExecutionPlan("Query", new ArrayList<>());

    // Act and Assert
    assertTrue((Boolean) postgreExecutionPlan.getPlanFeature("plan.cost"));
  }

  /**
   * Test {@link PostgreExecutionPlan#getPlanFeature(String)}.
   *
   * <ul>
   *   <li>When {@code plan.duration.measure}.
   *   <li>Then return {@code ms}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreExecutionPlan#getPlanFeature(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object PostgreExecutionPlan.getPlanFeature(String)"})
  public void testGetPlanFeature_whenPlanDurationMeasure_thenReturnMs() {
    // Arrange
    PostgreExecutionPlan postgreExecutionPlan =
        new PostgreExecutionPlan("Query", new ArrayList<>());

    // Act and Assert
    assertEquals("ms", postgreExecutionPlan.getPlanFeature("plan.duration.measure"));
  }

  /**
   * Test {@link PostgreExecutionPlan#getPlanFeature(String)}.
   *
   * <ul>
   *   <li>When {@code plan.duration}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreExecutionPlan#getPlanFeature(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object PostgreExecutionPlan.getPlanFeature(String)"})
  public void testGetPlanFeature_whenPlanDuration_thenReturnTrue() {
    // Arrange
    PostgreExecutionPlan postgreExecutionPlan =
        new PostgreExecutionPlan("Query", new ArrayList<>());

    // Act and Assert
    assertTrue((Boolean) postgreExecutionPlan.getPlanFeature("plan.duration"));
  }

  /**
   * Test {@link PostgreExecutionPlan#getPlanFeature(String)}.
   *
   * <ul>
   *   <li>When {@code plan.rows}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreExecutionPlan#getPlanFeature(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object PostgreExecutionPlan.getPlanFeature(String)"})
  public void testGetPlanFeature_whenPlanRows_thenReturnTrue() {
    // Arrange
    PostgreExecutionPlan postgreExecutionPlan =
        new PostgreExecutionPlan("Query", new ArrayList<>());

    // Act and Assert
    assertTrue((Boolean) postgreExecutionPlan.getPlanFeature("plan.rows"));
  }

  /**
   * Test {@link PostgreExecutionPlan#getPlanQueryString()}.
   *
   * <ul>
   *   <li>Then return {@code EXPLAIN EXPLAIN (FORMAT XML}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreExecutionPlan#getPlanQueryString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PostgreExecutionPlan.getPlanQueryString()"})
  public void testGetPlanQueryString_thenReturnExplainExplainFormatXml() {
    // Arrange, Act and Assert
    assertEquals(
        "EXPLAIN EXPLAIN (FORMAT XML",
        new PostgreExecutionPlan(
                true, false, "EXPLAIN (FORMAT XML", new DBCQueryPlannerConfiguration())
            .getPlanQueryString());
  }

  /**
   * Test {@link PostgreExecutionPlan#getPlanQueryString()}.
   *
   * <ul>
   *   <li>Then return {@code EXPLAIN (FORMAT XML) Query}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreExecutionPlan#getPlanQueryString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PostgreExecutionPlan.getPlanQueryString()"})
  public void testGetPlanQueryString_thenReturnExplainFormatXmlQuery() {
    // Arrange
    PostgreExecutionPlan postgreExecutionPlan =
        new PostgreExecutionPlan("Query", new ArrayList<>());

    // Act and Assert
    assertEquals("EXPLAIN (FORMAT XML) Query", postgreExecutionPlan.getPlanQueryString());
  }

  /**
   * Test {@link PostgreExecutionPlan#getPlanQueryString()}.
   *
   * <ul>
   *   <li>Then return {@code EXPLAIN VERBOSE EXPLAIN (FORMAT XML}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreExecutionPlan#getPlanQueryString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PostgreExecutionPlan.getPlanQueryString()"})
  public void testGetPlanQueryString_thenReturnExplainVerboseExplainFormatXml() {
    // Arrange, Act and Assert
    assertEquals(
        "EXPLAIN VERBOSE EXPLAIN (FORMAT XML",
        new PostgreExecutionPlan(
                true, true, "EXPLAIN (FORMAT XML", new DBCQueryPlannerConfiguration())
            .getPlanQueryString());
  }

  /**
   * Test {@link PostgreExecutionPlan#getPlanNodes(Map)}.
   *
   * <p>Method under test: {@link PostgreExecutionPlan#getPlanNodes(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List PostgreExecutionPlan.getPlanNodes(Map)"})
  public void testGetPlanNodes() {
    // Arrange
    PostgreExecutionPlan postgreExecutionPlan =
        new PostgreExecutionPlan("Query", new ArrayList<>());

    // Act and Assert
    assertTrue(postgreExecutionPlan.getPlanNodes(new HashMap<>()).isEmpty());
  }
}
