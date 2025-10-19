package org.jkiss.dbeaver.ext.firebird.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FireBirdFieldTypeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link FireBirdFieldType#getDataKind()}
   *   <li>{@link FireBirdFieldType#getName()}
   *   <li>{@link FireBirdFieldType#getTypeID()}
   *   <li>{@link FireBirdFieldType#getValueType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPDataKind FireBirdFieldType.getDataKind()",
    "String FireBirdFieldType.getName()",
    "int FireBirdFieldType.getTypeID()",
    "int FireBirdFieldType.getValueType()"
  })
  public void testGettersAndSetters() {
    // Arrange
    FireBirdFieldType valueOfResult = FireBirdFieldType.valueOf("SMALLINT");

    // Act
    DBPDataKind actualDataKind = valueOfResult.getDataKind();
    String actualName = valueOfResult.getName();
    int actualTypeID = valueOfResult.getTypeID();

    // Assert
    assertEquals("SMALLINT", actualName);
    assertEquals(5, valueOfResult.getValueType());
    assertEquals(7, actualTypeID);
    assertEquals(DBPDataKind.NUMERIC, actualDataKind);
  }

  /**
   * Test {@link FireBirdFieldType#getById(int, int)}.
   *
   * <ul>
   *   <li>When eight.
   *   <li>Then return {@code INTEGER}.
   * </ul>
   *
   * <p>Method under test: {@link FireBirdFieldType#getById(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FireBirdFieldType FireBirdFieldType.getById(int, int)"})
  public void testGetById_whenEight_thenReturnInteger() {
    // Arrange, Act and Assert
    assertEquals(FireBirdFieldType.INTEGER, FireBirdFieldType.getById(8, 3));
  }

  /**
   * Test {@link FireBirdFieldType#getById(int, int)}.
   *
   * <ul>
   *   <li>When eight.
   *   <li>Then return {@code NUMERIC}.
   * </ul>
   *
   * <p>Method under test: {@link FireBirdFieldType#getById(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FireBirdFieldType FireBirdFieldType.getById(int, int)"})
  public void testGetById_whenEight_thenReturnNumeric() {
    // Arrange, Act and Assert
    assertEquals(FireBirdFieldType.NUMERIC, FireBirdFieldType.getById(8, 1));
  }

  /**
   * Test {@link FireBirdFieldType#getById(int, int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FireBirdFieldType#getById(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FireBirdFieldType FireBirdFieldType.getById(int, int)"})
  public void testGetById_whenOne_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(FireBirdFieldType.getById(1, 1));
  }

  /**
   * Test {@link FireBirdFieldType#getById(int, int)}.
   *
   * <ul>
   *   <li>When seven.
   *   <li>Then return {@code SMALLINT}.
   * </ul>
   *
   * <p>Method under test: {@link FireBirdFieldType#getById(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FireBirdFieldType FireBirdFieldType.getById(int, int)"})
  public void testGetById_whenSeven_thenReturnSmallint() {
    // Arrange, Act and Assert
    assertEquals(FireBirdFieldType.SMALLINT, FireBirdFieldType.getById(7, 1));
  }

  /**
   * Test {@link FireBirdFieldType#getById(int, int)}.
   *
   * <ul>
   *   <li>When {@link Short#SIZE}.
   *   <li>Then return {@code NUMERIC}.
   * </ul>
   *
   * <p>Method under test: {@link FireBirdFieldType#getById(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FireBirdFieldType FireBirdFieldType.getById(int, int)"})
  public void testGetById_whenSize_thenReturnNumeric() {
    // Arrange, Act and Assert
    assertEquals(FireBirdFieldType.NUMERIC, FireBirdFieldType.getById(Short.SIZE, 1));
  }

  /**
   * Test {@link FireBirdFieldType#getById(int, int)}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return {@code BIGINT}.
   * </ul>
   *
   * <p>Method under test: {@link FireBirdFieldType#getById(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FireBirdFieldType FireBirdFieldType.getById(int, int)"})
  public void testGetById_whenThree_thenReturnBigint() {
    // Arrange, Act and Assert
    assertEquals(FireBirdFieldType.BIGINT, FireBirdFieldType.getById(Short.SIZE, 3));
  }

  /**
   * Test {@link FireBirdFieldType#getById(int, int)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code DECIMAL}.
   * </ul>
   *
   * <p>Method under test: {@link FireBirdFieldType#getById(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FireBirdFieldType FireBirdFieldType.getById(int, int)"})
  public void testGetById_whenTwo_thenReturnDecimal() {
    // Arrange, Act and Assert
    assertEquals(FireBirdFieldType.DECIMAL, FireBirdFieldType.getById(Short.SIZE, 2));
  }
}
