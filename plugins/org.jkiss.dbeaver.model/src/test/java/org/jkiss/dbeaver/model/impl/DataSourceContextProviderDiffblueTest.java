package org.jkiss.dbeaver.model.impl;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataSourceContextProviderDiffblueTest {
  /**
   * Test {@link DataSourceContextProvider#DataSourceContextProvider(DBSObject)}.
   *
   * <p>Method under test: {@link DataSourceContextProvider#DataSourceContextProvider(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataSourceContextProvider.<init>(DBSObject)"})
  public void testNewDataSourceContextProvider() {
    // Arrange
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    // Act and Assert
    assertNull(new DataSourceContextProvider(object).getExecutionContext());
  }

  /**
   * Test {@link DataSourceContextProvider#getExecutionContext()}.
   *
   * <p>Method under test: {@link DataSourceContextProvider#getExecutionContext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.exec.DBCExecutionContext DataSourceContextProvider.getExecutionContext()"
  })
  public void testGetExecutionContext() {
    // Arrange, Act and Assert
    assertNull(new DataSourceContextProvider(null).getExecutionContext());
  }
}
