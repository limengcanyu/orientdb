/* Generated By:JJTree: Do not edit this line. OAlterSecurityPolicyStatement.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.orientechnologies.orient.core.sql.parser;

import com.orientechnologies.orient.core.command.OCommandContext;
import com.orientechnologies.orient.core.db.ODatabaseInternal;
import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.exception.OCommandExecutionException;
import com.orientechnologies.orient.core.metadata.security.OSecurityInternal;
import com.orientechnologies.orient.core.metadata.security.OSecurityPolicy;
import com.orientechnologies.orient.core.sql.executor.OInternalResultSet;
import com.orientechnologies.orient.core.sql.executor.OResultInternal;
import com.orientechnologies.orient.core.sql.executor.OResultSet;

import java.util.Map;

public class OAlterSecurityPolicyStatement extends OSimpleExecStatement {

  protected OIdentifier name;

  protected OBooleanExpression create;
  protected OBooleanExpression read;
  protected OBooleanExpression beforeUpdate;
  protected OBooleanExpression afterUpdate;
  protected OBooleanExpression delete;
  protected OBooleanExpression execute;


  protected boolean removeCreate = false;
  protected boolean removeRead = false;
  protected boolean removeBeforeUpdate = false;
  protected boolean removeAfterUpdate = false;
  protected boolean removeDelete = false;
  protected boolean removeExecute = false;

  public OAlterSecurityPolicyStatement(int id) {
    super(id);
  }

  public OAlterSecurityPolicyStatement(OrientSql p, int id) {
    super(p, id);
  }


  @Override
  public OResultSet executeSimple(OCommandContext ctx) {
    ODatabaseSession db = (ODatabaseSession) ctx.getDatabase();
    OSecurityInternal security = ((ODatabaseInternal) db).getSharedContext().getSecurity();
    OSecurityPolicy policy = security.getSecurityPolicy(db, name.getStringValue());
    if (policy == null) {
      throw new OCommandExecutionException("Cannot find security policy " + name.toString());
    }

    if (create != null) {
      policy.setCreateRule(create.toString());
    }
    if (read != null) {
      policy.setReadRule(read.toString());
    }
    if (beforeUpdate != null) {
      policy.setBeforeUpdateRule(beforeUpdate.toString());
    }
    if (afterUpdate != null) {
      policy.setAfterUpdateRule(afterUpdate.toString());
    }
    if (delete != null) {
      policy.setDeleteRule(delete.toString());
    }
    if (execute != null) {
      policy.setExecuteRule(execute.toString());
    }

    if (removeCreate) {
      policy.setCreateRule(null);
    }
    if (removeRead) {
      policy.setReadRule(null);
    }
    if (removeBeforeUpdate) {
      policy.setBeforeUpdateRule(null);
    }
    if (removeAfterUpdate) {
      policy.setAfterUpdateRule(null);
    }
    if (removeDelete) {
      policy.setDeleteRule(null);
    }
    if (removeExecute) {
      policy.setExecuteRule(null);
    }
    security.saveSecurityPolicy(db, policy);


    OResultInternal result = new OResultInternal();
    result.setProperty("operation", "alter security policy");
    result.setProperty("name", name.getStringValue());
    OInternalResultSet rs = new OInternalResultSet();
    rs.add(result);
    return rs;

  }

  @Override
  public void toString(Map<Object, Object> params, StringBuilder builder) {
    builder.append("ALTER SECURITY POLICY ");
    name.toString(params, builder);

    boolean firstSet = true;
    if (create != null) {
      if (firstSet) {
        builder.append(" SET ");
      } else {
        builder.append(", ");
      }
      builder.append("CREATE = (");
      create.toString(params, builder);
      builder.append(")");
      firstSet = false;
    }

    if (read != null) {
      if (firstSet) {
        builder.append(" SET ");
      } else {
        builder.append(", ");
      }
      builder.append("READ = (");
      read.toString(params, builder);
      builder.append(")");
      firstSet = false;
    }
    if (beforeUpdate != null) {
      if (firstSet) {
        builder.append(" SET ");
      } else {
        builder.append(", ");
      }
      builder.append("BEFORE UPDATE = (");
      beforeUpdate.toString(params, builder);
      builder.append(")");
      firstSet = false;
    }

    if (afterUpdate != null) {
      if (firstSet) {
        builder.append(" SET ");
      } else {
        builder.append(", ");
      }
      builder.append("AFTER UPDATE = (");
      afterUpdate.toString(params, builder);
      builder.append(")");
      firstSet = false;
    }
    if (delete != null) {
      if (firstSet) {
        builder.append(" SET ");
      } else {
        builder.append(", ");
      }
      builder.append("DELETE = (");
      delete.toString(params, builder);
      builder.append(")");
      firstSet = false;
    }
    if (execute != null) {
      if (firstSet) {
        builder.append(" SET ");
      } else {
        builder.append(", ");
      }
      builder.append("EXECUTE = (");
      execute.toString(params, builder);
      builder.append(")");
      firstSet = false;
    }


    boolean firstRemove = true;
    if (removeCreate) {
      if (firstRemove) {
        builder.append(" REMOVE ");
      } else {
        builder.append(", ");
      }
      builder.append("CREATE");
      firstRemove = false;
    }

    if (removeRead) {
      if (firstRemove) {
        builder.append(" REMOVE ");
      } else {
        builder.append(", ");
      }
      builder.append("READ");
      firstRemove = false;
    }
    if (removeBeforeUpdate) {
      if (firstRemove) {
        builder.append(" REMOVE ");
      } else {
        builder.append(", ");
      }
      builder.append("BEFORE UPDATE");
      firstRemove = false;
    }

    if (removeAfterUpdate) {
      if (firstRemove) {
        builder.append(" REMOVE ");
      } else {
        builder.append(", ");
      }
      builder.append("AFTER UPDATE");
      firstRemove = false;
    }
    if (removeDelete) {
      if (firstRemove) {
        builder.append(" REMOVE ");
      } else {
        builder.append(", ");
      }
      builder.append("DELETE");
      firstRemove = false;
    }
    if (removeExecute) {
      if (firstRemove) {
        builder.append(" REMOVE ");
      } else {
        builder.append(", ");
      }
      builder.append("EXECUTE");
      firstRemove = false;
    }
  }

}
/* JavaCC - OriginalChecksum=849f284b6e4057d1f554daf024534423 (do not edit this line) */
