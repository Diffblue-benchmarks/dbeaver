package org.jkiss.dbeaver.model.data.hints.standard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.EnumSet;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.data.DBDAttributeBinding;
import org.jkiss.dbeaver.model.data.DBDAttributeBindingCustom;
import org.jkiss.dbeaver.model.data.DBDResultSetModel;
import org.jkiss.dbeaver.model.data.DBDRowIdentifier;
import org.jkiss.dbeaver.model.data.hints.DBDValueHint;
import org.jkiss.dbeaver.model.data.hints.DBDValueHint.HintStyle;
import org.jkiss.dbeaver.model.data.hints.DBDValueHint.HintType;
import org.jkiss.dbeaver.model.data.hints.ValueHintText;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSEntityReferrer;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class AttributeKeysHintProviderDiffblueTest {
  /**
   * Test {@link AttributeKeysHintProvider#getAttributeHints(DBDResultSetModel, DBDAttributeBinding,
   * EnumSet, int)}.
   *
   * <p>Method under test: {@link AttributeKeysHintProvider#getAttributeHints(DBDResultSetModel,
   * DBDAttributeBinding, EnumSet, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDValueHint[] AttributeKeysHintProvider.getAttributeHints(DBDResultSetModel, DBDAttributeBinding, EnumSet, int)"
  })
  public void testGetAttributeHints() {
    // Arrange
    AttributeKeysHintProvider attributeKeysHintProvider = new AttributeKeysHintProvider();

    DBDResultSetModel model = mock(DBDResultSetModel.class);
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBSDocumentConstraint entityIdentifier =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DBDRowIdentifier dbdRowIdentifier = new DBDRowIdentifier(entity, entityIdentifier);
    when(model.getDefaultRowIdentifier()).thenReturn(dbdRowIdentifier);

    DBDRowIdentifier dbdRowIdentifier2 = mock(DBDRowIdentifier.class);
    when(dbdRowIdentifier2.hasAttribute(Mockito.<DBDAttributeBinding>any())).thenReturn(false);
    when(dbdRowIdentifier2.getAttributes()).thenReturn(new ArrayList<>());
    when(dbdRowIdentifier2.isIncomplete()).thenReturn(false);
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    when(dbdRowIdentifier2.getEntity()).thenReturn(dbvEntity);

    DBDAttributeBindingCustom attribute = mock(DBDAttributeBindingCustom.class);
    when(attribute.getReferrers()).thenReturn(new ArrayList<>());
    when(attribute.getRowIdentifier()).thenReturn(dbdRowIdentifier2);

    // Act
    DBDValueHint[] actualAttributeHints =
        attributeKeysHintProvider.getAttributeHints(model, attribute, null, 1);

    // Assert
    verify(attribute).getReferrers();
    verify(attribute).getRowIdentifier();
    verify(model).getDefaultRowIdentifier();
    verify(dbdRowIdentifier2).getAttributes();
    verify(dbdRowIdentifier2).getEntity();
    verify(dbdRowIdentifier2).hasAttribute(isA(DBDAttributeBinding.class));
    verify(dbdRowIdentifier2).isIncomplete();
    DBDValueHint dbdValueHint = actualAttributeHints[0];
    assertTrue(dbdValueHint instanceof ValueHintText);
    assertEquals(
        "Unique key which will be used to edit this column's value",
        dbdValueHint.getHintDescription());
    assertEquals("Unique key: Name()", dbdValueHint.getHintText());
    assertNull(dbdValueHint.getHintIcon());
    assertEquals(1, actualAttributeHints.length);
  }

  /**
   * Test {@link AttributeKeysHintProvider#getAttributeHints(DBDResultSetModel, DBDAttributeBinding,
   * EnumSet, int)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AttributeKeysHintProvider#getAttributeHints(DBDResultSetModel,
   * DBDAttributeBinding, EnumSet, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDValueHint[] AttributeKeysHintProvider.getAttributeHints(DBDResultSetModel, DBDAttributeBinding, EnumSet, int)"
  })
  public void testGetAttributeHints_givenNull() {
    // Arrange
    AttributeKeysHintProvider attributeKeysHintProvider = new AttributeKeysHintProvider();
    DBDResultSetModel model = mock(DBDResultSetModel.class);

    DBDAttributeBindingCustom attribute = mock(DBDAttributeBindingCustom.class);
    when(attribute.getReferrers()).thenReturn(new ArrayList<>());
    when(attribute.getRowIdentifier()).thenReturn(null);

    // Act
    DBDValueHint[] actualAttributeHints =
        attributeKeysHintProvider.getAttributeHints(model, attribute, null, 1);

    // Assert
    verify(attribute).getReferrers();
    verify(attribute).getRowIdentifier();
    assertEquals(0, actualAttributeHints.length);
  }

  /**
   * Test {@link AttributeKeysHintProvider#getAttributeHints(DBDResultSetModel, DBDAttributeBinding,
   * EnumSet, int)}.
   *
   * <ul>
   *   <li>Then first element HintIcon return {@link DBIcon}.
   * </ul>
   *
   * <p>Method under test: {@link AttributeKeysHintProvider#getAttributeHints(DBDResultSetModel,
   * DBDAttributeBinding, EnumSet, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDValueHint[] AttributeKeysHintProvider.getAttributeHints(DBDResultSetModel, DBDAttributeBinding, EnumSet, int)"
  })
  public void testGetAttributeHints_thenFirstElementHintIconReturnDBIcon() {
    // Arrange
    AttributeKeysHintProvider attributeKeysHintProvider = new AttributeKeysHintProvider();
    DBDResultSetModel model = mock(DBDResultSetModel.class);

    ArrayList<DBSEntityReferrer> dbsEntityReferrerList = new ArrayList<>();
    dbsEntityReferrerList.add(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    DBDAttributeBindingCustom attribute = mock(DBDAttributeBindingCustom.class);
    when(attribute.getReferrers()).thenReturn(dbsEntityReferrerList);
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBSDocumentConstraint entityIdentifier =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DBDRowIdentifier dbdRowIdentifier = new DBDRowIdentifier(entity, entityIdentifier);
    when(attribute.getRowIdentifier()).thenReturn(dbdRowIdentifier);

    // Act
    DBDValueHint[] actualAttributeHints =
        attributeKeysHintProvider.getAttributeHints(model, attribute, null, 1);

    // Assert
    verify(attribute, atLeast(1)).getReferrers();
    verify(attribute).getRowIdentifier();
    DBDValueHint dbdValueHint = actualAttributeHints[0];
    DBPImage hintIcon = dbdValueHint.getHintIcon();
    assertTrue(hintIcon instanceof DBIcon);
    assertTrue(dbdValueHint instanceof ValueHintText);
    assertEquals("Refers to: ", dbdValueHint.getHintText());
    assertEquals("over/reference_ovr.png", hintIcon.getLocation());
    assertEquals("over_reference", ((DBIcon) hintIcon).getToken());
    assertNull(dbdValueHint.getHintDescription());
    assertEquals(1, actualAttributeHints.length);
  }

  /**
   * Test {@link AttributeKeysHintProvider#getAttributeHints(DBDResultSetModel, DBDAttributeBinding,
   * EnumSet, int)}.
   *
   * <ul>
   *   <li>Then first element HintIcon return {@link DBIcon}.
   * </ul>
   *
   * <p>Method under test: {@link AttributeKeysHintProvider#getAttributeHints(DBDResultSetModel,
   * DBDAttributeBinding, EnumSet, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDValueHint[] AttributeKeysHintProvider.getAttributeHints(DBDResultSetModel, DBDAttributeBinding, EnumSet, int)"
  })
  public void testGetAttributeHints_thenFirstElementHintIconReturnDBIcon2() {
    // Arrange
    AttributeKeysHintProvider attributeKeysHintProvider = new AttributeKeysHintProvider();
    DBDResultSetModel model = mock(DBDResultSetModel.class);

    ArrayList<DBSEntityReferrer> dbsEntityReferrerList = new ArrayList<>();
    dbsEntityReferrerList.add(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));
    dbsEntityReferrerList.add(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    DBDAttributeBindingCustom attribute = mock(DBDAttributeBindingCustom.class);
    when(attribute.getReferrers()).thenReturn(dbsEntityReferrerList);
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBSDocumentConstraint entityIdentifier =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DBDRowIdentifier dbdRowIdentifier = new DBDRowIdentifier(entity, entityIdentifier);
    when(attribute.getRowIdentifier()).thenReturn(dbdRowIdentifier);

    // Act
    DBDValueHint[] actualAttributeHints =
        attributeKeysHintProvider.getAttributeHints(model, attribute, null, 1);

    // Assert
    verify(attribute, atLeast(1)).getReferrers();
    verify(attribute).getRowIdentifier();
    DBDValueHint dbdValueHint = actualAttributeHints[0];
    DBPImage hintIcon = dbdValueHint.getHintIcon();
    assertTrue(hintIcon instanceof DBIcon);
    assertTrue(dbdValueHint instanceof ValueHintText);
    assertEquals("Refers to: ", dbdValueHint.getHintText());
    assertEquals("over/reference_ovr.png", hintIcon.getLocation());
    assertEquals("over_reference", ((DBIcon) hintIcon).getToken());
    assertNull(dbdValueHint.getHintDescription());
    assertEquals(1, actualAttributeHints.length);
  }

  /**
   * Test {@link AttributeKeysHintProvider#getAttributeHints(DBDResultSetModel, DBDAttributeBinding,
   * EnumSet, int)}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link AttributeKeysHintProvider#getAttributeHints(DBDResultSetModel,
   * DBDAttributeBinding, EnumSet, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDValueHint[] AttributeKeysHintProvider.getAttributeHints(DBDResultSetModel, DBDAttributeBinding, EnumSet, int)"
  })
  public void testGetAttributeHints_thenReturnArrayLengthIsZero() {
    // Arrange
    AttributeKeysHintProvider attributeKeysHintProvider = new AttributeKeysHintProvider();
    DBDResultSetModel model = mock(DBDResultSetModel.class);

    DBDAttributeBindingCustom attribute = mock(DBDAttributeBindingCustom.class);
    when(attribute.getReferrers()).thenReturn(new ArrayList<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBSDocumentConstraint entityIdentifier =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DBDRowIdentifier dbdRowIdentifier = new DBDRowIdentifier(entity, entityIdentifier);
    when(attribute.getRowIdentifier()).thenReturn(dbdRowIdentifier);

    // Act
    DBDValueHint[] actualAttributeHints =
        attributeKeysHintProvider.getAttributeHints(model, attribute, null, 1);

    // Assert
    verify(attribute).getReferrers();
    verify(attribute).getRowIdentifier();
    assertEquals(0, actualAttributeHints.length);
  }

  /**
   * Test {@link AttributeKeysHintProvider#getAttributeHints(DBDResultSetModel, DBDAttributeBinding,
   * EnumSet, int)}.
   *
   * <ul>
   *   <li>Then return second element HintText is {@code Part of key: 42}.
   * </ul>
   *
   * <p>Method under test: {@link AttributeKeysHintProvider#getAttributeHints(DBDResultSetModel,
   * DBDAttributeBinding, EnumSet, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDValueHint[] AttributeKeysHintProvider.getAttributeHints(DBDResultSetModel, DBDAttributeBinding, EnumSet, int)"
  })
  public void testGetAttributeHints_thenReturnSecondElementHintTextIsPartOfKey42() {
    // Arrange
    AttributeKeysHintProvider attributeKeysHintProvider = new AttributeKeysHintProvider();

    DBDResultSetModel model = mock(DBDResultSetModel.class);
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBSDocumentConstraint entityIdentifier =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DBDRowIdentifier dbdRowIdentifier = new DBDRowIdentifier(entity, entityIdentifier);
    when(model.getDefaultRowIdentifier()).thenReturn(dbdRowIdentifier);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBSDocumentContainer entity2 = mock(DBSDocumentContainer.class);
    when(entity2.getDataSource()).thenReturn(dbpDataSource);

    DBDRowIdentifier dbdRowIdentifier2 = mock(DBDRowIdentifier.class);
    when(dbdRowIdentifier2.getUniqueKey()).thenReturn(new DBSDocumentConstraint(entity2));
    when(dbdRowIdentifier2.hasAttribute(Mockito.<DBDAttributeBinding>any())).thenReturn(true);
    when(dbdRowIdentifier2.getAttributes()).thenReturn(new ArrayList<>());
    when(dbdRowIdentifier2.isIncomplete()).thenReturn(false);
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    when(dbdRowIdentifier2.getEntity()).thenReturn(dbvEntity);

    DBDAttributeBindingCustom attribute = mock(DBDAttributeBindingCustom.class);
    when(attribute.getReferrers()).thenReturn(new ArrayList<>());
    when(attribute.getRowIdentifier()).thenReturn(dbdRowIdentifier2);

    // Act
    DBDValueHint[] actualAttributeHints =
        attributeKeysHintProvider.getAttributeHints(model, attribute, null, 1);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(attribute).getReferrers();
    verify(attribute).getRowIdentifier();
    verify(model).getDefaultRowIdentifier();
    verify(dbdRowIdentifier2).getAttributes();
    verify(dbdRowIdentifier2).getEntity();
    verify(dbdRowIdentifier2).getUniqueKey();
    verify(dbdRowIdentifier2).hasAttribute(isA(DBDAttributeBinding.class));
    verify(dbdRowIdentifier2).isIncomplete();
    verify(sqlDialect).getQuotedIdentifier("DocumentKey", true, false);
    verify(entity2, atLeast(1)).getDataSource();
    DBDValueHint dbdValueHint = actualAttributeHints[1];
    DBPImage hintIcon = dbdValueHint.getHintIcon();
    assertTrue(hintIcon instanceof DBIcon);
    assertTrue(dbdValueHint instanceof ValueHintText);
    assertEquals("Part of key: 42", dbdValueHint.getHintText());
    assertEquals("over/key_ovr.png", hintIcon.getLocation());
    assertEquals("over_key", ((DBIcon) hintIcon).getToken());
    assertNull(dbdValueHint.getHintDescription());
    assertEquals(0, dbdValueHint.getHintOptions());
    assertEquals(2, actualAttributeHints.length);
    assertEquals(HintStyle.NORMAL, dbdValueHint.getHintStyle());
    assertEquals(HintType.STRING, dbdValueHint.getHintType());
  }

  /**
   * Test {@link AttributeKeysHintProvider#getAttributeHints(DBDResultSetModel, DBDAttributeBinding,
   * EnumSet, int)}.
   *
   * <ul>
   *   <li>Then return second element HintText is {@code Part of key: "DocumentKey"}.
   * </ul>
   *
   * <p>Method under test: {@link AttributeKeysHintProvider#getAttributeHints(DBDResultSetModel,
   * DBDAttributeBinding, EnumSet, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDValueHint[] AttributeKeysHintProvider.getAttributeHints(DBDResultSetModel, DBDAttributeBinding, EnumSet, int)"
  })
  public void testGetAttributeHints_thenReturnSecondElementHintTextIsPartOfKeyDocumentKey() {
    // Arrange
    AttributeKeysHintProvider attributeKeysHintProvider = new AttributeKeysHintProvider();

    DBDResultSetModel model = mock(DBDResultSetModel.class);
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBSDocumentConstraint entityIdentifier =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DBDRowIdentifier dbdRowIdentifier = new DBDRowIdentifier(entity, entityIdentifier);
    when(model.getDefaultRowIdentifier()).thenReturn(dbdRowIdentifier);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity2 = mock(DBSDocumentContainer.class);
    when(entity2.getDataSource()).thenReturn(dbpDataSource);

    DBDRowIdentifier dbdRowIdentifier2 = mock(DBDRowIdentifier.class);
    when(dbdRowIdentifier2.getUniqueKey()).thenReturn(new DBSDocumentConstraint(entity2));
    when(dbdRowIdentifier2.hasAttribute(Mockito.<DBDAttributeBinding>any())).thenReturn(true);
    when(dbdRowIdentifier2.getAttributes()).thenReturn(new ArrayList<>());
    when(dbdRowIdentifier2.isIncomplete()).thenReturn(false);
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    when(dbdRowIdentifier2.getEntity()).thenReturn(dbvEntity);

    DBDAttributeBindingCustom attribute = mock(DBDAttributeBindingCustom.class);
    when(attribute.getReferrers()).thenReturn(new ArrayList<>());
    when(attribute.getRowIdentifier()).thenReturn(dbdRowIdentifier2);

    // Act
    DBDValueHint[] actualAttributeHints =
        attributeKeysHintProvider.getAttributeHints(model, attribute, null, 1);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(attribute).getReferrers();
    verify(attribute).getRowIdentifier();
    verify(model).getDefaultRowIdentifier();
    verify(dbdRowIdentifier2).getAttributes();
    verify(dbdRowIdentifier2).getEntity();
    verify(dbdRowIdentifier2).getUniqueKey();
    verify(dbdRowIdentifier2).hasAttribute(isA(DBDAttributeBinding.class));
    verify(dbdRowIdentifier2).isIncomplete();
    verify(entity2, atLeast(1)).getDataSource();
    DBDValueHint dbdValueHint = actualAttributeHints[1];
    DBPImage hintIcon = dbdValueHint.getHintIcon();
    assertTrue(hintIcon instanceof DBIcon);
    assertTrue(dbdValueHint instanceof ValueHintText);
    assertEquals("Part of key: \"DocumentKey\"", dbdValueHint.getHintText());
    assertEquals("over/key_ovr.png", hintIcon.getLocation());
    assertEquals("over_key", ((DBIcon) hintIcon).getToken());
    assertNull(dbdValueHint.getHintDescription());
    assertEquals(0, dbdValueHint.getHintOptions());
    assertEquals(2, actualAttributeHints.length);
    assertEquals(HintStyle.NORMAL, dbdValueHint.getHintStyle());
    assertEquals(HintType.STRING, dbdValueHint.getHintType());
  }

  /**
   * Test {@link AttributeKeysHintProvider#getAttributeHints(DBDResultSetModel, DBDAttributeBinding,
   * EnumSet, int)}.
   *
   * <ul>
   *   <li>Then return second element HintText is {@code Part of key: "Name"}.
   * </ul>
   *
   * <p>Method under test: {@link AttributeKeysHintProvider#getAttributeHints(DBDResultSetModel,
   * DBDAttributeBinding, EnumSet, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDValueHint[] AttributeKeysHintProvider.getAttributeHints(DBDResultSetModel, DBDAttributeBinding, EnumSet, int)"
  })
  public void testGetAttributeHints_thenReturnSecondElementHintTextIsPartOfKeyName() {
    // Arrange
    AttributeKeysHintProvider attributeKeysHintProvider = new AttributeKeysHintProvider();

    DBDResultSetModel model = mock(DBDResultSetModel.class);
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBSDocumentConstraint entityIdentifier =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DBDRowIdentifier dbdRowIdentifier = new DBDRowIdentifier(entity, entityIdentifier);
    when(model.getDefaultRowIdentifier()).thenReturn(dbdRowIdentifier);

    DBVEntity dbvEntity = mock(DBVEntity.class);
    when(dbvEntity.getName()).thenReturn("Name");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentConstraint dbsDocumentConstraint = mock(DBSDocumentConstraint.class);
    when(dbsDocumentConstraint.getName()).thenReturn("Name");
    when(dbsDocumentConstraint.getDataSource()).thenReturn(dbpDataSource);

    DBDRowIdentifier dbdRowIdentifier2 = mock(DBDRowIdentifier.class);
    when(dbdRowIdentifier2.getUniqueKey()).thenReturn(dbsDocumentConstraint);
    when(dbdRowIdentifier2.hasAttribute(Mockito.<DBDAttributeBinding>any())).thenReturn(true);
    when(dbdRowIdentifier2.getAttributes()).thenReturn(new ArrayList<>());
    when(dbdRowIdentifier2.isIncomplete()).thenReturn(false);
    when(dbdRowIdentifier2.getEntity()).thenReturn(dbvEntity);

    DBDAttributeBindingCustom attribute = mock(DBDAttributeBindingCustom.class);
    when(attribute.getReferrers()).thenReturn(new ArrayList<>());
    when(attribute.getRowIdentifier()).thenReturn(dbdRowIdentifier2);

    // Act
    DBDValueHint[] actualAttributeHints =
        attributeKeysHintProvider.getAttributeHints(model, attribute, null, 1);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(attribute).getReferrers();
    verify(attribute).getRowIdentifier();
    verify(model).getDefaultRowIdentifier();
    verify(dbdRowIdentifier2).getAttributes();
    verify(dbdRowIdentifier2).getEntity();
    verify(dbdRowIdentifier2).getUniqueKey();
    verify(dbdRowIdentifier2).hasAttribute(isA(DBDAttributeBinding.class));
    verify(dbdRowIdentifier2).isIncomplete();
    verify(dbsDocumentConstraint, atLeast(1)).getDataSource();
    verify(dbsDocumentConstraint).getName();
    verify(dbvEntity).getName();
    DBDValueHint dbdValueHint = actualAttributeHints[1];
    DBPImage hintIcon = dbdValueHint.getHintIcon();
    assertTrue(hintIcon instanceof DBIcon);
    assertTrue(dbdValueHint instanceof ValueHintText);
    assertEquals("Part of key: \"Name\"", dbdValueHint.getHintText());
    assertEquals("over/key_ovr.png", hintIcon.getLocation());
    assertEquals("over_key", ((DBIcon) hintIcon).getToken());
    assertNull(dbdValueHint.getHintDescription());
    assertEquals(0, dbdValueHint.getHintOptions());
    assertEquals(2, actualAttributeHints.length);
    assertEquals(HintStyle.NORMAL, dbdValueHint.getHintStyle());
    assertEquals(HintType.STRING, dbdValueHint.getHintType());
  }
}
