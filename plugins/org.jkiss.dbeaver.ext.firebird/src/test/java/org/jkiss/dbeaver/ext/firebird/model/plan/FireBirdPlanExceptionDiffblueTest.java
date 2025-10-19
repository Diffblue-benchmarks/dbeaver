package org.jkiss.dbeaver.ext.firebird.model.plan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FireBirdPlanExceptionDiffblueTest {
  /**
   * Test {@link FireBirdPlanException#FireBirdPlanException(String, int, String)}.
   *
   * <p>Method under test: {@link FireBirdPlanException#FireBirdPlanException(String, int, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FireBirdPlanException.<init>(String, int, String)"})
  public void testNewFireBirdPlanException() {
    // Arrange and Act
    FireBirdPlanException actualFireBirdPlanException =
        new FireBirdPlanException("Unexpected", 1, "Plan");

    // Assert
    assertEquals(
        "Error parsing plan - unexpected token Unexpected at position 1\n^^^lan",
        actualFireBirdPlanException.getLocalizedMessage());
    assertEquals(
        "Error parsing plan - unexpected token Unexpected at position 1\n^^^lan",
        actualFireBirdPlanException.getMessage());
    assertNull(actualFireBirdPlanException.getCause());
    assertEquals(0, actualFireBirdPlanException.getSuppressed().length);
  }

  /**
   * Test {@link FireBirdPlanException#FireBirdPlanException(String, String)}.
   *
   * <p>Method under test: {@link FireBirdPlanException#FireBirdPlanException(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FireBirdPlanException.<init>(String, String)"})
  public void testNewFireBirdPlanException2() {
    // Arrange and Act
    FireBirdPlanException actualFireBirdPlanException = new FireBirdPlanException("Info", "Index");

    // Assert
    assertEquals(
        "Error when getting info about Index index(Info)",
        actualFireBirdPlanException.getLocalizedMessage());
    assertEquals(
        "Error when getting info about Index index(Info)",
        actualFireBirdPlanException.getMessage());
    assertNull(actualFireBirdPlanException.getCause());
    assertEquals(0, actualFireBirdPlanException.getSuppressed().length);
  }

  /**
   * Test {@link FireBirdPlanException#FireBirdPlanException(String, String, int, String)}.
   *
   * <p>Method under test: {@link FireBirdPlanException#FireBirdPlanException(String, String, int,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FireBirdPlanException.<init>(String, String, int, String)"})
  public void testNewFireBirdPlanException3() {
    // Arrange and Act
    FireBirdPlanException actualFireBirdPlanException =
        new FireBirdPlanException("Expected", "Actual", 1, "Plan");

    // Assert
    assertEquals(
        "Error parsing plan - expected Expected at position 1 but got Actual\n^^^lan",
        actualFireBirdPlanException.getLocalizedMessage());
    assertEquals(
        "Error parsing plan - expected Expected at position 1 but got Actual\n^^^lan",
        actualFireBirdPlanException.getMessage());
    assertNull(actualFireBirdPlanException.getCause());
    assertEquals(0, actualFireBirdPlanException.getSuppressed().length);
  }
}
