package org.jkiss.dbeaver.registry.expressions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.impl.data.StringContent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ContentExpressionFunctionsDiffblueTest {
  /**
   * Test {@link ContentExpressionFunctions#json(Object)}.
   *
   * <ul>
   *   <li>Given {@link DBPEvent#RENAME}.
   *   <li>When {@link HashMap#HashMap()} {@link DBPEvent#RENAME} is {@link DBPEvent#RENAME}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link ContentExpressionFunctions#json(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ContentExpressionFunctions.json(Object)"})
  public void testJson_givenRename_whenHashMapRenameIsRename_thenReturnSizeIsOne() {
    // Arrange
    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put(DBPEvent.RENAME, DBPEvent.RENAME);

    // Act
    Object actualJsonResult = ContentExpressionFunctions.json(objectObjectMap);

    // Assert
    assertTrue(actualJsonResult instanceof Map);
    assertEquals(1, ((Map<String, String>) actualJsonResult).size());
  }

  /**
   * Test {@link ContentExpressionFunctions#json(Object)}.
   *
   * <ul>
   *   <li>Given {@link DBPEvent#REORDER}.
   *   <li>When {@link HashMap#HashMap()} {@link DBPEvent#REORDER} is {@link DBPEvent#RENAME}.
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link ContentExpressionFunctions#json(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ContentExpressionFunctions.json(Object)"})
  public void testJson_givenReorder_whenHashMapReorderIsRename_thenReturnSizeIsTwo() {
    // Arrange
    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put(DBPEvent.REORDER, DBPEvent.RENAME);
    objectObjectMap.put(DBPEvent.RENAME, DBPEvent.RENAME);

    // Act
    Object actualJsonResult = ContentExpressionFunctions.json(objectObjectMap);

    // Assert
    assertTrue(actualJsonResult instanceof Map);
    assertEquals(2, ((Map<String, String>) actualJsonResult).size());
  }

  /**
   * Test {@link ContentExpressionFunctions#json(Object)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link ContentExpressionFunctions#json(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ContentExpressionFunctions.json(Object)"})
  public void testJson_whenEmptyString_thenReturnEmpty() {
    // Arrange and Act
    Object actualJsonResult = ContentExpressionFunctions.json("");

    // Assert
    assertTrue(actualJsonResult instanceof Map);
    assertTrue(((Map<Object, Object>) actualJsonResult).isEmpty());
  }

  /**
   * Test {@link ContentExpressionFunctions#json(Object)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then return {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link ContentExpressionFunctions#json(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ContentExpressionFunctions.json(Object)"})
  public void testJson_whenHashMap_thenReturnHashMap() {
    // Arrange
    HashMap<Object, Object> objectObjectMap = new HashMap<>();

    // Act
    Object actualJsonResult = ContentExpressionFunctions.json(objectObjectMap);

    // Assert
    assertEquals(objectObjectMap, actualJsonResult);
  }

  /**
   * Test {@link ContentExpressionFunctions#json(Object)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ContentExpressionFunctions#json(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ContentExpressionFunctions.json(Object)"})
  public void testJson_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(ContentExpressionFunctions.json(null));
  }

  /**
   * Test {@link ContentExpressionFunctions#xml(Object, String)} with {@code object}, {@code
   * expression}.
   *
   * <p>Method under test: {@link ContentExpressionFunctions#xml(Object, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ContentExpressionFunctions.xml(Object, String)"})
  public void testXmlWithObjectExpression() {
    // Arrange, Act and Assert
    assertNull(
        ContentExpressionFunctions.xml(
            new StringContent(mock(DBCExecutionContext.class), "string"), "Expression"));
  }

  /**
   * Test {@link ContentExpressionFunctions#xml(Object, String)} with {@code object}, {@code
   * expression}.
   *
   * <ul>
   *   <li>When empty string.
   * </ul>
   *
   * <p>Method under test: {@link ContentExpressionFunctions#xml(Object, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ContentExpressionFunctions.xml(Object, String)"})
  public void testXmlWithObjectExpression_whenEmptyString() {
    // Arrange, Act and Assert
    assertNull(ContentExpressionFunctions.xml(DBPEvent.RENAME, ""));
  }

  /**
   * Test {@link ContentExpressionFunctions#xml(Object, String)} with {@code object}, {@code
   * expression}.
   *
   * <ul>
   *   <li>When {@code Expression}.
   * </ul>
   *
   * <p>Method under test: {@link ContentExpressionFunctions#xml(Object, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ContentExpressionFunctions.xml(Object, String)"})
  public void testXmlWithObjectExpression_whenExpression() {
    // Arrange, Act and Assert
    assertNull(ContentExpressionFunctions.xml(DBPEvent.RENAME, "Expression"));
  }

  /**
   * Test {@link ContentExpressionFunctions#xml(Object, String)} with {@code object}, {@code
   * expression}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ContentExpressionFunctions#xml(Object, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ContentExpressionFunctions.xml(Object, String)"})
  public void testXmlWithObjectExpression_whenNull() {
    // Arrange, Act and Assert
    assertNull(ContentExpressionFunctions.xml(null, ""));
  }

  /**
   * Test {@link ContentExpressionFunctions#xml(Object, String)} with {@code object}, {@code
   * expression}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ContentExpressionFunctions#xml(Object, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ContentExpressionFunctions.xml(Object, String)"})
  public void testXmlWithObjectExpression_whenNull2() {
    // Arrange, Act and Assert
    assertNull(ContentExpressionFunctions.xml(DBPEvent.RENAME, null));
  }

  /**
   * Test {@link ContentExpressionFunctions#xml(Object, String, String)} with {@code object}, {@code
   * returnType}, {@code expression}.
   *
   * <p>Method under test: {@link ContentExpressionFunctions#xml(Object, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ContentExpressionFunctions.xml(Object, String, String)"})
  public void testXmlWithObjectReturnTypeExpression() {
    // Arrange and Act
    Object actualXmlResult =
        ContentExpressionFunctions.xml(
            new StringContent(mock(DBCExecutionContext.class), "Data"),
            "Return Type",
            "Expression");

    // Assert
    assertNull(actualXmlResult);
  }

  /**
   * Test {@link ContentExpressionFunctions#xml(Object, String, String)} with {@code object}, {@code
   * returnType}, {@code expression}.
   *
   * <ul>
   *   <li>When empty string.
   * </ul>
   *
   * <p>Method under test: {@link ContentExpressionFunctions#xml(Object, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ContentExpressionFunctions.xml(Object, String, String)"})
  public void testXmlWithObjectReturnTypeExpression_whenEmptyString() {
    // Arrange and Act
    Object actualXmlResult = ContentExpressionFunctions.xml(DBPEvent.RENAME, "Return Type", "");

    // Assert
    assertNull(actualXmlResult);
  }

  /**
   * Test {@link ContentExpressionFunctions#xml(Object, String, String)} with {@code object}, {@code
   * returnType}, {@code expression}.
   *
   * <ul>
   *   <li>When {@code Expression}.
   * </ul>
   *
   * <p>Method under test: {@link ContentExpressionFunctions#xml(Object, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ContentExpressionFunctions.xml(Object, String, String)"})
  public void testXmlWithObjectReturnTypeExpression_whenExpression() {
    // Arrange and Act
    Object actualXmlResult =
        ContentExpressionFunctions.xml(DBPEvent.RENAME, "Return Type", "Expression");

    // Assert
    assertNull(actualXmlResult);
  }

  /**
   * Test {@link ContentExpressionFunctions#xml(Object, String, String)} with {@code object}, {@code
   * returnType}, {@code expression}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ContentExpressionFunctions#xml(Object, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ContentExpressionFunctions.xml(Object, String, String)"})
  public void testXmlWithObjectReturnTypeExpression_whenNull() {
    // Arrange and Act
    Object actualXmlResult = ContentExpressionFunctions.xml(null, "Return Type", "");

    // Assert
    assertNull(actualXmlResult);
  }

  /**
   * Test {@link ContentExpressionFunctions#xml(Object, String, String)} with {@code object}, {@code
   * returnType}, {@code expression}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ContentExpressionFunctions#xml(Object, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ContentExpressionFunctions.xml(Object, String, String)"})
  public void testXmlWithObjectReturnTypeExpression_whenNull2() {
    // Arrange and Act
    Object actualXmlResult = ContentExpressionFunctions.xml(DBPEvent.RENAME, "Return Type", null);

    // Assert
    assertNull(actualXmlResult);
  }
}
