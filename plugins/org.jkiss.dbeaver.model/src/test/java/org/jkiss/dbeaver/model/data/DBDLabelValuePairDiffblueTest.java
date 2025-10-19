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

public class DBDLabelValuePairDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBDLabelValuePair#DBDLabelValuePair(String, Object)}
   *   <li>{@link DBDLabelValuePair#setValue(Object)}
   *   <li>{@link DBDLabelValuePair#toString()}
   *   <li>{@link DBDLabelValuePair#getLabel()}
   *   <li>{@link DBDLabelValuePair#getValue()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDLabelValuePair.<init>(String, Object)",
    "String DBDLabelValuePair.getLabel()",
    "Object DBDLabelValuePair.getValue()",
    "void DBDLabelValuePair.setValue(Object)",
    "String DBDLabelValuePair.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBDLabelValuePair actualDbdLabelValuePair = new DBDLabelValuePair("Label", DBPEvent.RENAME);
    Object object = DBPEvent.RENAME;
    actualDbdLabelValuePair.setValue(object);
    actualDbdLabelValuePair.toString();
    String actualLabel = actualDbdLabelValuePair.getLabel();

    // Assert
    assertEquals("Label", actualLabel);
    assertSame(object, actualDbdLabelValuePair.getValue());
  }

  /**
   * Test {@link DBDLabelValuePair#compareTo(Object)}.
   *
   * <ul>
   *   <li>Given {@link DBDDocumentXML#DBDDocumentXML(Document)} with document is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDLabelValuePair#compareTo(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDLabelValuePair.compareTo(Object)"})
  public void testCompareTo_givenDBDDocumentXMLWithDocumentIsNull() {
    // Arrange
    DBDLabelValuePair dbdLabelValuePair = new DBDLabelValuePair("Label", new DBDDocumentXML(null));

    // Act
    int actualCompareToResult = dbdLabelValuePair.compareTo(new DBDLabelValuePair("Label", 1));

    // Assert
    assertEquals(0, actualCompareToResult);
  }

  /**
   * Test {@link DBDLabelValuePair#compareTo(Object)}.
   *
   * <ul>
   *   <li>Given {@link DBDLabelValuePair#DBDLabelValuePair(String, Object)} with {@code Label} and
   *       value is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link DBDLabelValuePair#compareTo(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDLabelValuePair.compareTo(Object)"})
  public void testCompareTo_givenDBDLabelValuePairWithLabelAndValueIsFortyTwo() {
    // Arrange
    DBDLabelValuePair dbdLabelValuePair = new DBDLabelValuePair("Label", 42);

    // Act
    int actualCompareToResult =
        dbdLabelValuePair.compareTo(new DBDLabelValuePair("Label", DBPEvent.RENAME));

    // Assert
    assertEquals(0, actualCompareToResult);
  }

  /**
   * Test {@link DBDLabelValuePair#compareTo(Object)}.
   *
   * <ul>
   *   <li>Given {@link DBDLabelValuePair#DBDLabelValuePair(String, Object)} with {@code Label} and
   *       value is forty-two.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link DBDLabelValuePair#compareTo(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDLabelValuePair.compareTo(Object)"})
  public void testCompareTo_givenDBDLabelValuePairWithLabelAndValueIsFortyTwo_thenReturnOne() {
    // Arrange
    DBDLabelValuePair dbdLabelValuePair = new DBDLabelValuePair("Label", 42);

    // Act
    int actualCompareToResult = dbdLabelValuePair.compareTo(new DBDLabelValuePair("Label", 1));

    // Assert
    assertEquals(1, actualCompareToResult);
  }

  /**
   * Test {@link DBDLabelValuePair#compareTo(Object)}.
   *
   * <ul>
   *   <li>Given {@link DBDLabelValuePair#DBDLabelValuePair(String, Object)} with {@code Label} and
   *       value is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDLabelValuePair#compareTo(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDLabelValuePair.compareTo(Object)"})
  public void testCompareTo_givenDBDLabelValuePairWithLabelAndValueIsNull() {
    // Arrange
    DBDLabelValuePair dbdLabelValuePair = new DBDLabelValuePair("Label", null);
    DBDLabelValuePair dbdLabelValuePair2 = new DBDLabelValuePair("Label", null);

    // Act
    int actualCompareToResult = dbdLabelValuePair.compareTo(dbdLabelValuePair2);

    // Assert
    assertEquals(0, actualCompareToResult);
  }

  /**
   * Test {@link DBDLabelValuePair#compareTo(Object)}.
   *
   * <ul>
   *   <li>Given {@link DBDLabelValuePair#DBDLabelValuePair(String, Object)} with {@code Label} and
   *       value is {@code null}.
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link DBDLabelValuePair#compareTo(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDLabelValuePair.compareTo(Object)"})
  public void testCompareTo_givenDBDLabelValuePairWithLabelAndValueIsNull_thenReturnMinusOne() {
    // Arrange
    DBDLabelValuePair dbdLabelValuePair = new DBDLabelValuePair("Label", null);

    // Act
    int actualCompareToResult =
        dbdLabelValuePair.compareTo(new DBDLabelValuePair("Label", DBPEvent.RENAME));

    // Assert
    assertEquals(-1, actualCompareToResult);
  }

  /**
   * Test {@link DBDLabelValuePair#compareTo(Object)}.
   *
   * <ul>
   *   <li>Given {@link DBDLabelValuePair#DBDLabelValuePair(String, Object)} with {@code Label} and
   *       value is {@link DBPEvent#RENAME}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link DBDLabelValuePair#compareTo(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDLabelValuePair.compareTo(Object)"})
  public void testCompareTo_givenDBDLabelValuePairWithLabelAndValueIsRename_thenReturnOne() {
    // Arrange
    DBDLabelValuePair dbdLabelValuePair = new DBDLabelValuePair("Label", DBPEvent.RENAME);
    DBDLabelValuePair dbdLabelValuePair2 = new DBDLabelValuePair("Label", null);

    // Act
    int actualCompareToResult = dbdLabelValuePair.compareTo(dbdLabelValuePair2);

    // Assert
    assertEquals(1, actualCompareToResult);
  }

  /**
   * Test {@link DBDLabelValuePair#compareTo(Object)}.
   *
   * <ul>
   *   <li>Given {@link DBDLabelValuePair#DBDLabelValuePair(String, Object)} with {@code Label} and
   *       value is {@link DBPEvent#RENAME}.
   *   <li>When {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBDLabelValuePair#compareTo(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDLabelValuePair.compareTo(Object)"})
  public void testCompareTo_givenDBDLabelValuePairWithLabelAndValueIsRename_whenRename() {
    // Arrange, Act and Assert
    assertEquals(0, new DBDLabelValuePair("Label", DBPEvent.RENAME).compareTo(DBPEvent.RENAME));
  }

  /**
   * Test {@link DBDLabelValuePair#compareTo(Object)}.
   *
   * <ul>
   *   <li>Given {@link DBDLabelValuePair#DBDLabelValuePair(String, Object)} with {@code Label} and
   *       value is zero.
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link DBDLabelValuePair#compareTo(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDLabelValuePair.compareTo(Object)"})
  public void testCompareTo_givenDBDLabelValuePairWithLabelAndValueIsZero_thenReturnMinusOne() {
    // Arrange
    DBDLabelValuePair dbdLabelValuePair = new DBDLabelValuePair("Label", 0);

    // Act
    int actualCompareToResult = dbdLabelValuePair.compareTo(new DBDLabelValuePair("Label", 1));

    // Assert
    assertEquals(-1, actualCompareToResult);
  }

  /**
   * Test {@link DBDLabelValuePair#compareTo(Object)}.
   *
   * <ul>
   *   <li>Then return minus thirty-four.
   * </ul>
   *
   * <p>Method under test: {@link DBDLabelValuePair#compareTo(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDLabelValuePair.compareTo(Object)"})
  public void testCompareTo_thenReturnMinusThirtyFour() {
    // Arrange
    DBDLabelValuePair dbdLabelValuePair = new DBDLabelValuePair("Label", "42");

    // Act
    int actualCompareToResult =
        dbdLabelValuePair.compareTo(new DBDLabelValuePair("Label", "Value"));

    // Assert
    assertEquals(-34, actualCompareToResult);
  }
}
