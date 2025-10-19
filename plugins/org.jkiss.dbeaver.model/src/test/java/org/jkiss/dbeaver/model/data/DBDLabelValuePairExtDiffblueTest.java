package org.jkiss.dbeaver.model.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.impl.data.DBDDocumentXML;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBDLabelValuePairExtDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBDLabelValuePairExt#DBDLabelValuePairExt(String, Object, long)}
   *   <li>{@link DBDLabelValuePairExt#incCount()}
   *   <li>{@link DBDLabelValuePairExt#getCount()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDLabelValuePairExt.<init>(String, Object, long)",
    "long DBDLabelValuePairExt.getCount()",
    "void DBDLabelValuePairExt.incCount()"
  })
  public void testGettersAndSetters() {
    // Arrange
    Object object = DBPEvent.RENAME;

    // Act
    DBDLabelValuePairExt actualDbdLabelValuePairExt = new DBDLabelValuePairExt("Label", object, 3L);
    actualDbdLabelValuePairExt.incCount();
    long actualCount = actualDbdLabelValuePairExt.getCount();

    // Assert
    assertEquals("Label", actualDbdLabelValuePairExt.getLabel());
    assertEquals(4L, actualCount);
    assertSame(object, actualDbdLabelValuePairExt.getValue());
  }

  /**
   * Test {@link DBDLabelValuePairExt#compareTo(Object)}.
   *
   * <p>Method under test: {@link DBDLabelValuePairExt#compareTo(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDLabelValuePairExt.compareTo(Object)"})
  public void testCompareTo() {
    // Arrange
    DBDLabelValuePairExt dbdLabelValuePairExt = new DBDLabelValuePairExt("Label", 42, 3L);

    // Act
    int actualCompareToResult =
        dbdLabelValuePairExt.compareTo(new DBDLabelValuePair("Label", DBPEvent.RENAME));

    // Assert
    assertEquals(0, actualCompareToResult);
  }

  /**
   * Test {@link DBDLabelValuePairExt#compareTo(Object)}.
   *
   * <ul>
   *   <li>Given {@link DBDDocumentXML#DBDDocumentXML(Document)} with document is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDLabelValuePairExt#compareTo(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDLabelValuePairExt.compareTo(Object)"})
  public void testCompareTo_givenDBDDocumentXMLWithDocumentIsNull() {
    // Arrange
    DBDLabelValuePairExt dbdLabelValuePairExt =
        new DBDLabelValuePairExt("Label", new DBDDocumentXML(null), 3L);

    // Act
    int actualCompareToResult = dbdLabelValuePairExt.compareTo(new DBDLabelValuePair("Label", 1));

    // Assert
    assertEquals(0, actualCompareToResult);
  }

  /**
   * Test {@link DBDLabelValuePairExt#compareTo(Object)}.
   *
   * <ul>
   *   <li>Given {@link DBDLabelValuePairExt#DBDLabelValuePairExt(String, Object, long)} with {@code
   *       Label} and value is {@code null} and count is three.
   * </ul>
   *
   * <p>Method under test: {@link DBDLabelValuePairExt#compareTo(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDLabelValuePairExt.compareTo(Object)"})
  public void testCompareTo_givenDBDLabelValuePairExtWithLabelAndValueIsNullAndCountIsThree() {
    // Arrange
    DBDLabelValuePairExt dbdLabelValuePairExt = new DBDLabelValuePairExt("Label", null, 3L);
    DBDLabelValuePair dbdLabelValuePair = new DBDLabelValuePair("Label", null);

    // Act
    int actualCompareToResult = dbdLabelValuePairExt.compareTo(dbdLabelValuePair);

    // Assert
    assertEquals(0, actualCompareToResult);
  }

  /**
   * Test {@link DBDLabelValuePairExt#compareTo(Object)}.
   *
   * <ul>
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link DBDLabelValuePairExt#compareTo(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDLabelValuePairExt.compareTo(Object)"})
  public void testCompareTo_thenReturnMinusOne() {
    // Arrange
    DBDLabelValuePairExt dbdLabelValuePairExt = new DBDLabelValuePairExt("Label", null, 3L);

    // Act
    int actualCompareToResult =
        dbdLabelValuePairExt.compareTo(new DBDLabelValuePair("Label", DBPEvent.RENAME));

    // Assert
    assertEquals(-1, actualCompareToResult);
  }

  /**
   * Test {@link DBDLabelValuePairExt#compareTo(Object)}.
   *
   * <ul>
   *   <li>Then return minus thirty-four.
   * </ul>
   *
   * <p>Method under test: {@link DBDLabelValuePairExt#compareTo(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDLabelValuePairExt.compareTo(Object)"})
  public void testCompareTo_thenReturnMinusThirtyFour() {
    // Arrange
    DBDLabelValuePairExt dbdLabelValuePairExt = new DBDLabelValuePairExt("Label", "42", 3L);
    DBDLabelValuePairExt dbdLabelValuePairExt2 = new DBDLabelValuePairExt("Label", "Value", 3L);

    // Act
    int actualCompareToResult = dbdLabelValuePairExt.compareTo(dbdLabelValuePairExt2);

    // Assert
    assertEquals(-34, actualCompareToResult);
  }

  /**
   * Test {@link DBDLabelValuePairExt#compareTo(Object)}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link DBDLabelValuePairExt#compareTo(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDLabelValuePairExt.compareTo(Object)"})
  public void testCompareTo_thenReturnOne() {
    // Arrange
    DBDLabelValuePairExt dbdLabelValuePairExt = new DBDLabelValuePairExt("Label", 42, 3L);

    // Act
    int actualCompareToResult = dbdLabelValuePairExt.compareTo(new DBDLabelValuePair("Label", 1));

    // Assert
    assertEquals(1, actualCompareToResult);
  }

  /**
   * Test {@link DBDLabelValuePairExt#compareTo(Object)}.
   *
   * <ul>
   *   <li>Then return two.
   * </ul>
   *
   * <p>Method under test: {@link DBDLabelValuePairExt#compareTo(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDLabelValuePairExt.compareTo(Object)"})
  public void testCompareTo_thenReturnTwo() {
    // Arrange
    DBDLabelValuePairExt dbdLabelValuePairExt =
        new DBDLabelValuePairExt("Label", DBPEvent.RENAME, 1L);
    DBDLabelValuePairExt dbdLabelValuePairExt2 =
        new DBDLabelValuePairExt("Label", DBPEvent.RENAME, 3L);

    // Act
    int actualCompareToResult = dbdLabelValuePairExt.compareTo(dbdLabelValuePairExt2);

    // Assert
    assertEquals(2, actualCompareToResult);
  }

  /**
   * Test {@link DBDLabelValuePairExt#compareTo(Object)}.
   *
   * <ul>
   *   <li>When {@link DBDLabelValuePairExt#DBDLabelValuePairExt(String, Object, long)} with {@code
   *       Label} and value is {@link DBPEvent#RENAME} and count is three.
   * </ul>
   *
   * <p>Method under test: {@link DBDLabelValuePairExt#compareTo(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDLabelValuePairExt.compareTo(Object)"})
  public void testCompareTo_whenDBDLabelValuePairExtWithLabelAndValueIsRenameAndCountIsThree() {
    // Arrange
    DBDLabelValuePairExt dbdLabelValuePairExt =
        new DBDLabelValuePairExt("Label", DBPEvent.RENAME, 3L);
    DBDLabelValuePairExt dbdLabelValuePairExt2 =
        new DBDLabelValuePairExt("Label", DBPEvent.RENAME, 3L);

    // Act
    int actualCompareToResult = dbdLabelValuePairExt.compareTo(dbdLabelValuePairExt2);

    // Assert
    assertEquals(0, actualCompareToResult);
  }

  /**
   * Test {@link DBDLabelValuePairExt#compareTo(Object)}.
   *
   * <ul>
   *   <li>When {@link DBDLabelValuePair#DBDLabelValuePair(String, Object)} with {@code Label} and
   *       value is {@code null}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link DBDLabelValuePairExt#compareTo(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDLabelValuePairExt.compareTo(Object)"})
  public void testCompareTo_whenDBDLabelValuePairWithLabelAndValueIsNull_thenReturnOne() {
    // Arrange
    DBDLabelValuePairExt dbdLabelValuePairExt =
        new DBDLabelValuePairExt("Label", DBPEvent.RENAME, 3L);
    DBDLabelValuePair dbdLabelValuePair = new DBDLabelValuePair("Label", null);

    // Act
    int actualCompareToResult = dbdLabelValuePairExt.compareTo(dbdLabelValuePair);

    // Assert
    assertEquals(1, actualCompareToResult);
  }

  /**
   * Test {@link DBDLabelValuePairExt#compareTo(Object)}.
   *
   * <ul>
   *   <li>When {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBDLabelValuePairExt#compareTo(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDLabelValuePairExt.compareTo(Object)"})
  public void testCompareTo_whenRename() {
    // Arrange
    DBDLabelValuePairExt dbdLabelValuePairExt =
        new DBDLabelValuePairExt("Label", DBPEvent.RENAME, 3L);

    // Act and Assert
    assertEquals(0, dbdLabelValuePairExt.compareTo(DBPEvent.RENAME));
  }
}
