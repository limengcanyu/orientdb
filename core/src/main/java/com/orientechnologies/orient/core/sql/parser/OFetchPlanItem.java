/* Generated By:JJTree: Do not edit this line. OFetchPlanItem.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.orientechnologies.orient.core.sql.parser;

import com.orientechnologies.orient.core.sql.executor.OResult;
import com.orientechnologies.orient.core.sql.executor.OResultInternal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OFetchPlanItem extends SimpleNode {

  protected Boolean  star;
  protected OInteger leftDepth;
  protected boolean leftStar = false;
  protected OInteger rightDepth;
  protected List<String> fieldChain = new ArrayList<String>();

  public OFetchPlanItem(int id) {
    super(id);
  }

  public OFetchPlanItem(OrientSql p, int id) {
    super(p, id);
  }

  public void toString(Map<Object, Object> params, StringBuilder builder) {
    if (Boolean.TRUE.equals(star)) {
      builder.append("*");
    } else {
      if (leftDepth != null) {
        builder.append("[");
        leftDepth.toString(params, builder);
        builder.append("]");
      } else if (leftStar) {
        builder.append("[*]");
      }

      boolean first = true;
      for (String s : fieldChain) {
        if (!first) {
          builder.append(".");
        }
        builder.append(s);
        first = false;
      }

    }
    builder.append(":");
    rightDepth.toString(params, builder);
  }

  public OFetchPlanItem copy() {
    OFetchPlanItem result = new OFetchPlanItem(-1);
    result.star = star;
    result.leftDepth = leftDepth == null ? null : leftDepth.copy();
    result.leftStar = leftStar;
    result.rightDepth = rightDepth == null ? null : rightDepth.copy();
    result.fieldChain.addAll(fieldChain);
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    OFetchPlanItem that = (OFetchPlanItem) o;

    if (leftStar != that.leftStar)
      return false;
    if (star != null ? !star.equals(that.star) : that.star != null)
      return false;
    if (leftDepth != null ? !leftDepth.equals(that.leftDepth) : that.leftDepth != null)
      return false;
    if (rightDepth != null ? !rightDepth.equals(that.rightDepth) : that.rightDepth != null)
      return false;
    if (fieldChain != null ? !fieldChain.equals(that.fieldChain) : that.fieldChain != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = star != null ? star.hashCode() : 0;
    result = 31 * result + (leftDepth != null ? leftDepth.hashCode() : 0);
    result = 31 * result + (leftStar ? 1 : 0);
    result = 31 * result + (rightDepth != null ? rightDepth.hashCode() : 0);
    result = 31 * result + (fieldChain != null ? fieldChain.hashCode() : 0);
    return result;
  }

  public OResult serialize() {
    OResultInternal result = new OResultInternal();
    result.setProperty("star", star);
    if (leftDepth != null) {
      result.setProperty("leftDepth", leftDepth.serialize());
    }
    result.setProperty("leftStar", leftStar);
    if (rightDepth != null) {
      result.setProperty("rightDepth", rightDepth.serialize());
    }
    if (fieldChain != null) {
      result.setProperty("rightDepth", fieldChain.stream().collect(Collectors.toList()));
    }
    return result;
  }

  public void deserialize(OResult fromResult) {
    star = fromResult.getProperty("star");
    if (fromResult.getProperty("leftDepth") != null) {
      leftDepth = new OInteger(-1);
      leftDepth.deserialize(fromResult.getProperty("leftDepth"));
    }
    leftStar = fromResult.getProperty("leftStar");
    if (fromResult.getProperty("rightDepth") != null) {
      rightDepth = new OInteger(-1);
      rightDepth.deserialize(fromResult.getProperty("rightDepth"));
    }
    if (fromResult.getProperty("fieldChain") != null) {
      List<String> ser = fromResult.getProperty("fieldChain");
      fieldChain = new ArrayList<>();
      fieldChain.addAll(ser);
    }
  }
}
/* JavaCC - OriginalChecksum=b7f4c9a97a8f2ca3d85020e054a9ad16 (do not edit this line) */
