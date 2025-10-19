package org.jkiss.dbeaver.ext.clickhouse.model.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.clickhouse.model.ClickhouseTupleType;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.data.DBDValueCloneable;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.DBCStatement;
import org.jkiss.dbeaver.model.impl.local.LocalResultSet;
import org.jkiss.dbeaver.model.impl.local.LocalResultSetColumn;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSAttributeBase;
import org.jkiss.dbeaver.model.struct.DBSDataType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ClickhouseTupleValueDiffblueTest {
  /**
   * Test {@link ClickhouseTupleValue#ClickhouseTupleValue(DBRProgressMonitor, ClickhouseTupleType,
   * Object[])}.
   *
   * <ul>
   *   <li>When {@link LoggingProgressMonitor#LoggingProgressMonitor()}.
   *   <li>Then return RawValue is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ClickhouseTupleValue#ClickhouseTupleValue(DBRProgressMonitor,
   * ClickhouseTupleType, Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClickhouseTupleValue.<init>(DBRProgressMonitor, ClickhouseTupleType, Object[])"
  })
  public void testNewClickhouseTupleValue_whenLoggingProgressMonitor_thenReturnRawValueIsNull()
      throws DBCException {
    // Arrange and Act
    ClickhouseTupleValue actualClickhouseTupleValue =
        new ClickhouseTupleValue(new LoggingProgressMonitor(), null, null);

    // Assert
    assertNull(actualClickhouseTupleValue.getRawValue());
    assertNull(actualClickhouseTupleValue.getDataType());
    assertFalse(actualClickhouseTupleValue.isModified());
    assertTrue(actualClickhouseTupleValue.isNull());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ClickhouseTupleValue#release()}
   *   <li>{@link ClickhouseTupleValue#getDataType()}
   *   <li>{@link ClickhouseTupleValue#getRawValue()}
   *   <li>{@link ClickhouseTupleValue#isModified()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSDataType ClickhouseTupleValue.getDataType()",
    "Object ClickhouseTupleValue.getRawValue()",
    "boolean ClickhouseTupleValue.isModified()",
    "void ClickhouseTupleValue.release()",
    "java.lang.String ClickhouseTupleValue.toString()"
  })
  public void testGettersAndSetters() throws DBCException {
    // Arrange
    Object[] values = new Object[] {"Values"};
    ClickhouseTupleValue clickhouseTupleValue =
        new ClickhouseTupleValue(new LoggingProgressMonitor(), null, values);

    // Act
    clickhouseTupleValue.release();
    DBSDataType actualDataType = clickhouseTupleValue.getDataType();
    Object actualRawValue = clickhouseTupleValue.getRawValue();

    // Assert
    assertNull(actualRawValue);
    assertNull(actualDataType);
    assertFalse(clickhouseTupleValue.isModified());
  }

  /**
   * Test {@link ClickhouseTupleValue#getAttributeValue(DBSAttributeBase)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ClickhouseTupleValue#getAttributeValue(DBSAttributeBase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ClickhouseTupleValue.getAttributeValue(DBSAttributeBase)"})
  public void testGetAttributeValue_thenReturnNull() throws DBCException {
    // Arrange
    ClickhouseTupleValue clickhouseTupleValue =
        new ClickhouseTupleValue(new LoggingProgressMonitor(), null, null);

    // Act and Assert
    assertNull(clickhouseTupleValue.getAttributeValue(null));
  }

  /**
   * Test {@link ClickhouseTupleValue#getAttributeValue(DBSAttributeBase)}.
   *
   * <ul>
   *   <li>Then return {@code Values}.
   * </ul>
   *
   * <p>Method under test: {@link ClickhouseTupleValue#getAttributeValue(DBSAttributeBase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ClickhouseTupleValue.getAttributeValue(DBSAttributeBase)"})
  public void testGetAttributeValue_thenReturnValues() throws DBCException {
    // Arrange
    Object[] values = new Object[] {"Values"};
    ClickhouseTupleValue clickhouseTupleValue =
        new ClickhouseTupleValue(new LoggingProgressMonitor(), null, values);
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act
    Object actualAttributeValue =
        clickhouseTupleValue.getAttributeValue(
            new LocalResultSetColumn(resultSet, 0, "Label", DBPDataKind.BOOLEAN));

    // Assert
    assertEquals("Values", actualAttributeValue);
  }

  /**
   * Test {@link ClickhouseTupleValue#isNull()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ClickhouseTupleValue#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ClickhouseTupleValue.isNull()"})
  public void testIsNull_thenReturnFalse() throws DBCException {
    // Arrange
    Object[] values = new Object[] {"Values"};
    ClickhouseTupleValue clickhouseTupleValue =
        new ClickhouseTupleValue(new LoggingProgressMonitor(), null, values);

    // Act and Assert
    assertFalse(clickhouseTupleValue.isNull());
  }

  /**
   * Test {@link ClickhouseTupleValue#isNull()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ClickhouseTupleValue#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ClickhouseTupleValue.isNull()"})
  public void testIsNull_thenReturnTrue() throws DBCException {
    // Arrange
    ClickhouseTupleValue clickhouseTupleValue =
        new ClickhouseTupleValue(new LoggingProgressMonitor(), null, null);

    // Act and Assert
    assertTrue(clickhouseTupleValue.isNull());
  }

  /**
   * Test {@link ClickhouseTupleValue#cloneValue(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return RawValue is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ClickhouseTupleValue#cloneValue(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDValueCloneable ClickhouseTupleValue.cloneValue(DBRProgressMonitor)"})
  public void testCloneValue_thenReturnRawValueIsNull() throws DBCException {
    // Arrange
    ClickhouseTupleValue clickhouseTupleValue =
        new ClickhouseTupleValue(new LoggingProgressMonitor(), null, null);

    // Act
    DBDValueCloneable actualCloneValueResult =
        clickhouseTupleValue.cloneValue(new LoggingProgressMonitor());

    // Assert
    assertTrue(actualCloneValueResult instanceof ClickhouseTupleValue);
    assertNull(actualCloneValueResult.getRawValue());
    assertNull(((ClickhouseTupleValue) actualCloneValueResult).getDataType());
    assertFalse(actualCloneValueResult.isModified());
    assertTrue(actualCloneValueResult.isNull());
  }
}
