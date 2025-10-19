package org.jkiss.dbeaver.headless;

import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBeaverTestPlatformUIDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DBeaverTestPlatformUI}
   *   <li>{@link DBeaverTestPlatformUI#initialize()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBeaverTestPlatformUI.<init>()",
    "void DBeaverTestPlatformUI.initialize()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBeaverTestPlatformUI actualDBeaverTestPlatformUI = new DBeaverTestPlatformUI();
    actualDBeaverTestPlatformUI.initialize();

    // Assert
    assertFalse(actualDBeaverTestPlatformUI.readAndDispatchEvents());
  }
}
