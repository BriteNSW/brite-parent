package com.britensw.util;

import java.lang.reflect.Array;

/**
 * Collected methods which allow easy implementation of <code>hashCode</code>.
 * Implementation straight out of Effective Java by Joshua Bloch
 * <p/>
 * Example use case:
 * <pre>
 *  public int hashCode(){
 *    int result = HashCodeUtil.SEED;
 *    //collect the contributions of various fields
 *    result = HashCodeUtil.hash(result, fPrimitive);
 *    result = HashCodeUtil.hash(result, fObject);
 *    result = HashCodeUtil.hash(result, fArray);
 *    return result;
 *  }
 * <pre>
 *
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 * @since 3.0
 */
public final class HashCodeUtil {
    public static final int INVALID_HASHCODE = -1;
    private static final int PRIME_NUMBER = 37;
    private static final int SEED = 23;

    public static int hash(final Object... objects) {
        int result = SEED;
        for (final Object object : objects) {
            if (object instanceof Integer) {
                final Integer integer = (Integer) object;
                result += hash(result, integer.intValue());
            } else if (object instanceof Character) {
                final Character character = (Character) object;
                result += hash(result, character.charValue());
            } else if (object instanceof Boolean) {
                final Boolean aBoolean = (Boolean) object;
                result += hash(result, aBoolean.booleanValue());
            } else if (object instanceof Long) {
                final Long aLong = (Long) object;
                result += hash(result, aLong.longValue());
            } else if (object instanceof Float) {
                final Float aFloat = (Float) object;
                result += hash(result, aFloat.floatValue());
            } else if (object instanceof Double) {
                final Double aDouble = (Double) object;
                result += hash(result, aDouble.doubleValue());
            } else {
                result += hash(result, object);
            }
        }
        return result;
    }

    private static int hash(int seed, boolean aBoolean) {
        return firstTerm(seed) + (aBoolean ? 1 : 0);
    }

    private static int hash(int aSeed, char aChar) {
        return firstTerm(aSeed) + (int) aChar;
    }

    private static int hash(int aSeed, int aInt) {
        return firstTerm(aSeed) + aInt;
    }

    private static int hash(int aSeed, long aLong) {
        return firstTerm(aSeed) + (int) (aLong ^ (aLong >>> 32));
    }

    private static int hash(int aSeed, float aFloat) {
        return hash(aSeed, Float.floatToIntBits(aFloat));
    }

    private static int hash(int aSeed, double aDouble) {
        return hash(aSeed, Double.doubleToLongBits(aDouble));
    }

    private static int hash(int seed, Object object) {
        int result = seed;
        if (object == null) {
            result = hash(result, 0);
        } else if (!isArray(object)) {
            result = hash(result, object.hashCode());
        } else {
            int length = Array.getLength(object);
            for (int idx = 0; idx < length; ++idx) {
                final Object item = Array.get(object, idx);
                result = hash(result, item);
            }
        }
        return result;
    }

    private static int firstTerm(int seed) {
        return PRIME_NUMBER * seed;
    }

    private static boolean isArray(final Object object) {
        return object.getClass().isArray();
    }
}
