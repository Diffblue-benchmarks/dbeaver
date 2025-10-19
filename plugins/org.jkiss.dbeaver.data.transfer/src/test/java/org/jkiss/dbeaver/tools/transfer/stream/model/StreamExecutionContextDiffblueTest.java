package org.jkiss.dbeaver.tools.transfer.stream.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.exec.DBCInvalidatePhase;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class StreamExecutionContextDiffblueTest {
  /**
   * Test {@link StreamExecutionContext#StreamExecutionContext(StreamDataSource, String)}.
   *
   * <ul>
   *   <li>Then return ContextName is {@code Purpose}.
   * </ul>
   *
   * <p>Method under test: {@link StreamExecutionContext#StreamExecutionContext(StreamDataSource,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamExecutionContext.<init>(StreamDataSource, String)"})
  public void testNewStreamExecutionContext_thenReturnContextNameIsPurpose() {
    // Arrange
    StreamDataSource dataSource = new StreamDataSource("Input Name");

    // Act
    StreamExecutionContext actualStreamExecutionContext =
        new StreamExecutionContext(dataSource, "Purpose");

    // Assert
    assertEquals("Purpose", actualStreamExecutionContext.getContextName());
    assertNull(actualStreamExecutionContext.getContextDefaults());
    assertTrue(actualStreamExecutionContext.getContextAttributes().isEmpty());
    assertTrue(actualStreamExecutionContext.isConnected());
    assertSame(dataSource, actualStreamExecutionContext.getDataSource());
    assertSame(dataSource, actualStreamExecutionContext.getOwnerInstance());
  }

  /**
   * Test {@link StreamExecutionContext#getOwnerInstance()}.
   *
   * <p>Method under test: {@link StreamExecutionContext#getOwnerInstance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.struct.DBSInstance StreamExecutionContext.getOwnerInstance()"
  })
  public void testGetOwnerInstance() {
    // Arrange
    StreamDataSource dataSource = new StreamDataSource("Input Name");

    // Act and Assert
    assertSame(dataSource, new StreamExecutionContext(dataSource, "Purpose").getOwnerInstance());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link StreamExecutionContext#checkContextAlive(DBRProgressMonitor)}
   *   <li>{@link StreamExecutionContext#close()}
   *   <li>{@link StreamExecutionContext#invalidateContext(DBRProgressMonitor, DBCInvalidatePhase)}
   *   <li>{@link StreamExecutionContext#isConnected()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamExecutionContext.checkContextAlive(DBRProgressMonitor)",
    "void StreamExecutionContext.close()",
    "void StreamExecutionContext.invalidateContext(DBRProgressMonitor, DBCInvalidatePhase)",
    "boolean StreamExecutionContext.isConnected()"
  })
  public void testGettersAndSetters() throws DBException {
    // Arrange
    StreamExecutionContext streamExecutionContext =
        new StreamExecutionContext(new StreamDataSource("Input Name"), "Purpose");

    // Act
    streamExecutionContext.checkContextAlive(new LoggingProgressMonitor());
    streamExecutionContext.close();
    streamExecutionContext.invalidateContext(
        new LoggingProgressMonitor(), DBCInvalidatePhase.BEFORE_INVALIDATE);

    // Assert
    assertTrue(streamExecutionContext.isConnected());
  }
}
