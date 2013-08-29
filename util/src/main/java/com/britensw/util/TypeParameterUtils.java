package com.britensw.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Gets the type parameter for a given superclass of a class
 *
 * @author Stuart Douglas <stuart@baileyroberts.com.au>
 */
public class TypeParameterUtils {
    public static Class<?> getTypeParameter(Class<?> clazz, Class<?> superclass) {
        return getTypeParameter(clazz, superclass, 0);
    }

    public static Class<?> getTypeParameter(Class<?> clazz, Class<?> superclass, int parameterNo) {
        Class entityType;
        Class<?> annotationLiteralSubclass = getSubclass(clazz, superclass);
        if (annotationLiteralSubclass == null) {
            throw new RuntimeException(clazz + "is not a subclass of " + superclass);
        }
        entityType = getTypeParameter(annotationLiteralSubclass, parameterNo);
        if (entityType == null) {
            throw new RuntimeException(clazz + " does not specify the type parameter T of " + superclass);
        }
        return entityType;
    }


    private static Class<?> getSubclass(Class<?> clazz, Class<?> targetClass) {
        Class<?> superclass = clazz.getSuperclass();
        if (superclass.equals(targetClass)) {
            return clazz;
        } else if (superclass.equals(Object.class)) {
            return null;
        } else {
            return (getSubclass(superclass, targetClass));
        }
    }

    @SuppressWarnings("unchecked")
    private static Class getTypeParameter(Class superclass, int paramNo) {
        Type type = superclass.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            if (parameterizedType.getActualTypeArguments().length > paramNo) {
                return (Class) parameterizedType.getActualTypeArguments()[paramNo];
            }
        }
        return null;
    }

}
