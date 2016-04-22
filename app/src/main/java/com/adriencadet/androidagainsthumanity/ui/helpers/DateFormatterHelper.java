package com.adriencadet.androidagainsthumanity.ui.helpers;

import android.text.format.DateUtils;

import org.joda.time.DateTime;

/**
 * DateFormatterHelper
 * <p>
 */
public class DateFormatterHelper {
    public static String timeAgo(DateTime time) {
        return DateUtils
            .getRelativeTimeSpanString(
                time.toDate().getTime(),
                DateTime.now().toDate().getTime(),
                DateUtils.SECOND_IN_MILLIS
            )
            .toString();
    }
}
