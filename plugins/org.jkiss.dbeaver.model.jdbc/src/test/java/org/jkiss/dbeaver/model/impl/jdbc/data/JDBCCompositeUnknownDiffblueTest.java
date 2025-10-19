package org.jkiss.dbeaver.model.impl.jdbc.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import org.jkiss.dbeaver.model.impl.jdbc.data.JDBCComposite.StructType;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCConnectionImpl;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSAttributeBase;
import org.jkiss.dbeaver.model.struct.DBSEntityAttribute;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCCompositeUnknownDiffblueTest {
  /**
   * Test {@link JDBCCompositeUnknown#JDBCCompositeUnknown(JDBCComposite, DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link JDBCCompositeUnknown#JDBCCompositeUnknown(JDBCComposite,
   * DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCompositeUnknown.<init>(JDBCComposite, DBRProgressMonitor)"})
  public void testNewJDBCCompositeUnknown() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    JDBCCompositeDynamic struct = new JDBCCompositeDynamic(session, null, new RowSetMetaDataImpl());
    JDBCCompositeDynamic struct2 = new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());
    JDBCCompositeUnknown struct3 = new JDBCCompositeUnknown(struct2, new LoggingProgressMonitor());
    JDBCCompositeDynamic struct4 = new JDBCCompositeDynamic(struct3, new LoggingProgressMonitor());

    // Act
    JDBCCompositeUnknown actualJdbcCompositeUnknown =
        new JDBCCompositeUnknown(struct4, new LoggingProgressMonitor());

    // Assert
    verify(session).getDataSource();
    DBSAttributeBase[] attributes = actualJdbcCompositeUnknown.getAttributes();
    assertTrue(attributes instanceof DBSEntityAttribute[]);
    assertEquals("[]", actualJdbcCompositeUnknown.getStringRepresentation());
    assertNull(actualJdbcCompositeUnknown.getRawValue());
    assertEquals(0, actualJdbcCompositeUnknown.getAttributeCount());
    assertEquals(0, actualJdbcCompositeUnknown.getValues().length);
    assertEquals(0, attributes.length);
    assertTrue(actualJdbcCompositeUnknown.isNull());
  }

  /**
   * Test {@link JDBCCompositeUnknown#JDBCCompositeUnknown(DBCSession, Object)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then Attributes return {@code DBSEntityAttribute[]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCompositeUnknown#JDBCCompositeUnknown(DBCSession, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCompositeUnknown.<init>(DBCSession, Object)"})
  public void testNewJDBCCompositeUnknown_givenNull_thenAttributesReturnDBSEntityAttribute() {
    // Arrange
    JDBCConnectionImpl session = mock(JDBCConnectionImpl.class);
    when(session.getDataSource()).thenReturn(null);

    // Act
    JDBCCompositeUnknown actualJdbcCompositeUnknown =
        new JDBCCompositeUnknown(session, "Struct Data");

    // Assert
    verify(session).getDataSource();
    DBSAttributeBase[] attributes = actualJdbcCompositeUnknown.getAttributes();
    assertTrue(attributes instanceof DBSEntityAttribute[]);
    assertTrue(actualJdbcCompositeUnknown.getDataType() instanceof StructType);
    assertEquals("Object", actualJdbcCompositeUnknown.getTypeName());
    assertEquals("[Struct Data]", actualJdbcCompositeUnknown.getStringRepresentation());
    assertNull(actualJdbcCompositeUnknown.getRawValue());
    assertEquals(0, actualJdbcCompositeUnknown.getAttributeCount());
    assertEquals(0, attributes.length);
    assertEquals(1, actualJdbcCompositeUnknown.getValues().length);
    assertFalse(actualJdbcCompositeUnknown.isModified());
    assertFalse(actualJdbcCompositeUnknown.isNull());
  }

  /**
   * Test {@link JDBCCompositeUnknown#cloneValue(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link JDBCCompositeUnknown#cloneValue(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCCompositeUnknown JDBCCompositeUnknown.cloneValue(DBRProgressMonitor)"})
  public void testCloneValue() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    JDBCCompositeDynamic struct = new JDBCCompositeDynamic(session, null, new RowSetMetaDataImpl());
    JDBCCompositeDynamic struct2 = new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());
    JDBCCompositeUnknown jdbcCompositeUnknown =
        new JDBCCompositeUnknown(struct2, new LoggingProgressMonitor());

    // Act
    JDBCCompositeUnknown actualCloneValueResult =
        jdbcCompositeUnknown.cloneValue(new LoggingProgressMonitor());

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
   * Test {@link JDBCCompositeUnknown#getStringRepresentation()}.
   *
   * <ul>
   *   <li>Then return {@code []}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCompositeUnknown#getStringRepresentation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCCompositeUnknown.getStringRepresentation()"})
  public void testGetStringRepresentation_thenReturnLeftSquareBracketRightSquareBracket()
      throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    JDBCCompositeDynamic struct = new JDBCCompositeDynamic(session, null, new RowSetMetaDataImpl());
    JDBCCompositeDynamic struct2 = new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());
    JDBCCompositeUnknown jdbcCompositeUnknown =
        new JDBCCompositeUnknown(struct2, new LoggingProgressMonitor());

    // Act
    String actualStringRepresentation = jdbcCompositeUnknown.getStringRepresentation();

    // Assert
    verify(session).getDataSource();
    assertEquals("[]", actualStringRepresentation);
  }
}
