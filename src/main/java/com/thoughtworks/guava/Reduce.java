package com.thoughtworks.guava;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 6/28/13
 * Time: 12:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class Reduce {
    private Reduce() {

    }

    public static <F,T> T reduce(final Iterable<F> iterable, final Func<F, T> func, T origin) {

        for (Iterator iterator = iterable.iterator(); iterator.hasNext(); ) {
            origin = func.apply((F)(iterator.next()), origin);
        }

        return origin;
    }
}
