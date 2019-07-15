/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.xmiinout.domain.bean;


import gov.nih.nci.ncicb.xmiinout.domain.UMLAssociation;
import gov.nih.nci.ncicb.xmiinout.domain.UMLAssociationEnd;
import gov.nih.nci.ncicb.xmiinout.domain.UMLTaggedValue;
import org.jdom.Element;

import java.util.*;


public class UMLAssociationBean extends JDomDomainObject implements UMLAssociation {

  private List<UMLAssociationEnd> associationEnds;

  private Map<String, UMLTaggedValue> taggedValuesMap = new HashMap<>();

  private String roleName;

  public UMLAssociationBean(Element element,
                            String roleName,
                            List<UMLAssociationEnd> ends) {
    super(element);
    this.roleName = roleName;
    associationEnds = new ArrayList<>(ends);

    for (UMLAssociationEnd end : associationEnds) {
      ((UMLClassBean) (end.getUMLElement())).addAssociation(this);
      ((UMLAssociationEndBean) end).setOwningAssociation(this);
    }
  }

  public List<UMLAssociationEnd> getAssociationEnds() {
    return associationEnds;
  }

  public String getRoleName() {
    return roleName;
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

  public UMLTaggedValue addTaggedValue(String name, String value) {
    UMLTaggedValueBean taggedValue = new UMLTaggedValueBean(null, name, value);

    // add to jdom element
    UMLTaggedValue tv = writer.getUMLAssociationWriter().writeTaggedValue(this, taggedValue);

    taggedValuesMap.put(tv.getName(), tv);

    return tv;
  }

  public void removeTaggedValue(String name) {
    //remove from jdom element
    UMLTaggedValue tv = taggedValuesMap.remove(name);

    if (tv != null) {
      writer.getUMLAssociationWriter().removeTaggedValue(this, tv);
    }

  }


}
