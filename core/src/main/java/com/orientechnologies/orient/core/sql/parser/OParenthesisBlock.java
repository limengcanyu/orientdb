/* Generated By:JJTree: Do not edit this line. OParenthesisBlock.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.orientechnologies.orient.core.sql.parser;

import com.orientechnologies.orient.core.command.OCommandContext;
import com.orientechnologies.orient.core.db.record.OIdentifiable;
import com.orientechnologies.orient.core.sql.executor.OResult;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class OParenthesisBlock extends OBooleanExpression {

  protected OBooleanExpression subElement;

  public OParenthesisBlock(int id) {
    super(id);
  }

  public OParenthesisBlock(OrientSql p, int id) {
    super(p, id);
  }

  @Override
  public boolean evaluate(OIdentifiable currentRecord, OCommandContext ctx) {
    return subElement.evaluate(currentRecord, ctx);
  }

  @Override
  public boolean evaluate(OResult currentRecord, OCommandContext ctx) {
    return subElement.evaluate(currentRecord, ctx);
  }

  public void toString(Map<Object, Object> params, StringBuilder builder) {
    builder.append("(");
    subElement.toString(params, builder);
    builder.append(" )");
  }

  @Override
  public boolean supportsBasicCalculation() {
    return subElement.supportsBasicCalculation();
  }

  @Override
  protected int getNumberOfExternalCalculations() {
    return subElement.getNumberOfExternalCalculations();
  }

  @Override
  protected List<Object> getExternalCalculationConditions() {
    return subElement.getExternalCalculationConditions();
  }

  @Override
  public List<OAndBlock> flatten() {
    return subElement.flatten();
  }

  @Override
  public boolean needsAliases(Set<String> aliases) {
    return subElement.needsAliases(aliases);
  }

  @Override
  public OParenthesisBlock copy() {
    OParenthesisBlock result = new OParenthesisBlock(-1);
    result.subElement = subElement.copy();
    return result;
  }

  @Override
  public void extractSubQueries(SubQueryCollector collector) {
    this.subElement.extractSubQueries(collector);
  }

  @Override
  public boolean refersToParent() {
    return subElement.refersToParent();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    OParenthesisBlock that = (OParenthesisBlock) o;

    if (subElement != null ? !subElement.equals(that.subElement) : that.subElement != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    return subElement != null ? subElement.hashCode() : 0;
  }

  @Override
  public List<String> getMatchPatternInvolvedAliases() {
    return subElement.getMatchPatternInvolvedAliases();
  }

  @Override
  public void translateLuceneOperator() {
    subElement.translateLuceneOperator();
  }

  @Override
  public boolean isCacheable() {
    return subElement.isCacheable();
  }
}
/* JavaCC - OriginalChecksum=9a16b6cf7d051382acb94c45067631a9 (do not edit this line) */
