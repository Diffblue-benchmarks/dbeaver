package org.jkiss.dbeaver.model.impl;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.DBCStatement;
import org.jkiss.dbeaver.model.impl.local.LocalResultSet;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractResultSetDiffblueTest {
  /**
   * Test {@link AbstractResultSet#getSession()}.
   *
   * <p>Method under test: {@link AbstractResultSet#getSession()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCSession AbstractResultSet.getSession()"})
  public void testGetSession() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> localResultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBCSession actualSession = localResultSet.getSession();

    // Assert
    assertSame(localResultSet.session, actualSession);
  }

  /**
   * Test {@link AbstractResultSet#getSourceStatement()}.
   *
   * <p>Method under test: {@link AbstractResultSet#getSourceStatement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCStatement AbstractResultSet.getSourceStatement()"})
  public void testGetSourceStatement() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> localResultSet = new LocalResultSet<>(session, localStatement);

    // Act
    DBCStatement actualSourceStatement = localResultSet.getSourceStatement();

    // Assert
    assertSame(localResultSet.statement, actualSourceStatement);
  }

  /**
   * Test {@link AbstractResultSet#getAttributeValueMeta(int)}.
   *
   * <p>Method under test: {@link AbstractResultSet#getAttributeValueMeta(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.data.DBDValueMeta AbstractResultSet.getAttributeValueMeta(int)"
  })
  public void testGetAttributeValueMeta() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> localResultSet = new LocalResultSet<>(session, localStatement);

    // Act and Assert
    assertNull(localResultSet.getAttributeValueMeta(1));
  }

  /**
   * Test {@link AbstractResultSet#getRowMeta()}.
   *
   * <p>Method under test: {@link AbstractResultSet#getRowMeta()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"org.jkiss.dbeaver.model.data.DBDValueMeta AbstractResultSet.getRowMeta()"})
  public void testGetRowMeta() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> localResultSet = new LocalResultSet<>(session, localStatement);

    // Act and Assert
    assertNull(localResultSet.getRowMeta());
  }

  /**
   * Test {@link AbstractResultSet#getResultSetName()}.
   *
   * <p>Method under test: {@link AbstractResultSet#getResultSetName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AbstractResultSet.getResultSetName()"})
  public void testGetResultSetName() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> localResultSet = new LocalResultSet<>(session, localStatement);

    // Act and Assert
    assertNull(localResultSet.getResultSetName());
  }
}
