package org.jkiss.dbeaver.model.sql.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPScriptObject;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.struct.DBSEntity;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLGeneratorDiffblueTest {
  /**
   * Test {@link SQLGenerator#initGenerator(List)}.
   *
   * <ul>
   *   <li>Given {@link DBPScriptObject}.
   *   <li>When {@link ArrayList#ArrayList()} add {@link DBPScriptObject}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#initGenerator(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGenerator.initGenerator(List)"})
  public void testInitGenerator_givenDBPScriptObject_whenArrayListAddDBPScriptObject() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();

    ArrayList<DBPScriptObject> objects = new ArrayList<>();
    objects.add(mock(DBPScriptObject.class));

    // Act
    sqlGeneratorDDL.initGenerator(objects);

    // Assert
    assertSame(objects, sqlGeneratorDDL.getObjects());
  }

  /**
   * Test {@link SQLGenerator#initGenerator(List)}.
   *
   * <ul>
   *   <li>Given {@link DBPScriptObject}.
   *   <li>When {@link ArrayList#ArrayList()} add {@link DBPScriptObject}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#initGenerator(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGenerator.initGenerator(List)"})
  public void testInitGenerator_givenDBPScriptObject_whenArrayListAddDBPScriptObject2() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();

    ArrayList<DBPScriptObject> objects = new ArrayList<>();
    objects.add(mock(DBPScriptObject.class));
    objects.add(mock(DBPScriptObject.class));

    // Act
    sqlGeneratorDDL.initGenerator(objects);

    // Assert
    assertSame(objects, sqlGeneratorDDL.getObjects());
  }

  /**
   * Test {@link SQLGenerator#initGenerator(List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#initGenerator(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGenerator.initGenerator(List)"})
  public void testInitGenerator_whenArrayList() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();
    ArrayList<DBPScriptObject> objects = new ArrayList<>();

    // Act
    sqlGeneratorDDL.initGenerator(objects);

    // Assert
    assertSame(objects, sqlGeneratorDDL.getObjects());
  }

  /**
   * Test {@link SQLGenerator#getObjects()}.
   *
   * <p>Method under test: {@link SQLGenerator#getObjects()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLGenerator.getObjects()"})
  public void testGetObjects() {
    // Arrange, Act and Assert
    assertNull(new SQLGeneratorDDL().getObjects());
  }

  /**
   * Test {@link SQLGenerator#isFullyQualifiedNames()}.
   *
   * <ul>
   *   <li>Given {@link SQLGeneratorDDL} (default constructor).
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#isFullyQualifiedNames()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.isFullyQualifiedNames()"})
  public void testIsFullyQualifiedNames_givenSQLGeneratorDDL_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new SQLGeneratorDDL().isFullyQualifiedNames());
  }

  /**
   * Test {@link SQLGenerator#isFullyQualifiedNames()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#isFullyQualifiedNames()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.isFullyQualifiedNames()"})
  public void testIsFullyQualifiedNames_thenReturnFalse() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();
    sqlGeneratorDDL.setFullyQualifiedNames(false);

    // Act and Assert
    assertFalse(sqlGeneratorDDL.isFullyQualifiedNames());
  }

  /**
   * Test {@link SQLGenerator#isCompactSQL()}.
   *
   * <ul>
   *   <li>Given {@link SQLGeneratorDDL} (default constructor) CompactSQL is {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#isCompactSQL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.isCompactSQL()"})
  public void testIsCompactSQL_givenSQLGeneratorDDLCompactSQLIsTrue_thenReturnTrue() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();
    sqlGeneratorDDL.setCompactSQL(true);

    // Act and Assert
    assertTrue(sqlGeneratorDDL.isCompactSQL());
  }

  /**
   * Test {@link SQLGenerator#isCompactSQL()}.
   *
   * <ul>
   *   <li>Given {@link SQLGeneratorDDL} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#isCompactSQL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.isCompactSQL()"})
  public void testIsCompactSQL_givenSQLGeneratorDDL_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new SQLGeneratorDDL().isCompactSQL());
  }

  /**
   * Test {@link SQLGenerator#setCompactSQL(boolean)}.
   *
   * <p>Method under test: {@link SQLGenerator#setCompactSQL(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGenerator.setCompactSQL(boolean)"})
  public void testSetCompactSQL() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();

    // Act
    sqlGeneratorDDL.setCompactSQL(true);

    // Assert
    assertEquals(" ", sqlGeneratorDDL.getLineSeparator());
    assertTrue(sqlGeneratorDDL.isCompactSQL());
  }

  /**
   * Test {@link SQLGenerator#isShowComments()}.
   *
   * <ul>
   *   <li>Given {@link SQLGeneratorDDL} (default constructor) ShowComments is {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#isShowComments()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.isShowComments()"})
  public void testIsShowComments_givenSQLGeneratorDDLShowCommentsIsFalse_thenReturnFalse() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();
    sqlGeneratorDDL.setShowComments(false);

    // Act and Assert
    assertFalse(sqlGeneratorDDL.isShowComments());
  }

  /**
   * Test {@link SQLGenerator#isShowComments()}.
   *
   * <ul>
   *   <li>Given {@link SQLGeneratorDDL} (default constructor).
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#isShowComments()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.isShowComments()"})
  public void testIsShowComments_givenSQLGeneratorDDL_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new SQLGeneratorDDL().isShowComments());
  }

  /**
   * Test {@link SQLGenerator#isIncludePermissions()}.
   *
   * <ul>
   *   <li>Given {@link SQLGeneratorDDL} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#isIncludePermissions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.isIncludePermissions()"})
  public void testIsIncludePermissions_givenSQLGeneratorDDL_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new SQLGeneratorDDL().isIncludePermissions());
  }

  /**
   * Test {@link SQLGenerator#isIncludePermissions()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#isIncludePermissions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.isIncludePermissions()"})
  public void testIsIncludePermissions_thenReturnTrue() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();
    sqlGeneratorDDL.setShowPermissions(true);

    // Act and Assert
    assertTrue(sqlGeneratorDDL.isIncludePermissions());
  }

  /**
   * Test {@link SQLGenerator#setShowPermissions(boolean)}.
   *
   * <p>Method under test: {@link SQLGenerator#setShowPermissions(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGenerator.setShowPermissions(boolean)"})
  public void testSetShowPermissions() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();

    // Act
    sqlGeneratorDDL.setShowPermissions(true);

    // Assert
    assertTrue(sqlGeneratorDDL.isIncludePermissions());
  }

  /**
   * Test {@link SQLGenerator#isShowFullDdl()}.
   *
   * <ul>
   *   <li>Given {@link SQLGeneratorDDL} (default constructor) ShowFullDdl is {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#isShowFullDdl()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.isShowFullDdl()"})
  public void testIsShowFullDdl_givenSQLGeneratorDDLShowFullDdlIsTrue_thenReturnTrue() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();
    sqlGeneratorDDL.setShowFullDdl(true);

    // Act and Assert
    assertTrue(sqlGeneratorDDL.isShowFullDdl());
  }

  /**
   * Test {@link SQLGenerator#isShowFullDdl()}.
   *
   * <ul>
   *   <li>Given {@link SQLGeneratorDDL} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#isShowFullDdl()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.isShowFullDdl()"})
  public void testIsShowFullDdl_givenSQLGeneratorDDL_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new SQLGeneratorDDL().isShowFullDdl());
  }

  /**
   * Test {@link SQLGenerator#setShowFullDdl(boolean)}.
   *
   * <p>Method under test: {@link SQLGenerator#setShowFullDdl(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGenerator.setShowFullDdl(boolean)"})
  public void testSetShowFullDdl() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();

    // Act
    sqlGeneratorDDL.setShowFullDdl(true);

    // Assert
    assertTrue(sqlGeneratorDDL.isShowFullDdl());
  }

  /**
   * Test {@link SQLGenerator#isExcludeAutoGeneratedColumn()}.
   *
   * <p>Method under test: {@link SQLGenerator#isExcludeAutoGeneratedColumn()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.isExcludeAutoGeneratedColumn()"})
  public void testIsExcludeAutoGeneratedColumn() {
    // Arrange, Act and Assert
    assertFalse(new SQLGeneratorDDL().isExcludeAutoGeneratedColumn());
  }

  /**
   * Test {@link SQLGenerator#setExcludeAutoGeneratedColumn(boolean)}.
   *
   * <p>Method under test: {@link SQLGenerator#setExcludeAutoGeneratedColumn(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGenerator.setExcludeAutoGeneratedColumn(boolean)"})
  public void testSetExcludeAutoGeneratedColumn() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();

    // Act
    sqlGeneratorDDL.setExcludeAutoGeneratedColumn(true);

    // Assert
    assertTrue(sqlGeneratorDDL.isExcludeAutoGeneratedColumn());
  }

  /**
   * Test {@link SQLGenerator#isUseCustomDataFormat()}.
   *
   * <ul>
   *   <li>Given {@link SQLGeneratorDDL} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#isUseCustomDataFormat()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.isUseCustomDataFormat()"})
  public void testIsUseCustomDataFormat_givenSQLGeneratorDDL_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new SQLGeneratorDDL().isUseCustomDataFormat());
  }

  /**
   * Test {@link SQLGenerator#isUseCustomDataFormat()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#isUseCustomDataFormat()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.isUseCustomDataFormat()"})
  public void testIsUseCustomDataFormat_thenReturnTrue() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();
    sqlGeneratorDDL.setUseCustomDataFormat(true);

    // Act and Assert
    assertTrue(sqlGeneratorDDL.isUseCustomDataFormat());
  }

  /**
   * Test {@link SQLGenerator#setUseCustomDataFormat(boolean)}.
   *
   * <p>Method under test: {@link SQLGenerator#setUseCustomDataFormat(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGenerator.setUseCustomDataFormat(boolean)"})
  public void testSetUseCustomDataFormat() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();

    // Act
    sqlGeneratorDDL.setUseCustomDataFormat(true);

    // Assert
    assertTrue(sqlGeneratorDDL.isUseCustomDataFormat());
  }

  /**
   * Test {@link SQLGenerator#isUseSeparateForeignKeys()}.
   *
   * <ul>
   *   <li>Given {@link SQLGeneratorDDL} (default constructor).
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#isUseSeparateForeignKeys()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.isUseSeparateForeignKeys()"})
  public void testIsUseSeparateForeignKeys_givenSQLGeneratorDDL_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new SQLGeneratorDDL().isUseSeparateForeignKeys());
  }

  /**
   * Test {@link SQLGenerator#isUseSeparateForeignKeys()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#isUseSeparateForeignKeys()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.isUseSeparateForeignKeys()"})
  public void testIsUseSeparateForeignKeys_thenReturnFalse() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();
    sqlGeneratorDDL.setUseSeparateForeignKeys(false);

    // Act and Assert
    assertFalse(sqlGeneratorDDL.isUseSeparateForeignKeys());
  }

  /**
   * Test {@link SQLGenerator#isShowPartitionsDDL()}.
   *
   * <ul>
   *   <li>Given {@link SQLGeneratorDDL} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#isShowPartitionsDDL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.isShowPartitionsDDL()"})
  public void testIsShowPartitionsDDL_givenSQLGeneratorDDL_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new SQLGeneratorDDL().isShowPartitionsDDL());
  }

  /**
   * Test {@link SQLGenerator#isShowPartitionsDDL()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#isShowPartitionsDDL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.isShowPartitionsDDL()"})
  public void testIsShowPartitionsDDL_thenReturnTrue() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();
    sqlGeneratorDDL.setShowPartitionsDDL(true);

    // Act and Assert
    assertTrue(sqlGeneratorDDL.isShowPartitionsDDL());
  }

  /**
   * Test {@link SQLGenerator#setShowPartitionsDDL(boolean)}.
   *
   * <p>Method under test: {@link SQLGenerator#setShowPartitionsDDL(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGenerator.setShowPartitionsDDL(boolean)"})
  public void testSetShowPartitionsDDL() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();

    // Act
    sqlGeneratorDDL.setShowPartitionsDDL(true);

    // Assert
    assertTrue(sqlGeneratorDDL.isShowPartitionsDDL());
  }

  /**
   * Test {@link SQLGenerator#isShowCastParams()}.
   *
   * <ul>
   *   <li>Given {@link SQLGeneratorDDL} (default constructor) ShowCastParams is {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#isShowCastParams()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.isShowCastParams()"})
  public void testIsShowCastParams_givenSQLGeneratorDDLShowCastParamsIsTrue_thenReturnTrue() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();
    sqlGeneratorDDL.setShowCastParams(true);

    // Act and Assert
    assertTrue(sqlGeneratorDDL.isShowCastParams());
  }

  /**
   * Test {@link SQLGenerator#isShowCastParams()}.
   *
   * <ul>
   *   <li>Given {@link SQLGeneratorDDL} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#isShowCastParams()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.isShowCastParams()"})
  public void testIsShowCastParams_givenSQLGeneratorDDL_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new SQLGeneratorDDL().isShowCastParams());
  }

  /**
   * Test {@link SQLGenerator#setShowCastParams(boolean)}.
   *
   * <p>Method under test: {@link SQLGenerator#setShowCastParams(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGenerator.setShowCastParams(boolean)"})
  public void testSetShowCastParams() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();

    // Act
    sqlGeneratorDDL.setShowCastParams(true);

    // Assert
    assertTrue(sqlGeneratorDDL.isShowCastParams());
  }

  /**
   * Test {@link SQLGenerator#isDDLOption()}.
   *
   * <p>Method under test: {@link SQLGenerator#isDDLOption()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.isDDLOption()"})
  public void testIsDDLOption() {
    // Arrange, Act and Assert
    assertFalse(new SQLGeneratorDelete().isDDLOption());
  }

  /**
   * Test {@link SQLGenerator#isDMLOption()}.
   *
   * <p>Method under test: {@link SQLGenerator#isDMLOption()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.isDMLOption()"})
  public void testIsDMLOption() {
    // Arrange, Act and Assert
    assertFalse(new SQLGeneratorDDL().isDMLOption());
  }

  /**
   * Test {@link SQLGenerator#isInsertOption()}.
   *
   * <p>Method under test: {@link SQLGenerator#isInsertOption()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.isInsertOption()"})
  public void testIsInsertOption() {
    // Arrange, Act and Assert
    assertFalse(new SQLGeneratorDDL().isInsertOption());
  }

  /**
   * Test {@link SQLGenerator#supportCastParams()}.
   *
   * <p>Method under test: {@link SQLGenerator#supportCastParams()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.supportCastParams()"})
  public void testSupportCastParams() {
    // Arrange, Act and Assert
    assertFalse(new SQLGeneratorDDL().supportCastParams());
  }

  /**
   * Test {@link SQLGenerator#getGeneratorOption(String)}.
   *
   * <p>Method under test: {@link SQLGenerator#getGeneratorOption(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object SQLGenerator.getGeneratorOption(String)"})
  public void testGetGeneratorOption() {
    // Arrange, Act and Assert
    assertNull(new SQLGeneratorDDL().getGeneratorOption("Name"));
  }

  /**
   * Test {@link SQLGenerator#hasOptions()}.
   *
   * <p>Method under test: {@link SQLGenerator#hasOptions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLGenerator.hasOptions()"})
  public void testHasOptions() {
    // Arrange, Act and Assert
    assertTrue(new SQLGeneratorDDL().hasOptions());
  }

  /**
   * Test {@link SQLGenerator#getLineSeparator()}.
   *
   * <ul>
   *   <li>Given {@link SQLGeneratorDDL} (default constructor) CompactSQL is {@code true}.
   *   <li>Then return space.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#getLineSeparator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLGenerator.getLineSeparator()"})
  public void testGetLineSeparator_givenSQLGeneratorDDLCompactSQLIsTrue_thenReturnSpace() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();
    sqlGeneratorDDL.setCompactSQL(true);

    // Act and Assert
    assertEquals(" ", sqlGeneratorDDL.getLineSeparator());
  }

  /**
   * Test {@link SQLGenerator#getLineSeparator()}.
   *
   * <ul>
   *   <li>Given {@link SQLGeneratorDDL} (default constructor).
   *   <li>Then return lf.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#getLineSeparator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLGenerator.getLineSeparator()"})
  public void testGetLineSeparator_givenSQLGeneratorDDL_thenReturnLf() {
    // Arrange, Act and Assert
    assertEquals("\n", new SQLGeneratorDDL().getLineSeparator());
  }

  /**
   * Test {@link SQLGenerator#getEntityName(DBSEntity)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#getEntityName(DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLGenerator.getEntityName(DBSEntity)"})
  public void testGetEntityName_given42_thenCallsGetDataSource() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    String actualEntityName = sqlGeneratorDDL.getEntityName(entity);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(sqlDialect).getQuotedIdentifier("Name", true, false);
    assertEquals("42", actualEntityName);
  }

  /**
   * Test {@link SQLGenerator#getEntityName(DBSEntity)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getStructSeparator()} return {@code A}.
   *   <li>Then return {@code 42A42}.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#getEntityName(DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLGenerator.getEntityName(DBSEntity)"})
  public void testGetEntityName_givenSQLDialectGetStructSeparatorReturnA_thenReturn42a42() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStructSeparator()).thenReturn('A');
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    String actualEntityName = sqlGeneratorDDL.getEntityName(entity);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect, atLeast(1)).getQuotedIdentifier("Name", true, false);
    verify(sqlDialect).getStructSeparator();
    verify(parent).getDataSource();
    assertEquals("42A42", actualEntityName);
  }

  /**
   * Test {@link SQLGenerator#getEntityName(DBSEntity)}.
   *
   * <ul>
   *   <li>When {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and name is empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLGenerator#getEntityName(DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLGenerator.getEntityName(DBSEntity)"})
  public void testGetEntityName_whenDBVContainerWithParentIsDBVContainerAndNameIsEmptyString() {
    // Arrange
    SQLGeneratorDDL sqlGeneratorDDL = new SQLGeneratorDDL();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    String actualEntityName = sqlGeneratorDDL.getEntityName(entity);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect).getQuotedIdentifier("Name", true, false);
    verify(parent).getDataSource();
    assertEquals("42", actualEntityName);
  }

  /**
   * Test {@link SQLGenerator#addOptions(Map)}.
   *
   * <p>Method under test: {@link SQLGenerator#addOptions(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLGenerator.addOptions(Map)"})
  public void testAddOptions() {
    // Arrange
    SQLGeneratorDelete sqlGeneratorDelete = new SQLGeneratorDelete();
    HashMap<String, Object> options = new HashMap<>();

    // Act
    sqlGeneratorDelete.addOptions(options);

    // Assert
    assertEquals(11, options.size());
    assertFalse((Boolean) options.get("ddl.includeNestedObjects"));
    assertFalse((Boolean) options.get("ddl.includePartitions"));
    assertFalse((Boolean) options.get("ddl.includePermissions"));
    assertFalse((Boolean) options.get("script.exclude.autogenerated.column"));
    assertFalse((Boolean) options.get("script.format.compact"));
    assertFalse((Boolean) options.get("script.use.custom.data.format"));
    assertFalse((Boolean) options.get("sql.castParameter"));
    assertTrue((Boolean) options.get("ddl.includeComments"));
    assertTrue((Boolean) options.get("ddl.separateForeignKeys"));
    assertTrue((Boolean) options.get("ddl.source"));
    assertTrue((Boolean) options.get("useFQN"));
  }
}
