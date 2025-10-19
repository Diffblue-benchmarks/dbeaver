package org.jkiss.dbeaver.model.navigator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBNDataSourceDiffblueTest {
  /**
   * Test {@link DBNDataSource#getDataSourceNode(DBNNode)}.
   *
   * <p>Method under test: {@link DBNDataSource#getDataSourceNode(DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNDataSource DBNDataSource.getDataSourceNode(DBNNode)"})
  public void testGetDataSourceNode() {
    // Arrange, Act and Assert
    assertNull(DBNDataSource.getDataSourceNode(new DBNEmptyNode()));
  }

  /**
   * Test {@link DBNDataSource#makeDataSourceItemPath(DBPDataSourceContainer)}.
   *
   * <p>Method under test: {@link DBNDataSource#makeDataSourceItemPath(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNDataSource.makeDataSourceItemPath(DBPDataSourceContainer)"})
  public void testMakeDataSourceItemPath() {
    // Arrange
    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getId()).thenReturn("42");

    // Act
    String actualMakeDataSourceItemPathResult = DBNDataSource.makeDataSourceItemPath(dataSource);

    // Assert
    verify(dataSource).getId();
    assertEquals("database://42", actualMakeDataSourceItemPathResult);
  }
}
