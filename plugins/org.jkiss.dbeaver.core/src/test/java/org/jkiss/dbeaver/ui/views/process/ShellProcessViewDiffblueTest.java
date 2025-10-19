package org.jkiss.dbeaver.ui.views.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.runtime.DBRProcessDescriptor;
import org.jkiss.dbeaver.model.runtime.DBRShellCommand;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ShellProcessViewDiffblueTest {
  /**
   * Test {@link ShellProcessView#getProcessDescriptor()}.
   *
   * <p>Method under test: {@link ShellProcessView#getProcessDescriptor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBRProcessDescriptor ShellProcessView.getProcessDescriptor()"})
  public void testGetProcessDescriptor() {
    // Arrange, Act and Assert
    assertNull(new ShellProcessView().getProcessDescriptor());
  }

  /**
   * Test {@link ShellProcessView#initProcess(DBRProcessDescriptor)}.
   *
   * <p>Method under test: {@link ShellProcessView#initProcess(DBRProcessDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ShellProcessView.initProcess(DBRProcessDescriptor)"})
  public void testInitProcess() {
    // Arrange
    ShellProcessView shellProcessView = new ShellProcessView();
    DBRProcessDescriptor processDescriptor =
        new DBRProcessDescriptor(new DBRShellCommand("Command"));

    // Act
    shellProcessView.initProcess(processDescriptor);

    // Assert
    assertEquals("Command", shellProcessView.getPartName());
    assertEquals("Command", shellProcessView.getTitle());
    assertSame(processDescriptor, shellProcessView.getProcessDescriptor());
  }

  /**
   * Test new {@link ShellProcessView} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link ShellProcessView}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ShellProcessView.<init>()"})
  public void testNewShellProcessView() {
    // Arrange and Act
    ShellProcessView actualShellProcessView = new ShellProcessView();

    // Assert
    assertEquals("", actualShellProcessView.getContentDescription());
    assertEquals("", actualShellProcessView.getPartName());
    assertEquals("", actualShellProcessView.getTitle());
    assertEquals("", actualShellProcessView.getTitleToolTip());
    assertNull(actualShellProcessView.getViewSite());
    assertNull(actualShellProcessView.getSite());
    assertNull(actualShellProcessView.getProcessDescriptor());
    assertEquals(0, actualShellProcessView.getOrientation());
    assertTrue(actualShellProcessView.getPartProperties().isEmpty());
  }
}
