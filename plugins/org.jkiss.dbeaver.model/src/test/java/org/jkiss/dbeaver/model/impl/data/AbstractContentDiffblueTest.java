package org.jkiss.dbeaver.model.impl.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractContentDiffblueTest {
  /**
   * Test {@link AbstractContent#getDataSource()}.
   *
   * <p>Method under test: {@link AbstractContent#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource AbstractContent.getDataSource()"})
  public void testGetDataSource() {
    // Arrange
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));

    // Act
    new StringContent(executionContext, "Data").getDataSource();

    // Assert
    verify(executionContext).getDataSource();
  }

  /**
   * Test {@link AbstractContent#toString()}.
   *
   * <p>Method under test: {@link AbstractContent#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AbstractContent.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals("Data", new StringContent(mock(DBCExecutionContext.class), "Data").toString());
  }

  /**
   * Test {@link AbstractContent#isModified()}.
   *
   * <p>Method under test: {@link AbstractContent#isModified()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractContent.isModified()"})
  public void testIsModified() {
    // Arrange, Act and Assert
    assertFalse(new StringContent(mock(DBCExecutionContext.class), "Data").isModified());
  }
}
