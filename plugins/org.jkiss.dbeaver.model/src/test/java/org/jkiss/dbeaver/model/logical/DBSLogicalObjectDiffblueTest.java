package org.jkiss.dbeaver.model.logical;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBSLogicalObjectDiffblueTest {
  /**
   * Test {@link DBSLogicalObject#isExclude()}.
   *
   * <p>Method under test: {@link DBSLogicalObject#isExclude()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSLogicalObject.isExclude()"})
  public void testIsExclude() {
    // Arrange, Act and Assert
    assertFalse(new DBSLogicalCatalog().isExclude());
  }

  /**
   * Test {@link DBSLogicalObject#isObjectIncluded(DBSObject)}.
   *
   * <p>Method under test: {@link DBSLogicalObject#isObjectIncluded(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSLogicalObject.isObjectIncluded(DBSObject)"})
  public void testIsObjectIncluded() {
    // Arrange
    DBSLogicalCatalog dbsLogicalCatalog = new DBSLogicalCatalog();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    boolean actualIsObjectIncludedResult =
        dbsLogicalCatalog.isObjectIncluded(new DBVModel(dataSourceContainer));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(actualIsObjectIncludedResult);
  }
}
