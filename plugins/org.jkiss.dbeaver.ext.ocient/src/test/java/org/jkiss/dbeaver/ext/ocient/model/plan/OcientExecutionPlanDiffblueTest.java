package org.jkiss.dbeaver.ext.ocient.model.plan;

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
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OcientExecutionPlanDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link OcientExecutionPlan#OcientExecutionPlan(String, List)}
   *   <li>{@link OcientExecutionPlan#getQueryString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OcientExecutionPlan.<init>(String)",
    "void OcientExecutionPlan.<init>(String, List)",
    "String OcientExecutionPlan.getQueryString()"
  })
  public void testGettersAndSetters_whenArrayList() {
    // Arrange and Act
    OcientExecutionPlan actualOcientExecutionPlan =
        new OcientExecutionPlan("Query", new ArrayList<>());

    // Assert
    assertEquals("Query", actualOcientExecutionPlan.getQueryString());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Query}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link OcientExecutionPlan#OcientExecutionPlan(String)}
   *   <li>{@link OcientExecutionPlan#getQueryString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OcientExecutionPlan.<init>(String)",
    "void OcientExecutionPlan.<init>(String, List)",
    "String OcientExecutionPlan.getQueryString()"
  })
  public void testGettersAndSetters_whenQuery() {
    // Arrange, Act and Assert
    assertEquals("Query", new OcientExecutionPlan("Query").getQueryString());
  }

  /**
   * Test {@link OcientExecutionPlan#getPlanFeature(String)}.
   *
   * <ul>
   *   <li>When {@code Feature}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OcientExecutionPlan#getPlanFeature(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object OcientExecutionPlan.getPlanFeature(String)"})
  public void testGetPlanFeature_whenFeature_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new OcientExecutionPlan("Query").getPlanFeature("Feature"));
  }

  /**
   * Test {@link OcientExecutionPlan#getPlanFeature(String)}.
   *
   * <ul>
   *   <li>When {@code plan.cost}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link OcientExecutionPlan#getPlanFeature(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object OcientExecutionPlan.getPlanFeature(String)"})
  public void testGetPlanFeature_whenPlanCost_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((Boolean) new OcientExecutionPlan("Query").getPlanFeature("plan.cost"));
  }

  /**
   * Test {@link OcientExecutionPlan#getPlanFeature(String)}.
   *
   * <ul>
   *   <li>When {@code plan.duration.measure}.
   *   <li>Then return {@code ms}.
   * </ul>
   *
   * <p>Method under test: {@link OcientExecutionPlan#getPlanFeature(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object OcientExecutionPlan.getPlanFeature(String)"})
  public void testGetPlanFeature_whenPlanDurationMeasure_thenReturnMs() {
    // Arrange, Act and Assert
    assertEquals("ms", new OcientExecutionPlan("Query").getPlanFeature("plan.duration.measure"));
  }

  /**
   * Test {@link OcientExecutionPlan#getPlanFeature(String)}.
   *
   * <ul>
   *   <li>When {@code plan.duration}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link OcientExecutionPlan#getPlanFeature(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object OcientExecutionPlan.getPlanFeature(String)"})
  public void testGetPlanFeature_whenPlanDuration_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((Boolean) new OcientExecutionPlan("Query").getPlanFeature("plan.duration"));
  }

  /**
   * Test {@link OcientExecutionPlan#getPlanFeature(String)}.
   *
   * <ul>
   *   <li>When {@code plan.rows}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link OcientExecutionPlan#getPlanFeature(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object OcientExecutionPlan.getPlanFeature(String)"})
  public void testGetPlanFeature_whenPlanRows_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((Boolean) new OcientExecutionPlan("Query").getPlanFeature("plan.rows"));
  }

  /**
   * Test {@link OcientExecutionPlan#getPlanQueryString()}.
   *
   * <p>Method under test: {@link OcientExecutionPlan#getPlanQueryString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OcientExecutionPlan.getPlanQueryString()"})
  public void testGetPlanQueryString() {
    // Arrange, Act and Assert
    assertEquals("explain json Query", new OcientExecutionPlan("Query").getPlanQueryString());
  }

  /**
   * Test {@link OcientExecutionPlan#getPlanNodes(Map)}.
   *
   * <p>Method under test: {@link OcientExecutionPlan#getPlanNodes(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List OcientExecutionPlan.getPlanNodes(Map)"})
  public void testGetPlanNodes() {
    // Arrange
    OcientExecutionPlan ocientExecutionPlan = new OcientExecutionPlan("Query");

    // Act and Assert
    assertNull(ocientExecutionPlan.getPlanNodes(new HashMap<>()));
  }
}
