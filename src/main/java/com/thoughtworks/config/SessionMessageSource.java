/*
 * Copyright (c) 2013.
 */

package com.thoughtworks.config;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import static org.slf4j.LoggerFactory.getLogger;

/*
*
*
 */
public class SessionMessageSource extends ResourceBundleMessageSource {

    private static final Logger logger = getLogger(SessionMessageSource.class);

    private String[] basenames = new String[0];

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        final String brand = (String) attr.getAttribute("brand", RequestAttributes.SCOPE_SESSION);

        logger.debug(brand);

        ArrayList<String> basenameList = Lists.newArrayList(basenames);

        for (String basename : basenameList) {
            if (StringUtils.endsWithIgnoreCase(basename, brand)) {
                ResourceBundle bundle = getResourceBundle(basename, locale);
                if (bundle != null) {
                    return getStringOrNull(bundle, code);
                }
            }
        }

        return null;
    }

    @Override
    public void setBasenames(String... basenames) {
        if (basenames != null) {
            this.basenames = new String[basenames.length];
            for (int i = 0; i < basenames.length; i++) {
                String basename = basenames[i];
                Assert.hasText(basename, "Basename must not be empty");
                this.basenames[i] = basename.trim();
            }
        } else {
            this.basenames = new String[0];
        }
        super.setBasenames(basenames);
    }

    private String getStringOrNull(ResourceBundle bundle, String key) {
        try {
            return bundle.getString(key);
        } catch (MissingResourceException ex) {
            // Assume key not found
            // -> do NOT throw the exception to allow for checking parent message source.
            return null;
        }
    }
}
