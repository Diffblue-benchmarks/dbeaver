package org.jkiss.dbeaver.ui.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DatabaseOutputViewDiffblueTest {
  /**
   * Test {@link DatabaseOutputView#getAdapter(Class)}.
   *
   * <p>Method under test: {@link DatabaseOutputView#getAdapter(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object DatabaseOutputView.getAdapter(Class)"})
  public void testGetAdapter() {
    // Arrange
    DatabaseOutputView databaseOutputView = new DatabaseOutputView();
    Class<Object> adapter = Object.class;

    // Act and Assert
    assertNull(databaseOutputView.getAdapter(adapter));
  }

  /**
   * Test new {@link DatabaseOutputView} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link DatabaseOutputView}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseOutputView.<init>()"})
  public void testNewDatabaseOutputView() {
    // Arrange and Act
    DatabaseOutputView actualDatabaseOutputView = new DatabaseOutputView();

    // Assert
    assertEquals("", actualDatabaseOutputView.getContentDescription());
    assertEquals("", actualDatabaseOutputView.getPartName());
    assertEquals("", actualDatabaseOutputView.getTitle());
    assertEquals("", actualDatabaseOutputView.getTitleToolTip());
    assertNull(actualDatabaseOutputView.getViewSite());
    assertNull(actualDatabaseOutputView.getSite());
    assertEquals(0, actualDatabaseOutputView.getOrientation());
    assertTrue(actualDatabaseOutputView.getPartProperties().isEmpty());
  }
}
