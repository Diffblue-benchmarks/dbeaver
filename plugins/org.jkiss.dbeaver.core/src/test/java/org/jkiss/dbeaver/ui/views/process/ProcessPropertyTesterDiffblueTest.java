package org.jkiss.dbeaver.ui.views.process;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.runtime.DBRProcessDescriptor;
import org.jkiss.dbeaver.model.runtime.DBRShellCommand;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ProcessPropertyTesterDiffblueTest {
  /**
   * Test new {@link ProcessPropertyTester} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link ProcessPropertyTester}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProcessPropertyTester.<init>()"})
  public void testNewProcessPropertyTester() {
    // Arrange, Act and Assert
    assertTrue(new ProcessPropertyTester().isInstantiated());
  }

  /**
   * Test {@link ProcessPropertyTester#test(Object, String, Object[], Object)}.
   *
   * <ul>
   *   <li>Given {@link DBRShellCommand#DBRShellCommand(String)} with {@code Command}.
   * </ul>
   *
   * <p>Method under test: {@link ProcessPropertyTester#test(Object, String, Object[], Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProcessPropertyTester.test(Object, String, Object[], Object)"})
  public void testTest_givenDBRShellCommandWithCommand() {
    // Arrange
    ProcessPropertyTester processPropertyTester = new ProcessPropertyTester();

    ShellProcessView shellProcessView = new ShellProcessView();
    shellProcessView.initProcess(new DBRProcessDescriptor(new DBRShellCommand("Command")));

    // Act and Assert
    assertFalse(
        processPropertyTester.test(
            shellProcessView,
            ProcessPropertyTester.PROP_RUNNING,
            new Object[] {DBPEvent.RENAME},
            DBPEvent.RENAME));
  }

  /**
   * Test {@link ProcessPropertyTester#test(Object, String, Object[], Object)}.
   *
   * <ul>
   *   <li>Given {@link ProcessPropertyTester} (default constructor).
   *   <li>When {@code Property}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ProcessPropertyTester#test(Object, String, Object[], Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProcessPropertyTester.test(Object, String, Object[], Object)"})
  public void testTest_givenProcessPropertyTester_whenProperty_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        new ProcessPropertyTester()
            .test(DBPEvent.RENAME, "Property", new Object[] {DBPEvent.RENAME}, DBPEvent.RENAME));
  }

  /**
   * Test {@link ProcessPropertyTester#test(Object, String, Object[], Object)}.
   *
   * <ul>
   *   <li>Given {@link ProcessPropertyTester} (default constructor).
   *   <li>When {@link ShellProcessView} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ProcessPropertyTester#test(Object, String, Object[], Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProcessPropertyTester.test(Object, String, Object[], Object)"})
  public void testTest_givenProcessPropertyTester_whenShellProcessView_thenReturnFalse() {
    // Arrange
    ProcessPropertyTester processPropertyTester = new ProcessPropertyTester();

    // Act and Assert
    assertFalse(
        processPropertyTester.test(
            new ShellProcessView(), "Property", new Object[] {DBPEvent.RENAME}, DBPEvent.RENAME));
  }

  /**
   * Test {@link ProcessPropertyTester#test(Object, String, Object[], Object)}.
   *
   * <ul>
   *   <li>Given {@link ProcessPropertyTester} (default constructor).
   *   <li>When {@link ShellProcessView} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ProcessPropertyTester#test(Object, String, Object[], Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProcessPropertyTester.test(Object, String, Object[], Object)"})
  public void testTest_givenProcessPropertyTester_whenShellProcessView_thenReturnFalse2() {
    // Arrange
    ProcessPropertyTester processPropertyTester = new ProcessPropertyTester();

    // Act and Assert
    assertFalse(
        processPropertyTester.test(
            new ShellProcessView(),
            ProcessPropertyTester.PROP_RUNNING,
            new Object[] {DBPEvent.RENAME},
            DBPEvent.RENAME));
  }
}
