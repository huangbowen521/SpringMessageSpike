package com.thoughtworks.config;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.sun.istack.internal.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.indexOf;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 5/27/13
 * Time: 9:27 PM
 */
public class SessionMessageSource extends ResourceBundleMessageSource {

    static final Logger logger = LoggerFactory.getLogger(SessionMessageSource.class);

    private String[] basenames = new String[0];

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        final String brand = (String) attr.getAttribute("brand", RequestAttributes.SCOPE_SESSION);

        logger.debug(brand);

        ArrayList<String> basenameList = Lists.newArrayList(basenames);

        for(String basename : basenameList) {
            if (StringUtils.endsWithIgnoreCase(basename,brand)) {
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
        }
        else {
            this.basenames = new String[0];
        }
        super.setBasenames(basenames);
    }

    private String getStringOrNull(ResourceBundle bundle, String key) {
        try {
            return bundle.getString(key);
        }
        catch (MissingResourceException ex) {
            // Assume key not found
            // -> do NOT throw the exception to allow for checking parent message source.
            return null;
        }
    }
}
