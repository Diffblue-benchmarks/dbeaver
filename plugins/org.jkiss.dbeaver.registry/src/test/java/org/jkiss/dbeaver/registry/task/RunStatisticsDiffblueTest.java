package org.jkiss.dbeaver.registry.task;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RunStatisticsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RunStatistics#RunStatistics()}
   *   <li>{@link RunStatistics#getRuns()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RunStatistics.<init>()",
    "void RunStatistics.<init>(List)",
    "List RunStatistics.getRuns()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertTrue(new RunStatistics().getRuns().isEmpty());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return Runs is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RunStatistics#RunStatistics(List)}
   *   <li>{@link RunStatistics#getRuns()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RunStatistics.<init>()",
    "void RunStatistics.<init>(List)",
    "List RunStatistics.getRuns()"
  })
  public void testGettersAndSetters_whenArrayList_thenReturnRunsIsArrayList() {
    // Arrange
    ArrayList<TaskRunImpl> runs = new ArrayList<>();

    // Act
    List<TaskRunImpl> actualRuns = new RunStatistics(runs).getRuns();

    // Assert
    assertTrue(actualRuns.isEmpty());
    assertSame(runs, actualRuns);
  }
}
