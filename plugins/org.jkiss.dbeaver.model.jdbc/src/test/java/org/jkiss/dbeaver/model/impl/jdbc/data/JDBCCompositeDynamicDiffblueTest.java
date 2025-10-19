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

public class JDBCCompositeDynamicDiffblueTest {
  /**
   * Test {@link JDBCCompositeDynamic#JDBCCompositeDynamic(JDBCComposite, DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link JDBCCompositeDynamic#JDBCCompositeDynamic(JDBCComposite,
   * DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCompositeDynamic.<init>(JDBCComposite, DBRProgressMonitor)"})
  public void testNewJDBCCompositeDynamic() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    JDBCCompositeDynamic struct = new JDBCCompositeDynamic(session, null, new RowSetMetaDataImpl());
    JDBCCompositeDynamic struct2 = new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());
    JDBCCompositeUnknown struct3 = new JDBCCompositeUnknown(struct2, new LoggingProgressMonitor());

    // Act
    JDBCCompositeDynamic actualJdbcCompositeDynamic =
        new JDBCCompositeDynamic(struct3, new LoggingProgressMonitor());

    // Assert
    verify(session).getDataSource();
    DBSAttributeBase[] attributes = actualJdbcCompositeDynamic.getAttributes();
    assertTrue(attributes instanceof DBSEntityAttribute[]);
    assertEquals("[]", actualJdbcCompositeDynamic.getStringRepresentation());
    assertNull(actualJdbcCompositeDynamic.getRawValue());
    assertEquals(0, actualJdbcCompositeDynamic.getAttributeCount());
    assertEquals(0, actualJdbcCompositeDynamic.getValues().length);
    assertEquals(0, attributes.length);
    assertTrue(actualJdbcCompositeDynamic.isNull());
  }

  /**
   * Test {@link JDBCCompositeDynamic#cloneValue(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link JDBCCompositeDynamic#cloneValue(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCCompositeDynamic JDBCCompositeDynamic.cloneValue(DBRProgressMonitor)"})
  public void testCloneValue() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    JDBCCompositeDynamic struct = new JDBCCompositeDynamic(session, null, new RowSetMetaDataImpl());
    JDBCCompositeDynamic struct2 = new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());
    JDBCCompositeUnknown struct3 = new JDBCCompositeUnknown(struct2, new LoggingProgressMonitor());
    JDBCCompositeDynamic jdbcCompositeDynamic =
        new JDBCCompositeDynamic(struct3, new LoggingProgressMonitor());

    // Act
    JDBCCompositeDynamic actualCloneValueResult =
        jdbcCompositeDynamic.cloneValue(new LoggingProgressMonitor());

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
}
