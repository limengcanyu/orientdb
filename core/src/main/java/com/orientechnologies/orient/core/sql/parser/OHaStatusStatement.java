/* Generated By:JJTree: Do not edit this line. OHaStatusStatement.java Version 4.3 */
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

public class OHaStatusStatement extends OStatement {

  public boolean servers    = false;
  public boolean db         = false;
  public boolean latency    = false;
  public boolean messages   = false;
  public boolean outputText = false;
  public boolean locks      = false;

  public OHaStatusStatement(int id) {
    super(id);
  }

  public OHaStatusStatement(OrientSql p, int id) {
    super(p, id);
  }

  @Override
  public void toString(Map<Object, Object> params, StringBuilder builder) {
    builder.append("HA STATUS");
    if (servers) {
      builder.append(" -servers");
    }
    if (db) {
      builder.append(" -db");
    }
    if (latency) {
      builder.append(" -latency");
    }
    if (messages) {
      builder.append(" -messages");
    }
    if (locks) {
      builder.append(" -locks");
    }
    if (outputText) {
      builder.append(" -output=text");
    }
    if (servers) {
      builder.append(" -servers");
    }
  }

  @Override
  public OResultSet execute(ODatabase db, Object[] args, OCommandContext parentContext, boolean usePlanCache) {
    StringBuilder builder = new StringBuilder();
    Map<Object, Object> pars = new HashMap<>();
    if (args != null) {
      for (int i = 0; i < args.length; i++) {
        pars.put(Integer.toString(i + 1), args[i]);
      }
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
/* JavaCC - OriginalChecksum=c8ab1b0172e8cdbea2078efe2c629e6a (do not edit this line) */
