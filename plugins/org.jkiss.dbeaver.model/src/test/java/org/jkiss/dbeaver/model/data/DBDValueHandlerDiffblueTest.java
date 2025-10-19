package org.jkiss.dbeaver.model.data;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.impl.data.DefaultValueHandler;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBDValueHandlerDiffblueTest {
  /**
   * Test {@link DBDValueHandler#getComparator()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDValueHandler#getComparator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Comparator DBDValueHandler.getComparator()"})
  public void testGetComparator_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DefaultValueHandler.INSTANCE.getComparator());
  }
}
