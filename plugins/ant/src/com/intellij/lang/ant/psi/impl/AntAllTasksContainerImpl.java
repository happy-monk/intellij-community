package com.intellij.lang.ant.psi.impl;

import com.intellij.lang.ant.psi.AntAllTasksContainer;
import com.intellij.lang.ant.psi.AntElement;
import com.intellij.lang.ant.psi.introspection.AntTypeDefinition;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.StringBuilderSpinAllocator;
import org.jetbrains.annotations.NonNls;

public class AntAllTasksContainerImpl extends AntTaskImpl implements AntAllTasksContainer {

  public AntAllTasksContainerImpl(final AntElement parent, final XmlTag sourceElement, final AntTypeDefinition definition) {
    super(parent, sourceElement, definition);
    if (myDefinition.getNestedClassName(definition.getTypeId()) == null) {
      // allow all tasks as nested elements
      for (final AntTypeDefinition def : getAntFile().getBaseTypeDefinitions()) {
        if (def.isTask()) {
          myDefinition.registerNestedType(def.getTypeId(), def.getClassName());
        }
      }
    }
  }

  public String toString() {
    @NonNls final StringBuilder builder = StringBuilderSpinAllocator.alloc();
    try {
      builder.append("AntAllTasksContainer[");
      builder.append(getSourceElement().getName());
      builder.append("]");
      return builder.toString();
    }
    finally {
      StringBuilderSpinAllocator.dispose(builder);
    }
  }
}
