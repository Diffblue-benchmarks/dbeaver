package org.jkiss.dbeaver.ext.oracle.data;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.Connection;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleTimestampConverterDiffblueTest {
  /**
   * Test {@link OracleTimestampConverter#toTimestamp(Object, Connection)}.
   *
   * <p>Method under test: {@link OracleTimestampConverter#toTimestamp(Object, Connection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.sql.Timestamp OracleTimestampConverter.toTimestamp(Object, Connection)"})
  public void testToTimestamp() throws Exception {
    // Arrange, Act and Assert
    assertThrows(
        DBException.class,
        () -> OracleTimestampConverter.toTimestamp(DBPEvent.RENAME, mock(Connection.class)));
  }
}
