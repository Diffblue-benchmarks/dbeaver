package org.jkiss.dbeaver.ext.mssql.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.mssql.model.SQLServerLogin.LoginType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLServerLoginDiffblueTest {
  /**
   * Test LoginType {@link LoginType#getLoginType()}.
   *
   * <p>Method under test: {@link LoginType#getLoginType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String LoginType.getLoginType()"})
  public void testLoginTypeGetLoginType() {
    // Arrange, Act and Assert
    assertEquals("SQL Server login", LoginType.valueOf("S").getLoginType());
  }
}
