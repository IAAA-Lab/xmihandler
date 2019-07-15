/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.xmiinout.domain.bean;

import gov.nih.nci.ncicb.xmiinout.domain.UMLDependency;
import gov.nih.nci.ncicb.xmiinout.domain.UMLDependencyEnd;
import gov.nih.nci.ncicb.xmiinout.domain.UMLTaggedValue;
import gov.nih.nci.ncicb.xmiinout.domain.UMLVisibility;
import org.jdom.Element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class UMLDependencyBean extends JDomDomainObject implements UMLDependency {

  private UMLDependencyEnd client, supplier;

  private Map<String, UMLTaggedValue> taggedValuesMap = new HashMap<>();

  private UMLVisibility visibility;

  private String name;

  private String stereotype;

  public UMLDependencyBean(Element element, String name, UMLVisibility visibility, UMLDependencyEnd client, UMLDependencyEnd supplier) {
    super(element);

    this.visibility = visibility;

    this.supplier = supplier;
    this.client = client;

    this.name = name;

    ((IUMLDependencyEnd) supplier).addDependency(this);
    ((IUMLDependencyEnd) client).addDependency(this);

    this.stereotype = null;
  }

  public UMLDependencyBean(Element element, String name, UMLVisibility visibility, UMLDependencyEnd client, UMLDependencyEnd supplier, String stereotype) {
    super(element);

    this.visibility = visibility;

    this.supplier = supplier;
    this.client = client;

    this.name = name;

    ((IUMLDependencyEnd) supplier).addDependency(this);
    ((IUMLDependencyEnd) client).addDependency(this);

    this.stereotype = stereotype;

  }

  public UMLDependencyEnd getSupplier() {
    return supplier;
  }

  public UMLDependencyEnd getClient() {
    return client;
  }

  public UMLVisibility getVisibility() {
    return visibility;
  }

  public String getName() {
    return name;
  }

  public UMLTaggedValue getTaggedValue(String name) {
    return taggedValuesMap.get(name);
  }

  public UMLTaggedValue getTaggedValue(String name, boolean ignoreCase) {
    if (!ignoreCase)
      return getTaggedValue(name);

    for (String tvName : taggedValuesMap.keySet()) {
      if (tvName.equalsIgnoreCase(name)) {
        return taggedValuesMap.get(tvName);
      }
    }

    return null;
  }

  public Collection<UMLTaggedValue> getTaggedValues() {
    return new ArrayList<>(taggedValuesMap.values());
  }

  public UMLTaggedValue addTaggedValue(UMLTaggedValue taggedValue) {
    taggedValuesMap.put(taggedValue.getName(), taggedValue);
    return taggedValue;
  }

  public String getStereotype() {
    return stereotype;
  }

  public void addStereotype(String stereotype) {

    if (getJDomElement() == null) {
      throw new IllegalStateException("Cannot add to a detached object. Please persist first.");
    }


    //add to jdom element
    writer.getUMLDependencyWriter().writeStereotype(this, stereotype);


    this.stereotype = stereotype;
  }

  public void removeStereotype(String stereotype) {

    if (getJDomElement() == null) {
      throw new IllegalStateException("Cannot add to a detached object. Please persist first.");
    }


    //remove from jdom element
    writer.getUMLDependencyWriter().removeStereotype(this, stereotype);


    this.stereotype = stereotype;
  }


  public UMLTaggedValue addTaggedValue(String name, String value) throws IllegalStateException {
    UMLTaggedValueBean taggedValue = new UMLTaggedValueBean(null, name, value);

    if (getJDomElement() == null) {
      throw new IllegalStateException("Cannot add to a detached object. Please persist first.");
    }

    // add to jdom element
    UMLTaggedValue tv = writer.getUMLDependencyWriter().writeTaggedValue(this, taggedValue);

    taggedValuesMap.put(tv.getName(), tv);

    return tv;
  }

  public void removeTaggedValue(String name) {
    //remove from jdom element
    UMLTaggedValue tv = taggedValuesMap.remove(name);
    if (tv != null) {
      writer.getUMLDependencyWriter().removeTaggedValue(this, tv);
    }
  }

}
