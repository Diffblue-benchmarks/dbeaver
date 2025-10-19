package org.jkiss.dbeaver.model.sql.semantics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.antlr.v4.runtime.misc.Interval;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.jkiss.dbeaver.model.sql.semantics.model.SQLQueryModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLDocumentSyntaxContextDiffblueTest {
  /**
   * Test new {@link SQLDocumentSyntaxContext} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SQLDocumentSyntaxContext}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDocumentSyntaxContext.<init>()"})
  public void testNewSQLDocumentSyntaxContext() {
    // Arrange and Act
    SQLDocumentSyntaxContext actualSqlDocumentSyntaxContext = new SQLDocumentSyntaxContext();

    // Assert
    assertTrue(actualSqlDocumentSyntaxContext.getScriptItems().isEmpty());
    assertEquals(
        Integer.MAX_VALUE, actualSqlDocumentSyntaxContext.getLastAccessedScriptElementOffset());
    assertEquals(Integer.MAX_VALUE, actualSqlDocumentSyntaxContext.getLastAccessedTokenOffset());
  }

  /**
   * Test {@link SQLDocumentSyntaxContext#getScriptItems()}.
   *
   * <p>Method under test: {@link SQLDocumentSyntaxContext#getScriptItems()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLDocumentSyntaxContext.getScriptItems()"})
  public void testGetScriptItems() {
    // Arrange, Act and Assert
    assertTrue(new SQLDocumentSyntaxContext().getScriptItems().isEmpty());
  }

  /**
   * Test {@link SQLDocumentSyntaxContext#findScriptItem(int)}.
   *
   * <ul>
   *   <li>When {@link Integer#MAX_VALUE}.
   * </ul>
   *
   * <p>Method under test: {@link SQLDocumentSyntaxContext#findScriptItem(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLScriptItemAtOffset SQLDocumentSyntaxContext.findScriptItem(int)"})
  public void testFindScriptItem_whenMax_value() {
    // Arrange, Act and Assert
    assertNull(new SQLDocumentSyntaxContext().findScriptItem(Integer.MAX_VALUE));
  }

  /**
   * Test {@link SQLDocumentSyntaxContext#findScriptItem(int)}.
   *
   * <ul>
   *   <li>When two.
   * </ul>
   *
   * <p>Method under test: {@link SQLDocumentSyntaxContext#findScriptItem(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLScriptItemAtOffset SQLDocumentSyntaxContext.findScriptItem(int)"})
  public void testFindScriptItem_whenTwo() {
    // Arrange, Act and Assert
    assertNull(new SQLDocumentSyntaxContext().findScriptItem(2));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLDocumentSyntaxContext#getLastAccessedScriptElementOffset()}
   *   <li>{@link SQLDocumentSyntaxContext#getLastAccessedTokenOffset()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int SQLDocumentSyntaxContext.getLastAccessedScriptElementOffset()",
    "int SQLDocumentSyntaxContext.getLastAccessedTokenOffset()"
  })
  public void testGettersAndSetters() {
    // Arrange
    SQLDocumentSyntaxContext sqlDocumentSyntaxContext = new SQLDocumentSyntaxContext();

    // Act
    int actualLastAccessedScriptElementOffset =
        sqlDocumentSyntaxContext.getLastAccessedScriptElementOffset();

    // Assert
    assertEquals(Integer.MAX_VALUE, actualLastAccessedScriptElementOffset);
    assertEquals(Integer.MAX_VALUE, sqlDocumentSyntaxContext.getLastAccessedTokenOffset());
  }

  /**
   * Test {@link SQLDocumentSyntaxContext#registerScriptItemContext(String, SQLQueryModel, int, int,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link SQLDocumentSyntaxContext} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link SQLDocumentSyntaxContext#registerScriptItemContext(String,
   * SQLQueryModel, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLDocumentScriptItemSyntaxContext SQLDocumentSyntaxContext.registerScriptItemContext(String, SQLQueryModel, int, int, boolean)"
  })
  public void testRegisterScriptItemContext_givenSQLDocumentSyntaxContext() {
    // Arrange
    SQLDocumentSyntaxContext sqlDocumentSyntaxContext = new SQLDocumentSyntaxContext();

    // Act
    SQLDocumentScriptItemSyntaxContext actualRegisterScriptItemContextResult =
        sqlDocumentSyntaxContext.registerScriptItemContext(
            "Element Original Text", null, 2, 3, true);

    // Assert
    assertEquals("Element Original Text", actualRegisterScriptItemContextResult.getOriginalText());
    assertNull(actualRegisterScriptItemContextResult.getProblems());
    assertNull(actualRegisterScriptItemContextResult.getQueryModel());
    List<SQLScriptItemAtOffset> scriptItems = sqlDocumentSyntaxContext.getScriptItems();
    assertEquals(1, scriptItems.size());
    assertEquals(2, actualRegisterScriptItemContextResult.getInitialPosition());
    assertEquals(2, scriptItems.get(0).offset);
    assertEquals(3, actualRegisterScriptItemContextResult.length());
    assertFalse(actualRegisterScriptItemContextResult.isDirty());
    assertTrue(actualRegisterScriptItemContextResult.hasContextBoundaryAtLength());
  }

  /**
   * Test {@link SQLDocumentSyntaxContext#registerScriptItemContext(String, SQLQueryModel, int, int,
   * boolean)}.
   *
   * <ul>
   *   <li>Then calls {@link
   *       SQLDocumentSyntaxContextListener#onScriptItemIntroduced(SQLDocumentScriptItemSyntaxContext)}.
   * </ul>
   *
   * <p>Method under test: {@link SQLDocumentSyntaxContext#registerScriptItemContext(String,
   * SQLQueryModel, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLDocumentScriptItemSyntaxContext SQLDocumentSyntaxContext.registerScriptItemContext(String, SQLQueryModel, int, int, boolean)"
  })
  public void testRegisterScriptItemContext_thenCallsOnScriptItemIntroduced() {
    // Arrange
    SQLDocumentSyntaxContextListener listener = mock(SQLDocumentSyntaxContextListener.class);
    doNothing()
        .when(listener)
        .onScriptItemIntroduced(Mockito.<SQLDocumentScriptItemSyntaxContext>any());

    SQLDocumentSyntaxContext sqlDocumentSyntaxContext = new SQLDocumentSyntaxContext();
    sqlDocumentSyntaxContext.addListener(listener);

    // Act
    SQLDocumentScriptItemSyntaxContext actualRegisterScriptItemContextResult =
        sqlDocumentSyntaxContext.registerScriptItemContext(
            "Element Original Text", null, 2, 3, true);

    // Assert
    verify(listener).onScriptItemIntroduced(isA(SQLDocumentScriptItemSyntaxContext.class));
    assertEquals("Element Original Text", actualRegisterScriptItemContextResult.getOriginalText());
    assertNull(actualRegisterScriptItemContextResult.getProblems());
    assertNull(actualRegisterScriptItemContextResult.getQueryModel());
    List<SQLScriptItemAtOffset> scriptItems = sqlDocumentSyntaxContext.getScriptItems();
    assertEquals(1, scriptItems.size());
    assertEquals(2, actualRegisterScriptItemContextResult.getInitialPosition());
    assertEquals(2, scriptItems.get(0).offset);
    assertEquals(3, actualRegisterScriptItemContextResult.length());
    assertFalse(actualRegisterScriptItemContextResult.isDirty());
    assertTrue(actualRegisterScriptItemContextResult.hasContextBoundaryAtLength());
  }

  /**
   * Test {@link SQLDocumentSyntaxContext#applyDelta(int, int, int)}.
   *
   * <ul>
   *   <li>When {@link Integer#MAX_VALUE}.
   *   <li>Then return Offset is zero.
   * </ul>
   *
   * <p>Method under test: {@link SQLDocumentSyntaxContext#applyDelta(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IRegion SQLDocumentSyntaxContext.applyDelta(int, int, int)"})
  public void testApplyDelta_whenMax_value_thenReturnOffsetIsZero() {
    // Arrange and Act
    IRegion actualApplyDeltaResult =
        new SQLDocumentSyntaxContext().applyDelta(Integer.MAX_VALUE, 0, 3);

    // Assert
    assertTrue(actualApplyDeltaResult instanceof Region);
    assertEquals(0, actualApplyDeltaResult.getOffset());
    assertEquals(Integer.MAX_VALUE, actualApplyDeltaResult.getLength());
  }

  /**
   * Test {@link SQLDocumentSyntaxContext#applyDelta(int, int, int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return Offset is two.
   * </ul>
   *
   * <p>Method under test: {@link SQLDocumentSyntaxContext#applyDelta(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IRegion SQLDocumentSyntaxContext.applyDelta(int, int, int)"})
  public void testApplyDelta_whenOne_thenReturnOffsetIsTwo() {
    // Arrange and Act
    IRegion actualApplyDeltaResult = new SQLDocumentSyntaxContext().applyDelta(2, 1, 3);

    // Assert
    assertTrue(actualApplyDeltaResult instanceof Region);
    assertEquals(2, actualApplyDeltaResult.getOffset());
    assertEquals(3, actualApplyDeltaResult.getLength());
  }

  /**
   * Test {@link SQLDocumentSyntaxContext#applyDelta(int, int, int)}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return Offset is two.
   * </ul>
   *
   * <p>Method under test: {@link SQLDocumentSyntaxContext#applyDelta(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IRegion SQLDocumentSyntaxContext.applyDelta(int, int, int)"})
  public void testApplyDelta_whenThree_thenReturnOffsetIsTwo() {
    // Arrange and Act
    IRegion actualApplyDeltaResult = new SQLDocumentSyntaxContext().applyDelta(2, 3, 3);

    // Assert
    assertTrue(actualApplyDeltaResult instanceof Region);
    assertEquals(2, actualApplyDeltaResult.getOffset());
    assertEquals(3, actualApplyDeltaResult.getLength());
  }

  /**
   * Test {@link SQLDocumentSyntaxContext#applyDelta(int, int, int)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return Offset is zero.
   * </ul>
   *
   * <p>Method under test: {@link SQLDocumentSyntaxContext#applyDelta(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IRegion SQLDocumentSyntaxContext.applyDelta(int, int, int)"})
  public void testApplyDelta_whenZero_thenReturnOffsetIsZero() {
    // Arrange and Act
    IRegion actualApplyDeltaResult = new SQLDocumentSyntaxContext().applyDelta(2, 0, 3);

    // Assert
    assertTrue(actualApplyDeltaResult instanceof Region);
    assertEquals(0, actualApplyDeltaResult.getOffset());
    assertEquals(Integer.MAX_VALUE, actualApplyDeltaResult.getLength());
  }

  /**
   * Test {@link SQLDocumentSyntaxContext#applyDelta(int, int, int)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return Offset is zero.
   * </ul>
   *
   * <p>Method under test: {@link SQLDocumentSyntaxContext#applyDelta(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IRegion SQLDocumentSyntaxContext.applyDelta(int, int, int)"})
  public void testApplyDelta_whenZero_thenReturnOffsetIsZero2() {
    // Arrange and Act
    IRegion actualApplyDeltaResult = new SQLDocumentSyntaxContext().applyDelta(2, 0, 0);

    // Assert
    assertTrue(actualApplyDeltaResult instanceof Region);
    assertEquals(0, actualApplyDeltaResult.getOffset());
    assertEquals(Integer.MAX_VALUE, actualApplyDeltaResult.getLength());
  }

  /**
   * Test {@link SQLDocumentSyntaxContext#clear()}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDocumentSyntaxContextListener#onAllScriptItemsInvalidated()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLDocumentSyntaxContext#clear()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDocumentSyntaxContext.clear()"})
  public void testClear_thenCallsOnAllScriptItemsInvalidated() {
    // Arrange
    SQLDocumentSyntaxContextListener listener = mock(SQLDocumentSyntaxContextListener.class);
    doNothing().when(listener).onAllScriptItemsInvalidated();

    SQLDocumentSyntaxContext sqlDocumentSyntaxContext = new SQLDocumentSyntaxContext();
    sqlDocumentSyntaxContext.addListener(listener);

    // Act
    sqlDocumentSyntaxContext.clear();

    // Assert
    verify(listener).onAllScriptItemsInvalidated();
  }

  /**
   * Test {@link SQLDocumentSyntaxContext#dropInvisibleScriptItems(Interval)}.
   *
   * <ul>
   *   <li>Then return length is zero.
   * </ul>
   *
   * <p>Method under test: {@link SQLDocumentSyntaxContext#dropInvisibleScriptItems(Interval)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Interval SQLDocumentSyntaxContext.dropInvisibleScriptItems(Interval)"})
  public void testDropInvisibleScriptItems_thenReturnLengthIsZero() {
    // Arrange
    SQLDocumentSyntaxContext sqlDocumentSyntaxContext = new SQLDocumentSyntaxContext();

    // Act
    Interval actualDropInvisibleScriptItemsResult =
        sqlDocumentSyntaxContext.dropInvisibleScriptItems(Interval.of(1, 1));

    // Assert
    assertEquals(0, actualDropInvisibleScriptItemsResult.length());
    assertEquals(0, actualDropInvisibleScriptItemsResult.b);
    assertEquals(Integer.MAX_VALUE, actualDropInvisibleScriptItemsResult.a);
  }
}
