package com.example.springsocial.util;

import org.springframework.util.SerializationUtils;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Optional;

/**
 * CookieUtils provides utility methods to get, set, and delete cookies.
 * @author Michael Muzio
 *
 */
public class CookieUtils {

	/**
	 * Get a Cookie by name
	 * @param request The HTTP request
	 * @param name The Cookie name
	 * @return The Cookie (optional)
	 */
    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return Optional.of(cookie);
                }
            }
        }

        return Optional.empty();
    }

    /**
     * Add a new Cookie with a given name and value
     * @param response The HTTP response
     * @param name The Cookie name
     * @param value The Cookie value
     * @param maxAge The maximum age of the Cookie
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * Removes a cookie by name
     * @param request The HTTP request
     * @param response The HTTP response
     * @param name The cookie name
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals(name)) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

    /**
     * Serialize an object using base64 encoding
     * @param object The object
     * @return The base64 encoded object
     */
    public static String serialize(Object object) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(object));
    }

    /**
     * Deserialize a base64 encoded object
     * @param <T> The return object type
     * @param cookie The Cookie
     * @param cls The class
     * @return The decoded object
     */
    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(SerializationUtils.deserialize(
                        Base64.getUrlDecoder().decode(cookie.getValue())));
    }


}
