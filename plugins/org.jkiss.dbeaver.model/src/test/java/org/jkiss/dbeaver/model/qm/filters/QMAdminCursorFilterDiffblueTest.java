package org.jkiss.dbeaver.model.qm.filters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashSet;
import java.util.Set;
import org.jkiss.dbeaver.model.qm.QMEventFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QMAdminCursorFilterDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link QMAdminCursorFilter#QMAdminCursorFilter(String, QMAdminEventCriteria,
   *       QMEventFilter)}
   *   <li>{@link QMAdminCursorFilter#getAdminCriteria()}
   *   <li>{@link QMAdminCursorFilter#getFilter()}
   *   <li>{@link QMAdminCursorFilter#getSessionId()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void QMAdminCursorFilter.<init>(String, QMAdminEventCriteria, QMEventFilter)",
    "QMAdminEventCriteria QMAdminCursorFilter.getAdminCriteria()",
    "QMEventFilter QMAdminCursorFilter.getFilter()",
    "String QMAdminCursorFilter.getSessionId()"
  })
  public void testGettersAndSetters() {
    // Arrange
    QMEventCriteria criteria = new QMEventCriteria();
    QMAdminEventCriteria criteria2 = new QMAdminEventCriteria(criteria, new HashSet<>());
    QMEventFilter filter = mock(QMEventFilter.class);

    // Act
    QMAdminCursorFilter actualQmAdminCursorFilter =
        new QMAdminCursorFilter("42", criteria2, filter);
    QMAdminEventCriteria actualAdminCriteria = actualQmAdminCursorFilter.getAdminCriteria();
    QMEventFilter actualFilter = actualQmAdminCursorFilter.getFilter();

    // Assert
    assertEquals("42", actualQmAdminCursorFilter.getSessionId());
    assertSame(criteria2, actualAdminCriteria);
    assertSame(filter, actualFilter);
  }

  /**
   * Test {@link QMAdminCursorFilter#QMAdminCursorFilter(QMCursorFilter, Set)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link HashSet#HashSet()} add {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link QMAdminCursorFilter#QMAdminCursorFilter(QMCursorFilter, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMAdminCursorFilter.<init>(QMCursorFilter, Set)"})
  public void testNewQMAdminCursorFilter_given42_whenHashSetAdd42() {
    // Arrange
    QMEventCriteria criteria = new QMEventCriteria();
    QMCursorFilter cursorFilter = new QMCursorFilter("42", criteria, mock(QMEventFilter.class));

    HashSet<String> userNames = new HashSet<>();
    userNames.add("42");
    userNames.add("foo");

    // Act
    QMAdminCursorFilter actualQmAdminCursorFilter =
        new QMAdminCursorFilter(cursorFilter, userNames);

    // Assert
    assertEquals("42", actualQmAdminCursorFilter.getSessionId());
    QMAdminEventCriteria adminCriteria = actualQmAdminCursorFilter.getAdminCriteria();
    assertTrue(adminCriteria.hasUsers());
    assertSame(userNames, adminCriteria.getUsers());
    assertSame(criteria, adminCriteria.getCriteria());
  }

  /**
   * Test {@link QMAdminCursorFilter#QMAdminCursorFilter(QMCursorFilter, Set)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>Then return AdminCriteria hasUsers.
   * </ul>
   *
   * <p>Method under test: {@link QMAdminCursorFilter#QMAdminCursorFilter(QMCursorFilter, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMAdminCursorFilter.<init>(QMCursorFilter, Set)"})
  public void testNewQMAdminCursorFilter_givenFoo_thenReturnAdminCriteriaHasUsers() {
    // Arrange
    QMEventCriteria criteria = new QMEventCriteria();
    QMCursorFilter cursorFilter = new QMCursorFilter("42", criteria, mock(QMEventFilter.class));

    HashSet<String> userNames = new HashSet<>();
    userNames.add("foo");

    // Act
    QMAdminCursorFilter actualQmAdminCursorFilter =
        new QMAdminCursorFilter(cursorFilter, userNames);

    // Assert
    assertEquals("42", actualQmAdminCursorFilter.getSessionId());
    QMAdminEventCriteria adminCriteria = actualQmAdminCursorFilter.getAdminCriteria();
    assertTrue(adminCriteria.hasUsers());
    assertSame(userNames, adminCriteria.getUsers());
    assertSame(criteria, adminCriteria.getCriteria());
  }

  /**
   * Test {@link QMAdminCursorFilter#QMAdminCursorFilter(QMCursorFilter, Set)}.
   *
   * <ul>
   *   <li>When {@link HashSet#HashSet()}.
   *   <li>Then return not AdminCriteria hasUsers.
   * </ul>
   *
   * <p>Method under test: {@link QMAdminCursorFilter#QMAdminCursorFilter(QMCursorFilter, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMAdminCursorFilter.<init>(QMCursorFilter, Set)"})
  public void testNewQMAdminCursorFilter_whenHashSet_thenReturnNotAdminCriteriaHasUsers() {
    // Arrange
    QMEventCriteria criteria = new QMEventCriteria();
    QMCursorFilter cursorFilter = new QMCursorFilter("42", criteria, mock(QMEventFilter.class));

    // Act
    QMAdminCursorFilter actualQmAdminCursorFilter =
        new QMAdminCursorFilter(cursorFilter, new HashSet<>());

    // Assert
    assertEquals("42", actualQmAdminCursorFilter.getSessionId());
    QMAdminEventCriteria adminCriteria = actualQmAdminCursorFilter.getAdminCriteria();
    assertFalse(adminCriteria.hasUsers());
    assertTrue(adminCriteria.getUsers().isEmpty());
    assertSame(criteria, adminCriteria.getCriteria());
  }
}
