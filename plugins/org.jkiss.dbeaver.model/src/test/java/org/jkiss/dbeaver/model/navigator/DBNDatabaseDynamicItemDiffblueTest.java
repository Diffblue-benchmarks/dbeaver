package org.jkiss.dbeaver.model.navigator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DBNDatabaseDynamicItemDiffblueTest {
  @InjectMocks private DBNDatabaseDynamicItem dBNDatabaseDynamicItem;

  /**
   * Test {@link DBNDatabaseDynamicItem#dispose(boolean)}.
   *
   * <p>Method under test: {@link DBNDatabaseDynamicItem#dispose(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNDatabaseDynamicItem.dispose(boolean)"})
  public void testDispose() {
    // Arrange and Act
    dBNDatabaseDynamicItem.dispose(true);

    // Assert
    assertEquals("", dBNDatabaseDynamicItem.getNodeType());
    assertEquals("", dBNDatabaseDynamicItem.getNodeTypeLabel());
    assertEquals("[NULL]", dBNDatabaseDynamicItem.getNodeDisplayName());
    assertEquals("[NULL]", dBNDatabaseDynamicItem.getNodeFullName());
    assertEquals("[NULL]", dBNDatabaseDynamicItem.getNodeId());
    assertEquals("[NULL]", dBNDatabaseDynamicItem.getName());
    assertEquals("[NULL]", dBNDatabaseDynamicItem.getNodeTargetName());
    assertNull(dBNDatabaseDynamicItem.getValueObject());
    assertNull(dBNDatabaseDynamicItem.getObject());
    assertTrue(dBNDatabaseDynamicItem.isDisposed());
  }

  /**
   * Test {@link DBNDatabaseDynamicItem#allowsChildren()}.
   *
   * <ul>
   *   <li>Given {@link DBNDatabaseDynamicItem}.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseDynamicItem#allowsChildren()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNDatabaseDynamicItem.allowsChildren()"})
  public void testAllowsChildren_givenDBNDatabaseDynamicItem() {
    // Arrange, Act and Assert
    assertFalse(dBNDatabaseDynamicItem.allowsChildren());
  }

  /**
   * Test {@link DBNDatabaseDynamicItem#allowsChildren()}.
   *
   * <ul>
   *   <li>Given {@link DBNDatabaseDynamicItem} dispose {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseDynamicItem#allowsChildren()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNDatabaseDynamicItem.allowsChildren()"})
  public void testAllowsChildren_givenDBNDatabaseDynamicItemDisposeTrue() {
    // Arrange
    dBNDatabaseDynamicItem.dispose(true);

    // Act and Assert
    assertFalse(dBNDatabaseDynamicItem.allowsChildren());
  }
}
