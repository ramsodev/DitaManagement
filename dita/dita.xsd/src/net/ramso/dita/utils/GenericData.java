package net.ramso.dita.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.LogManager;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.namespace.QName;


public class GenericData {

	
	private Map<QName,Object> allAttributes;
    @XmlAnyAttribute
    public Map<QName,Object> getAnyAttributes(){
        if( allAttributes == null ){
            allAttributes = new HashMap<QName,Object>();
        }
        return allAttributes;
    }
    
    public HashMap<String, Object> getAllAttributes(){
    	HashMap<String, Object> attributes = new HashMap<String, Object>();
    	Field[] fields = getClass().getSuperclass().getDeclaredFields();
    	for (Field field : fields) {
			Annotation[] ano = field.getDeclaredAnnotations();
			for (Annotation annotation : ano) {
				if(annotation instanceof XmlAttribute){
					try {
						Method getter = new PropertyDescriptor(field.getName(), getClass().getSuperclass()).getReadMethod();
						Object value = getter.invoke(this, null);
						attributes.put(field.getName(), value);
					} catch (IllegalArgumentException | IllegalAccessException |  SecurityException | IntrospectionException | InvocationTargetException e) {
						
					}
				}
			}
		}
    	for (Entry<QName, Object> entry : getAnyAttributes().entrySet()) {
			attributes.put(entry.getKey().toString(),entry.getValue());
		}
    	return attributes;
    }
}
