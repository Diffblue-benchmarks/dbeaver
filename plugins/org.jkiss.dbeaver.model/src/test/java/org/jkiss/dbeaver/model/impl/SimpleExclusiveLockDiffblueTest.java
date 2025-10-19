package org.jkiss.dbeaver.model.impl;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPExclusiveResource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SimpleExclusiveLockDiffblueTest {
  /**
   * Test {@link SimpleExclusiveLock#releaseExclusiveLock(Object)}.
   *
   * <p>Method under test: {@link SimpleExclusiveLock#releaseExclusiveLock(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleExclusiveLock.releaseExclusiveLock(Object)"})
  public void testReleaseExclusiveLock() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new SimpleExclusiveLock().releaseExclusiveLock(DBPExclusiveResource.TASK_PROCESED));
  }

  /**
   * Test {@link SimpleExclusiveLock#releaseTaskLock(String, Object)}.
   *
   * <p>Method under test: {@link SimpleExclusiveLock#releaseTaskLock(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleExclusiveLock.releaseTaskLock(String, Object)"})
  public void testReleaseTaskLock() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new SimpleExclusiveLock()
                .releaseTaskLock("Task Name", DBPExclusiveResource.TASK_PROCESED));
  }
}
