/*
 * XML Type:  for-specType
 * Namespace: 
 * Java type: minderupt.spectacular.spine.config.ForSpecType
 *
 * Automatically generated - do not modify.
 */
package minderupt.spectacular.spine.config.impl;
/**
 * An XML for-specType(@).
 *
 * This is a complex type.
 */
public class ForSpecTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements minderupt.spectacular.spine.config.ForSpecType
{
    private static final long serialVersionUID = 1L;
    
    public ForSpecTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName OPTION$0 = 
        new javax.xml.namespace.QName("", "option");
    private static final javax.xml.namespace.QName SPEC$2 = 
        new javax.xml.namespace.QName("", "spec");
    
    
    /**
     * Gets array of all "option" elements
     */
    public minderupt.spectacular.spine.config.OptionType[] getOptionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(OPTION$0, targetList);
            minderupt.spectacular.spine.config.OptionType[] result = new minderupt.spectacular.spine.config.OptionType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "option" element
     */
    public minderupt.spectacular.spine.config.OptionType getOptionArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            minderupt.spectacular.spine.config.OptionType target = null;
            target = (minderupt.spectacular.spine.config.OptionType)get_store().find_element_user(OPTION$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "option" element
     */
    public int sizeOfOptionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(OPTION$0);
        }
    }
    
    /**
     * Sets array of all "option" element
     */
    public void setOptionArray(minderupt.spectacular.spine.config.OptionType[] optionArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(optionArray, OPTION$0);
        }
    }
    
    /**
     * Sets ith "option" element
     */
    public void setOptionArray(int i, minderupt.spectacular.spine.config.OptionType option)
    {
        synchronized (monitor())
        {
            check_orphaned();
            minderupt.spectacular.spine.config.OptionType target = null;
            target = (minderupt.spectacular.spine.config.OptionType)get_store().find_element_user(OPTION$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(option);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "option" element
     */
    public minderupt.spectacular.spine.config.OptionType insertNewOption(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            minderupt.spectacular.spine.config.OptionType target = null;
            target = (minderupt.spectacular.spine.config.OptionType)get_store().insert_element_user(OPTION$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "option" element
     */
    public minderupt.spectacular.spine.config.OptionType addNewOption()
    {
        synchronized (monitor())
        {
            check_orphaned();
            minderupt.spectacular.spine.config.OptionType target = null;
            target = (minderupt.spectacular.spine.config.OptionType)get_store().add_element_user(OPTION$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "option" element
     */
    public void removeOption(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(OPTION$0, i);
        }
    }
    
    /**
     * Gets the "spec" attribute
     */
    public java.lang.String getSpec()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SPEC$2);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "spec" attribute
     */
    public org.apache.xmlbeans.XmlString xgetSpec()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(SPEC$2);
            return target;
        }
    }
    
    /**
     * True if has "spec" attribute
     */
    public boolean isSetSpec()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(SPEC$2) != null;
        }
    }
    
    /**
     * Sets the "spec" attribute
     */
    public void setSpec(java.lang.String spec)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SPEC$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(SPEC$2);
            }
            target.setStringValue(spec);
        }
    }
    
    /**
     * Sets (as xml) the "spec" attribute
     */
    public void xsetSpec(org.apache.xmlbeans.XmlString spec)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(SPEC$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(SPEC$2);
            }
            target.set(spec);
        }
    }
    
    /**
     * Unsets the "spec" attribute
     */
    public void unsetSpec()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(SPEC$2);
        }
    }
}
