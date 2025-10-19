package org.jkiss.dbeaver.model.ai.engine.copilot.dto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.ai.engine.copilot.dto.CopilotModel.CopilotModelPolicy;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CopilotModelDiffblueTest {
  /**
   * Test {@link CopilotModel#isEnabled()}.
   *
   * <p>Method under test: {@link CopilotModel#isEnabled()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CopilotModel.isEnabled()"})
  public void testIsEnabled() {
    // Arrange
    CopilotModel copilotModel = new CopilotModel("Name", "42", false, null);

    // Act and Assert
    assertFalse(copilotModel.isEnabled());
  }

  /**
   * Test {@link CopilotModel#isEnabled()}.
   *
   * <p>Method under test: {@link CopilotModel#isEnabled()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CopilotModel.isEnabled()"})
  public void testIsEnabled2() {
    // Arrange
    CopilotModel copilotModel = new CopilotModel("Name", "42", true, null);

    // Act and Assert
    assertTrue(copilotModel.isEnabled());
  }

  /**
   * Test {@link CopilotModel#isEnabled()}.
   *
   * <ul>
   *   <li>Given {@link CopilotModelPolicy#CopilotModelPolicy(String)} with state is {@code
   *       enabled}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link CopilotModel#isEnabled()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CopilotModel.isEnabled()"})
  public void testIsEnabled_givenCopilotModelPolicyWithStateIsEnabled_thenReturnTrue() {
    // Arrange
    CopilotModel copilotModel =
        new CopilotModel("Name", "42", true, new CopilotModelPolicy("enabled"));

    // Act and Assert
    assertTrue(copilotModel.isEnabled());
  }

  /**
   * Test {@link CopilotModel#isEnabled()}.
   *
   * <ul>
   *   <li>Given {@link CopilotModelPolicy#CopilotModelPolicy(String)} with state is {@code MD}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link CopilotModel#isEnabled()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CopilotModel.isEnabled()"})
  public void testIsEnabled_givenCopilotModelPolicyWithStateIsMd_thenReturnFalse() {
    // Arrange
    CopilotModel copilotModel = new CopilotModel("Name", "42", true, new CopilotModelPolicy("MD"));

    // Act and Assert
    assertFalse(copilotModel.isEnabled());
  }

  /**
   * Test {@link CopilotModel#isEnabled()}.
   *
   * <ul>
   *   <li>Given {@link CopilotModelPolicy#CopilotModelPolicy(String)} with state is {@code null}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link CopilotModel#isEnabled()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CopilotModel.isEnabled()"})
  public void testIsEnabled_givenCopilotModelPolicyWithStateIsNull_thenReturnTrue() {
    // Arrange
    CopilotModel copilotModel = new CopilotModel("Name", "42", true, new CopilotModelPolicy(null));

    // Act and Assert
    assertTrue(copilotModel.isEnabled());
  }
}
