package org.jkiss.dbeaver.model.qm.filters;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QMAdminEventCriteriaDiffblueTest {
  /**
   * Test {@link QMAdminEventCriteria#QMAdminEventCriteria(QMEventCriteria, Set)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link HashSet#HashSet()} add {@code 42}.
   *   <li>Then return hasUsers.
   * </ul>
   *
   * <p>Method under test: {@link QMAdminEventCriteria#QMAdminEventCriteria(QMEventCriteria, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMAdminEventCriteria.<init>(QMEventCriteria, Set)"})
  public void testNewQMAdminEventCriteria_given42_whenHashSetAdd42_thenReturnHasUsers() {
    // Arrange
    QMEventCriteria criteria = new QMEventCriteria();

    HashSet<String> users = new HashSet<>();
    users.add("42");
    users.add("foo");

    // Act
    QMAdminEventCriteria actualQmAdminEventCriteria = new QMAdminEventCriteria(criteria, users);

    // Assert
    assertTrue(actualQmAdminEventCriteria.hasUsers());
    assertSame(users, actualQmAdminEventCriteria.getUsers());
    assertSame(criteria, actualQmAdminEventCriteria.getCriteria());
  }

  /**
   * Test {@link QMAdminEventCriteria#QMAdminEventCriteria(QMEventCriteria, Set)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link HashSet#HashSet()} add {@code foo}.
   *   <li>Then return hasUsers.
   * </ul>
   *
   * <p>Method under test: {@link QMAdminEventCriteria#QMAdminEventCriteria(QMEventCriteria, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMAdminEventCriteria.<init>(QMEventCriteria, Set)"})
  public void testNewQMAdminEventCriteria_givenFoo_whenHashSetAddFoo_thenReturnHasUsers() {
    // Arrange
    QMEventCriteria criteria = new QMEventCriteria();

    HashSet<String> users = new HashSet<>();
    users.add("foo");

    // Act
    QMAdminEventCriteria actualQmAdminEventCriteria = new QMAdminEventCriteria(criteria, users);

    // Assert
    assertTrue(actualQmAdminEventCriteria.hasUsers());
    assertSame(users, actualQmAdminEventCriteria.getUsers());
    assertSame(criteria, actualQmAdminEventCriteria.getCriteria());
  }

  /**
   * Test {@link QMAdminEventCriteria#QMAdminEventCriteria(QMEventCriteria, Set)}.
   *
   * <ul>
   *   <li>When {@link HashSet#HashSet()}.
   *   <li>Then return not hasUsers.
   * </ul>
   *
   * <p>Method under test: {@link QMAdminEventCriteria#QMAdminEventCriteria(QMEventCriteria, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMAdminEventCriteria.<init>(QMEventCriteria, Set)"})
  public void testNewQMAdminEventCriteria_whenHashSet_thenReturnNotHasUsers() {
    // Arrange
    QMEventCriteria criteria = new QMEventCriteria();

    // Act
    QMAdminEventCriteria actualQmAdminEventCriteria =
        new QMAdminEventCriteria(criteria, new HashSet<>());

    // Assert
    assertFalse(actualQmAdminEventCriteria.hasUsers());
    assertTrue(actualQmAdminEventCriteria.getUsers().isEmpty());
    assertSame(criteria, actualQmAdminEventCriteria.getCriteria());
  }

  /**
   * Test {@link QMAdminEventCriteria#hasUsers()}.
   *
   * <ul>
   *   <li>Given {@link HashSet#HashSet()} add {@code foo}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link QMAdminEventCriteria#hasUsers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMAdminEventCriteria.hasUsers()"})
  public void testHasUsers_givenHashSetAddFoo_thenReturnTrue() {
    // Arrange
    HashSet<String> users = new HashSet<>();
    users.add("foo");
    QMAdminEventCriteria qmAdminEventCriteria =
        new QMAdminEventCriteria(new QMEventCriteria(), users);

    // Act and Assert
    assertTrue(qmAdminEventCriteria.hasUsers());
  }

  /**
   * Test {@link QMAdminEventCriteria#hasUsers()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link QMAdminEventCriteria#hasUsers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMAdminEventCriteria.hasUsers()"})
  public void testHasUsers_thenReturnFalse() {
    // Arrange
    QMEventCriteria criteria = new QMEventCriteria();
    QMAdminEventCriteria qmAdminEventCriteria = new QMAdminEventCriteria(criteria, new HashSet<>());

    // Act and Assert
    assertFalse(qmAdminEventCriteria.hasUsers());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link QMAdminEventCriteria#setUsers(Set)}
   *   <li>{@link QMAdminEventCriteria#getCriteria()}
   *   <li>{@link QMAdminEventCriteria#getUsers()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "QMEventCriteria QMAdminEventCriteria.getCriteria()",
    "Set QMAdminEventCriteria.getUsers()",
    "void QMAdminEventCriteria.setUsers(Set)"
  })
  public void testGettersAndSetters() {
    // Arrange
    QMEventCriteria criteria = new QMEventCriteria();
    QMAdminEventCriteria qmAdminEventCriteria = new QMAdminEventCriteria(criteria, new HashSet<>());
    HashSet<String> users = new HashSet<>();

    // Act
    qmAdminEventCriteria.setUsers(users);
    QMEventCriteria actualCriteria = qmAdminEventCriteria.getCriteria();
    Set<String> actualUsers = qmAdminEventCriteria.getUsers();

    // Assert
    assertTrue(actualUsers.isEmpty());
    assertSame(users, actualUsers);
    assertSame(criteria, actualCriteria);
  }
}
