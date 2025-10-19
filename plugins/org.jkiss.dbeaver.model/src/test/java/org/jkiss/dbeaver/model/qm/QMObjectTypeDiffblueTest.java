package org.jkiss.dbeaver.model.qm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import org.jkiss.dbeaver.model.qm.meta.QMMetaObjectType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QMObjectTypeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link QMObjectType#getTitle()}
   *   <li>{@link QMObjectType#getTypes()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMObjectType.getTitle()", "List QMObjectType.getTypes()"})
  public void testGettersAndSetters() {
    // Arrange
    QMObjectType valueOfResult = QMObjectType.valueOf("session");

    // Act
    String actualTitle = valueOfResult.getTitle();
    List<QMMetaObjectType> actualTypes = valueOfResult.getTypes();

    // Assert
    assertEquals("Session", actualTitle);
    assertEquals(1, actualTypes.size());
    assertEquals(QMMetaObjectType.CONNECTION_INFO, actualTypes.get(0));
  }

  /**
   * Test {@link QMObjectType#toString(Collection)} with {@code Collection}.
   *
   * <ul>
   *   <li>Given {@code txn}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code txn}.
   *   <li>Then return {@code txn,session}.
   * </ul>
   *
   * <p>Method under test: {@link QMObjectType#toString(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMObjectType.toString(Collection)"})
  public void testToStringWithCollection_givenTxn_whenArrayListAddTxn_thenReturnTxnSession() {
    // Arrange
    ArrayList<QMObjectType> objectTypes = new ArrayList<>();
    objectTypes.add(QMObjectType.txn);
    objectTypes.add(QMObjectType.session);

    // Act and Assert
    assertEquals("txn,session", QMObjectType.toString(objectTypes));
  }

  /**
   * Test {@link QMObjectType#toString(Collection)} with {@code Collection}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link QMObjectType#toString(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMObjectType.toString(Collection)"})
  public void testToStringWithCollection_whenArrayList_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", QMObjectType.toString(new ArrayList<>()));
  }

  /**
   * Test {@link QMObjectType#toString(Collection)} with {@code Collection}.
   *
   * <ul>
   *   <li>When {@link LinkedHashSet#LinkedHashSet()} add {@code session}.
   *   <li>Then return {@code session}.
   * </ul>
   *
   * <p>Method under test: {@link QMObjectType#toString(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMObjectType.toString(Collection)"})
  public void testToStringWithCollection_whenLinkedHashSetAddSession_thenReturnSession() {
    // Arrange
    LinkedHashSet<QMObjectType> objectTypes = new LinkedHashSet<>();
    objectTypes.add(QMObjectType.session);

    // Act and Assert
    assertEquals("session", QMObjectType.toString(objectTypes));
  }

  /**
   * Test {@link QMObjectType#fromString(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link QMObjectType#fromString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection QMObjectType.fromString(String)"})
  public void testFromString_whenEmptyString_thenReturnEmpty() {
    // Arrange and Act
    Collection<QMObjectType> actualFromStringResult = QMObjectType.fromString("");

    // Assert
    assertTrue(actualFromStringResult instanceof List);
    assertTrue(actualFromStringResult.isEmpty());
  }

  /**
   * Test {@link QMObjectType#fromString(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link QMObjectType#fromString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection QMObjectType.fromString(String)"})
  public void testFromString_whenNull_thenReturnEmpty() {
    // Arrange and Act
    Collection<QMObjectType> actualFromStringResult = QMObjectType.fromString(null);

    // Assert
    assertTrue(actualFromStringResult instanceof List);
    assertTrue(actualFromStringResult.isEmpty());
  }

  /**
   * Test {@link QMObjectType#fromString(String)}.
   *
   * <ul>
   *   <li>When {@code query}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link QMObjectType#fromString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection QMObjectType.fromString(String)"})
  public void testFromString_whenQuery_thenReturnSizeIsOne() {
    // Arrange and Act
    Collection<QMObjectType> actualFromStringResult = QMObjectType.fromString("query");

    // Assert
    assertTrue(actualFromStringResult instanceof List);
    assertEquals(1, actualFromStringResult.size());
    assertEquals(QMObjectType.query, ((List<QMObjectType>) actualFromStringResult).get(0));
  }

  /**
   * Test {@link QMObjectType#fromString(String)}.
   *
   * <ul>
   *   <li>When {@code Str}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link QMObjectType#fromString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection QMObjectType.fromString(String)"})
  public void testFromString_whenStr_thenReturnEmpty() {
    // Arrange and Act
    Collection<QMObjectType> actualFromStringResult = QMObjectType.fromString("Str");

    // Assert
    assertTrue(actualFromStringResult instanceof List);
    assertTrue(actualFromStringResult.isEmpty());
  }
}
