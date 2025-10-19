package org.jkiss.dbeaver.model.sql.completion;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPKeywordType;
import org.jkiss.dbeaver.model.DBPNamedObject;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLCompletionHelperDiffblueTest {
  /**
   * Test {@link SQLCompletionHelper#readAdditionalProposalInfo(DBRProgressMonitor,
   * SQLCompletionContext, DBPNamedObject, String[], DBPKeywordType)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCompletionHelper#readAdditionalProposalInfo(DBRProgressMonitor,
   * SQLCompletionContext, DBPNamedObject, String[], DBPKeywordType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLCompletionHelper.readAdditionalProposalInfo(DBRProgressMonitor, SQLCompletionContext, DBPNamedObject, String[], DBPKeywordType)"
  })
  public void testReadAdditionalProposalInfo_givenFalse() {
    // Arrange
    SQLCompletionContext context = mock(SQLCompletionContext.class);
    when(context.isShowServerHelp()).thenReturn(false);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));

    // Act
    String actualReadAdditionalProposalInfoResult =
        SQLCompletionHelper.readAdditionalProposalInfo(
            null, context, null, new String[] {"Keywords"}, DBPKeywordType.KEYWORD);

    // Assert
    verify(context).getDataSource();
    verify(context).isShowServerHelp();
    assertEquals("Keywords", actualReadAdditionalProposalInfoResult);
  }

  /**
   * Test {@link SQLCompletionHelper#readAdditionalProposalInfo(DBRProgressMonitor,
   * SQLCompletionContext, DBPNamedObject, String[], DBPKeywordType)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCompletionHelper#readAdditionalProposalInfo(DBRProgressMonitor,
   * SQLCompletionContext, DBPNamedObject, String[], DBPKeywordType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLCompletionHelper.readAdditionalProposalInfo(DBRProgressMonitor, SQLCompletionContext, DBPNamedObject, String[], DBPKeywordType)"
  })
  public void testReadAdditionalProposalInfo_givenNull() {
    // Arrange
    SQLCompletionContext context = mock(SQLCompletionContext.class);
    when(context.getDataSource()).thenReturn(null);

    // Act
    String actualReadAdditionalProposalInfoResult =
        SQLCompletionHelper.readAdditionalProposalInfo(
            null, context, null, new String[] {"Keywords"}, DBPKeywordType.KEYWORD);

    // Assert
    verify(context).getDataSource();
    assertEquals("Keywords", actualReadAdditionalProposalInfoResult);
  }

  /**
   * Test {@link SQLCompletionHelper#readAdditionalProposalInfo(DBRProgressMonitor,
   * SQLCompletionContext, DBPNamedObject, String[], DBPKeywordType)}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>Then return {@code <b>Keywords</b> (KEYWORD)}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCompletionHelper#readAdditionalProposalInfo(DBRProgressMonitor,
   * SQLCompletionContext, DBPNamedObject, String[], DBPKeywordType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLCompletionHelper.readAdditionalProposalInfo(DBRProgressMonitor, SQLCompletionContext, DBPNamedObject, String[], DBPKeywordType)"
  })
  public void testReadAdditionalProposalInfo_givenTrue_thenReturnBKeywordsBKeyword() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLCompletionContext context = mock(SQLCompletionContext.class);
    when(context.isShowServerHelp()).thenReturn(true);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));

    // Act
    String actualReadAdditionalProposalInfoResult =
        SQLCompletionHelper.readAdditionalProposalInfo(
            monitor, context, null, new String[] {"Keywords"}, DBPKeywordType.KEYWORD);

    // Assert
    verify(context, atLeast(1)).getDataSource();
    verify(context).isShowServerHelp();
    assertEquals("<b>Keywords</b> (KEYWORD)", actualReadAdditionalProposalInfoResult);
  }

  /**
   * Test {@link SQLCompletionHelper#readAdditionalProposalInfo(DBRProgressMonitor,
   * SQLCompletionContext, DBPNamedObject, String[], DBPKeywordType)}.
   *
   * <ul>
   *   <li>When {@link SQLCompletionContext}.
   *   <li>Then return {@code Keywords}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCompletionHelper#readAdditionalProposalInfo(DBRProgressMonitor,
   * SQLCompletionContext, DBPNamedObject, String[], DBPKeywordType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLCompletionHelper.readAdditionalProposalInfo(DBRProgressMonitor, SQLCompletionContext, DBPNamedObject, String[], DBPKeywordType)"
  })
  public void testReadAdditionalProposalInfo_whenSQLCompletionContext_thenReturnKeywords() {
    // Arrange and Act
    String actualReadAdditionalProposalInfoResult =
        SQLCompletionHelper.readAdditionalProposalInfo(
            null, mock(SQLCompletionContext.class), null, new String[] {"Keywords"}, null);

    // Assert
    assertEquals("Keywords", actualReadAdditionalProposalInfoResult);
  }
}
