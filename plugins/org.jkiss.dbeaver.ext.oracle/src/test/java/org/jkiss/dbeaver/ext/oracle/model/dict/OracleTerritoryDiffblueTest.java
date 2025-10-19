package org.jkiss.dbeaver.ext.oracle.model.dict;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleTerritoryDiffblueTest {
  /**
   * Test {@link OracleTerritory#getTerritory()}.
   *
   * <p>Method under test: {@link OracleTerritory#getTerritory()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String OracleTerritory.getTerritory()"})
  public void testGetTerritory() {
    // Arrange, Act and Assert
    assertEquals("ALGERIA", OracleTerritory.valueOf("ALGERIA").getTerritory());
  }
}
