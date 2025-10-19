package org.jkiss.dbeaver.model.navigator.meta;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBXTreeItemDiffblueTest {
  /**
   * Test {@link DBXTreeItem#findPropertyReadMethod(Class, String)}.
   *
   * <ul>
   *   <li>When {@code equals}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBXTreeItem#findPropertyReadMethod(Class, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.reflect.Method DBXTreeItem.findPropertyReadMethod(Class, String)"})
  public void testFindPropertyReadMethod_whenEquals_thenReturnNull() {
    // Arrange
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertNull(DBXTreeItem.findPropertyReadMethod(clazz, "equals"));
  }

  /**
   * Test {@link DBXTreeItem#findPropertyReadMethod(Class, String)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBXTreeItem#findPropertyReadMethod(Class, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.reflect.Method DBXTreeItem.findPropertyReadMethod(Class, String)"})
  public void testFindPropertyReadMethod_whenJavaLangObject_thenReturnNull() {
    // Arrange
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertNull(DBXTreeItem.findPropertyReadMethod(clazz, "Property Name"));
  }

  /**
   * Test {@link DBXTreeItem#findPropertyReadMethod(Class, String)}.
   *
   * <ul>
   *   <li>When {@code DBXTreeItem}.
   * </ul>
   *
   * <p>Method under test: {@link DBXTreeItem#findPropertyReadMethod(Class, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.reflect.Method DBXTreeItem.findPropertyReadMethod(Class, String)"})
  public void testFindPropertyReadMethod_whenOrgJkissDbeaverModelNavigatorMetaDBXTreeItem() {
    // Arrange
    Class<DBXTreeItem> clazz = DBXTreeItem.class;

    // Act and Assert
    assertNull(DBXTreeItem.findPropertyReadMethod(clazz, "Property Name"));
  }
}
