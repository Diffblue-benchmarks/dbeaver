package org.jkiss.dbeaver.registry.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.jkiss.dbeaver.model.impl.app.BaseProjectImpl;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TaskUtilsDiffblueTest {
  /**
   * Test {@link TaskUtils#loadRunStatistics(Path, Gson)}.
   *
   * <ul>
   *   <li>When {@link Gson#Gson()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link TaskUtils#loadRunStatistics(Path, Gson)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List TaskUtils.loadRunStatistics(Path, Gson)"})
  public void testLoadRunStatistics_whenGson_thenReturnEmpty() {
    // Arrange
    Path metaFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    List<TaskRunImpl> actualLoadRunStatisticsResult =
        TaskUtils.loadRunStatistics(metaFile, new Gson());

    // Assert
    assertTrue(actualLoadRunStatisticsResult.isEmpty());
  }

  /**
   * Test {@link TaskUtils#loadRunStatistics(Path, Gson)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link TaskUtils#loadRunStatistics(Path, Gson)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List TaskUtils.loadRunStatistics(Path, Gson)"})
  public void testLoadRunStatistics_whenNull_thenReturnEmpty() {
    // Arrange and Act
    List<TaskRunImpl> actualLoadRunStatisticsResult =
        TaskUtils.loadRunStatistics(
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"), null);

    // Assert
    assertTrue(actualLoadRunStatisticsResult.isEmpty());
  }

  /**
   * Test {@link TaskUtils#loadRunStatistics(Path, Gson)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code Error reading task run statistics}.
   * </ul>
   *
   * <p>Method under test: {@link TaskUtils#loadRunStatistics(Path, Gson)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List TaskUtils.loadRunStatistics(Path, Gson)"})
  public void testLoadRunStatistics_whenPropertyIsJavaIoTmpdirIsErrorReadingTaskRunStatistics() {
    // Arrange and Act
    List<TaskRunImpl> actualLoadRunStatisticsResult =
        TaskUtils.loadRunStatistics(
            Paths.get(System.getProperty("java.io.tmpdir"), "Error reading task run statistics"),
            BaseProjectImpl.METADATA_GSON);

    // Assert
    assertTrue(actualLoadRunStatisticsResult.isEmpty());
  }

  /**
   * Test {@link TaskUtils#loadRunStatistics(Path, Gson)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link TaskUtils#loadRunStatistics(Path, Gson)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List TaskUtils.loadRunStatistics(Path, Gson)"})
  public void testLoadRunStatistics_whenPropertyIsJavaIoTmpdirIsTestTxt_thenReturnEmpty() {
    // Arrange and Act
    List<TaskRunImpl> actualLoadRunStatisticsResult =
        TaskUtils.loadRunStatistics(
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"),
            BaseProjectImpl.METADATA_GSON);

    // Assert
    assertTrue(actualLoadRunStatisticsResult.isEmpty());
  }

  /**
   * Test {@link TaskUtils#buildRunLogFileName(String)}.
   *
   * <p>Method under test: {@link TaskUtils#buildRunLogFileName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TaskUtils.buildRunLogFileName(String)"})
  public void testBuildRunLogFileName() {
    // Arrange, Act and Assert
    assertEquals("run_42.log", TaskUtils.buildRunLogFileName("42"));
  }
}
