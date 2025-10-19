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
import javax.sql.rowset.serial.SerialException;
import javax.sql.rowset.serial.SerialRef;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JDBCReferenceDiffblueTest {
  @InjectMocks private JDBCReference jDBCReference;

  /**
   * Test {@link JDBCReference#isNull()}.
   *
   * <ul>
   *   <li>Given {@link JDBCReference#JDBCReference(DBSDataType, Object)} with type is {@code null}
   *       and value is {@code null}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCReference#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCReference.isNull()"})
  public void testIsNull_givenJDBCReferenceWithTypeIsNullAndValueIsNull_thenReturnTrue()
      throws DBCException {
    // Arrange
    JDBCReference jdbcReference = new JDBCReference(null, null);

    // Act and Assert
    assertTrue(jdbcReference.isNull());
  }

  /**
   * Test {@link JDBCReference#isNull()}.
   *
   * <ul>
   *   <li>Given {@link JDBCReference#JDBCReference(DBSDataType, Object)} with type is {@code null}
   *       and {@code Value}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCReference#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCReference.isNull()"})
  public void testIsNull_givenJDBCReferenceWithTypeIsNullAndValue_thenReturnFalse()
      throws DBCException {
    // Arrange, Act and Assert
    assertFalse(new JDBCReference(null, "Value").isNull());
  }

  /**
   * Test {@link JDBCReference#release()}.
   *
   * <p>Method under test: {@link JDBCReference#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCReference.release()"})
  public void testRelease() throws DBCException {
    // Arrange
    JDBCReference jdbcReference = new JDBCReference(null, "Value");

    // Act
    jdbcReference.release();

    // Assert
    assertNull(jdbcReference.getRawValue());
    assertNull(jdbcReference.getValue());
    assertTrue(jdbcReference.isNull());
  }

  /**
   * Test {@link JDBCReference#getReferencedObject(DBCSession)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCReference#getReferencedObject(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object JDBCReference.getReferencedObject(DBCSession)"})
  public void testGetReferencedObject_whenNull_thenReturnNull() throws DBCException {
    // Arrange, Act and Assert
    assertNull(jDBCReference.getReferencedObject(null));
  }

  /**
   * Test {@link JDBCReference#toString()}.
   *
   * <ul>
   *   <li>Given {@link JDBCReference#JDBCReference(DBSDataType, Object)} with type is {@code null}
   *       and value is {@code null}.
   *   <li>Then return {@code [NULL]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCReference#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCReference.toString()"})
  public void testToString_givenJDBCReferenceWithTypeIsNullAndValueIsNull_thenReturnNull()
      throws DBCException {
    // Arrange
    JDBCReference jdbcReference = new JDBCReference(null, null);

    // Act and Assert
    assertEquals("[NULL]", jdbcReference.toString());
  }

  /**
   * Test {@link JDBCReference#toString()}.
   *
   * <ul>
   *   <li>Given {@link JDBCReference#JDBCReference(DBSDataType, Object)} with type is {@code null}
   *       and {@code Value}.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCReference#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCReference.toString()"})
  public void testToString_givenJDBCReferenceWithTypeIsNullAndValue_thenReturnValue()
      throws DBCException {
    // Arrange, Act and Assert
    assertEquals("Value", new JDBCReference(null, "Value").toString());
  }

  /**
   * Test {@link JDBCReference#toString()}.
   *
   * <ul>
   *   <li>Then return {@code Base Type Name}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCReference#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCReference.toString()"})
  public void testToString_thenReturnBaseTypeName() throws SerialException, DBCException {
    // Arrange
    SerialRef serialRef = mock(SerialRef.class);
    when(serialRef.getBaseTypeName()).thenReturn("Base Type Name");
    JDBCReference jdbcReference = new JDBCReference(null, serialRef);

    // Act
    String actualToStringResult = jdbcReference.toString();

    // Assert
    verify(serialRef).getBaseTypeName();
    assertEquals("Base Type Name", actualToStringResult);
  }
}
