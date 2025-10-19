package org.jkiss.dbeaver.registry.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import org.jkiss.dbeaver.model.impl.app.BaseProjectImpl;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class TaskManagerImplDiffblueTest {
  /**
   * Test {@link TaskManagerImpl#TaskManagerImpl(BaseProjectImpl, Path)}.
   *
   * <ul>
   *   <li>Then {@link TaskManagerImpl#systemDateFormat} NumberFormat return {@link DecimalFormat}.
   * </ul>
   *
   * <p>Method under test: {@link TaskManagerImpl#TaskManagerImpl(BaseProjectImpl, Path)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskManagerImpl.<init>(BaseProjectImpl, Path)"})
  public void testNewTaskManagerImpl_thenSystemDateFormatNumberFormatReturnDecimalFormat() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    BaseProjectImpl projectMetadata = mock(BaseProjectImpl.class);
    when(projectMetadata.hasRealmPermission(Mockito.<String>any())).thenReturn(false);
    when(projectMetadata.getDisplayName()).thenReturn("Display Name");
    Path statisticsFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    TaskManagerImpl actualTaskManagerImpl = new TaskManagerImpl(projectMetadata, statisticsFolder);

    // Assert
    verify(projectMetadata).getDisplayName();
    verify(projectMetadata).hasRealmPermission("project-datasource-view");
    SimpleDateFormat simpleDateFormat = actualTaskManagerImpl.systemDateFormat;
    assertTrue(simpleDateFormat.getNumberFormat() instanceof DecimalFormat);
    assertTrue(simpleDateFormat.getCalendar() instanceof GregorianCalendar);
    assertEquals("yyyyMMddHHmm", simpleDateFormat.toPattern());
    assertEquals(0, actualTaskManagerImpl.getAllTasks().length);
    assertEquals(0, actualTaskManagerImpl.getExistingTaskTypes().length);
    assertEquals(0, actualTaskManagerImpl.getTasksFolders().length);
    assertFalse(actualTaskManagerImpl.hasRunningTasks());
    assertTrue(simpleDateFormat.isLenient());
    assertSame(statisticsFolder, actualTaskManagerImpl.getStatisticsFolder());
    assertSame(projectMetadata, actualTaskManagerImpl.getProject());
  }
}
