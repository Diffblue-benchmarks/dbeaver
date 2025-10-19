package org.jkiss.dbeaver.model.impl.edit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.edit.DBEPersistAction;
import org.jkiss.dbeaver.model.edit.DBEPersistAction.ActionType;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLDatabasePersistActionCommentDiffblueTest {
  /**
   * Test {@link SQLDatabasePersistActionComment#SQLDatabasePersistActionComment(DBPDataSource,
   * String)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then calls {@link DBPDataSource#getSQLDialect()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLDatabasePersistActionComment#SQLDatabasePersistActionComment(DBPDataSource, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDatabasePersistActionComment.<init>(DBPDataSource, String)"})
  public void testNewSQLDatabasePersistActionComment_givenInstance_thenCallsGetSQLDialect() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    SQLDatabasePersistActionComment actualSqlDatabasePersistActionComment =
        new SQLDatabasePersistActionComment(dataSource, "Comment");

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("-- Comment", actualSqlDatabasePersistActionComment.getScript());
    assertEquals("Comment", actualSqlDatabasePersistActionComment.getTitle());
    assertEquals(ActionType.COMMENT, actualSqlDatabasePersistActionComment.getType());
    assertFalse(actualSqlDatabasePersistActionComment.isComplex());
  }

  /**
   * Test {@link SQLDatabasePersistActionComment#SQLDatabasePersistActionComment(DBPDataSource,
   * String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLDatabasePersistActionComment#SQLDatabasePersistActionComment(DBPDataSource, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDatabasePersistActionComment.<init>(DBPDataSource, String)"})
  public void testNewSQLDatabasePersistActionComment_whenNull() {
    // Arrange and Act
    SQLDatabasePersistActionComment actualSqlDatabasePersistActionComment =
        new SQLDatabasePersistActionComment(null, "Comment");

    // Assert
    assertEquals("-- Comment", actualSqlDatabasePersistActionComment.getScript());
    assertEquals("Comment", actualSqlDatabasePersistActionComment.getTitle());
    assertEquals(ActionType.COMMENT, actualSqlDatabasePersistActionComment.getType());
    assertFalse(actualSqlDatabasePersistActionComment.isComplex());
  }
}
