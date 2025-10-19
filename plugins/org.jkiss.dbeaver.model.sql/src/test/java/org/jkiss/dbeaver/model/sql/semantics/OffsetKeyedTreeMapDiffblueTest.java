package org.jkiss.dbeaver.model.sql.semantics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.BiConsumer;
import org.jkiss.dbeaver.model.sql.semantics.OffsetKeyedTreeMap.RemappingFunction;
import org.jkiss.dbeaver.model.sql.semantics.OffsetKeyedTreeMap.ValueAndOffset;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class OffsetKeyedTreeMapDiffblueTest {
  /**
   * Test new {@link OffsetKeyedTreeMap} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link OffsetKeyedTreeMap}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OffsetKeyedTreeMap.<init>()"})
  public void testNewOffsetKeyedTreeMap() {
    // Arrange and Act
    OffsetKeyedTreeMap<Object> actualOffsetKeyedTreeMap = new OffsetKeyedTreeMap<>();

    // Assert
    assertEquals(0, actualOffsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#find(int)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) one is {@code Value}.
   *   <li>When one.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#find(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.find(int)"})
  public void testFind_givenOffsetKeyedTreeMapOneIsValue_whenOne_thenReturnValue() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(1, "Value");

    // Act and Assert
    assertEquals("Value", offsetKeyedTreeMap.find(1));
  }

  /**
   * Test {@link OffsetKeyedTreeMap#find(int)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) one is {@code Value}.
   *   <li>When zero.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#find(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.find(int)"})
  public void testFind_givenOffsetKeyedTreeMapOneIsValue_whenZero_thenReturnNull() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(1, "Value");

    // Act and Assert
    assertNull(offsetKeyedTreeMap.find(0));
  }

  /**
   * Test {@link OffsetKeyedTreeMap#find(int)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) zero is {@code Value}.
   *   <li>When one.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#find(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.find(int)"})
  public void testFind_givenOffsetKeyedTreeMapZeroIsValue_whenOne_thenReturnValue() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(1, "Value");

    // Act and Assert
    assertEquals("Value", offsetKeyedTreeMap.find(1));
  }

  /**
   * Test {@link OffsetKeyedTreeMap#find(int)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor).
   *   <li>When one.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#find(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.find(int)"})
  public void testFind_givenOffsetKeyedTreeMap_whenOne_thenReturnNull() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();

    // Act and Assert
    assertNull(offsetKeyedTreeMap.find(1));
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)} with {@code pos}, {@code
   * value}, {@code remappingFunction}.
   *
   * <ul>
   *   <li>Given {@code Apply}.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object, RemappingFunction)"})
  public void testPutWithPosValueRemappingFunction_givenApply_thenReturnValue() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(1, "Value");

    RemappingFunction<Object> remappingFunction = mock(RemappingFunction.class);
    when(remappingFunction.apply(anyInt(), Mockito.<Object>any(), Mockito.<Object>any()))
        .thenReturn("Apply");

    // Act
    Object actualPutResult = offsetKeyedTreeMap.put(1, "Value", remappingFunction);

    // Assert
    verify(remappingFunction).apply(eq(1), isA(Object.class), isA(Object.class));
    assertEquals("Value", actualPutResult);
    assertEquals(1, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)} with {@code pos}, {@code
   * value}, {@code remappingFunction}.
   *
   * <ul>
   *   <li>Given {@code Apply}.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object, RemappingFunction)"})
  public void testPutWithPosValueRemappingFunction_givenApply_thenReturnValue2() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(3, "Value");
    offsetKeyedTreeMap.put(1, "Value");

    RemappingFunction<Object> remappingFunction = mock(RemappingFunction.class);
    when(remappingFunction.apply(anyInt(), Mockito.<Object>any(), Mockito.<Object>any()))
        .thenReturn("Apply");

    // Act
    Object actualPutResult = offsetKeyedTreeMap.put(1, "Value", remappingFunction);

    // Assert
    verify(remappingFunction).apply(eq(1), isA(Object.class), isA(Object.class));
    assertEquals("Value", actualPutResult);
    assertEquals(2, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)} with {@code pos}, {@code
   * value}, {@code remappingFunction}.
   *
   * <ul>
   *   <li>Given {@link IllegalStateException#IllegalStateException()}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object, RemappingFunction)"})
  public void testPutWithPosValueRemappingFunction_givenIllegalStateException() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(1, "Value");

    RemappingFunction<Object> remappingFunction = mock(RemappingFunction.class);
    when(remappingFunction.apply(anyInt(), Mockito.<Object>any(), Mockito.<Object>any()))
        .thenThrow(new IllegalStateException());

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> offsetKeyedTreeMap.put(1, "Value", remappingFunction));
    verify(remappingFunction).apply(eq(1), isA(Object.class), isA(Object.class));
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)} with {@code pos}, {@code
   * value}, {@code remappingFunction}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object, RemappingFunction)"})
  public void testPutWithPosValueRemappingFunction_givenOffsetKeyedTreeMap() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();

    // Act and Assert
    assertNull(offsetKeyedTreeMap.put(1, "Value", mock(RemappingFunction.class)));
    assertEquals(1, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)} with {@code pos}, {@code
   * value}, {@code remappingFunction}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) {@link Integer#MAX_VALUE} is
   *       {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object, RemappingFunction)"})
  public void testPutWithPosValueRemappingFunction_givenOffsetKeyedTreeMapMax_valueIsValue() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(-2147483647, "Value");
    offsetKeyedTreeMap.put(Integer.MAX_VALUE, "Value");
    offsetKeyedTreeMap.put(0, "Value");

    // Act and Assert
    assertNull(offsetKeyedTreeMap.put(1, "Value", mock(RemappingFunction.class)));
    assertEquals(4, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)} with {@code pos}, {@code
   * value}, {@code remappingFunction}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) {@link Integer#MIN_VALUE} is
   *       {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object, RemappingFunction)"})
  public void testPutWithPosValueRemappingFunction_givenOffsetKeyedTreeMapMin_valueIsValue() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");
    offsetKeyedTreeMap.put(0, "Value");

    // Act and Assert
    assertNull(offsetKeyedTreeMap.put(1, "Value", mock(RemappingFunction.class)));
    assertEquals(3, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)} with {@code pos}, {@code
   * value}, {@code remappingFunction}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) one is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object, RemappingFunction)"})
  public void testPutWithPosValueRemappingFunction_givenOffsetKeyedTreeMapOneIsNull() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(1, null);

    // Act and Assert
    assertNull(offsetKeyedTreeMap.put(1, "Value", mock(RemappingFunction.class)));
    assertEquals(2, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)} with {@code pos}, {@code
   * value}, {@code remappingFunction}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) two is {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object, RemappingFunction)"})
  public void testPutWithPosValueRemappingFunction_givenOffsetKeyedTreeMapTwoIsValue() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(3, "Value");
    offsetKeyedTreeMap.put(2, "Value");

    // Act and Assert
    assertNull(offsetKeyedTreeMap.put(1, "Value", mock(RemappingFunction.class)));
    assertEquals(3, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)} with {@code pos}, {@code
   * value}, {@code remappingFunction}.
   *
   * <ul>
   *   <li>Then {@link OffsetKeyedTreeMap} (default constructor) size is four.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object, RemappingFunction)"})
  public void testPutWithPosValueRemappingFunction_thenOffsetKeyedTreeMapSizeIsFour() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(-2147483647, "Value");
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");
    offsetKeyedTreeMap.put(0, "Value");

    // Act and Assert
    assertNull(offsetKeyedTreeMap.put(1, "Value", mock(RemappingFunction.class)));
    assertEquals(4, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)} with {@code pos}, {@code
   * value}, {@code remappingFunction}.
   *
   * <ul>
   *   <li>Then {@link OffsetKeyedTreeMap} (default constructor) size is three.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object, RemappingFunction)"})
  public void testPutWithPosValueRemappingFunction_thenOffsetKeyedTreeMapSizeIsThree() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(3, "Value");

    // Act and Assert
    assertNull(offsetKeyedTreeMap.put(1, "Value", mock(RemappingFunction.class)));
    assertEquals(3, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)} with {@code pos}, {@code
   * value}, {@code remappingFunction}.
   *
   * <ul>
   *   <li>Then {@link OffsetKeyedTreeMap} (default constructor) size is three.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object, RemappingFunction)"})
  public void testPutWithPosValueRemappingFunction_thenOffsetKeyedTreeMapSizeIsThree2() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(3, "Value");
    offsetKeyedTreeMap.put(0, "Value");

    // Act and Assert
    assertNull(offsetKeyedTreeMap.put(1, "Value", mock(RemappingFunction.class)));
    assertEquals(3, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)} with {@code pos}, {@code
   * value}, {@code remappingFunction}.
   *
   * <ul>
   *   <li>Then {@link OffsetKeyedTreeMap} (default constructor) size is two.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object, RemappingFunction)"})
  public void testPutWithPosValueRemappingFunction_thenOffsetKeyedTreeMapSizeIsTwo() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(3, "Value");

    // Act and Assert
    assertNull(offsetKeyedTreeMap.put(1, "Value", mock(RemappingFunction.class)));
    assertEquals(2, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)} with {@code pos}, {@code
   * value}, {@code remappingFunction}.
   *
   * <ul>
   *   <li>Then {@link OffsetKeyedTreeMap} (default constructor) size is two.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object, RemappingFunction)"})
  public void testPutWithPosValueRemappingFunction_thenOffsetKeyedTreeMapSizeIsTwo2() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(0, "Value");

    // Act and Assert
    assertNull(offsetKeyedTreeMap.put(1, "Value", mock(RemappingFunction.class)));
    assertEquals(2, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)} with {@code pos}, {@code
   * value}, {@code remappingFunction}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object, RemappingFunction)"})
  public void testPutWithPosValueRemappingFunction_thenThrowIllegalStateException() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");
    offsetKeyedTreeMap.put(-2147483647, "Value");

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> offsetKeyedTreeMap.put(1, "Value", mock(RemappingFunction.class)));
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object)} with {@code pos}, {@code value}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) {@link Integer#MIN_VALUE} is
   *       {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object)"})
  public void testPutWithPosValue_givenOffsetKeyedTreeMapMin_valueIsValue() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");
    offsetKeyedTreeMap.put(0, "Value");

    // Act and Assert
    assertNull(offsetKeyedTreeMap.put(1, "Value"));
    assertEquals(3, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object)} with {@code pos}, {@code value}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) one is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object)"})
  public void testPutWithPosValue_givenOffsetKeyedTreeMapOneIsNull() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(1, null);

    // Act and Assert
    assertNull(offsetKeyedTreeMap.put(1, "Value"));
    assertEquals(2, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object)} with {@code pos}, {@code value}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) one is {@code Value}.
   *   <li>When one.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object)"})
  public void testPutWithPosValue_givenOffsetKeyedTreeMapOneIsValue_whenOne_thenReturnValue() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(1, "Value");

    // Act and Assert
    assertEquals("Value", offsetKeyedTreeMap.put(1, "Value"));
    assertEquals(1, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object)} with {@code pos}, {@code value}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) one is {@code Value}.
   *   <li>When one.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object)"})
  public void testPutWithPosValue_givenOffsetKeyedTreeMapOneIsValue_whenOne_thenReturnValue2() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(1, "Value");

    // Act and Assert
    assertEquals("Value", offsetKeyedTreeMap.put(1, "Value"));
    assertEquals(2, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object)} with {@code pos}, {@code value}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) two is {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object)"})
  public void testPutWithPosValue_givenOffsetKeyedTreeMapTwoIsValue() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(2, "Value");
    offsetKeyedTreeMap.put(0, "Value");

    // Act and Assert
    assertNull(offsetKeyedTreeMap.put(1, "Value"));
    assertEquals(3, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object)} with {@code pos}, {@code value}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) two is {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object)"})
  public void testPutWithPosValue_givenOffsetKeyedTreeMapTwoIsValue2() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(2, "Value");
    offsetKeyedTreeMap.put(0, "Value");

    // Act and Assert
    assertNull(offsetKeyedTreeMap.put(1, "Value"));
    assertEquals(3, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object)} with {@code pos}, {@code value}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) two is {@code Value}.
   *   <li>When {@link Integer#MIN_VALUE}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object)"})
  public void testPutWithPosValue_givenOffsetKeyedTreeMapTwoIsValue_whenMin_value() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(2, "Value");
    offsetKeyedTreeMap.put(0, "Value");

    // Act and Assert
    assertNull(offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value"));
    assertEquals(3, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object)} with {@code pos}, {@code value}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor).
   *   <li>Then {@link OffsetKeyedTreeMap} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object)"})
  public void testPutWithPosValue_givenOffsetKeyedTreeMap_thenOffsetKeyedTreeMapSizeIsOne() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();

    // Act and Assert
    assertNull(offsetKeyedTreeMap.put(1, "Value"));
    assertEquals(1, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object)} with {@code pos}, {@code value}.
   *
   * <ul>
   *   <li>Then {@link OffsetKeyedTreeMap} (default constructor) size is five.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object)"})
  public void testPutWithPosValue_thenOffsetKeyedTreeMapSizeIsFive() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(-1, "Value");
    offsetKeyedTreeMap.put(-2147483647, "Value");
    offsetKeyedTreeMap.put(Integer.MAX_VALUE, "Value");
    offsetKeyedTreeMap.put(0, "Value");

    // Act and Assert
    assertNull(offsetKeyedTreeMap.put(1, "Value"));
    assertEquals(5, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object)} with {@code pos}, {@code value}.
   *
   * <ul>
   *   <li>Then {@link OffsetKeyedTreeMap} (default constructor) size is four.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object)"})
  public void testPutWithPosValue_thenOffsetKeyedTreeMapSizeIsFour() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(-2147483647, "Value");
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");
    offsetKeyedTreeMap.put(0, "Value");

    // Act and Assert
    assertNull(offsetKeyedTreeMap.put(1, "Value"));
    assertEquals(4, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object)} with {@code pos}, {@code value}.
   *
   * <ul>
   *   <li>Then {@link OffsetKeyedTreeMap} (default constructor) size is four.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object)"})
  public void testPutWithPosValue_thenOffsetKeyedTreeMapSizeIsFour2() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(-2147483647, "Value");
    offsetKeyedTreeMap.put(Integer.MAX_VALUE, "Value");
    offsetKeyedTreeMap.put(0, "Value");

    // Act and Assert
    assertNull(offsetKeyedTreeMap.put(1, "Value"));
    assertEquals(4, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object)} with {@code pos}, {@code value}.
   *
   * <ul>
   *   <li>Then {@link OffsetKeyedTreeMap} (default constructor) size is two.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object)"})
  public void testPutWithPosValue_thenOffsetKeyedTreeMapSizeIsTwo() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(0, "Value");

    // Act and Assert
    assertNull(offsetKeyedTreeMap.put(1, "Value"));
    assertEquals(2, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#put(int, Object)} with {@code pos}, {@code value}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#put(int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OffsetKeyedTreeMap.put(int, Object)"})
  public void testPutWithPosValue_thenThrowIllegalStateException() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");
    offsetKeyedTreeMap.put(-2147483647, "Value");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> offsetKeyedTreeMap.put(1, "Value"));
  }

  /**
   * Test {@link OffsetKeyedTreeMap#size()}.
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#size()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int OffsetKeyedTreeMap.size()"})
  public void testSize() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();

    // Act and Assert
    assertEquals(0, offsetKeyedTreeMap.size());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#validateBlackHeights()}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) one is {@code Value}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#validateBlackHeights()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int OffsetKeyedTreeMap.validateBlackHeights()"})
  public void testValidateBlackHeights_givenOffsetKeyedTreeMapOneIsValue_thenReturnOne() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(1, "Value");

    // Act and Assert
    assertEquals(1, offsetKeyedTreeMap.validateBlackHeights());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#validateBlackHeights()}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) two is {@code Value}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#validateBlackHeights()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int OffsetKeyedTreeMap.validateBlackHeights()"})
  public void testValidateBlackHeights_givenOffsetKeyedTreeMapTwoIsValue_thenReturnOne() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(2, "Value");
    offsetKeyedTreeMap.put(1, "Value");

    // Act and Assert
    assertEquals(1, offsetKeyedTreeMap.validateBlackHeights());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#validateBlackHeights()}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) zero is {@code Value}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#validateBlackHeights()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int OffsetKeyedTreeMap.validateBlackHeights()"})
  public void testValidateBlackHeights_givenOffsetKeyedTreeMapZeroIsValue_thenReturnOne() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(2, "Value");
    offsetKeyedTreeMap.put(1, "Value");

    // Act and Assert
    assertEquals(1, offsetKeyedTreeMap.validateBlackHeights());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#validateBlackHeights()}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) zero is {@code Value}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#validateBlackHeights()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int OffsetKeyedTreeMap.validateBlackHeights()"})
  public void testValidateBlackHeights_givenOffsetKeyedTreeMapZeroIsValue_thenReturnOne2() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(1, "Value");

    // Act and Assert
    assertEquals(1, offsetKeyedTreeMap.validateBlackHeights());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#validateBlackHeights()}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor).
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#validateBlackHeights()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int OffsetKeyedTreeMap.validateBlackHeights()"})
  public void testValidateBlackHeights_givenOffsetKeyedTreeMap_thenReturnZero() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();

    // Act and Assert
    assertEquals(0, offsetKeyedTreeMap.validateBlackHeights());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#applyOffset(int, int)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) one is {@code Value}.
   *   <li>When two.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#applyOffset(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OffsetKeyedTreeMap.applyOffset(int, int)"})
  public void testApplyOffset_givenOffsetKeyedTreeMapOneIsValue_whenTwo_thenDoesNotThrow() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(1, "Value");
    offsetKeyedTreeMap.put(2, "Value");

    // Act and Assert
    offsetKeyedTreeMap.applyOffset(1, 2);
  }

  /**
   * Test {@link OffsetKeyedTreeMap#applyOffset(int, int)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) two is {@code Value}.
   *   <li>When two.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#applyOffset(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OffsetKeyedTreeMap.applyOffset(int, int)"})
  public void testApplyOffset_givenOffsetKeyedTreeMapTwoIsValue_whenTwo_thenDoesNotThrow() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(2, "Value");

    // Act and Assert
    offsetKeyedTreeMap.applyOffset(1, 2);
  }

  /**
   * Test {@link OffsetKeyedTreeMap#applyOffset(int, int)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) zero is {@code Value}.
   *   <li>When two.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#applyOffset(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OffsetKeyedTreeMap.applyOffset(int, int)"})
  public void testApplyOffset_givenOffsetKeyedTreeMapZeroIsValue_whenTwo_thenDoesNotThrow() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(0, "Value");

    // Act and Assert
    offsetKeyedTreeMap.applyOffset(1, 2);
  }

  /**
   * Test {@link OffsetKeyedTreeMap#applyOffset(int, int)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) zero is {@code Value}.
   *   <li>When two.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#applyOffset(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OffsetKeyedTreeMap.applyOffset(int, int)"})
  public void testApplyOffset_givenOffsetKeyedTreeMapZeroIsValue_whenTwo_thenDoesNotThrow2() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(2, "Value");

    // Act and Assert
    offsetKeyedTreeMap.applyOffset(1, 2);
  }

  /**
   * Test {@link OffsetKeyedTreeMap#applyOffset(int, int)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) zero is {@code Value}.
   *   <li>When two.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#applyOffset(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OffsetKeyedTreeMap.applyOffset(int, int)"})
  public void testApplyOffset_givenOffsetKeyedTreeMapZeroIsValue_whenTwo_thenDoesNotThrow3() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(2, "Value");
    offsetKeyedTreeMap.put(0, "Value");

    // Act and Assert
    offsetKeyedTreeMap.applyOffset(1, 2);
  }

  /**
   * Test {@link OffsetKeyedTreeMap#applyOffset(int, int)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor).
   *   <li>When two.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#applyOffset(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OffsetKeyedTreeMap.applyOffset(int, int)"})
  public void testApplyOffset_givenOffsetKeyedTreeMap_whenTwo_thenDoesNotThrow() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();

    // Act and Assert
    offsetKeyedTreeMap.applyOffset(1, 2);
  }

  /**
   * Test {@link OffsetKeyedTreeMap#applyOffset(int, int)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor).
   *   <li>When zero.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#applyOffset(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OffsetKeyedTreeMap.applyOffset(int, int)"})
  public void testApplyOffset_givenOffsetKeyedTreeMap_whenZero_thenDoesNotThrow() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();

    // Act and Assert
    offsetKeyedTreeMap.applyOffset(1, 0);
  }

  /**
   * Test {@link OffsetKeyedTreeMap#applyOffset(int, int)}.
   *
   * <ul>
   *   <li>When {@link Integer#MIN_VALUE}.
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#applyOffset(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OffsetKeyedTreeMap.applyOffset(int, int)"})
  public void testApplyOffset_whenMin_value_thenThrowUnsupportedOperationException() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> offsetKeyedTreeMap.applyOffset(1, Integer.MIN_VALUE));
  }

  /**
   * Test {@link OffsetKeyedTreeMap#forEach(BiConsumer)}.
   *
   * <ul>
   *   <li>Given {@link IllegalStateException#IllegalStateException()}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#forEach(BiConsumer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OffsetKeyedTreeMap.forEach(BiConsumer)"})
  public void testForEach_givenIllegalStateException() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(1, "Value");

    BiConsumer<Integer, Object> action = mock(BiConsumer.class);
    doThrow(new IllegalStateException())
        .when(action)
        .accept(Mockito.<Integer>any(), Mockito.<Object>any());

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> offsetKeyedTreeMap.forEach(action));
    verify(action).accept(eq(1), isA(Object.class));
  }

  /**
   * Test {@link OffsetKeyedTreeMap#forEach(BiConsumer)}.
   *
   * <ul>
   *   <li>Given {@link IllegalStateException#IllegalStateException()}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#forEach(BiConsumer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OffsetKeyedTreeMap.forEach(BiConsumer)"})
  public void testForEach_givenIllegalStateException2() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");
    offsetKeyedTreeMap.put(1, "Value");

    BiConsumer<Integer, Object> action = mock(BiConsumer.class);
    doThrow(new IllegalStateException())
        .when(action)
        .accept(Mockito.<Integer>any(), Mockito.<Object>any());

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> offsetKeyedTreeMap.forEach(action));
    verify(action).accept(eq(-2147483648), isA(Object.class));
  }

  /**
   * Test {@link OffsetKeyedTreeMap#forEach(BiConsumer)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) minus one is {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#forEach(BiConsumer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OffsetKeyedTreeMap.forEach(BiConsumer)"})
  public void testForEach_givenOffsetKeyedTreeMapMinusOneIsValue() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(-1, "Value");
    offsetKeyedTreeMap.put(1, "Value", mock(RemappingFunction.class));
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");

    BiConsumer<Integer, Object> action = mock(BiConsumer.class);
    doNothing().when(action).accept(Mockito.<Integer>any(), Mockito.<Object>any());

    // Act
    offsetKeyedTreeMap.forEach(action);

    // Assert
    verify(action, atLeast(1)).accept(Mockito.<Integer>any(), isA(Object.class));
  }

  /**
   * Test {@link OffsetKeyedTreeMap#forEach(BiConsumer)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) one is {@code Value}.
   *   <li>When {@link BiConsumer} {@link BiConsumer#accept(Object, Object)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#forEach(BiConsumer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OffsetKeyedTreeMap.forEach(BiConsumer)"})
  public void testForEach_givenOffsetKeyedTreeMapOneIsValue_whenBiConsumerAcceptDoesNothing() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(1, "Value");

    BiConsumer<Integer, Object> action = mock(BiConsumer.class);
    doNothing().when(action).accept(Mockito.<Integer>any(), Mockito.<Object>any());

    // Act
    offsetKeyedTreeMap.forEach(action);

    // Assert
    verify(action).accept(eq(1), isA(Object.class));
  }

  /**
   * Test {@link OffsetKeyedTreeMap#forEach(BiConsumer)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) one is {@code Value}.
   *   <li>When {@link BiConsumer} {@link BiConsumer#accept(Object, Object)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#forEach(BiConsumer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OffsetKeyedTreeMap.forEach(BiConsumer)"})
  public void testForEach_givenOffsetKeyedTreeMapOneIsValue_whenBiConsumerAcceptDoesNothing2() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(1, "Value");

    BiConsumer<Integer, Object> action = mock(BiConsumer.class);
    doNothing().when(action).accept(Mockito.<Integer>any(), Mockito.<Object>any());

    // Act
    offsetKeyedTreeMap.forEach(action);

    // Assert
    verify(action, atLeast(1)).accept(Mockito.<Integer>any(), isA(Object.class));
  }

  /**
   * Test {@link OffsetKeyedTreeMap#forEach(BiConsumer)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) one is {@code Value}.
   *   <li>When {@link BiConsumer} {@link BiConsumer#accept(Object, Object)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#forEach(BiConsumer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OffsetKeyedTreeMap.forEach(BiConsumer)"})
  public void testForEach_givenOffsetKeyedTreeMapOneIsValue_whenBiConsumerAcceptDoesNothing3() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(1, "Value");
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");

    BiConsumer<Integer, Object> action = mock(BiConsumer.class);
    doNothing().when(action).accept(Mockito.<Integer>any(), Mockito.<Object>any());

    // Act
    offsetKeyedTreeMap.forEach(action);

    // Assert
    verify(action, atLeast(1)).accept(Mockito.<Integer>any(), isA(Object.class));
  }

  /**
   * Test {@link OffsetKeyedTreeMap#forEach(BiConsumer)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) zero is {@code Value}.
   *   <li>When {@link BiConsumer} {@link BiConsumer#accept(Object, Object)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#forEach(BiConsumer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OffsetKeyedTreeMap.forEach(BiConsumer)"})
  public void testForEach_givenOffsetKeyedTreeMapZeroIsValue_whenBiConsumerAcceptDoesNothing() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");

    BiConsumer<Integer, Object> action = mock(BiConsumer.class);
    doNothing().when(action).accept(Mockito.<Integer>any(), Mockito.<Object>any());

    // Act
    offsetKeyedTreeMap.forEach(action);

    // Assert
    verify(action, atLeast(1)).accept(Mockito.<Integer>any(), isA(Object.class));
  }

  /**
   * Test {@link OffsetKeyedTreeMap#forEach(BiConsumer)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor).
   *   <li>When {@link BiConsumer}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#forEach(BiConsumer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OffsetKeyedTreeMap.forEach(BiConsumer)"})
  public void testForEach_givenOffsetKeyedTreeMap_whenBiConsumer_thenDoesNotThrow() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();

    // Act and Assert
    offsetKeyedTreeMap.forEach(mock(BiConsumer.class));
  }

  /**
   * Test {@link OffsetKeyedTreeMap#forEach(BiConsumer)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap#put(int, Object, RemappingFunction)} with pos is zero and
   *       {@code Value} and {@link RemappingFunction}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#forEach(BiConsumer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OffsetKeyedTreeMap.forEach(BiConsumer)"})
  public void testForEach_givenPutWithPosIsZeroAndValueAndRemappingFunction() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");
    offsetKeyedTreeMap.put(0, "Value", mock(RemappingFunction.class));
    offsetKeyedTreeMap.put(-1, "Value");
    offsetKeyedTreeMap.put(1, "Value", mock(RemappingFunction.class));
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");

    BiConsumer<Integer, Object> action = mock(BiConsumer.class);
    doNothing().when(action).accept(Mockito.<Integer>any(), Mockito.<Object>any());

    // Act
    offsetKeyedTreeMap.forEach(action);

    // Assert
    verify(action, atLeast(1)).accept(Mockito.<Integer>any(), isA(Object.class));
  }

  /**
   * Test {@link OffsetKeyedTreeMap#forEach(BiConsumer)}.
   *
   * <ul>
   *   <li>Given {@link RemappingFunction} {@link RemappingFunction#apply(int, Object, Object)}
   *       return {@code Apply}.
   *   <li>Then calls {@link RemappingFunction#apply(int, Object, Object)}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#forEach(BiConsumer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OffsetKeyedTreeMap.forEach(BiConsumer)"})
  public void testForEach_givenRemappingFunctionApplyReturnApply_thenCallsApply() {
    // Arrange
    RemappingFunction<Object> remappingFunction = mock(RemappingFunction.class);
    when(remappingFunction.apply(anyInt(), Mockito.<Object>any(), Mockito.<Object>any()))
        .thenReturn("Apply");

    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");
    offsetKeyedTreeMap.put(1, "Value", mock(RemappingFunction.class));
    offsetKeyedTreeMap.put(-1, "Value");
    offsetKeyedTreeMap.put(1, "Value", remappingFunction);
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");

    BiConsumer<Integer, Object> action = mock(BiConsumer.class);
    doNothing().when(action).accept(Mockito.<Integer>any(), Mockito.<Object>any());

    // Act
    offsetKeyedTreeMap.forEach(action);

    // Assert
    verify(action, atLeast(1)).accept(Mockito.<Integer>any(), Mockito.<Object>any());
    verify(remappingFunction).apply(eq(1), isA(Object.class), isA(Object.class));
  }

  /**
   * Test {@link OffsetKeyedTreeMap#forEach(BiConsumer)}.
   *
   * <ul>
   *   <li>Given {@link RemappingFunction} {@link RemappingFunction#apply(int, Object, Object)}
   *       return {@code Apply}.
   *   <li>Then calls {@link RemappingFunction#apply(int, Object, Object)}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#forEach(BiConsumer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OffsetKeyedTreeMap.forEach(BiConsumer)"})
  public void testForEach_givenRemappingFunctionApplyReturnApply_thenCallsApply2() {
    // Arrange
    RemappingFunction<Object> remappingFunction = mock(RemappingFunction.class);
    when(remappingFunction.apply(anyInt(), Mockito.<Object>any(), Mockito.<Object>any()))
        .thenReturn("Apply");

    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(1, "Value");
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");
    offsetKeyedTreeMap.put(1, "Value", remappingFunction);
    offsetKeyedTreeMap.put(-1, "Value");
    offsetKeyedTreeMap.put(0, "Value", mock(RemappingFunction.class));
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");

    BiConsumer<Integer, Object> action = mock(BiConsumer.class);
    doNothing().when(action).accept(Mockito.<Integer>any(), Mockito.<Object>any());

    // Act
    offsetKeyedTreeMap.forEach(action);

    // Assert
    verify(action, atLeast(1)).accept(Mockito.<Integer>any(), Mockito.<Object>any());
    verify(remappingFunction).apply(eq(1), isA(Object.class), isA(Object.class));
  }

  /**
   * Test {@link OffsetKeyedTreeMap#collect()}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) one is {@code Value}.
   *   <li>Then return {@code [1 as 1] Value}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#collect()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String OffsetKeyedTreeMap.collect()"})
  public void testCollect_givenOffsetKeyedTreeMapOneIsValue_thenReturn1As1Value() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(1, "Value");

    // Act and Assert
    assertEquals("[1 as 1] Value\n", offsetKeyedTreeMap.collect());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#collect()}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) zero is {@code Value}.
   *   <li>Then return {@code [0 as 0] Value R[1 as 1] Value}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#collect()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String OffsetKeyedTreeMap.collect()"})
  public void testCollect_givenOffsetKeyedTreeMapZeroIsValue_thenReturn0As0ValueR1As1Value() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(1, "Value");

    // Act and Assert
    assertEquals("[0 as 0] Value\n  R[1 as 1] Value\n", offsetKeyedTreeMap.collect());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#collect()}.
   *
   * <ul>
   *   <li>Then return {@code [0 as 0] Value L[-2147483648 as -2147483648] Value R[1 as 1] Value}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#collect()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String OffsetKeyedTreeMap.collect()"})
  public void testCollect_thenReturn0As0ValueL2147483648As2147483648ValueR1As1Value() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(1, "Value");

    // Act and Assert
    assertEquals(
        "[0 as 0] Value\n  L[-2147483648 as -2147483648] Value\n  R[1 as 1] Value\n",
        offsetKeyedTreeMap.collect());
  }

  /**
   * Test {@link OffsetKeyedTreeMap#removeAt(int)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) minus one is {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#removeAt(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OffsetKeyedTreeMap.removeAt(int)"})
  public void testRemoveAt_givenOffsetKeyedTreeMapMinusOneIsValue() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(1, "Value");
    offsetKeyedTreeMap.put(-1, "Value");
    offsetKeyedTreeMap.put(1, "Value");

    // Act
    boolean actualRemoveAtResult = offsetKeyedTreeMap.removeAt(1);

    // Assert
    assertEquals(3, offsetKeyedTreeMap.size());
    assertTrue(actualRemoveAtResult);
  }

  /**
   * Test {@link OffsetKeyedTreeMap#removeAt(int)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) one is {@code Value}.
   *   <li>When zero.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#removeAt(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OffsetKeyedTreeMap.removeAt(int)"})
  public void testRemoveAt_givenOffsetKeyedTreeMapOneIsValue_whenZero_thenReturnFalse() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(1, "Value");

    // Act
    boolean actualRemoveAtResult = offsetKeyedTreeMap.removeAt(0);

    // Assert
    assertEquals(1, offsetKeyedTreeMap.size());
    assertFalse(actualRemoveAtResult);
  }

  /**
   * Test {@link OffsetKeyedTreeMap#removeAt(int)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) two is {@code Value}.
   *   <li>Then {@link OffsetKeyedTreeMap} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#removeAt(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OffsetKeyedTreeMap.removeAt(int)"})
  public void testRemoveAt_givenOffsetKeyedTreeMapTwoIsValue_thenOffsetKeyedTreeMapSizeIsOne() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(2, "Value");
    offsetKeyedTreeMap.put(1, "Value");

    // Act
    boolean actualRemoveAtResult = offsetKeyedTreeMap.removeAt(1);

    // Assert
    assertEquals(1, offsetKeyedTreeMap.size());
    assertTrue(actualRemoveAtResult);
  }

  /**
   * Test {@link OffsetKeyedTreeMap#removeAt(int)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) two is {@code Value}.
   *   <li>Then {@link OffsetKeyedTreeMap} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#removeAt(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OffsetKeyedTreeMap.removeAt(int)"})
  public void testRemoveAt_givenOffsetKeyedTreeMapTwoIsValue_thenOffsetKeyedTreeMapSizeIsOne2() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(1, "Value");
    offsetKeyedTreeMap.put(2, "Value");
    offsetKeyedTreeMap.put(1, "Value");

    // Act
    boolean actualRemoveAtResult = offsetKeyedTreeMap.removeAt(1);

    // Assert
    assertEquals(1, offsetKeyedTreeMap.size());
    assertTrue(actualRemoveAtResult);
  }

  /**
   * Test {@link OffsetKeyedTreeMap#removeAt(int)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) two is {@code Value}.
   *   <li>Then {@link OffsetKeyedTreeMap} (default constructor) size is two.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#removeAt(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OffsetKeyedTreeMap.removeAt(int)"})
  public void testRemoveAt_givenOffsetKeyedTreeMapTwoIsValue_thenOffsetKeyedTreeMapSizeIsTwo() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(1, "Value");
    offsetKeyedTreeMap.put(2, "Value");
    offsetKeyedTreeMap.put(1, "Value");

    // Act
    boolean actualRemoveAtResult = offsetKeyedTreeMap.removeAt(1);

    // Assert
    assertEquals(2, offsetKeyedTreeMap.size());
    assertTrue(actualRemoveAtResult);
  }

  /**
   * Test {@link OffsetKeyedTreeMap#removeAt(int)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) zero is {@code Value}.
   *   <li>Then {@link OffsetKeyedTreeMap} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#removeAt(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OffsetKeyedTreeMap.removeAt(int)"})
  public void testRemoveAt_givenOffsetKeyedTreeMapZeroIsValue_thenOffsetKeyedTreeMapSizeIsOne() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(1, "Value");

    // Act
    boolean actualRemoveAtResult = offsetKeyedTreeMap.removeAt(1);

    // Assert
    assertEquals(1, offsetKeyedTreeMap.size());
    assertTrue(actualRemoveAtResult);
  }

  /**
   * Test {@link OffsetKeyedTreeMap#removeAt(int)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor) zero is {@code Value}.
   *   <li>Then {@link OffsetKeyedTreeMap} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#removeAt(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OffsetKeyedTreeMap.removeAt(int)"})
  public void testRemoveAt_givenOffsetKeyedTreeMapZeroIsValue_thenOffsetKeyedTreeMapSizeIsOne2() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(1, "Value");
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(1, "Value");

    // Act
    boolean actualRemoveAtResult = offsetKeyedTreeMap.removeAt(1);

    // Assert
    assertEquals(1, offsetKeyedTreeMap.size());
    assertTrue(actualRemoveAtResult);
  }

  /**
   * Test {@link OffsetKeyedTreeMap#removeAt(int)}.
   *
   * <ul>
   *   <li>Given {@link OffsetKeyedTreeMap} (default constructor).
   *   <li>When one.
   *   <li>Then {@link OffsetKeyedTreeMap} (default constructor) size is zero.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#removeAt(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OffsetKeyedTreeMap.removeAt(int)"})
  public void testRemoveAt_givenOffsetKeyedTreeMap_whenOne_thenOffsetKeyedTreeMapSizeIsZero() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();

    // Act
    boolean actualRemoveAtResult = offsetKeyedTreeMap.removeAt(1);

    // Assert
    assertEquals(0, offsetKeyedTreeMap.size());
    assertFalse(actualRemoveAtResult);
  }

  /**
   * Test {@link OffsetKeyedTreeMap#removeAt(int)}.
   *
   * <ul>
   *   <li>Then {@link OffsetKeyedTreeMap} (default constructor) size is four.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#removeAt(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OffsetKeyedTreeMap.removeAt(int)"})
  public void testRemoveAt_thenOffsetKeyedTreeMapSizeIsFour() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(-1, "Value");
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(1, "Value");
    offsetKeyedTreeMap.put(2, "Value");
    offsetKeyedTreeMap.put(1, "Value");

    // Act
    boolean actualRemoveAtResult = offsetKeyedTreeMap.removeAt(Integer.MIN_VALUE);

    // Assert
    assertEquals(4, offsetKeyedTreeMap.size());
    assertTrue(actualRemoveAtResult);
  }

  /**
   * Test {@link OffsetKeyedTreeMap#removeAt(int)}.
   *
   * <ul>
   *   <li>Then {@link OffsetKeyedTreeMap} (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#removeAt(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OffsetKeyedTreeMap.removeAt(int)"})
  public void testRemoveAt_thenOffsetKeyedTreeMapSizeIsOne() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");
    offsetKeyedTreeMap.put(1, "Value");

    // Act
    boolean actualRemoveAtResult = offsetKeyedTreeMap.removeAt(1);

    // Assert
    assertEquals(1, offsetKeyedTreeMap.size());
    assertTrue(actualRemoveAtResult);
  }

  /**
   * Test {@link OffsetKeyedTreeMap#removeAt(int)}.
   *
   * <ul>
   *   <li>Then {@link OffsetKeyedTreeMap} (default constructor) size is three.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#removeAt(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OffsetKeyedTreeMap.removeAt(int)"})
  public void testRemoveAt_thenOffsetKeyedTreeMapSizeIsThree() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(1, "Value");
    offsetKeyedTreeMap.put(2, "Value");
    offsetKeyedTreeMap.put(1, "Value");

    // Act
    boolean actualRemoveAtResult = offsetKeyedTreeMap.removeAt(1);

    // Assert
    assertEquals(3, offsetKeyedTreeMap.size());
    assertTrue(actualRemoveAtResult);
  }

  /**
   * Test {@link OffsetKeyedTreeMap#removeAt(int)}.
   *
   * <ul>
   *   <li>Then {@link OffsetKeyedTreeMap} (default constructor) size is three.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#removeAt(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OffsetKeyedTreeMap.removeAt(int)"})
  public void testRemoveAt_thenOffsetKeyedTreeMapSizeIsThree2() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(1, "Value");
    offsetKeyedTreeMap.put(2, "Value");
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");

    // Act
    boolean actualRemoveAtResult = offsetKeyedTreeMap.removeAt(1);

    // Assert
    assertEquals(3, offsetKeyedTreeMap.size());
    assertTrue(actualRemoveAtResult);
  }

  /**
   * Test {@link OffsetKeyedTreeMap#removeAt(int)}.
   *
   * <ul>
   *   <li>When {@link Integer#MIN_VALUE}.
   *   <li>Then {@link OffsetKeyedTreeMap} (default constructor) size is three.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#removeAt(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OffsetKeyedTreeMap.removeAt(int)"})
  public void testRemoveAt_whenMin_value_thenOffsetKeyedTreeMapSizeIsThree() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(1, "Value");
    offsetKeyedTreeMap.put(2, "Value");
    offsetKeyedTreeMap.put(1, "Value");

    // Act
    boolean actualRemoveAtResult = offsetKeyedTreeMap.removeAt(Integer.MIN_VALUE);

    // Assert
    assertEquals(3, offsetKeyedTreeMap.size());
    assertTrue(actualRemoveAtResult);
  }

  /**
   * Test {@link OffsetKeyedTreeMap#removeAt(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then {@link OffsetKeyedTreeMap} (default constructor) size is zero.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#removeAt(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OffsetKeyedTreeMap.removeAt(int)"})
  public void testRemoveAt_whenOne_thenOffsetKeyedTreeMapSizeIsZero() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(1, "Value");

    // Act
    boolean actualRemoveAtResult = offsetKeyedTreeMap.removeAt(1);

    // Assert
    assertEquals(0, offsetKeyedTreeMap.size());
    assertTrue(actualRemoveAtResult);
  }

  /**
   * Test {@link OffsetKeyedTreeMap#removeAt(int)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then {@link OffsetKeyedTreeMap} (default constructor) size is three.
   * </ul>
   *
   * <p>Method under test: {@link OffsetKeyedTreeMap#removeAt(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OffsetKeyedTreeMap.removeAt(int)"})
  public void testRemoveAt_whenZero_thenOffsetKeyedTreeMapSizeIsThree() {
    // Arrange
    OffsetKeyedTreeMap<Object> offsetKeyedTreeMap = new OffsetKeyedTreeMap<>();
    offsetKeyedTreeMap.put(Integer.MIN_VALUE, "Value");
    offsetKeyedTreeMap.put(0, "Value");
    offsetKeyedTreeMap.put(1, "Value");
    offsetKeyedTreeMap.put(2, "Value");
    offsetKeyedTreeMap.put(1, "Value");

    // Act
    boolean actualRemoveAtResult = offsetKeyedTreeMap.removeAt(0);

    // Assert
    assertEquals(3, offsetKeyedTreeMap.size());
    assertTrue(actualRemoveAtResult);
  }

  /**
   * Test ValueAndOffset {@link ValueAndOffset#ValueAndOffset(Object, int)}.
   *
   * <p>Method under test: {@link ValueAndOffset#ValueAndOffset(Object, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ValueAndOffset.<init>(Object, int)"})
  public void testValueAndOffsetNewValueAndOffset() {
    // Arrange and Act
    ValueAndOffset<Object> actualValueAndOffset = new ValueAndOffset<>("Value", 2);

    // Assert
    assertEquals("Value", actualValueAndOffset.value);
    assertEquals(2, actualValueAndOffset.offset);
  }
}
