package org.jkiss.dbeaver.model.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ProxyProgressMonitorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ProxyProgressMonitor#ProxyProgressMonitor(DBRProgressMonitor)}
   *   <li>{@link ProxyProgressMonitor#internalWorked(double)}
   *   <li>{@link ProxyProgressMonitor#setCanceled(boolean)}
   *   <li>{@link ProxyProgressMonitor#setTaskName(String)}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ProxyProgressMonitor.<init>(DBRProgressMonitor)",
    "void ProxyProgressMonitor.internalWorked(double)",
    "void ProxyProgressMonitor.setCanceled(boolean)",
    "void ProxyProgressMonitor.setTaskName(String)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    ProxyProgressMonitor actualProxyProgressMonitor =
        new ProxyProgressMonitor(new LoggingProgressMonitor());
    actualProxyProgressMonitor.internalWorked(10.0d);
    actualProxyProgressMonitor.setCanceled(true);
    actualProxyProgressMonitor.setTaskName("Name");

    // Assert
    DBRProgressMonitor dbrProgressMonitor = actualProxyProgressMonitor.original;
    assertTrue(dbrProgressMonitor instanceof LoggingProgressMonitor);
    assertNull(dbrProgressMonitor.getActiveBlocks());
    assertNull(actualProxyProgressMonitor.getActiveBlocks());
    assertFalse(actualProxyProgressMonitor.isForceCacheUsage());
    assertFalse(dbrProgressMonitor.isForceCacheUsage());
    assertSame(
        actualProxyProgressMonitor.getNestedMonitor(), dbrProgressMonitor.getNestedMonitor());
  }

  /**
   * Test {@link ProxyProgressMonitor#isCanceled()}.
   *
   * <ul>
   *   <li>Given {@link DBRProgressMonitor} {@link DBRProgressMonitor#isCanceled()} return {@code
   *       false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyProgressMonitor#isCanceled()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProxyProgressMonitor.isCanceled()"})
  public void testIsCanceled_givenDBRProgressMonitorIsCanceledReturnFalse_thenReturnFalse() {
    // Arrange
    DBRProgressMonitor original = mock(DBRProgressMonitor.class);
    when(original.isCanceled()).thenReturn(false);

    // Act
    boolean actualIsCanceledResult = new ProxyProgressMonitor(original).isCanceled();

    // Assert
    verify(original).isCanceled();
    assertFalse(actualIsCanceledResult);
  }

  /**
   * Test {@link ProxyProgressMonitor#isCanceled()}.
   *
   * <ul>
   *   <li>Given {@link DBRProgressMonitor} {@link DBRProgressMonitor#isCanceled()} return {@code
   *       true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyProgressMonitor#isCanceled()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProxyProgressMonitor.isCanceled()"})
  public void testIsCanceled_givenDBRProgressMonitorIsCanceledReturnTrue_thenReturnTrue() {
    // Arrange
    DBRProgressMonitor original = mock(DBRProgressMonitor.class);
    when(original.isCanceled()).thenReturn(true);

    // Act
    boolean actualIsCanceledResult = new ProxyProgressMonitor(original).isCanceled();

    // Assert
    verify(original).isCanceled();
    assertTrue(actualIsCanceledResult);
  }

  /**
   * Test {@link ProxyProgressMonitor#startBlock(DBRBlockingObject, String)}.
   *
   * <p>Method under test: {@link ProxyProgressMonitor#startBlock(DBRBlockingObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProxyProgressMonitor.startBlock(DBRBlockingObject, String)"})
  public void testStartBlock() {
    // Arrange
    ProxyProgressMonitor proxyProgressMonitor =
        new ProxyProgressMonitor(new LoggingProgressMonitor());
    DBRBlockingObject object = mock(DBRBlockingObject.class);

    // Act
    proxyProgressMonitor.startBlock(object, "Task Name");

    // Assert
    DBRProgressMonitor dbrProgressMonitor = proxyProgressMonitor.original;
    assertTrue(dbrProgressMonitor instanceof LoggingProgressMonitor);
    List<DBRBlockingObject> activeBlocks = proxyProgressMonitor.getActiveBlocks();
    assertEquals(1, activeBlocks.size());
    assertEquals(activeBlocks, dbrProgressMonitor.getActiveBlocks());
    assertSame(object, activeBlocks.get(0));
  }

  /**
   * Test {@link ProxyProgressMonitor#startBlock(DBRBlockingObject, String)}.
   *
   * <p>Method under test: {@link ProxyProgressMonitor#startBlock(DBRBlockingObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProxyProgressMonitor.startBlock(DBRBlockingObject, String)"})
  public void testStartBlock2() {
    // Arrange
    DefaultProgressMonitor original =
        new DefaultProgressMonitor(new LocalCacheProgressMonitor(new LoggingProgressMonitor()));
    ProxyProgressMonitor proxyProgressMonitor = new ProxyProgressMonitor(original);
    LocalStatement object = new LocalStatement(mock(DBCSession.class), "Text");

    // Act
    proxyProgressMonitor.startBlock(object, "Task Name");

    // Assert
    DBRProgressMonitor dbrProgressMonitor = proxyProgressMonitor.original;
    assertTrue(dbrProgressMonitor instanceof DefaultProgressMonitor);
    List<DBRBlockingObject> activeBlocks = proxyProgressMonitor.getActiveBlocks();
    assertEquals(1, activeBlocks.size());
    assertEquals(activeBlocks, dbrProgressMonitor.getActiveBlocks());
    assertSame(object, activeBlocks.get(0));
  }

  /**
   * Test {@link ProxyProgressMonitor#startBlock(DBRBlockingObject, String)}.
   *
   * <p>Method under test: {@link ProxyProgressMonitor#startBlock(DBRBlockingObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProxyProgressMonitor.startBlock(DBRBlockingObject, String)"})
  public void testStartBlock3() {
    // Arrange
    ProxyProgressMonitor proxyProgressMonitor =
        new ProxyProgressMonitor(new LocalCacheProgressMonitor(new LoggingProgressMonitor()));
    LocalStatement object = new LocalStatement(mock(DBCSession.class), "Text");

    // Act
    proxyProgressMonitor.startBlock(object, "Task Name");

    // Assert
    DBRProgressMonitor dbrProgressMonitor = proxyProgressMonitor.original;
    assertTrue(dbrProgressMonitor instanceof LocalCacheProgressMonitor);
    DBRProgressMonitor dbrProgressMonitor2 =
        ((LocalCacheProgressMonitor) dbrProgressMonitor).original;
    assertTrue(dbrProgressMonitor2 instanceof LoggingProgressMonitor);
    List<DBRBlockingObject> activeBlocks = proxyProgressMonitor.getActiveBlocks();
    assertEquals(1, activeBlocks.size());
    assertEquals(activeBlocks, dbrProgressMonitor.getActiveBlocks());
    assertEquals(activeBlocks, dbrProgressMonitor2.getActiveBlocks());
    assertSame(object, activeBlocks.get(0));
  }

  /**
   * Test {@link ProxyProgressMonitor#startBlock(DBRBlockingObject, String)}.
   *
   * <p>Method under test: {@link ProxyProgressMonitor#startBlock(DBRBlockingObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProxyProgressMonitor.startBlock(DBRBlockingObject, String)"})
  public void testStartBlock4() {
    // Arrange
    DefaultProgressMonitor original =
        new DefaultProgressMonitor(new LocalCacheProgressMonitor(new LoggingProgressMonitor()));
    LocalCacheProgressMonitor nestedMonitor = new LocalCacheProgressMonitor(original);
    DefaultProgressMonitor original2 = new DefaultProgressMonitor(nestedMonitor);
    ProxyProgressMonitor proxyProgressMonitor = new ProxyProgressMonitor(original2);
    DBRBlockingObject object = mock(DBRBlockingObject.class);

    // Act
    proxyProgressMonitor.startBlock(object, "Task Name");

    // Assert
    DBRProgressMonitor dbrProgressMonitor = proxyProgressMonitor.original;
    assertTrue(dbrProgressMonitor instanceof DefaultProgressMonitor);
    List<DBRBlockingObject> activeBlocks = proxyProgressMonitor.getActiveBlocks();
    assertEquals(1, activeBlocks.size());
    assertEquals(activeBlocks, dbrProgressMonitor.getActiveBlocks());
    assertSame(object, activeBlocks.get(0));
  }

  /**
   * Test {@link ProxyProgressMonitor#getActiveBlocks()}.
   *
   * <p>Method under test: {@link ProxyProgressMonitor#getActiveBlocks()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List ProxyProgressMonitor.getActiveBlocks()"})
  public void testGetActiveBlocks() {
    // Arrange, Act and Assert
    assertNull(new ProxyProgressMonitor(new LoggingProgressMonitor()).getActiveBlocks());
  }

  /**
   * Test {@link ProxyProgressMonitor#getActiveBlocks()}.
   *
   * <p>Method under test: {@link ProxyProgressMonitor#getActiveBlocks()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List ProxyProgressMonitor.getActiveBlocks()"})
  public void testGetActiveBlocks2() {
    // Arrange, Act and Assert
    assertNull(
        new ProxyProgressMonitor(new LocalCacheProgressMonitor(new LoggingProgressMonitor()))
            .getActiveBlocks());
  }
}
