package org.jkiss.dbeaver.model.qm.filters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.qm.QMEventFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QMCursorFilterDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link QMCursorFilter#QMCursorFilter(String, QMEventCriteria, QMEventFilter)}
   *   <li>{@link QMCursorFilter#getCriteria()}
   *   <li>{@link QMCursorFilter#getFilter()}
   *   <li>{@link QMCursorFilter#getSessionId()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void QMCursorFilter.<init>(String, QMEventCriteria, QMEventFilter)",
    "QMEventCriteria QMCursorFilter.getCriteria()",
    "QMEventFilter QMCursorFilter.getFilter()",
    "String QMCursorFilter.getSessionId()"
  })
  public void testGettersAndSetters() {
    // Arrange
    QMEventCriteria criteria = new QMEventCriteria();
    QMEventFilter filter = mock(QMEventFilter.class);

    // Act
    QMCursorFilter actualQmCursorFilter = new QMCursorFilter("42", criteria, filter);
    QMEventCriteria actualCriteria = actualQmCursorFilter.getCriteria();
    QMEventFilter actualFilter = actualQmCursorFilter.getFilter();

    // Assert
    assertEquals("42", actualQmCursorFilter.getSessionId());
    assertSame(criteria, actualCriteria);
    assertSame(filter, actualFilter);
  }
}
