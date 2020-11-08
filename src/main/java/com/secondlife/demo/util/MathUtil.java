package com.secondlife.demo.util;

import com.secondlife.demo.advertisement.Advertisement;
import com.secondlife.demo.user.UserProfile;

public class MathUtil {
    public boolean isWithinRadius(UserProfile user, double usrLongitude, double usrLatitude, Advertisement ad) {
        double adLongitude = ad.getLongitude();
        double adLatitude = ad.getLatitude();
        double radius = user.getRadius();
        if((Math.abs(adLongitude - usrLongitude) < radius) && (Math.abs(adLatitude - usrLatitude) < radius)) {
            return true;
        }
        return false;
    }
}
