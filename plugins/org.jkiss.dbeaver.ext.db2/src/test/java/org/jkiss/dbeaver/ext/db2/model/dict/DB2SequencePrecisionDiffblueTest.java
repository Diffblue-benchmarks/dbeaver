package org.jkiss.dbeaver.ext.db2.model.dict;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DB2SequencePrecisionDiffblueTest {
  /**
   * Test {@link DB2SequencePrecision#getFromDataType(Integer)}.
   *
   * <ul>
   *   <li>When five.
   *   <li>Then return {@code P5}.
   * </ul>
   *
   * <p>Method under test: {@link DB2SequencePrecision#getFromDataType(Integer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DB2SequencePrecision DB2SequencePrecision.getFromDataType(Integer)"})
  public void testGetFromDataType_whenFive_thenReturnP5() {
    // Arrange, Act and Assert
    assertEquals(DB2SequencePrecision.P5, DB2SequencePrecision.getFromDataType(5));
  }

  /**
   * Test {@link DB2SequencePrecision#getFromDataType(Integer)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DB2SequencePrecision#getFromDataType(Integer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DB2SequencePrecision DB2SequencePrecision.getFromDataType(Integer)"})
  public void testGetFromDataType_whenOne_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DB2SequencePrecision.getFromDataType(1));
  }
}
