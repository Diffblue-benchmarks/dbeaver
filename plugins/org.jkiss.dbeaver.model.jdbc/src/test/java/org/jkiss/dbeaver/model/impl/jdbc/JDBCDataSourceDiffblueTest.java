package org.jkiss.dbeaver.model.impl.jdbc;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCDataSourceDiffblueTest {
  /**
   * Test {@link JDBCDataSource#getDataKind(String, int)}.
   *
   * <ul>
   *   <li>When {@code 1111}.
   *   <li>Then return {@code OBJECT}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSource#getDataKind(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataKind JDBCDataSource.getDataKind(String, int)"})
  public void testGetDataKind_when1111_thenReturnObject() {
    // Arrange, Act and Assert
    assertEquals(DBPDataKind.OBJECT, JDBCDataSource.getDataKind("Type Name", 1111));
  }

  /**
   * Test {@link JDBCDataSource#getDataKind(String, int)}.
   *
   * <ul>
   *   <li>When {@code 2002}.
   *   <li>Then return {@code STRUCT}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSource#getDataKind(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataKind JDBCDataSource.getDataKind(String, int)"})
  public void testGetDataKind_when2002_thenReturnStruct() {
    // Arrange, Act and Assert
    assertEquals(DBPDataKind.STRUCT, JDBCDataSource.getDataKind("Type Name", 2002));
  }

  /**
   * Test {@link JDBCDataSource#getDataKind(String, int)}.
   *
   * <ul>
   *   <li>When {@code 2003}.
   *   <li>Then return {@code ARRAY}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSource#getDataKind(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataKind JDBCDataSource.getDataKind(String, int)"})
  public void testGetDataKind_when2003_thenReturnArray() {
    // Arrange, Act and Assert
    assertEquals(DBPDataKind.ARRAY, JDBCDataSource.getDataKind("Type Name", 2003));
  }

  /**
   * Test {@link JDBCDataSource#getDataKind(String, int)}.
   *
   * <ul>
   *   <li>When {@code 2004}.
   *   <li>Then return {@code CONTENT}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSource#getDataKind(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataKind JDBCDataSource.getDataKind(String, int)"})
  public void testGetDataKind_when2004_thenReturnContent() {
    // Arrange, Act and Assert
    assertEquals(DBPDataKind.CONTENT, JDBCDataSource.getDataKind("Type Name", 2004));
  }

  /**
   * Test {@link JDBCDataSource#getDataKind(String, int)}.
   *
   * <ul>
   *   <li>When {@code 2006}.
   *   <li>Then return {@code REFERENCE}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSource#getDataKind(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataKind JDBCDataSource.getDataKind(String, int)"})
  public void testGetDataKind_when2006_thenReturnReference() {
    // Arrange, Act and Assert
    assertEquals(DBPDataKind.REFERENCE, JDBCDataSource.getDataKind("Type Name", 2006));
  }

  /**
   * Test {@link JDBCDataSource#getDataKind(String, int)}.
   *
   * <ul>
   *   <li>When {@code 2009}.
   *   <li>Then return {@code CONTENT}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSource#getDataKind(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataKind JDBCDataSource.getDataKind(String, int)"})
  public void testGetDataKind_when2009_thenReturnContent() {
    // Arrange, Act and Assert
    assertEquals(DBPDataKind.CONTENT, JDBCDataSource.getDataKind("Type Name", 2009));
  }

  /**
   * Test {@link JDBCDataSource#getDataKind(String, int)}.
   *
   * <ul>
   *   <li>When {@code BLOB}.
   *   <li>Then return {@code CONTENT}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSource#getDataKind(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataKind JDBCDataSource.getDataKind(String, int)"})
  public void testGetDataKind_whenBlob_thenReturnContent() {
    // Arrange, Act and Assert
    assertEquals(DBPDataKind.CONTENT, JDBCDataSource.getDataKind("BLOB", 1111));
  }

  /**
   * Test {@link JDBCDataSource#getDataKind(String, int)}.
   *
   * <ul>
   *   <li>When {@code BLOB}.
   *   <li>Then return {@code UNKNOWN}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSource#getDataKind(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataKind JDBCDataSource.getDataKind(String, int)"})
  public void testGetDataKind_whenBlob_thenReturnUnknown() {
    // Arrange, Act and Assert
    assertEquals(DBPDataKind.UNKNOWN, JDBCDataSource.getDataKind("BLOB", 42));
  }

  /**
   * Test {@link JDBCDataSource#getDataKind(String, int)}.
   *
   * <ul>
   *   <li>When {@code bool}.
   *   <li>Then return {@code BOOLEAN}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSource#getDataKind(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataKind JDBCDataSource.getDataKind(String, int)"})
  public void testGetDataKind_whenBool_thenReturnBoolean() {
    // Arrange, Act and Assert
    assertEquals(DBPDataKind.BOOLEAN, JDBCDataSource.getDataKind("bool", -7));
  }

  /**
   * Test {@link JDBCDataSource#getDataKind(String, int)}.
   *
   * <ul>
   *   <li>When {@code CLOB}.
   *   <li>Then return {@code CONTENT}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSource#getDataKind(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataKind JDBCDataSource.getDataKind(String, int)"})
  public void testGetDataKind_whenClob_thenReturnContent() {
    // Arrange, Act and Assert
    assertEquals(DBPDataKind.CONTENT, JDBCDataSource.getDataKind("CLOB", 1111));
  }

  /**
   * Test {@link JDBCDataSource#getDataKind(String, int)}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then return {@code UNKNOWN}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSource#getDataKind(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataKind JDBCDataSource.getDataKind(String, int)"})
  public void testGetDataKind_whenFortyTwo_thenReturnUnknown() {
    // Arrange, Act and Assert
    assertEquals(DBPDataKind.UNKNOWN, JDBCDataSource.getDataKind("Type Name", 42));
  }

  /**
   * Test {@link JDBCDataSource#getDataKind(String, int)}.
   *
   * <ul>
   *   <li>When minus eight.
   *   <li>Then return {@code ROWID}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSource#getDataKind(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataKind JDBCDataSource.getDataKind(String, int)"})
  public void testGetDataKind_whenMinusEight_thenReturnRowid() {
    // Arrange, Act and Assert
    assertEquals(DBPDataKind.ROWID, JDBCDataSource.getDataKind("Type Name", -8));
  }

  /**
   * Test {@link JDBCDataSource#getDataKind(String, int)}.
   *
   * <ul>
   *   <li>When minus five.
   *   <li>Then return {@code NUMERIC}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSource#getDataKind(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataKind JDBCDataSource.getDataKind(String, int)"})
  public void testGetDataKind_whenMinusFive_thenReturnNumeric() {
    // Arrange, Act and Assert
    assertEquals(DBPDataKind.NUMERIC, JDBCDataSource.getDataKind("Type Name", -5));
  }

  /**
   * Test {@link JDBCDataSource#getDataKind(String, int)}.
   *
   * <ul>
   *   <li>When minus four.
   *   <li>Then return {@code BINARY}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSource#getDataKind(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataKind JDBCDataSource.getDataKind(String, int)"})
  public void testGetDataKind_whenMinusFour_thenReturnBinary() {
    // Arrange, Act and Assert
    assertEquals(DBPDataKind.BINARY, JDBCDataSource.getDataKind("Type Name", -4));
  }

  /**
   * Test {@link JDBCDataSource#getDataKind(String, int)}.
   *
   * <ul>
   *   <li>When minus seven.
   *   <li>Then return {@code NUMERIC}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSource#getDataKind(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataKind JDBCDataSource.getDataKind(String, int)"})
  public void testGetDataKind_whenMinusSeven_thenReturnNumeric() {
    // Arrange, Act and Assert
    assertEquals(DBPDataKind.NUMERIC, JDBCDataSource.getDataKind("Type Name", -7));
  }

  /**
   * Test {@link JDBCDataSource#getDataKind(String, int)}.
   *
   * <ul>
   *   <li>When minus sixteen.
   *   <li>Then return {@code STRING}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSource#getDataKind(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataKind JDBCDataSource.getDataKind(String, int)"})
  public void testGetDataKind_whenMinusSixteen_thenReturnString() {
    // Arrange, Act and Assert
    assertEquals(DBPDataKind.STRING, JDBCDataSource.getDataKind("Type Name", -16));
  }

  /**
   * Test {@link JDBCDataSource#getDataKind(String, int)}.
   *
   * <ul>
   *   <li>When {@code NCLOB}.
   *   <li>Then return {@code CONTENT}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSource#getDataKind(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataKind JDBCDataSource.getDataKind(String, int)"})
  public void testGetDataKind_whenNclob_thenReturnContent() {
    // Arrange, Act and Assert
    assertEquals(DBPDataKind.CONTENT, JDBCDataSource.getDataKind("NCLOB", 1111));
  }

  /**
   * Test {@link JDBCDataSource#getDataKind(String, int)}.
   *
   * <ul>
   *   <li>When ninety-one.
   *   <li>Then return {@code DATETIME}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSource#getDataKind(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataKind JDBCDataSource.getDataKind(String, int)"})
  public void testGetDataKind_whenNinetyOne_thenReturnDatetime() {
    // Arrange, Act and Assert
    assertEquals(DBPDataKind.DATETIME, JDBCDataSource.getDataKind("Type Name", 91));
  }

  /**
   * Test {@link JDBCDataSource#getDataKind(String, int)}.
   *
   * <ul>
   *   <li>When {@link Short#SIZE}.
   *   <li>Then return {@code BOOLEAN}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSource#getDataKind(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataKind JDBCDataSource.getDataKind(String, int)"})
  public void testGetDataKind_whenSize_thenReturnBoolean() {
    // Arrange, Act and Assert
    assertEquals(DBPDataKind.BOOLEAN, JDBCDataSource.getDataKind("Type Name", Short.SIZE));
  }

  /**
   * Test {@link JDBCDataSource#getDataKind(String, int)}.
   *
   * <ul>
   *   <li>When {@code TINYINT}.
   *   <li>Then return {@code NUMERIC}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSource#getDataKind(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataKind JDBCDataSource.getDataKind(String, int)"})
  public void testGetDataKind_whenTinyint_thenReturnNumeric() {
    // Arrange, Act and Assert
    assertEquals(DBPDataKind.NUMERIC, JDBCDataSource.getDataKind("TINYINT", -7));
  }

  /**
   * Test {@link JDBCDataSource#getDataKind(String, int)}.
   *
   * <ul>
   *   <li>When twelve.
   *   <li>Then return {@code STRING}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSource#getDataKind(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataKind JDBCDataSource.getDataKind(String, int)"})
  public void testGetDataKind_whenTwelve_thenReturnString() {
    // Arrange, Act and Assert
    assertEquals(DBPDataKind.STRING, JDBCDataSource.getDataKind("Type Name", 12));
  }
}
