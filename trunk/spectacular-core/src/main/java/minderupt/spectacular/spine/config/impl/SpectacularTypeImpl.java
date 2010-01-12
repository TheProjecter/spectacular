/*
 * XML Type:  spectacularType
 * Namespace: 
 * Java type: minderupt.spectacular.spine.config.SpectacularType
 *
 * Automatically generated - do not modify.
 */
package minderupt.spectacular.spine.config.impl;
/**
 * An XML spectacularType(@).
 *
 * This is a complex type.
 */
public class SpectacularTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements minderupt.spectacular.spine.config.SpectacularType
{
    private static final long serialVersionUID = 1L;
    
    public SpectacularTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FORSPEC$0 = 
        new javax.xml.namespace.QName("", "for-spec");
    
    
    /**
     * Gets array of all "for-spec" elements
     */
    public minderupt.spectacular.spine.config.ForSpecType[] getForSpecArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(FORSPEC$0, targetList);
            minderupt.spectacular.spine.config.ForSpecType[] result = new minderupt.spectacular.spine.config.ForSpecType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "for-spec" element
     */
    public minderupt.spectacular.spine.config.ForSpecType getForSpecArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            minderupt.spectacular.spine.config.ForSpecType target = null;
            target = (minderupt.spectacular.spine.config.ForSpecType)get_store().find_element_user(FORSPEC$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "for-spec" element
     */
    public int sizeOfForSpecArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(FORSPEC$0);
        }
    }
    
    /**
     * Sets array of all "for-spec" element
     */
    public void setForSpecArray(minderupt.spectacular.spine.config.ForSpecType[] forSpecArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(forSpecArray, FORSPEC$0);
        }
    }
    
    /**
     * Sets ith "for-spec" element
     */
    public void setForSpecArray(int i, minderupt.spectacular.spine.config.ForSpecType forSpec)
    {
        synchronized (monitor())
        {
            check_orphaned();
            minderupt.spectacular.spine.config.ForSpecType target = null;
            target = (minderupt.spectacular.spine.config.ForSpecType)get_store().find_element_user(FORSPEC$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(forSpec);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "for-spec" element
     */
    public minderupt.spectacular.spine.config.ForSpecType insertNewForSpec(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            minderupt.spectacular.spine.config.ForSpecType target = null;
            target = (minderupt.spectacular.spine.config.ForSpecType)get_store().insert_element_user(FORSPEC$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "for-spec" element
     */
    public minderupt.spectacular.spine.config.ForSpecType addNewForSpec()
    {
        synchronized (monitor())
        {
            check_orphaned();
            minderupt.spectacular.spine.config.ForSpecType target = null;
            target = (minderupt.spectacular.spine.config.ForSpecType)get_store().add_element_user(FORSPEC$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "for-spec" element
     */
    public void removeForSpec(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(FORSPEC$0, i);
        }
    }
}
