package org.jkiss.dbeaver.model.impl.jdbc.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import javax.sql.rowset.RowSetMetaDataImpl;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSAttributeBase;
import org.jkiss.dbeaver.model.struct.DBSEntityAttribute;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCCompositeStaticDiffblueTest {
  /**
   * Test {@link JDBCCompositeStatic#JDBCCompositeStatic(JDBCComposite, DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link JDBCCompositeStatic#JDBCCompositeStatic(JDBCComposite,
   * DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCompositeStatic.<init>(JDBCComposite, DBRProgressMonitor)"})
  public void testNewJDBCCompositeStatic() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    JDBCCompositeDynamic struct = new JDBCCompositeDynamic(session, null, new RowSetMetaDataImpl());
    JDBCCompositeDynamic struct2 = new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());
    JDBCCompositeUnknown struct3 = new JDBCCompositeUnknown(struct2, new LoggingProgressMonitor());
    JDBCCompositeDynamic struct4 = new JDBCCompositeDynamic(struct3, new LoggingProgressMonitor());

    // Act
    JDBCCompositeStatic actualJdbcCompositeStatic =
        new JDBCCompositeStatic(struct4, new LoggingProgressMonitor());

    // Assert
    verify(session).getDataSource();
    DBSAttributeBase[] attributes = actualJdbcCompositeStatic.getAttributes();
    assertTrue(attributes instanceof DBSEntityAttribute[]);
    assertEquals("[]", actualJdbcCompositeStatic.getStringRepresentation());
    assertNull(actualJdbcCompositeStatic.getRawValue());
    assertEquals(0, actualJdbcCompositeStatic.getAttributeCount());
    assertEquals(0, actualJdbcCompositeStatic.getValues().length);
    assertEquals(0, attributes.length);
    assertTrue(actualJdbcCompositeStatic.isNull());
  }

  /**
   * Test {@link JDBCCompositeStatic#cloneValue(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link JDBCCompositeStatic#cloneValue(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCCompositeStatic JDBCCompositeStatic.cloneValue(DBRProgressMonitor)"})
  public void testCloneValue() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    JDBCCompositeDynamic struct = new JDBCCompositeDynamic(session, null, new RowSetMetaDataImpl());
    JDBCCompositeDynamic struct2 = new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());
    JDBCCompositeStatic jdbcCompositeStatic =
        new JDBCCompositeStatic(struct2, new LoggingProgressMonitor());

    // Act
    JDBCCompositeStatic actualCloneValueResult =
        jdbcCompositeStatic.cloneValue(new LoggingProgressMonitor());

    // Assert
    verify(session).getDataSource();
    DBSAttributeBase[] attributes = actualCloneValueResult.getAttributes();
    assertTrue(attributes instanceof DBSEntityAttribute[]);
    assertEquals("[]", actualCloneValueResult.getStringRepresentation());
    assertNull(actualCloneValueResult.getRawValue());
    assertEquals(0, actualCloneValueResult.getAttributeCount());
    assertEquals(0, actualCloneValueResult.getValues().length);
    assertEquals(0, attributes.length);
    assertTrue(actualCloneValueResult.isNull());
  }

  /**
   * Test {@link JDBCCompositeStatic#getStringRepresentation()}.
   *
   * <p>Method under test: {@link JDBCCompositeStatic#getStringRepresentation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCCompositeStatic.getStringRepresentation()"})
  public void testGetStringRepresentation() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    JDBCCompositeDynamic struct = new JDBCCompositeDynamic(session, null, new RowSetMetaDataImpl());
    JDBCCompositeDynamic struct2 = new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());
    JDBCCompositeStatic jdbcCompositeStatic =
        new JDBCCompositeStatic(struct2, new LoggingProgressMonitor());

    // Act
    String actualStringRepresentation = jdbcCompositeStatic.getStringRepresentation();

    // Assert
    verify(session).getDataSource();
    assertEquals("[]", actualStringRepresentation);
  }
}
