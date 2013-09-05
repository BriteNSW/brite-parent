package com.britensw.util;

import sun.reflect.generics.reflectiveObjects.TypeVariableImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
public class TypeUtil {

    private static final Class<?>[] EMPTY = new Class<?>[0];

    public static <T extends Annotation> T getAnnotation(final Class<T> annotationClass, final Class<?> clazz) {
        T that = clazz.getAnnotation(annotationClass);
        if (that != null) {
            return that;
        }
        final Class<?> superClass = clazz.getSuperclass();
        final Class<?>[] interfaces = clazz.getInterfaces();
        if (superClass != null && !Object.class.equals(superClass)) {
            that = getAnnotation(annotationClass, superClass);
            if (that != null) {
                return that;
            }
        }
        for (final Class<?> interfaz : interfaces) {
            that = getAnnotation(annotationClass, interfaz);
            if (that != null) {
                return that;
            }
        }
        return that;
    }

    public static <T extends Annotation> T getAnnotation(final Class<T> annotationClass, final Method method) {
        T that = method.getAnnotation(annotationClass);
        if (that != null) {
            return that;
        }
        final Class<?> superClass = method.getDeclaringClass().getSuperclass();
        final Class<?>[] interfaces = method.getDeclaringClass().getInterfaces();
        if (superClass != null && !Object.class.equals(superClass)) {
            try {
                final Method face = superClass.getMethod(method.getName(), method.getParameterTypes());
                that = getAnnotation(annotationClass, face);
                if (that != null) {
                    return that;
                }
            } catch (NoSuchMethodException e) {
                //
            }
        }
        for (final Class<?> interfaz : interfaces) {
            try {
                final Method face = interfaz.getMethod(method.getName(), method.getParameterTypes());
                that = getAnnotation(annotationClass, face);
                if (that != null) {
                    return that;
                }
            } catch (NoSuchMethodException e) {
                //
            }
        }
        return that;
    }

    /**
     * Retrieve an annotation from a method or it's class
     *
     * @param annotationClass
     * @param method
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Annotation> T getAnnotation(final Class<T> annotationClass, final Method method, final Class<?> clazz) {
        T that = getAnnotation(annotationClass, method);
        if (that != null) {
            return that;
        }
        that = getAnnotation(annotationClass, clazz);
        return that;
    }

    public static Class<?> resolve(final Type type, final Object that) {
        return type instanceof Class
                ? (Class<?>)type
                : that == null
                    ? Object.class
                    : that.getClass();
    }

    public static Type[] getGenericReturnTypes(final Method method) {
        final Type that = method.getGenericReturnType();
        if (that instanceof ParameterizedType) {
            return ((ParameterizedType)that).getActualTypeArguments();
        } else if (that instanceof Class) {
            return ((Class)that).getTypeParameters();
        }
        return new Type[]{method.getReturnType()};
    }

    public static Type[] getGenericParameterTypes(final Method method) {
        final Type type = method.getGenericParameterTypes()[0];
        if (type instanceof ParameterizedType) {
            final ParameterizedType parameterizedType = (ParameterizedType) type;
            return parameterizedType.getActualTypeArguments();
        }
        return new Type[]{type};
    }

    public static Class<?>[] getClassGenericSuperclassParameterTypes(final Method method) {
        return getClassGenericSuperclassParameterTypes(method.getDeclaringClass());
    }

    public static Class<?>[] getClassGenericSuperclassParameterTypes(final Class<?> clazz) {
        return getTypeGenericSuperclassParameterTypes(clazz.getGenericSuperclass());
    }

    public static Class<?>[] getTypeGenericSuperclassParameterTypes(final Type superclass) {
        if (superclass == null || superclass instanceof Class) {
            return EMPTY;
        } else {
            final Type[] types = ((ParameterizedType)superclass).getActualTypeArguments();
            final Class<?>[] clazzes = new Class[types.length];
            for (int i = 0; i < types.length; ++i) {
                final Type type = types[i];
                if (Class.class.isAssignableFrom(type.getClass())) {
                    clazzes[i] = (Class<?>) type;
                } else if (ParameterizedType.class.isAssignableFrom(type.getClass())) {
                    clazzes[i] = (Class<?>) ((ParameterizedType) type).getRawType();
                } else if (TypeVariableImpl.class.isAssignableFrom(type.getClass())) {
                    final GenericDeclaration that = ((TypeVariableImpl) type).getGenericDeclaration();
                    if (Class.class.isAssignableFrom(that.getClass())) {
                        clazzes[i] = (Class<?>) that;
                    }
                }
            }
            return clazzes;
        }
    }
}
