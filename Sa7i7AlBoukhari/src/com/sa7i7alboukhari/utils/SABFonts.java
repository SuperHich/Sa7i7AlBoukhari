package com.sa7i7alboukhari.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Sa7i7 Al Boukhari
 * @author HICHEM LAROUSSI - RAMI TRABELSI
 * Copyright (c) 2014 Zad Group. All rights reserved.
 */

public class SABFonts {
	
	private static Typeface MOHAND;
	
	public static void InitSABFonts(Context context){
		MOHAND  = Typeface.createFromAsset(context.getAssets(), "fonts/ae_AlMohanad.ttf");
	}

	public static Typeface getMOHANDFont() {
		return MOHAND;
	}
}
