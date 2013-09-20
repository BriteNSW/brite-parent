package com.britensw.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * // TODO: Document this
 *
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 * @since 3.0
 */
public class CookieUtil {

    public static String getCookie(final HttpServletRequest request, final String name) {
        final Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (final Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
