package org.jkiss.dbeaver.model.data.hints;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.data.DBDAttributeBinding;
import org.jkiss.dbeaver.model.data.hints.standard.ArrayCellHintProvider;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBDCellHintProviderDiffblueTest {
  /**
   * Test {@link DBDCellHintProvider#getAttributeHintSize(DBDValueHintContext,
   * DBDAttributeBinding)}.
   *
   * <ul>
   *   <li>Given {@link ArrayCellHintProvider} (default constructor).
   *   <li>When {@code null}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DBDCellHintProvider#getAttributeHintSize(DBDValueHintContext,
   * DBDAttributeBinding)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int DBDCellHintProvider.getAttributeHintSize(DBDValueHintContext, DBDAttributeBinding)"
  })
  public void testGetAttributeHintSize_givenArrayCellHintProvider_whenNull_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(
        0, new ArrayCellHintProvider().getAttributeHintSize(mock(DBDValueHintContext.class), null));
  }
}
