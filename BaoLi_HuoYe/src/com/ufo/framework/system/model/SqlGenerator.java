package com.ufo.framework.system.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SQLQuery;

import com.model.hibernate.system._Module;
import com.ufo.framework.common.core.web.SortParameter;
import com.ufo.framework.system.repertory.SqlField;
import com.ufo.framework.system.repertory.SqlLeftJoin;

public class SqlGenerator
{
  HttpServletRequest request;
  private _Module aggregationParentModule;
//  private UserSession userSession;
  private List<SqlField> fieldList;
  private List<SqlLeftJoin> joinOn;
  private SortParameter[] sorts;
  private _Module module;
  //private ReportParam reportParam;
  private boolean isAggregation;
  private List<SqlField> joinField;

  protected String getWhere()
  {
    ArrayList localArrayList;
    (localArrayList = new ArrayList())
      .addAll(a.getUserDataFilterInfoSql());

    if (
      a.reportParam.getModuleConditions() != null)
    {
      tmpTernaryOp = (localIterator = a.reportParam.getModuleConditions().iterator());
      Iterator localIterator;
    }
  }

  public SqlLeftJoin getSQLManyToOneLeftJoinString moduleName,String childModuleName)
  {
    SqlLeftJoin localSqlLeftJoin = new SqlLeftJoin();
    SqlLeftJoin tmp12_10 = localSqlLeftJoin;
    SqlLeftJoin tmp13_12 = tmp12_10;
    SqlLeftJoin tmp18_16 = localSqlLeftJoin;
    _Module tmp22_21 = a; localSqlLeftJoin.setModuleName(tmp22_21.getTf_tableName()); tmp18_16
      .setTableAsName(tmp22_21
      .getTableAsName());

    tmp18_16.setPrimaryKey(
      a.getTf_primaryKey());

    tmp13_12
      .setChildModuleName(a); tmp12_10
      .setChildTableAsName(a);

    return 
      tmp13_12;
  }

  public void addScalar(SQLQuery a)
  {
    Iterator localIterator;
    SqlField localSqlField;
    for (goto 37; localIterator.hasNext(); a.addScalar(localSqlField.getFieldScalar()))
    {
      localSqlField = (SqlField)localIterator.next();
    }

    for (tmpTernaryOp = (localIterator = a.joinField.iterator()); localIterator.hasNext(); ) { localSqlField = (SqlField)localIterator.next();

      a.addScalar(localSqlField.getFieldScalar());
    }
  }

  protected SqlField getSqlFieldWithAsName(String a)
  {
    Iterator localIterator;
    SqlField localSqlField;
    for (goto 40; localIterator.hasNext(); )
      if (
        (localSqlField = (SqlField)localIterator.next()).getFieldAsName().equals(a))
      {
        return localSqlField;
      }
    for (tmpTernaryOp = (localIterator = a.joinField.iterator()); localIterator.hasNext(); )
      if (
        (localSqlField = (SqlField)localIterator.next()).getFieldAsName().equals(a))
      {
        return localSqlField;
      }
    return null;
  }

  public void setJoinOn(List<SqlLeftJoin> a)
  {
    a.joinOn = a;
  }

  public void setReportParam(ReportParam a)
  {
    a.reportParam = a;
  }

  public String getGroupByIdAndCount(String a, Boolean a)
  {
    String str = ModuleConstants.ALLATORIxDEMO("?' '/6l") + a + Trade.ALLATORIxDEMO("\023_@\036ZZ\023\022\023]\\K]J\033\024\032\036RM\023]\\K]J");

    str = str + ModuleConstants.ALLATORIxDEMO("b*0#/l") + a.getFrom();

    str = str + a.getLeftJoin();

    str = str + a.getWhere();

    str = str + Trade.ALLATORIxDEMO("\023YAQFN\023\\J\036") + a;

    str = str + ModuleConstants.ALLATORIxDEMO("b#0('>b.;l") + a;

    if ((a != null) && (a.booleanValue()))
    {
      str = str + Trade.ALLATORIxDEMO("\023ZVMP\036");
    }
    return str;
  }

  protected List<String> getUserDataFilterInfoSql()
  {
    ArrayList localArrayList = new ArrayList();
    UserDataFilterInfo localUserDataFilterInfo;
    if ((localUserDataFilterInfo = a.userSession.getUserDataFilterInfoWithModuleName(a.module.getTf_moduleName())) != null)
    {
      localArrayList.add(localUserDataFilterInfo.getSqlWhere());
    }
    tmpTernaryOp = (localIterator = a.joinOn.iterator());
    Iterator localIterator;
    break label95;
  }

  public ReportParam getReportParam()
  {
    return a.reportParam;
  }

  protected String getSelectFields()
  {
    String str = ModuleConstants.ALLATORIxDEMO("l");
    Iterator localIterator;
    SqlField localSqlField;
    for (goto 64; localIterator.hasNext(); ) { localSqlField = (SqlField)localIterator.next();

      str = str + localSqlField.getFieldSql() + Trade.ALLATORIxDEMO("\022");
    }
    for (tmpTernaryOp = (localIterator = a.joinField.iterator()); localIterator.hasNext(); ) { localSqlField = (SqlField)localIterator.next();

      str = str + localSqlField.getFieldSql() + ModuleConstants.ALLATORIxDEMO("`");
    }
    String tmp139_138 = str; str = tmp139_138.substring(0, tmp139_138.length() - 1);

    return str + Trade.ALLATORIxDEMO("\036");
  }

  public SortParameter[] getSorts()
  {
    return a.sorts;
  }

  public void addAllLeftJoinInfo(_Module a)
  {
    Iterator localIterator;
    _Module local_Module;
    for (goto 39; localIterator.hasNext(); a.getAllSQLManyToOne(local_Module, a.getTf_moduleName(), a.getTableAsName()))
    {
      local_Module = (_Module)localIterator.next();
    }

    for (tmpTernaryOp = (localIterator = a.getParentOneToOnes().iterator()); localIterator.hasNext(); a.getAllSQLParentOneToOne(local_Module, a.getTf_moduleName(), a.getTableAsName()))
    {
      local_Module = (_Module)localIterator.next();
    }
    for (tmpTernaryOp = (localIterator = a.getChildOneToOnes().iterator()); localIterator.hasNext(); a.getAllSQLChildOneToOne(local_Module, a.getTf_moduleName(), a.getTableAsName()))
    {
      local_Module = (_Module)localIterator.next();
    }
  }

  public void getAllSQLChildOneToOne(_Module a, String a, String a)
  {
    for (goto 46; ((Iterator)localObject2).hasNext(); ) {
      if (
        (localObject1 = (SqlLeftJoin)((Iterator)localObject2).next()).getModuleName().equals(a.getTf_moduleName())) {
        return;
      }

    }

    if (a.getTf_moduleName().equals(a.module.getTf_moduleName()))
    {
      return;
    }

    Object localObject1 = a.getTableAsName() + "___" + a.module.getTf_primaryKey();
     tmp126_125 = null;

    Object localObject2 = new SqlField(a.getTf_moduleName(), a.getTableAsName(), a.module.getTf_primaryKey(), tmp126_125, tmp126_125, FieldType.String.getValue(), Boolean.valueOf(false));
    Object tmp148_146 = localObject2; tmp148_146.setFieldAsName((String)localObject1); tmp148_146
      .setFieldScalar((String)localObject1);

    a.getJoinField().add(localObject2);

    if ((a
      .getTf_nameFields() != null) && (!a.getTf_nameFields().equals(ModuleConstants.ALLATORIxDEMO("9,('*+\"'("))) && (!a.getTf_nameFields().equals(a.getTf_primaryKey())))
    {
      localObject1 = a.getTableAsName() + "___" + 
        a.getTf_nameFields();
       tmp252_251 = null;

      SqlField localSqlField = new SqlField(a.getTf_moduleName(), a.getTableAsName(), a.getTf_nameFields(), tmp252_251, tmp252_251, FieldType.String.getValue(), Boolean.valueOf(false));
      SqlField tmp273_271 = localSqlField; tmp273_271.setFieldAsName((String)localObject1); tmp273_271.setFieldScalar(
        (String)localObject1);

      ((SqlField)localObject2).setNameField(localSqlField);

      a
        .getJoinField().add(localSqlField);
    }
    a.getJoinOn().add(a.getSQLOneToOneChildLeftJoin(a, a, a));
  }

  public String getGroup_order_by()
  {
    if (a.reportParam.getGroups() == null)
    {
      return "";
    }
  }

  public void getAllSQLManyToOne(_Module a, String a, String a)
  {
    for (goto 46; ((Iterator)localObject2).hasNext(); )
      if (
        (localObject1 = (SqlLeftJoin)((Iterator)localObject2).next()).getModuleName().equals(a.getTf_moduleName()))
        return;
    if (a.getTf_moduleName().equals(a.module.getTf_moduleName()))
    {
      return;
    }

    Object localObject1 = a.getTableAsName() + "___" + a.getTf_primaryKey();
     tmp120_119 = null;

    Object localObject2 = new SqlField(a.getTf_moduleName(), a.getTableAsName(), a.getTf_primaryKey(), tmp120_119, tmp120_119, FieldType.String.getValue(), Boolean.valueOf(false));
    Object tmp142_140 = localObject2; tmp142_140.setFieldAsName((String)localObject1); tmp142_140
      .setFieldScalar((String)localObject1);

    a
      .getJoinField().add(localObject2);
    Object localObject3;
    if (!
      a.getTf_nameFields().equals(a.getTf_primaryKey()))
    {
      localObject1 = a.getTableAsName() + "___" + 
        a.getTf_nameFields();
       tmp223_222 = null;

      localObject3 = new SqlField(a.getTf_moduleName(), a.getTableAsName(), a.getTf_nameFields(), tmp223_222, tmp223_222, FieldType.String.getValue(), Boolean.valueOf(false));
      Object tmp244_242 = localObject3; tmp244_242.setFieldAsName((String)localObject1); tmp244_242.setFieldScalar(
        (String)localObject1);

      ((SqlField)localObject2).setNameField((SqlField)localObject3);

      a.getJoinField().add(localObject3);
    }

    a.getJoinOn().add(a.getSQLManyToOneLeftJoin(a, a, a));

    if ((
      a.isAggregation) && (a.aggregationParentModule == a))
      return;
    if (a.getParents() != null)
    {
      for (tmpTernaryOp = (a = a.getParents().iterator()); a.hasNext(); a.getAllSQLManyToOne((_Module)localObject3, a.getTf_moduleName(), a.getTableAsName()))
      {
        localObject3 = (_Module)a.next();
      }
    }
    if (a.getParentOneToOnes() != null)
    {
      for (tmpTernaryOp = (a = a.getParentOneToOnes().iterator()); a.hasNext(); a.getAllSQLParentOneToOne((_Module)localObject3, a.getTf_moduleName(), a.getTableAsName()))
      {
        localObject3 = (_Module)a.next();
      }
    }
  }

  public String getSumSqlStatment()
  {
    String str = Trade.ALLATORIxDEMO("\023\031怈\036\023讟\024\036RM\023aGQG__a\023\022\023\017\003\036RM\023a_[E[_a\023\022\023]\\K]J\033\024\032\036RM\023aPQFPGa\023\022");
    Iterator localIterator;
    SqlField localSqlField;
    for (goto 409; 
      (localSqlField.getAggregationType() == FieldAggregationType.MIN ? localIterator : 
      localSqlField.getAggregationType() == FieldAggregationType.MAX ? 
      localIterator : localSqlField.getAggregationType() == FieldAggregationType.AVG ? 
      localIterator : localSqlField.isCanSubTotal() ? 
      localIterator : (localSqlField = (SqlField)localIterator.next())
      .getDivisor() != null ? localIterator : localIterator).hasNext(); )
    {
      str = str + localSqlField.getSumDivisor_Denominator() + ModuleConstants.ALLATORIxDEMO("b-1l") + localSqlField.getFieldAsName() + 
        Trade.ALLATORIxDEMO("\023\022");

      str = str + ModuleConstants.ALLATORIxDEMO("19/d") + localSqlField.getFieldAsName() + Trade.ALLATORIxDEMO("\027\023_@\036") + localSqlField.getFieldAsName() + ModuleConstants.ALLATORIxDEMO("b`");

      str = str + Trade.ALLATORIxDEMO("RHT\026") + localSqlField.getFieldAsName() + ModuleConstants.ALLATORIxDEMO("eb-1l") + localSqlField.getFieldAsName() + Trade.ALLATORIxDEMO("\023\022");

      str = str + ModuleConstants.ALLATORIxDEMO("/-:d") + localSqlField.getFieldAsName() + Trade.ALLATORIxDEMO("\027\023_@\036") + localSqlField.getFieldAsName() + ModuleConstants.ALLATORIxDEMO("b`");

      str = str + Trade.ALLATORIxDEMO("^W]\026") + localSqlField.getFieldAsName() + ModuleConstants.ALLATORIxDEMO("eb-1l") + localSqlField.getFieldAsName() + Trade.ALLATORIxDEMO("\023\022");

      str = str + ModuleConstants.ALLATORIxDEMO("l,9. b-1l") + localSqlField.getFieldAsName() + Trade.ALLATORIxDEMO("\022");
    }
    for (tmpTernaryOp = (localIterator = a.joinField.iterator()); localIterator.hasNext(); str = str + ModuleConstants.ALLATORIxDEMO("l,9. b-1l") + localSqlField.getFieldAsName() + Trade.ALLATORIxDEMO("\022"))
    {
      localSqlField = (SqlField)localIterator.next();
    }
    String tmp492_491 = str; str = tmp492_491.substring(0, tmp492_491.length() - 1);

    return str + ModuleConstants.ALLATORIxDEMO("l");
  }

  public String getGroupFieldname(int a)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i;
    for (goto 87; i <= a; )
    {
      localStringBuffer.append(Trade.ALLATORIxDEMO("\036") + ((GroupFieldDefine)a.reportParam.getGroups().get(i)).getFieldAsName() + ModuleConstants.ALLATORIxDEMO("l"));

      if (i < a) {
        localStringBuffer.append(Trade.ALLATORIxDEMO("\022"));
      }

      i++;
    }

    return localStringBuffer.toString();
  }

  public String getCountSqlStatement()
  {
    String str = ModuleConstants.ALLATORIxDEMO("1).)!8b/-9,8jfkl");

    str = str + Trade.ALLATORIxDEMO("\023XAQ^\036") + a.getFrom();

    str = str + a.getLeftJoin();

    return str += a.getWhere();
  }

  public void setSorts(SortParameter[] a)
  {
    a.sorts = a;
  }

  protected String getFrom()
  {
    return ModuleConstants.ALLATORIxDEMO("l") + a.module.getTf_tableName() + Trade.ALLATORIxDEMO("\036") + a.module.getTableAsName();
  }

  public String getSubTotalSqlStatment()
  {
    GroupFieldDefine tmp18_17 = 
      (localObject = (GroupFieldDefine)a.reportParam.getGroups().get(0));

    String str = tmp18_17.getModuleField().getTf_fieldName();
    if (!tmp18_17
      .getModule().equals(a.reportParam.getBaseModule()))
    {
      str = ((GroupFieldDefine)localObject).getModule().getTableAsName() + "___" + str;
    }
    Object localObject = str + ModuleConstants.ALLATORIxDEMO("l#?b\0236#6-.\023b`b~rl#?b\023.)4).\023b`b/-9,8jfkl#?b\023!#7\"6\023b`");

    tmpTernaryOp = (localIterator = a.fieldList.iterator());
    Iterator localIterator;
    break label517;
  }

  public List<SqlLeftJoin> getJoinOn()
  {
    return a.joinOn;
  }

  public String getSortByString()
  {
    String str = "";

    if ((!a.reportParam.hasSubTotal()) && (a.reportParam.getIsShowTotal().booleanValue()))
    {
      str = ModuleConstants.ALLATORIxDEMO("l\035 ':' \035lnl");
    }

    if ((a.sorts != null) && (a.sorts.length > 0))
    {
      SortParameter[] arrayOfSortParameter;
      int j = (arrayOfSortParameter = a.sorts).length; tmpTernaryOp = (i = 0);
      int i;
    }
  }

  public String getAggregateFieldSqlStatement(String a, String a)
  {
    a = ModuleConstants.ALLATORIxDEMO("?' '/6l") + a + Trade.ALLATORIxDEMO("\026") + a.module.getTableAsName() + ModuleConstants.ALLATORIxDEMO("b") + a + Trade.ALLATORIxDEMO("\032\036");

    a = a + ModuleConstants.ALLATORIxDEMO("b*0#/l") + a.getFrom();

    a = a + a.getLeftJoin();

    a = a.aggregationParentModule.getTf_moduleName() + Trade.ALLATORIxDEMO("\036") + 
      a.aggregationParentModule.getTableAsName() + ModuleConstants.ALLATORIxDEMO("b#,l") + 
      a.aggregationParentModule.getTableAsName();

    String str = a.aggregationParentModule.getTf_moduleName() + Trade.ALLATORIxDEMO("\036l][W_Z") + 
      a.aggregationParentModule.getTableAsName() + ModuleConstants.ALLATORIxDEMO("b#,l\035/*%.(") + 
      a.aggregationParentModule.getTableAsName();

    a = a.replaceFirst(a, str);

    return 
      a += a.getWhere();
  }

  public String getSubTotalSqlStatment(int a)
  {
    Object localObject = ((GroupFieldDefine)a.reportParam.getGroups().get(a))
      .getFieldAsName();

    String str1 = a.getGroupFieldname(a);

    String str2 = "";
    int i;
    for (goto 70; i < a; 
      str2 = i++) new StringBuilder(String.valueOf(str2));

    StringBuffer localStringBuffer = new StringBuffer();
    StringBuffer tmp89_86 = localStringBuffer; tmp89_86; tmp89_86.<init>(ModuleConstants.ALLATORIxDEMO("k"));

    localObject = a.fieldList.iterator();

    new java/lang/StringBuilder.append(str2 + Trade.ALLATORIxDEMO("\024\025") + (String)localObject + ModuleConstants.ALLATORIxDEMO("b-1l\0358-8# \035lnl") + (20 + a) + Trade.ALLATORIxDEMO("\036RM\023a_[E[_a\023\022\023]\\K]J\033\024\032\036RM\023aPQFPGa\023\022"));

    for (tmpTernaryOp = localObject; 
      (str1.indexOf(ModuleConstants.ALLATORIxDEMO("l") + a.getFieldAsName() + Trade.ALLATORIxDEMO("\036")) != -1 ? localObject : 
      a.getAggregationType() == FieldAggregationType.MIN ? localObject : 
      a.getAggregationType() == FieldAggregationType.MAX ? localObject : 
      a.getAggregationType() == FieldAggregationType.AVG ? localObject : a.isCanSubTotal() ? localObject : (a = (SqlField)((Iterator)localObject).next())
      .getDivisor() != null ? localObject : (Iterator)localObject).hasNext(); )
    {
      localStringBuffer.append(a.getSumDivisor_Denominator() + ModuleConstants.ALLATORIxDEMO("b-1l") + a.getFieldAsName() + Trade.ALLATORIxDEMO("\023\022"));

      localStringBuffer.append(ModuleConstants.ALLATORIxDEMO("19/d") + a.getFieldAsName() + Trade.ALLATORIxDEMO("\027\023_@\036") + a.getFieldAsName() + ModuleConstants.ALLATORIxDEMO("b`"));

      localStringBuffer.append(Trade.ALLATORIxDEMO("RHT\026") + a.getFieldAsName() + ModuleConstants.ALLATORIxDEMO("eb-1l") + a.getFieldAsName() + Trade.ALLATORIxDEMO("\023\022"));

      localStringBuffer.append(ModuleConstants.ALLATORIxDEMO("/-:d") + a.getFieldAsName() + Trade.ALLATORIxDEMO("\027\023_@\036") + a.getFieldAsName() + ModuleConstants.ALLATORIxDEMO("b`"));

      localStringBuffer.append(Trade.ALLATORIxDEMO("^W]\026") + a.getFieldAsName() + ModuleConstants.ALLATORIxDEMO("eb-1l") + a.getFieldAsName() + Trade.ALLATORIxDEMO("\023\022"));

      localStringBuffer.append(ModuleConstants.ALLATORIxDEMO("l") + a.getFieldAsName() + Trade.ALLATORIxDEMO("\023_@\036") + a.getFieldAsName() + ModuleConstants.ALLATORIxDEMO("`"));

      localStringBuffer.append(Trade.ALLATORIxDEMO("\036]K_R\023_@\036") + a.getFieldAsName() + ModuleConstants.ALLATORIxDEMO("`"));
    }
    for (tmpTernaryOp = (localObject = a.joinField.iterator()); 
      (str1.indexOf(Trade.ALLATORIxDEMO("\036") + a.getFieldAsName() + ModuleConstants.ALLATORIxDEMO("l")) != -1 ? localObject : (Iterator)localObject).hasNext(); ) { a = (SqlField)((Iterator)localObject).next();

      localStringBuffer.append(Trade.ALLATORIxDEMO("\036") + a.getFieldAsName() + ModuleConstants.ALLATORIxDEMO("b-1l") + a.getFieldAsName() + Trade.ALLATORIxDEMO("\022"));

      localStringBuffer.append(ModuleConstants.ALLATORIxDEMO("l,9. b-1l") + a.getFieldAsName() + Trade.ALLATORIxDEMO("\022"));
    }
    StringBuffer tmp825_823 = localStringBuffer; return tmp825_823.substring(0, tmp825_823.length() - 1) + ModuleConstants.ALLATORIxDEMO("l");
  }

  protected String getGridColumnFilterString()
  {
    String str;
    if (((str = a.reportParam.getQuery()) == null) || 
      (str.length() < 1))
      return null;
  }

  public List<SqlField> getJoinField()
  {
    return a.joinField;
  }

  public SqlGenerator(ReportParam a, _Module a, HttpServletRequest a)
  {
  }

  public void setFieldList(List<SqlField> a)
  {
    a.fieldList = a;
  }

  public SqlGenerator(ReportParam a, HttpServletRequest a)
  {
  }

  protected SqlField getSqlFieldWithReportField(ReportField a)
  {
    Iterator localIterator;
    SqlField localSqlField;
    for (goto 43; localIterator.hasNext(); )
    {
      if ((localSqlField = (SqlField)localIterator.next())
        .getFieldFullName().equals(a.getFieldname()))
      {
        return localSqlField;
      }
    }
    for (tmpTernaryOp = (localIterator = a.joinField.iterator()); localIterator.hasNext(); ) {
      if (
        (localSqlField = (SqlField)localIterator.next()).getFieldFullName().equals(a.getFieldname()))
      {
        return localSqlField;
      }

    }

    return null;
  }

  public void getAllSQLParentOneToOne(_Module a, String a, String a)
  {
    for (goto 46; ((Iterator)localObject2).hasNext(); )
    {
      if ((localObject1 = (SqlLeftJoin)((Iterator)localObject2).next())
        .getModuleName().equals(a.getTf_moduleName()))
      {
        return;
      }

    }

    if (a.getTf_moduleName().equals(a.module.getTf_moduleName()))
    {
      return;
    }

    Object localObject1 = a.getTableAsName() + "___" + a.getTf_primaryKey();
     tmp120_119 = null;

    Object localObject2 = new SqlField(a.getTf_moduleName(), a.getTableAsName(), a.getTf_primaryKey(), tmp120_119, tmp120_119, FieldType.String.getValue(), Boolean.valueOf(false));
    Object tmp142_140 = localObject2; tmp142_140.setFieldAsName((String)localObject1); tmp142_140.setFieldScalar(
      (String)localObject1);

    a.getJoinField().add(localObject2);
    Object localObject3;
    if ((
      a.getTf_nameFields() != null) && (!a.getTf_nameFields().equals(Trade.ALLATORIxDEMO("K]ZVXZPVZ"))) && (!a.getTf_nameFields().equals(a.getTf_primaryKey())))
    {
      localObject1 = a.getTableAsName() + "___" + a.getTf_nameFields();
       tmp246_245 = null;

      localObject3 = new SqlField(a.getTf_moduleName(), a.getTableAsName(), 
        a.getTf_nameFields(), tmp246_245, tmp246_245, FieldType.String.getValue(), Boolean.valueOf(false));
      Object tmp267_265 = localObject3; tmp267_265.setFieldAsName((String)localObject1); tmp267_265.setFieldScalar(
        (String)localObject1);

      ((SqlField)localObject2).setNameField((SqlField)localObject3);

      a
        .getJoinField().add(localObject3);
    }

    a.getJoinOn().add(a.getSQLManyToOneLeftJoin(a, a, a));

    if ((
      a.isAggregation) && (a.aggregationParentModule == a))
      return;
    if (a.getParents() != null)
    {
      for (tmpTernaryOp = (a = a.getParents().iterator()); a.hasNext(); a.getAllSQLManyToOne((_Module)localObject3, a.getTf_moduleName(), a.getTableAsName()))
      {
        localObject3 = (_Module)a.next();
      }
    }
    if (a.getParentOneToOnes() != null)
    {
      for (tmpTernaryOp = (a = a.getParentOneToOnes().iterator()); a.hasNext(); a.getAllSQLParentOneToOne((_Module)localObject3, a.getTf_moduleName(), a.getTableAsName()))
      {
        localObject3 = (_Module)a.next();
      }
    }
  }

  public List<SqlField> getFieldList()
  {
    return a.fieldList;
  }

  public String getSqlStatment()
  {
    String str1 = ModuleConstants.ALLATORIxDEMO("?' '/6lekb-1l\0358-8# \035lnls|rl#?b\023.)4).\023b`b|b-1l\035/-9,8\035lnl") + a.getSelectFields();

    str1 = str1 + Trade.ALLATORIxDEMO("\023XAQ^\036") + a.getFrom();

    str1 = str1 + a.getLeftJoin();

    str1 = str1 + a.getWhere();

    if ((!a.reportParam.hasSubTotal()) && (!a.reportParam.getIsShowTotal().booleanValue()))
    {
      String str2;
      if ((str2 = a.getSortByString())
        .length() > 0)
        str1 = str1 + ModuleConstants.ALLATORIxDEMO("b#0('>b.;l") + str2;
    }
    return str1;
  }

  public void setJoinField(List<SqlField> a)
  {
    a.joinField = a;
  }

  protected String getLeftJoin()
  {
    String str = "";
    Iterator localIterator;
    SqlLeftJoin localSqlLeftJoin;
    for (goto 52; localIterator.hasNext(); str = str + localSqlLeftJoin.getJoinSql())
    {
      localSqlLeftJoin = (SqlLeftJoin)localIterator.next();
    }
    return str;
  }
}