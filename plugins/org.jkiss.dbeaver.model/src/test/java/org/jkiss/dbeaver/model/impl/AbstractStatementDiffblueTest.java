package org.jkiss.dbeaver.model.impl;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.exec.DBCExecutionSource;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.jkiss.dbeaver.model.struct.DBSDataContainer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractStatementDiffblueTest {
  /**
   * Test {@link AbstractStatement#getSession()}.
   *
   * <p>Method under test: {@link AbstractStatement#getSession()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCSession AbstractStatement.getSession()"})
  public void testGetSession() {
    // Arrange
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    // Act
    DBCSession actualSession = localStatement.getSession();

    // Assert
    assertSame(localStatement.connection, actualSession);
  }

  /**
   * Test {@link AbstractStatement#getStatementSource()}.
   *
   * <p>Method under test: {@link AbstractStatement#getStatementSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCExecutionSource AbstractStatement.getStatementSource()"})
  public void testGetStatementSource() {
    // Arrange, Act and Assert
    assertNull(new LocalStatement(mock(DBCSession.class), "Text").getStatementSource());
  }

  /**
   * Test {@link AbstractStatement#setStatementSource(DBCExecutionSource)}.
   *
   * <p>Method under test: {@link AbstractStatement#setStatementSource(DBCExecutionSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractStatement.setStatementSource(DBCExecutionSource)"})
  public void testSetStatementSource() {
    // Arrange
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");
    AbstractExecutionSource source =
        new AbstractExecutionSource(
            mock(DBSDataContainer.class), mock(DBCExecutionContext.class), DBPEvent.RENAME);

    // Act
    localStatement.setStatementSource(source);

    // Assert
    assertSame(source, localStatement.getStatementSource());
  }
}
