package org.jkiss.dbeaver.ext.oracle.data;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.SQLException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleXMLWrapperDiffblueTest {
  /**
   * Test {@link OracleXMLWrapper#free()}.
   *
   * <p>Method under test: {@link OracleXMLWrapper#free()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OracleXMLWrapper.free()"})
  public void testFree() throws SQLException {
    // Arrange, Act and Assert
    assertThrows(SQLException.class, () -> new OracleXMLWrapper(DBPEvent.RENAME).free());
  }

  /**
   * Test {@link OracleXMLWrapper#getBinaryStream()}.
   *
   * <p>Method under test: {@link OracleXMLWrapper#getBinaryStream()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.io.InputStream OracleXMLWrapper.getBinaryStream()"})
  public void testGetBinaryStream() throws SQLException {
    // Arrange, Act and Assert
    assertThrows(SQLException.class, () -> new OracleXMLWrapper(DBPEvent.RENAME).getBinaryStream());
  }

  /**
   * Test {@link OracleXMLWrapper#setBinaryStream()}.
   *
   * <p>Method under test: {@link OracleXMLWrapper#setBinaryStream()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.io.OutputStream OracleXMLWrapper.setBinaryStream()"})
  public void testSetBinaryStream() throws SQLException {
    // Arrange, Act and Assert
    assertThrows(SQLException.class, () -> new OracleXMLWrapper(DBPEvent.RENAME).setBinaryStream());
  }

  /**
   * Test {@link OracleXMLWrapper#getCharacterStream()}.
   *
   * <p>Method under test: {@link OracleXMLWrapper#getCharacterStream()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.io.Reader OracleXMLWrapper.getCharacterStream()"})
  public void testGetCharacterStream() throws SQLException {
    // Arrange, Act and Assert
    assertThrows(
        SQLException.class, () -> new OracleXMLWrapper(DBPEvent.RENAME).getCharacterStream());
  }

  /**
   * Test {@link OracleXMLWrapper#setCharacterStream()}.
   *
   * <p>Method under test: {@link OracleXMLWrapper#setCharacterStream()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.io.Writer OracleXMLWrapper.setCharacterStream()"})
  public void testSetCharacterStream() throws SQLException {
    // Arrange, Act and Assert
    assertThrows(
        SQLException.class, () -> new OracleXMLWrapper(DBPEvent.RENAME).setCharacterStream());
  }

  /**
   * Test {@link OracleXMLWrapper#getString()}.
   *
   * <p>Method under test: {@link OracleXMLWrapper#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OracleXMLWrapper.getString()"})
  public void testGetString() throws SQLException {
    // Arrange, Act and Assert
    assertThrows(SQLException.class, () -> new OracleXMLWrapper(DBPEvent.RENAME).getString());
  }

  /**
   * Test {@link OracleXMLWrapper#setString(String)}.
   *
   * <p>Method under test: {@link OracleXMLWrapper#setString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OracleXMLWrapper.setString(String)"})
  public void testSetString() throws SQLException {
    // Arrange, Act and Assert
    assertThrows(SQLException.class, () -> new OracleXMLWrapper(DBPEvent.RENAME).setString("42"));
  }

  /**
   * Test {@link OracleXMLWrapper#getSource(Class)}.
   *
   * <p>Method under test: {@link OracleXMLWrapper#getSource(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Source OracleXMLWrapper.getSource(Class)"})
  public void testGetSource() throws SQLException {
    // Arrange
    OracleXMLWrapper oracleXMLWrapper = new OracleXMLWrapper(DBPEvent.RENAME);
    Class<Source> sourceClass = Source.class;

    // Act and Assert
    assertNull(oracleXMLWrapper.getSource(sourceClass));
  }

  /**
   * Test {@link OracleXMLWrapper#setResult(Class)}.
   *
   * <p>Method under test: {@link OracleXMLWrapper#setResult(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Result OracleXMLWrapper.setResult(Class)"})
  public void testSetResult() throws SQLException {
    // Arrange
    OracleXMLWrapper oracleXMLWrapper = new OracleXMLWrapper(DBPEvent.RENAME);
    Class<Result> resultClass = Result.class;

    // Act and Assert
    assertThrows(SQLException.class, () -> oracleXMLWrapper.setResult(resultClass));
  }
}
