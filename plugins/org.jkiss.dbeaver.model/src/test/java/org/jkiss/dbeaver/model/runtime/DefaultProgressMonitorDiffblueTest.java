package org.jkiss.dbeaver.model.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.jkiss.dbeaver.Log;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DefaultProgressMonitorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DefaultProgressMonitor#DefaultProgressMonitor(IProgressMonitor)}
   *   <li>{@link DefaultProgressMonitor#getNestedMonitor()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DefaultProgressMonitor.<init>(IProgressMonitor)",
    "IProgressMonitor DefaultProgressMonitor.getNestedMonitor()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalCacheProgressMonitor nestedMonitor =
        new LocalCacheProgressMonitor(new LoggingProgressMonitor());

    // Act and Assert
    assertSame(nestedMonitor, new DefaultProgressMonitor(nestedMonitor).getNestedMonitor());
  }

  /**
   * Test {@link DefaultProgressMonitor#isCanceled()}.
   *
   * <ul>
   *   <li>Given {@link NullProgressMonitor} (default constructor) Canceled is {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultProgressMonitor#isCanceled()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DefaultProgressMonitor.isCanceled()"})
  public void testIsCanceled_givenNullProgressMonitorCanceledIsTrue_thenReturnTrue() {
    // Arrange
    NullProgressMonitor nestedMonitor = new NullProgressMonitor();
    nestedMonitor.setCanceled(true);

    // Act and Assert
    assertTrue(new DefaultProgressMonitor(nestedMonitor).isCanceled());
  }

  /**
   * Test {@link DefaultProgressMonitor#startBlock(DBRBlockingObject, String)}.
   *
   * <p>Method under test: {@link DefaultProgressMonitor#startBlock(DBRBlockingObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultProgressMonitor.startBlock(DBRBlockingObject, String)"})
  public void testStartBlock() {
    // Arrange
    DefaultProgressMonitor defaultProgressMonitor =
        new DefaultProgressMonitor(new LocalCacheProgressMonitor(new LoggingProgressMonitor()));
    DBRBlockingObject object = mock(DBRBlockingObject.class);

    // Act
    defaultProgressMonitor.startBlock(object, "Task Name");

    // Assert
    List<DBRBlockingObject> activeBlocks = defaultProgressMonitor.getActiveBlocks();
    assertEquals(1, activeBlocks.size());
    assertSame(object, activeBlocks.get(0));
  }

  /**
   * Test {@link DefaultProgressMonitor#startBlock(DBRBlockingObject, String)}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultProgressMonitor#startBlock(DBRBlockingObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultProgressMonitor.startBlock(DBRBlockingObject, String)"})
  public void testStartBlock_givenJavaLangObject() {
    // Arrange
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor original = new LoggingProgressMonitor(Log.getLog(forClass));
    LocalCacheProgressMonitor nestedMonitor = new LocalCacheProgressMonitor(original);
    DefaultProgressMonitor defaultProgressMonitor = new DefaultProgressMonitor(nestedMonitor);
    DBRBlockingObject object = mock(DBRBlockingObject.class);

    // Act
    defaultProgressMonitor.startBlock(object, "Task Name");

    // Assert
    List<DBRBlockingObject> activeBlocks = defaultProgressMonitor.getActiveBlocks();
    assertEquals(1, activeBlocks.size());
    assertSame(object, activeBlocks.get(0));
  }

  /**
   * Test {@link DefaultProgressMonitor#startBlock(DBRBlockingObject, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultProgressMonitor#startBlock(DBRBlockingObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultProgressMonitor.startBlock(DBRBlockingObject, String)"})
  public void testStartBlock_whenNull() {
    // Arrange
    DefaultProgressMonitor defaultProgressMonitor =
        new DefaultProgressMonitor(new LocalCacheProgressMonitor(new LoggingProgressMonitor()));
    DBRBlockingObject object = mock(DBRBlockingObject.class);

    // Act
    defaultProgressMonitor.startBlock(object, null);

    // Assert
    List<DBRBlockingObject> activeBlocks = defaultProgressMonitor.getActiveBlocks();
    assertEquals(1, activeBlocks.size());
    assertSame(object, activeBlocks.get(0));
  }

  /**
   * Test {@link DefaultProgressMonitor#endBlock()}.
   *
   * <p>Method under test: {@link DefaultProgressMonitor#endBlock()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultProgressMonitor.endBlock()"})
  public void testEndBlock() {
    // Arrange
    DefaultProgressMonitor defaultProgressMonitor =
        new DefaultProgressMonitor(new LocalCacheProgressMonitor(new LoggingProgressMonitor()));

    // Act
    defaultProgressMonitor.endBlock();

    // Assert that nothing has changed
    assertNull(defaultProgressMonitor.getActiveBlocks());
  }

  /**
   * Test {@link DefaultProgressMonitor#endBlock()}.
   *
   * <p>Method under test: {@link DefaultProgressMonitor#endBlock()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultProgressMonitor.endBlock()"})
  public void testEndBlock2() {
    // Arrange
    DefaultProgressMonitor defaultProgressMonitor =
        new DefaultProgressMonitor(new LocalCacheProgressMonitor(new LoggingProgressMonitor()));
    defaultProgressMonitor.startBlock(
        mock(DBRBlockingObject.class), "End block invoked while no blocking objects are in stack");

    // Act
    defaultProgressMonitor.endBlock();

    // Assert
    assertNull(defaultProgressMonitor.getActiveBlocks());
  }

  /**
   * Test {@link DefaultProgressMonitor#getActiveBlocks()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultProgressMonitor#getActiveBlocks()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DefaultProgressMonitor.getActiveBlocks()"})
  public void testGetActiveBlocks_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(
        new DefaultProgressMonitor(new LocalCacheProgressMonitor(new LoggingProgressMonitor()))
            .getActiveBlocks());
  }

  /**
   * Test {@link DefaultProgressMonitor#getActiveBlocks()}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link DefaultProgressMonitor#getActiveBlocks()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DefaultProgressMonitor.getActiveBlocks()"})
  public void testGetActiveBlocks_thenReturnSizeIsOne() {
    // Arrange
    DefaultProgressMonitor defaultProgressMonitor =
        new DefaultProgressMonitor(new LocalCacheProgressMonitor(new LoggingProgressMonitor()));
    defaultProgressMonitor.startBlock(mock(DBRBlockingObject.class), "Task Name");

    // Act and Assert
    assertEquals(1, defaultProgressMonitor.getActiveBlocks().size());
  }
}
