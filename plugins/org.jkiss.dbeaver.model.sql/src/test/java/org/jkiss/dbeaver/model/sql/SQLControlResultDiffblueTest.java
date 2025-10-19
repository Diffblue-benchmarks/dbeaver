package org.jkiss.dbeaver.model.sql;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLControlResultDiffblueTest {
  /**
   * Test {@link SQLControlResult#success()}.
   *
   * <p>Method under test: {@link SQLControlResult#success()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLControlResult SQLControlResult.success()"})
  public void testSuccess() {
    // Arrange, Act and Assert
    assertNull(SQLControlResult.success().getTransformed());
  }

  /**
   * Test {@link SQLControlResult#failure()}.
   *
   * <p>Method under test: {@link SQLControlResult#failure()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLControlResult SQLControlResult.failure()"})
  public void testFailure() {
    // Arrange, Act and Assert
    assertNull(SQLControlResult.failure().getTransformed());
  }

  /**
   * Test {@link SQLControlResult#transform(SQLScriptElement)}.
   *
   * <p>Method under test: {@link SQLControlResult#transform(SQLScriptElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLControlResult SQLControlResult.transform(SQLScriptElement)"})
  public void testTransform() {
    // Arrange
    SQLQuery element = new SQLQuery(mock(DBPDataSource.class), "Text");

    // Act and Assert
    assertSame(element, SQLControlResult.transform(element).getTransformed());
  }

  /**
   * Test {@link SQLControlResult#getTransformed()}.
   *
   * <p>Method under test: {@link SQLControlResult#getTransformed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLScriptElement SQLControlResult.getTransformed()"})
  public void testGetTransformed() {
    // Arrange, Act and Assert
    assertNull(SQLControlResult.failure().getTransformed());
  }
}
