/* Generated By:JJTree: Do not edit this line. OHaSyncClusterStatement.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.orientechnologies.orient.core.sql.parser;

import com.orientechnologies.orient.core.command.OCommandContext;
import com.orientechnologies.orient.core.db.ODatabase;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.core.sql.executor.OIteratorResultSet;
import com.orientechnologies.orient.core.sql.executor.OResultSet;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OHaSyncClusterStatement extends OStatement {

  public OIdentifier clusterName;
  public boolean modeFull  = true;
  public boolean modeMerge = false;

  public OHaSyncClusterStatement(int id) {
    super(id);
  }

  public OHaSyncClusterStatement(OrientSql p, int id) {
    super(p, id);
  }

  @Override
  public void toString(Map<Object, Object> params, StringBuilder builder) {
    builder.append("HA SYNC CLUSTER ");
    clusterName.toString(params, builder);
  }

  @Override
  public OResultSet execute(ODatabase db, Object[] args, OCommandContext parentContext, boolean usePlanCache) {
    StringBuilder builder = new StringBuilder();
    Map<Object, Object> pars = new HashMap<>();
    for (int i = 0; i < args.length; i++) {
      pars.put(Integer.toString(i + 1), args[i]);
    }
    toString(pars, builder);
    Object result = db.command(new OCommandSQL(builder.toString())).execute();
    List listResult;
    if (result instanceof List) {
      listResult = (List) result;
    } else {
      listResult = Collections.singletonList(result);
    }
    return new OIteratorResultSet(listResult.iterator());
  }

  @Override
  public OResultSet execute(ODatabase db, Map args, OCommandContext parentContext, boolean usePlanCache) {
    StringBuilder builder = new StringBuilder();
    toString(args, builder);
    Object result = db.command(new OCommandSQL(builder.toString())).execute();
    List listResult;
    if (result instanceof List) {
      listResult = (List) result;
    } else {
      listResult = Collections.singletonList(result);
    }
    return new OIteratorResultSet(listResult.iterator());
  }
}
/* JavaCC - OriginalChecksum=fbf0df8004d889ebc80f39be85008720 (do not edit this line) */
