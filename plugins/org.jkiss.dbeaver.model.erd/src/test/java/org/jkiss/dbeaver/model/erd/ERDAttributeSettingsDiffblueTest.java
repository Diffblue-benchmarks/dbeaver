package org.jkiss.dbeaver.model.erd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ERDAttributeSettingsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ERDAttributeSettings#ERDAttributeSettings(ERDAttributeVisibility, boolean)}
   *   <li>{@link ERDAttributeSettings#getVisibility()}
   *   <li>{@link ERDAttributeSettings#isAlphabeticalOrder()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDAttributeSettings.<init>(ERDAttributeVisibility, boolean)",
    "ERDAttributeVisibility ERDAttributeSettings.getVisibility()",
    "boolean ERDAttributeSettings.isAlphabeticalOrder()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    ERDAttributeSettings actualErdAttributeSettings =
        new ERDAttributeSettings(ERDAttributeVisibility.ALL, true);
    ERDAttributeVisibility actualVisibility = actualErdAttributeSettings.getVisibility();

    // Assert
    assertEquals(ERDAttributeVisibility.ALL, actualVisibility);
    assertTrue(actualErdAttributeSettings.isAlphabeticalOrder());
  }
}
