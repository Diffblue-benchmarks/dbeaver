package org.jkiss.dbeaver.model.connection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBPDriverConfigurationTypeDiffblueTest {
  /**
   * Test {@link DBPDriverConfigurationType#isDefault()}.
   *
   * <ul>
   *   <li>Given {@code MANUAL}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBPDriverConfigurationType#isDefault()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPDriverConfigurationType.isDefault()"})
  public void testIsDefault_givenManual_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(DBPDriverConfigurationType.MANUAL.isDefault());
  }

  /**
   * Test {@link DBPDriverConfigurationType#isDefault()}.
   *
   * <ul>
   *   <li>Given {@code URL}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBPDriverConfigurationType#isDefault()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPDriverConfigurationType.isDefault()"})
  public void testIsDefault_givenUrl_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DBPDriverConfigurationType.URL.isDefault());
  }
}
