package org.jkiss.dbeaver.model.data.aggregate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Set;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FunctionCountDistinctDiffblueTest {
  @InjectMocks private FunctionCountDistinct functionCountDistinct;

  @Mock private Set<Object> set;

  /**
   * Test {@link FunctionCountDistinct#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>Then {@link FunctionCountDistinct} (default constructor) fourth Result intValue is one.
   * </ul>
   *
   * <p>Method under test: {@link FunctionCountDistinct#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionCountDistinct.accumulate(Object, boolean)"})
  public void testAccumulate_thenFunctionCountDistinctFourthResultIntValueIsOne() {
    // Arrange
    FunctionCountDistinct functionCountDistinct = new FunctionCountDistinct();

    // Act
    boolean actualAccumulateResult = functionCountDistinct.accumulate(DBPEvent.RENAME, true);

    // Assert
    assertEquals(1, ((Integer) functionCountDistinct.getResult(3)).intValue());
    assertTrue(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionCountDistinct#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>Then {@link FunctionCountDistinct} fourth Result intValue is zero.
   * </ul>
   *
   * <p>Method under test: {@link FunctionCountDistinct#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionCountDistinct.accumulate(Object, boolean)"})
  public void testAccumulate_thenFunctionCountDistinctFourthResultIntValueIsZero() {
    // Arrange
    when(set.contains(Mockito.<Object>any())).thenReturn(true);

    // Act
    boolean actualAccumulateResult = functionCountDistinct.accumulate(DBPEvent.RENAME, true);

    // Assert
    verify(set).contains(isA(Object.class));
    Object result = functionCountDistinct.getResult(3);
    assertEquals(0, ((Integer) result).intValue());
    assertFalse(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionCountDistinct#getResult(int)}.
   *
   * <p>Method under test: {@link FunctionCountDistinct#getResult(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object FunctionCountDistinct.getResult(int)"})
  public void testGetResult() {
    // Arrange, Act and Assert
    assertEquals(0, ((Integer) new FunctionCountDistinct().getResult(3)).intValue());
  }
}
