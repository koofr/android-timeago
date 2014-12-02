package net.koofr.android.timeago;

import java.util.Date;

import android.content.Context;
import android.content.res.Resources;

public class TimeAgo {

	protected Context context;

	public TimeAgo(Context context) {
		this.context = context;
	}

	public String timeAgo(Date date) {
		return timeAgo(date.getTime());
	}

	public String timeAgo(long millis) {
		long diff = new Date().getTime() - millis;

		Resources r = context.getResources();

		String prefix = r.getString(R.string.time_ago_prefix);
		String suffix = r.getString(R.string.time_ago_suffix);

		double seconds = Math.abs(diff) / 1000;
		double minutes = seconds / 60;
		double hours = minutes / 60;
		double days = hours / 24;
		double years = days / 365;

		String words;

		if (seconds < 45) {
			words = r.getString(R.string.time_ago_seconds, (int)Math.round(seconds));
		} else if (seconds < 90) {
			words = r.getString(R.string.time_ago_minute, 1);
		} else if (minutes < 45) {
			words = r.getQuantityString(R.plurals.time_ago_minutes,
					(int)Math.round(minutes), (int)Math.round(minutes));
		} else if (minutes < 90) {
			words = r.getString(R.string.time_ago_hour, 1);
		} else if (hours < 24) {
			words = r.getQuantityString(R.plurals.time_ago_hours,
					(int)Math.round(hours), (int)Math.round(hours));
		} else if (hours < 42) {
			words = r.getString(R.string.time_ago_day, 1);
		} else if (days < 30) {
			words = r.getQuantityString(R.plurals.time_ago_days,
					(int)Math.round(days), (int)Math.round(days));
		} else if (days < 45) {
			words = r.getString(R.string.time_ago_month, 1);
		} else if (days < 365) {
			words = r.getQuantityString(R.plurals.time_ago_months,
					(int)Math.round(days / 30), (int)Math.round(days / 30));
		} else if (years < 1.5) {
			words = r.getString(R.string.time_ago_year, 1);
		} else {
			words = r.getQuantityString(R.plurals.time_ago_years,
					(int)Math.round(years), (int)Math.round(years));
		}

		StringBuilder sb = new StringBuilder();

		if (prefix != null && prefix.length() > 0) {
			sb.append(prefix).append(" ");
		}

		sb.append(words);

		if (suffix != null && suffix.length() > 0) {
			sb.append(" ").append(suffix);
		}

		return sb.toString().trim();
	}
}
