package org.jkiss.dbeaver.ext.hana.model.data.wkb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GeometryTypeDiffblueTest {
  /**
   * Test {@link GeometryType#getTypeCode()}.
   *
   * <p>Method under test: {@link GeometryType#getTypeCode()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int GeometryType.getTypeCode()"})
  public void testGetTypeCode() {
    // Arrange, Act and Assert
    assertEquals(1, GeometryType.valueOf("POINT").getTypeCode());
  }

  /**
   * Test {@link GeometryType#getFromCode(int)}.
   *
   * <ul>
   *   <li>When eight.
   *   <li>Then return {@code CIRCULARSTRING}.
   * </ul>
   *
   * <p>Method under test: {@link GeometryType#getFromCode(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GeometryType GeometryType.getFromCode(int)"})
  public void testGetFromCode_whenEight_thenReturnCircularstring() {
    // Arrange, Act and Assert
    assertEquals(GeometryType.CIRCULARSTRING, GeometryType.getFromCode(8));
  }

  /**
   * Test {@link GeometryType#getFromCode(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code POINT}.
   * </ul>
   *
   * <p>Method under test: {@link GeometryType#getFromCode(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GeometryType GeometryType.getFromCode(int)"})
  public void testGetFromCode_whenOne_thenReturnPoint() {
    // Arrange, Act and Assert
    assertEquals(GeometryType.POINT, GeometryType.getFromCode(1));
  }

  /**
   * Test {@link GeometryType#getFromCode(int)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GeometryType#getFromCode(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GeometryType GeometryType.getFromCode(int)"})
  public void testGetFromCode_whenZero_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(GeometryType.getFromCode(0));
  }
}
