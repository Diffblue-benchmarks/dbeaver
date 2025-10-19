package org.jkiss.dbeaver.ui.actions;

import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ToolBarConfigurationPropertyTesterDiffblueTest {
  /**
   * Test {@link ToolBarConfigurationPropertyTester#test(Object, String, Object[], Object)}.
   *
   * <ul>
   *   <li>Given {@link ToolBarConfigurationPropertyTester} (default constructor).
   *   <li>When {@code null}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ToolBarConfigurationPropertyTester#test(Object, String, Object[],
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ToolBarConfigurationPropertyTester.test(Object, String, Object[], Object)"
  })
  public void testTest_givenToolBarConfigurationPropertyTester_whenNull_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(
        new ToolBarConfigurationPropertyTester()
            .test(
                DBPEvent.RENAME,
                ToolBarConfigurationPropertyTester.PROP_VISIBLE,
                null,
                DBPEvent.RENAME));
  }

  /**
   * Test {@link ToolBarConfigurationPropertyTester#test(Object, String, Object[], Object)}.
   *
   * <ul>
   *   <li>Given {@link ToolBarConfigurationPropertyTester} (default constructor).
   *   <li>When {@code Property}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ToolBarConfigurationPropertyTester#test(Object, String, Object[],
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ToolBarConfigurationPropertyTester.test(Object, String, Object[], Object)"
  })
  public void testTest_givenToolBarConfigurationPropertyTester_whenProperty_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(
        new ToolBarConfigurationPropertyTester()
            .test(DBPEvent.RENAME, "Property", new Object[] {DBPEvent.RENAME}, DBPEvent.RENAME));
  }

  /**
   * Test {@link ToolBarConfigurationPropertyTester#test(Object, String, Object[], Object)}.
   *
   * <ul>
   *   <li>When array of {@link Object} with {@link DBPEvent#RENAME}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ToolBarConfigurationPropertyTester#test(Object, String, Object[],
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ToolBarConfigurationPropertyTester.test(Object, String, Object[], Object)"
  })
  public void testTest_whenArrayOfObjectWithRename_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(
        new ToolBarConfigurationPropertyTester()
            .test(
                DBPEvent.RENAME,
                ToolBarConfigurationPropertyTester.PROP_VISIBLE,
                new Object[] {DBPEvent.RENAME},
                DBPEvent.RENAME));
  }

  /**
   * Test new {@link ToolBarConfigurationPropertyTester} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * ToolBarConfigurationPropertyTester}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ToolBarConfigurationPropertyTester.<init>()"})
  public void testNewToolBarConfigurationPropertyTester() {
    // Arrange, Act and Assert
    assertTrue(new ToolBarConfigurationPropertyTester().isInstantiated());
  }
}
